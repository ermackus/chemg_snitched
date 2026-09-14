package com.qw.curtain.lib;

import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.graphics.Rect;
import com.qw.curtain.lib.shape.Shape;
import com.qw.curtain.lib.debug.CurtainDebug;
import android.view.View;
import android.util.SparseArray;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;

public class Curtain
{
    Param buildParams;
    
    public Curtain(final Fragment fragment) {
        this(fragment.requireActivity());
        this.buildParams.fragmentManager = fragment.getChildFragmentManager();
    }
    
    public Curtain(final FragmentActivity activity) {
        final Param buildParams = new Param();
        this.buildParams = buildParams;
        buildParams.activity = (Context)activity;
        this.buildParams.hollows = (SparseArray<HollowInfo>)new SparseArray();
        this.buildParams.fragmentManager = activity.getSupportFragmentManager();
    }
    
    private HollowInfo getHollowInfo(final View targetView) {
        final SparseArray<HollowInfo> hollows = this.buildParams.hollows;
        HollowInfo hollowInfo;
        if ((hollowInfo = (HollowInfo)hollows.get(targetView.hashCode())) == null) {
            hollowInfo = new HollowInfo(targetView);
            hollowInfo.targetView = targetView;
            hollows.append(targetView.hashCode(), (Object)hollowInfo);
        }
        return hollowInfo;
    }
    
    public Curtain addOnTopViewClickListener(final int n, final OnViewInTopClickListener<IGuide> onViewInTopClickListener) {
        this.buildParams.topViewOnClickListeners.append(n, (Object)onViewInTopClickListener);
        return this;
    }
    
    public Curtain setAnimationStyle(final int animationStyle) {
        this.buildParams.animationStyle = animationStyle;
        return this;
    }
    
    public Curtain setCallBack(final CallBack callBack) {
        this.buildParams.callBack = callBack;
        return this;
    }
    
    public Curtain setCancelBackPressed(final boolean cancelBackPressed) {
        this.buildParams.cancelBackPressed = cancelBackPressed;
        return this;
    }
    
    public Curtain setCurtainColor(final int curtainColor) {
        this.buildParams.curtainColor = curtainColor;
        return this;
    }
    
    public Curtain setCurtainColorRes(final int curtainColor) {
        this.buildParams.curtainColor = curtainColor;
        return this;
    }
    
    public Curtain setNoCurtainAnimation(final boolean b) {
        if (b) {
            this.setAnimationStyle(0);
        }
        return this;
    }
    
    public Curtain setTopView(final int topLayoutRes) {
        this.buildParams.topLayoutRes = topLayoutRes;
        return this;
    }
    
    public void show() {
        final SparseArray<HollowInfo> hollows = this.buildParams.hollows;
        if (hollows.size() == 0) {
            CurtainDebug.w("Curtain", "with out any views");
            return;
        }
        final View targetView = ((HollowInfo)hollows.valueAt(0)).targetView;
        if (targetView.getWidth() == 0) {
            targetView.post((Runnable)new Runnable(this) {
                final Curtain this$0;
                
                public void run() {
                    this.this$0.show();
                }
            });
            return;
        }
        GuideDialogFragment.newInstance(this.buildParams).show();
    }
    
    public Curtain with(final View view) {
        return this.with(view, true);
    }
    
    public Curtain with(final View view, final boolean autoAdaptViewBackGround) {
        this.getHollowInfo(view).setAutoAdaptViewBackGround(autoAdaptViewBackGround);
        return this;
    }
    
    public Curtain withOffset(final View view, final int n, final int n2) {
        this.getHollowInfo(view).setOffset(n, n2);
        return this;
    }
    
    public Curtain withPadding(final View view, final int n) {
        return this.withPadding(view, Padding.all(n));
    }
    
    public Curtain withPadding(final View view, final Padding padding) {
        this.getHollowInfo(view).padding = padding;
        return this;
    }
    
    public Curtain withShape(final View view, final Shape shape) {
        this.getHollowInfo(view).setShape(shape);
        return this;
    }
    
    public Curtain withSize(final View view, final int n, final int n2) {
        this.getHollowInfo(view).targetBound = new Rect(0, 0, n, n2);
        return this;
    }
    
    public interface CallBack
    {
        void onDismiss(final IGuide p0);
        
        void onShow(final IGuide p0);
    }
    
    public static class Param
    {
        Context activity;
        int animationStyle;
        CallBack callBack;
        boolean cancelBackPressed;
        int curtainColor;
        FragmentManager fragmentManager;
        SparseArray<HollowInfo> hollows;
        int topLayoutRes;
        SparseArray<OnViewInTopClickListener> topViewOnClickListeners;
        
        public Param() {
            this.cancelBackPressed = true;
            this.curtainColor = -1442840576;
            this.animationStyle = -1;
            this.topViewOnClickListeners = (SparseArray<OnViewInTopClickListener>)new SparseArray();
        }
    }
}
