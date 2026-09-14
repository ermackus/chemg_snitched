package com.kingagroot.component.ui.widget.periodictable;

import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.view.View$OnClickListener;
import com.kingagroot.component.ui.utils.SVGDrawUtils;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegate;
import com.kingagroot.component.ui.model.GAtom;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class ElementCollectAdapter extends BaseAdapter
{
    private final Context context;
    private final List<GAtom> gAtoms;
    private OnElementCollectListener onElementCollectListener;
    
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    
    ElementCollectAdapter(final Context context, final List<GAtom> gAtoms) {
        this.context = context;
        this.gAtoms = gAtoms;
    }
    
    public int getCount() {
        final List<GAtom> gAtoms = this.gAtoms;
        if (gAtoms == null) {
            return 0;
        }
        return gAtoms.size();
    }
    
    public GAtom getItem(final int n) {
        return (GAtom)this.gAtoms.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(R.layout.component_adapter_list_periodic, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GAtom item = this.getItem(n);
        tag.tvEnName.setText((CharSequence)item.getName());
        tag.tvEnName.setTextColor(item.getColorHex());
        final Drawable changeColor = SVGDrawUtils.changeColor(this.context, R.drawable.ic_periodic_collect_round, item.getColorHex());
        changeColor.setAlpha(220);
        tag.tvNum.setBackground(changeColor);
        tag.tvNum.setText((CharSequence)String.valueOf(item.getIndex()));
        tag.ibtDelete.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, item) {
            final ElementCollectAdapter this$0;
            final GAtom val$gAtom;
            
            public void onClick(final View view) {
                this.this$0.onElementCollectListener.onDeleteCollect(this.val$gAtom);
            }
        });
        return inflate;
    }
    
    public void setOnElementCollectListener(final OnElementCollectListener onElementCollectListener) {
        this.onElementCollectListener = onElementCollectListener;
    }
    
    public interface OnElementCollectListener
    {
        void onDeleteCollect(final GAtom p0);
    }
    
    static class ViewHolder
    {
        ImageView ibtDelete;
        TextView tvEnName;
        TextView tvNum;
        
        ViewHolder(final View view) {
            this.tvNum = (TextView)view.findViewById(R.id.tv_num);
            this.tvEnName = (TextView)view.findViewById(R.id.tv_en_name);
            (this.ibtDelete = (ImageView)view.findViewById(R.id.ibt_delete)).setFocusable(false);
        }
    }
}
