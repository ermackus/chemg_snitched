package com.tencent.connect.share;

import java.util.Set;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer;
import com.tencent.open.TDialog;
import com.tencent.tauth.UiError;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.SystemClock;
import com.tencent.open.b.e;
import android.net.Uri;
import android.content.Intent;
import com.tencent.connect.a.a;
import com.tencent.open.utils.f;
import android.util.Base64;
import com.tencent.open.utils.k;
import java.net.URLEncoder;
import android.text.TextUtils;
import org.json.JSONObject;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import android.os.Bundle;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class QzonePublish extends BaseApi
{
    public static final String HULIAN_CALL_BACK = "hulian_call_back";
    public static final String HULIAN_EXTRA_SCENE = "hulian_extra_scene";
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_EXTMAP = "extMap";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";
    
    public QzonePublish(final Context context, final QQToken qqToken) {
        super(qqToken);
    }
    
    private void b(final Activity activity, Bundle keySet, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        final StringBuffer sb = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        final ArrayList stringArrayList = ((Bundle)keySet).getStringArrayList("imageUrl");
        final String string = ((Bundle)keySet).getString("summary");
        final int int1 = ((Bundle)keySet).getInt("req_type", 3);
        Object o = ((Bundle)keySet).getString("appName");
        final String string2 = ((Bundle)keySet).getString("videoPath");
        final int int2 = ((Bundle)keySet).getInt("videoDuration");
        final long long1 = ((Bundle)keySet).getLong("videoSize");
        Object o3 = null;
        Label_0267: {
            Label_0257: {
                Label_0245: {
                    try {
                        final Bundle bundle = ((Bundle)keySet).getBundle("extMap");
                        if (bundle != null) {
                            keySet = (Exception)bundle.keySet();
                            Object o2 = o;
                            try {
                                o2 = o;
                                final JSONObject jsonObject = new JSONObject();
                                o2 = o;
                                final Iterator iterator = ((Set)keySet).iterator();
                                keySet = (Exception)o;
                                o = iterator;
                                while (true) {
                                    o2 = keySet;
                                    Label_0200: {
                                        if (!((Iterator)o).hasNext()) {
                                            break Label_0200;
                                        }
                                        o2 = keySet;
                                        final String s = (String)((Iterator)o).next();
                                        o2 = keySet;
                                        Label_0197: {
                                            if (TextUtils.isEmpty((CharSequence)bundle.getString(s))) {
                                                break Label_0197;
                                            }
                                            try {
                                                jsonObject.put(s, (Object)bundle.getString(s));
                                                continue;
                                                o = jsonObject.toString();
                                                break Label_0267;
                                                o3 = (o = keySet);
                                                iftrue(Label_0257:)(jsonObject.length() <= 0);
                                            }
                                            catch (final Exception o2) {
                                                o = keySet;
                                            }
                                        }
                                    }
                                }
                            }
                            catch (final Exception keySet) {
                                o = o2;
                                break Label_0245;
                            }
                        }
                        break Label_0257;
                    }
                    catch (final Exception ex) {}
                }
                Object o2 = keySet;
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", (Throwable)o2);
            }
            final String s2 = "";
            o3 = o;
            o = s2;
        }
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("openId:");
        sb2.append(openId);
        SLog.v("openSDK_LOG.QzonePublish", sb2.toString());
        String s3;
        String s4;
        if (3 == int1 && stringArrayList != null) {
            final StringBuffer sb3 = new StringBuffer();
            final StringBuffer sb4 = new StringBuffer();
            for (int size = stringArrayList.size(), i = 0; i < size; ++i) {
                sb3.append(URLEncoder.encode((String)stringArrayList.get(i)));
                final String a = k.a(appId, activity, (String)stringArrayList.get(i), uiListener);
                if (!TextUtils.isEmpty((CharSequence)a)) {
                    sb4.append(URLEncoder.encode(a));
                }
                if (i != size - 1) {
                    sb3.append(";");
                    sb4.append(";");
                }
            }
            s3 = openId;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&image_url=");
            sb5.append(Base64.encodeToString(k.j(sb3.toString()), 2));
            sb.append(sb5.toString());
            if (!TextUtils.isEmpty((CharSequence)sb4.toString())) {
                final StringBuilder sb6 = new StringBuilder();
                sb6.append("&image_uri=");
                sb6.append(Base64.encodeToString(k.j(sb4.toString()), 2));
                sb.append(sb6.toString());
            }
            s4 = "7";
        }
        else {
            final String s5 = "";
            s3 = openId;
            s4 = s5;
        }
        if (4 == int1) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&videoPath=");
            sb7.append(Base64.encodeToString(k.j(string2), 2));
            sb.append(sb7.toString());
            final String a2 = k.a(appId, activity, string2, uiListener);
            if (!TextUtils.isEmpty((CharSequence)a2)) {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append("&videoUri=");
                sb8.append(Base64.encodeToString(k.j(a2), 2));
                sb.append(sb8.toString());
            }
            final StringBuilder sb9 = new StringBuilder();
            sb9.append("&videoDuration=");
            sb9.append(Base64.encodeToString(k.j(String.valueOf(int2)), 2));
            sb.append(sb9.toString());
            final StringBuilder sb10 = new StringBuilder();
            sb10.append("&videoSize=");
            sb10.append(Base64.encodeToString(k.j(String.valueOf(long1)), 2));
            sb.append(sb10.toString());
            s4 = "8";
        }
        if (!TextUtils.isEmpty((CharSequence)string)) {
            final StringBuilder sb11 = new StringBuilder();
            sb11.append("&description=");
            sb11.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb11.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb12 = new StringBuilder();
            sb12.append("&share_id=");
            sb12.append(appId);
            sb.append(sb12.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)o3)) {
            final StringBuilder sb13 = new StringBuilder();
            sb13.append("&app_name=");
            sb13.append(Base64.encodeToString(k.j((String)o3), 2));
            sb.append(sb13.toString());
        }
        if (!k.e(s3)) {
            final StringBuilder sb14 = new StringBuilder();
            sb14.append("&open_id=");
            sb14.append(Base64.encodeToString(k.j(s3), 2));
            sb.append(sb14.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)o)) {
            final StringBuilder sb15 = new StringBuilder();
            sb15.append("&share_qzone_ext_str=");
            sb15.append(Base64.encodeToString(k.j((String)o), 2));
            sb.append(sb15.toString());
        }
        final StringBuilder sb16 = new StringBuilder();
        sb16.append("&req_type=");
        sb16.append(Base64.encodeToString(k.j(String.valueOf(int1)), 2));
        sb.append(sb16.toString());
        final StringBuilder sb17 = new StringBuilder();
        sb17.append("doPublishToQzone, url: ");
        sb17.append(sb.toString());
        SLog.v("openSDK_LOG.QzonePublish", sb17.toString());
        a.a(f.a(), this.c, "requireApi", new String[] { "shareToNativeQQ" });
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (this.a(intent)) {
            this.a(activity, 10104, intent, false);
            e.a().a(0, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", s4, "0", "1", "0");
        }
        else {
            SLog.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
            e.a().a(this.c.getOpenId(), this.c.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", s4, "0", "1", "0");
        }
        SLog.i("openSDK_LOG", "doPublishToQzone() --end");
    }
    
    public void publishToQzone(final Activity activity, final Bundle bundle, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (bundle == null) {
            uiListener.onError(new UiError(-6, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a", (String)null));
            SLog.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a");
            return;
        }
        if (!k.f((Context)activity)) {
            uiListener.onError(new UiError(-15, "\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u8bf7\u4e0b\u8f7d\u5b89\u88c5\u6700\u65b0\u7248\u624bQ", (String)null));
            SLog.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog((Context)activity, "", this.a(""), null, this.c).show();
            return;
        }
        final String a = k.a((Context)activity);
        int i = 0;
        String s;
        if (a == null) {
            s = bundle.getString("appName");
        }
        else {
            s = a;
            if (a.length() > 20) {
                final StringBuilder sb = new StringBuilder();
                sb.append(a.substring(0, 20));
                sb.append("...");
                s = sb.toString();
            }
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            bundle.putString("appName", s);
        }
        final int int1 = bundle.getInt("req_type");
        if (int1 == 3) {
            final ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
            if (stringArrayList != null && stringArrayList.size() > 0) {
                while (i < stringArrayList.size()) {
                    int n = i;
                    if (!k.i((String)stringArrayList.get(i))) {
                        stringArrayList.remove(i);
                        n = i - 1;
                    }
                    i = n + 1;
                }
                bundle.putStringArrayList("imageUrl", stringArrayList);
            }
            this.b(activity, bundle, uiListener);
            SLog.i("openSDK_LOG.QzonePublish", "publishToQzone() --end");
        }
        else {
            if (int1 != 4) {
                uiListener.onError(new UiError(-5, "\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b", (String)null));
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end\u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                e.a().a(1, "SHARE_CHECK_SDK", "1000", this.c.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() \u8bf7\u9009\u62e9\u652f\u6301\u7684\u5206\u4eab\u7c7b\u578b");
                return;
            }
            final String string = bundle.getString("videoPath");
            if (!k.i(string)) {
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                uiListener.onError(new UiError(-5, "\u8bf7\u9009\u62e9\u6709\u6548\u7684\u89c6\u9891\u6587\u4ef6", (String)null));
                return;
            }
            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)new QzonePublish$1(this, string, bundle, activity, uiListener));
            mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)new QzonePublish$2(this, uiListener));
            try {
                mediaPlayer.setDataSource(string);
                mediaPlayer.prepareAsync();
            }
            catch (final Exception ex) {
                SLog.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                uiListener.onError(new UiError(-5, "\u8bf7\u9009\u62e9\u6709\u6548\u7684\u89c6\u9891\u6587\u4ef6", (String)null));
            }
        }
    }
}
