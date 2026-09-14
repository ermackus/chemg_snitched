package com.kingagroot.kingdraw.utils;

import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.goodsrc.library.utils.AppUtil;
import com.kingagroot.kingdraw.base.MApplication;
import org.xutils.common.Callback$CommonCallback;
import org.xutils.x;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import org.xutils.http.RequestParams;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.app.Dialog;
import android.app.Activity;
import com.kingagroot.kingdraw.dialog.DialogManager;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import com.kingagroot.kingdraw.dialog.UpdateVersionDialog$OnUpdateListener;
import com.kingagroot.kingdraw.dialog.UpdateVersionDialog;
import android.os.Handler;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.model.AppUpdateModel;
import android.app.ProgressDialog;
import com.kingagroot.component.ui.widget.LoadingDialog;
import android.content.Context;
import org.xutils.common.Callback$Cancelable;

public class UpdateUtil
{
    private Callback$Cancelable cancelable;
    private final Context context;
    private boolean isForcedUpdate;
    LoadingDialog loadingProgress;
    private final OnUpdateAppListener onUpdateAppListener;
    ProgressDialog updateProgressDialog;
    private boolean updating;
    
    public UpdateUtil(final Context context, final boolean isForcedUpdate, final OnUpdateAppListener onUpdateAppListener) {
        this.context = context;
        this.isForcedUpdate = isForcedUpdate;
        this.onUpdateAppListener = onUpdateAppListener;
    }
    
    private void delayExit() {
        ToastUtil.showShort((CharSequence)this.context.getString(2131820604));
        new Handler().postDelayed((Runnable)_$$Lambda$UpdateUtil$ARpQAdXUkryAoZiseykwry7dovo.INSTANCE, 2000L);
    }
    
    private void ifUpdateDialog(final AppUpdateModel appUpdateModel) {
        final UpdateVersionDialog updateVersionDialog = new UpdateVersionDialog(this.context, appUpdateModel, (UpdateVersionDialog$OnUpdateListener)new _$$Lambda$UpdateUtil$Fx_gMpqIeW2OEgFIWNruptXaiEk(this, appUpdateModel));
        if (this.isForcedUpdate) {
            updateVersionDialog.setCanceledOnTouchOutside(false);
            updateVersionDialog.setCancelable(false);
            updateVersionDialog.setOnKeyListener((DialogInterface$OnKeyListener)new _$$Lambda$UpdateUtil$_CgMtwWC9XuhyGMRaa_cuG43AJc(this));
        }
        updateVersionDialog.setOnDismissListener((DialogInterface$OnDismissListener)new _$$Lambda$UpdateUtil$yaMRabMi8kI24j59xUQV0ZQC9o4(this));
        DialogManager.getInstance().getDialogQueue((Activity)this.context).addDailog((Dialog)updateVersionDialog);
    }
    
    private void stopUpdateCheck(final boolean b) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.context);
        alertDialog$Builder.setMessage(2131820598);
        alertDialog$Builder.setNegativeButton((CharSequence)this.context.getString(2131820661), (DialogInterface$OnClickListener)null);
        alertDialog$Builder.setPositiveButton((CharSequence)this.context.getString(2131820588), (DialogInterface$OnClickListener)new _$$Lambda$UpdateUtil$S00C1GsGJSnhfRSBmZs_nNRdS0I(this, b));
        alertDialog$Builder.create().show();
    }
    
    private void update(final AppUpdateModel appUpdateModel) {
        (this.updateProgressDialog = new ProgressDialog(this.context)).setProgressStyle(1);
        this.updateProgressDialog.setCanceledOnTouchOutside(false);
        this.updateProgressDialog.setCancelable(false);
        this.updateProgressDialog.setProgressNumberFormat("%1d M/%2d M");
        this.updateProgressDialog.setOnKeyListener((DialogInterface$OnKeyListener)_$$Lambda$UpdateUtil$NT3Z8XeBowcSfBgOt_3_HeovvBc.INSTANCE);
        final RequestParams requestParams = new RequestParams(appUpdateModel.getFileUrl());
        final StringBuilder sb = new StringBuilder();
        sb.append(FileConfig.BASE_PATH);
        sb.append("/kingdraw.apk");
        final String string = sb.toString();
        final File file = new File(FileConfig.BASE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        else if (!file.isDirectory() && file.canWrite()) {
            file.delete();
            file.mkdirs();
        }
        final File file2 = new File(string);
        if (file2.exists()) {
            file2.delete();
        }
        requestParams.setSaveFilePath(string);
        requestParams.setAutoResume(false);
        this.cancelable = x.http().get(requestParams, (Callback$CommonCallback)new UpdateUtil$2(this));
    }
    
    protected void cancelProgress() {
        final LoadingDialog loadingProgress = this.loadingProgress;
        if (loadingProgress != null) {
            loadingProgress.dismiss();
        }
    }
    
    public void checkVersion() {
        this.checkVersion(false);
    }
    
    public void checkVersion(final boolean b) {
        final String versionName = AppUtil.getVersionName((Context)MApplication.getInstance());
        if (b) {
            this.showProgress(this.context.getString(2131821522));
        }
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.appUpdate()), (RequestCallBack)new UpdateUtil$1(this, versionName, b));
    }
    
    protected void showPorgress(final String textMessage, final boolean onTouchOutside) {
        if (this.loadingProgress == null) {
            this.loadingProgress = new LoadingDialog((BaseActivity)this.context);
        }
        this.loadingProgress.setTextMessage(textMessage);
        this.loadingProgress.setOnTouchOutside(onTouchOutside);
        if (!this.loadingProgress.isShowing()) {
            this.loadingProgress.show();
        }
    }
    
    protected void showProgress(final String textMessage) {
        if (this.loadingProgress == null) {
            this.loadingProgress = new LoadingDialog((BaseActivity)this.context);
        }
        this.loadingProgress.setTextMessage(textMessage);
        this.loadingProgress.setOnTouchOutside(false);
        if (!this.loadingProgress.isShowing()) {
            this.loadingProgress.show();
        }
    }
    
    public interface OnUpdateAppListener
    {
        void onUpdateFinish();
    }
}
