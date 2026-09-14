package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class TitleAction extends BaseAction
{
    public TitleAction(final Context context) {
        super(context);
    }
    
    public void action(final String viewTitle, final CallBackFunction callBackFunction) {
        PediasActivity.getIntense().setViewTitle(viewTitle);
    }
}
