package com.kingagroot.kingdraw.ui.account;

import android.view.MenuItem;
import android.view.KeyEvent;
import android.content.Context;
import com.kingagroot.kingdraw.utils.link.NewVerifyCodeUtils$OnVerifyCodeListener;
import com.kingagroot.kingdraw.utils.link.NewVerifyCodeUtils;
import com.kingagroot.kingdraw.model.SlideModel;
import androidx.activity.result.ActivityResult;
import java.util.Iterator;
import androidx.fragment.app.FragmentTransaction;
import java.io.Serializable;
import android.os.Bundle;
import com.kingagroot.component.ui.model.CountryModel;
import androidx.fragment.app.Fragment;
import java.util.Objects;
import android.os.Handler;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import com.kingagroot.kingdraw.utils.ShaUtil;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import com.kingagroot.component.ui.account.InputPasswordFragment;
import com.kingagroot.component.ui.account.GetPasswordPhoneFragment;
import com.kingagroot.component.ui.account.GetPasswordEmailFragment;
import androidx.fragment.app.FragmentManager;
import com.kingagroot.component.ui.account.InputPasswordFragment$OnRegisterPassword;
import com.kingagroot.component.ui.account.inter.OnGetPasswordViewListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class GetPasswordActivity extends ToolBarActivity implements OnGetPasswordViewListener, InputPasswordFragment$OnRegisterPassword
{
    public static String EMAIL_KEY = "EMAIL_KEY";
    public static String GET_TYPE = "GET_TYPE";
    public static String MODEL_KEY = "MODEL_KEY";
    public static String PHONE_KEY = "PHONE_KEY";
    private static final int REQUEST_CODE_COUNTRY = 1000;
    private String code;
    private FragmentManager fragmentManager;
    private GetPasswordEmailFragment getPasswordEmailFragment;
    private GetPasswordPhoneFragment getPasswordPhoneFragment;
    private InputPasswordFragment inputPasswordFragment;
    private final ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private int pwdType;
    private String suffix;
    private String userName;
    
    public GetPasswordActivity() {
        this.intentActivityResultLauncher = (ActivityResultLauncher<Intent>)this.registerForActivityResult((ActivityResultContract)new ActivityResultContracts$StartActivityForResult(), (ActivityResultCallback)new _$$Lambda$GetPasswordActivity$S4zoEha8FKJZLTxu66pPGqzJ3PQ(this));
    }
    
    private void getPassword(final String s, final String s2, final String s3, final String s4) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getPassword());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", (Object)s);
            jsonObject.put("password", (Object)ShaUtil.sha(s2));
            jsonObject.put("repeatPassword", (Object)ShaUtil.sha(s3));
            jsonObject.put("smsCode", (Object)s4);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new GetPasswordActivity$3(this));
    }
    
    private void setTabSelection(final int n, final String title) {
        final FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
        this.fragmentManager.getFragments();
        final Iterator iterator = this.fragmentManager.getFragments().iterator();
        while (iterator.hasNext()) {
            beginTransaction.hide((Fragment)iterator.next());
        }
        if (n == 0) {
            final GetPasswordPhoneFragment getPasswordPhoneFragment = (GetPasswordPhoneFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.getPasswordPhoneFragment = getPasswordPhoneFragment) == null) {
                beginTransaction.add(2131296692, (Fragment)(this.getPasswordPhoneFragment = new GetPasswordPhoneFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)getPasswordPhoneFragment);
            }
            final CountryModel countryModel = (CountryModel)this.getIntent().getSerializableExtra(GetPasswordActivity.MODEL_KEY);
            final String stringExtra = this.getIntent().getStringExtra(GetPasswordActivity.PHONE_KEY);
            final Bundle arguments = new Bundle();
            arguments.putString("phone", stringExtra);
            arguments.putSerializable("model", (Serializable)countryModel);
            this.getPasswordPhoneFragment.setArguments(arguments);
            this.getPasswordPhoneFragment.setGetPasswordViewListener((OnGetPasswordViewListener)this);
        }
        else if (n == 1) {
            final GetPasswordEmailFragment getPasswordEmailFragment = (GetPasswordEmailFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.getPasswordEmailFragment = getPasswordEmailFragment) == null) {
                beginTransaction.add(2131296692, (Fragment)(this.getPasswordEmailFragment = new GetPasswordEmailFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)getPasswordEmailFragment);
            }
            final String stringExtra2 = this.getIntent().getStringExtra(GetPasswordActivity.EMAIL_KEY);
            final Bundle arguments2 = new Bundle();
            arguments2.putString("email", stringExtra2);
            this.getPasswordEmailFragment.setArguments(arguments2);
            this.getPasswordEmailFragment.setOnEmailGetPasswordListener((OnGetPasswordViewListener)this);
        }
        else if (n == 2) {
            final InputPasswordFragment inputPasswordFragment = (InputPasswordFragment)this.fragmentManager.findFragmentByTag(title);
            if ((this.inputPasswordFragment = inputPasswordFragment) == null) {
                beginTransaction.add(2131296692, (Fragment)(this.inputPasswordFragment = new InputPasswordFragment()), title);
            }
            else {
                beginTransaction.show((Fragment)inputPasswordFragment);
            }
            this.inputPasswordFragment.setOnSureClickListener((InputPasswordFragment$OnRegisterPassword)this);
        }
        this.setTitle((CharSequence)title);
        beginTransaction.addToBackStack(title);
        beginTransaction.commit();
    }
    
    private void userNext(final int n, final String s, final String s2, final String s3) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.getNextCode());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sendType", n);
            jsonObject.put("wayType", 1);
            jsonObject.put("userName", (Object)s);
            jsonObject.put("suffix", (Object)s2);
            jsonObject.put("code", (Object)s3);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new GetPasswordActivity$2(this));
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1000 && n2 == -1 && intent != null) {
            this.getPasswordPhoneFragment.setModelData((CountryModel)Objects.requireNonNull((Object)intent.getSerializableExtra("CountryModel")));
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
    
    public void onChooseCountryClick() {
        this.intentActivityResultLauncher.launch((Object)new Intent((Context)this, (Class)ChoiceCountryActivity.class));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492894);
        this.fragmentManager = this.getSupportFragmentManager();
        this.getPasswordPhoneFragment = new GetPasswordPhoneFragment();
        this.getPasswordEmailFragment = new GetPasswordEmailFragment();
        this.inputPasswordFragment = new InputPasswordFragment();
        final int intExtra = this.getIntent().getIntExtra(GetPasswordActivity.GET_TYPE, 0);
        if (intExtra == 0) {
            this.setTabSelection(0, this.getString(2131820908));
        }
        else if (intExtra == 1) {
            this.setTabSelection(1, this.getString(2131820908));
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.onBackPressed();
        }
        return true;
    }
    
    public void onNextViewClick(final int pwdType, final String userName, final String suffix, final String code) {
        this.userNext(this.pwdType = pwdType, this.userName = userName, this.suffix = suffix, this.code = code);
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
        }
        return false;
    }
    
    public void onSendCodeClick(final int n, final String s, final String s2) {
        final CodeSliderPop codeSliderPop = new CodeSliderPop((Context)this);
        codeSliderPop.setOnSliderInfoListener((CodeSliderPop$OnSliderInfoListener)new _$$Lambda$GetPasswordActivity$U3Rli_qu01DyQ0Ijv0UX9HSS2z4(this, codeSliderPop, n, s, s2));
        codeSliderPop.show();
    }
    
    public void onSureClick(final String s) {
        final int pwdType = this.pwdType;
        String s2;
        if (pwdType == 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.suffix);
            sb.append("-");
            sb.append(this.userName);
            s2 = sb.toString();
        }
        else if (pwdType == 1) {
            s2 = this.userName;
        }
        else {
            s2 = null;
        }
        this.getPassword(s2, s, s, this.code);
    }
}
