package org.xutils.http.body;

import java.io.OutputStream;
import java.io.IOException;
import java.util.Iterator;
import android.net.Uri;
import android.text.TextUtils;
import org.xutils.common.util.KeyValue;
import java.util.List;

public class UrlEncodedParamsBody implements RequestBody
{
    private String charset;
    private byte[] content;
    
    public UrlEncodedParamsBody(final List<KeyValue> list, final String charset) throws IOException {
        this.charset = "UTF-8";
        if (!TextUtils.isEmpty((CharSequence)charset)) {
            this.charset = charset;
        }
        final StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (final KeyValue keyValue : list) {
                final String key = keyValue.key;
                final String valueStr = keyValue.getValueStr();
                if (!TextUtils.isEmpty((CharSequence)key) && valueStr != null) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(Uri.encode(key, this.charset));
                    sb.append("=");
                    sb.append(Uri.encode(valueStr, this.charset));
                }
            }
        }
        this.content = sb.toString().getBytes(this.charset);
    }
    
    @Override
    public long getContentLength() {
        return this.content.length;
    }
    
    @Override
    public String getContentType() {
        final StringBuilder sb = new StringBuilder();
        sb.append("application/x-www-form-urlencoded;charset=");
        sb.append(this.charset);
        return sb.toString();
    }
    
    @Override
    public void setContentType(final String s) {
    }
    
    @Override
    public void writeTo(final OutputStream outputStream) throws IOException {
        outputStream.write(this.content);
        outputStream.flush();
    }
}
