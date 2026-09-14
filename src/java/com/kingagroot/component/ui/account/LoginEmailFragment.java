package com.kingagroot.component.ui.account;

import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.text.TextWatcher;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.text.TextUtils;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnLoginViewListener;
import com.kingagroot.component.ui.view.ClearEditText;
import com.kingagroot.component.ui.view.EmailAutoCompleteTextView;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class LoginEmailFragment extends Fragment implements View$OnClickListener
{
    private AppCompatButton btnLoginConfirm;
    private CheckBox chbLoginAuto;
    private EmailAutoCompleteTextView etEmail;
    private ClearEditText etLoginPassword;
    private OnLoginViewListener onLoginViewListener;
    private TextView tvForgotPassword;
    
    private void initData() {
        if (AccountUtils.getLoginType() == 1) {
            final String loginUserName = AccountUtils.getLoginUserName();
            if (!TextUtils.isEmpty((CharSequence)loginUserName)) {
                this.etEmail.setText((CharSequence)loginUserName);
            }
        }
    }
    
    private void initView(final View view) {
        this.etEmail = (EmailAutoCompleteTextView)view.findViewById(R$id.et_email);
        this.etLoginPassword = (ClearEditText)view.findViewById(R$id.et_login_password);
        this.btnLoginConfirm = (AppCompatButton)view.findViewById(R$id.btn_login_confirm);
        this.chbLoginAuto = (CheckBox)view.findViewById(R$id.chb_login_auto);
        this.tvForgotPassword = (TextView)view.findViewById(R$id.tv_forgot_password);
        this.btnLoginConfirm.setOnClickListener((View$OnClickListener)this);
        this.tvForgotPassword.setOnClickListener((View$OnClickListener)this);
        final LoginEmailFragment.LoginEmailFragment$TextChange loginEmailFragment$TextChange = new LoginEmailFragment.LoginEmailFragment$TextChange(this);
        this.etEmail.addTextChangedListener((TextWatcher)loginEmailFragment$TextChange);
        this.etLoginPassword.addTextChangedListener((TextWatcher)loginEmailFragment$TextChange);
        this.btnLoginConfirm.setEnabled(false);
    }
    
    public void onClick(final View view) {
        if (view == this.btnLoginConfirm) {
            final OnLoginViewListener onLoginViewListener = this.onLoginViewListener;
            if (onLoginViewListener != null) {
                onLoginViewListener.onLoginClick(this.etEmail.getText().toString(), this.etLoginPassword.getText().toString(), 1, "", this.chbLoginAuto.isChecked(), "");
            }
        }
        else if (view == this.tvForgotPassword) {
            final OnLoginViewListener onLoginViewListener2 = this.onLoginViewListener;
            if (onLoginViewListener2 != null) {
                onLoginViewListener2.onEmailGetPasswordClick(this.etEmail.getText().toString());
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_login_email, viewGroup, false);
        this.initView(inflate);
        this.initData();
        return inflate;
    }
    
    public void setLoginViewClickListener(final OnLoginViewListener onLoginViewListener) {
        this.onLoginViewListener = onLoginViewListener;
    }
}
