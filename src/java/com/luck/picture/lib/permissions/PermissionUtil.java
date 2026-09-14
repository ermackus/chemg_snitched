package com.luck.picture.lib.permissions;

import androidx.core.content.ContextCompat;
import android.os.Build$VERSION;
import android.content.Context;
import android.net.Uri;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public class PermissionUtil
{
    public static final String ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION";
    
    public static void goIntentSetting(final Fragment fragment, final int n) {
        try {
            final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", fragment.getActivity().getPackageName(), (String)null));
            fragment.startActivityForResult(intent, n);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean hasPermissions(final Context context, final String... array) {
        if (Build$VERSION.SDK_INT < 23) {
            return true;
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            if (ContextCompat.checkSelfPermission(context, array[i]) != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isAllGranted(final int[] array) {
        final int length = array.length;
        boolean b = false;
        if (length > 0) {
            for (int length2 = array.length, i = 0; i < length2; ++i) {
                if (array[i] != 0) {
                    b = b;
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
}
