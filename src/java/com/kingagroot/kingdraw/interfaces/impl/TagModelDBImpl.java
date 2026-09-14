package com.kingagroot.kingdraw.interfaces.impl;

import android.text.TextUtils;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.component.ui.view.OperationState;
import java.util.Iterator;
import org.xutils.ex.DbException;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.kingdraw.widget.GTagInputFilter;
import com.kingagroot.kingdraw.model.GTagModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.TagModelDBI;

public class TagModelDBImpl extends BaseDBImpl implements TagModelDBI
{
    public boolean addSelectTagModels(final List<String> list) {
        if (list != null) {
            try {
                this.clearSelectTags();
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.db.update((Class)GTagModel.class, WhereBuilder.b("name", "=", (Object)GTagInputFilter.filter((String)iterator.next())), new KeyValue[] { new KeyValue("isSelect", (Object)true), new KeyValue("selectTime", (Object)System.currentTimeMillis()) });
                }
            }
            catch (final DbException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
    
    public OperationState addTag(final String s) {
        try {
            final String filter = GTagInputFilter.filter(s);
            if (this.db.selector((Class)GTagModel.class).where("name", "=", (Object)filter).count() > 0L) {
                return this.error(MApplication.getInstance().getString(2131820815));
            }
            final GTagModel gTagModel = new GTagModel();
            gTagModel.setName(filter);
            gTagModel.setCreateTime(System.currentTimeMillis());
            gTagModel.setSelect(false);
            this.db.save((Object)gTagModel);
            return new OperationState(true, MApplication.getInstance().getString(2131821454));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public boolean clearSelectTags() {
        try {
            this.db.update((Class)GTagModel.class, (WhereBuilder)null, new KeyValue[] { new KeyValue("isSelect", (Object)false) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTag(String filter) {
        try {
            filter = GTagInputFilter.filter(filter);
            this.db.delete((Class)GTagModel.class, WhereBuilder.b("name", "=", (Object)filter));
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<GTagModel> getSelectTagModels() {
        try {
            return (List<GTagModel>)this.db.selector((Class)GTagModel.class).where("isSelect", "=", (Object)1).orderBy("selectTime", false).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GTagModel> getTagModels() {
        try {
            return (List<GTagModel>)this.db.selector((Class)GTagModel.class).orderBy("createTime", true).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GTagModel> getUnSelectTagModels() {
        try {
            return (List<GTagModel>)this.db.selector((Class)GTagModel.class).where("isSelect", "=", (Object)0).orderBy("selectTime", true).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GTagModel> getUnSelectTagModels(final List<String> list) {
        String s2;
        final String s = s2 = "";
        if (list != null) {
            final Iterator iterator = list.iterator();
            String string = s;
            while (true) {
                s2 = string;
                if (!iterator.hasNext()) {
                    break;
                }
                final String filter = GTagInputFilter.filter((String)iterator.next());
                final StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(",\"");
                sb.append(filter);
                sb.append("\"");
                string = sb.toString();
            }
        }
        String substring = s2;
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            substring = s2.substring(1);
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT * FROM GTagModel");
        if (!TextUtils.isEmpty((CharSequence)substring)) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(" WHERE name NOT IN (");
            sb3.append(substring);
            sb3.append(")");
            sb2.append(sb3.toString());
        }
        sb2.append(" order by createTime DESC");
        try {
            return (List<GTagModel>)this.findAllBySql((Class)GTagModel.class, sb2.toString());
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean removeTag(final String s) {
        try {
            this.db.update((Class)GTagModel.class, WhereBuilder.b("name", "=", (Object)GTagInputFilter.filter(s)), new KeyValue[] { new KeyValue("isSelect", (Object)false) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public OperationState updataTag(final int n, String filter) {
        try {
            filter = GTagInputFilter.filter(filter);
            if (this.db.selector((Class)GTagModel.class).where("name", "=", (Object)filter).count() > 0L) {
                return this.error(MApplication.getInstance().getString(2131820815));
            }
            this.db.update((Class)GTagModel.class, WhereBuilder.b("Id", "=", (Object)n), new KeyValue[] { new KeyValue("name", (Object)filter) });
            return this.success();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
}
