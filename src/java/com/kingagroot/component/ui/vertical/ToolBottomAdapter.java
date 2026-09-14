package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.widget.LinearLayout;
import com.kingagroot.component.ui.view.GImageButton;
import com.kingagroot.component.ui.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.graphics.drawable.Drawable;
import com.kingagroot.component.ui.R$drawable;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import com.kingagroot.component.ui.vertical.model.ToolBaseModel;
import java.util.List;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class ToolBottomAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private String checkTool;
    private boolean chiralCheck;
    private boolean chiralEnable;
    private final Context context;
    private boolean isFormatCheck;
    private boolean isLineCheck;
    private boolean isSelect;
    private ToolBottomAdapter.ToolBottomAdapter$OnListItemClickListener listener;
    private final List<ToolBaseModel> toolModels;
    
    public ToolBottomAdapter(final Context context, final List<ToolBaseModel> toolModels) {
        this.context = context;
        this.toolModels = toolModels;
    }
    
    public int getItemCount() {
        return this.toolModels.size();
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        final ToolBaseModel toolBaseModel = (ToolBaseModel)this.toolModels.get(n);
        viewHolder.ibtBottomItem.setButtonDrawable(GBitmapUtils.getResId(this.context, toolBaseModel.getResourceName()));
        viewHolder.ibtBottomItem.setOnClickListener((View$OnClickListener)new ToolBottomAdapter$1(this, n));
        if (toolBaseModel.getResourceName().equals((Object)ToolDataManage.PIC_FORMAT)) {
            if (this.isFormatCheck) {
                viewHolder.llBottomToolItem.setBackgroundResource(R$drawable.bg_gray_round);
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_draw_96_sel);
            }
            else {
                viewHolder.llBottomToolItem.setBackground((Drawable)null);
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_draw_96_nor);
            }
        }
        else if (toolBaseModel.getResourceName().equals((Object)ToolDataManage.PIC_GRID_LINE)) {
            if (this.isLineCheck) {
                viewHolder.llBottomToolItem.setBackgroundResource(R$drawable.bg_gray_round);
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_draw_grids_sel);
            }
            else {
                viewHolder.llBottomToolItem.setBackground((Drawable)null);
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_draw_grids_nor);
            }
        }
        else {
            final String checkTool = this.checkTool;
            if (checkTool != null && checkTool.equals((Object)toolBaseModel.getResourceName())) {
                viewHolder.llBottomToolItem.setBackgroundResource(R$drawable.bg_gray_round);
            }
            else {
                viewHolder.llBottomToolItem.setBackground((Drawable)null);
            }
        }
        if ("ic_iupac_name".equals((Object)toolBaseModel.getResourceName())) {
            if (this.isSelect) {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_iupac_name);
            }
            else {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_iupac_name_no);
            }
            viewHolder.ibtBottomItem.setEnabled(this.isSelect);
        }
        else {
            viewHolder.ibtBottomItem.setEnabled(true);
        }
        if (ToolDataManage.PIC_PREDICTION.equals((Object)toolBaseModel.getResourceName())) {
            if (this.isSelect) {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_forecast_default);
            }
            else {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_forecast_disabled);
            }
            viewHolder.ibtBottomItem.setEnabled(this.isSelect);
        }
        else {
            viewHolder.ibtBottomItem.setEnabled(true);
        }
        if ("ic_chiral_disable".equals((Object)toolBaseModel.getResourceName())) {
            if (!this.chiralEnable) {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_chiral_disable);
                viewHolder.llBottomToolItem.setBackground((Drawable)null);
            }
            else if (this.chiralCheck) {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_chiral_sel);
                viewHolder.llBottomToolItem.setBackgroundResource(R$drawable.bg_gray_round);
            }
            else {
                viewHolder.ibtBottomItem.setButtonDrawable(R$drawable.ic_chiral_nor);
                viewHolder.llBottomToolItem.setBackground((Drawable)null);
            }
            viewHolder.ibtBottomItem.setEnabled(this.chiralEnable);
        }
        else {
            viewHolder.ibtBottomItem.setEnabled(true);
        }
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.adapter_bottom_tool, viewGroup, false));
    }
    
    public void setCheckTool(final String checkTool) {
        this.checkTool = checkTool;
    }
    
    public void setChiralEnable(final boolean chiralEnable, final boolean chiralCheck) {
        this.chiralEnable = chiralEnable;
        this.chiralCheck = chiralCheck;
    }
    
    public void setFormatCheck(final boolean isFormatCheck) {
        this.isFormatCheck = isFormatCheck;
    }
    
    public void setLineCheck(final boolean isLineCheck) {
        this.isLineCheck = isLineCheck;
    }
    
    public void setNameEnable(final boolean isSelect) {
        this.isSelect = isSelect;
    }
    
    public void setOnItemClickListener(final ToolBottomAdapter.ToolBottomAdapter$OnListItemClickListener listener) {
        this.listener = listener;
    }
    
    public static class ViewHolder extends RecyclerView$ViewHolder
    {
        private final GImageButton ibtBottomItem;
        private final LinearLayout llBottomToolItem;
        
        public ViewHolder(final View view) {
            super(view);
            this.llBottomToolItem = (LinearLayout)view.findViewById(R$id.ll_bottom_tool_item);
            this.ibtBottomItem = (GImageButton)view.findViewById(R$id.ibt_bottom_item);
        }
    }
}
