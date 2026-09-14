package com.kingagroot.component.ui.vertical;

import com.kingagroot.component.ui.R$id;
import android.view.View;
import android.widget.TextView;
import com.kingagroot.component.ui.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View$OnClickListener;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import android.content.Context;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView$Adapter;

public class ColorAdapter extends RecyclerView$Adapter<ViewHolder>
{
    private final List<Integer> colorList;
    private final Context context;
    private ColorAdapter.ColorAdapter$OnColorItemClickListener onColorItemClickListener;
    
    public ColorAdapter(final Context context, final List<Integer> colorList) {
        this.context = context;
        this.colorList = colorList;
    }
    
    public int getItemCount() {
        return this.colorList.size();
    }
    
    public int getItemValue(final int n) {
        return (int)this.colorList.get(n);
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
        viewHolder.tvColor.setBackgroundColor((int)this.colorList.get(n));
        viewHolder.tvColor.setOnClickListener((View$OnClickListener)new ColorAdapter$1(this, n));
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.component_layout_menu_color, viewGroup, false));
    }
    
    public void setOnItemClickListener(final ColorAdapter.ColorAdapter$OnColorItemClickListener onColorItemClickListener) {
        this.onColorItemClickListener = onColorItemClickListener;
    }
    
    public static class ViewHolder extends RecyclerView$ViewHolder
    {
        private final TextView tvColor;
        
        public ViewHolder(final View view) {
            super(view);
            this.tvColor = (TextView)view.findViewById(R$id.tv_color);
        }
    }
}
