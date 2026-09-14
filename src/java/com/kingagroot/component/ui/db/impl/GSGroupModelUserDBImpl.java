package com.kingagroot.component.ui.db.impl;

import android.graphics.Bitmap;
import com.kingagroot.kingdraw.core.FileWriter$Builder;
import java.io.IOException;
import android.util.Base64;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import com.kingagroot.kingdraw.core.image.DrawOption;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.SupThumImageOption;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.core.FileWriter;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import java.util.List;
import com.kingagroot.component.ui.R$string;
import org.xutils.ex.DbException;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.x;
import org.xutils.DbManager$DbUpgradeListener;
import java.io.File;
import com.kingagroot.component.ui.UIComponentHelper;
import org.xutils.DbManager$DaoConfig;
import com.kingagroot.component.ui.db.GSGroupModelDBI;

public class GSGroupModelUserDBImpl extends GSGroupModeBaseDB implements GSGroupModelDBI
{
    public static final String KINGSUP_DB_NAME = "kingDrawSUP_Coustom.db";
    private static final int KINGSUP_VERSION_DB = 3;
    
    public GSGroupModelUserDBImpl() {
        this.init("kingDrawSUP_Coustom.db", 3);
    }
    
    private void init(final String dbName, final int dbVersion) {
        this.db = x.getDb(new DbManager$DaoConfig().setDbName(dbName).setDbDir(new File(UIComponentHelper.getDbFilePath())).setDbVersion(dbVersion).setDbUpgradeListener((DbManager$DbUpgradeListener)new GSGroupModelUserDBImpl$1(this)));
    }
    
