package com.kingagroot.kingdraw.ui.account;

import java.io.Serializable;
import com.kingagroot.kingdraw.utils.link.LoginLink$OnUserLoginListener;
import com.kingagroot.kingdraw.utils.link.LoginLink;
import android.view.KeyEvent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import com.kingagroot.component.ui.model.CountryModel;
import androidx.activity.result.ActivityResult;
import java.util.Iterator;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import com.kingagroot.kingdraw.utils.link.UserInfoLink$OnUserInfoListener;
import com.kingagroot.kingdraw.utils.link.UserInfoLink;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import android.widget.TextView;
import com.kingagroot.component.ui.account.LoginFragment;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import android.widget.ImageButton;
import androidx.fragment.app.FragmentManager;
import com.kingagroot.component.ui.account.inter.OnLoginViewListener;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class LoginMainActivity extends BaseActivity implements View$OnClickListener, OnLoginViewListener
{
    private static LoginMainActivity loginMainActivity;
    private FragmentManager fragmentManager;
    private ImageButton ibtCloseLogin;
    private final ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private LoginFragment loginFragment;
    private TextView tvRegister;
    
    public LoginMainActivity() {
        this.intentActivityResultLauncher = (ActivityResultLauncher<Intent>)this.registerForActivityResult((ActivityResultContract)new ActivityResultContracts$StartActivityForResult(), (ActivityResultCallback)new _$$Lambda$LoginMainActivity$0wN1akVKWqbQBNAjVTj2gxtWrlc(this));
        LoginMainActivity.loginMainActivity = this;
    }
    
    public static LoginMainActivity getLoginMainActivity() {
        return LoginMainActivity.loginMainActivity;
    }
    
    private void getUserInfo() {
        new UserInfoLink((UserInfoLink$OnUserInfoListener)new LoginMainActivity$2(this)).getUserInfo(true);
    }
    
    private void initView() {
        this.ibtCloseLogin = (ImageButton)this.findViewById(2131296804);
        this.tvRegister = (TextView)this.findViewById(2131297651);
        this.ibtCloseLogin.setOnClickListener((View$OnClickListener)this);
        this.tvRegister.setOnClickListener((View$OnClickListener)this);
        this.fragmentManager = this.getSupportFragmentManager();
        this.loginFragment = new LoginFragment();
        this.setTabSelection();
    }
    
    private void setTabSelection() {
        final FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        final Iterator iterator = this.fragmentManager.getFragments().iterator();
        while (iterator.hasNext()) {
            beginTransaction.remove((Fragment)iterator.next());
        }
        beginTransaction.commitNowAllowingStateLoss();
        beginTransaction.add(2131296694, (Fragment)(this.loginFragment = new LoginFragment()), "");
        this.loginFragment.setLoginClickListener((OnLoginViewListener)this);
        beginTransaction.commit();
    }
    
    public void onChooseCountryClick() {
        this.intentActivityResultLauncher.launch((Object)new Intent((Context)this, (Class)ChoiceCountryActivity.class));
    }
    
    public void onClick(final View view) {
        if (view == this.ibtCloseLogin) {
            this.finish();
        }
        else if (view == this.tvRegister) {
            this.startActivity(new Intent((Context)this, (Class)RegisterMainActivity.class));
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492895);
        this.initView();
    }
    
    public void onEmailGetPasswordClick(final String s) {
        final Intent intent = new Intent((Context)this, (Class)GetPasswordActivity.class);
        intent.putExtra(GetPasswordActivity.GET_TYPE, 1);
        intent.putExtra(GetPasswordActivity.EMAIL_KEY, s);
        this.startActivity(intent);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.finish();
        }
        return true;
    }
    
    public void onLoginClick(final String s, final String s2, final int n, final String s3, final boolean b, final String s4) {
        new LoginLink((LoginLink$OnUserLoginListener)new LoginMainActivity$1(this, b, s, s2, n, s3, s4)).userLogin(s, s2, n, s3, b);
    }
    
    public void onPhoneGetPasswordClick(final CountryModel countryModel, final String s) {
        final Intent intent = new Intent((Context)this, (Class)GetPasswordActivity.class);
        intent.putExtra(GetPasswordActivity.GET_TYPE, 0);
        intent.putExtra(GetPasswordActivity.MODEL_KEY, (Serializable)countryModel);
        intent.putExtra(GetPasswordActivity.PHONE_KEY, s);
        this.startActivity(intent);
    }
}
