package com.alipay.sdk.m.f0;

import java.util.HashMap;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import java.util.Map;
import com.alipay.sdk.m.z.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

public class b
{
    public static c a(final DataReportResult dataReportResult) {
        final c c = new c();
        if (dataReportResult == null) {
            return null;
        }
        ((com.alipay.sdk.m.f0.a)c).a = dataReportResult.success;
        ((com.alipay.sdk.m.f0.a)c).b = dataReportResult.resultCode;
        final Map resultData = dataReportResult.resultData;
        if (resultData != null) {
            c.c = (String)resultData.get((Object)"apdid");
            c.d = (String)resultData.get((Object)"apdidToken");
            c.g = (String)resultData.get((Object)"dynamicKey");
            c.h = (String)resultData.get((Object)"timeInterval");
            c.i = (String)resultData.get((Object)"webrtcUrl");
            c.j = "";
            final String s = (String)resultData.get((Object)"drmSwitch");
            if (a.b(s)) {
                if (s.length() > 0) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(s.charAt(0));
                    c.e = sb.toString();
                }
                if (s.length() >= 3) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(s.charAt(2));
                    c.f = sb2.toString();
                }
            }
            if (resultData.containsKey((Object)"apse_degrade")) {
                c.k = (String)resultData.get((Object)"apse_degrade");
            }
        }
        return c;
    }
    
    public static DataReportRequest a(final d d) {
        final DataReportRequest dataReportRequest = new DataReportRequest();
        if (d == null) {
            return null;
        }
        dataReportRequest.os = d.a;
        dataReportRequest.rpcVersion = d.j;
        dataReportRequest.bizType = "1";
        (dataReportRequest.bizData = (Map)new HashMap()).put((Object)"apdid", (Object)d.b);
        dataReportRequest.bizData.put((Object)"apdidToken", (Object)d.c);
        dataReportRequest.bizData.put((Object)"umidToken", (Object)d.d);
        dataReportRequest.bizData.put((Object)"dynamicKey", (Object)d.e);
        dataReportRequest.deviceData = d.f;
        return dataReportRequest;
    }
}
