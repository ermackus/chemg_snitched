package com.tencent.open.log;

import android.util.Log;

public final class e extends Tracer
{
    public static final e a;
    
    static {
        a = new e();
    }
    
    protected void doTrace(final int n, final Thread thread, final long n2, final String s, final String s2, final Throwable t) {
        if (n != 1) {
            if (n != 2) {
                if (n != 4) {
                    if (n != 8) {
                        if (n != 16) {
                            if (n == 32) {
                                Log.e(s, s2, t);
                            }
                        }
                        else {
                            Log.e(s, s2, t);
                        }
                    }
                    else {
                        Log.w(s, s2, t);
                    }
                }
                else {
                    Log.i(s, s2, t);
                }
            }
            else {
                Log.d(s, s2, t);
            }
        }
        else {
            Log.v(s, s2, t);
        }
    }
}
