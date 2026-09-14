package com.king.zxing;

import androidx.core.content.ContextCompat;
import androidx.camera.core.ZoomState;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.ImageAnalysis$Analyzer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.camera.core.ImageAnalysis$Builder;
import androidx.camera.core.CameraSelector$Builder;
import androidx.camera.core.Preview$Builder;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.FocusMeteringAction$Builder;
import android.content.Intent;
import android.util.DisplayMetrics;
import com.king.zxing.manager.AmbientLightManager$OnLightSensorEventListener;
import com.king.zxing.util.LogUtils;
import android.view.View$OnTouchListener;
import android.view.ScaleGestureDetector;
import androidx.lifecycle.Observer;
import com.king.zxing.analyze.MultiFormatAnalyzer;
import com.google.zxing.common.detector.MathUtils;
import android.view.MotionEvent;
import com.google.zxing.ResultPoint;
import com.google.zxing.BarcodeFormat;
import androidx.fragment.app.Fragment;
import com.google.zxing.Result;
import androidx.lifecycle.MutableLiveData;
import androidx.camera.view.PreviewView;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.fragment.app.FragmentActivity;
import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.king.zxing.config.CameraConfig;
import androidx.camera.core.Camera;
import com.king.zxing.manager.BeepManager;
import com.king.zxing.analyze.Analyzer;
import com.king.zxing.manager.AmbientLightManager;
import android.view.View;

public class DefaultCameraScan extends CameraScan
{
    private static final int HOVER_TAP_SLOP = 20;
    private static final int HOVER_TAP_TIMEOUT = 150;
    private View flashlightView;
    private volatile boolean isAnalyze;
    private volatile boolean isAnalyzeResult;
    private boolean isClickTap;
    private AmbientLightManager mAmbientLightManager;
    private Analyzer mAnalyzer;
    private BeepManager mBeepManager;
    private Camera mCamera;
    private CameraConfig mCameraConfig;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private Context mContext;
    private float mDownX;
    private float mDownY;
    private FragmentActivity mFragmentActivity;
    private long mLastAutoZoomTime;
    private long mLastHoveTapTime;
    private LifecycleOwner mLifecycleOwner;
    private ScaleGestureDetector$OnScaleGestureListener mOnScaleGestureListener;
    private CameraScan$OnScanResultCallback mOnScanResultCallback;
    private int mOrientation;
    private PreviewView mPreviewView;
    private MutableLiveData<Result> mResultLiveData;
    private int mScreenHeight;
    private int mScreenWidth;
    
    public DefaultCameraScan(final Fragment mLifecycleOwner, final PreviewView mPreviewView) {
        this.isAnalyze = true;
        this.mOnScaleGestureListener = (ScaleGestureDetector$OnScaleGestureListener)new DefaultCameraScan$1(this);
        this.mFragmentActivity = mLifecycleOwner.getActivity();
        this.mLifecycleOwner = (LifecycleOwner)mLifecycleOwner;
        this.mContext = mLifecycleOwner.getContext();
        this.mPreviewView = mPreviewView;
        this.initData();
    }
    
    public DefaultCameraScan(final FragmentActivity mContext, final PreviewView mPreviewView) {
        this.isAnalyze = true;
        this.mOnScaleGestureListener = (ScaleGestureDetector$OnScaleGestureListener)new DefaultCameraScan$1(this);
        this.mFragmentActivity = mContext;
        this.mLifecycleOwner = (LifecycleOwner)mContext;
        this.mContext = (Context)mContext;
        this.mPreviewView = mPreviewView;
        this.initData();
    }
    
    private void handleAnalyzeResult(final Result result) {
        synchronized (this) {
            if (!this.isAnalyzeResult && this.isAnalyze) {
                this.isAnalyzeResult = true;
                if (this.mBeepManager != null) {
                    this.mBeepManager.playBeepSoundAndVibrate();
                }
                if (result.getBarcodeFormat() == BarcodeFormat.QR_CODE && this.isNeedAutoZoom() && this.mLastAutoZoomTime + 100L < System.currentTimeMillis()) {
                    final ResultPoint[] resultPoints = result.getResultPoints();
                    if (resultPoints != null && resultPoints.length >= 2) {
                        float n = ResultPoint.distance(resultPoints[0], resultPoints[1]);
                        if (resultPoints.length >= 3) {
                            n = Math.max(Math.max(n, ResultPoint.distance(resultPoints[1], resultPoints[2])), ResultPoint.distance(resultPoints[0], resultPoints[2]));
                        }
                        if (this.handleAutoZoom((int)n, result)) {
                            return;
                        }
                    }
                }
                this.scanResultCallback(result);
            }
        }
    }
    
