package com.hjq.permissions;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import android.text.TextUtils;
import android.content.Context;
import android.content.res.XmlResourceParser;

final class AndroidManifestParser
{
    private static final String ANDROID_MANIFEST_FILE_NAME = "AndroidManifest.xml";
    private static final String ANDROID_NAMESPACE_URI = "http://schemas.android.com/apk/res/android";
    private static final String ATTR_MAX_SDK_VERSION = "maxSdkVersion";
    private static final String ATTR_MIN_SDK_VERSION = "minSdkVersion";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PACKAGE = "package";
    private static final String ATTR_PERMISSION = "permission";
    private static final String ATTR_REQUEST_LEGACY_EXTERNAL_STORAGE = "requestLegacyExternalStorage";
    private static final String ATTR_SUPPORTS_PICTURE_IN_PICTURE = "supportsPictureInPicture";
    private static final String ATTR_USES_PERMISSION_FLAGS = "usesPermissionFlags";
    private static final String TAG_ACTIVITY = "activity";
    private static final String TAG_ACTIVITY_ALIAS = "activity-alias";
    private static final String TAG_APPLICATION = "application";
    private static final String TAG_MANIFEST = "manifest";
    private static final String TAG_SERVICE = "service";
    private static final String TAG_USES_PERMISSION = "uses-permission";
    private static final String TAG_USES_PERMISSION_SDK_23 = "uses-permission-sdk-23";
    private static final String TAG_USES_PERMISSION_SDK_M = "uses-permission-sdk-m";
    private static final String TAG_USES_SDK = "uses-sdk";
    
    private static AndroidManifestInfo.ActivityInfo parseActivityFromXml(final XmlResourceParser xmlResourceParser) {
        final AndroidManifestInfo.ActivityInfo activityInfo = new AndroidManifestInfo.ActivityInfo();
        activityInfo.name = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
        activityInfo.supportsPictureInPicture = xmlResourceParser.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "supportsPictureInPicture", false);
        return activityInfo;
    }
    
    static AndroidManifestInfo parseAndroidManifest(final Context context, final int n) throws IOException, XmlPullParserException {
        final AndroidManifestInfo androidManifestInfo = new AndroidManifestInfo();
        final XmlResourceParser openXmlResourceParser = context.getAssets().openXmlResourceParser(n, "AndroidManifest.xml");
        do {
            if (openXmlResourceParser.getEventType() != 2) {
                continue;
            }
            final String name = openXmlResourceParser.getName();
            if (TextUtils.equals((CharSequence)"manifest", (CharSequence)name)) {
                androidManifestInfo.packageName = openXmlResourceParser.getAttributeValue((String)null, "package");
            }
            if (TextUtils.equals((CharSequence)"uses-sdk", (CharSequence)name)) {
                androidManifestInfo.usesSdkInfo = parseUsesSdkFromXml(openXmlResourceParser);
            }
            if (TextUtils.equals((CharSequence)"uses-permission", (CharSequence)name) || TextUtils.equals((CharSequence)"uses-permission-sdk-23", (CharSequence)name) || TextUtils.equals((CharSequence)"uses-permission-sdk-m", (CharSequence)name)) {
                androidManifestInfo.permissionInfoList.add((Object)parsePermissionFromXml(openXmlResourceParser));
            }
            if (TextUtils.equals((CharSequence)"application", (CharSequence)name)) {
                androidManifestInfo.applicationInfo = parseApplicationFromXml(openXmlResourceParser);
            }
            if (TextUtils.equals((CharSequence)"activity", (CharSequence)name) || TextUtils.equals((CharSequence)"activity-alias", (CharSequence)name)) {
                androidManifestInfo.activityInfoList.add((Object)parseActivityFromXml(openXmlResourceParser));
            }
            if (!TextUtils.equals((CharSequence)"service", (CharSequence)name)) {
                continue;
            }
            androidManifestInfo.serviceInfoList.add((Object)parseServerFromXml(openXmlResourceParser));
        } while (openXmlResourceParser.next() != 1);
        openXmlResourceParser.close();
        return androidManifestInfo;
    }
    
    private static AndroidManifestInfo.ApplicationInfo parseApplicationFromXml(final XmlResourceParser xmlResourceParser) {
        final AndroidManifestInfo.ApplicationInfo applicationInfo = new AndroidManifestInfo.ApplicationInfo();
        applicationInfo.name = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
        applicationInfo.requestLegacyExternalStorage = xmlResourceParser.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "requestLegacyExternalStorage", false);
        return applicationInfo;
    }
    
    private static AndroidManifestInfo.PermissionInfo parsePermissionFromXml(final XmlResourceParser xmlResourceParser) {
        final AndroidManifestInfo.PermissionInfo permissionInfo = new AndroidManifestInfo.PermissionInfo();
        permissionInfo.name = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
        permissionInfo.maxSdkVersion = xmlResourceParser.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxSdkVersion", Integer.MAX_VALUE);
        permissionInfo.usesPermissionFlags = xmlResourceParser.getAttributeIntValue("http://schemas.android.com/apk/res/android", "usesPermissionFlags", 0);
        return permissionInfo;
    }
    
    private static AndroidManifestInfo.ServiceInfo parseServerFromXml(final XmlResourceParser xmlResourceParser) {
        final AndroidManifestInfo.ServiceInfo serviceInfo = new AndroidManifestInfo.ServiceInfo();
        serviceInfo.name = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
        serviceInfo.permission = xmlResourceParser.getAttributeValue("http://schemas.android.com/apk/res/android", "permission");
        return serviceInfo;
    }
    
    private static AndroidManifestInfo.UsesSdkInfo parseUsesSdkFromXml(final XmlResourceParser xmlResourceParser) {
        final AndroidManifestInfo.UsesSdkInfo usesSdkInfo = new AndroidManifestInfo.UsesSdkInfo();
        usesSdkInfo.minSdkVersion = xmlResourceParser.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minSdkVersion", 0);
        return usesSdkInfo;
    }
}
