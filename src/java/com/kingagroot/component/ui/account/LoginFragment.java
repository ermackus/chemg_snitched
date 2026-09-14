package com.kingagroot.component.ui.account;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.kingagroot.component.ui.R$layout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.component.ui.model.CountryModel;
import java.util.Objects;
import com.google.android.material.tabs.TabLayout$Tab;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.component.ui.utils.AccountUtils;
import androidx.viewpager.widget.PagerAdapter;
import com.kingagroot.component.ui.R$string;
import androidx.viewpager.widget.ViewPager;
import com.kingagroot.component.ui.R$id;
import com.google.android.material.tabs.TabLayout;
import android.view.View;
import android.view.WindowManager$LayoutParams;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;
import com.kingagroot.component.ui.account.inter.OnLoginViewListener;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment implements OnLoginViewListener
{
    private final List<Fragment> fragmentList;
    private LoginEmailFragment loginEmailFragment;
    private LoginPhoneFragment loginPhoneFragment;
    private Context mContext;
    private OnLoginViewListener onLoginViewListener;
    private String[] strings;
    
    public LoginFragment() {
        this.fragmentList = (List<Fragment>)new ArrayList();
    }
    
    private void backgroundAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = this.getActivity().getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getActivity().getWindow().setAttributes(attributes);
    }
    
    private void initView(final View view) {
        final TabLayout tabLayout = (TabLayout)view.findViewById(R$id.tab_layout);
        final ViewPager viewPager = (ViewPager)view.findViewById(R$id.vp_login);
        this.strings = new String[] { this.mContext.getString(R$string.login_phone), this.mContext.getString(R$string.login_email) };
        this.loginPhoneFragment = new LoginPhoneFragment();
        this.loginEmailFragment = new LoginEmailFragment();
        this.fragmentList.add((Object)this.loginPhoneFragment);
        this.fragmentList.add((Object)this.loginEmailFragment);
        viewPager.setAdapter((PagerAdapter)new MyAdapter(this.getActivity().getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        final int loginType = AccountUtils.getLoginType();
        if (loginType == -1) {
            final String string = SPUtil.getString("COUNTRY_IP", "ipCode", "CN");
            if (!"CN".equals((Object)string) && !"MO".equals((Object)string) && !"HK".equals((Object)string) && !"TW".equals((Object)string)) {
                ((TabLayout$Tab)Objects.requireNonNull((Object)tabLayout.getTabAt(1))).select();
                viewPager.setCurrentItem(1);
            }
            else {
                ((TabLayout$Tab)Objects.requireNonNull((Object)tabLayout.getTabAt(0))).select();
                viewPager.setCurrentItem(0);
            }
        }
        else if (loginType == 0) {
            ((TabLayout$Tab)Objects.requireNonNull((Object)tabLayout.getTabAt(0))).select();
            viewPager.setCurrentItem(0);
        }
        else {
            ((TabLayout$Tab)Objects.requireNonNull((Object)tabLayout.getTabAt(1))).select();
            viewPager.setCurrentItem(1);
        }
        this.loginPhoneFragment.setLoginViewClickListener((OnLoginViewListener)this);
        this.loginEmailFragment.setLoginViewClickListener((OnLoginViewListener)this);
    }
    
    private void showPop(final CountryModel countryModel, final String s, final String s2) {
        final GetPwdPop getPwdPop = new GetPwdPop(this.getContext());
        getPwdPop.setOnPwdPopClickListener((GetPwdPop$OnPwdPopClickListener)new LoginFragment$1(this, countryModel, s, s2));
        getPwdPop.showAtLocation(this.getView(), 80, 0, 0);
        this.backgroundAlpha(0.4f);
        getPwdPop.setOnDismissListener((PopupWindow$OnDismissListener)new LoginFragment$2(this));
    }
    
    public void onChooseCountryClick() {
        final OnLoginViewListener onLoginViewListener = this.onLoginViewListener;
        if (onLoginViewListener != null) {
            onLoginViewListener.onChooseCountryClick();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = (Context)this.getActivity();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(R$layout.fragment_login_account, viewGroup, false);
        this.initView(inflate);
        return inflate;
    }
    
    public void onEmailGetPasswordClick(final String s) {
        this.showPop(null, "", s);
    }
    
    public void onLoginClick(final String s, final String s2, final int n, final String s3, final boolean b, final String s4) {
        final OnLoginViewListener onLoginViewListener = this.onLoginViewListener;
        if (onLoginViewListener != null) {
            onLoginViewListener.onLoginClick(s, s2, n, s3, b, s4);
        }
    }
    
    public void onPhoneGetPasswordClick(final CountryModel countryModel, final String s) {
        this.showPop(countryModel, s, "");
    }
    
    public void setLoginClickListener(final OnLoginViewListener onLoginViewListener) {
        this.onLoginViewListener = onLoginViewListener;
    }
    
    public void setModelData(final CountryModel modelData) {
        this.loginPhoneFragment.setModelData(modelData);
    }
    
    public class MyAdapter extends FragmentPagerAdapter
    {
        final LoginFragment this$0;
        
        public MyAdapter(final LoginFragment this$0, final FragmentManager fragmentManager) {
            this.this$0 = this$0;
            super(fragmentManager);
        }
        
        public int getCount() {
            return 2;
        }
        
        public Fragment getItem(final int n) {
            return (Fragment)this.this$0.fragmentList.get(n);
        }
        
        public CharSequence getPageTitle(final int n) {
            return (CharSequence)this.this$0.strings[n];
        }
    }
}
