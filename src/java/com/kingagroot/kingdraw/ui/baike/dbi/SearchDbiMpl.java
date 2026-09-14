package com.kingagroot.kingdraw.ui.baike.dbi;

import java.util.List;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.ui.baike.model.SearchHistoryModel;
import com.kingagroot.kingdraw.interfaces.impl.BaseDBImpl;

public class SearchDbiMpl extends BaseDBImpl implements SearchDbi
{
    long maxNum;
    
    public SearchDbiMpl() {
        this.maxNum = 10L;
    }
    
    private SearchHistoryModel getFirstByKey(final String s) {
        try {
            return (SearchHistoryModel)this.db.selector((Class)SearchHistoryModel.class).where("searchName", "=", (Object)s).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean addSearchData(final String searchName) {
        try {
            final SearchHistoryModel firstByKey = this.getFirstByKey(searchName);
            if (firstByKey == null) {
                final SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
                searchHistoryModel.setSearchName(searchName);
                searchHistoryModel.setCreateTime(System.currentTimeMillis());
                this.db.save((Object)searchHistoryModel);
            }
            else {
                firstByKey.setCreateTime(System.currentTimeMillis());
                this.db.saveOrUpdate((Object)firstByKey);
            }
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteAllData() {
        try {
            this.db.delete((Class)SearchHistoryModel.class);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<SearchHistoryModel> getAllList() {
        try {
            return (List<SearchHistoryModel>)this.db.selector((Class)SearchHistoryModel.class).limit((int)this.maxNum).orderBy("createTime", true).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
