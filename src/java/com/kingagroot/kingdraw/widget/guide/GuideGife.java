package com.kingagroot.kingdraw.widget.guide;

import androidx.viewpager.widget.ViewPager$OnPageChangeListener;
import android.view.View$OnClickListener;
import androidx.viewpager.widget.PagerAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.view.View;
import java.util.List;
import android.content.Context;
import android.widget.LinearLayout;

public class GuideGife extends LinearLayout implements GuideBaseView
{
    private final Context context;
    private int current;
    private GuideBaseView guideBaseView;
    private final int[] imas;
    private final List<View> listViews;
    private final RelativeLayout rlSuspension;
    private final TextView tvNumBoot;
    private final TextView tvSkip;
    private final View view;
    private final ViewPager vp;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public GuideGife(final Context context) {
        this(context, null);
    }
    
    public GuideGife(final Context context, final AttributeSet set) {
        super(context, set);
        this.listViews = (List<View>)new ArrayList();
        this.imas = new int[] { 2131231060, 2131231061, 2131231059 };
        this.context = context;
        final View inflate = View.inflate(context, 2131493070, (ViewGroup)this);
        this.view = inflate;
        this.vp = (ViewPager)inflate.findViewById(2131297749);
        this.tvNumBoot = (TextView)this.view.findViewById(2131297484);
        this.rlSuspension = (RelativeLayout)this.view.findViewById(2131297321);
        this.tvSkip = (TextView)this.view.findViewById(2131297540);
        this.initLisenner();
        this.initData();
    }
    
    private void initData() {
        final String string = this.context.getResources().getString(2131820915);
        int i = 0;
        final String string2 = this.context.getResources().getString(2131820916);
        final String string3 = this.context.getResources().getString(2131820914);
        while (i < this.imas.length) {
            final View inflate = LayoutInflater.from(this.context).inflate(2131492957, (ViewGroup)null);
            final TextView textView = (TextView)inflate.findViewById(2131297752);
            textView.setText((CharSequence)Html.fromHtml((new String[] { string, string2, string3 })[i]));
            textView.setMovementMethod((MovementMethod)new ScrollingMovementMethod());
            Glide.with(inflate).asGif().load(Integer.valueOf(this.imas[i])).into((ImageView)inflate.findViewById(2131297750));
            this.listViews.add((Object)inflate);
            ++i;
        }
        this.vp.setAdapter((PagerAdapter)new VpAdapter());
    }
    
    private void initLisenner() {
        final TextView tvNumBoot = this.tvNumBoot;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.current + 1);
        sb.append("/");
        sb.append(this.imas.length);
        tvNumBoot.setText((CharSequence)sb.toString());
        this.rlSuspension.setOnClickListener((View$OnClickListener)new GuideGife$1(this));
        this.tvSkip.setOnClickListener((View$OnClickListener)new GuideGife$2(this));
        this.vp.setOnPageChangeListener((ViewPager$OnPageChangeListener)new ViewPager$OnPageChangeListener(this) {
            final GuideGife this$0;
            
            public void onPageScrollStateChanged(final int n) {
            }
            
            public void onPageScrolled(final int n, final float n2, final int n3) {
            }
            
            public void onPageSelected(final int n) {
                this.this$0.current = n;
                final TextView access$300 = this.this$0.tvNumBoot;
                final StringBuilder sb = new StringBuilder();
                sb.append(this.this$0.current + 1);
                sb.append("/");
                sb.append(this.this$0.imas.length);
                access$300.setText((CharSequence)sb.toString());
            }
        });
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
    
    class VpAdapter extends PagerAdapter
    {
        final GuideGife this$0;
        
        VpAdapter(final GuideGife this$0) {
            this.this$0 = this$0;
        }
        
        public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
            viewGroup.removeView((View)o);
        }
        
        public int getCount() {
            if (this.this$0.listViews != null && this.this$0.listViews.size() > 0) {
                return this.this$0.listViews.size();
            }
            return 0;
        }
        
        public int getItemPosition(final Object o) {
            return -2;
        }
        
        public Object instantiateItem(final ViewGroup viewGroup, final int n) {
            viewGroup.addView((View)this.this$0.listViews.get(n));
            return this.this$0.listViews.get(n);
        }
        
        public boolean isViewFromObject(final View view, final Object o) {
            return view == o;
        }
    }
}
