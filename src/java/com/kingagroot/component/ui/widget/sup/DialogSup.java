package com.kingagroot.component.ui.widget.sup;

import com.kingagroot.component.ui.R$layout;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Dialog;
import com.goodsrc.library.utils.SystemUtils;
import java.util.Objects;
import android.content.Context;
import android.content.res.Configuration;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.text.TextUtils;
import com.kingagroot.component.ui.utils.base64imageloader.Base64BitmapLoader;
import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.widget.ImageView;
import com.kingagroot.component.ui.model.GSGroupModel;
import android.widget.TextView;
import android.view.View$OnClickListener;
import androidx.fragment.app.DialogFragment;

public class DialogSup extends DialogFragment implements View$OnClickListener
{
    private TextView btnCancelCommon;
    private TextView btnSetCommon;
    private TextView btnSupCancel;
    private TextView btnSupDelete;
    private TextView btnSupEdit;
    private final GSGroupModel groupModel;
    private ImageView imgSup;
    private DialogSup.DialogSup$OnCancelCommonListener onCancelCommonListener;
    private DialogSup.DialogSup$OnSetCommonListener onSetCommonListener;
    private DialogSup.DialogSup$OnSupCancelListener onSupCancelListener;
    private DialogSup.DialogSup$OnSupDeleteListener onSupDeleteListener;
    private DialogSup.DialogSup$OnSupEditListener onSupEditListener;
    private TextView tvDialogSupName;
    
    public DialogSup(final GSGroupModel groupModel) {
        this.groupModel = groupModel;
    }
    
    private void initView(final View view) {
        this.imgSup = (ImageView)view.findViewById(R$id.img_sup);
        this.tvDialogSupName = (TextView)view.findViewById(R$id.tv_dialog_sup_name);
        this.btnSetCommon = (TextView)view.findViewById(R$id.btn_set_common);
        this.btnCancelCommon = (TextView)view.findViewById(R$id.btn_cancel_common);
        this.btnSupEdit = (TextView)view.findViewById(R$id.btn_sup_edit);
        this.btnSupDelete = (TextView)view.findViewById(R$id.btn_sup_delete);
        this.btnSupCancel = (TextView)view.findViewById(R$id.btn_sup_cancel);
        this.btnSetCommon.setOnClickListener((View$OnClickListener)this);
        this.btnCancelCommon.setOnClickListener((View$OnClickListener)this);
        this.btnSupEdit.setOnClickListener((View$OnClickListener)this);
        this.btnSupDelete.setOnClickListener((View$OnClickListener)this);
        this.btnSupCancel.setOnClickListener((View$OnClickListener)this);
    }
    
    private void setData() {
        final Base64BitmapLoader instance = Base64BitmapLoader.getInstance();
        final String base64String = this.groupModel.getBase64String();
        final String name = this.groupModel.getName();
        final int isCollection = this.groupModel.getIsCollection();
        if (!TextUtils.isEmpty((CharSequence)base64String)) {
            instance.loadImage(name, base64String, this.imgSup);
        }
        if (TextUtils.isEmpty((CharSequence)this.groupModel.getNameHtml())) {
            if (!TextUtils.isEmpty((CharSequence)name)) {
                this.tvDialogSupName.setText((CharSequence)name);
            }
        }
        else {
            this.tvDialogSupName.setText((CharSequence)SpanUtil.htmlToSpan(this.groupModel.getNameHtml()));
        }
        if (isCollection == 1) {
            this.btnSetCommon.setVisibility(8);
            this.btnCancelCommon.setVisibility(0);
        }
        else {
            this.btnSetCommon.setVisibility(0);
            this.btnCancelCommon.setVisibility(8);
        }
    }
    
    public void onClick(final View view) {
        if (view == this.btnSetCommon) {
            this.onSetCommonListener.onSetCommonClick();
        }
        else if (view == this.btnSupEdit) {
            this.onSupEditListener.onSupEditClick();
        }
        else if (view == this.btnSupDelete) {
            this.onSupDeleteListener.onSupDeleteClick();
        }
        else if (view == this.btnSupCancel) {
            this.onSupCancelListener.onSupCancelClick();
        }
        else if (view == this.btnCancelCommon) {
            this.onCancelCommonListener.onCancelCommonClick();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final int screenWidth = SystemUtils.getScreenWidth((Context)Objects.requireNonNull((Object)this.getContext()));
            if (SystemUtils.getScreenOrientation(this.getContext()) == 2) {
                dialog.getWindow().setLayout((int)(screenWidth * 0.5), -2);
            }
            else if (SystemUtils.getScreenOrientation(this.getContext()) == 1) {
                dialog.getWindow().setLayout((int)(screenWidth * 0.8), -2);
            }
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        ((Dialog)Objects.requireNonNull((Object)this.getDialog())).requestWindowFeature(1);
        final View inflate = layoutInflater.inflate(R$layout.component_dialog_sup, viewGroup);
        this.initView(inflate);
        this.setData();
        return inflate;
    }
    
    public void onStart() {
        final Dialog dialog = this.getDialog();
        if (dialog != null) {
            final int screenWidth = SystemUtils.getScreenWidth((Context)Objects.requireNonNull((Object)this.getContext()));
            if (SystemUtils.getScreenOrientation(this.getContext()) == 2) {
                dialog.getWindow().setLayout((int)(screenWidth * 0.5), -2);
            }
            else if (SystemUtils.getScreenOrientation(this.getContext()) == 1) {
                dialog.getWindow().setLayout((int)(screenWidth * 0.8), -2);
            }
        }
        super.onStart();
    }
    
    public void setOnCancelCommonListener(final DialogSup.DialogSup$OnCancelCommonListener onCancelCommonListener) {
        this.onCancelCommonListener = onCancelCommonListener;
    }
    
    public void setOnSetCommonListener(final DialogSup.DialogSup$OnSetCommonListener onSetCommonListener) {
        this.onSetCommonListener = onSetCommonListener;
    }
    
    public void setOnSupCancelListener(final DialogSup.DialogSup$OnSupCancelListener onSupCancelListener) {
        this.onSupCancelListener = onSupCancelListener;
    }
    
    public void setOnSupDeleteListener(final DialogSup.DialogSup$OnSupDeleteListener onSupDeleteListener) {
        this.onSupDeleteListener = onSupDeleteListener;
    }
    
    public void setOnSupEditListener(final DialogSup.DialogSup$OnSupEditListener onSupEditListener) {
        this.onSupEditListener = onSupEditListener;
    }
}
