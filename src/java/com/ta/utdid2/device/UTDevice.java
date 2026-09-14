package com.ta.utdid2.device;

import com.ta.utdid2.b.a.i;
import android.content.Context;

public class UTDevice
{
    public static String getUtdid(final Context context) {
        final a b = com.ta.utdid2.device.b.b(context);
        String f;
        if (b != null && !i.a(b.f())) {
            f = b.f();
        }
        else {
            f = "ffffffffffffffffffffffff";
        }
        return f;
    }
    
    public static String getUtdidForUpdate(final Context context) {
        final String h = c.a(context).h();
        if (h != null) {
            final String s = h;
            if (!i.a(h)) {
                return s;
            }
        }
        return "ffffffffffffffffffffffff";
    }
}
