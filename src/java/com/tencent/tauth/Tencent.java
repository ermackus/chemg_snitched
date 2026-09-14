package com.tencent.tauth;

import com.tencent.open.miniapp.MiniApp;
import com.tencent.open.im.IM;
import com.tencent.connect.api.QQAuthManage;
import com.tencent.open.apireq.IApiCallback;
import com.tencent.connect.share.QzoneShare;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.emotion.QQEmotion;
import java.util.ArrayList;
import com.tencent.connect.avatar.QQAvatar;
import org.json.JSONException;
import java.io.IOException;
import com.tencent.open.utils.HttpUtils;
import com.tencent.connect.share.QzonePublish;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.log.Tracer;
import android.net.Uri;
import java.util.Iterator;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import com.tencent.open.utils.k;
import com.tencent.open.utils.i;
import com.tencent.connect.common.UIListenerManager;
import android.content.Intent;
import com.tencent.open.utils.g;
import com.tencent.open.a.a;
import android.text.TextUtils;
import com.tencent.open.utils.f;
import android.content.pm.PackageManager$NameNotFoundException;
import com.tencent.open.log.SLog;
import android.content.ComponentName;
import com.tencent.open.b.b;
import android.content.Context;
import com.tencent.connect.auth.c;

public class Tencent
{
    public static final int REQUEST_LOGIN = 10001;
    private static Tencent c;
    private final c a;
    private String b;
    
    private Tencent(final String s, final Context context) {
        this.a = com.tencent.connect.auth.c.a(s, context);
        com.tencent.open.b.b.a().a(s, context);
    }
    
    private static String a(final Object... array) {
        if (array == null || array.length == 0) {
            return "";
        }
        if (array.length % 2 != 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; i += 2) {
            if (i > 0) {
                sb.append('|');
            }
            sb.append(array[i]);
            sb.append(':');
            sb.append(array[i + 1]);
        }
        return sb.toString();
    }
    
    private static void a(final String s, final Object o) {
        b.a().a(s, o);
    }
    
    private static void a(final String s, final Object... array) {
        b.a().a(s, a(array));
    }
    
