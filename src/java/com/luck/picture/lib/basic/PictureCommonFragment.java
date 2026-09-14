package com.luck.picture.lib.basic;

import android.os.Parcelable;
import com.luck.picture.lib.utils.MediaStoreUtils;
import android.view.View$OnKeyListener;
import com.luck.picture.lib.interfaces.OnRecordAudioInterceptListener;
import com.luck.picture.lib.config.PermissionEvent;
import com.luck.picture.lib.R$raw;
import com.luck.picture.lib.dialog.PictureLoadingDialog;
import com.luck.picture.lib.interfaces.OnItemClickListener;
import com.luck.picture.lib.dialog.PhotoItemSelectedDialog;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.utils.FileDirMap;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.R$anim;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.content.res.Configuration;
import com.luck.picture.lib.interfaces.OnRequestPermissionListener;
import org.json.JSONObject;
import org.json.JSONArray;
import com.luck.picture.lib.config.Crop;
import com.luck.picture.lib.service.ForegroundService;
import com.luck.picture.lib.language.PictureLanguageUtils;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.permissions.PermissionUtil;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import com.luck.picture.lib.utils.SpUtils;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.luck.picture.lib.interfaces.OnSelectFilterListener;
import com.luck.picture.lib.utils.ToastUtils;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.engine.CropEngine;
import java.util.List;
import com.luck.picture.lib.engine.CropFileEngine;
import java.util.Collection;
import java.util.HashSet;
import com.luck.picture.lib.utils.BitmapUtils;
import com.luck.picture.lib.dialog.RemindDialog;
import com.luck.picture.lib.immersive.ImmersiveManager;
import com.luck.picture.lib.utils.SdkVersionUtils;
import android.app.Activity;
import com.luck.picture.lib.utils.ActivityCompatHelper;
import com.luck.picture.lib.config.SelectMimeType;
import android.content.Intent;
import com.luck.picture.lib.engine.PictureSelectorEngine;
import com.luck.picture.lib.app.PictureAppMaster;
import java.io.File;
import java.io.FileNotFoundException;
import com.luck.picture.lib.utils.MediaUtils;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import com.luck.picture.lib.utils.PictureFileUtils;
import java.io.FileInputStream;
import android.net.Uri;
import android.text.TextUtils;
import com.luck.picture.lib.thread.PictureThreadUtils$Task;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.interfaces.OnCallbackIndexListener;
import com.luck.picture.lib.thread.PictureThreadUtils$SimpleTask;
import com.luck.picture.lib.R$string;
import java.util.Iterator;
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import java.util.Map$Entry;
import com.luck.picture.lib.config.PictureMimeType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import com.luck.picture.lib.entity.LocalMedia;
import android.media.SoundPool;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.permissions.PermissionResultCallback;
import android.app.Dialog;
import com.luck.picture.lib.loader.IBridgeMediaLoader;
import android.content.Context;
import androidx.fragment.app.Fragment;

public abstract class PictureCommonFragment extends Fragment implements IPictureSelectorCommonEvent
{
    public static final String TAG;
    private Context context;
    private long enterAnimDuration;
    protected IBridgePictureBehavior iBridgePictureBehavior;
    protected IBridgeMediaLoader mLoader;
    private Dialog mLoadingDialog;
    protected int mPage;
    private PermissionResultCallback mPermissionResultCallback;
    protected SelectorConfig selectorConfig;
    private int soundID;
    private SoundPool soundPool;
    protected Dialog tipsDialog;
    
    static {
        TAG = PictureCommonFragment.class.getSimpleName();
    }
    
    public PictureCommonFragment() {
        this.mPage = 1;
    }
    
