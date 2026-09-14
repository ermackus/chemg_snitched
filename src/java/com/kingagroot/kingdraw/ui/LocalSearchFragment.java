package com.kingagroot.kingdraw.ui;

import java.util.List;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import android.content.BroadcastReceiver;
import com.kingagroot.kingdraw.model.GSearchModel;

public class LocalSearchFragment extends LocalBaseFragment
{
    boolean dataChange;
    private GSearchModel gSearchModel;
    BroadcastReceiver receiver;
    
    public LocalSearchFragment() {
        this.receiver = (BroadcastReceiver)new LocalSearchFragment$1(this);
    }
    
    @Override
    public void notifyListView() {
        super.notifyListView();
        if (this.folderFileModels != null && this.folderFileModels.size() > 0) {
            this.rlSearchDataEmpty.setVisibility(8);
        }
        else if (!this.gSearchModel.isEmpty()) {
            this.rlSearchDataEmpty.setVisibility(0);
        }
        else {
            this.rlSearchDataEmpty.setVisibility(8);
        }
    }
    
    @Override
    protected void onAdapterConvert(final ViewHolder viewHolder, final FolderFileModel folderFileModel) {
        super.onAdapterConvert(viewHolder, folderFileModel);
        final CheckBox checkBox = (CheckBox)viewHolder.getView(2131296478);
        final View view = viewHolder.getView(2131296526);
        checkBox.setVisibility(8);
        view.setVisibility(8);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.gSearchModel = new GSearchModel();
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("local_data_changed");
        instance.registerReceiver(this.receiver, intentFilter);
        return onCreateView;
    }
    
    public void onDestroy() {
        super.onDestroy();
        if (this.dataChange) {
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.requireContext());
            final Intent intent = new Intent("local_data_changed");
            intent.putExtra("datachange_key", true);
            instance.sendBroadcast(intent);
        }
    }
    
    @Override
    public void onMenuItemSelect(final int n) {
        super.onMenuItemSelect(n);
        this.dataChange = true;
    }
    
    public void onSearchKey(final String key, final List<String> tags, final String smiles) {
        this.loading();
        this.gSearchModel.setKey(key);
        this.gSearchModel.setSmiles(smiles);
        this.gSearchModel.setTags((List)tags);
        this.refreshData();
    }
    
    @Override
    protected void refreshData() {
        super.refreshData();
        this.localPresenterI.refreshSearchData(this.gSearchModel);
    }
}
