package com.tencent.connect.auth;

import com.tencent.connect.a.a;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.DialogInterface$OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.Dialog;
import android.graphics.drawable.PaintDrawable;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.widget.TextView;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View$OnClickListener;
import android.graphics.Bitmap;
import java.io.InputStream;
import android.content.res.AssetManager;
import java.io.IOException;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.Rect;
import android.graphics.NinePatch;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import com.tencent.tauth.DefaultUiListener;
import android.content.Context;
import com.tencent.open.utils.g;
import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.k;
import android.text.TextUtils;
import android.content.Intent;
import android.os.SystemClock;
import com.tencent.open.b.e;
import com.tencent.connect.common.UIListenerManager;
import androidx.fragment.app.Fragment;
import com.tencent.connect.common.Constants;
import android.os.Bundle;
import com.tencent.open.utils.j;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.h;
import com.tencent.open.utils.i;
import android.webkit.CookieSyncManager;
import com.tencent.open.utils.f;
import java.util.Map;
import com.tencent.open.log.SLog;
import android.os.Build;
import android.app.Activity;
import java.lang.ref.WeakReference;
import com.tencent.tauth.IUiListener;
import com.tencent.connect.common.BaseApi;

public class AuthAgent extends BaseApi
{
    public static final String KEY_FORCE_QR_LOGIN = "KEY_FORCE_QR_LOGIN";
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_NAME;
    public static final int SECURE_LIB_VERSION = 5;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
    private IUiListener a;
    private String d;
    private WeakReference<Activity> e;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(AuthAgent.SECURE_LIB_FILE_NAME);
        sb.append(".so");
        AuthAgent.SECURE_LIB_NAME = sb.toString();
        final String cpu_ABI = Build.CPU_ABI;
        if (cpu_ABI != null && !cpu_ABI.equals((Object)"")) {
            if (cpu_ABI.equalsIgnoreCase("arm64-v8a")) {
                AuthAgent.SECURE_LIB_FILE_NAME = "libwbsafeedit_64";
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(AuthAgent.SECURE_LIB_FILE_NAME);
                sb2.append(".so");
                AuthAgent.SECURE_LIB_NAME = sb2.toString();
                SLog.i("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
            }
            else if (cpu_ABI.equalsIgnoreCase("x86")) {
                AuthAgent.SECURE_LIB_FILE_NAME = "libwbsafeedit_x86";
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(AuthAgent.SECURE_LIB_FILE_NAME);
                sb3.append(".so");
                AuthAgent.SECURE_LIB_NAME = sb3.toString();
                SLog.i("openSDK_LOG.AuthAgent", "is x86 architecture");
            }
            else if (cpu_ABI.equalsIgnoreCase("x86_64")) {
                AuthAgent.SECURE_LIB_FILE_NAME = "libwbsafeedit_x86_64";
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(AuthAgent.SECURE_LIB_FILE_NAME);
                sb4.append(".so");
                AuthAgent.SECURE_LIB_NAME = sb4.toString();
                SLog.i("openSDK_LOG.AuthAgent", "is x86_64 architecture");
            }
            else {
                AuthAgent.SECURE_LIB_FILE_NAME = "libwbsafeedit";
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(AuthAgent.SECURE_LIB_FILE_NAME);
                sb5.append(".so");
                AuthAgent.SECURE_LIB_NAME = sb5.toString();
                SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
            }
        }
        else {
            AuthAgent.SECURE_LIB_FILE_NAME = "libwbsafeedit";
            final StringBuilder sb6 = new StringBuilder();
            sb6.append(AuthAgent.SECURE_LIB_FILE_NAME);
            sb6.append(".so");
            AuthAgent.SECURE_LIB_NAME = sb6.toString();
            SLog.i("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        }
    }
    
    public AuthAgent(final QQToken qqToken) {
        super(qqToken);
    }
    
    private int a(final boolean b, final IUiListener uiListener, final boolean b2, final Map<String, Object> map) {
        CookieSyncManager.createInstance(f.a());
        final Bundle a = this.a();
        if (b) {
            a.putString("isadd", "1");
        }
        a.putString("scope", this.d);
        a.putString("client_id", this.c.getAppId());
        if (AuthAgent.isOEM) {
            final StringBuilder sb = new StringBuilder();
            sb.append("desktop_m_qq-");
            sb.append(AuthAgent.installChannel);
            sb.append("-");
            sb.append("android");
            sb.append("-");
            sb.append(AuthAgent.registerChannel);
            sb.append("-");
            sb.append(AuthAgent.businessId);
            a.putString("pf", sb.toString());
        }
        else {
            a.putString("pf", "openmobile_android");
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(System.currentTimeMillis() / 1000L);
        sb2.append("");
        final String string = sb2.toString();
        a.putString("sign", i.b(f.a(), string));
        a.putString("time", string);
        a.putString("display", "mobile");
        a.putString("response_type", "token");
        a.putString("redirect_uri", "auth://tauth.qq.com/");
        a.putString("cancel_display", "1");
        a.putString("switch", "1");
        a.putString("compat_v", "1");
        if (b2) {
            a.putString("style", "qr");
        }
        final String a2 = this.a(map);
        a.putString("show_download_ui", a2);
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("OpenUi, showDialog -- start, showDownloadUi=");
        sb3.append(a2);
        SLog.i("openSDK_LOG.AuthAgent", sb3.toString());
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(h.a().a(f.a(), "https://openmobile.qq.com/oauth2.0/m_authorize?"));
        sb4.append(HttpUtils.encodeUrl(a));
        final String string2 = sb4.toString();
        final c c = new c(f.a(), uiListener, true, false);
        SLog.d("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        j.b((Runnable)new AuthAgent$1(this, string2, (IUiListener)c));
        SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return 2;
    }
    
    private String a(final Bundle bundle) {
        final String string = bundle.getString("status_os");
        final String string2 = bundle.getString("status_machine");
        final String string3 = bundle.getString("status_version");
        final String string4 = bundle.getString("sdkv");
        final String string5 = bundle.getString("client_id");
        final String string6 = bundle.getString("need_pay");
        final String string7 = bundle.getString("pf");
        final StringBuilder sb = new StringBuilder();
        sb.append("os=");
        sb.append(string);
        sb.append(", machine=");
        sb.append(string2);
        sb.append(", version=");
        sb.append(string3);
        sb.append(", sdkv=");
        sb.append(string4);
        sb.append(", appId=");
        sb.append(string5);
        sb.append(", needPay=");
        sb.append(string6);
        sb.append(", pf=");
        sb.append(string7);
        SLog.d("openSDK_LOG.AuthAgent", sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        String s = string;
        if (string == null) {
            s = "";
        }
        sb2.append(s);
        String s2;
        if ((s2 = string2) == null) {
            s2 = "";
        }
        sb2.append(s2);
        String s3;
        if ((s3 = string3) == null) {
            s3 = "";
        }
        sb2.append(s3);
        String s4;
        if ((s4 = string4) == null) {
            s4 = "";
        }
        sb2.append(s4);
        String s5;
        if ((s5 = string5) == null) {
            s5 = "";
        }
        sb2.append(s5);
        String s6;
        if ((s6 = string6) == null) {
            s6 = "";
        }
        sb2.append(s6);
        String s7;
        if ((s7 = string7) == null) {
            s7 = "";
        }
        sb2.append(s7);
        return sb2.toString();
    }
    
    private String a(final Map<String, Object> map) {
        String s2;
        final String s = s2 = "true";
        if (map != null) {
            if (map.isEmpty()) {
                s2 = s;
            }
            else {
                final Object value = map.get((Object)Constants.KEY_ENABLE_SHOW_DOWNLOAD_URL);
                if (!(value instanceof Boolean) || (boolean)value) {
                    s2 = s;
                }
                else {
                    s2 = "false";
                }
            }
        }
        return s2;
    }
    
    private void a(final Bundle bundle, final Map<String, Object> map) {
    }
    
    private boolean a(final Activity activity, final Fragment fragment, final Map<String, Object> map, final boolean b, final Object[] array) {
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        final Intent b2 = this.b("com.tencent.open.agent.AgentActivity");
        if (b2 != null) {
            final Bundle a = this.a();
            if (b) {
                a.putString("isadd", "1");
            }
            a.putString("scope", this.d);
            a.putString("client_id", this.c.getAppId());
            if (AuthAgent.isOEM) {
                final StringBuilder sb = new StringBuilder();
                sb.append("desktop_m_qq-");
                sb.append(AuthAgent.installChannel);
                sb.append("-");
                sb.append("android");
                sb.append("-");
                sb.append(AuthAgent.registerChannel);
                sb.append("-");
                sb.append(AuthAgent.businessId);
                a.putString("pf", sb.toString());
            }
            else {
                a.putString("pf", "openmobile_android");
            }
            a.putString("need_pay", "1");
            this.a(a, map);
            a.putString("oauth_app_name", i.a(f.a()));
            b2.putExtra("key_action", "action_login");
            b2.putExtra("key_params", a);
            b2.putExtra("appid", this.c.getAppId());
            a.putString("ppsts", i.a(activity, this.a(a)));
            if (this.a(b2)) {
                this.a = (IUiListener)new b(this.a);
                UIListenerManager.getInstance().setListenerWithRequestcode(11101, this.a);
                if (fragment != null) {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    this.a(fragment, b2, 11101, (Map)map);
                }
                else {
                    SLog.d("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    this.a(activity, b2, 11101, (Map)map);
                }
                SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                com.tencent.open.b.e.a().a(0, "LOGIN_CHECK_SDK", "1000", this.c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                array[0] = "0";
                array[1] = 1;
                return true;
            }
        }
        com.tencent.open.b.e.a().a(1, "LOGIN_CHECK_SDK", "1000", this.c.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        SLog.i("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }
    
    static /* synthetic */ Bundle j(final AuthAgent authAgent) {
        return authAgent.b();
    }
    
    int a(final Activity activity, final String s, final IUiListener uiListener, final boolean b, final Fragment fragment, final boolean b2) {
        return this.doLogin(activity, s, uiListener, b, fragment, b2, null);
    }
    
    protected void a(final IUiListener uiListener) {
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        final String accessToken = this.c.getAccessToken();
        final String openId = this.c.getOpenId();
        final String appId = this.c.getAppId();
        String g;
        if (!TextUtils.isEmpty((CharSequence)accessToken) && !TextUtils.isEmpty((CharSequence)openId) && !TextUtils.isEmpty((CharSequence)appId)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("tencent&sdk&qazxc***14969%%");
            sb.append(accessToken);
            sb.append(appId);
            sb.append(openId);
            sb.append("qzone3.4");
            g = k.g(sb.toString());
        }
        else {
            g = "";
        }
        if (TextUtils.isEmpty((CharSequence)g)) {
            SLog.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        final Bundle a = this.a();
        a.putString("encrytoken", g);
        HttpUtils.requestAsync(this.c, f.a(), "https://openmobile.qq.com/user/user_login_statis", a, "POST", (IRequestListener)null);
        SLog.i("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }
    
    protected void b(final IUiListener uiListener) {
        final Bundle a = this.a();
        a.putString("reqType", "checkLogin");
        HttpUtils.requestAsync(this.c, f.a(), "https://openmobile.qq.com/v3/user/get_info", a, "GET", (IRequestListener)new BaseApi.TempRequestListener((IUiListener)new a(uiListener)));
    }
    
    public int doLogin(final Activity activity, final String d, final IUiListener a, final boolean b, final Fragment fragment, final boolean b2, final Map<String, Object> map) {
        this.d = d;
        this.e = (WeakReference<Activity>)new WeakReference((Object)activity);
        this.a = a;
        final Object[] array = new Object[2];
        final boolean booleanExtra = activity.getIntent().getBooleanExtra("KEY_FORCE_QR_LOGIN", false);
        final boolean b3 = g.a((Context)activity, this.c.getAppId()).b("C_LoginWeb");
        final StringBuilder sb = new StringBuilder();
        sb.append("doLogin needForceQrLogin=");
        sb.append(booleanExtra);
        sb.append(", toWebLogin=");
        sb.append(b3);
        SLog.i("openSDK_LOG.AuthAgent", sb.toString());
        if (!booleanExtra && !b3 && this.a(activity, fragment, map, b, array)) {
            SLog.i("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            com.tencent.open.b.e.a().a(this.c.getOpenId(), this.c.getAppId(), "2", "1", "5", (String)array[0], "0", "0");
            return (int)array[1];
        }
        com.tencent.open.b.e.a().a(this.c.getOpenId(), this.c.getAppId(), "2", "1", "5", "1", "0", "0");
        SLog.w("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
        final b a2 = new b(this.a);
        this.a = (IUiListener)a2;
        return this.a(b, (IUiListener)a2, b2, map);
    }
    
    public void releaseResource() {
        this.a = null;
    }
    
    private class a extends DefaultUiListener
    {
        IUiListener a;
        final AuthAgent b;
        
        public a(final AuthAgent b, final IUiListener a) {
            this.b = b;
            this.a = a;
        }
        
        @Override
        public void onCancel() {
            final IUiListener a = this.a;
            if (a != null) {
                a.onCancel();
            }
        }
        
        @Override
        public void onComplete(final Object o) {
            if (o == null) {
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            final JSONObject jsonObject = (JSONObject)o;
            try {
                final int int1 = jsonObject.getInt("ret");
                String string;
                if (int1 == 0) {
                    string = "success";
                }
                else {
                    string = jsonObject.getString("msg");
                }
                if (this.a != null) {
                    this.a.onComplete((Object)new JSONObject().put("ret", int1).put("msg", (Object)string));
                }
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }
        
        @Override
        public void onError(final UiError uiError) {
            final IUiListener a = this.a;
            if (a != null) {
                a.onError(uiError);
            }
        }
    }
    
    private class b extends DefaultUiListener
    {
        WeakReference<IUiListener> a;
        final AuthAgent b;
        private final String c;
        private final String d;
        private final String e;
        
        public b(final AuthAgent b, final IUiListener uiListener) {
            this.b = b;
            this.c = "sendinstall";
            this.d = "installwording";
            this.e = "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";
            this.a = (WeakReference<IUiListener>)new WeakReference((Object)uiListener);
        }
        
        private Drawable a(final String s, Context fromStream) {
            final AssetManager assets = fromStream.getApplicationContext().getAssets();
            final Context context = fromStream = null;
            try {
                final InputStream open = assets.open(s);
                if (open == null) {
                    return null;
                }
                fromStream = context;
                if (s.endsWith(".9.png")) {
                    fromStream = context;
                    Bitmap decodeStream;
                    try {
                        decodeStream = BitmapFactory.decodeStream(open);
                    }
                    catch (final OutOfMemoryError outOfMemoryError) {
                        fromStream = context;
                        outOfMemoryError.printStackTrace();
                        decodeStream = null;
                    }
                    if (decodeStream != null) {
                        fromStream = context;
                        final byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                        fromStream = context;
                        NinePatch.isNinePatchChunk(ninePatchChunk);
                        fromStream = context;
                        fromStream = context;
                        fromStream = context;
                        final Rect rect = new Rect();
                        fromStream = context;
                        final Object o = new NinePatchDrawable(decodeStream, ninePatchChunk, rect, (String)null);
                        return (Drawable)o;
                    }
                    return null;
                }
                else {
                    fromStream = context;
                    final Object o2 = fromStream = (Context)Drawable.createFromStream(open, s);
                    open.close();
                    fromStream = (Context)o2;
                }
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
            final Object o = fromStream;
            return (Drawable)o;
        }
        
        private View a(final Context context, final Drawable imageDrawable, final String text, final View$OnClickListener onClickListener, final View$OnClickListener onClickListener2) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            final float density = displayMetrics.density;
            final RelativeLayout relativeLayout = new RelativeLayout(context);
            final ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(imageDrawable);
            imageView.setScaleType(ImageView$ScaleType.FIT_XY);
            imageView.setId(1);
            final int n = (int)(60.0f * density);
            final int rightMargin = (int)(density * 14.0f);
            final int n2 = (int)(18.0f * density);
            final int n3 = (int)(6.0f * density);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n, n);
            relativeLayout$LayoutParams.addRule(9);
            relativeLayout$LayoutParams.setMargins(0, n2, n3, n2);
            relativeLayout.addView((View)imageView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
            final TextView textView = new TextView(context);
            textView.setText((CharSequence)text);
            textView.setTextSize(14.0f);
            textView.setGravity(3);
            textView.setIncludeFontPadding(false);
            textView.setPadding(0, 0, 0, 0);
            textView.setLines(2);
            textView.setId(5);
            textView.setMinWidth((int)(185.0f * density));
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
            relativeLayout$LayoutParams2.addRule(1, 1);
            relativeLayout$LayoutParams2.addRule(6, 1);
            final float cornerRadius = 5.0f * density;
            relativeLayout$LayoutParams2.setMargins(0, 0, (int)cornerRadius, 0);
            relativeLayout.addView((View)textView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
            final View view = new View(context);
            view.setBackgroundColor(Color.rgb(214, 214, 214));
            view.setId(3);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-2, 2);
            relativeLayout$LayoutParams3.addRule(3, 1);
            relativeLayout$LayoutParams3.addRule(5, 1);
            relativeLayout$LayoutParams3.addRule(7, 5);
            final int n4 = (int)(12.0f * density);
            relativeLayout$LayoutParams3.setMargins(0, 0, 0, n4);
            relativeLayout.addView(view, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
            final LinearLayout linearLayout = new LinearLayout(context);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(-2, -2);
            relativeLayout$LayoutParams4.addRule(5, 1);
            relativeLayout$LayoutParams4.addRule(7, 5);
            relativeLayout$LayoutParams4.addRule(3, 3);
            final Button button = new Button(context);
            button.setText((CharSequence)"\u8df3\u8fc7");
            button.setBackgroundDrawable(this.a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(20.0f);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            final int n5 = (int)(45.0f * density);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, n5);
            linearLayout$LayoutParams.rightMargin = rightMargin;
            final int n6 = (int)(4.0f * density);
            linearLayout$LayoutParams.leftMargin = n6;
            linearLayout$LayoutParams.weight = 1.0f;
            linearLayout.addView((View)button, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            final Button button2 = new Button(context);
            button2.setText((CharSequence)"\u786e\u5b9a");
            button2.setTextSize(20.0f);
            button2.setTextColor(Color.rgb(255, 255, 255));
            button2.setBackgroundDrawable(this.a("buttonPost.png", context));
            button2.setOnClickListener(onClickListener);
            final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(0, n5);
            linearLayout$LayoutParams2.weight = 1.0f;
            linearLayout$LayoutParams2.rightMargin = n6;
            linearLayout.addView((View)button2, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
            relativeLayout.addView((View)linearLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
            final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams((int)(279.0f * density), (int)(density * 163.0f));
            relativeLayout.setPadding(rightMargin, 0, n4, n4);
            relativeLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            final PaintDrawable backgroundDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            backgroundDrawable.setCornerRadius(cornerRadius);
            relativeLayout.setBackgroundDrawable((Drawable)backgroundDrawable);
            return (View)relativeLayout;
        }
        
        private void a(final String s, final IUiListener uiListener, final Object o) {
            if (this.b.e == null) {
                SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity null and return");
                return;
            }
            final Activity activity = (Activity)this.b.e.get();
            if (activity == null) {
                SLog.i("openSDK_LOG.AuthAgent", "showFeedConfrimDialog mActivity.get() null and return");
                return;
            }
            final Dialog dialog = new Dialog((Context)activity);
            dialog.requestWindowFeature(1);
            final PackageManager packageManager = activity.getPackageManager();
            Drawable loadIcon = null;
            PackageInfo packageInfo;
            try {
                packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            }
            catch (final PackageManager$NameNotFoundException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("showFeedConfrimDialog exception:");
                sb.append(ex.getStackTrace().toString());
                SLog.e("openSDK_LOG.AuthAgent", sb.toString());
                packageInfo = null;
            }
            if (packageInfo != null) {
                loadIcon = packageInfo.applicationInfo.loadIcon(packageManager);
            }
            final AuthAgent$b$a authAgent$b$a = new AuthAgent$b$a(this, dialog, uiListener, o) {
                final IUiListener a;
                final Object b;
                final b c;
                
                public void onClick(final View view) {
                    this.c.a();
                    if (this.d != null && this.d.isShowing()) {
                        this.d.dismiss();
                    }
                    final IUiListener a = this.a;
                    if (a != null) {
                        a.onComplete(this.b);
                    }
                }
            };
            final AuthAgent$b$a authAgent$b$a2 = new AuthAgent$b$a(this, dialog, uiListener, o) {
                final IUiListener a;
                final Object b;
                final b c;
                
                public void onClick(final View view) {
                    if (this.d != null && this.d.isShowing()) {
                        this.d.dismiss();
                    }
                    final IUiListener a = this.a;
                    if (a != null) {
                        a.onComplete(this.b);
                    }
                }
            };
            final ColorDrawable backgroundDrawable = new ColorDrawable();
            backgroundDrawable.setAlpha(0);
            dialog.getWindow().setBackgroundDrawable((Drawable)backgroundDrawable);
            dialog.setContentView(this.a((Context)activity, loadIcon, s, (View$OnClickListener)authAgent$b$a, (View$OnClickListener)authAgent$b$a2));
            dialog.setOnCancelListener((DialogInterface$OnCancelListener)new AuthAgent$b$3(this, uiListener, o));
            if (activity != null && !activity.isFinishing()) {
                dialog.show();
            }
        }
        
        protected void a() {
            final Bundle j = AuthAgent.j(this.b);
            if (this.b.e == null) {
                return;
            }
            final Activity activity = (Activity)this.b.e.get();
            if (activity != null) {
                HttpUtils.requestAsync(this.b.c, (Context)activity, "https://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", j, "POST", (IRequestListener)null);
            }
        }
        
        @Override
        public void onCancel() {
            if (this.a.get() != null) {
                ((IUiListener)this.a.get()).onCancel();
            }
        }
        
        @Override
        public void onComplete(final Object p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: ifnull          293
            //     4: aload_1        
            //     5: checkcast       Lorg/json/JSONObject;
            //     8: astore          6
            //    10: aload           6
            //    12: ifnull          293
            //    15: iconst_0       
            //    16: istore          4
            //    18: aload           6
            //    20: ldc             "sendinstall"
            //    22: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
            //    25: istore_2       
            //    26: iconst_1       
            //    27: istore_3       
            //    28: iload_2        
            //    29: iconst_1       
            //    30: if_icmpne       36
            //    33: goto            38
            //    36: iconst_0       
            //    37: istore_3       
            //    38: aload           6
            //    40: ldc             "installwording"
            //    42: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
            //    45: astore          5
            //    47: goto            68
            //    50: astore          5
            //    52: iconst_0       
            //    53: istore_3       
            //    54: ldc_w           "openSDK_LOG.AuthAgent"
            //    57: ldc_w           "FeedConfirmListener onComplete There is no value for sendinstall."
            //    60: invokestatic    com/tencent/open/log/SLog.w:(Ljava/lang/String;Ljava/lang/String;)V
            //    63: ldc_w           ""
            //    66: astore          5
            //    68: aload           5
            //    70: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;)Ljava/lang/String;
            //    73: astore          5
            //    75: new             Ljava/lang/StringBuilder;
            //    78: dup            
            //    79: invokespecial   java/lang/StringBuilder.<init>:()V
            //    82: astore          7
            //    84: aload           7
            //    86: ldc_w           " WORDING = "
            //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    92: pop            
            //    93: aload           7
            //    95: aload           5
            //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   100: pop            
            //   101: aload           7
            //   103: ldc_w           "xx,showConfirmDialog="
            //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   109: pop            
            //   110: aload           7
            //   112: iload_3        
            //   113: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
            //   116: pop            
            //   117: ldc_w           "openSDK_LOG.AuthAgent"
            //   120: aload           7
            //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   125: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   128: iload_3        
            //   129: ifeq            167
            //   132: aload           5
            //   134: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //   137: ifne            167
            //   140: aload_0        
            //   141: aload           5
            //   143: aload_0        
            //   144: getfield        com/tencent/connect/auth/AuthAgent$b.a:Ljava/lang/ref/WeakReference;
            //   147: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
            //   150: checkcast       Lcom/tencent/tauth/IUiListener;
            //   153: aload_1        
            //   154: invokespecial   com/tencent/connect/auth/AuthAgent$b.a:(Ljava/lang/String;Lcom/tencent/tauth/IUiListener;Ljava/lang/Object;)V
            //   157: ldc_w           "openSDK_LOG.AuthAgent"
            //   160: ldc_w           " WORDING is not empty and return"
            //   163: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   166: return         
            //   167: aload_0        
            //   168: getfield        com/tencent/connect/auth/AuthAgent$b.a:Ljava/lang/ref/WeakReference;
            //   171: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
            //   174: checkcast       Lcom/tencent/tauth/IUiListener;
            //   177: astore          5
            //   179: aload           5
            //   181: ifnull          284
            //   184: iload           4
            //   186: istore_3       
            //   187: aload_0        
            //   188: getfield        com/tencent/connect/auth/AuthAgent$b.b:Lcom/tencent/connect/auth/AuthAgent;
            //   191: invokestatic    com/tencent/connect/auth/AuthAgent.h:(Lcom/tencent/connect/auth/AuthAgent;)Lcom/tencent/connect/auth/QQToken;
            //   194: ifnull          246
            //   197: aload_0        
            //   198: getfield        com/tencent/connect/auth/AuthAgent$b.b:Lcom/tencent/connect/auth/AuthAgent;
            //   201: invokestatic    com/tencent/connect/auth/AuthAgent.i:(Lcom/tencent/connect/auth/AuthAgent;)Lcom/tencent/connect/auth/QQToken;
            //   204: aload           6
            //   206: invokevirtual   com/tencent/connect/auth/QQToken.saveSession:(Lorg/json/JSONObject;)Z
            //   209: istore_3       
            //   210: new             Ljava/lang/StringBuilder;
            //   213: dup            
            //   214: invokespecial   java/lang/StringBuilder.<init>:()V
            //   217: astore          6
            //   219: aload           6
            //   221: ldc_w           " saveSession saveSuccess="
            //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   227: pop            
            //   228: aload           6
            //   230: iload_3        
            //   231: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
            //   234: pop            
            //   235: ldc_w           "openSDK_LOG.AuthAgent"
            //   238: aload           6
            //   240: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   243: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   246: iload_3        
            //   247: ifeq            261
            //   250: aload           5
            //   252: aload_1        
            //   253: invokeinterface com/tencent/tauth/IUiListener.onComplete:(Ljava/lang/Object;)V
            //   258: goto            293
            //   261: aload           5
            //   263: new             Lcom/tencent/tauth/UiError;
            //   266: dup            
            //   267: bipush          -6
            //   269: ldc_w           "\u6301\u4e45\u5316\u5931\u8d25!"
            //   272: aconst_null    
            //   273: invokespecial   com/tencent/tauth/UiError.<init>:(ILjava/lang/String;Ljava/lang/String;)V
            //   276: invokeinterface com/tencent/tauth/IUiListener.onError:(Lcom/tencent/tauth/UiError;)V
            //   281: goto            293
            //   284: ldc_w           "openSDK_LOG.AuthAgent"
            //   287: ldc_w           " userListener is null"
            //   290: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   293: return         
            //   294: astore          5
            //   296: goto            54
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                    
            //  -----  -----  -----  -----  ------------------------
            //  18     26     50     54     Lorg/json/JSONException;
            //  38     47     294    299    Lorg/json/JSONException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
            //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
            //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
            //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
            //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
            //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:799)
            //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
            //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
            //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
            //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
            //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
            //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
            //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
            //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
            //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
            //     at java.lang.Thread.run(Thread.java:920)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @Override
        public void onError(final UiError uiError) {
            if (this.a.get() != null) {
                ((IUiListener)this.a.get()).onError(uiError);
            }
        }
    }
    
    private class c extends DefaultUiListener
    {
        final AuthAgent a;
        private final IUiListener b;
        private final boolean c;
        private final Context d;
        
        public c(final AuthAgent a, final Context d, final IUiListener b, final boolean c, final boolean b2) {
            this.a = a;
            this.d = d;
            this.b = b;
            this.c = c;
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }
        
        @Override
        public void onCancel() {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            SLog.release();
        }
        
        @Override
        public void onComplete(Object o) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            o = o;
            try {
                final String string = ((JSONObject)o).getString("access_token");
                final String string2 = ((JSONObject)o).getString("expires_in");
                final String string3 = ((JSONObject)o).getString("openid");
                if (string != null && this.a.c != null && string3 != null) {
                    this.a.c.setAccessToken(string, string2);
                    this.a.c.setOpenId(string3);
                    com.tencent.connect.a.a.d(this.d, this.a.c);
                }
                final String string4 = ((JSONObject)o).getString("pf");
                if (string4 != null) {
                    try {
                        this.d.getSharedPreferences("pfStore", 0).edit().putString("pf", string4).commit();
                    }
                    catch (final Exception ex) {
                        ex.printStackTrace();
                        SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", (Throwable)ex);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            }
            catch (final JSONException ex2) {
                ex2.printStackTrace();
                SLog.e("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", (Throwable)ex2);
            }
            this.b.onComplete(o);
            this.a.releaseResource();
            SLog.release();
        }
        
        @Override
        public void onError(final UiError uiError) {
            SLog.d("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            SLog.release();
        }
    }
}
