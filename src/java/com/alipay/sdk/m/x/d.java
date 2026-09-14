package com.alipay.sdk.m.x;

import org.json.JSONException;
import java.lang.ref.WeakReference;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.webkit.JsPromptResult;
import android.net.Uri;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.view.animation.Animation$AnimationListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import java.util.Map;
import android.widget.ImageView;
import android.content.Context;
import com.alipay.sdk.m.j.b;
import org.json.JSONObject;
import com.alipay.sdk.m.j.d$a;
import android.text.TextUtils;
import com.alipay.sdk.m.u.n;
import android.app.Activity;
import com.alipay.sdk.m.s.a;

public class d extends c implements com.alipay.sdk.m.x.e.f, com.alipay.sdk.m.x.e.g, h
{
    public static final String A = "action";
    public static final String B = "pushWindow";
    public static final String C = "h5JsFuncCallback";
    public static final String D = "sdkInfo";
    public static final String E = "canUseTaoLogin";
    public static final String F = "taoLogin";
    public static final String l = "sdk_result_code:";
    public static final String m = "alipayjsbridge://";
    public static final String n = "onBack";
    public static final String o = "setTitle";
    public static final String p = "onRefresh";
    public static final String q = "showBackButton";
    public static final String r = "onExit";
    public static final String s = "onLoadJs";
    public static final String t = "callNativeFunc";
    public static final String u = "back";
    public static final String v = "title";
    public static final String w = "refresh";
    public static final String x = "backButton";
    public static final String y = "refreshButton";
    public static final String z = "exit";
    public boolean e;
    public String f;
    public boolean g;
    public final a h;
    public boolean i;
    public com.alipay.sdk.m.x.e j;
    public com.alipay.sdk.m.x.f k;
    
    public d(final Activity activity, final a h, final String s) {
        super(activity, s);
        this.e = true;
        this.f = "GET";
        this.g = false;
        this.j = null;
        this.k = new com.alipay.sdk.m.x.f();
        this.h = h;
        this.g();
    }
    
    public static /* synthetic */ com.alipay.sdk.m.x.e a(final d d) {
        return d.j;
    }
    
