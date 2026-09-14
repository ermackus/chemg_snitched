package org.xutils.http.body;

import android.text.TextUtils;

public final class BodyItemWrapper
{
    private final String contentType;
    private final String fileName;
    private final Object value;
    
    public BodyItemWrapper(final Object o, final String s) {
        this(o, s, null);
    }
    
    public BodyItemWrapper(final Object value, final String contentType, final String fileName) {
        this.value = value;
        if (TextUtils.isEmpty((CharSequence)contentType)) {
            this.contentType = "application/octet-stream";
        }
        else {
            this.contentType = contentType;
        }
        this.fileName = fileName;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public Object getValue() {
        return this.value;
    }
}
