package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.interfaces.GestureDbi;
import com.kingagroot.kingdraw.dialog.PrivacyPolicyDialog$OnPrivacyPolicyDialogListner;
import com.kingagroot.kingdraw.data.GestureData;
import com.kingagroot.kingdraw.interfaces.impl.GestureDbiMpl;
import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.app.Activity;
import android.os.Bundle;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.ui.library.QyPermissions$RequestPermissionsCallBack;
import com.goodsrc.ui.library.QyPermissions$EasyParam;
import com.kingagroot.kingdraw.NewMainActivity;
import android.content.Intent;
import com.kingagroot.kingdraw.config.ShareData;
import com.goodsrc.library.utils.AppUtil;
import android.app.Application;
import com.goodsrc.ui.library.MANServiceConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import com.kingagroot.kingdraw.jpush.JPushAPI;
import com.goodsrc.library.utils.SPUtil;
import com.goodsrc.library.core.LibraryApplication;
import com.goodsrc.library.utils.LanguageTool;
import com.kingagroot.kingdraw.base.AppDataIniter$AppDataIniterLisener;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.base.MApplication;
import java.io.File;
import android.text.TextUtils;
import com.goodsrc.library.utils.UriUtil;
import android.net.Uri;
import android.app.ProgressDialog;
import com.kingagroot.kingdraw.dialog.PrivacyPolicyDialog;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import com.kingagroot.kingdraw.base.AppDataIniter;
import com.goodsrc.ui.library.BaseActivity;

public class StartActivity extends BaseActivity
{
    private AppDataIniter appDataIniter;
    NotchContext notchContext;
    private PrivacyPolicyDialog privacyPolicyDialog;
    private ProgressDialog progressDialog;
    
    private String copyFileByPath(Uri openInputStream) {
        if (openInputStream != null) {
            final String uri2File = UriUtil.Uri2File((Uri)openInputStream);
            if (!TextUtils.isEmpty((CharSequence)uri2File)) {
                try {
                    final File file = new File(uri2File);
                    if (!file.exists()) {
                        return null;
                    }
                    final String name = file.getName();
                    openInputStream = (SecurityException)MApplication.getInstance().getContentResolver().openInputStream((Uri)openInputStream);
                    final String absolutePath = this.getCacheDir().getAbsolutePath();
                    final StringBuilder sb = new StringBuilder();
                    sb.append(absolutePath);
                    sb.append(File.separator);
                    sb.append(name);
                    final String string = sb.toString();
                    final File file2 = new File(string);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    else {
                        file2.getParentFile().mkdirs();
                    }
                    FileUtil.writeFile((InputStream)openInputStream, file2);
                    return string;
                }
                catch (final SecurityException openInputStream) {}
                catch (final IOException ex) {}
                ((Exception)openInputStream).printStackTrace();
            }
        }
        return null;
    }
    
    private void init() {
        this.appDataIniter = new AppDataIniter((Context)this);
        if (MApplication.isIsAppRuning()) {
            this.onNextActivity();
        }
        else {
            MApplication.setIsAppRuning(true);
            this.appDataIniter.start((AppDataIniter$AppDataIniterLisener)new _$$Lambda$StartActivity$JkaJ1WBjiMdjRuuIk6rmolpyVCE(this));
        }
    }
    
    private void initView() {
        (this.progressDialog = new ProgressDialog((Context)this)).setCancelable(false);
        this.progressDialog.setMessage((CharSequence)this.getString(2131821404));
        LibraryApplication.setLanguage(LanguageTool.getLanguageType((Context)this));
        SPUtil.setBooleanDefault("FLOAT_PEDIA", false);
        SPUtil.setBooleanDefault("FLOAT_TEMPLATE", false);
        SPUtil.setBooleanDefault("FLOAT_WEB_STATION", false);
    }
    
    private void libInit() {
        JPushAPI.init((Context)this);
        final Release release = AppConfig.RELEASE;
        final Release standard = Release.STANDARD;
        boolean b = true;
        if (release == standard) {
            JPushAPI.setDebugMode(false);
            CrashReport.initCrashReport(this.getApplicationContext(), "31c694f880", false);
        }
        else {
            CrashReport.initCrashReport(this.getApplicationContext(), "31c694f880", true);
        }
        final Application application = this.getApplication();
        if (AppConfig.RELEASE == Release.STANDARD) {
            b = false;
        }
        MANServiceConfig.init(application, b);
    }
    
