package com.luck.picture.lib.dialog;

import android.view.View;
import android.view.WindowManager$LayoutParams;
import com.luck.picture.lib.R;
import android.content.Context;
import android.widget.TextView;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class RemindDialog extends Dialog implements View$OnClickListener
{
    private final TextView btnOk;
    private OnDialogClickListener listener;
    private final TextView tvContent;
    
    public RemindDialog(final Context context, final String text) {
        super(context, R.style.Picture_Theme_Dialog);
        this.setContentView(R.layout.ps_remind_dialog);
        this.btnOk = (TextView)this.findViewById(R.id.btnOk);
        (this.tvContent = (TextView)this.findViewById(R.id.tv_content)).setText((CharSequence)text);
        this.btnOk.setOnClickListener((View$OnClickListener)this);
        this.setDialogSize();
    }
    
    public static RemindDialog buildDialog(final Context context, final String s) {
        return new RemindDialog(context, s);
    }
    
    private void setDialogSize() {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 17;
        this.getWindow().setWindowAnimations(R.style.PictureThemeDialogWindowStyle);
        this.getWindow().setAttributes(attributes);
    }
    
    @Deprecated
    public static Dialog showTipsDialog(final Context context, final String s) {
        return new RemindDialog(context, s);
    }
    
    public void onClick(final View view) {
        if (view.getId() == R.id.btnOk) {
            final OnDialogClickListener listener = this.listener;
            if (listener != null) {
                listener.onClick(view);
            }
            else {
                this.dismiss();
            }
        }
    }
    
    public void setButtonText(final String text) {
        this.btnOk.setText((CharSequence)text);
    }
    
    public void setButtonTextColor(final int textColor) {
        this.btnOk.setTextColor(textColor);
    }
    
    public void setContent(final String text) {
        this.tvContent.setText((CharSequence)text);
    }
    
    public void setContentTextColor(final int textColor) {
        this.tvContent.setTextColor(textColor);
    }
    
    public void setOnDialogClickListener(final OnDialogClickListener listener) {
        this.listener = listener;
    }
    
    public interface OnDialogClickListener
    {
        void onClick(final View p0);
    }
}
