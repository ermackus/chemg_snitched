package com.lzf.easyfloat.permission.rom;

import android.util.Log;
import android.os.Binder;
import android.app.AppOpsManager;
import android.os.Build$VERSION;
import android.content.Context;
import android.content.ComponentName;
import android.content.Intent;
import android.app.Fragment;

public class OppoUtils
{
    private static final String TAG = "OppoUtils";
    
    public static void applyOppoPermission(final Fragment fragment) {
        try {
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity"));
            fragment.startActivityForResult(intent, 199);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
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
                Log.e("OppoUtils", Log.getStackTraceString((Throwable)ex));
                return false;
            }
        }
        Log.e("OppoUtils", "Below API 19 cannot invoke!");
        return false;
    }
}
