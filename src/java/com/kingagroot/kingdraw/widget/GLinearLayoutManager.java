package com.kingagroot.kingdraw.widget;

import androidx.recyclerview.widget.RecyclerView$State;
import androidx.recyclerview.widget.RecyclerView$Recycler;
import android.util.AttributeSet;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

public class GLinearLayoutManager extends LinearLayoutManager
{
    public GLinearLayoutManager(final Context context) {
        super(context);
    }
    
    public GLinearLayoutManager(final Context context, final int n, final boolean b) {
        super(context, n, b);
    }
    
    public GLinearLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    public void onLayoutChildren(final RecyclerView$Recycler recyclerView$Recycler, final RecyclerView$State recyclerView$State) {
        try {
            super.onLayoutChildren(recyclerView$Recycler, recyclerView$State);
        }
        catch (final IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
}
