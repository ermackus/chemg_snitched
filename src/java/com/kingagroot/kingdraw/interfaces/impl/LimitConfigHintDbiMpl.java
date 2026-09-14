package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.LimitHintModel;
import com.kingagroot.kingdraw.interfaces.LimitConfigHintDbi;

public class LimitConfigHintDbiMpl extends BaseDBImpl implements LimitConfigHintDbi
{
    public static final String TAG = "LimitConfigHintDbiMpl";
    
    public LimitHintModel getLimitHintData() {
        try {
            return (LimitHintModel)this.db.findFirst((Class)LimitHintModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getLocalFileNumHint() {
        return this.getLimitHintData().getLocalFileStoreNumHint();
    }
    
    public String getPediaHint() {
        if (this.getLimitHintData() != null) {
            return this.getLimitHintData().getEncyclopediaAvailableHint();
        }
        return "";
    }
    
    public String getPicHint() {
        if (this.getLimitHintData() != null) {
            return this.getLimitHintData().getAllowImagesExportedHint();
        }
        return "";
    }
    
    public String getSearchHint() {
        if (this.getLimitHintData() != null) {
            return this.getLimitHintData().getFileRetrievalServiceHint();
        }
        return "";
    }
    
    public String getStereoscopicConfigurationHint() {
        if (this.getLimitHintData() != null) {
            return this.getLimitHintData().getDimensionalSettingHint();
        }
        return "";
    }
    
    public String getSupExportHint() {
        if (this.getLimitHintData() != null) {
            return this.getLimitHintData().getGroupUnfoldsHint();
        }
        return "";
    }
    
    public void saveLimitHintData(final LimitHintModel limitHintModel) {
        try {
            this.db.saveOrUpdate((Object)limitHintModel);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
}
