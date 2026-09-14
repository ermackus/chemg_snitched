package com.alipay.sdk.m.x;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.graphics.Bitmap;
import android.webkit.JsPromptResult;
import com.alipay.sdk.m.u.n;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.lang.reflect.Method;
import android.webkit.WebSettings;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.os.Build$VERSION;
import android.webkit.WebSettings$TextSize;
import android.webkit.WebSettings$RenderPriority;
import android.text.TextUtils$TruncateAt;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.alipay.sdk.m.u.k;
import android.widget.ImageView$ScaleType;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Looper;
import android.view.View$OnClickListener;
import com.alipay.sdk.m.s.a;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.LinearLayout;

public class e extends LinearLayout
{
    public static Handler m;
    public ImageView a;
    public TextView b;
    public ImageView c;
    public ProgressBar d;
    public WebView e;
    public final e f;
    public f g;
    public g h;
    public h i;
    public final a j;
    public View$OnClickListener k;
    public final float l;
    
    static {
        e.m = new Handler(Looper.getMainLooper());
    }
    
    public e(final Context context, final AttributeSet set, final a j, final e e) {
        super(context, set);
        this.k = (View$OnClickListener)new View$OnClickListener(this) {
            public final e a;
            
            public void onClick(final View view) {
                final h a = com.alipay.sdk.m.x.e.a(this.a);
                if (a != null) {
                    view.setEnabled(false);
                    com.alipay.sdk.m.x.e.b().postDelayed((Runnable)new Runnable(this, view) {
                        public final View a;
                        public final e$a b;
                        
                        public void run() {
                            this.a.setEnabled(true);
                        }
                    }, 256L);
                    if (view == com.alipay.sdk.m.x.e.b(this.a)) {
                        a.b(this.a);
                    }
                    else if (view == com.alipay.sdk.m.x.e.c(this.a)) {
                        a.a(this.a);
                    }
                }
            }
        };
        e f = e;
        if (e == null) {
            f = new e(false, false);
        }
        this.f = f;
        this.j = j;
        this.l = context.getResources().getDisplayMetrics().density;
        this.setOrientation(1);
        this.a(context);
        this.b(context);
        this.c(context);
    }
    
    public e(final Context context, final a a, final e e) {
        this(context, null, a, e);
    }
    
    private int a(final int n) {
        return (int)(n * this.l);
    }
    
    public static /* synthetic */ h a(final e e) {
        return e.i;
    }
    
