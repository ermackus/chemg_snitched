package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import com.goodsrc.library.utils.GsonUtil;
import android.text.TextUtils;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;

public class CloseHandler extends KDNativeBaseHander
{
    public static final String CLOSEWHO_ALL = "all";
    public static final String CLOSEWHO_ME = "me";
    public static final String CLOSEWHO_OTHER = "other";
    
    public CloseHandler(final JsWebView webView) {
        this.webView = webView;
    }
    
    public void action(String closeWho) {
        if (TextUtils.isEmpty((CharSequence)closeWho)) {
            return;
        }
        String appId = "";
        final String s = "all";
        try {
            final CloseHandler.CloseHandler$CloseHandlerModel closeHandler$CloseHandlerModel = (CloseHandler.CloseHandler$CloseHandlerModel)GsonUtil.fromJson(closeWho, (Class)CloseHandler.CloseHandler$CloseHandlerModel.class);
            closeWho = s;
            if (closeHandler$CloseHandlerModel != null) {
                appId = closeHandler$CloseHandlerModel.appId;
                closeWho = closeHandler$CloseHandlerModel.closeWho;
            }
            if (this.webView != null) {
                this.webView.onCloseHandler(appId, closeWho);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void destroy() {
    }
    
    public String handerName() {
        return "close";
    }
}
