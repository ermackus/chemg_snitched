package com.luck.picture.lib.dialog;

import android.os.Bundle;
import android.view.WindowManager$LayoutParams;
import com.luck.picture.lib.R;
import android.content.Context;
import android.app.Dialog;

public class PictureLoadingDialog extends Dialog
{
    public PictureLoadingDialog(final Context context) {
        super(context, R.style.Picture_Theme_AlertDialog);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
    }
    
    private void setDialogSize() {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 17;
        this.getWindow().setWindowAnimations(R.style.PictureThemeDialogWindowStyle);
        this.getWindow().setAttributes(attributes);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.ps_alert_dialog);
        this.setDialogSize();
    }
}
