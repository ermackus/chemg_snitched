package com.kingagroot.kingdraw.pressenter.impl;

import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.interfaces.OnSearchResultListener;
import java.util.Collection;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.model.FileResultStatusEnum;
import com.kingagroot.kingdraw.model.FileResultModel;
import com.kingagroot.kingdraw.utils.FileExport$OnFileExportListner;
import com.kingagroot.kingdraw.utils.FileExport;
import com.kingagroot.component.ui.widget.LoadingDialog;
import com.kingagroot.kingdraw.dialog.FileExportDialog;
import com.kingagroot.kingdraw.model.FileType;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.limit.LimitDialog;
import android.content.Intent;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.config.NetConfig;
import com.kingagroot.kingdraw.model.CloudFileModel;
import com.kingagroot.kingdraw.http.NewNetBean;
import com.goodsrc.library.http.RequestCallBack;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import java.io.File;
import com.kingagroot.kingdraw.config.NetConfig$File;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.goodsrc.ui.library.MANServiceConfig;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.kingdraw.interfaces.impl.FolderFileModelSearchImpl;
import com.kingagroot.kingdraw.ui.LocalSearchFragment;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import com.kingagroot.kingdraw.pressenter.view.LocalView;
import com.kingagroot.kingdraw.model.GSearchModel;
import com.kingagroot.kingdraw.interfaces.FolderFileModelSearchDBI;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.pressenter.LocalPresenterI;

public class LocalPresenterImpl implements LocalPresenterI
{
    private final DrawFileDataI drawFileDataI;
    private List<FolderFileModel> folderFileModels;
    FolderFileModelSearchDBI folderfilemodelsearchdbi;
    private GSearchModel gSearchModel;
    boolean isSearchFragment;
    private final LocalView localView;
    private boolean noMore;
    private int pageIndex;
    private final SynFileDBI synFileDBI;
    
    public LocalPresenterImpl(final LocalView localView) {
        this.folderFileModels = (List<FolderFileModel>)new ArrayList();
        this.localView = localView;
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        this.synFileDBI = (SynFileDBI)new SynFileDBImpl();
        if (localView instanceof LocalSearchFragment) {
            this.isSearchFragment = true;
            this.folderfilemodelsearchdbi = (FolderFileModelSearchDBI)new FolderFileModelSearchImpl(true);
        }
    }
    
