package com.kingagroot.kingdraw.adapter;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.pay.model.SkuModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class VipAdapter extends BaseAdapter
{
    public static final String TAG = "VipAdapter";
    private final Context context;
    private final List<SkuModel> skuModels;
    
    public VipAdapter(final Context context, final List<SkuModel> skuModels) {
        this.context = context;
        this.skuModels = skuModels;
    }
    
    public int getCount() {
        return this.skuModels.size();
    }
    
    public SkuModel getItem(final int n) {
        return (SkuModel)this.skuModels.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131492956, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final SkuModel item = this.getItem(n);
        tag.tvVipTitle.setText((CharSequence)item.getTitle());
        tag.tvVipPrice.setText((CharSequence)String.valueOf(item.getCost()));
        return inflate;
    }
    
    protected static class ViewHolder
    {
        private final TextView tvVipPrice;
        private final TextView tvVipTitle;
        
        public ViewHolder(final View view) {
            this.tvVipTitle = (TextView)view.findViewById(2131297702);
            this.tvVipPrice = (TextView)view.findViewById(2131297701);
        }
    }
}
