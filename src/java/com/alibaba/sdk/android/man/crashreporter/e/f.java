package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.Reader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public class f
{
    public static void a(final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (final IOException ex) {}
    }
    
    public static void a(final OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        }
        catch (final IOException ex) {}
    }
    
    public static void a(final Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        }
        catch (final IOException ex) {}
    }
}
