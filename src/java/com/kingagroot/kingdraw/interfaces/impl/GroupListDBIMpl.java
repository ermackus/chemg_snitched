package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import com.kingagroot.component.ui.UIComponentHelper;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.model.GroupModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.GroupListDBI;

public class GroupListDBIMpl extends BaseDBImpl implements GroupListDBI
{
    public OperationState addGroupList(final List<GroupModel> list) {
        try {
            this.db.saveOrUpdate((Object)list);
            return new OperationState(true, UIComponentHelper.getInstance().getString(2131820762));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public void cleanAllData() {
        try {
            this.db.delete((Class)GroupModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public OperationState deleteGroup(final String s) {
        try {
            this.db.delete((Class)GroupModel.class, WhereBuilder.b("groupOpenID", "=", (Object)s));
            return new OperationState(true, UIComponentHelper.getInstance().getString(2131820762));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return this.error();
        }
    }
    
    public GroupModel getDefaultGroup(final int n) {
        try {
            return (GroupModel)this.db.selector((Class)GroupModel.class).where("groupID", "=", (Object)n).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GroupModel> getGroupList() {
        try {
            return (List<GroupModel>)this.db.findAll((Class)GroupModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
