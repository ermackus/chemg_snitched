package com.kingagroot.kingdraw.widget.guide;

import java.util.Iterator;
import android.view.WindowManager$LayoutParams;
import android.view.View$OnKeyListener;
import com.goodsrc.ui.library.widget.notch.NotchCallBack;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import com.goodsrc.ui.library.widget.notch.NotchContext;
import java.util.List;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class GuideContentPopupWindow extends PopupWindow implements GuideBaseView
{
    private View content;
    private final Context context;
    private FrameLayout frameContent;
    private GuideBaseView guideBaseView;
    private final List<GuideBaseView> guideChilds;
    private int index;
    NotchContext notchContext;
    
    public GuideContentPopupWindow(final Context context) {
        this.guideChilds = (List<GuideBaseView>)new ArrayList();
        this.index = 0;
        this.context = context;
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493066, (ViewGroup)null);
        this.initView(inflate);
        inflate.setFocusable(true);
        this.setContentView(inflate);
        this.setWidth(-1);
        this.setHeight(-1);
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        (this.notchContext = new NotchContext((Activity)context)).checkNotchInScreen((NotchCallBack)new NotchCallBack(this) {
            final GuideContentPopupWindow this$0;
            
            public void onResult(final boolean b) {
                if (b) {
                    this.this$0.setClippingEnabled(false);
                }
            }
        });
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        inflate.setOnKeyListener((View$OnKeyListener)new GuideContentPopupWindow$2(this));
    }
    
    private void initView(final View view) {
        this.content = view.findViewById(2131296541);
        this.frameContent = (FrameLayout)view.findViewById(2131296752);
    }
    
    public GuideContentPopupWindow addGuideView(final GuideBaseView guideBaseView) {
        if (guideBaseView != null) {
            guideBaseView.addObserver((GuideBaseView)this);
            this.guideChilds.add((Object)guideBaseView);
        }
        return this;
    }
    
    public void addObserver(final GuideBaseView guideBaseView) {
    }
    
    public void next() {
        final GuideBaseView guideBaseView = this.guideBaseView;
        if (guideBaseView != null) {
            guideBaseView.dismiss();
        }
        final List<GuideBaseView> guideChilds = this.guideChilds;
        if (guideChilds != null && this.index < guideChilds.size()) {
            this.frameContent.removeAllViews();
            (this.guideBaseView = (GuideBaseView)this.guideChilds.get(this.index)).show();
            this.frameContent.addView((View)this.guideBaseView);
            this.frameContent.invalidate();
            ++this.index;
            return;
        }
        this.dismiss();
    }
    
    public void setBackground(final int backgroundColor) {
        this.content.setBackgroundColor(backgroundColor);
    }
    
    public void setBackgroundAlpha(final float alpha) {
        final Context context = this.context;
        if (context instanceof Activity) {
            final Activity activity = (Activity)context;
            final WindowManager$LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = alpha;
            activity.getWindow().addFlags(2);
            activity.getWindow().setAttributes(attributes);
        }
    }
    
    public void setGuideViews(final List<GuideBaseView> list) {
        if (list != null) {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                this.addGuideView((GuideBaseView)iterator.next());
            }
        }
    }
    
    public void show() {
    }
}
