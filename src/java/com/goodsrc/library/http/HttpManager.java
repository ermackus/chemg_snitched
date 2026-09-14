package com.goodsrc.library.http;

import com.google.gson.Gson;
import com.goodsrc.library.utils.L;
import com.goodsrc.library.utils.ModelUtil;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Handler;
import com.goodsrc.library.utils.ToastUtil;
import org.xutils.x;
import org.xutils.common.Callback$CommonCallback;
import com.goodsrc.library.utils.NetworkUtil;
import org.xutils.common.Callback$CancelledException;
import java.io.File;
import org.xutils.common.Callback$ProgressCallback;
import org.xutils.http.RequestParams;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import android.app.Activity;
import java.util.Iterator;
import java.util.Map$Entry;
import android.text.TextUtils;
import com.goodsrc.library.utils.AppUtil;
import com.goodsrc.library.core.LibraryApplication;
import java.util.Map;
import android.app.ProgressDialog;
import org.xutils.http.HttpMethod;
import android.content.Context;
import org.xutils.common.Callback$Cancelable;

public class HttpManager
{
    public static String CHART_GB2312 = "GB2312";
    public static String CHART_UTF8 = "UTF-8";
    private Callback$Cancelable cancelable;
    private final int connectTimeOut;
    private final Context context;
    private final HttpMethod httpMethod;
    private String loadingStr;
    private ProgressDialog progressDialog;
    private final int readTimeOut;
    private final String textCharset;
    
    private HttpManager(final Builder builder) {
        this.loadingStr = "";
        this.context = builder.context;
        this.connectTimeOut = builder.connectTimeOut;
        this.readTimeOut = builder.readTimeOut;
        this.textCharset = builder.textCharset;
        this.httpMethod = builder.httpMethod;
        this.loadingStr = builder.loadingStr;
    }
    
