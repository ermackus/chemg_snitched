package com.kingagroot.kingdraw.widget;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.goodsrc.library.utils.StringUtils;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.synchornize.SynchronizeActivity;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.FrameLayout;

public class GTabViewLocal extends GTabView
{
    private FrameLayout frameIcon;
    private ImageView imgBg;
    private ImageView imgUp;
    ObjectAnimator transAnim;
    private TextView tvTab;
    private final ViewPager viewPager;
    
    public GTabViewLocal(final Context context, final ViewPager viewPager) {
        super(context);
        final View inflate = View.inflate(context, 2131493088, (ViewGroup)this);
        this.viewPager = viewPager;
        this.initView(inflate);
    }
    
    private void initView(final View view) {
        this.imgUp = (ImageView)view.findViewById(2131296882);
        this.tvTab = (TextView)view.findViewById(2131297664);
        this.frameIcon = (FrameLayout)this.findViewById(2131296753);
        this.imgBg = (ImageView)this.findViewById(2131296872);
        this.frameIcon.setOnClickListener((View$OnClickListener)new _$$Lambda$GTabViewLocal$fq21_4ddZRjoI7E_Yhvz64q6xFM(this));
    }
    
    public ImageView getImageView() {
        return this.imgBg;
    }
    
    public TextView getTabText() {
        return this.tvTab;
    }
    
    public void setShowIcon(final boolean b) {
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        this.frameIcon.setVisibility(visibility);
    }
    
    public void setTabText(final int n) {
        this.tvTab.setText((CharSequence)StringUtils.format(this.getContext().getString(2131820988), new Object[] { n }));
    }
    
    public void startAnim() {
        final ObjectAnimator transAnim = this.transAnim;
        if (transAnim == null || !transAnim.isStarted()) {
            final int height = this.imgUp.getHeight();
            (this.transAnim = ObjectAnimator.ofFloat((Object)this.imgUp, "translationY", new float[] { (float)height, (float)(height - (this.imgBg.getHeight() + this.imgUp.getHeight())) })).setDuration(800L);
            this.transAnim.setInterpolator((TimeInterpolator)new DecelerateInterpolator());
            this.transAnim.setRepeatMode(1);
            this.transAnim.setRepeatCount(-1);
            this.transAnim.start();
        }
    }
    
    public void stopAnim() {
        final ObjectAnimator transAnim = this.transAnim;
        if (transAnim != null) {
            transAnim.cancel();
        }
        this.transAnim = null;
        this.imgUp.setTranslationY(0.0f);
    }
}
