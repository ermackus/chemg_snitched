package com.kingagroot.component.ui.widget.periodictable;

import java.util.Collection;
import com.goodsrc.library.utils.SystemUtils;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.kingagroot.component.ui.db.impl.PeriodicTableDBImpl;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.component.ui.db.PeriodicTableDBI;
import android.widget.ListView;
import android.widget.ImageButton;
import com.kingagroot.component.ui.model.GAtom;
import java.util.List;
import android.widget.LinearLayout;

public class NavPeriodicCollectView extends LinearLayout
{
    private ElementCollectAdapter adapter;
    private final List<GAtom> gAtoms;
    private ImageButton ibtClose;
    private ListView lvAtoms;
    private OnNavPeriodicCollectViewListener onNavPeriodicCollectViewListener;
    private PeriodicTableDBI periodictabledbi;
    
    public NavPeriodicCollectView(final Context context) {
        this(context, null);
    }
    
    public NavPeriodicCollectView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavPeriodicCollectView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.gAtoms = (List<GAtom>)new ArrayList();
        this.initView(View.inflate(context, R.layout.component_navigationview_periodic_collect, (ViewGroup)this));
        this.initData();
    }
    
    private void initData() {
        this.periodictabledbi = (PeriodicTableDBI)new PeriodicTableDBImpl();
        final ElementCollectAdapter elementCollectAdapter = new ElementCollectAdapter(this.getContext(), this.gAtoms);
        this.adapter = elementCollectAdapter;
        this.lvAtoms.setAdapter((ListAdapter)elementCollectAdapter);
        this.lvAtoms.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final NavPeriodicCollectView this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                this.this$0.onNavPeriodicCollectViewListener.onSelectGatom(this.this$0.adapter.getItem(n));
            }
        });
        this.adapter.setOnElementCollectListener((ElementCollectAdapter.OnElementCollectListener)new NavPeriodicCollectView$2(this));
        this.ibtClose.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final NavPeriodicCollectView this$0;
            
            public void onClick(final View view) {
                this.this$0.onNavPeriodicCollectViewListener.onClose();
            }
        });
        this.refreshData();
    }
    
    private void initView(final View view) {
        this.lvAtoms = (ListView)view.findViewById(R.id.lv_data);
        this.ibtClose = (ImageButton)view.findViewById(R.id.ibt_close);
    }
    
    protected void onMeasure(final int n, int n2) {
        super.onMeasure(n, n2);
        final int size = View$MeasureSpec.getSize(n2);
        final int screenDefaultWidth = SystemUtils.getScreenDefaultWidth(this.getContext());
        n2 = size;
        if (size > screenDefaultWidth) {
            n2 = screenDefaultWidth;
        }
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(n2, 1073741824));
    }
    
    public void refreshData() {
        final List<GAtom> collectPeriodicTable = this.periodictabledbi.getCollectPeriodicTable();
        if (collectPeriodicTable != null) {
            this.gAtoms.clear();
            this.gAtoms.addAll((Collection)collectPeriodicTable);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    public void setOnNavPeriodicCollectViewListener(final OnNavPeriodicCollectViewListener onNavPeriodicCollectViewListener) {
        this.onNavPeriodicCollectViewListener = onNavPeriodicCollectViewListener;
    }
    
    public interface OnNavPeriodicCollectViewListener
    {
        void onClose();
        
        void onDeleteCollect(final GAtom p0);
        
        void onSelectGatom(final GAtom p0);
    }
}