    public OperationState addCollection(final int n, final boolean b) {
        try {
            this.db.update((Class)GSGroupModel.class, WhereBuilder.b("rowid", "=", (Object)String.valueOf(n)), new KeyValue[] { new KeyValue("isCollection", (Object)"1") });
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public OperationState checkName(final String s) {
        try {
            if (this.db.selector((Class)GSGroupModel.class).where("name", "=", (Object)s).count() > 0L) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_name_exist_hint));
            }
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error(UIComponentHelper.getInstance().getString(R$string.file_name_check_error));
        }
    }
    
    public OperationState deleteGSGroupModel(final int n, final boolean b) {
        try {
            this.db.getDatabase().disableWriteAheadLogging();
            this.db.deleteById((Class)GSGroupModel.class, (Object)n);
            this.db.getDatabase().enableWriteAheadLogging();
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public List<GSGroupModel> findAll() {
        try {
            return (List<GSGroupModel>)this.findAllBySql((Class)GSGroupModel.class, "SELECT rowid, isCustomize, smiles, content, isCollection, canEdit, key, name,nameHtml, imageData, base64String, isRHave, isDelete FROM \"GSGroupModel\" WHERE \"isDelete\" = '0' ORDER BY \"isCollection\" ASC");
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GSGroupModel> getCollectionSupModel() {
        try {
            return (List<GSGroupModel>)this.findAllBySql((Class)GSGroupModel.class, "SELECT rowid, isCustomize, smiles, content, isCollection, canEdit, key, name,nameHtml, imageData, base64String, isRHave, isDelete FROM \"GSGroupModel\" WHERE \"isCollection\" = '1' AND \"isDelete\" = '0'");
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getKdxFile(final int n, final boolean b) {
        return this.getKdxFile(n);
    }
    
    public OperationState reName(final int n, final String name, final String nameHtml, final boolean b) {
        try {
            final GSGroupModel gsGroupModel = (GSGroupModel)this.db.findById((Class)GSGroupModel.class, (Object)n);
            if (gsGroupModel == null) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_not_exist_hint));
            }
            if (this.db.selector((Class)GSGroupModel.class).where("name", "=", (Object)name).and("rowid", "!=", (Object)n).count() > 0L) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_name_exist_hint));
            }
            gsGroupModel.setName(name);
            gsGroupModel.setNameHtml(nameHtml);
            this.db.update((Object)gsGroupModel, new String[] { "name", "nameHtml" });
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public OperationState removeCollection(final int n, final boolean b) {
        try {
            this.db.update((Class)GSGroupModel.class, WhereBuilder.b("rowid", "=", (Object)String.valueOf(n)), new KeyValue[] { new KeyValue("isCollection", (Object)"0") });
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public OperationState saveFile(final String name, final String nameHtml, final KingDrawView drawWithView) {
        try {
            final int haveR = drawWithView.haveR();
            if (haveR == 2 || !drawWithView.haveStruct()) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.palette_save_error_hint));
            }
            if (TextUtils.isEmpty((CharSequence)name)) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_name_empty_hint));
            }
            final int n = 1;
            final boolean b = haveR == 1;
            if (this.db.selector((Class)GSGroupModel.class).where("name", "=", (Object)name).count() > 0L) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_group_exists_hint));
            }
            final FileWriter$Builder setNeedSmiles = FileWriter.getBuilder().withView(drawWithView).setNeedSmiles(true);
            final String fileCachePath = UIComponentHelper.getFileCachePath();
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("");
            final String string = sb.toString();
            if (!setNeedSmiles.writeToFile(fileCachePath, string, ProtocolTypeEnum.KDX)) {
                return this.error();
            }
            final SupThumImageOption drawOption = new SupThumImageOption();
            drawOption.setMark(false);
            final String smiles = setNeedSmiles.getSmiles();
            final ImageDrawBuilder setDrawOption = new ImageDrawBuilder().setBackgroundColor(0).setDrawWithView(drawWithView).setProofStatus(false).setDrawOption((DrawOption)drawOption);
            final Bitmap drawBitmap = setDrawOption.getDrawBitmap();
            setDrawOption.onDestroy();
            final String bitmaptoString = GBitmapUtils.bitmaptoString(drawBitmap, 100);
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(fileCachePath);
            sb2.append(File.separator);
            sb2.append(string);
            sb2.append(ProtocolTypeEnum.KDX.extension);
            final File file = new File(sb2.toString());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream)new FileInputStream(file));
            final byte[] array = new byte[1024];
            while (true) {
                final int read = bufferedInputStream.read(array, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
            }
            bufferedInputStream.close();
            final String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            final GSGroupModel data = new GSGroupModel();
            data.setName(name);
            data.setCustomize(true);
            int isRHave;
            if (b) {
                isRHave = n;
            }
            else {
                isRHave = 0;
            }
            data.setIsRHave(isRHave);
            data.setSmiles(smiles);
            data.setKdxFileBytes(encodeToString);
            data.setBase64String(bitmaptoString);
            data.setNameHtml(nameHtml);
            this.db.getDatabase().disableWriteAheadLogging();
            this.db.saveBindingId((Object)data);
            this.db.getDatabase().enableWriteAheadLogging();
            data.setKdxFileBytes("");
            file.delete();
            byteArrayOutputStream.close();
            final OperationState success = this.success(UIComponentHelper.getInstance().getString(R$string.file_save_success_hint));
            success.data = data;
            return success;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public List<GSGroupModel> searchGroup(final String s) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT rowid, isCustomize, smiles, content, isCollection, canEdit, key, name, nameHtml,imageData, base64String, isRHave, isDelete FROM \"GSGroupModel\" WHERE \"isDelete\" = '0' AND (\"name\" like '%");
            sb.append(s);
            sb.append("%' OR \"smiles\" like '%");
            sb.append(s);
            sb.append("%') ORDER BY \"isCollection\" ASC");
            return (List<GSGroupModel>)this.findAllBySql((Class)GSGroupModel.class, sb.toString());
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public OperationState updateFile(int isRHave, final boolean b, final KingDrawView drawWithView) {
        try {
            final int haveR = drawWithView.haveR();
            if (drawWithView.isEmpty() || haveR == 2 || !drawWithView.haveStruct()) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.palette_save_error_hint));
            }
            final GSGroupModel gsGroupModel = (GSGroupModel)this.db.findById((Class)GSGroupModel.class, (Object)isRHave);
            if (gsGroupModel == null) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_not_exist_hint));
            }
            final int n = 1;
            if (haveR == 1) {
                isRHave = 1;
            }
            else {
                isRHave = 0;
            }
            final FileWriter$Builder withView = FileWriter.getBuilder().setNeedSmiles(true).withView(drawWithView);
            final String fileCachePath = UIComponentHelper.getFileCachePath();
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("");
            final String string = sb.toString();
            if (!withView.writeToFile(fileCachePath, string, ProtocolTypeEnum.KDX)) {
                return this.error();
            }
            final SupThumImageOption drawOption = new SupThumImageOption();
            drawOption.setMark(false);
            final String smiles = withView.getSmiles();
            final ImageDrawBuilder setDrawOption = new ImageDrawBuilder().setBackgroundColor(0).setDrawWithView(drawWithView).setProofStatus(false).setDrawOption((DrawOption)drawOption);
            final Bitmap drawBitmap = setDrawOption.getDrawBitmap();
            setDrawOption.onDestroy();
            final String bitmaptoString = GBitmapUtils.bitmaptoString(drawBitmap, 100);
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(fileCachePath);
            sb2.append(File.separator);
            sb2.append(string);
            sb2.append(ProtocolTypeEnum.KDX.extension);
            final File file = new File(sb2.toString());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream)new FileInputStream(file));
            final byte[] array = new byte[1024];
            while (true) {
                final int read = bufferedInputStream.read(array, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
            }
            bufferedInputStream.close();
            gsGroupModel.setKdxFileBytes(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
            gsGroupModel.setContent("");
            if (isRHave != 0) {
                isRHave = n;
            }
            else {
                isRHave = 0;
            }
            gsGroupModel.setIsRHave(isRHave);
            gsGroupModel.setSmiles(smiles);
            gsGroupModel.setBase64String(bitmaptoString);
            final OperationState updateFile = this.updateFile(gsGroupModel, b);
            gsGroupModel.setKdxFileBytes("");
            file.delete();
            byteArrayOutputStream.close();
            return updateFile;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public OperationState updateFile(final GSGroupModel gsGroupModel, final boolean b) {
        try {
            this.db.getDatabase().disableWriteAheadLogging();
            this.db.saveOrUpdate((Object)gsGroupModel);
            this.db.getDatabase().enableWriteAheadLogging();
            return this.success(UIComponentHelper.getInstance().getString(R$string.file_save_success_hint));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
}
