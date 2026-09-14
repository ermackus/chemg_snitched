package com.kingagroot.kingdraw.ui.baike.nativeaction;

import android.net.Uri;
import android.content.Intent;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class CallAction extends BaseAction
{
    public CallAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        final Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        final StringBuilder sb = new StringBuilder();
        sb.append("tel:");
        sb.append(s);
        intent.setData(Uri.parse(sb.toString()));
        this.context.startActivity(intent);
    }
}
