package com.kingagroot.kingdraw.base;

import com.kingagroot.kingdraw.mqtt.MsgMqttService;
import com.kingagroot.kingdraw.mqtt.VipStateCheckService;
import com.kingagroot.kingdraw.mqtt.QuitMqttService;
import com.kingagroot.kingdraw.mqtt.MyMqttService;
import com.goodsrc.ui.library.MANServiceConfig;
import java.util.Set;
import java.util.HashSet;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.interfaces.impl.UserDBImpl;
import com.tencent.bugly.crashreport.CrashReport;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import com.kingagroot.kingdraw.jpush.JPushAPI;
import com.kingagroot.kingdraw.gesture.GestureRecognize;
import com.kingagroot.kingdraw.core.view3d.base.Chem3DConfig;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.kingdraw.core.data.DataLibConfig;
import com.goodsrc.library.utils.FileUtil;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.goodsrc.library.utils.AppUtil;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import com.goodsrc.library.utils.LanguageTool;
import androidx.multidex.MultiDex;
import android.content.res.Configuration;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.goodsrc.library.utils.LocalLanguageManageUtil;
import android.content.ComponentName;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import com.goodsrc.library.core.LibraryApplication;
import java.util.ArrayList;
import java.util.List;
import android.app.NotificationManager;
import com.kingagroot.kingdraw.NewMainActivity;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils;
import com.kingagroot.kingdraw.ui.GuideActivity;
import com.kingagroot.kingdraw.ui.StartActivity;
import com.kingagroot.kingdraw.config.ShareData;
import android.text.TextUtils;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import com.kingagroot.kingdraw.interfaces.UserDBI;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;

public class MApplication extends Application
{
    private static boolean IsAppRuning;
    private static MApplication mApplication;
    private Application$ActivityLifecycleCallbacks activityLifecycleCallbacks;
    private UserDBI userdbi;
    
