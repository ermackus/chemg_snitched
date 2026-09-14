package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;

public final class WXAppLaunchData
{
    public static final String ACTION_HANDLE_WXAPPLAUNCH = ".ACTION_HANDLE_WXAPPLAUNCH";
    public static final String ACTION_HANDLE_WXAPP_RESULT = ".ACTION_HANDLE_WXAPP_RESULT";
    public static final String ACTION_HANDLE_WXAPP_SHOW = ".ACTION_HANDLE_WXAPP_SHOW";
    public int launchType;
    public String message;
    
    public static class Builder
    {
        public static WXAppLaunchData fromBundle(final Bundle bundle) {
            final WXAppLaunchData wxAppLaunchData = new WXAppLaunchData();
            wxAppLaunchData.launchType = bundle.getInt("_wxapplaunchdata_launchType");
            wxAppLaunchData.message = bundle.getString("_wxapplaunchdata_message");
            return wxAppLaunchData;
        }
        
        public static Bundle toBundle(final WXAppLaunchData wxAppLaunchData) {
            final Bundle bundle = new Bundle();
            bundle.putInt("_wxapplaunchdata_launchType", wxAppLaunchData.launchType);
            bundle.putString("_wxapplaunchdata_message", wxAppLaunchData.message);
            return bundle;
        }
    }
}
