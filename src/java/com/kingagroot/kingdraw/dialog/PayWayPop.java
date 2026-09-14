package com.kingagroot.kingdraw.dialog;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.PopupWindow;

public class PayWayPop extends PopupWindow implements View$OnClickListener
{
    public static final String TAG = "PayWayPop";
    private Button btnAli;
    private Button btnGoogle;
    private Button btnPayCancel;
    private Button btnWechat;
    private final Context mContext;
    private final OnPayWayListener onPayWayListener;
    
    public PayWayPop(final Context mContext, final OnPayWayListener onPayWayListener) {
        super(mContext);
        this.mContext = mContext;
        this.onPayWayListener = onPayWayListener;
        this.init();
    }
    
    private void init() {
        final View inflate = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2131493148, (ViewGroup)null);
        this.btnGoogle = (Button)inflate.findViewById(2131296431);
        this.btnWechat = (Button)inflate.findViewById(2131296464);
        this.btnAli = (Button)inflate.findViewById(2131296412);
        this.btnPayCancel = (Button)inflate.findViewById(2131296442);
        this.btnGoogle.setOnClickListener((View$OnClickListener)this);
        this.btnWechat.setOnClickListener((View$OnClickListener)this);
        this.btnAli.setOnClickListener((View$OnClickListener)this);
        this.btnPayCancel.setOnClickListener((View$OnClickListener)this);
        this.setContentView(inflate);
        this.setAnimationStyle(2131886857);
        this.setWidth(-1);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
    }
    
    public void onClick(final View view) {
        if (view == this.btnGoogle) {
            final OnPayWayListener onPayWayListener = this.onPayWayListener;
            if (onPayWayListener != null) {
                onPayWayListener.onGooglePay();
            }
        }
        else if (view == this.btnWechat) {
            final OnPayWayListener onPayWayListener2 = this.onPayWayListener;
            if (onPayWayListener2 != null) {
                onPayWayListener2.onWeChatPay();
            }
        }
        else if (view == this.btnAli) {
            final OnPayWayListener onPayWayListener3 = this.onPayWayListener;
            if (onPayWayListener3 != null) {
                onPayWayListener3.onAliPay();
            }
        }
        else if (view == this.btnPayCancel) {
            this.dismiss();
        }
    }
    
    public interface OnPayWayListener
    {
        void onAliPay();
        
        void onGooglePay();
        
        void onWeChatPay();
    }
}
