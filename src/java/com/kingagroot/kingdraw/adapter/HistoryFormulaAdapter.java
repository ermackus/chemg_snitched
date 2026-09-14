package com.kingagroot.kingdraw.adapter;

import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class HistoryFormulaAdapter extends BaseAdapter
{
    Context context;
    List<GFileSearchModel> models;
    
    public HistoryFormulaAdapter(final Context context, final List<GFileSearchModel> models) {
        this.context = context;
        this.models = models;
    }
    
    public int getCount() {
        final List<GFileSearchModel> models = this.models;
        if (models == null) {
            return 0;
        }
        return models.size();
    }
    
    public GFileSearchModel getItem(final int n) {
        return (GFileSearchModel)this.models.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(2131492949, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        ImageLoader.bind(tag.img_history, this.getItem(n).getPicPath());
        return inflate;
    }
    
    class ViewHolder
    {
        ImageView img_history;
        final HistoryFormulaAdapter this$0;
        
        public ViewHolder(final HistoryFormulaAdapter this$0, final View view) {
            this.this$0 = this$0;
            this.img_history = (ImageView)view.findViewById(2131296876);
        }
    }
}
