package com.goodsrc.library.core;

import com.goodsrc.library.utils.L;
import org.xutils.x$Ext;
import com.goodsrc.library.utils.SPUtil;
import com.goodsrc.library.utils.LanguageTool;
import android.text.TextUtils;
import android.content.Context;
import android.app.Application;

public class LibraryApplication
{
    private static String LANGUAGE;
    private static Application context;
    public static boolean isDebug = true;
    private static String token;
    
    public static Context getContext() {
        return (Context)LibraryApplication.context;
    }
    
    public static String getLanguage() {
        if (TextUtils.isEmpty((CharSequence)LibraryApplication.LANGUAGE)) {
            LibraryApplication.LANGUAGE = LanguageTool.getLanguageType((Context)LibraryApplication.context);
        }
        return LibraryApplication.LANGUAGE;
    }
    
    public static String getToken() {
        if (SPUtil.getBoolean("USER_DEVICE", "auto", false)) {
            return SPUtil.getString("USER_DEVICE", "token", "-");
        }
        if (TextUtils.isEmpty((CharSequence)LibraryApplication.token)) {
            LibraryApplication.token = "-";
        }
        return LibraryApplication.token;
    }
    
    public static void init(final Application context) {
        x$Ext.init(LibraryApplication.context = context);
        x$Ext.setDebug(true);
        L.setTag(context.getPackageName());
    }
    
    public static void setDebug(final boolean b) {
        x$Ext.setDebug(b);
        LibraryApplication.isDebug = b;
    }
    
    public static void setLanguage(final String language) {
        LibraryApplication.LANGUAGE = language;
    }
    
    public static void setToken(final boolean b, final String token) {
        LibraryApplication.token = token;
        if (b) {
            SPUtil.setString("USER_DEVICE", "token", token);
        }
        SPUtil.setBoolean("USER_DEVICE", "auto", b);
    }
}
