package com.kingagroot.kingdraw.ui;

import android.content.IntentFilter;
import android.os.IBinder;
import com.kingagroot.kingdraw.interfaces.impl.CloudFileDataImple;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.base.MApplication;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import java.io.File;
import com.kingagroot.kingdraw.config.NetConfig$File;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import com.kingagroot.kingdraw.limit.LimitPalette;
import com.kingagroot.kingdraw.config.FileConfig;
import android.graphics.Bitmap$CompressFormat;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import java.io.Serializable;
import android.content.Intent;
import android.content.Context;
import java.util.Iterator;
import android.os.Bundle;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.google.gson.Gson;
import com.kingagroot.kingdraw.model.SynTypeEnum;
import com.kingagroot.kingdraw.model.SynStatusEnum;
import java.util.UUID;
import com.kingagroot.kingdraw.model.CloudFileModel;
import java.util.Collection;
import android.os.Message;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.SynFileModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import android.content.BroadcastReceiver;
import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.interfaces.CloudFileDataI;
import org.xutils.common.Callback$Cancelable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Handler;
import android.app.Service;

public class SynService extends Service
{
    public static final String INTENT_DATA_SYNFILEMODELS = "intent_data_synfilemodels";
    public static final String INTENT_DATA_SYN_AUTO = "intent_data_syn_auto";
    private static final int MSG_WHAT_ADD_DOWNLOAD = 22;
    private static final int MSG_WHAT_ADD_UPLOAD = 12;
    private static final int MSG_WHAT_DOWNLOAD = 21;
    private static final int MSG_WHAT_RESTART_DOWNLOAD = 23;
    private static final int MSG_WHAT_RESTART_UPLOAD = 13;
    private static final int MSG_WHAT_UPLOAD = 11;
    Handler SynHandler;
    private LocalBroadcastManager broadcastManager;
    private Callback$Cancelable cancelable_Download_file;
    private Callback$Cancelable cancelable_Download_picture;
    private Callback$Cancelable cancelable_Upload;
    private CloudFileDataI cloudFileDataI;
    private int dowloadStatus;
    private boolean downFileSuccess;
    private DrawFileDataI drawFileDataI;
    private long errorMsgTime;
    private boolean isAutoSyn;
    private final boolean isDebug;
    private boolean isDownloading;
    private boolean isOverCount;
    private boolean isUploading;
    BroadcastReceiver receiver;
    private SynFileDBI synFileDBI;
    List<SynFileModel> taskDownLoadFileModels;
    List<SynFileModel> taskUploadFileModels;
    private boolean uploadFileSuccess;
    
