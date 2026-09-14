package com.tencent.open;

import com.tencent.tauth.UiError;
import android.text.TextUtils;
import com.tencent.tauth.DefaultUiListener;
import android.webkit.WebSettings;
import com.tencent.open.c.b;
import com.tencent.open.utils.i;
import com.tencent.open.utils.HttpUtils;
import android.webkit.CookieSyncManager;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import com.tencent.open.utils.k;
import com.tencent.open.utils.h;
import com.tencent.open.utils.g;
import com.tencent.open.utils.f;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import android.os.Bundle;
import android.content.Intent;
import com.tencent.connect.auth.c;
import com.tencent.connect.auth.QQToken;
import android.app.Activity;
import com.tencent.connect.common.BaseApi;

public class SocialApiIml extends BaseApi
{
    private Activity a;
    
    public SocialApiIml(final QQToken qqToken) {
        super(qqToken);
    }
    
    public SocialApiIml(final c c, final QQToken qqToken) {
        super(c, qqToken);
    }
    
    private void a(final Activity activity, final Intent intent, final String s, final Bundle bundle, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->handleIntentWithAgent action = ");
        sb.append(s);
        SLog.i("openSDK_LOG.SocialApiIml", sb.toString());
        intent.putExtra("key_action", s);
        intent.putExtra("key_params", bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(11105, uiListener);
        this.a(activity, intent, 11105);
    }
    
    private void a(final Activity activity, final Intent intent, final String s, final Bundle bundle, final String s2, final IUiListener uiListener, final boolean b) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->handleIntent action = ");
        sb.append(s);
        sb.append(", activityIntent = null ? ");
        final boolean b2 = true;
        sb.append(intent == null);
        SLog.i("openSDK_LOG.SocialApiIml", sb.toString());
        if (intent != null) {
            this.a(activity, intent, s, bundle, uiListener);
        }
        else {
            final g a = g.a(f.a(), this.c.getAppId());
            int n = b2 ? 1 : 0;
            if (!b) {
                if (a.b("C_LoginH5")) {
                    n = (b2 ? 1 : 0);
                }
                else {
                    n = 0;
                }
            }
            if (n != 0) {
                this.a(activity, s, bundle, s2, uiListener);
            }
            else {
                this.a(activity, bundle, uiListener);
            }
        }
    }
    
