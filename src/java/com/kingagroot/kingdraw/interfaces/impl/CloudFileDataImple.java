package com.kingagroot.kingdraw.interfaces.impl;

import com.kingagroot.component.ui.view.OperationState;
import org.xutils.ex.DbException;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import com.kingagroot.kingdraw.interfaces.CloudFileDataI;

public class CloudFileDataImple extends BaseDBImpl implements CloudFileDataI
{
    ExecutorService executorService;
    
    public CloudFileDataImple() {
        this.executorService = Executors.newFixedThreadPool(3);
    }
    
    public FolderFileModel findFileByFileName(final String s) {
        try {
            return (FolderFileModel)this.db.selector((Class)FolderFileModel.class).where(WhereBuilder.b("FileName", "=", (Object)s)).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getCopyFileName(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            9
        //     7: aload_1        
        //     8: areturn        
        //     9: aload_1        
        //    10: invokestatic    com/kingagroot/kingdraw/utils/DrawFileUtil.getFileNameNoExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    13: astore          5
        //    15: aload_1        
        //    16: invokestatic    com/kingagroot/kingdraw/utils/DrawFileUtil.getExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    19: astore_1       
        //    20: aload_0        
        //    21: getfield        com/kingagroot/kingdraw/interfaces/impl/CloudFileDataImple.db:Lorg/xutils/DbManager;
        //    24: ldc             Lcom/kingagroot/kingdraw/model/FolderFileModel;.class
        //    26: invokeinterface org/xutils/DbManager.selector:(Ljava/lang/Class;)Lorg/xutils/db/Selector;
        //    31: astore          6
        //    33: new             Ljava/lang/StringBuilder;
        //    36: astore          7
        //    38: aload           7
        //    40: invokespecial   java/lang/StringBuilder.<init>:()V
        //    43: aload           7
        //    45: aload           5
        //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    50: pop            
        //    51: aload           7
        //    53: ldc             "%"
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: pop            
        //    59: aload           6
        //    61: ldc             "FileName"
        //    63: ldc             "like"
        //    65: aload           7
        //    67: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    70: invokestatic    org/xutils/db/sqlite/WhereBuilder.b:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/xutils/db/sqlite/WhereBuilder;
        //    73: invokevirtual   org/xutils/db/Selector.where:(Lorg/xutils/db/sqlite/WhereBuilder;)Lorg/xutils/db/Selector;
        //    76: invokevirtual   org/xutils/db/Selector.findAll:()Ljava/util/List;
        //    79: astore          7
        //    81: aload           7
        //    83: ifnonnull       89
        //    86: aload           5
        //    88: areturn        
        //    89: new             Ljava/util/ArrayList;
        //    92: astore          6
        //    94: aload           6
        //    96: invokespecial   java/util/ArrayList.<init>:()V
        //    99: aload           7
        //   101: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   106: astore          7
        //   108: aload           7
        //   110: invokeinterface java/util/Iterator.hasNext:()Z
        //   115: istore          4
        //   117: iconst_1       
        //   118: istore_2       
        //   119: iload           4
        //   121: ifeq            223
        //   124: aload           7
        //   126: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   131: checkcast       Lcom/kingagroot/kingdraw/model/FolderFileModel;
        //   134: invokevirtual   com/kingagroot/kingdraw/model/FolderFileModel.getFileNameNoExtension:()Ljava/lang/String;
        //   137: aload           5
        //   139: invokevirtual   java/lang/String.length:()I
        //   142: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   145: astore          8
        //   147: aload           8
        //   149: ldc             "("
        //   151: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   154: istore_3       
        //   155: aload           8
        //   157: ldc             ")"
        //   159: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   162: istore_2       
        //   163: iload_3        
        //   164: iconst_m1      
        //   165: if_icmpeq       108
        //   168: iload_2        
        //   169: iconst_m1      
        //   170: if_icmpeq       108
        //   173: iload_3        
        //   174: iload_2        
        //   175: if_icmpge       108
        //   178: aload           8
        //   180: aload           8
        //   182: ldc             "("
        //   184: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   187: iconst_1       
        //   188: iadd           
        //   189: aload           8
        //   191: ldc             ")"
        //   193: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   196: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   199: astore          8
        //   201: aload           6
        //   203: aload           8
        //   205: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //   208: invokevirtual   java/lang/Integer.intValue:()I
        //   211: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   214: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   219: pop            
        //   220: goto            108
        //   223: aload           6
        //   225: invokestatic    java/util/Collections.sort:(Ljava/util/List;)V
        //   228: aload           6
        //   230: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   235: astore          6
        //   237: aload           6
        //   239: invokeinterface java/util/Iterator.hasNext:()Z
        //   244: ifeq            280
        //   247: aload           6
        //   249: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   254: checkcast       Ljava/lang/Integer;
        //   257: invokevirtual   java/lang/Integer.intValue:()I
        //   260: istore_3       
        //   261: iload_3        
        //   262: iload_2        
        //   263: if_icmple       269
        //   266: goto            280
        //   269: iload_3        
        //   270: iload_2        
        //   271: if_icmpne       237
        //   274: iinc            2, 1
        //   277: goto            237
        //   280: new             Ljava/lang/StringBuilder;
        //   283: astore          6
        //   285: aload           6
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: aload           6
        //   292: aload           5
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: pop            
        //   298: aload           6
        //   300: ldc             "("
        //   302: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   305: pop            
        //   306: aload           6
        //   308: iload_2        
        //   309: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   312: pop            
        //   313: aload           6
        //   315: ldc             ")"
        //   317: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   320: pop            
        //   321: aload           6
        //   323: aload_1        
        //   324: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: pop            
        //   328: aload           6
        //   330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   333: astore_1       
        //   334: aload_1        
        //   335: areturn        
        //   336: astore_1       
        //   337: aload_1        
        //   338: invokevirtual   org/xutils/ex/DbException.printStackTrace:()V
        //   341: aconst_null    
        //   342: areturn        
        //   343: astore          8
        //   345: goto            108
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                       
        //  -----  -----  -----  -----  ---------------------------
        //  0      7      336    343    Lorg/xutils/ex/DbException;
        //  9      81     336    343    Lorg/xutils/ex/DbException;
        //  89     108    336    343    Lorg/xutils/ex/DbException;
        //  108    117    336    343    Lorg/xutils/ex/DbException;
        //  124    163    336    343    Lorg/xutils/ex/DbException;
        //  178    201    336    343    Lorg/xutils/ex/DbException;
        //  201    220    343    348    Ljava/lang/Exception;
        //  201    220    336    343    Lorg/xutils/ex/DbException;
        //  223    237    336    343    Lorg/xutils/ex/DbException;
        //  237    261    336    343    Lorg/xutils/ex/DbException;
        //  280    334    336    343    Lorg/xutils/ex/DbException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0223:
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
    
    public OperationState save(final FolderFileModel folderFileModel) {
        new CloudFileDataImple.CloudFileDataImple$SynSaveThread(this).start(folderFileModel, this.executorService);
        return this.success();
    }
}
