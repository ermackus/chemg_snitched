package com.tencent.connect.auth;

import android.os.Message;
import android.os.Looper;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.tencent.tauth.UiError;
import android.os.SystemClock;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.view.Window;
import android.app.Activity;
import android.os.Bundle;
import com.tencent.open.utils.HttpUtils;
import android.webkit.WebSettings;
import com.tencent.open.web.security.JniInterface;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.tencent.open.a$b;
import com.tencent.open.web.security.SecureJsInterface;
import android.webkit.WebSettings$RenderPriority;
import com.tencent.open.utils.i;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View$OnLongClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.graphics.Color;
import java.util.Locale;
import android.widget.TextView;
import android.widget.LinearLayout$LayoutParams;
import org.json.JSONObject;
import org.json.JSONException;
import android.widget.Toast;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageView$ScaleType;
import com.tencent.open.utils.k;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.text.TextUtils;
import com.tencent.open.log.SLog;
import java.util.HashMap;
import com.tencent.open.web.security.b;
import android.content.Context;
import com.tencent.open.c.c;
import android.widget.ProgressBar;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.os.Handler;
import com.tencent.tauth.IUiListener;
import android.app.Dialog;

public class a extends Dialog
{
    private String a;
    private com.tencent.connect.auth.a.a$b b;
    private IUiListener c;
    private Handler d;
    private FrameLayout e;
    private LinearLayout f;
    private FrameLayout g;
    private ProgressBar h;
    private String i;
    private com.tencent.open.c.c j;
    private Context k;
    private b l;
    private boolean m;
    private int n;
    private String o;
    private String p;
    private long q;
    private long r;
    private HashMap<String, Runnable> s;
    
    public a(final Context k, final String i, final String a, final IUiListener c, final QQToken qqToken) {
        super(k, 16973840);
        this.m = false;
        this.q = 0L;
        this.r = 30000L;
        this.k = k;
        this.a = a;
        this.b = new com.tencent.connect.auth.a.a$b(this, i, a, qqToken.getAppId(), c);
        this.d = new c(this.b, k.getMainLooper());
        this.c = c;
        this.i = i;
        this.l = new b();
        this.getWindow().setSoftInputMode(32);
    }
    
    private String a() {
        final String a = this.a;
        final String substring = a.substring(a.indexOf("?") + 1);
        final StringBuilder sb = new StringBuilder();
        sb.append("https://imgcache.qq.com/ptlogin/static/qzsjump.html?");
        sb.append(substring);
        final String string = sb.toString();
        SLog.i("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: https://imgcache.qq.com/ptlogin/static/qzsjump.html?");
        return string;
    }
    
    private String a(final String s) {
        final StringBuilder sb = new StringBuilder(s);
        if (!TextUtils.isEmpty((CharSequence)this.p) && this.p.length() >= 4) {
            final String p = this.p;
            final String substring = p.substring(p.length() - 4);
            sb.append("_u_");
            sb.append(substring);
        }
        return sb.toString();
    }
    