    private void a(final Activity a, final String s, final Bundle bundle, final IUiListener uiListener) {
        this.a = a;
        Intent intent;
        if ((intent = this.c("com.tencent.open.agent.SocialFriendChooser")) == null) {
            SLog.i("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            intent = this.c("com.tencent.open.agent.RequestFreegiftActivity");
        }
        bundle.putAll(this.b());
        if ("action_ask".equals((Object)s)) {
            bundle.putString("type", "request");
        }
        else if ("action_gift".equals((Object)s)) {
            bundle.putString("type", "freegift");
        }
        this.a(a, intent, s, bundle, h.a().a(f.a(), "https://imgcache.qq.com/open/mobile/request/sdk_request.html?"), uiListener, false);
    }
    
    private void a(Activity activity, String s, final Bundle bundle, final String s2, final IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->handleIntentWithH5 action = ");
        sb.append(s);
        SLog.i("openSDK_LOG.SocialApiIml", sb.toString());
        final Intent b = this.b("com.tencent.open.agent.AgentActivity");
        s = (String)new a(activity, uiListener, s, s2, bundle);
        final Intent b2 = this.b("com.tencent.open.agent.EncryTokenActivity");
        if (b2 != null && b != null && b.getComponent() != null && b2.getComponent() != null && b.getComponent().getPackageName().equals((Object)b2.getComponent().getPackageName())) {
            b2.putExtra("oauth_consumer_key", this.c.getAppId());
            b2.putExtra("openid", this.c.getOpenId());
            b2.putExtra("access_token", this.c.getAccessToken());
            b2.putExtra("key_action", "action_check_token");
            if (this.a(b2)) {
                SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
                UIListenerManager.getInstance().setListenerWithRequestcode(11106, (IUiListener)s);
                this.a(activity, b2, 11106);
            }
        }
        else {
            SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("tencent&sdk&qazxc***14969%%");
            sb2.append(this.c.getAccessToken());
            sb2.append(this.c.getAppId());
            sb2.append(this.c.getOpenId());
            sb2.append("qzone3.4");
            final String g = k.g(sb2.toString());
            activity = (Activity)new JSONObject();
            try {
                ((JSONObject)activity).put("encry_token", (Object)g);
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
            }
            ((a)s).onComplete(activity);
        }
    }
    
    private void a(final Context context, final String s, final Bundle bundle, final String s2, final IUiListener uiListener) {
        SLog.v("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString("oauth_consumer_key", this.c.getAppId());
        if (this.c.isSessionValid()) {
            bundle.putString("access_token", this.c.getAccessToken());
        }
        final String openId = this.c.getOpenId();
        if (openId != null) {
            bundle.putString("openid", openId);
        }
        try {
            bundle.putString("pf", f.a().getSharedPreferences("pfStore", 0).getString("pf", "openmobile_android"));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            bundle.putString("pf", "openmobile_android");
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s2);
        sb.append(HttpUtils.encodeUrl(bundle));
        final String string = sb.toString();
        SLog.d("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (!"action_challenge".equals((Object)s) && !"action_brag".equals((Object)s)) {
            new TDialog((Context)this.a, s, string, uiListener, this.c).show();
        }
        else {
            SLog.d("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
            new com.tencent.open.c((Context)this.a, s, string, uiListener, this.c).show();
        }
    }
    
    public void ask(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a(activity, "action_ask", bundle, uiListener);
    }
    
    protected Intent b(final String s) {
        final Intent intent = new Intent();
        intent.setClassName("com.qzone", s);
        final Intent intent2 = new Intent();
        intent2.setClassName("com.tencent.mobileqq", s);
        final Intent intent3 = new Intent();
        intent3.setClassName("com.tencent.minihd.qq", s);
        if (k.c(f.a()) && i.a(f.a(), intent3)) {
            return intent3;
        }
        if (i.a(f.a(), intent2) && i.c(f.a(), "4.7") >= 0) {
            return intent2;
        }
        if (i.a(f.a(), intent) && i.a(i.a(f.a(), "com.qzone"), "4.2") >= 0) {
            Intent intent4;
            if (i.a(f.a(), intent.getComponent().getPackageName(), "ec96e9ac1149251acbb1b0c5777cae95")) {
                intent4 = intent;
            }
            else {
                intent4 = null;
            }
            return intent4;
        }
        return null;
    }
    
    public void gift(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        this.a(activity, "action_gift", bundle, uiListener);
    }
    
    public void invite(final Activity a, final Bundle bundle, final IUiListener uiListener) {
        this.a = a;
        Intent intent;
        if ((intent = this.c("com.tencent.open.agent.SocialFriendChooser")) == null) {
            SLog.i("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            intent = this.c("com.tencent.open.agent.AppInvitationActivity");
        }
        bundle.putAll(this.b());
        this.a(a, intent, "action_invite", bundle, h.a().a(f.a(), "https://imgcache.qq.com/open/mobile/invite/sdk_invite.html?"), uiListener, false);
    }
    
    public void story(final Activity a, final Bundle bundle, final IUiListener uiListener) {
        this.a = a;
        final Intent c = this.c("com.tencent.open.agent.SendStoryActivity");
        bundle.putAll(this.b());
        this.a(a, c, "action_story", bundle, h.a().a(f.a(), "https://imgcache.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?"), uiListener, false);
    }
    
    public void writeEncryToken(final Context context) {
        final String accessToken = this.c.getAccessToken();
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        String g;
        if (accessToken != null && accessToken.length() > 0 && appId != null && appId.length() > 0 && openId != null && openId.length() > 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append("tencent&sdk&qazxc***14969%%");
            sb.append(accessToken);
            sb.append(appId);
            sb.append(openId);
            sb.append("qzone3.4");
            g = k.g(sb.toString());
        }
        else {
            g = null;
        }
        final b b = new b(context);
        final WebSettings settings = b.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        i.a(settings);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"");
        sb2.append(this.c.getOpenId());
        sb2.append("_");
        sb2.append(this.c.getAppId());
        sb2.append("\"]=\"");
        sb2.append(g);
        sb2.append("\";</script></head><body></body></html>");
        final String string = sb2.toString();
        final String a = h.a().a(context, "https://imgcache.qq.com");
        b.loadDataWithBaseURL(a, string, "text/html", "utf-8", a);
    }
    
    private class a extends DefaultUiListener
    {
        final SocialApiIml a;
        private IUiListener b;
        private String c;
        private String d;
        private Bundle e;
        private Activity f;
        
        a(final SocialApiIml a, final Activity f, final IUiListener b, final String c, final String d, final Bundle e) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }
        
        @Override
        public void onCancel() {
            this.b.onCancel();
        }
        
        @Override
        public void onComplete(final Object o) {
            final JSONObject jsonObject = (JSONObject)o;
            String string;
            try {
                string = jsonObject.getString("encry_token");
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
                SLog.e("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", (Throwable)ex);
                string = null;
            }
            this.e.putString("encrytoken", string);
            final SocialApiIml a = this.a;
            a.a((Context)a.a, this.c, this.e, this.d, this.b);
            if (TextUtils.isEmpty((CharSequence)string)) {
                SLog.d("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                this.a.writeEncryToken((Context)this.f);
            }
        }
        
        @Override
        public void onError(final UiError uiError) {
            final StringBuilder sb = new StringBuilder();
            sb.append("OpenApi, EncryptTokenListener() onError");
            sb.append(uiError.errorMessage);
            SLog.d("openSDK_LOG.SocialApiIml", sb.toString());
            this.b.onError(uiError);
        }
    }
}
