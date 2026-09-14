package org.xutils.http.body;

import java.util.Iterator;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import java.io.FileInputStream;
import org.xutils.common.Callback$CancelledException;
import java.io.InputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.OutputStream;
import android.text.TextUtils;
import org.xutils.common.util.KeyValue;
import java.util.List;
import org.xutils.http.ProgressHandler;

public class MultipartBody implements ProgressBody
{
    private static byte[] BOUNDARY_PREFIX_BYTES;
    private static byte[] END_BYTES;
    private static byte[] TWO_DASHES_BYTES;
    private byte[] boundaryPostfixBytes;
    private ProgressHandler callBackHandler;
    private String charset;
    private String contentType;
    private long current;
    private List<KeyValue> multipartParams;
    private long total;
    
    static {
        MultipartBody.BOUNDARY_PREFIX_BYTES = "--------7da3d81520810".getBytes();
        MultipartBody.END_BYTES = "\r\n".getBytes();
        MultipartBody.TWO_DASHES_BYTES = "--".getBytes();
    }
    
    public MultipartBody(final List<KeyValue> multipartParams, final String charset) {
        this.charset = "UTF-8";
        this.total = 0L;
        this.current = 0L;
        if (!TextUtils.isEmpty((CharSequence)charset)) {
            this.charset = charset;
        }
        this.multipartParams = multipartParams;
        this.generateContentType();
        final MultipartBody.MultipartBody$CounterOutputStream multipartBody$CounterOutputStream = new MultipartBody.MultipartBody$CounterOutputStream(this);
        try {
            this.writeTo((OutputStream)multipartBody$CounterOutputStream);
            this.total = multipartBody$CounterOutputStream.total.get();
        }
        catch (final IOException ex) {
            this.total = -1L;
        }
    }
    
