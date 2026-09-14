package com.kingagroot.component.ui.menu.menuitem;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import com.kingagroot.component.ui.menu.MoreMenuPop;

public interface MenuBaseItem
{
    void contactPopupWindow(final MoreMenuPop p0);
    
    boolean isCheckOnly();
    
    void setCheck(final boolean p0);
    
    void setOnMenuClickListener(final OnMenuClickListener p0);
    
    void setOnToolChangeListener(final OnToolChangeListener p0);
    
    void setOnToolHintListener(final OnToolHintListener p0);
}
