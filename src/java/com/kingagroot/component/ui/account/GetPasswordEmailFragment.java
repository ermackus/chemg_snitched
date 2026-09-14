package com.kingagroot.component.ui.account;

import android.content.Context;
import android.content.Intent;
import com.kingagroot.component.ui.account.service.EmailPwdCodeTimerService;
import com.kingagroot.component.ui.R$layout;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.PopupWindow$OnDismissListener;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.utils.AccountUtils;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import android.content.IntentFilter;
import com.kingagroot.component.ui.UIComponentHelper;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.view.WindowManager$LayoutParams;
import android.widget.TextView;
import com.kingagroot.component.ui.account.inter.OnGetPasswordViewListener;
import android.content.BroadcastReceiver;
import com.kingagroot.component.ui.view.EmailAutoCompleteTextView;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import androidx.fragment.app.Fragment;

public class GetPasswordEmailFragment extends Fragment implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    private AppCompatButton btnEmailPassNext;
    private AppCompatButton btnSendCode;
    private EditText etCode;
    private EmailAutoCompleteTextView etPasswordEmail;
    private final BroadcastReceiver mUpdateReceiver;
    private OnGetPasswordViewListener onGetPasswordViewListener;
    private TextView tvReasonCode;
    
    public GetPasswordEmailFragment() {
        this.mUpdateReceiver = (BroadcastReceiver)new GetPasswordEmailFragment$1(this);
    }
    
    private void backgroundAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = this.getActivity().getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getActivity().getWindow().setAttributes(attributes);
    }
    
    private void initView(final View view) {
        this.etPasswordEmail = (EmailAutoCompleteTextView)view.findViewById(R$id.et_password_email);
        this.etCode = (EditText)view.findViewById(R$id.et_code);
        this.btnSendCode = (AppCompatButton)view.findViewById(R$id.btn_send_code);
        this.btnEmailPassNext = (AppCompatButton)view.findViewById(R$id.btn_email_pass_next);
        this.tvReasonCode = (TextView)view.findViewById(R$id.tv_reason_code);
        this.btnEmailPassNext.setEnabled(false);
        this.btnSendCode.setOnClickListener((View$OnClickListener)this);
        this.btnEmailPassNext.setOnClickListener((View$OnClickListener)this);
        this.tvReasonCode.setOnClickListener((View$OnClickListener)this);
        final GetPasswordEmailFragment.GetPasswordEmailFragment$TextChange getPasswordEmailFragment$TextChange = new GetPasswordEmailFragment.GetPasswordEmailFragment$TextChange(this);
        this.etPasswordEmail.addTextChangedListener((TextWatcher)getPasswordEmailFragment$TextChange);
        this.etCode.addTextChangedListener((TextWatcher)getPasswordEmailFragment$TextChange);
        final String string = this.getArguments().getString("email");
        if (!TextUtils.isEmpty((CharSequence)string)) {
            this.etPasswordEmail.setText((CharSequence)string);
        }
        if (!TextUtils.isEmpty((CharSequence)UIComponentHelper.getPwdEmail())) {
            this.etPasswordEmail.setText((CharSequence)UIComponentHelper.getPwdEmail());
        }
    }
    
    private static IntentFilter updateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Email_PWD_IN_RUNNING");
        intentFilter.addAction("Email_PWD_END_RUNNING");
        return intentFilter;
    }
    
    public void onClick(final View view) {
        if (view == this.btnSendCode) {
            if (!CheckDoubleClick.isFastDoubleClick()) {
                if (AccountUtils.isEmail(this.etPasswordEmail.getText().toString())) {
                    final OnGetPasswordViewListener onGetPasswordViewListener = this.onGetPasswordViewListener;
                    if (onGetPasswordViewListener != null) {
                        onGetPasswordViewListener.onSendCodeClick(1, this.etPasswordEmail.getText().toString(), "");
                    }
                }
                else {
                    ToastUtil.showShort((CharSequence)this.getString(R$string.enter_correct_email));
                }
            }
        }
        else if (view == this.btnEmailPassNext) {
            final OnGetPasswordViewListener onGetPasswordViewListener2 = this.onGetPasswordViewListener;
            if (onGetPasswordViewListener2 != null) {
                onGetPasswordViewListener2.onNextViewClick(1, this.etPasswordEmail.getText().toString(), "", this.etCode.getText().toString());
            }
        }
        else if (view == this.tvReasonCode) {
            final CodeExplainPop codeExplainPop = new CodeExplainPop(this.getContext(), 1);
            codeExplainPop.showAtLocation(this.getView(), 80, 0, 0);
            this.backgroundAlpha(0.4f);
            codeExplainPop.setOnDismissListener((PopupWindow$OnDismissListener)new GetPasswordEmailFragment$2(this));
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_get_password_email, viewGroup, false);
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
    
    public void setOnEmailGetPasswordListener(final OnGetPasswordViewListener onGetPasswordViewListener) {
        this.onGetPasswordViewListener = onGetPasswordViewListener;
    }
    
    public void timeStart() {
        this.getActivity().startService(new Intent((Context)this.getActivity(), (Class)EmailPwdCodeTimerService.class));
        UIComponentHelper.setPwdEmail(this.etPasswordEmail.getText().toString());
    }
}
