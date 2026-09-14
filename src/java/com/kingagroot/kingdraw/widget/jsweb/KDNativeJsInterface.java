package com.kingagroot.kingdraw.widget.jsweb;

import com.kingagroot.kingdraw.interfaces.GroupListDBI;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.OpenNetChemFileHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.OpenHandler;
import android.webkit.ValueCallback;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import com.kingagroot.kingdraw.interfaces.impl.GroupListDBIMpl;
import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.DownLoadHandler;
import java.util.Iterator;
import java.util.Map$Entry;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.CloseHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.ClearNetChemFileHandler;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.BundleDownloadHandler;
import android.webkit.JavascriptInterface;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import android.util.Log;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.widget.jsweb.KDNativeHander.KDNativeBaseHander;
import java.util.HashMap;

public class KDNativeJsInterface
{
    private HashMap<String, KDNativeBaseHander> handers;
    private JsWebView webView;
    
    public KDNativeJsInterface(final JsWebView webView) {
        this.handers = (HashMap<String, KDNativeBaseHander>)new HashMap();
        this.webView = webView;
        if (webView == null) {}
    }
    
    public void addHander(final KDNativeBaseHander kdNativeBaseHander) {
        this.handers.put((Object)kdNativeBaseHander.handerName(), (Object)kdNativeBaseHander);
    }
    
