package com.tencent.open.im;

import com.tencent.tauth.IUiListener;
import com.tencent.open.TDialog;
import android.net.Uri;
import android.content.Intent;
import com.tencent.open.utils.k;
import android.text.TextUtils;
import android.app.Activity;
import com.tencent.connect.auth.c;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import com.tencent.connect.common.BaseApi;

public class IM extends BaseApi
{
    public static final String CHAT_TYPE_AIO = "thirdparty2c";
    public static final String CHAT_TYPE_AUDIO_CHAT = "audio_chat";
    public static final String CHAT_TYPE_VIDEO_CHAT = "video_chat";
    public static final int IM_LENGTH_SHORT = -3;
    public static final int IM_SHOULD_DOWNLOAD = -2;
    public static final int IM_SUCCESS = 0;
    public static final int IM_UIN_EMPTY = -1;
    public static final int IM_UIN_NOT_DIGIT = -4;
    public static final int IM_UNKNOWN_TYPE = -5;
    
    public IM(final Context context, final QQToken qqToken) {
        super(qqToken);
    }
    
    public IM(final Context context, final c c, final QQToken qqToken) {
        super(c, qqToken);
    }
    
    public IM(final QQToken qqToken) {
        super(qqToken);
    }
    
    public int startIMConversation(final Activity activity, final String s, final String s2, final String s3) {
        if (s == null || (!s.equals((Object)"thirdparty2c") && !s.equals((Object)"audio_chat") && !s.equals((Object)"video_chat"))) {
            return -5;
        }
        if (!s.equals((Object)"audio_chat")) {
            s.equals((Object)"video_chat");
        }
        if (TextUtils.isEmpty((CharSequence)s2)) {
            return -1;
        }
        if (s2.length() < 5) {
            return -3;
        }
        for (int i = 0; i < s2.length(); ++i) {
            if (!Character.isDigit(s2.charAt(i))) {
                return -4;
            }
        }
        if (k.d((Context)activity)) {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(String.format("mqqapi://im/chat?chat_type=%1$s&uin=%2$s&version=1&src_type=app&open_id=%3$s&app_id=%4$s&app_pkg_name=%5$s", new Object[] { s, s2, k.l(this.c.getOpenId()), k.l(this.c.getAppId()), k.l(s3) })));
            intent.putExtra("pkg_name", s3);
            activity.startActivity(intent);
            return 0;
        }
        try {
            new TDialog((Context)activity, "", this.a(""), null, this.c).show();
        }
        catch (final RuntimeException ex) {
            ex.printStackTrace();
        }
        return -2;
    }
}
