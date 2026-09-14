package com.tencent.tauth;

import com.tencent.open.utils.i;
import android.content.Context;
import android.content.Intent;
import com.tencent.connect.common.AssistActivity;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.utils.k;
import com.tencent.open.log.SLog;
import android.net.Uri;
import android.app.Activity;

public class AuthActivity extends Activity
{
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a;
    
    private void a(final Uri uri) {
        SLog.i("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (uri != null && uri.toString() != null) {
            final String string = uri.toString();
            final String s = "";
            if (!string.equals((Object)"")) {
                final String string2 = uri.toString();
                final Bundle a = k.a(string2.substring(string2.indexOf("#") + 1));
                if (a == null) {
                    SLog.w("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
                    this.finish();
                    return;
                }
                final String string3 = a.getString("action");
                final StringBuilder sb = new StringBuilder();
                sb.append("-->handleActionUri, action: ");
                sb.append(string3);
                SLog.i("openSDK_LOG.AuthActivity", sb.toString());
                if (string3 == null) {
                    this.finish();
                }
                else if (!string3.equals((Object)"shareToQQ") && !string3.equals((Object)"shareToQzone") && !string3.equals((Object)"sendToMyComputer") && !string3.equals((Object)"shareToTroopBar")) {
                    if (string3.equals((Object)"addToQQFavorites")) {
                        final Intent intent = this.getIntent();
                        intent.putExtras(a);
                        intent.putExtra("key_action", "action_share");
                        final IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string3);
                        if (listnerWithAction != null) {
                            UIListenerManager.getInstance().handleDataToListener(intent, (IUiListener)listnerWithAction);
                        }
                        this.finish();
                    }
                    else if (string3.equals((Object)"sharePrize")) {
                        final Intent launchIntentForPackage = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
                        final String string4 = a.getString("response");
                        String string5;
                        try {
                            string5 = k.d(string4).getString("activityid");
                        }
                        catch (final Exception ex) {
                            SLog.e("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", (Throwable)ex);
                            string5 = s;
                        }
                        if (!TextUtils.isEmpty((CharSequence)string5)) {
                            launchIntentForPackage.putExtra("sharePrize", true);
                            final Bundle bundle = new Bundle();
                            bundle.putString("activityid", string5);
                            launchIntentForPackage.putExtras(bundle);
                        }
                        this.startActivity(launchIntentForPackage);
                        this.finish();
                    }
                    else if (string3.equals((Object)"sdkSetAvatar")) {
                        final boolean booleanExtra = this.getIntent().getBooleanExtra("stay_back_stack", false);
                        final Intent intent2 = new Intent((Context)this, (Class)AssistActivity.class);
                        intent2.putExtra("key_request_code", 10108);
                        intent2.putExtra("stay_back_stack", booleanExtra);
                        intent2.putExtras(a);
                        intent2.setFlags(603979776);
                        this.startActivity(intent2);
                        this.finish();
                    }
                    else if ("sdkSetDynamicAvatar".equals((Object)string3)) {
                        final boolean booleanExtra2 = this.getIntent().getBooleanExtra("stay_back_stack", false);
                        final Intent intent3 = new Intent((Context)this, (Class)AssistActivity.class);
                        intent3.putExtra("key_request_code", 10110);
                        intent3.putExtra("stay_back_stack", booleanExtra2);
                        intent3.putExtras(a);
                        intent3.setFlags(603979776);
                        this.startActivity(intent3);
                        this.finish();
                    }
                    else if (string3.equals((Object)"sdkSetEmotion")) {
                        final boolean booleanExtra3 = this.getIntent().getBooleanExtra("stay_back_stack", false);
                        final Intent intent4 = new Intent((Context)this, (Class)AssistActivity.class);
                        intent4.putExtra("key_request_code", 10109);
                        intent4.putExtra("stay_back_stack", booleanExtra3);
                        intent4.putExtras(a);
                        intent4.setFlags(603979776);
                        this.startActivity(intent4);
                        this.finish();
                    }
                    else if (string3.equals((Object)"bindGroup")) {
                        SLog.i("openSDK_LOG.AuthActivity", "-->handleActionUri--bind group callback.");
                        final boolean booleanExtra4 = this.getIntent().getBooleanExtra("stay_back_stack", false);
                        final Intent intent5 = new Intent((Context)this, (Class)AssistActivity.class);
                        intent5.putExtra("key_request_code", 10112);
                        intent5.putExtra("stay_back_stack", booleanExtra4);
                        intent5.putExtras(a);
                        intent5.setFlags(603979776);
                        this.startActivity(intent5);
                        this.finish();
                    }
                    else if (string3.equals((Object)"joinGroup")) {
                        SLog.i("openSDK_LOG.AuthActivity", "-->handleActionUri--join group callback. ");
                        final boolean booleanExtra5 = this.getIntent().getBooleanExtra("stay_back_stack", false);
                        final Intent intent6 = new Intent((Context)this, (Class)AssistActivity.class);
                        intent6.putExtra("key_request_code", 10111);
                        intent6.putExtra("stay_back_stack", booleanExtra5);
                        intent6.putExtras(a);
                        intent6.setFlags(603979776);
                        this.startActivity(intent6);
                        this.finish();
                    }
                    else {
                        this.finish();
                    }
                }
                else {
                    if (string3.equals((Object)"shareToQzone") && i.a((Context)this, "com.tencent.mobileqq") != null && i.c((Context)this, "5.2.0") < 0 && ++AuthActivity.a == 2) {
                        AuthActivity.a = 0;
                        this.finish();
                        return;
                    }
                    SLog.i("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
                    final Intent intent7 = new Intent((Context)this, (Class)AssistActivity.class);
                    intent7.putExtras(a);
                    intent7.setFlags(603979776);
                    this.startActivity(intent7);
                    this.finish();
                }
                return;
            }
        }
        SLog.w("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
        this.finish();
    }
    
    public void finish() {
        try {
            super.finish();
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.AuthActivity", "activity finish exception: ", (Throwable)ex);
        }
    }
    
    protected void onCreate(Bundle data) {
        super.onCreate(data);
        if (this.getIntent() == null) {
            SLog.w("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            this.finish();
            return;
        }
        data = null;
        try {
            data = (Bundle)this.getIntent().getData();
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.AuthActivity", "onCreate exception: ", (Throwable)ex);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("-->onCreate, uri: ");
        sb.append((Object)data);
        SLog.v("openSDK_LOG.AuthActivity", sb.toString());
        try {
            this.a((Uri)data);
        }
        catch (final Exception ex2) {
            SLog.e("openSDK_LOG.AuthActivity", "onCreate exception: ", (Throwable)ex2);
            this.finish();
        }
    }
}