    private static boolean a(final Context context, String s) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 128);
            try {
                s = (String)new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity");
                context.getPackageManager().getActivityInfo((ComponentName)s, 128);
                return true;
            }
            catch (final PackageManager$NameNotFoundException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity,\u8bf7\u52a0\u4e0acom.tencent.connect.common.AssistActivity,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.");
                sb.append("\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>");
                final String string = sb.toString();
                s = (String)new StringBuilder();
                ((StringBuilder)s).append("AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity\n");
                ((StringBuilder)s).append(string);
                SLog.e("openSDK_LOG.Tencent", ((StringBuilder)s).toString());
                return false;
            }
        }
        catch (final PackageManager$NameNotFoundException ex2) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity,\u8bf7\u52a0\u4e0acom.tencent.tauth.AuthActivity,\u5e76\u914d\u7f6e<data android:scheme=\"tencent");
            sb2.append(s);
            sb2.append("\" />,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.");
            final String string2 = sb2.toString();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append("\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent");
            sb3.append(s);
            sb3.append("\" />\n</intent-filter>\n</activity>");
            final String string3 = sb3.toString();
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity");
            sb4.append(string3);
            SLog.e("openSDK_LOG.Tencent", sb4.toString());
            return false;
        }
    }
    
    public static Tencent createInstance(final String s, final Context context) {
        synchronized (Tencent.class) {
            f.a(context.getApplicationContext());
            final StringBuilder sb = new StringBuilder();
            sb.append("createInstance()  -- start, appId = ");
            sb.append(s);
            SLog.i("openSDK_LOG.Tencent", sb.toString());
            if (TextUtils.isEmpty((CharSequence)s)) {
                SLog.e("openSDK_LOG.Tencent", "appId should not be empty!");
                return null;
            }
            if (Tencent.c == null) {
                Tencent.c = new Tencent(s, context);
            }
            else if (!s.equals((Object)Tencent.c.getAppId())) {
                Tencent.c.logout(context);
                Tencent.c = new Tencent(s, context);
            }
            if (!a(context, s)) {
                return null;
            }
            a("createInstance", "appid", s);
            a.a().a(g.a(context, s));
            SLog.i("openSDK_LOG.Tencent", "createInstance()  -- end");
            return Tencent.c;
        }
    }
    
    public static Tencent createInstance(final String s, final Context context, final String b) {
        synchronized (Tencent.class) {
            final Tencent instance = createInstance(s, context);
            final StringBuilder sb = new StringBuilder();
            sb.append("createInstance()  -- start, appId = ");
            sb.append(s);
            sb.append(", authorities=");
            sb.append(b);
            SLog.i("openSDK_LOG.Tencent", sb.toString());
            a("createInstance_authority", "appid", s, "authorities", b);
            if (instance != null) {
                instance.b = b;
            }
            else {
                SLog.i("openSDK_LOG.Tencent", "null == tencent set mAuthorities fail");
            }
            return instance;
        }
    }
    
    public static String getAuthorities(String b) {
        synchronized (Tencent.class) {
            a("getAuthorities", "appid", b);
            if (TextUtils.isEmpty((CharSequence)b)) {
                SLog.i("openSDK_LOG.Tencent", "TextUtils.isEmpty(appId)");
                return null;
            }
            if (Tencent.c == null) {
                SLog.i("openSDK_LOG.Tencent", "sInstance == null");
                return null;
            }
            if (b.equals((Object)Tencent.c.getAppId())) {
                b = Tencent.c.b;
            }
            else {
                b = "";
            }
            return b;
        }
    }
    
    public static void handleResultData(final Intent intent, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("handleResultData() data = null ? ");
        final boolean b = true;
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(uiListener == null && b);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("handleResultData", new Object[0]);
        UIListenerManager.getInstance().handleDataToListener(intent, uiListener);
    }
    
    public static boolean isSupportPushToQZone(final Context context) {
        final boolean b = i.c(context, "5.9.5") >= 0 || i.a(context, "com.tencent.qqlite") != null;
        final StringBuilder sb = new StringBuilder();
        sb.append("isSupportPushToQZone() support=");
        sb.append(b);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isSupportPushToQZone", (Object)b);
        return b;
    }
    
    public static boolean isSupportShareToQQ(final Context context) {
        SLog.i("openSDK_LOG.Tencent", "isSupportShareToQQ()");
        final boolean c = k.c(context);
        final boolean b = true;
        if (c && i.a(context, "com.tencent.minihd.qq") != null) {
            a("isSupportShareToQQ", (Object)true);
            return true;
        }
        boolean b2 = b;
        if (i.c(context, "4.1") < 0) {
            b2 = b;
            if (i.a(context, "com.tencent.tim") == null) {
                b2 = (i.a(context, "com.tencent.qqlite") != null && b);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("isSupportShareToQQ() support=");
        sb.append(b2);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isSupportShareToQQ", (Object)b2);
        return b2;
    }
    
    public static boolean onActivityResultData(final int n, final int n2, final Intent intent, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("onActivityResultData() reqcode = ");
        sb.append(n);
        sb.append(", resultcode = ");
        sb.append(n2);
        sb.append(", data = null ? ");
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(uiListener == null);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("onActivityResultData", "requestCode", n, "resultCode", n2);
        return UIListenerManager.getInstance().onActivityResult(n, n2, intent, uiListener);
    }
    
    public static Map<String, String> parseMiniParameters(final Intent intent) {
        a("parseMiniParameters", new Object[0]);
        final HashMap hashMap = new HashMap();
        if (intent == null) {
            SLog.e("openSDK_LOG.Tencent", "parseMiniParameters null == intent");
            return (Map<String, String>)hashMap;
        }
        try {
            final String stringExtra = intent.getStringExtra("appParameter");
            if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("parseMiniParameters appParameter=");
                sb.append(stringExtra);
                SLog.d("openSDK_LOG.Tencent", sb.toString());
                final JSONObject jsonObject = new JSONObject(stringExtra);
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s = (String)keys.next();
                    ((Map)hashMap).put((Object)s, (Object)jsonObject.getString(s));
                }
                return (Map<String, String>)hashMap;
            }
            final Uri data = intent.getData();
            if (data == null) {
                SLog.d("openSDK_LOG.Tencent", "parseMiniParameters uri==null");
                return (Map<String, String>)hashMap;
            }
            final String string = data.toString();
            if (TextUtils.isEmpty((CharSequence)string)) {
                SLog.d("openSDK_LOG.Tencent", "parseMiniParameters uriStr isEmpty");
                return (Map<String, String>)hashMap;
            }
            final String substring = string.substring(string.lastIndexOf(63) + 1);
            if (TextUtils.isEmpty((CharSequence)substring)) {
                SLog.d("openSDK_LOG.Tencent", "parseMiniParameters uriParam is empty");
                return (Map<String, String>)hashMap;
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("parseMiniParameters uriParam=");
            sb2.append(substring);
            SLog.d("openSDK_LOG.Tencent", sb2.toString());
            final String[] split = substring.split("&");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String[] split2 = split[i].split("=");
                if (split2.length == 2) {
                    ((Map)hashMap).put((Object)split2[0], (Object)split2[1]);
                }
            }
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.Tencent", "parseMiniParameters Exception", (Throwable)ex);
        }
        return (Map<String, String>)hashMap;
    }
    
    public static void setCustomLogger(final Tracer customLogger) {
        SLog.i("openSDK_LOG.Tencent", "setCustomLogger");
        a("setCustomLogger", new Object[0]);
        SLog.getInstance().setCustomLogger(customLogger);
    }
    
    public void checkLogin(final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "checkLogin()");
        a("checkLogin", new Object[0]);
        this.a.a(uiListener);
    }
    
    public String getAccessToken() {
        final String accessToken = this.a.b().getAccessToken();
        final StringBuilder sb = new StringBuilder();
        sb.append("getAccessToken() accessToken = ");
        sb.append(accessToken);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("getAccessToken", new Object[0]);
        return accessToken;
    }
    
    public String getAppId() {
        final String appId = this.a.b().getAppId();
        final StringBuilder sb = new StringBuilder();
        sb.append("getAppId() appid =");
        sb.append(appId);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("getAppId", appId);
        return appId;
    }
    
    public long getExpiresIn() {
        final long expireTimeInSecond = this.a.b().getExpireTimeInSecond();
        final StringBuilder sb = new StringBuilder();
        sb.append("getExpiresIn() expiresin= ");
        sb.append(expireTimeInSecond);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("getExpiresIn", (Object)expireTimeInSecond);
        return expireTimeInSecond;
    }
    
    public String getOpenId() {
        final String openId = this.a.b().getOpenId();
        final StringBuilder sb = new StringBuilder();
        sb.append("getOpenId() openid= ");
        sb.append(openId);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("getOpenId", new Object[0]);
        return openId;
    }
    
    public QQToken getQQToken() {
        SLog.i("openSDK_LOG.Tencent", "getQQToken()");
        a("getQQToken", new Object[0]);
        return this.a.b();
    }
    
    @Deprecated
    public void handleLoginData(final Intent intent, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("handleLoginData() data = null ? ");
        final boolean b = true;
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(uiListener == null && b);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("handleLoginData", new Object[0]);
        UIListenerManager.getInstance().handleDataToListener(intent, uiListener);
    }
    
    public void initSessionCache(final JSONObject jsonObject) {
        a("initSessionCache", new Object[0]);
        try {
            final String string = jsonObject.getString("access_token");
            final String string2 = jsonObject.getString("expires_in");
            final String string3 = jsonObject.getString("openid");
            if (!TextUtils.isEmpty((CharSequence)string) && !TextUtils.isEmpty((CharSequence)string2) && !TextUtils.isEmpty((CharSequence)string3)) {
                this.setAccessToken(string, string2);
                this.setOpenId(string3);
            }
            SLog.i("openSDK_LOG.Tencent", "initSessionCache()");
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("initSessionCache ");
            sb.append(ex.toString());
            SLog.i("QQToken", sb.toString());
        }
    }
    
    public boolean isQQInstalled(final Context context) {
        final boolean b = i.b(context);
        final StringBuilder sb = new StringBuilder();
        sb.append("isQQInstalled() installed=");
        sb.append(b);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isQQInstalled", (Object)b);
        return b;
    }
    
    public boolean isReady() {
        final boolean b = this.isSessionValid() && this.getOpenId() != null;
        final StringBuilder sb = new StringBuilder();
        sb.append("isReady() --ready=");
        sb.append(b);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isReady", (Object)b);
        return b;
    }
    
    public boolean isSessionValid() {
        final boolean c = this.a.c();
        final StringBuilder sb = new StringBuilder();
        sb.append("isSessionValid() isvalid =");
        sb.append(c);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isSessionValid", (Object)c);
        return c;
    }
    
    public boolean isSupportSSOLogin(final Activity activity) {
        SLog.i("openSDK_LOG.Tencent", "isSupportSSOLogin()");
        final boolean c = k.c((Context)activity);
        final boolean b = true;
        if (c && i.a((Context)activity, "com.tencent.minihd.qq") != null) {
            a("isSupportSSOLogin", (Object)true);
            return true;
        }
        boolean b2 = b;
        if (i.c((Context)activity, "4.1") < 0) {
            b2 = b;
            if (i.d((Context)activity, "1.1") < 0) {
                b2 = (i.e((Context)activity, "4.0.0") >= 0 && b);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("isSupportSSOLogin() support=");
        sb.append(b2);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("isSupportSSOLogin", (Object)b2);
        return b2;
    }
    
    public JSONObject loadSession(final String s) {
        final JSONObject loadSession = this.a.b().loadSession(s);
        final StringBuilder sb = new StringBuilder();
        sb.append("loadSession() appid ");
        sb.append(s);
        sb.append(", length=");
        int length;
        if (loadSession != null) {
            length = loadSession.length();
        }
        else {
            length = 0;
        }
        sb.append(length);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("loadSession", "appid", s);
        return loadSession;
    }
    
    public int login(final Activity activity, final IUiListener uiListener, final Map<String, Object> map) {
        SLog.i("openSDK_LOG.Tencent", "login activity with params");
        a("login_param", new Object[0]);
        return this.a.a(activity, uiListener, (Map)map);
    }
    
    public int login(final Activity activity, final String s, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("login() with activity, scope is ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("login_scope", "scope", s);
        return this.a.a(activity, s, uiListener);
    }
    
    public int login(final Activity activity, final String s, final IUiListener uiListener, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("login() with activity, scope is ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("login_qrcode", "scope", s, "qrcode", b);
        return this.a.a(activity, s, uiListener, b);
    }
    
    public int login(final Fragment fragment, final String s, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("login() with fragment, scope is ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("login_fragment_scope", "scope", s);
        return this.a.a(fragment, s, uiListener, "");
    }
    
    public int login(final Fragment fragment, final String s, final IUiListener uiListener, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("login() with fragment, scope is ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("login_fragment_scope_qrcode", "scope", s, "qrcode", b);
        return this.a.a(fragment, s, uiListener, "", b);
    }
    
    public int loginServerSide(final Activity activity, final String s, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("loginServerSide() with activity, scope = ");
        sb.append(s);
        sb.append(",server_side");
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("loginServerSide_activity", "scope", s);
        final c a = this.a;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s);
        sb2.append(",server_side");
        return a.a(activity, sb2.toString(), uiListener);
    }
    
    public int loginServerSide(final Fragment fragment, final String s, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("loginServerSide() with fragment, scope = ");
        sb.append(s);
        sb.append(",server_side");
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("loginServerSide_fragment", "scope", s);
        final c a = this.a;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(s);
        sb2.append(",server_side");
        return a.a(fragment, sb2.toString(), uiListener, "");
    }
    
    public int loginWithOEM(final Activity activity, final String s, final IUiListener uiListener, final boolean b, final String s2, final String s3, final String s4) {
        final StringBuilder sb = new StringBuilder();
        sb.append("loginWithOEM() with activity, scope = ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("loginWithOEM", "scope", s, "qrcode", b, "registerChannel", s2, "installChannel", s3, "businessId", s4);
        return this.a.a(activity, s, uiListener, b, s2, s3, s4);
    }
    
    public void logout(final Context context) {
        SLog.i("openSDK_LOG.Tencent", "logout()");
        a("logout", new Object[0]);
        this.a.b().setAccessToken((String)null, "0");
        this.a.b().setOpenId((String)null);
        this.a.b().removeSession(this.a.b().getAppId());
    }
    
    public void publishToQzone(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "publishToQzone()");
        a("publishToQzone", new Object[0]);
        new QzonePublish((Context)activity, this.a.b()).publishToQzone(activity, bundle, uiListener);
    }
    
    public int reAuth(final Activity activity, final String s, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("reAuth() with activity, scope = ");
        sb.append(s);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("reAuth", "scope", s);
        return this.a.b(activity, s, uiListener);
    }
    
    public void reportDAU() {
        SLog.i("openSDK_LOG.Tencent", "reportDAU() ");
        a("reportDAU", new Object[0]);
        this.a.a();
    }
    
    public JSONObject request(final String s, final Bundle bundle, final String s2) throws IOException, JSONException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException {
        SLog.i("openSDK_LOG.Tencent", "request()");
        a("request", "graphPath", s, "httpMethod", s2);
        return HttpUtils.request(this.a.b(), f.a(), s, bundle, s2);
    }
    
    public void requestAsync(final String s, final Bundle bundle, final String s2, final IRequestListener requestListener) {
        SLog.i("openSDK_LOG.Tencent", "requestAsync()");
        a("requestAsync", "graphPath", s, "httpMethod", s2);
        HttpUtils.requestAsync(this.a.b(), f.a(), s, bundle, s2, requestListener);
    }
    
    public void saveSession(final JSONObject jsonObject) {
        final StringBuilder sb = new StringBuilder();
        sb.append("saveSession() length=");
        int length;
        if (jsonObject != null) {
            length = jsonObject.length();
        }
        else {
            length = 0;
        }
        sb.append(length);
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("saveSession", new Object[0]);
        this.a.b().saveSession(jsonObject);
    }
    
    public void setAccessToken(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("setAccessToken(), expiresIn = ");
        sb.append(s2);
        sb.append("");
        SLog.i("openSDK_LOG.Tencent", sb.toString());
        a("setAccessToken", new Object[0]);
        this.a.a(s, s2);
    }
    
    public void setAvatar(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "setAvatar()");
        a("setAvatar", new Object[0]);
        new QQAvatar(this.a.b()).setAvatar(activity, Uri.parse(bundle.getString("picture")), uiListener, bundle.getInt("exitAnim"));
    }
    
    public void setAvatar(final Activity activity, final Bundle bundle, final IUiListener uiListener, final int n, final int n2) {
        SLog.i("openSDK_LOG.Tencent", "setAvatar()");
        a("setAvatar_anim", new Object[0]);
        bundle.putInt("exitAnim", n2);
        activity.overridePendingTransition(n, 0);
        this.setAvatar(activity, bundle, uiListener);
    }
    
    public void setAvatarByQQ(final Activity activity, final Uri uri, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "setAvatarByQQ()");
        a("setAvatarByQQ", new Object[0]);
        new QQAvatar(this.a.b()).setAvatarByQQ(activity, uri, uiListener);
    }
    
    public void setDynamicAvatar(final Activity activity, final Uri uri, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "setDynamicAvatar()");
        a("setDynamicAvatar", new Object[0]);
        new QQAvatar(this.a.b()).setDynamicAvatar(activity, uri, uiListener);
    }
    
    public void setEmotions(final Activity activity, final ArrayList<Uri> list, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "saveQQEmotions()");
        a("setEmotions", new Object[0]);
        new QQEmotion(this.a.b()).setEmotions(activity, (ArrayList)list, uiListener);
    }
    
    public void setOpenId(final String s) {
        SLog.i("openSDK_LOG.Tencent", "setOpenId() --start");
        a("setOpenId", new Object[0]);
        this.a.b(f.a(), s);
        SLog.i("openSDK_LOG.Tencent", "setOpenId() --end");
    }
    
    public void shareToQQ(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "shareToQQ()");
        a("shareToQQ", new Object[0]);
        if (TextUtils.isEmpty((CharSequence)this.b)) {
            uiListener.onWarning(-19);
        }
        new QQShare((Context)activity, this.a.b()).shareToQQ(activity, bundle, uiListener);
    }
    
    public void shareToQzone(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.Tencent", "shareToQzone()");
        a("shareToQzone", new Object[0]);
        new QzoneShare((Context)activity, this.a.b()).shareToQzone(activity, bundle, uiListener);
    }
    
    public void startAuthManagePage(final Activity activity, final IApiCallback apiCallback) {
        SLog.i("openSDK_LOG.Tencent", "startAuthManagePage");
        new QQAuthManage(this.a, this.getQQToken()).gotoManagePage(activity, apiCallback);
    }
    
    public int startIMAio(final Activity activity, final String s, final String s2) {
        SLog.i("openSDK_LOG.Tencent", "startIMAio()");
        a("startIMAio", "uin", s, "pkg_name", s2);
        return this.startIMConversation(activity, "thirdparty2c", s, s2);
    }
    
    public int startIMAudio(final Activity activity, final String s, final String s2) {
        SLog.i("openSDK_LOG.Tencent", "startIMAudio()");
        a("startIMAudio", "uin", s, "pkg_name", s2);
        return this.startIMConversation(activity, "audio_chat", s, s2);
    }
    
    public int startIMConversation(final Activity activity, final String s, final String s2, final String s3) {
        a("startIMConversation", "chatType", s, "uin", s2, "pkg_name", s3);
        return new IM(this.getQQToken()).startIMConversation(activity, s, s2, s3);
    }
    
    public int startIMVideo(final Activity activity, final String s, final String s2) {
        SLog.i("openSDK_LOG.Tencent", "startIMVideo()");
        a("startIMVideo", "uin", s, "pkg_name", s2);
        return this.startIMConversation(activity, "video_chat", s, s2);
    }
    
    public int startMiniApp(final Activity activity, final String s, final String s2, final String s3) {
        SLog.i("openSDK_LOG.Tencent", "startMiniApp()");
        a("startMiniApp", "miniAppId", s, "miniAppPath", s2, "miniAppVersion", s3);
        return new MiniApp(this.getQQToken()).startMiniApp(activity, "mini_program_or_game", s, "21", s2, s3);
    }
}
