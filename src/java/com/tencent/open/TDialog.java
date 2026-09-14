package com.tencent.open;

import android.os.SystemClock;
import com.tencent.open.b.h;
import com.tencent.tauth.UiError;
import com.tencent.tauth.DefaultUiListener;
import android.os.Looper;
import android.os.Bundle;
import android.webkit.WebView;
import com.tencent.open.log.SLog;
import org.json.JSONObject;
import org.json.JSONException;
import com.tencent.open.utils.k;
import android.webkit.WebSettings;
import android.webkit.WebSettings$RenderPriority;
import com.tencent.open.utils.i;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.TextView;
import com.tencent.connect.auth.QQToken;
import android.os.Handler;
import android.widget.FrameLayout;
import com.tencent.tauth.IUiListener;
import android.content.Context;
import android.app.ProgressDialog;
import java.lang.ref.WeakReference;
import android.widget.Toast;
import android.widget.FrameLayout$LayoutParams;

public class TDialog extends b
{
    static final FrameLayout$LayoutParams c;
    static Toast d;
    private static WeakReference<ProgressDialog> f;
    private WeakReference<Context> e;
    private String g;
    private OnTimeListener h;
    private IUiListener i;
    private FrameLayout j;
    private com.tencent.open.c.b k;
    private Handler l;
    private boolean m;
    private QQToken n;
    
    static {
        c = new FrameLayout$LayoutParams(-1, -1);
        TDialog.d = null;
    }
    
    public TDialog(final Context context, final String s, final String g, final IUiListener i, final QQToken n) {
        super(context, 16973840);
        this.m = false;
        this.n = null;
        this.e = (WeakReference<Context>)new WeakReference((Object)context);
        this.g = g;
        this.h = new OnTimeListener(context, s, g, n.getAppId(), i);
        this.l = (Handler)new TDialog.TDialog$THandler(this, this.h, context.getMainLooper());
        this.i = i;
        this.n = n;
    }
    
    private void a() {
        new TextView((Context)this.e.get()).setText((CharSequence)"test");
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        (this.k = new com.tencent.open.c.b((Context)this.e.get())).setLayoutParams((ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.j = new FrameLayout((Context)this.e.get());
        frameLayout$LayoutParams.gravity = 17;
        this.j.setLayoutParams((ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.j.addView((View)this.k);
        this.setContentView((View)this.j);
    }
    
    private void b() {
        this.k.setVerticalScrollBarEnabled(false);
        this.k.setHorizontalScrollBarEnabled(false);
        this.k.setWebViewClient((WebViewClient)new TDialog.TDialog$FbWebViewClient(this, (TDialog$1)null));
        this.k.setWebChromeClient(this.b);
        this.k.clearFormData();
        final WebSettings settings = this.k.getSettings();
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
        final WeakReference<Context> e = this.e;
        if (e != null && e.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context)this.e.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.a.a((a$b)new JsListener((TDialog$1)null), "sdk_js_if");
        this.k.loadUrl(this.g);
        this.k.setLayoutParams((ViewGroup$LayoutParams)TDialog.c);
        this.k.setVisibility(4);
    }
    
    private static void c(final Context context, String string) {
        try {
            final JSONObject d = k.d(string);
            final int int1 = d.getInt("type");
            string = d.getString("msg");
            if (int1 == 0) {
                if (TDialog.d == null) {
                    TDialog.d = Toast.makeText(context, (CharSequence)string, 0);
                }
                else {
                    TDialog.d.setView(TDialog.d.getView());
                    TDialog.d.setText((CharSequence)string);
                    TDialog.d.setDuration(0);
                }
                TDialog.d.show();
            }
            else if (int1 == 1) {
                if (TDialog.d == null) {
                    TDialog.d = Toast.makeText(context, (CharSequence)string, 1);
                }
                else {
                    TDialog.d.setView(TDialog.d.getView());
                    TDialog.d.setText((CharSequence)string);
                    TDialog.d.setDuration(1);
                }
                TDialog.d.show();
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
                    final int int1 = d.getInt("action");
                    final String string = d.getString("msg");
                    if (int1 == 1) {
                        if (TDialog.f != null && TDialog.f.get() != null) {
                            ((ProgressDialog)TDialog.f.get()).setMessage((CharSequence)string);
                            if (!((ProgressDialog)TDialog.f.get()).isShowing()) {
                                ((ProgressDialog)TDialog.f.get()).show();
                            }
                        }
                        else {
                            final ProgressDialog progressDialog = new ProgressDialog(context);
                            progressDialog.setMessage((CharSequence)string);
                            TDialog.f = (WeakReference<ProgressDialog>)new WeakReference((Object)progressDialog);
                            progressDialog.show();
                        }
                    }
                    else if (int1 == 0) {
                        if (TDialog.f == null) {
                            return;
                        }
                        if (TDialog.f.get() != null && ((ProgressDialog)TDialog.f.get()).isShowing()) {
                            ((ProgressDialog)TDialog.f.get()).dismiss();
                            TDialog.f = null;
                        }
                    }
                }
                catch (final JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    protected void a(final String s) {
        SLog.d("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.a.a((WebView)this.k, s);
        }
        catch (final Exception ex) {}
    }
    
    public void onBackPressed() {
        final OnTimeListener h = this.h;
        if (h != null) {
            h.onCancel();
        }
        super.onBackPressed();
    }
    
    protected void onCreate(final Bundle bundle) {
        this.requestWindowFeature(1);
        super.onCreate(bundle);
        this.a();
        new Handler(Looper.getMainLooper()).post((Runnable)new TDialog$1(this));
        this.b();
    }
    
    private class JsListener extends a$b
    {
        final TDialog a;
        
        private JsListener(final TDialog a) {
            this.a = a;
        }
        
        public void onAddShare(final String s) {
            SLog.d("openSDK_LOG.TDialog", "JsListener onAddShare");
            this.onComplete(s);
        }
        
        public void onCancel(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("JsListener onCancel --msg = ");
            sb.append(s);
            SLog.e("openSDK_LOG.TDialog", sb.toString());
            this.a.l.obtainMessage(2, (Object)s).sendToTarget();
            this.a.dismiss();
        }
        
        public void onCancelAddShare(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("JsListener onCancelAddShare");
            sb.append(s);
            SLog.e("openSDK_LOG.TDialog", sb.toString());
            this.onCancel("cancel");
        }
        
        public void onCancelInvite() {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            this.onCancel("");
        }
        
        public void onCancelLogin() {
            this.onCancel("");
        }
        
        public void onComplete(final String s) {
            this.a.l.obtainMessage(1, (Object)s).sendToTarget();
            final StringBuilder sb = new StringBuilder();
            sb.append("JsListener onComplete");
            sb.append(s);
            SLog.e("openSDK_LOG.TDialog", sb.toString());
            this.a.dismiss();
        }
        
        public void onInvite(final String s) {
            this.onComplete(s);
        }
        
        public void onLoad(final String s) {
            this.a.l.obtainMessage(4, (Object)s).sendToTarget();
        }
        
        public void showMsg(final String s) {
            this.a.l.obtainMessage(3, (Object)s).sendToTarget();
        }
    }
    
    private static class OnTimeListener extends DefaultUiListener
    {
        String a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private IUiListener e;
        
        public OnTimeListener(final Context context, final String d, final String a, final String b, final IUiListener e) {
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
