package com.kingagroot.kingdraw.widget.jsweb;

import com.kingagroot.kingdraw.interfaces.GroupListDBI;
import com.kingagroot.kingdraw.interfaces.WhiteListDbi;
import com.kingagroot.kingdraw.utils.link.GroupListLink$OnGroupListener;
import com.kingagroot.kingdraw.utils.link.GroupListLink;
import com.goodsrc.library.utils.ToastUtil;
import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import com.goodsrc.ui.library.widget.AppManager;
import com.kingagroot.kingdraw.ui.account.LoginMainActivity;
import com.goodsrc.library.utils.NetworkUtil;
import android.webkit.JavascriptInterface;
import com.kingagroot.kingdraw.NewMainActivity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import com.kingagroot.kingdraw.ui.workstation.WebStationActivity;
import android.content.IntentFilter;
import android.webkit.WebSettings;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.ClearNetChemFileHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.CloseHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.OpenHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.BundleDownloadHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.OpenNetChemFileHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.KDNativeBaseHander;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.DownLoadHandler;
import android.webkit.WebChromeClient;
import android.net.Uri;
import com.goodsrc.library.utils.LanguageTool;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import com.kingagroot.kingdraw.model.GroupModel;
import com.kingagroot.kingdraw.interfaces.impl.GroupListDBIMpl;
import com.kingagroot.kingdraw.config.ShareData;
import android.webkit.ValueCallback;
import android.util.Log;
import android.webkit.WebViewClient;
import com.goodsrc.library.utils.AppUtil;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import android.webkit.WebSettings$LayoutAlgorithm;
import org.json.JSONException;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import org.json.JSONObject;
import com.kingagroot.kingdraw.base.MApplication;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.net.URI;
import com.kingagroot.kingdraw.model.WebWhiteModel;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.impl.WhiteListDbiMpl;
import android.util.AttributeSet;
import android.text.TextUtils;
import android.content.Intent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.Activity;
import android.webkit.WebView;

public class JsWebView extends WebView
{
    public static final String INTENT_ACTION_CLOSE_APP = "intent_action_close_app";
    public static final String INTENT_ACTION_REFRESH = "INTENT_ACTION_REFRESH";
    public static String netErrCn = "file:///android_asset/wifi.html";
    public static String netErrEn = "file:///android_asset/wifi_en.html";
    private Activity activity;
    private String appId;
    private LocalBroadcastManager broadcastManager;
    private final Context context;
    private boolean isCloseOther;
    private KDNativeJsInterface kdNativeJsInterface;
    private OnJsWebListener onJsWebListener;
    BroadcastReceiver receiver;
    private SwipeRefreshLayout swipeFresh;
    
