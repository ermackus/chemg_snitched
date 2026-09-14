package com.kingagroot.kingdraw.interfaces.impl;

import java.util.List;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.SearchHisModel;
import com.kingagroot.kingdraw.model.SearchTypeEnum;
import com.kingagroot.kingdraw.interfaces.SearchHisDBI;

public class SearchHisDBImpl extends BaseDBImpl implements SearchHisDBI
{
    private void deleteLast(final SearchTypeEnum searchTypeEnum) {
        try {
            if (this.db.selector((Class)SearchHisModel.class).where("type", "=", (Object)searchTypeEnum.getCode()).count() > 11L) {
                this.delete(((SearchHisModel)this.db.selector((Class)SearchHisModel.class).where("type", "=", (Object)searchTypeEnum.getCode()).orderBy("creatTime", false).findFirst()).getId());
            }
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    private SearchHisModel getFirstByKey(final String s, final SearchTypeEnum searchTypeEnum) {
        try {
            return (SearchHisModel)this.db.selector((Class)SearchHisModel.class).where("data", "=", (Object)s).and("type", "=", (Object)searchTypeEnum.getCode()).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean addHis(final String data, final SearchTypeEnum searchTypeEnum) {
        try {
            final SearchHisModel firstByKey = this.getFirstByKey(data, searchTypeEnum);
            if (firstByKey == null) {
                final SearchHisModel searchHisModel = new SearchHisModel();
                searchHisModel.setData(data);
                searchHisModel.setCreatTime(System.currentTimeMillis());
                searchHisModel.setType(searchTypeEnum.getCode());
                this.db.save((Object)searchHisModel);
            }
            else {
                firstByKey.setType(searchTypeEnum.getCode());
                firstByKey.setCreatTime(System.currentTimeMillis());
                this.db.saveOrUpdate((Object)firstByKey);
            }
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean clear(final SearchTypeEnum searchTypeEnum) {
        try {
            this.db.delete((Class)SearchHisModel.class, WhereBuilder.b("type", "=", (Object)searchTypeEnum.getCode()));
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(final int n) {
        try {
            this.db.deleteById((Class)SearchHisModel.class, (Object)n);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<SearchHisModel> getList(final SearchTypeEnum searchTypeEnum) {
        try {
            return (List<SearchHisModel>)this.db.selector((Class)SearchHisModel.class).where("type", "=", (Object)searchTypeEnum.getCode()).orderBy("creatTime", true).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
