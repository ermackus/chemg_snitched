package org.xutils.http.body;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import android.text.TextUtils;

public class StringBody implements RequestBody
{
    private String charset;
    private byte[] content;
    private String contentType;
    
    public StringBody(final String s, final String charset) throws UnsupportedEncodingException {
        this.charset = "UTF-8";
        if (!TextUtils.isEmpty((CharSequence)charset)) {
            this.charset = charset;
        }
        this.content = s.getBytes(this.charset);
    }
    
    @Override
    public long getContentLength() {
        return this.content.length;
    }
    
    @Override
    public String getContentType() {
        String s;
        if (TextUtils.isEmpty((CharSequence)this.contentType)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("application/json;charset=");
            sb.append(this.charset);
            s = sb.toString();
        }
        else {
            s = this.contentType;
        }
        return s;
    }
    
    @Override
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        outputStream.write(this.content);
        outputStream.flush();
    }
}
