package com.king.zxing.util;

import androidx.fragment.app.Fragment;
import android.app.Activity;
import androidx.core.app.ActivityCompat;
import android.content.Context;

public class PermissionUtils
{
    private PermissionUtils() {
        throw new AssertionError();
    }
    
    public static boolean checkPermission(final Context context, final String s) {
        return ActivityCompat.checkSelfPermission(context, s) == 0;
    }
    
    public static void requestPermission(final Activity activity, final String s, final int n) {
        requestPermissions(activity, new String[] { s }, n);
    }
    
    public static void requestPermission(final Fragment fragment, final String s, final int n) {
        requestPermissions(fragment, new String[] { s }, n);
    }
    
    public static void requestPermissions(final Activity activity, final String[] array, final int n) {
        ActivityCompat.requestPermissions(activity, array, n);
    }
    
    public static void requestPermissions(final Fragment fragment, final String[] array, final int n) {
        fragment.requestPermissions(array, n);
    }
    
    public static boolean requestPermissionsResult(final String s, final String[] array, final int[] array2) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (s.equals((Object)array[i]) && array2[i] == 0) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean requestPermissionsResult(final String[] array, final String[] array2, final int[] array3) {
        for (int length = array2.length, i = 0; i < length; ++i) {
            for (int j = 0; j < array.length; ++j) {
                if (array[j].equals((Object)array2[i]) && array3[i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
