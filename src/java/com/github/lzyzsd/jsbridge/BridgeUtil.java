package com.github.lzyzsd.jsbridge;

import android.webkit.WebView;
import android.content.Context;

public class BridgeUtil
{
    static final String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    static final String EMPTY_STR = "";
    public static final String JAVASCRIPT_STR = "javascript:";
    static final String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();";
    static final String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    static final String SPLIT_MARK = "/";
    static final String UNDERLINE_STR = "_";
    static final String YY_FETCH_QUEUE = "yy://return/_fetchQueue/";
    static final String YY_OVERRIDE_SCHEMA = "yy://";
    static final String YY_RETURN_DATA = "yy://return/";
    
    public static String assetFile2Str(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: aload_0        
        //     3: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //     6: aload_1        
        //     7: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //    10: astore_1       
        //    11: aload_1        
        //    12: astore_0       
        //    13: new             Ljava/io/BufferedReader;
        //    16: astore_2       
        //    17: aload_1        
        //    18: astore_0       
        //    19: new             Ljava/io/InputStreamReader;
        //    22: astore_3       
        //    23: aload_1        
        //    24: astore_0       
        //    25: aload_3        
        //    26: aload_1        
        //    27: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    30: aload_1        
        //    31: astore_0       
        //    32: aload_2        
        //    33: aload_3        
        //    34: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    37: aload_1        
        //    38: astore_0       
        //    39: new             Ljava/lang/StringBuilder;
        //    42: astore          4
        //    44: aload_1        
        //    45: astore_0       
        //    46: aload           4
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: aload_1        
        //    52: astore_0       
        //    53: aload_2        
        //    54: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    57: astore_3       
        //    58: aload_3        
        //    59: ifnull          82
        //    62: aload_1        
        //    63: astore_0       
        //    64: aload_3        
        //    65: ldc             "^\\s*\\/\\/.*"
        //    67: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    70: ifne            82
        //    73: aload_1        
        //    74: astore_0       
        //    75: aload           4
        //    77: aload_3        
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: pop            
        //    82: aload_3        
        //    83: ifnonnull       51
        //    86: aload_1        
        //    87: astore_0       
        //    88: aload_2        
        //    89: invokevirtual   java/io/BufferedReader.close:()V
        //    92: aload_1        
        //    93: astore_0       
        //    94: aload_1        
        //    95: invokevirtual   java/io/InputStream.close:()V
        //    98: aload_1        
        //    99: astore_0       
        //   100: aload           4
        //   102: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   105: astore_2       
        //   106: aload_1        
        //   107: ifnull          114
        //   110: aload_1        
        //   111: invokevirtual   java/io/InputStream.close:()V
        //   114: aload_2        
        //   115: areturn        
        //   116: astore_2       
        //   117: goto            129
        //   120: astore_0       
        //   121: aload_2        
        //   122: astore_1       
        //   123: goto            150
        //   126: astore_2       
        //   127: aconst_null    
        //   128: astore_1       
        //   129: aload_1        
        //   130: astore_0       
        //   131: aload_2        
        //   132: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   135: aload_1        
        //   136: ifnull          143
        //   139: aload_1        
        //   140: invokevirtual   java/io/InputStream.close:()V
        //   143: aconst_null    
        //   144: areturn        
        //   145: astore_2       
        //   146: aload_0        
        //   147: astore_1       
        //   148: aload_2        
        //   149: astore_0       
        //   150: aload_1        
        //   151: ifnull          158
        //   154: aload_1        
        //   155: invokevirtual   java/io/InputStream.close:()V
        //   158: aload_0        
        //   159: athrow         
        //   160: astore_0       
        //   161: goto            114
        //   164: astore_0       
        //   165: goto            143
        //   168: astore_1       
        //   169: goto            158
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      11     126    129    Ljava/lang/Exception;
        //  2      11     120    126    Any
        //  13     17     116    120    Ljava/lang/Exception;
        //  13     17     145    150    Any
        //  19     23     116    120    Ljava/lang/Exception;
        //  19     23     145    150    Any
        //  25     30     116    120    Ljava/lang/Exception;
        //  25     30     145    150    Any
        //  32     37     116    120    Ljava/lang/Exception;
        //  32     37     145    150    Any
        //  39     44     116    120    Ljava/lang/Exception;
        //  39     44     145    150    Any
        //  46     51     116    120    Ljava/lang/Exception;
        //  46     51     145    150    Any
        //  53     58     116    120    Ljava/lang/Exception;
        //  53     58     145    150    Any
        //  64     73     116    120    Ljava/lang/Exception;
        //  64     73     145    150    Any
        //  75     82     116    120    Ljava/lang/Exception;
        //  75     82     145    150    Any
        //  88     92     116    120    Ljava/lang/Exception;
        //  88     92     145    150    Any
        //  94     98     116    120    Ljava/lang/Exception;
        //  94     98     145    150    Any
        //  100    106    116    120    Ljava/lang/Exception;
        //  100    106    145    150    Any
        //  110    114    160    164    Ljava/io/IOException;
        //  131    135    145    150    Any
        //  139    143    164    168    Ljava/io/IOException;
        //  154    158    168    172    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 109, Size: 109
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String getDataFromReturnUrl(final String s) {
        if (s.startsWith("yy://return/_fetchQueue/")) {
            return s.replace((CharSequence)"yy://return/_fetchQueue/", (CharSequence)"");
        }
        final String[] split = s.replace((CharSequence)"yy://return/", (CharSequence)"").split("/");
        if (split.length >= 2) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 1; i < split.length; ++i) {
                sb.append(split[i]);
            }
            return sb.toString();
        }
        return null;
    }
    
    public static String getFunctionFromReturnUrl(final String s) {
        final String[] split = s.replace((CharSequence)"yy://return/", (CharSequence)"").split("/");
        if (split.length >= 1) {
            return split[0];
        }
        return null;
    }
    
    public static String parseFunctionName(final String s) {
        return s.replace((CharSequence)"javascript:WebViewJavascriptBridge.", (CharSequence)"").replaceAll("\\(.*\\);", "");
    }
    
    public static void webViewLoadJs(final WebView webView, String string) {
        final StringBuilder sb = new StringBuilder();
        sb.append("var newscript = document.createElement(\"script\");");
        sb.append("newscript.src=\"");
        sb.append(string);
        sb.append("\";");
        string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        sb2.append("document.scripts[0].parentNode.insertBefore(newscript,document.scripts[0]);");
        final String string2 = sb2.toString();
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("javascript:");
        sb3.append(string2);
        webView.loadUrl(sb3.toString());
    }
    
    public static void webViewLoadLocalJs(final WebView webView, final String s) {
        final String assetFile2Str = assetFile2Str(webView.getContext(), s);
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(assetFile2Str);
        webView.loadUrl(sb.toString());
    }
}
