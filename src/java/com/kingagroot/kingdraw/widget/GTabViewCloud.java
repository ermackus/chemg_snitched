package com.kingagroot.kingdraw.widget;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.goodsrc.library.utils.StringUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.FrameLayout;

public class GTabViewCloud extends GTabView
{
    private FrameLayout frameIcon;
    private ImageView imgBg;
    private ImageView imgDown;
    ObjectAnimator transAnim;
    private TextView tvTab;
    private final ViewPager viewPager;
    
    public GTabViewCloud(final Context context, final ViewPager viewPager) {
        super(context);
        final View inflate = View.inflate(context, 2131493087, (ViewGroup)this);
        this.viewPager = viewPager;
        this.initView(inflate);
    }
    
    private void initView(final View view) {
        this.tvTab = (TextView)view.findViewById(2131297664);
        this.imgDown = (ImageView)this.findViewById(2131296874);
        this.frameIcon = (FrameLayout)this.findViewById(2131296753);
        this.imgBg = (ImageView)this.findViewById(2131296872);
        this.frameIcon.setOnClickListener((View$OnClickListener)new GTabViewCloud$1(this));
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
        this.tvTab.setText((CharSequence)StringUtils.format(this.getContext().getString(2131820701), new Object[] { n }));
    }
    
    public void startAnim() {
        final int n = -this.imgDown.getHeight();
        final int height = this.imgBg.getHeight();
        final int height2 = this.imgDown.getHeight();
        final ObjectAnimator transAnim = this.transAnim;
        if (transAnim == null || !transAnim.isStarted()) {
            (this.transAnim = ObjectAnimator.ofFloat((Object)this.imgDown, "translationY", new float[] { (float)n, (float)(height + height2 + n) })).setDuration(800L);
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
        this.imgDown.setTranslationY(0.0f);
    }
}
