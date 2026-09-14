package com.alipay.sdk.m.c0;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import com.alipay.sdk.m.g0.a;
import java.io.File;

public final class b
{
    public File a;
    public a b;
    
    public b(final String s, final a b) {
        this.a = null;
        this.b = null;
        this.a = new File(s);
        this.b = b;
    }
    
    public static String a(final String s) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", (Object)"id");
            jsonObject.put("error", (Object)s);
            return jsonObject.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    private void b() {
        synchronized (this) {
            final File a = this.a;
            if (a == null) {
                return;
            }
            if (a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
                final ArrayList list = new ArrayList();
                final String[] list2 = this.a.list();
                final int length = list2.length;
                final int n = 0;
                for (int i = 0; i < length; ++i) {
                    ((List)list).add((Object)list2[i]);
                }
                Collections.sort((List)list);
                final String s = (String)((List)list).get(((List)list).size() - 1);
                final int size = ((List)list).size();
                final String format = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                final StringBuilder sb = new StringBuilder();
                sb.append(format);
                sb.append(".log");
                String s2 = s;
                int n2 = size;
                if (s.equals((Object)sb.toString())) {
                    if (((List)list).size() < 2) {
                        return;
                    }
                    s2 = (String)((List)list).get(((List)list).size() - 2);
                    n2 = size - 1;
                }
                final String a2 = a(com.alipay.sdk.m.z.b.a(this.a.getAbsolutePath(), s2));
                int n3 = n2;
                int j = n;
                if (!this.b.logCollect(a2)) {
                    n3 = n2 - 1;
                    j = n;
                }
                while (j < n3) {
                    new File(this.a, (String)((List)list).get(j)).delete();
                    ++j;
                }
            }
        }
    }
    
    public final void a() {
        new Thread((Runnable)new c(this)).start();
    }
}
