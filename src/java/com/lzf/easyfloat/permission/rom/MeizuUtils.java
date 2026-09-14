package com.lzf.easyfloat.permission.rom;

import android.os.Binder;
import android.app.AppOpsManager;
import android.os.Build$VERSION;
import android.content.Context;
import com.lzf.easyfloat.permission.PermissionUtils;
import android.util.Log;
import android.content.Intent;
import android.app.Fragment;

public class MeizuUtils
{
    private static final String TAG = "MeizuUtils";
    
    public static void applyPermission(final Fragment fragment) {
        try {
            final Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.putExtra("packageName", fragment.getActivity().getPackageName());
            fragment.startActivityForResult(intent, 199);
        }
        catch (final Exception ex) {
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append("\u83b7\u53d6\u60ac\u6d6e\u7a97\u6743\u9650, \u6253\u5f00AppSecActivity\u5931\u8d25, ");
                sb.append(Log.getStackTraceString((Throwable)ex));
                Log.e("MeizuUtils", sb.toString());
                PermissionUtils.commonROMPermissionApplyInternal(fragment);
            }
            catch (final Exception ex2) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("\u83b7\u53d6\u60ac\u6d6e\u7a97\u6743\u9650\u5931\u8d25, \u901a\u7528\u83b7\u53d6\u65b9\u6cd5\u5931\u8d25, ");
                sb2.append(Log.getStackTraceString((Throwable)ex2));
                Log.e("MeizuUtils", sb2.toString());
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
                Log.e("MeizuUtils", Log.getStackTraceString((Throwable)ex));
                return false;
            }
        }
        Log.e("MeizuUtils", "Below API 19 cannot invoke!");
        return false;
    }
}
