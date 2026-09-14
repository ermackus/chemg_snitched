package com.kingagroot.component.ui.account;

import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.account.service.PhonePwdCodeTimerService;
import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.PopupWindow$OnDismissListener;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.content.IntentFilter;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.text.TextWatcher;
import android.os.Bundle;
import android.text.TextUtils;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.component.ui.db.impl.CountryDbiMpl;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.view.WindowManager$LayoutParams;
import com.kingagroot.component.ui.model.CountryModel;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnGetPasswordViewListener;
import android.content.BroadcastReceiver;
import com.kingagroot.component.ui.view.ClearEditText;
import android.widget.EditText;
import com.kingagroot.component.ui.db.CountryDbi;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class GetPasswordPhoneFragment extends Fragment implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    private AppCompatButton btnPhonePassNext;
    private AppCompatButton btnSendCode;
    private CountryDbi countryDbi;
    private EditText etAreaCode;
    private EditText etPasswordCode;
    private ClearEditText etPasswordPhone;
    private boolean isArea;
    private final BroadcastReceiver mUpdateReceiver;
    private OnGetPasswordViewListener onGetPasswordViewListener;
    private String suffix;
    private TextView tvCountry;
    private TextView tvReasonCode;
    
    public GetPasswordPhoneFragment() {
        this.mUpdateReceiver = (BroadcastReceiver)new GetPasswordPhoneFragment$1(this);
    }
    
    private void backgroundAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = this.getActivity().getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getActivity().getWindow().setAttributes(attributes);
    }
    
    private void initView(final View view) {
        this.tvCountry = (TextView)view.findViewById(R$id.tv_country);
        this.etAreaCode = (EditText)view.findViewById(R$id.et_area_code);
        this.etPasswordPhone = (ClearEditText)view.findViewById(R$id.et_password_phone);
        this.etPasswordCode = (EditText)view.findViewById(R$id.et_password_code);
        this.btnSendCode = (AppCompatButton)view.findViewById(R$id.btn_send_code);
        this.btnPhonePassNext = (AppCompatButton)view.findViewById(R$id.btn_phone_pass_next);
        this.tvReasonCode = (TextView)view.findViewById(R$id.tv_reason_code);
        this.btnPhonePassNext.setEnabled(false);
        this.countryDbi = (CountryDbi)new CountryDbiMpl();
        final Bundle arguments = this.getArguments();
        this.setDataFromLogin((CountryModel)arguments.getSerializable("model"), arguments.getString("phone"));
        if (!TextUtils.isEmpty((CharSequence)UIComponentHelper.getPwdPhone())) {
            this.etPasswordPhone.setText((CharSequence)UIComponentHelper.getPwdPhone());
        }
    }
    
    private void initViewClick() {
        this.tvCountry.setOnClickListener((View$OnClickListener)this);
        this.btnSendCode.setOnClickListener((View$OnClickListener)this);
        this.btnPhonePassNext.setOnClickListener((View$OnClickListener)this);
        this.tvReasonCode.setOnClickListener((View$OnClickListener)this);
        final GetPasswordPhoneFragment.GetPasswordPhoneFragment$TextChange getPasswordPhoneFragment$TextChange = new GetPasswordPhoneFragment.GetPasswordPhoneFragment$TextChange(this);
        this.etPasswordPhone.addTextChangedListener((TextWatcher)getPasswordPhoneFragment$TextChange);
        this.etPasswordCode.addTextChangedListener((TextWatcher)getPasswordPhoneFragment$TextChange);
        this.etAreaCode.addTextChangedListener((TextWatcher)new GetPasswordPhoneFragment$2(this));
    }
    
    private void setAreaData(final CountryModel countryModel) {
        final boolean b = false;
        if (countryModel != null) {
            this.tvCountry.setText((CharSequence)countryModel.getKey());
            this.suffix = countryModel.getValue();
            final String string = this.etPasswordPhone.getText().toString();
            final AppCompatButton btnPhonePassNext = this.btnPhonePassNext;
            boolean enabled = b;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                enabled = b;
                if (AccountUtils.isPhone(this.suffix, string)) {
                    enabled = true;
                }
            }
            btnPhonePassNext.setEnabled(enabled);
            this.isArea = true;
        }
        else {
            this.tvCountry.setText(R$string.no_area);
            this.btnPhonePassNext.setEnabled(false);
            this.isArea = false;
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
        this.isArea = true;
    }
    
    private static IntentFilter updateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("PHONE_PWD_IN_RUNNING");
        intentFilter.addAction("PHONE_PWD_END_RUNNING");
        return intentFilter;
    }
    
    public void onClick(final View view) {
        if (view == this.tvCountry) {
            final OnGetPasswordViewListener onGetPasswordViewListener = this.onGetPasswordViewListener;
            if (onGetPasswordViewListener != null) {
                onGetPasswordViewListener.onChooseCountryClick();
            }
        }
        else if (view == this.btnSendCode) {
            if (!CheckDoubleClick.isFastDoubleClick()) {
                if (this.isArea) {
                    final String string = this.etPasswordPhone.getText().toString();
                    if (!TextUtils.isEmpty((CharSequence)string) && AccountUtils.isPhone(this.suffix, string)) {
                        final OnGetPasswordViewListener onGetPasswordViewListener2 = this.onGetPasswordViewListener;
                        if (onGetPasswordViewListener2 != null) {
                            onGetPasswordViewListener2.onSendCodeClick(0, this.etPasswordPhone.getText().toString(), this.suffix);
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
        }
        else if (view == this.btnPhonePassNext) {
            final OnGetPasswordViewListener onGetPasswordViewListener3 = this.onGetPasswordViewListener;
            if (onGetPasswordViewListener3 != null) {
                onGetPasswordViewListener3.onNextViewClick(0, this.etPasswordPhone.getText().toString(), this.suffix, this.etPasswordCode.getText().toString());
            }
        }
        else if (view == this.tvReasonCode) {
            final CodeExplainPop codeExplainPop = new CodeExplainPop(this.getContext(), 0);
            codeExplainPop.showAtLocation(this.getView(), 80, 0, 0);
            this.backgroundAlpha(0.4f);
            codeExplainPop.setOnDismissListener((PopupWindow$OnDismissListener)new GetPasswordPhoneFragment$3(this));
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_get_password_phone, viewGroup, false);
        this.initView(inflate);
        this.initViewClick();
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
    
    public void setDataFromLogin(final CountryModel countryModel, final String text) {
        if (countryModel != null) {
            this.tvCountry.setText((CharSequence)countryModel.getKey());
            this.etAreaCode.setText((CharSequence)countryModel.getValue().replace((CharSequence)"+", (CharSequence)""));
            this.suffix = countryModel.getValue();
            this.isArea = true;
        }
        else {
            this.setAreaDefault();
        }
        if (!TextUtils.isEmpty((CharSequence)text)) {
            this.etPasswordPhone.setText((CharSequence)text);
        }
    }
    
    public void setGetPasswordViewListener(final OnGetPasswordViewListener onGetPasswordViewListener) {
        this.onGetPasswordViewListener = onGetPasswordViewListener;
    }
    
    public void setModelData(final CountryModel countryModel) {
        this.etAreaCode.setText((CharSequence)countryModel.getValue().replace((CharSequence)"+", (CharSequence)"").trim());
        this.tvCountry.setText((CharSequence)countryModel.getKey());
        this.suffix = countryModel.getValue();
        final String string = this.etPasswordPhone.getText().toString();
        this.btnPhonePassNext.setEnabled(!TextUtils.isEmpty((CharSequence)string) && AccountUtils.isPhone(this.suffix, string));
    }
    
    public void timeStart() {
        this.getActivity().startService(new Intent((Context)this.getActivity(), (Class)PhonePwdCodeTimerService.class));
        UIComponentHelper.setPwdPhone(this.etPasswordPhone.getText().toString());
    }
}