    public void addFile(final FolderFileModel folderFileModel) {
        MANServiceConfig.addFileUpEvent();
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.upLoadFileCloud());
        final File file = new File(folderFileModel.getFilePath());
        final File file2 = new File(folderFileModel.getPicPath());
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("filename", (Object)folderFileModel.getFullFileName()));
        ((List)list).add((Object)new KeyValue("filesmiles", (Object)folderFileModel.getFileSmiles()));
        ((List)list).add((Object)new KeyValue("oss", (Object)file));
        ((List)list).add((Object)new KeyValue("smallpic", (Object)file2));
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<CloudFileModel>>(this, folderFileModel) {
            final LocalPresenterImpl this$0;
            final FolderFileModel val$model;
            
            public void onFinished() {
                super.onFinished();
                this.this$0.localView.loadingFinish();
            }
            
            public void onSuccess(final NewNetBean<CloudFileModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    final CloudFileModel cloudFileModel = (CloudFileModel)newNetBean.getData();
                    if (cloudFileModel != null) {
                        this.val$model.setFileOssId(cloudFileModel.getFileOSSID());
                        this.val$model.setFileSmallPicId(cloudFileModel.getThumbnailOSSID());
                        LocalBroadcastManager.getInstance((Context)this.this$0.localView.getActivity()).sendBroadcast(new Intent("cloud_data_changed"));
                    }
                }
                else if (newNetBean.getCode() == 521) {
                    LimitDialog.showVipDialog(this.this$0.localView.getContext(), newNetBean.getMessage());
                }
                else {
                    ToastUtil.showShort((CharSequence)newNetBean.getMessage());
                }
            }
        });
    }
    
    public void checkFile(final FolderFileModel folderFileModel) {
        if (this.synFileDBI.getSynFileModelByFolderFileModelId(folderFileModel.getId()) != null) {
            ToastUtil.showShort((CharSequence)this.localView.getString(2131821122));
            return;
        }
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.getFileByName());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("FileName", (Object)folderFileModel.getFullFileName());
            params.addBodyParameter("", jsonObject.toString());
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<LocalPresenterImpl$FolderCheckModel>>(this) {
            final LocalPresenterImpl this$0;
            
            public void onError(final Exception ex, final String s) {
                super.onError(ex, s);
                this.this$0.localView.loadingFinish();
            }
            
            public void onSuccess(final NewNetBean<LocalPresenterImpl$FolderCheckModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    final LocalPresenterImpl$FolderCheckModel localPresenterImpl$FolderCheckModel = (LocalPresenterImpl$FolderCheckModel)newNetBean.getData();
                    if (localPresenterImpl$FolderCheckModel != null && localPresenterImpl$FolderCheckModel.getIsRepeat() <= 0) {
                        this.this$0.localView.upFile(true, (String)null);
                    }
                    else {
                        this.this$0.localView.upFile(false, localPresenterImpl$FolderCheckModel.getRefileName());
                    }
                }
                else {
                    this.this$0.localView.loadingFinish();
                    ToastUtil.showShort((CharSequence)newNetBean.getMessage());
                }
            }
        });
    }
    
    public void copyFile(final FolderFileModel folderFileModel) {
        this.drawFileDataI.copyFile(folderFileModel.getId());
    }
    
    public void deleteFile(final FolderFileModel folderFileModel) {
        if (this.synFileDBI.getSynFileModelByFolderFileModelId(folderFileModel.getId()) != null) {
            ToastUtil.showShort((CharSequence)this.localView.getString(2131821122));
            return;
        }
        if (this.drawFileDataI.deleteFile(folderFileModel.getId()).isSuccess) {
            this.folderFileModels.remove((Object)folderFileModel);
            this.localView.notifyListView();
        }
    }
    
    public void editFile(final FolderFileModel folderFileModel) {
        this.addFile(folderFileModel);
    }
    
    public void exportAsFile(final FolderFileModel folderFileModel, final String s, final FileType fileType, final FileExportDialog fileExportDialog) {
        final DrawFileDataI drawFileDataI = this.drawFileDataI;
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(fileType.extension);
        final OperationState checkFileNameAvailable = drawFileDataI.checkFileNameAvailable(sb.toString());
        if (!checkFileNameAvailable.isSuccess) {
            ToastUtil.showShort((CharSequence)checkFileNameAvailable.info);
            return;
        }
        final LoadingDialog loadingDialog = new LoadingDialog((BaseActivity)this.localView.getContext());
        loadingDialog.setTextMessage(this.localView.getString(2131821523));
        loadingDialog.setCancelable(false);
        loadingDialog.setOnTouchOutside(false);
        new FileExport().exportAsFile(s, folderFileModel, fileType, (FileExport$OnFileExportListner)new FileExport$OnFileExportListner(this, loadingDialog, fileType, fileExportDialog) {
            final LocalPresenterImpl this$0;
            final FileExportDialog val$de;
            final FileType val$fileType;
            final LoadingDialog val$progressDialog;
            
            public void onFinish(final FileResultModel fileResultModel) {
                final LoadingDialog val$progressDialog = this.val$progressDialog;
                if (val$progressDialog != null) {
                    val$progressDialog.dismiss();
                }
                if (fileResultModel.status == FileResultStatusEnum.\u6210\u529f.getCode()) {
                    if (this.val$fileType != FileType.PNG && this.val$fileType != FileType.JPG) {
                        final FolderFileModel folderFileModel = (FolderFileModel)fileResultModel.object;
                        if (folderFileModel != null) {
                            this.this$0.folderFileModels.add(0, (Object)folderFileModel);
                            this.this$0.localView.notifyListView();
                        }
                        ToastUtil.showShort((CharSequence)MApplication.getInstance().getString(2131820856));
                    }
                    else {
                        ToastUtil.showShort(2131820838);
                    }
                    this.val$de.dismiss();
                }
                else if (fileResultModel.status == FileResultStatusEnum.molv2000\u8d85\u8fc7\u9650\u5236.getCode()) {
                    ToastUtil.showShort(2131820843);
                }
                else {
                    ToastUtil.showShort(2131820857);
                }
            }
            
            public void onStart() {
                this.val$progressDialog.show(1000L);
            }
        });
    }
    
    public List<FolderFileModel> getDatas() {
        if (this.folderFileModels == null) {
            this.folderFileModels = (List<FolderFileModel>)new ArrayList();
        }
        return this.folderFileModels;
    }
    
    public void loadMoreData() {
        this.loadMoreSearchData();
    }
    
    public void loadMoreSearchData() {
        if (this.noMore) {
            this.localView.loadingFinish();
            return;
        }
        this.localView.loading();
        final int pageIndex = this.pageIndex + 1;
        this.pageIndex = pageIndex;
        if (this.isSearchFragment) {
            this.folderfilemodelsearchdbi.findDrawFiles(this.gSearchModel, pageIndex, (OnSearchResultListener)new _$$Lambda$LocalPresenterImpl$EypgurGhqAzCnTWyDyhoD0E2YHQ(this));
        }
        else {
            this.drawFileDataI.findDrawFiles(this.gSearchModel, pageIndex, (OnSearchResultListener)new _$$Lambda$LocalPresenterImpl$wdOolwdtL_EbmDBZnnApDbmCrPE(this));
        }
    }
    
    public void reLoadData() {
        this.folderfilemodelsearchdbi.reloadData();
    }
    
    public void refreshData() {
        (this.gSearchModel = new GSearchModel()).setKey("");
        this.refreshSearchData(this.gSearchModel);
    }
    
    public void refreshSearchData(final GSearchModel gSearchModel) {
        this.noMore = false;
        this.pageIndex = 0;
        this.gSearchModel = gSearchModel;
        if (this.isSearchFragment) {
            this.folderfilemodelsearchdbi.findDrawFiles(gSearchModel, 0, (OnSearchResultListener)new _$$Lambda$LocalPresenterImpl$irTnGjew_QR1LNVFVlo7Ivdplxk(this));
        }
        else {
            this.drawFileDataI.findDrawFiles(gSearchModel, 0, (OnSearchResultListener)new _$$Lambda$LocalPresenterImpl$EGYHVRLDo9LKRvA7tP4rcfe4Svk(this));
        }
    }
    
    public void refreshSearchData(final String s) {
        this.noMore = false;
        this.pageIndex = 0;
        final List drawFiles = this.drawFileDataI.findDrawFiles(s, 0);
        this.folderFileModels.clear();
        if (drawFiles != null) {
            this.folderFileModels.addAll((Collection)drawFiles);
        }
        this.localView.notifyListView();
        this.localView.loadingFinish();
    }
    
    public void rename(final FolderFileModel folderFileModel, final String s) {
        if (this.synFileDBI.getSynFileModelByFolderFileModelId(folderFileModel.getId()) != null) {
            ToastUtil.showShort((CharSequence)this.localView.getString(2131821122));
            return;
        }
        final OperationState rename = this.drawFileDataI.rename(folderFileModel.getId(), s, folderFileModel.getFileTypeEnum());
        ToastUtil.showShort((CharSequence)rename.getInfo());
        if (rename.isSuccess) {
            final FolderFileModel folderFileModel2 = (FolderFileModel)rename.getData();
            if (folderFileModel2 != null) {
                folderFileModel.setFileName(folderFileModel2.getFileName());
                folderFileModel.setFilePath(folderFileModel2.getFilePath());
            }
            this.localView.notifyListView();
        }
    }
    
    public void savePicByFile(final String s) {
        final BaseActivity baseActivity = (BaseActivity)this.localView.getActivity();
        new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$LocalPresenterImpl$Tr2uRCmqGaaG2mLvAnsbx2UlzSw(baseActivity, s)).checkExportPic((Context)baseActivity);
    }
}
