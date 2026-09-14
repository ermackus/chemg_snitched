package com.goodsrc.ui.library;

import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import java.util.Iterator;
import android.app.Activity;
import androidx.core.app.ActivityCompat;
import android.content.Context;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.net.Uri;
import android.os.Build$VERSION;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class QyPermissions
{
    private static final int REQUEST_PERMESION_CODE = 1001;
    private List<EasyParam> ResumePermissions;
    private BaseActivity activity;
    private Map<String, EasyParam> easyParamMap;
    
    public QyPermissions(final BaseActivity activity) {
        this.easyParamMap = (Map<String, EasyParam>)new HashMap();
        this.ResumePermissions = (List<EasyParam>)new ArrayList();
        this.activity = activity;
    }
    
    private boolean getAppDetailSettingIntent() {
        try {
            final Intent intent = new Intent();
            intent.addFlags(268435456);
            if (Build$VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", this.activity.getPackageName(), (String)null));
            }
            else if (Build$VERSION.SDK_INT <= 8) {
                intent.setAction("android.intent.action.VIEW");
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", this.activity.getPackageName());
            }
            this.activity.startActivity(intent);
            return true;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void onCallBack(final String s, final boolean b) {
        final EasyParam easyParam = (EasyParam)this.easyParamMap.get((Object)s);
        if (easyParam != null) {
            easyParam.callBack.onRequestResult(s, b);
            if (!b && easyParam.showSetting) {
                this.showPermissionSetting(s);
            }
            if (!b && easyParam.onResumeCheck) {
                this.ResumePermissions.add((Object)easyParam);
            }
            this.easyParamMap.remove((Object)s);
        }
    }
    
    public void checkPermission(final EasyParam easyParam, final RequestPermissionsCallBack callBack) {
        if (TextUtils.isEmpty((CharSequence)easyParam.permission)) {
            callBack.onRequestResult("", true);
            return;
        }
        easyParam.callBack = callBack;
        this.easyParamMap.put((Object)easyParam.permission, (Object)easyParam);
        if (ContextCompat.checkSelfPermission((Context)this.activity, easyParam.permission) == 0) {
            this.onCallBack(easyParam.permission, true);
        }
        else {
            ActivityCompat.requestPermissions((Activity)this.activity, new String[] { easyParam.permission }, 1001);
        }
    }
    
    public void onRequestPermissionsResult(int i, final String[] array, final int[] array2) {
        if (i == 1001) {
            for (i = 0; i < array2.length; ++i) {
                this.onCallBack(array[i], array2[i] == 0);
            }
        }
    }
    
    public void onResume() {
        for (final EasyParam easyParam : this.ResumePermissions) {
            if (easyParam.onResumeCheck) {
                easyParam.callBack.onRequestResult(easyParam.permission, ContextCompat.checkSelfPermission((Context)this.activity, easyParam.permission) == 0);
            }
        }
        this.ResumePermissions.clear();
    }
    
    public void showPermissionSetting(String message) {
        if (message.equals((Object)"android.permission.WRITE_EXTERNAL_STORAGE")) {
            message = this.activity.getString(R.string.perimission_name_storage);
        }
        else if (message.equals((Object)"android.permission.CAMERA")) {
            message = this.activity.getString(R.string.perimission_name_camera);
        }
        else {
            message = this.activity.getString(R.string.perimission_name_other);
        }
        message = String.format(this.activity.getString(R.string.perimission_msg_hint), new Object[] { message });
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.activity);
        alertDialog$Builder.setTitle(R.string.perimission_title_hint).setMessage((CharSequence)message).setPositiveButton(R.string.perimission_go_setting, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this) {
            final QyPermissions this$0;
            
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (this.this$0.getAppDetailSettingIntent()) {
                    dialogInterface.dismiss();
                }
            }
        }).setNegativeButton(R.string.perimission_cancel, (DialogInterface$OnClickListener)null);
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Button button = create.getButton(-1);
        if (button != null) {
            button.setTextColor(-2015681);
        }
        final Button button2 = create.getButton(-2);
        if (button2 != null) {
            button2.setTextColor(-2015681);
        }
    }
    
    public static class EasyParam
    {
        public RequestPermissionsCallBack callBack;
        public boolean onResumeCheck;
        public String permission;
        public boolean showSetting;
        
        public EasyParam() {
            this.showSetting = false;
            this.onResumeCheck = false;
        }
    }
    
    public interface RequestPermissionsCallBack
    {
        void onRequestResult(final String p0, final boolean p1);
    }
}
