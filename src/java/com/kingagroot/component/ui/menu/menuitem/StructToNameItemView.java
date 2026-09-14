package com.kingagroot.component.ui.menu.menuitem;

import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.component.ui.R$mipmap;
import com.kingagroot.kingdraw.core.StructConvertListener;
import com.kingagroot.kingdraw.core.tool.Struct2NameTool;
import com.kingagroot.component.ui.R$string;
import android.view.View;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import android.content.Context;
import com.kingagroot.component.ui.widget.LoadingDialog;

public class StructToNameItemView extends ToolBaseItemView
{
    private LoadingDialog loadingDialog;
    
    public StructToNameItemView(final Context context) {
        super(context);
        this.init();
    }
    
    private void init() {
        this.setButtonDrawable(R$drawable.ic_iupac_name);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setClickEnable(false);
    }
    
    protected void dismissLoading() {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.showLoading(this.context.getString(R$string.iupac_conversion));
        new Struct2NameTool(this.kingDrawView.getPaletteId()).structToName((StructConvertListener)new StructToNameItemView$1(this));
    }
    
    @Override
    public void setClickEnable(final boolean clickEnable) {
        super.setClickEnable(clickEnable);
        if (clickEnable) {
            this.setButtonDrawable(R$mipmap.ic_iupac_name);
        }
        else {
            this.setButtonDrawable(R$mipmap.ic_iupac_name_no);
        }
    }
    
    @Override
    public void setKingDrawView(final KingDrawView kingDrawView) {
        super.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new StructToNameItemView$2(this));
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
        this.loadingDialog.setTextMessage(textMessage);
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setOnTouchOutside(false).show();
    }
}
