package org.xutils.http.body;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestBody
{
    long getContentLength();
    
    String getContentType();
    
    void setContentType(final String p0);
    
    void writeTo(final OutputStream p0) throws IOException;
}
