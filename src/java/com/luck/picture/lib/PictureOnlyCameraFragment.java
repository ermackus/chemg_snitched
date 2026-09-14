package com.luck.picture.lib;

import com.luck.picture.lib.permissions.PermissionResultCallback;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.luck.picture.lib.permissions.PermissionConfig;
import com.luck.picture.lib.utils.ToastUtils;
import com.luck.picture.lib.utils.SdkVersionUtils;
import com.luck.picture.lib.permissions.PermissionChecker;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.basic.PictureCommonFragment;

public class PictureOnlyCameraFragment extends PictureCommonFragment
{
    public static final String TAG;
    
    static {
        TAG = PictureOnlyCameraFragment.class.getSimpleName();
    }
    
    public static PictureOnlyCameraFragment newInstance() {
        return new PictureOnlyCameraFragment();
    }
    
    @Override
    public void dispatchCameraMediaResult(final LocalMedia localMedia) {
        if (this.confirmSelect(localMedia, false) == 0) {
            this.dispatchTransformResult();
        }
        else {
            this.onKeyBackFragmentFinish();
        }
    }
    
    @Override
    public String getFragmentTag() {
        return PictureOnlyCameraFragment.TAG;
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
            b = PermissionChecker.isCheckCamera(this.getContext());
            if (!SdkVersionUtils.isQ()) {
                b = PermissionChecker.isCheckWriteExternalStorage(this.getContext());
            }
        }
        if (b) {
            this.openSelectedCamera();
        }
        else {
            if (!PermissionChecker.isCheckCamera(this.getContext())) {
                ToastUtils.showToast(this.getContext(), this.getString(R$string.ps_camera));
            }
            else if (!PermissionChecker.isCheckWriteExternalStorage(this.getContext())) {
                ToastUtils.showToast(this.getContext(), this.getString(R$string.ps_jurisdiction));
            }
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
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle == null) {
            if (SdkVersionUtils.isQ()) {
                this.openSelectedCamera();
            }
            else {
                final String[] array = { "android.permission.WRITE_EXTERNAL_STORAGE" };
                PermissionChecker.getInstance().requestPermissions((Fragment)this, array, (PermissionResultCallback)new PictureOnlyCameraFragment$1(this, array));
            }
        }
    }
}
