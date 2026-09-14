package com.qw.curtain.lib;

import androidx.recyclerview.widget.RecyclerView;
import com.qw.curtain.lib.debug.CurtainDebug;
import android.view.View;
import android.widget.AdapterView;

public class ViewGetter
{
    public static View getFromAdapterView(final AdapterView adapterView, final int n) {
        final View child = adapterView.getChildAt(n - adapterView.getFirstVisiblePosition());
        if (child == null) {
            CurtainDebug.w("Curtain", "your target position may not on screen now");
        }
        return child;
    }
    
    public static View getFromRecyclerView(final RecyclerView recyclerView, final int n) {
        if (recyclerView.getLayoutManager() == null) {
            CurtainDebug.w("Curtain", "recyclerView did not have layoutManager yet");
            return null;
        }
        return recyclerView.getLayoutManager().getChildAt(n);
    }
}