    public SynService() {
        this.isDebug = false;
        this.taskUploadFileModels = (List<SynFileModel>)new ArrayList();
        this.taskDownLoadFileModels = (List<SynFileModel>)new ArrayList();
        this.isOverCount = false;
        this.isAutoSyn = true;
        this.SynHandler = new Handler() {
            final SynService this$0;
            
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                final int what = message.what;
                final SynFileModel synFileModel = null;
                final SynFileModel synFileModel2 = null;
                final int n = 1;
                boolean b = true;
                Label_0935: {
                    switch (what) {
                        default: {
                            switch (what) {
                                default: {
                                    break Label_0935;
                                }
                                case 23: {
                                    final Bundle data = message.getData();
                                    if (data == null) {
                                        return;
                                    }
                                    final ArrayList list = (ArrayList)data.getSerializable("intent_data_synfilemodels");
                                    if (list != null) {
                                        this.this$0.synFileDBI.addSynFileModels((List)list);
                                        this.this$0.taskDownLoadFileModels.addAll((Collection)list);
                                        this.this$0.startDownload();
                                        this.this$0.sendAddDownloadSuccess();
                                        break Label_0935;
                                    }
                                    break Label_0935;
                                }
                                case 22: {
                                    final Bundle data2 = message.getData();
                                    if (data2 == null) {
                                        return;
                                    }
                                    final ArrayList list2 = (ArrayList)data2.getSerializable("intent_data_synfilemodels");
                                    final ArrayList list3 = new ArrayList();
                                    if (list2 != null) {
                                        for (final CloudFileModel cloudFileModel : list2) {
                                            cloudFileModel.setPicPath(cloudFileModel.getThumbnailOSSID());
                                            final SynFileModel synFileModel3 = new SynFileModel();
                                            synFileModel3.setId(UUID.randomUUID().toString());
                                            synFileModel3.setFolderFileModelId(String.valueOf(cloudFileModel.getId()));
                                            synFileModel3.setStatus(SynStatusEnum.\u7b49\u5f85\u4e2d.getCode());
                                            synFileModel3.setSynType(SynTypeEnum.\u4e0b\u8f7d.getCode());
                                            synFileModel3.setCreatTime(System.currentTimeMillis());
                                            synFileModel3.setFolderFileModelStr(new Gson().toJson((Object)cloudFileModel));
                                            list3.add((Object)synFileModel3);
                                        }
                                        this.this$0.synFileDBI.addSynFileModels((List)list3);
                                        this.this$0.taskDownLoadFileModels.addAll((Collection)list3);
                                        this.this$0.startDownload();
                                        this.this$0.sendAddDownloadSuccess();
                                        break Label_0935;
                                    }
                                    break Label_0935;
                                }
                                case 21: {
                                    if (this.this$0.isDownloading) {
                                        return;
                                    }
                                    this.this$0.isDownloading = true;
                                    if (this.this$0.taskDownLoadFileModels != null && this.this$0.taskDownLoadFileModels.size() > 0) {
                                        while (true) {
                                            for (final SynFileModel synFileModel4 : this.this$0.taskDownLoadFileModels) {
                                                if (synFileModel4 != null && synFileModel4.getStatus() != SynStatusEnum.\u5931\u8d25.getCode()) {
                                                    if (b) {
                                                        this.this$0.delayDownload(synFileModel4);
                                                        break Label_0935;
                                                    }
                                                    this.this$0.isDownloading = false;
                                                    this.this$0.sendBoardDownloadFinish();
                                                    break Label_0935;
                                                }
                                            }
                                            b = false;
                                            SynFileModel synFileModel4 = synFileModel2;
                                            continue;
                                        }
                                    }
                                    this.this$0.isDownloading = false;
                                    this.this$0.sendBoardDownloadFinish();
                                    break Label_0935;
                                }
                            }
                            break;
                        }
                        case 13: {
                            final Bundle data3 = message.getData();
                            if (data3 == null) {
                                return;
                            }
                            final ArrayList list4 = (ArrayList)data3.getSerializable("intent_data_synfilemodels");
                            if (list4 != null) {
                                this.this$0.synFileDBI.addSynFileModels((List)list4);
                                this.this$0.taskUploadFileModels.addAll((Collection)list4);
                                this.this$0.startUpload();
                                this.this$0.sendAddUploadSuccess();
                                break;
                            }
                            break;
                        }
                        case 12: {
                            final Bundle data4 = message.getData();
                            if (data4 == null) {
                                return;
                            }
                            final ArrayList list5 = (ArrayList)data4.getSerializable("intent_data_synfilemodels");
                            final ArrayList list6 = new ArrayList();
                            if (list5 != null) {
                                for (final FolderFileModel folderFileModel : list5) {
                                    final SynFileModel synFileModel5 = new SynFileModel();
                                    synFileModel5.setId(UUID.randomUUID().toString());
                                    synFileModel5.setFolderFileModelId(folderFileModel.getId());
                                    synFileModel5.setStatus(SynStatusEnum.\u7b49\u5f85\u4e2d.getCode());
                                    synFileModel5.setSynType(SynTypeEnum.\u4e0a\u4f20.getCode());
                                    synFileModel5.setCreatTime(System.currentTimeMillis());
                                    synFileModel5.setFolderFileModelStr(new Gson().toJson((Object)folderFileModel));
                                    list6.add((Object)synFileModel5);
                                }
                                this.this$0.synFileDBI.addSynFileModels((List)list6);
                                this.this$0.taskUploadFileModels.addAll((Collection)list6);
                                this.this$0.startUpload();
                                this.this$0.sendAddUploadSuccess();
                                break;
                            }
                            break;
                        }
                        case 11: {
                            if (this.this$0.isUploading) {
                                return;
                            }
                            this.this$0.isUploading = true;
                            if (this.this$0.taskUploadFileModels != null && this.this$0.taskUploadFileModels.size() > 0) {
                                while (true) {
                                    for (final SynFileModel synFileModel6 : this.this$0.taskUploadFileModels) {
                                        if (synFileModel6 != null && synFileModel6.getStatus() != SynStatusEnum.\u5931\u8d25.getCode()) {
                                            final int n2 = n;
                                            if (n2 != 0) {
                                                this.this$0.delayUpload(synFileModel6);
                                                break Label_0935;
                                            }
                                            this.this$0.isUploading = false;
                                            this.this$0.sendBoardUpLoadFinish();
                                            break Label_0935;
                                        }
                                    }
                                    final int n2 = 0;
                                    SynFileModel synFileModel6 = synFileModel;
                                    continue;
                                }
                            }
                            this.this$0.isUploading = false;
                            this.this$0.sendBoardUpLoadFinish();
                            break;
                        }
                    }
                }
            }
        };
        this.receiver = new BroadcastReceiver() {
            final SynService this$0;
            
            public void onReceive(final Context context, final Intent intent) {
                final String action = intent.getAction();
                if (action.equals((Object)"syn_add_download_task")) {
                    final Message message = new Message();
                    message.what = 22;
                    final ArrayList list = (ArrayList)intent.getSerializableExtra("intent_data_synfilemodels");
                    final Bundle data = new Bundle();
                    data.putSerializable("intent_data_synfilemodels", (Serializable)list);
                    message.setData(data);
                    this.this$0.SynHandler.sendMessage(message);
                }
                else if (action.equals((Object)"syn_add_upload_task")) {
                    final Message message2 = new Message();
                    message2.what = 12;
                    final ArrayList list2 = (ArrayList)intent.getSerializableExtra("intent_data_synfilemodels");
                    final Bundle data2 = new Bundle();
                    data2.putSerializable("intent_data_synfilemodels", (Serializable)list2);
                    message2.setData(data2);
                    this.this$0.SynHandler.sendMessage(message2);
                }
                else if (action.equals((Object)"syn_upload_next")) {
                    this.this$0.sendUploadMsg();
                }
                else if (action.equals((Object)"syn_download_next")) {
                    this.this$0.sendDownloadMsg();
                }
                else if (action.equals((Object)"syn_upload_restart")) {
                    final Message message3 = new Message();
                    message3.what = 13;
                    final ArrayList list3 = (ArrayList)intent.getSerializableExtra("intent_data_synfilemodels");
                    final Bundle data3 = new Bundle();
                    data3.putSerializable("intent_data_synfilemodels", (Serializable)list3);
                    message3.setData(data3);
                    this.this$0.SynHandler.sendMessage(message3);
                }
                else if (action.equals((Object)"syn_download_restart")) {
                    final Message message4 = new Message();
                    message4.what = 23;
                    final ArrayList list4 = (ArrayList)intent.getSerializableExtra("intent_data_synfilemodels");
                    final Bundle data4 = new Bundle();
                    data4.putSerializable("intent_data_synfilemodels", (Serializable)list4);
                    message4.setData(data4);
                    this.this$0.SynHandler.sendMessage(message4);
                }
                else if (action.equals((Object)"syn_auto")) {
                    this.this$0.isAutoSyn = intent.getBooleanExtra("intent_data_syn_auto", true);
                }
                else if (action.equals((Object)"login_data_changed")) {
                    this.this$0.checkLogin();
                }
            }
        };
    }
    
    private void DownLoadPicture(final SynFileModel synFileModel, final FolderFileModel folderFileModel, final String s, final String s2) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.picDownLoad());
        params.addBodyParameter("fileOSSID", s);
        params.addBodyParameter("height", "");
        params.addBodyParameter("width", "");
        this.cancelable_Download_picture = build.downLoad(params, s2, true, (RequestCallBack)new SynService$6(this, synFileModel, folderFileModel));
    }
    
    private void DownloadRequest(final SynFileModel synFileModel, final FolderFileModel folderFileModel, final String s) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.fileDownLoad());
        params.addBodyParameter("fileid", folderFileModel.getId());
        this.cancelable_Download_file = build.downLoad(params, s, true, (RequestCallBack)new SynService$5(this, synFileModel, folderFileModel));
    }
    
    private void delayDownload(SynFileModel synFileModel) {
        new Handler().postDelayed((Runnable)new _$$Lambda$SynService$yssi7r9f8VWbHMleNox3139lSZY(this, synFileModel), 500L);
        synFileModel = (SynFileModel)synFileModel.clone();
        synFileModel.setStatus(SynStatusEnum.\u4f20\u8f93\u4e2d.getCode());
        synFileModel.setFileSize(100L);
        synFileModel.setSynProgress(0L);
        this.sendBoardDownloading(synFileModel);
    }
    
    private void delayUpload(SynFileModel synFileModel) {
        new Handler().postDelayed((Runnable)new _$$Lambda$SynService$5cC2rI5ro_FozC9KMALO9mwI0_s(this, synFileModel), 500L);
        synFileModel = (SynFileModel)synFileModel.clone();
        synFileModel.setStatus(SynStatusEnum.\u4f20\u8f93\u4e2d.getCode());
        synFileModel.setFileSize(100L);
        synFileModel.setSynProgress(0L);
        this.sendBoardUploading(synFileModel);
    }
    
    private void downLoadTask(final SynFileModel synFileModel) {
        final CloudFileModel cloudFileModel = (CloudFileModel)new Gson().fromJson(synFileModel.getFolderFileModelStr(), (Class)CloudFileModel.class);
        if (cloudFileModel == null) {
            synFileModel.setStatus(SynStatusEnum.\u6210\u529f.getCode());
            this.sendBoardDownloadSuccess(synFileModel);
            this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u6210\u529f.getCode());
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(".");
        sb.append((Object)Bitmap$CompressFormat.JPEG);
        final String string = sb.toString();
        String picPath;
        String filePath;
        String fileName;
        if (this.cloudFileDataI.findFileByFileName(cloudFileModel.getFileName()) == null) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(FileConfig.DRAW_FILE_PIC_PATH);
            sb2.append(cloudFileModel.getFileNameNoExtension());
            sb2.append(cloudFileModel.getThumbnailOSSID());
            sb2.append(string);
            picPath = sb2.toString();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(FileConfig.DRAW_FILE_PATH);
            sb3.append(cloudFileModel.getFileName());
            filePath = sb3.toString();
            fileName = cloudFileModel.getFileName();
        }
        else {
            fileName = this.cloudFileDataI.getCopyFileName(cloudFileModel.getFileName());
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(FileConfig.DRAW_FILE_PIC_PATH);
            sb4.append(fileName);
            sb4.append(cloudFileModel.getThumbnailOSSID());
            sb4.append(string);
            picPath = sb4.toString();
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(FileConfig.DRAW_FILE_PATH);
            sb5.append(fileName);
            filePath = sb5.toString();
        }
        final FolderFileModel folderFileModel = new FolderFileModel();
        folderFileModel.setId(String.valueOf(cloudFileModel.getId()));
        folderFileModel.setFileName(fileName);
        folderFileModel.setFilePath(filePath);
        folderFileModel.setPicPath(picPath);
        folderFileModel.setFileOssId(cloudFileModel.getFileOSSID());
        folderFileModel.setFileSmallPicId(cloudFileModel.getThumbnailOSSID());
        folderFileModel.setModifyTime(System.currentTimeMillis());
        folderFileModel.setCreateTime(cloudFileModel.getCreateTime());
        folderFileModel.setFileExtension(cloudFileModel.getFileExtension());
        folderFileModel.setFileSmiles(cloudFileModel.getSmiles());
        folderFileModel.setFileLength((int)cloudFileModel.getFileLength());
        this.dowloadStatus = 0;
        this.downFileSuccess = false;
        new LimitPalette((LimitPalette$OnJumpPalette)new SynService$4(this, synFileModel, folderFileModel, filePath, cloudFileModel, picPath)).jumpPaletteCheck((Context)this.getApplication());
    }
    
    private void isDownloadFinish(final SynFileModel synFileModel, final FolderFileModel folderFileModel) {
        final int dowloadStatus = this.dowloadStatus + 1;
        this.dowloadStatus = dowloadStatus;
        if (dowloadStatus >= 2) {
            this.isDownloading = false;
            this.taskDownLoadFileModels.remove((Object)synFileModel);
            if (this.isAutoSyn) {
                this.sendDownloadMsg();
            }
            if (this.downFileSuccess) {
                synFileModel.setStatus(SynStatusEnum.\u6210\u529f.getCode());
                this.sendBoardDownloadSuccess(synFileModel);
                this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u6210\u529f.getCode());
                this.cloudFileDataI.save(folderFileModel);
            }
            else {
                synFileModel.setStatus(SynStatusEnum.\u5931\u8d25.getCode());
                this.sendBoardDownloadFail(synFileModel);
                this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u5931\u8d25.getCode());
            }
        }
    }
    
    private void isLogin() {
        final List upLoadFileModels = this.synFileDBI.getUpLoadFileModels();
        this.taskUploadFileModels.clear();
        if (upLoadFileModels != null && upLoadFileModels.size() > 0) {
            this.taskUploadFileModels.addAll((Collection)upLoadFileModels);
            this.startUpload();
        }
        final List downLodFileModels = this.synFileDBI.getDownLodFileModels();
        this.taskDownLoadFileModels.clear();
        if (downLodFileModels != null && downLodFileModels.size() > 0) {
            this.taskDownLoadFileModels.addAll((Collection)downLodFileModels);
            this.startDownload();
        }
    }
    
    private void onFinishUpload(final SynFileModel synFileModel) {
        if (!this.isOverCount) {
            this.taskUploadFileModels.remove((Object)synFileModel);
            if (this.isAutoSyn) {
                this.sendUploadMsg();
            }
            if (this.uploadFileSuccess) {
                synFileModel.setStatus(SynStatusEnum.\u6210\u529f.getCode());
                this.sendBoardUploadSuccess(synFileModel);
                this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u6210\u529f.getCode());
            }
            else {
                synFileModel.setStatus(SynStatusEnum.\u5931\u8d25.getCode());
                this.sendBoardUploadFail(synFileModel);
                this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u5931\u8d25.getCode());
            }
            this.isUploading = false;
        }
    }
    
    private void outLogin() {
        this.taskUploadFileModels.clear();
        this.taskDownLoadFileModels.clear();
        final Callback$Cancelable cancelable_Download_file = this.cancelable_Download_file;
        if (cancelable_Download_file != null) {
            cancelable_Download_file.cancel();
        }
        final Callback$Cancelable cancelable_Download_picture = this.cancelable_Download_picture;
        if (cancelable_Download_picture != null) {
            cancelable_Download_picture.cancel();
        }
        final Callback$Cancelable cancelable_Upload = this.cancelable_Upload;
        if (cancelable_Upload != null) {
            cancelable_Upload.cancel();
        }
    }
    
    private void sendAddDownloadSuccess() {
        final Intent intent = new Intent();
        intent.setAction("syn_download_add");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendAddUploadSuccess() {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_add");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardDownloadFail(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_download_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardDownloadFinish() {
        final Intent intent = new Intent();
        intent.setAction("syn_download_finish");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardDownloadStart() {
        final Intent intent = new Intent();
        intent.setAction("syn_download_start");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardDownloadSuccess(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_download_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardDownloading(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_download_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardUpLoadFinish() {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_finish");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardUploadFail(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardUploadStart() {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_start");
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardUploadSuccess(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendBoardUploading(final SynFileModel synFileModel) {
        final Intent intent = new Intent();
        intent.setAction("syn_upload_item");
        intent.putExtra("intent_data_synfilemodels", (Serializable)synFileModel);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private void sendDownloadMsg() {
        final Message message = new Message();
        message.what = 21;
        this.SynHandler.sendMessage(message);
    }
    
    private void sendUploadMsg() {
        final Message message = new Message();
        message.what = 11;
        this.SynHandler.sendMessage(message);
    }
    
    private void showErrorMsg(final String s) {
        if (System.currentTimeMillis() - this.errorMsgTime >= 15000L) {
            this.errorMsgTime = System.currentTimeMillis();
            ToastUtil.showShort(2131821065);
        }
    }
    
    private void startDownload() {
        this.sendDownloadMsg();
        this.sendBoardDownloadStart();
    }
    
    private void startUpload() {
        this.sendUploadMsg();
        this.sendBoardUploadStart();
    }
    
    private void uploadTask(final SynFileModel synFileModel) {
        final FolderFileModel drawFile = this.drawFileDataI.findDrawFile(synFileModel.getFolderFileModelId());
        if (drawFile == null) {
            synFileModel.setStatus(SynStatusEnum.\u6210\u529f.getCode());
            this.sendBoardUploadSuccess(synFileModel);
            this.synFileDBI.setSynFileModelStatus(synFileModel.getId(), SynStatusEnum.\u6210\u529f.getCode());
            this.onFinishUpload(synFileModel);
            return;
        }
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$File.upLoadFileAutoReName());
        final File file = new File(drawFile.getFilePath());
        final File file2 = new File(drawFile.getPicPath());
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("filename", (Object)drawFile.getFullFileName()));
        ((List)list).add((Object)new KeyValue("filesmiles", (Object)drawFile.getFileSmiles()));
        ((List)list).add((Object)new KeyValue("oss", (Object)file));
        ((List)list).add((Object)new KeyValue("smallpic", (Object)file2));
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        this.isOverCount = false;
        this.cancelable_Upload = build.request(params, (RequestCallBack)new SynService$3(this, synFileModel));
    }
    
    public void checkLogin() {
        final boolean login = MApplication.getInstance().isLogin();
        this.drawFileDataI = (DrawFileDataI)new DrawFileDataImpl();
        if (login) {
            this.synFileDBI = (SynFileDBI)new SynFileDBImpl();
            this.cloudFileDataI = (CloudFileDataI)new CloudFileDataImple();
            this.isLogin();
        }
        else {
            this.outLogin();
        }
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.getApplication());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("login_data_changed");
        intentFilter.addAction("syn_add_download_task");
        intentFilter.addAction("syn_add_upload_task");
        intentFilter.addAction("syn_auto");
        intentFilter.addAction("syn_upload_next");
        intentFilter.addAction("syn_download_next");
        intentFilter.addAction("syn_download_restart");
        intentFilter.addAction("syn_upload_restart");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
        this.checkLogin();
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.broadcastManager.unregisterReceiver(this.receiver);
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        return super.onStartCommand(intent, n, n2);
    }
}
