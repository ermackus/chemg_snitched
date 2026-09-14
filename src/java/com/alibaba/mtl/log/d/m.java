package com.alibaba.mtl.log.d;

import android.content.SharedPreferences$Editor;
import java.io.UnsupportedEncodingException;
import android.text.TextUtils;
import android.content.Context;
import java.util.Random;

public class m
{
    private static final Random a;
    
    static {
        a = new Random();
    }
    
    public static String getImei(final Context context) {
        if (context != null) {
            try {
                final String string = context.getSharedPreferences("UTCommon", 0).getString("_ie", "");
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    final String s = new String(c.decode(string.getBytes(), 2), "UTF-8");
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        return s;
                    }
                }
            }
            catch (final Exception ex) {}
        }
        String uniqueID = null;
        if (TextUtils.isEmpty((CharSequence)null)) {
            uniqueID = getUniqueID();
        }
        if (context != null) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("UTCommon", 0).edit();
                edit.putString("_ie", new String(c.encode(uniqueID.getBytes("UTF-8"), 2)));
                edit.commit();
            }
            catch (final UnsupportedEncodingException ex2) {
                ex2.printStackTrace();
            }
        }
        return uniqueID;
    }
    
    public static String getImsi(final Context context) {
        if (context != null) {
            try {
                final String string = context.getSharedPreferences("UTCommon", 0).getString("_is", "");
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    final String s = new String(c.decode(string.getBytes(), 2), "UTF-8");
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        return s;
                    }
                }
            }
            catch (final Exception ex) {}
        }
        String uniqueID = null;
        if (TextUtils.isEmpty((CharSequence)null)) {
            uniqueID = getUniqueID();
        }
        if (context != null) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("UTCommon", 0).edit();
                edit.putString("_is", new String(c.encode(uniqueID.getBytes("UTF-8"), 2)));
                edit.commit();
            }
            catch (final UnsupportedEncodingException ex2) {
                ex2.printStackTrace();
            }
        }
        return uniqueID;
    }
    
    public static final String getUniqueID() {
        final int n = (int)(System.currentTimeMillis() / 1000L);
        final int n2 = (int)System.nanoTime();
        final int nextInt = m.a.nextInt();
        final int nextInt2 = m.a.nextInt();
        final byte[] bytes = f.getBytes(n);
        final byte[] bytes2 = f.getBytes(n2);
        final byte[] bytes3 = f.getBytes(nextInt);
        final byte[] bytes4 = f.getBytes(nextInt2);
        final byte[] array = new byte[16];
        System.arraycopy((Object)bytes, 0, (Object)array, 0, 4);
        System.arraycopy((Object)bytes2, 0, (Object)array, 4, 4);
        System.arraycopy((Object)bytes3, 0, (Object)array, 8, 4);
        System.arraycopy((Object)bytes4, 0, (Object)array, 12, 4);
        return c.encodeToString(array, 2);
    }
}
