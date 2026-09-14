package com.tencent.connect.share;

import com.tencent.open.TDialog;
import com.tencent.connect.common.UIListenerManager;
import android.net.Uri;
import android.content.Intent;
import com.tencent.open.utils.i;
import android.util.Base64;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.f;
import java.io.File;
import java.util.ArrayList;
import android.os.SystemClock;
import com.tencent.open.b.e;
import com.tencent.tauth.UiError;
import com.tencent.open.utils.d;
import com.tencent.open.utils.c;
import com.tencent.open.utils.k;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import android.os.Bundle;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class QQShare extends BaseApi
{
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 512;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 128;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_ARK_INFO = "share_to_qq_ark_info";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_GAME_MESSAGE_EXT = "game_message_ext";
    public static final String SHARE_TO_QQ_GAME_TAG_NAME = "game_tag_name";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QQ_MINI_PROGRAM = 7;
    public static final String SHARE_TO_QQ_MINI_PROGRAM_APPID = "mini_program_appid";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_PATH = "mini_program_path";
    public static final String SHARE_TO_QQ_MINI_PROGRAM_TYPE = "mini_program_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType;
    
    public QQShare(final Context context, final QQToken qqToken) {
        super(qqToken);
        this.mViaShareQQType = "";
    }
    
    private void b(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        final String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        final StringBuilder sb = new StringBuilder();
        sb.append("shareToMobileQQ -- imageUrl: ");
        sb.append(string);
        SLog.v("openSDK_LOG.QQShare", sb.toString());
        if (!TextUtils.isEmpty((CharSequence)string)) {
            if (k.h(string)) {
                if (!k.f((Context)activity, "4.3.0")) {
                    this.d(activity, bundle, uiListener);
                }
                else {
                    new c(activity).a(string, (d)new d(this, bundle, string2, string3, uiListener, activity) {
                        final Bundle a;
                        final String b;
                        final String c;
                        final IUiListener d;
                        final Activity e;
                        final QQShare f;
                        
                        public void a(final int n, final String s) {
                            if (n == 0) {
                                this.a.putString("imageLocalUrl", s);
                            }
                            else if (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c)) {
                                final IUiListener d = this.d;
                                if (d != null) {
                                    d.onError(new UiError(-6, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!", (String)null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                }
                                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.f.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                return;
                            }
                            this.f.d(this.e, this.a, this.d);
                        }
                        
                        public void a(final int n, final ArrayList<String> list) {
                        }
                    });
                }
            }
            else {
                bundle.putString("imageUrl", (String)null);
                if (k.f((Context)activity, "4.3.0")) {
                    SLog.d("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                    this.d(activity, bundle, uiListener);
                }
                else {
                    final boolean n = k.n(string);
                    final boolean c = k.c();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("shareToMobileQQ -- QQ Version is > 4.3.0:isAppSpecificDir=");
                    sb2.append(n);
                    sb2.append(",hasSDPermission:");
                    sb2.append(c);
                    SLog.d("openSDK_LOG.QQShare", sb2.toString());
                    a.a((Context)activity, string, (d)new d(this, bundle, string2, string3, uiListener, activity) {
                        final Bundle a;
                        final String b;
                        final String c;
                        final IUiListener d;
                        final Activity e;
                        final QQShare f;
                        
                        public void a(final int n, final String s) {
                            if (n == 0) {
                                this.a.putString("imageLocalUrl", s);
                            }
                            else if (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c)) {
                                final IUiListener d = this.d;
                                if (d != null) {
                                    d.onError(new UiError(-6, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!", (String)null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                }
                                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.f.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                return;
                            }
                            this.f.d(this.e, this.a, this.d);
                        }
                        
                        public void a(final int n, final ArrayList<String> list) {
                            if (n == 0) {
                                this.a.putStringArrayList("imageLocalUrlArray", (ArrayList)list);
                            }
                            else if (TextUtils.isEmpty((CharSequence)this.b) && TextUtils.isEmpty((CharSequence)this.c)) {
                                final IUiListener d = this.d;
                                if (d != null) {
                                    d.onError(new UiError(-6, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!", (String)null));
                                    SLog.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                }
                                com.tencent.open.b.e.a().a(1, "SHARE_CHECK_SDK", "1000", this.f.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                                return;
                            }
                            this.f.d(this.e, this.a, this.d);
                        }
                    });
                }
            }
        }
        else if (bundle.getInt("req_type", 1) == 5) {
            this.c(activity, bundle, uiListener);
        }
        else {
            this.d(activity, bundle, uiListener);
        }
        SLog.i("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }
    
    private void c(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        final String string = bundle.getString("imageLocalUrl");
        final long length = new File(string).length();
        String string2 = null;
        if (length >= 5242880L) {
            if (uiListener != null) {
                uiListener.onError(new UiError(-16, "\u56fe\u7247\u592a\u5927\uff0c\u8bf7\u538b\u7f29\u52305M\u5185\u518d\u5206\u4eab!", (String)null));
            }
            SLog.e("openSDK_LOG.QQShare", "doShareImageToQQ -- error: \u56fe\u7247\u592a\u5927\uff0c\u8bf7\u538b\u7f29\u52305M\u5185\u518d\u5206\u4eab!");
            return;
        }
        final File a = f.a("Images");
        if (a != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(a.getAbsolutePath());
            sb.append(File.separator);
            sb.append(Constants.QQ_SHARE_TEMP_DIR);
            string2 = sb.toString();
        }
        else {
            SLog.i("openSDK_LOG.QQShare", "doShareImageToQQ() getExternalFilesDir return null");
        }
        final File file = new File(string);
        final String absolutePath = file.getAbsolutePath();
        final String name = file.getName();
        final boolean n = k.n(absolutePath);
        final boolean c = k.c();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("doShareImageToQQ() check file: isAppSpecificDir=");
        sb2.append(n);
        sb2.append(",hasSDPermission=");
        sb2.append(c);
        sb2.append(",fileDir=");
        sb2.append(absolutePath);
        SLog.i("openSDK_LOG.QQShare", sb2.toString());
        final ArrayList list = new ArrayList(2);
        String string3 = null;
        Label_0377: {
            if (!n) {
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(string2);
                    sb3.append(File.separator);
                    sb3.append(name);
                    string3 = sb3.toString();
                    final boolean a2 = k.a((Context)activity, absolutePath, string3);
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("doShareImageToQQ() sd permission not denied. copy to app specific:");
                    sb4.append(string3);
                    sb4.append(",isSuccess=");
                    sb4.append(a2);
                    SLog.i("openSDK_LOG.QQShare", sb4.toString());
                    if (a2) {
                        break Label_0377;
                    }
                }
            }
            string3 = absolutePath;
        }
        list.add((Object)absolutePath);
        list.add((Object)string3);
        final StringBuilder sb5 = new StringBuilder("doShareImageToQQ() destFilePaths=[");
        sb5.append((String)list.get(0));
        sb5.append(",");
        sb5.append((String)list.get(1));
        sb5.append("]");
        SLog.i("openSDK_LOG.QQShare", sb5.toString());
        bundle.putStringArrayList("imageLocalUrlArray", list);
        this.d(activity, bundle, uiListener);
    }
    
    private void d(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        final StringBuffer sb = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        final String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        final String string4 = bundle.getString("targetUrl");
        final String string5 = bundle.getString("audio_url");
        final int int1 = bundle.getInt("req_type", 1);
        final String string6 = bundle.getString("share_to_qq_ark_info");
        final String string7 = bundle.getString("mini_program_appid");
        final String string8 = bundle.getString("mini_program_path");
        final String string9 = bundle.getString("mini_program_type");
        final int int2 = bundle.getInt("cflag", 0);
        final String string10 = bundle.getString("share_qq_ext_str");
        String s;
        if ((s = k.a((Context)activity)) == null) {
            s = bundle.getString("appName");
        }
        final String string11 = bundle.getString("imageLocalUrl");
        final ArrayList stringArrayList = bundle.getStringArrayList("imageLocalUrlArray");
        final String appId = this.c.getAppId();
        final String openIdWithCache = this.c.getOpenIdWithCache();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("doShareToQQ -- openid: ");
        sb2.append(openIdWithCache);
        sb2.append(",appName=");
        sb2.append(s);
        SLog.i("openSDK_LOG.QQShare", sb2.toString());
        if (stringArrayList != null && stringArrayList.size() >= 2) {
            String s2 = (String)stringArrayList.get(0);
            if (s2 == null) {
                s2 = "";
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("&file_data=");
            sb3.append(Base64.encodeToString(k.j(s2), 2));
            sb.append(sb3.toString());
            String s4;
            final String s3 = s4 = (String)stringArrayList.get(1);
            if (int1 == 7) {
                s4 = s3;
                if (!TextUtils.isEmpty((CharSequence)s3)) {
                    s4 = s3;
                    if (i.c((Context)activity, "8.3.3") < 0) {
                        s4 = null;
                        SLog.e("openSDK_LOG.QQShare", "doShareToQQ() share to mini program set file uri empty");
                    }
                }
            }
            final Uri a = k.a(activity, appId, s4);
            if (a != null) {
                sb.append("&file_uri=");
                sb.append(Base64.encodeToString(k.j(a.toString()), 2));
            }
        }
        else if (!TextUtils.isEmpty((CharSequence)string11)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&file_data=");
            sb4.append(Base64.encodeToString(k.j(string11), 2));
            sb.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&image_url=");
            sb5.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb5.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("&title=");
            sb6.append(Base64.encodeToString(k.j(string2), 2));
            sb.append(sb6.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&description=");
            sb7.append(Base64.encodeToString(k.j(string3), 2));
            sb.append(sb7.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb8 = new StringBuilder();
            sb8.append("&share_id=");
            sb8.append(appId);
            sb.append(sb8.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string4)) {
            final StringBuilder sb9 = new StringBuilder();
            sb9.append("&url=");
            sb9.append(Base64.encodeToString(k.j(string4), 2));
            sb.append(sb9.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            String string12 = s;
            if (s.length() > 20) {
                final StringBuilder sb10 = new StringBuilder();
                sb10.append(s.substring(0, 20));
                sb10.append("...");
                string12 = sb10.toString();
            }
            final StringBuilder sb11 = new StringBuilder();
            sb11.append("&app_name=");
            sb11.append(Base64.encodeToString(k.j(string12), 2));
            sb.append(sb11.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)openIdWithCache)) {
            final StringBuilder sb12 = new StringBuilder();
            sb12.append("&open_id=");
            sb12.append(Base64.encodeToString(k.j(openIdWithCache), 2));
            sb.append(sb12.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string5)) {
            final StringBuilder sb13 = new StringBuilder();
            sb13.append("&audioUrl=");
            sb13.append(Base64.encodeToString(k.j(string5), 2));
            sb.append(sb13.toString());
        }
        final StringBuilder sb14 = new StringBuilder();
        sb14.append("&req_type=");
        sb14.append(Base64.encodeToString(k.j(String.valueOf(int1)), 2));
        sb.append(sb14.toString());
        if (!TextUtils.isEmpty((CharSequence)string7)) {
            final StringBuilder sb15 = new StringBuilder();
            sb15.append("&mini_program_appid=");
            sb15.append(Base64.encodeToString(k.j(String.valueOf((Object)string7)), 2));
            sb.append(sb15.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string8)) {
            final StringBuilder sb16 = new StringBuilder();
            sb16.append("&mini_program_path=");
            sb16.append(Base64.encodeToString(k.j(String.valueOf((Object)string8)), 2));
            sb.append(sb16.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string9)) {
            final StringBuilder sb17 = new StringBuilder();
            sb17.append("&mini_program_type=");
            sb17.append(Base64.encodeToString(k.j(String.valueOf((Object)string9)), 2));
            sb.append(sb17.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string6)) {
            final StringBuilder sb18 = new StringBuilder();
            sb18.append("&share_to_qq_ark_info=");
            sb18.append(Base64.encodeToString(k.j(string6), 2));
            sb.append(sb18.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)string10)) {
            final StringBuilder sb19 = new StringBuilder();
            sb19.append("&share_qq_ext_str=");
            sb19.append(Base64.encodeToString(k.j(string10), 2));
            sb.append(sb19.toString());
        }
        final StringBuilder sb20 = new StringBuilder();
        sb20.append("&cflag=");
        sb20.append(Base64.encodeToString(k.j(String.valueOf(int2)), 2));
        sb.append(sb20.toString());
        final boolean c = k.c();
        final StringBuilder sb21 = new StringBuilder();
        sb21.append("&third_sd=");
        sb21.append(Base64.encodeToString(k.j(String.valueOf(c)), 2));
        sb.append(sb21.toString());
        final StringBuilder sb22 = new StringBuilder();
        sb22.append("doShareToQQ -- url: ");
        sb22.append(sb.toString());
        SLog.v("openSDK_LOG.QQShare", sb22.toString());
        com.tencent.connect.a.a.a(f.a(), this.c, "requireApi", new String[] { "shareToNativeQQ" });
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (k.f((Context)activity, "4.6.0")) {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            if (this.a(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(11103, uiListener);
                this.a(activity, intent, 11103);
            }
        }
        else {
            SLog.i("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (UIListenerManager.getInstance().setListnerWithAction("shareToQQ", uiListener) != null) {
                SLog.i("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            if (this.a(intent)) {
                this.a(activity, 10103, intent, true);
            }
        }
        String s5;
        if (int2 == 1) {
            s5 = "11";
        }
        else {
            s5 = "10";
        }
        if (this.a(intent)) {
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQQ.XX", s5, "3", "0", this.mViaShareQQType, "0", "1", "0");
            e.a().a(0, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        }
        else {
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQQ.XX", s5, "3", "1", this.mViaShareQQType, "0", "1", "0");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        SLog.i("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }
    
    public void releaseResource() {
    }
    
    public void shareToQQ(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        final String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        final String string4 = bundle.getString("targetUrl");
        final String string5 = bundle.getString("imageLocalUrl");
        final String string6 = bundle.getString("mini_program_appid");
        final String string7 = bundle.getString("mini_program_path");
        final int int1 = bundle.getInt("req_type", 1);
        final StringBuilder sb = new StringBuilder();
        sb.append("shareToQQ -- type: ");
        sb.append(int1);
        SLog.i("openSDK_LOG.QQShare", sb.toString());
        if (int1 != 1) {
            if (int1 != 2) {
                if (int1 != 5) {
                    if (int1 == 7) {
                        this.mViaShareQQType = "9";
                    }
                }
                else {
                    this.mViaShareQQType = "2";
                }
            }
            else {
                this.mViaShareQQType = "3";
            }
        }
        else {
            this.mViaShareQQType = "1";
        }
        if (!k.a() && k.f((Context)activity, "4.5.0")) {
            uiListener.onError(new UiError(-6, "\u5206\u4eab\u56fe\u7247\u5931\u8d25\uff0c\u68c0\u6d4b\u4e0d\u5230SD\u5361!", (String)null));
            SLog.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
            return;
        }
        if (int1 == 5) {
            if (k.f((Context)activity, "4.3.0")) {
                uiListener.onError(new UiError(-6, "\u4f4e\u7248\u672c\u624bQ\u4e0d\u652f\u6301\u8be5\u9879\u529f\u80fd!", (String)null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
                return;
            }
            if (!k.i(string5)) {
                uiListener.onError(new UiError(-6, "\u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!", (String)null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ -- error: \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                return;
            }
        }
        if (int1 != 5 && int1 != 7) {
            if (TextUtils.isEmpty((CharSequence)string4) || (!string4.startsWith("http://") && !string4.startsWith("https://"))) {
                uiListener.onError(new UiError(-6, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!", (String)null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
                return;
            }
            if (TextUtils.isEmpty((CharSequence)string2)) {
                uiListener.onError(new UiError(-6, "title\u4e0d\u80fd\u4e3a\u7a7a!", (String)null));
                SLog.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
                return;
            }
        }
        if (int1 == 7) {
            if (TextUtils.isEmpty((CharSequence)string6) || TextUtils.isEmpty((CharSequence)string7) || TextUtils.isEmpty((CharSequence)string4) || TextUtils.isEmpty((CharSequence)this.c.getAppId())) {
                uiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!", "appid || path || url empty."));
                return;
            }
            if (i.c((Context)activity, "8.0.8") < 0 && i.d((Context)activity, "3.1") < 0 && i.a((Context)activity, "com.tencent.qqlite") == null) {
                uiListener.onError(new UiError(-5, "\u4f4e\u7248\u672c\u624bQ\u4e0d\u652f\u6301\u8be5\u9879\u529f\u80fd!", "\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u5206\u4eab\u5c0f\u7a0b\u5e8f"));
                return;
            }
            if (TextUtils.isEmpty((CharSequence)string2) || TextUtils.isEmpty((CharSequence)string3)) {
                uiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!", "title || summary empty."));
                return;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)string) && !string.startsWith("http://") && !string.startsWith("https://") && !new File(string).exists()) {
            uiListener.onError(new UiError(-6, "\u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!", (String)null));
            SLog.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)string2) && string2.length() > 128) {
            bundle.putString("title", k.a(string2, 128, (String)null, (String)null));
        }
        if (!TextUtils.isEmpty((CharSequence)string3) && string3.length() > 512) {
            bundle.putString("summary", k.a(string3, 512, (String)null, (String)null));
        }
        if (k.a((Context)activity, bundle.getInt("cflag", 0) == 1)) {
            SLog.i("openSDK_LOG.QQShare", "shareToQQ, support share");
            this.b(activity, bundle, uiListener);
        }
        else {
            try {
                SLog.w("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                new TDialog((Context)activity, "", this.a(""), null, this.c).show();
            }
            catch (final RuntimeException ex) {
                SLog.e("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", (Throwable)ex);
                ex.printStackTrace();
                uiListener.onError(new UiError(-6, "\u6ca1\u6709\u5728\u4e3b\u7ebf\u7a0b\u8c03\u7528\uff01", (String)null));
            }
        }
        SLog.i("openSDK_LOG.QQShare", "shareToQQ() -- end.");
    }
}
