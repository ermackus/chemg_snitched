package com.goodsrc.library.utils;

import android.content.SharedPreferences$Editor;
import android.content.Context;
import java.util.Locale;
import android.content.SharedPreferences;

public class LanguageSPUtil
{
    private static volatile LanguageSPUtil instance;
    private final String SP_NAME;
    private final String TAG_LANGUAGE;
    private final String TAG_SYSTEM_LANGUAGE;
    private final SharedPreferences mSharedPreferences;
    private Locale systemCurrentLocal;
    
    public LanguageSPUtil(final Context context) {
        this.SP_NAME = "language_setting";
        this.TAG_LANGUAGE = "language_select";
        this.TAG_SYSTEM_LANGUAGE = "system_language";
        this.systemCurrentLocal = Locale.getDefault();
        this.mSharedPreferences = context.getSharedPreferences("language_setting", 0);
    }
    
    public static LanguageSPUtil getInstance(final Context context) {
        if (LanguageSPUtil.instance == null) {
            synchronized (LanguageSPUtil.class) {
                if (LanguageSPUtil.instance == null) {
                    LanguageSPUtil.instance = new LanguageSPUtil(context);
                }
            }
        }
        return LanguageSPUtil.instance;
    }
    
    public int getSelectLanguage() {
        return this.mSharedPreferences.getInt("language_select", 0);
    }
    
    public Locale getSystemCurrentLocal() {
        if (!this.systemCurrentLocal.getLanguage().equals((Object)"zh")) {
            return Locale.ENGLISH;
        }
        return this.systemCurrentLocal;
    }
    
    public void saveLanguage(final int n) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putInt("language_select", n);
        edit.commit();
    }
    
    public void setSystemCurrentLocal(final Locale systemCurrentLocal) {
        this.systemCurrentLocal = systemCurrentLocal;
    }
}
