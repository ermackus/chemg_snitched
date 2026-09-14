package com.lzf.easyfloat.permission.rom;

import android.os.Binder;
import android.app.AppOpsManager;
import android.os.Build$VERSION;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import android.content.ComponentName;
import android.content.Intent;
import android.app.Fragment;

public class HuaweiUtils
{
    private static final String TAG = "HuaweiUtils";
    
    public static void applyPermission(final Fragment fragment) {
        try {
            final Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (RomUtils.getEmuiVersion() == 3.1) {
                fragment.startActivityForResult(intent, 199);
            }
            else {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
                fragment.startActivityForResult(intent, 199);
            }
        }
        catch (final Exception ex) {
            Toast.makeText((Context)fragment.getActivity(), (CharSequence)"\u8fdb\u5165\u8bbe\u7f6e\u9875\u9762\u5931\u8d25\uff0c\u8bf7\u624b\u52a8\u8bbe\u7f6e", 1).show();
            Log.e("HuaweiUtils", Log.getStackTraceString((Throwable)ex));
        }
        catch (final ActivityNotFoundException ex2) {
            final Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            fragment.startActivityForResult(intent2, 199);
            ex2.printStackTrace();
            Log.e("HuaweiUtils", Log.getStackTraceString((Throwable)ex2));
        }
        catch (final SecurityException ex3) {
            final Intent intent3 = new Intent();
            intent3.setFlags(268435456);
            intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            fragment.startActivityForResult(intent3, 199);
            Log.e("HuaweiUtils", Log.getStackTraceString((Throwable)ex3));
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
                Log.e("HuaweiUtils", Log.getStackTraceString((Throwable)ex));
                return false;
            }
        }
        Log.e("HuaweiUtils", "Below API 19 cannot invoke!");
        return false;
    }
}
