package com.tencent.mm.opensdk.channel.a;

import java.security.MessageDigest;
import android.content.Intent;
import com.tencent.mm.opensdk.utils.b;
import android.content.Context;
import com.tencent.mm.opensdk.utils.Log;
import android.os.Bundle;

public class a
{
    public static int a(final Bundle bundle, final String s, int int1) {
        if (bundle == null) {
            return int1;
        }
        try {
            int1 = bundle.getInt(s, int1);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getIntExtra exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.IntentUtil", sb.toString());
        }
        return int1;
    }
    
    public static Object a(final int n, final String s) {
        Label_0060: {
            switch (n) {
                case 6: {
                    break Label_0060;
                }
                case 5: {
                    break Label_0060;
                }
                case 4: {
                    break Label_0060;
                }
                case 3: {
                    return s;
                }
                case 2: {
                    break Label_0060;
                }
                case 1: {
                    Label_0084: {
                        Label_0065: {
                            break Label_0065;
                            try {
                                return Double.valueOf(s);
                                return Long.valueOf(s);
                                return Integer.valueOf(s);
                                return Float.valueOf(s);
                                return Boolean.valueOf(s);
                            }
                            catch (final Exception ex) {
                                break Label_0084;
                            }
                        }
                        break;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("resolveObj exception:");
                    final Exception ex;
                    sb.append(ex.getMessage());
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", sb.toString());
                    return null;
                }
            }
        }
        Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
        return null;
    }
    
    public static String a(final Bundle bundle, final String s) {
        final String s2 = null;
        if (bundle == null) {
            return null;
        }
        String string;
        try {
            string = bundle.getString(s);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("getStringExtra exception:");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.IntentUtil", sb.toString());
            string = s2;
        }
        return string;
    }
    
    public static boolean a(final Context context, final a a) {
        String s;
        if (context != null && a != null) {
            if (!b.b(a.b)) {
                String string = null;
                if (!b.b(a.a)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(a.a);
                    sb.append(".permission.MM_MESSAGE");
                    string = sb.toString();
                }
                final Intent intent = new Intent(a.b);
                final Bundle e = a.e;
                if (e != null) {
                    intent.putExtras(e);
                }
                final String packageName = context.getPackageName();
                intent.putExtra("_mmessage_sdkVersion", 638065664);
                intent.putExtra("_mmessage_appPackage", packageName);
                intent.putExtra("_mmessage_content", a.c);
                intent.putExtra("_mmessage_support_content_type", a.d);
                intent.putExtra("_mmessage_checksum", a(a.c, 638065664, packageName));
                context.sendBroadcast(intent, string);
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("send mm message, intent=");
                sb2.append((Object)intent);
                sb2.append(", perm=");
                sb2.append(string);
                Log.d("MicroMsg.SDK.MMessage", sb2.toString());
                return true;
            }
            s = "send fail, action is null";
        }
        else {
            s = "send fail, invalid argument";
        }
        Log.e("MicroMsg.SDK.MMessage", s);
        return false;
    }
    
    public static byte[] a(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore          6
        //     5: aload_0        
        //     6: ifnull          1480
        //     9: aload_0        
        //    10: invokevirtual   java/lang/String.length:()I
        //    13: ifne            19
        //    16: goto            1480
        //    19: new             Ljava/net/URL;
        //    22: astore_3       
        //    23: aload_3        
        //    24: aload_0        
        //    25: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    28: aload_3        
        //    29: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    32: checkcast       Ljava/net/HttpURLConnection;
        //    35: astore_0       
        //    36: aload_0        
        //    37: ifnonnull       114
        //    40: ldc             "MicroMsg.SDK.NetUtil"
        //    42: ldc             "open connection failed."
        //    44: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //    47: aload_0        
        //    48: ifnull          92
        //    51: aload_0        
        //    52: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //    55: goto            92
        //    58: astore_0       
        //    59: new             Ljava/lang/StringBuilder;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuilder.<init>:()V
        //    66: astore_2       
        //    67: aload_2        
        //    68: ldc             "httpGet ex:"
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    73: pop            
        //    74: aload_2        
        //    75: aload_0        
        //    76: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: pop            
        //    83: ldc             "MicroMsg.SDK.NetUtil"
        //    85: aload_2        
        //    86: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    89: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //    92: aconst_null    
        //    93: areturn        
        //    94: astore          4
        //    96: goto            192
        //    99: astore          7
        //   101: goto            203
        //   104: astore          7
        //   106: goto            215
        //   109: astore          7
        //   111: goto            227
        //   114: aload_0        
        //   115: ldc             "GET"
        //   117: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   120: aload_0        
        //   121: iload_1        
        //   122: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   125: aload_0        
        //   126: iload_1        
        //   127: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   130: aload_0        
        //   131: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   134: istore_1       
        //   135: iload_1        
        //   136: sipush          300
        //   139: if_icmplt       239
        //   142: ldc             "MicroMsg.SDK.NetUtil"
        //   144: ldc             "httpURLConnectionGet 300"
        //   146: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   149: aload_0        
        //   150: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   153: goto            190
        //   156: astore_2       
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: astore_0       
        //   165: aload_0        
        //   166: ldc             "httpGet ex:"
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: pop            
        //   172: aload_0        
        //   173: aload_2        
        //   174: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: pop            
        //   181: ldc             "MicroMsg.SDK.NetUtil"
        //   183: aload_0        
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   190: aconst_null    
        //   191: areturn        
        //   192: aconst_null    
        //   193: astore_3       
        //   194: aload_0        
        //   195: astore          5
        //   197: aload           4
        //   199: astore_0       
        //   200: goto            1331
        //   203: aconst_null    
        //   204: astore          5
        //   206: aconst_null    
        //   207: astore          4
        //   209: aload_0        
        //   210: astore          6
        //   212: goto            613
        //   215: aconst_null    
        //   216: astore          5
        //   218: aconst_null    
        //   219: astore          4
        //   221: aload_0        
        //   222: astore          6
        //   224: goto            853
        //   227: aconst_null    
        //   228: astore          5
        //   230: aconst_null    
        //   231: astore          4
        //   233: aload_0        
        //   234: astore          6
        //   236: goto            1093
        //   239: aload_0        
        //   240: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   243: astore_2       
        //   244: new             Ljava/io/ByteArrayOutputStream;
        //   247: astore_3       
        //   248: aload_3        
        //   249: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   252: sipush          1024
        //   255: newarray        B
        //   257: astore          4
        //   259: aload_2        
        //   260: aload           4
        //   262: invokevirtual   java/io/InputStream.read:([B)I
        //   265: istore_1       
        //   266: iload_1        
        //   267: iconst_m1      
        //   268: if_icmpeq       282
        //   271: aload_3        
        //   272: aload           4
        //   274: iconst_0       
        //   275: iload_1        
        //   276: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   279: goto            259
        //   282: aload_3        
        //   283: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   286: astore          4
        //   288: ldc             "MicroMsg.SDK.NetUtil"
        //   290: ldc             "httpGet end"
        //   292: invokestatic    com/tencent/mm/opensdk/utils/Log.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   295: aload_0        
        //   296: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   299: goto            338
        //   302: astore          5
        //   304: new             Ljava/lang/StringBuilder;
        //   307: dup            
        //   308: invokespecial   java/lang/StringBuilder.<init>:()V
        //   311: astore_0       
        //   312: aload_0        
        //   313: ldc             "httpGet ex:"
        //   315: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   318: pop            
        //   319: aload_0        
        //   320: aload           5
        //   322: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: pop            
        //   329: ldc             "MicroMsg.SDK.NetUtil"
        //   331: aload_0        
        //   332: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   335: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   338: aload_2        
        //   339: invokevirtual   java/io/InputStream.close:()V
        //   342: goto            379
        //   345: astore_0       
        //   346: new             Ljava/lang/StringBuilder;
        //   349: dup            
        //   350: invokespecial   java/lang/StringBuilder.<init>:()V
        //   353: astore_2       
        //   354: aload_2        
        //   355: ldc             "httpGet ex:"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: pop            
        //   361: aload_2        
        //   362: aload_0        
        //   363: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: pop            
        //   370: ldc             "MicroMsg.SDK.NetUtil"
        //   372: aload_2        
        //   373: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   376: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   379: aload_3        
        //   380: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   383: goto            420
        //   386: astore_2       
        //   387: new             Ljava/lang/StringBuilder;
        //   390: dup            
        //   391: invokespecial   java/lang/StringBuilder.<init>:()V
        //   394: astore_0       
        //   395: aload_0        
        //   396: ldc             "httpGet ex:"
        //   398: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   401: pop            
        //   402: aload_0        
        //   403: aload_2        
        //   404: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   410: pop            
        //   411: ldc             "MicroMsg.SDK.NetUtil"
        //   413: aload_0        
        //   414: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   417: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   420: aload           4
        //   422: areturn        
        //   423: astore          5
        //   425: aload_0        
        //   426: astore          4
        //   428: aload           5
        //   430: astore_0       
        //   431: goto            593
        //   434: astore          7
        //   436: aload_2        
        //   437: astore          5
        //   439: aload_3        
        //   440: astore          4
        //   442: aload_0        
        //   443: astore          6
        //   445: goto            613
        //   448: astore          7
        //   450: aload_2        
        //   451: astore          5
        //   453: aload_3        
        //   454: astore          4
        //   456: aload_0        
        //   457: astore          6
        //   459: goto            853
        //   462: astore          7
        //   464: aload_2        
        //   465: astore          5
        //   467: aload_3        
        //   468: astore          4
        //   470: aload_0        
        //   471: astore          6
        //   473: goto            1093
        //   476: astore          5
        //   478: aload           6
        //   480: astore_3       
        //   481: aload_0        
        //   482: astore          4
        //   484: aload           5
        //   486: astore_0       
        //   487: goto            593
        //   490: astore_3       
        //   491: aload_2        
        //   492: astore          5
        //   494: aload_3        
        //   495: astore_2       
        //   496: aload_0        
        //   497: astore          6
        //   499: aload_2        
        //   500: astore_0       
        //   501: goto            607
        //   504: astore_3       
        //   505: aload_2        
        //   506: astore          5
        //   508: aload_3        
        //   509: astore_2       
        //   510: aload_0        
        //   511: astore          6
        //   513: aload_2        
        //   514: astore_0       
        //   515: goto            847
        //   518: astore_3       
        //   519: aload_2        
        //   520: astore          5
        //   522: aload_3        
        //   523: astore_2       
        //   524: aload_0        
        //   525: astore          6
        //   527: aload_2        
        //   528: astore_0       
        //   529: goto            1087
        //   532: astore          5
        //   534: aconst_null    
        //   535: astore_2       
        //   536: aload           6
        //   538: astore_3       
        //   539: aload_0        
        //   540: astore          4
        //   542: aload           5
        //   544: astore_0       
        //   545: goto            593
        //   548: astore_2       
        //   549: aconst_null    
        //   550: astore          5
        //   552: aload_0        
        //   553: astore          6
        //   555: aload_2        
        //   556: astore_0       
        //   557: goto            607
        //   560: astore_2       
        //   561: aconst_null    
        //   562: astore          5
        //   564: aload_0        
        //   565: astore          6
        //   567: aload_2        
        //   568: astore_0       
        //   569: goto            847
        //   572: astore_2       
        //   573: aconst_null    
        //   574: astore          5
        //   576: aload_0        
        //   577: astore          6
        //   579: aload_2        
        //   580: astore_0       
        //   581: goto            1087
        //   584: astore_0       
        //   585: aconst_null    
        //   586: astore          4
        //   588: aconst_null    
        //   589: astore_2       
        //   590: aload           6
        //   592: astore_3       
        //   593: aload           4
        //   595: astore          5
        //   597: goto            1331
        //   600: astore_0       
        //   601: aconst_null    
        //   602: astore          6
        //   604: aconst_null    
        //   605: astore          5
        //   607: aconst_null    
        //   608: astore          4
        //   610: aload_0        
        //   611: astore          7
        //   613: aload           5
        //   615: astore_2       
        //   616: aload           4
        //   618: astore_3       
        //   619: aload           6
        //   621: astore_0       
        //   622: new             Ljava/lang/StringBuilder;
        //   625: astore          8
        //   627: aload           5
        //   629: astore_2       
        //   630: aload           4
        //   632: astore_3       
        //   633: aload           6
        //   635: astore_0       
        //   636: aload           8
        //   638: invokespecial   java/lang/StringBuilder.<init>:()V
        //   641: aload           5
        //   643: astore_2       
        //   644: aload           4
        //   646: astore_3       
        //   647: aload           6
        //   649: astore_0       
        //   650: aload           8
        //   652: ldc             "httpGet ex:"
        //   654: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   657: pop            
        //   658: aload           5
        //   660: astore_2       
        //   661: aload           4
        //   663: astore_3       
        //   664: aload           6
        //   666: astore_0       
        //   667: aload           8
        //   669: aload           7
        //   671: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   674: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   677: pop            
        //   678: aload           5
        //   680: astore_2       
        //   681: aload           4
        //   683: astore_3       
        //   684: aload           6
        //   686: astore_0       
        //   687: ldc             "MicroMsg.SDK.NetUtil"
        //   689: aload           8
        //   691: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   694: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   697: aload           6
        //   699: ifnull          744
        //   702: aload           6
        //   704: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   707: goto            744
        //   710: astore_0       
        //   711: new             Ljava/lang/StringBuilder;
        //   714: dup            
        //   715: invokespecial   java/lang/StringBuilder.<init>:()V
        //   718: astore_2       
        //   719: aload_2        
        //   720: ldc             "httpGet ex:"
        //   722: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   725: pop            
        //   726: aload_2        
        //   727: aload_0        
        //   728: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   731: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   734: pop            
        //   735: ldc             "MicroMsg.SDK.NetUtil"
        //   737: aload_2        
        //   738: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   741: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   744: aload           5
        //   746: ifnull          791
        //   749: aload           5
        //   751: invokevirtual   java/io/InputStream.close:()V
        //   754: goto            791
        //   757: astore_2       
        //   758: new             Ljava/lang/StringBuilder;
        //   761: dup            
        //   762: invokespecial   java/lang/StringBuilder.<init>:()V
        //   765: astore_0       
        //   766: aload_0        
        //   767: ldc             "httpGet ex:"
        //   769: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   772: pop            
        //   773: aload_0        
        //   774: aload_2        
        //   775: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   778: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   781: pop            
        //   782: ldc             "MicroMsg.SDK.NetUtil"
        //   784: aload_0        
        //   785: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   788: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   791: aload           4
        //   793: ifnull          838
        //   796: aload           4
        //   798: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   801: goto            838
        //   804: astore_0       
        //   805: new             Ljava/lang/StringBuilder;
        //   808: dup            
        //   809: invokespecial   java/lang/StringBuilder.<init>:()V
        //   812: astore_2       
        //   813: aload_2        
        //   814: ldc             "httpGet ex:"
        //   816: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   819: pop            
        //   820: aload_2        
        //   821: aload_0        
        //   822: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   825: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   828: pop            
        //   829: ldc             "MicroMsg.SDK.NetUtil"
        //   831: aload_2        
        //   832: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   835: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   838: aconst_null    
        //   839: areturn        
        //   840: astore_0       
        //   841: aconst_null    
        //   842: astore          6
        //   844: aconst_null    
        //   845: astore          5
        //   847: aconst_null    
        //   848: astore          4
        //   850: aload_0        
        //   851: astore          7
        //   853: aload           5
        //   855: astore_2       
        //   856: aload           4
        //   858: astore_3       
        //   859: aload           6
        //   861: astore_0       
        //   862: new             Ljava/lang/StringBuilder;
        //   865: astore          8
        //   867: aload           5
        //   869: astore_2       
        //   870: aload           4
        //   872: astore_3       
        //   873: aload           6
        //   875: astore_0       
        //   876: aload           8
        //   878: invokespecial   java/lang/StringBuilder.<init>:()V
        //   881: aload           5
        //   883: astore_2       
        //   884: aload           4
        //   886: astore_3       
        //   887: aload           6
        //   889: astore_0       
        //   890: aload           8
        //   892: ldc             "httpGet ex:"
        //   894: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   897: pop            
        //   898: aload           5
        //   900: astore_2       
        //   901: aload           4
        //   903: astore_3       
        //   904: aload           6
        //   906: astore_0       
        //   907: aload           8
        //   909: aload           7
        //   911: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   914: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   917: pop            
        //   918: aload           5
        //   920: astore_2       
        //   921: aload           4
        //   923: astore_3       
        //   924: aload           6
        //   926: astore_0       
        //   927: ldc             "MicroMsg.SDK.NetUtil"
        //   929: aload           8
        //   931: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   934: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   937: aload           6
        //   939: ifnull          984
        //   942: aload           6
        //   944: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   947: goto            984
        //   950: astore_0       
        //   951: new             Ljava/lang/StringBuilder;
        //   954: dup            
        //   955: invokespecial   java/lang/StringBuilder.<init>:()V
        //   958: astore_2       
        //   959: aload_2        
        //   960: ldc             "httpGet ex:"
        //   962: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   965: pop            
        //   966: aload_2        
        //   967: aload_0        
        //   968: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   971: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   974: pop            
        //   975: ldc             "MicroMsg.SDK.NetUtil"
        //   977: aload_2        
        //   978: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   981: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   984: aload           5
        //   986: ifnull          1031
        //   989: aload           5
        //   991: invokevirtual   java/io/InputStream.close:()V
        //   994: goto            1031
        //   997: astore_2       
        //   998: new             Ljava/lang/StringBuilder;
        //  1001: dup            
        //  1002: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1005: astore_0       
        //  1006: aload_0        
        //  1007: ldc             "httpGet ex:"
        //  1009: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1012: pop            
        //  1013: aload_0        
        //  1014: aload_2        
        //  1015: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1018: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1021: pop            
        //  1022: ldc             "MicroMsg.SDK.NetUtil"
        //  1024: aload_0        
        //  1025: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1028: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1031: aload           4
        //  1033: ifnull          1078
        //  1036: aload           4
        //  1038: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //  1041: goto            1078
        //  1044: astore_2       
        //  1045: new             Ljava/lang/StringBuilder;
        //  1048: dup            
        //  1049: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1052: astore_0       
        //  1053: aload_0        
        //  1054: ldc             "httpGet ex:"
        //  1056: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1059: pop            
        //  1060: aload_0        
        //  1061: aload_2        
        //  1062: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1065: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1068: pop            
        //  1069: ldc             "MicroMsg.SDK.NetUtil"
        //  1071: aload_0        
        //  1072: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1075: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1078: aconst_null    
        //  1079: areturn        
        //  1080: astore_0       
        //  1081: aconst_null    
        //  1082: astore          6
        //  1084: aconst_null    
        //  1085: astore          5
        //  1087: aconst_null    
        //  1088: astore          4
        //  1090: aload_0        
        //  1091: astore          7
        //  1093: aload           5
        //  1095: astore_2       
        //  1096: aload           4
        //  1098: astore_3       
        //  1099: aload           6
        //  1101: astore_0       
        //  1102: new             Ljava/lang/StringBuilder;
        //  1105: astore          8
        //  1107: aload           5
        //  1109: astore_2       
        //  1110: aload           4
        //  1112: astore_3       
        //  1113: aload           6
        //  1115: astore_0       
        //  1116: aload           8
        //  1118: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1121: aload           5
        //  1123: astore_2       
        //  1124: aload           4
        //  1126: astore_3       
        //  1127: aload           6
        //  1129: astore_0       
        //  1130: aload           8
        //  1132: ldc             "httpGet ex:"
        //  1134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1137: pop            
        //  1138: aload           5
        //  1140: astore_2       
        //  1141: aload           4
        //  1143: astore_3       
        //  1144: aload           6
        //  1146: astore_0       
        //  1147: aload           8
        //  1149: aload           7
        //  1151: invokevirtual   java/net/MalformedURLException.getMessage:()Ljava/lang/String;
        //  1154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1157: pop            
        //  1158: aload           5
        //  1160: astore_2       
        //  1161: aload           4
        //  1163: astore_3       
        //  1164: aload           6
        //  1166: astore_0       
        //  1167: ldc             "MicroMsg.SDK.NetUtil"
        //  1169: aload           8
        //  1171: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1174: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1177: aload           6
        //  1179: ifnull          1224
        //  1182: aload           6
        //  1184: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //  1187: goto            1224
        //  1190: astore_2       
        //  1191: new             Ljava/lang/StringBuilder;
        //  1194: dup            
        //  1195: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1198: astore_0       
        //  1199: aload_0        
        //  1200: ldc             "httpGet ex:"
        //  1202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1205: pop            
        //  1206: aload_0        
        //  1207: aload_2        
        //  1208: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1214: pop            
        //  1215: ldc             "MicroMsg.SDK.NetUtil"
        //  1217: aload_0        
        //  1218: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1221: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1224: aload           5
        //  1226: ifnull          1271
        //  1229: aload           5
        //  1231: invokevirtual   java/io/InputStream.close:()V
        //  1234: goto            1271
        //  1237: astore_0       
        //  1238: new             Ljava/lang/StringBuilder;
        //  1241: dup            
        //  1242: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1245: astore_2       
        //  1246: aload_2        
        //  1247: ldc             "httpGet ex:"
        //  1249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1252: pop            
        //  1253: aload_2        
        //  1254: aload_0        
        //  1255: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1261: pop            
        //  1262: ldc             "MicroMsg.SDK.NetUtil"
        //  1264: aload_2        
        //  1265: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1268: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1271: aload           4
        //  1273: ifnull          1318
        //  1276: aload           4
        //  1278: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //  1281: goto            1318
        //  1284: astore_2       
        //  1285: new             Ljava/lang/StringBuilder;
        //  1288: dup            
        //  1289: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1292: astore_0       
        //  1293: aload_0        
        //  1294: ldc             "httpGet ex:"
        //  1296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1299: pop            
        //  1300: aload_0        
        //  1301: aload_2        
        //  1302: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1308: pop            
        //  1309: ldc             "MicroMsg.SDK.NetUtil"
        //  1311: aload_0        
        //  1312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1315: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1318: aconst_null    
        //  1319: areturn        
        //  1320: astore          5
        //  1322: aload_0        
        //  1323: astore          4
        //  1325: aload           5
        //  1327: astore_0       
        //  1328: goto            593
        //  1331: aload           5
        //  1333: ifnull          1384
        //  1336: aload           5
        //  1338: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //  1341: goto            1384
        //  1344: astore          4
        //  1346: new             Ljava/lang/StringBuilder;
        //  1349: dup            
        //  1350: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1353: astore          5
        //  1355: aload           5
        //  1357: ldc             "httpGet ex:"
        //  1359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1362: pop            
        //  1363: aload           5
        //  1365: aload           4
        //  1367: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1370: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1373: pop            
        //  1374: ldc             "MicroMsg.SDK.NetUtil"
        //  1376: aload           5
        //  1378: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1381: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1384: aload_2        
        //  1385: ifnull          1433
        //  1388: aload_2        
        //  1389: invokevirtual   java/io/InputStream.close:()V
        //  1392: goto            1433
        //  1395: astore_2       
        //  1396: new             Ljava/lang/StringBuilder;
        //  1399: dup            
        //  1400: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1403: astore          4
        //  1405: aload           4
        //  1407: ldc             "httpGet ex:"
        //  1409: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1412: pop            
        //  1413: aload           4
        //  1415: aload_2        
        //  1416: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1419: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1422: pop            
        //  1423: ldc             "MicroMsg.SDK.NetUtil"
        //  1425: aload           4
        //  1427: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1430: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1433: aload_3        
        //  1434: ifnull          1478
        //  1437: aload_3        
        //  1438: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //  1441: goto            1478
        //  1444: astore_3       
        //  1445: new             Ljava/lang/StringBuilder;
        //  1448: dup            
        //  1449: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1452: astore_2       
        //  1453: aload_2        
        //  1454: ldc             "httpGet ex:"
        //  1456: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1459: pop            
        //  1460: aload_2        
        //  1461: aload_3        
        //  1462: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //  1465: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1468: pop            
        //  1469: ldc             "MicroMsg.SDK.NetUtil"
        //  1471: aload_2        
        //  1472: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1475: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1478: aload_0        
        //  1479: athrow         
        //  1480: ldc             "MicroMsg.SDK.NetUtil"
        //  1482: ldc             "httpGet, url is null"
        //  1484: invokestatic    com/tencent/mm/opensdk/utils/Log.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1487: aconst_null    
        //  1488: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  19     36     1080   1087   Ljava/net/MalformedURLException;
        //  19     36     840    847    Ljava/io/IOException;
        //  19     36     600    607    Ljava/lang/Exception;
        //  19     36     584    593    Any
        //  40     47     109    114    Ljava/net/MalformedURLException;
        //  40     47     104    109    Ljava/io/IOException;
        //  40     47     99     104    Ljava/lang/Exception;
        //  40     47     94     99     Any
        //  51     55     58     92     Any
        //  114    135    572    584    Ljava/net/MalformedURLException;
        //  114    135    560    572    Ljava/io/IOException;
        //  114    135    548    560    Ljava/lang/Exception;
        //  114    135    532    548    Any
        //  142    149    109    114    Ljava/net/MalformedURLException;
        //  142    149    104    109    Ljava/io/IOException;
        //  142    149    99     104    Ljava/lang/Exception;
        //  142    149    94     99     Any
        //  149    153    156    190    Any
        //  239    244    572    584    Ljava/net/MalformedURLException;
        //  239    244    560    572    Ljava/io/IOException;
        //  239    244    548    560    Ljava/lang/Exception;
        //  239    244    532    548    Any
        //  244    252    518    532    Ljava/net/MalformedURLException;
        //  244    252    504    518    Ljava/io/IOException;
        //  244    252    490    504    Ljava/lang/Exception;
        //  244    252    476    490    Any
        //  252    259    462    476    Ljava/net/MalformedURLException;
        //  252    259    448    462    Ljava/io/IOException;
        //  252    259    434    448    Ljava/lang/Exception;
        //  252    259    423    434    Any
        //  259    266    462    476    Ljava/net/MalformedURLException;
        //  259    266    448    462    Ljava/io/IOException;
        //  259    266    434    448    Ljava/lang/Exception;
        //  259    266    423    434    Any
        //  271    279    462    476    Ljava/net/MalformedURLException;
        //  271    279    448    462    Ljava/io/IOException;
        //  271    279    434    448    Ljava/lang/Exception;
        //  271    279    423    434    Any
        //  282    295    462    476    Ljava/net/MalformedURLException;
        //  282    295    448    462    Ljava/io/IOException;
        //  282    295    434    448    Ljava/lang/Exception;
        //  282    295    423    434    Any
        //  295    299    302    338    Any
        //  338    342    345    379    Any
        //  379    383    386    420    Any
        //  622    627    1320   1331   Any
        //  636    641    1320   1331   Any
        //  650    658    1320   1331   Any
        //  667    678    1320   1331   Any
        //  687    697    1320   1331   Any
        //  702    707    710    744    Any
        //  749    754    757    791    Any
        //  796    801    804    838    Any
        //  862    867    1320   1331   Any
        //  876    881    1320   1331   Any
        //  890    898    1320   1331   Any
        //  907    918    1320   1331   Any
        //  927    937    1320   1331   Any
        //  942    947    950    984    Any
        //  989    994    997    1031   Any
        //  1036   1041   1044   1078   Any
        //  1102   1107   1320   1331   Any
        //  1116   1121   1320   1331   Any
        //  1130   1138   1320   1331   Any
        //  1147   1158   1320   1331   Any
        //  1167   1177   1320   1331   Any
        //  1182   1187   1190   1224   Any
        //  1229   1234   1237   1271   Any
        //  1276   1281   1284   1318   Any
        //  1336   1341   1344   1384   Any
        //  1388   1392   1395   1433   Any
        //  1437   1441   1444   1478   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0744:
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
    
    public static byte[] a(final String s, int i, final String s2) {
        final StringBuffer sb = new StringBuffer();
        if (s != null) {
            sb.append(s);
        }
        sb.append(i);
        sb.append(s2);
        sb.append("mMcShCsTr");
        final byte[] bytes = sb.toString().substring(1, 9).getBytes();
        final char[] array2;
        final char[] array = array2 = new char[16];
        array2[0] = '0';
        array2[1] = '1';
        array2[2] = '2';
        array2[3] = '3';
        array2[4] = '4';
        array2[5] = '5';
        array2[6] = '6';
        array2[7] = '7';
        array2[8] = '8';
        array2[9] = '9';
        array2[10] = 'a';
        array2[11] = 'b';
        array2[12] = 'c';
        array2[13] = 'd';
        array2[14] = 'e';
        array2[15] = 'f';
        String s3;
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            final byte[] digest = instance.digest();
            final int length = digest.length;
            final char[] array3 = new char[length * 2];
            i = 0;
            int n = 0;
            while (i < length) {
                final byte b = digest[i];
                final int n2 = n + 1;
                array3[n] = array[b >>> 4 & 0xF];
                n = n2 + 1;
                array3[n2] = array[b & 0xF];
                ++i;
            }
            s3 = new String(array3);
        }
        catch (final Exception ex) {
            s3 = null;
        }
        return s3.getBytes();
    }
    
    public static class a
    {
        public String a;
        public String b;
        public String c;
        public long d;
        public Bundle e;
    }
}
