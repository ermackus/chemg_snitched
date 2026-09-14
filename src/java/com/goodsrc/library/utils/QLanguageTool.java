package com.goodsrc.library.utils;

import java.util.Locale;
import android.os.LocaleList;
import android.os.Build$VERSION;
import com.goodsrc.library.core.LibraryApplication;
import android.content.SharedPreferences$Editor;
import android.content.Context;

public class QLanguageTool
{
    private static final String SPKEY_LANGUAGE_TYPE = "spkey_language_type";
    private static QLanguageTool languageTool;
    private final Context context;
    private SharedPreferences$Editor editor;
    private LanguageTypeEnum languageTypeEnum;
    
    private QLanguageTool() {
        this.languageTypeEnum = LanguageTypeEnum.AUTO;
        this.context = LibraryApplication.getContext();
    }
    
    public static QLanguageTool getInstance() {
        if (QLanguageTool.languageTool == null) {
            QLanguageTool.languageTool = new QLanguageTool();
        }
        return QLanguageTool.languageTool;
    }
    
    private static LanguageTypeEnum getSystemLanguageType() {
        Locale locale;
        if (Build$VERSION.SDK_INT >= 24) {
            locale = LocaleList.getDefault().get(0);
        }
        else {
            locale = Locale.getDefault();
        }
        if (locale.getLanguage().equals((Object)Locale.CHINESE.getLanguage())) {
            return LanguageTypeEnum.ZH;
        }
        return LanguageTypeEnum.En;
    }
    
    public void changeLanguage(LanguageTypeEnum languageTypeEnum) {
        languageTypeEnum = this.languageTypeEnum;
        if (languageTypeEnum != null) {
            this.languageTypeEnum = languageTypeEnum;
        }
    }
    
    public String getLanguage() {
        if (this.languageTypeEnum != LanguageTypeEnum.AUTO) {
            return this.languageTypeEnum.name();
        }
        return getSystemLanguageType().name();
    }
    
    public LanguageTypeEnum getLanguageType() {
        return this.languageTypeEnum;
    }
    
    enum LanguageTypeEnum
    {
        private static final LanguageTypeEnum[] $VALUES;
        
        AUTO(0), 
        En(2), 
        ZH(1);
        
        int code;
        
        private LanguageTypeEnum(final int code) {
            this.code = code;
        }
    }
}