    private void nextActivity(final Uri uri) {
        if (AppUtil.getVersionCode((Context)MApplication.getInstance()) > ShareData.getLastBootVersion()) {
            final Intent intent = new Intent((Context)this, (Class)GuideActivity.class);
            if (uri != null) {
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
            }
            this.startActivity(intent);
            this.finish();
        }
        else {
            final Intent intent2 = new Intent((Context)this, (Class)NewMainActivity.class);
            if (uri != null) {
                intent2.setAction("android.intent.action.VIEW");
                intent2.setData(uri);
            }
            this.startActivity(intent2);
            this.finish();
        }
    }
    
    private void onNextActivity() {
        final Intent intent = this.getIntent();
        final String action = intent.getAction();
        if ("android.intent.action.SEND".equals((Object)action)) {
            final Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.STREAM");
            final QyPermissions$EasyParam qyPermissions$EasyParam = new QyPermissions$EasyParam();
            qyPermissions$EasyParam.showSetting = false;
            qyPermissions$EasyParam.onResumeCheck = false;
            qyPermissions$EasyParam.permission = "android.permission.WRITE_EXTERNAL_STORAGE";
            this.checkPermission(qyPermissions$EasyParam, (QyPermissions$RequestPermissionsCallBack)new _$$Lambda$StartActivity$W1T1ZVoVWzLslO65FD8oqABLOZc(this, uri));
        }
        else if ("android.intent.action.VIEW".equals((Object)action)) {
            final Uri data = intent.getData();
            final QyPermissions$EasyParam qyPermissions$EasyParam2 = new QyPermissions$EasyParam();
            qyPermissions$EasyParam2.showSetting = false;
            qyPermissions$EasyParam2.onResumeCheck = false;
            qyPermissions$EasyParam2.permission = "android.permission.WRITE_EXTERNAL_STORAGE";
            this.checkPermission(qyPermissions$EasyParam2, (QyPermissions$RequestPermissionsCallBack)new _$$Lambda$StartActivity$QhWG96Kt0Gwmxf6E5_OP6t44vD4(this, data));
        }
        else {
            this.nextActivity(null);
        }
    }
    
    public void onBackPressed() {
        final AppDataIniter appDataIniter = this.appDataIniter;
        if (appDataIniter == null || !appDataIniter.isRunning()) {
            super.onBackPressed();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (!this.isTaskRoot()) {
            final Intent intent = this.getIntent();
            final String action = intent.getAction();
            if (action != null && intent.hasCategory("android.intent.category.LAUNCHER") && action.equals((Object)"android.intent.action.MAIN")) {
                this.finish();
                return;
            }
        }
        (this.notchContext = new NotchContext((Activity)this)).checkNotchInScreen((NotchCallBack)new _$$Lambda$StartActivity$ORBf47ih8tuhGP_lxzvw5KkOocA(this));
        this.initView();
        final boolean showPrivacyPolicyStatus = ShareData.getShowPrivacyPolicyStatus();
        final GestureDbiMpl gestureDbiMpl = new GestureDbiMpl();
        if (!ShareData.isSaveGesture()) {
            ((GestureDbi)gestureDbiMpl).save(GestureData.gestureListData());
        }
        boolean b = false;
        if (!showPrivacyPolicyStatus) {
            (this.privacyPolicyDialog = new PrivacyPolicyDialog((Context)this)).setCancelable(false);
            this.privacyPolicyDialog.setOnPrivacyPolicyDialogListner((PrivacyPolicyDialog$OnPrivacyPolicyDialogListner)new StartActivity$1(this));
            this.privacyPolicyDialog.show();
        }
        else {
            this.init();
            final Application application = this.getApplication();
            if (AppConfig.RELEASE != Release.STANDARD) {
                b = true;
            }
            MANServiceConfig.init(application, b);
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        final AppDataIniter appDataIniter = this.appDataIniter;
        if (appDataIniter != null) {
            appDataIniter.cancel();
        }
        final ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
    
    protected void onStop() {
        super.onStop();
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
    
    public void showProgress() {
        try {
            if (!this.isFinishing()) {
                this.progressDialog.show();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
