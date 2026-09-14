package com.kingagroot.kingdraw.model;

import android.graphics.drawable.Drawable;

public class AppModel
{
    Drawable AppIcon;
    String AppLauncherClassName;
    String AppPkgName;
    String appName;
    boolean isMore;
    
    public Drawable getAppIcon() {
        return this.AppIcon;
    }
    
    public String getAppLauncherClassName() {
        return this.AppLauncherClassName;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public String getAppPkgName() {
        return this.AppPkgName;
    }
    
    public boolean isMore() {
        return this.isMore;
    }
    
    public void setAppIcon(final Drawable appIcon) {
        this.AppIcon = appIcon;
    }
    
    public void setAppLauncherClassName(final String appLauncherClassName) {
        this.AppLauncherClassName = appLauncherClassName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public void setAppPkgName(final String appPkgName) {
        this.AppPkgName = appPkgName;
    }
    
    public void setMore(final boolean isMore) {
        this.isMore = isMore;
    }
}
