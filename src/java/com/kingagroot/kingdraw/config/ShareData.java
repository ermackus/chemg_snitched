package com.kingagroot.kingdraw.config;

import com.goodsrc.library.utils.AppUtil;
import android.content.Context;
import androidx.preference.PreferenceManager;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.SharedPreferences;
import com.kingagroot.kingdraw.utils.PreferencesUtils;

public class ShareData
{
    private static final String CLOUD_HINT_KEY = "cloud_hint_key";
    private static final String DEFAULT_GROUP = "DEFAULT_GROUP";
    private static final String FOLDER_MODE = "folder_mode";
    private static final String GATEWAY_IP = "GATEWAY_IP";
    private static final String GATEWAY_PORT = "GATEWAY_IP";
    private static String GESTURE_SAVE_STATE = "GESTURE_SAVE_STATE";
    private static final String GSGROUP_VERSIONNO = "GSGroup_versionNo";
    private static final String GUIDE_MAIN_STATE = "GUIDE_MAIN_STATE";
    private static String GUIDE_ME_STATE = "GUIDE_ME_STATE";
    private static final String LAST_VERSION_KEY = "last_version_key";
    private static final String LAST_VERSION_KEY_BOOT_PAGE = "last_version_key_boot_page";
    private static final String LOGIN_TIME = "LOGIN_TIME";
    private static final String SP_KEY_APP_SCORE_DEFAULT = "sp_key_app_score_default";
    private static final String SP_KEY_IS_SHOW_PRIVACY_POLICY = "sp_key_is_show_privacy_policy_2";
    private static final String SP_KEY_USED_PALETTE = "sp_key_used_palette";
    private static final String STR_SCREEN_DOT_STATE = "STR_SCREEN_DOT_STATE";
    private static final String STR_SCREEN_STATE = "STR_SCREEN_STATE";
    private static String STR_SWITCH_AI_STATE = "STR_SWITCH_AI_STATE";
    private static final String STR_SWITCH_CHECK_STATE = "STR_SWITCH_CHECK_STATE";
    private static final String STR_SWITCH_COLOR_STATE = "STR_SWITCH_COLOR_STATE";
    private static String STR_SWITCH_GATEWAY_STATE = "STR_SWITCH_GATEWAY_STATE";
    private static final String STR_SWITCH_GUIDES = "STR_SWITCH_GUIDES";
    private static final String STR_SWITCH_ZOOMER_STATE = "STR_SWITCH_ZOOMER_STATE";
    private static final String STR_TYPE_CARBON_STATE = "STR_TYPE_CARBON_STATE";
    
    public static int getCarbonState() {
        return (int)PreferencesUtils.getData(getSettingShare(), "STR_TYPE_CARBON_STATE", (Object)0);
    }
    