    @JavascriptInterface
    public void alert(final String message) {
        final JsWebView webView = this.webView;
        if (webView == null) {
            return;
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(webView.getNewContext());
        alertDialog$Builder.setTitle((CharSequence)this.webView.getNewContext().getString(2131821524)).setMessage((CharSequence)message).setPositiveButton((CharSequence)this.webView.getNewContext().getString(2131820731), (DialogInterface$OnClickListener)_$$Lambda$KDNativeJsInterface$rcVhkIO_EkPdKSxvqp9dm6Guc9I.INSTANCE);
        alertDialog$Builder.show().getButton(-1).setTextColor(ContextCompat.getColor(this.webView.getNewContext(), 2131099698));
    }
    
    @JavascriptInterface
    public void backToWorkbench() {
        Log.e("action", "backToWorkbench");
        this.webView.closeMe();
    }
    
    @JavascriptInterface
    public void bundleDownload(final String s) {
        final BundleDownloadHandler bundleDownloadHandler = (BundleDownloadHandler)this.handers.get((Object)"bundleDownload");
        if (bundleDownloadHandler != null) {
            bundleDownloadHandler.action(s);
        }
    }
    
    @JavascriptInterface
    public void clearNetChemFile(final String s) {
        final ClearNetChemFileHandler clearNetChemFileHandler = (ClearNetChemFileHandler)this.handers.get((Object)"clearNetChemFile");
        if (clearNetChemFileHandler != null) {
            clearNetChemFileHandler.action(s);
        }
    }
    
    @JavascriptInterface
    public void close(final String s) {
        final CloseHandler closeHandler = (CloseHandler)this.handers.get((Object)"close");
        if (closeHandler != null) {
            closeHandler.action(s);
        }
    }
    
    public void destroy() {
        final Iterator iterator = this.handers.entrySet().iterator();
        while (iterator.hasNext()) {
            ((KDNativeBaseHander)((Map$Entry)iterator.next()).getValue()).destroy();
        }
    }
    
    @JavascriptInterface
    public void download(final String s) {
        final DownLoadHandler downLoadHandler = (DownLoadHandler)this.handers.get((Object)"download");
        if (downLoadHandler != null) {
            downLoadHandler.action(s);
        }
    }
    
    @JavascriptInterface
    public void getCurrentOrgId(String string) {
        if (this.webView == null) {
            return;
        }
        final int groupDefault = ShareData.getGroupDefault();
        String groupOpenID;
        if (groupDefault == -1) {
            groupOpenID = "";
        }
        else {
            groupOpenID = ((GroupListDBI)new GroupListDBIMpl()).getDefaultGroup(groupDefault).getGroupOpenID();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd.callbackData('");
        sb.append(string);
        sb.append("','");
        sb.append(groupOpenID);
        sb.append("')");
        string = sb.toString();
        this.webView.post((Runnable)new _$$Lambda$KDNativeJsInterface$hVp7OB6OuETwYRqXMQuiY_cZk4M(this, string));
    }
    
    @JavascriptInterface
    public void getDeviceId(String string) {
        if (this.webView == null) {
            return;
        }
        final String userDeviceId = HttpHeadUtils.getUserDeviceId();
        final StringBuilder sb = new StringBuilder();
        sb.append("kd.callbackData('");
        sb.append(string);
        sb.append("','");
        sb.append(userDeviceId);
        sb.append("')");
        string = sb.toString();
        this.webView.post((Runnable)new _$$Lambda$KDNativeJsInterface$anIvjJlpnqMFpbl3excNGcZs1do(this, string));
    }
    
    public String getInterfaceName() {
        return "KingDrawNative";
    }
    
    @JavascriptInterface
    public void open(final String s) {
        final OpenHandler openHandler = (OpenHandler)this.handers.get((Object)"open");
        if (openHandler != null) {
            openHandler.action(s);
        }
    }
    
    @JavascriptInterface
    public void openNetChemFile(final String s) {
        final OpenNetChemFileHandler openNetChemFileHandler = (OpenNetChemFileHandler)this.handers.get((Object)"openNetChemFile");
        if (openNetChemFileHandler != null) {
            openNetChemFileHandler.action(s);
        }
    }
    
    @JavascriptInterface
    public void refreshTab(final String s) {
        Log.e("opts", s);
        final RefreshTabModel refreshTabModel = (RefreshTabModel)GsonUtil.fromJson(s, (Class)RefreshTabModel.class);
        if (refreshTabModel != null) {
            this.webView.setRefresh(refreshTabModel.getUrl(), refreshTabModel.getOpts().getAppId());
            if (refreshTabModel.getOpts().isCloseTab()) {
                this.webView.closeMe();
            }
        }
    }
    
    @JavascriptInterface
    public void setBackButton(final boolean backShow) {
        this.webView.setBackShow(backShow);
    }
    
    @JavascriptInterface
    public void setPullRefreshStatus(final boolean pullRefreshStatus) {
        final JsWebView webView = this.webView;
        if (webView == null) {
            return;
        }
        webView.setPullRefreshStatus(pullRefreshStatus);
    }
    
    @JavascriptInterface
    public void toast(final String s) {
        ToastUtil.showLong((CharSequence)s);
    }
    
    private class RefreshTabModel
    {
        private RefreshTabOptsModel opts;
        final KDNativeJsInterface this$0;
        private String url;
        
        private RefreshTabModel(final KDNativeJsInterface this$0) {
            this.this$0 = this$0;
        }
        
        public RefreshTabOptsModel getOpts() {
            return this.opts;
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public void setOpts(final RefreshTabOptsModel opts) {
            this.opts = opts;
        }
        
        public void setUrl(final String url) {
            this.url = url;
        }
    }
    
    private class RefreshTabOptsModel
    {
        private String appId;
        private boolean closeTab;
        final KDNativeJsInterface this$0;
        private String title;
        
        private RefreshTabOptsModel(final KDNativeJsInterface this$0) {
            this.this$0 = this$0;
        }
        
        public String getAppId() {
            return this.appId;
        }
        
        public String getTitle() {
            return this.title;
        }
        
        public boolean isCloseTab() {
            return this.closeTab;
        }
        
        public void setAppId(final String appId) {
            this.appId = appId;
        }
        
        public void setCloseTab(final boolean closeTab) {
            this.closeTab = closeTab;
        }
        
        public void setTitle(final String title) {
            this.title = title;
        }
    }
}
