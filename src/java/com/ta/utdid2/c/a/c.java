package com.ta.utdid2.c.a;

import com.ta.utdid2.b.a.i;
import java.util.Iterator;
import java.util.Map$Entry;
import android.os.Environment;
import java.io.File;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;

public class c
{
    private SharedPreferences$Editor a;
    private SharedPreferences a;
    private b.a a;
    private b a;
    private d a;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Context mContext;
    
    public c(final Context p0, final String p1, final String p2, final boolean p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: ldc             ""
        //     7: putfield        com/ta/utdid2/c/a/c.e:Ljava/lang/String;
        //    10: aload_0        
        //    11: ldc             ""
        //    13: putfield        com/ta/utdid2/c/a/c.f:Ljava/lang/String;
        //    16: aload_0        
        //    17: iconst_0       
        //    18: putfield        com/ta/utdid2/c/a/c.g:Z
        //    21: aload_0        
        //    22: iconst_0       
        //    23: putfield        com/ta/utdid2/c/a/c.h:Z
        //    26: aload_0        
        //    27: iconst_0       
        //    28: putfield        com/ta/utdid2/c/a/c.i:Z
        //    31: aconst_null    
        //    32: astore          16
        //    34: aload_0        
        //    35: aconst_null    
        //    36: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //    39: aload_0        
        //    40: aconst_null    
        //    41: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //    44: aload_0        
        //    45: aconst_null    
        //    46: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences$Editor;
        //    49: aload_0        
        //    50: aconst_null    
        //    51: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b$a;
        //    54: aload_0        
        //    55: aconst_null    
        //    56: putfield        com/ta/utdid2/c/a/c.mContext:Landroid/content/Context;
        //    59: aload_0        
        //    60: aconst_null    
        //    61: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //    64: aload_0        
        //    65: iconst_0       
        //    66: putfield        com/ta/utdid2/c/a/c.j:Z
        //    69: aload_0        
        //    70: iload           4
        //    72: putfield        com/ta/utdid2/c/a/c.g:Z
        //    75: aload_0        
        //    76: iload           5
        //    78: putfield        com/ta/utdid2/c/a/c.j:Z
        //    81: aload_0        
        //    82: aload_3        
        //    83: putfield        com/ta/utdid2/c/a/c.e:Ljava/lang/String;
        //    86: aload_0        
        //    87: aload_2        
        //    88: putfield        com/ta/utdid2/c/a/c.f:Ljava/lang/String;
        //    91: aload_0        
        //    92: aload_1        
        //    93: putfield        com/ta/utdid2/c/a/c.mContext:Landroid/content/Context;
        //    96: aload_1        
        //    97: ifnull          129
        //   100: aload_1        
        //   101: aload_3        
        //   102: iconst_0       
        //   103: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   106: astore          17
        //   108: aload_0        
        //   109: aload           17
        //   111: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   114: aload           17
        //   116: ldc             "t"
        //   118: lconst_0       
        //   119: invokeinterface android/content/SharedPreferences.getLong:(Ljava/lang/String;J)J
        //   124: lstore          8
        //   126: goto            132
        //   129: lconst_0       
        //   130: lstore          8
        //   132: invokestatic    android/os/Environment.getExternalStorageState:()Ljava/lang/String;
        //   135: astore          17
        //   137: aload           17
        //   139: astore          16
        //   141: goto            151
        //   144: astore          17
        //   146: aload           17
        //   148: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   151: aload           16
        //   153: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //   156: ifeq            172
        //   159: aload_0        
        //   160: iconst_0       
        //   161: putfield        com/ta/utdid2/c/a/c.i:Z
        //   164: aload_0        
        //   165: iconst_0       
        //   166: putfield        com/ta/utdid2/c/a/c.h:Z
        //   169: goto            228
        //   172: aload           16
        //   174: ldc             "mounted"
        //   176: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   179: ifeq            195
        //   182: aload_0        
        //   183: iconst_1       
        //   184: putfield        com/ta/utdid2/c/a/c.i:Z
        //   187: aload_0        
        //   188: iconst_1       
        //   189: putfield        com/ta/utdid2/c/a/c.h:Z
        //   192: goto            228
        //   195: aload           16
        //   197: ldc             "mounted_ro"
        //   199: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   202: ifeq            218
        //   205: aload_0        
        //   206: iconst_1       
        //   207: putfield        com/ta/utdid2/c/a/c.h:Z
        //   210: aload_0        
        //   211: iconst_0       
        //   212: putfield        com/ta/utdid2/c/a/c.i:Z
        //   215: goto            228
        //   218: aload_0        
        //   219: iconst_0       
        //   220: putfield        com/ta/utdid2/c/a/c.i:Z
        //   223: aload_0        
        //   224: iconst_0       
        //   225: putfield        com/ta/utdid2/c/a/c.h:Z
        //   228: aload_0        
        //   229: getfield        com/ta/utdid2/c/a/c.h:Z
        //   232: ifne            242
        //   235: aload_0        
        //   236: getfield        com/ta/utdid2/c/a/c.i:Z
        //   239: ifeq            855
        //   242: aload_1        
        //   243: ifnull          855
        //   246: aload_2        
        //   247: invokestatic    com/ta/utdid2/b/a/i.a:(Ljava/lang/String;)Z
        //   250: ifne            855
        //   253: aload_0        
        //   254: aload_2        
        //   255: invokespecial   com/ta/utdid2/c/a/c.a:(Ljava/lang/String;)Lcom/ta/utdid2/c/a/d;
        //   258: astore_2       
        //   259: aload_0        
        //   260: aload_2        
        //   261: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   264: aload_2        
        //   265: ifnull          855
        //   268: aload_2        
        //   269: aload_3        
        //   270: iconst_0       
        //   271: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   274: astore_2       
        //   275: aload_0        
        //   276: aload_2        
        //   277: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   280: aload_2        
        //   281: ldc             "t"
        //   283: lconst_0       
        //   284: invokeinterface com/ta/utdid2/c/a/b.getLong:(Ljava/lang/String;J)J
        //   289: lstore          14
        //   291: iload           5
        //   293: ifne            479
        //   296: lload           8
        //   298: lload           14
        //   300: lcmp           
        //   301: istore          6
        //   303: iload           6
        //   305: ifle            360
        //   308: lload           8
        //   310: lstore          12
        //   312: lload           14
        //   314: lstore          10
        //   316: aload_0        
        //   317: aload_0        
        //   318: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   321: aload_0        
        //   322: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   325: invokespecial   com/ta/utdid2/c/a/c.a:(Landroid/content/SharedPreferences;Lcom/ta/utdid2/c/a/b;)V
        //   328: lload           8
        //   330: lstore          12
        //   332: lload           14
        //   334: lstore          10
        //   336: aload_0        
        //   337: aload_0        
        //   338: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   341: aload_3        
        //   342: iconst_0       
        //   343: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   346: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   349: lload           8
        //   351: lstore          12
        //   353: lload           14
        //   355: lstore          10
        //   357: goto            862
        //   360: iload           6
        //   362: ifge            414
        //   365: lload           8
        //   367: lstore          12
        //   369: lload           14
        //   371: lstore          10
        //   373: aload_0        
        //   374: aload_0        
        //   375: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   378: aload_0        
        //   379: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   382: invokespecial   com/ta/utdid2/c/a/c.a:(Lcom/ta/utdid2/c/a/b;Landroid/content/SharedPreferences;)V
        //   385: lload           8
        //   387: lstore          12
        //   389: lload           14
        //   391: lstore          10
        //   393: aload_0        
        //   394: aload_1        
        //   395: aload_3        
        //   396: iconst_0       
        //   397: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   400: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   403: lload           8
        //   405: lstore          12
        //   407: lload           14
        //   409: lstore          10
        //   411: goto            862
        //   414: lload           8
        //   416: lstore          12
        //   418: lload           14
        //   420: lstore          10
        //   422: iload           6
        //   424: ifne            862
        //   427: lload           8
        //   429: lstore          12
        //   431: lload           14
        //   433: lstore          10
        //   435: aload_0        
        //   436: aload_0        
        //   437: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   440: aload_0        
        //   441: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   444: invokespecial   com/ta/utdid2/c/a/c.a:(Landroid/content/SharedPreferences;Lcom/ta/utdid2/c/a/b;)V
        //   447: lload           8
        //   449: lstore          12
        //   451: lload           14
        //   453: lstore          10
        //   455: aload_0        
        //   456: aload_0        
        //   457: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   460: aload_3        
        //   461: iconst_0       
        //   462: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   465: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   468: lload           8
        //   470: lstore          12
        //   472: lload           14
        //   474: lstore          10
        //   476: goto            862
        //   479: lload           8
        //   481: lstore          12
        //   483: lload           14
        //   485: lstore          10
        //   487: aload_0        
        //   488: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   491: ldc             "t2"
        //   493: lconst_0       
        //   494: invokeinterface android/content/SharedPreferences.getLong:(Ljava/lang/String;J)J
        //   499: lstore          8
        //   501: lload           8
        //   503: lstore          12
        //   505: lload           14
        //   507: lstore          10
        //   509: aload_0        
        //   510: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   513: ldc             "t2"
        //   515: lconst_0       
        //   516: invokeinterface com/ta/utdid2/c/a/b.getLong:(Ljava/lang/String;J)J
        //   521: lstore          14
        //   523: lload           8
        //   525: lload           14
        //   527: lcmp           
        //   528: istore          7
        //   530: iload           7
        //   532: ifge            594
        //   535: lload           8
        //   537: lconst_0       
        //   538: lcmp           
        //   539: ifle            594
        //   542: lload           8
        //   544: lstore          12
        //   546: lload           14
        //   548: lstore          10
        //   550: aload_0        
        //   551: aload_0        
        //   552: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   555: aload_0        
        //   556: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   559: invokespecial   com/ta/utdid2/c/a/c.a:(Landroid/content/SharedPreferences;Lcom/ta/utdid2/c/a/b;)V
        //   562: lload           8
        //   564: lstore          12
        //   566: lload           14
        //   568: lstore          10
        //   570: aload_0        
        //   571: aload_0        
        //   572: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   575: aload_3        
        //   576: iconst_0       
        //   577: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   580: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   583: lload           8
        //   585: lstore          12
        //   587: lload           14
        //   589: lstore          10
        //   591: goto            862
        //   594: iload           7
        //   596: ifle            655
        //   599: lload           14
        //   601: lconst_0       
        //   602: lcmp           
        //   603: ifle            655
        //   606: lload           8
        //   608: lstore          12
        //   610: lload           14
        //   612: lstore          10
        //   614: aload_0        
        //   615: aload_0        
        //   616: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   619: aload_0        
        //   620: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   623: invokespecial   com/ta/utdid2/c/a/c.a:(Lcom/ta/utdid2/c/a/b;Landroid/content/SharedPreferences;)V
        //   626: lload           8
        //   628: lstore          12
        //   630: lload           14
        //   632: lstore          10
        //   634: aload_0        
        //   635: aload_1        
        //   636: aload_3        
        //   637: iconst_0       
        //   638: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   641: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   644: lload           8
        //   646: lstore          12
        //   648: lload           14
        //   650: lstore          10
        //   652: goto            862
        //   655: lload           8
        //   657: lconst_0       
        //   658: lcmp           
        //   659: istore          6
        //   661: iload           6
        //   663: ifne            722
        //   666: lload           14
        //   668: lconst_0       
        //   669: lcmp           
        //   670: ifle            722
        //   673: lload           8
        //   675: lstore          12
        //   677: lload           14
        //   679: lstore          10
        //   681: aload_0        
        //   682: aload_0        
        //   683: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   686: aload_0        
        //   687: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   690: invokespecial   com/ta/utdid2/c/a/c.a:(Lcom/ta/utdid2/c/a/b;Landroid/content/SharedPreferences;)V
        //   693: lload           8
        //   695: lstore          12
        //   697: lload           14
        //   699: lstore          10
        //   701: aload_0        
        //   702: aload_1        
        //   703: aload_3        
        //   704: iconst_0       
        //   705: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   708: putfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   711: lload           8
        //   713: lstore          12
        //   715: lload           14
        //   717: lstore          10
        //   719: goto            862
        //   722: lload           14
        //   724: lconst_0       
        //   725: lcmp           
        //   726: ifne            786
        //   729: iload           6
        //   731: ifle            786
        //   734: lload           8
        //   736: lstore          12
        //   738: lload           14
        //   740: lstore          10
        //   742: aload_0        
        //   743: aload_0        
        //   744: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   747: aload_0        
        //   748: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   751: invokespecial   com/ta/utdid2/c/a/c.a:(Landroid/content/SharedPreferences;Lcom/ta/utdid2/c/a/b;)V
        //   754: lload           8
        //   756: lstore          12
        //   758: lload           14
        //   760: lstore          10
        //   762: aload_0        
        //   763: aload_0        
        //   764: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   767: aload_3        
        //   768: iconst_0       
        //   769: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   772: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   775: lload           8
        //   777: lstore          12
        //   779: lload           14
        //   781: lstore          10
        //   783: goto            862
        //   786: lload           8
        //   788: lstore          12
        //   790: lload           14
        //   792: lstore          10
        //   794: iload           7
        //   796: ifne            862
        //   799: lload           8
        //   801: lstore          12
        //   803: lload           14
        //   805: lstore          10
        //   807: aload_0        
        //   808: aload_0        
        //   809: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   812: aload_0        
        //   813: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   816: invokespecial   com/ta/utdid2/c/a/c.a:(Landroid/content/SharedPreferences;Lcom/ta/utdid2/c/a/b;)V
        //   819: lload           8
        //   821: lstore          12
        //   823: lload           14
        //   825: lstore          10
        //   827: aload_0        
        //   828: aload_0        
        //   829: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/d;
        //   832: aload_3        
        //   833: iconst_0       
        //   834: invokevirtual   com/ta/utdid2/c/a/d.a:(Ljava/lang/String;I)Lcom/ta/utdid2/c/a/b;
        //   837: putfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   840: lload           8
        //   842: lstore          12
        //   844: lload           14
        //   846: lstore          10
        //   848: goto            862
        //   851: astore_1       
        //   852: goto            862
        //   855: lconst_0       
        //   856: lstore          10
        //   858: lload           8
        //   860: lstore          12
        //   862: lload           12
        //   864: lload           10
        //   866: lcmp           
        //   867: ifne            884
        //   870: lload           12
        //   872: lconst_0       
        //   873: lcmp           
        //   874: ifne            988
        //   877: lload           10
        //   879: lconst_0       
        //   880: lcmp           
        //   881: ifne            988
        //   884: invokestatic    java/lang/System.currentTimeMillis:()J
        //   887: lstore          8
        //   889: aload_0        
        //   890: getfield        com/ta/utdid2/c/a/c.j:Z
        //   893: istore          4
        //   895: iload           4
        //   897: ifeq            919
        //   900: iload           4
        //   902: ifeq            988
        //   905: lload           12
        //   907: lconst_0       
        //   908: lcmp           
        //   909: ifne            988
        //   912: lload           10
        //   914: lconst_0       
        //   915: lcmp           
        //   916: ifne            988
        //   919: aload_0        
        //   920: getfield        com/ta/utdid2/c/a/c.a:Landroid/content/SharedPreferences;
        //   923: astore_1       
        //   924: aload_1        
        //   925: ifnull          953
        //   928: aload_1        
        //   929: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   934: astore_1       
        //   935: aload_1        
        //   936: ldc             "t2"
        //   938: lload           8
        //   940: invokeinterface android/content/SharedPreferences$Editor.putLong:(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;
        //   945: pop            
        //   946: aload_1        
        //   947: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   952: pop            
        //   953: aload_0        
        //   954: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   957: ifnull          988
        //   960: aload_0        
        //   961: getfield        com/ta/utdid2/c/a/c.a:Lcom/ta/utdid2/c/a/b;
        //   964: invokeinterface com/ta/utdid2/c/a/b.a:()Lcom/ta/utdid2/c/a/b$a;
        //   969: astore_1       
        //   970: aload_1        
        //   971: ldc             "t2"
        //   973: lload           8
        //   975: invokeinterface com/ta/utdid2/c/a/b$a.a:(Ljava/lang/String;J)Lcom/ta/utdid2/c/a/b$a;
        //   980: pop            
        //   981: aload_1        
        //   982: invokeinterface com/ta/utdid2/c/a/b$a.commit:()Z
        //   987: pop            
        //   988: return         
        //   989: astore_1       
        //   990: goto            855
        //   993: astore_1       
        //   994: goto            988
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  132    137    144    151    Ljava/lang/Exception;
        //  268    291    989    993    Ljava/lang/Exception;
        //  316    328    851    855    Ljava/lang/Exception;
        //  336    349    851    855    Ljava/lang/Exception;
        //  373    385    851    855    Ljava/lang/Exception;
        //  393    403    851    855    Ljava/lang/Exception;
        //  435    447    851    855    Ljava/lang/Exception;
        //  455    468    851    855    Ljava/lang/Exception;
        //  487    501    851    855    Ljava/lang/Exception;
        //  509    523    851    855    Ljava/lang/Exception;
        //  550    562    851    855    Ljava/lang/Exception;
        //  570    583    851    855    Ljava/lang/Exception;
        //  614    626    851    855    Ljava/lang/Exception;
        //  634    644    851    855    Ljava/lang/Exception;
        //  681    693    851    855    Ljava/lang/Exception;
        //  701    711    851    855    Ljava/lang/Exception;
        //  742    754    851    855    Ljava/lang/Exception;
        //  762    775    851    855    Ljava/lang/Exception;
        //  807    819    851    855    Ljava/lang/Exception;
        //  827    840    851    855    Ljava/lang/Exception;
        //  953    988    993    997    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0953:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:535)
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
    
    private d a(final String s) {
        final File a = this.a(s);
        if (a != null) {
            return this.a = new d(a.getAbsolutePath());
        }
        return null;
    }
    
    private File a(final String s) {
        final File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            final File file = new File(String.format("%s%s%s", new Object[] { externalStorageDirectory.getAbsolutePath(), File.separator, s }));
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }
    
    private void a(final SharedPreferences sharedPreferences, final b b) {
        if (sharedPreferences != null && b != null) {
            final b.a a = b.a();
            if (a != null) {
                a.b();
                for (final Map$Entry map$Entry : sharedPreferences.getAll().entrySet()) {
                    final String s = (String)map$Entry.getKey();
                    final Object value = map$Entry.getValue();
                    if (value instanceof String) {
                        a.a(s, (String)value);
                    }
                    else if (value instanceof Integer) {
                        a.a(s, (int)value);
                    }
                    else if (value instanceof Long) {
                        a.a(s, (long)value);
                    }
                    else if (value instanceof Float) {
                        a.a(s, (float)value);
                    }
                    else {
                        if (!(value instanceof Boolean)) {
                            continue;
                        }
                        a.a(s, (boolean)value);
                    }
                }
                a.commit();
            }
        }
    }
    
    private void a(final b b, final SharedPreferences sharedPreferences) {
        if (b != null && sharedPreferences != null) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (final Map$Entry map$Entry : b.getAll().entrySet()) {
                    final String s = (String)map$Entry.getKey();
                    final Object value = map$Entry.getValue();
                    if (value instanceof String) {
                        edit.putString(s, (String)value);
                    }
                    else if (value instanceof Integer) {
                        edit.putInt(s, (int)value);
                    }
                    else if (value instanceof Long) {
                        edit.putLong(s, (long)value);
                    }
                    else if (value instanceof Float) {
                        edit.putFloat(s, (float)value);
                    }
                    else {
                        if (!(value instanceof Boolean)) {
                            continue;
                        }
                        edit.putBoolean(s, (boolean)value);
                    }
                }
                edit.commit();
            }
        }
    }
    
    private boolean b() {
        final b a = this.a;
        if (a != null) {
            final boolean a2 = a.a();
            if (!a2) {
                this.commit();
            }
            return a2;
        }
        return false;
    }
    
    private void c() {
        if (this.a == null) {
            final SharedPreferences a = this.a;
            if (a != null) {
                this.a = a.edit();
            }
        }
        if (this.i && this.a == null) {
            final b a2 = this.a;
            if (a2 != null) {
                this.a = a2.a();
            }
        }
        this.b();
    }
    
    public boolean commit() {
        final long currentTimeMillis = System.currentTimeMillis();
        final SharedPreferences$Editor a = this.a;
        int n = 0;
        Label_0059: {
            if (a != null) {
                if (!this.j && this.a != null) {
                    a.putLong("t", currentTimeMillis);
                }
                if (!this.a.commit()) {
                    n = 0;
                    break Label_0059;
                }
            }
            n = 1;
        }
        if (this.a != null) {
            final Context mContext = this.mContext;
            if (mContext != null) {
                this.a = mContext.getSharedPreferences(this.e, 0);
            }
        }
        String externalStorageState = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        int n2 = n;
        if (com.ta.utdid2.b.a.i.a(externalStorageState)) {
            return n2 != 0;
        }
        int n3 = n;
        if (externalStorageState.equals((Object)"mounted")) {
            if (this.a == null) {
                final d a2 = this.a(this.f);
                n3 = n;
                if (a2 != null) {
                    final b a3 = a2.a(this.e, 0);
                    this.a = a3;
                    if (!this.j) {
                        this.a(this.a, a3);
                    }
                    else {
                        this.a(a3, this.a);
                    }
                    this.a = this.a.a();
                    n3 = n;
                }
            }
            else {
                final b.a a4 = this.a;
                n3 = n;
                if (a4 != null) {
                    n3 = n;
                    if (!a4.commit()) {
                        n3 = 0;
                    }
                }
            }
        }
        if (!externalStorageState.equals((Object)"mounted")) {
            n2 = n3;
            if (!externalStorageState.equals((Object)"mounted_ro")) {
                return n2 != 0;
            }
            n2 = n3;
            if (this.a == null) {
                return n2 != 0;
            }
        }
        n2 = n3;
        try {
            if (this.a != null) {
                this.a = this.a.a(this.e, 0);
                n2 = n3;
            }
            return n2 != 0;
        }
        catch (final Exception ex2) {
            n2 = n3;
            return n2 != 0;
        }
    }
    
    public String getString(final String s) {
        this.b();
        final SharedPreferences a = this.a;
        if (a != null) {
            final String string = a.getString(s, "");
            if (!com.ta.utdid2.b.a.i.a(string)) {
                return string;
            }
        }
        final b a2 = this.a;
        if (a2 != null) {
            return a2.getString(s, "");
        }
        return "";
    }
    
    public void putString(final String s, final String s2) {
        if (!com.ta.utdid2.b.a.i.a(s) && !s.equals((Object)"t")) {
            this.c();
            final SharedPreferences$Editor a = this.a;
            if (a != null) {
                a.putString(s, s2);
            }
            final b.a a2 = this.a;
            if (a2 != null) {
                a2.a(s, s2);
            }
        }
    }
    
    public void remove(final String s) {
        if (!com.ta.utdid2.b.a.i.a(s) && !s.equals((Object)"t")) {
            this.c();
            final SharedPreferences$Editor a = this.a;
            if (a != null) {
                a.remove(s);
            }
            final b.a a2 = this.a;
            if (a2 != null) {
                a2.a(s);
            }
        }
    }
}
