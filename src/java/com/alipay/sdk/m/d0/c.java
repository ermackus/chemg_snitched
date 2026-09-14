package com.alipay.sdk.m.d0;

import org.json.JSONObject;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.aa;
import android.content.Context;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

public class c implements a
{
    public static c d;
    public static DataReportResult e;
    public w a;
    public BugTrackMessageService b;
    public DataReportService c;
    
    public c(final Context context, final String s) {
        this.a = null;
        this.b = null;
        this.c = null;
        final aa aa = new aa();
        aa.a(s);
        final h a = new h(context);
        this.a = a;
        this.b = (BugTrackMessageService)a.a((Class)BugTrackMessageService.class, aa);
        this.c = (DataReportService)this.a.a((Class)DataReportService.class, aa);
    }
    
    public static c a(final Context context, final String s) {
        synchronized (c.class) {
            if (c.d == null) {
                c.d = new c(context, s);
            }
            return c.d;
        }
    }
    
    public DataReportResult a(final DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.c != null) {
            com.alipay.sdk.m.d0.c.e = null;
            new Thread((Runnable)new b(this, dataReportRequest)).start();
            for (int n = 300000; com.alipay.sdk.m.d0.c.e == null && n >= 0; n -= 50) {
                Thread.sleep(50L);
            }
        }
        return com.alipay.sdk.m.d0.c.e;
    }
    
    public boolean logCollect(String logCollect) {
        final boolean a = com.alipay.sdk.m.z.a.a(logCollect);
        final boolean b = false;
        if (a) {
            return false;
        }
        final BugTrackMessageService b2 = this.b;
        boolean booleanValue = b;
        if (b2 != null) {
            final String s = null;
            String s2;
            try {
                logCollect = b2.logCollect(com.alipay.sdk.m.z.a.f(logCollect));
            }
            finally {
                s2 = s;
            }
            booleanValue = b;
            if (!com.alipay.sdk.m.z.a.a(s2)) {
                booleanValue = (boolean)new JSONObject(s2).get("success");
            }
        }
        return booleanValue;
    }
}