    public MApplication() {
        this.activityLifecycleCallbacks = (Application$ActivityLifecycleCallbacks)new Application$ActivityLifecycleCallbacks() {
            private int activityCount = 0;
            private boolean isStartMainActivity = false;
            final MApplication this$0;
            
            public void onActivityCreated(final Activity activity, final Bundle bundle) {
            }
            
            public void onActivityDestroyed(final Activity activity) {
                --this.activityCount;
            }
            
            public void onActivityPaused(final Activity activity) {
            }
            
            public void onActivityResumed(final Activity activity) {
                final int activityCount = this.activityCount + 1;
                this.activityCount = activityCount;
                if (activityCount == 1 && this.isStartMainActivity) {
                    final AppDataIniter appDataIniter = new AppDataIniter((Context)activity);
                    if (TextUtils.isEmpty((CharSequence)HttpHeadUtils.getUserDeviceId()) || "-".equals((Object)HttpHeadUtils.getUserDeviceId())) {
                        appDataIniter.getUdid();
                    }
                }
                if (ShareData.getShowPrivacyPolicyStatus() && !(activity instanceof StartActivity) && !(activity instanceof GuideActivity)) {
                    ShareFileTokenUtils.check(activity);
                }
                if (activity instanceof NewMainActivity) {
                    this.isStartMainActivity = true;
                }
            }
            
            public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
            }
            
            public void onActivityStarted(final Activity activity) {
            }
            
            public void onActivityStopped(final Activity activity) {
                --this.activityCount;
            }
        };
    }
    
    private static void clearNotification() {
        try {
            ((NotificationManager)getInstance().getSystemService("notification")).cancelAll();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private List<Activity> getActivities() {
        return (List<Activity>)new ArrayList();
    }
    
    public static MApplication getInstance() {
        synchronized (MApplication.class) {
            return MApplication.mApplication;
        }
    }
    
    public static String getUserToken() {
        return LibraryApplication.getToken();
    }
    
    public static boolean isIsAppRuning() {
        return MApplication.IsAppRuning;
    }
    
    private boolean isNeedRestart() {
        final List runningTasks = ((ActivityManager)this.getSystemService("activity")).getRunningTasks(1);
        if (!runningTasks.isEmpty()) {
            final ComponentName topActivity = ((ActivityManager$RunningTaskInfo)runningTasks.get(0)).topActivity;
            final ActivityManager$RunningTaskInfo activityManager$RunningTaskInfo = (ActivityManager$RunningTaskInfo)runningTasks.get(0);
            if (topActivity.getPackageName().equals((Object)this.getPackageName())) {
                return topActivity.getClassName().equals((Object)"com.tencent.connect.common.AssistActivity");
            }
        }
        return false;
    }
    
    public static void setIsAppRuning(final boolean isAppRuning) {
        MApplication.IsAppRuning = isAppRuning;
    }
    
    public static void setUserToken(final boolean b, final String s) {
        LibraryApplication.setToken(b, s);
        ShareData.setLoginTime();
    }
    
    protected void attachBaseContext(final Context local) {
        LocalLanguageManageUtil.saveSystemCurrentLanguage(local);
        super.attachBaseContext(LocalLanguageManageUtil.setLocal(local));
    }
    
    public AccountUserModel getAccountUserModel() {
        return this.userdbi.getCurrentUserInfo();
    }
    
    public boolean isLogin() {
        return this.userdbi.getCurrentUserInfo() != null;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LocalLanguageManageUtil.onConfigurationChanged(this.getApplicationContext());
    }
    
    public void onCreate() {
        super.onCreate();
        MultiDex.install((Context)(MApplication.mApplication = this));
        LocalLanguageManageUtil.setApplicationLanguage((Context)this);
        LibraryApplication.setLanguage(LanguageTool.getLanguageType((Context)this));
        KingDrawConfig.init((Context)this);
        KingDrawConfig.setAppVersion(AppUtil.getVersionName((Context)this));
        KingDrawConfig.setProofStatus(ShareData.getProofStatus());
        KingDrawConfig.setMagnifierStatus(ShareData.getZoomerStatus());
        KingDrawConfig.setBaseLineStatus(ShareData.getGuidesState());
        KingDrawConfig.setScaleConfigure(4.0f, 0.1f, 0.02f);
        KingDrawConfig.setTouchPadding(GDensityUtil.cm2px(0.18f));
        final String paletteCachePath = FileConfig.getPaletteCachePath();
        if (!TextUtils.isEmpty((CharSequence)paletteCachePath)) {
            final File file = new File(paletteCachePath);
            if (file.exists()) {
                FileUtil.clearDir(file);
            }
            else {
                file.mkdirs();
            }
            KingDrawConfig.setCachePath(paletteCachePath);
        }
        final String fileCachePath = FileConfig.getFileCachePath();
        if (!TextUtils.isEmpty((CharSequence)fileCachePath)) {
            final File file2 = new File(fileCachePath);
            if (file2.exists()) {
                FileUtil.clearDir(file2);
            }
            else {
                file2.mkdirs();
            }
            DataLibConfig.InitConfig(fileCachePath, AppUtil.getVersionName((Context)this));
        }
        UIComponentHelper.init(this);
        UIComponentHelper.setFileCachePath(FileConfig.getFileCachePath());
        UIComponentHelper.setPaletteCachePath(FileConfig.getPaletteCachePath());
        Chem3DConfig.init((Application)this);
        GestureRecognize.init((Context)this);
        LibraryApplication.init((Application)this);
        if (ShareData.getShowPrivacyPolicyStatus()) {
            JPushAPI.init((Context)this);
            if (AppConfig.RELEASE == Release.STANDARD) {
                JPushAPI.setDebugMode(false);
                CrashReport.initCrashReport(this.getApplicationContext(), "31c694f880", false);
            }
            else {
                CrashReport.initCrashReport(this.getApplicationContext(), "31c694f880", true);
            }
        }
        if (AppConfig.RELEASE == Release.STANDARD) {
            CrashHandler.getInstance().init((Context)this);
            LibraryApplication.setDebug(false);
        }
        KingDrawConfig.setDebug(false);
        this.registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        this.userdbi = (UserDBI)new UserDBImpl();
    }
    
    public void setAccountUserModel(final AccountUserModel accountUserModel, final boolean b) {
        this.userdbi.saveUserInfo(accountUserModel);
        if (b) {
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.mApplication);
            instance.sendBroadcast(new Intent("login_data_changed"));
            instance.sendBroadcast(new Intent("USER_LOGIN"));
            final HashSet set = new HashSet();
            ((Set)set).add((Object)accountUserModel.getUuid());
            JPushAPI.setTags((Context)this, 0, (Set)set);
            MANServiceConfig.userLogin(String.valueOf(accountUserModel.getId()));
            try {
                MyMqttService.startService((Context)this);
                QuitMqttService.startService((Context)this);
                VipStateCheckService.startService((Context)this);
            }
            catch (final IllegalStateException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void startMsgMqtt() {
        try {
            MsgMqttService.startService((Context)this);
        }
        catch (final IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stopMsgMqtt() {
        MsgMqttService.stopService((Context)this);
    }
    
    public void userLogout() {
        this.userdbi.clearUserInfo();
        setUserToken(false, "-");
        clearNotification();
        MyMqttService.stopService((Context)this);
        QuitMqttService.stopService((Context)this);
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.mApplication);
        instance.sendBroadcast(new Intent("login_data_changed"));
        instance.sendBroadcast(new Intent("USER_LOGOUT"));
        ShareData.setGroupDefault(-1);
    }
}
