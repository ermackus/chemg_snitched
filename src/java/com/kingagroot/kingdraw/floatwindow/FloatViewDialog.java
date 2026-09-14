package com.kingagroot.kingdraw.floatwindow;

import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.Window;
import com.goodsrc.library.utils.StatusBarUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.lzf.easyfloat.EasyFloat;
import com.kingagroot.kingdraw.ui.workstation.WorkWebCountView;
import com.kingagroot.kingdraw.ui.workstation.TemplateActivity;
import com.kingagroot.kingdraw.ui.workstation.PediasActivity;
import com.goodsrc.library.utils.SPUtil;
import java.io.Serializable;
import com.kingagroot.kingdraw.ui.workstation.WebStationActivity;
import com.kingagroot.kingdraw.config.NetConfig;
import android.content.Intent;
import android.widget.AdapterView;
import android.view.View;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import android.widget.ListAdapter;
import com.kingagroot.kingdraw.ui.workstation.WorkStationModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnClickListener;
import android.content.Context;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.app.Dialog;

public class FloatViewDialog extends Dialog
{
    private RelativeLayout flContent;
    private ListView lvWindow;
    
    public FloatViewDialog(final Context context) {
        super(context);
    }
    
    private void initView() {
        this.flContent = (RelativeLayout)this.findViewById(2131296689);
        this.lvWindow = (ListView)this.findViewById(2131297047);
        this.flContent.setOnClickListener((View$OnClickListener)new _$$Lambda$FloatViewDialog$Ooy3NTsRiNl_HlR384XfooplAPM(this));
        this.lvWindow.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$FloatViewDialog$RUftB_hDryms2slhQkFjMrhUqqg(this));
    }
    
    private void setData() {
        final WorkWindowDbiMpl workWindowDbiMpl = new WorkWindowDbiMpl();
        final List allWorkItems = ((WorkWindowDbi)workWindowDbiMpl).getAllWorkItems();
        if (allWorkItems.size() > 0) {
            final FloatAdapter adapter = new FloatAdapter(this.getContext(), (List<WorkStationModel>)allWorkItems);
            this.lvWindow.setAdapter((ListAdapter)adapter);
            adapter.setOnDeleteItem((OnDeleteItem)new _$$Lambda$FloatViewDialog$7PXd_lB_LxZKSRhPOrBF4gom0F0(this, allWorkItems, adapter, (WorkWindowDbi)workWindowDbiMpl));
        }
    }
    
    public void dismiss() {
        super.dismiss();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2131493021);
        this.setCanceledOnTouchOutside(false);
        final Window window = this.getWindow();
        window.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        window.setLayout(-1, -1);
        window.setGravity(8388613);
        StatusBarUtil.setTransparent(window);
        StatusBarUtil.setTextDark(window, true);
        this.initView();
        this.setData();
    }
    
    public void setContentBackground(final Drawable background) {
        this.flContent.setBackground(background);
    }
    
    static class FloatAdapter extends BaseAdapter
    {
        private final Context context;
        private OnDeleteItem onDeleteItem;
        private final List<WorkStationModel> workStationModels;
        
        public FloatAdapter(final Context context, final List<WorkStationModel> workStationModels) {
            this.context = context;
            this.workStationModels = workStationModels;
        }
        
        public int getCount() {
            return this.workStationModels.size();
        }
        
        public Object getItem(final int n) {
            return this.workStationModels.get(n);
        }
        
        public long getItemId(final int n) {
            return 0L;
        }
        
        public View getView(final int n, View inflate, final ViewGroup viewGroup) {
            final LayoutInflater from = LayoutInflater.from(this.context);
            ViewHolder tag;
            if (inflate == null) {
                inflate = from.inflate(2131493077, (ViewGroup)null);
                tag = new ViewHolder(inflate);
                inflate.setTag((Object)tag);
            }
            else {
                tag = (ViewHolder)inflate.getTag();
            }
            final WorkStationModel workStationModel = (WorkStationModel)this.workStationModels.get(n);
            Glide.with(this.context).load(workStationModel.getLogo()).into((ImageView)tag.icon);
            final String language = LibraryApplication.getLanguage();
            if (language.equals((Object)LanguageTool.SER_ZH)) {
                tag.backItem.setText((CharSequence)workStationModel.getNameCN());
            }
            else if (language.equals((Object)LanguageTool.SER_EN)) {
                tag.backItem.setText((CharSequence)workStationModel.getNameEN());
            }
            tag.closeItem.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, workStationModel) {
                final FloatAdapter this$0;
                final WorkStationModel val$model;
                
                public void onClick(final View view) {
                    if (this.this$0.onDeleteItem != null) {
                        this.this$0.onDeleteItem.onDeleteClickItem(this.val$model);
                    }
                }
            });
            return inflate;
        }
        
        public void setOnDeleteItem(final OnDeleteItem onDeleteItem) {
            this.onDeleteItem = onDeleteItem;
        }
        
        public interface OnDeleteItem
        {
            void onDeleteClickItem(final WorkStationModel p0);
        }
        
        static class ViewHolder
        {
            private final TextView backItem;
            private final ImageButton closeItem;
            private final RoundAndCircleImageView icon;
            
            public ViewHolder(final View view) {
                this.icon = (RoundAndCircleImageView)view.findViewById(2131296834);
                this.backItem = (TextView)view.findViewById(2131296357);
                this.closeItem = (ImageButton)view.findViewById(2131296533);
            }
        }
    }
}
