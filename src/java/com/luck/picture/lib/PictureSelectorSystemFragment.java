package com.luck.picture.lib;

import com.luck.picture.lib.permissions.PermissionResultCallback;
import android.os.Bundle;
import android.view.View;
import com.luck.picture.lib.interfaces.OnRequestPermissionListener;
import android.content.Intent;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.luck.picture.lib.utils.ToastUtils;
import com.luck.picture.lib.permissions.PermissionChecker;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import com.luck.picture.lib.config.SelectorConfig;
import com.luck.picture.lib.entity.LocalMedia;
import androidx.activity.result.ActivityResultLauncher;
import com.luck.picture.lib.basic.PictureCommonFragment;

public class PictureSelectorSystemFragment extends PictureCommonFragment
{
    public static final String TAG;
    private ActivityResultLauncher<String> mContentLauncher;
    private ActivityResultLauncher<String> mContentsLauncher;
    private ActivityResultLauncher<String> mDocMultipleLauncher;
    private ActivityResultLauncher<String> mDocSingleLauncher;
    
    static {
        TAG = PictureSelectorSystemFragment.class.getSimpleName();
    }
    
    private void createContent() {
        this.mContentLauncher = (ActivityResultLauncher<String>)this.registerForActivityResult((ActivityResultContract)new PictureSelectorSystemFragment$9(this), (ActivityResultCallback)new PictureSelectorSystemFragment$10(this));
    }
    
    private void createMultipleContents() {
        this.mContentsLauncher = (ActivityResultLauncher<String>)this.registerForActivityResult((ActivityResultContract)new PictureSelectorSystemFragment$7(this), (ActivityResultCallback)new PictureSelectorSystemFragment$8(this));
    }
    
    private void createMultipleDocuments() {
        this.mDocMultipleLauncher = (ActivityResultLauncher<String>)this.registerForActivityResult((ActivityResultContract)new PictureSelectorSystemFragment$3(this), (ActivityResultCallback)new PictureSelectorSystemFragment$4(this));
    }
    
    private void createSingleDocuments() {
        this.mDocSingleLauncher = (ActivityResultLauncher<String>)this.registerForActivityResult((ActivityResultContract)new PictureSelectorSystemFragment$5(this), (ActivityResultCallback)new PictureSelectorSystemFragment$6(this));
    }
    
    private void createSystemContracts() {
        if (this.selectorConfig.selectionMode == 1) {
            if (this.selectorConfig.chooseMode == SelectMimeType.ofAll()) {
                this.createSingleDocuments();
            }
            else {
                this.createContent();
            }
        }
        else if (this.selectorConfig.chooseMode == SelectMimeType.ofAll()) {
            this.createMultipleDocuments();
        }
        else {
            this.createMultipleContents();
        }
    }
    
    private String getInput() {
        if (this.selectorConfig.chooseMode == SelectMimeType.ofVideo()) {
            return "video/*";
        }
        if (this.selectorConfig.chooseMode == SelectMimeType.ofAudio()) {
            return "audio/*";
        }
        return "image/*";
    }
    
    public static PictureSelectorSystemFragment newInstance() {
        return new PictureSelectorSystemFragment();
    }
    
    private void openSystemAlbum() {
        this.onPermissionExplainEvent(false, null);
        if (this.selectorConfig.selectionMode == 1) {
            if (this.selectorConfig.chooseMode == SelectMimeType.ofAll()) {
                this.mDocSingleLauncher.launch((Object)"image/*,video/*");
            }
            else {
                this.mContentLauncher.launch((Object)this.getInput());
            }
        }
        else if (this.selectorConfig.chooseMode == SelectMimeType.ofAll()) {
            this.mDocMultipleLauncher.launch((Object)"image/*,video/*");
        }
        else {
            this.mContentsLauncher.launch((Object)this.getInput());
        }
    }
    
    @Override
    public String getFragmentTag() {
        return PictureSelectorSystemFragment.TAG;
    }
    
    @Override
    public int getResourceId() {
        return R$layout.ps_empty;
    }
    
    @Override
    public void handlePermissionSettingResult(final String[] array) {
        this.onPermissionExplainEvent(false, null);
        boolean b;
        if (this.selectorConfig.onPermissionsEventListener != null) {
            b = this.selectorConfig.onPermissionsEventListener.hasPermissions((Fragment)this, array);
        }
        else {
            b = PermissionChecker.isCheckReadStorage(this.selectorConfig.chooseMode, this.getContext());
        }
        if (b) {
            this.openSystemAlbum();
        }
        else {
            ToastUtils.showToast(this.getContext(), this.getString(R$string.ps_jurisdiction));
            this.onKeyBackFragmentFinish();
        }
        PermissionConfig.CURRENT_REQUEST_PERMISSION = new String[0];
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == 0) {
            this.onKeyBackFragmentFinish();
        }
    }
    
    @Override
    public void onApplyPermissionsEvent(final int n, final String[] array) {
        if (n == -2) {
            this.selectorConfig.onPermissionsEventListener.requestPermission((Fragment)this, PermissionConfig.getReadPermissionArray(this.getAppContext(), this.selectorConfig.chooseMode), (OnRequestPermissionListener)new PictureSelectorSystemFragment$2(this));
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        final ActivityResultLauncher<String> mDocMultipleLauncher = this.mDocMultipleLauncher;
        if (mDocMultipleLauncher != null) {
            mDocMultipleLauncher.unregister();
        }
        final ActivityResultLauncher<String> mDocSingleLauncher = this.mDocSingleLauncher;
        if (mDocSingleLauncher != null) {
            mDocSingleLauncher.unregister();
        }
        final ActivityResultLauncher<String> mContentsLauncher = this.mContentsLauncher;
        if (mContentsLauncher != null) {
            mContentsLauncher.unregister();
        }
        final ActivityResultLauncher<String> mContentLauncher = this.mContentLauncher;
        if (mContentLauncher != null) {
            mContentLauncher.unregister();
        }
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.createSystemContracts();
        if (PermissionChecker.isCheckReadStorage(this.selectorConfig.chooseMode, this.getContext())) {
            this.openSystemAlbum();
        }
        else {
            final String[] readPermissionArray = PermissionConfig.getReadPermissionArray(this.getAppContext(), this.selectorConfig.chooseMode);
            this.onPermissionExplainEvent(true, readPermissionArray);
            if (this.selectorConfig.onPermissionsEventListener != null) {
                this.onApplyPermissionsEvent(-2, readPermissionArray);
            }
            else {
                PermissionChecker.getInstance().requestPermissions((Fragment)this, readPermissionArray, (PermissionResultCallback)new PictureSelectorSystemFragment$1(this, readPermissionArray));
            }
        }
    }
}
