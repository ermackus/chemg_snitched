package com.kingagroot.kingdraw.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.View$OnClickListener;
import android.content.Context;
import android.widget.EditText;
import android.widget.Button;
import android.app.Dialog;

public class PassSureDialog extends Dialog
{
    private Button btnCancel;
    private Button btnSure;
    private EditText etEditName;
    private OnNoOnclickListener noOnclickListener;
    private OnYesOnclickListener yesOnclickListener;
    
    public PassSureDialog(final Context context) {
        super(context);
    }
    
    private void init() {
        this.etEditName = (EditText)this.findViewById(2131296633);
        this.btnCancel = (Button)this.findViewById(2131296413);
        this.btnSure = (Button)this.findViewById(2131296460);
    }
    
    private void initEvent() {
        this.btnSure.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final PassSureDialog this$0;
            
            public void onClick(final View view) {
                if (!this.this$0.etEditName.getText().toString().isEmpty() && this.this$0.yesOnclickListener != null) {
                    this.this$0.yesOnclickListener.onYesClick();
                }
            }
        });
        this.btnCancel.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final PassSureDialog this$0;
            
            public void onClick(final View view) {
                if (this.this$0.noOnclickListener != null) {
                    this.this$0.noOnclickListener.onNoClick();
                }
            }
        });
    }
    
    public void dismiss() {
        super.dismiss();
    }
    
    public String getString() {
        return this.etEditName.getText().toString();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2131493029);
        this.setCanceledOnTouchOutside(false);
        this.init();
        this.initEvent();
    }
    
    public void setNoOnclickListener(final OnNoOnclickListener noOnclickListener) {
        this.noOnclickListener = noOnclickListener;
    }
    
    public void setYesOnclickListener(final OnYesOnclickListener yesOnclickListener) {
        this.yesOnclickListener = yesOnclickListener;
    }
    
    public void show() {
        super.show();
    }
    
    public interface OnNoOnclickListener
    {
        void onNoClick();
    }
    
    public interface OnYesOnclickListener
    {
        void onYesClick();
    }
}
