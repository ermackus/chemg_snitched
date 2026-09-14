package com.kingagroot.kingdraw.ui;

import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import androidx.viewpager.widget.ViewPager$OnPageChangeListener;
import androidx.viewpager.widget.ViewPager$PageTransformer;
import android.view.View$OnClickListener;
import android.net.Uri;
import com.kingagroot.kingdraw.config.ShareData;
import com.goodsrc.library.utils.AppUtil;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Intent;
import com.kingagroot.kingdraw.NewMainActivity;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import android.widget.LinearLayout;
import android.widget.Button;
import com.goodsrc.ui.library.BaseActivity;

public class GuideActivity extends BaseActivity
{
    static final int NUM_PAGES = 5;
    private Button btnDone;
    private LinearLayout circles;
    boolean isOpaque;
    NotchContext notchContext;
    private ViewPager pager;
    PagerAdapter pagerAdapter;
    private TextView tvSkip;
    
    public GuideActivity() {
        this.isOpaque = true;
    }
    
    private void buildCircles() {
        this.circles = (LinearLayout)this.findViewById(2131296525);
        final int n = (int)(this.getResources().getDisplayMetrics().density * 5.0f + 0.5f);
        for (int i = 0; i < 4; ++i) {
            final ImageView imageView = new ImageView((Context)this);
            imageView.setImageResource(2131231044);
            imageView.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(n, 0, n, 0);
            this.circles.addView((View)imageView);
        }
        this.setIndicator(0);
    }
    
    private void endTutorial() {
        final Intent intent = this.getIntent();
        final Uri data = intent.getData();
        if (data != null) {
            final Intent intent2 = new Intent((Context)this, (Class)NewMainActivity.class);
            intent2.setAction(intent.getAction());
            intent2.setData(data);
            this.startActivity(intent2);
            this.finish();
        }
        else {
            this.startActivity(new Intent((Context)this, (Class)NewMainActivity.class));
            this.finish();
        }
        ShareData.saveLastBootVersion(AppUtil.getVersionCode((Context)MApplication.getInstance()));
        this.overridePendingTransition(2130771968, 2130771969);
    }
    
    private void init() {
        this.pager = (ViewPager)this.findViewById(2131297168);
        this.tvSkip = (TextView)this.findViewById(2131297659);
        this.circles = (LinearLayout)this.findViewById(2131296525);
        this.btnDone = (Button)this.findViewById(2131296423);
        this.tvSkip.setOnClickListener((View$OnClickListener)new _$$Lambda$GuideActivity$HVCJ2rsmGBOfMN2BCSFTqXTtiZ4(this));
        this.btnDone.setOnClickListener((View$OnClickListener)new _$$Lambda$GuideActivity$FxO_GcpzvImTyqm2tbc80s1GzmA(this));
        final GuideActivity.GuideActivity$ScreenSlidePagerAdapter guideActivity$ScreenSlidePagerAdapter = new GuideActivity.GuideActivity$ScreenSlidePagerAdapter(this.getSupportFragmentManager());
        this.pagerAdapter = (PagerAdapter)guideActivity$ScreenSlidePagerAdapter;
        this.pager.setAdapter((PagerAdapter)guideActivity$ScreenSlidePagerAdapter);
        this.pager.setPageTransformer(true, (ViewPager$PageTransformer)new GuideActivity.GuideActivity$CrossfadePageTransformer());
        this.pager.addOnPageChangeListener((ViewPager$OnPageChangeListener)new GuideActivity$1(this));
        this.buildCircles();
    }
    
    private void setIndicator(final int n) {
        if (n < 5) {
            for (int i = 0; i < 4; ++i) {
                final ImageView imageView = (ImageView)this.circles.getChildAt(i);
                if (i == n) {
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    imageView.setColorFilter(17170445);
                                }
                                else {
                                    imageView.setColorFilter(Color.parseColor("#FF00BF53"));
                                }
                            }
                            else {
                                imageView.setColorFilter(Color.parseColor("#FFFFBB00"));
                            }
                        }
                        else {
                            imageView.setColorFilter(Color.parseColor("#FF6BD2FF"));
                        }
                    }
                    else {
                        imageView.setColorFilter(Color.parseColor("#FFFF6769"));
                    }
                }
                else {
                    imageView.setColorFilter(17170445);
                }
            }
        }
    }
    
    public void onBackPressed() {
        if (this.pager.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            final ViewPager pager = this.pager;
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492911);
        (this.notchContext = new NotchContext((Activity)this)).checkNotchInScreen((NotchCallBack)new _$$Lambda$GuideActivity$hRJHWbuBj1SZ7Iag6_VAgVFIVlo(this));
        this.init();
    }
    
    protected void onDestroy() {
        super.onDestroy();
        final ViewPager pager = this.pager;
        if (pager != null) {
            pager.clearOnPageChangeListeners();
        }
    }
}
