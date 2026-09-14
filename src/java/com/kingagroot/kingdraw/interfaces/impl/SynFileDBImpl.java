package com.kingagroot.kingdraw.interfaces.impl;

import com.kingagroot.kingdraw.interfaces.UserDBI;
import org.xutils.common.util.KeyValue;
import com.kingagroot.kingdraw.model.SynStatusEnum;
import com.kingagroot.kingdraw.model.SynTypeEnum;
import org.xutils.db.sqlite.WhereBuilder;
import java.util.List;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.SynFileModel;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;

public class SynFileDBImpl extends BaseDBImpl implements SynFileDBI
{
    String dbname;
    
    public SynFileDBImpl() {
        this.dbname = null;
        final AccountUserModel currentUserInfo = ((UserDBI)new UserDBImpl()).getCurrentUserInfo();
        if (currentUserInfo != null) {
            this.dbname = String.valueOf(currentUserInfo.getId());
        }
        this.initDB(this.dbname);
    }
    
    public boolean addSynFileModel(final SynFileModel synFileModel) {
        try {
            this.db.save((Object)synFileModel);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean addSynFileModels(final List<SynFileModel> list) {
        try {
            this.db.saveOrUpdate((Object)list);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteSynFileByFolderFileId(final String s) {
        try {
            this.db.delete((Class)SynFileModel.class, WhereBuilder.b("FolderFileModelId", "=", (Object)s));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteSynFileModel(final String s) {
        try {
            this.db.deleteById((Class)SynFileModel.class, (Object)s);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<SynFileModel> getDownLoadingFileModels() {
        try {
            return (List<SynFileModel>)this.db.selector((Class)SynFileModel.class).where("SynType", "=", (Object)SynTypeEnum.\u4e0b\u8f7d.getCode()).and("Status", "=", (Object)SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()).or("Status", "=", (Object)SynStatusEnum.\u4f20\u8f93\u4e2d.getCode()).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<SynFileModel> getDownLodFileModels() {
        try {
            return (List<SynFileModel>)this.db.selector((Class)SynFileModel.class).where("Status", "!=", (Object)"3").and("SynType", "=", (Object)SynTypeEnum.\u4e0b\u8f7d.getCode()).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public SynFileModel getSynFileModelByFolderFileModelId(final String s) {
        try {
            return (SynFileModel)this.db.selector((Class)SynFileModel.class).where("FolderFileModelId", "=", (Object)s).and("Status", "=", (Object)SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()).or("Status", "=", (Object)SynStatusEnum.\u4f20\u8f93\u4e2d.getCode()).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<SynFileModel> getUpLoadFileModels() {
        try {
            return (List<SynFileModel>)this.db.selector((Class)SynFileModel.class).where("Status", "!=", (Object)"3").and("SynType", "=", (Object)SynTypeEnum.\u4e0a\u4f20.getCode()).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void restartSynFile() {
        try {
            this.db.update((Class)SynFileModel.class, WhereBuilder.b("Status", "=", (Object)SynStatusEnum.\u5931\u8d25.getCode()), new KeyValue[] { new KeyValue("Status", (Object)SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()) });
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean setSynFileModelStatus(final String s, final int n) {
        try {
            this.db.update((Class)SynFileModel.class, WhereBuilder.b("Id", "=", (Object)s), new KeyValue[] { new KeyValue("Status", (Object)n) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean stopAllSynModel(final int n) {
        try {
            this.db.update((Class)SynFileModel.class, WhereBuilder.b("SynType", "=", (Object)n).and(WhereBuilder.b("Status", "=", (Object)SynStatusEnum.\u7b49\u5f85\u4e2d.getCode()).or("Status", "=", (Object)SynStatusEnum.\u4f20\u8f93\u4e2d.getCode())), new KeyValue[] { new KeyValue("Status", (Object)SynStatusEnum.\u5931\u8d25.getCode()) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
