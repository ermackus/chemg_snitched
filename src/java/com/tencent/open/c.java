package com.tencent.open;

import android.os.SystemClock;
import com.tencent.open.b.h;
import com.tencent.tauth.UiError;
import com.tencent.tauth.DefaultUiListener;
import android.os.Bundle;
import android.webkit.WebView;
import org.json.JSONObject;
import org.json.JSONException;
import com.tencent.open.utils.k;
import android.webkit.WebSettings;
import android.webkit.WebSettings$RenderPriority;
import com.tencent.open.utils.i;
import android.webkit.WebViewClient;
import android.graphics.Paint;
import android.view.View;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.tencent.open.log.SLog;
import com.tencent.connect.auth.QQToken;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.tencent.open.c.a;
import android.os.Handler;
import com.tencent.tauth.IUiListener;
import android.widget.Toast;
import com.tencent.open.c.a$a;

public class c extends com.tencent.open.b implements a$a
{
    static Toast c;
    private String d;
    private IUiListener e;
    private c f;
    private Handler g;
    private a h;
    private com.tencent.open.c.b i;
    private WeakReference<Context> j;
    private int k;
    
    public c(final Context context, final String s, final String d, final IUiListener e, final QQToken qqToken) {
        super(context, 16973840);
        this.j = (WeakReference<Context>)new WeakReference((Object)context);
        this.d = d;
        this.f = new c(context, s, d, qqToken.getAppId(), e);
        this.g = (Handler)new c$d(this, this.f, context.getMainLooper());
        this.e = e;
        this.k = Math.round(context.getResources().getDisplayMetrics().density * 185.0f);
        final StringBuilder sb = new StringBuilder();
        sb.append("density=");
        sb.append(context.getResources().getDisplayMetrics().density);
        sb.append("; webviewHeight=");
        sb.append(this.k);
        SLog.e("openSDK_LOG.PKDialog", sb.toString());
    }
    
    private void b() {
        (this.h = new a((Context)this.j.get())).setBackgroundColor(1711276032);
        this.h.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        (this.i = new com.tencent.open.c.b((Context)this.j.get())).setBackgroundColor(0);
        this.i.setBackgroundDrawable((Drawable)null);
        if (Build$VERSION.SDK_INT >= 11) {
            try {
                View.class.getMethod("setLayerType", Integer.TYPE, Paint.class).invoke((Object)this.i, new Object[] { 1, new Paint() });
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, this.k);
        layoutParams.addRule(13, -1);
        this.i.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.h.addView((View)this.i);
        this.h.a((a$a)this);
        this.setContentView((View)this.h);
    }
    
    private void c() {
        this.i.setVerticalScrollBarEnabled(false);
        this.i.setHorizontalScrollBarEnabled(false);
        this.i.setWebViewClient((WebViewClient)new c$a(this, (c$1)null));
        this.i.setWebChromeClient(this.b);
        this.i.clearFormData();
        final WebSettings settings = this.i.getSettings();
        if (settings == null) {
            return;
        }
        com.tencent.open.utils.i.a(settings);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings$RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        final WeakReference<Context> j = this.j;
        if (j != null && j.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context)this.j.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.a.a((a$b)new b((c$1)null), "sdk_js_if");
        this.i.clearView();
        this.i.loadUrl(this.d);
    }
    
    private static void c(final Context context, String string) {
        try {
            final JSONObject d = k.d(string);
            final int int1 = d.getInt("type");
            string = d.getString("msg");
            if (int1 == 0) {
                if (com.tencent.open.c.c == null) {
                    com.tencent.open.c.c = Toast.makeText(context, (CharSequence)string, 0);
                }
                else {
                    com.tencent.open.c.c.setView(com.tencent.open.c.c.getView());
                    com.tencent.open.c.c.setText((CharSequence)string);
                    com.tencent.open.c.c.setDuration(0);
                }
                com.tencent.open.c.c.show();
            }
            else if (int1 == 1) {
                if (com.tencent.open.c.c == null) {
                    com.tencent.open.c.c = Toast.makeText(context, (CharSequence)string, 1);
                }
                else {
                    com.tencent.open.c.c.setView(com.tencent.open.c.c.getView());
                    com.tencent.open.c.c.setText((CharSequence)string);
                    com.tencent.open.c.c.setDuration(1);
                }
                com.tencent.open.c.c.show();
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void d(final Context context, final String s) {
        if (context != null) {
            if (s != null) {
                try {
                    final JSONObject d = k.d(s);
                    d.getInt("action");
                    d.getString("msg");
                }
                catch (final JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void a() {
        this.i.getLayoutParams().height = this.k;
        SLog.e("openSDK_LOG.PKDialog", "onKeyboardHidden keyboard hide");
    }
    
    public void a(final int height) {
        final WeakReference<Context> j = this.j;
        if (j != null && j.get() != null) {
            if (height < this.k && 2 == ((Context)this.j.get()).getResources().getConfiguration().orientation) {
                this.i.getLayoutParams().height = height;
            }
            else {
                this.i.getLayoutParams().height = this.k;
            }
        }
        SLog.e("openSDK_LOG.PKDialog", "onKeyboardShown keyboard show");
    }
    
    protected void a(final String s) {
        SLog.d("openSDK_LOG.PKDialog", "--onConsoleMessage--");
        try {
            this.a.a((WebView)this.i, s);
        }
        catch (final Exception ex) {}
    }
    
    public void onBackPressed() {
        super.onBackPressed();
    }
    
    protected void onCreate(final Bundle bundle) {
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        this.getWindow().setSoftInputMode(16);
        this.getWindow().setSoftInputMode(1);
        this.b();
        this.c();
    }
    
    private class b extends a$b
    {
        final c a;
        
        private b(final c a) {
            this.a = a;
        }
    }
    
    private static class c extends DefaultUiListener
    {
        String a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private IUiListener e;
        
        public c(final Context context, final String d, final String a, final String b, final IUiListener e) {
            this.c = (WeakReference<Context>)new WeakReference((Object)context);
            this.d = d;
            this.a = a;
            this.b = b;
            this.e = e;
        }
        
        private void a(final String s) {
            try {
                this.onComplete(com.tencent.open.utils.k.d(s));
            }
            catch (final JSONException ex) {
                ex.printStackTrace();
                this.onError(new UiError(-4, "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u6709\u8bef!", s));
            }
        }
        
        @Override
        public void onCancel() {
            final IUiListener e = this.e;
            if (e != null) {
                e.onCancel();
                this.e = null;
            }
        }
        
        @Override
        public void onComplete(final Object o) {
            final JSONObject jsonObject = (JSONObject)o;
            final h a = com.tencent.open.b.h.a();
            final StringBuilder sb = new StringBuilder();
            sb.append(this.d);
            sb.append("_H5");
            a.a(sb.toString(), SystemClock.elapsedRealtime(), 0L, 0L, jsonObject.optInt("ret", -6), this.a, false);
            final IUiListener e = this.e;
            if (e != null) {
                e.onComplete((Object)jsonObject);
                this.e = null;
            }
        }
        
        @Override
        public void onError(final UiError uiError) {
            String s;
            if (uiError.errorMessage != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(uiError.errorMessage);
                sb.append(this.a);
                s = sb.toString();
            }
            else {
                s = this.a;
            }
            final h a = com.tencent.open.b.h.a();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(this.d);
            sb2.append("_H5");
            a.a(sb2.toString(), SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, s, false);
            final IUiListener e = this.e;
            if (e != null) {
                e.onError(uiError);
                this.e = null;
            }
        }
    }
}
