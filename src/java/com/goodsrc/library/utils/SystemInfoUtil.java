package com.goodsrc.library.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import android.os.Environment;
import java.util.Properties;
import android.text.TextUtils;
import android.os.Build$VERSION;

public class SystemInfoUtil
{
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_FLYME = "sys_flyme";
    public static final String SYS_MIUI = "sys_miui";
    private static SystemInfo systemInfoInstance;
    
    private static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }
    
    private static void getSystem(final SystemInfo systemInfo) {
        try {
            if (Build$VERSION.SDK_INT > 25) {
                if (TextUtils.isEmpty((CharSequence)getSystemProperty("ro.miui.ui.version.code", "")) && TextUtils.isEmpty((CharSequence)getSystemProperty("ro.miui.ui.version.name", "")) && TextUtils.isEmpty((CharSequence)getSystemProperty("ro.miui.internal.storage", ""))) {
                    if (TextUtils.isEmpty((CharSequence)getSystemProperty("ro.build.hw_emui_api_level", "")) && TextUtils.isEmpty((CharSequence)getSystemProperty("ro.build.version.emui", "")) && TextUtils.isEmpty((CharSequence)getSystemProperty("ro.confg.hw_systemversion", ""))) {
                        if (getMeizuFlymeOSFlag().toLowerCase().contains((CharSequence)"flyme")) {
                            systemInfo.os = "sys_flyme";
                        }
                    }
                    else {
                        systemInfo.os = "sys_emui";
                    }
                }
                else {
                    systemInfo.os = "sys_miui";
                }
            }
            else {
                final Properties properties = new Properties();
                properties.load((InputStream)new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                if (properties.getProperty("ro.miui.ui.version.code", (String)null) == null && properties.getProperty("ro.miui.ui.version.name", (String)null) == null && properties.getProperty("ro.miui.internal.storage", (String)null) == null) {
                    if (properties.getProperty("ro.build.hw_emui_api_level", (String)null) == null && properties.getProperty("ro.build.version.emui", (String)null) == null && properties.getProperty("ro.confg.hw_systemversion", (String)null) == null) {
                        if (getMeizuFlymeOSFlag().toLowerCase().contains((CharSequence)"flyme")) {
                            systemInfo.os = "sys_flyme";
                            systemInfo.versionCode = 0;
                            systemInfo.versionName = "unknown";
                        }
                    }
                    else {
                        systemInfo.os = "sys_emui";
                        systemInfo.versionCode = Integer.valueOf(properties.getProperty("ro.build.hw_emui_api_level", "0"));
                        systemInfo.versionName = properties.getProperty("ro.build.version.emui", "unknown");
                    }
                }
                else {
                    systemInfo.os = "sys_miui";
                    systemInfo.versionCode = Integer.valueOf(properties.getProperty("ro.miui.ui.version.code", "0"));
                    systemInfo.versionName = properties.getProperty("ro.miui.ui.version.name", "V0");
                }
            }
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static SystemInfo getSystemInfo() {
        if (SystemInfoUtil.systemInfoInstance == null) {
            synchronized (SystemInfoUtil.class) {
                if (SystemInfoUtil.systemInfoInstance == null) {
                    getSystem(SystemInfoUtil.systemInfoInstance = new SystemInfo());
                }
            }
        }
        return SystemInfoUtil.systemInfoInstance;
    }
    
    private static String getSystemProperty(String s, final String s2) {
        try {
            final Class<?> forName = Class.forName("android.os.SystemProperties");
            s = (String)forName.getMethod("get", String.class, String.class).invoke((Object)forName, new Object[] { s, s2 });
            return s;
        }
        catch (final Exception ex) {
            return s2;
        }
    }
    
    public static class SystemInfo
    {
        private String os;
        private int versionCode;
        private String versionName;
        
        public SystemInfo() {
            this.os = "android";
            this.versionName = Build$VERSION.RELEASE;
            this.versionCode = Build$VERSION.SDK_INT;
        }
        
        public String getOs() {
            return this.os;
        }
        
        public int getVersionCode() {
            return this.versionCode;
        }
        
        public String getVersionName() {
            return this.versionName;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("SystemInfo{os='");
            sb.append(this.os);
            sb.append('\'');
            sb.append(", versionName='");
            sb.append(this.versionName);
            sb.append('\'');
            sb.append(", versionCode=");
            sb.append(this.versionCode);
            sb.append('}');
            return sb.toString();
        }
    }
}
