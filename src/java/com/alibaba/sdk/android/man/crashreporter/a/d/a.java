package com.alibaba.sdk.android.man.crashreporter.a.d;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;

public class a
{
    public static String a(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        int identifier = 0;
        try {
            identifier = context.getResources().getIdentifier(s, "string", context.getPackageName());
        }
        catch (final Exception ex) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getString Id error", (Throwable)ex);
        }
        if (identifier != 0) {
            return context.getString(identifier);
        }
        return null;
    }
    
    public static void a(final Map<String, String> map, final Context context) {
        if (context != null) {
            try {
                if (!map.containsKey((Object)"pt")) {
                    final String a = a(context, "package_type");
                    if (!TextUtils.isEmpty((CharSequence)a)) {
                        map.put((Object)"pt", (Object)a);
                    }
                }
                if (!map.containsKey((Object)"pid")) {
                    final String a2 = a(context, "project_id");
                    if (!TextUtils.isEmpty((CharSequence)a2)) {
                        map.put((Object)"pid", (Object)a2);
                    }
                }
                if (!map.containsKey((Object)"bid")) {
                    final String a3 = a(context, "build_id");
                    if (!TextUtils.isEmpty((CharSequence)a3)) {
                        map.put((Object)"bid", (Object)a3);
                    }
                }
                if (!map.containsKey((Object)"bv")) {
                    final String a4 = a(context, "base_version");
                    if (!TextUtils.isEmpty((CharSequence)a4)) {
                        map.put((Object)"bv", (Object)a4);
                    }
                }
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("get MetaData err", (Throwable)ex);
            }
        }
    }
    
    public static byte[] b(final Map map) {
        Label_0011: {
            if (map != null) {
                break Label_0011;
            }
            try {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("serializeMetaData err,map is null!");
                return null;
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream)byteArrayOutputStream);
                objectOutputStream.writeObject((Object)map);
                objectOutputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            catch (final Exception ex) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("serializeMetaData err!", (Throwable)ex);
                return null;
            }
        }
    }
    
    public static String d(final Context context) {
        if (context != null) {
            final String a = a(context, "base_version");
            if (!TextUtils.isEmpty((CharSequence)a)) {
                return a;
            }
        }
        return null;
    }
}
