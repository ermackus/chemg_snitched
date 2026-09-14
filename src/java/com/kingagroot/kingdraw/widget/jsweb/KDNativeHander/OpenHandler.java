package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import java.net.URISyntaxException;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.workstation.WebWorkActivity;
import com.kingagroot.kingdraw.config.ShareData;
import android.app.Activity;
import com.goodsrc.library.utils.GsonUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;

public class OpenHandler extends KDNativeBaseHander
{
    public OpenHandler(final JsWebView webView) {
        this.webView = webView;
    }
    
    public void action(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        try {
            final OpenHandler.OpenHandler$CallBackModel openHandler$CallBackModel = (OpenHandler.OpenHandler$CallBackModel)GsonUtil.fromJson(s, (Class)OpenHandler.OpenHandler$CallBackModel.class);
            if (openHandler$CallBackModel == null) {
                return;
            }
            if (this.webView != null) {
                final Activity activity = (Activity)this.webView.getNewContext();
                if (ShareData.getGatewayState()) {
                    final Intent intent = new Intent((Context)activity, (Class)WebWorkActivity.class);
                    intent.putExtra(WebWorkActivity.URL_DATA, openHandler$CallBackModel.url);
                    if (openHandler$CallBackModel.opts != null) {
                        intent.putExtra(WebWorkActivity.INTENT_KEY_APPID, openHandler$CallBackModel.opts.appId);
                        intent.putExtra(WebWorkActivity.INTENT_KEY_TITLE, openHandler$CallBackModel.opts.title);
                    }
                    activity.startActivity(intent);
                }
                else if (JsWebView.checkDomain(openHandler$CallBackModel.url)) {
                    final Intent intent2 = new Intent((Context)activity, (Class)WebWorkActivity.class);
                    intent2.putExtra(WebWorkActivity.URL_DATA, openHandler$CallBackModel.url);
                    if (openHandler$CallBackModel.opts != null) {
                        intent2.putExtra(WebWorkActivity.INTENT_KEY_APPID, openHandler$CallBackModel.opts.appId);
                        intent2.putExtra(WebWorkActivity.INTENT_KEY_TITLE, openHandler$CallBackModel.opts.title);
                    }
                    activity.startActivity(intent2);
                }
                else {
                    final Intent intent3 = new Intent();
                    intent3.setAction("android.intent.action.VIEW");
                    intent3.setData(Uri.parse(openHandler$CallBackModel.url));
                    activity.startActivity(intent3);
                }
            }
        }
        catch (final URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    public void destroy() {
    }
    
    public String handerName() {
        return "open";
    }
}
