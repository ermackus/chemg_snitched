package com.goodsrc.library.utils;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.os.LocaleList;
import android.os.Build$VERSION;
import java.util.Locale;
import android.content.Context;

public class LocalLanguageManageUtil
{
    public static final int LT_AUTO = 0;
    public static final int LT_CN = 1;
    public static final int LT_EN = 2;
    private static final String TAG = "LocalLanguageManageUtil";
    
    public static Locale getSetLanguageLocale(final Context context) {
        final int selectLanguage = LanguageSPUtil.getInstance(context).getSelectLanguage();
        if (selectLanguage == 0) {
            return getSystemLocale(context);
        }
        if (selectLanguage == 1) {
            return Locale.CHINA;
        }
        if (selectLanguage != 2) {
            return getSystemLocale(context);
        }
        return Locale.ENGLISH;
    }
    
    public static Locale getSystemLocale(final Context context) {
        return LanguageSPUtil.getInstance(context).getSystemCurrentLocal();
    }
    
    public static void onConfigurationChanged(final Context context) {
        saveSystemCurrentLanguage(context);
        setLocal(context);
        setApplicationLanguage(context);
    }
    
    public static void saveSelectLanguage(final Context applicationLanguage, final int n) {
        LanguageSPUtil.getInstance(applicationLanguage).saveLanguage(n);
        setApplicationLanguage(applicationLanguage);
    }
    
    public static void saveSystemCurrentLanguage(final Context context) {
        Locale systemCurrentLocal;
        if (Build$VERSION.SDK_INT >= 24) {
            systemCurrentLocal = LocaleList.getDefault().get(0);
        }
        else {
            systemCurrentLocal = Locale.getDefault();
        }
        LanguageSPUtil.getInstance(context).setSystemCurrentLocal(systemCurrentLocal);
    }
    
    public static void setApplicationLanguage(final Context context) {
        final Resources resources = context.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        final Configuration configuration = resources.getConfiguration();
        final Locale setLanguageLocale = getSetLanguageLocale(context);
        configuration.locale = setLanguageLocale;
        if (Build$VERSION.SDK_INT >= 24) {
            final LocaleList list = new LocaleList(new Locale[] { setLanguageLocale });
            LocaleList.setDefault(list);
            configuration.setLocales(list);
            context.getApplicationContext().createConfigurationContext(configuration);
            Locale.setDefault(setLanguageLocale);
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }
    
    public static Context setLocal(final Context context) {
        return updateResources(context, getSetLanguageLocale(context));
    }
    
    private static Context updateResources(Context configurationContext, final Locale locale) {
        Locale.setDefault(locale);
        final Resources resources = configurationContext.getResources();
        final Configuration configuration = new Configuration(resources.getConfiguration());
        if (Build$VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
            configurationContext = configurationContext.createConfigurationContext(configuration);
        }
        else {
            configuration.locale = locale;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return configurationContext;
    }
}
