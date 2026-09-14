package org.xutils.http.app;

import java.io.InputStream;
import java.lang.reflect.Type;

public abstract class InputStreamResponseParser implements ResponseParser
{
    public abstract Object parse(final Type p0, final Class<?> p1, final InputStream p2) throws Throwable;
    
    @Deprecated
    @Override
    public final Object parse(final Type type, final Class<?> clazz, final String s) throws Throwable {
        return null;
    }
}
