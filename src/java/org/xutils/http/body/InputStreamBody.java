package org.xutils.http.body;

import java.io.IOException;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import org.xutils.common.Callback$CancelledException;
import java.io.OutputStream;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import org.xutils.http.ProgressHandler;

public class InputStreamBody implements ProgressBody
{
    private ProgressHandler callBackHandler;
    private InputStream content;
    private String contentType;
    private long current;
    private final long total;
    
    public InputStreamBody(final InputStream inputStream) {
        this(inputStream, null);
    }
    
    public InputStreamBody(final InputStream content, final String contentType) {
        this.current = 0L;
        this.content = content;
        this.contentType = contentType;
        this.total = getInputStreamLength(content);
    }
    
    public static long getInputStreamLength(final InputStream inputStream) {
        try {
            if (inputStream instanceof FileInputStream || inputStream instanceof ByteArrayInputStream) {
                return inputStream.available();
            }
            return -1L;
        }
        finally {
            return -1L;
        }
    }
    
    public long getContentLength() {
        return this.total;
    }
    
    public String getContentType() {
        String contentType;
        if (TextUtils.isEmpty((CharSequence)this.contentType)) {
            contentType = "application/octet-stream";
        }
        else {
            contentType = this.contentType;
        }
        return contentType;
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public void setProgressHandler(final ProgressHandler callBackHandler) {
        this.callBackHandler = callBackHandler;
    }
    
    public void writeTo(final OutputStream outputStream) throws IOException {
        final ProgressHandler callBackHandler = this.callBackHandler;
        if (callBackHandler != null && !callBackHandler.updateProgress(this.total, this.current, true)) {
            throw new Callback$CancelledException("upload stopped!");
        }
        final byte[] array = new byte[1024];
        try {
            while (true) {
                final int read = this.content.read(array);
                if (read == -1) {
                    outputStream.flush();
                    if (this.callBackHandler != null) {
                        this.callBackHandler.updateProgress(this.total, this.total, true);
                    }
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
            IOUtil.closeQuietly((Closeable)this.content);
        }
    }
}
