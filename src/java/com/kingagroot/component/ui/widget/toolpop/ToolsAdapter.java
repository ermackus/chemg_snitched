package com.kingagroot.component.ui.widget.toolpop;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegate;
import com.kingagroot.component.ui.model.ToolModel;
import java.util.List;
import android.content.Context;
import com.kingagroot.component.ui.ToolEnum;
import android.widget.BaseAdapter;

public class ToolsAdapter extends BaseAdapter
{
    private ToolEnum checkTool;
    private final Context context;
    private final List<ToolModel> toolModels;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    public ToolsAdapter(final Context context, final List<ToolModel> toolModels) {
        this.context = context;
        this.toolModels = toolModels;
    }
    
    public int getCount() {
        final List<ToolModel> toolModels = this.toolModels;
        if (toolModels == null) {
            return 0;
        }
        return toolModels.size();
    }
    
    public ToolModel getItem(final int n) {
        return (ToolModel)this.toolModels.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(R.layout.component_adapter_menu_tool, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final ToolModel item = this.getItem(n);
        tag.imgItem.setImageResource(item.getResId());
        final ToolEnum checkTool = this.checkTool;
        if (checkTool != null && checkTool == item.getToolEnum()) {
            tag.content.setBackgroundResource(R.drawable.bg_gray_round);
        }
        else {
            tag.content.setBackgroundDrawable((Drawable)null);
        }
        if (item.getToolEnum() == ToolEnum.GGRID_TOOL || item.getToolEnum() == ToolEnum.GFORMAT_96_SET) {
            if (item.isCheck()) {
                tag.content.setBackgroundResource(R.drawable.bg_gray_round);
            }
            else {
                tag.content.setBackgroundDrawable((Drawable)null);
            }
        }
        return inflate;
    }
    
    public void setCheckTool(final ToolEnum checkTool) {
        this.checkTool = checkTool;
    }
    
    class ViewHolder
    {
        LinearLayout content;
        ImageView imgItem;
        final ToolsAdapter this$0;
        
        public ViewHolder(final ToolsAdapter this$0, final View view) {
            this.this$0 = this$0;
            this.imgItem = (ImageView)view.findViewById(R.id.img_item);
            this.content = (LinearLayout)view.findViewById(R.id.ll_content);
        }
    }
}
