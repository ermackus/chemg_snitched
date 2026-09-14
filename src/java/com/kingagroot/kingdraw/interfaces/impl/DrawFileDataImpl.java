package com.kingagroot.kingdraw.interfaces.impl;

import com.kingagroot.kingdraw.interfaces.OnSearchResultListener;
import org.xutils.db.Selector;
import java.io.File;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.config.FileConfig;
import java.util.UUID;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.component.ui.view.OperationState;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.List;
import com.kingagroot.kingdraw.model.GSearchModel;
import org.xutils.ex.DbException;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.kingdraw.utils.FileTagUtils;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.model.FileType;
import com.kingagroot.kingdraw.interfaces.FolderFileModelSearchDBI;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;

public class DrawFileDataImpl extends BaseDBImpl implements DrawFileDataI
{
    FolderFileModelSearchDBI folderFileModelSearchDBI;
    
    public DrawFileDataImpl() {
        this.folderFileModelSearchDBI = (FolderFileModelSearchDBI)new FolderFileModelSearchImpl(false);
    }
    
    private String getFullName(final String s, final FileType fileType) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(fileType.extension);
        return sb.toString();
    }
    
    public boolean addTag(final String s, String tagFormat) {
        try {
            String tags;
            if ((tags = ((FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s)).getTags()) == null) {
                tags = "";
            }
            tagFormat = FileTagUtils.tagFormat(tagFormat);
            if (tags.contains((CharSequence)tagFormat)) {
                return false;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(tags);
            sb.append(tagFormat);
            this.db.update((Class)FolderFileModel.class, WhereBuilder.b("Id", "=", (Object)s), new KeyValue[] { new KeyValue("Tags", (Object)sb.toString()) });
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<FolderFileModel> checkAll(final GSearchModel gSearchModel) {
        try {
            final WhereBuilder b = WhereBuilder.b();
            String key;
            if (TextUtils.isEmpty((CharSequence)gSearchModel.getKey())) {
                key = "";
            }
            else {
                key = gSearchModel.getKey();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(key);
            sb.append("%");
            b.and("FileName", "LIKE", (Object)sb.toString());
            if (gSearchModel.getTags() != null) {
                for (final String s : gSearchModel.getTags()) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("%");
                    sb2.append(FileTagUtils.tagFormat(s));
                    sb2.append("%");
                    b.and("Tags", "LIKE", (Object)sb2.toString());
                }
            }
            return (List<FolderFileModel>)this.db.selector((Class)FolderFileModel.class).where(b).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public OperationState checkFileNameAvailable(final String s) {
        try {
            if (this.db.selector((Class)FolderFileModel.class).where("FileName", "=", (Object)s).count() > 0L) {
                return this.error(MApplication.getInstance().getString(2131820847));
            }
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error(MApplication.getInstance().getString(2131820845));
        }
    }
    
    public OperationState copyFile(final FolderFileModel data, String picPath) {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            data.setId(UUID.randomUUID().toString());
            final StringBuilder sb = new StringBuilder();
            sb.append(picPath);
            sb.append(data.getFileExtension());
            final String string = sb.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(FileConfig.DRAW_FILE_PATH);
            sb2.append(string);
            final String string2 = sb2.toString();
            FileUtil.copyFile(data.getFilePath(), string2);
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(picPath);
            sb3.append(DrawFileUtil.getExtension(data.getPicPath()));
            picPath = sb3.toString();
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(FileConfig.DRAW_FILE_PIC_PATH);
            sb4.append(picPath);
            picPath = sb4.toString();
            FileUtil.copyFile(data.getPicPath(), picPath);
            data.setFileName(string);
            data.setFilePath(string2);
            data.setPicPath(picPath);
            data.setCreateTime(currentTimeMillis);
            data.setModifyTime(currentTimeMillis);
            this.db.save((Object)data);
            final OperationState success = this.success(MApplication.getInstance().getString(2131820836));
            success.data = data;
            return success;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return this.error(MApplication.getInstance().getString(2131820835));
        }
    }
    
    public OperationState copyFile(final String s) {
        try {
            final FolderFileModel folderFileModel = (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
            if (folderFileModel != null && new File(folderFileModel.getFilePath()).exists()) {
                return this.copyFile(folderFileModel, this.getCopyFileNameNoExtension(folderFileModel.getFullFileName()));
            }
            return this.error(MApplication.getInstance().getString(2131820850));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return this.error(MApplication.getInstance().getString(2131820835));
        }
    }
    
    public OperationState deleteFile(final String s) {
        try {
            final FolderFileModel folderFileModel = (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
            if (folderFileModel == null) {
                return this.error(MApplication.getInstance().getString(2131820850));
            }
            FileUtil.deleteFile(folderFileModel.getFilePath());
            FileUtil.deleteFile(folderFileModel.getPicPath());
            this.db.deleteById((Class)FolderFileModel.class, (Object)s);
            return this.success(MApplication.getInstance().getString(2131820837));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public FolderFileModel findDrawFile(final String s) {
        try {
            return (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public FolderFileModel findDrawFileById(final String s) {
        try {
            return (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<FolderFileModel> findDrawFiles(final String s, final int n) {
        try {
            final Selector selector = this.db.selector((Class)FolderFileModel.class);
            final StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(s);
            sb.append("%");
            return (List<FolderFileModel>)selector.where("FileName", "LIKE", (Object)sb.toString()).offset(n * 20).limit(20).orderBy("ModifyTime", true).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void findDrawFiles(final GSearchModel gSearchModel, final int n, final OnSearchResultListener onSearchResultListener) {
        this.folderFileModelSearchDBI.findDrawFiles(gSearchModel, n, onSearchResultListener);
    }
    
    public List<FolderFileModel> findDrawFilesWithOutIds(final String s, final int n, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM FolderFileModel");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(" WHERE FileName LIKE '%");
        sb2.append(s);
        sb2.append("%'");
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(" AND Id NOT IN (");
        sb3.append(s2);
        sb3.append(")");
        sb.append(sb3.toString());
        sb.append(" order by ModifyTime desc");
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(" LIMIT 20 OFFSET ");
        sb4.append(n * 20);
        sb.append(sb4.toString());
        try {
            return (List<FolderFileModel>)this.findAllBySql((Class)FolderFileModel.class, sb.toString());
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
        //    13: astore          6
        //    15: aload_1        
        //    16: invokestatic    com/kingagroot/kingdraw/utils/DrawFileUtil.getExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    19: astore          7
        //    21: aload_0        
        //    22: getfield        com/kingagroot/kingdraw/interfaces/impl/DrawFileDataImpl.db:Lorg/xutils/DbManager;
        //    25: ldc             Lcom/kingagroot/kingdraw/model/FolderFileModel;.class
        //    27: invokeinterface org/xutils/DbManager.selector:(Ljava/lang/Class;)Lorg/xutils/db/Selector;
        //    32: astore          8
        //    34: new             Ljava/lang/StringBuilder;
        //    37: astore          5
        //    39: aload           5
        //    41: invokespecial   java/lang/StringBuilder.<init>:()V
        //    44: aload           5
        //    46: aload           6
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: pop            
        //    52: aload           5
        //    54: ldc             "%"
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: pop            
        //    60: aload           8
        //    62: ldc             "FileName"
        //    64: ldc_w           "like"
        //    67: aload           5
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: invokestatic    org/xutils/db/sqlite/WhereBuilder.b:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/xutils/db/sqlite/WhereBuilder;
        //    75: invokevirtual   org/xutils/db/Selector.where:(Lorg/xutils/db/sqlite/WhereBuilder;)Lorg/xutils/db/Selector;
        //    78: invokevirtual   org/xutils/db/Selector.findAll:()Ljava/util/List;
        //    81: astore          8
        //    83: aload_1        
        //    84: astore          5
        //    86: aload           8
        //    88: ifnull          344
        //    91: aload           8
        //    93: invokeinterface java/util/List.isEmpty:()Z
        //    98: ifeq            107
        //   101: aload_1        
        //   102: astore          5
        //   104: goto            344
        //   107: new             Ljava/util/ArrayList;
        //   110: astore_1       
        //   111: aload_1        
        //   112: invokespecial   java/util/ArrayList.<init>:()V
        //   115: aload           8
        //   117: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   122: astore          5
        //   124: aload           5
        //   126: invokeinterface java/util/Iterator.hasNext:()Z
        //   131: istore          4
        //   133: iconst_1       
        //   134: istore_2       
        //   135: iload           4
        //   137: ifeq            242
        //   140: aload           5
        //   142: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   147: checkcast       Lcom/kingagroot/kingdraw/model/FolderFileModel;
        //   150: invokevirtual   com/kingagroot/kingdraw/model/FolderFileModel.getFileNameNoExtension:()Ljava/lang/String;
        //   153: aload           6
        //   155: invokevirtual   java/lang/String.length:()I
        //   158: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   161: astore          8
        //   163: aload           8
        //   165: ldc_w           "("
        //   168: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   171: istore_2       
        //   172: aload           8
        //   174: ldc_w           ")"
        //   177: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   180: istore_3       
        //   181: iload_2        
        //   182: iconst_m1      
        //   183: if_icmpeq       124
        //   186: iload_3        
        //   187: iconst_m1      
        //   188: if_icmpeq       124
        //   191: iload_2        
        //   192: iload_3        
        //   193: if_icmpge       124
        //   196: aload           8
        //   198: aload           8
        //   200: ldc_w           "("
        //   203: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   206: iconst_1       
        //   207: iadd           
        //   208: aload           8
        //   210: ldc_w           ")"
        //   213: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   216: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   219: astore          8
        //   221: aload_1        
        //   222: aload           8
        //   224: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //   227: invokevirtual   java/lang/Integer.intValue:()I
        //   230: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   233: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   238: pop            
        //   239: goto            124
        //   242: aload_1        
        //   243: invokestatic    java/util/Collections.sort:(Ljava/util/List;)V
        //   246: aload_1        
        //   247: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   252: astore_1       
        //   253: aload_1        
        //   254: invokeinterface java/util/Iterator.hasNext:()Z
        //   259: ifeq            294
        //   262: aload_1        
        //   263: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   268: checkcast       Ljava/lang/Integer;
        //   271: invokevirtual   java/lang/Integer.intValue:()I
        //   274: istore_3       
        //   275: iload_3        
        //   276: iload_2        
        //   277: if_icmple       283
        //   280: goto            294
        //   283: iload_3        
        //   284: iload_2        
        //   285: if_icmpne       253
        //   288: iinc            2, 1
        //   291: goto            253
        //   294: new             Ljava/lang/StringBuilder;
        //   297: astore_1       
        //   298: aload_1        
        //   299: invokespecial   java/lang/StringBuilder.<init>:()V
        //   302: aload_1        
        //   303: aload           6
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: pop            
        //   309: aload_1        
        //   310: ldc_w           "("
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: pop            
        //   317: aload_1        
        //   318: iload_2        
        //   319: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   322: pop            
        //   323: aload_1        
        //   324: ldc_w           ")"
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: pop            
        //   331: aload_1        
        //   332: aload           7
        //   334: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   337: pop            
        //   338: aload_1        
        //   339: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   342: astore          5
        //   344: aload           5
        //   346: areturn        
        //   347: astore_1       
        //   348: aload_1        
        //   349: invokevirtual   org/xutils/ex/DbException.printStackTrace:()V
        //   352: aconst_null    
        //   353: areturn        
        //   354: astore          8
        //   356: goto            124
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                       
        //  -----  -----  -----  -----  ---------------------------
        //  0      7      347    354    Lorg/xutils/ex/DbException;
        //  9      83     347    354    Lorg/xutils/ex/DbException;
        //  91     101    347    354    Lorg/xutils/ex/DbException;
        //  107    124    347    354    Lorg/xutils/ex/DbException;
        //  124    133    347    354    Lorg/xutils/ex/DbException;
        //  140    181    347    354    Lorg/xutils/ex/DbException;
        //  196    221    347    354    Lorg/xutils/ex/DbException;
        //  221    239    354    359    Ljava/lang/Exception;
        //  221    239    347    354    Lorg/xutils/ex/DbException;
        //  242    253    347    354    Lorg/xutils/ex/DbException;
        //  253    275    347    354    Lorg/xutils/ex/DbException;
        //  294    344    347    354    Lorg/xutils/ex/DbException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0242:
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
    
    public String getCopyFileNameNoExtension(final String s) {
        return DrawFileUtil.getFileNameNoExtension(this.getCopyFileName(s));
    }
    
    public long getDrawFileAmount() {
        try {
            return this.db.selector((Class)FolderFileModel.class).count();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    public long getDrawFileAmount(final GSearchModel gSearchModel) {
        try {
            final WhereBuilder b = WhereBuilder.b();
            String key;
            if (TextUtils.isEmpty((CharSequence)gSearchModel.getKey())) {
                key = "";
            }
            else {
                key = gSearchModel.getKey();
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(key);
            sb.append("%");
            b.and("FileName", "LIKE", (Object)sb.toString());
            if (gSearchModel.getTags() != null) {
                for (final String s : gSearchModel.getTags()) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("%");
                    sb2.append(FileTagUtils.tagFormat(s));
                    sb2.append("%");
                    b.and("Tags", "LIKE", (Object)sb2.toString());
                }
            }
            return this.db.selector((Class)FolderFileModel.class).where(b).count();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    public List<FolderFileModel> getNoSmilesFiles() {
        try {
            return (List<FolderFileModel>)this.db.selector((Class)FolderFileModel.class).where("FileSmiles", "=", (Object)"").findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean modifyTagByOldTag(final String s, String s2, String tagFormat) {
        try {
            String tags;
            if ((tags = ((FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s)).getTags()) == null) {
                tags = "";
            }
            tagFormat = FileTagUtils.tagFormat(tagFormat);
            s2 = FileTagUtils.tagFormat(s2);
            if (tags.contains((CharSequence)tagFormat)) {
                s2 = tags.replace((CharSequence)tagFormat, (CharSequence)s2);
            }
            else {
                final StringBuilder sb = new StringBuilder();
                sb.append(tags);
                sb.append(s2);
                s2 = sb.toString();
            }
            this.db.update((Class)FolderFileModel.class, WhereBuilder.b("Id", "=", (Object)s), new KeyValue[] { new KeyValue("Tags", (Object)s2) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean removeTag(String s, String tagFormat) {
        try {
            final FolderFileModel folderFileModel = (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
            final String tags = folderFileModel.getTags();
            tagFormat = FileTagUtils.tagFormat(tagFormat);
            s = tags;
            if (tags == null) {
                s = "";
            }
            if (!s.contains((CharSequence)tagFormat)) {
                return false;
            }
            folderFileModel.setTags(s.replace((CharSequence)tagFormat, (CharSequence)""));
            this.db.update((Object)folderFileModel, new String[] { "Tags" });
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public OperationState rename(final String s, String fullName, final FileType fileType) {
        try {
            final FolderFileModel data = (FolderFileModel)this.db.findById((Class)FolderFileModel.class, (Object)s);
            if (data == null) {
                return this.error(MApplication.getInstance().getString(2131820850));
            }
            final Selector selector = this.db.selector((Class)FolderFileModel.class);
            final StringBuilder sb = new StringBuilder();
            sb.append(fullName);
            sb.append(fileType.extension);
            if (selector.where("FileName", "=", (Object)sb.toString()).and("Id", "!=", (Object)s).count() > 0L) {
                return this.error(MApplication.getInstance().getString(2131820847));
            }
            fullName = this.getFullName(fullName, fileType);
            final File file = new File(data.getFilePath());
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(FileConfig.DRAW_FILE_PATH);
            sb2.append(File.separator);
            sb2.append(fullName);
            final String string = sb2.toString();
            if (file.exists()) {
                file.renameTo(new File(string));
            }
            data.setFileName(fullName);
            data.setFilePath(string);
            this.db.update((Object)data, new String[] { "FileName", "FilePath" });
            final OperationState success = this.success();
            success.setData((Object)data);
            return success;
        }
        catch (final DbException ex) {
            return this.error();
        }
    }
    
    public OperationState saveFolderFileModel(final FolderFileModel folderFileModel) {
        try {
            this.db.saveBindingId((Object)folderFileModel);
            return this.success(MApplication.getInstance().getString(2131820858));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public boolean setTags(final String s, final List<String> list) {
        try {
            this.db.update((Class)FolderFileModel.class, WhereBuilder.b("Id", "=", (Object)s), new KeyValue[] { new KeyValue("Tags", (Object)FileTagUtils.tagsFormat((List)list)) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public OperationState updataFolderFileModel(final FolderFileModel folderFileModel) {
        try {
            this.db.saveOrUpdate((Object)folderFileModel);
            return this.success(MApplication.getInstance().getString(2131820858));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public boolean updataModel(final FolderFileModel folderFileModel) {
        try {
            this.db.update((Object)folderFileModel, new String[] { "FileSmiles", "Tags" });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
