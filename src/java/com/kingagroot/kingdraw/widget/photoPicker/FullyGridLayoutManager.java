package com.kingagroot.kingdraw.widget.photoPicker;

import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView$LayoutParams;
import androidx.recyclerview.widget.RecyclerView$Recycler;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView$State;
import androidx.recyclerview.widget.GridLayoutManager;

public class FullyGridLayoutManager extends GridLayoutManager
{
    private final int[] mMeasuredDimension;
    final RecyclerView$State mState;
    
    public FullyGridLayoutManager(final Context context, final int n) {
        super(context, n);
        this.mMeasuredDimension = new int[2];
        this.mState = new RecyclerView$State();
    }
    
    public FullyGridLayoutManager(final Context context, final int n, final int n2, final boolean b) {
        super(context, n, n2, b);
        this.mMeasuredDimension = new int[2];
        this.mState = new RecyclerView$State();
    }
    
    private void measureScrapChild(final RecyclerView$Recycler recyclerView$Recycler, final int n, final int n2, final int n3, final int[] array) {
        if (n < this.mState.getItemCount()) {
            try {
                final View viewForPosition = recyclerView$Recycler.getViewForPosition(0);
                final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(n2, this.getPaddingLeft() + this.getPaddingRight(), recyclerView$LayoutParams.width), ViewGroup.getChildMeasureSpec(n3, this.getPaddingTop() + this.getPaddingBottom(), recyclerView$LayoutParams.height));
                array[0] = viewForPosition.getMeasuredWidth() + recyclerView$LayoutParams.leftMargin + recyclerView$LayoutParams.rightMargin;
                array[1] = viewForPosition.getMeasuredHeight() + recyclerView$LayoutParams.bottomMargin + recyclerView$LayoutParams.topMargin;
                recyclerView$Recycler.recycleView(viewForPosition);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void onMeasure(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State, int n, int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        final int itemCount = this.getItemCount();
        final int spanCount = this.getSpanCount();
        int i = 0;
        n2 = 0;
        n = 0;
        while (i < itemCount) {
            this.measureScrapChild(recyclerView$Recycler, i, View$MeasureSpec.makeMeasureSpec(i, 0), View$MeasureSpec.makeMeasureSpec(i, 0), this.mMeasuredDimension);
            if (this.getOrientation() == 0) {
                int n3 = n2;
                if (i % spanCount == 0) {
                    n3 = n2 + this.mMeasuredDimension[0];
                }
                n2 = n3;
                if (i == 0) {
                    n = this.mMeasuredDimension[1];
                    n2 = n3;
                }
            }
            else {
                int n4 = n;
                if (i % spanCount == 0) {
                    n4 = n + this.mMeasuredDimension[1];
                }
                n = n4;
                if (i == 0) {
                    n2 = this.mMeasuredDimension[0];
                    n = n4;
                }
            }
            ++i;
        }
        if (mode == 1073741824) {
            n2 = size;
        }
        if (mode2 == 1073741824) {
            n = size2;
        }
        this.setMeasuredDimension(n2, n);
    }
}
