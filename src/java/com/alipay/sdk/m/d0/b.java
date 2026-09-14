package com.alipay.sdk.m.d0;

import com.alipay.sdk.m.z.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;

public class b implements Runnable
{
    public final DataReportRequest a;
    public final c b;
    
    public b(final c b, final DataReportRequest a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        try {
            c.a(c.a(this.b).reportData(this.a));
        }
        finally {
            c.a(new DataReportResult());
            c.a().success = false;
            final DataReportResult a = c.a();
            final StringBuilder sb = new StringBuilder("static data rpc upload error, ");
            final Throwable t;
            sb.append(com.alipay.sdk.m.z.a.a(t));
            a.resultCode = sb.toString();
            new StringBuilder("rpc failed:").append(com.alipay.sdk.m.z.a.a(t));
        }
    }
}
