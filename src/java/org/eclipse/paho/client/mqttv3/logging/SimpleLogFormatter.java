package org.eclipse.paho.client.mqttv3.logging;

import java.util.logging.LogRecord;
import java.util.logging.Formatter;

public class SimpleLogFormatter extends Formatter
{
    private static final String LS;
    
    static {
        LS = System.getProperty("line.separator");
    }
    
    public static String left(final String s, int n, final char c) {
        if (s.length() >= n) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(n);
        sb.append(s);
        n -= s.length();
        while (--n >= 0) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public String format(final LogRecord p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuffer.<init>:()V
        //     7: astore          5
        //     9: aload           5
        //    11: aload_1        
        //    12: invokevirtual   java/util/logging/LogRecord.getLevel:()Ljava/util/logging/Level;
        //    15: invokevirtual   java/util/logging/Level.getName:()Ljava/lang/String;
        //    18: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    21: pop            
        //    22: aload           5
        //    24: ldc             "\t"
        //    26: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    29: pop            
        //    30: new             Ljava/lang/StringBuilder;
        //    33: dup            
        //    34: ldc             "{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} "
        //    36: iconst_1       
        //    37: anewarray       Ljava/lang/Object;
        //    40: dup            
        //    41: iconst_0       
        //    42: new             Ljava/util/Date;
        //    45: dup            
        //    46: aload_1        
        //    47: invokevirtual   java/util/logging/LogRecord.getMillis:()J
        //    50: invokespecial   java/util/Date.<init>:(J)V
        //    53: aastore        
        //    54: invokestatic    java/text/MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    57: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    60: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    63: astore_3       
        //    64: aload_3        
        //    65: ldc             "\t"
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: pop            
        //    71: aload           5
        //    73: aload_3        
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    80: pop            
        //    81: aload_1        
        //    82: invokevirtual   java/util/logging/LogRecord.getSourceClassName:()Ljava/lang/String;
        //    85: astore_3       
        //    86: aload_3        
        //    87: ifnull          157
        //    90: aload_3        
        //    91: invokevirtual   java/lang/String.length:()I
        //    94: istore_2       
        //    95: iload_2        
        //    96: bipush          20
        //    98: if_icmple       116
        //   101: aload_1        
        //   102: invokevirtual   java/util/logging/LogRecord.getSourceClassName:()Ljava/lang/String;
        //   105: iload_2        
        //   106: bipush          19
        //   108: isub           
        //   109: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   112: astore_3       
        //   113: goto            160
        //   116: new             Ljava/lang/StringBuffer;
        //   119: dup            
        //   120: invokespecial   java/lang/StringBuffer.<init>:()V
        //   123: astore          4
        //   125: aload           4
        //   127: aload_3        
        //   128: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   131: pop            
        //   132: aload           4
        //   134: iconst_1       
        //   135: newarray        C
        //   137: dup            
        //   138: iconst_0       
        //   139: bipush          32
        //   141: castore        
        //   142: iconst_0       
        //   143: iconst_1       
        //   144: invokevirtual   java/lang/StringBuffer.append:([CII)Ljava/lang/StringBuffer;
        //   147: pop            
        //   148: aload           4
        //   150: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   153: astore_3       
        //   154: goto            160
        //   157: ldc             ""
        //   159: astore_3       
        //   160: aload           5
        //   162: aload_3        
        //   163: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   166: pop            
        //   167: aload           5
        //   169: ldc             "\t"
        //   171: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   174: pop            
        //   175: aload           5
        //   177: ldc             " "
        //   179: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   182: pop            
        //   183: aload           5
        //   185: aload_1        
        //   186: invokevirtual   java/util/logging/LogRecord.getSourceMethodName:()Ljava/lang/String;
        //   189: bipush          23
        //   191: bipush          32
        //   193: invokestatic    org/eclipse/paho/client/mqttv3/logging/SimpleLogFormatter.left:(Ljava/lang/String;IC)Ljava/lang/String;
        //   196: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   199: pop            
        //   200: aload           5
        //   202: ldc             "\t"
        //   204: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   207: pop            
        //   208: aload           5
        //   210: aload_1        
        //   211: invokevirtual   java/util/logging/LogRecord.getThreadID:()I
        //   214: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   217: pop            
        //   218: aload           5
        //   220: ldc             "\t"
        //   222: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   225: pop            
        //   226: aload           5
        //   228: aload_0        
        //   229: aload_1        
        //   230: invokevirtual   org/eclipse/paho/client/mqttv3/logging/SimpleLogFormatter.formatMessage:(Ljava/util/logging/LogRecord;)Ljava/lang/String;
        //   233: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   236: pop            
        //   237: aload           5
        //   239: getstatic       org/eclipse/paho/client/mqttv3/logging/SimpleLogFormatter.LS:Ljava/lang/String;
        //   242: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   245: pop            
        //   246: aload_1        
        //   247: invokevirtual   java/util/logging/LogRecord.getThrown:()Ljava/lang/Throwable;
        //   250: ifnull          333
        //   253: aload           5
        //   255: ldc             "Throwable occurred: "
        //   257: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   260: pop            
        //   261: aload_1        
        //   262: invokevirtual   java/util/logging/LogRecord.getThrown:()Ljava/lang/Throwable;
        //   265: astore_1       
        //   266: aconst_null    
        //   267: astore_3       
        //   268: new             Ljava/io/StringWriter;
        //   271: astore          6
        //   273: aload           6
        //   275: invokespecial   java/io/StringWriter.<init>:()V
        //   278: new             Ljava/io/PrintWriter;
        //   281: astore          4
        //   283: aload           4
        //   285: aload           6
        //   287: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //   290: aload_1        
        //   291: aload           4
        //   293: invokevirtual   java/lang/Throwable.printStackTrace:(Ljava/io/PrintWriter;)V
        //   296: aload           5
        //   298: aload           6
        //   300: invokevirtual   java/io/StringWriter.toString:()Ljava/lang/String;
        //   303: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   306: pop            
        //   307: aload           4
        //   309: invokevirtual   java/io/PrintWriter.close:()V
        //   312: goto            333
        //   315: astore_1       
        //   316: aload           4
        //   318: astore_3       
        //   319: goto            323
        //   322: astore_1       
        //   323: aload_3        
        //   324: ifnull          331
        //   327: aload_3        
        //   328: invokevirtual   java/io/PrintWriter.close:()V
        //   331: aload_1        
        //   332: athrow         
        //   333: aload           5
        //   335: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   338: areturn        
        //   339: astore_1       
        //   340: goto            333
        //   343: astore_3       
        //   344: goto            331
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  268    290    322    323    Any
        //  290    307    315    322    Any
        //  307    312    339    343    Ljava/lang/Exception;
        //  327    331    343    347    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0331:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
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
}
