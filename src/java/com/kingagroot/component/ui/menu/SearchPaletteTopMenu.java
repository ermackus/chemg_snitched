package com.kingagroot.component.ui.menu;

import com.kingagroot.component.ui.OnToolHintListener;
import com.kingagroot.component.ui.OnToolChangeListener;
import com.kingagroot.component.ui.menu.menuitem.ToolBaseItemView;
import java.util.List;
import android.animation.ObjectAnimator;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.component.ui.R$id;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.SearchPaletteViewListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.LinearLayout;

public class SearchPaletteTopMenu extends LinearLayout implements BaseToolMenu, View$OnClickListener
{
    private Button btnCancel;
    private Button btnDone;
    private CheckBox cbFullScreen;
    private final View contentView;
    private LinearLayout llContent;
    private TopMenu menu;
    private SearchPaletteViewListener searchPaletteViewListener;
    
    public SearchPaletteTopMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.contentView = View.inflate(context, R$layout.component_layout_menu_search_top, (ViewGroup)this);
        this.initView();
    }
    
    private void initView() {
        this.llContent = (LinearLayout)this.findViewById(R$id.ll_content);
        this.btnDone = (Button)this.findViewById(R$id.btn_done);
        this.btnCancel = (Button)this.findViewById(R$id.btn_cancel);
        this.menu = (TopMenu)this.findViewById(R$id.menu);
        (this.cbFullScreen = (CheckBox)this.findViewById(R$id.cb_full_screen)).setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new SearchPaletteTopMenu$1(this));
        this.btnDone.setOnClickListener((View$OnClickListener)this);
        this.btnCancel.setOnClickListener((View$OnClickListener)this);
    }
    
    public void clearCheck() {
        this.menu.clearCheck();
    }
    
    public void contextKingDrawView(final KingDrawView kingDrawView) {
        this.menu.contextKingDrawView(kingDrawView);
    }
    
    public void dismiss() {
        final LinearLayout llContent = this.llContent;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llContent, "translationY", new float[] { llContent.getTranslationY(), (float)(-this.getMeasuredHeight()) });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
    
    public void initData(final List<ToolBaseItemView> list) {
        this.menu.addMenuItems((List)list);
        this.menu.layoutMenu();
    }
    
    public void onClick(final View view) {
        if (view == this.btnDone) {
            final SearchPaletteViewListener searchPaletteViewListener = this.searchPaletteViewListener;
            if (searchPaletteViewListener != null) {
                searchPaletteViewListener.onSearchDone();
            }
        }
        else if (view == this.btnCancel) {
            final SearchPaletteViewListener searchPaletteViewListener2 = this.searchPaletteViewListener;
            if (searchPaletteViewListener2 != null) {
                searchPaletteViewListener2.onSearchCancel();
            }
        }
    }
    
    public void setMainPaletteView(final SearchPaletteViewListener searchPaletteViewListener) {
        this.searchPaletteViewListener = searchPaletteViewListener;
    }
    
    public void setNotch(final boolean b) {
    }
    
    public void setOnToolChangeListener(final OnToolChangeListener onToolChangeListener) {
        this.menu.setOnToolChangeListener(onToolChangeListener);
    }
    
    public void setOnToolHintListener(final OnToolHintListener onToolHintListener) {
        this.menu.setOnToolHintListener(onToolHintListener);
    }
    
    public void show() {
        final LinearLayout llContent = this.llContent;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)llContent, "translationY", new float[] { llContent.getTranslationY(), 0.0f });
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}
