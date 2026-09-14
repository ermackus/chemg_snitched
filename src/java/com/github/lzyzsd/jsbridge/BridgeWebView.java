package com.github.lzyzsd.jsbridge;

import android.os.Looper;
import android.webkit.WebViewClient;
import android.os.Build$VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import java.util.List;
import java.util.Map;
import android.webkit.WebView;

public class BridgeWebView extends WebView implements WebViewJavascriptBridge
{
    public static final String toLoadJs = "WebViewJavascriptBridge.js";
    private final String TAG;
    BridgeHandler defaultHandler;
    Map<String, BridgeHandler> messageHandlers;
    Map<String, CallBackFunction> responseCallbacks;
    private List<Message> startupMessage;
    private long uniqueId;
    
    public BridgeWebView(final Context context) {
        super(context);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = (Map<String, CallBackFunction>)new HashMap();
        this.messageHandlers = (Map<String, BridgeHandler>)new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = (List<Message>)new ArrayList();
        this.uniqueId = 0L;
        this.init();
    }
    
    public BridgeWebView(final Context context, final AttributeSet set) {
        super(context, set);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = (Map<String, CallBackFunction>)new HashMap();
        this.messageHandlers = (Map<String, BridgeHandler>)new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = (List<Message>)new ArrayList();
        this.uniqueId = 0L;
        this.init();
    }
    
    public BridgeWebView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.TAG = "BridgeWebView";
        this.responseCallbacks = (Map<String, CallBackFunction>)new HashMap();
        this.messageHandlers = (Map<String, BridgeHandler>)new HashMap();
        this.defaultHandler = new DefaultHandler();
        this.startupMessage = (List<Message>)new ArrayList();
        this.uniqueId = 0L;
        this.init();
    }
    
    private void doSend(final String handlerName, String format, final CallBackFunction callBackFunction) {
        final Message message = new Message();
        if (!TextUtils.isEmpty((CharSequence)format)) {
            message.setData(format);
        }
        if (callBackFunction != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(++this.uniqueId);
            sb.append("_");
            sb.append(SystemClock.currentThreadTimeMillis());
            format = String.format("JAVA_CB_%s", new Object[] { sb.toString() });
            this.responseCallbacks.put((Object)format, (Object)callBackFunction);
            message.setCallbackId(format);
        }
        if (!TextUtils.isEmpty((CharSequence)handlerName)) {
            message.setHandlerName(handlerName);
        }
        this.queueMessage(message);
    }
    
    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setJavaScriptEnabled(true);
        if (Build$VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.setWebViewClient((WebViewClient)this.generateBridgeWebViewClient());
    }
    
    private void queueMessage(final Message message) {
        final List<Message> startupMessage = this.startupMessage;
        if (startupMessage != null) {
            startupMessage.add((Object)message);
        }
        else {
            this.dispatchMessage(message);
        }
    }
    
    public void callHandler(final String s, final String s2, final CallBackFunction callBackFunction) {
        this.doSend(s, s2, callBackFunction);
    }
    
    void dispatchMessage(final Message message) {
        final String format = String.format("javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');", new Object[] { message.toJson().replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\"") });
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.loadUrl(format);
        }
    }
    
    void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.loadUrl("javascript:WebViewJavascriptBridge._fetchQueue();", new CallBackFunction(this) {
                final BridgeWebView this$0;
                
                @Override
                public void onCallBack(String s) {
                    try {
                        final List<Message> arrayList = Message.toArrayList(s);
                        if (arrayList != null) {
                            if (arrayList.size() != 0) {
                                for (int i = 0; i < arrayList.size(); ++i) {
                                    final Message message = (Message)arrayList.get(i);
                                    s = message.getResponseId();
                                    if (!TextUtils.isEmpty((CharSequence)s)) {
                                        ((CallBackFunction)this.this$0.responseCallbacks.get((Object)s)).onCallBack(message.getResponseData());
                                        this.this$0.responseCallbacks.remove((Object)s);
                                    }
                                    else {
                                        s = message.getCallbackId();
                                        CallBackFunction callBackFunction;
                                        if (!TextUtils.isEmpty((CharSequence)s)) {
                                            callBackFunction = new CallBackFunction(this, s) {
                                                final BridgeWebView$1 this$1;
                                                final String val$callbackId;
                                                
                                                @Override
                                                public void onCallBack(final String responseData) {
                                                    final Message message = new Message();
                                                    message.setResponseId(this.val$callbackId);
                                                    message.setResponseData(responseData);
                                                    this.this$1.this$0.queueMessage(message);
                                                }
                                            };
                                        }
                                        else {
                                            callBackFunction = new CallBackFunction(this) {
                                                final BridgeWebView$1 this$1;
                                                
                                                @Override
                                                public void onCallBack(final String s) {
                                                }
                                            };
                                        }
                                        BridgeHandler defaultHandler;
                                        if (!TextUtils.isEmpty((CharSequence)message.getHandlerName())) {
                                            defaultHandler = (BridgeHandler)this.this$0.messageHandlers.get((Object)message.getHandlerName());
                                        }
                                        else {
                                            defaultHandler = this.this$0.defaultHandler;
                                        }
                                        if (defaultHandler != null) {
                                            defaultHandler.handler(message.getData(), callBackFunction);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (final Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }
    
    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }
    
    public List<Message> getStartupMessage() {
        return this.startupMessage;
    }
    
    void handlerReturnData(String dataFromReturnUrl) {
        final String functionFromReturnUrl = BridgeUtil.getFunctionFromReturnUrl(dataFromReturnUrl);
        final CallBackFunction callBackFunction = (CallBackFunction)this.responseCallbacks.get((Object)functionFromReturnUrl);
        dataFromReturnUrl = BridgeUtil.getDataFromReturnUrl(dataFromReturnUrl);
        if (callBackFunction != null) {
            callBackFunction.onCallBack(dataFromReturnUrl);
            this.responseCallbacks.remove((Object)functionFromReturnUrl);
        }
    }
    
    public void loadUrl(final String s, final CallBackFunction callBackFunction) {
        this.loadUrl(s);
        this.responseCallbacks.put((Object)BridgeUtil.parseFunctionName(s), (Object)callBackFunction);
    }
    
    public void registerHandler(final String s, final BridgeHandler bridgeHandler) {
        if (bridgeHandler != null) {
            this.messageHandlers.put((Object)s, (Object)bridgeHandler);
        }
    }
    
    public void send(final String s) {
        this.send(s, null);
    }
    
    public void send(final String s, final CallBackFunction callBackFunction) {
        this.doSend(null, s, callBackFunction);
    }
    
    public void setDefaultHandler(final BridgeHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
    
    public void setStartupMessage(final List<Message> startupMessage) {
        this.startupMessage = startupMessage;
    }
}
