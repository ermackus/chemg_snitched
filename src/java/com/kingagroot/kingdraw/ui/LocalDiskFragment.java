package com.kingagroot.kingdraw.ui;

import android.app.Activity;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import android.widget.ListAdapter;
import androidx.fragment.app.FragmentActivity;
import java.io.Serializable;
import com.goodsrc.ui.library.MANServiceConfig;
import com.kingagroot.kingdraw.dialog.LogInHintDialog;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import androidx.appcompat.app.AlertDialog$Builder;
import com.kingagroot.kingdraw.widget.FileTag.OnLocalFilterViewAnimListener;
import com.kingagroot.kingdraw.widget.FileTag.GTagLocalFilterView$OnLocalFilterListner;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import android.content.IntentFilter;
import android.content.Context;
import com.kingagroot.kingdraw.config.ShareData;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.Objects;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import android.content.Intent;
import java.util.List;
import com.goodsrc.library.utils.ToastUtil;
import android.view.View;
import android.widget.CheckBox;
import java.util.Iterator;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.ArrayList;
import android.os.Message;
import android.content.DialogInterface;
import com.kingagroot.kingdraw.model.GSearchModel;
import com.kingagroot.kingdraw.model.SynFileModel;
import android.content.BroadcastReceiver;
import android.app.ProgressDialog;
import com.kingagroot.kingdraw.widget.MainFragMorePopwindow;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.widget.MainFragMorePopwindow$OnMainFragMorePopListener;
import com.kingagroot.kingdraw.widget.BatchOperateView$OnBatchOperateViewListener;

public class LocalDiskFragment extends LocalBaseFragment implements BatchOperateView$OnBatchOperateViewListener, MainFragMorePopwindow$OnMainFragMorePopListener
{
    private static final int MSG_CHECK_ALL_END = 13;
    private static final int MSG_DELETE_END = 12;
    private static final int MSG_DELETE_START = 11;
    private static final int MSG_UP_VIEW = 14;
    boolean addEnable;
    private LocalBroadcastManager broadcastManager;
    Handler mHandler;
    private MainFragMorePopwindow mainFragMorePopwindow;
    private LocalDiskFragment.LocalDiskFragment$OnLocalFileCountChangeListener onLocalFileCountChangeListener;
    private ProgressDialog progressDialog;
    BroadcastReceiver receiver;
    
    public LocalDiskFragment() {
        this.addEnable = true;
        this.mHandler = (Handler)new LocalDiskFragment$1(this);
        this.receiver = (BroadcastReceiver)new LocalDiskFragment$2(this);
    }
    
    private void getCheckAll() {
        new Thread((Runnable)new _$$Lambda$LocalDiskFragment$9CnSKZL5hb13onZNpSpF1rWD5wE(this)).start();
    }
    
    private long getFilterDrawFileAmount() {
        final GSearchModel gSearchModel = new GSearchModel();
        gSearchModel.setTags(this.tagFilterView.getTags());
        return this.drawFileDataI.getDrawFileAmount(gSearchModel);
    }
    
    private void getItemPosition(final SynFileModel synFileModel) {
        new Thread((Runnable)new _$$Lambda$LocalDiskFragment$M1EVVxEPAveug7RAfhD52sOKKqI(this, synFileModel)).start();
    }
    
    private boolean isCheckAll() {
        final long filterDrawFileAmount = this.getFilterDrawFileAmount();
        boolean b = false;
        if (filterDrawFileAmount <= 0L) {
            return false;
        }
        if (this.getFilterDrawFileAmount() <= this.batchView.getCheckMap().size()) {
            b = true;
        }
        return b;
    }
    
    private void sendMsgCheckAll() {
        final Message message = new Message();
        message.what = 13;
        this.mHandler.sendMessage(message);
    }
    
    private void sendMsgDeleteEnd() {
        final Message message = new Message();
        message.what = 12;
        this.mHandler.sendMessage(message);
    }
    
    private void sendMsgDeleteStart() {
        final Message message = new Message();
        message.what = 11;
        this.mHandler.sendMessage(message);
    }
    
    private void sendMsgUpdate(final int arg1) {
        final Message message = new Message();
        message.what = 14;
        message.arg1 = arg1;
        this.mHandler.sendMessage(message);
    }
    
    private void startDelete(final ArrayList<FolderFileModel> list) {
        new Thread((Runnable)new _$$Lambda$LocalDiskFragment$anFHTYDo1xo0FX_K_jcrzxXN5RY(this, (ArrayList)list)).start();
    }
    
    @Override
    public void notifyListView() {
        super.notifyListView();
        this.showMenuItem();
        if (this.isBatchMode) {
            this.batchView.setCheck(this.isCheckAll());
        }
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
    }
    
