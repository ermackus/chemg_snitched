package com.tencent.connect.emotion;

import com.tencent.tauth.UiError;
import com.tencent.connect.common.UIListenerManager;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.open.utils.i;
import java.util.Iterator;
import android.util.Base64;
import com.tencent.open.log.SLog;
import android.content.Context;
import com.tencent.open.utils.k;
import android.net.Uri;
import java.util.ArrayList;
import android.app.Activity;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.connect.common.BaseApi;

public class QQEmotion extends BaseApi
{
    private IUiListener a;
    
    public QQEmotion(final QQToken qqToken) {
        super(qqToken);
    }
    
    private String a(final Activity activity, final ArrayList<Uri> list) {
        final StringBuilder sb = new StringBuilder();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            final Uri a = k.a(activity, this.c.getAppId(), k.b((Context)activity, (Uri)iterator.next()));
            if (a == null) {
                SLog.e("QQEmotion", "getFilePathListJson: grantedUri = null");
            }
            else {
                sb.append((Object)a);
                sb.append(";");
            }
        }
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-->getFilePathListJson listStr : ");
        sb2.append(string);
        SLog.i("QQEmotion", sb2.toString());
        return Base64.encodeToString(k.j(string), 2);
    }
    
    private boolean a(final Context context, final ArrayList<Uri> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        if (list.size() > 9) {
            final StringBuilder sb = new StringBuilder();
            sb.append("isLegality -->illegal, file count > 9, count = ");
            sb.append(list.size());
            SLog.i("QQEMOTION", sb.toString());
            return false;
        }
        long n = 0L;
        for (int i = 0; i < list.size(); ++i) {
            final long a = k.a(context, (Uri)list.get(i));
            if (a > 1048576L) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("isLegality -->illegal, fileSize: ");
                sb2.append(a);
                SLog.i("QQEMOTION", sb2.toString());
                return false;
            }
            n += a;
        }
        if (n > 3145728L) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("isLegality -->illegal, totalSize: ");
            sb3.append(n);
            SLog.i("QQEMOTION", sb3.toString());
            return false;
        }
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("isLegality -->legal, totalSize: ");
        sb4.append(n);
        SLog.i("QQEMOTION", sb4.toString());
        return true;
    }
    
    public void setEmotions(final Activity activity, final ArrayList<Uri> list, final IUiListener a) {
        final IUiListener a2 = this.a;
        if (a2 != null) {
            a2.onCancel();
        }
        this.a = a;
        if (!i.b((Context)activity)) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673a\u672a\u5b89\u88c5QQ\uff0c\u8bf7\u5b89\u88c5\u6700\u65b0\u7248QQ\u540e\u518d\u8bd5\u3002", 1).show();
            return;
        }
        if (i.c((Context)activity, "8.0.0") < 0) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u5f53\u524d\u624b\u673aQQ\u7248\u672c\u8fc7\u4f4e\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u8868\u60c5\u529f\u80fd\u3002", 1).show();
            return;
        }
        if (!this.a(activity.getApplicationContext(), list)) {
            Toast.makeText(activity.getApplicationContext(), (CharSequence)"\u56fe\u7247\u4e0d\u7b26\u5408\u8981\u6c42\uff0c\u4e0d\u652f\u6301\u8bbe\u7f6e\u8868\u60c5\u529f\u80fd\u3002", 1).show();
            return;
        }
        final String a3 = k.a((Context)activity);
        final StringBuffer sb = new StringBuffer("mqqapi://profile/sdk_face_collection?");
        if (!TextUtils.isEmpty((CharSequence)a3)) {
            String string = a3;
            if (a3.length() > 20) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(a3.substring(0, 20));
                sb2.append("...");
                string = sb2.toString();
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("&app_name=");
            sb3.append(Base64.encodeToString(k.j(string), 2));
            sb.append(sb3.toString());
        }
        final String appId = this.c.getAppId();
        final String openId = this.c.getOpenId();
        if (!TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&share_id=");
            sb4.append(appId);
            sb.append(sb4.toString());
        }
        if (!TextUtils.isEmpty((CharSequence)openId)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&open_id=");
            sb5.append(Base64.encodeToString(k.j(openId), 2));
            sb.append(sb5.toString());
        }
        final StringBuilder sb6 = new StringBuilder();
        sb6.append("&sdk_version=");
        sb6.append(Base64.encodeToString(k.j("3.5.4.lite"), 2));
        sb.append(sb6.toString());
        final String a4 = this.a(activity, list);
        if (!TextUtils.isEmpty((CharSequence)a4)) {
            final StringBuilder sb7 = new StringBuilder();
            sb7.append("&set_uri_list=");
            sb7.append(a4);
            sb.append(sb7.toString());
            final StringBuilder sb8 = new StringBuilder();
            sb8.append("-->set avatar, url: ");
            sb8.append(sb.toString());
            SLog.v("QQEMOTION", sb8.toString());
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(sb.toString()));
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)list);
            if (this.a(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(10109, a);
                this.a(activity, 10109, intent, false);
            }
            return;
        }
        a.onError(new UiError(-6, "\u672a\u77e5\u9519\u8bef!", "picPathList is null"));
    }
}
