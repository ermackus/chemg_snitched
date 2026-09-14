package com.kingagroot.kingdraw.dialog;

import android.widget.AdapterView;
import android.view.WindowManager$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.goodsrc.library.utils.SystemUtils;
import java.util.Collection;
import java.util.Iterator;
import android.content.pm.PackageManager;
import java.util.Comparator;
import java.util.Collections;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.GridView;
import android.content.Context;
import android.view.View;
import java.util.List;
import com.kingagroot.kingdraw.model.AppModel;
import com.goodsrc.ui.library.widget.fastAdapter.CommonAdapter;
import android.app.Dialog;

public class ShareSelectDialog extends Dialog
{
    private CommonAdapter<AppModel> adapter;
    private final List<AppModel> appModels;
    private final View conentView;
    private final Context context;
    private final String filType;
    private GridView gridView;
    private int maxHeight;
    private OnShareSelectDialogListener onShareSelectDialogListener;
    private final List<AppModel> showModels;
    private final HashMap<String, Integer> sortMap;
    
    public ShareSelectDialog(final Context context, final String filType) {
        super(context);
        this.appModels = (List<AppModel>)new ArrayList();
        this.showModels = (List<AppModel>)new ArrayList();
        this.sortMap = new HashMap<String, Integer>() {
            final ShareSelectDialog this$0;
            
            {
                this.put((Object)"com.tencent.mm", (Object)1);
                this.put((Object)"com.tencent.mobileqq", (Object)2);
                this.put((Object)"com.alibaba.android.rimet", (Object)3);
            }
        };
        this.context = context;
        this.filType = filType;
        this.conentView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131493202, (ViewGroup)null);
        this.requestWindowFeature(1);
        this.setContentView(this.conentView);
        this.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        this.getWindow().setWindowAnimations(2131886852);
        this.getWindow().setGravity(80);
        this.getWindow().setLayout(-1, -2);
        this.setCanceledOnTouchOutside(true);
        this.initView();
        this.initData();
    }
    
    private List<AppModel> getShareApps(final String type) {
        final Intent intent = new Intent("android.intent.action.SEND", (Uri)null);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType(type);
        final List queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        final ArrayList list = new ArrayList();
        final PackageManager packageManager = this.context.getPackageManager();
        if (queryIntentActivities != null) {
            for (final ResolveInfo resolveInfo : queryIntentActivities) {
                final AppModel appModel = new AppModel();
                appModel.setAppPkgName(resolveInfo.activityInfo.packageName);
                appModel.setAppLauncherClassName(resolveInfo.activityInfo.name);
                appModel.setAppName(resolveInfo.loadLabel(packageManager).toString());
                appModel.setAppIcon(resolveInfo.loadIcon(packageManager));
                ((List)list).add((Object)appModel);
            }
        }
        Collections.sort((List)list, (Comparator)new AppSort((ShareSelectDialog$1)null));
        return (List<AppModel>)list;
    }
    
    private void initData() {
        this.appModels.addAll((Collection)this.getShareApps(this.filType));
        final Iterator iterator = this.appModels.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            this.showModels.add((Object)iterator.next());
            if (++n == 7) {
                final AppModel appModel = new AppModel();
                appModel.setAppName(this.context.getString(2131821024));
                appModel.setMore(true);
                appModel.setAppIcon(this.getContext().getResources().getDrawable(2131231343));
                this.showModels.add((Object)appModel);
                break;
            }
        }
        this.adapter.notifyDataSetChanged();
    }
    
    private void initView() {
        this.gridView = (GridView)this.conentView.findViewById(2131296768);
        final int orientation = this.context.getResources().getConfiguration().orientation;
        if (orientation == 2) {
            this.gridView.setNumColumns(8);
            this.maxHeight = (int)(SystemUtils.getScreenHeight(this.getContext()) * 0.6);
        }
        else if (orientation == 1) {
            this.gridView.setNumColumns(4);
            this.maxHeight = (int)(SystemUtils.getScreenHeight(this.getContext()) * 0.6);
        }
        final ShareSelectDialog$1 shareSelectDialog$1 = new ShareSelectDialog$1(this, this.context, (List)this.showModels, 2131493201);
        this.adapter = (CommonAdapter<AppModel>)shareSelectDialog$1;
        this.gridView.setAdapter((ListAdapter)shareSelectDialog$1);
        this.gridView.setOnItemClickListener((AdapterView$OnItemClickListener)new _$$Lambda$ShareSelectDialog$y2vP8G_FJnjOjdbNJZQdaYHvWuU(this));
    }
    
    private void loadMOreData() {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.height = this.maxHeight;
        this.getWindow().setAttributes(attributes);
        this.showModels.clear();
        this.showModels.addAll((Collection)this.appModels);
        this.adapter.notifyDataSetChanged();
    }
    
    public void setOnShareSelectDialogListener(final OnShareSelectDialogListener onShareSelectDialogListener) {
        this.onShareSelectDialogListener = onShareSelectDialogListener;
    }
    
    private class AppSort implements Comparator<AppModel>
    {
        final ShareSelectDialog this$0;
        
        private AppSort(final ShareSelectDialog this$0) {
            this.this$0 = this$0;
        }
        
        public int compare(final AppModel appModel, final AppModel appModel2) {
            final Integer n = (Integer)this.this$0.sortMap.get((Object)appModel.getAppPkgName());
            final Integer n2 = (Integer)this.this$0.sortMap.get((Object)appModel2.getAppPkgName());
            final Integer value = 0;
            Integer n3 = n;
            if (n == null) {
                n3 = value;
            }
            Integer n4;
            if ((n4 = n2) == null) {
                n4 = value;
            }
            if (n3 > n4) {
                return -1;
            }
            if (n3.equals((Object)n4)) {
                return 0;
            }
            return 1;
        }
    }
    
    public interface OnShareSelectDialogListener
    {
        void onChooser(final AppModel p0);
    }
}
