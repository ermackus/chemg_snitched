package com.kingagroot.component.ui.widget.periodictable;

import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.kingagroot.component.ui.utils.SVGDrawUtils;
import android.content.res.ColorStateList;
import com.kingagroot.component.ui.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.model.GAtom;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class ChemAdapter extends BaseAdapter
{
    private final Context context;
    private final List<GAtom> gAtoms;
    
    public ChemAdapter(final Context context, final List<GAtom> gAtoms) {
        this.context = context;
        this.gAtoms = gAtoms;
    }
    
    public int getCount() {
        final List<GAtom> gAtoms = this.gAtoms;
        if (gAtoms != null) {
            return gAtoms.size();
        }
        return 0;
    }
    
    public GAtom getItem(final int n) {
        return (GAtom)this.gAtoms.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(int colorHex, View inflate, final ViewGroup viewGroup) {
        final LayoutInflater from = LayoutInflater.from(this.context);
        ViewHolder tag;
        if (inflate == null) {
            inflate = from.inflate(R.layout.component_adapter_chem_table, (ViewGroup)null);
            tag = new ViewHolder(inflate);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final GAtom item = this.getItem(colorHex);
        final TextView tvWeight = tag.tvWeight;
        String value;
        if (item.index < 0) {
            value = "";
        }
        else {
            value = String.valueOf(item.index);
        }
        tvWeight.setText((CharSequence)value);
        tag.tvName.setText((CharSequence)item.name);
        tag.tvName.setTextColor(item.colorHex);
        if (item.getIndex() == -1) {
            final Drawable changeColor = SVGDrawUtils.changeColor(this.context.getResources().getDrawable(R.drawable.bg_periodic_table_item), new ColorStateList(new int[][] { new int[0], { -16842919, -16842908 }, { 16842919, 16842908 } }, new int[] { -1, -1, -1 }));
            changeColor.setAlpha(100);
            tag.llContent.setBackground(changeColor);
        }
        else if (item.isCollect()) {
            final int colorHex2 = item.getColorHex();
            colorHex = item.getColorHex();
            final Drawable changeColor2 = SVGDrawUtils.changeColor(this.context.getResources().getDrawable(R.drawable.bg_periodic_table_item), new ColorStateList(new int[][] { new int[0], { -16842919, -16842908 }, { 16842919, 16842908 } }, new int[] { colorHex2, colorHex, -2500135 }));
            changeColor2.setAlpha(100);
            tag.llContent.setBackground(changeColor2);
        }
        else {
            final Drawable changeColor3 = SVGDrawUtils.changeColor(this.context.getResources().getDrawable(R.drawable.bg_periodic_table_item), new ColorStateList(new int[][] { new int[0], { -16842919, -16842908 }, { 16842919, 16842908 } }, new int[] { -1, -1, -2500135 }));
            changeColor3.setAlpha(100);
            tag.llContent.setBackground(changeColor3);
        }
        return inflate;
    }
    
    static class ViewHolder
    {
        LinearLayout llContent;
        TextView tvName;
        TextView tvWeight;
        
        ViewHolder(final View view) {
            this.tvWeight = (TextView)view.findViewById(R.id.tv_weight);
            this.tvName = (TextView)view.findViewById(R.id.tv_name);
            this.llContent = (LinearLayout)view.findViewById(R.id.ll_content);
        }
    }
}
