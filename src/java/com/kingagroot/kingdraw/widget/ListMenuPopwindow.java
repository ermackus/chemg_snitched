package com.kingagroot.kingdraw.widget;

import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.goodsrc.library.utils.DisplayUtil;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class ListMenuPopwindow extends PopupWindow
{
    int RawX;
    int RawY;
    private final View conentView;
    private final Context context;
    private final ListView listview;
    private final String[] menuItems;
    private OnListMenuPopListener onListMenuPopListener;
    private final ListView parentListView;
    
    public ListMenuPopwindow(final Context context, final String[] menuItems, final ListView parentListView) {
        super(context);
        this.menuItems = menuItems;
        this.context = context;
        this.parentListView = parentListView;
        final View inflate = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493154, (ViewGroup)null);
        this.conentView = inflate;
        (this.listview = (ListView)inflate.findViewById(2131296981)).setAdapter((ListAdapter)new ArrayAdapter(context, 2131492950, (Object[])menuItems));
        this.setContentView(this.conentView);
        this.setWidth(DisplayUtil.dip2px(context, 150.0f));
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        parentListView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener(this) {
            final ListMenuPopwindow this$0;
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                this.this$0.RawX = (int)motionEvent.getRawX();
                this.this$0.RawY = (int)motionEvent.getRawY();
                return false;
            }
        });
        this.listview.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final ListMenuPopwindow this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                this.this$0.onListMenuPopListener.onMenuItemSelect(n);
                this.this$0.dismiss();
            }
        });
    }
    
    public void setOnListMenuPopListener(final OnListMenuPopListener onListMenuPopListener) {
        this.onListMenuPopListener = onListMenuPopListener;
    }
    
    public void show() {
        this.showAtLocation((View)this.parentListView, 51, this.RawX, this.RawY);
    }
    
    public interface OnListMenuPopListener
    {
        void onMenuItemSelect(final int p0);
    }
}
