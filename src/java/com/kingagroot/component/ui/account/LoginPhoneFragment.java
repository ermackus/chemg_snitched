package com.kingagroot.component.ui.account;

import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.goodsrc.library.utils.LanguageTool;
import com.kingagroot.component.ui.db.impl.CountryDbiMpl;
import com.kingagroot.component.ui.R$string;
import android.text.TextWatcher;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.text.TextUtils;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnLoginViewListener;
import com.kingagroot.component.ui.view.ClearEditText;
import android.widget.EditText;
import com.kingagroot.component.ui.model.CountryModel;
import com.kingagroot.component.ui.db.CountryDbi;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class LoginPhoneFragment extends Fragment implements View$OnClickListener
{
    private AppCompatButton btnPasswordConfirm;
    private CheckBox chbLoginAuto;
    private String countryCode;
    private CountryDbi countryDbi;
    private CountryModel countryModel;
    private EditText etAreaCode;
    private ClearEditText etLoginPassword;
    private ClearEditText etLoginPhone;
    private OnLoginViewListener onLoginViewListener;
    private String suffix;
    private TextView tvCountry;
    private TextView tvForgotPassword;
    
    private void initData() {
        if (AccountUtils.getLoginType() == 0) {
            final String loginUserName = AccountUtils.getLoginUserName();
            if (!TextUtils.isEmpty((CharSequence)loginUserName)) {
                this.etLoginPhone.setText((CharSequence)loginUserName);
            }
        }
    }
    
    private void initView(final View view) {
        this.tvCountry = (TextView)view.findViewById(R$id.tv_country);
        this.etAreaCode = (EditText)view.findViewById(R$id.et_area_code);
        this.etLoginPhone = (ClearEditText)view.findViewById(R$id.et_login_phone);
        this.etLoginPassword = (ClearEditText)view.findViewById(R$id.et_login_password);
        this.btnPasswordConfirm = (AppCompatButton)view.findViewById(R$id.btn_password_confirm);
        this.chbLoginAuto = (CheckBox)view.findViewById(R$id.chb_login_auto);
        this.tvForgotPassword = (TextView)view.findViewById(R$id.tv_forgot_password);
        this.setLoginButtonEnable(false);
        this.setAreaDefault();
        this.tvCountry.setOnClickListener((View$OnClickListener)this);
        this.tvForgotPassword.setOnClickListener((View$OnClickListener)this);
        this.btnPasswordConfirm.setOnClickListener((View$OnClickListener)this);
        final LoginPhoneFragment.LoginPhoneFragment$TextChange loginPhoneFragment$TextChange = new LoginPhoneFragment.LoginPhoneFragment$TextChange(this);
        this.etLoginPhone.addTextChangedListener((TextWatcher)loginPhoneFragment$TextChange);
        this.etLoginPassword.addTextChangedListener((TextWatcher)loginPhoneFragment$TextChange);
        this.etAreaCode.addTextChangedListener((TextWatcher)new LoginPhoneFragment$1(this));
    }
    
    private void setAreaData(final CountryModel countryModel) {
        final boolean b = false;
        if (countryModel != null) {
            this.tvCountry.setText((CharSequence)countryModel.getKey());
            this.suffix = countryModel.getValue();
            this.countryCode = countryModel.getCountryCode();
            final String string = this.etLoginPhone.getText().toString();
            boolean loginButtonEnable = b;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                loginButtonEnable = b;
                if (AccountUtils.isPhone(this.suffix, string)) {
                    loginButtonEnable = b;
                    if (this.etLoginPassword.length() > 5) {
                        loginButtonEnable = b;
                        if (this.etLoginPassword.length() < 17) {
                            loginButtonEnable = true;
                        }
                    }
                }
            }
            this.setLoginButtonEnable(loginButtonEnable);
        }
        else {
            this.tvCountry.setText(R$string.no_area);
            this.suffix = "";
            this.setLoginButtonEnable(false);
        }
    }
    
    private void setAreaDefault() {
        this.countryDbi = (CountryDbi)new CountryDbiMpl();
        final String countryCode = AccountUtils.getCountryCode();
        if (TextUtils.isEmpty((CharSequence)countryCode)) {
            if (LanguageTool.getLanguageType(this.getContext()).equals((Object)LanguageTool.SER_ZH)) {
                this.etAreaCode.setText((CharSequence)String.valueOf(86));
            }
            else {
                this.etAreaCode.setText((CharSequence)String.valueOf(1));
            }
            final CountryDbi countryDbi = this.countryDbi;
            final StringBuilder sb = new StringBuilder();
            sb.append("+");
            sb.append(this.etAreaCode.getText().toString());
            final CountryModel countryByName = countryDbi.getCountryByName(sb.toString());
            if (countryByName == null) {
                this.countryModel = DefaultData.getDefaultArea(this.getContext());
            }
            else {
                this.countryModel = countryByName;
            }
            this.tvCountry.setText((CharSequence)this.countryModel.getKey());
        }
        else {
            final CountryModel countryByCountryCode = this.countryDbi.getCountryByCountryCode(countryCode);
            if (countryByCountryCode == null) {
                this.countryModel = DefaultData.getDefaultArea(this.getContext());
            }
            else {
                this.countryModel = countryByCountryCode;
            }
            this.tvCountry.setText((CharSequence)this.countryModel.getKey());
            this.etAreaCode.setText((CharSequence)this.countryModel.getValue().replace((CharSequence)"+", (CharSequence)"").trim());
        }
        this.countryCode = this.countryModel.getCountryCode();
        this.suffix = this.countryModel.getValue();
    }
    
    private void setLoginButtonEnable(final boolean enabled) {
        this.btnPasswordConfirm.setEnabled(enabled);
    }
    
    public boolean getChbState() {
        return this.chbLoginAuto.isChecked();
    }
    
    public void onClick(final View view) {
        if (view == this.tvCountry) {
            final OnLoginViewListener onLoginViewListener = this.onLoginViewListener;
            if (onLoginViewListener != null) {
                onLoginViewListener.onChooseCountryClick();
            }
        }
        else if (view == this.tvForgotPassword) {
            final OnLoginViewListener onLoginViewListener2 = this.onLoginViewListener;
            if (onLoginViewListener2 != null) {
                onLoginViewListener2.onPhoneGetPasswordClick(this.countryModel, this.etLoginPhone.getText().toString());
            }
        }
        else if (view == this.btnPasswordConfirm) {
            final OnLoginViewListener onLoginViewListener3 = this.onLoginViewListener;
            if (onLoginViewListener3 != null) {
                onLoginViewListener3.onLoginClick(this.etLoginPhone.getText().toString(), this.etLoginPassword.getText().toString(), 0, this.suffix, this.getChbState(), this.countryCode);
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_login_phone, viewGroup, false);
        this.initView(inflate);
        this.initData();
        return inflate;
    }
    
    public void setLoginViewClickListener(final OnLoginViewListener onLoginViewListener) {
        this.onLoginViewListener = onLoginViewListener;
    }
    
    public void setModelData(final CountryModel countryModel) {
        this.etAreaCode.setText((CharSequence)countryModel.getValue().replace((CharSequence)"+", (CharSequence)"").trim());
        this.tvCountry.setText((CharSequence)countryModel.getKey());
        this.suffix = countryModel.getValue();
        this.countryCode = countryModel.getCountryCode();
        final String string = this.etLoginPhone.getText().toString();
        this.setLoginButtonEnable(!TextUtils.isEmpty((CharSequence)string) && AccountUtils.isPhone(this.suffix, string) && this.etLoginPassword.length() > 5 && this.etLoginPassword.length() < 17);
    }
}
