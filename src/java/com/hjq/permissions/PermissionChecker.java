package com.hjq.permissions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import android.app.Activity;

final class PermissionChecker
{
    static boolean checkActivityStatus(final Activity activity, final boolean b) {
        if (activity == null) {
            if (!b) {
                return false;
            }
            throw new IllegalArgumentException("The instance of the context must be an activity object");
        }
        else if (activity.isFinishing()) {
            if (!b) {
                return false;
            }
            throw new IllegalStateException("The activity has been finishing, please manually determine the status of the activity");
        }
        else {
            if (!AndroidVersion.isAndroid4_2() || !activity.isDestroyed()) {
                return true;
            }
            if (!b) {
                return false;
            }
            throw new IllegalStateException("The activity has been destroyed, please manually determine the status of the activity");
        }
    }
    
    static void checkBodySensorsPermission(final List<String> list) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BODY_SENSORS_BACKGROUND")) {
            return;
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BODY_SENSORS_BACKGROUND") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BODY_SENSORS")) {
            throw new IllegalArgumentException("Applying for background sensor permissions must contain android.permission.BODY_SENSORS");
        }
        for (final String s : list) {
            if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_BACKGROUND_LOCATION at the same time is not supported");
            }
            if (!PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION")) {
                continue;
            }
            throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_MEDIA_LOCATION at the same time is not supported");
        }
    }
    
    static void checkLocationPermission(final List<String> list) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return;
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_COARSE_LOCATION") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_FINE_LOCATION")) {
            throw new IllegalArgumentException("Applying for background positioning permissions must include android.permission.ACCESS_FINE_LOCATION");
        }
        for (final String s : list) {
            if (!PermissionUtils.equalsPermission(s, "android.permission.ACCESS_FINE_LOCATION") && !PermissionUtils.equalsPermission(s, "android.permission.ACCESS_COARSE_LOCATION")) {
                if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    continue;
                }
                throw new IllegalArgumentException("Because it includes background location permissions, do not apply for permissions unrelated to location");
            }
        }
    }
    
    static void checkManifestPermission(final List<AndroidManifestInfo.PermissionInfo> list, final String s) {
        checkManifestPermission(list, s, Integer.MAX_VALUE);
    }
    
    static void checkManifestPermission(final List<AndroidManifestInfo.PermissionInfo> list, final String s, final int n) {
        while (true) {
            for (final AndroidManifestInfo.PermissionInfo permissionInfo : list) {
                if (TextUtils.equals((CharSequence)permissionInfo.name, (CharSequence)s)) {
                    if (permissionInfo == null) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("Please register permissions in the AndroidManifest.xml file <uses-permission android:name=\"");
                        sb.append(s);
                        sb.append("\" />");
                        throw new IllegalStateException(sb.toString());
                    }
                    final int maxSdkVersion = permissionInfo.maxSdkVersion;
                    if (maxSdkVersion < n) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("The AndroidManifest.xml file <uses-permission android:name=\"");
                        sb2.append(s);
                        sb2.append("\" android:maxSdkVersion=\"");
                        sb2.append(maxSdkVersion);
                        sb2.append("\" /> does not meet the requirements, ");
                        String s2;
                        if (n != Integer.MAX_VALUE) {
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("the minimum requirement for maxSdkVersion is ");
                            sb3.append(n);
                            s2 = sb3.toString();
                        }
                        else {
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append("please delete the android:maxSdkVersion=\"");
                            sb4.append(maxSdkVersion);
                            sb4.append("\" attribute");
                            s2 = sb4.toString();
                        }
                        sb2.append(s2);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    return;
                }
            }
            AndroidManifestInfo.PermissionInfo permissionInfo = null;
            continue;
        }
    }
    
    static void checkManifestPermissions(final Context context, final List<String> list, final AndroidManifestInfo androidManifestInfo) {
        if (androidManifestInfo == null) {
            return;
        }
        final List<AndroidManifestInfo.PermissionInfo> permissionInfoList = androidManifestInfo.permissionInfoList;
        if (!permissionInfoList.isEmpty()) {
            int n;
            if (AndroidVersion.isAndroid7()) {
                n = context.getApplicationInfo().minSdkVersion;
            }
            else if (androidManifestInfo.usesSdkInfo != null) {
                n = androidManifestInfo.usesSdkInfo.minSdkVersion;
            }
            else {
                n = 23;
            }
            for (final String s : list) {
                if (!PermissionUtils.equalsPermission(s, "android.permission.NOTIFICATION_SERVICE") && !PermissionUtils.equalsPermission(s, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE") && !PermissionUtils.equalsPermission(s, "android.permission.BIND_VPN_SERVICE")) {
                    if (PermissionUtils.equalsPermission(s, "android.permission.PICTURE_IN_PICTURE")) {
                        continue;
                    }
                    checkManifestPermission(permissionInfoList, s);
                    if (PermissionUtils.equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND")) {
                        checkManifestPermission(permissionInfoList, "android.permission.BODY_SENSORS");
                    }
                    else if (PermissionUtils.equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                        if (AndroidVersion.getTargetSdkVersionCode(context) >= 31) {
                            checkManifestPermission(permissionInfoList, "android.permission.ACCESS_FINE_LOCATION", 30);
                            checkManifestPermission(permissionInfoList, "android.permission.ACCESS_COARSE_LOCATION");
                        }
                        else {
                            checkManifestPermission(permissionInfoList, "android.permission.ACCESS_FINE_LOCATION");
                        }
                    }
                    else {
                        if (n < 33) {
                            if (PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
                                checkManifestPermission(permissionInfoList, "android.permission.READ_EXTERNAL_STORAGE", 32);
                                continue;
                            }
                            if (PermissionUtils.equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES")) {
                                checkManifestPermission(permissionInfoList, "android.permission.ACCESS_FINE_LOCATION", 32);
                                continue;
                            }
                        }
                        if (n < 31) {
                            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_SCAN")) {
                                checkManifestPermission(permissionInfoList, "android.permission.BLUETOOTH_ADMIN", 30);
                                checkManifestPermission(permissionInfoList, "android.permission.ACCESS_FINE_LOCATION", 30);
                                continue;
                            }
                            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_CONNECT")) {
                                checkManifestPermission(permissionInfoList, "android.permission.BLUETOOTH", 30);
                                continue;
                            }
                            if (PermissionUtils.equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
                                checkManifestPermission(permissionInfoList, "android.permission.BLUETOOTH_ADMIN", 30);
                                continue;
                            }
                        }
                        if (n < 30 && PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                            checkManifestPermission(permissionInfoList, "android.permission.READ_EXTERNAL_STORAGE", 29);
                            checkManifestPermission(permissionInfoList, "android.permission.WRITE_EXTERNAL_STORAGE", 29);
                        }
                        else if (n < 26 && PermissionUtils.equalsPermission(s, "android.permission.READ_PHONE_NUMBERS")) {
                            checkManifestPermission(permissionInfoList, "android.permission.READ_PHONE_STATE", 25);
                        }
                        else {
                            if (!PermissionUtils.equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS")) {
                                continue;
                            }
                            checkManifestPermission(permissionInfoList, "android.permission.QUERY_ALL_PACKAGES");
                        }
                    }
                }
            }
            return;
        }
        throw new IllegalStateException("No permissions are registered in the AndroidManifest.xml file");
    }
    
    static void checkMediaLocationPermission(final Context context, final List<String> list) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_MEDIA_LOCATION")) {
            return;
        }
        for (final String s : list) {
            if (!PermissionUtils.equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION") && !PermissionUtils.equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") && !PermissionUtils.equalsPermission(s, "android.permission.READ_EXTERNAL_STORAGE") && !PermissionUtils.equalsPermission(s, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                if (PermissionUtils.equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    continue;
                }
                throw new IllegalArgumentException("Because it includes access media location permissions, do not apply for permissions unrelated to access media location");
            }
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
            if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_IMAGES")) {
                if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    throw new IllegalArgumentException("You must add android.permission.READ_MEDIA_IMAGES or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
                }
            }
        }
        else if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE")) {
            if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                throw new IllegalArgumentException("You must add android.permission.READ_EXTERNAL_STORAGE or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
            }
        }
    }
    
    static void checkNearbyDevicesPermission(final List<String> list, final AndroidManifestInfo androidManifestInfo) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BLUETOOTH_SCAN") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.NEARBY_WIFI_DEVICES")) {
            return;
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_FINE_LOCATION")) {
            return;
        }
        if (androidManifestInfo == null) {
            return;
        }
        for (final AndroidManifestInfo.PermissionInfo permissionInfo : androidManifestInfo.permissionInfoList) {
            if (!PermissionUtils.equalsPermission(permissionInfo.name, "android.permission.BLUETOOTH_SCAN") && !PermissionUtils.equalsPermission(permissionInfo.name, "android.permission.NEARBY_WIFI_DEVICES")) {
                continue;
            }
            if (!permissionInfo.neverForLocation()) {
                String string;
                if (permissionInfo.maxSdkVersion != Integer.MAX_VALUE) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("android:maxSdkVersion=\"");
                    sb.append(permissionInfo.maxSdkVersion);
                    sb.append("\" ");
                    string = sb.toString();
                }
                else {
                    string = "";
                }
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("If your app doesn't use ");
                sb2.append(permissionInfo.name);
                sb2.append(" to get physical location, please change the <uses-permission android:name=\"");
                sb2.append(permissionInfo.name);
                sb2.append("\" ");
                sb2.append(string);
                sb2.append("/> node in the manifest file to <uses-permission android:name=\"");
                sb2.append(permissionInfo.name);
                sb2.append("\" android:usesPermissionFlags=\"neverForLocation\" ");
                sb2.append(string);
                sb2.append("/> node, if your app need use ");
                sb2.append(permissionInfo.name);
                sb2.append(" to get physical location, also need to add ");
                sb2.append("android.permission.ACCESS_FINE_LOCATION");
                sb2.append(" permissions");
                throw new IllegalArgumentException(sb2.toString());
            }
        }
    }
    
    static void checkNotificationListenerPermission(final List<String> list, final AndroidManifestInfo androidManifestInfo) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
            return;
        }
        if (androidManifestInfo == null) {
            return;
        }
        final List<AndroidManifestInfo.ServiceInfo> serviceInfoList = androidManifestInfo.serviceInfoList;
        for (int i = 0; i < serviceInfoList.size(); ++i) {
            if (TextUtils.equals((CharSequence)((AndroidManifestInfo.ServiceInfo)serviceInfoList.get(i)).permission, (CharSequence)"android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
                return;
            }
        }
        throw new IllegalArgumentException("No service registered permission attribute, please register <service android:permission=\"android.permission.BIND_NOTIFICATION_LISTENER_SERVICE\" > in AndroidManifest.xml");
    }
    
    static boolean checkPermissionArgument(final List<String> list, final boolean b) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            if (AndroidVersion.getAndroidVersionCode() > 33) {
                return true;
            }
            if (b) {
                final ArrayList list2 = new ArrayList();
                final Field[] declaredFields = Permission.class.getDeclaredFields();
                if (declaredFields.length == 0) {
                    return true;
                }
                while (i < declaredFields.length) {
                    final Field field = declaredFields[i];
                    if (String.class.equals(field.getType())) {
                        try {
                            ((List)list2).add((Object)field.get((Object)null));
                        }
                        catch (final IllegalAccessException ex) {
                            ex.printStackTrace();
                        }
                    }
                    ++i;
                }
                for (final String s : list) {
                    if (PermissionUtils.containsPermission((Collection<String>)list2, s)) {
                        continue;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("The ");
                    sb.append(s);
                    sb.append(" is not a dangerous permission or special permission, please do not request dynamically");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return true;
        }
        else {
            if (!b) {
                return false;
            }
            throw new IllegalArgumentException("The requested permission cannot be empty");
        }
    }
    
    static void checkPictureInPicturePermission(final Activity activity, final List<String> list, final AndroidManifestInfo androidManifestInfo) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.PICTURE_IN_PICTURE")) {
            return;
        }
        if (androidManifestInfo == null) {
            return;
        }
        final List<AndroidManifestInfo.ActivityInfo> activityInfoList = androidManifestInfo.activityInfoList;
        for (int i = 0; i < activityInfoList.size(); ++i) {
            if (((AndroidManifestInfo.ActivityInfo)activityInfoList.get(i)).supportsPictureInPicture) {
                return;
            }
        }
        final String replace = activity.getClass().getName().replace((CharSequence)activity.getPackageName(), (CharSequence)"");
        final StringBuilder sb = new StringBuilder();
        sb.append("No activity registered supportsPictureInPicture attribute, please register \n<activity android:name=\"");
        sb.append(replace);
        sb.append("\" android:supportsPictureInPicture=\"true\" > in AndroidManifest.xml");
        throw new IllegalArgumentException(sb.toString());
    }
    
    static void checkStoragePermission(final Context context, final List<String> list, final AndroidManifestInfo androidManifestInfo) {
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_IMAGES") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_VIDEO") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_AUDIO") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return;
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 33 && PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE")) {
            throw new IllegalArgumentException("When targetSdkVersion >= 33 should use android.permission.READ_MEDIA_IMAGES, android.permission.READ_MEDIA_VIDEO, android.permission.READ_MEDIA_AUDIO instead of android.permission.READ_EXTERNAL_STORAGE");
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_IMAGES")) {
            return;
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_MEDIA_LOCATION")) {
            return;
        }
        if (androidManifestInfo == null) {
            return;
        }
        final AndroidManifestInfo.ApplicationInfo applicationInfo = androidManifestInfo.applicationInfo;
        if (applicationInfo == null) {
            return;
        }
        final boolean scopedStorage = PermissionUtils.isScopedStorage(context);
        final int targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context);
        final boolean requestLegacyExternalStorage = applicationInfo.requestLegacyExternalStorage;
        if (targetSdkVersionCode >= 29 && !requestLegacyExternalStorage && (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE") || !scopedStorage)) {
            throw new IllegalStateException("Please register the android:requestLegacyExternalStorage=\"true\" attribute in the AndroidManifest.xml file, otherwise it will cause incompatibility with the old version");
        }
        if (targetSdkVersionCode >= 30 && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE") && !scopedStorage) {
            throw new IllegalArgumentException("The storage permission application is abnormal. If you have adapted the scope storage, please register the <meta-data android:name=\"ScopedStorage\" android:value=\"true\" /> attribute in the AndroidManifest.xml file. If there is no adaptation scope storage, please use android.permission.MANAGE_EXTERNAL_STORAGE to apply for permission");
        }
    }
    
    static void checkTargetSdkVersion(final Context context, final List<String> list) {
        int n;
        if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.POST_NOTIFICATIONS") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.NEARBY_WIFI_DEVICES") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BODY_SENSORS_BACKGROUND") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_IMAGES") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_VIDEO") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_AUDIO")) {
            if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BLUETOOTH_SCAN") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BLUETOOTH_CONNECT") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BLUETOOTH_ADVERTISE") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.SCHEDULE_EXACT_ALARM")) {
                if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    n = 30;
                }
                else if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_BACKGROUND_LOCATION") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACTIVITY_RECOGNITION") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_MEDIA_LOCATION")) {
                    if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCEPT_HANDOVER")) {
                        n = 28;
                    }
                    else if (!PermissionUtils.containsPermission((Collection<String>)list, "android.permission.REQUEST_INSTALL_PACKAGES") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ANSWER_PHONE_CALLS") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_PHONE_NUMBERS") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.PICTURE_IN_PICTURE")) {
                        n = 23;
                    }
                    else {
                        n = 26;
                    }
                }
                else {
                    n = 29;
                }
            }
            else {
                n = 31;
            }
        }
        else {
            n = 33;
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= n) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("The targetSdkVersion SDK must be ");
        sb.append(n);
        sb.append(" or more, if you do not want to upgrade targetSdkVersion, please apply with the old permissions");
        throw new RuntimeException(sb.toString());
    }
    
    static void optimizeDeprecatedPermission(final List<String> list) {
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.POST_NOTIFICATIONS") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.NOTIFICATION_SERVICE")) {
                list.add((Object)"android.permission.NOTIFICATION_SERVICE");
            }
            if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.NEARBY_WIFI_DEVICES") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_FINE_LOCATION")) {
                list.add((Object)"android.permission.ACCESS_FINE_LOCATION");
            }
            if ((PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_IMAGES") || PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_VIDEO") || PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_MEDIA_AUDIO")) && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE")) {
                list.add((Object)"android.permission.READ_EXTERNAL_STORAGE");
            }
        }
        if (!AndroidVersion.isAndroid12() && PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BLUETOOTH_SCAN") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACCESS_FINE_LOCATION")) {
            list.add((Object)"android.permission.ACCESS_FINE_LOCATION");
        }
        if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE")) {
            if (PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE") || PermissionUtils.containsPermission((Collection<String>)list, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                throw new IllegalArgumentException("If you have applied for MANAGE_EXTERNAL_STORAGE permissions, do not apply for the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permissions");
            }
            if (!AndroidVersion.isAndroid11()) {
                list.add((Object)"android.permission.READ_EXTERNAL_STORAGE");
                list.add((Object)"android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (!AndroidVersion.isAndroid10() && PermissionUtils.containsPermission((Collection<String>)list, "android.permission.ACTIVITY_RECOGNITION") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.BODY_SENSORS")) {
            list.add((Object)"android.permission.BODY_SENSORS");
        }
        if (!AndroidVersion.isAndroid8() && PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_PHONE_NUMBERS") && !PermissionUtils.containsPermission((Collection<String>)list, "android.permission.READ_PHONE_STATE")) {
            list.add((Object)"android.permission.READ_PHONE_STATE");
        }
    }
}
