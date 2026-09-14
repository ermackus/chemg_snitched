package com.alipay.tscenter.biz.rpc.report.general.model;

import java.util.Map;
import java.io.Serializable;

public class DataReportResult implements Serializable
{
    public String resultCode;
    public Map<String, String> resultData;
    public boolean success;
}
