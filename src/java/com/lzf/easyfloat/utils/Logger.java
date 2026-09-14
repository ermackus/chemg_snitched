package com.lzf.easyfloat.utils;

import android.util.Log;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e" }, d2 = { "Lcom/lzf/easyfloat/utils/Logger;", "", "()V", "logEnable", "", "tag", "", "d", "", "msg", "e", "i", "v", "w", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class Logger
{
    public static final Logger INSTANCE;
    private static boolean logEnable;
    private static String tag;
    
    static {
        INSTANCE = new Logger();
        Logger.tag = "EasyFloat--->";
    }
    
    private Logger() {
    }
    
    public final void d(final Object o) {
        Intrinsics.checkNotNullParameter(o, "msg");
        this.d(Logger.tag, o.toString());
    }
    
    public final void d(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "msg");
        if (Logger.logEnable) {
            Log.d(s, s2);
        }
    }
    
    public final void e(final Object o) {
        Intrinsics.checkNotNullParameter(o, "msg");
        this.e(Logger.tag, o.toString());
    }
    
    public final void e(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "msg");
        if (Logger.logEnable) {
            Log.e(s, s2);
        }
    }
    
    public final void i(final Object o) {
        Intrinsics.checkNotNullParameter(o, "msg");
        this.i(Logger.tag, o.toString());
    }
    
    public final void i(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "msg");
        if (Logger.logEnable) {
            Log.i(s, s2);
        }
    }
    
    public final void v(final Object o) {
        Intrinsics.checkNotNullParameter(o, "msg");
        this.v(Logger.tag, o.toString());
    }
    
    public final void v(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "msg");
        if (Logger.logEnable) {
            Log.v(s, s2);
        }
    }
    
    public final void w(final Object o) {
        Intrinsics.checkNotNullParameter(o, "msg");
        this.w(Logger.tag, o.toString());
    }
    
    public final void w(final String s, final String s2) {
        Intrinsics.checkNotNullParameter((Object)s, "tag");
        Intrinsics.checkNotNullParameter((Object)s2, "msg");
        if (Logger.logEnable) {
            Log.w(s, s2);
        }
    }
}
