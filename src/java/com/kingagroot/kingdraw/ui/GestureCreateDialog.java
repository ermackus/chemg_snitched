package com.kingagroot.kingdraw.ui;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.app.Dialog;
import java.util.Objects;
import android.view.Window;
import android.util.DisplayMetrics;
import android.content.res.Configuration;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.view.View;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import android.widget.ImageView;
import androidx.fragment.app.DialogFragment;

public class GestureCreateDialog extends DialogFragment
{
    private ImageView ivGesture;
    private ImageView ivValue;
    private GestureGroupModel model;
    private GestureCreateDialog.GestureCreateDialog$OnGestureListener onGestureListener;
    
    private void initView(final View view) {
        this.ivValue = (ImageView)view.findViewById(2131296944);
        this.ivGesture = (ImageView)view.findViewById(2131296920);
        final TextView textView = (TextView)view.findViewById(2131297558);
        final TextView textView2 = (TextView)view.findViewById(2131297557);
        textView.setOnClickListener((View$OnClickListener)new _$$Lambda$GestureCreateDialog$1Z8ZoupGDLtMyLFP0oPpE3pu6vM(this));
        textView2.setOnClickListener((View$OnClickListener)new _$$Lambda$GestureCreateDialog$OPNXXuimE9W5rUOp7tzUnTWl_AM(this));
    }
    
    private void setData() {
        this.ivGesture.setImageResource(GBitmapUtils.getResId(this.getContext(), this.model.getGestureRes()));
        this.ivValue.setImageResource(GBitmapUtils.getResId(this.getContext(), this.model.getBondRes()));
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (configuration.orientation == 2) {
                ((Window)Objects.requireNonNull((Object)dialog.getWindow())).setLayout((int)(displayMetrics.widthPixels * 0.6), -2);
            }
            else {
                ((Window)Objects.requireNonNull((Object)dialog.getWindow())).setLayout((int)(displayMetrics.widthPixels * 0.9), -2);
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setCancelable(false);
        this.setStyle(2, 0);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493022, viewGroup, false);
        this.initView(inflate);
        this.setData();
        return inflate;
    }
    
    public void onStart() {
        super.onStart();
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (this.getResources().getConfiguration().orientation == 2) {
                ((Window)Objects.requireNonNull((Object)dialog.getWindow())).setLayout((int)(displayMetrics.widthPixels * 0.6), -2);
            }
            else {
                ((Window)Objects.requireNonNull((Object)dialog.getWindow())).setLayout((int)(displayMetrics.widthPixels * 0.9), -2);
            }
        }
    }
    
    public void setDataModel(final GestureGroupModel model) {
        this.model = model;
    }
    
    public void setOnGestureListener(final GestureCreateDialog.GestureCreateDialog$OnGestureListener onGestureListener) {
        this.onGestureListener = onGestureListener;
    }
}
