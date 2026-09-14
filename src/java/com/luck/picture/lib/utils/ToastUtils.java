package com.luck.picture.lib.utils;

import android.widget.Toast;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.app.PictureAppMaster;
import android.text.TextUtils;
import android.content.Context;

public class ToastUtils
{
    private static final long TIME = 1000L;
    private static long lastClickTime;
    private static String mLastText;
    
    public static boolean isFastDoubleClick() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - ToastUtils.lastClickTime < 1000L) {
            return true;
        }
        ToastUtils.lastClickTime = currentTimeMillis;
        return false;
    }
    
    public static void showToast(final Context context, final String mLastText) {
        if (isFastDoubleClick() && TextUtils.equals((CharSequence)mLastText, (CharSequence)ToastUtils.mLastText)) {
            return;
        }
        Context context2;
        if ((context2 = PictureAppMaster.getInstance().getAppContext()) == null) {
            context2 = context.getApplicationContext();
        }
        if (PictureThreadUtils.isInUiThread()) {
            Toast.makeText(context2, (CharSequence)mLastText, 0).show();
            ToastUtils.mLastText = mLastText;
        }
        else {
            PictureThreadUtils.runOnUiThread((Runnable)new Runnable(context, mLastText) {
                final Context val$context;
                final String val$text;
                
                public void run() {
                    Context context;
                    if ((context = PictureAppMaster.getInstance().getAppContext()) == null) {
                        context = this.val$context.getApplicationContext();
                    }
                    Toast.makeText(context, (CharSequence)this.val$text, 0).show();
                    ToastUtils.mLastText = this.val$text;
                }
            });
        }
    }
}