    private void a(final String s, final String s2, final String s3) {
        synchronized (this) {
            final com.alipay.sdk.m.x.e j = this.j;
            if (j == null) {
                return;
            }
            final JSONObject h = com.alipay.sdk.m.u.n.h(s3);
            final f f = new f(j, s, s2, h);
            final Context context = j.getContext();
            try {
                final String b = f.b;
                int n = -1;
                final int hashCode = b.hashCode();
                int n2 = 4;
                switch (hashCode) {
                    case 2033767917: {
                        if (b.equals((Object)"refreshButton")) {
                            n = 5;
                            break;
                        }
                        break;
                    }
                    case 1947723784: {
                        if (b.equals((Object)"sdkInfo")) {
                            n = 7;
                            break;
                        }
                        break;
                    }
                    case 1906413305: {
                        if (b.equals((Object)"backButton")) {
                            n = 4;
                            break;
                        }
                        break;
                    }
                    case 1703426986: {
                        if (b.equals((Object)"pushWindow")) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    case 1085444827: {
                        if (b.equals((Object)"refresh")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case 110371416: {
                        if (b.equals((Object)"title")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 3127582: {
                        if (b.equals((Object)"exit")) {
                            n = 3;
                            break;
                        }
                        break;
                    }
                    case 3015911: {
                        if (b.equals((Object)"back")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case -552487705: {
                        if (b.equals((Object)"taoLogin")) {
                            n = 9;
                            break;
                        }
                        break;
                    }
                    case -1785164386: {
                        if (b.equals((Object)"canUseTaoLogin")) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 9: {
                        final String url = j.getUrl();
                        if (!com.alipay.sdk.m.u.n.a(this.h, url)) {
                            com.alipay.sdk.m.k.a.b(this.h, "biz", "jsUrlErr", url);
                            break;
                        }
                        final String optString = h.optString("random");
                        final JSONObject optJSONObject = h.optJSONObject("options");
                        if (TextUtils.isEmpty((CharSequence)"random")) {
                            break;
                        }
                        if (optJSONObject == null) {
                            break;
                        }
                        final String optString2 = optJSONObject.optString("url");
                        final String optString3 = optJSONObject.optString("action");
                        if (TextUtils.isEmpty((CharSequence)optString2)) {
                            break;
                        }
                        if (TextUtils.isEmpty((CharSequence)optString3)) {
                            break;
                        }
                        if (context instanceof Activity) {
                            com.alipay.sdk.m.j.d.a(this.h, (Activity)context, 1010, optString2, optString3, (com.alipay.sdk.m.j.d$a)new g(f, optString));
                            break;
                        }
                        break;
                    }
                    case 8: {
                        final String url2 = j.getUrl();
                        if (!com.alipay.sdk.m.u.n.a(this.h, url2)) {
                            com.alipay.sdk.m.k.a.b(this.h, "biz", "jsUrlErr", url2);
                            break;
                        }
                        final JSONObject jsonObject = new JSONObject();
                        final boolean a = com.alipay.sdk.m.j.d.a(this.h, context);
                        jsonObject.put("enabled", a);
                        com.alipay.sdk.m.k.a.a(this.h, "biz", "TbChk", String.valueOf(a));
                        f.a(jsonObject);
                        break;
                    }
                    case 7: {
                        final JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("sdk_version", (Object)"15.8.17");
                        jsonObject2.put("app_name", (Object)this.h.b());
                        jsonObject2.put("app_version", (Object)this.h.c());
                        f.a(jsonObject2);
                        break;
                    }
                    case 6: {
                        this.b(h.optString("url"), h.optString("title", ""));
                        break;
                    }
                    case 5: {
                        final boolean optBoolean = h.optBoolean("show", true);
                        final ImageView refreshButton = j.getRefreshButton();
                        if (optBoolean) {
                            n2 = 0;
                        }
                        refreshButton.setVisibility(n2);
                        break;
                    }
                    case 4: {
                        final boolean optBoolean2 = h.optBoolean("show", true);
                        final ImageView backButton = j.getBackButton();
                        if (optBoolean2) {
                            n2 = 0;
                        }
                        backButton.setVisibility(n2);
                        break;
                    }
                    case 3: {
                        com.alipay.sdk.m.j.b.a(h.optString("result", (String)null));
                        this.a(h.optBoolean("success", false));
                        break;
                    }
                    case 2: {
                        this.i();
                        break;
                    }
                    case 1: {
                        j.getWebView().reload();
                        break;
                    }
                    case 0: {
                        if (h.has("title")) {
                            j.getTitle().setText((CharSequence)h.optString("title", ""));
                            break;
                        }
                        break;
                    }
                }
            }
            finally {
                final Throwable t;
                com.alipay.sdk.m.k.a.a(this.h, "biz", "jInfoErr", t, s);
            }
        }
    }
    
    private void a(final boolean b) {
        synchronized (this) {
            b.a(b);
            super.a.finish();
        }
    }
    
    public static /* synthetic */ boolean a(final d d, final boolean g) {
        return d.g = g;
    }
    
    public static /* synthetic */ a b(final d d) {
        return d.h;
    }
    
    private void b(final String s) {
        synchronized (this) {
            final Map<String, String> b = com.alipay.sdk.m.u.n.b(this.h, s);
            if (s.startsWith("callNativeFunc")) {
                this.a((String)b.get((Object)"func"), (String)b.get((Object)"cbId"), (String)b.get((Object)"data"));
            }
            else if (s.startsWith("onBack")) {
                this.i();
            }
            else if (s.startsWith("setTitle") && b.containsKey((Object)"title")) {
                this.j.getTitle().setText((CharSequence)b.get((Object)"title"));
            }
            else if (s.startsWith("onRefresh")) {
                this.j.getWebView().reload();
            }
            else if (s.startsWith("showBackButton") && b.containsKey((Object)"bshow")) {
                final boolean equals = TextUtils.equals((CharSequence)"true", (CharSequence)b.get((Object)"bshow"));
                final ImageView backButton = this.j.getBackButton();
                int visibility;
                if (equals) {
                    visibility = 0;
                }
                else {
                    visibility = 4;
                }
                backButton.setVisibility(visibility);
            }
            else if (s.startsWith("onExit")) {
                com.alipay.sdk.m.j.b.a((String)b.get((Object)"result"));
                this.a(TextUtils.equals((CharSequence)"true", (CharSequence)b.get((Object)"bsucc")));
            }
            else if (s.startsWith("onLoadJs")) {
                this.j.a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
            }
        }
    }
    
    private boolean b(final String s, final String text) {
        synchronized (this) {
            final com.alipay.sdk.m.x.e j = this.j;
            try {
                (this.j = new com.alipay.sdk.m.x.e((Context)super.a, this.h, new com.alipay.sdk.m.x.e.e(this.a() ^ true, this.a() ^ true))).setChromeProxy((com.alipay.sdk.m.x.e.f)this);
                this.j.setWebClientProxy((com.alipay.sdk.m.x.e.g)this);
                this.j.setWebEventProxy((h)this);
                if (!TextUtils.isEmpty((CharSequence)text)) {
                    this.j.getTitle().setText((CharSequence)text);
                }
                this.g = true;
                this.k.a(j);
                final TranslateAnimation animation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
                animation.setDuration(400L);
                animation.setFillAfter(false);
                animation.setAnimationListener((Animation$AnimationListener)new e(this, j, s) {
                    public final com.alipay.sdk.m.x.e a;
                    public final String b;
                    public final d c;
                    
                    @Override
                    public void onAnimationEnd(final Animation animation) {
                        this.c.removeView((View)this.a);
                        d.a(this.c).a(this.b);
                        d.a(this.c, false);
                    }
                });
                this.j.setAnimation((Animation)animation);
                this.addView((View)this.j);
                return true;
            }
            finally {
                return false;
            }
        }
    }
    
    private boolean e() {
        synchronized (this) {
            if (this.k.b()) {
                super.a.finish();
            }
            else {
                this.g = true;
                final com.alipay.sdk.m.x.e j = this.j;
                this.j = this.k.c();
                final TranslateAnimation animation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
                animation.setDuration(400L);
                animation.setFillAfter(false);
                animation.setAnimationListener((Animation$AnimationListener)new e(this, j) {
                    public final com.alipay.sdk.m.x.e a;
                    public final d b;
                    
                    @Override
                    public void onAnimationEnd(final Animation animation) {
                        this.a.a();
                        d.a(this.b, false);
                    }
                });
                j.setAnimation((Animation)animation);
                this.removeView((View)j);
                this.addView((View)this.j);
            }
            return true;
        }
    }
    
    private void f() {
        synchronized (this) {
            final Activity a = super.a;
            final com.alipay.sdk.m.x.e j = this.j;
            if (a != null && j != null) {
                if (this.e) {
                    a.finish();
                }
                else {
                    j.a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
                }
            }
        }
    }
    
    private boolean g() {
        synchronized (this) {
            try {
                (this.j = new com.alipay.sdk.m.x.e((Context)super.a, this.h, new com.alipay.sdk.m.x.e.e(this.a() ^ true, this.a() ^ true))).setChromeProxy((com.alipay.sdk.m.x.e.f)this);
                this.j.setWebClientProxy((com.alipay.sdk.m.x.e.g)this);
                this.j.setWebEventProxy((h)this);
                this.addView((View)this.j);
                return true;
            }
            catch (final Exception ex) {
                return false;
            }
        }
    }
    
    private void h() {
        final com.alipay.sdk.m.x.e j = this.j;
        if (j != null) {
            j.getWebView().loadUrl("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        }
    }
    
    private void i() {
        synchronized (this) {
            final WebView webView = this.j.getWebView();
            if (webView.canGoBack()) {
                webView.goBack();
            }
            else {
                final com.alipay.sdk.m.x.f k = this.k;
                if (k != null && !k.b()) {
                    this.e();
                }
                else {
                    this.a(false);
                }
            }
        }
    }
    
    @Override
    public void a(final com.alipay.sdk.m.x.e e) {
        synchronized (this) {
            e.getWebView().reload();
            e.getRefreshButton().setVisibility(4);
        }
    }
    
    @Override
    public void a(final String s) {
        synchronized (this) {
            if ("POST".equals((Object)this.f)) {
                this.j.a(s, null);
            }
            else {
                this.j.a(s);
            }
            c.a(this.j.getWebView());
        }
    }
    
    public void a(final String text, final String f, final boolean e) {
        synchronized (this) {
            this.f = f;
            this.j.getTitle().setText((CharSequence)text);
            this.e = e;
        }
    }
    
    @Override
    public boolean a(final com.alipay.sdk.m.x.e e, final int n, final String s, final String s2) {
        synchronized (this) {
            this.i = true;
            final a h = this.h;
            final StringBuilder sb = new StringBuilder();
            sb.append("onReceivedError:");
            sb.append(n);
            sb.append("|");
            sb.append(s2);
            com.alipay.sdk.m.k.a.b(h, "net", "webError", sb.toString());
            e.getRefreshButton().setVisibility(0);
            return false;
        }
    }
    
    @Override
    public boolean a(final com.alipay.sdk.m.x.e e, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        synchronized (this) {
            final Activity a = super.a;
            if (a == null) {
                return true;
            }
            final a h = this.h;
            final StringBuilder sb = new StringBuilder();
            sb.append("2-");
            sb.append((Object)sslError);
            com.alipay.sdk.m.k.a.b(h, "net", "SSLError", sb.toString());
            a.runOnUiThread((Runnable)new Runnable(this, a, sslErrorHandler) {
                public final Activity a;
                public final SslErrorHandler b;
                public final d c;
                
                public void run() {
                    com.alipay.sdk.m.x.b.a((Context)this.a, "\u5b89\u5168\u8b66\u544a", "\u5b89\u5168\u8fde\u63a5\u8bc1\u4e66\u6821\u9a8c\u65e0\u6548\uff0c\u5c06\u65e0\u6cd5\u4fdd\u8bc1\u8bbf\u95ee\u6570\u636e\u7684\u5b89\u5168\u6027\uff0c\u8bf7\u5b89\u88c5\u652f\u4ed8\u5b9d\u540e\u91cd\u8bd5\u3002", "\u786e\u5b9a", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this) {
                        public final d$d a;
                        
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            this.a.b.cancel();
                            com.alipay.sdk.m.k.a.b(d.b(this.a.c), "net", "SSLDenied", "2");
                            com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a());
                            this.a.a.finish();
                        }
                    }, null, null);
                }
            });
            return true;
        }
    }
    
    @Override
    public boolean a(final com.alipay.sdk.m.x.e e, final String s) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                return false;
            }
            final Activity a = super.a;
            if (a == null) {
                return true;
            }
            if (com.alipay.sdk.m.u.n.a(this.h, s, a)) {
                return true;
            }
            if (s.startsWith("alipayjsbridge://")) {
                this.b(s.substring(17));
            }
            else if (TextUtils.equals((CharSequence)s, (CharSequence)"sdklite://h5quit")) {
                this.a(false);
            }
            else if (!s.startsWith("http://") && !s.startsWith("https://")) {
                try {
                    final Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(s));
                    a.startActivity(intent);
                }
                finally {
                    final Throwable t;
                    com.alipay.sdk.m.k.a.a(this.h, "biz", t);
                }
            }
            else {
                this.j.a(s);
            }
            return true;
        }
    }
    
    @Override
    public boolean a(final com.alipay.sdk.m.x.e e, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
        synchronized (this) {
            if (s2.startsWith("<head>") && s2.contains((CharSequence)"sdk_result_code:")) {
                super.a.runOnUiThread((Runnable)new Runnable(this) {
                    public final d a;
                    
                    public void run() {
                        this.a.a.finish();
                    }
                });
            }
            jsPromptResult.cancel();
            return true;
        }
    }
    
    @Override
    public void b(final com.alipay.sdk.m.x.e e) {
        synchronized (this) {
            this.f();
        }
    }
    
    @Override
    public boolean b() {
        synchronized (this) {
            final Activity a = super.a;
            if (a == null) {
                return true;
            }
            if (!this.a()) {
                if (!this.g) {
                    this.f();
                }
                return true;
            }
            final com.alipay.sdk.m.x.e j = this.j;
            if (j != null && j.getWebView() != null) {
                if (j.getWebView().canGoBack()) {
                    if (this.d()) {
                        final com.alipay.sdk.m.j.c b = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.f.b());
                        com.alipay.sdk.m.j.b.a(com.alipay.sdk.m.j.b.a(b.b(), b.a(), ""));
                        a.finish();
                    }
                }
                else {
                    b.a(b.a());
                    a.finish();
                }
                return true;
            }
            a.finish();
            return true;
        }
    }
    
    @Override
    public boolean b(final com.alipay.sdk.m.x.e e, final String s) {
        synchronized (this) {
            final a h = this.h;
            final StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            sb.append("|");
            sb.append(com.alipay.sdk.m.u.n.i(s));
            com.alipay.sdk.m.k.a.a(h, "biz", "h5ldd", sb.toString());
            this.h();
            e.getRefreshButton().setVisibility(0);
            return true;
        }
    }
    
    @Override
    public void c() {
        synchronized (this) {
            this.j.a();
            this.k.a();
        }
    }
    
    @Override
    public void c(final com.alipay.sdk.m.x.e e, final String text) {
        synchronized (this) {
            if (!text.startsWith("http") && !e.getUrl().endsWith(text)) {
                this.j.getTitle().setText((CharSequence)text);
            }
        }
    }
    
    public boolean d() {
        return this.i;
    }
    
    @Override
    public boolean d(final com.alipay.sdk.m.x.e e, final String s) {
        synchronized (this) {
            final a h = this.h;
            final StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            sb.append("|");
            sb.append(com.alipay.sdk.m.u.n.i(s));
            com.alipay.sdk.m.k.a.a(h, "biz", "h5ld", sb.toString());
            if (!TextUtils.isEmpty((CharSequence)s) && !s.endsWith(".apk")) {
                this.h();
            }
            return false;
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        synchronized (this) {
            return this.g || super.onInterceptTouchEvent(motionEvent);
        }
    }
    
    public abstract static class e implements Animation$AnimationListener
    {
        public void onAnimationEnd(final Animation animation) {
        }
        
        public void onAnimationRepeat(final Animation animation) {
        }
        
        public void onAnimationStart(final Animation animation) {
        }
    }
    
    public static class f
    {
        public final WeakReference<com.alipay.sdk.m.x.e> a;
        public final String b;
        public final String c;
        public final JSONObject d;
        public boolean e;
        
        public f(final com.alipay.sdk.m.x.e e, final String b, final String c, final JSONObject d) {
            this.e = false;
            this.a = (WeakReference<com.alipay.sdk.m.x.e>)new WeakReference((Object)e);
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        public static String a(final String s) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                return "";
            }
            return s.replace((CharSequence)"'", (CharSequence)"");
        }
        
        public void a(final JSONObject jsonObject) {
            if (this.e) {
                return;
            }
            final com.alipay.sdk.m.x.e e = com.alipay.sdk.m.u.n.a(this.a);
            if (e == null) {
                return;
            }
            this.e = true;
            e.a(String.format("javascript:window.AlipayJSBridge.callBackFromNativeFunc('%s','%s');", new Object[] { a(this.c), a(jsonObject.toString()) }));
        }
    }
    
    public static class g implements com.alipay.sdk.m.j.d$a
    {
        public final f a;
        public final String b;
        
        public g(final f a, final String b) {
            this.a = a;
            this.b = b;
        }
        
        public void a(final boolean b, final JSONObject jsonObject, final String s) {
            try {
                this.a.a(new JSONObject().put("success", b).put("random", (Object)this.b).put("code", (Object)jsonObject).put("status", (Object)s));
            }
            catch (final JSONException ex) {}
        }
    }
}
