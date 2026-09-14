package com.goodsrc.library.utils;

import java.util.Locale;
import android.content.Context;

public class LanguageTool
{
    public static String SER_EN = "en-us";
    public static String SER_ZH = "zh-cn";
    
    public static String getLanguageType(final Context context) {
        final int selectLanguage = LanguageSPUtil.getInstance(context).getSelectLanguage();
        String s;
        if (selectLanguage != 0) {
            if (selectLanguage != 1) {
                if (selectLanguage != 2) {
                    s = localLanguage(context);
                }
                else {
                    s = LanguageTool.SER_EN;
                }
            }
            else {
                s = LanguageTool.SER_ZH;
            }
        }
        else {
            s = localLanguage(context);
        }
        return s;
    }
    
    private static String localLanguage(final Context context) {
        String s;
        if (LocalLanguageManageUtil.getSystemLocale(context).getLanguage().equals((Object)Locale.CHINESE.getLanguage())) {
            s = LanguageTool.SER_ZH;
        }
        else {
            s = LanguageTool.SER_EN;
        }
        return s;
    }
}
