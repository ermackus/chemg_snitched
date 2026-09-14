package com.kingagroot.component.ui.widget.sup;

import android.widget.TextView;
import android.widget.ImageButton;
import android.text.SpannableStringBuilder;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.text.TextUtils;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class SupCollectAdapter extends BaseAdapter
{
    private final Context context;
    private final List<GSGroupModel> gsGroupModels;
    private OnSupCollectListener onSupCollectListener;
    
    public SupCollectAdapter(final Context context, final List<GSGroupModel> gsGroupModels) {
        this.context = context;
        this.gsGroupModels = gsGroupModels;
    }
    
    public int getCount() {
        return this.gsGroupModels.size();
    }
    
    public GSGroupModel getItem(final int n) {
        return (GSGroupModel)this.gsGroupModels.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(R.layout.component_adapter_list_sup, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GSGroupModel item = this.getItem(n);
        if (TextUtils.isEmpty((CharSequence)item.getNameHtml())) {
            tag.tvSupName.setText((CharSequence)item.getName());
        }
        else {
            final SpannableStringBuilder htmlToSpan = SpanUtil.htmlToSpan(item.getNameHtml());
            SpanUtil.setSpanFontSize(htmlToSpan, 24);
            tag.tvSupName.setText((CharSequence)htmlToSpan);
        }
        tag.ibtDelete.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, item) {
            final SupCollectAdapter this$0;
            final GSGroupModel val$gSup;
            
            public void onClick(final View view) {
                this.this$0.onSupCollectListener.onDeleteCollect(this.val$gSup);
            }
        });
        return inflate;
    }
    
    public void setOnSupCollectListener(final OnSupCollectListener onSupCollectListener) {
        this.onSupCollectListener = onSupCollectListener;
    }
    
    public interface OnSupCollectListener
    {
        void onDeleteCollect(final GSGroupModel p0);
    }
    
    static class ViewHolder
    {
        ImageButton ibtDelete;
        TextView tvSupName;
        
        ViewHolder(final View view) {
            this.tvSupName = (TextView)view.findViewById(R.id.tv_sup_name);
            (this.ibtDelete = (ImageButton)view.findViewById(R.id.ibt_delete)).setFocusable(false);
        }
    }
}
