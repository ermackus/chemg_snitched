package com.luck.picture.lib.basic;

import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;
import com.luck.picture.lib.entity.LocalMedia;

public interface IPictureSelectorCommonEvent
{
    boolean checkAddBitmapWatermark();
    
    boolean checkCompressValidity();
    
    boolean checkCropValidity();
    
    @Deprecated
    boolean checkOldCompressValidity();
    
    @Deprecated
    boolean checkOldCropValidity();
    
    @Deprecated
    boolean checkOldTransformSandboxFile();
    
    boolean checkOnlyMimeTypeValidity(final LocalMedia p0, final boolean p1, final String p2, final String p3, final long p4, final long p5);
    
    boolean checkTransformSandboxFile();
    
    boolean checkVideoThumbnail();
    
    boolean checkWithMimeTypeValidity(final LocalMedia p0, final boolean p1, final String p2, final int p3, final long p4, final long p5);
    
    int confirmSelect(final LocalMedia p0, final boolean p1);
    
    void dismissLoading();
    
    void dispatchCameraMediaResult(final LocalMedia p0);
    
    int getResourceId();
    
    void handlePermissionDenied(final String[] p0);
    
    void handlePermissionSettingResult(final String[] p0);
    
    void initAppLanguage();
    
    void onApplyPermissionsEvent(final int p0, final String[] p1);
    
    void onCheckOriginalChange();
    
    void onCompress(final ArrayList<LocalMedia> p0);
    
    void onCreateLoader();
    
    void onCrop(final ArrayList<LocalMedia> p0);
    
    void onEditMedia(final Intent p0);
    
    void onEnterFragment();
    
    void onExitFragment();
    
    void onFixedSelectedChange(final LocalMedia p0);
    
    void onFragmentResume();
    
    void onInterceptCameraEvent(final int p0);
    
    void onKeyBackFragmentFinish();
    
    @Deprecated
    void onOldCompress(final ArrayList<LocalMedia> p0);
    
    void onOldCrop(final ArrayList<LocalMedia> p0);
    
    void onPermissionExplainEvent(final boolean p0, final String[] p1);
    
    void onRecreateEngine();
    
    void onResultEvent(final ArrayList<LocalMedia> p0);
    
    void onSelectedChange(final boolean p0, final LocalMedia p1);
    
    void onSelectedOnlyCamera();
    
    void openImageCamera();
    
    void openSelectedCamera();
    
    void openSoundRecording();
    
    void openVideoCamera();
    
    void reStartSavedInstance(final Bundle p0);
    
    void sendChangeSubSelectPositionEvent(final boolean p0);
    
    void sendFixedSelectedChangeEvent(final LocalMedia p0);
    
    void sendSelectedChangeEvent(final boolean p0, final LocalMedia p1);
    
    void sendSelectedOriginalChangeEvent();
    
    void showLoading();
}