    private void a(final ViewGroup viewGroup) {
        final ImageView imageView = new ImageView(this.k);
        final int a = com.tencent.connect.avatar.a.a(this.k, 15.6f);
        final int a2 = com.tencent.connect.avatar.a.a(this.k, 25.2f);
        final int a3 = com.tencent.connect.avatar.a.a(this.k, 10.0f);
        final int n = a3 * 2;
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(a + n, a2 + n);
        layoutParams.leftMargin = a3;
        imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        imageView.setPadding(a3, a3, a3, a3);
        imageView.setImageDrawable(com.tencent.open.utils.k.a("h5_qr_back.png", this.k));
        imageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
        imageView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final a a;
            
            public void onClick(final View view) {
                this.a.dismiss();
                if (!this.a.m && this.a.b != null) {
                    this.a.b.onCancel();
                }
            }
        });
        viewGroup.addView((View)imageView);
    }
    
    private void b() {
        this.c();
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        this.j = new com.tencent.open.c.c(this.k);
        if (Build$VERSION.SDK_INT >= 11) {
            this.j.setLayerType(1, (Paint)null);
        }
        this.j.setLayoutParams((ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.e = new FrameLayout(this.k);
        frameLayout$LayoutParams.gravity = 17;
        this.e.setLayoutParams((ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.e.addView((View)this.j);
        this.e.addView((View)this.g);
        final String string = com.tencent.open.utils.k.b(this.a).getString("style");
        if (string != null && "qr".equals((Object)string)) {
            this.a((ViewGroup)this.e);
        }
        this.setContentView((View)this.e);
    }
    
    private static void b(final Context context, String string) {
        try {
            final JSONObject d = k.d(string);
            final int int1 = d.getInt("type");
            string = d.getString("msg");
            Toast.makeText(context.getApplicationContext(), (CharSequence)string, int1).show();
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void c() {
        (this.h = new ProgressBar(this.k)).setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        this.f = new LinearLayout(this.k);
        TextView textView;
        if (this.i.equals((Object)"action_login")) {
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            textView = new TextView(this.k);
            if (Locale.getDefault().getLanguage().equals((Object)"zh")) {
                textView.setText((CharSequence)"\u767b\u5f55\u4e2d...");
            }
            else {
                textView.setText((CharSequence)"Logging in...");
            }
            textView.setTextColor(Color.rgb(255, 255, 255));
            textView.setTextSize(18.0f);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        else {
            textView = null;
        }
        final FrameLayout$LayoutParams layoutParams2 = new FrameLayout$LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        this.f.addView((View)this.h);
        if (textView != null) {
            this.f.addView((View)textView);
        }
        this.g = new FrameLayout(this.k);
        final FrameLayout$LayoutParams layoutParams3 = new FrameLayout$LayoutParams(-1, -1);
        layoutParams3.gravity = 17;
        this.g.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        this.g.setBackgroundColor(Color.parseColor("#B3000000"));
        this.g.addView((View)this.f);
    }
    
    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient((WebViewClient)new a());
        this.j.setWebChromeClient(new WebChromeClient());
        this.j.clearFormData();
        this.j.clearSslPreferences();
        this.j.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener(this) {
            final a a;
            
            public boolean onLongClick(final View view) {
                return true;
            }
        });
        this.j.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener(this) {
            final a a;
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final int action = motionEvent.getAction();
                if (action == 0 || action == 1) {
                    if (!view.hasFocus()) {
                        view.requestFocus();
                    }
                }
                return false;
            }
        });
        final WebSettings settings = this.j.getSettings();
        com.tencent.open.utils.i.a(settings);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings$RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        final StringBuilder sb = new StringBuilder();
        sb.append("-->mUrl : ");
        sb.append(this.a);
        SLog.v("openSDK_LOG.AuthDialog", sb.toString());
        final String a = this.a;
        this.o = a;
        this.j.loadUrl(a);
        this.j.setVisibility(4);
        this.l.a((a$b)new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener((DialogInterface$OnDismissListener)new DialogInterface$OnDismissListener(this) {
            final a a;
            
            public void onDismiss(final DialogInterface dialogInterface) {
                try {
                    if (JniInterface.isJniOk) {
                        JniInterface.clearAllPWD();
                    }
                }
                catch (final Exception ex) {}
            }
        });
    }
    
    private boolean e() {
        final com.tencent.connect.auth.b a = com.tencent.connect.auth.b.a();
        final String c = a.c();
        final com.tencent.connect.auth.b.a a2 = new com.tencent.connect.auth.b.a();
        a2.a = this.c;
        a2.b = this;
        a2.c = c;
        final String a3 = a.a(a2);
        final String a4 = this.a;
        final String substring = a4.substring(0, a4.indexOf("?"));
        final Bundle b = com.tencent.open.utils.k.b(this.a);
        b.putString("token_key", c);
        b.putString("serial", a3);
        b.putString("browser", "1");
        final StringBuilder sb = new StringBuilder();
        sb.append(substring);
        sb.append("?");
        sb.append(HttpUtils.encodeUrl(b));
        final String string = sb.toString();
        this.a = string;
        return com.tencent.open.utils.k.a(this.k, string);
    }
    
    public void a(String string, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(string);
        sb.append("(");
        sb.append(s);
        sb.append(");void(");
        sb.append(System.currentTimeMillis());
        sb.append(");");
        string = sb.toString();
        this.j.loadUrl(string);
    }
    
    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages((Object)null);
        try {
            if (this.k instanceof Activity && !((Activity)this.k).isFinishing() && this.isShowing()) {
                super.dismiss();
                SLog.i("openSDK_LOG.AuthDialog", "-->dismiss dialog");
            }
        }
        catch (final Exception ex) {
            SLog.e("openSDK_LOG.AuthDialog", "-->dismiss dialog exception:", (Throwable)ex);
        }
        final com.tencent.open.c.c j = this.j;
        if (j != null) {
            j.destroy();
            this.j = null;
        }
    }
    
    public void onBackPressed() {
        if (!this.m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }
    
    protected void onCreate(final Bundle bundle) {
        this.requestWindowFeature(1);
        final Window window = this.getWindow();
        if (window != null) {
            window.setFlags(1024, 1024);
        }
        super.onCreate(bundle);
        if (window != null) {
            final View decorView = window.getDecorView();
            if (Build$VERSION.SDK_INT >= 16) {
                decorView.setSystemUiVisibility(1280);
            }
        }
        this.b();
        this.d();
        this.s = (HashMap<String, Runnable>)new HashMap();
    }
    
    protected void onStop() {
        super.onStop();
    }
    
    private class a extends WebViewClient
    {
        final com.tencent.connect.auth.a a;
        
        private a(final com.tencent.connect.auth.a a) {
            this.a = a;
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            final StringBuilder sb = new StringBuilder();
            sb.append("-->onPageFinished, url: ");
            sb.append(s);
            SLog.v("openSDK_LOG.AuthDialog", sb.toString());
            this.a.g.setVisibility(8);
            if (this.a.j != null) {
                this.a.j.setVisibility(0);
            }
            if (!TextUtils.isEmpty((CharSequence)s)) {
                this.a.d.removeCallbacks((Runnable)this.a.s.remove((Object)s));
            }
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            final StringBuilder sb = new StringBuilder();
            sb.append("-->onPageStarted, url: ");
            sb.append(s);
            SLog.v("openSDK_LOG.AuthDialog", sb.toString());
            super.onPageStarted(webView, s, bitmap);
            this.a.g.setVisibility(0);
            this.a.q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty((CharSequence)this.a.o)) {
                this.a.d.removeCallbacks((Runnable)this.a.s.remove((Object)this.a.o));
            }
            this.a.o = s;
            final com.tencent.connect.auth.a a = this.a;
            final d d = a.new d(a.o);
            this.a.s.put((Object)s, (Object)d);
            this.a.d.postDelayed((Runnable)d, 120000L);
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            super.onReceivedError(webView, n, s, s2);
            final StringBuilder sb = new StringBuilder();
            sb.append("-->onReceivedError, errorCode: ");
            sb.append(n);
            sb.append(" | description: ");
            sb.append(s);
            SLog.i("openSDK_LOG.AuthDialog", sb.toString());
            if (!com.tencent.open.utils.k.b(this.a.k)) {
                this.a.b.onError(new UiError(9001, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", s2));
                this.a.dismiss();
                return;
            }
            if (!this.a.o.startsWith("https://imgcache.qq.com/ptlogin/static/qzsjump.html?")) {
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                final long j = this.a.q;
                if (this.a.n < 1 && elapsedRealtime - j < this.a.r) {
                    this.a.n++;
                    this.a.d.postDelayed((Runnable)new Runnable(this) {
                        final a a;
                        
                        public void run() {
                            this.a.a.j.loadUrl(this.a.a.o);
                        }
                    }, 500L);
                }
                else {
                    this.a.j.loadUrl(this.a.a());
                }
                return;
            }
            this.a.b.onError(new UiError(n, s, s2));
            this.a.dismiss();
        }
        
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            final StringBuilder sb = new StringBuilder();
            sb.append("-->onReceivedSslError ");
            sb.append(sslError.getPrimaryError());
            sb.append("\u8bf7\u6c42\u4e0d\u5408\u6cd5\uff0c\u8bf7\u68c0\u67e5\u624b\u673a\u5b89\u5168\u8bbe\u7f6e\uff0c\u5982\u7cfb\u7edf\u65f6\u95f4\u3001\u4ee3\u7406\u7b49");
            SLog.e("openSDK_LOG.AuthDialog", sb.toString());
            sslErrorHandler.cancel();
        }
        
        public boolean shouldOverrideUrlLoading(final WebView p0, final String p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: invokespecial   java/lang/StringBuilder.<init>:()V
            //     7: astore_1       
            //     8: aload_1        
            //     9: ldc             "-->Redirect URL: "
            //    11: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    14: pop            
            //    15: aload_1        
            //    16: aload_2        
            //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    20: pop            
            //    21: ldc             "openSDK_LOG.AuthDialog"
            //    23: aload_1        
            //    24: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    27: invokestatic    com/tencent/open/log/SLog.v:(Ljava/lang/String;Ljava/lang/String;)V
            //    30: aload_2        
            //    31: ldc             "auth://browser"
            //    33: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //    36: ifeq            272
            //    39: aload_2        
            //    40: invokestatic    com/tencent/open/utils/k.c:(Ljava/lang/String;)Lorg/json/JSONObject;
            //    43: astore_2       
            //    44: aload_0        
            //    45: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //    48: astore_1       
            //    49: aload_1        
            //    50: aload_1        
            //    51: invokestatic    com/tencent/connect/auth/a.b:(Lcom/tencent/connect/auth/a;)Z
            //    54: invokestatic    com/tencent/connect/auth/a.a:(Lcom/tencent/connect/auth/a;Z)Z
            //    57: pop            
            //    58: aload_0        
            //    59: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //    62: invokestatic    com/tencent/connect/auth/a.c:(Lcom/tencent/connect/auth/a;)Z
            //    65: ifeq            71
            //    68: goto            270
            //    71: aload_2        
            //    72: ldc             "fail_cb"
            //    74: aconst_null    
            //    75: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
            //    78: ifnull          100
            //    81: aload_0        
            //    82: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //    85: aload_2        
            //    86: ldc             "fail_cb"
            //    88: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
            //    91: ldc_w           ""
            //    94: invokevirtual   com/tencent/connect/auth/a.a:(Ljava/lang/String;Ljava/lang/String;)V
            //    97: goto            270
            //   100: aload_2        
            //   101: ldc_w           "fall_to_wv"
            //   104: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;)I
            //   107: iconst_1       
            //   108: if_icmpne       246
            //   111: aload_0        
            //   112: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   115: astore_2       
            //   116: new             Ljava/lang/StringBuilder;
            //   119: dup            
            //   120: invokespecial   java/lang/StringBuilder.<init>:()V
            //   123: astore          4
            //   125: aload           4
            //   127: aload_0        
            //   128: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   131: invokestatic    com/tencent/connect/auth/a.d:(Lcom/tencent/connect/auth/a;)Ljava/lang/String;
            //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   137: pop            
            //   138: aload_0        
            //   139: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   142: invokestatic    com/tencent/connect/auth/a.d:(Lcom/tencent/connect/auth/a;)Ljava/lang/String;
            //   145: astore          5
            //   147: ldc_w           "?"
            //   150: astore_1       
            //   151: aload           5
            //   153: ldc_w           "?"
            //   156: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
            //   159: iconst_m1      
            //   160: if_icmple       167
            //   163: ldc_w           "&"
            //   166: astore_1       
            //   167: aload           4
            //   169: aload_1        
            //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   173: pop            
            //   174: aload_2        
            //   175: aload           4
            //   177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   180: invokestatic    com/tencent/connect/auth/a.b:(Lcom/tencent/connect/auth/a;Ljava/lang/String;)Ljava/lang/String;
            //   183: pop            
            //   184: aload_0        
            //   185: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   188: astore_1       
            //   189: new             Ljava/lang/StringBuilder;
            //   192: dup            
            //   193: invokespecial   java/lang/StringBuilder.<init>:()V
            //   196: astore_2       
            //   197: aload_2        
            //   198: aload_0        
            //   199: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   202: invokestatic    com/tencent/connect/auth/a.d:(Lcom/tencent/connect/auth/a;)Ljava/lang/String;
            //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   208: pop            
            //   209: aload_2        
            //   210: ldc_w           "browser_error=1"
            //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   216: pop            
            //   217: aload_1        
            //   218: aload_2        
            //   219: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   222: invokestatic    com/tencent/connect/auth/a.b:(Lcom/tencent/connect/auth/a;Ljava/lang/String;)Ljava/lang/String;
            //   225: pop            
            //   226: aload_0        
            //   227: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   230: invokestatic    com/tencent/connect/auth/a.e:(Lcom/tencent/connect/auth/a;)Lcom/tencent/open/c/c;
            //   233: aload_0        
            //   234: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   237: invokestatic    com/tencent/connect/auth/a.d:(Lcom/tencent/connect/auth/a;)Ljava/lang/String;
            //   240: invokevirtual   com/tencent/open/c/c.loadUrl:(Ljava/lang/String;)V
            //   243: goto            270
            //   246: aload_2        
            //   247: ldc_w           "redir"
            //   250: aconst_null    
            //   251: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
            //   254: astore_1       
            //   255: aload_1        
            //   256: ifnull          270
            //   259: aload_0        
            //   260: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   263: invokestatic    com/tencent/connect/auth/a.e:(Lcom/tencent/connect/auth/a;)Lcom/tencent/open/c/c;
            //   266: aload_1        
            //   267: invokevirtual   com/tencent/open/c/c.loadUrl:(Ljava/lang/String;)V
            //   270: iconst_1       
            //   271: ireturn        
            //   272: aload_2        
            //   273: ldc_w           "auth://tauth.qq.com/"
            //   276: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   279: ifeq            305
            //   282: aload_0        
            //   283: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   286: invokestatic    com/tencent/connect/auth/a.f:(Lcom/tencent/connect/auth/a;)Lcom/tencent/connect/auth/a$b;
            //   289: aload_2        
            //   290: invokestatic    com/tencent/open/utils/k.c:(Ljava/lang/String;)Lorg/json/JSONObject;
            //   293: invokevirtual   com/tencent/connect/auth/a$b.onComplete:(Ljava/lang/Object;)V
            //   296: aload_0        
            //   297: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   300: invokevirtual   com/tencent/connect/auth/a.dismiss:()V
            //   303: iconst_1       
            //   304: ireturn        
            //   305: aload_2        
            //   306: ldc_w           "auth://cancel"
            //   309: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   312: ifeq            334
            //   315: aload_0        
            //   316: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   319: invokestatic    com/tencent/connect/auth/a.f:(Lcom/tencent/connect/auth/a;)Lcom/tencent/connect/auth/a$b;
            //   322: invokevirtual   com/tencent/connect/auth/a$b.onCancel:()V
            //   325: aload_0        
            //   326: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   329: invokevirtual   com/tencent/connect/auth/a.dismiss:()V
            //   332: iconst_1       
            //   333: ireturn        
            //   334: aload_2        
            //   335: ldc_w           "auth://close"
            //   338: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   341: ifeq            353
            //   344: aload_0        
            //   345: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   348: invokevirtual   com/tencent/connect/auth/a.dismiss:()V
            //   351: iconst_1       
            //   352: ireturn        
            //   353: aload_2        
            //   354: ldc_w           "download://"
            //   357: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   360: ifne            550
            //   363: aload_2        
            //   364: ldc_w           ".apk"
            //   367: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
            //   370: ifeq            376
            //   373: goto            550
            //   376: aload_2        
            //   377: ldc_w           "auth://progress"
            //   380: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   383: ifeq            470
            //   386: aload_2        
            //   387: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
            //   390: invokevirtual   android/net/Uri.getPathSegments:()Ljava/util/List;
            //   393: astore_1       
            //   394: aload_1        
            //   395: invokeinterface java/util/List.isEmpty:()Z
            //   400: ifeq            405
            //   403: iconst_1       
            //   404: ireturn        
            //   405: aload_1        
            //   406: iconst_0       
            //   407: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   412: checkcast       Ljava/lang/String;
            //   415: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
            //   418: invokevirtual   java/lang/Integer.intValue:()I
            //   421: istore_3       
            //   422: iload_3        
            //   423: ifne            452
            //   426: aload_0        
            //   427: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   430: invokestatic    com/tencent/connect/auth/a.g:(Lcom/tencent/connect/auth/a;)Landroid/widget/FrameLayout;
            //   433: bipush          8
            //   435: invokevirtual   android/widget/FrameLayout.setVisibility:(I)V
            //   438: aload_0        
            //   439: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   442: invokestatic    com/tencent/connect/auth/a.e:(Lcom/tencent/connect/auth/a;)Lcom/tencent/open/c/c;
            //   445: iconst_0       
            //   446: invokevirtual   com/tencent/open/c/c.setVisibility:(I)V
            //   449: goto            468
            //   452: iload_3        
            //   453: iconst_1       
            //   454: if_icmpne       468
            //   457: aload_0        
            //   458: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   461: invokestatic    com/tencent/connect/auth/a.g:(Lcom/tencent/connect/auth/a;)Landroid/widget/FrameLayout;
            //   464: iconst_0       
            //   465: invokevirtual   android/widget/FrameLayout.setVisibility:(I)V
            //   468: iconst_1       
            //   469: ireturn        
            //   470: aload_2        
            //   471: ldc_w           "auth://onLoginSubmit"
            //   474: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   477: ifeq            517
            //   480: aload_2        
            //   481: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
            //   484: invokevirtual   android/net/Uri.getPathSegments:()Ljava/util/List;
            //   487: astore_1       
            //   488: aload_1        
            //   489: invokeinterface java/util/List.isEmpty:()Z
            //   494: ifne            515
            //   497: aload_0        
            //   498: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   501: aload_1        
            //   502: iconst_0       
            //   503: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   508: checkcast       Ljava/lang/String;
            //   511: invokestatic    com/tencent/connect/auth/a.c:(Lcom/tencent/connect/auth/a;Ljava/lang/String;)Ljava/lang/String;
            //   514: pop            
            //   515: iconst_1       
            //   516: ireturn        
            //   517: aload_0        
            //   518: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   521: invokestatic    com/tencent/connect/auth/a.h:(Lcom/tencent/connect/auth/a;)Lcom/tencent/open/web/security/b;
            //   524: aload_0        
            //   525: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   528: invokestatic    com/tencent/connect/auth/a.e:(Lcom/tencent/connect/auth/a;)Lcom/tencent/open/c/c;
            //   531: aload_2        
            //   532: invokevirtual   com/tencent/open/web/security/b.a:(Landroid/webkit/WebView;Ljava/lang/String;)Z
            //   535: ifeq            540
            //   538: iconst_1       
            //   539: ireturn        
            //   540: ldc             "openSDK_LOG.AuthDialog"
            //   542: ldc_w           "-->Redirect URL: return false"
            //   545: invokestatic    com/tencent/open/log/SLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   548: iconst_0       
            //   549: ireturn        
            //   550: aload_2        
            //   551: ldc_w           "download://"
            //   554: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   557: ifeq            576
            //   560: aload_2        
            //   561: bipush          11
            //   563: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
            //   566: invokestatic    android/net/Uri.decode:(Ljava/lang/String;)Ljava/lang/String;
            //   569: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
            //   572: astore_1       
            //   573: goto            584
            //   576: aload_2        
            //   577: invokestatic    android/net/Uri.decode:(Ljava/lang/String;)Ljava/lang/String;
            //   580: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
            //   583: astore_1       
            //   584: new             Landroid/content/Intent;
            //   587: astore_2       
            //   588: aload_2        
            //   589: ldc_w           "android.intent.action.VIEW"
            //   592: aload_1        
            //   593: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;Landroid/net/Uri;)V
            //   596: aload_2        
            //   597: ldc_w           268435456
            //   600: invokevirtual   android/content/Intent.addFlags:(I)Landroid/content/Intent;
            //   603: pop            
            //   604: aload_0        
            //   605: getfield        com/tencent/connect/auth/a$a.a:Lcom/tencent/connect/auth/a;
            //   608: invokestatic    com/tencent/connect/auth/a.a:(Lcom/tencent/connect/auth/a;)Landroid/content/Context;
            //   611: aload_2        
            //   612: invokevirtual   android/content/Context.startActivity:(Landroid/content/Intent;)V
            //   615: goto            628
            //   618: astore_1       
            //   619: ldc             "openSDK_LOG.AuthDialog"
            //   621: ldc_w           "-->start download activity exception, e: "
            //   624: aload_1        
            //   625: invokestatic    com/tencent/open/log/SLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
            //   628: iconst_1       
            //   629: ireturn        
            //   630: astore_1       
            //   631: goto            468
            //   634: astore_1       
            //   635: goto            515
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  386    403    630    634    Ljava/lang/Exception;
            //  405    422    630    634    Ljava/lang/Exception;
            //  426    449    630    634    Ljava/lang/Exception;
            //  457    468    630    634    Ljava/lang/Exception;
            //  480    515    634    638    Ljava/lang/Exception;
            //  550    573    618    628    Ljava/lang/Exception;
            //  576    584    618    628    Ljava/lang/Exception;
            //  584    615    618    628    Ljava/lang/Exception;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0515:
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
    }
    
    private class c extends Handler
    {
        final a a;
        private com.tencent.connect.auth.a$b b;
        
        public c(final a a, final com.tencent.connect.auth.a$b b, final Looper looper) {
            this.a = a;
            super(looper);
            this.b = b;
        }
        
        public void handleMessage(final Message message) {
            final int what = message.what;
            if (what != 1) {
                if (what != 2) {
                    if (what == 3) {
                        b(this.a.k, (String)message.obj);
                    }
                }
                else {
                    this.b.onCancel();
                }
            }
            else {
                com.tencent.connect.auth.a$b.a(this.b, (String)message.obj);
            }
        }
    }
    
    class d implements Runnable
    {
        String a;
        final a b;
        
        public d(final a b, final String a) {
            this.b = b;
            this.a = "";
            this.a = a;
        }
        
        public void run() {
            final StringBuilder sb = new StringBuilder();
            sb.append("-->timeoutUrl: ");
            sb.append(this.a);
            sb.append(" | mRetryUrl: ");
            sb.append(this.b.o);
            SLog.v("openSDK_LOG.AuthDialog", sb.toString());
            if (this.a.equals((Object)this.b.o)) {
                this.b.b.onError(new UiError(9002, "\u8bf7\u6c42\u9875\u9762\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", this.b.o));
                this.b.dismiss();
            }
        }
    }
}