    public static String getUrl(String token, final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        sb.append(token);
        if (!token.endsWith("?")) {
            sb.append("?");
        }
        if (!token.endsWith("?") && !sb.toString().endsWith("?")) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("&version=");
            sb2.append(AppUtil.getVersionCode(LibraryApplication.getContext()));
            sb.append(sb2.toString());
        }
        else {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("version=");
            sb3.append(AppUtil.getVersionCode(LibraryApplication.getContext()));
            sb.append(sb3.toString());
        }
        token = LibraryApplication.getToken();
        if (!TextUtils.isEmpty((CharSequence)token)) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("&token=");
            sb4.append(token);
            sb.append(sb4.toString());
        }
        final String language = LibraryApplication.getLanguage();
        if (!TextUtils.isEmpty((CharSequence)language)) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("&lang=");
            sb5.append(language);
            sb.append(sb5.toString());
        }
        if (map != null) {
            for (final Map$Entry map$Entry : map.entrySet()) {
                final String s = (String)map$Entry.getKey();
                final String s2 = (String)map$Entry.getValue();
                final StringBuilder sb6 = new StringBuilder();
                sb6.append("&");
                sb6.append(s);
                sb6.append("=");
                sb6.append(s2);
                sb.append(sb6.toString());
            }
        }
        return sb.toString();
    }
    
    private void showProgress(final Context context) {
        final Activity activity = (Activity)context;
        if (context != null) {
            if (!activity.isFinishing()) {
                final ProgressDialog progressDialog = this.progressDialog;
                if (progressDialog == null || progressDialog.getContext() != context) {
                    (this.progressDialog = ProgressDialog.show(context, (CharSequence)null, (CharSequence)this.loadingStr)).setCanceledOnTouchOutside(true);
                    this.progressDialog.setOnDismissListener((DialogInterface$OnDismissListener)new DialogInterface$OnDismissListener(this) {
                        final HttpManager this$0;
                        
                        public void onDismiss(final DialogInterface dialogInterface) {
                            if (this.this$0.cancelable != null && !this.this$0.cancelable.isCancelled()) {
                                this.this$0.cancelable.cancel();
                            }
                        }
                    });
                }
                this.progressDialog.show();
            }
        }
    }
    
    public <T> Callback$Cancelable downLoad(final RequestParams requestParams, final String saveFilePath, final boolean autoResume, final RequestCallBack<T> requestCallBack) {
        requestParams.setSaveFilePath(saveFilePath);
        requestParams.setAutoResume(autoResume);
        return this.cancelable = this.http().get(requestParams, (Callback$CommonCallback)new Callback$ProgressCallback<File>(this, requestCallBack) {
            final HttpManager this$0;
            final RequestCallBack val$requestCallBack;
            
            public void onCancelled(final Callback$CancelledException ex) {
                this.val$requestCallBack.onCancelled();
            }
            
            public void onError(final Throwable t, final boolean b) {
                this.val$requestCallBack.onError(new Exception(t), "");
            }
            
            public void onFinished() {
                this.val$requestCallBack.onFinished();
                this.this$0.stopProgress();
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                this.val$requestCallBack.onLoading(n, n2, b);
            }
            
            public void onStarted() {
                if (!NetworkUtil.isNetworkConnected(LibraryApplication.getContext())) {
                    this.this$0.cancelable.cancel();
                    this.val$requestCallBack.onError(null, "");
                }
                else {
                    this.val$requestCallBack.onStarted();
                    final HttpManager this$0 = this.this$0;
                    this$0.showProgress(this$0.context);
                }
            }
            
            public void onSuccess(final File file) {
                try {
                    if (File.class == this.val$requestCallBack.mType) {
                        this.val$requestCallBack.onSuccess(file);
                    }
                    else {
                        this.val$requestCallBack.onError(null, null);
                    }
                }
                catch (final Exception ex) {
                    ex.printStackTrace();
                    this.val$requestCallBack.onError(ex, ex.getMessage());
                }
            }
            
            public void onWaiting() {
            }
        });
    }
    
    public org.xutils.HttpManager http() {
        return x.http();
    }
    
    public boolean onNetBeanType(Object o, final RequestCallBack requestCallBack) {
        if (o.getClass() == NetBean.class) {
            try {
                final NetBean netBean = (NetBean)o;
                if (((NetBean)o).getType() != null && netBean.getType().equals((Object)NetBeanTypeEnum.RELOGIN.data)) {
                    o = new Exception("");
                    requestCallBack.onError((Exception)o, netBean.getInfo());
                    ToastUtil.showFormatInfo((CharSequence)netBean.getInfo());
                    o = new Handler();
                    ((Handler)o).postDelayed((Runnable)new Runnable(this) {
                        final HttpManager this$0;
                        
                        public void run() {
                            LocalBroadcastManager.getInstance(LibraryApplication.getContext()).sendBroadcast(new Intent("kingagroot.intent.action.http_relogin"));
                        }
                    }, 1000L);
                    return false;
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    public RequestParams params(final String s) {
        return this.params(s, null);
    }
    
    public RequestParams params(String s, final Object o) {
        final RequestParams requestParams = new RequestParams(s);
        requestParams.setConnectTimeout(this.connectTimeOut);
        requestParams.setCharset(this.textCharset);
        requestParams.setReadTimeout(this.connectTimeOut);
        requestParams.addHeader("platform", "3");
        requestParams.addHeader("isPro", "0");
        s = LibraryApplication.getToken();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            requestParams.addBodyParameter("token", s);
        }
        s = LibraryApplication.getLanguage();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            requestParams.addBodyParameter("lang", s);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(AppUtil.getVersionCode(LibraryApplication.getContext()));
        sb.append("");
        requestParams.addBodyParameter("Version", sb.toString());
        requestParams.addParameter("uuid", (Object)AppUtil.getImei(LibraryApplication.getContext()));
        requestParams.addParameter("isIos", (Object)0);
        requestParams.addParameter("platform", (Object)"android");
        requestParams.addParameter("systemversion", (Object)String.valueOf(Build$VERSION.SDK_INT));
        requestParams.addParameter("phonemodel", (Object)Build.MODEL);
        requestParams.addParameter("hardwareinfo", (Object)AppUtil.getHardwareinfo());
        RequestParams requestParamsReflect = requestParams;
        if (o != null) {
            requestParamsReflect = ModelUtil.RequestParamsReflect(o, requestParams);
        }
        return requestParamsReflect;
    }
    
    public <T> Callback$Cancelable request(final RequestParams requestParams, final RequestCallBack<T> requestCallBack) {
        return this.cancelable = this.http().request(this.httpMethod, requestParams, (Callback$CommonCallback)new Callback$ProgressCallback<String>(this, requestCallBack, requestParams) {
            final HttpManager this$0;
            final RequestParams val$params;
            final RequestCallBack val$requestCallBack;
            
            public void onCancelled(final Callback$CancelledException ex) {
                this.val$requestCallBack.onCancelled();
            }
            
            public void onError(final Throwable t, final boolean b) {
                L.e(this.val$params.toString(), t);
                this.val$requestCallBack.onError(new Exception(t), "");
            }
            
            public void onFinished() {
                this.val$requestCallBack.onFinished();
                this.this$0.stopProgress();
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                this.val$requestCallBack.onLoading(n, n2, b);
            }
            
            public void onStarted() {
                if (!NetworkUtil.isNetworkConnected(LibraryApplication.getContext())) {
                    this.this$0.cancelable.cancel();
                    this.val$requestCallBack.onError(null, "");
                }
                else {
                    this.val$requestCallBack.onStarted();
                    final HttpManager this$0 = this.this$0;
                    this$0.showProgress(this$0.context);
                }
            }
            
            public void onSuccess(final String s) {
                try {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(this.val$params.toString());
                    sb.append(":\n");
                    sb.append(s);
                    L.i(sb.toString());
                    if (String.class != this.val$requestCallBack.mType) {
                        final Object fromJson = new Gson().fromJson(s, this.val$requestCallBack.mType);
                        if (fromJson == null) {
                            this.val$requestCallBack.onError(null, "response null");
                            return;
                        }
                        if (this.this$0.onNetBeanType(fromJson, this.val$requestCallBack)) {
                            this.val$requestCallBack.onSuccess(fromJson);
                        }
                    }
                    else {
                        this.val$requestCallBack.onSuccess(s);
                    }
                }
                catch (final Exception ex) {
                    ex.printStackTrace();
                    this.val$requestCallBack.onError(ex, ex.getMessage());
                }
            }
            
            public void onWaiting() {
            }
        });
    }
    
    public void stopProgress() {
        final ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
    
    public static class Builder
    {
        private int connectTimeOut;
        private Context context;
        private HttpMethod httpMethod;
        private String loadingStr;
        private int readTimeOut;
        private String textCharset;
        
        public Builder() {
            this.connectTimeOut = 120000;
            this.readTimeOut = 120000;
            this.textCharset = HttpManager.CHART_UTF8;
            this.httpMethod = HttpMethod.POST;
            this.loadingStr = "\u52a0\u8f7d\u4e2d···";
        }
        
        public HttpManager build() {
            return new HttpManager(this, null);
        }
        
        public Builder setConnectTimeOut(final int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
            return this;
        }
        
        public Builder setContext(final Context context) {
            this.context = context;
            return this;
        }
        
        public Builder setHttpMethod(final HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }
        
        public Builder setLoadingStr(final String loadingStr) {
            this.loadingStr = loadingStr;
            return this;
        }
        
        public Builder setReadTimeOut(final int readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }
        
        public Builder setTextCharset(final String textCharset) {
            this.textCharset = textCharset;
            return this;
        }
    }
}
