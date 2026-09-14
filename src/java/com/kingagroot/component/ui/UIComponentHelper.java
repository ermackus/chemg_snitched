package com.kingagroot.component.ui;

import com.kingagroot.kingdraw.core.KingDrawConfig;
import org.xutils.x$Ext;
import android.app.Application;

public class UIComponentHelper extends Application
{
    private static String DB_FILE_PATH = "";
    private static String fileCachePath;
    private static Application mApplication;
    private static String paletteCachePath;
    private static String pwdEmail;
    private static String pwdPhone;
    private static String regEmail;
    private static String regPhone;
    
    public static String getDbFilePath() {
        return UIComponentHelper.DB_FILE_PATH;
    }
    
    public static final String getFileCachePath() {
        return UIComponentHelper.fileCachePath;
    }
    
    public static Application getInstance() {
        synchronized (UIComponentHelper.class) {
            return UIComponentHelper.mApplication;
        }
    }
    
    public static final String getPaletteCachePath() {
        return UIComponentHelper.paletteCachePath;
    }
    
    public static String getPwdEmail() {
        return UIComponentHelper.pwdEmail;
    }
    
    public static String getPwdPhone() {
        return UIComponentHelper.pwdPhone;
    }
    
    public static String getRegEmail() {
        return UIComponentHelper.regEmail;
    }
    
    public static String getRegPhone() {
        return UIComponentHelper.regPhone;
    }
    
    public static void init(final Application mApplication) {
        x$Ext.init(UIComponentHelper.mApplication = mApplication);
        x$Ext.setDebug(false);
        final StringBuilder sb = new StringBuilder();
        sb.append((Object)mApplication.getExternalFilesDir(""));
        sb.append("/KingDraw/DBFiles/");
        final String string = sb.toString();
        setDbFilePath(string);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        sb2.append("kingDrawSUP_Coustom.db");
        KingDrawConfig.addSupDBPath("GSGroupModel", sb2.toString());
    }
    
    public static void setDbFilePath(final String db_FILE_PATH) {
        UIComponentHelper.DB_FILE_PATH = db_FILE_PATH;
    }
    
    public static void setFileCachePath(final String fileCachePath) {
        UIComponentHelper.fileCachePath = fileCachePath;
    }
    
    public static void setPaletteCachePath(final String paletteCachePath) {
        UIComponentHelper.paletteCachePath = paletteCachePath;
    }
    
    public static void setPwdEmail(final String pwdEmail) {
        UIComponentHelper.pwdEmail = pwdEmail;
    }
    
    public static void setPwdPhone(final String pwdPhone) {
        UIComponentHelper.pwdPhone = pwdPhone;
    }
    
    public static void setRegEmail(final String regEmail) {
        UIComponentHelper.regEmail = regEmail;
    }
    
    public static void setRegPhone(final String regPhone) {
        UIComponentHelper.regPhone = regPhone;
    }
}
