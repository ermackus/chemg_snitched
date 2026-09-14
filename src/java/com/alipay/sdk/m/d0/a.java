package com.alipay.sdk.m.d0;

import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;

public interface a
{
    DataReportResult a(final DataReportRequest p0);
    
    boolean logCollect(final String p0);
}
