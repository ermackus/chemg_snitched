package com.kingagroot.kingdraw.ui.synchornize;

import android.content.IntentFilter;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import androidx.appcompat.app.AlertDialog$Builder;
import com.google.gson.Gson;
import com.kingagroot.kingdraw.model.FolderFileModel;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.kingagroot.kingdraw.model.SynStatusEnum;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView$ItemAnimator;
import com.kingagroot.kingdraw.adapter.SynAdapterAnim;
import androidx.recyclerview.widget.RecyclerView$Adapter;
import androidx.recyclerview.widget.RecyclerView$LayoutManager;
import com.kingagroot.kingdraw.widget.GLinearLayoutManager;
import java.util.Collection;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import android.content.BroadcastReceiver;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.kingagroot.kingdraw.model.SynFileModel;
import java.util.List;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.adapter.SynchornizeAdapter;
import com.kingagroot.kingdraw.adapter.SynchornizeAdapter$OnSynChronizeAdapterListner;

public class UploadFragment extends SynView implements SynchornizeAdapter$OnSynChronizeAdapterListner
{
    private SynchornizeAdapter adapter;
    private LocalBroadcastManager broadcastManager;
    private LinearLayout emptyView;
    private final List<SynFileModel> folderFileModels;
    LinearLayoutManager layoutManager;
    private RecyclerView listivew;
    BroadcastReceiver receiver;
    SynFileDBI synFileDBI;
    
    public UploadFragment() {
        this.folderFileModels = (List<SynFileModel>)new ArrayList();
        this.receiver = (BroadcastReceiver)new UploadFragment$3(this);
    }
    
    private int getSynFilePosition(final SynFileModel synFileModel) {
        for (int i = 0; i < this.adapter.getItemCount(); ++i) {
            if (this.adapter.getItem(i).getId().equals((Object)synFileModel.getId())) {
                return i;
            }
        }
        return -1;
    }
    
    private void initData() {
        final SynFileDBImpl synFileDBI = new SynFileDBImpl();
        this.synFileDBI = (SynFileDBI)synFileDBI;
        final List upLoadFileModels = ((SynFileDBI)synFileDBI).getUpLoadFileModels();
        this.folderFileModels.clear();
        if (upLoadFileModels != null) {
            this.folderFileModels.addAll((Collection)upLoadFileModels);
        }
        (this.adapter = new SynchornizeAdapter(this.getContext(), (List)this.folderFileModels, false)).setOnSynChronizeAdapterListner((SynchornizeAdapter$OnSynChronizeAdapterListner)this);
        final GLinearLayoutManager gLinearLayoutManager = new GLinearLayoutManager(this.getContext());
        this.layoutManager = (LinearLayoutManager)gLinearLayoutManager;
        this.listivew.setLayoutManager((RecyclerView$LayoutManager)gLinearLayoutManager);
        this.listivew.setAdapter((RecyclerView$Adapter)this.adapter);
        this.listivew.setItemAnimator((RecyclerView$ItemAnimator)new SynAdapterAnim());
        this.setEmptyView();
    }
    
    private void initView(final View view) {
        this.listivew = (RecyclerView)view.findViewById(2131296980);
        this.emptyView = (LinearLayout)view.findViewById(2131296617);
    }
    
    private void setEmptyView() {
        if (this.adapter.getItemCount() > 0) {
            this.emptyView.setVisibility(8);
        }
        else {
            this.emptyView.setVisibility(0);
        }
    }
    
    private void startNextSyn() {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_next");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private boolean upLoadFinish(final SynFileModel synFileModel) {
        return synFileModel.getStatus() == SynStatusEnum.\u5931\u8d25.getCode() || synFileModel.getStatus() == SynStatusEnum.\u6210\u529f.getCode();
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493037, viewGroup, false);
        this.initView(inflate);
        this.initData();
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.getActivity());
        return inflate;
    }
    
    public void onPause() {
        super.onPause();
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    public void onRestart(final SynFileModel synFileModel, final int n) {
        final FolderFileModel folderFileModel = (FolderFileModel)new Gson().fromJson(synFileModel.getFolderFileModelStr(), (Class)FolderFileModel.class);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.getContext(), 2131886327);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.getString(2131821324), new Object[] { folderFileModel.getFullFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setTitle((CharSequence)this.getString(2131821524)).setPositiveButton((CharSequence)this.getString(2131821323), (DialogInterface$OnClickListener)new UploadFragment$2(this, synFileModel)).setNegativeButton((CharSequence)this.getString(2131820665), (DialogInterface$OnClickListener)new UploadFragment$1(this, synFileModel, n)).setNeutralButton((CharSequence)this.getString(2131820967), (DialogInterface$OnClickListener)null);
        final AlertDialog show = alertDialog$Builder.show();
        show.getButton(-1).setTextColor(ContextCompat.getColor(this.getContext(), 2131099773));
        show.getButton(-2).setTextColor(ContextCompat.getColor(this.getContext(), 2131099773));
        show.getButton(-3).setTextColor(-10066330);
    }
    
    public void onResume() {
        super.onResume();
        this.initData();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("syn_upload_item");
        this.broadcastManager.registerReceiver(this.receiver, new IntentFilter(intentFilter));
    }
    
    public void onStartNext() {
        this.startNextSyn();
        this.setEmptyView();
    }
    
    public void updateView(final int n, final SynFileModel synFileModel) {
        final int firstVisibleItemPosition = this.layoutManager.findFirstVisibleItemPosition();
        final int lastVisibleItemPosition = this.layoutManager.findLastVisibleItemPosition();
        if (n >= firstVisibleItemPosition && n <= lastVisibleItemPosition) {
            this.adapter.updataView(this.layoutManager.findViewByPosition(n), n, synFileModel);
        }
        else if (this.upLoadFinish(synFileModel)) {
            if (n < this.folderFileModels.size()) {
                this.folderFileModels.remove(n);
                this.adapter.notifyItemRemoved(n);
            }
            this.startNextSyn();
        }
    }
}
