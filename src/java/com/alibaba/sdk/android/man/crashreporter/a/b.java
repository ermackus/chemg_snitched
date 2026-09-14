package com.alibaba.sdk.android.man.crashreporter.a;

import com.alibaba.sdk.android.man.crashreporter.c;
import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.global.a;
import java.util.Map;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;

public interface b
{
    CrashReportDataForSave a();
    
    CrashReportDataForSave a(final String p0);
    
    CrashReportDataForSave a(final String p0, final String p1, final String p2, final Map p3);
    
    String a(final String p0);
    
    Map<String, String> a();
    
    Map<a, String> a(final int p0, final int p1, final int p2, final int p3);
    
    void a(final ReporterConfigure p0, final BaseDataContent p1, final int p2);
    
    void a(final Map p0, final String p1, final String p2, final String p3);
    
    boolean a(final Context p0, final ReporterConfigure p1, final c p2, final com.alibaba.sdk.android.man.crashreporter.d.c p3, final com.alibaba.sdk.android.man.crashreporter.d.c p4);
    
    CrashReportDataForSave b(final String p0, final String p1, final String p2, final Map p3);
}
