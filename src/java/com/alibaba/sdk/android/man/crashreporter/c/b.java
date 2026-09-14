package com.alibaba.sdk.android.man.crashreporter.c;

import com.alibaba.sdk.android.man.crashreporter.d.c;
import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.global.a;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;

public interface b
{
    void a(final CrashReportDataForSave p0, final Map<a, String> p1, final int p2);
    
    boolean a(final Context p0, final com.alibaba.sdk.android.man.crashreporter.a.b p1, final c p2, final c p3);
    
    void b(final Map<a, String> p0);
}
