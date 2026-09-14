package com.kingagroot.component.ui.menu.menuitem;

import com.goodsrc.ui.library.BaseActivity;
import android.view.View;
import com.kingagroot.component.ui.widget.EditPop$OnEditPopListener;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.widget.EditPop$Build;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import com.kingagroot.component.ui.widget.LoadingDialog;

public class NameToStructItemView extends ToolBaseItemView
{
    private LoadingDialog loadingDialog;
    
    public NameToStructItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_name_iupac);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    protected void dismissLoading() {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    
    public void inputText() {
        new EditPop$Build(this.getContext()).setContent("").setHint(this.getContext().getString(R$string.palette_input_iupac_name)).setMaxLength(200).setShowCount(false).setOnEditPopListener((EditPop$OnEditPopListener)new NameToStructItemView$1(this)).create().show();
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.inputText();
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawable(R$drawable.ic_name_iupac);
        }
        else {
            this.setButtonDrawable(R$drawable.ic_name_iupac_no);
        }
    }
    
    protected void showLoading(final String textMessage) {
        final BaseActivity baseActivity = (BaseActivity)this.context;
        if (baseActivity.isFinishing()) {
            return;
        }
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(baseActivity);
        }
        else {
            loadingDialog.dismiss();
        }
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setTextMessage(textMessage).setOnTouchOutside(false).show();
    }
}
