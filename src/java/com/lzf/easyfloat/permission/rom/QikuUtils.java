package com.lzf.easyfloat.permission.rom;

import android.os.Binder;
import android.app.AppOpsManager;
import android.os.Build$VERSION;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.app.Fragment;

public class QikuUtils
{
    private static final String TAG = "QikuUtils";
    
    public static void applyPermission(final Fragment fragment) {
        final Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
            fragment.startActivityForResult(intent, 199);
        }
        else {
            intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
            if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
                fragment.startActivityForResult(intent, 199);
            }
            else {
                Log.e("QikuUtils", "can't open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
            }
        }
    }
    
    public static boolean checkFloatWindowPermission(final Context context) {
        return Build$VERSION.SDK_INT < 19 || checkOp(context, 24);
    }
    
    private static boolean checkOp(final Context context, int intValue) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b = false;
        if (sdk_INT >= 19) {
            final AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService("appops");
            try {
                intValue = (int)AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((Object)appOpsManager, new Object[] { intValue, Binder.getCallingUid(), context.getPackageName() });
                if (intValue == 0) {
                    b = true;
                }
                return b;
            }
            catch (final Exception ex) {
                Log.e("QikuUtils", Log.getStackTraceString((Throwable)ex));
                return false;
            }
        }
        Log.e("", "Below API 19 cannot invoke!");
        return false;
    }
    
    private static boolean isIntentAvailable(final Intent intent, final Context context) {
        boolean b = false;
        if (intent == null) {
            return false;
        }
        if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
            b = true;
        }
        return b;
    }
}
