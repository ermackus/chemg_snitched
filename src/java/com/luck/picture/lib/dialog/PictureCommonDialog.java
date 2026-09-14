package com.luck.picture.lib.dialog;

import android.view.View;
import android.view.WindowManager$LayoutParams;
import android.widget.TextView;
import android.widget.Button;
import com.luck.picture.lib.R;
import android.content.Context;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class PictureCommonDialog extends Dialog implements View$OnClickListener
{
    private OnDialogEventListener eventListener;
    
    public PictureCommonDialog(final Context context, final String text, final String text2) {
        super(context, R.style.Picture_Theme_Dialog);
        this.setContentView(R.layout.ps_common_dialog);
        final Button button = (Button)this.findViewById(R.id.btn_cancel);
        final Button button2 = (Button)this.findViewById(R.id.btn_commit);
        final TextView textView = (TextView)this.findViewById(R.id.tvTitle);
        final TextView textView2 = (TextView)this.findViewById(R.id.tv_content);
        textView.setText((CharSequence)text);
        textView2.setText((CharSequence)text2);
        button.setOnClickListener((View$OnClickListener)this);
        button2.setOnClickListener((View$OnClickListener)this);
        this.setDialogSize();
    }
    
    private void setDialogSize() {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 17;
        this.getWindow().setWindowAnimations(R.style.PictureThemeDialogWindowStyle);
        this.getWindow().setAttributes(attributes);
    }
    
    public static PictureCommonDialog showDialog(final Context context, final String s, final String s2) {
        final PictureCommonDialog pictureCommonDialog = new PictureCommonDialog(context, s, s2);
        pictureCommonDialog.show();
        return pictureCommonDialog;
    }
    
    public void onClick(final View view) {
        final int id = view.getId();
        if (id == R.id.btn_cancel) {
            this.dismiss();
        }
        else if (id == R.id.btn_commit) {
            this.dismiss();
            final OnDialogEventListener eventListener = this.eventListener;
            if (eventListener != null) {
                eventListener.onConfirm();
            }
        }
    }
    
    public void setOnDialogEventListener(final OnDialogEventListener eventListener) {
        this.eventListener = eventListener;
    }
    
    public interface OnDialogEventListener
    {
        void onConfirm();
    }
}
