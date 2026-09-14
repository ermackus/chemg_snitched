package com.hjq.permissions;

import android.provider.Settings$Secure;
import java.util.Iterator;
import android.content.ComponentName;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;

final class NotificationListenerPermissionCompat
{
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    
    static Intent getPermissionIntent(final Context context) {
        final boolean android11 = AndroidVersion.isAndroid11();
        Intent intent2;
        final Intent intent = intent2 = null;
        if (android11) {
            final AndroidManifestInfo androidManifestInfo = PermissionUtils.getAndroidManifestInfo(context);
            AndroidManifestInfo.ServiceInfo serviceInfo2 = null;
            Label_0088: {
                if (androidManifestInfo != null) {
                    final Iterator iterator = androidManifestInfo.serviceInfoList.iterator();
                    AndroidManifestInfo.ServiceInfo serviceInfo = null;
                    while (true) {
                        serviceInfo2 = serviceInfo;
                        if (!iterator.hasNext()) {
                            break Label_0088;
                        }
                        final AndroidManifestInfo.ServiceInfo serviceInfo3 = (AndroidManifestInfo.ServiceInfo)iterator.next();
                        if (!TextUtils.equals((CharSequence)serviceInfo3.permission, (CharSequence)"android.permission.BIND_NOTIFICATION_LISTENER_SERVICE")) {
                            continue;
                        }
                        if (serviceInfo != null) {
                            break;
                        }
                        serviceInfo = serviceInfo3;
                    }
                }
                serviceInfo2 = null;
            }
            intent2 = intent;
            if (serviceInfo2 != null) {
                intent2 = new Intent("android.settings.NOTIFICATION_LISTENER_DETAIL_SETTINGS");
                intent2.putExtra("android.provider.extra.NOTIFICATION_LISTENER_COMPONENT_NAME", new ComponentName(context, serviceInfo2.name).flattenToString());
                if (!PermissionUtils.areActivityIntent(context, intent2)) {
                    intent2 = intent;
                }
            }
        }
        Intent intent3;
        if ((intent3 = intent2) == null) {
            Intent intent4;
            if (AndroidVersion.isAndroid5_1()) {
                intent4 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            else {
                intent4 = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            intent3 = intent4;
        }
        Intent applicationDetailsIntent = intent3;
        if (!PermissionUtils.areActivityIntent(context, intent3)) {
            applicationDetailsIntent = PermissionIntentManager.getApplicationDetailsIntent(context);
        }
        return applicationDetailsIntent;
    }
    
    static boolean isGrantedPermission(final Context context) {
        if (!AndroidVersion.isAndroid4_3()) {
            return true;
        }
        final String string = Settings$Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty((CharSequence)string)) {
            return false;
        }
        final String[] split = string.split(":");
        for (int length = split.length, i = 0; i < length; ++i) {
            final ComponentName unflattenFromString = ComponentName.unflattenFromString(split[i]);
            if (TextUtils.equals((CharSequence)unflattenFromString.getPackageName(), (CharSequence)context.getPackageName())) {
                final String className = unflattenFromString.getClassName();
                try {
                    Class.forName(className);
                    return true;
                }
                catch (final ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}
