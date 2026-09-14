package com.hjq.permissions;

import java.util.Iterator;
import java.util.Collection;
import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.app.Fragment;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public final class XXPermissions
{
    public static final int REQUEST_CODE = 1025;
    private static Boolean sCheckMode;
    private static IPermissionInterceptor sInterceptor;
    private Boolean mCheckMode;
    private final Context mContext;
    private IPermissionInterceptor mInterceptor;
    private final List<String> mPermissions;
    
    private XXPermissions(final Context mContext) {
        this.mPermissions = (List<String>)new ArrayList();
        this.mContext = mContext;
    }
    
    public static boolean containsSpecial(final List<String> list) {
        return PermissionApi.containsSpecialPermission(list);
    }
    
    public static boolean containsSpecial(final String... array) {
        return containsSpecial((List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static List<String> getDenied(final Context context, final List<String> list) {
        return PermissionApi.getDeniedPermissions(context, list);
    }
    
    public static List<String> getDenied(final Context context, final String... array) {
        return getDenied(context, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static List<String> getDenied(final Context context, final String[]... array) {
        return getDenied(context, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static IPermissionInterceptor getInterceptor() {
        if (XXPermissions.sInterceptor == null) {
            XXPermissions.sInterceptor = (IPermissionInterceptor)new XXPermissions$1();
        }
        return XXPermissions.sInterceptor;
    }
    
    private boolean isCheckMode(final Context context) {
        if (this.mCheckMode == null) {
            if (XXPermissions.sCheckMode == null) {
                XXPermissions.sCheckMode = PermissionUtils.isDebugMode(context);
            }
            this.mCheckMode = XXPermissions.sCheckMode;
        }
        return this.mCheckMode;
    }
    
    public static boolean isGranted(final Context context, final List<String> list) {
        return PermissionApi.isGrantedPermissions(context, list);
    }
    
    public static boolean isGranted(final Context context, final String... array) {
        return isGranted(context, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static boolean isGranted(final Context context, final String[]... array) {
        return isGranted(context, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static boolean isPermanentDenied(final Activity activity, final List<String> list) {
        return PermissionApi.isPermissionPermanentDenied(activity, list);
    }
    
    public static boolean isPermanentDenied(final Activity activity, final String... array) {
        return isPermanentDenied(activity, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static boolean isPermanentDenied(final Activity activity, final String[]... array) {
        return isPermanentDenied(activity, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static boolean isSpecial(final String s) {
        return PermissionApi.isSpecialPermission(s);
    }
    
    public static void setCheckMode(final boolean b) {
        XXPermissions.sCheckMode = b;
    }
    
    public static void setInterceptor(final IPermissionInterceptor sInterceptor) {
        XXPermissions.sInterceptor = sInterceptor;
    }
    
    public static void startPermissionActivity(final Activity activity) {
        startPermissionActivity(activity, (List<String>)new ArrayList(0));
    }
    
    public static void startPermissionActivity(final Activity activity, final String s, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, (List<String>)PermissionUtils.asArrayList(s), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final Activity activity, final List<String> list) {
        startPermissionActivity(activity, list, 1025);
    }
    
    public static void startPermissionActivity(final Activity activity, final List<String> list, final int n) {
        StartActivityManager.startActivityForResult(activity, PermissionUtils.getSmartPermissionIntent((Context)activity, list), n);
    }
    
    public static void startPermissionActivity(final Activity activity, final List<String> list, final OnPermissionPageCallback onPermissionPageCallback) {
        if (list.isEmpty()) {
            StartActivityManager.startActivity(activity, PermissionIntentManager.getApplicationDetailsIntent((Context)activity));
            return;
        }
        PermissionPageFragment.beginRequest(activity, (ArrayList<String>)list, onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final Activity activity, final String... array) {
        startPermissionActivity(activity, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static void startPermissionActivity(final Activity activity, final String[] array, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, (List<String>)PermissionUtils.asArrayLists(new String[][] { array }), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final Activity activity, final String[]... array) {
        startPermissionActivity(activity, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static void startPermissionActivity(final Fragment fragment) {
        startPermissionActivity(fragment, (List<String>)new ArrayList(0));
    }
    
    public static void startPermissionActivity(final Fragment fragment, final String s, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayList(s), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final Fragment fragment, final List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }
    
    public static void startPermissionActivity(final Fragment fragment, final List<String> list, final int n) {
        final Activity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent((Context)activity));
            return;
        }
        StartActivityManager.startActivityForResult(fragment, PermissionUtils.getSmartPermissionIntent((Context)activity, list), n);
    }
    
    public static void startPermissionActivity(final Fragment fragment, final List<String> list, final OnPermissionPageCallback onPermissionPageCallback) {
        final Activity activity = fragment.getActivity();
        if (activity != null) {
            if (!activity.isFinishing()) {
                if (AndroidVersion.isAndroid4_2() && activity.isDestroyed()) {
                    return;
                }
                if (list.isEmpty()) {
                    StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent((Context)activity));
                    return;
                }
                PermissionPageFragment.beginRequest(activity, (ArrayList<String>)list, onPermissionPageCallback);
            }
        }
    }
    
    public static void startPermissionActivity(final Fragment fragment, final String... array) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static void startPermissionActivity(final Fragment fragment, final String[] array, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayLists(new String[][] { array }), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final Fragment fragment, final String[]... array) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static void startPermissionActivity(final Context context) {
        startPermissionActivity(context, (List<String>)new ArrayList(0));
    }
    
    public static void startPermissionActivity(final Context context, final List<String> list) {
        final Activity activity = PermissionUtils.findActivity(context);
        if (activity != null) {
            startPermissionActivity(activity, list);
            return;
        }
        final Intent smartPermissionIntent = PermissionUtils.getSmartPermissionIntent(context, list);
        if (!(context instanceof Activity)) {
            smartPermissionIntent.addFlags(268435456);
        }
        StartActivityManager.startActivity(context, smartPermissionIntent);
    }
    
    public static void startPermissionActivity(final Context context, final String... array) {
        startPermissionActivity(context, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static void startPermissionActivity(final Context context, final String[]... array) {
        startPermissionActivity(context, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment) {
        startPermissionActivity(fragment, (List<String>)new ArrayList());
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final String s, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayList(s), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final List<String> list, final int n) {
        final FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return;
        }
        if (list.isEmpty()) {
            StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent((Context)activity));
            return;
        }
        StartActivityManager.startActivityForResult(fragment, PermissionUtils.getSmartPermissionIntent((Context)activity, list), n);
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final List<String> list, final OnPermissionPageCallback onPermissionPageCallback) {
        final FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            if (!((Activity)activity).isFinishing()) {
                if (AndroidVersion.isAndroid4_2() && ((Activity)activity).isDestroyed()) {
                    return;
                }
                if (list.isEmpty()) {
                    StartActivityManager.startActivity(fragment, PermissionIntentManager.getApplicationDetailsIntent((Context)activity));
                    return;
                }
                PermissionPageFragment.beginRequest((Activity)activity, (ArrayList<String>)list, onPermissionPageCallback);
            }
        }
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final String... array) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayList(array));
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final String[] array, final OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayLists(new String[][] { array }), onPermissionPageCallback);
    }
    
    public static void startPermissionActivity(final androidx.fragment.app.Fragment fragment, final String[]... array) {
        startPermissionActivity(fragment, (List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public static XXPermissions with(final Fragment fragment) {
        return with((Context)fragment.getActivity());
    }
    
    public static XXPermissions with(final Context context) {
        return new XXPermissions(context);
    }
    
    public static XXPermissions with(final androidx.fragment.app.Fragment fragment) {
        return with((Context)fragment.getActivity());
    }
    
    public XXPermissions interceptor(final IPermissionInterceptor mInterceptor) {
        this.mInterceptor = mInterceptor;
        return this;
    }
    
    public XXPermissions permission(final List<String> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                for (final String s : list) {
                    if (PermissionUtils.containsPermission((Collection<String>)this.mPermissions, s)) {
                        continue;
                    }
                    this.mPermissions.add((Object)s);
                }
            }
        }
        return this;
    }
    
    public XXPermissions permission(final String... array) {
        return this.permission((List<String>)PermissionUtils.asArrayList(array));
    }
    
    public XXPermissions permission(final String[]... array) {
        return this.permission((List<String>)PermissionUtils.asArrayLists(array));
    }
    
    public void request(final OnPermissionCallback onPermissionCallback) {
        if (this.mContext == null) {
            return;
        }
        if (this.mInterceptor == null) {
            this.mInterceptor = getInterceptor();
        }
        final Context mContext = this.mContext;
        final IPermissionInterceptor mInterceptor = this.mInterceptor;
        final ArrayList list = new ArrayList((Collection)this.mPermissions);
        final boolean checkMode = this.isCheckMode(mContext);
        final Activity activity = PermissionUtils.findActivity(mContext);
        if (!PermissionChecker.checkActivityStatus(activity, checkMode)) {
            return;
        }
        if (!PermissionChecker.checkPermissionArgument((List<String>)list, checkMode)) {
            return;
        }
        if (checkMode) {
            final AndroidManifestInfo androidManifestInfo = PermissionUtils.getAndroidManifestInfo(mContext);
            PermissionChecker.checkMediaLocationPermission(mContext, (List<String>)list);
            PermissionChecker.checkStoragePermission(mContext, (List<String>)list, androidManifestInfo);
            PermissionChecker.checkBodySensorsPermission((List)list);
            PermissionChecker.checkLocationPermission((List<String>)list);
            PermissionChecker.checkPictureInPicturePermission(activity, (List<String>)list, androidManifestInfo);
            PermissionChecker.checkNotificationListenerPermission((List<String>)list, androidManifestInfo);
            PermissionChecker.checkNearbyDevicesPermission((List<String>)list, androidManifestInfo);
            PermissionChecker.checkTargetSdkVersion(mContext, (List<String>)list);
            PermissionChecker.checkManifestPermissions(mContext, (List<String>)list, androidManifestInfo);
        }
        PermissionChecker.optimizeDeprecatedPermission((List<String>)list);
        if (PermissionApi.isGrantedPermissions(mContext, (List<String>)list)) {
            if (onPermissionCallback != null) {
                mInterceptor.grantedPermissionRequest(activity, (List<String>)list, (List<String>)list, true, onPermissionCallback);
                mInterceptor.finishPermissionRequest(activity, (List<String>)list, true, onPermissionCallback);
            }
            return;
        }
        mInterceptor.launchPermissionRequest(activity, (List<String>)list, onPermissionCallback);
    }
    
    public boolean revokeOnKill() {
        final Context mContext = this.mContext;
        if (mContext == null) {
            return false;
        }
        final List<String> mPermissions = this.mPermissions;
        if (mPermissions.isEmpty()) {
            return false;
        }
        if (!AndroidVersion.isAndroid13()) {
            return false;
        }
        try {
            if (mPermissions.size() == 1) {
                mContext.revokeSelfPermissionOnKill((String)mPermissions.get(0));
            }
            else {
                mContext.revokeSelfPermissionsOnKill((Collection)mPermissions);
            }
            return true;
        }
        catch (final IllegalArgumentException ex) {
            if (!this.isCheckMode(mContext)) {
                ex.printStackTrace();
                return false;
            }
            throw ex;
        }
    }
    
    public XXPermissions unchecked() {
        this.mCheckMode = false;
        return this;
    }
}
