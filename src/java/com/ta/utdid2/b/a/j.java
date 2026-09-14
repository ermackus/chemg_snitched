package com.ta.utdid2.b.a;

import android.util.Log;

public class j
{
    public static final String TAG;
    
    static {
        TAG = j.class.getName();
    }
    
    public static boolean a(final long n, final int n2) {
        final boolean b = (System.currentTimeMillis() - n) / 86400000L < n2;
        if (d.e) {
            final String tag = j.TAG;
            final StringBuilder sb = new StringBuilder("isUpToDate: ");
            sb.append(b);
            sb.append("; oldTimestamp: ");
            sb.append(n);
            sb.append("; currentTimestamp");
            sb.append(System.currentTimeMillis());
            Log.d(tag, sb.toString());
        }
        return b;
    }
}
