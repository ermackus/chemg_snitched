package com.luck.picture.lib.decoration;

import androidx.recyclerview.widget.RecyclerView$State;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView$ItemDecoration;

public class GridSpacingItemDecoration extends RecyclerView$ItemDecoration
{
    private final boolean includeEdge;
    private final int spacing;
    private final int spanCount;
    
    public GridSpacingItemDecoration(final int spanCount, final int spacing, final boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }
    
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        final int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        final int spanCount = this.spanCount;
        final int n = childAdapterPosition % spanCount;
        if (this.includeEdge) {
            final int spacing = this.spacing;
            rect.left = spacing - n * spacing / spanCount;
            rect.right = (n + 1) * this.spacing / this.spanCount;
        }
        else {
            rect.left = this.spacing * n / spanCount;
            final int spacing2 = this.spacing;
            rect.right = spacing2 - (n + 1) * spacing2 / this.spanCount;
        }
        if (childAdapterPosition < this.spanCount) {
            rect.top = this.spacing;
        }
        rect.bottom = this.spacing;
    }
}
