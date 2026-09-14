package com.kingagroot.component.ui.widget.sup;

import android.text.SpannableStringBuilder;
import com.kingagroot.component.ui.widget.richinput.SpanUtil;
import android.text.TextUtils;
import com.goodsrc.library.utils.SystemUtils;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import com.kingagroot.component.ui.utils.SVGDrawUtils;
import android.view.View$OnClickListener;
import android.widget.ImageButton;
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
import android.widget.TextView;
import android.widget.ListView;
import com.kingagroot.component.ui.db.GSGroupModelDBI;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import android.widget.LinearLayout;

public class SupCollectView extends LinearLayout
{
    private List<GSGroupModel> gSups;
    private GSGroupModelDBI groupModelDB;
    private GSGroupModel hisSup;
    private ListView lvSup;
    private OnSupMenuListener onSupMenuListener;
    private OnSupSelectListener onSupSelectListener;
    private SupCollectAdapter supCollectAdapter;
    private TextView tvUseSupName;
    
    public SupCollectView(final Context context) {
        this(context, null);
    }
    
    public SupCollectView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SupCollectView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.gSups = (List<GSGroupModel>)new ArrayList();
        this.groupModelDB = (GSGroupModelDBI)new GSGroupModelDBImpl();
        this.initView(View.inflate(context, R.layout.component_layout_menu_sup, (ViewGroup)this));
        this.initData();
    }
    
    private void initData() {
        final SupCollectAdapter supCollectAdapter = new SupCollectAdapter(this.getContext(), this.gSups);
        this.supCollectAdapter = supCollectAdapter;
        this.lvSup.setAdapter((ListAdapter)supCollectAdapter);
        final List<GSGroupModel> collectionSupModel = this.groupModelDB.getCollectionSupModel();
        if (collectionSupModel != null) {
            this.gSups.addAll((Collection)collectionSupModel);
        }
        this.supCollectAdapter.notifyDataSetChanged();
        this.supCollectAdapter.setOnSupCollectListener((SupCollectAdapter.OnSupCollectListener)new SupCollectView$4(this));
        this.lvSup.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final SupCollectView this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final SupCollectView this$0 = this.this$0;
                this$0.hisSup = this$0.supCollectAdapter.getItem(n);
                final SupCollectView this$2 = this.this$0;
                this$2.setChoiceSup(this$2.hisSup);
                this.this$0.onSupSelectListener.onSelectSup(this.this$0.hisSup);
            }
        });
    }
    
    private void initView(final View view) {
        this.tvUseSupName = (TextView)view.findViewById(R.id.tv_use_sup_name);
        this.lvSup = (ListView)view.findViewById(R.id.lv_sup);
        final TextView textView = (TextView)view.findViewById(R.id.tv_more);
        final ImageButton imageButton = (ImageButton)view.findViewById(R.id.ibt_close_sup);
        this.tvUseSupName.setVisibility(8);
        imageButton.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SupCollectView this$0;
            
            public void onClick(final View view) {
                if (this.this$0.onSupMenuListener != null) {
                    this.this$0.onSupMenuListener.onCloseOnClick();
                }
            }
        });
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SupCollectView this$0;
            
            public void onClick(final View view) {
                if (this.this$0.onSupMenuListener != null) {
                    this.this$0.onSupMenuListener.onOpenSupViewClick();
                }
            }
        });
        this.tvUseSupName.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SupCollectView this$0;
            
            public void onClick(final View view) {
                this.this$0.onSupSelectListener.onSelectSup(this.this$0.hisSup);
            }
        });
        this.tvUseSupName.setCompoundDrawablesWithIntrinsicBounds(SVGDrawUtils.changeColor(this.getContext(), R.drawable.ic_select_nor, -16399360), (Drawable)null, (Drawable)null, (Drawable)null);
    }
    
    public void notifyData() {
        this.gSups.clear();
        final List<GSGroupModel> collectionSupModel = this.groupModelDB.getCollectionSupModel();
        if (collectionSupModel != null) {
            this.gSups.addAll((Collection)collectionSupModel);
        }
        this.supCollectAdapter.notifyDataSetChanged();
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
    
    public void restSup() {
        this.tvUseSupName.setText((CharSequence)"");
        this.tvUseSupName.setVisibility(8);
    }
    
    public void setChoiceSup(final GSGroupModel hisSup) {
        if (hisSup != null) {
            if (this.tvUseSupName.getVisibility() == 8) {
                this.tvUseSupName.setVisibility(0);
            }
            this.hisSup = hisSup;
            if (TextUtils.isEmpty((CharSequence)hisSup.getNameHtml())) {
                this.tvUseSupName.setText((CharSequence)hisSup.getName());
            }
            else {
                final SpannableStringBuilder htmlToSpan = SpanUtil.htmlToSpan(hisSup.getNameHtml());
                SpanUtil.setSpanFontSize(htmlToSpan, 24);
                this.tvUseSupName.setText((CharSequence)htmlToSpan);
            }
        }
    }
    
    public void setOnSupMenuListener(final OnSupMenuListener onSupMenuListener) {
        this.onSupMenuListener = onSupMenuListener;
    }
    
    public void setOnSupSelectListener(final OnSupSelectListener onSupSelectListener) {
        this.onSupSelectListener = onSupSelectListener;
    }
    
    public interface OnSupMenuListener
    {
        void onCloseOnClick();
        
        void onOpenSupViewClick();
    }
}
