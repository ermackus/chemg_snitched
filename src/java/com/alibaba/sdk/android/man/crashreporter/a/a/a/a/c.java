package com.alibaba.sdk.android.man.crashreporter.a.a.a.a;

import android.os.Build;
import android.os.Build$VERSION;
import com.alibaba.sdk.android.man.crashreporter.global.a;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.b;

public class c implements b
{
    private static String e() {
        if (i.b((CharSequence)System.getProperty("java.vm.name")) && System.getProperty("java.vm.name").toLowerCase().contains((CharSequence)"lemur")) {
            return "aliyunos";
        }
        if (i.b((CharSequence)"ro.yunos.version")) {
            return "aliyunos";
        }
        return "Android";
    }
    
    public void a(final Map<a, String> map) {
        map.put((Object)a.n, (Object)e());
        map.put((Object)a.o, (Object)Build$VERSION.RELEASE);
        map.put((Object)a.p, (Object)Build$VERSION.CODENAME);
        map.put((Object)a.k, (Object)Build.BRAND);
        map.put((Object)a.m, (Object)Build.MODEL);
    }
}
