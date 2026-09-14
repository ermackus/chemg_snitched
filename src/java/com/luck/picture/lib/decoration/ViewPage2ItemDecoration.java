package com.luck.picture.lib.decoration;

import androidx.recyclerview.widget.RecyclerView$State;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;

public class ViewPage2ItemDecoration extends RecyclerView$ItemDecoration
{
    private final int spacing;
    private final int spanCount;
    
    public ViewPage2ItemDecoration(final int spanCount, final int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }
    
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        final int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        final int spanCount = this.spanCount;
        final int n = childAdapterPosition % spanCount;
        final int spacing = this.spacing;
        rect.left = spacing - n * spacing / spanCount;
        rect.right = (n + 1) * this.spacing / this.spanCount;
    }
}
