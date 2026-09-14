package com.kingagroot.component.ui.account;

import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.account.service.EmailRegCodeTimerService;
import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.widget.PopupWindow$OnDismissListener;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.content.IntentFilter;
import com.kingagroot.component.ui.model.CountryModel;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.utils.SPUtil;
import android.text.TextWatcher;
import android.text.TextUtils;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.component.ui.db.impl.CountryDbiMpl;
import com.kingagroot.component.ui.R$id;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import com.kingagroot.component.ui.R$string;
import android.view.WindowManager$LayoutParams;
import java.util.Objects;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnRegisterViewClick;
import android.content.BroadcastReceiver;
import com.kingagroot.component.ui.view.EmailAutoCompleteTextView;
import android.widget.EditText;
import com.kingagroot.component.ui.db.CountryDbi;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class RegisterEmailFragment extends Fragment implements View$OnClickListener
{
    private AppCompatButton btnEmailSendCode;
    private AppCompatButton btnRegister;
    private CheckBox checkRegisterAgree;
    private String countryArea;
    private CountryDbi countryDbi;
    private EditText etCode;
    private EmailAutoCompleteTextView etEmail;
    private final BroadcastReceiver mUpdateReceiver;
    private OnRegisterViewClick onRegisterViewClick;
    private TextView tvCountry;
    private TextView tvReasonCode;
    private TextView tvRegisterAgreement;
    
    public RegisterEmailFragment() {
        this.mUpdateReceiver = (BroadcastReceiver)new RegisterEmailFragment$1(this);
    }
    
    private void avoidHintColor(final View view) {
        if (view instanceof TextView) {
            ((TextView)view).setHighlightColor(0);
        }
    }
    
    private void backgroundAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = ((FragmentActivity)Objects.requireNonNull((Object)this.getActivity())).getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getActivity().getWindow().setAttributes(attributes);
    }
    
    private void initAgreementData() {
        final String string = this.getString(R$string.service_agreement);
        final String string2 = this.getString(R$string.privacy_agreement);
        final SpannableString spannableString = new SpannableString((CharSequence)string);
        spannableString.setSpan((Object)new RegisterEmailFragment$2(this), 0, spannableString.length(), 33);
        this.tvRegisterAgreement.append((CharSequence)spannableString);
        final SpannableString spannableString2 = new SpannableString((CharSequence)string2);
        spannableString2.setSpan((Object)new RegisterEmailFragment$3(this), 0, spannableString2.length(), 33);
        this.tvRegisterAgreement.append((CharSequence)spannableString2);
        this.tvRegisterAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    private void initView(final View view) {
        this.tvCountry = (TextView)view.findViewById(R$id.tv_country);
        this.etEmail = (EmailAutoCompleteTextView)view.findViewById(R$id.et_email);
        this.etCode = (EditText)view.findViewById(R$id.et_code);
        this.btnEmailSendCode = (AppCompatButton)view.findViewById(R$id.btn_email_send_code);
        this.checkRegisterAgree = (CheckBox)view.findViewById(R$id.check_register_agree);
        this.tvRegisterAgreement = (TextView)view.findViewById(R$id.tv_register_agreement);
        this.btnRegister = (AppCompatButton)view.findViewById(R$id.btn_register);
        this.tvReasonCode = (TextView)view.findViewById(R$id.tv_reason_code);
        this.initAgreementData();
        this.initViewClick();
        this.countryDbi = (CountryDbi)new CountryDbiMpl();
        this.setAreaDefault();
        this.btnRegister.setEnabled(false);
        if (!TextUtils.isEmpty((CharSequence)UIComponentHelper.getRegEmail())) {
            this.etEmail.setText((CharSequence)UIComponentHelper.getRegEmail());
        }
    }
    
    private void initViewClick() {
        this.tvCountry.setOnClickListener((View$OnClickListener)this);
        this.btnEmailSendCode.setOnClickListener((View$OnClickListener)this);
        this.btnRegister.setOnClickListener((View$OnClickListener)this);
        this.tvReasonCode.setOnClickListener((View$OnClickListener)this);
        final RegisterEmailFragment.RegisterEmailFragment$TextChange registerEmailFragment$TextChange = new RegisterEmailFragment.RegisterEmailFragment$TextChange(this);
        this.etEmail.addTextChangedListener((TextWatcher)registerEmailFragment$TextChange);
        this.etCode.addTextChangedListener((TextWatcher)registerEmailFragment$TextChange);
    }
    
    private void setAreaDefault() {
        final String string = SPUtil.getString("AREA", "areaKey", "");
        CountryModel countryModel;
        if (TextUtils.isEmpty((CharSequence)string)) {
            String s;
            if (LanguageTool.getLanguageType(this.getContext()).equals((Object)LanguageTool.SER_ZH)) {
                s = "+86";
            }
            else {
                s = "+1";
            }
            if ((countryModel = this.countryDbi.getCountryByName(s)) == null) {
                countryModel = DefaultData.getDefaultArea(this.getContext());
            }
        }
        else if ((countryModel = this.countryDbi.getCountryByCountryCode(string)) == null) {
            countryModel = DefaultData.getDefaultArea(this.getContext());
        }
        this.tvCountry.setText((CharSequence)countryModel.getKey());
        this.countryArea = countryModel.getCountryCode();
    }
    
    private static IntentFilter updateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("EMAIL_REG_IN_RUNNING");
        intentFilter.addAction("EMAIL_REG_END_RUNNING");
        return intentFilter;
    }
    
    public void onClick(final View view) {
        if (view == this.tvCountry) {
            final OnRegisterViewClick onRegisterViewClick = this.onRegisterViewClick;
            if (onRegisterViewClick != null) {
                onRegisterViewClick.onChooseCountryClick(1);
            }
        }
        else if (view == this.btnEmailSendCode) {
            if (AccountUtils.isEmail(this.etEmail.getText().toString())) {
                if (this.onRegisterViewClick != null && !CheckDoubleClick.isFastDoubleClick()) {
                    this.onRegisterViewClick.onSendCodeClick(1, this.etEmail.getText().toString(), "");
                }
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(R$string.enter_correct_email));
            }
        }
        else if (view == this.btnRegister) {
            if (this.checkRegisterAgree.isChecked()) {
                final OnRegisterViewClick onRegisterViewClick2 = this.onRegisterViewClick;
                if (onRegisterViewClick2 != null) {
                    onRegisterViewClick2.onRegisterClick(1, 0, this.etEmail.getText().toString(), "", this.etCode.getText().toString(), this.countryArea);
                }
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(R$string.read_agreement));
            }
        }
        else if (view == this.tvReasonCode) {
            final CodeExplainPop codeExplainPop = new CodeExplainPop(this.getContext(), 1);
            codeExplainPop.showAtLocation(this.getView(), 80, 0, 0);
            this.backgroundAlpha(0.4f);
            codeExplainPop.setOnDismissListener((PopupWindow$OnDismissListener)new RegisterEmailFragment$4(this));
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_email_register, viewGroup, false);
        this.initView(inflate);
        return inflate;
    }
    
    public void onPause() {
        super.onPause();
        ((FragmentActivity)Objects.requireNonNull((Object)this.getActivity())).unregisterReceiver(this.mUpdateReceiver);
    }
    
    public void onResume() {
        super.onResume();
        ((FragmentActivity)Objects.requireNonNull((Object)this.getActivity())).registerReceiver(this.mUpdateReceiver, updateIntentFilter());
    }
    
    public void setModelData(final CountryModel countryModel) {
        this.tvCountry.setText((CharSequence)countryModel.getKey());
        this.countryArea = countryModel.getCountryCode();
        this.btnRegister.setEnabled(!TextUtils.isEmpty((CharSequence)this.tvCountry.getText().toString()) && AccountUtils.isEmail(this.etEmail.getText().toString()) && this.etCode.length() > 3 && this.etCode.length() < 7);
    }
    
    public void setOnRegisterEventClickListener(final OnRegisterViewClick onRegisterViewClick) {
        this.onRegisterViewClick = onRegisterViewClick;
    }
    
    public void timeStart() {
        ((FragmentActivity)Objects.requireNonNull((Object)this.getActivity())).startService(new Intent((Context)this.getActivity(), (Class)EmailRegCodeTimerService.class));
        UIComponentHelper.setRegEmail(this.etEmail.getText().toString());
    }
}
