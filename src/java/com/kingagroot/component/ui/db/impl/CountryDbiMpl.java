package com.kingagroot.component.ui.db.impl;

import org.xutils.db.Selector;
import org.xutils.db.table.TableEntity;
import com.kingagroot.component.ui.R$string;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.component.ui.view.OperationState;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import com.kingagroot.component.ui.model.CountryModel;
import com.kingagroot.component.ui.db.CountryDbi;

public class CountryDbiMpl extends BaseDBImpl implements CountryDbi
{
    public static final String AREA_DB_NAME = "AreaData.db";
    private static final int AREA_VERSION_DB = 1;
    
    public CountryDbiMpl() {
        super("AreaData.db", 1);
    }
    
    public long CountryCount() {
        if (this.isTableExist((Class)CountryModel.class)) {
            try {
                return this.db.selector((Class)CountryModel.class).count();
            }
            catch (final DbException ex) {
                ex.printStackTrace();
            }
        }
        return 0L;
    }
    
    public OperationState addCountryList(final List<CountryModel> list) {
        try {
            this.db.saveOrUpdate((Object)list);
            return new OperationState(true, UIComponentHelper.getInstance().getString(R$string.db_oper_success));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public void deleteAllData() {
        try {
            this.db.delete((Class)CountryModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<CountryModel> getAllData() {
        try {
            return (List<CountryModel>)this.db.findAll((Class)CountryModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public CountryModel getCountryByCountryCode(final String s) {
        try {
            return (CountryModel)this.db.selector((Class)CountryModel.class).where("countryCode", "=", (Object)s).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public CountryModel getCountryByName(final String s) {
        try {
            return (CountryModel)this.db.selector((Class)CountryModel.class).where("value", "=", (Object)s).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<CountryModel> getCountryModels(final String s) {
        try {
            final Selector selector = this.db.selector((Class)CountryModel.class);
            final StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(s);
            sb.append("%");
            final Selector where = selector.where("key", "LIKE", (Object)sb.toString());
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("%");
            sb2.append(s);
            sb2.append("%");
            return (List<CountryModel>)where.or("value", "LIKE", (Object)sb2.toString()).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
