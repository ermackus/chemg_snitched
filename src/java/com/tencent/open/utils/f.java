package com.tencent.open.utils;

import java.io.File;
import android.content.Context;

public final class f
{
    private static Context a;
    
    public static final Context a() {
        Context a;
        if ((a = f.a) == null) {
            a = null;
        }
        return a;
    }
    
    public static final File a(final String s) {
        return k.h(a(), s);
    }
    
    public static final void a(final Context a) {
        f.a = a;
    }
    
    public static final String b() {
        if (a() == null) {
            return "";
        }
        return a().getPackageName();
    }
    
    public static final File c() {
        if (a() == null) {
            return null;
        }
        return a().getFilesDir();
    }
    
    public static final File d() {
        final Context a = a();
        File cacheDir;
        if (a != null) {
            cacheDir = a.getCacheDir();
        }
        else {
            cacheDir = null;
        }
        return cacheDir;
    }
    
    public static final File e() {
        return a((String)null);
    }
}
