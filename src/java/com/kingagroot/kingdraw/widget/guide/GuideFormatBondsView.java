package com.kingagroot.kingdraw.widget.guide;

import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageButton;
import android.view.View;
import android.widget.FrameLayout;

public class GuideFormatBondsView extends FrameLayout implements GuideBaseView
{
    private final View contentView;
    private GuideBaseView guideBaseView;
    private final ImageButton imgbtnClose;
    
    public GuideFormatBondsView(final Context context) {
        this(context, null);
    }
    
    public GuideFormatBondsView(final Context context, final AttributeSet set) {
        super(context, set);
        final View inflate = View.inflate(context, 2131493067, (ViewGroup)this);
        this.contentView = inflate;
        (this.imgbtnClose = (ImageButton)inflate.findViewById(2131296887)).setOnClickListener((View$OnClickListener)new GuideFormatBondsView$1(this));
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
