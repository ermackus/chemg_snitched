package com.kingagroot.kingdraw.ui.account;

import com.kingagroot.kingdraw.ui.WebViewActivity;
import java.util.Map;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import java.util.HashMap;
import com.kingagroot.kingdraw.config.ServerInfoConfig;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.os.Bundle;
import com.kingagroot.component.ui.model.CountryModel;
import android.content.Intent;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.utils.link.NewVerifyCodeUtils$OnVerifyCodeListener;
import com.kingagroot.kingdraw.utils.link.NewVerifyCodeUtils;
import com.kingagroot.kingdraw.model.SlideModel;
import android.app.AlertDialog;
import android.os.Build$VERSION;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import java.util.Iterator;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import com.kingagroot.kingdraw.utils.ShaUtil;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import java.util.Objects;
import android.os.Handler;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.kingdraw.utils.link.UserInfoLink$OnUserInfoListener;
import com.kingagroot.kingdraw.utils.link.UserInfoLink;
import com.kingagroot.component.ui.account.RegisterPhoneFragment;
import com.kingagroot.component.ui.account.RegisterEmailFragment;
import com.kingagroot.component.ui.account.InputPasswordFragment;
import androidx.fragment.app.FragmentManager;
import com.kingagroot.component.ui.account.InputPasswordFragment$OnRegisterPassword;
import com.kingagroot.component.ui.account.inter.OnRegisterViewClick;
import com.goodsrc.ui.library.ToolBarActivity;

public class RegisterMainActivity extends ToolBarActivity implements OnRegisterViewClick, InputPasswordFragment$OnRegisterPassword
{
    private static final int REQUEST_CODE_COUNTRY_EMAIL = 2000;
    private static final int REQUEST_CODE_COUNTRY_PHONE = 1000;
    private String code;
    private String countryArea;
    private FragmentManager fragmentManager;
    private InputPasswordFragment inputPasswordFragment;
    private RegisterEmailFragment registerEmailFragment;
    private RegisterPhoneFragment registerPhoneFragment;
    private int seedType;
    private String suffix;
    private String userName;
    
    private void getUserInfo() {
        new UserInfoLink((UserInfoLink$OnUserInfoListener)new RegisterMainActivity$5(this)).getUserInfo(true);
    }
    
    private void initView() {
        this.fragmentManager = this.getSupportFragmentManager();
        this.registerPhoneFragment = new RegisterPhoneFragment();
        this.inputPasswordFragment = new InputPasswordFragment();
        this.registerEmailFragment = new RegisterEmailFragment();
        final String string = SPUtil.getString("COUNTRY_IP", "ipCode", "CN");
        if (!"CN".equals((Object)string) && !"MO".equals((Object)string) && !"HK".equals((Object)string) && !"TW".equals((Object)string)) {
            this.setTabSelection(2, this.getString(2131821312));
        }
        else {
            this.setTabSelection(0, this.getString(2131821312));
        }
    }
    
