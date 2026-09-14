package com.kingagroot.kingdraw.ui.baike.nativeaction;

import android.net.Uri;
import android.content.Intent;
import android.content.DialogInterface;
import com.goodsrc.library.utils.GsonUtil;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import java.util.Iterator;
import java.util.List;
import android.content.DialogInterface$OnClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kingagroot.kingdraw.ui.baike.model.DialogJsonModel$Btns;
import com.kingagroot.kingdraw.ui.baike.model.DialogJsonModel;
import android.content.Context;

public class DialogAction extends BaseAction
{
    static final boolean $assertionsDisabled = false;
    
    public DialogAction(final Context context) {
        super(context);
    }
    
    private void showMDDialog(final DialogJsonModel dialogJsonModel) {
        final String[] array = { null };
        final List btns = dialogJsonModel.getBtns();
        for (final DialogJsonModel$Btns dialogJsonModel$Btns : btns) {
            if (dialogJsonModel$Btns.getBtnType() == 1) {
                array[0] = dialogJsonModel$Btns.getGetData();
            }
        }
        if (btns.size() > 1) {
            new MaterialAlertDialogBuilder(this.context, 2131886086).setTitle((CharSequence)dialogJsonModel.getDialogTitile()).setMessage((CharSequence)dialogJsonModel.getDialogMsg()).setPositiveButton((CharSequence)((DialogJsonModel$Btns)btns.get(1)).getBtnName(), (DialogInterface$OnClickListener)new _$$Lambda$DialogAction$_zVujhmY8B_gA0_2CdJ01kYHYKU(this, array)).setNegativeButton((CharSequence)((DialogJsonModel$Btns)btns.get(0)).getBtnName(), (DialogInterface$OnClickListener)null).show();
        }
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        this.showMDDialog((DialogJsonModel)GsonUtil.fromJson(s, (Class)DialogJsonModel.class));
    }
}