    private void a(final Context context) {
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        int visibility;
        if (com.alipay.sdk.m.x.e.e.a(this.f)) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        linearLayout.setVisibility(visibility);
        (this.a = new ImageView(context)).setOnClickListener(this.k);
        this.a.setScaleType(ImageView$ScaleType.CENTER);
        this.a.setImageDrawable(com.alipay.sdk.m.u.k.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.a.setPadding(this.a(12), 0, this.a(12), 0);
        linearLayout.addView((View)this.a, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        final View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(this.a(1), this.a(25)));
        (this.b = new TextView(context)).setTextColor(-15658735);
        this.b.setTextSize(17.0f);
        this.b.setMaxLines(1);
        this.b.setEllipsize(TextUtils$TruncateAt.END);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams.setMargins(this.a(17), 0, 0, 0);
        linearLayout$LayoutParams.weight = 1.0f;
        linearLayout.addView((View)this.b, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        (this.c = new ImageView(context)).setOnClickListener(this.k);
        this.c.setScaleType(ImageView$ScaleType.CENTER);
        this.c.setImageDrawable(com.alipay.sdk.m.u.k.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.c.setPadding(this.a(12), 0, this.a(12), 0);
        linearLayout.addView((View)this.c, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.a(48)));
    }
    
    public static /* synthetic */ Handler b() {
        return e.m;
    }
    
    public static /* synthetic */ ImageView b(final e e) {
        return e.a;
    }
    
    private void b(final Context context) {
        (this.d = new ProgressBar(context, (AttributeSet)null, 16973855)).setProgressDrawable(context.getResources().getDrawable(17301612));
        this.d.setMax(100);
        this.d.setBackgroundColor(-218103809);
        this.addView((View)this.d, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.a(2)));
    }
    
    public static /* synthetic */ ImageView c(final e e) {
        return e.c;
    }
    
    private void c(final Context context) {
        (this.e = new WebView(context)).setVerticalScrollbarOverlay(true);
        this.a(this.e, context);
        final WebSettings settings = this.e.getSettings();
        settings.setRenderPriority(WebSettings$RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings$TextSize.NORMAL);
        if (Build$VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        this.e.setVerticalScrollbarOverlay(true);
        this.e.setDownloadListener((DownloadListener)new DownloadListener(this, context) {
            public final Context a;
            public final e b;
            
            public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
                try {
                    final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent.setFlags(268435456);
                    this.a.startActivity(intent);
                }
                finally {}
            }
        });
        while (true) {
            try {
                this.e.removeJavascriptInterface("searchBoxJavaBridge_");
                this.e.removeJavascriptInterface("accessibility");
                this.e.removeJavascriptInterface("accessibilityTraversal");
                break Label_0275;
            }
            catch (final Exception ex) {
                final e e = this;
                final WebView webView = e.e;
                final Class<? extends WebView> class1 = webView.getClass();
                final Class<? extends WebView> class2 = class1;
                final String s = "removeJavascriptInterface";
                final int n = 0;
                final Class[] array = new Class[n];
                final Method method = class2.getMethod(s, (Class<?>[])array);
                final Method method3;
                final Method method2 = method3 = method;
                if (method3 == null) {
                    break Label_0275;
                }
                final Method method4 = method2;
                final e e2 = this;
                final WebView webView2 = e2.e;
                final int n2 = 1;
                final Object[] array2 = new Object[n2];
                final int n3 = 0;
                final String s2 = "searchBoxJavaBridge_";
                array2[n3] = s2;
                method4.invoke((Object)webView2, array2);
                final Method method5 = method2;
                final e e3 = this;
                final WebView webView3 = e3.e;
                final int n4 = 1;
                final Object[] array3 = new Object[n4];
                final int n5 = 0;
                final String s3 = "accessibility";
                array3[n5] = s3;
                method5.invoke((Object)webView3, array3);
                final Method method6 = method2;
                final e e4 = this;
                final WebView webView4 = e4.e;
                final int n6 = 1;
                final Object[] array4 = new Object[n6];
                final int n7 = 0;
                final String s4 = "accessibilityTraversal";
                array4[n7] = s4;
                method6.invoke((Object)webView4, array4);
            }
            try {
                final e e = this;
                final WebView webView = e.e;
                final Class<? extends WebView> class2;
                final Class<? extends WebView> class1 = class2 = webView.getClass();
                final String s = "removeJavascriptInterface";
                final int n = 0;
                final Class[] array = new Class[n];
                final Method method = class2.getMethod(s, (Class<?>[])array);
                final Method method3;
                final Method method2 = method3 = method;
                if (method3 != null) {
                    final Method method4 = method2;
                    final e e2 = this;
                    final WebView webView2 = e2.e;
                    final int n2 = 1;
                    final Object[] array2 = new Object[n2];
                    final int n3 = 0;
                    final String s2 = "searchBoxJavaBridge_";
                    array2[n3] = s2;
                    method4.invoke((Object)webView2, array2);
                    final Method method5 = method2;
                    final e e3 = this;
                    final WebView webView3 = e3.e;
                    final int n4 = 1;
                    final Object[] array3 = new Object[n4];
                    final int n5 = 0;
                    final String s3 = "accessibility";
                    array3[n5] = s3;
                    method5.invoke((Object)webView3, array3);
                    final Method method6 = method2;
                    final e e4 = this;
                    final WebView webView4 = e4.e;
                    final int n6 = 1;
                    final Object[] array4 = new Object[n6];
                    final int n7 = 0;
                    final String s4 = "accessibilityTraversal";
                    array4[n7] = s4;
                    method6.invoke((Object)webView4, array4);
                }
                com.alipay.sdk.m.x.c.a(this.e);
                this.addView((View)this.e, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    public static /* synthetic */ e d(final e e) {
        return e.f;
    }
    
    public static /* synthetic */ ProgressBar e(final e e) {
        return e.d;
    }
    
    public static /* synthetic */ f f(final e e) {
        return e.g;
    }
    
    public static /* synthetic */ g g(final e e) {
        return e.h;
    }
    
    public void a() {
        this.removeAllViews();
        this.e.removeAllViews();
        this.e.setWebViewClient((WebViewClient)null);
        this.e.setWebChromeClient((WebChromeClient)null);
        this.e.destroy();
    }
    
    public void a(final WebView webView, final Context context) {
        final String userAgentString = webView.getSettings().getUserAgentString();
        final WebSettings settings = webView.getSettings();
        final StringBuilder sb = new StringBuilder();
        sb.append(userAgentString);
        sb.append(n.g(context));
        settings.setUserAgentString(sb.toString());
    }
    
    public void a(final String s) {
        this.e.loadUrl(s);
        com.alipay.sdk.m.x.c.a(this.e);
    }
    
    public void a(final String s, final byte[] array) {
        this.e.postUrl(s, array);
    }
    
    public ImageView getBackButton() {
        return this.a;
    }
    
    public ProgressBar getProgressbar() {
        return this.d;
    }
    
    public ImageView getRefreshButton() {
        return this.c;
    }
    
    public TextView getTitle() {
        return this.b;
    }
    
    public String getUrl() {
        return this.e.getUrl();
    }
    
    public WebView getWebView() {
        return this.e;
    }
    
    public void setChromeProxy(final f g) {
        this.g = g;
        if (g == null) {
            this.e.setWebChromeClient((WebChromeClient)null);
        }
        else {
            this.e.setWebChromeClient((WebChromeClient)new WebChromeClient(this) {
                public final e a;
                
                public boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
                    return com.alipay.sdk.m.x.e.f(this.a).a(this.a, s, s2, s3, jsPromptResult);
                }
                
                public void onProgressChanged(final WebView webView, final int progress) {
                    if (com.alipay.sdk.m.x.e.e.b(com.alipay.sdk.m.x.e.d(this.a))) {
                        if (progress > 90) {
                            com.alipay.sdk.m.x.e.e(this.a).setVisibility(4);
                        }
                        else {
                            if (com.alipay.sdk.m.x.e.e(this.a).getVisibility() == 4) {
                                com.alipay.sdk.m.x.e.e(this.a).setVisibility(0);
                            }
                            com.alipay.sdk.m.x.e.e(this.a).setProgress(progress);
                        }
                    }
                    else {
                        com.alipay.sdk.m.x.e.e(this.a).setVisibility(8);
                    }
                }
                
                public void onReceivedTitle(final WebView webView, final String s) {
                    com.alipay.sdk.m.x.e.f(this.a).c(this.a, s);
                }
            });
        }
    }
    
    public void setWebClientProxy(final g h) {
        this.h = h;
        if (h == null) {
            this.e.setWebViewClient((WebViewClient)null);
        }
        else {
            this.e.setWebViewClient((WebViewClient)new WebViewClient(this) {
                public final e a;
                
                public void onPageFinished(final WebView webView, final String s) {
                    if (!com.alipay.sdk.m.x.e.g(this.a).b(this.a, s)) {
                        super.onPageFinished(webView, s);
                    }
                }
                
                public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
                    if (!com.alipay.sdk.m.x.e.g(this.a).d(this.a, s)) {
                        super.onPageFinished(webView, s);
                    }
                }
                
                public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                    if (!com.alipay.sdk.m.x.e.g(this.a).a(this.a, n, s, s2)) {
                        super.onReceivedError(webView, n, s, s2);
                    }
                }
                
                public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
                    if (!com.alipay.sdk.m.x.e.g(this.a).a(this.a, sslErrorHandler, sslError)) {
                        super.onReceivedSslError(webView, sslErrorHandler, sslError);
                    }
                }
                
                public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                    return com.alipay.sdk.m.x.e.g(this.a).a(this.a, s) || super.shouldOverrideUrlLoading(webView, s);
                }
            });
        }
    }
    
    public void setWebEventProxy(final h i) {
        this.i = i;
    }
    
    public static final class e
    {
        public boolean a;
        public boolean b;
        
        public e(final boolean a, final boolean b) {
            this.a = a;
            this.b = b;
        }
        
        public static /* synthetic */ boolean a(final e e) {
            return e.a;
        }
        
        public static /* synthetic */ boolean b(final e e) {
            return e.b;
        }
    }
    
    public interface f
    {
        boolean a(final e p0, final String p1, final String p2, final String p3, final JsPromptResult p4);
        
        void c(final e p0, final String p1);
    }
    
    public interface g
    {
        boolean a(final e p0, final int p1, final String p2, final String p3);
        
        boolean a(final e p0, final SslErrorHandler p1, final SslError p2);
        
        boolean a(final e p0, final String p1);
        
        boolean b(final e p0, final String p1);
        
        boolean d(final e p0, final String p1);
    }
    
    public interface h
    {
        void a(final e p0);
        
        void b(final e p0);
    }
}
