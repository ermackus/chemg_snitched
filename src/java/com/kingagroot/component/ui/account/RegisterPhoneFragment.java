package com.kingagroot.component.ui.account;

import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.account.service.PhoneRegCodeTimerService;
import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import java.util.Objects;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.content.IntentFilter;
import android.text.TextWatcher;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.component.ui.utils.AccountUtils;
import com.kingagroot.component.ui.db.impl.CountryDbiMpl;
import android.text.TextUtils;
import com.kingagroot.component.ui.UIComponentHelper;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.model.CountryModel;
import android.view.View;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnRegisterViewClick;
import android.content.BroadcastReceiver;
import android.widget.ImageButton;
import com.kingagroot.component.ui.view.ClearEditText;
import android.widget.EditText;
import com.kingagroot.component.ui.db.CountryDbi;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class RegisterPhoneFragment extends Fragment implements View$OnClickListener
{
    private AppCompatButton btnRegister;
    private AppCompatButton btnRegisterSendCode;
    private CheckBox checkRegisterAgree;
    private String countryArea;
    private CountryDbi countryDbi;
    private EditText etAreaCode;
    private EditText etRegisterCode;
    private ClearEditText etRegisterPhone;
    private ImageButton ibtRegisterEmail;
    private boolean isArea;
    private final BroadcastReceiver mUpdateReceiver;
    private OnRegisterViewClick onRegisterViewClick;
    private String suffix;
    private TextView tvCountry;
    
    public RegisterPhoneFragment() {
        this.mUpdateReceiver = (BroadcastReceiver)new RegisterPhoneFragment$1(this);
    }
    
    private void avoidHintColor(final View view) {
        if (view instanceof TextView) {
            ((TextView)view).setHighlightColor(0);
        }
    }
    
    private void initView(final View view) {
        this.tvCountry = (TextView)view.findViewById(R$id.tv_country);
        this.etAreaCode = (EditText)view.findViewById(R$id.et_area_code);
        this.etRegisterPhone = (ClearEditText)view.findViewById(R$id.et_register_phone);
        this.etRegisterCode = (EditText)view.findViewById(R$id.et_register_code);
        this.btnRegisterSendCode = (AppCompatButton)view.findViewById(R$id.btn_register_send_code);
        this.checkRegisterAgree = (CheckBox)view.findViewById(R$id.check_register_agree);
        final TextView textView = (TextView)view.findViewById(R$id.tv_register_agreement);
        this.btnRegister = (AppCompatButton)view.findViewById(R$id.btn_register);
        this.ibtRegisterEmail = (ImageButton)view.findViewById(R$id.ibt_register_email);
        final String string = this.getString(R$string.service_agreement);
        final String string2 = this.getString(R$string.privacy_agreement);
        final SpannableString spannableString = new SpannableString((CharSequence)string);
        spannableString.setSpan((Object)new RegisterPhoneFragment$2(this), 0, spannableString.length(), 33);
        textView.append((CharSequence)spannableString);
        final SpannableString spannableString2 = new SpannableString((CharSequence)string2);
        spannableString2.setSpan((Object)new RegisterPhoneFragment$3(this), 0, spannableString2.length(), 33);
        textView.append((CharSequence)spannableString2);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (!TextUtils.isEmpty((CharSequence)UIComponentHelper.getRegPhone())) {
            this.etRegisterPhone.setText((CharSequence)UIComponentHelper.getRegPhone());
        }
        this.countryDbi = (CountryDbi)new CountryDbiMpl();
        this.setAreaDefault();
        this.setViewClick();
        this.setRegisterButtonEnable(false);
    }
    
    private void setAreaData(final CountryModel countryModel) {
        final boolean b = false;
        if (countryModel != null) {
            this.tvCountry.setText((CharSequence)countryModel.getKey());
            this.suffix = countryModel.getValue();
            this.countryArea = countryModel.getCountryCode();
            final String string = this.etRegisterPhone.getText().toString();
            boolean registerButtonEnable = b;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                registerButtonEnable = b;
                if (AccountUtils.isPhone(this.suffix, string)) {
                    registerButtonEnable = b;
                    if (this.etRegisterCode.length() > 3) {
                        registerButtonEnable = b;
                        if (this.etRegisterCode.length() < 7) {
                            registerButtonEnable = true;
                        }
                    }
                }
            }
            this.setRegisterButtonEnable(registerButtonEnable);
            this.isArea = true;
        }
        else {
            this.isArea = false;
            this.tvCountry.setText(R$string.no_area);
            this.setRegisterButtonEnable(false);
        }
    }
    
    private void setAreaDefault() {
        final String string = SPUtil.getString("AREA", "areaKey", "");
        CountryModel countryModel;
        if (TextUtils.isEmpty((CharSequence)string)) {
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
            if ((countryModel = countryDbi.getCountryByName(sb.toString())) == null) {
                countryModel = DefaultData.getDefaultArea(this.getContext());
            }
            this.tvCountry.setText((CharSequence)countryModel.getKey());
        }
        else {
            if ((countryModel = this.countryDbi.getCountryByCountryCode(string)) == null) {
                countryModel = DefaultData.getDefaultArea(this.getContext());
            }
            this.tvCountry.setText((CharSequence)countryModel.getKey());
            this.etAreaCode.setText((CharSequence)countryModel.getValue().replace((CharSequence)"+", (CharSequence)"").trim());
        }
        this.suffix = countryModel.getValue();
        this.countryArea = countryModel.getCountryCode();
        this.isArea = true;
    }
    
    private void setRegisterButtonEnable(final boolean enabled) {
        this.btnRegister.setEnabled(enabled);
    }
    
    private void setViewClick() {
        this.tvCountry.setOnClickListener((View$OnClickListener)this);
        this.btnRegisterSendCode.setOnClickListener((View$OnClickListener)this);
        this.btnRegister.setOnClickListener((View$OnClickListener)this);
        this.ibtRegisterEmail.setOnClickListener((View$OnClickListener)this);
        final RegisterPhoneFragment.RegisterPhoneFragment$TextChange registerPhoneFragment$TextChange = new RegisterPhoneFragment.RegisterPhoneFragment$TextChange(this);
        this.etRegisterPhone.addTextChangedListener((TextWatcher)registerPhoneFragment$TextChange);
        this.etRegisterCode.addTextChangedListener((TextWatcher)registerPhoneFragment$TextChange);
        this.etAreaCode.addTextChangedListener((TextWatcher)new RegisterPhoneFragment$4(this));
    }
    
    private static IntentFilter updateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("PHONE_REG_IN_RUNNING");
        intentFilter.addAction("PHONE_REG_END_RUNNING");
        return intentFilter;
    }
    
    public void onClick(final View view) {
        if (view == this.tvCountry) {
            final OnRegisterViewClick onRegisterViewClick = this.onRegisterViewClick;
            if (onRegisterViewClick != null) {
                onRegisterViewClick.onChooseCountryClick(0);
            }
        }
        else if (view == this.btnRegisterSendCode) {
            if (this.isArea) {
                final String string = this.etRegisterPhone.getText().toString();
                if (!TextUtils.isEmpty((CharSequence)string) && AccountUtils.isPhone(this.suffix, string)) {
                    if (this.onRegisterViewClick != null && !CheckDoubleClick.isFastDoubleClick()) {
                        this.onRegisterViewClick.onSendCodeClick(0, this.etRegisterPhone.getText().toString(), this.suffix);
                    }
                }
                else {
                    ToastUtil.showShort((CharSequence)this.getString(R$string.enter_correct_phone));
                }
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(R$string.enter_correct_area_code));
            }
        }
        else if (view == this.btnRegister) {
            if (this.checkRegisterAgree.isChecked()) {
                final OnRegisterViewClick onRegisterViewClick2 = this.onRegisterViewClick;
                if (onRegisterViewClick2 != null) {
                    onRegisterViewClick2.onRegisterClick(0, 0, Objects.requireNonNull((Object)this.etRegisterPhone.getText()).toString(), this.suffix, this.etRegisterCode.getText().toString(), this.countryArea);
                }
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(R$string.read_agreement));
            }
        }
        else if (view == this.ibtRegisterEmail) {
            final OnRegisterViewClick onRegisterViewClick3 = this.onRegisterViewClick;
            if (onRegisterViewClick3 != null) {
                onRegisterViewClick3.onEmailClick();
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_phone_register, viewGroup, false);
        this.initView(inflate);
        return inflate;
    }
    
    public void onPause() {
        super.onPause();
        this.getActivity().unregisterReceiver(this.mUpdateReceiver);
    }
    
    public void onResume() {
        super.onResume();
        this.getActivity().registerReceiver(this.mUpdateReceiver, updateIntentFilter());
    }
    
    public void setModelData(final CountryModel countryModel) {
        this.etAreaCode.setText((CharSequence)countryModel.getValue().replace((CharSequence)"+", (CharSequence)"").trim());
        this.tvCountry.setText((CharSequence)countryModel.getKey());
        this.suffix = countryModel.getValue();
        this.countryArea = countryModel.getCountryCode();
        final String string = this.etRegisterPhone.getText().toString();
        this.setRegisterButtonEnable(!TextUtils.isEmpty((CharSequence)string) && AccountUtils.isPhone(this.suffix, string) && !TextUtils.isEmpty((CharSequence)this.etRegisterCode.getText().toString()));
    }
    
    public void setOnRegisterEventClickListener(final OnRegisterViewClick onRegisterViewClick) {
        this.onRegisterViewClick = onRegisterViewClick;
    }
    
    public void timeStart() {
        this.getActivity().startService(new Intent((Context)this.getActivity(), (Class)PhoneRegCodeTimerService.class));
        UIComponentHelper.setRegPhone(this.etRegisterPhone.getText().toString());
    }
}
