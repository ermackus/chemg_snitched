package com.luck.picture.lib.basic;

import com.luck.picture.lib.R$layout;
import android.os.Bundle;
import android.content.res.Configuration;
import com.luck.picture.lib.language.PictureLanguageUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.luck.picture.lib.PictureSelectorFragment;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.immersive.ImmersiveManager;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.R$color;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.config.SelectorConfig;
import androidx.appcompat.app.AppCompatActivity;

public class PictureSelectorSupporterActivity extends AppCompatActivity
{
    private SelectorConfig selectorConfig;
    
    private void immersive() {
        final SelectMainStyle selectMainStyle = this.selectorConfig.selectorStyle.getSelectMainStyle();
        final int statusBarColor = selectMainStyle.getStatusBarColor();
        final int navigationBarColor = selectMainStyle.getNavigationBarColor();
        final boolean darkStatusBarBlack = selectMainStyle.isDarkStatusBarBlack();
        int color = statusBarColor;
        if (!StyleUtils.checkStyleValidity(statusBarColor)) {
            color = ContextCompat.getColor((Context)this, R$color.ps_color_grey);
        }
        int color2 = navigationBarColor;
        if (!StyleUtils.checkStyleValidity(navigationBarColor)) {
            color2 = ContextCompat.getColor((Context)this, R$color.ps_color_grey);
        }
        ImmersiveManager.immersiveAboveAPI23((AppCompatActivity)this, color, color2, darkStatusBarBlack);
    }
    
    private void initSelectorConfig() {
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
    }
    
    private void setupFragment() {
        FragmentInjectManager.injectFragment((FragmentActivity)this, PictureSelectorFragment.TAG, (Fragment)PictureSelectorFragment.newInstance());
    }
    
    protected void attachBaseContext(final Context context) {
        final SelectorConfig selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        if (selectorConfig != null) {
            super.attachBaseContext((Context)PictureContextWrapper.wrap(context, selectorConfig.language, selectorConfig.defaultLanguage));
        }
        else {
            super.attachBaseContext(context);
        }
    }
    
    public void finish() {
        super.finish();
        final SelectorConfig selectorConfig = this.selectorConfig;
        if (selectorConfig != null) {
            this.overridePendingTransition(0, selectorConfig.selectorStyle.getWindowAnimationStyle().activityExitAnimation);
        }
    }
    
    public void initAppLanguage() {
        final SelectorConfig selectorConfig = this.selectorConfig;
        if (selectorConfig != null && selectorConfig.language != -2 && !this.selectorConfig.isOnlyCamera) {
            PictureLanguageUtils.setAppLanguage((Context)this, this.selectorConfig.language, this.selectorConfig.defaultLanguage);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.initAppLanguage();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.initSelectorConfig();
        this.immersive();
        this.setContentView(R$layout.ps_activity_container);
        this.setupFragment();
    }
}
