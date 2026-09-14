package com.tencent.connect.share;

import com.tencent.open.TDialog;
import com.tencent.open.utils.i;
import com.tencent.tauth.UiError;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import android.os.SystemClock;
import com.tencent.open.b.e;
import com.tencent.connect.common.UIListenerManager;
import android.net.Uri;
import android.content.Intent;
import com.tencent.connect.a.a;
import com.tencent.open.utils.f;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.open.utils.k;
import java.net.URLEncoder;
import org.json.JSONObject;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import android.os.Bundle;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class QzoneShare extends BaseApi
{
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_EXTMAP = "extMap";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_MINI_PROGRAM = 7;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
    private boolean a;
    private boolean d;
    private boolean e;
    private boolean f;
    public String mViaShareQzoneType;
    
    public QzoneShare(final Context context, final QQToken qqToken) {
        super(qqToken);
        this.mViaShareQzoneType = "";
        this.a = true;
        this.d = false;
        this.e = false;
        this.f = false;
    }
    
    private void b(final Activity activity, Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QzoneShare", "doshareToQzone() --start");
        final StringBuffer sb = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        final ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        final String string = bundle.getString("title");
        final String string2 = bundle.getString("summary");
        final String string3 = bundle.getString("targetUrl");
        final String string4 = bundle.getString("audio_url");
        int int1 = bundle.getInt("req_type", 1);
        final String string5 = bundle.getString("appName");
        Object string6 = bundle.getString("mini_program_appid");
        final String string7 = bundle.getString("mini_program_path");
        final String string8 = bundle.getString("mini_program_type");
        final int int2 = bundle.getInt("cflag", 0);
        final String string9 = bundle.getString("share_qq_ext_str");
        final String s = "";
        int n = 0;
        Object string10 = null;
        Label_0362: {
            Label_0342: {
                try {
                    final Bundle bundle2 = bundle.getBundle("extMap");
                    if (bundle2 != null) {
                        final Set keySet = bundle2.keySet();
                        n = int1;
                        string10 = string6;
                        try {
                            n = int1;
                            string10 = string6;
                            final JSONObject jsonObject = new JSONObject();
                            n = int1;
                            string10 = string6;
                            final Iterator iterator = keySet.iterator();
                            bundle = (Bundle)string6;
                            while (true) {
                                n = int1;
                                string10 = bundle;
                                Label_0258: {
                                    if (!iterator.hasNext()) {
                                        break Label_0258;
                                    }
                                    n = int1;
                                    string10 = bundle;
                                    final Object next = iterator.next();
                                    string10 = bundle;
                                    String s2;
                                    try {
                                        s2 = (String)next;
                                        final JSONObject jsonObject2 = jsonObject;
                                        final String s3 = s2;
                                        final Bundle bundle3 = bundle2;
                                        final String s4 = s2;
                                        final Object o = bundle3.get(s4);
                                        jsonObject2.put(s3, o);
                                        bundle = (Bundle)string10;
                                        continue;
                                    }
                                    catch (final Exception string6) {
                                        bundle = (Bundle)string10;
                                        break Label_0342;
                                    }
                                    try {
                                        final JSONObject jsonObject2 = jsonObject;
                                        final String s3 = s2;
                                        final Bundle bundle3 = bundle2;
                                        final String s4 = s2;
                                        final Object o = bundle3.get(s4);
                                        jsonObject2.put(s3, o);
                                        bundle = (Bundle)string10;
                                        continue;
                                        final int n2 = int1;
                                        final Bundle bundle4 = bundle;
                                        string10 = s;
                                        n = n2;
                                        string6 = bundle4;
                                        iftrue(Label_0362:)(keySet.size() <= 0);
                                        string10 = jsonObject.toString();
                                        n = n2;
                                        string6 = bundle4;
                                    }
                                    catch (final Exception string6) {}
                                }
                            }
                        }
                        catch (final Exception string6) {
                            int1 = n;
                            bundle = (Bundle)string10;
                            break Label_0342;
                        }
                    }
                    string10 = s;
                    n = int1;
                    break Label_0362;
                }
                catch (final Exception ex) {
                    bundle = (Bundle)string6;
                    string6 = ex;
                }
            }
            SLog.e("openSDK_LOG.QzoneShare", "ShareToQzone()  --error parse extmap", (Throwable)string6);
            string6 = bundle;
            n = int1;
            string10 = s;
        }
        final String appId = this.c.getAppId();
        final String openIdWithCache = this.c.getOpenIdWithCache();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("openId:");
        sb2.append(openIdWithCache);
        SLog.v("openSDK_LOG.QzoneShare", sb2.toString());
        if (stringArrayList != null && stringArrayList.size() > 0) {
            final String s5 = (String)stringArrayList.get(0);
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("&image_url=");
            sb3.append(Base64.encodeToString(k.j(URLEncoder.encode(s5)), 2));
            sb.append(sb3.toString());
            if (!k.h(s5)) {
                final String a = k.a(appId, activity, s5, uiListener);
                if (!TextUtils.isEmpty((CharSequence)a)) {
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("&image_uri=");
                    sb4.append(Base64.encodeToString(k.j(URLEncoder.encode(a)), 2));
                    sb.append(sb4.toString());
                }
            }
        }
        if (!TextUtils.isEmpty((CharSequence)string)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&title=");
            sb5.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb5.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("&description=");
            sb6.append(Base64.encodeToString(k.j(string2), 2));
            sb.append(sb6.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&share_id=");
            sb7.append(appId);
            sb.append(sb7.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            final StringBuilder sb8 = new StringBuilder();
            sb8.append("&url=");
            sb8.append(Base64.encodeToString(k.j(string3), 2));
            sb.append(sb8.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string5)) {
            final StringBuilder sb9 = new StringBuilder();
            sb9.append("&app_name=");
            sb9.append(Base64.encodeToString(k.j(string5), 2));
            sb.append(sb9.toString());
        }
        if (!k.e(openIdWithCache)) {
            final StringBuilder sb10 = new StringBuilder();
            sb10.append("&open_id=");
            sb10.append(Base64.encodeToString(k.j(openIdWithCache), 2));
            sb.append(sb10.toString());
        }
        if (!k.e(string4)) {
            final StringBuilder sb11 = new StringBuilder();
            sb11.append("&audioUrl=");
            sb11.append(Base64.encodeToString(k.j(string4), 2));
            sb.append(sb11.toString());
        }
        final StringBuilder sb12 = new StringBuilder();
        sb12.append("&req_type=");
        sb12.append(Base64.encodeToString(k.j(String.valueOf(n)), 2));
        sb.append(sb12.toString());
        if (!TextUtils.isEmpty((CharSequence)string6)) {
            final StringBuilder sb13 = new StringBuilder();
            sb13.append("&mini_program_appid=");
            sb13.append(Base64.encodeToString(k.j(String.valueOf(string6)), 2));
            sb.append(sb13.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string7)) {
            final StringBuilder sb14 = new StringBuilder();
            sb14.append("&mini_program_path=");
            sb14.append(Base64.encodeToString(k.j(String.valueOf((Object)string7)), 2));
            sb.append(sb14.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string8)) {
            final StringBuilder sb15 = new StringBuilder();
            sb15.append("&mini_program_type=");
            sb15.append(Base64.encodeToString(k.j(String.valueOf((Object)string8)), 2));
            sb.append(sb15.toString());
        }
        if (!k.e(string9)) {
            final StringBuilder sb16 = new StringBuilder();
            sb16.append("&share_qq_ext_str=");
            sb16.append(Base64.encodeToString(k.j(string9), 2));
            sb.append(sb16.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string10)) {
            final StringBuilder sb17 = new StringBuilder();
            sb17.append("&share_qzone_ext_str=");
            sb17.append(Base64.encodeToString(k.j((String)string10), 2));
            sb.append(sb17.toString());
        }
        final StringBuilder sb18 = new StringBuilder();
        sb18.append("&cflag=");
        sb18.append(Base64.encodeToString(k.j(String.valueOf(int2)), 2));
        sb.append(sb18.toString());
        final StringBuilder sb19 = new StringBuilder();
        sb19.append("doshareToQzone, url: ");
        sb19.append(sb.toString());
        SLog.v("openSDK_LOG.QzoneShare", sb19.toString());
        com.tencent.connect.a.a.a(com.tencent.open.utils.f.a(), this.c, "requireApi", new String[] { "shareToNativeQQ" });
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (k.g((Context)activity, "4.6.0")) {
            if (this.a(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(11104, uiListener);
                this.a(activity, intent, 11104);
            }
            SLog.i("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is < 4.6.0");
        }
        else {
            SLog.i("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is > 4.6.0");
            if (UIListenerManager.getInstance().setListnerWithAction("shareToQzone", uiListener) != null) {
                SLog.i("openSDK_LOG.QzoneShare", "doShareToQzone() -- do listener onCancel()");
            }
            if (this.a(intent)) {
                this.a(activity, 10104, intent, false);
            }
        }
        if (this.a(intent)) {
            com.tencent.open.b.e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "0", this.mViaShareQzoneType, "0", "1", "0");
            com.tencent.open.b.e.a().a(0, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        }
        else {
            com.tencent.open.b.e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", this.mViaShareQzoneType, "0", "1", "0");
            com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        SLog.i("openSDK_LOG", "doShareToQzone() --end");
    }
    
    public void releaseResource() {
    }
    
    public void shareToQzone(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QzoneShare", "shareToQzone() -- start");
        if (bundle == null) {
            uiListener.onError(new UiError(-6, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a", (String)null));
            SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() params is null");
            com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a");
            return;
        }
        final String string = bundle.getString("title");
        final String string2 = bundle.getString("summary");
        final String string3 = bundle.getString("targetUrl");
        final String string4 = bundle.getString("mini_program_appid");
        final String string5 = bundle.getString("mini_program_path");
        final ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        final String a = k.a((Context)activity);
        String string7 = null;
        Label_0205: {
            String string6;
            if (a == null) {
                string6 = bundle.getString("appName");
            }
            else {
                string6 = a;
                if (a.length() > 20) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(a.substring(0, 20));
                    sb.append("...");
                    string7 = sb.toString();
                    break Label_0205;
                }
            }
            string7 = string6;
        }
        final int int1 = bundle.getInt("req_type");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("shareToQzone() get SHARE_TO_QZONE_KEY_TYPE: ");
        sb2.append(int1);
        SLog.e("openSDK_LOG.QzoneShare", sb2.toString());
        if (int1 != 1) {
            if (int1 != 5) {
                this.mViaShareQzoneType = "1";
            }
            else {
                this.mViaShareQzoneType = "2";
            }
        }
        else {
            this.mViaShareQzoneType = "1";
        }
        String string8;
        if (int1 != 1) {
            if (int1 == 5) {
                uiListener.onError(new UiError(-5, "\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b", (String)null));
                SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() error--end\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() \u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                return;
            }
            if (int1 != 7) {
                if (k.e(string) && k.e(string2)) {
                    if (stringArrayList != null && stringArrayList.size() != 0) {
                        this.a = false;
                        string8 = string;
                    }
                    else {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("\u6765\u81ea");
                        sb3.append(string7);
                        sb3.append("\u7684\u5206\u4eab");
                        string8 = sb3.toString();
                        this.a = true;
                    }
                }
                else {
                    this.a = true;
                    string8 = string;
                }
                this.d = false;
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("-->shareToQzone, default needTitle = true, shareType = ");
                sb4.append(int1);
                SLog.e("openSDK_LOG.QzoneShare", sb4.toString());
                this.e = true;
                this.f = false;
            }
            else {
                if (TextUtils.isEmpty((CharSequence)string4) || TextUtils.isEmpty((CharSequence)string5)) {
                    uiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!", "appid or path empty."));
                }
                this.e = false;
                this.f = false;
                this.a = false;
                string8 = string;
            }
        }
        else {
            SLog.e("openSDK_LOG.QzoneShare", "-->shareToQzone, SHARE_TO_QZONE_TYPE_IMAGE_TEXT needTitle = true");
            this.a = true;
            this.d = false;
            this.e = true;
            this.f = false;
            string8 = string;
        }
        if (!k.a() && k.g((Context)activity, "4.5.0")) {
            uiListener.onError(new UiError(-6, "\u5206\u4eab\u56fe\u7247\u5931\u8d25\uff0c\u68c0\u6d4b\u4e0d\u5230SD\u5361!", (String)null));
            SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() sdcard is null--end");
            com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u5206\u4eab\u56fe\u7247\u5931\u8d25\uff0c\u68c0\u6d4b\u4e0d\u5230SD\u5361!");
            return;
        }
        if (this.a) {
            if (TextUtils.isEmpty((CharSequence)string3)) {
                uiListener.onError(new UiError(-5, "targetUrl\u4e3a\u5fc5\u586b\u9879\uff0c\u8bf7\u8865\u5145\u540e\u5206\u4eab", (String)null));
                SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl\u4e3a\u5fc5\u586b\u9879\uff0c\u8bf7\u8865\u5145\u540e\u5206\u4eab");
                return;
            }
            if (!k.h(string3)) {
                uiListener.onError(new UiError(-5, "targetUrl\u6709\u8bef", (String)null));
                SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl\u6709\u8bef");
                return;
            }
        }
        if (this.d) {
            bundle.putString("title", "");
            bundle.putString("summary", "");
        }
        else {
            if (this.e && k.e(string8)) {
                uiListener.onError(new UiError(-6, "title\u4e0d\u80fd\u4e3a\u7a7a!", (String)null));
                SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() title is null--end");
                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() title is null");
                return;
            }
            if (!k.e(string8) && string8.length() > 200) {
                bundle.putString("title", k.a(string8, 200, (String)null, (String)null));
            }
            if (!k.e(string2) && string2.length() > 600) {
                bundle.putString("summary", k.a(string2, 600, (String)null, (String)null));
            }
        }
        if (!TextUtils.isEmpty((CharSequence)string7)) {
            bundle.putString("appName", string7);
        }
        if (stringArrayList != null && (stringArrayList == null || stringArrayList.size() != 0)) {
            int n;
            for (int i = 0; i < stringArrayList.size(); i = n + 1) {
                final String s = (String)stringArrayList.get(i);
                n = i;
                if (!k.h(s)) {
                    n = i;
                    if (!k.i(s)) {
                        stringArrayList.remove(i);
                        n = i - 1;
                    }
                }
            }
            if (stringArrayList.size() == 0) {
                uiListener.onError(new UiError(-6, "\u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!", (String)null));
                SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                return;
            }
            bundle.putStringArrayList("imageUrl", stringArrayList);
        }
        else if (this.f) {
            uiListener.onError(new UiError(-6, "\u7eaf\u56fe\u5206\u4eab\uff0cimageUrl \u4e0d\u80fd\u4e3a\u7a7a", (String)null));
            SLog.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
            com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() imageUrl is null");
            return;
        }
        if (!k.g((Context)activity, "4.6.0")) {
            SLog.i("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
            this.b(activity, bundle, uiListener);
        }
        else if (i.c((Context)activity, "4.2.0") >= 0 && i.c((Context)activity, "4.6.0") < 0) {
            SLog.w("openSDK_LOG.QzoneShare", "shareToQzone() qqver between 4.2.0 and 4.6.0, will use qqshare");
            final QQShare qqShare = new QQShare((Context)activity, this.c);
            if (stringArrayList != null && stringArrayList.size() > 0) {
                final String s2 = (String)stringArrayList.get(0);
                if (int1 == 5 && !k.i(s2)) {
                    uiListener.onError(new UiError(-6, "\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247", (String)null));
                    SLog.e("openSDK_LOG.QzoneShare", "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                    com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                    return;
                }
                bundle.putString("imageLocalUrl", s2);
            }
            if (!k.g((Context)activity, "4.5.0")) {
                bundle.putInt("cflag", 1);
            }
            qqShare.shareToQQ(activity, bundle, uiListener);
        }
        else {
            SLog.w("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
            new TDialog((Context)activity, "", this.a(""), null, this.c).show();
        }
        SLog.i("openSDK_LOG.QzoneShare", "shareToQzone() --end");
    }
}