    public JsWebView(final Context context) {
        super(context);
        this.isCloseOther = false;
        this.receiver = new BroadcastReceiver() {
            final JsWebView this$0;
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals((Object)"intent_action_close_app") && !TextUtils.isEmpty((CharSequence)this.this$0.appId)) {
                    final String stringExtra = intent.getStringExtra("appid");
                    if (!TextUtils.isEmpty((CharSequence)stringExtra) && stringExtra.equals((Object)this.this$0.appId)) {
                        if (!this.this$0.isCloseOther) {
                            this.this$0.closeMe();
                        }
                        this.this$0.isCloseOther = false;
                    }
                }
            }
        };
        this.context = context;
        this.initialize();
    }
    
    public JsWebView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isCloseOther = false;
        this.receiver = new BroadcastReceiver() {
            final JsWebView this$0;
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals((Object)"intent_action_close_app") && !TextUtils.isEmpty((CharSequence)this.this$0.appId)) {
                    final String stringExtra = intent.getStringExtra("appid");
                    if (!TextUtils.isEmpty((CharSequence)stringExtra) && stringExtra.equals((Object)this.this$0.appId)) {
                        if (!this.this$0.isCloseOther) {
                            this.this$0.closeMe();
                        }
                        this.this$0.isCloseOther = false;
                    }
                }
            }
        };
        this.context = context;
        this.initialize();
    }
    
    public JsWebView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isCloseOther = false;
        this.receiver = new BroadcastReceiver() {
            final JsWebView this$0;
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals((Object)"intent_action_close_app") && !TextUtils.isEmpty((CharSequence)this.this$0.appId)) {
                    final String stringExtra = intent.getStringExtra("appid");
                    if (!TextUtils.isEmpty((CharSequence)stringExtra) && stringExtra.equals((Object)this.this$0.appId)) {
                        if (!this.this$0.isCloseOther) {
                            this.this$0.closeMe();
                        }
                        this.this$0.isCloseOther = false;
                    }
                }
            }
        };
        this.context = context;
        this.initialize();
    }
    
    public static boolean checkDomain(String host) throws URISyntaxException {
        if (!host.startsWith("http://") && !host.startsWith("https://")) {
            return false;
        }
        final List whiteList = ((WhiteListDbi)new WhiteListDbiMpl()).getWhiteList();
        final ArrayList list = new ArrayList();
        if (whiteList != null && whiteList.size() > 0) {
            for (int i = 0; i < whiteList.size(); ++i) {
                ((List)list).add((Object)((WebWhiteModel)whiteList.get(i)).getUrl());
            }
        }
        else {
            ((List)list).add((Object)"kingdraw.com");
            ((List)list).add((Object)"kingdraw.cn");
            ((List)list).add((Object)"kingagroot.com");
        }
        host = new URI(host).getHost();
        final Iterator iterator = ((List)list).iterator();
        while (iterator.hasNext()) {
            if (host.contains((CharSequence)iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    private void closeOther(final String s) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.getInstance());
        final Intent intent = new Intent();
        intent.setAction("intent_action_close_app");
        intent.putExtra("appid", s);
        instance.sendBroadcast(intent);
        this.isCloseOther = true;
    }
    
    private String getDeviceData() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("platform", (Object)"3");
            jsonObject.put("platformType", (Object)HttpHeadUtils.getPlatformType());
            jsonObject.put("platformVersion", (Object)HttpHeadUtils.getSystemVersion());
            jsonObject.put("platformSystem", (Object)HttpHeadUtils.getPhoneInfo());
            jsonObject.put("deviceBrand", (Object)HttpHeadUtils.getDeviceBrand());
            jsonObject.put("deviceModel", (Object)HttpHeadUtils.getSystemModel());
            jsonObject.put("netType", (Object)HttpHeadUtils.netConnectType(LibraryApplication.getContext()));
            jsonObject.put("gprsType", (Object)HttpHeadUtils.cellularType(LibraryApplication.getContext()));
            jsonObject.put("appVersion", (Object)HttpHeadUtils.getVerName(LibraryApplication.getContext()));
            jsonObject.put("channel", (Object)HttpHeadUtils.getAppChannel(LibraryApplication.getContext()));
            jsonObject.put("apiVersion", (Object)HttpHeadUtils.getNetConnectVersion());
            jsonObject.put("udid", (Object)HttpHeadUtils.getUserDeviceId());
            jsonObject.put("timestamp", (Object)String.valueOf(HttpHeadUtils.getCurrentTime()));
            jsonObject.put("noncestr", (Object)HttpHeadUtils.getRandomString(8));
            jsonObject.put("token", (Object)LibraryApplication.getToken());
            jsonObject.put("lang", (Object)HttpHeadUtils.getCurrentLanguage());
            jsonObject.put("VersionType", (Object)String.valueOf(HttpHeadUtils.getVersionType()));
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }
    
    private void initialize() {
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(2);
        settings.setLoadsImagesAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings$LayoutAlgorithm.NARROW_COLUMNS);
        if (AppConfig.RELEASE != Release.STANDARD) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        final StringBuilder sb = new StringBuilder();
        sb.append(settings.getUserAgentString());
        sb.append(";KingDraw/");
        sb.append(AppUtil.getVersionName(this.getContext()));
        sb.append(";KD-SDK/0.0.1");
        settings.setUserAgentString(sb.toString());
        this.setWebViewClient((WebViewClient)new WebViewClient(this) {
            final JsWebView this$0;
            
            public void onPageFinished(final WebView webView, final String s) {
                super.onPageFinished(webView, s);
                if (this.this$0.onJsWebListener != null) {
                    this.this$0.onJsWebListener.onLoadFinish();
                }
                final int groupDefault = ShareData.getGroupDefault();
                String groupOpenID = "";
                if (groupDefault != -1) {
                    final GroupModel defaultGroup = ((GroupListDBI)new GroupListDBIMpl()).getDefaultGroup(groupDefault);
                    if (defaultGroup != null) {
                        groupOpenID = defaultGroup.getGroupOpenID();
                    }
                }
                this.this$0.post((Runnable)new _$$Lambda$JsWebView$2$UhLdXzuxG3BSglHaHKJ6dc7PahI(this, groupOpenID));
                this.this$0.post((Runnable)new _$$Lambda$JsWebView$2$0t5ruinqboyv6Wc5yD_W6LjE6nA(this));
            }
            
            public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                final int errorCode = webResourceError.getErrorCode();
                if (errorCode == -2 || errorCode == -8) {
                    if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                        webView.loadUrl(JsWebView.netErrCn);
                    }
                    else {
                        webView.loadUrl(JsWebView.netErrEn);
                    }
                }
            }
            
            public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                try {
                    if (ShareData.getGatewayState()) {
                        webView.loadUrl(s);
                    }
                    else if (JsWebView.checkDomain(s)) {
                        webView.loadUrl(s);
                    }
                    else {
                        this.this$0.getNewContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                    }
                    return true;
                }
                catch (final Exception ex) {
                    return true;
                }
            }
        });
        this.setWebChromeClient((WebChromeClient)new WebChromeClient(this) {
            final JsWebView this$0;
            
            public void onReceivedTitle(final WebView webView, final String s) {
                super.onReceivedTitle(webView, s);
                if (this.this$0.onJsWebListener != null) {
                    this.this$0.onJsWebListener.onUpdateTitle(s);
                }
            }
        });
        this.addJavascriptInterface((Object)new JsInterface(), "kdApp");
        (this.kdNativeJsInterface = new KDNativeJsInterface(this)).addHander((KDNativeBaseHander)new DownLoadHandler(this));
        this.kdNativeJsInterface.addHander((KDNativeBaseHander)new OpenNetChemFileHandler(this));
        this.kdNativeJsInterface.addHander((KDNativeBaseHander)new BundleDownloadHandler(this));
        this.kdNativeJsInterface.addHander((KDNativeBaseHander)new OpenHandler(this));
        this.kdNativeJsInterface.addHander((KDNativeBaseHander)new CloseHandler(this));
        this.kdNativeJsInterface.addHander((KDNativeBaseHander)new ClearNetChemFileHandler(this));
        final KDNativeJsInterface kdNativeJsInterface = this.kdNativeJsInterface;
        this.addJavascriptInterface((Object)kdNativeJsInterface, kdNativeJsInterface.getInterfaceName());
        this.registerReceiver();
    }
    
    private void registerReceiver() {
        this.broadcastManager = LocalBroadcastManager.getInstance(this.getContext());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("intent_action_close_app");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    private void unRegisterReceiver() {
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    public void closeMe() {
        final Activity activity = this.activity;
        if (activity != null) {
            if (activity instanceof WebStationActivity) {
                ((WebStationActivity)activity).closeActivity();
            }
            else {
                activity.finish();
            }
        }
        else {
            final Context context = this.getContext();
            if (context instanceof WebStationActivity) {
                ((WebStationActivity)context).closeActivity();
            }
            else {
                ((Activity)this.getContext()).finish();
            }
        }
    }
    
    public void endLoading() {
        if (this.swipeFresh != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new _$$Lambda$JsWebView$TlS8ZBKoDdhPXY2KiTEh8VDLIZo(this));
        }
    }
    
    public void finish() {
        final KDNativeJsInterface kdNativeJsInterface = this.kdNativeJsInterface;
        if (kdNativeJsInterface != null) {
            kdNativeJsInterface.destroy();
        }
        this.unRegisterReceiver();
    }
    
    public String getAppId() {
        return this.appId;
    }
    
    public Context getNewContext() {
        final Activity activity = this.activity;
        if (activity != null) {
            return (Context)activity;
        }
        return this.getContext();
    }
    
    public void onCloseHandler(final String s, final String s2) {
        this.isCloseOther = false;
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.closeMe();
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)s2) && s2.equals((Object)"other")) {
            this.closeOther(s);
        }
        else if (!TextUtils.isEmpty((CharSequence)s2) && s2.equals((Object)"me")) {
            this.closeMe();
        }
        else {
            this.closeOther(s);
            this.closeMe();
        }
    }
    
    public void setActivity(final Activity activity) {
        this.activity = activity;
    }
    
    public void setAppId(final String appId) {
        this.appId = appId;
    }
    
    public void setBackShow(final boolean b) {
        final OnJsWebListener onJsWebListener = this.onJsWebListener;
        if (onJsWebListener != null) {
            onJsWebListener.onBackButtonShow(b);
        }
    }
    
    public void setOnJsWebListener(final OnJsWebListener onJsWebListener) {
        this.onJsWebListener = onJsWebListener;
    }
    
    public void setPullRefreshStatus(final boolean b) {
        if (this.swipeFresh != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new _$$Lambda$JsWebView$S7lwvxDAmuxyJI9LtpN2UPTvu_I(this, b));
        }
    }
    
    public void setRefresh(final String s, final String s2) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)MApplication.getInstance());
        final Intent intent = new Intent();
        intent.setAction("INTENT_ACTION_REFRESH");
        intent.putExtra("url", s);
        intent.putExtra("appid", s2);
        instance.sendBroadcast(intent);
    }
    
    public void setSwipeFresh(final SwipeRefreshLayout swipeFresh) {
        this.swipeFresh = swipeFresh;
    }
    
    public void startLoading() {
        if (this.swipeFresh != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new _$$Lambda$JsWebView$yxZiheAgWHfdDlRUfQqgWPtarBU(this));
        }
    }
    
    public class JsInterface
    {
        final JsWebView this$0;
        
        public JsInterface(final JsWebView this$0) {
            this.this$0 = this$0;
        }
        
        @JavascriptInterface
        public void onCloseWeb() {
            Log.d("JavascriptInterface", "onCloseWeb");
            final JsWebView this$0 = this.this$0;
            this$0.post((Runnable)new _$$Lambda$3F80JrOLCYJBRB_a2iCCJrAhka0(this$0));
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onCloseWeb();
            }
        }
        
        @JavascriptInterface
        public void onGetDeviceData() {
            this.this$0.post((Runnable)new _$$Lambda$JsWebView$JsInterface$yqCyO8pbPXOt_yaBuAb80fK8BSs(this));
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onGetDeviceData();
            }
        }
        
        @JavascriptInterface
        public void onGetOrgId() {
            final int groupDefault = ShareData.getGroupDefault();
            String groupOpenID;
            if (groupDefault == -1) {
                groupOpenID = "";
            }
            else {
                groupOpenID = ((GroupListDBI)new GroupListDBIMpl()).getDefaultGroup(groupDefault).getGroupOpenID();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("setOrgId:");
            sb.append(groupOpenID);
            Log.e("JavascriptInterface", sb.toString());
            this.this$0.post((Runnable)new _$$Lambda$JsWebView$JsInterface$20qnQfophJ4oq5yH6NuxE6o8f2I(this, groupOpenID));
        }
        
        @JavascriptInterface
        public void onGetUserToken() {
            final String userToken = MApplication.getUserToken();
            if (!TextUtils.isEmpty((CharSequence)userToken)) {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", (Object)userToken);
                }
                catch (final JSONException ex) {
                    ex.printStackTrace();
                }
                this.this$0.post((Runnable)new _$$Lambda$JsWebView$JsInterface$ub6cKCvGLMAXi0dYBLjIvx_oPqQ(this, jsonObject));
            }
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onGetUserToken();
            }
        }
        
        @JavascriptInterface
        public void onLogin() {
            Log.e("JavascriptInterface", "onLogin");
            if (NetworkUtil.isNetworkConnected(this.this$0.context)) {
                this.this$0.context.startActivity(new Intent(this.this$0.context, (Class)LoginMainActivity.class));
            }
            else {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)AppManager.getInstance().getLastActivity());
                alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)this.this$0.context.getString(2131821066)).setPositiveButton(2131820731, (DialogInterface$OnClickListener)_$$Lambda$JsWebView$JsInterface$hEdOm_oCluxwvr09TVicbxeWHWM.INSTANCE);
                final AlertDialog create = alertDialog$Builder.create();
                create.setCanceledOnTouchOutside(false);
                create.setCancelable(false);
                create.show();
                create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
            }
        }
        
        @JavascriptInterface
        public void onLogout() {
            Log.d("JavascriptInterface", "onLogout");
            MApplication.getInstance().userLogout();
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onLogout();
            }
        }
        
        @JavascriptInterface
        public void onOpenAppUrl(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("onOpenAppUrl\uff1a");
            sb.append(s);
            Log.d("JavascriptInterface", sb.toString());
            if (!NetworkUtil.isNetworkConnected(this.this$0.context)) {
                ToastUtil.showShort(2131821067);
            }
            else if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onOpenAppUrl(s);
            }
        }
        
        @JavascriptInterface
        public void onOpenUrl(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("onOpenUrl:");
            sb.append(s);
            Log.d("JavascriptInterface", sb.toString());
            if (!NetworkUtil.isNetworkConnected(this.this$0.context)) {
                ToastUtil.showShort(2131821067);
            }
            else if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onOpenUrl(s);
            }
        }
        
        @JavascriptInterface
        public void onReLogin() {
            MApplication.getInstance().userLogout();
            if (NetworkUtil.isNetworkConnected(this.this$0.context)) {
                this.this$0.context.startActivity(new Intent(this.this$0.context, (Class)LoginMainActivity.class));
            }
            else {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)AppManager.getInstance().getLastActivity());
                alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)this.this$0.context.getString(2131821066)).setPositiveButton(2131820731, (DialogInterface$OnClickListener)_$$Lambda$JsWebView$JsInterface$Cm1vywxPYiYpUIXuqgqETy_8CWc.INSTANCE);
                final AlertDialog create = alertDialog$Builder.create();
                create.setCanceledOnTouchOutside(false);
                create.setCancelable(false);
                create.show();
                create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
            }
        }
        
        @JavascriptInterface
        public void onSetBadge(final int n) {
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onSetBadge(n);
            }
        }
        
        @JavascriptInterface
        public void onUpdateBadge() {
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onUpdateBadge();
            }
        }
        
        @JavascriptInterface
        public void onUpdateOrgList() {
            Log.d("JavascriptInterface", "onUpdateOrgList");
            new GroupListLink((GroupListLink$OnGroupListener)_$$Lambda$JsWebView$JsInterface$jsHe7JofRJIJVN1drh9qV6jdJ_k.INSTANCE).getUserGroupList();
        }
        
        @JavascriptInterface
        public void onUpdateTitle(final String s) {
            final StringBuilder sb = new StringBuilder();
            sb.append("onUpdateTitle\uff1a");
            sb.append(s);
            Log.d("JavascriptInterface", sb.toString());
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onUpdateTitle(s);
            }
        }
        
        @JavascriptInterface
        public void onUserAccountChanged() {
            Log.d("JavascriptInterface", "onUserAccountChanged");
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onUserAccountChanged();
            }
        }
        
        @JavascriptInterface
        public void onUserPwdChanged() {
            Log.d("JavascriptInterface", "onUserPwdChanged");
            if (this.this$0.onJsWebListener != null) {
                this.this$0.onJsWebListener.onUserPwdChanged();
            }
        }
    }
}