    @Override
    protected void onAdapterConvert(final ViewHolder viewHolder, final FolderFileModel folderFileModel) {
        super.onAdapterConvert(viewHolder, folderFileModel);
        final CheckBox checkBox = (CheckBox)viewHolder.getView(2131296478);
        final View view = viewHolder.getView(2131296526);
        if (this.isBatchMode) {
            checkBox.setVisibility(0);
            view.setVisibility(0);
        }
        else {
            checkBox.setVisibility(8);
            view.setVisibility(8);
        }
        checkBox.setChecked(this.batchView.isCheck(folderFileModel.getId()));
        if (this.synfiledbi.getSynFileModelByFolderFileModelId(folderFileModel.getId()) != null) {
            checkBox.setEnabled(false);
            checkBox.setChecked(true);
        }
        else {
            checkBox.setEnabled(true);
        }
        checkBox.setOnClickListener((View$OnClickListener)new _$$Lambda$LocalDiskFragment$zCTFGtah99eHprc6yTbvOySca9A(this, checkBox, folderFileModel));
    }
    
    public void onBatchOperate() {
        ((MainFragment)Objects.requireNonNull((Object)this.getParentFragment())).toBatchOperate();
        this.batchView.setCheckNum();
    }
    
    public void onCheckAll() {
        if (this.getFilterDrawFileAmount() > 50L) {
            this.progressDialog = ProgressDialog.show(this.getContext(), (CharSequence)"", (CharSequence)this.getString(2131820907));
        }
        this.getCheckAll();
    }
    
    public void onCheckClear() {
        this.batchView.clearCheck();
        this.batchView.setCheckNum();
        this.adapterList.notifyDataSetChanged();
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.listData.setEmptyView(this.llLocalEmpty);
        this.batchView.setBtnDownloadText(this.getString(2131821496));
        final boolean folderMode = ShareData.getFolderMode();
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("syn_upload_add");
        intentFilter.addAction("local_data_changed");
        intentFilter.addAction("syn_upload_item");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        this.batchView.setOnBatchOperateViewListener((BatchOperateView$OnBatchOperateViewListener)this);
        (this.mainFragMorePopwindow = new MainFragMorePopwindow(this.getContext())).setOnMainFragMorePopListener((MainFragMorePopwindow$OnMainFragMorePopListener)this);
        if (folderMode) {
            this.onListMode();
        }
        else {
            this.onPreviewMode();
        }
        this.synfiledbi = (SynFileDBI)new SynFileDBImpl();
        this.refreshData();
        this.tagFilterView.setVisibility(0);
        this.tagFilterView.setOnLocalFilterListner((GTagLocalFilterView$OnLocalFilterListner)new _$$Lambda$LocalDiskFragment$1hFf3qvUt9ZOKYEAi9F26yaWNmk(this));
        this.tagFilterView.setOnLocalFilterViewAnimListener((OnLocalFilterViewAnimListener)new LocalDiskFragment$3(this));
        return onCreateView;
    }
    
