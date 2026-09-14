package org.xutils.http.app;

import java.lang.reflect.Type;
import org.xutils.http.request.UriRequest;

public interface ResponseParser
{
    void checkResponse(final UriRequest p0) throws Throwable;
    
    Object parse(final Type p0, final Class<?> p1, final String p2) throws Throwable;
}
