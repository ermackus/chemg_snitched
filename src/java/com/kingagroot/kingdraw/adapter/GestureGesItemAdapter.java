package com.kingagroot.kingdraw.adapter;

import android.widget.ImageView;
import com.kingagroot.kingdraw.widget.CenterCheckBox;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class GestureGesItemAdapter extends BaseAdapter
{
    private final Context context;
    private final List<GestureGroupModel> gestureGroupModels;
    private GestureGroupModel selectModel;
    
    public GestureGesItemAdapter(final Context context, final List<GestureGroupModel> gestureGroupModels) {
        this.context = context;
        this.gestureGroupModels = gestureGroupModels;
    }
    
    public int getCount() {
        return this.gestureGroupModels.size();
    }
    
    public GestureGroupModel getItem(final int n) {
        return (GestureGroupModel)this.gestureGroupModels.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131493079, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GestureGroupModel item = this.getItem(n);
        tag.ivItem.setImageResource(GBitmapUtils.getResId(this.context, item.getGestureRes()));
        tag.chbItem.setChecked(this.selectModel == item);
        return inflate;
    }
    
    public void setSelection(final GestureGroupModel selectModel) {
        this.selectModel = selectModel;
    }
    
    protected static class ViewHolder
    {
        private final CenterCheckBox chbItem;
        private final ImageView ivItem;
        
        public ViewHolder(final View view) {
            this.ivItem = (ImageView)view.findViewById(2131296923);
            this.chbItem = (CenterCheckBox)view.findViewById(2131296508);
        }
    }
}
