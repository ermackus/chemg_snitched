package com.kingagroot.component.ui.menu.menuitem;

import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.component.ui.menu.MoreMenuPop;
import java.util.Collection;
import android.view.View;
import java.util.Iterator;
import android.content.DialogInterface$OnDismissListener;
import com.kingagroot.component.ui.menu.menuitem.color.OnColorMenuItemListener;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.R$drawable;
import com.kingagroot.component.ui.R$mipmap;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.Color;
import com.kingagroot.kingdraw.core.graphics.KDColor;
import java.util.ArrayList;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.color.KdNormalColorMenuItem;
import java.util.List;
import com.kingagroot.component.ui.menu.menuitem.color.KdCustomColorItem;
import com.kingagroot.component.ui.widget.colorpicker.ColorRgbPop;
import android.view.View$OnLongClickListener;

public class ColorItemView extends ToolBaseItemView implements View$OnLongClickListener
{
    private ColorRgbPop colorRgbPop;
    private KdCustomColorItem customItem;
    private int menuColor;
    private final List<KdNormalColorMenuItem> normalColorItems;
    
    public ColorItemView(final Context context) {
        super(context);
        this.menuColor = -16777216;
        this.normalColorItems = (List<KdNormalColorMenuItem>)new ArrayList();
        this.init();
    }
    
    private int getChooseColor() {
        return KDColor.parseColor(this.kingDrawView.getSelectedColor());
    }
    
    private List<Integer> getNormalColor() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)Color.parseColor("#FF0000"));
        ((List)list).add((Object)Color.parseColor("#FFFF00"));
        ((List)list).add((Object)Color.parseColor("#0000FF"));
        ((List)list).add((Object)Color.parseColor("#008000"));
        ((List)list).add((Object)Color.parseColor("#000000"));
        ((List)list).add((Object)Color.parseColor("#00FFFF"));
        ((List)list).add((Object)Color.parseColor("#800080"));
        return (List<Integer>)list;
    }
    
    private void init() {
        this.setCornerGravity(3);
        this.setCornerWidth(GDensityUtil.dp2px(6.0f));
        this.setButtonDrawable(R$mipmap.ic_tool_colors);
        this.setBackgroundResource(R$drawable.palete_menu_nor_bg);
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.initColor();
    }
    
    private void initColor() {
        this.colorRgbPop = new ColorRgbPop(this.context);
        for (final int intValue : this.getNormalColor()) {
            final KdNormalColorMenuItem kdNormalColorMenuItem = new KdNormalColorMenuItem(this.context);
            kdNormalColorMenuItem.setColor(intValue);
            this.normalColorItems.add((Object)kdNormalColorMenuItem);
        }
        (this.customItem = new KdCustomColorItem(this.context)).setOnColorMenuItemListener((OnColorMenuItemListener)new ColorItemView$4(this));
        this.colorRgbPop.setOnDismissListener((DialogInterface$OnDismissListener)new ColorItemView$5(this));
    }
    
    private void setElementColor() {
        if (this.kingDrawView != null) {
            this.kingDrawView.setSelectedColor(KDColor.getHexString(this.menuColor));
        }
    }
    
    @Override
    public void onClick(final View view) {
        super.onClick(view);
        this.setElementColor();
    }
    
    public boolean onLongClick(final View view) {
        final ArrayList secondaryMenuItems = new ArrayList((Collection)this.normalColorItems);
        this.customItem.setColor(this.getChooseColor());
        ((List)secondaryMenuItems).add((Object)this.customItem);
        if (this.moreMenuPop != null) {
            final Iterator iterator = this.normalColorItems.iterator();
            while (iterator.hasNext()) {
                ((KdNormalColorMenuItem)iterator.next()).setOnColorMenuItemListener((OnColorMenuItemListener)new ColorItemView$1(this));
            }
            this.moreMenuPop.setSecondaryMenuItems((List)secondaryMenuItems);
        }
        else {
            this.setVisibleCorner(false);
            this.setBackgroundResource(R$drawable.tool_bg_sel);
            final Iterator iterator2 = this.normalColorItems.iterator();
            while (iterator2.hasNext()) {
                ((KdNormalColorMenuItem)iterator2.next()).setOnColorMenuItemListener((OnColorMenuItemListener)new ColorItemView$2(this));
            }
            final MoreMenuPop moreMenuPop = new MoreMenuPop(this.context);
            moreMenuPop.setMenuItemList((List)secondaryMenuItems, 4);
            moreMenuPop.show((View)this, 0, 0);
            moreMenuPop.setOnDismissListener((PopupWindow$OnDismissListener)new ColorItemView$3(this));
        }
        return true;
    }
}
