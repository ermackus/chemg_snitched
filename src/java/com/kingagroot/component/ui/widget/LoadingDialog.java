package com.kingagroot.component.ui.widget;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.kingagroot.component.ui.R$id;
import com.kingagroot.component.ui.R$layout;
import java.util.Objects;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.res.Configuration;
import android.app.Dialog;
import com.goodsrc.library.utils.SystemUtils;
import android.content.Context;
import com.goodsrc.library.utils.DisplayUtil;
import com.kingagroot.component.ui.R$string;
import android.widget.TextView;
import android.os.Handler;
import com.goodsrc.ui.library.BaseActivity;
import androidx.fragment.app.DialogFragment;

public class LoadingDialog extends DialogFragment
{
    private final int WIDTH_MAX;
    private BaseActivity activity;
    private String msg;
    private boolean onTouchOutside;
    private Handler showHandler;
    private LoadingDialog.LoadingDialog$ShowRunable showRunable;
    private TextView tvMsg;
    
    public LoadingDialog() {
        this.onTouchOutside = true;
        this.msg = "";
        this.WIDTH_MAX = 600;
    }
    
    public LoadingDialog(final BaseActivity activity) {
        this.onTouchOutside = true;
        this.msg = "";
        this.activity = activity;
        this.msg = activity.getString(R$string.wait_hint);
        this.WIDTH_MAX = DisplayUtil.dip2px((Context)activity, 300.0f);
    }
    
    private void onOrientationChange() {
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final int screenWidth = SystemUtils.getScreenWidth(this.getContext());
            Label_0070: {
                double n;
                double n2;
                if (SystemUtils.getScreenOrientation(this.getContext()) == 2) {
                    n = screenWidth;
                    n2 = 0.4;
                }
                else {
                    if (SystemUtils.getScreenOrientation(this.getContext()) != 1) {
                        break Label_0070;
                    }
                    n = screenWidth;
                    n2 = 0.6;
                }
                final int n3 = (int)(n * n2);
            }
            final int width_MAX = this.WIDTH_MAX;
            dialog.getWindow().setLayout(this.WIDTH_MAX, -2);
        }
    }
    
    private void removeShowRunableCallback() {
        final LoadingDialog.LoadingDialog$ShowRunable showRunable = this.showRunable;
        if (showRunable != null) {
            final Handler showHandler = this.showHandler;
            if (showHandler != null) {
                showHandler.removeCallbacks((Runnable)showRunable);
            }
        }
    }
    
    public void dismiss() {
        this.removeShowRunableCallback();
        this.setShowsDialog(false);
        final BaseActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing() && this.isShowing()) {
            super.dismissAllowingStateLoss();
        }
    }
    
    public boolean isShowing() {
        final Dialog dialog = this.getDialog();
        return dialog != null && dialog.isShowing();
    }
    
    public void onAttach(final Context context) {
        super.onAttach(context);
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.onOrientationChange();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        ((Dialog)Objects.requireNonNull((Object)this.getDialog())).requestWindowFeature(1);
        this.getDialog().setCanceledOnTouchOutside(this.onTouchOutside);
        final View inflate = layoutInflater.inflate(R$layout.component_dialog_loading, viewGroup);
        (this.tvMsg = (TextView)inflate.findViewById(R$id.tv_msg)).setText((CharSequence)this.msg);
        return inflate;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
    
    public void onStart() {
        this.onOrientationChange();
        super.onStart();
    }
    
    public LoadingDialog setOnTouchOutside(final boolean b) {
        this.onTouchOutside = b;
        if (this.getDialog() != null) {
            this.getDialog().setCanceledOnTouchOutside(b);
        }
        return this;
    }
    
    public LoadingDialog setTextMessage(final String s) {
        this.msg = s;
        final TextView tvMsg = this.tvMsg;
        if (tvMsg != null) {
            tvMsg.setText((CharSequence)s);
        }
        return this;
    }
    
    public void show() {
        final BaseActivity activity = this.activity;
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing()) {
            this.setShowsDialog(true);
            this.showNow(this.activity.getSupportFragmentManager(), "");
        }
    }
    
    public void show(final long n) {
        if (this.showRunable == null) {
            this.showRunable = new LoadingDialog.LoadingDialog$ShowRunable(this);
        }
        if (this.showHandler == null) {
            this.showHandler = new Handler();
        }
        this.showHandler.postDelayed((Runnable)this.showRunable, n);
    }
    
    public void showNow(final FragmentManager fragmentManager, final String s) {
        if (fragmentManager.isDestroyed()) {
            return;
        }
        final FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        final Fragment fragmentByTag = fragmentManager.findFragmentByTag(s);
        if (fragmentByTag != null) {
            beginTransaction.remove(fragmentByTag);
            beginTransaction.commitNowAllowingStateLoss();
        }
        beginTransaction.add((Fragment)this, s);
        beginTransaction.commitNowAllowingStateLoss();
    }
}