    public void onDelete() {
        final ArrayList list = (ArrayList)this.batchView.getCheckMap().clone();
        if (list.size() <= 0) {
            return;
        }
        final String string = this.getString(2131820770);
        final StringBuilder sb = new StringBuilder();
        sb.append(list.size());
        sb.append("");
        final String replace = string.replace((CharSequence)"%d", (CharSequence)sb.toString());
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.requireContext(), 2131886327);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(replace, new Object[] { ((FolderFileModel)list.get(0)).getFullFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setTitle((CharSequence)this.getString(2131821524)).setPositiveButton((CharSequence)this.getString(2131820766), (DialogInterface$OnClickListener)new _$$Lambda$LocalDiskFragment$_o_MTeniZm6bgRyL4Kxm118p3dE(this, list)).setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)_$$Lambda$LocalDiskFragment$dxCHDPQDWD7O7jrqiE3OmmYy7r4.INSTANCE);
        final AlertDialog show = alertDialog$Builder.show();
        show.getButton(-1).setTextColor(ContextCompat.getColor(this.requireContext(), 2131099773));
        show.getButton(-2).setTextColor(ContextCompat.getColor(this.requireContext(), 2131099773));
    }
    
    public void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
        if (this.tagFilterView != null) {
            this.tagFilterView.onDestroy();
        }
    }
    
    public void onListMode() {
        this.setListMode(true);
        this.mainFragMorePopwindow.toPreviewMode();
    }
    
    public void onPreviewMode() {
        this.setListMode(false);
        this.mainFragMorePopwindow.toListMode();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.tagFilterView.onResume();
    }
    
    public void onSynchronise() {
        if (!this.addEnable) {
            return;
        }
        this.addEnable = false;
        this.getContext().startService(new Intent(this.getContext(), (Class)SynService.class));
        final MainFragment mainFragment = (MainFragment)this.getParentFragment();
        if (!mainFragment.checkLogin()) {
            this.addEnable = true;
            new LogInHintDialog(this.getContext()).show();
            return;
        }
        mainFragment.localTab.startAnim();
        final ArrayList list = (ArrayList)this.batchView.getCheckMap().clone();
        this.batchView.clearCheck();
        this.batchView.setCheck(this.isCheckAll());
        this.batchView.setCheckNum();
        this.adapterList.notifyDataSetChanged();
        MANServiceConfig.addFileUpEvent();
        final Intent intent = new Intent();
        intent.setAction("syn_add_upload_task");
        intent.putExtra("intent_data_synfilemodels", (Serializable)list);
        this.broadcastManager.sendBroadcast(intent);
        this.addEnable = true;
    }
    
    @Override
    protected void refreshData() {
        final List tags = this.tagFilterView.getTags();
        final GSearchModel gSearchModel = new GSearchModel();
        gSearchModel.setTags(tags);
        this.localPresenterI.refreshSearchData(gSearchModel);
        if (tags != null && !tags.isEmpty()) {
            this.llLocalEmpty.setVisibility(8);
            this.listData.setEmptyView(this.rlSearchDataEmpty);
        }
        else {
            this.rlSearchDataEmpty.setVisibility(8);
            this.listData.setEmptyView(this.llLocalEmpty);
        }
        final LocalDiskFragment.LocalDiskFragment$OnLocalFileCountChangeListener onLocalFileCountChangeListener = this.onLocalFileCountChangeListener;
        if (onLocalFileCountChangeListener != null) {
            onLocalFileCountChangeListener.onCountChange((int)this.drawFileDataI.getDrawFileAmount(gSearchModel));
        }
    }
    
    public void scrollTop() {
        this.listData.smoothScrollToPosition(0);
    }
    
    @Override
    public void setListMode(final boolean listMode) {
        ShareData.saveFolderMode(listMode);
        super.setListMode(listMode);
    }
    
    public void setOnLocalFileCountChangeListener(final LocalDiskFragment.LocalDiskFragment$OnLocalFileCountChangeListener onLocalFileCountChangeListener) {
        this.onLocalFileCountChangeListener = onLocalFileCountChangeListener;
    }
    
    public void showAtLocation(final View view, final int n, final int n2, final int n3) {
        final FragmentActivity activity = this.getActivity();
        if (activity != null && !((Activity)activity).isFinishing()) {
            this.mainFragMorePopwindow.setShowBatchOperate(this.folderFileModels.size() > 0);
            this.mainFragMorePopwindow.showAtLocation(view, n, n2, n3);
        }
    }
    
    public void showMenuItem() {
        final MainFragment mainFragment = (MainFragment)this.getParentFragment();
        if (mainFragment == null) {
            return;
        }
        if (mainFragment.viewpager.getCurrentItem() == 0) {
            if (this.drawFileDataI.getDrawFileAmount() > 0L) {
                if (mainFragment.searchItem != null && mainFragment.moreItem != null && mainFragment.cloudHintItem != null && !this.isBatchMode) {
                    mainFragment.searchItem.setVisible(true);
                    mainFragment.moreItem.setVisible(true);
                    mainFragment.cloudHintItem.setVisible(false);
                }
            }
            else if (mainFragment.searchItem != null && mainFragment.moreItem != null && mainFragment.cloudHintItem != null) {
                mainFragment.searchItem.setVisible(false);
                mainFragment.moreItem.setVisible(false);
                mainFragment.cloudHintItem.setVisible(false);
            }
        }
    }
    
    public void toBatchOperate(final boolean isBatchMode) {
        int visibility;
        if (isBatchMode) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.batchView.setVisibility(visibility);
        if (!isBatchMode) {
            this.batchView.clearCheck();
            this.batchView.setCheckNum();
            this.batchView.setCheck(false);
        }
        this.isBatchMode = isBatchMode;
        if (isBatchMode) {
            this.listData.setAdapter((ListAdapter)this.adapterList);
            this.adapterList.notifyDataSetChanged();
        }
        else if (ShareData.getFolderMode()) {
            this.onListMode();
        }
        else {
            this.onPreviewMode();
        }
        this.adapterList.notifyDataSetChanged();
    }
    
    public void updateView(final int n) {
        final int firstVisiblePosition = this.listData.getFirstVisiblePosition();
        final int lastVisiblePosition = this.listData.getLastVisiblePosition();
        if (n >= firstVisiblePosition && n <= lastVisiblePosition) {
            final CheckBox checkBox = (CheckBox)this.listData.getChildAt(n - firstVisiblePosition).findViewById(2131296478);
            final FolderFileModel folderFileModel = (FolderFileModel)this.adapterList.getItem(n);
            checkBox.setChecked(this.batchView.isCheck(folderFileModel.getId()));
            if (this.synfiledbi.getSynFileModelByFolderFileModelId(folderFileModel.getId()) != null) {
                checkBox.setEnabled(false);
                checkBox.setChecked(true);
            }
            else {
                checkBox.setEnabled(true);
            }
        }
    }
}
