package com.kingagroot.component.ui.account;

import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.R$string;
import android.view.View$OnClickListener;
import android.text.TextWatcher;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import com.kingagroot.component.ui.view.ClearEditText;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class InputPasswordFragment extends Fragment
{
    private AppCompatButton btnPasswordConfirm;
    private ClearEditText etPasswordFirst;
    private ClearEditText etPasswordSecond;
    private InputPasswordFragment.InputPasswordFragment$OnRegisterPassword onRegisterPassword;
    private String strFirstPass;
    private String strSecondPass;
    
    private void getTextData() {
        this.strFirstPass = this.etPasswordFirst.getText().toString();
        this.strSecondPass = this.etPasswordSecond.getText().toString();
    }
    
    private void initView(final View view) {
        this.etPasswordFirst = (ClearEditText)view.findViewById(R$id.et_password_first);
        this.etPasswordSecond = (ClearEditText)view.findViewById(R$id.et_password_second);
        this.btnPasswordConfirm = (AppCompatButton)view.findViewById(R$id.btn_password_confirm);
        final InputPasswordFragment.InputPasswordFragment$TextChange inputPasswordFragment$TextChange = new InputPasswordFragment.InputPasswordFragment$TextChange(this);
        this.etPasswordFirst.addTextChangedListener((TextWatcher)inputPasswordFragment$TextChange);
        this.etPasswordSecond.addTextChangedListener((TextWatcher)inputPasswordFragment$TextChange);
        this.btnPasswordConfirm.setOnClickListener((View$OnClickListener)new InputPasswordFragment$1(this));
        this.btnPasswordConfirm.setEnabled(false);
    }
    
    private boolean isInputCorrect() {
        this.getTextData();
        boolean b;
        if (!this.strFirstPass.equals((Object)this.strSecondPass)) {
            b = false;
            ToastUtil.showShort((CharSequence)this.getString(R$string.password_not_match));
        }
        else {
            b = true;
        }
        return b;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_password, viewGroup, false);
        this.initView(inflate);
        return inflate;
    }
    
    public void setOnSureClickListener(final InputPasswordFragment.InputPasswordFragment$OnRegisterPassword onRegisterPassword) {
        this.onRegisterPassword = onRegisterPassword;
    }
}
