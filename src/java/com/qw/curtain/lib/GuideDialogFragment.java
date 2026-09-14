package com.qw.curtain.lib;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Context;
import androidx.appcompat.app.AlertDialog$Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.Window;
import android.util.SparseArray;
import android.app.Dialog;
import android.widget.FrameLayout;
import androidx.fragment.app.DialogFragment;

public class GuideDialogFragment extends DialogFragment implements IGuide
{
    private static final int GUIDE_ID = 3;
    private static final int MAX_CHILD_COUNT = 2;
    private FrameLayout contentView;
    private Dialog dialog;
    private GuideView guideView;
    private Curtain$Param param;
    private int topLayoutRes;
    
    public GuideDialogFragment() {
        this.topLayoutRes = 0;
    }
    
    public static GuideDialogFragment newInstance(final Curtain$Param param) {
        final GuideDialogFragment guideDialogFragment = new GuideDialogFragment();
        guideDialogFragment.setParam(param);
        guideDialogFragment.setCancelable(param.cancelBackPressed);
        guideDialogFragment.setTopViewRes(param.topLayoutRes);
        final GuideView guideView = new GuideView(param.activity);
        guideView.setCurtainColor(param.curtainColor);
        final SparseArray hollows = param.hollows;
        final HollowInfo[] hollowInfo = new HollowInfo[hollows.size()];
        for (int i = 0; i < hollows.size(); ++i) {
            hollowInfo[i] = (HollowInfo)hollows.valueAt(i);
        }
        guideView.setHollowInfo(hollowInfo);
        guideDialogFragment.setGuideView(guideView);
        return guideDialogFragment;
    }
    
    private void setAnimation(final Dialog dialog) {
        if (dialog != null) {
            if (dialog.getWindow() != null) {
                if (this.param.animationStyle == 0) {
                    return;
                }
                final Window window = dialog.getWindow();
                int windowAnimations;
                if (this.param.animationStyle == -1) {
                    windowAnimations = R$style.dialogWindowAnim;
                }
                else {
                    windowAnimations = this.param.animationStyle;
                }
                window.setWindowAnimations(windowAnimations);
            }
        }
    }
    
    private void updateTopView() {
        if (this.contentView.getChildCount() == 2) {
            this.contentView.removeViewAt(1);
        }
        LayoutInflater.from(this.contentView.getContext()).inflate(this.topLayoutRes, (ViewGroup)this.contentView, true);
        final SparseArray topViewOnClickListeners = this.param.topViewOnClickListeners;
        for (int size = topViewOnClickListeners.size(), i = 0; i < size; ++i) {
            final int key = topViewOnClickListeners.keyAt(i);
            final OnViewInTopClickListener onViewInTopClickListener = (OnViewInTopClickListener)topViewOnClickListeners.valueAt(i);
            final View viewById = this.contentView.findViewById(key);
            if (viewById == null) {
                throw new NullPointerException("the target view was not find in the top view, check your setTopView layout res first");
            }
            viewById.setOnClickListener((View$OnClickListener)new GuideDialogFragment$1(this, onViewInTopClickListener));
        }
    }
    
    public void dismissGuide() {
        this.dismissAllowingStateLoss();
    }
    
    public <T extends View> T findViewByIdInTopView(final int n) {
        final FrameLayout contentView = this.contentView;
        if (contentView == null) {
            return null;
        }
        return (T)contentView.findViewById(n);
    }
    
    public void onActivityCreated(final Bundle bundle) {
        try {
            super.onActivityCreated(bundle);
            if (this.param.callBack != null) {
                this.param.callBack.onShow((IGuide)this);
            }
        }
        catch (final Exception ex) {}
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.dialog == null) {
            this.setAnimation(this.dialog = (Dialog)new AlertDialog$Builder((Context)this.requireActivity(), R$style.TransparentDialog).setView((View)this.contentView).create());
        }
        return this.dialog;
    }
    
    public void onDestroyView() {
        super.onDestroyView();
        if (this.dialog != null) {
            this.dialog = null;
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.param.callBack != null) {
            this.param.callBack.onDismiss((IGuide)this);
        }
    }
    
    public void setGuideView(final GuideView guideView) {
        this.guideView = guideView;
    }
    
    public void setParam(final Curtain$Param param) {
        this.param = param;
    }
    
    public void setTopViewRes(final int topLayoutRes) {
        this.topLayoutRes = topLayoutRes;
    }
    
    public void show() {
        this.guideView.setId(3);
        (this.contentView = new FrameLayout(this.guideView.getContext())).addView((View)this.guideView);
        if (this.topLayoutRes != 0) {
            this.updateTopView();
        }
        this.show(this.param.fragmentManager, "GuideDialogFragment");
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        try {
            super.show(fragmentManager, s);
        }
        catch (final Exception ex) {
            fragmentManager.beginTransaction().add((Fragment)this, s).commitAllowingStateLoss();
        }
    }
    
    void updateContent() {
        this.contentView.removeAllViews();
        this.contentView.addView((View)this.guideView);
        this.updateTopView();
    }
    
    public void updateHollows(final HollowInfo... hollowInfo) {
        final GuideView guideView = (GuideView)this.contentView.findViewById(3);
        if (guideView != null) {
            guideView.setHollowInfo(hollowInfo);
        }
    }
    
    public void updateTopView(final int topViewRes) {
        if (this.contentView != null) {
            if (this.getActivity() != null) {
                this.setTopViewRes(topViewRes);
                this.updateTopView();
            }
        }
    }
}
