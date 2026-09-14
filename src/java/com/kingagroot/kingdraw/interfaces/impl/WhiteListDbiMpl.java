package com.kingagroot.kingdraw.interfaces.impl;

import java.util.List;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.WebWhiteModel;
import com.kingagroot.kingdraw.interfaces.WhiteListDbi;

public class WhiteListDbiMpl extends BaseDBImpl implements WhiteListDbi
{
    public void cleanAllData() {
        try {
            this.db.delete((Class)WebWhiteModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<WebWhiteModel> getWhiteList() {
        try {
            return (List<WebWhiteModel>)this.db.findAll((Class)WebWhiteModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void saveWhiteData(final List<WebWhiteModel> list) {
        try {
            this.db.saveOrUpdate((Object)list);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
}
