package com.kingagroot.component.ui.widget.sup;

import android.widget.TextView;
import android.widget.ImageView;
import com.goodsrc.ui.library.widget.SquareLayout;
import android.text.SpannableStringBuilder;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.text.TextUtils;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.utils.base64imageloader.Base64BitmapLoader;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class SupListAdapter extends BaseAdapter
{
    private Context context;
    private List<GSGroupModel> gSups;
    private Base64BitmapLoader loader;
    
    public SupListAdapter(final Context context, final List<GSGroupModel> gSups) {
        this.context = context;
        this.gSups = gSups;
        this.loader = Base64BitmapLoader.getInstance();
    }
    
    public int getCount() {
        return this.gSups.size();
    }
    
    public GSGroupModel getItem(final int n) {
        return (GSGroupModel)this.gSups.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(R.layout.component_adapter_sup_list, (ViewGroup)null);
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
            SpanUtil.setSpanFontSize(htmlToSpan, 18);
            tag.tvSupName.setText((CharSequence)htmlToSpan);
        }
        if (item.getIsCollection() == 1) {
            tag.content.setBackgroundResource(R.drawable.shape_sup_collect);
        }
        else {
            tag.content.setBackgroundResource(R.drawable.shape_edit_table);
        }
        this.loader.loadImage(item.getName(), item.getBase64String(), tag.imgSup);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        final Base64BitmapLoader loader = this.loader;
        if (loader != null) {
            loader.recycle();
        }
    }
    
    public void onDestroy() {
        final Base64BitmapLoader loader = this.loader;
        if (loader != null) {
            loader.recycle();
        }
    }
    
    static class ViewHolder
    {
        private SquareLayout content;
        private ImageView imgSup;
        private TextView tvSupName;
        
        ViewHolder(final View view) {
            this.content = (SquareLayout)view.findViewById(R.id.content);
            this.imgSup = (ImageView)view.findViewById(R.id.img_sup);
            this.tvSupName = (TextView)view.findViewById(R.id.tv_sup_name);
        }
    }
}
