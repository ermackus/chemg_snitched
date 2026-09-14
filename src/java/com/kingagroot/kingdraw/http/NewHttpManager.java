package com.kingagroot.kingdraw.http;

import com.goodsrc.library.utils.ModelUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.content.DialogInterface;
import android.os.Handler;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.base.MApplication;
import org.xutils.x;
import org.xutils.HttpManager;
import org.xutils.common.Callback$CommonCallback;
import com.goodsrc.library.http.RequestCallBack;
import org.xutils.http.RequestParams;
import android.content.DialogInterface$OnDismissListener;
import android.app.Activity;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.Map$Entry;
import com.goodsrc.library.core.LibraryApplication;
import java.util.Map;
import android.app.ProgressDialog;
import org.xutils.http.HttpMethod;
import android.content.Context;
import org.xutils.common.Callback$Cancelable;

public class NewHttpManager
{
    public static String CHART_UTF8 = "UTF-8";
    private Callback$Cancelable cancelable;
    private final int connectTimeOut;
    private final Context context;
    private final HttpMethod httpMethod;
    private final String loadingStr;
    private ProgressDialog progressDialog;
    private final String textCharset;
    
    private NewHttpManager(final Builder builder) {
        this.context = builder.context;
        this.connectTimeOut = 120000;
        this.textCharset = builder.textCharset;
        this.httpMethod = builder.httpMethod;
        this.loadingStr = "\u52a0\u8f7d\u4e2d···";
    }
    
    public static String getUrl(final String s, final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        if (!s.endsWith("?")) {
            sb.append("?");
        }
        if (s.endsWith("?") || sb.toString().endsWith("?")) {
            sb.append("Lang=");
            sb.append(HttpHeadUtils.getCurrentLanguage());
        }
        else {
            sb.append("&Lang=");
            sb.append(HttpHeadUtils.getCurrentLanguage());
        }
        sb.append("&AppVersion=");
        sb.append(HttpHeadUtils.getVerName(LibraryApplication.getContext()));
        if (map != null) {
            for (final Map$Entry map$Entry : map.entrySet()) {
                final String s2 = (String)map$Entry.getKey();
                final String s3 = (String)map$Entry.getValue();
                sb.append("&");
                sb.append(s2);
                sb.append("=");
                sb.append(s3);
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
                    this.progressDialog.setOnDismissListener((DialogInterface$OnDismissListener)new _$$Lambda$NewHttpManager$PLuSwG2YjRUjUtA1WVdXr96da6U(this));
                }
                this.progressDialog.show();
            }
        }
    }
    
    public <T> Callback$Cancelable downLoad(final RequestParams requestParams, final String saveFilePath, final boolean autoResume, final RequestCallBack<T> requestCallBack) {
        requestParams.setSaveFilePath(saveFilePath);
        requestParams.setAutoResume(autoResume);
        return this.cancelable = this.http().get(requestParams, (Callback$CommonCallback)new NewHttpManager$2(this, (RequestCallBack)requestCallBack));
    }
    
    public RequestParams emptyParams(final String s) {
        final RequestParams requestParams = new RequestParams(s);
        requestParams.setConnectTimeout(this.connectTimeOut);
        requestParams.setCharset(this.textCharset);
        requestParams.setReadTimeout(this.connectTimeOut);
        return requestParams;
    }
    
    public HttpManager http() {
        return x.http();
    }
    
    public boolean isReLogin(final Object o, final RequestCallBack requestCallBack) {
        if (o.getClass() == NewNetBean.class) {
            final NewNetBean newNetBean = (NewNetBean)o;
            if (newNetBean.getCode() == 401) {
                requestCallBack.onError(new Exception(""), newNetBean.getMessage());
                MApplication.getInstance().userLogout();
                ToastUtil.showFormatInfo((CharSequence)newNetBean.getMessage());
                new Handler().postDelayed((Runnable)_$$Lambda$NewHttpManager$uB8tkiX9OenSyowyU5k4c7rmu9I.INSTANCE, 1000L);
                return false;
            }
        }
        return true;
    }
    
    public RequestParams params(final String s) {
        return this.params(s, null);
    }
    
    public RequestParams params(final String s, final Object o) {
        final RequestParams requestParams = new RequestParams(s);
        requestParams.setConnectTimeout(this.connectTimeOut);
        requestParams.setCharset(this.textCharset);
        requestParams.setReadTimeout(this.connectTimeOut);
        try {
            requestParams.addHeader("deviceBrand", URLDecoder.decode(HttpHeadUtils.getDeviceBrand(), "UTF-8"));
            requestParams.addHeader("deviceModel", URLDecoder.decode(HttpHeadUtils.getSystemModel(), "UTF-8"));
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        requestParams.addHeader("platform", "3");
        requestParams.addHeader("platformType", HttpHeadUtils.getPlatformType());
        requestParams.addHeader("platformVersion", HttpHeadUtils.getSystemVersion());
        requestParams.addHeader("platformSystem", HttpHeadUtils.getPhoneInfo());
        requestParams.addHeader("netType", HttpHeadUtils.netConnectType(LibraryApplication.getContext()));
        requestParams.addHeader("gprsType", HttpHeadUtils.cellularType(LibraryApplication.getContext()));
        requestParams.addHeader("appVersion", HttpHeadUtils.getVerName(LibraryApplication.getContext()));
        requestParams.addHeader("channel", HttpHeadUtils.getAppChannel(LibraryApplication.getContext()));
        requestParams.addHeader("apiVersion", HttpHeadUtils.getNetConnectVersion());
        requestParams.addHeader("udid", HttpHeadUtils.getUserDeviceId());
        requestParams.addHeader("oldudid", HttpHeadUtils.getOldDeviceId());
        requestParams.addHeader("timestamp", String.valueOf(HttpHeadUtils.getCurrentTime()));
        requestParams.addHeader("noncestr", HttpHeadUtils.getRandomString(8));
        requestParams.addHeader("token", LibraryApplication.getToken());
        requestParams.addHeader("lang", HttpHeadUtils.getCurrentLanguage());
        requestParams.addHeader("VersionType", String.valueOf(HttpHeadUtils.getVersionType()));
        RequestParams requestParamsReflect = requestParams;
        if (o != null) {
            requestParamsReflect = ModelUtil.RequestParamsReflect(o, requestParams);
        }
        return requestParamsReflect;
    }
    
    public <T> Callback$Cancelable request(final RequestParams requestParams, final RequestCallBack<T> requestCallBack) {
        return this.cancelable = this.http().request(this.httpMethod, requestParams, (Callback$CommonCallback)new NewHttpManager$1(this, (RequestCallBack)requestCallBack, requestParams));
    }
    
    public void stopProgress() {
        final ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
    
    public static class Builder
    {
        private final int connectTimeOut;
        private Context context;
        private HttpMethod httpMethod;
        private final String loadingStr;
        private final String textCharset;
        
        public Builder() {
            this.connectTimeOut = 120000;
            this.textCharset = NewHttpManager.CHART_UTF8;
            this.loadingStr = "\u52a0\u8f7d\u4e2d···";
            this.httpMethod = HttpMethod.POST;
        }
        
        public NewHttpManager build() {
            return new NewHttpManager(this, null);
        }
        
        public Builder setContext(final Context context) {
            this.context = context;
            return this;
        }
        
        public Builder setHttpMethod(final HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }
    }
}
