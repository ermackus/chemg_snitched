package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.kingagroot.kingdraw.ui.baike.ChemInfoMoreDialog;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class MoreInfoAction extends BaseAction
{
    public MoreInfoAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        new ChemInfoMoreDialog(this.context, s).show();
    }
}
