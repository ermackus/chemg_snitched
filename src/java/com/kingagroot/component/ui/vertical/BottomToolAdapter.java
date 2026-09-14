package com.kingagroot.component.ui.vertical;

import android.widget.LinearLayout;
import com.kingagroot.component.ui.view.GImageButton;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.vertical.model.ToolBaseModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class BottomToolAdapter extends BaseAdapter
{
    protected int clickTemp;
    private final Context mContext;
    private final List<ToolBaseModel> models;
    private int paletteType;
    
    public BottomToolAdapter(final Context mContext, final List<ToolBaseModel> models) {
        this.clickTemp = -1;
        this.mContext = mContext;
        this.models = models;
    }
    
    public int getCount() {
        return this.models.size();
    }
    
    public ToolBaseModel getItem(final int n) {
        return (ToolBaseModel)this.models.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        ViewHolder tag;
        if (inflate == null) {
            inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_bottom_tool, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final ToolBaseModel item = this.getItem(n);
        if (this.isEnabled(n)) {
            tag.ibtBottomItem.setButtonDrawable(GBitmapUtils.getResId(this.mContext, item.getResourceName()));
        }
        else if (n == 4) {
            tag.ibtBottomItem.setButtonDrawable(R.drawable.ic_solid_small_enable);
        }
        else if (n == 5) {
            tag.ibtBottomItem.setButtonDrawable(R.drawable.ic_fd_rectangle_enable);
        }
        else if (n == 6) {
            tag.ibtBottomItem.setButtonDrawable(R.drawable.ic_bracket_enable);
        }
        else if (n == 7) {
            tag.ibtBottomItem.setButtonDrawable(R.drawable.ic_fd_circle_no_enable);
        }
        if (item.getSubChemList() != null && item.getSubChemList().size() > 0) {
            tag.ibtBottomItem.setCornerGravity(2);
            tag.ibtBottomItem.setCornerWidth(GDensityUtil.dp2px(6.0f));
        }
        else if (item.getResourceName().equals((Object)ToolDataManage.PIC_COLOR)) {
            tag.ibtBottomItem.setCornerGravity(2);
            tag.ibtBottomItem.setCornerWidth(GDensityUtil.dp2px(6.0f));
        }
        if (this.clickTemp == n) {
            tag.llBottomToolItem.setBackgroundColor(-1);
        }
        else {
            tag.llBottomToolItem.setBackgroundColor(0);
        }
        return inflate;
    }
    
    public boolean isEnabled(final int n) {
        final int paletteType = this.paletteType;
        final int type_BASE = ToolDataManage.TYPE_BASE;
        boolean b = true;
        if (paletteType != type_BASE) {
            b = (n != 4 && n != 5 && n != 6 && n != 7 && b);
        }
        return b;
    }
    
    public void setPaletteType(final int paletteType) {
        this.paletteType = paletteType;
    }
    
    public void setSelection(final int clickTemp) {
        this.clickTemp = clickTemp;
    }
    
    protected static class ViewHolder
    {
        private final GImageButton ibtBottomItem;
        private final LinearLayout llBottomToolItem;
        
        public ViewHolder(final View view) {
            this.llBottomToolItem = (LinearLayout)view.findViewById(R.id.ll_bottom_tool_item);
            this.ibtBottomItem = (GImageButton)view.findViewById(R.id.ibt_bottom_item);
        }
    }
}
