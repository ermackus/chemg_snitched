package com.kingagroot.kingdraw.adapter;

import android.widget.ImageView;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import java.util.List;
import android.widget.BaseAdapter;

public class GestureAdapter extends BaseAdapter
{
    private final List<GestureGroupModel> gestureGroupModels;
    private final Context mContext;
    private UnboundItemOnClickListener unboundItemOnClickListener;
    
    public GestureAdapter(final Context mContext, final List<GestureGroupModel> gestureGroupModels) {
        this.mContext = mContext;
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
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131493078, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GestureGroupModel item = this.getItem(n);
        tag.ivGesture.setImageResource(GBitmapUtils.getResId(this.mContext, item.getGestureRes()));
        tag.ivValue.setImageResource(GBitmapUtils.getResId(this.mContext, item.getBondRes()));
        tag.ivBind.setOnClickListener((View$OnClickListener)new _$$Lambda$GestureAdapter$dSJNUKauRiVY0LX0OSQfTt1w_PM(this, item));
        return inflate;
    }
    
    public void setUnboundItemClickListener(final UnboundItemOnClickListener unboundItemOnClickListener) {
        this.unboundItemOnClickListener = unboundItemOnClickListener;
    }
    
    public interface UnboundItemOnClickListener
    {
        void unboundItemClick(final GestureGroupModel p0);
    }
    
    protected static class ViewHolder
    {
        private final ImageView ivBind;
        private final ImageView ivGesture;
        private final ImageView ivValue;
        
        public ViewHolder(final View view) {
            this.ivValue = (ImageView)view.findViewById(2131296944);
            this.ivGesture = (ImageView)view.findViewById(2131296920);
            this.ivBind = (ImageView)view.findViewById(2131296916);
        }
    }
}
