package com.luck.picture.lib.magical;

import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import android.widget.ListView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class BuildRecycleItemViewParams
{
    private static final List<ViewParams> viewParams;
    
    static {
        viewParams = (List)new ArrayList();
    }
    
    public static void clear() {
        if (BuildRecycleItemViewParams.viewParams.size() > 0) {
            BuildRecycleItemViewParams.viewParams.clear();
        }
    }
    
    private static void fillPlaceHolder(final List<View> list, int i, int j, final int n) {
        if (j > 0) {
            while (j >= 1) {
                list.add(0, (Object)null);
                --j;
            }
        }
        if (n < i) {
            for (i = i - 1 - n; i >= 1; --i) {
                list.add((Object)null);
            }
        }
    }
    
    public static void generateViewParams(final ViewGroup viewGroup, final int n) {
        final ArrayList list = new ArrayList();
        final boolean b = viewGroup instanceof RecyclerView;
        int n2;
        if (b) {
            n2 = ((RecyclerView)viewGroup).getChildCount();
        }
        else {
            if (!(viewGroup instanceof ListView)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(viewGroup.getClass().getCanonicalName());
                sb.append(" Must be ");
                sb.append((Object)RecyclerView.class);
                sb.append(" or ");
                sb.append((Object)ListView.class);
                throw new IllegalArgumentException(sb.toString());
            }
            n2 = ((ListView)viewGroup).getChildCount();
        }
        for (int i = 0; i < n2; ++i) {
            final View child = viewGroup.getChildAt(i);
            if (child != null) {
                ((List)list).add((Object)child);
            }
        }
        int n3;
        int n4;
        int n5;
        if (b) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager)((RecyclerView)viewGroup).getLayoutManager();
            if (gridLayoutManager == null) {
                return;
            }
            n3 = gridLayoutManager.getItemCount();
            n4 = gridLayoutManager.findFirstVisibleItemPosition();
            n5 = gridLayoutManager.findLastVisibleItemPosition();
        }
        else {
            final ListView listView = (ListView)viewGroup;
            final ListAdapter adapter = listView.getAdapter();
            if (adapter == null) {
                return;
            }
            n3 = adapter.getCount();
            n4 = listView.getFirstVisiblePosition();
            n5 = listView.getLastVisiblePosition();
        }
        int n6 = n5;
        if (n5 > n3) {
            n6 = n3 - 1;
        }
        fillPlaceHolder((List<View>)list, n3, n4, n6);
        BuildRecycleItemViewParams.viewParams.clear();
        for (int j = 0; j < ((List)list).size(); ++j) {
            final View view = (View)((List)list).get(j);
            final ViewParams viewParams = new ViewParams();
            if (view == null) {
                viewParams.setLeft(0);
                viewParams.setTop(0);
                viewParams.setWidth(0);
                viewParams.setHeight(0);
            }
            else {
                final int[] array = new int[2];
                view.getLocationOnScreen(array);
                viewParams.setLeft(array[0]);
                viewParams.setTop(array[1] - n);
                viewParams.setWidth(view.getWidth());
                viewParams.setHeight(view.getHeight());
            }
            BuildRecycleItemViewParams.viewParams.add((Object)viewParams);
        }
    }
    
    public static ViewParams getItemViewParams(final int n) {
        ViewParams viewParams;
        if (BuildRecycleItemViewParams.viewParams.size() > n) {
            viewParams = (ViewParams)BuildRecycleItemViewParams.viewParams.get(n);
        }
        else {
            viewParams = null;
        }
        return viewParams;
    }
}
