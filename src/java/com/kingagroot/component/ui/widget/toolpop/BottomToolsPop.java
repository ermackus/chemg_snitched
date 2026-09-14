package com.kingagroot.component.ui.widget.toolpop;

import com.goodsrc.ui.library.widget.notch.NotchContext;
import android.app.Activity;
import java.util.Collection;
import com.kingagroot.component.ui.ToolEnum;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.goodsrc.library.utils.SystemUtils;
import com.kingagroot.component.ui.R$id;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import com.kingagroot.component.ui.R$layout;
import android.view.LayoutInflater;
import java.util.ArrayList;
import com.kingagroot.component.ui.model.ToolModel;
import java.util.List;
import android.content.Context;
import android.view.View;

public class BottomToolsPop extends PaletteBasePop
{
    private ToolsAdapter adapter;
    private View contentView;
    private final Context context;
    private View indicator;
    private int offsetY;
    private BottomToolsPop.BottomToolsPop$OrientationChangedReceive orientationChangedReceive;
    private final View parentView;
    private final List<ToolModel> toolModels;
    
    public BottomToolsPop(final Context context, final View parentView) {
        this.toolModels = (List<ToolModel>)new ArrayList();
        this.context = context;
        this.parentView = parentView;
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            this.contentView = layoutInflater.inflate(R$layout.component_popuwindow_tools_bottom, (ViewGroup)null);
        }
        this.setContentView(this.contentView);
        this.setWidth(parentView.getWidth());
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.initView();
    }
    
    private void initView() {
        final GridViewMaxHeight gridViewMaxHeight = (GridViewMaxHeight)this.contentView.findViewById(R$id.grid_view);
        this.indicator = this.contentView.findViewById(R$id.indicator);
        gridViewMaxHeight.setMaxHeight(SystemUtils.getScreenHeight(this.context) / 2);
        gridViewMaxHeight.setAdapter((ListAdapter)(this.adapter = new ToolsAdapter(this.context, (List)this.toolModels)));
        gridViewMaxHeight.setOnItemClickListener((AdapterView$OnItemClickListener)new BottomToolsPop$1(this));
    }
    
    private void registerReceiver() {
        this.orientationChangedReceive = new BottomToolsPop.BottomToolsPop$OrientationChangedReceive(this, (BottomToolsPop$1)null);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        this.context.registerReceiver((BroadcastReceiver)this.orientationChangedReceive, intentFilter);
    }
    
    private void unregisterReceiver() {
        final BottomToolsPop.BottomToolsPop$OrientationChangedReceive orientationChangedReceive = this.orientationChangedReceive;
        if (orientationChangedReceive != null) {
            this.context.unregisterReceiver((BroadcastReceiver)orientationChangedReceive);
        }
    }
    
    public void dismiss() {
        super.dismiss();
        this.unregisterReceiver();
    }
    
    public View getIndicatorView() {
        return this.indicator;
    }
    
    public boolean isNeedMove() {
        return true;
    }
    
    public void setOldCheck(final ToolEnum checkTool) {
        this.adapter.setCheckTool(checkTool);
    }
    
    public void setToolModels(final List<ToolModel> list) {
        this.toolModels.clear();
        if (list != null) {
            this.toolModels.addAll((Collection)list);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    public void show(final View view) {
        final View indicatorView = this.getIndicatorView();
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        if (this.isNeedMove()) {
            final float n = (float)array[0];
            final NotchContext notchContext = new NotchContext((Activity)this.context, this.contentView);
            float translationX = n;
            if (notchContext.isNotchScreen()) {
                translationX = n - notchContext.getNotchSize()[1];
            }
            indicatorView.setTranslationX(translationX);
        }
        this.showAtLocation(view, 85, 0, this.offsetY = view.getHeight());
        this.registerReceiver();
    }
}
