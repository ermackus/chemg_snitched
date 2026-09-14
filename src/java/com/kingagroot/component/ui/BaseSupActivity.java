package com.kingagroot.component.ui;

import android.os.Bundle;
import android.content.res.Configuration;
import android.view.View;
import com.kingagroot.component.ui.widget.sup.DialogSup$OnSupCancelListener;
import com.kingagroot.component.ui.widget.sup.DialogSup$OnSupDeleteListener;
import com.kingagroot.component.ui.widget.sup.DialogSup$OnSupEditListener;
import com.kingagroot.component.ui.widget.sup.DialogSup$OnCancelCommonListener;
import com.kingagroot.component.ui.widget.sup.DialogSup$OnSetCommonListener;
import com.kingagroot.component.ui.widget.sup.DialogSup;
import java.io.Serializable;
import android.content.Intent;
import java.util.Collection;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import com.kingagroot.component.ui.widget.sup.OnSupSelectListener;
import android.widget.ListAdapter;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import androidx.appcompat.app.AlertDialog$Builder;
import com.kingagroot.component.ui.db.impl.GSGroupModelDBImpl;
import java.util.ArrayList;
import com.kingagroot.component.ui.widget.sup.SupCommonView;
import android.content.BroadcastReceiver;
import android.widget.ImageButton;
import com.kingagroot.component.ui.db.GSGroupModelDBI;
import android.widget.GridView;
import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.List;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.component.ui.widget.sup.SupListAdapter;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class BaseSupActivity extends BaseActivity implements View$OnClickListener
{
    public static final String GROUP_INTENTFILTER = "gsgroup_data_changed";
    public static String KEY_SUP_FILE = "KEY_SUP_FILE";
    private SupListAdapter adapter;
    private LocalBroadcastManager broadcastManager;
    private DrawerLayout dlSup;
    private TextView etSearch;
    private final List<GSGroupModel> gSups;
    protected GridView gridSup;
    private final GSGroupModelDBI gsGroupModelDBI;
    private ImageButton ibtAddSup;
    private ImageButton ibtClose;
    private ImageButton ibtListSelect;
    BroadcastReceiver receiver;
    private SupCommonView supCommon;
    
    public BaseSupActivity() {
        this.gSups = (List<GSGroupModel>)new ArrayList();
        this.gsGroupModelDBI = (GSGroupModelDBI)new GSGroupModelDBImpl();
        this.receiver = (BroadcastReceiver)new BaseSupActivity$11(this);
    }
    
    private void deleteSupDialog(final GSGroupModel gsGroupModel) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"").setMessage(R$string.sup_delete_dailog_msg_hint).setNegativeButton(R$string.cancel, (DialogInterface$OnClickListener)null).setPositiveButton(R$string.sup_delete_confirm, (DialogInterface$OnClickListener)new BaseSupActivity$10(this, gsGroupModel));
        alertDialog$Builder.show().getButton(-1).setTextColor(ContextCompat.getColor((Context)this, R$color.colorRed));
    }
    
    private void initData() {
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("gsgroup_data_changed");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        this.notifyData();
    }
    
    private void initView() {
        this.dlSup = (DrawerLayout)this.findViewById(R$id.dl_sup);
        this.ibtClose = (ImageButton)this.findViewById(R$id.ibt_close);
        this.ibtListSelect = (ImageButton)this.findViewById(R$id.ibt_list_select);
        this.ibtAddSup = (ImageButton)this.findViewById(R$id.ibt_add_sup);
        this.etSearch = (TextView)this.findViewById(R$id.et_search);
        this.gridSup = (GridView)this.findViewById(R$id.grid_sup);
        this.supCommon = (SupCommonView)this.findViewById(R$id.sup_common);
        this.etSearch.setOnClickListener((View$OnClickListener)new BaseSupActivity$1(this));
        this.ibtClose.setOnClickListener((View$OnClickListener)this);
        this.ibtAddSup.setOnClickListener((View$OnClickListener)this);
        this.ibtListSelect.setOnClickListener((View$OnClickListener)this);
        this.dlSup.setScrimColor(0);
        final SupListAdapter supListAdapter = new SupListAdapter((Context)this, (List)this.gSups);
        this.adapter = supListAdapter;
        this.gridSup.setAdapter((ListAdapter)supListAdapter);
        this.supCommon.setOnSupSelectListener((OnSupSelectListener)new BaseSupActivity$2(this));
        this.gridSup.setOnItemClickListener((AdapterView$OnItemClickListener)new BaseSupActivity$3(this));
        this.gridSup.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new BaseSupActivity$4(this));
        this.notifyData();
    }
    
    private void notifyData() {
        this.gSups.clear();
        final List searchGroup = this.gsGroupModelDBI.searchGroup(this.etSearch.getText().toString());
        if (searchGroup != null) {
            this.gSups.addAll((Collection)searchGroup);
        }
        this.adapter.notifyDataSetChanged();
    }
    
    private void setRsultIntent(final GSGroupModel gsGroupModel) {
        final Intent intent = new Intent();
        intent.putExtra(BaseSupActivity.KEY_SUP_FILE, (Serializable)gsGroupModel);
        this.setResult(-1, intent);
        this.finish();
    }
    
    private void showDialog(final GSGroupModel gsGroupModel) {
        final DialogSup dialogSup = new DialogSup(gsGroupModel);
        dialogSup.setOnSetCommonListener((DialogSup$OnSetCommonListener)new BaseSupActivity$5(this, gsGroupModel, dialogSup));
        dialogSup.setOnCancelCommonListener((DialogSup$OnCancelCommonListener)new BaseSupActivity$6(this, gsGroupModel, dialogSup));
        dialogSup.setOnSupEditListener((DialogSup$OnSupEditListener)new BaseSupActivity$7(this, gsGroupModel, dialogSup));
        dialogSup.setOnSupDeleteListener((DialogSup$OnSupDeleteListener)new BaseSupActivity$8(this, dialogSup, gsGroupModel));
        dialogSup.setOnSupCancelListener((DialogSup$OnSupCancelListener)new BaseSupActivity$9(this, dialogSup));
        dialogSup.show(this.getSupportFragmentManager(), "");
    }
    
    public void onClick(final View view) {
        if (view == this.ibtClose) {
            this.finish();
        }
        else if (view == this.ibtAddSup) {
            this.onSupPaletteEdit(null);
        }
        else if (view == this.ibtListSelect) {
            this.dlSup.openDrawer(5);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.onOrientationChanged(configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.component_activity_sup);
        this.initView();
        this.initData();
        this.onOrientationChanged(this.getResources().getConfiguration());
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.adapter.onDestroy();
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    protected void onOrientationChanged(final Configuration configuration) {
    }
    
    protected void onSupPaletteEdit(final GSGroupModel gsGroupModel) {
    }
}
