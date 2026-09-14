package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.component.ui.db.BaseDBI;

public interface UserDBI extends BaseDBI
{
    boolean clearUserInfo();
    
    AccountUserModel getCurrentUserInfo();
    
    boolean saveUserInfo(final AccountUserModel p0);
}
