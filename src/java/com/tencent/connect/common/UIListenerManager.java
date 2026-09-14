package com.tencent.connect.common;

import org.json.JSONObject;
import org.json.JSONException;
import com.tencent.tauth.UiError;
import com.tencent.open.utils.k;
import android.content.Intent;
import com.tencent.open.utils.i;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IUiListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UIListenerManager
{
    private static UIListenerManager a;
    private Map<String, ApiTask> b;
    
    private UIListenerManager() {
        final Map synchronizedMap = Collections.synchronizedMap((Map)new HashMap());
        this.b = (Map<String, ApiTask>)synchronizedMap;
        if (synchronizedMap == null) {
            this.b = (Map<String, ApiTask>)Collections.synchronizedMap((Map)new HashMap());
        }
    }
    
    private IUiListener a(final int n, final IUiListener uiListener) {
        if (n == 11101) {
            SLog.e("openSDK_LOG.UIListenerManager", "\u767b\u5f55\u7684\u63a5\u53e3\u56de\u8c03\u4e0d\u80fd\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        }
        else if (n == 11105) {
            SLog.e("openSDK_LOG.UIListenerManager", "Social Api \u7684\u63a5\u53e3\u56de\u8c03\u9700\u8981\u4f7f\u7528param\u6765\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        }
        else if (n == 11106) {
            SLog.e("openSDK_LOG.UIListenerManager", "Social Api \u7684H5\u63a5\u53e3\u56de\u8c03\u9700\u8981\u4f7f\u7528param\u6765\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        }
        return uiListener;
    }
    
    public static UIListenerManager getInstance() {
        if (UIListenerManager.a == null) {
            UIListenerManager.a = new UIListenerManager();
        }
        return UIListenerManager.a;
    }
    
    public IUiListener getListnerWithAction(final String s) {
        if (s == null) {
            SLog.e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
            return null;
        }
        final Map<String, ApiTask> b = this.b;
        synchronized (b) {
            final ApiTask apiTask = (ApiTask)this.b.get((Object)s);
            this.b.remove((Object)s);
            monitorexit(b);
            if (apiTask == null) {
                return null;
            }
            return apiTask.mListener;
        }
    }
    
    public IUiListener getListnerWithRequestCode(final int n) {
        final String a = i.a(n);
        if (a == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getListner action is null! rquestCode=");
            sb.append(n);
            SLog.e("openSDK_LOG.UIListenerManager", sb.toString());
            return null;
        }
        return this.getListnerWithAction(a);
    }
    
    public void handleDataToListener(final Intent intent, final IUiListener uiListener) {
        SLog.i("openSDK_LOG.UIListenerManager", "handleDataToListener");
        if (intent == null) {
            uiListener.onCancel();
            return;
        }
        final String stringExtra = intent.getStringExtra("key_action");
        if ("action_login".equals((Object)stringExtra)) {
            final int intExtra = intent.getIntExtra("key_error_code", 0);
            if (intExtra == 0) {
                final String stringExtra2 = intent.getStringExtra("key_response");
                if (stringExtra2 != null) {
                    try {
                        uiListener.onComplete((Object)k.d(stringExtra2));
                    }
                    catch (final JSONException ex) {
                        uiListener.onError(new UiError(-4, "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u6709\u8bef!", stringExtra2));
                        SLog.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", (Throwable)ex);
                    }
                }
                else {
                    SLog.d("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                    uiListener.onComplete((Object)new JSONObject());
                }
            }
            else {
                final StringBuilder sb = new StringBuilder();
                sb.append("OpenUi, onActivityResult, onError = ");
                sb.append(intExtra);
                sb.append("");
                SLog.e("openSDK_LOG.UIListenerManager", sb.toString());
                uiListener.onError(new UiError(intExtra, intent.getStringExtra("key_error_msg"), intent.getStringExtra("key_error_detail")));
            }
        }
        else if ("action_share".equals((Object)stringExtra)) {
            final String stringExtra3 = intent.getStringExtra("result");
            final String stringExtra4 = intent.getStringExtra("response");
            if ("cancel".equals((Object)stringExtra3)) {
                uiListener.onCancel();
            }
            else if ("error".equals((Object)stringExtra3)) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(stringExtra4);
                sb2.append("");
                uiListener.onError(new UiError(-6, "unknown error", sb2.toString()));
            }
            else if ("complete".equals((Object)stringExtra3)) {
                try {
                    String s;
                    if (stringExtra4 == null) {
                        s = "{\"ret\": 0}";
                    }
                    else {
                        s = stringExtra4;
                    }
                    uiListener.onComplete((Object)new JSONObject(s));
                }
                catch (final JSONException ex2) {
                    ex2.printStackTrace();
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(stringExtra4);
                    sb3.append("");
                    uiListener.onError(new UiError(-4, "json error", sb3.toString()));
                }
            }
        }
    }
    
    public boolean onActivityResult(int n, final int n2, Intent stringExtra, IUiListener uiListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("onActivityResult req=");
        sb.append(n);
        sb.append(" res=");
        sb.append(n2);
        SLog.i("openSDK_LOG.UIListenerManager", sb.toString());
        IUiListener uiListener2;
        if ((uiListener2 = this.getListnerWithRequestCode(n)) == null) {
            if (uiListener == null) {
                SLog.e("openSDK_LOG.UIListenerManager", "onActivityResult can't find the listener");
                return false;
            }
            uiListener2 = this.a(n, uiListener);
        }
        if (n2 == -1) {
            if (stringExtra == null) {
                uiListener2.onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
                return true;
            }
            final String stringExtra2 = stringExtra.getStringExtra("key_action");
            if ("action_login".equals((Object)stringExtra2)) {
                n = stringExtra.getIntExtra("key_error_code", 0);
                if (n == 0) {
                    uiListener = (IUiListener)stringExtra.getStringExtra("key_response");
                    if (uiListener != null) {
                        try {
                            uiListener2.onComplete((Object)k.d((String)uiListener));
                        }
                        catch (final JSONException ex) {
                            uiListener2.onError(new UiError(-4, "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u6709\u8bef!", (String)uiListener));
                            SLog.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", (Throwable)ex);
                        }
                    }
                    else {
                        SLog.d("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                        uiListener2.onComplete((Object)new JSONObject());
                    }
                }
                else {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("OpenUi, onActivityResult, onError = ");
                    sb2.append(n);
                    sb2.append("");
                    SLog.e("openSDK_LOG.UIListenerManager", sb2.toString());
                    uiListener2.onError(new UiError(n, stringExtra.getStringExtra("key_error_msg"), stringExtra.getStringExtra("key_error_detail")));
                }
            }
            else if (!"action_share".equals((Object)stringExtra2) && !"action_request_avatar".equals((Object)stringExtra2) && !"action_request_dynamic_avatar".equals((Object)stringExtra2) && !"action_request_set_emotion".equals((Object)stringExtra2)) {
                n = stringExtra.getIntExtra("key_error_code", 0);
                if (n == 0) {
                    stringExtra = (Intent)stringExtra.getStringExtra("key_response");
                    if (stringExtra != null) {
                        try {
                            uiListener2.onComplete((Object)k.d((String)stringExtra));
                        }
                        catch (final JSONException ex2) {
                            uiListener2.onError(new UiError(-4, "\u670d\u52a1\u5668\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u6709\u8bef!", (String)stringExtra));
                        }
                    }
                    else {
                        uiListener2.onComplete((Object)new JSONObject());
                    }
                }
                else {
                    uiListener2.onError(new UiError(n, stringExtra.getStringExtra("key_error_msg"), stringExtra.getStringExtra("key_error_detail")));
                }
            }
            else {
                final String stringExtra3 = stringExtra.getStringExtra("result");
                uiListener = (IUiListener)stringExtra.getStringExtra("response");
                if ("cancel".equals((Object)stringExtra3)) {
                    uiListener2.onCancel();
                }
                else if ("error".equals((Object)stringExtra3)) {
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append((String)uiListener);
                    sb3.append("");
                    uiListener2.onError(new UiError(-6, "unknown error", sb3.toString()));
                }
                else if ("complete".equals((Object)stringExtra3)) {
                    try {
                        Object o;
                        if (uiListener == null) {
                            o = "{\"ret\": 0}";
                        }
                        else {
                            o = uiListener;
                        }
                        uiListener2.onComplete((Object)new JSONObject((String)o));
                    }
                    catch (final JSONException ex3) {
                        ex3.printStackTrace();
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append((String)uiListener);
                        sb4.append("");
                        uiListener2.onError(new UiError(-4, "json error", sb4.toString()));
                    }
                }
            }
        }
        else {
            uiListener2.onCancel();
        }
        return true;
    }
    
    public Object setListenerWithRequestcode(final int n, final IUiListener uiListener) {
        final String a = i.a(n);
        if (a == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("setListener action is null! rquestCode=");
            sb.append(n);
            SLog.e("openSDK_LOG.UIListenerManager", sb.toString());
            return null;
        }
        final Map<String, ApiTask> b = this.b;
        synchronized (b) {
            final ApiTask apiTask = (ApiTask)this.b.put((Object)a, (Object)new ApiTask(n, uiListener));
            monitorexit(b);
            if (apiTask == null) {
                return null;
            }
            return apiTask.mListener;
        }
    }
    
    public Object setListnerWithAction(final String s, final IUiListener uiListener) {
        final int a = i.a(s);
        if (a == -1) {
            final StringBuilder sb = new StringBuilder();
            sb.append("setListnerWithAction fail, action = ");
            sb.append(s);
            SLog.e("openSDK_LOG.UIListenerManager", sb.toString());
            return null;
        }
        final Map<String, ApiTask> b = this.b;
        synchronized (b) {
            final ApiTask apiTask = (ApiTask)this.b.put((Object)s, (Object)new ApiTask(a, uiListener));
            monitorexit(b);
            if (apiTask == null) {
                return null;
            }
            return apiTask.mListener;
        }
    }
    
    public class ApiTask
    {
        final UIListenerManager a;
        public IUiListener mListener;
        public int mRequestCode;
        
        public ApiTask(final UIListenerManager a, final int mRequestCode, final IUiListener mListener) {
            this.a = a;
            this.mRequestCode = mRequestCode;
            this.mListener = mListener;
        }
    }
}
