package com.kingagroot.kingdraw.dialog;

import android.view.WindowManager$LayoutParams;
import com.goodsrc.library.utils.DisplayUtil;
import android.view.WindowManager;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.ui.library.widget.AppManager;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.account.LoginMainActivity;
import com.goodsrc.library.utils.NetworkUtil;
import android.view.View;
import android.content.DialogInterface;
import android.view.View$OnClickListener;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.widget.Button;
import android.app.Dialog;

public class LogInHintDialog extends Dialog
{
    private Button btnLogin;
    private Button btnNoLogin;
    private final Context context;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public LogInHintDialog(final Context context) {
        super(context, 2131886326);
        this.context = context;
    }
    
    private void initView() {
        this.btnLogin = (Button)this.findViewById(2131296435);
        this.btnNoLogin = (Button)this.findViewById(2131296438);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.btnLogin.setOnClickListener((View$OnClickListener)new _$$Lambda$LogInHintDialog$kSzwxt_LGDITNawFOoRjuYKlIXw(this));
        this.btnNoLogin.setOnClickListener((View$OnClickListener)new _$$Lambda$LogInHintDialog$ewAMqLRr1_LGHtveVVO2golmMNo(this));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493025);
        this.initView();
    }
    
    public void show() {
        super.show();
        final WindowManager windowManager = (WindowManager)this.getContext().getSystemService("window");
        final int width = windowManager.getDefaultDisplay().getWidth();
        final int height = windowManager.getDefaultDisplay().getHeight();
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = width;
        attributes.height = height - DisplayUtil.dip2px(this.getContext(), 200.0f);
        this.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        this.getWindow().setAttributes(attributes);
    }
}
