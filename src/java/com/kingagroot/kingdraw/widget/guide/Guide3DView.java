package com.kingagroot.kingdraw.widget.guide;

import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.widget.LinearLayout;

public class Guide3DView extends LinearLayout implements GuideBaseView
{
    View contentView;
    GuideBaseView guideBaseView;
    TextView tvGot;
    
    public Guide3DView(final Context context) {
        this(context, null);
    }
    
    public Guide3DView(final Context context, final AttributeSet set) {
        super(context, set);
        final View inflate = View.inflate(context, 2131493065, (ViewGroup)this);
        this.contentView = inflate;
        (this.tvGot = (TextView)inflate.findViewById(2131297606)).setOnClickListener((View$OnClickListener)new Guide3DView$1(this));
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
