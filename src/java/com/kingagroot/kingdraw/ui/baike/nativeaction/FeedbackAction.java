package com.kingagroot.kingdraw.ui.baike.nativeaction;

import android.content.Intent;
import com.kingagroot.kingdraw.ui.FeedbackActivity;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class FeedbackAction extends BaseAction
{
    public FeedbackAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        this.context.startActivity(new Intent(this.context, (Class)FeedbackActivity.class));
    }
}
