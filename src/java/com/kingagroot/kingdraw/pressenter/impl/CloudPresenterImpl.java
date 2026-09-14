package com.kingagroot.kingdraw.pressenter.impl;

import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.limit.LimitPalette;
import com.kingagroot.kingdraw.limit.LimitDialog;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface$OnClickListener;
import com.kingagroot.kingdraw.widget.WordBreakTextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog$Builder;
import org.xutils.http.HttpMethod;
import android.content.Intent;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.Collection;
import com.kingagroot.kingdraw.config.NetConfig;
import com.kingagroot.kingdraw.http.NewNetBean;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$File;
import java.util.UUID;
import com.kingagroot.kingdraw.utils.SmilesUtils;
import android.text.TextUtils;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.goodsrc.library.utils.ToastUtil;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import java.io.File;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.interfaces.impl.CloudFileDataImple;
import java.util.ArrayList;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.pressenter.view.CloudView;
import com.kingagroot.kingdraw.model.CloudFileModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.CloudFileDataI;
import com.kingagroot.kingdraw.pressenter.CloudPresenterI;

public class CloudPresenterImpl implements CloudPresenterI
{
    private static final String PIC_EXT;
    public static int totalCount;
    private final CloudFileDataI cloudFileDataI;
    private final List<CloudFileModel> cloudFileModels;
    private final CloudView cloudView;
    private boolean downFileSuccess;
    private int downloadStatus;
    boolean isAdd;
    private String modifyTime;
    private boolean noMore;
    private CloudPresenterImpl.CloudPresenterImpl$OnGetCloudFileCount onGetCloudFileCount;
    private final List<String> searchKey;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(".");
        sb.append((Object)Bitmap$CompressFormat.JPEG);
        PIC_EXT = sb.toString();
    }
    
    public CloudPresenterImpl(final CloudView cloudView) {
        this.cloudFileModels = (List<CloudFileModel>)new ArrayList();
        this.searchKey = (List<String>)new ArrayList();
        this.cloudView = cloudView;
        this.cloudFileDataI = (CloudFileDataI)new CloudFileDataImple();
    }
    
    private void downLoadPicture(final CloudFileModel cloudFileModel, final String s, final String s2) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.picDownLoad());
        params.addBodyParameter("fileOSSID", s);
        params.addBodyParameter("width", "");
        params.addBodyParameter("height", "");
        build.downLoad(params, s2, true, (RequestCallBack)new RequestCallBack<File>(this, cloudFileModel) {
            final CloudPresenterImpl this$0;
            final CloudFileModel val$model;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onFinished() {
                super.onFinished();
                this.this$0.isDownloadFinish(this.val$model);
            }
            
            public void onSuccess(final File file) {
            }
        });
    }
    
    private void downloadRequest(final CloudFileModel cloudFileModel, final String s, final String s2, final String s3) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.fileDownLoad());
        final StringBuilder sb = new StringBuilder();
        sb.append(cloudFileModel.getId());
        sb.append("");
        params.addBodyParameter("fileid", sb.toString());
        build.downLoad(params, s2, true, (RequestCallBack)new RequestCallBack<File>(this, cloudFileModel, s2, s, s3) {
            final CloudPresenterImpl this$0;
            final String val$fileName;
            final String val$filePath;
            final CloudFileModel val$model;
            final String val$picPath;
            
            public void onError(final Exception ex, final String s) {
                super.onError(ex, s);
                ToastUtil.showShort(2131820850);
            }
            
            public void onFinished() {
                super.onFinished();
                this.this$0.isDownloadFinish(this.val$model);
            }
            
            public void onSuccess(final File file) {
                this.this$0.downFileSuccess = true;
                final FolderFileModel folderFileModel = new FolderFileModel();
                if (TextUtils.isEmpty((CharSequence)this.val$model.getSmiles())) {
                    folderFileModel.setFileSmiles(SmilesUtils.getSmiles(this.val$filePath));
                }
                folderFileModel.setId(UUID.randomUUID().toString());
                folderFileModel.setFileName(this.val$fileName);
                folderFileModel.setFilePath(this.val$filePath);
                folderFileModel.setPicPath(this.val$picPath);
                folderFileModel.setModifyTime(System.currentTimeMillis());
                folderFileModel.setCreateTime(this.val$model.getCreateTime());
                folderFileModel.setFileExtension(this.val$model.getFileExtension());
                this.this$0.cloudFileDataI.save(folderFileModel);
            }
        });
    }
    
    private void getListFile(final List<String> list) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.getFileList());
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("modifyTimeStr", (Object)this.modifyTime);
            final JSONArray jsonArray = new JSONArray();
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next());
            }
            jsonObject.put("fileNameArray", (Object)jsonArray);
            jsonObject.put("marksArray", (Object)"");
            jsonObject.put("fileSmiles", (Object)"");
            params.addBodyParameter("", jsonObject.toString());
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<CloudPresenterImpl$CloudListModel>>(this) {
            final CloudPresenterImpl this$0;
            
            public void onFinished() {
                super.onFinished();
                this.this$0.cloudView.loadingFinish();
            }
            
            public void onSuccess(final NewNetBean<CloudPresenterImpl$CloudListModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    final List datas = ((CloudPresenterImpl$CloudListModel)newNetBean.getData()).getDatas();
                    CloudPresenterImpl.totalCount = ((CloudPresenterImpl$CloudListModel)newNetBean.getData()).getUserTotalCount();
                    if (!this.this$0.isAdd) {
                        this.this$0.cloudFileModels.clear();
                        if (datas != null) {
                            this.this$0.cloudFileModels.addAll((Collection)datas);
                        }
                    }
                    else if (datas != null && datas.size() > 0) {
                        this.this$0.cloudFileModels.addAll((Collection)datas);
                    }
                    else {
                        this.this$0.noMore = true;
                        ToastUtil.showShort((CharSequence)this.this$0.cloudView.getString(2131821091));
                    }
                    if (datas != null && datas.size() > 0) {
                        this.this$0.modifyTime = String.valueOf(((CloudFileModel)datas.get(datas.size() - 1)).getModifyTime());
                    }
                    this.this$0.cloudView.notifyListView();
                    if (this.this$0.onGetCloudFileCount != null) {
                        this.this$0.onGetCloudFileCount.onGetCloudFileCount(CloudPresenterImpl.totalCount);
                    }
                }
                else {
                    ToastUtil.showShort((CharSequence)newNetBean.getMessage());
                }
            }
        });
    }
    
    private boolean isDownloadFinish(final CloudFileModel cloudFileModel) {
        final int downloadStatus = this.downloadStatus + 1;
        this.downloadStatus = downloadStatus;
        if (downloadStatus >= 2) {
            this.cloudView.loadingFinish();
            if (this.downFileSuccess) {
                final StringBuilder sb = new StringBuilder();
                sb.append(cloudFileModel.getFileName());
                sb.append(this.cloudView.getString(2131820786));
                ToastUtil.showShort((CharSequence)sb.toString());
                LocalBroadcastManager.getInstance((Context)this.cloudView.getActivity()).sendBroadcast(new Intent("local_data_changed"));
            }
            return true;
        }
        return false;
    }
    
    public void deleteFile(final CloudFileModel cloudFileModel) {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        final RequestParams params = build.params(NetConfig$File.deleteFileCloud());
        params.addBodyParameter("fileId", String.valueOf(cloudFileModel.getId()));
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<String>>(this, cloudFileModel) {
            final CloudPresenterImpl this$0;
            final CloudFileModel val$model;
            
            public void onFinished() {
                super.onFinished();
                this.this$0.cloudView.loadingFinish();
            }
            
            public void onSuccess(final NewNetBean<String> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    this.this$0.cloudFileModels.remove((Object)this.val$model);
                    this.this$0.cloudView.notifyListView();
                }
                ToastUtil.showShort((CharSequence)newNetBean.getMessage());
            }
        });
    }
    
    public void downloadCheck(final CloudFileModel cloudFileModel) {
        final FolderFileModel fileByFileName = this.cloudFileDataI.findFileByFileName(cloudFileModel.getFileName());
        if (fileByFileName == null) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.cloudView.requireContext(), 2131886327);
            final View inflate = LayoutInflater.from(this.cloudView.getContext()).inflate(2131493085, (ViewGroup)null);
            ((WordBreakTextView)inflate.findViewById(2131297623)).setText(this.cloudView.requireContext().getString(2131820774), new Object[] { cloudFileModel.getFileName() });
            alertDialog$Builder.setView(inflate);
            alertDialog$Builder.setTitle(2131821524).setPositiveButton((CharSequence)this.cloudView.requireContext().getString(2131820588), (DialogInterface$OnClickListener)new _$$Lambda$CloudPresenterImpl$s8DTrLen1oq1Oe3U4sJYxHPvsx4(this, cloudFileModel)).setNegativeButton((CharSequence)this.cloudView.getContext().getString(2131820661), (DialogInterface$OnClickListener)null);
            final AlertDialog show = alertDialog$Builder.show();
            show.setCanceledOnTouchOutside(false);
            show.setCancelable(false);
            show.getButton(-1).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
            show.getButton(-2).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
            show.getButton(-3).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
        }
        else {
            final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder(this.cloudView.getContext(), 2131886327);
            final View inflate2 = LayoutInflater.from(this.cloudView.getContext()).inflate(2131493085, (ViewGroup)null);
            ((WordBreakTextView)inflate2.findViewById(2131297623)).setText(this.cloudView.getString(2131820788), new Object[] { fileByFileName.getFullFileName(), cloudFileModel.getFileName() });
            alertDialog$Builder2.setView(inflate2);
            alertDialog$Builder2.setTitle((CharSequence)this.cloudView.getString(2131821524)).setPositiveButton((CharSequence)this.cloudView.getString(2131821316), (DialogInterface$OnClickListener)new _$$Lambda$CloudPresenterImpl$cY1TlKd6wDLcz9Ja6G_H5vA06qc(this, cloudFileModel)).setNegativeButton((CharSequence)this.cloudView.getString(2131820960), (DialogInterface$OnClickListener)new _$$Lambda$CloudPresenterImpl$Npi1YFefN7w6rPKsBs21hVI7mrI(this, cloudFileModel)).setNeutralButton((CharSequence)this.cloudView.getString(2131820661), (DialogInterface$OnClickListener)null);
            final AlertDialog show2 = alertDialog$Builder2.show();
            show2.setCanceledOnTouchOutside(false);
            show2.setCancelable(false);
            show2.getButton(-1).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
            show2.getButton(-2).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
            show2.getButton(-3).setTextColor(ContextCompat.getColor(this.cloudView.getContext(), 2131099773));
        }
    }
    
    public List<CloudFileModel> getDates() {
        return this.cloudFileModels;
    }
    
    public int getTotalCount() {
        return CloudPresenterImpl.totalCount;
    }
    
    public void loadMoreData() {
        this.loadMoreSearchData();
    }
    
    public void loadMoreSearchData() {
        if (this.noMore) {
            this.cloudView.loadingFinish();
            return;
        }
        this.isAdd = true;
        this.cloudView.loading();
        this.getListFile(this.searchKey);
    }
    
    public void refreshData() {
        this.refreshSearchData("");
    }
    
    public void refreshSearchData(final String s) {
        this.modifyTime = "";
        this.noMore = false;
        this.searchKey.clear();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.searchKey.add((Object)s);
        }
        this.isAdd = false;
        if (MApplication.getInstance().isLogin()) {
            this.getListFile(this.searchKey);
        }
        else {
            this.cloudFileModels.clear();
            this.cloudView.loadingFinish();
        }
    }
    
    public void renameFile(final CloudFileModel cloudFileModel, final String s) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.fileReName());
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileID", cloudFileModel.getId());
            jsonObject.put("newFileName", (Object)s);
            params.addBodyParameter("", jsonObject.toString());
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<CloudFileModel>>(this, cloudFileModel, s) {
            final CloudPresenterImpl this$0;
            final CloudFileModel val$model;
            final String val$newName;
            
            public void onFinished() {
                super.onFinished();
                this.this$0.cloudView.loadingFinish();
            }
            
            public void onSuccess(final NewNetBean<CloudFileModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    this.val$model.setFileName(this.val$newName);
                    this.this$0.cloudView.notifyListView();
                }
                ToastUtil.showShort((CharSequence)newNetBean.getMessage());
            }
        });
    }
    
    public void setOnGetCloudFileCount(final CloudPresenterImpl.CloudPresenterImpl$OnGetCloudFileCount onGetCloudFileCount) {
        this.onGetCloudFileCount = onGetCloudFileCount;
    }
}
