package com.luck.picture.lib.basic;

import com.luck.picture.lib.R$layout;
import android.os.Bundle;
import com.luck.picture.lib.R$anim;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.PictureOnlyCameraFragment;
import java.util.Collection;
import java.util.ArrayList;
import com.luck.picture.lib.PictureSelectorPreviewFragment;
import com.luck.picture.lib.PictureSelectorSystemFragment;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.content.Intent;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.immersive.ImmersiveManager;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.R$color;
import com.luck.picture.lib.utils.StyleUtils;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.config.SelectorConfig;
import androidx.appcompat.app.AppCompatActivity;

public class PictureSelectorTransparentActivity extends AppCompatActivity
{
    private SelectorConfig selectorConfig;
    
    private void immersive() {
        if (this.selectorConfig.selectorStyle == null) {
            SelectorProviders.getInstance().getSelectorConfig();
        }
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
    
    private boolean isExternalPreview() {
        final Intent intent = this.getIntent();
        boolean b = false;
        if (intent.getIntExtra("com.luck.picture.lib.mode_type_source", 0) == 2) {
            b = true;
        }
        return b;
    }
    
    private void setActivitySize() {
        final Window window = this.getWindow();
        window.setGravity(51);
        final WindowManager$LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = 1;
        attributes.width = 1;
        window.setAttributes(attributes);
    }
    
    private void setupFragment() {
        final int intExtra = this.getIntent().getIntExtra("com.luck.picture.lib.mode_type_source", 0);
        String s;
        Object o;
        if (intExtra == 1) {
            s = PictureSelectorSystemFragment.TAG;
            o = PictureSelectorSystemFragment.newInstance();
        }
        else if (intExtra == 2) {
            Object onInjectPreviewFragment;
            if (this.selectorConfig.onInjectActivityPreviewListener != null) {
                onInjectPreviewFragment = this.selectorConfig.onInjectActivityPreviewListener.onInjectPreviewFragment();
            }
            else {
                onInjectPreviewFragment = null;
            }
            if (onInjectPreviewFragment != null) {
                final String fragmentTag = ((PictureSelectorPreviewFragment)onInjectPreviewFragment).getFragmentTag();
                o = onInjectPreviewFragment;
                s = fragmentTag;
            }
            else {
                s = PictureSelectorPreviewFragment.TAG;
                o = PictureSelectorPreviewFragment.newInstance();
            }
            final int intExtra2 = this.getIntent().getIntExtra("com.luck.picture.lib.current_preview_position", 0);
            final ArrayList list = new ArrayList((Collection)this.selectorConfig.selectedPreviewResult);
            ((PictureSelectorPreviewFragment)o).setExternalPreviewData(intExtra2, list.size(), list, this.getIntent().getBooleanExtra("com.luck.picture.lib.external_preview_display_delete", false));
        }
        else {
            s = PictureOnlyCameraFragment.TAG;
            o = PictureOnlyCameraFragment.newInstance();
        }
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        final Fragment fragmentByTag = supportFragmentManager.findFragmentByTag(s);
        if (fragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(fragmentByTag).commitAllowingStateLoss();
        }
        FragmentInjectManager.injectSystemRoomFragment(supportFragmentManager, s, (Fragment)o);
    }
    
    public void finish() {
        super.finish();
        if (this.getIntent().getIntExtra("com.luck.picture.lib.mode_type_source", 0) == 2 && !this.selectorConfig.isPreviewZoomEffect) {
            this.overridePendingTransition(0, this.selectorConfig.selectorStyle.getWindowAnimationStyle().activityExitAnimation);
        }
        else {
            this.overridePendingTransition(0, R$anim.ps_anim_fade_out);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.initSelectorConfig();
        this.immersive();
        this.setContentView(R$layout.ps_empty);
        if (!this.isExternalPreview()) {
            this.setActivitySize();
        }
        this.setupFragment();
    }
}
