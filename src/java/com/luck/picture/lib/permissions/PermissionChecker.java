package com.luck.picture.lib.permissions;

import java.util.Iterator;
import androidx.fragment.app.FragmentActivity;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import android.os.Build$VERSION;
import com.luck.picture.lib.basic.PictureCommonFragment;
import android.app.Activity;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import java.util.List;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.utils.SdkVersionUtils;
import androidx.core.content.ContextCompat;
import android.content.Context;

public class PermissionChecker
{
    private static final int REQUEST_CODE = 10086;
    private static PermissionChecker mInstance;
    
    private PermissionChecker() {
    }
    
    public static boolean checkSelfPermission(final Context context, final String[] array) {
        boolean b = false;
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), array[i]) != 0) {
                    return b;
                }
            }
        }
        b = true;
        return b;
    }
    
    public static PermissionChecker getInstance() {
        if (PermissionChecker.mInstance == null) {
            synchronized (PermissionChecker.class) {
                if (PermissionChecker.mInstance == null) {
                    PermissionChecker.mInstance = new PermissionChecker();
                }
            }
        }
        return PermissionChecker.mInstance;
    }
    
    public static boolean isCheckCamera(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.CAMERA" });
    }
    
    public static boolean isCheckReadAudio(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.READ_MEDIA_AUDIO" });
    }
    
    public static boolean isCheckReadExternalStorage(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.READ_EXTERNAL_STORAGE" });
    }
    
    public static boolean isCheckReadImages(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.READ_MEDIA_IMAGES" });
    }
    
    public static boolean isCheckReadStorage(final int n, final Context context) {
        if (!SdkVersionUtils.isTIRAMISU()) {
            return isCheckReadExternalStorage(context);
        }
        if (n == SelectMimeType.ofImage()) {
            return isCheckReadImages(context);
        }
        if (n == SelectMimeType.ofVideo()) {
            return isCheckReadVideo(context);
        }
        if (n == SelectMimeType.ofAudio()) {
            return isCheckReadAudio(context);
        }
        return isCheckReadImages(context) && isCheckReadVideo(context);
    }
    
    public static boolean isCheckReadVideo(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.READ_MEDIA_VIDEO" });
    }
    
    public static boolean isCheckSelfPermission(final Context context, final String[] array) {
        return checkSelfPermission(context, array);
    }
    
    public static boolean isCheckWriteExternalStorage(final Context context) {
        return checkSelfPermission(context, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
    }
    
    private void requestPermissions(final Fragment fragment, final List<String[]> list, final int n, final PermissionResultCallback permissionsResultAction) {
        if (ActivityCompatHelper.isDestroy((Activity)fragment.getActivity())) {
            return;
        }
        if (fragment instanceof PictureCommonFragment) {
            if (Build$VERSION.SDK_INT < 23) {
                if (permissionsResultAction != null) {
                    permissionsResultAction.onGranted();
                }
                return;
            }
            final FragmentActivity activity = fragment.getActivity();
            final ArrayList list2 = new ArrayList();
            for (final String[] array : list) {
                for (final String s : array) {
                    if (ContextCompat.checkSelfPermission((Context)activity, s) != 0) {
                        ((List)list2).add((Object)s);
                    }
                }
            }
            if (((List)list2).size() > 0) {
                ((PictureCommonFragment)fragment).setPermissionsResultAction(permissionsResultAction);
                final String[] array2 = new String[((List)list2).size()];
                ((List)list2).toArray((Object[])array2);
                fragment.requestPermissions(array2, n);
                ActivityCompat.requestPermissions((Activity)activity, array2, n);
            }
            else if (permissionsResultAction != null) {
                permissionsResultAction.onGranted();
            }
        }
    }
    
    public void onRequestPermissionsResult(final int[] array, final PermissionResultCallback permissionResultCallback) {
        if (PermissionUtil.isAllGranted(array)) {
            permissionResultCallback.onGranted();
        }
        else {
            permissionResultCallback.onDenied();
        }
    }
    
    public void requestPermissions(final Fragment fragment, final List<String[]> list, final PermissionResultCallback permissionResultCallback) {
        this.requestPermissions(fragment, list, 10086, permissionResultCallback);
    }
    
    public void requestPermissions(final Fragment fragment, final String[] array, final PermissionResultCallback permissionResultCallback) {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)array);
        this.requestPermissions(fragment, (List<String[]>)list, 10086, permissionResultCallback);
    }
}
