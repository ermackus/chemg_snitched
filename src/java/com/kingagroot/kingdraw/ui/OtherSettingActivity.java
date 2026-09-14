package com.kingagroot.kingdraw.ui;

import android.content.res.Configuration;
import android.view.View;
import android.content.Intent;
import com.google.android.material.tabs.TabLayout$Tab;
import java.util.Iterator;
import android.view.View$OnClickListener;
import com.google.android.material.tabs.TabLayoutMediator$TabConfigurationStrategy;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.kingagroot.kingdraw.config.ShareData;
import java.io.Serializable;
import android.os.Bundle;
import com.kingagroot.component.ui.model.GFormatValue;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import java.util.List;
import com.goodsrc.ui.library.ToolBarActivity;

public class OtherSettingActivity extends ToolBarActivity
{
    public static final String INTENT_KEY_COLOR = "intent_key_color";
    public static final String INTENT_KEY_FORMAT = "intent_key_format";
    public static final String INTENT_SET_TYPE = "intent_set_type";
    public static final int RESULT_FORMAT_TYPE = 10011;
    private static boolean isShowAll;
    private static OtherSettingActivity settingActivity;
    private ElementSettingFragment elementSettingFragment;
    private final List<Fragment> fragments;
    private OtherSettingFragment otherSettingFragment;
    
    public OtherSettingActivity() {
        this.fragments = (List<Fragment>)new ArrayList();
        OtherSettingActivity.settingActivity = this;
    }
    
    public static OtherSettingActivity getOtherSettingActivity() {
        return OtherSettingActivity.settingActivity;
    }
    
    public static boolean getShowAll() {
        return OtherSettingActivity.isShowAll;
    }
    
    private void initData() {
        final GFormatValue gFormatValue = (GFormatValue)this.getIntent().getSerializableExtra("intent_key_format");
        final Bundle arguments = new Bundle();
        arguments.putSerializable("intent_key_format", (Serializable)gFormatValue);
        this.elementSettingFragment.setArguments(arguments);
        final boolean booleanExtra = this.getIntent().getBooleanExtra("intent_key_color", ShareData.getColorState());
        final Bundle arguments2 = new Bundle();
        arguments2.putSerializable("intent_key_color", (Serializable)booleanExtra);
        this.otherSettingFragment.setArguments(arguments2);
    }
    
    private void initView() {
        final TabLayout tabLayout = (TabLayout)this.findViewById(2131297448);
        final ViewPager2 viewPager2 = (ViewPager2)this.findViewById(2131297737);
        this.fragments.clear();
        for (final Fragment fragment : this.getSupportFragmentManager().getFragments()) {
            if (fragment instanceof ElementSettingFragment) {
                this.elementSettingFragment = (ElementSettingFragment)fragment;
            }
            else {
                if (!(fragment instanceof OtherSettingFragment)) {
                    continue;
                }
                this.otherSettingFragment = (OtherSettingFragment)fragment;
            }
        }
        final String string = this.getString(2131821144);
        final String string2 = this.getString(2131821125);
        if (this.otherSettingFragment == null) {
            this.otherSettingFragment = new OtherSettingFragment();
        }
        if (this.elementSettingFragment == null) {
            this.elementSettingFragment = new ElementSettingFragment();
        }
        this.fragments.add((Object)this.elementSettingFragment);
        this.fragments.add((Object)this.otherSettingFragment);
        viewPager2.setOrientation(0);
        viewPager2.setOffscreenPageLimit(-1);
        viewPager2.setAdapter((RecyclerView$Adapter)new OtherSettingActivity$1(this, (FragmentActivity)this));
        new TabLayoutMediator(tabLayout, viewPager2, (TabLayoutMediator$TabConfigurationStrategy)new _$$Lambda$OtherSettingActivity$24DPRczRghQvA1L_p6sR_RtYHXk(new String[] { string, string2 })).attach();
        this.elementSettingFragment.setSaveStateListener((ElementSettingFragment$OnSaveSuccessFormatListener)new _$$Lambda$OtherSettingActivity$xDgDUfjKGJiVrVW0Jwr1k2AB_nI(this));
        this.otherSettingFragment.setConfigChangeListener((OtherSettingFragment$OnConfigChangeListener)new OtherSettingActivity$2(this));
        this.toolbar.setNavigationOnClickListener((View$OnClickListener)new _$$Lambda$OtherSettingActivity$mBx8ZBkF2OrBJF3CF7mdzqgwycI(this));
    }
    
    public void onBackPressed() {
        this.elementSettingFragment.backCheck();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131821365);
        this.setContentView(2131492921);
        this.initView();
        this.initData();
        final Configuration configuration = this.getResources().getConfiguration();
        if (configuration.orientation == 2) {
            this.setRequestedOrientation(11);
        }
        else if (configuration.orientation == 1) {
            this.setRequestedOrientation(1);
        }
    }
}