    public static boolean getCloudHintStatus() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), "cloud_hint_key", (Object)true);
    }
    
    public static boolean getColorState() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), "STR_SWITCH_COLOR_STATE", (Object)true);
    }
    
    private static SharedPreferences getDefaultShare() {
        return PreferenceManager.getDefaultSharedPreferences((Context)MApplication.getInstance());
    }
    
    public static boolean getFolderMode() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), "folder_mode", (Object)true);
    }
    
    public static int getGSGroupDBVersion() {
        return (int)PreferencesUtils.getData(getDefaultShare(), "GSGroup_versionNo", (Object)1);
    }
    
    public static String getGatewayIp() {
        return (String)PreferencesUtils.getData(getDefaultShare(), "GATEWAY_IP", (Object)"");
    }
    
    public static String getGatewayPort() {
        return (String)PreferencesUtils.getData(getDefaultShare(), "GATEWAY_IP", (Object)"");
    }
    
    public static boolean getGatewayState() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), ShareData.STR_SWITCH_GATEWAY_STATE, (Object)false);
    }
    
    public static int getGroupDefault() {
        return (int)PreferencesUtils.getData(getSettingShare(), "DEFAULT_GROUP", (Object)(-1));
    }
    
    public static boolean getGuidesState() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), "STR_SWITCH_GUIDES", (Object)true);
    }
    
    public static int getLastBootVersion() {
        return (int)PreferencesUtils.getData(getDefaultShare(), "last_version_key_boot_page", (Object)0);
    }
    
    public static int getLastVersion() {
        return (int)PreferencesUtils.getData(getDefaultShare(), "last_version_key", (Object)0);
    }
    
    public static long getLoginTime() {
        return (long)PreferencesUtils.getData(getDefaultShare(), "LOGIN_TIME", (Object)0L);
    }
    
    public static boolean getMainGuide() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), "GUIDE_MAIN_STATE", (Object)true);
    }
    
    public static boolean getMeGuide() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), ShareData.GUIDE_ME_STATE, (Object)true);
    }
    
    public static boolean getPicAiStatus() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), ShareData.STR_SWITCH_AI_STATE, (Object)true);
    }
    
    public static boolean getProofStatus() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), "STR_SWITCH_CHECK_STATE", (Object)true);
    }
    
    public static boolean getScreenDirection() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), "STR_SCREEN_STATE", (Object)false);
    }
    
    public static boolean getScreenDotShow() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), "STR_SCREEN_DOT_STATE", (Object)true);
    }
    
    private static SharedPreferences getSettingShare() {
        return MApplication.getInstance().getSharedPreferences("setting", 0);
    }
    
    public static boolean getShowPrivacyPolicyStatus() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), "sp_key_is_show_privacy_policy_2", (Object)false);
    }
    
    public static boolean getZoomerStatus() {
        return (boolean)PreferencesUtils.getData(getSettingShare(), "STR_SWITCH_ZOOMER_STATE", (Object)true);
    }
    
    public static boolean isSaveGesture() {
        return (boolean)PreferencesUtils.getData(getDefaultShare(), ShareData.GESTURE_SAVE_STATE, (Object)false);
    }
    
    public static boolean isUsedPalette() {
        final StringBuilder sb = new StringBuilder();
        sb.append("sp_key_used_palette");
        sb.append(AppUtil.getVersionCode((Context)MApplication.getInstance()));
        return (boolean)PreferencesUtils.getData(getDefaultShare(), sb.toString(), (Object)false);
    }
    
    public static boolean needToAppScore() {
        final StringBuilder sb = new StringBuilder();
        sb.append("sp_key_app_score_default");
        sb.append(AppUtil.getVersionCode((Context)MApplication.getInstance()));
        return (boolean)PreferencesUtils.getData(getDefaultShare(), sb.toString(), (Object)true);
    }
    
    public static void saveCloudHintStatus(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), "cloud_hint_key", (Object)b);
    }
    
    public static void saveColorState(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), "STR_SWITCH_COLOR_STATE", (Object)b);
    }
    
    public static void saveFolderMode(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), "folder_mode", (Object)b);
    }
    
    public static void saveGSGroupDBVersion(final int n) {
        PreferencesUtils.saveData(getDefaultShare(), "GSGroup_versionNo", (Object)n);
    }
    
    public static void saveGatewayState(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), ShareData.STR_SWITCH_GATEWAY_STATE, (Object)b);
    }
    
    public static void saveGuideState(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), "STR_SWITCH_GUIDES", (Object)b);
    }
    
    public static void saveLastBootVersion(final int n) {
        PreferencesUtils.saveData(getDefaultShare(), "last_version_key_boot_page", (Object)n);
    }
    
    public static void saveMainGuide(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), "GUIDE_MAIN_STATE", (Object)b);
    }
    
    public static void saveMeGuide(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), ShareData.GUIDE_ME_STATE, (Object)b);
    }
    
    public static void savePicAiStatus(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), ShareData.STR_SWITCH_AI_STATE, (Object)b);
    }
    
    public static void saveProofStatus(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), "STR_SWITCH_CHECK_STATE", (Object)b);
    }
    
    public static void saveScoredAppFlag() {
        final StringBuilder sb = new StringBuilder();
        sb.append("sp_key_app_score_default");
        sb.append(AppUtil.getVersionCode((Context)MApplication.getInstance()));
        PreferencesUtils.saveData(getDefaultShare(), sb.toString(), (Object)false);
    }
    
    public static void saveShowPrivacyPolicyStatus(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), "sp_key_is_show_privacy_policy_2", (Object)b);
    }
    
    public static void saveUsedPaletteFlag() {
        final StringBuilder sb = new StringBuilder();
        sb.append("sp_key_used_palette");
        sb.append(AppUtil.getVersionCode((Context)MApplication.getInstance()));
        PreferencesUtils.saveData(getDefaultShare(), sb.toString(), (Object)true);
    }
    
    public static void saveZoomerStatus(final boolean b) {
        PreferencesUtils.saveData(getSettingShare(), "STR_SWITCH_ZOOMER_STATE", (Object)b);
    }
    
    public static void setCarbonState(final int n) {
        PreferencesUtils.saveData(getSettingShare(), "STR_TYPE_CARBON_STATE", (Object)n);
    }
    
    public static void setGatewayIp(final String s) {
        PreferencesUtils.saveData(getDefaultShare(), "GATEWAY_IP", (Object)s);
    }
    
    public static void setGatewayPort(final String s) {
        PreferencesUtils.saveData(getDefaultShare(), "GATEWAY_IP", (Object)s);
    }
    
    public static void setGestureSaveState(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), ShareData.GESTURE_SAVE_STATE, (Object)b);
    }
    
    public static void setGroupDefault(final int n) {
        PreferencesUtils.saveData(getSettingShare(), "DEFAULT_GROUP", (Object)n);
    }
    
    public static void setLoginTime() {
        PreferencesUtils.saveData(getDefaultShare(), "LOGIN_TIME", (Object)System.currentTimeMillis());
    }
    
    public static void setScreenDirection(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), "STR_SCREEN_STATE", (Object)b);
    }
    
    public static void setScreenDotShow(final boolean b) {
        PreferencesUtils.saveData(getDefaultShare(), "STR_SCREEN_DOT_STATE", (Object)b);
    }
}
