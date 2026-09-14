package com.kingagroot.kingdraw.dialog;

import android.view.View$OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.Window;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.GridView;
import android.content.Context;
import android.view.View;
import java.util.List;
import com.kingagroot.kingdraw.model.AppModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import android.app.Dialog;

public class ShareFileDialog extends Dialog
{
    private CommonAdapter<AppModel> adapter;
    private final List<AppModel> appModels;
    private final View conentView;
    private final Context context;
    private final GridView gridView;
    private OnShareFileDialogListener onShareFileDialogListener;
    private final List<AppModel> showModels;
    private final TextView tvCancel;
    
    public ShareFileDialog(final Context context) {
        super(context);
        this.appModels = (List<AppModel>)new ArrayList();
        this.showModels = (List<AppModel>)new ArrayList();
        this.context = context;
        this.conentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493200, (ViewGroup)null);
        this.requestWindowFeature(1);
        this.setContentView(this.conentView);
        this.tvCancel = (TextView)this.conentView.findViewById(2131297543);
        this.gridView = (GridView)this.conentView.findViewById(2131296768);
        final Window window = this.getWindow();
        window.setBackgroundDrawable((Drawable)new ColorDrawable(0));
        window.setWindowAnimations(2131886852);
        window.setGravity(80);
        final int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == 2) {
            this.gridView.setNumColumns(2);
            window.setLayout(GDensityUtil.dp2px(180.0f), -1);
            window.setWindowAnimations(2131886853);
            window.setGravity(5);
        }
        else if (orientation == 1) {
            final int dp2px = GDensityUtil.dp2px(210.0f);
            this.gridView.setNumColumns(4);
            window.setLayout(-1, dp2px);
            window.setWindowAnimations(2131886852);
        }
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.initData();
    }
    
    private void initData() {
        final AppModel appModel = new AppModel();
        appModel.setAppPkgName("com.tencent.mobileqq");
        appModel.setAppIcon(this.context.getResources().getDrawable(2131231466));
        appModel.setAppName(this.context.getString(2131821369));
        final AppModel appModel2 = new AppModel();
        appModel2.setAppPkgName("com.tencent.mm");
        appModel2.setAppIcon(this.context.getResources().getDrawable(2131231469));
        appModel2.setAppName(this.context.getString(2131821382));
        this.showModels.add((Object)appModel2);
        this.showModels.add((Object)appModel);
        final AppModel appModel3 = new AppModel();
        appModel3.setAppPkgName("copylink");
        appModel3.setAppIcon(this.context.getResources().getDrawable(2131231463));
        appModel3.setAppName(this.context.getString(2131821376));
        this.showModels.add((Object)appModel3);
        final ShareFileDialog$1 shareFileDialog$1 = new ShareFileDialog$1(this, this.context, (List)this.showModels, 2131493203);
        this.adapter = (CommonAdapter<AppModel>)shareFileDialog$1;
        this.gridView.setAdapter((ListAdapter)shareFileDialog$1);
        this.gridView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener(this) {
            final ShareFileDialog this$0;
            
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                this.this$0.dismiss();
                final AppModel appModel = (AppModel)this.this$0.adapter.getItem(n);
                if (this.this$0.onShareFileDialogListener != null) {
                    this.this$0.onShareFileDialogListener.onChooser(appModel);
                }
            }
        });
        this.gridView.setAdapter((ListAdapter)this.adapter);
        this.tvCancel.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final ShareFileDialog this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
    }
    
    public void setOnShareFileDialogListener(final OnShareFileDialogListener onShareFileDialogListener) {
        this.onShareFileDialogListener = onShareFileDialogListener;
    }
    
    public interface OnShareFileDialogListener
    {
        void onChooser(final AppModel p0);
    }
}
