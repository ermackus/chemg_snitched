package com.kingagroot.kingdraw.ui.baike.bridgeHandler;

import com.github.lzyzsd.jsbridge.Message;
import com.goodsrc.library.utils.ToastUtil;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.GsonBuilder;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import java.util.HashMap;
import java.util.Map;

public class SearchCommandHandler extends BaseCommandHandler
{
    private static final Map<Character, String> htmlSafeKey;
    private float max;
    private float min;
    private String text;
    private String type;
    
    static {
        (htmlSafeKey = (Map)new HashMap()).put((Object)'%', (Object)"\\u0025");
    }
    
    public SearchCommandHandler(final BridgeWebView bridgeWebView) {
        super(bridgeWebView);
        this.min = 90.0f;
        this.max = 90.0f;
    }
    
    private String HtmlEscaping(final String s) {
        final StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            for (int length = s.length(), i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                final String s2 = (String)SearchCommandHandler.htmlSafeKey.get((Object)char1);
                if (s2 != null) {
                    sb.append(s2);
                }
                else {
                    sb.append(char1);
                }
            }
        }
        return sb.toString();
    }
    
    private String getDatas() {
        final SearchCommandHandler.SearchCommandHandler$JsSearchModel searchCommandHandler$JsSearchModel = new SearchCommandHandler.SearchCommandHandler$JsSearchModel(this, (SearchCommandHandler$1)null);
        final SearchCommandHandler.SearchCommandHandler$JsSearchDataModel searchCommandHandler$JsSearchDataModel = new SearchCommandHandler.SearchCommandHandler$JsSearchDataModel(this, (SearchCommandHandler$1)null);
        SearchCommandHandler.SearchCommandHandler$JsSearchDataModel.access$202(searchCommandHandler$JsSearchDataModel, this.max);
        SearchCommandHandler.SearchCommandHandler$JsSearchDataModel.access$302(searchCommandHandler$JsSearchDataModel, this.min);
        SearchCommandHandler.SearchCommandHandler$JsSearchDataModel.access$402(searchCommandHandler$JsSearchDataModel, this.text);
        SearchCommandHandler.SearchCommandHandler$JsSearchModel.access$502(searchCommandHandler$JsSearchModel, this.type);
        SearchCommandHandler.SearchCommandHandler$JsSearchModel.access$602(searchCommandHandler$JsSearchModel, searchCommandHandler$JsSearchDataModel);
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        return this.HtmlEscaping(gsonBuilder.create().toJson((Object)searchCommandHandler$JsSearchModel));
    }
    
    public void command() {
        final String datas = this.getDatas();
        if (this.bridgeWebView != null) {
            this.bridgeWebView.callHandler("jssearch", datas, (CallBackFunction)new CallBackFunction(this) {
                final SearchCommandHandler this$0;
                
                public void onCallBack(final String s) {
                    ToastUtil.showShort((CharSequence)s);
                }
            });
        }
    }
    
    public Message creatMessage() {
        final Message message = new Message();
        final String datas = this.getDatas();
        if (!TextUtils.isEmpty((CharSequence)datas)) {
            message.setData(datas);
        }
        if (!TextUtils.isEmpty((CharSequence)"jssearch")) {
            message.setHandlerName("jssearch");
        }
        return message;
    }
    
    public void setMax(final float max) {
        this.max = max;
    }
    
    public void setMin(final float min) {
        this.min = min;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