    private void setPassword(final String s, final String s2, final String s3, final String s4, final String s5) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getRegisterUrl());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", (Object)s);
            jsonObject.put("password", (Object)ShaUtil.sha(s2));
            jsonObject.put("smsCode", (Object)s3);
            jsonObject.put("regType", (Object)s4);
            jsonObject.put("CountryArea", (Object)s5);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new RegisterMainActivity$4(this, s2, s5));
    }
    
    private void setTabSelection(final int n, final String title) {
        final FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        this.fragmentManager.getFragments();
        final Iterator iterator = this.fragmentManager.getFragments().iterator();
        while (iterator.hasNext()) {
            beginTransaction.hide((Fragment)iterator.next());
        }
        if (n == 0) {
            this.setTitle((CharSequence)title);
            final RegisterPhoneFragment registerPhoneFragment = (RegisterPhoneFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.registerPhoneFragment = registerPhoneFragment) == null) {
                beginTransaction.add(2131296700, (Fragment)(this.registerPhoneFragment = new RegisterPhoneFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)registerPhoneFragment);
            }
            this.registerPhoneFragment.setOnRegisterEventClickListener((OnRegisterViewClick)this);
        }
        else if (n == 1) {
            this.setTitle((CharSequence)title);
            final InputPasswordFragment inputPasswordFragment = (InputPasswordFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.inputPasswordFragment = inputPasswordFragment) == null) {
                beginTransaction.add(2131296700, (Fragment)(this.inputPasswordFragment = new InputPasswordFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)inputPasswordFragment);
            }
            this.inputPasswordFragment.setOnSureClickListener((InputPasswordFragment$OnRegisterPassword)this);
        }
        else if (n == 2) {
            this.setTitle((CharSequence)title);
            final RegisterEmailFragment registerEmailFragment = (RegisterEmailFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.registerEmailFragment = registerEmailFragment) == null) {
                beginTransaction.add(2131296700, (Fragment)(this.registerEmailFragment = new RegisterEmailFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)registerEmailFragment);
            }
            this.registerEmailFragment.setOnRegisterEventClickListener((OnRegisterViewClick)this);
        }
        beginTransaction.addToBackStack(title);
        beginTransaction.commit();
    }
    
    private void showRegisterAgainDialog(final String message, final String s, final String s2) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)message).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$RegisterMainActivity$DwY7ndlnnKQ6devqBgmSRUA1q0g(this, s2, s));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
        if (Build$VERSION.SDK_INT >= 23) {
            create.getButton(-1).setTextColor(this.getColor(2131099695));
            create.getButton(-3).setTextColor(this.getColor(2131099695));
            create.getButton(-2).setTextColor(this.getColor(2131099695));
        }
    }
    
    private void userRegister(final int n, final int n2, final String s, final String s2, final String s3) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getNextCode());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sendType", n);
            jsonObject.put("wayType", n2);
            jsonObject.put("userName", (Object)s);
            jsonObject.put("suffix", (Object)s2);
            jsonObject.put("code", (Object)s3);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new RegisterMainActivity$3(this));
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1000) {
            if (n2 == -1 && intent != null) {
                this.registerPhoneFragment.setModelData((CountryModel)Objects.requireNonNull((Object)intent.getSerializableExtra("CountryModel")));
            }
        }
        else if (n == 2000 && n2 == -1 && intent != null) {
            this.registerEmailFragment.setModelData((CountryModel)Objects.requireNonNull((Object)intent.getSerializableExtra("CountryModel")));
        }
    }
    
    public void onBackPressed() {
        if (this.fragmentManager.getBackStackEntryCount() > 1) {
            this.fragmentManager.popBackStackImmediate();
            final FragmentManager fragmentManager = this.fragmentManager;
            this.setTitle((CharSequence)fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName());
        }
        else {
            this.finish();
        }
    }
    
    public void onChooseCountryClick(final int n) {
        if (n == 0) {
            this.startActivityForResult(new Intent((Context)this, (Class)ChoiceCountryActivity.class), 1000);
        }
        else {
            this.startActivityForResult(new Intent((Context)this, (Class)ChoiceCountryActivity.class), 2000);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131821307);
        this.setContentView(2131492928);
        this.initView();
    }
    
    public void onEmailClick() {
        this.setTabSelection(2, this.getString(2131820794));
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.onBackPressed();
        }
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
        }
        return false;
    }
    
    public void onPrivacyClick() {
        final String privacyPolicy = ServerInfoConfig.getPrivacyPolicy();
        final HashMap hashMap = new HashMap(1);
        ((Map)hashMap).put((Object)"ContentType", (Object)privacyPolicy);
        final String url = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map)hashMap);
        final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
        intent.putExtra("url_key", url);
        this.startActivity(intent);
    }
    
    public void onRegisterClick(final int seedType, final int n, final String userName, final String suffix, final String code, final String countryArea) {
        this.userName = userName;
        this.suffix = suffix;
        this.seedType = seedType;
        this.code = code;
        this.countryArea = countryArea;
        this.userRegister(seedType, n, userName, suffix, code);
    }
    
    public void onSendCodeClick(final int n, final String s, final String s2) {
        if (n == 0) {
            final CodeSliderPop codeSliderPop = new CodeSliderPop((Context)this);
            codeSliderPop.setOnSliderInfoListener((CodeSliderPop$OnSliderInfoListener)new _$$Lambda$RegisterMainActivity$MMKkV8hIF8lz3iBStWWKkGkq5wQ(this, codeSliderPop, n, s, s2));
            codeSliderPop.show();
        }
        else if (n == 1) {
            new NewVerifyCodeUtils((NewVerifyCodeUtils$OnVerifyCodeListener)new RegisterMainActivity$2(this)).getVerifyCode(n, 0, s, s2);
        }
    }
    
    public void onServiceClick() {
        final String serviceAgreement = ServerInfoConfig.getServiceAgreement();
        final HashMap hashMap = new HashMap(1);
        ((Map)hashMap).put((Object)"ContentType", (Object)serviceAgreement);
        final String url = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map)hashMap);
        final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
        intent.putExtra("url_key", url);
        this.startActivity(intent);
    }
    
    public void onSureClick(final String s) {
        String s2;
        String s3;
        if (this.seedType == 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.suffix);
            sb.append("-");
            sb.append(this.userName);
            s2 = sb.toString();
            s3 = "phone";
        }
        else {
            s2 = this.userName;
            s3 = "email";
        }
        this.setPassword(s2, s, this.code, s3, this.countryArea);
    }
}
