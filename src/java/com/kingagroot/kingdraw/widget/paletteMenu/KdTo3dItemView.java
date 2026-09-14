package com.kingagroot.kingdraw.widget.paletteMenu;

import com.goodsrc.ui.library.BaseActivity;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.core.view3d.DataElements;
import android.text.TextUtils;
import android.view.View;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.view.View$OnClickListener;
import android.content.Context;
import com.kingagroot.component.ui.widget.LoadingDialog;
import com.kingagroot.component.ui.menu.menuitem.To3dItemView;

public class KdTo3dItemView extends To3dItemView
{
    private LoadingDialog loadingDialog;
    
    public KdTo3dItemView(final Context context) {
        super(context);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    protected void dismissLoading() {
        final LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
    
    public void onClick(final View view) {
        super.onClick(view);
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = this.kingDrawView.getSelectedMoleculeElementsJson()))) {
            s = this.kingDrawView.getAllElementsJson();
        }
        DataElements.cleanElements();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.showLoading(this.context.getString(2131820953));
            new Thread((Runnable)new KdTo3dItemView$1(this, s)).start();
        }
        else {
            ToastUtil.showShort(2131821134);
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
        this.loadingDialog.setTextMessage(textMessage);
        this.loadingDialog.setCancelable(false);
        this.loadingDialog.setOnTouchOutside(false).show(1000L);
    }
}
