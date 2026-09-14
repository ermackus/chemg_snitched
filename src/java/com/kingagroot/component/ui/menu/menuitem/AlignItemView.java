package com.kingagroot.component.ui.menu.menuitem;

import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import android.view.View;
import com.kingagroot.kingdraw.core.tool.AlignTypeEnum;
import com.kingagroot.kingdraw.core.model.NodeDirectEnum;
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

public class AlignItemView extends ToolBaseItemView implements View$OnLongClickListener
{
    private boolean isCheck;
    private final List<SecondaryMenuItem> menuItems;
    private ToolEnum toolEnum;
    
    public AlignItemView(final Context context) {
        super(context);
        this.menuItems = (List<SecondaryMenuItem>)new ArrayList();
        this.init();
    }
    
    private List<ToolModel> getAlignTools() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_LEFT_TOOL, R$drawable.ic_align_left));
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_RIGHT_TOOL, R$drawable.ic_align_right));
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_TOP_TOOL, R$drawable.ic_align_top));
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_BOTTOM_TOOL, R$drawable.ic_align_bottom));
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_VERTICAL_TOOL, R$drawable.ic_align_vertical));
        ((List)list).add((Object)new ToolModel(ToolEnum.GALIGN_HORIZONTAL_TOOL, R$drawable.ic_align_horizontal));
        ((List)list).add((Object)new ToolModel(ToolEnum.GLAYER_TOP, R$drawable.ic_layer_top));
        ((List)list).add((Object)new ToolModel(ToolEnum.GLAYER_BOTTOM, R$drawable.ic_layer_bottom));
        ((List)list).add((Object)new ToolModel(ToolEnum.GMIRROR_LEFT_RIGHT, R$drawable.ic_draw_mirror1_nor));
        ((List)list).add((Object)new ToolModel(ToolEnum.GMIRROR_TOP_BOTTOM, R$drawable.ic_draw_mirror2_nor));
        ((List)list).add((Object)new ToolModel(ToolEnum.NODE_DIRECT_AUTO, R$drawable.ic_align_text_auto));
        ((List)list).add((Object)new ToolModel(ToolEnum.NODE_DIRECT_RIGHT, R$drawable.ic_align_text_right));
        return (List<ToolModel>)list;
    }
    
    private void init() {
        this.setCornerGravity(3);
        this.setCornerWidth(GDensityUtil.dp2px(6.0f));
        this.setButtonDrawable(R$drawable.ic_align_left);
        this.toolEnum = ToolEnum.GALIGN_LEFT_TOOL;
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.initMenuItems();
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
    }
    
    private void initMenuItems() {
        for (final ToolModel toolModel : this.getAlignTools()) {
            final SecondaryMenuItem secondaryMenuItem = new SecondaryMenuItem(this.context);
            secondaryMenuItem.setToolModel(toolModel);
            secondaryMenuItem.setOnMenuClickListener((OnMenuClickListener)new AlignItemView$1(this));
            this.menuItems.add((Object)secondaryMenuItem);
        }
    }
    
    private void onToolUse() {
        if (this.kingDrawView != null) {
            if (this.toolEnum == ToolEnum.NODE_DIRECT_AUTO) {
                this.kingDrawView.setNodeDirect(NodeDirectEnum.AUTO);
            }
            else if (this.toolEnum == ToolEnum.NODE_DIRECT_RIGHT) {
                this.kingDrawView.setNodeDirect(NodeDirectEnum.USER_RIGHT);
            }
            else {
                this.kingDrawView.setSelectElementAlignType(AlignTypeEnum.valueOfTypee(this.toolEnum.toolParm));
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
        this.onToolUse();
        if (this.onToolHintListener != null) {
            this.onToolHintListener.onShowToolName(this.getResources().getString(this.toolEnum.hintNameId));
        }
    }
    
    public boolean onLongClick(final View view) {
        if (this.moreMenuPop != null) {
            this.moreMenuPop.setSecondaryMenuItems((List)this.menuItems);
        }
        else {
            this.setVisibleCorner(false);
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            final MoreMenuPop moreMenuPop = new MoreMenuPop(this.context);
            moreMenuPop.setMenuItemList((List)this.menuItems, 4);
            moreMenuPop.show((View)this, 0, 0);
            moreMenuPop.setOnDismissListener((PopupWindow$OnDismissListener)new AlignItemView$2(this));
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
