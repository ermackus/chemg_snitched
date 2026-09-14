package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import android.content.res.Configuration;
import android.widget.PopupWindow$OnDismissListener;
import com.kingagroot.component.ui.menu.menuitem.MenuBaseItem;
import android.view.ViewTreeObserver;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import java.util.Iterator;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.view.GCompoundButton;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.menu.menuitem.UndoItemView;
import com.kingagroot.component.ui.menu.menuitem.RedoItemView;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import com.kingagroot.component.ui.view.GImageButton;
import android.view.View$OnClickListener;
import android.widget.LinearLayout;

public class TopMenu extends LinearLayout implements View$OnClickListener
{
    private GImageButton ibtMore;
    private LinearLayout llMenuItems;
    private LinearLayout llMoreItem;
    private final List<ToolBaseItemView> menuItems;
    private final List<ToolBaseItemView> moreItems;
    private MoreMenuPop moreMenuPop;
    OnMenuClickListener onMenuClickListener;
    private RedoItemView redoItemView;
    private UndoItemView undoItemView;
    
    public TopMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.menuItems = (List<ToolBaseItemView>)new ArrayList();
        this.moreItems = (List<ToolBaseItemView>)new ArrayList();
        this.onMenuClickListener = (OnMenuClickListener)new TopMenu$4(this);
        View.inflate(context, R.layout.component_layout_menu_top_tool, (ViewGroup)this);
        this.initView();
    }
    
    private void initView() {
        this.llMenuItems = (LinearLayout)this.findViewById(R.id.ll_menu_items);
        this.llMoreItem = (LinearLayout)this.findViewById(R.id.ll_more_item);
        this.ibtMore = (GImageButton)this.findViewById(R.id.ibt_more);
        this.undoItemView = (UndoItemView)this.findViewById(R.id.undo_item_view);
        this.redoItemView = (RedoItemView)this.findViewById(R.id.redo_item_view);
        this.ibtMore.setOnClickListener((View$OnClickListener)this);
        this.initEnable(false);
    }
    
    private void setViewEnable(final GCompoundButton gCompoundButton, final boolean clickEnable) {
        gCompoundButton.setClickEnable(clickEnable);
        if (clickEnable) {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R.color.elementColor));
        }
        else {
            gCompoundButton.setButtonDrawableColor(ContextCompat.getColor(this.getContext(), R.color.line));
        }
    }
    
    public void addMenuItem(final ToolBaseItemView toolBaseItemView) {
        toolBaseItemView.setOnMenuClickListener(this.onMenuClickListener);
        this.menuItems.add((Object)toolBaseItemView);
    }
    
    public void addMenuItems(final List<ToolBaseItemView> list) {
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            this.addMenuItem((ToolBaseItemView)iterator.next());
        }
    }
    
    public void clearCheck() {
        for (final ToolBaseItemView toolBaseItemView : this.menuItems) {
            if (toolBaseItemView.isCheckOnly()) {
                toolBaseItemView.setCheck(false);
            }
        }
    }
    
    public void contextKingDrawView(final KingDrawView kingDrawView) {
        this.undoItemView.setKingDrawView(kingDrawView);
        this.redoItemView.setKingDrawView(kingDrawView);
        kingDrawView.addCoreAvailableListener((OnCoreAvailableListener)new TopMenu$1(this));
    }
    
    public void initEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.undoItemView, b);
        this.setViewEnable((GCompoundButton)this.redoItemView, b);
    }
    
    public void layoutMenu() {
        this.llMenuItems.removeAllViews();
        this.llMenuItems.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener(this) {
            final TopMenu this$0;
            
            public void onGlobalLayout() {
                final ViewTreeObserver viewTreeObserver = this.this$0.llMenuItems.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                }
                final int measuredWidth = this.this$0.llMenuItems.getMeasuredWidth();
                final float dimension = this.this$0.getContext().getResources().getDimension(R.dimen.home_tool_height);
                float n2;
                final float n = n2 = measuredWidth / dimension;
                if (n - (int)n > 0.7) {
                    n2 = n + 1.0f;
                }
                final int n3 = (int)n2;
                this.this$0.llMoreItem.setVisibility(8);
                final int size = this.this$0.menuItems.size();
                final int n4 = 0;
                int n5 = n3;
                int i = n4;
                if (size > n3) {
                    n5 = n3 - 1;
                    this.this$0.llMoreItem.setVisibility(0);
                    i = n4;
                }
                while (i < n5 && i < this.this$0.menuItems.size()) {
                    final ToolBaseItemView toolBaseItemView = (ToolBaseItemView)this.this$0.menuItems.get(i);
                    final int n6 = (int)dimension;
                    final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n6, n6);
                    final ViewGroup viewGroup = (ViewGroup)toolBaseItemView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView((View)toolBaseItemView);
                    }
                    toolBaseItemView.contactPopupWindow((MoreMenuPop)null);
                    this.this$0.llMenuItems.addView((View)toolBaseItemView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                    ++i;
                }
                this.this$0.moreItems.clear();
                while (i < this.this$0.menuItems.size()) {
                    final ToolBaseItemView toolBaseItemView2 = (ToolBaseItemView)this.this$0.menuItems.get(i);
                    toolBaseItemView2.contactPopupWindow((MoreMenuPop)null);
                    this.this$0.moreItems.add((Object)toolBaseItemView2);
                    ++i;
                }
            }
        });
    }
    
    public void onClick(final View view) {
        if (view == this.ibtMore) {
            if (this.moreMenuPop == null) {
                this.moreMenuPop = new MoreMenuPop(this.getContext());
            }
            this.moreMenuPop.setMenuItemList((List<? extends MenuBaseItem>)this.moreItems);
            this.moreMenuPop.show(view, 0, 0);
            this.ibtMore.setBackgroundResource(R.drawable.palete_menu_check_bg);
            this.moreMenuPop.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener(this) {
                final TopMenu this$0;
                
                public void onDismiss() {
                    this.this$0.ibtMore.setBackgroundResource(R.drawable.palete_menu_nor_bg);
                }
            });
        }
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.layoutMenu();
        final MoreMenuPop moreMenuPop = this.moreMenuPop;
        if (moreMenuPop != null && moreMenuPop.isShowing()) {
            this.moreMenuPop.dismiss();
        }
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        final Iterator iterator = this.menuItems.iterator();
        while (iterator.hasNext()) {
            ((ToolBaseItemView)iterator.next()).setOnToolChangeListener(onToolChangeListener);
        }
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        final Iterator iterator = this.menuItems.iterator();
        while (iterator.hasNext()) {
            ((ToolBaseItemView)iterator.next()).setOnToolHintListener(onToolHintListener);
        }
        this.undoItemView.setOnToolHintListener(onToolHintListener);
        this.redoItemView.setOnToolHintListener(onToolHintListener);
    }
    
    public void setRedoEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.redoItemView, b);
    }
    
    public void setUndoEnable(final boolean b) {
        this.setViewEnable((GCompoundButton)this.undoItemView, b);
    }
}
