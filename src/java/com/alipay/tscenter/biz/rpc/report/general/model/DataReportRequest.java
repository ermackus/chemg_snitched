package com.alipay.tscenter.biz.rpc.report.general.model;

import java.util.Map;
import java.io.Serializable;

public class DataReportRequest implements Serializable
{
    public Map<String, String> bizData;
    public String bizType;
    public Map<String, String> deviceData;
    public String os;
    public String rpcVersion;
}
