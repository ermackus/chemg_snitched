package com.kingagroot.component.ui.widget.sup;

import com.goodsrc.library.utils.SystemUtils;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Collection;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.view.View;
import com.kingagroot.component.ui.R;
import com.kingagroot.component.ui.db.impl.GSGroupModelDBImpl;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListView;
import android.widget.ImageButton;
import com.kingagroot.component.ui.db.GSGroupModelDBI;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import android.widget.LinearLayout;

public class SupCommonView extends LinearLayout
{
    private SupCollectAdapter adapter;
    private final List<GSGroupModel> gSups;
    GSGroupModelDBI gsGroupModelDBI;
    private ImageButton ibtClose;
    private ListView lvData;
    private OnSupSelectListener onSupSelectListener;
    
    public SupCommonView(final Context context) {
        this(context, null);
    }
    
    public SupCommonView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SupCommonView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.gSups = (List<GSGroupModel>)new ArrayList();
        this.gsGroupModelDBI = (GSGroupModelDBI)new GSGroupModelDBImpl();
        this.initView(View.inflate(context, R.layout.component_navigationview_periodic_collect, (ViewGroup)this));
    }
    
    private void initView(final View view) {
        this.lvData = (ListView)this.findViewById(R.id.lv_data);
        this.ibtClose = (ImageButton)this.findViewById(R.id.ibt_close);
        final SupCollectAdapter supCollectAdapter = new SupCollectAdapter(this.getContext(), this.gSups);
        this.adapter = supCollectAdapter;
        this.lvData.setAdapter((ListAdapter)supCollectAdapter);
        final List<GSGroupModel> collectionSupModel = this.gsGroupModelDBI.getCollectionSupModel();
        if (collectionSupModel != null) {
            this.gSups.addAll((Collection)collectionSupModel);
        }
        this.adapter.notifyDataSetChanged();
        this.adapter.setOnSupCollectListener((SupCollectAdapter.OnSupCollectListener)new SupCommonView$1(this));
        this.lvData.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final SupCommonView this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                this.this$0.onSupSelectListener.onSelectSup(this.this$0.adapter.getItem(n));
            }
        });
        this.ibtClose.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SupCommonView this$0;
            
            public void onClick(final View view) {
                if (this.this$0.onSupSelectListener != null) {
                    this.this$0.onSupSelectListener.onClose();
                }
            }
        });
    }
    
    public void notifyData() {
        this.gSups.clear();
        final List<GSGroupModel> collectionSupModel = this.gsGroupModelDBI.getCollectionSupModel();
        if (collectionSupModel != null) {
            this.gSups.addAll((Collection)collectionSupModel);
        }
        this.adapter.notifyDataSetChanged();
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
    
    public void setOnSupSelectListener(final OnSupSelectListener onSupSelectListener) {
        this.onSupSelectListener = onSupSelectListener;
    }
}
