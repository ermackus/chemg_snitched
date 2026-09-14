package com.alibaba.sdk.android.man.crashreporter.d;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;

public interface c
{
    BaseDataContent a();
    
    CrashReportDataForSave a(final String p0, final int p1);
    
    String a(final long p0);
    
    void a(final BaseDataContent p0);
    
    boolean a(final CrashReportDataForSave p0, final int p1);
    
    String[] a(final int p0);
    
    CrashReportDataForSave b(final String p0);
    
    void b(final CrashReportDataForSave p0);
    
    void b(final boolean p0);
    
    boolean c(final Context p0);
    
    String h();
    
    String i();
    
    String j();
}
