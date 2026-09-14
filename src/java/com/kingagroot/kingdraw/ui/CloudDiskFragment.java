package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.pressenter.impl.CloudPresenterImpl$OnGetCloudFileCount;
import com.goodsrc.ui.library.BaseActivity;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.Objects;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.widget.fastAdapter.ViewHolder;
import android.util.Log;
import com.goodsrc.library.utils.ToastUtil;
import android.widget.CheckBox;
import android.os.Message;
import java.io.Serializable;
import android.content.Intent;
import android.content.DialogInterface;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$File;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog$Builder;
import com.kingagroot.kingdraw.model.CloudFileModel;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.SynFileModel;
import android.content.BroadcastReceiver;
import com.kingagroot.component.ui.widget.LoadingDialog;
import com.kingagroot.kingdraw.widget.MainFragMorePopwindow;
import android.os.Handler;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.widget.MainFragMorePopwindow$OnMainFragMorePopListener;
import com.kingagroot.kingdraw.widget.BatchOperateView$OnBatchOperateViewListener;

public class CloudDiskFragment extends CloudBaseFragment implements BatchOperateView$OnBatchOperateViewListener, MainFragMorePopwindow$OnMainFragMorePopListener
{
    public static final String CLOUDED_INTERFILE = "cloud_data_changed";
    private static final int MSG_UP_VIEW = 34;
    boolean addEnable;
    private LocalBroadcastManager broadcastManager;
    boolean isCheckAllMode;
    Handler mHandler;
    private MainFragMorePopwindow mainFragMorePopwindow;
    private CloudDiskFragment.CloudDiskFragment$OnCloudFileCountChangeListener onCloudFileCountChangeListener;
    private LoadingDialog progressDialog;
    BroadcastReceiver receiver;
    
    public CloudDiskFragment() {
        this.addEnable = true;
        this.mHandler = (Handler)new CloudDiskFragment$1(this);
        this.receiver = (BroadcastReceiver)new CloudDiskFragment$2(this);
    }
    
