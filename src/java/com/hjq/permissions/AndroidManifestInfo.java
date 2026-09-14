package com.hjq.permissions;

import java.util.ArrayList;
import java.util.List;

final class AndroidManifestInfo
{
    final List<ActivityInfo> activityInfoList;
    ApplicationInfo applicationInfo;
    String packageName;
    final List<PermissionInfo> permissionInfoList;
    final List<ServiceInfo> serviceInfoList;
    UsesSdkInfo usesSdkInfo;
    
    AndroidManifestInfo() {
        this.permissionInfoList = (List<PermissionInfo>)new ArrayList();
        this.activityInfoList = (List<ActivityInfo>)new ArrayList();
        this.serviceInfoList = (List<ServiceInfo>)new ArrayList();
    }
    
    static final class ActivityInfo
    {
        public String name;
        public boolean supportsPictureInPicture;
    }
    
    static final class ApplicationInfo
    {
        public String name;
        public boolean requestLegacyExternalStorage;
    }
    
    static final class PermissionInfo
    {
        private static final int REQUESTED_PERMISSION_NEVER_FOR_LOCATION = 65536;
        public int maxSdkVersion;
        public String name;
        public int usesPermissionFlags;
        
        public boolean neverForLocation() {
            return (this.usesPermissionFlags & 0x10000) != 0x0;
        }
    }
    
    static final class ServiceInfo
    {
        public String name;
        public String permission;
    }
    
    static final class UsesSdkInfo
    {
        public int minSdkVersion;
    }
}
