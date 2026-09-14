package com.kingagroot.component.ui.account;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.view.View$OnClickListener;
import android.widget.PopupWindow;

public class GetPwdPop extends PopupWindow implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    private OnPwdPopClickListener onPwdPopClickListener;
    private TextView tvPwdCancel;
    private TextView tvPwdEmail;
    private TextView tvPwdPhone;
    
    public GetPwdPop(final Context context) {
        super(context);
        this.setWidth(-1);
        this.setHeight(-2);
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.pop_get_password, (ViewGroup)null);
        inflate.measure(0, 0);
        this.setContentView(inflate);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.setAnimationStyle(R.style.pop_anim_style);
        this.initView(inflate);
    }
    
    private void initView(final View view) {
        this.tvPwdPhone = (TextView)view.findViewById(R.id.tv_pwd_phone);
        this.tvPwdEmail = (TextView)view.findViewById(R.id.tv_pwd_email);
        this.tvPwdCancel = (TextView)view.findViewById(R.id.tv_pwd_cancel);
        this.tvPwdPhone.setOnClickListener((View$OnClickListener)this);
        this.tvPwdEmail.setOnClickListener((View$OnClickListener)this);
        this.tvPwdCancel.setOnClickListener((View$OnClickListener)this);
    }
    
    public void onClick(final View view) {
        if (view == this.tvPwdPhone) {
            final OnPwdPopClickListener onPwdPopClickListener = this.onPwdPopClickListener;
            if (onPwdPopClickListener != null) {
                onPwdPopClickListener.onPwdPhoneClick();
                this.dismiss();
            }
        }
        else if (view == this.tvPwdEmail) {
            final OnPwdPopClickListener onPwdPopClickListener2 = this.onPwdPopClickListener;
            if (onPwdPopClickListener2 != null) {
                onPwdPopClickListener2.onPwdEmailClick();
                this.dismiss();
            }
        }
        else if (view == this.tvPwdCancel) {
            this.dismiss();
        }
    }
    
    public void setOnPwdPopClickListener(final OnPwdPopClickListener onPwdPopClickListener) {
        this.onPwdPopClickListener = onPwdPopClickListener;
    }
    
    public interface OnPwdPopClickListener
    {
        void onPwdEmailClick();
        
        void onPwdPhoneClick();
    }
}
