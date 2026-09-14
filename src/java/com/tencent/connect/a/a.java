package com.tencent.connect.a;

import com.tencent.open.b.e;
import android.text.TextUtils;
import com.tencent.open.utils.g;
import com.tencent.open.log.SLog;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import java.lang.reflect.Method;

public class a
{
    private static Class<?> a;
    private static Class<?> b;
    private static Method c;
    private static Method d;
    private static Method e;
    private static Method f;
    private static boolean g;
    
    public static void a(final Context context, final QQToken qqToken, final String s, final String... array) {
        if (!com.tencent.connect.a.a.g) {
            return;
        }
        b(context, qqToken);
        try {
            com.tencent.connect.a.a.d.invoke((Object)com.tencent.connect.a.a.b, new Object[] { context, s, array });
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("trackCustomEvent exception: ");
            sb.append(ex.toString());
            SLog.e("OpenConfig", sb.toString());
        }
    }
    
    public static boolean a(final Context context, final QQToken qqToken) {
        return com.tencent.open.utils.g.a(context, qqToken.getAppId()).b("Common_ta_enable");
    }
    
    public static void b(final Context context, final QQToken qqToken) {
        try {
            if (a(context, qqToken)) {
                com.tencent.connect.a.a.f.invoke((Object)com.tencent.connect.a.a.a, new Object[] { true });
            }
            else {
                com.tencent.connect.a.a.f.invoke((Object)com.tencent.connect.a.a.a, new Object[] { false });
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("checkStatStatus exception: ");
            sb.append(ex.toString());
            SLog.e("OpenConfig", sb.toString());
        }
    }
    
    public static void c(final Context context, final QQToken qqToken) {
        final String appId = qqToken.getAppId();
        final StringBuilder sb = new StringBuilder();
        sb.append("Aqc");
        sb.append(appId);
        final String string = sb.toString();
        try {
            com.tencent.connect.a.a.a = Class.forName("com.tencent.stat.StatConfig");
            com.tencent.connect.a.a.c = (com.tencent.connect.a.a.b = Class.forName("com.tencent.stat.StatService")).getMethod("reportQQ", Context.class, String.class);
            com.tencent.connect.a.a.d = com.tencent.connect.a.a.b.getMethod("trackCustomEvent", Context.class, String.class, String[].class);
            com.tencent.connect.a.a.e = com.tencent.connect.a.a.b.getMethod("commitEvents", Context.class, Integer.TYPE);
            com.tencent.connect.a.a.f = com.tencent.connect.a.a.a.getMethod("setEnableStatService", Boolean.TYPE);
            b(context, qqToken);
            com.tencent.connect.a.a.a.getMethod("setAutoExceptionCaught", Boolean.TYPE).invoke((Object)com.tencent.connect.a.a.a, new Object[] { false });
            com.tencent.connect.a.a.a.getMethod("setEnableSmartReporting", Boolean.TYPE).invoke((Object)com.tencent.connect.a.a.a, new Object[] { true });
            com.tencent.connect.a.a.a.getMethod("setSendPeriodMinutes", Integer.TYPE).invoke((Object)com.tencent.connect.a.a.a, new Object[] { 1440 });
            final Class<?> forName = Class.forName("com.tencent.stat.StatReportStrategy");
            com.tencent.connect.a.a.a.getMethod("setStatSendStrategy", forName).invoke((Object)com.tencent.connect.a.a.a, new Object[] { forName.getField("PERIOD").get((Object)null) });
            com.tencent.connect.a.a.b.getMethod("startStatService", Context.class, String.class, String.class).invoke((Object)com.tencent.connect.a.a.b, new Object[] { context, string, Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get((Object)null) });
            com.tencent.connect.a.a.g = true;
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("start4QQConnect exception: ");
            sb2.append(ex.toString());
            SLog.e("OpenConfig", sb2.toString());
        }
    }
    
    public static void d(final Context context, final QQToken qqToken) {
        if (!TextUtils.isEmpty((CharSequence)qqToken.getOpenId())) {
            com.tencent.open.b.e.a().a(qqToken.getOpenId(), qqToken.getAppId(), "2", "1", "11", "0", "0", "0");
        }
        if (!com.tencent.connect.a.a.g) {
            return;
        }
        b(context, qqToken);
        if (qqToken.getOpenId() != null) {
            try {
                com.tencent.connect.a.a.c.invoke((Object)com.tencent.connect.a.a.b, new Object[] { context, qqToken.getOpenId() });
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("reportQQ exception: ");
                sb.append(ex.toString());
                SLog.e("OpenConfig", sb.toString());
            }
        }
    }
}
