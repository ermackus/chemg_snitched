package com.tencent.open.miniapp;

import com.tencent.tauth.IUiListener;
import com.tencent.open.TDialog;
import android.net.Uri;
import com.tencent.open.utils.i;
import android.content.Intent;
import com.tencent.open.utils.k;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import android.app.Activity;
import com.tencent.connect.auth.c;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import java.util.Arrays;
import java.util.List;
import com.tencent.connect.common.BaseApi;

public class MiniApp extends BaseApi
{
    public static final int MINIAPP_CONTEXT_NULL = -6;
    public static final int MINIAPP_ID_EMPTY = -1;
    public static final int MINIAPP_ID_NOT_DIGIT = -4;
    public static final int MINIAPP_SHOULD_DOWNLOAD = -2;
    public static final String MINIAPP_SRC_ID = "21";
    public static final int MINIAPP_SRC_ID_NOT_DIGIT = -3;
    public static final int MINIAPP_SUCCESS = 0;
    public static final String MINIAPP_TYPE_NORMAL = "mini_program_or_game";
    public static final int MINIAPP_UNKNOWN_TYPE = -5;
    public static final String MINIAPP_VERSION_DEVELOP = "develop";
    public static final String MINIAPP_VERSION_RELEASE = "release";
    public static final String MINIAPP_VERSION_TRIAL = "trial";
    public static final int MINIAPP_VERSION_WRONG = -7;
    public static final List<String> OPEN_CONNECT_DEMO_MINI_APP_VERSIONS;
    
    static {
        OPEN_CONNECT_DEMO_MINI_APP_VERSIONS = Arrays.asList((Object[])new String[] { "develop", "trial", "release" });
    }
    
    public MiniApp(final Context context, final QQToken qqToken) {
        super(qqToken);
    }
    
    public MiniApp(final Context context, final c c, final QQToken qqToken) {
        super(c, qqToken);
    }
    
    public MiniApp(final QQToken qqToken) {
        super(qqToken);
    }
    
    public int startMiniApp(final Activity activity, final String s, final String s2, final String s3, final String s4, final String s5) {
        if (activity == null) {
            SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_CONTEXT_NULL : -6");
            return -6;
        }
        if (!"mini_program_or_game".equals((Object)s)) {
            SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_UNKNOWN_TYPE : -5");
            return -5;
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_ID_EMPTY : -1");
            return -1;
        }
        for (int i = 0; i < s2.length(); ++i) {
            if (!Character.isDigit(s2.charAt(i))) {
                SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_ID_NOT_DIGIT : -4");
                return -4;
            }
        }
        String s6;
        if ((s6 = s4) == null) {
            s6 = "";
        }
        if (!MiniApp.OPEN_CONNECT_DEMO_MINI_APP_VERSIONS.contains((Object)s5)) {
            SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_VERSION_WRONG : -7");
            return -7;
        }
        if (k.e((Context)activity)) {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(String.format("mqqapi://connect_miniapp/launch?app_type=%1$s&mini_app_id=%2$s&version=1&src_type=app&app_name=%3$s&app_id=%4$s&src_id=%5$s&mini_app_path=%6$s&mini_app_type=%7$s&open_id=%8$s", new Object[] { s, s2, k.l(i.a((Context)activity)), k.l(this.c.getAppId()), s3, k.l(s6), k.l(s5), k.l(this.c.getOpenId()) })));
            intent.putExtra("pkg_name", activity.getPackageName());
            activity.startActivity(intent);
            SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_SUCCESS : 0");
            return 0;
        }
        try {
            new TDialog((Context)activity, "", this.a(""), null, this.c).show();
        }
        catch (final RuntimeException ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Show download dialog exception:");
            sb.append(ex.getMessage());
            SLog.e("openSDK_LOG.MiniApp", sb.toString());
        }
        SLog.i("openSDK_LOG.MiniApp", "Result is MINIAPP_SHOULD_DOWNLOAD : -2");
        return -2;
    }
}