    private void deleteDialog(final ArrayList<CloudFileModel> list) {
        final String replace = this.getString(2131820770).replace((CharSequence)"%d", (CharSequence)String.valueOf(list.size()));
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.requireContext(), 2131886327);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(2131493085, (ViewGroup)null);
        ((WordBreakTextView)inflate.findViewById(2131297623)).setText(replace, new Object[] { ((CloudFileModel)list.get(0)).getFileName() });
        alertDialog$Builder.setView(inflate);
        alertDialog$Builder.setTitle((CharSequence)this.getString(2131821524)).setPositiveButton((CharSequence)this.getString(2131820766), (DialogInterface$OnClickListener)new _$$Lambda$CloudDiskFragment$1zN9CArWfT366CmhmpmVYC6yv3g(this, (ArrayList)list)).setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)_$$Lambda$CloudDiskFragment$wP7_6Yb7gW_uqdvBaKb5zGY8Csk.INSTANCE);
        final AlertDialog show = alertDialog$Builder.show();
        show.getButton(-1).setTextColor(ContextCompat.getColor(this.requireContext(), 2131099773));
        show.getButton(-2).setTextColor(ContextCompat.getColor(this.requireContext(), 2131099773));
    }
    
    private void deleteMulti(final String s, final boolean b) {
        this.progressDialog.setTextMessage(this.getString(2131820771));
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.deleteFileMulti());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("IdListString", (Object)s);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new CloudDiskFragment$4(this, b));
    }
    
    private void getCheckFolderModel(final String s, final CloudDiskFragment.CloudDiskFragment$OnCheckFolderModelListener cloudDiskFragment$OnCheckFolderModelListener) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.getFileListWithout());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("IdListString", (Object)s);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new CloudDiskFragment$3(this, cloudDiskFragment$OnCheckFolderModelListener));
    }
    
    private int getCheckNum() {
        if (this.isCheckAllMode) {
            return this.cloudPresenterI.getTotalCount() - this.batchView.getCloudCheckMap().size();
        }
        return this.batchView.getCloudCheckMap().size();
    }
    
    private String getIds(final ArrayList<CloudFileModel> list) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            if (i == 0) {
                sb.append(((CloudFileModel)list.get(i)).getId());
            }
            else {
                sb.append(",");
                sb.append(((CloudFileModel)list.get(i)).getId());
            }
        }
        return sb.toString();
    }
    
    private void getItemPosition(final SynFileModel synFileModel) {
        new Thread((Runnable)new _$$Lambda$CloudDiskFragment$_gnzBGy0zd_5dNjRoMOMru9UZKI(this, synFileModel)).start();
    }
    
    private boolean isCheckAll() {
        final int totalCount = this.cloudPresenterI.getTotalCount();
        boolean b = false;
        final boolean b2 = false;
        if (totalCount <= 0) {
            return false;
        }
        if (this.isCheckAllMode) {
            boolean b3 = b2;
            if (this.batchView.getCloudCheckMap().size() == 0) {
                b3 = true;
            }
            return b3;
        }
        if (this.cloudPresenterI.getTotalCount() <= this.batchView.getCloudCheckMap().size()) {
            b = true;
        }
        return b;
    }
    
    private void sendDownLoadTast(final ArrayList<CloudFileModel> list) {
        final Intent intent = new Intent();
        intent.setAction("syn_add_download_task");
        intent.putExtra("intent_data_synfilemodels", (Serializable)list);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendMsgUpdata(final int arg1) {
        final Message message = new Message();
        message.what = 34;
        message.arg1 = arg1;
        this.mHandler.sendMessage(message);
    }
    
    @Override
    public void notifyListView() {
        super.notifyListView();
        this.showMenuItem();
    }
    
    @Override
    protected void onAdapterConvert(final ViewHolder viewHolder, final CloudFileModel cloudFileModel) {
        super.onAdapterConvert(viewHolder, cloudFileModel);
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
        if (this.isCheckAllMode) {
            checkBox.setChecked(this.batchView.isCloudCheck(cloudFileModel.getId()) ^ true);
        }
        else {
            checkBox.setChecked(this.batchView.isCloudCheck(cloudFileModel.getId()));
        }
        if (this.synfiledbi.getSynFileModelByFolderFileModelId(String.valueOf(cloudFileModel.getId())) != null) {
            checkBox.setEnabled(false);
            checkBox.setChecked(true);
        }
        else {
            checkBox.setEnabled(true);
        }
        checkBox.setOnClickListener((View$OnClickListener)new _$$Lambda$CloudDiskFragment$5wZJGrTT23BrO_STVxyuWGnz460(this, checkBox, cloudFileModel));
    }
    
    public void onBatchOperate() {
        ((MainFragment)Objects.requireNonNull((Object)this.getParentFragment())).toBatchOperate();
        this.batchView.setCloudCheckNum();
    }
    
    public void onCheckAll() {
        this.isCheckAllMode = true;
        this.batchView.clearCloudCheck();
        final List downLoadingFileModels = this.synfiledbi.getDownLoadingFileModels();
        if (downLoadingFileModels != null) {
            final Iterator iterator = downLoadingFileModels.iterator();
            while (iterator.hasNext()) {
                final FolderFileModel folderFileModel = (FolderFileModel)new Gson().fromJson(((SynFileModel)iterator.next()).getFolderFileModelStr(), (Class)FolderFileModel.class);
                this.batchView.addCheck(folderFileModel.getId(), folderFileModel);
            }
        }
        this.batchView.setCheckNum(this.getCheckNum());
        this.adapterList.notifyDataSetChanged();
    }
    
    public void onCheckClear() {
        this.isCheckAllMode = false;
        this.batchView.clearCloudCheck();
        this.batchView.setCloudCheckNum();
        this.adapterList.notifyDataSetChanged();
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.requireActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("syn_download_add");
        intentFilter.addAction("cloud_data_changed");
        intentFilter.addAction("syn_download_item");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        this.listview.setEmptyView((View)this.emptyView);
        (this.mainFragMorePopwindow = new MainFragMorePopwindow(this.getContext())).setOnMainFragMorePopListener((MainFragMorePopwindow$OnMainFragMorePopListener)this);
        this.mainFragMorePopwindow.hideListMode();
        this.batchView.setOnBatchOperateViewListener((BatchOperateView$OnBatchOperateViewListener)this);
        (this.progressDialog = new LoadingDialog((BaseActivity)this.requireActivity())).setTextMessage(this.getString(2131820907));
        return onCreateView;
    }
    
    public void onDelete() {
        final ArrayList cloudCheckMap = this.batchView.getCloudCheckMap();
        if (this.getCheckNum() <= 0) {
            return;
        }
        if (this.isCheckAllMode) {
            this.progressDialog.setTextMessage(this.getString(2131820907));
            this.progressDialog.show();
            new Handler().postDelayed((Runnable)new _$$Lambda$CloudDiskFragment$iN0dVttqQ1PvtJ4QcedXiYneUnw(this, cloudCheckMap), 300L);
        }
        else if (cloudCheckMap != null) {
            this.deleteDialog((ArrayList<CloudFileModel>)cloudCheckMap);
        }
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
    }
    
    public void onListMode() {
    }
    
    public void onPreviewMode() {
    }
    
    @Override
    public void onResume() {
        super.onResume();
        this.refreshData();
    }
    
    public void onSynchronise() {
        if (!this.addEnable) {
            return;
        }
        this.addEnable = false;
        this.requireContext().startService(new Intent(this.getContext(), (Class)SynService.class));
        ((MainFragment)Objects.requireNonNull((Object)this.getParentFragment())).cloudTab.startAnim();
        if (this.isCheckAllMode) {
            this.progressDialog.setTextMessage(this.getString(2131820907));
            this.progressDialog.show();
            final ArrayList list = (ArrayList)this.batchView.getCloudCheckMap().clone();
            this.isCheckAllMode = false;
            this.batchView.clearCloudCheck();
            this.batchView.setCheck(this.isCheckAll());
            this.batchView.setCloudCheckNum();
            this.adapterList.notifyDataSetChanged();
            new Handler().postDelayed((Runnable)new _$$Lambda$CloudDiskFragment$W9W4QjkrIxfUmjKU_jJfFPtcmYk(this, list), 300L);
        }
        else {
            final ArrayList list2 = (ArrayList)this.batchView.getCloudCheckMap().clone();
            this.isCheckAllMode = false;
            this.batchView.clearCloudCheck();
            this.batchView.setCheck(this.isCheckAll());
            this.batchView.setCloudCheckNum();
            this.adapterList.notifyDataSetChanged();
            new Handler().postDelayed((Runnable)new _$$Lambda$CloudDiskFragment$rdmS3KYhCYZ0cU3SJntGoDg_BC4(this, list2), 300L);
            this.addEnable = true;
        }
    }
    
    @Override
    protected void refreshData() {
        super.refreshData();
        this.cloudPresenterI.refreshData();
        if (this.onCloudFileCountChangeListener != null) {
            this.cloudPresenterI.setOnGetCloudFileCount((CloudPresenterImpl$OnGetCloudFileCount)new _$$Lambda$CloudDiskFragment$0yVbW5sJ3I6KMuidMVo0XKrxbDY(this));
        }
    }
    
    public void setOnCloudFileCountChangeListener(final CloudDiskFragment.CloudDiskFragment$OnCloudFileCountChangeListener onCloudFileCountChangeListener) {
        this.onCloudFileCountChangeListener = onCloudFileCountChangeListener;
    }
    
    public void showAtLocation(final View view, final int n, final int n2, final int n3) {
        this.mainFragMorePopwindow.setShowBatchOperate(this.drawFileModels.size() > 0);
        this.mainFragMorePopwindow.showAtLocation(view, n, n2, n3);
    }
    
    public void showMenuItem() {
        final MainFragment mainFragment = (MainFragment)this.getParentFragment();
        if (mainFragment == null) {
            return;
        }
        if (mainFragment.viewpager.getCurrentItem() == 1) {
            if (this.drawFileModels.size() > 0) {
                if (mainFragment.searchItem != null && mainFragment.moreItem != null && mainFragment.addItem != null && mainFragment.cloudHintItem != null && !this.isBatchMode) {
                    mainFragment.searchItem.setVisible(true);
                    mainFragment.moreItem.setVisible(true);
                    mainFragment.addItem.setVisible(false);
                    mainFragment.cloudHintItem.setVisible(true);
                }
            }
            else if (mainFragment.searchItem != null && mainFragment.moreItem != null && mainFragment.addItem != null && mainFragment.cloudHintItem != null) {
                mainFragment.searchItem.setVisible(false);
                mainFragment.moreItem.setVisible(false);
                mainFragment.addItem.setVisible(false);
                mainFragment.cloudHintItem.setVisible(true);
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
        if (!(this.isBatchMode = isBatchMode)) {
            this.isCheckAllMode = false;
            this.batchView.clearCloudCheck();
            this.batchView.setCloudCheckNum();
            this.batchView.setCheck(false);
        }
        this.adapterList.notifyDataSetChanged();
    }
    
    public void updateView(final int n) {
        final int firstVisiblePosition = this.listview.getFirstVisiblePosition();
        final int lastVisiblePosition = this.listview.getLastVisiblePosition();
        if (n >= firstVisiblePosition && n <= lastVisiblePosition) {
            final CheckBox checkBox = (CheckBox)this.listview.getChildAt(n - firstVisiblePosition).findViewById(2131296478);
            final CloudFileModel cloudFileModel = (CloudFileModel)this.adapterList.getItem(n);
            if (this.isCheckAllMode) {
                checkBox.setChecked(this.batchView.isCloudCheck(cloudFileModel.getId()) ^ true);
            }
            else {
                checkBox.setChecked(this.batchView.isCloudCheck(cloudFileModel.getId()));
            }
            if (this.synfiledbi.getSynFileModelByFolderFileModelId(String.valueOf(cloudFileModel.getId())) != null) {
                checkBox.setEnabled(false);
                checkBox.setChecked(true);
            }
            else {
                checkBox.setEnabled(true);
            }
        }
    }
}
