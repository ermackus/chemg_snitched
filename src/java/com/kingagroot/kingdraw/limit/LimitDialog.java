package com.kingagroot.kingdraw.limit;

import android.text.TextUtils;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.kingdraw.ui.VipPayActivity;
import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.ui.library.widget.AppManager;
import com.kingagroot.kingdraw.ui.account.LoginMainActivity;
import com.goodsrc.library.utils.NetworkUtil;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.workstation.WebWorkActivity;
import android.content.DialogInterface;
import android.content.Context;

public class LimitDialog
{
    public static final String TAG = "LimitDialog";
    
    public static void showInfoDialog(final Context context, String stringDefault) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)stringDefault);
        stringDefault = SPUtil.getStringDefault("LIMIT_URL", "");
        if (!TextUtils.isEmpty((CharSequence)stringDefault)) {
            alertDialog$Builder.setPositiveButton(2131821510, (DialogInterface$OnClickListener)new _$$Lambda$LimitDialog$aZYupMr8vSRFaJ1et_vup_JKUkg(context, stringDefault)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)_$$Lambda$LimitDialog$7C9Drjjxe2bD8JqVLbrpKEaeGjk.INSTANCE);
        }
        else {
            alertDialog$Builder.setPositiveButton(2131820909, (DialogInterface$OnClickListener)_$$Lambda$LimitDialog$jdXQYiYDE7N41eOZSfTs9sYIGLI.INSTANCE);
        }
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
        create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
        create.getButton(-2).setTextColor(Color.parseColor("#666666"));
    }
    
    public static void showLoginDialog(final Context context, final String message) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)message).setPositiveButton(2131820994, (DialogInterface$OnClickListener)new _$$Lambda$LimitDialog$Az5DPnwiT7I5Vt_ppyr_R7bH9aU(context)).setNegativeButton(2131820993, (DialogInterface$OnClickListener)_$$Lambda$LimitDialog$3LXfXoOuZKHzbzQAv_jKUXwVFcU.INSTANCE);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
        create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
        create.getButton(-2).setTextColor(Color.parseColor("#666666"));
    }
    
    public static void showVipDialog(final Context context, final String message) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context, 2131886085);
        alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)message).setPositiveButton(2131821121, (DialogInterface$OnClickListener)new _$$Lambda$LimitDialog$QUvNY4rfkClMMKIYpM4M4kSxwRQ(context)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)_$$Lambda$LimitDialog$fjiHfXQr_VwFaiuYylmfN3OS9eM.INSTANCE);
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
        create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
        create.getButton(-2).setTextColor(Color.parseColor("#666666"));
    }
}
