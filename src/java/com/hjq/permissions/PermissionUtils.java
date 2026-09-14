package com.hjq.permissions;

import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.List;
import android.net.Uri;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.text.TextUtils;
import android.content.res.AssetManager;
import java.lang.reflect.Method;
import android.content.ContextWrapper;
import android.app.Activity;
import java.util.Iterator;
import android.content.pm.ApplicationInfo;
import java.lang.reflect.InvocationTargetException;
import android.app.AppOpsManager;
import java.util.Collection;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$ResolveInfoFlags;
import android.content.Intent;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;

final class PermissionUtils
{
    private static final Handler HANDLER;
    
    static {
        HANDLER = new Handler(Looper.getMainLooper());
    }
    
    static boolean areActivityIntent(final Context context, final Intent intent) {
        if (intent == null) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        if (AndroidVersion.isAndroid13()) {
            return packageManager.queryIntentActivities(intent, PackageManager$ResolveInfoFlags.of(65536L)).isEmpty() ^ true;
        }
        return packageManager.queryIntentActivities(intent, 65536).isEmpty() ^ true;
    }
    
    static <T> ArrayList<T> asArrayList(final T... array) {
        final int n = 0;
        int length;
        if (array != null) {
            length = array.length;
        }
        else {
            length = 0;
        }
        final ArrayList list = new ArrayList(length);
        if (array != null) {
            if (array.length != 0) {
                for (int length2 = array.length, i = n; i < length2; ++i) {
                    list.add((Object)array[i]);
                }
            }
        }
        return (ArrayList<T>)list;
    }
    
    @SafeVarargs
    static <T> ArrayList<T> asArrayLists(final T[]... array) {
        final ArrayList list = new ArrayList();
        if (array != null) {
            if (array.length != 0) {
                for (int length = array.length, i = 0; i < length; ++i) {
                    list.addAll((Collection)asArrayList(array[i]));
                }
            }
        }
        return (ArrayList<T>)list;
    }
    
    static boolean checkOpNoThrow(final Context context, final String s) {
        final AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService("appops");
        int n;
        if (AndroidVersion.isAndroid10()) {
            n = appOpsManager.unsafeCheckOpNoThrow(s, context.getApplicationInfo().uid, context.getPackageName());
        }
        else {
            n = appOpsManager.checkOpNoThrow(s, context.getApplicationInfo().uid, context.getPackageName());
        }
        return n == 0;
    }
    
    static boolean checkOpNoThrow(Context packageName, final String s, int n) {
        final AppOpsManager appOpsManager = (AppOpsManager)packageName.getSystemService("appops");
        final ApplicationInfo applicationInfo = packageName.getApplicationInfo();
        packageName = (Context)packageName.getApplicationContext().getPackageName();
        final int uid = applicationInfo.uid;
        boolean b = true;
        try {
            final Class<?> forName = Class.forName(AppOpsManager.class.getName());
            try {
                n = (int)forName.getDeclaredField(s).get((Object)Integer.class);
            }
            catch (final NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            n = (int)forName.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke((Object)appOpsManager, new Object[] { n, uid, packageName });
            if (n != 0) {
                b = false;
            }
            return b;
        }
        catch (final ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | RuntimeException ex2) {
            return b;
        }
    }
    
    static boolean checkSelfPermission(final Context context, final String s) {
        return context.checkSelfPermission(s) == 0;
    }
    
    static boolean containsPermission(final Collection<String> collection, final String s) {
        if (collection.isEmpty()) {
            return false;
        }
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (equalsPermission((String)iterator.next(), s)) {
                return true;
            }
        }
        return false;
    }
    
    static boolean equalsPermission(final String s, final String s2) {
        int i = s.length();
        if (i != s2.length()) {
            return false;
        }
        --i;
        while (i >= 0) {
            if (s.charAt(i) != s2.charAt(i)) {
                return false;
            }
            --i;
        }
        return true;
    }
    
    static Activity findActivity(Context baseContext) {
        while (!(baseContext instanceof Activity)) {
            if (!(baseContext instanceof ContextWrapper) || (baseContext = ((ContextWrapper)baseContext).getBaseContext()) == null) {
                return null;
            }
        }
        return (Activity)baseContext;
    }
    
