package com.luck.picture.lib.language;

import android.util.DisplayMetrics;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import com.luck.picture.lib.utils.SpUtils;
import java.util.Locale;
import android.content.Context;

public class PictureLanguageUtils
{
    private static final String KEY_LOCALE = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";
    
    private PictureLanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    
    private static void applyLanguage(final Context context, final Locale locale) {
        applyLanguage(context, locale, false);
    }
    
    private static void applyLanguage(final Context context, final Locale locale, final boolean b) {
        if (b) {
            SpUtils.putString(context, "KEY_LOCALE", "VALUE_FOLLOW_SYSTEM");
        }
        else {
            final String language = locale.getLanguage();
            final String country = locale.getCountry();
            final StringBuilder sb = new StringBuilder();
            sb.append(language);
            sb.append("$");
            sb.append(country);
            SpUtils.putString(context, "KEY_LOCALE", sb.toString());
        }
        updateLanguage(context, locale);
    }
    
    private static boolean equals(final CharSequence charSequence, final CharSequence obj) {
        if (charSequence == obj) {
            return true;
        }
        if (charSequence != null && obj != null) {
            final int length = charSequence.length();
            if (length == obj.length()) {
                if (charSequence instanceof String && obj instanceof String) {
                    return charSequence.equals(obj);
                }
                for (int i = 0; i < length; ++i) {
                    if (charSequence.charAt(i) != obj.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public static void setAppLanguage(final Context context, final int n, final int n2) {
        final WeakReference weakReference = new WeakReference((Object)context);
        if (n >= 0) {
            applyLanguage((Context)weakReference.get(), LocaleTransform.getLanguage(n));
        }
        else if (n2 >= 0) {
            applyLanguage((Context)weakReference.get(), LocaleTransform.getLanguage(n2));
        }
        else {
            setDefaultLanguage((Context)weakReference.get());
        }
    }
    
    private static void setDefaultLanguage(final Context context) {
        final Resources resources = context.getResources();
        final Configuration configuration = resources.getConfiguration();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(Locale.getDefault());
        context.createConfigurationContext(configuration);
        resources.updateConfiguration(configuration, displayMetrics);
    }
    
    private static void updateLanguage(final Context context, final Locale locale) {
        final Resources resources = context.getResources();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale2 = configuration.locale;
        if (equals((CharSequence)locale2.getLanguage(), (CharSequence)locale.getLanguage()) && equals((CharSequence)locale2.getCountry(), (CharSequence)locale.getCountry())) {
            return;
        }
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(locale);
        context.createConfigurationContext(configuration);
        resources.updateConfiguration(configuration, displayMetrics);
    }
}
