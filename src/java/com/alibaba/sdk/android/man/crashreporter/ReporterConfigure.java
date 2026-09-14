package com.alibaba.sdk.android.man.crashreporter;

public class ReporterConfigure
{
    public int enabeANRTimeoutInterval;
    public boolean enableANRMainThreadOnly;
    public boolean enableAbortCount;
    public boolean enableActivityMonitor;
    public boolean enableCatchANRException;
    public boolean enableCatchNativeException;
    public boolean enableCatchUncaughtException;
    public boolean enableDebug;
    public boolean enableDeduplication;
    public boolean enableDumpAllThread;
    public boolean enableDumpAppLog;
    public boolean enableDumpEventsLog;
    public boolean enableDumpRadioLog;
    public boolean enableDumpSysLog;
    public int enableMaxThreadNumber;
    public int enableMaxThreadStackTraceNumber;
    public boolean enableStartCount;
    public int enableSysLogcatLinkMaxCount;
    public int enableSysLogcatMaxCount;
    public int sendOnLaunchDelay;
    
    public ReporterConfigure() {
        this.enableCatchUncaughtException = true;
        this.enableCatchNativeException = true;
        this.enableCatchANRException = true;
        this.enableStartCount = true;
        this.sendOnLaunchDelay = 0;
        this.enableActivityMonitor = true;
        this.enableDumpSysLog = false;
        this.enableDumpEventsLog = false;
        this.enableDumpRadioLog = false;
        this.enableDumpAppLog = false;
        this.enableDumpAllThread = false;
        this.enableDebug = false;
        this.enabeANRTimeoutInterval = 5000;
        this.enableANRMainThreadOnly = false;
        this.enableDeduplication = false;
        this.enableAbortCount = false;
        this.enableMaxThreadNumber = 15;
        this.enableMaxThreadStackTraceNumber = 15;
        this.enableSysLogcatMaxCount = 100;
        this.enableSysLogcatLinkMaxCount = 100;
    }
    
    public void setEnableANRMainThreadOnly(final boolean enableANRMainThreadOnly) {
        this.enableANRMainThreadOnly = enableANRMainThreadOnly;
    }
    
    public void setEnableCatchANRException(final boolean enableCatchANRException) {
        this.enableCatchANRException = enableCatchANRException;
    }
    
    public void setEnableDebug(final boolean enableDebug) {
        this.enableDebug = enableDebug;
    }
    
    public void setEnableDumpAllThread(final boolean enableDumpAllThread) {
        this.enableDumpAllThread = enableDumpAllThread;
    }
    
    public void setEnableDumpAppLog(final boolean enableDumpAppLog) {
        this.enableDumpAppLog = enableDumpAppLog;
    }
    
    public void setEnableDumpEventsLog(final boolean enableDumpEventsLog) {
        this.enableDumpEventsLog = enableDumpEventsLog;
    }
    
    public void setEnableDumpRadioLog(final boolean enableDumpRadioLog) {
        this.enableDumpRadioLog = enableDumpRadioLog;
    }
    
    public void setEnableDumpSysLog(final boolean enableDumpSysLog) {
        this.enableDumpSysLog = enableDumpSysLog;
    }
}