    private static byte[] buildContentDisposition(final String s, final String s2, final String s3) throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder("Content-Disposition: form-data");
        sb.append("; name=\"");
        sb.append(s.replace((CharSequence)"\"", (CharSequence)"\\\""));
        sb.append("\"");
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            sb.append("; filename=\"");
            sb.append(s2.replace((CharSequence)"\"", (CharSequence)"\\\""));
            sb.append("\"");
        }
        return sb.toString().getBytes(s3);
    }
    
    private static byte[] buildContentType(final Object o, final String s, final String s2) throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder("Content-Type: ");
        String s3;
        if (TextUtils.isEmpty((CharSequence)s)) {
            if (o instanceof String) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("text/plain; charset=");
                sb2.append(s2);
                s3 = sb2.toString();
            }
            else {
                s3 = "application/octet-stream";
            }
        }
        else {
            s3 = s.replaceFirst("\\/jpg$", "/jpeg");
        }
        sb.append(s3);
        return sb.toString().getBytes(s2);
    }
    
    private void generateContentType() {
        final String hexString = Double.toHexString(Math.random() * 65535.0);
        this.boundaryPostfixBytes = hexString.getBytes();
        final StringBuilder sb = new StringBuilder();
        sb.append("multipart/form-data; boundary=");
        sb.append(new String(MultipartBody.BOUNDARY_PREFIX_BYTES));
        sb.append(hexString);
        this.contentType = sb.toString();
    }
    
    private void writeEntry(final OutputStream outputStream, final String s, final Object o) throws IOException {
        this.writeLine(outputStream, new byte[][] { MultipartBody.TWO_DASHES_BYTES, MultipartBody.BOUNDARY_PREFIX_BYTES, this.boundaryPostfixBytes });
        Object value;
        String fileName;
        String contentType;
        if (o instanceof BodyItemWrapper) {
            final BodyItemWrapper bodyItemWrapper = (BodyItemWrapper)o;
            value = bodyItemWrapper.getValue();
            fileName = bodyItemWrapper.getFileName();
            contentType = bodyItemWrapper.getContentType();
        }
        else {
            fileName = "";
            final CharSequence charSequence = null;
            value = o;
            contentType = (String)charSequence;
        }
        if (value instanceof File) {
            final File file = (File)value;
            String name = fileName;
            if (TextUtils.isEmpty((CharSequence)fileName)) {
                name = file.getName();
            }
            String fileContentType = contentType;
            if (TextUtils.isEmpty((CharSequence)contentType)) {
                fileContentType = FileBody.getFileContentType(file);
            }
            this.writeLine(outputStream, new byte[][] { buildContentDisposition(s, name, this.charset) });
            this.writeLine(outputStream, new byte[][] { buildContentType(value, fileContentType, this.charset) });
            this.writeLine(outputStream);
            this.writeFile(outputStream, file);
            this.writeLine(outputStream);
        }
        else {
            this.writeLine(outputStream, new byte[][] { buildContentDisposition(s, fileName, this.charset) });
            this.writeLine(outputStream, new byte[][] { buildContentType(value, contentType, this.charset) });
            this.writeLine(outputStream);
            if (value instanceof InputStream) {
                this.writeStreamAndCloseIn(outputStream, (InputStream)value);
                this.writeLine(outputStream);
            }
            else {
                byte[] bytes;
                if (value instanceof byte[]) {
                    bytes = (byte[])value;
                }
                else {
                    bytes = String.valueOf(value).getBytes(this.charset);
                }
                this.writeLine(outputStream, new byte[][] { bytes });
                final long current = this.current + bytes.length;
                this.current = current;
                final ProgressHandler callBackHandler = this.callBackHandler;
                if (callBackHandler != null) {
                    if (!callBackHandler.updateProgress(this.total, current, false)) {
                        throw new Callback$CancelledException("upload stopped!");
                    }
                }
            }
        }
    }
    
    private void writeFile(final OutputStream outputStream, final File file) throws IOException {
        if (outputStream instanceof MultipartBody.MultipartBody$CounterOutputStream) {
            ((MultipartBody.MultipartBody$CounterOutputStream)outputStream).addFile(file);
        }
        else {
            this.writeStreamAndCloseIn(outputStream, (InputStream)new FileInputStream(file));
        }
    }
    
    private void writeLine(final OutputStream outputStream, final byte[]... array) throws IOException {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                outputStream.write(array[i]);
            }
        }
        outputStream.write(MultipartBody.END_BYTES);
    }
    
    private void writeStreamAndCloseIn(final OutputStream outputStream, final InputStream inputStream) throws IOException {
        if (outputStream instanceof MultipartBody.MultipartBody$CounterOutputStream) {
            ((MultipartBody.MultipartBody$CounterOutputStream)outputStream).addStream(inputStream);
            return;
        }
        try {
            final byte[] array = new byte[1024];
            while (true) {
                final int read = inputStream.read(array);
                if (read < 0) {
                    return;
                }
                outputStream.write(array, 0, read);
                final long current = this.current + read;
                this.current = current;
                if (this.callBackHandler == null) {
                    continue;
                }
                if (this.callBackHandler.updateProgress(this.total, current, false)) {
                    continue;
                }
                throw new Callback$CancelledException("upload stopped!");
            }
        }
        finally {
            IOUtil.closeQuietly((Closeable)inputStream);
        }
    }
    
    public long getContentLength() {
        return this.total;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(final String s) {
        final int index = this.contentType.indexOf(";");
        final StringBuilder sb = new StringBuilder();
        sb.append("multipart/");
        sb.append(s);
        sb.append(this.contentType.substring(index));
        this.contentType = sb.toString();
    }
    
    public void setProgressHandler(final ProgressHandler callBackHandler) {
        this.callBackHandler = callBackHandler;
    }
    
    public void writeTo(final OutputStream outputStream) throws IOException {
        final ProgressHandler callBackHandler = this.callBackHandler;
        if (callBackHandler != null && !callBackHandler.updateProgress(this.total, this.current, true)) {
            throw new Callback$CancelledException("upload stopped!");
        }
        for (final KeyValue keyValue : this.multipartParams) {
            final String key = keyValue.key;
            final Object value = keyValue.value;
            if (!TextUtils.isEmpty((CharSequence)key) && value != null) {
                this.writeEntry(outputStream, key, value);
            }
        }
        final byte[] two_DASHES_BYTES = MultipartBody.TWO_DASHES_BYTES;
        this.writeLine(outputStream, new byte[][] { two_DASHES_BYTES, MultipartBody.BOUNDARY_PREFIX_BYTES, this.boundaryPostfixBytes, two_DASHES_BYTES });
        outputStream.flush();
        final ProgressHandler callBackHandler2 = this.callBackHandler;
        if (callBackHandler2 != null) {
            final long total = this.total;
            callBackHandler2.updateProgress(total, total, true);
        }
    }
}
