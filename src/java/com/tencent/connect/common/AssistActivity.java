package com.tencent.connect.common;

import android.net.Uri;
import org.json.JSONObject;
import android.text.TextUtils;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Looper;
import android.content.Intent;
import com.tencent.tauth.IUiListener;
import com.tencent.open.b.e;
import com.tencent.tauth.UiError;
import android.content.Context;
import com.tencent.open.utils.k;
import android.os.Bundle;
import com.tencent.open.log.SLog;
import android.os.Message;
import android.os.Handler;
import android.app.Activity;

public class AssistActivity extends Activity
{
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected boolean a;
    protected Handler b;
    private boolean c;
    private String d;
    private QQStayReceiver e;
    private boolean f;
    
    public AssistActivity() {
        this.c = false;
        this.a = false;
        this.b = new Handler() {
            final AssistActivity a;
            
            public void handleMessage(final Message message) {
                if (message.what == 0) {
                    if (!this.a.isFinishing()) {
                        SLog.w("openSDK_LOG.AssistActivity", "-->finish by timeout");
                        this.a.finish();
                    }
                }
            }
        };
    }
    
    private void a(final Bundle bundle) {
        final String string = bundle.getString("viaShareType");
        final String string2 = bundle.getString("callbackAction");
        final String string3 = bundle.getString("url");
        final String string4 = bundle.getString("openId");
        final String string5 = bundle.getString("appId");
        final boolean equals = "shareToQQ".equals((Object)string2);
        String s = "";
        String s2;
        if (equals) {
            s = "ANDROIDQQ.SHARETOQQ.XX";
            s2 = "10";
        }
        else if ("shareToQzone".equals((Object)string2)) {
            s = "ANDROIDQQ.SHARETOQZ.XX";
            s2 = "11";
        }
        else {
            s2 = "";
        }
        if (!k.a((Context)this, string3)) {
            final IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string2);
            if (listnerWithAction != null) {
                listnerWithAction.onError(new UiError(-6, "\u6253\u5f00\u6d4f\u89c8\u5668\u5931\u8d25!", (String)null));
            }
            com.tencent.open.b.e.a().a(string4, string5, s, s2, "3", "1", string, "0", "2", "0");
            this.finish();
        }
        else {
            com.tencent.open.b.e.a().a(string4, string5, s, s2, "3", "0", string, "0", "2", "0");
        }
        this.getIntent().removeExtra("shareH5");
    }
    
    public static Intent getAssistActivityIntent(final Context context) {
        return new Intent(context, (Class)AssistActivity.class);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        final StringBuilder sb = new StringBuilder();
        sb.append("--onActivityResult--requestCode: ");
        sb.append(n);
        sb.append(" | resultCode: ");
        sb.append(n2);
        sb.append("data = null ? ");
        sb.append(intent == null);
        SLog.i("openSDK_LOG.AssistActivity", sb.toString());
        super.onActivityResult(n, n2, intent);
        if (n == 0) {
            return;
        }
        if (intent != null) {
            intent.putExtra("key_action", "action_login");
        }
        this.setResultData(n, intent);
        if (!this.f) {
            SLog.i("openSDK_LOG.AssistActivity", "onActivityResult finish immediate");
            this.finish();
        }
        else {
            new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable(this) {
                final AssistActivity a;
                
                public void run() {
                    SLog.i("openSDK_LOG.AssistActivity", "onActivityResult finish delay");
                    this.a.finish();
                }
            }, 200L);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        this.getWindow().addFlags(67108864);
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        this.f = this.getIntent().getBooleanExtra(Constants.KEY_RESTORE_LANDSCAPE, false);
        final StringBuilder sb = new StringBuilder();
        sb.append("--onCreate-- mRestoreLandscape=");
        sb.append(this.f);
        SLog.i("openSDK_LOG.AssistActivity", sb.toString());
        if (this.getIntent() == null) {
            SLog.e("openSDK_LOG.AssistActivity", "-->onCreate--getIntent() returns null");
            this.finish();
        }
        final Intent intent = (Intent)this.getIntent().getParcelableExtra("openSDK_LOG.AssistActivity.ExtraIntent");
        int intExtra;
        if (intent == null) {
            intExtra = 0;
        }
        else {
            intExtra = intent.getIntExtra("key_request_code", 0);
        }
        String stringExtra;
        if (intent == null) {
            stringExtra = "";
        }
        else {
            stringExtra = intent.getStringExtra("appid");
        }
        this.d = stringExtra;
        final Bundle bundleExtra = this.getIntent().getBundleExtra("h5_share_data");
        if (bundle != null) {
            this.c = bundle.getBoolean("RESTART_FLAG");
            this.a = bundle.getBoolean("RESUME_FLAG", false);
        }
        if (!this.c) {
            if (bundleExtra == null) {
                if (intent != null) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("--onCreate--activityIntent not null, will start activity, reqcode = ");
                    sb2.append(intExtra);
                    SLog.i("openSDK_LOG.AssistActivity", sb2.toString());
                    try {
                        final String queryParameter = intent.getData().getQueryParameter("share_id");
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("com.tencent.tauth.opensdk.SHARE_SUCCESS_AND_STAY_QQ_");
                        sb3.append(queryParameter);
                        final IntentFilter intentFilter = new IntentFilter(sb3.toString());
                        if (this.e == null) {
                            this.e = new QQStayReceiver();
                        }
                        this.registerReceiver((BroadcastReceiver)this.e, intentFilter);
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append("registerReceiver exception : ");
                        sb4.append(ex.getMessage());
                        SLog.i("openSDK_LOG.AssistActivity", sb4.toString());
                    }
                    this.startActivityForResult(intent, intExtra);
                }
                else {
                    SLog.e("openSDK_LOG.AssistActivity", "--onCreate--activityIntent is null");
                    this.finish();
                }
            }
            else {
                SLog.w("openSDK_LOG.AssistActivity", "--onCreate--h5 bundle not null, will open browser");
                this.a(bundleExtra);
            }
        }
        else {
            SLog.d("openSDK_LOG.AssistActivity", "is restart");
        }
    }
    
    protected void onDestroy() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onDestroy");
        super.onDestroy();
        final QQStayReceiver e = this.e;
        if (e != null) {
            this.unregisterReceiver((BroadcastReceiver)e);
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent");
        super.onNewIntent(intent);
        final int intExtra = intent.getIntExtra("key_request_code", -1);
        if (intExtra == 10108) {
            intent.putExtra("key_action", "action_request_avatar");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                this.moveTaskToBack(true);
            }
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                this.finish();
            }
        }
        else if (intExtra == 10109) {
            intent.putExtra("key_action", "action_request_set_emotion");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                this.moveTaskToBack(true);
            }
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                this.finish();
            }
        }
        else if (intExtra == 10110) {
            intent.putExtra("key_action", "action_request_dynamic_avatar");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                this.moveTaskToBack(true);
            }
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                this.finish();
            }
        }
        else if (intExtra == 10111) {
            intent.putExtra("key_action", "joinGroup");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                this.moveTaskToBack(true);
            }
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                this.finish();
            }
        }
        else if (intExtra == 10112) {
            intent.putExtra("key_action", "bindGroup");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                this.moveTaskToBack(true);
            }
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                this.finish();
            }
        }
        else {
            intent.putExtra("key_action", "action_share");
            this.setResult(-1, intent);
            if (!this.isFinishing()) {
                SLog.i("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
                this.finish();
            }
        }
    }
    
    protected void onPause() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onPause");
        this.b.removeMessages(0);
        super.onPause();
    }
    
    protected void onResume() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onResume");
        super.onResume();
        final Intent intent = this.getIntent();
        if (intent.getBooleanExtra("is_login", false)) {
            return;
        }
        if (!intent.getBooleanExtra("is_qq_mobile_share", false) && this.c && !this.isFinishing()) {
            this.finish();
        }
        if (this.a) {
            this.b.sendMessage(this.b.obtainMessage(0));
            return;
        }
        this.a = true;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        SLog.i("openSDK_LOG.AssistActivity", "--onSaveInstanceState--");
        bundle.putBoolean("RESTART_FLAG", true);
        bundle.putBoolean("RESUME_FLAG", this.a);
        super.onSaveInstanceState(bundle);
    }
    
    protected void onStart() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onStart");
        super.onStart();
    }
    
    protected void onStop() {
        SLog.i("openSDK_LOG.AssistActivity", "-->onStop");
        super.onStop();
    }
    
    public void setResultData(final int n, final Intent intent) {
        if (intent == null) {
            SLog.w("openSDK_LOG.AssistActivity", "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            this.setResult(0);
            if (n == 11101) {
                com.tencent.open.b.e.a().a("", this.d, "2", "1", "7", "2");
            }
            return;
        }
        try {
            final String stringExtra = intent.getStringExtra("key_response");
            SLog.d("openSDK_LOG.AssistActivity", "--setResultDataForLogin-- ");
            if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                final JSONObject jsonObject = new JSONObject(stringExtra);
                final String optString = jsonObject.optString("openid");
                final String optString2 = jsonObject.optString("access_token");
                final String optString3 = jsonObject.optString("proxy_code");
                final long optLong = jsonObject.optLong("proxy_expires_in");
                if (!TextUtils.isEmpty((CharSequence)optString) && !TextUtils.isEmpty((CharSequence)optString2)) {
                    SLog.i("openSDK_LOG.AssistActivity", "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
                    this.setResult(-1, intent);
                    com.tencent.open.b.e.a().a(optString, this.d, "2", "1", "7", "0");
                }
                else if (!TextUtils.isEmpty((CharSequence)optString3) && optLong != 0L) {
                    SLog.i("openSDK_LOG.AssistActivity", "--setResultData--proxy_code and proxy_expires_in are valid");
                    this.setResult(-1, intent);
                }
                else {
                    SLog.w("openSDK_LOG.AssistActivity", "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                    this.setResult(0, intent);
                    com.tencent.open.b.e.a().a("", this.d, "2", "1", "7", "1");
                }
            }
            else {
                SLog.w("openSDK_LOG.AssistActivity", "--setResultData--response is empty, setResult ACTIVITY_OK");
                this.setResult(-1, intent);
            }
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.AssistActivity", "--setResultData--parse response failed");
            ex.printStackTrace();
        }
    }
    
    private class QQStayReceiver extends BroadcastReceiver
    {
        final AssistActivity a;
        
        private QQStayReceiver(final AssistActivity a) {
            this.a = a;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            String s = "#";
            final Intent intent2 = new Intent();
            intent2.putExtra("key_action", "action_share");
            try {
                final Uri data = (Uri)intent.getParcelableExtra("uriData");
                final String string = data.toString();
                if (!string.contains((CharSequence)"#")) {
                    s = "?";
                }
                final String[] split = string.substring(string.indexOf(s) + 1).split("&");
                for (int length = split.length, i = 0; i < length; ++i) {
                    final String[] split2 = split[i].split("=");
                    intent2.putExtra(split2[0], split2[1]);
                }
                intent2.setData(data);
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("QQStayReceiver parse uri error : ");
                sb.append(ex.getMessage());
                SLog.i("openSDK_LOG.AssistActivity", sb.toString());
                intent2.putExtra("result", "error");
                intent2.putExtra("response", "parse error.");
            }
            this.a.setResult(-1, intent2);
        }
    }
}
