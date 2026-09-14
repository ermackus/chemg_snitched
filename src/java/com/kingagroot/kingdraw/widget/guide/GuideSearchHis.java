package com.kingagroot.kingdraw.widget.guide;

import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;

public class GuideSearchHis extends LinearLayout implements GuideBaseView
{
    GuideBaseView guideBaseView;
    private final RelativeLayout rlSearchPageOne;
    private final RelativeLayout rlSearchPageTwo;
    private final View view;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public GuideSearchHis(final Context context) {
        this(context, null);
    }
    
    public GuideSearchHis(final Context context, final AttributeSet set) {
        super(context, set);
        final View inflate = View.inflate(context, 2131493071, (ViewGroup)this);
        this.view = inflate;
        this.rlSearchPageOne = (RelativeLayout)inflate.findViewById(2131297311);
        this.rlSearchPageTwo = (RelativeLayout)this.view.findViewById(2131297312);
        this.rlSearchPageOne.setVisibility(0);
        this.rlSearchPageTwo.setVisibility(8);
        this.rlSearchPageOne.setOnClickListener((View$OnClickListener)new GuideSearchHis$1(this));
        this.rlSearchPageTwo.setOnClickListener((View$OnClickListener)new GuideSearchHis$2(this));
    }
    
    public void addObserver(final GuideBaseView guideBaseView) {
        this.guideBaseView = guideBaseView;
    }
    
    public void dismiss() {
        this.setVisibility(8);
    }
    
    public void next() {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.next();
        }
    }
    
    public void setBackground(final int background) {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.setBackground(background);
        }
    }
    
    public void setBackgroundAlpha(final float backgroundAlpha) {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.setBackgroundAlpha(backgroundAlpha);
        }
    }
    
    public void show() {
        this.setVisibility(0);
    }
}
