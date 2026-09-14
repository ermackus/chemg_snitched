package com.kingagroot.component.ui.menu.menuitem;

import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import android.view.View;
import java.util.Iterator;
import com.kingagroot.component.ui.menu.OnMenuClickListener;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.component.ui.model.ToolModel;
import java.util.ArrayList;
import android.content.Context;
import com.kingagroot.component.ui.ToolEnum;
import java.util.List;
import android.view.View$OnLongClickListener;

public class SelectItemView extends ToolBaseItemView implements View$OnLongClickListener
{
    private boolean isCheck;
    private final List<SecondaryMenuItem> menuItems;
    private ToolEnum toolEnum;
    
    public SelectItemView(final Context context) {
        super(context);
        this.menuItems = (List<SecondaryMenuItem>)new ArrayList();
        this.init();
    }
    
    private List<ToolModel> getSelectorTools() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new ToolModel(ToolEnum.GREC_SELECTOR_TOOL, R$drawable.ic_select_rectangle));
        ((List)list).add((Object)new ToolModel(ToolEnum.GLASSO_SELSCTOT_TOOL, R$drawable.ic_sel_lasso));
        return (List<ToolModel>)list;
    }
    
    private void init() {
        this.setCornerGravity(3);
        this.setCornerWidth(GDensityUtil.dp2px(6.0f));
        this.setButtonDrawable(R$drawable.ic_select_rectangle);
        this.toolEnum = ToolEnum.GREC_SELECTOR_TOOL;
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.initMenuItems();
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.setOnClickListener((View$OnClickListener)this);
    }
    
    private void initMenuItems() {
        for (final ToolModel toolModel : this.getSelectorTools()) {
            final SecondaryMenuItem secondaryMenuItem = new SecondaryMenuItem(this.context);
            secondaryMenuItem.setToolModel(toolModel);
            secondaryMenuItem.setOnMenuClickListener((OnMenuClickListener)new SelectItemView$1(this));
            this.menuItems.add((Object)secondaryMenuItem);
        }
    }
    
    private void onSelected() {
        if (!this.isCheck) {
            this.setCheck(true);
            if (this.onMenuClickListener != null) {
                this.onMenuClickListener.onClearOtherMenuItem((MenuBaseItem)this);
            }
            if (this.onToolChangeListener != null) {
                this.onToolChangeListener.onChange(this.toolEnum);
            }
            if (this.onToolHintListener != null) {
                this.onToolHintListener.onShowToolName(this.getContext().getString(this.toolEnum.hintNameId));
            }
        }
    }
    
    @Override
    public boolean isCheckOnly() {
        return true;
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.onSelected();
    }
    
    public boolean onLongClick(final View view) {
        for (final SecondaryMenuItem secondaryMenuItem : this.menuItems) {
            secondaryMenuItem.setCheck(false);
            if (this.isCheck && secondaryMenuItem.getToolModel().getToolEnum() == this.toolEnum) {
                secondaryMenuItem.setCheck(true);
            }
        }
        if (this.moreMenuPop != null) {
            this.moreMenuPop.setSecondaryMenuItems((List)this.menuItems);
        }
        else {
            this.setVisibleCorner(false);
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            final MoreMenuPop moreMenuPop = new MoreMenuPop(this.context);
            moreMenuPop.setMenuItemList((List)this.menuItems, 4);
            moreMenuPop.show((View)this, 0, 0);
            moreMenuPop.setOnDismissListener((PopupWindow$OnDismissListener)new SelectItemView$2(this));
        }
        return true;
    }
    
    @Override
    public void setCheck(final boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackgroundResource(R$drawable.palete_menu_check_bg);
        }
        else {
            this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        }
    }
}
