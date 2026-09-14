package com.kingagroot.kingdraw.dialog;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.kingagroot.kingdraw.interfaces.impl.DrawSearchHisDBImpl;
import android.widget.TextView;
import java.util.List;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import com.goodsrc.ui.library.widget.HorizontalListView;
import com.kingagroot.kingdraw.interfaces.DrawSearchHisDBI;
import android.content.Context;
import android.view.View;
import com.kingagroot.kingdraw.adapter.HistoryFormulaAdapter;
import android.widget.PopupWindow;

public class HistoryPalettePop extends PopupWindow
{
    private HistoryFormulaAdapter adapter;
    private View anchor;
    private View conentView;
    Context context;
    private final DrawSearchHisDBI drawSearchHisDBI;
    boolean isRegisterReceiver;
    private HorizontalListView listView;
    GFileSearchModel model;
    private List<GFileSearchModel> models;
    private OnGetData onGetdata;
    private OrientationChangedReceive orientationChangedReceive;
    private TextView tvEmpty;
    
    public HistoryPalettePop(final Context context) {
        super(context);
        this.drawSearchHisDBI = (DrawSearchHisDBI)new DrawSearchHisDBImpl();
        this.isRegisterReceiver = false;
        this.context = context;
        final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            this.conentView = layoutInflater.inflate(2131493146, (ViewGroup)null);
        }
        this.setContentView(this.conentView);
        this.setWidth(-1);
        this.setHeight(-2);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable((Drawable)new ColorDrawable(-16777216));
        this.init();
    }
    
    private void init() {
        this.listView = (HorizontalListView)this.conentView.findViewById(2131296978);
        final TextView textView = (TextView)this.conentView.findViewById(2131297544);
        this.tvEmpty = (TextView)this.conentView.findViewById(2131297575);
        final List<GFileSearchModel> hisData = this.drawSearchHisDBI.getHisData();
        this.models = hisData;
        if (hisData != null && hisData.size() > 0) {
            this.tvEmpty.setVisibility(8);
            final HistoryFormulaAdapter historyFormulaAdapter = new HistoryFormulaAdapter(this.context, (List)this.models);
            this.adapter = historyFormulaAdapter;
            this.listView.setAdapter((ListAdapter)historyFormulaAdapter);
        }
        else {
            this.tvEmpty.setVisibility(0);
        }
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final HistoryPalettePop this$0;
            
            public void onClick(final View view) {
                this.this$0.drawSearchHisDBI.clear();
                this.this$0.tvEmpty.setVisibility(0);
            }
        });
        this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final HistoryPalettePop this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                this.this$0.dismiss();
                final HistoryPalettePop this$0 = this.this$0;
                this$0.model = (GFileSearchModel)this$0.models.get(n);
                this.this$0.onGetdata.onGet(this.this$0.model);
            }
        });
    }
    
    private void registerReceiver() {
        if (this.isRegisterReceiver) {
            return;
        }
        this.orientationChangedReceive = new OrientationChangedReceive();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        this.context.registerReceiver((BroadcastReceiver)this.orientationChangedReceive, intentFilter);
        this.isRegisterReceiver = true;
    }
    
    private void unregisterReceiver() {
        final OrientationChangedReceive orientationChangedReceive = this.orientationChangedReceive;
        if (orientationChangedReceive != null && this.isRegisterReceiver) {
            this.context.unregisterReceiver((BroadcastReceiver)orientationChangedReceive);
            this.isRegisterReceiver = false;
        }
    }
    
    public void dismiss() {
        super.dismiss();
        this.unregisterReceiver();
    }
    
    public void setData(final OnGetData onGetdata) {
        this.onGetdata = onGetdata;
    }
    
    public void showAsDropDown(final View anchor) {
        super.showAsDropDown(anchor);
        this.anchor = anchor;
        this.registerReceiver();
    }
    
    public interface OnGetData
    {
        void onGet(final GFileSearchModel p0);
    }
    
    private class OrientationChangedReceive extends BroadcastReceiver
    {
        final HistoryPalettePop this$0;
        
        private OrientationChangedReceive(final HistoryPalettePop this$0) {
            this.this$0 = this$0;
        }
        
        public void onReceive(final Context context, final Intent intent) {
            final HistoryPalettePop this$0 = this.this$0;
            this$0.update(this$0.anchor, -1, -1);
            if (this.this$0.adapter != null) {
                this.this$0.adapter.notifyDataSetChanged();
            }
        }
    }
}