    private boolean handleAutoZoom(final int n, final Result result) {
        if (n * 4 < Math.min(this.mScreenWidth, this.mScreenHeight)) {
            this.mLastAutoZoomTime = System.currentTimeMillis();
            this.zoomIn();
            this.scanResultCallback(result);
            return true;
        }
        return false;
    }
    
    private void handlePreviewViewClickTap(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        boolean isClickTap = true;
        if (pointerCount == 1) {
            final int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        if (MathUtils.distance(this.mDownX, this.mDownY, motionEvent.getX(), motionEvent.getY()) >= 20.0f) {
                            isClickTap = false;
                        }
                        this.isClickTap = isClickTap;
                    }
                }
                else if (this.isClickTap && this.mLastHoveTapTime + 150L > System.currentTimeMillis()) {
                    this.startFocusAndMetering(motionEvent.getX(), motionEvent.getY());
                }
            }
            else {
                this.isClickTap = true;
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                this.mLastHoveTapTime = System.currentTimeMillis();
            }
        }
    }
    
    private void initConfig() {
        if (this.mCameraConfig == null) {
            this.mCameraConfig = new CameraConfig();
        }
        if (this.mAnalyzer == null) {
            this.mAnalyzer = (Analyzer)new MultiFormatAnalyzer();
        }
    }
    
    private void initData() {
        (this.mResultLiveData = (MutableLiveData<Result>)new MutableLiveData()).observe(this.mLifecycleOwner, (Observer)new _$$Lambda$DefaultCameraScan$7Y8JYbDBjgftp5njYhpTQq9ZUS0(this));
        this.mOrientation = this.mContext.getResources().getConfiguration().orientation;
        this.mPreviewView.setOnTouchListener((View$OnTouchListener)new _$$Lambda$DefaultCameraScan$90NmNolwBoyrnN8pz19WoFmiDDU(this, new ScaleGestureDetector(this.mContext, this.mOnScaleGestureListener)));
        final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        LogUtils.d(String.format("displayMetrics:%dx%d", new Object[] { this.mScreenWidth, this.mScreenHeight }));
        this.mBeepManager = new BeepManager(this.mContext);
        final AmbientLightManager mAmbientLightManager = new AmbientLightManager(this.mContext);
        this.mAmbientLightManager = mAmbientLightManager;
        if (mAmbientLightManager != null) {
            mAmbientLightManager.register();
            this.mAmbientLightManager.setOnLightSensorEventListener((AmbientLightManager$OnLightSensorEventListener)new _$$Lambda$DefaultCameraScan$FxBLUkasL3PIwxloANx_d1EUB6E(this));
        }
    }
    
    private void scanResultCallback(final Result result) {
        final CameraScan$OnScanResultCallback mOnScanResultCallback = this.mOnScanResultCallback;
        if (mOnScanResultCallback != null && mOnScanResultCallback.onScanResultCallback(result)) {
            this.isAnalyzeResult = false;
            return;
        }
        if (this.mFragmentActivity != null) {
            final Intent intent = new Intent();
            intent.putExtra(DefaultCameraScan.SCAN_RESULT, result.getText());
            this.mFragmentActivity.setResult(-1, intent);
            this.mFragmentActivity.finish();
        }
    }
    
    private void startFocusAndMetering(final float n, final float n2) {
        if (this.mCamera != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("startFocusAndMetering:");
            sb.append(n);
            sb.append(",");
            sb.append(n2);
            LogUtils.d(sb.toString());
            this.mCamera.getCameraControl().startFocusAndMetering(new FocusMeteringAction$Builder(this.mPreviewView.getMeteringPointFactory().createPoint(n, n2)).build());
        }
    }
    
    public CameraScan bindFlashlightView(final View flashlightView) {
        this.flashlightView = flashlightView;
        final AmbientLightManager mAmbientLightManager = this.mAmbientLightManager;
        if (mAmbientLightManager != null) {
            mAmbientLightManager.setLightSensorEnabled(flashlightView != null);
        }
        return this;
    }
    
    public void enableTorch(final boolean b) {
        if (this.mCamera != null && this.hasFlashUnit()) {
            this.mCamera.getCameraControl().enableTorch(b);
        }
    }
    
    public Camera getCamera() {
        return this.mCamera;
    }
    
    public boolean hasFlashUnit() {
        final Camera mCamera = this.mCamera;
        return mCamera != null && mCamera.getCameraInfo().hasFlashUnit();
    }
    
    public boolean isTorchEnabled() {
        final Camera mCamera = this.mCamera;
        boolean b = false;
        if (mCamera != null) {
            b = b;
            if ((int)mCamera.getCameraInfo().getTorchState().getValue() == 1) {
                b = true;
            }
        }
        return b;
    }
    
    public void lineZoomIn() {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            final float linearZoom = ((ZoomState)mCamera.getCameraInfo().getZoomState().getValue()).getLinearZoom() + 0.1f;
            if (linearZoom <= 1.0f) {
                this.mCamera.getCameraControl().setLinearZoom(linearZoom);
            }
        }
    }
    
    public void lineZoomOut() {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            final float linearZoom = ((ZoomState)mCamera.getCameraInfo().getZoomState().getValue()).getLinearZoom() - 0.1f;
            if (linearZoom >= 0.0f) {
                this.mCamera.getCameraControl().setLinearZoom(linearZoom);
            }
        }
    }
    
    public void lineZoomTo(final float linearZoom) {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            mCamera.getCameraControl().setLinearZoom(linearZoom);
        }
    }
    
    public void release() {
        this.isAnalyze = false;
        this.flashlightView = null;
        final AmbientLightManager mAmbientLightManager = this.mAmbientLightManager;
        if (mAmbientLightManager != null) {
            mAmbientLightManager.unregister();
        }
        final BeepManager mBeepManager = this.mBeepManager;
        if (mBeepManager != null) {
            mBeepManager.close();
        }
        this.stopCamera();
    }
    
    public CameraScan setAnalyzeImage(final boolean isAnalyze) {
        this.isAnalyze = isAnalyze;
        return this;
    }
    
    public CameraScan setAnalyzer(final Analyzer mAnalyzer) {
        this.mAnalyzer = mAnalyzer;
        return this;
    }
    
    public CameraScan setBrightLightLux(final float brightLightLux) {
        final AmbientLightManager mAmbientLightManager = this.mAmbientLightManager;
        if (mAmbientLightManager != null) {
            mAmbientLightManager.setBrightLightLux(brightLightLux);
        }
        return this;
    }
    
    public CameraScan setCameraConfig(final CameraConfig mCameraConfig) {
        if (mCameraConfig != null) {
            this.mCameraConfig = mCameraConfig;
        }
        return this;
    }
    
    public CameraScan setDarkLightLux(final float darkLightLux) {
        final AmbientLightManager mAmbientLightManager = this.mAmbientLightManager;
        if (mAmbientLightManager != null) {
            mAmbientLightManager.setDarkLightLux(darkLightLux);
        }
        return this;
    }
    
    public CameraScan setOnScanResultCallback(final CameraScan$OnScanResultCallback mOnScanResultCallback) {
        this.mOnScanResultCallback = mOnScanResultCallback;
        return this;
    }
    
    public CameraScan setPlayBeep(final boolean playBeep) {
        final BeepManager mBeepManager = this.mBeepManager;
        if (mBeepManager != null) {
            mBeepManager.setPlayBeep(playBeep);
        }
        return this;
    }
    
    public CameraScan setVibrate(final boolean vibrate) {
        final BeepManager mBeepManager = this.mBeepManager;
        if (mBeepManager != null) {
            mBeepManager.setVibrate(vibrate);
        }
        return this;
    }
    
    public void startCamera() {
        this.initConfig();
        (this.mCameraProviderFuture = (ListenableFuture<ProcessCameraProvider>)ProcessCameraProvider.getInstance(this.mContext)).addListener((Runnable)new _$$Lambda$DefaultCameraScan$2GDqXc1_j2hxdA71T8t20m8winI(this), ContextCompat.getMainExecutor(this.mContext));
    }
    
    public void stopCamera() {
        final ListenableFuture<ProcessCameraProvider> mCameraProviderFuture = this.mCameraProviderFuture;
        if (mCameraProviderFuture != null) {
            try {
                ((ProcessCameraProvider)mCameraProviderFuture.get()).unbindAll();
            }
            catch (final Exception ex) {
                LogUtils.e((Throwable)ex);
            }
        }
    }
    
    public void zoomIn() {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            final float zoomRatio = ((ZoomState)mCamera.getCameraInfo().getZoomState().getValue()).getZoomRatio() + 0.1f;
            if (zoomRatio <= ((ZoomState)this.mCamera.getCameraInfo().getZoomState().getValue()).getMaxZoomRatio()) {
                this.mCamera.getCameraControl().setZoomRatio(zoomRatio);
            }
        }
    }
    
    public void zoomOut() {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            final float zoomRatio = ((ZoomState)mCamera.getCameraInfo().getZoomState().getValue()).getZoomRatio() - 0.1f;
            if (zoomRatio >= ((ZoomState)this.mCamera.getCameraInfo().getZoomState().getValue()).getMinZoomRatio()) {
                this.mCamera.getCameraControl().setZoomRatio(zoomRatio);
            }
        }
    }
    
    public void zoomTo(float max) {
        final Camera mCamera = this.mCamera;
        if (mCamera != null) {
            final ZoomState zoomState = (ZoomState)mCamera.getCameraInfo().getZoomState().getValue();
            max = Math.max(Math.min(max, zoomState.getMaxZoomRatio()), zoomState.getMinZoomRatio());
            this.mCamera.getCameraControl().setZoomRatio(max);
        }
    }
}
