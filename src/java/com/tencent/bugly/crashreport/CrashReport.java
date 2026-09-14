package com.tencent.bugly.crashreport;

import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import android.os.Build$VERSION;
import android.webkit.WebView;
import java.net.InetAddress;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.Map;
import java.net.Proxy;
import java.util.HashSet;
import java.util.Set;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.CrashModule;
import android.util.Log;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.b;
import android.content.Context;

public class CrashReport
{
    private static Context a;
    
    public static void closeBugly() {
        if (!b.a) {
            Log.w(x.a, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (CrashReport.a == null) {
            return;
        }
        final BuglyBroadcastReceiver instance = BuglyBroadcastReceiver.getInstance();
        if (instance != null) {
            instance.unregister(CrashReport.a);
        }
        closeCrashReport();
        com.tencent.bugly.crashreport.biz.b.a(CrashReport.a);
        final w a = w.a();
        if (a != null) {
            a.b();
        }
    }
    
    public static void closeCrashReport() {
        if (!b.a) {
            Log.w(x.a, "Can not close crash report because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        c.a().d();
    }
    
    public static void closeNativeReport() {
        if (!b.a) {
            Log.w(x.a, "Can not close native report because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        c.a().f();
    }
    
    public static void enableBugly(final boolean a) {
        b.a = a;
    }
    
    public static void enableObtainId(final Context context, final boolean b) {
        if (!b.a) {
            Log.w(x.a, "Can not set DB name because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(x.a, "enableObtainId args context should not be null");
            return;
        }
        final String a = x.a;
        final StringBuilder sb = new StringBuilder("Enable identification obtaining? ");
        sb.append(b);
        Log.i(a, sb.toString());
        com.tencent.bugly.crashreport.common.info.a.a(context).b(b);
    }
    
    public static Set<String> getAllUserDataKeys(final Context context) {
        if (!b.a) {
            Log.w(x.a, "Can not get all keys of user data because bugly is disable.");
            return (Set<String>)new HashSet();
        }
        if (context == null) {
            Log.e(x.a, "getAllUserDataKeys args context should not be null");
            return (Set<String>)new HashSet();
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).w();
    }
    
    public static String getAppChannel() {
        if (!b.a) {
            Log.w(x.a, "Can not get App channel because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(CrashReport.a).m;
    }
    
    public static String getAppID() {
        if (!b.a) {
            Log.w(x.a, "Can not get App ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(CrashReport.a).f();
    }
    
    public static String getAppVer() {
        if (!b.a) {
            Log.w(x.a, "Can not get app version because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(CrashReport.a).k;
    }
    
    public static String getBuglyVersion(final Context context) {
        if (context == null) {
            x.d("Please call with context.", new Object[0]);
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).c();
    }
    
    public static Proxy getHttpProxy() {
        return com.tencent.bugly.proguard.a.b();
    }
    
    public static Map<String, String> getSdkExtraData() {
        if (!b.a) {
            Log.w(x.a, "Can not get SDK extra data because bugly is disable.");
            return (Map<String, String>)new HashMap();
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
        return (Map<String, String>)com.tencent.bugly.crashreport.common.info.a.a(CrashReport.a).C;
    }
    
    public static Map<String, String> getSdkExtraData(final Context context) {
        if (!b.a) {
            Log.w(x.a, "Can not get SDK extra data because bugly is disable.");
            return (Map<String, String>)new HashMap();
        }
        if (context == null) {
            x.d("Context should not be null.", new Object[0]);
            return null;
        }
        return (Map<String, String>)com.tencent.bugly.crashreport.common.info.a.a(context).C;
    }
    
    public static String getUserData(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not get user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(x.a, "getUserDataValue args context should not be null");
            return "unknown";
        }
        if (z.a(s)) {
            return null;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).g(s);
    }
    
    public static int getUserDatasSize(final Context context) {
        if (!b.a) {
            Log.w(x.a, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(x.a, "getUserDatasSize args context should not be null");
            return -1;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).v();
    }
    
    public static String getUserId() {
        if (!b.a) {
            Log.w(x.a, "Can not get user ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(CrashReport.a).g();
    }
    
    public static int getUserSceneTagId(final Context context) {
        if (!b.a) {
            Log.w(x.a, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(x.a, "getUserSceneTagId args context should not be null");
            return -1;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).z();
    }
    
    public static void initCrashReport(final Context a) {
        if (a == null) {
            return;
        }
        CrashReport.a = a;
        b.a((com.tencent.bugly.a)CrashModule.getInstance());
        b.a(a);
    }
    
    public static void initCrashReport(final Context a, final CrashReport.CrashReport$UserStrategy crashReport$UserStrategy) {
        if (a == null) {
            return;
        }
        CrashReport.a = a;
        b.a((com.tencent.bugly.a)CrashModule.getInstance());
        b.a(a, (BuglyStrategy)crashReport$UserStrategy);
    }
    
    public static void initCrashReport(final Context a, final String s, final boolean b) {
        if (a != null) {
            CrashReport.a = a;
            b.a((com.tencent.bugly.a)CrashModule.getInstance());
            b.a(a, s, b, null);
        }
    }
    
    public static void initCrashReport(final Context a, final String s, final boolean b, final CrashReport.CrashReport$UserStrategy crashReport$UserStrategy) {
        if (a == null) {
            return;
        }
        CrashReport.a = a;
        b.a((com.tencent.bugly.a)CrashModule.getInstance());
        b.a(a, s, b, (BuglyStrategy)crashReport$UserStrategy);
    }
    
    public static boolean isLastSessionCrash() {
        if (!b.a) {
            Log.w(x.a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
        return c.a().b();
    }
    
    public static void postCatchedException(final Throwable t) {
        postCatchedException(t, Thread.currentThread(), false);
    }
    
    public static void postCatchedException(final Throwable t, final Thread thread) {
        postCatchedException(t, thread, false);
    }
    
    public static void postCatchedException(final Throwable t, final Thread thread, final boolean b) {
        if (!b.a) {
            Log.w(x.a, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (t == null) {
            x.d("throwable is null, just return", new Object[0]);
            return;
        }
        Thread currentThread;
        if ((currentThread = thread) == null) {
            currentThread = Thread.currentThread();
        }
        c.a().a(currentThread, t, false, null, null, b);
    }
    
    public static void postException(final int n, final String s, final String s2, final String s3, final Map<String, String> map) {
        postException(Thread.currentThread(), n, s, s2, s3, map);
    }
    
    public static void postException(final Thread thread, final int n, final String s, final String s2, final String s3, final Map<String, String> map) {
        if (!b.a) {
            Log.w(x.a, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        d.a(thread, n, s, s2, s3, map);
    }
    
    private static void putSdkData(final Context context, String s, final String s2) {
        if (context != null && !z.a(s)) {
            if (!z.a(s2)) {
                final String s3 = s = s.replace((CharSequence)"[a-zA-Z[0-9]]+", (CharSequence)"");
                if (s3.length() > 100) {
                    Log.w(x.a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[] { 50 }));
                    s = s3.substring(0, 50);
                }
                String substring = s2;
                if (s2.length() > 500) {
                    Log.w(x.a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[] { 200 }));
                    substring = s2.substring(0, 200);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).c(s, substring);
                x.b(String.format("[param] putSdkData data: %s - %s", new Object[] { s, substring }), new Object[0]);
            }
        }
    }
    
    public static void putUserData(final Context context, final String s, String substring) {
        if (!b.a) {
            Log.w(x.a, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(x.a, "putUserData args context should not be null");
            return;
        }
        if (s == null) {
            x.d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (substring == null) {
            x.d("putUserData args value should not be null", new Object[0]);
            return;
        }
        String substring2 = substring;
        if (substring.length() > 200) {
            x.d("user data value length over limit %d, it will be cutted!", 200);
            substring2 = substring.substring(0, 200);
        }
        final a a = com.tencent.bugly.crashreport.common.info.a.a(context);
        if (a.w().contains((Object)s)) {
            final NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.putKeyValueToNative(s, substring2);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).b(s, substring2);
            x.c("replace KV %s %s", s, substring2);
            return;
        }
        if (a.v() >= 50) {
            x.d("user data size is over limit %d, it will be cutted!", 50);
            return;
        }
        substring = s;
        if (s.length() > 50) {
            x.d("user data key length over limit %d , will drop this new key %s", 50, s);
            substring = s.substring(0, 50);
        }
        final NativeCrashHandler instance2 = NativeCrashHandler.getInstance();
        if (instance2 != null) {
            instance2.putKeyValueToNative(substring, substring2);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).b(substring, substring2);
        x.b("[param] set user data: %s - %s", substring, substring2);
    }
    
    public static String removeUserData(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not remove user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(x.a, "removeUserData args context should not be null");
            return "unknown";
        }
        if (z.a(s)) {
            return null;
        }
        x.b("[param] remove user data: %s", s);
        return com.tencent.bugly.crashreport.common.info.a.a(context).f(s);
    }
    
    public static void setAppChannel(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not set App channel because Bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(x.a, "setAppChannel args context should not be null");
            return;
        }
        if (s == null) {
            Log.w(x.a, "App channel is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).m = s;
        final NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            instance.setNativeAppChannel(s);
        }
    }
    
    public static void setAppPackage(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(x.a, "setAppPackage args context should not be null");
            return;
        }
        if (s == null) {
            Log.w(x.a, "App package is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).c = s;
        final NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            instance.setNativeAppPackage(s);
        }
    }
    
    public static void setAppVersion(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(x.a, "setAppVersion args context should not be null");
            return;
        }
        if (s == null) {
            Log.w(x.a, "App version is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).k = s;
        final NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            instance.setNativeAppVersion(s);
        }
    }
    
    public static void setBuglyDbName(final String a) {
        if (!b.a) {
            Log.w(x.a, "Can not set DB name because bugly is disable.");
            return;
        }
        final String a2 = x.a;
        final StringBuilder sb = new StringBuilder("Set Bugly DB name: ");
        sb.append(a);
        Log.i(a2, sb.toString());
        q.a = a;
    }
    
    public static void setContext(final Context a) {
        CrashReport.a = a;
    }
    
    public static void setCrashFilter(final String n) {
        if (!b.a) {
            Log.w(x.a, "Can not set App package because bugly is disable.");
            return;
        }
        final String a = x.a;
        final StringBuilder sb = new StringBuilder("Set crash stack filter: ");
        sb.append(n);
        Log.i(a, sb.toString());
        c.n = n;
    }
    
    public static void setCrashRegularFilter(final String o) {
        if (!b.a) {
            Log.w(x.a, "Can not set App package because bugly is disable.");
            return;
        }
        final String a = x.a;
        final StringBuilder sb = new StringBuilder("Set crash stack filter: ");
        sb.append(o);
        Log.i(a, sb.toString());
        c.o = o;
    }
    
    public static void setHandleNativeCrashInJava(final boolean shouldHandleInJava) {
        if (!b.a) {
            Log.w(x.a, "Can not set App package because bugly is disable.");
            return;
        }
        final String a = x.a;
        final StringBuilder sb = new StringBuilder("Should handle native crash in Java profile after handled in native profile: ");
        sb.append(shouldHandleInJava);
        Log.i(a, sb.toString());
        NativeCrashHandler.setShouldHandleInJava(shouldHandleInJava);
    }
    
    public static void setHttpProxy(final String s, final int n) {
        com.tencent.bugly.proguard.a.a(s, n);
    }
    
    public static void setHttpProxy(final InetAddress inetAddress, final int n) {
        com.tencent.bugly.proguard.a.a(inetAddress, n);
    }
    
    public static void setIsAppForeground(final Context context, final boolean b) {
        if (!b.a) {
            Log.w(x.a, "Can not set 'isAppForeground' because bugly is disable.");
            return;
        }
        if (context == null) {
            x.d("Context should not be null.", new Object[0]);
            return;
        }
        if (b) {
            x.c("App is in foreground.", new Object[0]);
        }
        else {
            x.c("App is in background.", new Object[0]);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).a(b);
    }
    
    public static void setIsDevelopmentDevice(final Context context, final boolean a) {
        if (!b.a) {
            Log.w(x.a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
            return;
        }
        if (context == null) {
            x.d("Context should not be null.", new Object[0]);
            return;
        }
        if (a) {
            x.c("This is a development device.", new Object[0]);
        }
        else {
            x.c("This is not a development device.", new Object[0]);
        }
        a.a(context).A = a;
    }
    
    public static boolean setJavascriptMonitor(final WebView webView, final boolean b) {
        return setJavascriptMonitor(webView, b, false);
    }
    
    public static boolean setJavascriptMonitor(final WebView webView, final boolean b, final boolean b2) {
        if (webView == null) {
            Log.w(x.a, "WebView is null.");
            return false;
        }
        return setJavascriptMonitor((WebViewInterface)new CrashReport$1(webView), b, b2);
    }
    
    public static boolean setJavascriptMonitor(final WebViewInterface webViewInterface, final boolean b) {
        return setJavascriptMonitor(webViewInterface, b, false);
    }
    
    public static boolean setJavascriptMonitor(final WebViewInterface webViewInterface, final boolean b, final boolean b2) {
        if (webViewInterface == null) {
            Log.w(x.a, "WebViewInterface is null.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            x.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        x.a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!b.a) {
            Log.w(x.a, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        x.c("URL of webview is %s", webViewInterface.getUrl());
        if (!b2 && Build$VERSION.SDK_INT < 19) {
            x.e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
        x.a("Enable the javascript needed by webview monitor.", new Object[0]);
        webViewInterface.setJavaScriptEnabled(true);
        final H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webViewInterface);
        if (instance != null) {
            x.a("Add a secure javascript interface to the webview.", new Object[0]);
            webViewInterface.addJavascriptInterface(instance, "exceptionUploader");
        }
        if (b) {
            x.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.crashreport.crash.h5.b.b());
            final String a = com.tencent.bugly.crashreport.crash.h5.b.a();
            if (a == null) {
                x.e("Failed to inject Bugly.js.", com.tencent.bugly.crashreport.crash.h5.b.b());
                return false;
            }
            final StringBuilder sb = new StringBuilder("javascript:");
            sb.append(a);
            webViewInterface.loadUrl(sb.toString());
        }
        return true;
    }
    
    public static void setSdkExtraData(final Context context, final String s, final String s2) {
        if (!b.a) {
            Log.w(x.a, "Can not put SDK extra data because bugly is disable.");
            return;
        }
        if (context != null && !z.a(s)) {
            if (!z.a(s2)) {
                com.tencent.bugly.crashreport.common.info.a.a(context).a(s, s2);
            }
        }
    }
    
    public static void setServerUrl(final String s) {
        if (!z.a(s) && z.c(s)) {
            com.tencent.bugly.crashreport.common.strategy.a.a(s);
            StrategyBean.a = s;
            StrategyBean.b = s;
            return;
        }
        Log.i(x.a, "URL is invalid.");
    }
    
    public static void setSessionIntervalMills(final long n) {
        if (!b.a) {
            Log.w(x.a, "Can not set 'SessionIntervalMills' because bugly is disable.");
            return;
        }
        com.tencent.bugly.crashreport.biz.b.a(n);
    }
    
    public static void setUserId(final Context context, final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(x.a, "Context should not be null when bugly has not been initialed!");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            x.d("userId should not be null", new Object[0]);
            return;
        }
        String substring = s;
        if (s.length() > 100) {
            substring = s.substring(0, 100);
            x.d("userId %s length is over limit %d substring to %s", s, 100, substring);
        }
        if (substring.equals((Object)com.tencent.bugly.crashreport.common.info.a.a(context).g())) {
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).b(substring);
        x.b("[user] set userId : %s", substring);
        final NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            instance.setNativeUserId(substring);
        }
        if (CrashModule.getInstance().hasInitialized()) {
            com.tencent.bugly.crashreport.biz.b.a();
        }
    }
    
    public static void setUserId(final String s) {
        if (!b.a) {
            Log.w(x.a, "Can not set user ID because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        setUserId(CrashReport.a, s);
    }
    
    public static void setUserSceneTag(final Context context, final int n) {
        if (!b.a) {
            Log.w(x.a, "Can not set tag caught because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(x.a, "setTag args context should not be null");
            return;
        }
        if (n <= 0) {
            x.d("setTag args tagId should > 0", new Object[0]);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).a(n);
        x.b("[param] set user scene tag: %d", n);
    }
    
    public static void startCrashReport() {
        if (!b.a) {
            Log.w(x.a, "Can not start crash report because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        c.a().c();
    }
    
    public static void testANRCrash() {
        if (!b.a) {
            Log.w(x.a, "Can not test ANR crash because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        x.a("start to create a anr crash for test!", new Object[0]);
        c.a().k();
    }
    
    public static void testJavaCrash() {
        if (!b.a) {
            Log.w(x.a, "Can not test Java crash because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        final a b = com.tencent.bugly.crashreport.common.info.a.b();
        if (b != null) {
            b.b(24096);
        }
        throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
    }
    
    public static void testNativeCrash() {
        testNativeCrash(false, false, false);
    }
    
    public static void testNativeCrash(final boolean b, final boolean b2, final boolean b3) {
        if (!b.a) {
            Log.w(x.a, "Can not test native crash because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(x.a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        x.a("start to create a native crash for test!", new Object[0]);
        c.a().a(b, b2, b3);
    }
    
    public interface WebViewInterface
    {
        void addJavascriptInterface(final H5JavaScriptInterface p0, final String p1);
        
        CharSequence getContentDescription();
        
        String getUrl();
        
        void loadUrl(final String p0);
        
        void setJavaScriptEnabled(final boolean p0);
    }
}
