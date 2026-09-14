package com.lzf.easyfloat.permission.rom;

import android.net.Uri;
import android.content.Intent;
import android.os.Binder;
import android.app.AppOpsManager;
import android.os.Build$VERSION;
import android.content.Context;
import android.util.Log;
import android.app.Fragment;

public class MiuiUtils
{
    private static final String TAG = "MiuiUtils";
    
    public static void applyMiuiPermission(final Fragment fragment) {
        final int miuiVersion = getMiuiVersion();
        if (miuiVersion == 5) {
            goToMiuiPermissionActivity_V5(fragment);
        }
        else if (miuiVersion == 6) {
            goToMiuiPermissionActivity_V6(fragment);
        }
        else if (miuiVersion == 7) {
            goToMiuiPermissionActivity_V7(fragment);
        }
        else if (miuiVersion >= 8) {
            goToMiuiPermissionActivity_V8(fragment);
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append("this is a special MIUI rom version, its version code ");
            sb.append(miuiVersion);
            Log.e("MiuiUtils", sb.toString());
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
                Log.e("MiuiUtils", Log.getStackTraceString((Throwable)ex));
                return false;
            }
        }
        Log.e("MiuiUtils", "Below API 19 cannot invoke!");
        return false;
    }
    
    public static int getMiuiVersion() {
        final String systemProperty = RomUtils.getSystemProperty("ro.miui.ui.version.name");
        if (systemProperty != null) {
            try {
                return Integer.parseInt(systemProperty.substring(1));
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("get miui version code error, version : ");
                sb.append(systemProperty);
                Log.e("MiuiUtils", sb.toString());
                Log.e("MiuiUtils", Log.getStackTraceString((Throwable)ex));
            }
        }
        return -1;
    }
    
    public static void goToMiuiPermissionActivity_V5(final Fragment fragment) {
        final String packageName = fragment.getActivity().getPackageName();
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", packageName, (String)null));
        if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
            fragment.startActivityForResult(intent, 199);
        }
        else {
            Log.e("MiuiUtils", "intent is not available!");
        }
    }
    
    public static void goToMiuiPermissionActivity_V6(final Fragment fragment) {
        final Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", fragment.getActivity().getPackageName());
        if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
            fragment.startActivityForResult(intent, 199);
        }
        else {
            Log.e("MiuiUtils", "Intent is not available!");
        }
    }
    
    public static void goToMiuiPermissionActivity_V7(final Fragment fragment) {
        final Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", fragment.getActivity().getPackageName());
        if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
            fragment.startActivityForResult(intent, 199);
        }
        else {
            Log.e("MiuiUtils", "Intent is not available!");
        }
    }
    
    public static void goToMiuiPermissionActivity_V8(final Fragment fragment) {
        final Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", fragment.getActivity().getPackageName());
        if (isIntentAvailable(intent, (Context)fragment.getActivity())) {
            fragment.startActivityForResult(intent, 199);
        }
        else {
            final Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent2.setPackage("com.miui.securitycenter");
            intent2.putExtra("extra_pkgname", fragment.getActivity().getPackageName());
            if (isIntentAvailable(intent2, (Context)fragment.getActivity())) {
                fragment.startActivityForResult(intent2, 199);
            }
            else {
                Log.e("MiuiUtils", "Intent is not available!");
            }
        }
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
