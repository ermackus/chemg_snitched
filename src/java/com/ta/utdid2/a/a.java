package com.ta.utdid2.a;

import com.ta.utdid2.b.a.j;
import com.ta.utdid2.b.a.i;
import com.ta.utdid2.b.a.f;
import android.util.Log;
import android.content.Context;

public class a
{
    private static final String TAG;
    private static a a;
    private Context mContext;
    
    static {
        TAG = a.class.getName();
    }
    
    private a(final Context mContext) {
        this.mContext = mContext;
    }
    
    public static a a(final Context context) {
        synchronized (a.class) {
            if (com.ta.utdid2.a.a.a == null) {
                com.ta.utdid2.a.a.a = new a(context);
            }
            return com.ta.utdid2.a.a.a;
        }
    }
    
    private String b(final String s, final String s2, final String s3) {
        synchronized (this) {
            if (this.mContext == null) {
                Log.e(com.ta.utdid2.a.a.TAG, "no context!");
                return "";
            }
            String a = "";
            if (f.a(this.mContext)) {
                a = b.a(this.mContext).a(s, s2, s3, c.a(this.mContext, s, s2));
            }
            c.a(this.mContext, s, a, s2);
            return a;
        }
    }
    
    public String a(final String s, final String s2, String tag) {
        if (this.mContext == null || i.a(s) || i.a(s2)) {
            tag = com.ta.utdid2.a.a.TAG;
            final StringBuilder sb = new StringBuilder("mContext:");
            sb.append((Object)this.mContext);
            sb.append("; has appName:");
            sb.append(i.a(s) ^ true);
            sb.append("; has token:");
            sb.append(i.a(s2) ^ true);
            Log.e(tag, sb.toString());
            return "";
        }
        final String a = c.a(this.mContext, s, s2);
        if (!i.a(a) && j.a(c.a(this.mContext, s, s2), 1)) {
            return a;
        }
        if (f.a(this.mContext)) {
            return this.b(s, s2, tag);
        }
        return a;
    }
    
    public void a(final String s, final String s2, String tag, final com.ut.device.a a) {
        if (a == null) {
            Log.e(a.TAG, "callback is null!");
            return;
        }
        if (this.mContext != null && !i.a(s) && !i.a(s2)) {
            final String a2 = c.a(this.mContext, s, s2);
            if (!i.a(a2) && j.a(c.a(this.mContext, s, s2), 1)) {
                a.a(1001, a2);
            }
            else if (f.a(this.mContext)) {
                b.a(this.mContext).a(s, s2, tag, a2, a);
            }
            else {
                a.a(1003, a2);
            }
            return;
        }
        tag = a.TAG;
        final StringBuilder sb = new StringBuilder("mContext:");
        sb.append((Object)this.mContext);
        sb.append("; callback:");
        sb.append((Object)a);
        sb.append("; has appName:");
        sb.append(i.a(s) ^ true);
        sb.append("; has token:");
        sb.append(i.a(s2) ^ true);
        Log.e(tag, sb.toString());
        a.a(1002, "");
    }
}
