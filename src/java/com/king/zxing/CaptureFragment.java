package com.king.zxing;

import com.king.zxing.util.LogUtils;
import com.king.zxing.util.PermissionUtils;
import com.google.zxing.Result;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.camera.view.PreviewView;
import android.view.View;
import androidx.fragment.app.Fragment;

public class CaptureFragment extends Fragment implements CameraScan$OnScanResultCallback
{
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 134;
    protected View ivFlashlight;
    private CameraScan mCameraScan;
    private View mRootView;
    protected PreviewView previewView;
    protected ViewfinderView viewfinderView;
    
    public static CaptureFragment newInstance() {
        final Bundle arguments = new Bundle();
        final CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setArguments(arguments);
        return captureFragment;
    }
    
    private void releaseCamera() {
        final CameraScan mCameraScan = this.mCameraScan;
        if (mCameraScan != null) {
            mCameraScan.release();
        }
    }
    
    public View createRootView(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        return layoutInflater.inflate(this.getLayoutId(), viewGroup, false);
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
    
    public View getRootView() {
        return this.mRootView;
    }
    
    public int getViewfinderViewId() {
        return R$id.viewfinderView;
    }
    
    public void initCameraScan() {
        (this.mCameraScan = new DefaultCameraScan(this, this.previewView)).setOnScanResultCallback((CameraScan$OnScanResultCallback)this);
    }
    
    public void initUI() {
        this.previewView = (PreviewView)this.mRootView.findViewById(this.getPreviewViewId());
        final int viewfinderViewId = this.getViewfinderViewId();
        if (viewfinderViewId != 0) {
            this.viewfinderView = (ViewfinderView)this.mRootView.findViewById(viewfinderViewId);
        }
        final int flashlightId = this.getFlashlightId();
        if (flashlightId != 0) {
            final View viewById = this.mRootView.findViewById(flashlightId);
            if ((this.ivFlashlight = viewById) != null) {
                viewById.setOnClickListener((View$OnClickListener)new _$$Lambda$CaptureFragment$1ufekcB5FFQt8ErPgWTbCkKt_CY(this));
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
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.isContentView(this.getLayoutId())) {
            this.mRootView = this.createRootView(layoutInflater, viewGroup);
        }
        this.initUI();
        return this.mRootView;
    }
    
    public void onDestroy() {
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
            this.getActivity().finish();
        }
    }
    
    public void startCamera() {
        if (this.mCameraScan != null) {
            if (PermissionUtils.checkPermission(this.getContext(), "android.permission.CAMERA")) {
                this.mCameraScan.startCamera();
            }
            else {
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED");
                PermissionUtils.requestPermission((Fragment)this, "android.permission.CAMERA", 134);
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
