package com.alipay.sdk.m.y;

import java.security.MessageDigest;
import com.alipay.sdk.m.z.a;

public final class b
{
    public static String a(String string) {
        try {
            if (a.a(string)) {
                return null;
            }
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(string.getBytes("UTF-8"));
            final byte[] digest = instance.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(String.format("%02x", new Object[] { digest[i] }));
            }
            string = sb.toString();
            return string;
        }
        catch (final Exception ex) {
            return null;
        }
    }
}
