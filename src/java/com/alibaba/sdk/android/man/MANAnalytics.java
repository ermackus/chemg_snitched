package com.alibaba.sdk.android.man;

import com.ut.mini.UTPageHitHelper;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.utils.AMSConfigUtils;
import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.utils.crashdefend.SDKMessageCallback;
import com.alibaba.sdk.android.utils.AlicloudTrackerManager;
import com.ut.mini.IUTApplication;
import com.ut.mini.UTAnalytics;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.alibaba.sdk.android.man.util.ToolKit;
import android.content.Context;
import android.app.Application;

public class MANAnalytics
{
    private static final String PRODUCT = "man";
    private static final int crashLimitCount = 10;
    private static final int initTimeSecond = 5;
    public final String TAG;
    private String appVersion;
    private String channel;
    private volatile Boolean isEnabled;
    private Boolean turnOnDebug;
    
    private MANAnalytics() {
        this.appVersion = "";
        this.channel = "";
        this.turnOnDebug = false;
        this.TAG = "MAN_MANAnalytics";
        this.isEnabled = true;
    }
    
    protected static MANAnalytics getInstance() {
        return Singleton.instance;
    }
    
    private void innerInit(final Application application, final Context context, final String appKey, final String s) {
        if (this.appVersion.isEmpty()) {
            this.appVersion = ToolKit.getMetaDataAppVersion(context);
        }
        if (this.channel.isEmpty()) {
            this.channel = ToolKit.getMetaDataChannel(context);
        }
        UTWrapper.utInit(appKey, s, application);
        UTAnalytics.getInstance().setAppApplicationInstance(application, (IUTApplication)new MANAnalytics$2(this, appKey, s));
        this.initMANInternal(context, MANTracker.getInstance().appKey = appKey, this.appVersion);
        UTWrapper.commitDAUEvent(context);
    }
    
    private void setMetaDataChannel(final Context context) {
        final String metaDataChannel = ToolKit.getMetaDataChannel(context);
        if (!metaDataChannel.equals((Object)"")) {
            this.channel = metaDataChannel;
        }
    }
    
    public MANTracker getDefaultTracker() {
        return MANTracker.getInstance();
    }
    
    public void init(final Application application, final Context context, final String s, final String s2) {
        if (context != null && application != null) {
            AlicloudTrackerManager.getInstance(application).registerCrashDefend("man", "1.2.4", 10, 5, (SDKMessageCallback)new MANAnalytics$1(this));
            if (this.isEnabled) {
                MANLog.Logd("MAN_MANAnalytics", "isEnabled is true, so execute init function!");
                this.innerInit(application, context, s, s2);
            }
            return;
        }
        MANLog.Loge("MAN_MANAnalytics", "MAN init failed, app context can't be null.");
    }
    
    public boolean init(final Application application, final Context context) {
        if (context == null || application == null) {
            MANLog.Loge("MAN_MANAnalytics", "MAN init failed, app context can't be null.");
            return false;
        }
        final String appKey = AMSConfigUtils.getAppKey(context);
        final String appSecret = AMSConfigUtils.getAppSecret(context);
        String metaDataAppKey = appKey;
        if (ToolKit.isNullOrEmpty(appKey)) {
            metaDataAppKey = ToolKit.getMetaDataAppKey(context);
        }
        String metaDataAppSecret = appSecret;
        if (ToolKit.isNullOrEmpty(appSecret)) {
            metaDataAppSecret = ToolKit.getMetaDataAppSecret(context);
        }
        if (!ToolKit.isNullOrEmpty(metaDataAppKey) && !ToolKit.isNullOrEmpty(metaDataAppSecret)) {
            this.init(application, context, metaDataAppKey, metaDataAppSecret);
            return true;
        }
        MANLog.Loge("MAN_MANAnalytics", "MAN init failed, invalid appKey/appSecret.");
        return false;
    }
    
    public void initMANInternal(final Context metaDataChannel, final String s, final String s2) {
        this.setMetaDataChannel(metaDataChannel);
        if (MotuCrashReporter.getInstance().enable(metaDataChannel, s, s2, null, null, null)) {
            MANLog.Logi("CrashReporter", "Turn on success.");
        }
        else {
            MANLog.Loge("CrashReporter", "Turn on fail.");
        }
    }
    
    public void sendCustomPerformance(final MANCustomPerformance manCustomPerformance) {
        if (!this.isEnabled) {
            MANLog.Loge("MAN_MANAnalytics", "MAN init failed,can not work for now!");
            return;
        }
        if (manCustomPerformance != null && manCustomPerformance.getDuration() != -1L) {
            if (!ToolKit.isNullOrEmpty(manCustomPerformance.getEventLabel())) {
                EventCommitTool.commitCustomPerformanceEvent(manCustomPerformance);
            }
        }
    }
    
    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
        MotuCrashReporter.getInstance().setAppVersion(appVersion);
        UTAnalytics.getInstance().setAppVersion(appVersion);
    }
    
    public void setChannel(final String channel) {
        this.channel = channel;
    }
    
    public void turnOffAutoPageTrack() {
        if (!this.isEnabled) {
            MANLog.Loge("MAN_MANAnalytics", "MAN init failed,can not work for now!");
            return;
        }
        UTPageHitHelper.getInstance().turnOffAutoPageTrack();
    }
    
    public void turnOffCrashReporter() {
        if (MotuCrashReporter.getInstance().turnoffCrashReporter()) {
            MANLog.Logi("CrashReporter", "Turn off success.");
        }
    }
    
    public void turnOnDebug() {
        this.turnOnDebug = true;
        MANLog.enableLog();
    }
    
    public void updateUserAccount(final String s, final String s2) {
        if (!this.isEnabled) {
            MANLog.Loge("MAN_MANAnalytics", "MAN init failed,can not work for now!");
            return;
        }
        UTAnalytics.getInstance().updateUserAccount(s, s2);
        UTWrapper.commitUserEvent("2");
    }
    
    public void userRegister(final String s) {
        if (!this.isEnabled) {
            MANLog.Loge("MAN_MANAnalytics", "MAN init failed,can not work for now!");
            return;
        }
        UTAnalytics.getInstance().userRegister(s);
        UTWrapper.commitUserEvent("1");
    }
    
    private static class Singleton
    {
        static MANAnalytics instance;
        
        static {
            Singleton.instance = new MANAnalytics(null);
        }
    }
}
