package com.luck.picture.lib.dialog;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.widget.TextView;
import android.content.DialogInterface;
import com.luck.picture.lib.R$layout;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.luck.picture.lib.R$id;
import android.view.View;
import android.view.Window;
import android.app.Dialog;
import com.luck.picture.lib.R$style;
import com.luck.picture.lib.utils.DensityUtil;
import com.luck.picture.lib.interfaces.OnItemClickListener;
import android.view.View$OnClickListener;
import androidx.fragment.app.DialogFragment;

public class PhotoItemSelectedDialog extends DialogFragment implements View$OnClickListener
{
    public static final int IMAGE_CAMERA = 0;
    public static final int VIDEO_CAMERA = 1;
    private boolean isCancel;
    private PhotoItemSelectedDialog.PhotoItemSelectedDialog$OnDismissListener onDismissListener;
    private OnItemClickListener onItemClickListener;
    
    public PhotoItemSelectedDialog() {
        this.isCancel = true;
    }
    
    private void initDialogStyle() {
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(DensityUtil.getRealScreenWidth(this.getContext()), -2);
                window.setGravity(80);
                window.setWindowAnimations(R$style.PictureThemeDialogFragmentAnim);
            }
        }
    }
    
    public static PhotoItemSelectedDialog newInstance() {
        return new PhotoItemSelectedDialog();
    }
    
    public void onClick(final View view) {
        final int id = view.getId();
        if (this.onItemClickListener != null) {
            if (id == R$id.ps_tv_photo) {
                this.onItemClickListener.onItemClick(view, 0);
                this.isCancel = false;
            }
            else if (id == R$id.ps_tv_video) {
                this.onItemClickListener.onItemClick(view, 1);
                this.isCancel = false;
            }
        }
        this.dismissAllowingStateLoss();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.getDialog() != null) {
            this.getDialog().requestWindowFeature(1);
            if (this.getDialog().getWindow() != null) {
                this.getDialog().getWindow().setBackgroundDrawableResource(17170445);
            }
        }
        return layoutInflater.inflate(R$layout.ps_dialog_camera_selected, viewGroup);
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        final PhotoItemSelectedDialog.PhotoItemSelectedDialog$OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this.isCancel, dialogInterface);
        }
    }
    
    public void onStart() {
        super.onStart();
        this.initDialogStyle();
    }
    
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        final TextView textView = (TextView)view.findViewById(R$id.ps_tv_photo);
        final TextView textView2 = (TextView)view.findViewById(R$id.ps_tv_video);
        final TextView textView3 = (TextView)view.findViewById(R$id.ps_tv_cancel);
        textView2.setOnClickListener((View$OnClickListener)this);
        textView.setOnClickListener((View$OnClickListener)this);
        textView3.setOnClickListener((View$OnClickListener)this);
    }
    
    public void setOnDismissListener(final PhotoItemSelectedDialog.PhotoItemSelectedDialog$OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
    
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        final FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment)this, s);
        beginTransaction.commitAllowingStateLoss();
    }
}
