package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.kingagroot.kingdraw.ui.baike.PicDialog;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class PicPreviewAction extends BaseAction
{
    public PicPreviewAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        new PicDialog(this.context, s).show();
    }
}
