package com.alipay.sdk.m.b0;

import java.io.File;
import com.alipay.sdk.m.z.a;

public final class b
{
    public static String a(final String s) {
        String s2;
        try {
            f.a(s);
        }
        finally {
            s2 = "";
        }
        String a = s2;
        if (com.alipay.sdk.m.z.a.a(s2)) {
            final StringBuilder sb = new StringBuilder(".SystemConfig");
            sb.append(File.separator);
            sb.append(s);
            a = c.a(sb.toString());
        }
        return a;
    }
}
