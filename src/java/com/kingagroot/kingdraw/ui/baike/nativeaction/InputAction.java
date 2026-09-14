package com.kingagroot.kingdraw.ui.baike.nativeaction;

import android.app.Activity;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.baike.SearchPediaActivity;
import com.kingagroot.component.ui.utils.CheckDoubleClick;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class InputAction extends BaseAction
{
    public static final int REQUESTCODE_INPUT = 1001;
    
    public InputAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        if (CheckDoubleClick.isFastDoubleClick()) {
            return;
        }
        final Intent intent = new Intent(this.context, (Class)SearchPediaActivity.class);
        final Activity activity = (Activity)this.context;
        intent.putExtra("searchKey", s);
        activity.startActivityForResult(intent, 1001);
    }
}
