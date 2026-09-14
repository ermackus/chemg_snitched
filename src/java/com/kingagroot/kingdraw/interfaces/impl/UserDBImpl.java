package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.kingdraw.interfaces.UserDBI;

public class UserDBImpl extends BaseDBImpl implements UserDBI
{
    public boolean clearUserInfo() {
        try {
            this.db.delete((Class)AccountUserModel.class);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public AccountUserModel getCurrentUserInfo() {
        try {
            return (AccountUserModel)this.db.findFirst((Class)AccountUserModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean saveUserInfo(final AccountUserModel accountUserModel) {
        try {
            this.db.dropTable((Class)AccountUserModel.class);
            this.db.saveOrUpdate((Object)accountUserModel);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
