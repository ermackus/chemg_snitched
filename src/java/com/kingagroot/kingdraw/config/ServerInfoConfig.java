package com.kingagroot.kingdraw.config;

import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;

public class ServerInfoConfig
{
    public static final String TAG = "ServerInfoConfig";
    
    public static String getPrivacyPolicy() {
        String s;
        if (LanguageTool.getLanguageType(LibraryApplication.getContext()).equals((Object)LanguageTool.SER_ZH)) {
            s = "Ch_A_APP_YSXY";
        }
        else {
            s = "intl_A_APP_YSXY";
        }
        return s;
    }
    
    public static String getServiceAgreement() {
        String s;
        if (LanguageTool.getLanguageType(LibraryApplication.getContext()).equals((Object)LanguageTool.SER_ZH)) {
            s = "Ch_A_APP_FFXY";
        }
        else {
            s = "intl_A_APP_FFXY";
        }
        return s;
    }
    
    public static String getVipAgreement() {
        return "intl_A_ALL_HYXY";
    }
    
    public static String getVipAutoAgreement() {
        String s;
        if (LanguageTool.getLanguageType(LibraryApplication.getContext()).equals((Object)LanguageTool.SER_ZH)) {
            s = "Ch_A_app_ZDXF";
        }
        else {
            s = "intl_A_app_ZDXF";
        }
        return s;
    }
}
