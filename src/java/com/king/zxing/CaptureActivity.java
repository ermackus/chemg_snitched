package com.king.zxing;

import android.app.Activity;
import com.king.zxing.util.LogUtils;
import android.content.Context;
import com.king.zxing.util.PermissionUtils;
import com.google.zxing.Result;
import android.os.Bundle;
import android.view.View$OnClickListener;
import androidx.fragment.app.FragmentActivity;
import androidx.camera.view.PreviewView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CaptureActivity extends AppCompatActivity implements CameraScan$OnScanResultCallback
{
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 134;
    protected View ivFlashlight;
    private CameraScan mCameraScan;
    protected PreviewView previewView;
    protected ViewfinderView viewfinderView;
    
    private void releaseCamera() {
        final CameraScan mCameraScan = this.mCameraScan;
        if (mCameraScan != null) {
            mCameraScan.release();
        }
    }
    
    public CameraScan getCameraScan() {
        return this.mCameraScan;
    }
    
    public int getFlashlightId() {
        return R$id.ivFlashlight;
    }
    
    public int getLayoutId() {
        return R$layout.zxl_capture;
    }
    
    public int getPreviewViewId() {
        return R$id.previewView;
    }
    
    public int getViewfinderViewId() {
        return R$id.viewfinderView;
    }
    
    public void initCameraScan() {
        (this.mCameraScan = new DefaultCameraScan((FragmentActivity)this, this.previewView)).setOnScanResultCallback((CameraScan$OnScanResultCallback)this);
    }
    
    public void initUI() {
        this.previewView = (PreviewView)this.findViewById(this.getPreviewViewId());
        final int viewfinderViewId = this.getViewfinderViewId();
        if (viewfinderViewId != 0) {
            this.viewfinderView = (ViewfinderView)this.findViewById(viewfinderViewId);
        }
        final int flashlightId = this.getFlashlightId();
        if (flashlightId != 0) {
            final View viewById = this.findViewById(flashlightId);
            if ((this.ivFlashlight = viewById) != null) {
                viewById.setOnClickListener((View$OnClickListener)new _$$Lambda$CaptureActivity$BUmGCFGALb7xM_EgaJXWO2Tc2nA(this));
            }
        }
        this.initCameraScan();
        this.startCamera();
    }
    
    public boolean isContentView(final int n) {
        return true;
    }
    
    protected void onClickFlashlight() {
        this.toggleTorchState();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final int layoutId = this.getLayoutId();
        if (this.isContentView(layoutId)) {
            this.setContentView(layoutId);
        }
        this.initUI();
    }
    
    protected void onDestroy() {
        this.releaseCamera();
        super.onDestroy();
    }
    
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        super.onRequestPermissionsResult(n, array, array2);
        if (n == 134) {
            this.requestCameraPermissionResult(array, array2);
        }
    }
    
    public boolean onScanResultCallback(final Result result) {
        return false;
    }
    
    public void requestCameraPermissionResult(final String[] array, final int[] array2) {
        if (PermissionUtils.requestPermissionsResult("android.permission.CAMERA", array, array2)) {
            this.startCamera();
        }
        else {
            this.finish();
        }
    }
    
    public void startCamera() {
        if (this.mCameraScan != null) {
            if (PermissionUtils.checkPermission((Context)this, "android.permission.CAMERA")) {
                this.mCameraScan.startCamera();
            }
            else {
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED");
                PermissionUtils.requestPermission((Activity)this, "android.permission.CAMERA", 134);
            }
        }
    }
    
    protected void toggleTorchState() {
        final CameraScan mCameraScan = this.mCameraScan;
        if (mCameraScan != null) {
            final boolean torchEnabled = mCameraScan.isTorchEnabled();
            this.mCameraScan.enableTorch(torchEnabled ^ true);
            final View ivFlashlight = this.ivFlashlight;
            if (ivFlashlight != null) {
                ivFlashlight.setSelected(torchEnabled ^ true);
            }
        }
    }
}
