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
import java.util.Iterator;
import org.xutils.db.table.TableEntity;
import org.xutils.db.sqlite.SqlInfoBuilder;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import java.util.Comparator;
import java.util.Collections;
import com.kingagroot.component.ui.widget.sup.GSGroupModelComparator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.x;
import org.xutils.DbManager$DbUpgradeListener;
import java.io.File;
import org.xutils.DbManager$DaoConfig;
import org.xutils.ex.DbException;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.component.ui.db.GSGroupModelDBI;

public class GSGroupModelDBImpl extends GSGroupModeBaseDB implements GSGroupModelDBI
{
    public static final String KINGDRAWSUP_DB_NAME = "kingDrawSUP.db";
    private static final int KINGDRAWSUP_VERSION_DB = 7;
    GSGroupModelDBI gsGroupModelUserDBI;
    
    public GSGroupModelDBImpl() {
        this.init("kingDrawSUP.db", 7);
        this.gsGroupModelUserDBI = (GSGroupModelDBI)new GSGroupModelUserDBImpl();
    }
    
    private OperationState checkNameSelf(final String s) {
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
    
    private void init(final String dbName, final int dbVersion) {
        this.db = x.getDb(new DbManager$DaoConfig().setDbName(dbName).setDbDir(new File(UIComponentHelper.getDbFilePath())).setDbVersion(dbVersion).setDbUpgradeListener((DbManager$DbUpgradeListener)new GSGroupModelDBImpl$1(this)));
    }
    
    public OperationState addCollection(final int n, final boolean b) {
        Label_0016: {
            if (!b) {
                break Label_0016;
            }
            try {
                return this.gsGroupModelUserDBI.addCollection(n, b);
                this.db.update((Class)GSGroupModel.class, WhereBuilder.b("rowid", "=", (Object)String.valueOf(n)), new KeyValue[] { new KeyValue("isCollection", (Object)"1") });
                return this.success();
            }
            catch (final DbException ex) {
                ex.printStackTrace();
                return this.error();
            }
        }
    }
    
    public OperationState checkName(final String s) {
        final OperationState checkName = this.gsGroupModelUserDBI.checkName(s);
        if (!checkName.isSuccess) {
            return checkName;
        }
        return this.checkNameSelf(s);
    }
    
    public OperationState deleteGSGroupModel(final int n, final boolean b) {
        if (b) {
            return this.gsGroupModelUserDBI.deleteGSGroupModel(n, b);
        }
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
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("rowid, smiles, content, isCollection, canEdit, key, name,nameHtml, imageData, base64String, isRHave, isDelete");
            sb.append(" FROM \"GSGroupModel\"");
            sb.append(" WHERE \"isDelete\" = '0' ORDER BY \"isCollection\" ASC");
            final ArrayList list = new ArrayList();
            final List all = this.gsGroupModelUserDBI.findAll();
            final List allBySql = this.findAllBySql((Class)GSGroupModel.class, sb.toString());
            if (all != null) {
                ((List)list).addAll((Collection)all);
            }
            if (allBySql != null) {
                ((List)list).addAll((Collection)allBySql);
            }
            Collections.sort((List)list, (Comparator)new GSGroupModelComparator());
            return (List<GSGroupModel>)list;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GSGroupModel> getCollectionSupModel() {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("rowid, smiles, content, isCollection, canEdit, key, name,nameHtml, imageData, base64String, isRHave, isDelete");
            sb.append(" FROM \"GSGroupModel\"");
            sb.append(" WHERE \"isCollection\" = '1' AND \"isDelete\" = '0'");
            final ArrayList list = new ArrayList();
            final List allBySql = this.findAllBySql((Class)GSGroupModel.class, sb.toString());
            final List collectionSupModel = this.gsGroupModelUserDBI.getCollectionSupModel();
            if (collectionSupModel != null) {
                ((List)list).addAll((Collection)collectionSupModel);
            }
            if (allBySql != null) {
                ((List)list).addAll((Collection)allBySql);
            }
            return (List<GSGroupModel>)list;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getKdxFile(final int n, final boolean b) {
        if (b) {
            return this.gsGroupModelUserDBI.getKdxFile(n, b);
        }
        return this.getKdxFile(n);
    }
    
    public OperationState reName(final int n, final String name, final String nameHtml, final boolean b) {
        if (b) {
            final OperationState checkNameSelf = this.checkNameSelf(name);
            if (!checkNameSelf.isSuccess) {
                return checkNameSelf;
            }
            return this.gsGroupModelUserDBI.reName(n, name, nameHtml, b);
        }
        else {
            try {
                final OperationState checkName = this.gsGroupModelUserDBI.checkName(name);
                if (!checkName.isSuccess) {
                    return checkName;
                }
                final GSGroupModel gsGroupModel = (GSGroupModel)this.db.findById((Class)GSGroupModel.class, (Object)n);
                if (gsGroupModel == null) {
                    return this.error(UIComponentHelper.getInstance().getString(R$string.sup_not_exist_hint));
                }
                if (this.db.selector((Class)GSGroupModel.class).where("name", "=", (Object)name).and("rowid", "!=", (Object)String.valueOf(n)).count() > 0L) {
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
    }
    
    public OperationState removeCollection(final int n, final boolean b) {
        Label_0016: {
            if (!b) {
                break Label_0016;
            }
            try {
                return this.gsGroupModelUserDBI.removeCollection(n, b);
                this.db.update((Class)GSGroupModel.class, WhereBuilder.b("rowid", "=", (Object)String.valueOf(n)), new KeyValue[] { new KeyValue("isCollection", (Object)"0") });
                return this.success();
            }
            catch (final DbException ex) {
                ex.printStackTrace();
                return this.error();
            }
        }
    }
    
    public OperationState saveFile(final String s, final String s2, final KingDrawView kingDrawView) {
        try {
            if (kingDrawView.isEmpty() || !kingDrawView.haveStruct()) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.palette_save_error_hint));
            }
            if (this.db.selector((Class)GSGroupModel.class).where("name", "=", (Object)s).count() > 0L) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_group_exists_hint));
            }
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        return this.gsGroupModelUserDBI.saveFile(s, s2, kingDrawView);
    }
    
    public List<GSGroupModel> searchGroup(final String s) {
        final ArrayList list = new ArrayList();
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("rowid, smiles, content, isCollection, canEdit, key, name, nameHtml,imageData, base64String, isRHave, isDelete");
            sb.append(" FROM \"GSGroupModel\"");
            sb.append(" WHERE \"isDelete\" = '0' AND (\"name\" like '%");
            sb.append(s);
            sb.append("%' OR \"smiles\" like '%");
            sb.append(s);
            sb.append("%') ORDER BY \"isCollection\" ASC");
            final List allBySql = this.findAllBySql((Class)GSGroupModel.class, sb.toString());
            final List searchGroup = this.gsGroupModelUserDBI.searchGroup(s);
            if (searchGroup != null) {
                ((List)list).addAll((Collection)searchGroup);
            }
            if (allBySql != null) {
                ((List)list).addAll((Collection)allBySql);
            }
            Collections.sort((List)list, (Comparator)new GSGroupModelComparator());
            return (List<GSGroupModel>)list;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void update(final Object o, final String[] array) throws DbException {
        try {
            this.beginTransaction();
            if (o instanceof List) {
                final List list = (List)o;
                if (list.isEmpty()) {
                    return;
                }
                final TableEntity table = this.db.getTable((Class)list.get(0).getClass());
                if (!table.tableIsExist()) {
                    return;
                }
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.db.execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, iterator.next(), array));
                }
            }
            else {
                final TableEntity table2 = this.db.getTable((Class)o.getClass());
                if (!table2.tableIsExist()) {
                    return;
                }
                this.db.execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table2, o, array));
            }
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }
    
    public OperationState updateFile(int isRHave, final boolean b, final KingDrawView drawWithView) {
        if (b) {
            return this.gsGroupModelUserDBI.updateFile(isRHave, b, drawWithView);
        }
        try {
            final int haveR = drawWithView.haveR();
            if (drawWithView.isEmpty() || haveR == 2 || !drawWithView.haveStruct()) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.palette_save_error_hint));
            }
            final GSGroupModel gsGroupModel = (GSGroupModel)this.db.findById((Class)GSGroupModel.class, (Object)isRHave);
            if (gsGroupModel == null) {
                return this.error(UIComponentHelper.getInstance().getString(R$string.sup_updata_not_exist));
            }
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
            final String smiles = withView.getSmiles();
            final SupThumImageOption drawOption = new SupThumImageOption();
            drawOption.setMark(false);
            final ImageDrawBuilder setDrawOption = new ImageDrawBuilder().setBackgroundColor(0).setDrawWithView(drawWithView).setProofStatus(false).setDrawOption((DrawOption)drawOption);
            final Bitmap drawBitmap = setDrawOption.getDrawBitmap();
            setDrawOption.onDestroy();
            final String bitmaptoString = GBitmapUtils.bitmaptoString(drawBitmap, 100);
            drawBitmap.recycle();
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
            if (isRHave != 0) {
                isRHave = 1;
            }
            else {
                isRHave = 0;
            }
            gsGroupModel.setIsRHave(isRHave);
            gsGroupModel.setSmiles(smiles);
            gsGroupModel.setContent("");
            gsGroupModel.setKdxFileBytes(encodeToString);
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
        if (b) {
            return this.gsGroupModelUserDBI.updateFile(gsGroupModel, b);
        }
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