    static int findApkPathCookie(final Context context, final String s) {
        final AssetManager assets = context.getAssets();
        try {
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 28 && AndroidVersion.getAndroidVersionCode() >= 28 && AndroidVersion.getAndroidVersionCode() < 30) {
                final Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                declaredMethod.setAccessible(true);
                final Method method = (Method)declaredMethod.invoke((Object)AssetManager.class, new Object[] { "findCookieForPath", { String.class } });
                if (method != null) {
                    method.setAccessible(true);
                    final Integer n = (Integer)method.invoke((Object)context.getAssets(), new Object[] { s });
                    if (n != null) {
                        return n;
                    }
                }
            }
            final Integer n2 = (Integer)assets.getClass().getDeclaredMethod("addAssetPath", String.class).invoke((Object)assets, new Object[] { s });
            if (n2 != null) {
                return n2;
            }
        }
        catch (final InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (final IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        catch (final NoSuchMethodException ex3) {
            ex3.printStackTrace();
        }
        return 0;
    }
    
    static AndroidManifestInfo getAndroidManifestInfo(final Context context) {
        final int apkPathCookie = findApkPathCookie(context, context.getApplicationInfo().sourceDir);
        final AndroidManifestInfo androidManifestInfo = null;
        final AndroidManifestInfo androidManifestInfo2 = null;
        if (apkPathCookie == 0) {
            return null;
        }
        AndroidManifestInfo androidManifestInfo3;
        try {
            final AndroidManifestInfo androidManifest = AndroidManifestParser.parseAndroidManifest(context, apkPathCookie);
            try {
                if (!TextUtils.equals((CharSequence)context.getPackageName(), (CharSequence)androidManifest.packageName)) {
                    return null;
                }
                return androidManifest;
            }
            catch (final XmlPullParserException ex) {
                androidManifestInfo3 = androidManifest;
            }
            catch (final IOException ex2) {
                androidManifestInfo3 = androidManifest;
            }
        }
        catch (final XmlPullParserException ex) {
            androidManifestInfo3 = androidManifestInfo2;
        }
        catch (final IOException ex2) {
            androidManifestInfo3 = androidManifestInfo;
        }
        final IOException ex2;
        ex2.printStackTrace();
        return androidManifestInfo3;
    }
    
    static Uri getPackageNameUri(final Context context) {
        final StringBuilder sb = new StringBuilder();
        sb.append("package:");
        sb.append(context.getPackageName());
        return Uri.parse(sb.toString());
    }
    
    static Intent getSmartPermissionIntent(final Context context, final List<String> list) {
        if (list == null || list.isEmpty()) {
            return PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        if (!PermissionApi.containsSpecialPermission(list)) {
            if (list.size() == 1) {
                return PermissionApi.getPermissionIntent(context, (String)list.get(0));
            }
            return PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        else {
            final int size = list.size();
            if (size != 1) {
                if (size != 2) {
                    if (size == 3) {
                        if (AndroidVersion.isAndroid11() && containsPermission((Collection<String>)list, "android.permission.MANAGE_EXTERNAL_STORAGE") && containsPermission((Collection<String>)list, "android.permission.READ_EXTERNAL_STORAGE") && containsPermission((Collection<String>)list, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                            return PermissionApi.getPermissionIntent(context, "android.permission.MANAGE_EXTERNAL_STORAGE");
                        }
                    }
                }
                else if (!AndroidVersion.isAndroid13() && containsPermission((Collection<String>)list, "android.permission.NOTIFICATION_SERVICE") && containsPermission((Collection<String>)list, "android.permission.POST_NOTIFICATIONS")) {
                    return PermissionApi.getPermissionIntent(context, "android.permission.NOTIFICATION_SERVICE");
                }
                return PermissionIntentManager.getApplicationDetailsIntent(context);
            }
            return PermissionApi.getPermissionIntent(context, (String)list.get(0));
        }
    }
    
    static boolean isActivityReverse(final Activity activity) {
        int n;
        if (AndroidVersion.isAndroid11()) {
            n = activity.getDisplay().getRotation();
        }
        else {
            n = activity.getWindowManager().getDefaultDisplay().getRotation();
        }
        return n == 2 || n == 3;
    }
    
    static boolean isDebugMode(final Context context) {
        return (context.getApplicationInfo().flags & 0x2) != 0x0;
    }
    
    static boolean isScopedStorage(final Context context) {
        try {
            final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (metaData != null && metaData.containsKey("ScopedStorage")) {
                return Boolean.parseBoolean(String.valueOf(metaData.get("ScopedStorage")));
            }
        }
        catch (final PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    static boolean isSpecialPermission(final String s) {
        return equalsPermission(s, "android.permission.MANAGE_EXTERNAL_STORAGE") || equalsPermission(s, "android.permission.REQUEST_INSTALL_PACKAGES") || equalsPermission(s, "android.permission.SYSTEM_ALERT_WINDOW") || equalsPermission(s, "android.permission.WRITE_SETTINGS") || equalsPermission(s, "android.permission.NOTIFICATION_SERVICE") || equalsPermission(s, "android.permission.PACKAGE_USAGE_STATS") || equalsPermission(s, "android.permission.SCHEDULE_EXACT_ALARM") || equalsPermission(s, "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE") || equalsPermission(s, "android.permission.ACCESS_NOTIFICATION_POLICY") || equalsPermission(s, "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS") || equalsPermission(s, "android.permission.BIND_VPN_SERVICE") || equalsPermission(s, "android.permission.PICTURE_IN_PICTURE");
    }
    
    static void lockActivityOrientation(final Activity activity) {
        try {
            final int orientation = activity.getResources().getConfiguration().orientation;
            int requestedOrientation = 1;
            if (orientation != 1) {
                if (orientation == 2) {
                    int requestedOrientation2;
                    if (isActivityReverse(activity)) {
                        requestedOrientation2 = 8;
                    }
                    else {
                        requestedOrientation2 = 0;
                    }
                    activity.setRequestedOrientation(requestedOrientation2);
                }
            }
            else {
                if (isActivityReverse(activity)) {
                    requestedOrientation = 9;
                }
                activity.setRequestedOrientation(requestedOrientation);
            }
        }
        catch (final IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
    
    static void optimizePermissionResults(final Activity activity, final String[] array, final int[] array2) {
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final boolean specialPermission = PermissionApi.isSpecialPermission(s);
            final boolean android13 = AndroidVersion.isAndroid13();
            final int n = 1;
            boolean b = specialPermission;
            if (android13) {
                b = specialPermission;
                if (AndroidVersion.getTargetSdkVersionCode((Context)activity) >= 33) {
                    b = specialPermission;
                    if (equalsPermission(s, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                        b = true;
                    }
                }
            }
            boolean b2 = b;
            Label_0151: {
                if (!AndroidVersion.isAndroid13()) {
                    if (!equalsPermission(s, "android.permission.POST_NOTIFICATIONS") && !equalsPermission(s, "android.permission.NEARBY_WIFI_DEVICES") && !equalsPermission(s, "android.permission.BODY_SENSORS_BACKGROUND") && !equalsPermission(s, "android.permission.READ_MEDIA_IMAGES") && !equalsPermission(s, "android.permission.READ_MEDIA_VIDEO")) {
                        b2 = b;
                        if (!equalsPermission(s, "android.permission.READ_MEDIA_AUDIO")) {
                            break Label_0151;
                        }
                    }
                    b2 = true;
                }
            }
            boolean b3 = b2;
            Label_0201: {
                if (!AndroidVersion.isAndroid12()) {
                    if (!equalsPermission(s, "android.permission.BLUETOOTH_SCAN") && !equalsPermission(s, "android.permission.BLUETOOTH_CONNECT")) {
                        b3 = b2;
                        if (!equalsPermission(s, "android.permission.BLUETOOTH_ADVERTISE")) {
                            break Label_0201;
                        }
                    }
                    b3 = true;
                }
            }
            boolean b4 = b3;
            Label_0251: {
                if (!AndroidVersion.isAndroid10()) {
                    if (!equalsPermission(s, "android.permission.ACCESS_BACKGROUND_LOCATION") && !equalsPermission(s, "android.permission.ACTIVITY_RECOGNITION")) {
                        b4 = b3;
                        if (!equalsPermission(s, "android.permission.ACCESS_MEDIA_LOCATION")) {
                            break Label_0251;
                        }
                    }
                    b4 = true;
                }
            }
            boolean b5 = b4;
            if (!AndroidVersion.isAndroid9()) {
                b5 = b4;
                if (equalsPermission(s, "android.permission.ACCEPT_HANDOVER")) {
                    b5 = true;
                }
            }
            int n2 = b5 ? 1 : 0;
            Label_0318: {
                if (!AndroidVersion.isAndroid8()) {
                    if (!equalsPermission(s, "android.permission.ANSWER_PHONE_CALLS")) {
                        n2 = (b5 ? 1 : 0);
                        if (!equalsPermission(s, "android.permission.READ_PHONE_NUMBERS")) {
                            break Label_0318;
                        }
                    }
                    n2 = 1;
                }
            }
            if (equalsPermission(s, "com.android.permission.GET_INSTALLED_APPS")) {
                n2 = n;
            }
            if (n2 != 0) {
                int n3;
                if (PermissionApi.isGrantedPermission((Context)activity, s)) {
                    n3 = 0;
                }
                else {
                    n3 = -1;
                }
                array2[i] = n3;
            }
        }
    }
    
    static void postActivityResult(final List<String> list, final Runnable runnable) {
        final boolean android11 = AndroidVersion.isAndroid11();
        final long n = 300L;
        long n2;
        if (android11) {
            n2 = 200L;
        }
        else {
            n2 = 300L;
        }
        if (!PhoneRomUtils.isEmui() && !PhoneRomUtils.isHarmonyOs()) {
            if (PhoneRomUtils.isMiui() && AndroidVersion.isAndroid11() && containsPermission((Collection<String>)list, "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
                n2 = 1000L;
            }
        }
        else if (AndroidVersion.isAndroid8()) {
            n2 = n;
        }
        else {
            n2 = 500L;
        }
        postDelayed(runnable, n2);
    }
    
    static void postDelayed(final Runnable runnable, final long n) {
        PermissionUtils.HANDLER.postDelayed(runnable, n);
    }
    
    static boolean shouldShowRequestPermissionRationale(final Activity activity, final String s) {
        if (AndroidVersion.getAndroidVersionCode() == 31) {
            PackageManager packageManager = null;
            try {
                packageManager = activity.getApplication().getPackageManager();
                return (boolean)PackageManager.class.getMethod("shouldShowRequestPermissionRationale", String.class).invoke((Object)packageManager, new Object[] { s });
            }
            catch (final IllegalAccessException packageManager) {}
            catch (final InvocationTargetException packageManager) {}
            catch (final NoSuchMethodException ex) {}
            ((ReflectiveOperationException)packageManager).printStackTrace();
        }
        return activity.shouldShowRequestPermissionRationale(s);
    }
}