    private void addBitmapWatermark(final ArrayList<LocalMedia> list) {
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < list.size(); ++i) {
            final LocalMedia localMedia = (LocalMedia)list.get(i);
            if (!PictureMimeType.isHasAudio(localMedia.getMimeType())) {
                concurrentHashMap.put((Object)localMedia.getAvailablePath(), (Object)localMedia);
            }
        }
        if (concurrentHashMap.size() == 0) {
            this.dispatchWatermarkResult(list);
        }
        else {
            for (final Map$Entry map$Entry : concurrentHashMap.entrySet()) {
                this.selectorConfig.onBitmapWatermarkListener.onAddBitmapWatermark(this.getAppContext(), (String)map$Entry.getKey(), ((LocalMedia)map$Entry.getValue()).getMimeType(), (OnKeyValueResultCallbackListener)new PictureCommonFragment$12(this, (ArrayList)list, concurrentHashMap));
            }
        }
    }
    
    private boolean checkCompleteSelectLimit() {
        if (this.selectorConfig.selectionMode == 2) {
            if (!this.selectorConfig.isOnlyCamera) {
                if (this.selectorConfig.isWithVideoImage) {
                    final ArrayList selectedResult = this.selectorConfig.getSelectedResult();
                    int i = 0;
                    int n = 0;
                    int n2 = 0;
                    while (i < selectedResult.size()) {
                        if (PictureMimeType.isHasVideo(((LocalMedia)selectedResult.get(i)).getMimeType())) {
                            ++n2;
                        }
                        else {
                            ++n;
                        }
                        ++i;
                    }
                    if (this.selectorConfig.minSelectNum > 0 && n < this.selectorConfig.minSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), (LocalMedia)null, this.selectorConfig, 5)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_min_img_num, new Object[] { String.valueOf(this.selectorConfig.minSelectNum) }));
                        return true;
                    }
                    else if (this.selectorConfig.minVideoSelectNum > 0 && n2 < this.selectorConfig.minVideoSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), (LocalMedia)null, this.selectorConfig, 7)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_min_video_num, new Object[] { String.valueOf(this.selectorConfig.minVideoSelectNum) }));
                        return true;
                    }
                }
                else {
                    final String resultFirstMimeType = this.selectorConfig.getResultFirstMimeType();
                    if (PictureMimeType.isHasImage(resultFirstMimeType) && this.selectorConfig.minSelectNum > 0 && this.selectorConfig.getSelectCount() < this.selectorConfig.minSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), (LocalMedia)null, this.selectorConfig, 5)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_min_img_num, new Object[] { String.valueOf(this.selectorConfig.minSelectNum) }));
                        return true;
                    }
                    else if (PictureMimeType.isHasVideo(resultFirstMimeType) && this.selectorConfig.minVideoSelectNum > 0 && this.selectorConfig.getSelectCount() < this.selectorConfig.minVideoSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), (LocalMedia)null, this.selectorConfig, 7)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_min_video_num, new Object[] { String.valueOf(this.selectorConfig.minVideoSelectNum) }));
                        return true;
                    }
                    else if (PictureMimeType.isHasAudio(resultFirstMimeType) && this.selectorConfig.minAudioSelectNum > 0 && this.selectorConfig.getSelectCount() < this.selectorConfig.minAudioSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), (LocalMedia)null, this.selectorConfig, 12)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_min_audio_num, new Object[] { String.valueOf(this.selectorConfig.minAudioSelectNum) }));
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Deprecated
    private void copyExternalPathToAppInDirFor29(final ArrayList<LocalMedia> list) {
        this.showLoading();
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new PictureThreadUtils$SimpleTask<ArrayList<LocalMedia>>(this, list) {
            final PictureCommonFragment this$0;
            final ArrayList val$result;
            
            public ArrayList<LocalMedia> doInBackground() {
                for (int i = 0; i < this.val$result.size(); ++i) {
                    this.this$0.selectorConfig.sandboxFileEngine.onStartSandboxFileTransform(this.this$0.getAppContext(), this.this$0.selectorConfig.isCheckOriginalImage, i, (LocalMedia)this.val$result.get(i), (OnCallbackIndexListener)new PictureCommonFragment$14$1(this));
                }
                return (ArrayList<LocalMedia>)this.val$result;
            }
            
            public void onSuccess(final ArrayList<LocalMedia> list) {
                PictureThreadUtils.cancel((PictureThreadUtils$Task)this);
                this.this$0.dispatchUriToFileTransformResult(list);
            }
        });
    }
    
    private void copyOutputAudioToDir() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.selectorConfig.outPutAudioDir)) {
                Object openInputStream;
                if (PictureMimeType.isContent(this.selectorConfig.cameraPath)) {
                    openInputStream = PictureContentResolver.openInputStream(this.getAppContext(), Uri.parse(this.selectorConfig.cameraPath));
                }
                else {
                    openInputStream = new FileInputStream(this.selectorConfig.cameraPath);
                }
                String s;
                if (TextUtils.isEmpty((CharSequence)this.selectorConfig.outPutAudioFileName)) {
                    s = "";
                }
                else if (this.selectorConfig.isOnlyCamera) {
                    s = this.selectorConfig.outPutAudioFileName;
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(System.currentTimeMillis());
                    sb.append("_");
                    sb.append(this.selectorConfig.outPutAudioFileName);
                    s = sb.toString();
                }
                final File cameraFile = PictureFileUtils.createCameraFile(this.getAppContext(), this.selectorConfig.chooseMode, s, "", this.selectorConfig.outPutAudioDir);
                if (PictureFileUtils.writeFileFromIS((InputStream)openInputStream, (OutputStream)new FileOutputStream(cameraFile.getAbsolutePath()))) {
                    MediaUtils.deleteUri(this.getAppContext(), this.selectorConfig.cameraPath);
                    this.selectorConfig.cameraPath = cameraFile.getAbsolutePath();
                }
            }
        }
        catch (final FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void createCompressEngine() {
        if (this.selectorConfig.isCompressEngine) {
            if (this.selectorConfig.compressFileEngine == null) {
                final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
                if (pictureSelectorEngine != null) {
                    this.selectorConfig.compressFileEngine = pictureSelectorEngine.createCompressFileEngine();
                }
            }
            if (this.selectorConfig.compressEngine == null) {
                final PictureSelectorEngine pictureSelectorEngine2 = PictureAppMaster.getInstance().getPictureSelectorEngine();
                if (pictureSelectorEngine2 != null) {
                    this.selectorConfig.compressEngine = pictureSelectorEngine2.createCompressEngine();
                }
            }
        }
    }
    
    private void createImageLoaderEngine() {
        if (this.selectorConfig.imageEngine == null) {
            final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine != null) {
                this.selectorConfig.imageEngine = pictureSelectorEngine.createImageLoaderEngine();
            }
        }
    }
    
    private void createLayoutResourceListener() {
        if (this.selectorConfig.isInjectLayoutResource && this.selectorConfig.onLayoutResourceListener == null) {
            final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine != null) {
                this.selectorConfig.onLayoutResourceListener = pictureSelectorEngine.createLayoutResourceListener();
            }
        }
    }
    
    private void createLoaderDataEngine() {
        if (this.selectorConfig.isLoaderDataEngine && this.selectorConfig.loaderDataEngine == null) {
            final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine != null) {
                this.selectorConfig.loaderDataEngine = pictureSelectorEngine.createLoaderDataEngine();
            }
        }
        if (this.selectorConfig.isLoaderFactoryEngine && this.selectorConfig.loaderFactory == null) {
            final PictureSelectorEngine pictureSelectorEngine2 = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine2 != null) {
                this.selectorConfig.loaderFactory = pictureSelectorEngine2.onCreateLoader();
            }
        }
    }
    
    private void createResultCallbackListener() {
        if (this.selectorConfig.isResultListenerBack && this.selectorConfig.onResultCallListener == null) {
            final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine != null) {
                this.selectorConfig.onResultCallListener = pictureSelectorEngine.getResultCallbackListener();
            }
        }
    }
    
    private void createSandboxFileEngine() {
        if (this.selectorConfig.isSandboxFileEngine) {
            if (this.selectorConfig.uriToFileTransformEngine == null) {
                final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
                if (pictureSelectorEngine != null) {
                    this.selectorConfig.uriToFileTransformEngine = pictureSelectorEngine.createUriToFileTransformEngine();
                }
            }
            if (this.selectorConfig.sandboxFileEngine == null) {
                final PictureSelectorEngine pictureSelectorEngine2 = PictureAppMaster.getInstance().getPictureSelectorEngine();
                if (pictureSelectorEngine2 != null) {
                    this.selectorConfig.sandboxFileEngine = pictureSelectorEngine2.createSandboxFileEngine();
                }
            }
        }
    }
    
    private void createVideoPlayerEngine() {
        if (this.selectorConfig.videoPlayerEngine == null) {
            final PictureSelectorEngine pictureSelectorEngine = PictureAppMaster.getInstance().getPictureSelectorEngine();
            if (pictureSelectorEngine != null) {
                this.selectorConfig.videoPlayerEngine = pictureSelectorEngine.createVideoPlayerEngine();
            }
        }
    }
    
    private void dispatchHandleCamera(final Intent intent) {
        PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new PictureThreadUtils$SimpleTask<LocalMedia>(this, intent) {
            final PictureCommonFragment this$0;
            final Intent val$intent;
            
            public LocalMedia doInBackground() {
                final String outputPath = this.this$0.getOutputPath(this.val$intent);
                if (!TextUtils.isEmpty((CharSequence)outputPath)) {
                    this.this$0.selectorConfig.cameraPath = outputPath;
                }
                if (TextUtils.isEmpty((CharSequence)this.this$0.selectorConfig.cameraPath)) {
                    return null;
                }
                if (this.this$0.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
                    this.this$0.copyOutputAudioToDir();
                }
                final PictureCommonFragment this$0 = this.this$0;
                final LocalMedia buildLocalMedia = this$0.buildLocalMedia(this$0.selectorConfig.cameraPath);
                buildLocalMedia.setCameraSource(true);
                return buildLocalMedia;
            }
            
            public void onSuccess(final LocalMedia localMedia) {
                PictureThreadUtils.cancel((PictureThreadUtils$Task)this);
                if (localMedia != null) {
                    this.this$0.onScannerScanFile(localMedia);
                    this.this$0.dispatchCameraMediaResult(localMedia);
                }
                this.this$0.selectorConfig.cameraPath = "";
            }
        });
    }
    
    private void dispatchUriToFileTransformResult(final ArrayList<LocalMedia> list) {
        this.showLoading();
        if (this.checkAddBitmapWatermark()) {
            this.addBitmapWatermark(list);
        }
        else if (this.checkVideoThumbnail()) {
            this.videoThumbnail(list);
        }
        else {
            this.onCallBackResult(list);
        }
    }
    
    private void dispatchWatermarkResult(final ArrayList<LocalMedia> list) {
        if (this.checkVideoThumbnail()) {
            this.videoThumbnail(list);
        }
        else {
            this.onCallBackResult(list);
        }
    }
    
    private static String getTipsMsg(final Context context, final String s, final int n) {
        if (PictureMimeType.isHasVideo(s)) {
            return context.getString(R$string.ps_message_video_max_num, new Object[] { String.valueOf(n) });
        }
        if (PictureMimeType.isHasAudio(s)) {
            return context.getString(R$string.ps_message_audio_max_num, new Object[] { String.valueOf(n) });
        }
        return context.getString(R$string.ps_message_max_num, new Object[] { String.valueOf(n) });
    }
    
    private void mergeOriginalImage(final ArrayList<LocalMedia> list) {
        if (this.selectorConfig.isCheckOriginalImage) {
            for (int i = 0; i < list.size(); ++i) {
                final LocalMedia localMedia = (LocalMedia)list.get(i);
                localMedia.setOriginal(true);
                localMedia.setOriginalPath(localMedia.getPath());
            }
        }
    }
    
    private void onCallBackResult(final ArrayList<LocalMedia> list) {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            this.dismissLoading();
            if (this.selectorConfig.isActivityResultBack) {
                this.getActivity().setResult(-1, PictureSelector.putIntentResult((ArrayList)list));
                this.onSelectFinish(-1, list);
            }
            else if (this.selectorConfig.onResultCallListener != null) {
                this.selectorConfig.onResultCallListener.onResult((ArrayList)list);
            }
            this.onExitPictureSelector();
        }
    }
    
    private void onScannerScanFile(final LocalMedia localMedia) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        if (SdkVersionUtils.isQ()) {
            if (PictureMimeType.isHasVideo(localMedia.getMimeType()) && PictureMimeType.isContent(localMedia.getPath())) {
                new PictureMediaScannerConnection((Context)this.getActivity(), localMedia.getRealPath());
            }
        }
        else {
            String s;
            if (PictureMimeType.isContent(localMedia.getPath())) {
                s = localMedia.getRealPath();
            }
            else {
                s = localMedia.getPath();
            }
            new PictureMediaScannerConnection((Context)this.getActivity(), s);
            if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                final int dcimLastImageId = MediaUtils.getDCIMLastImageId(this.getAppContext(), new File(s).getParent());
                if (dcimLastImageId != -1) {
                    MediaUtils.removeMedia(this.getAppContext(), dcimLastImageId);
                }
            }
        }
    }
    
    private void playClickEffect() {
        if (this.soundPool != null && this.selectorConfig.isOpenClickSound) {
            this.soundPool.play(this.soundID, 0.1f, 0.5f, 0, 1, 1.0f);
        }
    }
    
    private void releaseSoundPool() {
        try {
            if (this.soundPool != null) {
                this.soundPool.release();
                this.soundPool = null;
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setTranslucentStatusBar() {
        if (this.selectorConfig.isPreviewFullScreenMode) {
            ImmersiveManager.translucentStatusBar((Activity)this.requireActivity(), this.selectorConfig.selectorStyle.getSelectMainStyle().isDarkStatusBarBlack());
        }
    }
    
    private void showTipsDialog(final String s) {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        try {
            if (this.tipsDialog != null && this.tipsDialog.isShowing()) {
                return;
            }
            (this.tipsDialog = (Dialog)RemindDialog.buildDialog(this.getAppContext(), s)).show();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void uriToFileTransform29(final ArrayList<LocalMedia> list) {
        this.showLoading();
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < list.size(); ++i) {
            final LocalMedia localMedia = (LocalMedia)list.get(i);
            concurrentHashMap.put((Object)localMedia.getPath(), (Object)localMedia);
        }
        if (concurrentHashMap.size() == 0) {
            this.dispatchUriToFileTransformResult(list);
        }
        else {
            PictureThreadUtils.executeByIo((PictureThreadUtils$Task)new PictureThreadUtils$SimpleTask<ArrayList<LocalMedia>>(this, concurrentHashMap, list) {
                final PictureCommonFragment this$0;
                final ConcurrentHashMap val$queue;
                final ArrayList val$result;
                
                public ArrayList<LocalMedia> doInBackground() {
                    final Iterator iterator = this.val$queue.entrySet().iterator();
                    while (iterator.hasNext()) {
                        final LocalMedia localMedia = (LocalMedia)((Map$Entry)iterator.next()).getValue();
                        if (this.this$0.selectorConfig.isCheckOriginalImage || TextUtils.isEmpty((CharSequence)localMedia.getSandboxPath())) {
                            this.this$0.selectorConfig.uriToFileTransformEngine.onUriToFileAsyncTransform(this.this$0.getAppContext(), localMedia.getPath(), localMedia.getMimeType(), (OnKeyValueResultCallbackListener)new PictureCommonFragment$13$1(this));
                        }
                    }
                    return (ArrayList<LocalMedia>)this.val$result;
                }
                
                public void onSuccess(final ArrayList<LocalMedia> list) {
                    PictureThreadUtils.cancel((PictureThreadUtils$Task)this);
                    this.this$0.dispatchUriToFileTransformResult(list);
                }
            });
        }
    }
    
    private void videoThumbnail(final ArrayList<LocalMedia> list) {
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < list.size(); ++i) {
            final LocalMedia localMedia = (LocalMedia)list.get(i);
            final String availablePath = localMedia.getAvailablePath();
            if (PictureMimeType.isHasVideo(localMedia.getMimeType()) || PictureMimeType.isUrlHasVideo(availablePath)) {
                concurrentHashMap.put((Object)availablePath, (Object)localMedia);
            }
        }
        if (concurrentHashMap.size() == 0) {
            this.onCallBackResult(list);
        }
        else {
            final Iterator iterator = concurrentHashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                this.selectorConfig.onVideoThumbnailEventListener.onVideoThumbnail(this.getAppContext(), (String)((Map$Entry)iterator.next()).getKey(), (OnKeyValueResultCallbackListener)new PictureCommonFragment$11(this, concurrentHashMap, (ArrayList)list));
            }
        }
    }
    
    protected LocalMedia buildLocalMedia(final String sandboxPath) {
        final LocalMedia generateLocalMedia = LocalMedia.generateLocalMedia(this.getAppContext(), sandboxPath);
        generateLocalMedia.setChooseModel(this.selectorConfig.chooseMode);
        if (SdkVersionUtils.isQ() && !PictureMimeType.isContent(sandboxPath)) {
            generateLocalMedia.setSandboxPath(sandboxPath);
        }
        else {
            generateLocalMedia.setSandboxPath((String)null);
        }
        if (this.selectorConfig.isCameraRotateImage && PictureMimeType.isHasImage(generateLocalMedia.getMimeType())) {
            BitmapUtils.rotateImage(this.getAppContext(), sandboxPath);
        }
        return generateLocalMedia;
    }
    
    public boolean checkAddBitmapWatermark() {
        return this.selectorConfig.onBitmapWatermarkListener != null;
    }
    
    public boolean checkCompressValidity() {
        if (this.selectorConfig.compressFileEngine != null) {
            for (int i = 0; i < this.selectorConfig.getSelectCount(); ++i) {
                if (PictureMimeType.isHasImage(((LocalMedia)this.selectorConfig.getSelectedResult().get(i)).getMimeType())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkCropValidity() {
        final CropFileEngine cropFileEngine = this.selectorConfig.cropFileEngine;
        boolean b = false;
        if (cropFileEngine != null) {
            final HashSet set = new HashSet();
            final List skipCropList = this.selectorConfig.skipCropList;
            if (skipCropList != null && skipCropList.size() > 0) {
                set.addAll((Collection)skipCropList);
            }
            if (this.selectorConfig.getSelectCount() == 1) {
                final String resultFirstMimeType = this.selectorConfig.getResultFirstMimeType();
                final boolean hasImage = PictureMimeType.isHasImage(resultFirstMimeType);
                return (!hasImage || !set.contains((Object)resultFirstMimeType)) && hasImage;
            }
            int i = 0;
            int n = 0;
            while (i < this.selectorConfig.getSelectCount()) {
                final LocalMedia localMedia = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
                int n2 = n;
                if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                    n2 = n;
                    if (set.contains((Object)localMedia.getMimeType())) {
                        n2 = n + 1;
                    }
                }
                ++i;
                n = n2;
            }
            b = b;
            if (n != this.selectorConfig.getSelectCount()) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean checkOldCompressValidity() {
        if (this.selectorConfig.compressEngine != null) {
            for (int i = 0; i < this.selectorConfig.getSelectCount(); ++i) {
                if (PictureMimeType.isHasImage(((LocalMedia)this.selectorConfig.getSelectedResult().get(i)).getMimeType())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkOldCropValidity() {
        final CropEngine cropEngine = this.selectorConfig.cropEngine;
        boolean b = false;
        if (cropEngine != null) {
            final HashSet set = new HashSet();
            final List skipCropList = this.selectorConfig.skipCropList;
            if (skipCropList != null && skipCropList.size() > 0) {
                set.addAll((Collection)skipCropList);
            }
            if (this.selectorConfig.getSelectCount() == 1) {
                final String resultFirstMimeType = this.selectorConfig.getResultFirstMimeType();
                final boolean hasImage = PictureMimeType.isHasImage(resultFirstMimeType);
                return (!hasImage || !set.contains((Object)resultFirstMimeType)) && hasImage;
            }
            int i = 0;
            int n = 0;
            while (i < this.selectorConfig.getSelectCount()) {
                final LocalMedia localMedia = (LocalMedia)this.selectorConfig.getSelectedResult().get(i);
                int n2 = n;
                if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                    n2 = n;
                    if (set.contains((Object)localMedia.getMimeType())) {
                        n2 = n + 1;
                    }
                }
                ++i;
                n = n2;
            }
            b = b;
            if (n != this.selectorConfig.getSelectCount()) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean checkOldTransformSandboxFile() {
        return SdkVersionUtils.isQ() && this.selectorConfig.sandboxFileEngine != null;
    }
    
    public boolean checkOnlyMimeTypeValidity(final LocalMedia localMedia, final boolean b, final String s, final String s2, final long n, final long n2) {
        if (PictureMimeType.isMimeTypeSame(s2, s)) {
            if (this.selectorConfig.selectMaxFileSize > 0L && n > this.selectorConfig.selectMaxFileSize) {
                if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 1)) {
                    return true;
                }
                this.showTipsDialog(this.getString(R$string.ps_select_max_size, new Object[] { PictureFileUtils.formatFileSize(this.selectorConfig.selectMaxFileSize) }));
                return true;
            }
            else {
                if (this.selectorConfig.selectMinFileSize <= 0L || n >= this.selectorConfig.selectMinFileSize) {
                    if (PictureMimeType.isHasVideo(s)) {
                        if (this.selectorConfig.selectionMode == 2) {
                            final SelectorConfig selectorConfig = this.selectorConfig;
                            int maxVideoSelectNum;
                            if (selectorConfig.maxVideoSelectNum > 0) {
                                maxVideoSelectNum = this.selectorConfig.maxVideoSelectNum;
                            }
                            else {
                                maxVideoSelectNum = this.selectorConfig.maxSelectNum;
                            }
                            selectorConfig.maxVideoSelectNum = maxVideoSelectNum;
                            if (!b && this.selectorConfig.getSelectCount() >= this.selectorConfig.maxVideoSelectNum) {
                                if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 6)) {
                                    return true;
                                }
                                this.showTipsDialog(getTipsMsg(this.getAppContext(), s, this.selectorConfig.maxVideoSelectNum));
                                return true;
                            }
                        }
                        if (!b && this.selectorConfig.selectMinDurationSecond > 0 && DateUtils.millisecondToSecond(n2) < this.selectorConfig.selectMinDurationSecond) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 9)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_select_video_min_second, new Object[] { this.selectorConfig.selectMinDurationSecond / 1000 }));
                            return true;
                        }
                        else if (!b && this.selectorConfig.selectMaxDurationSecond > 0 && DateUtils.millisecondToSecond(n2) > this.selectorConfig.selectMaxDurationSecond) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 8)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_select_video_max_second, new Object[] { this.selectorConfig.selectMaxDurationSecond / 1000 }));
                            return true;
                        }
                    }
                    else if (PictureMimeType.isHasAudio(s)) {
                        if (this.selectorConfig.selectionMode == 2 && !b && this.selectorConfig.getSelectedResult().size() >= this.selectorConfig.maxSelectNum) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 4)) {
                                return true;
                            }
                            this.showTipsDialog(getTipsMsg(this.getAppContext(), s, this.selectorConfig.maxSelectNum));
                            return true;
                        }
                        else if (!b && this.selectorConfig.selectMinDurationSecond > 0 && DateUtils.millisecondToSecond(n2) < this.selectorConfig.selectMinDurationSecond) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 11)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_select_audio_min_second, new Object[] { this.selectorConfig.selectMinDurationSecond / 1000 }));
                            return true;
                        }
                        else if (!b && this.selectorConfig.selectMaxDurationSecond > 0 && DateUtils.millisecondToSecond(n2) > this.selectorConfig.selectMaxDurationSecond) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 10)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_select_audio_max_second, new Object[] { this.selectorConfig.selectMaxDurationSecond / 1000 }));
                            return true;
                        }
                    }
                    else if (this.selectorConfig.selectionMode == 2 && !b && this.selectorConfig.getSelectedResult().size() >= this.selectorConfig.maxSelectNum) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 4)) {
                            return true;
                        }
                        this.showTipsDialog(getTipsMsg(this.getAppContext(), s, this.selectorConfig.maxSelectNum));
                        return true;
                    }
                    return false;
                }
                if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 2)) {
                    return true;
                }
                this.showTipsDialog(this.getString(R$string.ps_select_min_size, new Object[] { PictureFileUtils.formatFileSize(this.selectorConfig.selectMinFileSize) }));
                return true;
            }
        }
        else {
            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 3)) {
                return true;
            }
            this.showTipsDialog(this.getString(R$string.ps_rule));
            return true;
        }
    }
    
    public boolean checkTransformSandboxFile() {
        return SdkVersionUtils.isQ() && this.selectorConfig.uriToFileTransformEngine != null;
    }
    
    public boolean checkVideoThumbnail() {
        return this.selectorConfig.onVideoThumbnailEventListener != null;
    }
    
    public boolean checkWithMimeTypeValidity(final LocalMedia localMedia, final boolean b, final String s, final int n, final long n2, final long n3) {
        if (this.selectorConfig.selectMaxFileSize > 0L && n2 > this.selectorConfig.selectMaxFileSize) {
            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 1)) {
                return true;
            }
            this.showTipsDialog(this.getString(R$string.ps_select_max_size, new Object[] { PictureFileUtils.formatFileSize(this.selectorConfig.selectMaxFileSize) }));
            return true;
        }
        else {
            if (this.selectorConfig.selectMinFileSize <= 0L || n2 >= this.selectorConfig.selectMinFileSize) {
                if (PictureMimeType.isHasVideo(s)) {
                    if (this.selectorConfig.selectionMode == 2) {
                        if (this.selectorConfig.maxVideoSelectNum <= 0) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 3)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_rule));
                            return true;
                        }
                        else if (!b && this.selectorConfig.getSelectedResult().size() >= this.selectorConfig.maxSelectNum) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 4)) {
                                return true;
                            }
                            this.showTipsDialog(this.getString(R$string.ps_message_max_num, new Object[] { this.selectorConfig.maxSelectNum }));
                            return true;
                        }
                        else if (!b && n >= this.selectorConfig.maxVideoSelectNum) {
                            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 6)) {
                                return true;
                            }
                            this.showTipsDialog(getTipsMsg(this.getAppContext(), s, this.selectorConfig.maxVideoSelectNum));
                            return true;
                        }
                    }
                    if (!b && this.selectorConfig.selectMinDurationSecond > 0 && DateUtils.millisecondToSecond(n3) < this.selectorConfig.selectMinDurationSecond) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 9)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_select_video_min_second, new Object[] { this.selectorConfig.selectMinDurationSecond / 1000 }));
                        return true;
                    }
                    else if (!b && this.selectorConfig.selectMaxDurationSecond > 0 && DateUtils.millisecondToSecond(n3) > this.selectorConfig.selectMaxDurationSecond) {
                        if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 8)) {
                            return true;
                        }
                        this.showTipsDialog(this.getString(R$string.ps_select_video_max_second, new Object[] { this.selectorConfig.selectMaxDurationSecond / 1000 }));
                        return true;
                    }
                }
                else if (this.selectorConfig.selectionMode == 2 && !b && this.selectorConfig.getSelectedResult().size() >= this.selectorConfig.maxSelectNum) {
                    if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 4)) {
                        return true;
                    }
                    this.showTipsDialog(this.getString(R$string.ps_message_max_num, new Object[] { this.selectorConfig.maxSelectNum }));
                    return true;
                }
                return false;
            }
            if (this.selectorConfig.onSelectLimitTipsListener != null && this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 2)) {
                return true;
            }
            this.showTipsDialog(this.getString(R$string.ps_select_min_size, new Object[] { PictureFileUtils.formatFileSize(this.selectorConfig.selectMinFileSize) }));
            return true;
        }
    }
    
    public int confirmSelect(final LocalMedia localMedia, final boolean b) {
        final OnSelectFilterListener onSelectFilterListener = this.selectorConfig.onSelectFilterListener;
        boolean b2 = false;
        final int n = 0;
        if (onSelectFilterListener != null && this.selectorConfig.onSelectFilterListener.onSelectFilter(localMedia)) {
            int onSelectLimitTips = n;
            if (this.selectorConfig.onSelectLimitTipsListener != null) {
                onSelectLimitTips = (this.selectorConfig.onSelectLimitTipsListener.onSelectLimitTips(this.getAppContext(), localMedia, this.selectorConfig, 13) ? 1 : 0);
            }
            if (onSelectLimitTips == 0) {
                ToastUtils.showToast(this.getAppContext(), this.getString(R$string.ps_select_no_support));
            }
            return -1;
        }
        if (this.isCheckSelectValidity(localMedia, b) != 200) {
            return -1;
        }
        final ArrayList selectedResult = this.selectorConfig.getSelectedResult();
        if (b) {
            ((List)selectedResult).remove((Object)localMedia);
            b2 = true;
        }
        else {
            if (this.selectorConfig.selectionMode == 1 && ((List)selectedResult).size() > 0) {
                this.sendFixedSelectedChangeEvent((LocalMedia)((List)selectedResult).get(0));
                ((List)selectedResult).clear();
            }
            ((List)selectedResult).add((Object)localMedia);
            localMedia.setNum(((List)selectedResult).size());
            this.playClickEffect();
        }
        this.sendSelectedChangeEvent((boolean)(((b2 ? 1 : 0) ^ 0x1) != 0x0), localMedia);
        return b2 ? 1 : 0;
    }
    
    public void dismissLoading() {
        try {
            if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
                return;
            }
            if (this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void dispatchCameraMediaResult(final LocalMedia localMedia) {
    }
    
    protected void dispatchTransformResult() {
        if (this.checkCompleteSelectLimit()) {
            return;
        }
        if (!this.isAdded()) {
            return;
        }
        final ArrayList list = new ArrayList((Collection)this.selectorConfig.getSelectedResult());
        if (this.checkCropValidity()) {
            this.onCrop((ArrayList<LocalMedia>)list);
        }
        else if (this.checkOldCropValidity()) {
            this.onOldCrop((ArrayList<LocalMedia>)list);
        }
        else if (this.checkCompressValidity()) {
            this.onCompress((ArrayList<LocalMedia>)list);
        }
        else if (this.checkOldCompressValidity()) {
            this.onOldCompress((ArrayList<LocalMedia>)list);
        }
        else {
            this.onResultEvent((ArrayList<LocalMedia>)list);
        }
    }
    
    protected Context getAppContext() {
        final Context context = this.getContext();
        if (context != null) {
            return context;
        }
        final Context appContext = PictureAppMaster.getInstance().getAppContext();
        if (appContext != null) {
            return appContext;
        }
        return this.context;
    }
    
    public long getEnterAnimationDuration() {
        long enterAnimDuration;
        final long n = enterAnimDuration = this.enterAnimDuration;
        if (n > 50L) {
            enterAnimDuration = n - 50L;
        }
        if (enterAnimDuration < 0L) {
            enterAnimDuration = 0L;
        }
        return enterAnimDuration;
    }
    
    public String getFragmentTag() {
        return PictureCommonFragment.TAG;
    }
    
    protected String getOutputPath(final Intent intent) {
        if (intent == null) {
            return null;
        }
        final Uri uri = (Uri)intent.getParcelableExtra("output");
        final String cameraPath = this.selectorConfig.cameraPath;
        final boolean b = TextUtils.isEmpty((CharSequence)cameraPath) || PictureMimeType.isContent(cameraPath) || new File(cameraPath).exists();
        Uri data = null;
        Label_0097: {
            if (this.selectorConfig.chooseMode != SelectMimeType.ofAudio()) {
                data = uri;
                if (b) {
                    break Label_0097;
                }
            }
            if ((data = uri) == null) {
                data = intent.getData();
            }
        }
        if (data == null) {
            return null;
        }
        String s;
        if (PictureMimeType.isContent(data.toString())) {
            s = data.toString();
        }
        else {
            s = data.getPath();
        }
        return s;
    }
    
    public int getResourceId() {
        return 0;
    }
    
    protected PictureCommonFragment.PictureCommonFragment$SelectorResult getResult(final int n, final ArrayList<LocalMedia> list) {
        Intent putIntentResult;
        if (list != null) {
            putIntentResult = PictureSelector.putIntentResult((ArrayList)list);
        }
        else {
            putIntentResult = null;
        }
        return new PictureCommonFragment.PictureCommonFragment$SelectorResult(n, putIntentResult);
    }
    
    public void handlePermissionDenied(final String[] current_REQUEST_PERMISSION) {
        PermissionConfig.CURRENT_REQUEST_PERMISSION = current_REQUEST_PERMISSION;
        if (current_REQUEST_PERMISSION != null && current_REQUEST_PERMISSION.length > 0) {
            SpUtils.putBoolean(this.getAppContext(), current_REQUEST_PERMISSION[0], true);
        }
        if (this.selectorConfig.onPermissionDeniedListener != null) {
            this.onPermissionExplainEvent(false, null);
            this.selectorConfig.onPermissionDeniedListener.onDenied((Fragment)this, current_REQUEST_PERMISSION, 1102, (OnCallbackListener)new PictureCommonFragment$1(this));
        }
        else {
            PermissionUtil.goIntentSetting((Fragment)this, 1102);
        }
    }
    
    public void handlePermissionSettingResult(final String[] array) {
    }
    
    public void initAppLanguage() {
        if (this.selectorConfig == null) {
            this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        }
        final SelectorConfig selectorConfig = this.selectorConfig;
        if (selectorConfig != null && selectorConfig.language != -2) {
            PictureLanguageUtils.setAppLanguage((Context)this.getActivity(), this.selectorConfig.language, this.selectorConfig.defaultLanguage);
        }
    }
    
    protected int isCheckSelectValidity(final LocalMedia localMedia, final boolean b) {
        final String mimeType = localMedia.getMimeType();
        final long duration = localMedia.getDuration();
        final long size = localMedia.getSize();
        final ArrayList selectedResult = this.selectorConfig.getSelectedResult();
        if (this.selectorConfig.isWithVideoImage) {
            int i = 0;
            int n = 0;
            while (i < ((List)selectedResult).size()) {
                int n2 = n;
                if (PictureMimeType.isHasVideo(((LocalMedia)((List)selectedResult).get(i)).getMimeType())) {
                    n2 = n + 1;
                }
                ++i;
                n = n2;
            }
            if (this.checkWithMimeTypeValidity(localMedia, b, mimeType, n, size, duration)) {
                return -1;
            }
        }
        else if (this.checkOnlyMimeTypeValidity(localMedia, b, mimeType, this.selectorConfig.getResultFirstMimeType(), size, duration)) {
            return -1;
        }
        return 200;
    }
    
    protected boolean isNormalDefaultEnter() {
        return this.getActivity() instanceof PictureSelectorSupporterActivity || this.getActivity() instanceof PictureSelectorTransparentActivity;
    }
    
    public void onActivityResult(int i, final int n, final Intent intent) {
        super.onActivityResult(i, n, intent);
        ForegroundService.stopService(this.getAppContext());
        String path = "";
        if (n == -1) {
            if (i == 909) {
                this.dispatchHandleCamera(intent);
            }
            else if (i == 696) {
                this.onEditMedia(intent);
            }
            else if (i == 69) {
                final ArrayList selectedResult = this.selectorConfig.getSelectedResult();
                try {
                    i = ((List)selectedResult).size();
                    boolean cut = false;
                    if (i == 1) {
                        final LocalMedia localMedia = (LocalMedia)((List)selectedResult).get(0);
                        final Uri output = Crop.getOutput(intent);
                        if (output != null) {
                            path = output.getPath();
                        }
                        localMedia.setCutPath(path);
                        if (!TextUtils.isEmpty((CharSequence)localMedia.getCutPath())) {
                            cut = true;
                        }
                        localMedia.setCut(cut);
                        localMedia.setCropImageWidth(Crop.getOutputImageWidth(intent));
                        localMedia.setCropImageHeight(Crop.getOutputImageHeight(intent));
                        localMedia.setCropOffsetX(Crop.getOutputImageOffsetX(intent));
                        localMedia.setCropOffsetY(Crop.getOutputImageOffsetY(intent));
                        localMedia.setCropResultAspectRatio(Crop.getOutputCropAspectRatio(intent));
                        localMedia.setCustomData(Crop.getOutputCustomExtraData(intent));
                        localMedia.setSandboxPath(localMedia.getCutPath());
                    }
                    else {
                        String s;
                        if (TextUtils.isEmpty((CharSequence)(s = intent.getStringExtra("output")))) {
                            s = intent.getStringExtra("com.yalantis.ucrop.OutputUri");
                        }
                        final JSONArray jsonArray = new JSONArray(s);
                        if (jsonArray.length() == ((List)selectedResult).size()) {
                            LocalMedia localMedia2;
                            JSONObject optJSONObject;
                            for (i = 0; i < ((List)selectedResult).size(); ++i) {
                                localMedia2 = (LocalMedia)((List)selectedResult).get(i);
                                optJSONObject = jsonArray.optJSONObject(i);
                                localMedia2.setCutPath(optJSONObject.optString("outPutPath"));
                                localMedia2.setCut(!TextUtils.isEmpty((CharSequence)localMedia2.getCutPath()));
                                localMedia2.setCropImageWidth(optJSONObject.optInt("imageWidth"));
                                localMedia2.setCropImageHeight(optJSONObject.optInt("imageHeight"));
                                localMedia2.setCropOffsetX(optJSONObject.optInt("offsetX"));
                                localMedia2.setCropOffsetY(optJSONObject.optInt("offsetY"));
                                localMedia2.setCropResultAspectRatio((float)optJSONObject.optDouble("aspectRatio"));
                                localMedia2.setCustomData(optJSONObject.optString("customExtraData"));
                                localMedia2.setSandboxPath(localMedia2.getCutPath());
                            }
                        }
                    }
                }
                catch (final Exception ex) {
                    ex.printStackTrace();
                    ToastUtils.showToast(this.getAppContext(), ex.getMessage());
                }
                final ArrayList list = new ArrayList((Collection)selectedResult);
                if (this.checkCompressValidity()) {
                    this.onCompress((ArrayList<LocalMedia>)list);
                }
                else if (this.checkOldCompressValidity()) {
                    this.onOldCompress((ArrayList<LocalMedia>)list);
                }
                else {
                    this.onResultEvent((ArrayList<LocalMedia>)list);
                }
            }
        }
        else if (n == 96) {
            Throwable error;
            if (intent != null) {
                error = Crop.getError(intent);
            }
            else {
                error = new Throwable("image crop error");
            }
            if (error != null) {
                ToastUtils.showToast(this.getAppContext(), error.getMessage());
            }
        }
        else if (n == 0) {
            if (i == 909) {
                if (!TextUtils.isEmpty((CharSequence)this.selectorConfig.cameraPath)) {
                    MediaUtils.deleteUri(this.getAppContext(), this.selectorConfig.cameraPath);
                    this.selectorConfig.cameraPath = "";
                }
            }
            else if (i == 1102) {
                this.handlePermissionSettingResult(PermissionConfig.CURRENT_REQUEST_PERMISSION);
            }
        }
    }
    
    public void onApplyPermissionsEvent(final int n, final String[] array) {
        this.selectorConfig.onPermissionsEventListener.requestPermission((Fragment)this, array, (OnRequestPermissionListener)new PictureCommonFragment$7(this, n));
    }
    
    public void onAttach(final Context context) {
        this.initAppLanguage();
        this.onRecreateEngine();
        super.onAttach(context);
        this.context = context;
        if (this.getParentFragment() instanceof IBridgePictureBehavior) {
            this.iBridgePictureBehavior = (IBridgePictureBehavior)this.getParentFragment();
        }
        else if (context instanceof IBridgePictureBehavior) {
            this.iBridgePictureBehavior = (IBridgePictureBehavior)context;
        }
    }
    
    protected void onBackCurrentFragment() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            if (!this.isStateSaved()) {
                if (this.selectorConfig.viewLifecycle != null) {
                    this.selectorConfig.viewLifecycle.onDestroy((Fragment)this);
                }
                this.getActivity().getSupportFragmentManager().popBackStack();
            }
            final List fragments = this.getActivity().getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragments.size(); ++i) {
                final Fragment fragment = (Fragment)fragments.get(i);
                if (fragment instanceof PictureCommonFragment) {
                    ((PictureCommonFragment)fragment).onFragmentResume();
                }
            }
        }
    }
    
    public void onCheckOriginalChange() {
    }
    
    public void onCompress(final ArrayList<LocalMedia> list) {
        this.showLoading();
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        final ArrayList list2 = new ArrayList();
        for (int i = 0; i < list.size(); ++i) {
            final LocalMedia localMedia = (LocalMedia)list.get(i);
            final String availablePath = localMedia.getAvailablePath();
            if (!PictureMimeType.isHasHttp(availablePath)) {
                if (!this.selectorConfig.isCheckOriginalImage || !this.selectorConfig.isOriginalSkipCompress) {
                    if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                        Uri uri;
                        if (PictureMimeType.isContent(availablePath)) {
                            uri = Uri.parse(availablePath);
                        }
                        else {
                            uri = Uri.fromFile(new File(availablePath));
                        }
                        list2.add((Object)uri);
                        concurrentHashMap.put((Object)availablePath, (Object)localMedia);
                    }
                }
            }
        }
        if (concurrentHashMap.size() == 0) {
            this.onResultEvent(list);
        }
        else {
            this.selectorConfig.compressFileEngine.onStartCompress(this.getAppContext(), list2, (OnKeyValueResultCallbackListener)new PictureCommonFragment$9(this, (ArrayList)list, concurrentHashMap));
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.initAppLanguage();
    }
    
    public Animation onCreateAnimation(final int n, final boolean b, final int n2) {
        final PictureWindowAnimationStyle windowAnimationStyle = this.selectorConfig.selectorStyle.getWindowAnimationStyle();
        Animation animation;
        if (b) {
            if (windowAnimationStyle.activityEnterAnimation != 0) {
                animation = AnimationUtils.loadAnimation(this.getAppContext(), windowAnimationStyle.activityEnterAnimation);
            }
            else {
                animation = AnimationUtils.loadAnimation(this.getAppContext(), R$anim.ps_anim_alpha_enter);
            }
            this.setEnterAnimationDuration(animation.getDuration());
            this.onEnterFragment();
        }
        else {
            if (windowAnimationStyle.activityExitAnimation != 0) {
                animation = AnimationUtils.loadAnimation(this.getAppContext(), windowAnimationStyle.activityExitAnimation);
            }
            else {
                animation = AnimationUtils.loadAnimation(this.getAppContext(), R$anim.ps_anim_alpha_exit);
            }
            this.onExitFragment();
        }
        return animation;
    }
    
    public void onCreateLoader() {
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.getResourceId() != 0) {
            return layoutInflater.inflate(this.getResourceId(), viewGroup, false);
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    public void onCrop(final ArrayList<LocalMedia> list) {
        final ArrayList list2 = new ArrayList();
        int i = 0;
        Uri uri = null;
        Uri uri2 = null;
        while (i < list.size()) {
            final LocalMedia localMedia = (LocalMedia)list.get(i);
            list2.add((Object)localMedia.getAvailablePath());
            Uri uri3 = uri;
            Uri fromFile = uri2;
            if (uri == null) {
                uri3 = uri;
                fromFile = uri2;
                if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
                    final String availablePath = localMedia.getAvailablePath();
                    Uri uri4;
                    if (!PictureMimeType.isContent(availablePath) && !PictureMimeType.isHasHttp(availablePath)) {
                        uri4 = Uri.fromFile(new File(availablePath));
                    }
                    else {
                        uri4 = Uri.parse(availablePath);
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append(DateUtils.getCreateFileName("CROP_"));
                    sb.append(".jpg");
                    fromFile = Uri.fromFile(new File(new File(FileDirMap.getFileDirPath(this.getAppContext(), 1)).getAbsolutePath(), sb.toString()));
                    uri3 = uri4;
                }
            }
            ++i;
            uri = uri3;
            uri2 = fromFile;
        }
        this.selectorConfig.cropFileEngine.onStartCrop((Fragment)this, uri, uri2, list2, 69);
    }
    
    public void onDestroy() {
        this.releaseSoundPool();
        super.onDestroy();
    }
    
    public void onEditMedia(final Intent intent) {
    }
    
    public void onEnterFragment() {
    }
    
    public void onExitFragment() {
    }
    
    protected void onExitPictureSelector() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            if (this.isNormalDefaultEnter()) {
                if (this.selectorConfig.viewLifecycle != null) {
                    this.selectorConfig.viewLifecycle.onDestroy((Fragment)this);
                }
                this.getActivity().finish();
            }
            else {
                final List fragments = this.getActivity().getSupportFragmentManager().getFragments();
                for (int i = 0; i < fragments.size(); ++i) {
                    if (((Fragment)fragments.get(i)) instanceof PictureCommonFragment) {
                        this.onBackCurrentFragment();
                    }
                }
            }
        }
        SelectorProviders.getInstance().destroy();
    }
    
    public void onFixedSelectedChange(final LocalMedia localMedia) {
    }
    
    public void onFragmentResume() {
    }
    
    public void onInterceptCameraEvent(final int n) {
        ForegroundService.startForegroundService(this.getAppContext(), this.selectorConfig.isCameraForegroundService);
        this.selectorConfig.onCameraInterceptListener.openCamera((Fragment)this, n, 909);
    }
    
    public void onKeyBackFragmentFinish() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            if (this.selectorConfig.isActivityResultBack) {
                this.getActivity().setResult(0);
                this.onSelectFinish(0, null);
            }
            else if (this.selectorConfig.onResultCallListener != null) {
                this.selectorConfig.onResultCallListener.onCancel();
            }
            this.onExitPictureSelector();
        }
    }
    
    public void onOldCompress(final ArrayList<LocalMedia> list) {
        this.showLoading();
        if (this.selectorConfig.isCheckOriginalImage && this.selectorConfig.isOriginalSkipCompress) {
            this.onResultEvent(list);
        }
        else {
            this.selectorConfig.compressEngine.onStartCompress(this.getAppContext(), (ArrayList)list, (OnCallbackListener)new PictureCommonFragment$10(this));
        }
    }
    
    public void onOldCrop(final ArrayList<LocalMedia> list) {
        while (true) {
            for (int i = 0; i < list.size(); ++i) {
                final LocalMedia localMedia = (LocalMedia)list.get(i);
                if (PictureMimeType.isHasImage(((LocalMedia)list.get(i)).getMimeType())) {
                    this.selectorConfig.cropEngine.onStartCrop((Fragment)this, localMedia, (ArrayList)list, 69);
                    return;
                }
            }
            final LocalMedia localMedia = null;
            continue;
        }
    }
    
    public void onPermissionExplainEvent(final boolean b, final String[] array) {
        if (this.selectorConfig.onPermissionDescriptionListener != null) {
            if (b) {
                if (PermissionChecker.isCheckSelfPermission(this.getAppContext(), array)) {
                    SpUtils.putBoolean(this.getAppContext(), array[0], false);
                }
                else if (!SpUtils.getBoolean(this.getAppContext(), array[0], false)) {
                    this.selectorConfig.onPermissionDescriptionListener.onPermissionDescription((Fragment)this, array);
                }
            }
            else {
                this.selectorConfig.onPermissionDescriptionListener.onDismiss((Fragment)this);
            }
        }
    }
    
    public void onRecreateEngine() {
        this.createImageLoaderEngine();
        this.createVideoPlayerEngine();
        this.createCompressEngine();
        this.createSandboxFileEngine();
        this.createLoaderDataEngine();
        this.createResultCallbackListener();
        this.createLayoutResourceListener();
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        super.onRequestPermissionsResult(n, array, array2);
        if (this.mPermissionResultCallback != null) {
            PermissionChecker.getInstance().onRequestPermissionsResult(array2, this.mPermissionResultCallback);
            this.mPermissionResultCallback = null;
        }
    }
    
    public void onResultEvent(final ArrayList<LocalMedia> list) {
        if (this.checkTransformSandboxFile()) {
            this.uriToFileTransform29(list);
        }
        else if (this.checkOldTransformSandboxFile()) {
            this.copyExternalPathToAppInDirFor29(list);
        }
        else {
            this.mergeOriginalImage(list);
            this.dispatchUriToFileTransformResult(list);
        }
    }
    
    protected void onSelectFinish(final int n, final ArrayList<LocalMedia> list) {
        if (this.iBridgePictureBehavior != null) {
            this.iBridgePictureBehavior.onSelectFinish(this.getResult(n, list));
        }
    }
    
    public void onSelectedChange(final boolean b, final LocalMedia localMedia) {
    }
    
    public void onSelectedOnlyCamera() {
        final PhotoItemSelectedDialog instance = PhotoItemSelectedDialog.newInstance();
        instance.setOnItemClickListener((OnItemClickListener)new PictureCommonFragment$3(this));
        instance.setOnDismissListener((PhotoItemSelectedDialog.PhotoItemSelectedDialog$OnDismissListener)new PictureCommonFragment$4(this));
        instance.show(this.getChildFragmentManager(), "PhotoItemSelectedDialog");
    }
    
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.selectorConfig = SelectorProviders.getInstance().getSelectorConfig();
        FileDirMap.init(view.getContext());
        if (this.selectorConfig.viewLifecycle != null) {
            this.selectorConfig.viewLifecycle.onViewCreated((Fragment)this, view, bundle);
        }
        if (this.selectorConfig.onCustomLoadingListener != null) {
            this.mLoadingDialog = this.selectorConfig.onCustomLoadingListener.create(this.getAppContext());
        }
        else {
            this.mLoadingDialog = (Dialog)new PictureLoadingDialog(this.getAppContext());
        }
        this.setRequestedOrientation();
        this.setTranslucentStatusBar();
        this.setRootViewKeyListener(this.requireView());
        if (this.selectorConfig.isOpenClickSound && !this.selectorConfig.isOnlyCamera) {
            final SoundPool soundPool = new SoundPool(1, 3, 0);
            this.soundPool = soundPool;
            this.soundID = soundPool.load(this.getAppContext(), R$raw.ps_click_music, 1);
        }
    }
    
    public void openImageCamera() {
        this.onPermissionExplainEvent(true, PermissionConfig.CAMERA);
        if (this.selectorConfig.onPermissionsEventListener != null) {
            this.onApplyPermissionsEvent(PermissionEvent.EVENT_IMAGE_CAMERA, PermissionConfig.CAMERA);
        }
        else {
            PermissionChecker.getInstance().requestPermissions((Fragment)this, PermissionConfig.CAMERA, (PermissionResultCallback)new PictureCommonFragment$5(this));
        }
    }
    
    public void openSelectedCamera() {
        final int chooseMode = this.selectorConfig.chooseMode;
        if (chooseMode != 0) {
            if (chooseMode != 1) {
                if (chooseMode != 2) {
                    if (chooseMode == 3) {
                        this.openSoundRecording();
                    }
                }
                else {
                    this.openVideoCamera();
                }
            }
            else {
                this.openImageCamera();
            }
        }
        else if (this.selectorConfig.ofAllCameraType == SelectMimeType.ofImage()) {
            this.openImageCamera();
        }
        else if (this.selectorConfig.ofAllCameraType == SelectMimeType.ofVideo()) {
            this.openVideoCamera();
        }
        else {
            this.onSelectedOnlyCamera();
        }
    }
    
    public void openSoundRecording() {
        if (this.selectorConfig.onRecordAudioListener != null) {
            ForegroundService.startForegroundService(this.getAppContext(), this.selectorConfig.isCameraForegroundService);
            this.selectorConfig.onRecordAudioListener.onRecordAudio((Fragment)this, 909);
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(OnRecordAudioInterceptListener.class.getSimpleName());
        sb.append(" interface needs to be implemented for recording");
        throw new NullPointerException(sb.toString());
    }
    
    public void openVideoCamera() {
        this.onPermissionExplainEvent(true, PermissionConfig.CAMERA);
        if (this.selectorConfig.onPermissionsEventListener != null) {
            this.onApplyPermissionsEvent(PermissionEvent.EVENT_VIDEO_CAMERA, PermissionConfig.CAMERA);
        }
        else {
            PermissionChecker.getInstance().requestPermissions((Fragment)this, PermissionConfig.CAMERA, (PermissionResultCallback)new PictureCommonFragment$6(this));
        }
    }
    
    public void reStartSavedInstance(final Bundle bundle) {
    }
    
    public void sendChangeSubSelectPositionEvent(final boolean b) {
    }
    
    public void sendFixedSelectedChangeEvent(final LocalMedia localMedia) {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            final List fragments = this.getActivity().getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragments.size(); ++i) {
                final Fragment fragment = (Fragment)fragments.get(i);
                if (fragment instanceof PictureCommonFragment) {
                    ((PictureCommonFragment)fragment).onFixedSelectedChange(localMedia);
                }
            }
        }
    }
    
    public void sendSelectedChangeEvent(final boolean b, final LocalMedia localMedia) {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            final List fragments = this.getActivity().getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragments.size(); ++i) {
                final Fragment fragment = (Fragment)fragments.get(i);
                if (fragment instanceof PictureCommonFragment) {
                    ((PictureCommonFragment)fragment).onSelectedChange(b, localMedia);
                }
            }
        }
    }
    
    public void sendSelectedOriginalChangeEvent() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            final List fragments = this.getActivity().getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragments.size(); ++i) {
                final Fragment fragment = (Fragment)fragments.get(i);
                if (fragment instanceof PictureCommonFragment) {
                    ((PictureCommonFragment)fragment).onCheckOriginalChange();
                }
            }
        }
    }
    
    public void setEnterAnimationDuration(final long enterAnimDuration) {
        this.enterAnimDuration = enterAnimDuration;
    }
    
    public void setPermissionsResultAction(final PermissionResultCallback mPermissionResultCallback) {
        this.mPermissionResultCallback = mPermissionResultCallback;
    }
    
    protected void setRequestedOrientation() {
        if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            return;
        }
        this.getActivity().setRequestedOrientation(this.selectorConfig.requestedOrientation);
    }
    
    public void setRootViewKeyListener(final View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((View$OnKeyListener)new PictureCommonFragment$2(this));
    }
    
    public void showLoading() {
        try {
            if (ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
                return;
            }
            if (!this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.show();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void startCameraImageCapture() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            this.onPermissionExplainEvent(false, null);
            if (this.selectorConfig.onCameraInterceptListener != null) {
                this.onInterceptCameraEvent(1);
            }
            else {
                final Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                if (intent.resolveActivity(this.getActivity().getPackageManager()) != null) {
                    ForegroundService.startForegroundService(this.getAppContext(), this.selectorConfig.isCameraForegroundService);
                    final Uri cameraOutImageUri = MediaStoreUtils.createCameraOutImageUri(this.getAppContext(), this.selectorConfig);
                    if (cameraOutImageUri != null) {
                        if (this.selectorConfig.isCameraAroundState) {
                            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                        }
                        intent.putExtra("output", (Parcelable)cameraOutImageUri);
                        this.startActivityForResult(intent, 909);
                    }
                }
            }
        }
    }
    
    protected void startCameraVideoCapture() {
        if (!ActivityCompatHelper.isDestroy((Activity)this.getActivity())) {
            this.onPermissionExplainEvent(false, null);
            if (this.selectorConfig.onCameraInterceptListener != null) {
                this.onInterceptCameraEvent(2);
            }
            else {
                final Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                if (intent.resolveActivity(this.getActivity().getPackageManager()) != null) {
                    ForegroundService.startForegroundService(this.getAppContext(), this.selectorConfig.isCameraForegroundService);
                    final Uri cameraOutVideoUri = MediaStoreUtils.createCameraOutVideoUri(this.getAppContext(), this.selectorConfig);
                    if (cameraOutVideoUri != null) {
                        intent.putExtra("output", (Parcelable)cameraOutVideoUri);
                        if (this.selectorConfig.isCameraAroundState) {
                            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                        }
                        intent.putExtra("android.intent.extra.quickCapture", this.selectorConfig.isQuickCapture);
                        intent.putExtra("android.intent.extra.durationLimit", this.selectorConfig.recordVideoMaxSecond);
                        intent.putExtra("android.intent.extra.videoQuality", this.selectorConfig.videoQuality);
                        this.startActivityForResult(intent, 909);
                    }
                }
            }
        }
    }
}
