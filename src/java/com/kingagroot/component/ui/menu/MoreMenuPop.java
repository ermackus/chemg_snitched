package com.kingagroot.component.ui.menu;

import java.util.Collection;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.menu.menuitem.NavBackMenuItem;
import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup;
import com.kingagroot.component.ui.menu.menuitem.MenuBaseItem;
import java.util.List;
import android.widget.LinearLayout;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class MoreMenuPop extends PopupWindow
{
    private View contentView;
    private Context context;
    private LinearLayout llMenu;
    private List<? extends MenuBaseItem> menuItemList;
    
    public MoreMenuPop(final Context context) {
        this.init(this.context = context);
    }
    
    private void addMenuItems(final List<? extends MenuBaseItem> list, final int n) {
        final int size = list.size();
        for (int i = 0; i < this.llMenu.getChildCount(); ++i) {
            ((ViewGroup)this.llMenu.getChildAt(i)).removeAllViews();
        }
        this.llMenu.removeAllViews();
        int n2;
        if (size % n == 0) {
            n2 = size / n;
        }
        else {
            n2 = size / n + 1;
        }
        for (int j = 0; j < n2; ++j) {
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
            final LinearLayout linearLayout = new LinearLayout(this.context);
            this.llMenu.addView((View)linearLayout, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            for (int k = 0; k < n; ++k) {
                final int n3 = j * n + k;
                if (n3 < size) {
                    final MenuBaseItem menuBaseItem = (MenuBaseItem)list.get(n3);
                    menuBaseItem.contactPopupWindow(this);
                    final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(GDensityUtil.dp2px(40.0f), GDensityUtil.dp2px(40.0f));
                    final View view = (View)menuBaseItem;
                    final ViewGroup viewGroup = (ViewGroup)view.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(view);
                    }
                    linearLayout.addView(view, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
                }
            }
        }
    }
    
    private int getPopHeight() {
        this.contentView.measure(0, 0);
        return this.contentView.getMeasuredHeight();
    }
    
    private int getPopWidth() {
        this.contentView.measure(0, 0);
        return this.contentView.getMeasuredWidth();
    }
    
    private void init(final Context context) {
        this.context = context;
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            this.contentView = layoutInflater.inflate(R.layout.component_popuwindow_tools_more, (ViewGroup)null);
        }
        this.setContentView(this.contentView);
        this.setWidth(-2);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.llMenu = (LinearLayout)this.contentView.findViewById(R.id.ll_menu);
    }
    
    public void setMenuItemList(final List<? extends MenuBaseItem> menuItemList) {
        this.addMenuItems(this.menuItemList = menuItemList, 6);
    }
    
    public void setMenuItemList(final List<? extends MenuBaseItem> menuItemList, final int n) {
        this.addMenuItems(this.menuItemList = menuItemList, n);
    }
    
    public void setSecondaryMenuItems(final List<? extends MenuBaseItem> list) {
        final int width = this.llMenu.getWidth();
        final int height = this.llMenu.getHeight();
        final ArrayList list2 = new ArrayList();
        final NavBackMenuItem navBackMenuItem = new NavBackMenuItem(this.context);
        navBackMenuItem.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final MoreMenuPop this$0;
            
            public void onClick(final View view) {
                final MoreMenuPop this$0 = this.this$0;
                this$0.addMenuItems(this$0.menuItemList, 6);
            }
        });
        ((List)list2).add(0, (Object)navBackMenuItem);
        ((List)list2).addAll((Collection)list);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.llMenu.getLayoutParams();
        linearLayout$LayoutParams.width = width;
        linearLayout$LayoutParams.height = height;
        this.addMenuItems((List<? extends MenuBaseItem>)list2, 6);
    }
    
    public void show(final View view, final int n, final int n2) {
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        this.showAtLocation(view, 51, n2 + array[0] - (this.getPopWidth() - view.getWidth()) / 2 - n, view.getHeight() + array[1]);
    }
    
    public void showAsDropUp(final View view, final int n, final int n2) {
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        this.showAtLocation(view, 51, n2 + array[0] - (this.getPopWidth() - view.getWidth()) / 2 - n, array[1] - this.getPopHeight());
    }
    
    public void verticalShow(final View view) {
        this.contentView.measure(0, 0);
        this.showAsDropDown(view, -this.contentView.getMeasuredWidth(), -this.contentView.getMeasuredHeight());
    }
}
