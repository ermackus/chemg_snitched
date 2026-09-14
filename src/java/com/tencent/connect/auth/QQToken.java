package com.tencent.connect.auth;

import com.tencent.open.b.b;
import android.content.SharedPreferences$Editor;
import com.tencent.open.utils.i;
import com.tencent.open.web.security.JniInterface;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import org.json.JSONObject;
import android.util.Base64;
import com.tencent.open.utils.k;
import com.tencent.open.utils.f;
import com.tencent.open.utils.a;
import android.content.SharedPreferences;

public class QQToken
{
    public static final int AUTH_QQ = 2;
    public static final int AUTH_QZONE = 3;
    public static final int AUTH_WEB = 1;
    private static SharedPreferences g;
    private String a;
    private String b;
    private String c;
    private int d;
    private long e;
    private a f;
    
    public QQToken(final String a) {
        this.d = 1;
        this.e = -1L;
        this.a = a;
    }
    
    private static SharedPreferences a() {
        synchronized (QQToken.class) {
            if (QQToken.g == null) {
                QQToken.g = f.a().getSharedPreferences("token_info_file", 0);
            }
            return QQToken.g;
        }
    }
    
    private static String a(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Base64.encodeToString(k.j(s), 2));
        sb.append("_aes_google");
        return sb.toString();
    }
    
    private static JSONObject a(String b, final a a) {
        synchronized (QQToken.class) {
            if (f.a() == null) {
                SLog.i("QQToken", "loadJsonPreference context null");
                return null;
            }
            if (b == null) {
                SLog.i("QQToken", "loadJsonPreference prefKey is null");
                return null;
            }
            final String string = a().getString(a((String)b), "");
            Label_0418: {
                if (TextUtils.isEmpty((CharSequence)string)) {
                    if (!JniInterface.isJniOk) {
                        i.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 5);
                        JniInterface.loadSo();
                    }
                    if (!JniInterface.isJniOk) {
                        SLog.i("QQToken", "loadJsonPreference jni load fail SECURE_LIB_VERSION=5");
                        return null;
                    }
                    final String c = c((String)b);
                    final String string2 = a().getString(c, "");
                    if (TextUtils.isEmpty((CharSequence)string2)) {
                        final String b2 = b((String)b);
                        final String string3 = a().getString(b2, "");
                        if (TextUtils.isEmpty((CharSequence)string3)) {
                            SLog.i("QQToken", "loadJsonPreference oldDesValue null");
                            return null;
                        }
                        try {
                            try {
                                final String d1 = JniInterface.d1(string3);
                                if (TextUtils.isEmpty((CharSequence)d1)) {
                                    SLog.i("QQToken", "loadJsonPreference decodeResult d1 empty");
                                    a().edit().remove(b2).apply();
                                    return null;
                                }
                                a((String)b, new JSONObject(d1), a);
                                a().edit().remove(b2).apply();
                                b = d1;
                                break Label_0418;
                            }
                            finally {}
                        }
                        catch (final Exception ex) {
                            SLog.e("QQToken", "Catch Exception", (Throwable)ex);
                            a().edit().remove(b2).apply();
                            return null;
                        }
                        a().edit().remove(b2).apply();
                    }
                    else {
                        try {
                            try {
                                final String d2 = JniInterface.d2(string2);
                                a((String)b, new JSONObject(d2), a);
                                a().edit().remove(c).apply();
                                b = d2;
                            }
                            finally {}
                        }
                        catch (final Exception ex2) {
                            SLog.e("QQToken", "Catch Exception", (Throwable)ex2);
                            a().edit().remove(c).apply();
                            return null;
                        }
                        a().edit().remove(c).apply();
                    }
                }
                else {
                    b = a.b(string);
                }
                try {
                    final JSONObject jsonObject = new JSONObject((String)b);
                    SLog.i("QQToken", "loadJsonPreference sucess");
                    return jsonObject;
                }
                catch (final Exception ex3) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("loadJsonPreference decode ");
                    sb.append(ex3.toString());
                    SLog.i("QQToken", sb.toString());
                    return null;
                }
            }
        }
    }
    
    private static boolean a(String a, final JSONObject jsonObject, final a a2) {
        synchronized (QQToken.class) {
            if (f.a() == null) {
                SLog.i("QQToken", "saveJsonPreference context null");
                return false;
            }
            if (a != null) {
                if (jsonObject != null) {
                    try {
                        final String string = jsonObject.getString("expires_in");
                        if (TextUtils.isEmpty((CharSequence)string)) {
                            SLog.i("QQToken", "expires is null");
                            return false;
                        }
                        jsonObject.put("expires_time", System.currentTimeMillis() + Long.parseLong(string) * 1000L);
                        a = a(a);
                        final String a3 = a2.a(jsonObject.toString());
                        if (a.length() > 6 && a3 != null) {
                            a().edit().putString(a, a3).commit();
                            SLog.i("QQToken", "saveJsonPreference sucess");
                            return true;
                        }
                        SLog.i("QQToken", "saveJsonPreference keyEncode or josnEncode null");
                        return false;
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("saveJsonPreference exception:");
                        sb.append(ex.toString());
                        SLog.e("QQToken", sb.toString());
                        return false;
                    }
                }
            }
            SLog.i("QQToken", "saveJsonPreference prefKey or jsonObject null");
            return false;
        }
    }
    
    @Deprecated
    private static String b(final String s) {
        return Base64.encodeToString(k.j(s), 2);
    }
    
    @Deprecated
    private static String c(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(Base64.encodeToString(k.j(s), 2));
        sb.append("_spkey");
        return sb.toString();
    }
    
    public String getAccessToken() {
        return this.b;
    }
    
    public String getAppId() {
        return this.a;
    }
    
    public int getAuthSource() {
        return this.d;
    }
    
    public long getExpireTimeInSecond() {
        return this.e;
    }
    
    public String getOpenId() {
        return this.c;
    }
    
    public String getOpenIdWithCache() {
        String openId;
        final String s = openId = this.getOpenId();
        try {
            if (TextUtils.isEmpty((CharSequence)s)) {
                openId = s;
                final JSONObject loadSession = this.loadSession(this.a);
                String string = s;
                if (loadSession != null) {
                    openId = s;
                    final String openId2 = openId = (string = loadSession.getString("openid"));
                    if (!TextUtils.isEmpty((CharSequence)openId2)) {
                        openId = openId2;
                        this.setOpenId(openId2);
                        string = openId2;
                    }
                }
                openId = string;
                openId = string;
                final StringBuilder sb = new StringBuilder();
                openId = string;
                sb.append("getOpenId from Session openId = ");
                openId = string;
                sb.append(string);
                openId = string;
                sb.append(" appId = ");
                openId = string;
                sb.append(this.a);
                openId = string;
                SLog.i("QQToken", sb.toString());
                openId = string;
            }
            else {
                openId = s;
                openId = s;
                final StringBuilder sb2 = new StringBuilder();
                openId = s;
                sb2.append("getOpenId from field openId = ");
                openId = s;
                sb2.append(s);
                openId = s;
                sb2.append(" appId = ");
                openId = s;
                sb2.append(this.a);
                openId = s;
                SLog.i("QQToken", sb2.toString());
                openId = s;
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("getLocalOpenIdByAppId ");
            sb3.append(ex.toString());
            SLog.i("QQToken", sb3.toString());
        }
        return openId;
    }
    
    public boolean isSessionValid() {
        return this.b != null && System.currentTimeMillis() < this.e;
    }
    
    public JSONObject loadSession(final String s) {
        try {
            if (this.f == null) {
                this.f = new a(com.tencent.open.utils.f.a());
            }
            return a(s, this.f);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("login loadSession");
            sb.append(ex.toString());
            SLog.i("QQToken", sb.toString());
            return null;
        }
    }
    
    public void removeSession(final String s) {
        final SharedPreferences$Editor edit = a().edit();
        edit.remove(c(s));
        edit.remove(c(s));
        edit.remove(a(s));
        edit.apply();
        SLog.i("QQToken", "removeSession sucess");
    }
    
    public boolean saveSession(final JSONObject jsonObject) {
        try {
            if (this.f == null) {
                this.f = new a(com.tencent.open.utils.f.a());
            }
            return a(this.a, jsonObject, this.f);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("login saveSession");
            sb.append(ex.toString());
            SLog.i("QQToken", sb.toString());
            return false;
        }
    }
    
    public void setAccessToken(final String b, final String s) throws NumberFormatException {
        this.b = b;
        this.e = 0L;
        if (s != null) {
            this.e = System.currentTimeMillis() + Long.parseLong(s) * 1000L;
        }
    }
    
    public void setAppId(final String a) {
        this.a = a;
    }
    
    public void setAuthSource(final int d) {
        this.d = d;
    }
    
    public void setOpenId(final String c) {
        this.c = c;
        com.tencent.open.b.b.a().a(c);
    }
}
