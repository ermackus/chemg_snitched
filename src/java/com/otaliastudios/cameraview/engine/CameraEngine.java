package com.otaliastudios.cameraview.engine;

import java.io.FileDescriptor;
import java.io.File;
import com.otaliastudios.cameraview.VideoResult$Stub;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.metering.MeteringRegions;
import com.otaliastudios.cameraview.gesture.Gesture;
import android.graphics.PointF;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.preview.CameraPreview;
import com.otaliastudios.cameraview.size.SizeSelector;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.overlay.Overlay;
import com.otaliastudios.cameraview.controls.Mode;
import android.location.Location;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.frame.FrameManager;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.Audio;
import com.otaliastudios.cameraview.engine.offset.Angles;
import com.otaliastudios.cameraview.controls.Facing;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.otaliastudios.cameraview.CameraOptions;
import com.google.android.gms.tasks.SuccessContinuation;
import java.util.concurrent.Callable;
import com.otaliastudios.cameraview.engine.orchestrator.CameraState;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import java.util.concurrent.CountDownLatch;
import android.os.Looper;
import com.otaliastudios.cameraview.engine.orchestrator.CameraOrchestrator$Callback;
import com.otaliastudios.cameraview.engine.orchestrator.CameraStateOrchestrator;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import android.os.Handler;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.video.VideoRecorder$VideoResultListener;
import com.otaliastudios.cameraview.picture.PictureRecorder$PictureResultListener;
import com.otaliastudios.cameraview.preview.CameraPreview$SurfaceCallback;

public abstract class CameraEngine implements CameraPreview$SurfaceCallback, PictureRecorder$PictureResultListener, VideoRecorder$VideoResultListener
{
    private static final int DESTROY_RETRIES = 2;
    protected static final CameraLogger LOG;
    protected static final String TAG;
    private final CameraEngine.CameraEngine$Callback mCallback;
    Handler mCrashHandler;
    private WorkerHandler mHandler;
    private final CameraStateOrchestrator mOrchestrator;
    
    static {
        LOG = CameraLogger.create(TAG = CameraEngine.class.getSimpleName());
    }
    
    protected CameraEngine(final CameraEngine.CameraEngine$Callback mCallback) {
        this.mOrchestrator = new CameraStateOrchestrator((CameraOrchestrator$Callback)new CameraOrchestrator$Callback() {
            final CameraEngine this$0;
            
            public WorkerHandler getJobWorker(final String s) {
                return this.this$0.mHandler;
            }
            
            public void handleJobException(final String s, final Exception ex) {
                this.this$0.handleException((Throwable)ex, false);
            }
        });
        this.mCallback = mCallback;
        this.mCrashHandler = new Handler(Looper.getMainLooper());
        this.recreateHandler(false);
    }
    
    private void destroy(final boolean b, int n) {
        CameraEngine.LOG.i(new Object[] { "DESTROY:", "state:", this.getState(), "thread:", Thread.currentThread(), "depth:", n, "unrecoverably:", b });
        if (b) {
            this.mHandler.getThread().setUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)new CameraEngine.CameraEngine$NoOpExceptionHandler((CameraEngine$1)null));
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.stop(true).addOnCompleteListener(this.mHandler.getExecutor(), (OnCompleteListener)new OnCompleteListener<Void>(this, countDownLatch) {
            final CameraEngine this$0;
            final CountDownLatch val$latch;
            
            public void onComplete(final Task<Void> task) {
                this.val$latch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(6L, TimeUnit.SECONDS)) {
                CameraEngine.LOG.e(new Object[] { "DESTROY: Could not destroy synchronously after 6 seconds.", "Current thread:", Thread.currentThread(), "Handler thread:", this.mHandler.getThread() });
                if (++n < 2) {
                    this.recreateHandler(true);
                    CameraEngine.LOG.e(new Object[] { "DESTROY: Trying again on thread:", this.mHandler.getThread() });
                    this.destroy(b, n);
                }
                else {
                    CameraEngine.LOG.w(new Object[] { "DESTROY: Giving up because DESTROY_RETRIES was reached." });
                }
            }
        }
        catch (final InterruptedException ex) {}
    }
    
    private void handleException(final Throwable t, final boolean b) {
        if (b) {
            CameraEngine.LOG.e(new Object[] { "EXCEPTION:", "Handler thread is gone. Replacing." });
            this.recreateHandler(false);
        }
        CameraEngine.LOG.e(new Object[] { "EXCEPTION:", "Scheduling on the crash handler..." });
        this.mCrashHandler.post((Runnable)new CameraEngine$2(this, t));
    }
    
    private void recreateHandler(final boolean b) {
        final WorkerHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.destroy();
        }
        final WorkerHandler value = WorkerHandler.get("CameraViewEngine");
        this.mHandler = value;
        value.getThread().setUncaughtExceptionHandler((Thread$UncaughtExceptionHandler)new CameraEngine.CameraEngine$CrashExceptionHandler(this, (CameraEngine$1)null));
        if (b) {
            this.mOrchestrator.reset();
        }
    }
    
    private Task<Void> startBind() {
        return this.mOrchestrator.scheduleStateChange(CameraState.ENGINE, CameraState.BIND, true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Void>>)new CameraEngine$8(this));
    }
    
    private Task<Void> startEngine() {
        return (Task<Void>)this.mOrchestrator.scheduleStateChange(CameraState.OFF, CameraState.ENGINE, true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Object>>)new CameraEngine$5(this)).onSuccessTask((SuccessContinuation)new SuccessContinuation<CameraOptions, Void>(this) {
            final CameraEngine this$0;
            
            public Task<Void> then(final CameraOptions cameraOptions) {
                if (cameraOptions != null) {
                    this.this$0.mCallback.dispatchOnCameraOpened(cameraOptions);
                    return (Task<Void>)Tasks.forResult((Object)null);
                }
                throw new RuntimeException("Null options!");
            }
        });
    }
    
    private Task<Void> startPreview() {
        return this.mOrchestrator.scheduleStateChange(CameraState.BIND, CameraState.PREVIEW, true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Void>>)new CameraEngine$10(this));
    }
    
    private Task<Void> stopBind(final boolean b) {
        return this.mOrchestrator.scheduleStateChange(CameraState.BIND, CameraState.ENGINE, b ^ true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Void>>)new CameraEngine$9(this));
    }
    
    private Task<Void> stopEngine(final boolean b) {
        return (Task<Void>)this.mOrchestrator.scheduleStateChange(CameraState.ENGINE, CameraState.OFF, b ^ true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Object>>)new CameraEngine$7(this)).addOnSuccessListener((OnSuccessListener)new OnSuccessListener<Void>(this) {
            final CameraEngine this$0;
            
            public void onSuccess(final Void void1) {
                this.this$0.mCallback.dispatchOnCameraClosed();
            }
        });
    }
    
    private Task<Void> stopPreview(final boolean b) {
        return this.mOrchestrator.scheduleStateChange(CameraState.PREVIEW, CameraState.BIND, b ^ true, (java.util.concurrent.Callable<com.google.android.gms.tasks.Task<Void>>)new CameraEngine$11(this));
    }
    
    protected abstract boolean collectCameraInfo(final Facing p0);
    
    public void destroy(final boolean b) {
        this.destroy(b, 0);
    }
    
    public abstract Angles getAngles();
    
    public abstract Audio getAudio();
    
    public abstract int getAudioBitRate();
    
    public abstract AudioCodec getAudioCodec();
    
    public abstract long getAutoFocusResetDelay();
    
    protected final CameraEngine.CameraEngine$Callback getCallback() {
        return this.mCallback;
    }
    
    public abstract CameraOptions getCameraOptions();
    
    public abstract float getExposureCorrectionValue();
    
    public abstract Facing getFacing();
    
    public abstract Flash getFlash();
    
    public abstract FrameManager getFrameManager();
    
    public abstract int getFrameProcessingFormat();
    
    public abstract int getFrameProcessingMaxHeight();
    
    public abstract int getFrameProcessingMaxWidth();
    
    public abstract int getFrameProcessingPoolSize();
    
    public abstract Hdr getHdr();
    
    public abstract Location getLocation();
    
    public abstract Mode getMode();
    
    protected final CameraStateOrchestrator getOrchestrator() {
        return this.mOrchestrator;
    }
    
    public abstract Overlay getOverlay();
    
    public abstract PictureFormat getPictureFormat();
    
    public abstract boolean getPictureMetering();
    
    public abstract Size getPictureSize(final Reference p0);
    
    public abstract SizeSelector getPictureSizeSelector();
    
    public abstract boolean getPictureSnapshotMetering();
    
    public abstract CameraPreview getPreview();
    
    public abstract float getPreviewFrameRate();
    
    public abstract boolean getPreviewFrameRateExact();
    
    public abstract Size getPreviewStreamSize(final Reference p0);
    
    public abstract SizeSelector getPreviewStreamSizeSelector();
    
    public abstract int getSnapshotMaxHeight();
    
    public abstract int getSnapshotMaxWidth();
    
    public final CameraState getState() {
        return this.mOrchestrator.getCurrentState();
    }
    
    public final CameraState getTargetState() {
        return this.mOrchestrator.getTargetState();
    }
    
    public abstract Size getUncroppedSnapshotSize(final Reference p0);
    
    public abstract int getVideoBitRate();
    
    public abstract VideoCodec getVideoCodec();
    
    public abstract int getVideoMaxDuration();
    
    public abstract long getVideoMaxSize();
    
    public abstract Size getVideoSize(final Reference p0);
    
    public abstract SizeSelector getVideoSizeSelector();
    
    public abstract WhiteBalance getWhiteBalance();
    
    public abstract float getZoomValue();
    
    public abstract boolean hasFrameProcessors();
    
    public final boolean isChangingState() {
        return this.mOrchestrator.hasPendingStateChange();
    }
    
    public abstract boolean isTakingPicture();
    
    public abstract boolean isTakingVideo();
    
    protected abstract Task<Void> onStartBind();
    
    protected abstract Task<CameraOptions> onStartEngine();
    
    protected abstract Task<Void> onStartPreview();
    
    protected abstract Task<Void> onStopBind();
    
    protected abstract Task<Void> onStopEngine();
    
    protected abstract Task<Void> onStopPreview();
    
    public final void onSurfaceAvailable() {
        CameraEngine.LOG.i(new Object[] { "onSurfaceAvailable:", "Size is", this.getPreview().getSurfaceSize() });
        this.startBind();
        this.startPreview();
    }
    
    public final void onSurfaceDestroyed() {
        CameraEngine.LOG.i(new Object[] { "onSurfaceDestroyed" });
        this.stopPreview(false);
        this.stopBind(false);
    }
    
    public void restart() {
        CameraEngine.LOG.i(new Object[] { "RESTART:", "scheduled. State:", this.getState() });
        this.stop(false);
        this.start();
    }
    
    protected Task<Void> restartBind() {
        CameraEngine.LOG.i(new Object[] { "RESTART BIND:", "scheduled. State:", this.getState() });
        this.stopPreview(false);
        this.stopBind(false);
        this.startBind();
        return this.startPreview();
    }
    
    protected Task<Void> restartPreview() {
        CameraEngine.LOG.i(new Object[] { "RESTART PREVIEW:", "scheduled. State:", this.getState() });
        this.stopPreview(false);
        return this.startPreview();
    }
    
    public abstract void setAudio(final Audio p0);
    
    public abstract void setAudioBitRate(final int p0);
    
    public abstract void setAudioCodec(final AudioCodec p0);
    
    public abstract void setAutoFocusResetDelay(final long p0);
    
    public abstract void setExposureCorrection(final float p0, final float[] p1, final PointF[] p2, final boolean p3);
    
    public abstract void setFacing(final Facing p0);
    
    public abstract void setFlash(final Flash p0);
    
    public abstract void setFrameProcessingFormat(final int p0);
    
    public abstract void setFrameProcessingMaxHeight(final int p0);
    
    public abstract void setFrameProcessingMaxWidth(final int p0);
    
    public abstract void setFrameProcessingPoolSize(final int p0);
    
    public abstract void setHasFrameProcessors(final boolean p0);
    
    public abstract void setHdr(final Hdr p0);
    
    public abstract void setLocation(final Location p0);
    
    public abstract void setMode(final Mode p0);
    
    public abstract void setOverlay(final Overlay p0);
    
    public abstract void setPictureFormat(final PictureFormat p0);
    
    public abstract void setPictureMetering(final boolean p0);
    
    public abstract void setPictureSizeSelector(final SizeSelector p0);
    
    public abstract void setPictureSnapshotMetering(final boolean p0);
    
    public abstract void setPlaySounds(final boolean p0);
    
    public abstract void setPreview(final CameraPreview p0);
    
    public abstract void setPreviewFrameRate(final float p0);
    
    public abstract void setPreviewFrameRateExact(final boolean p0);
    
    public abstract void setPreviewStreamSizeSelector(final SizeSelector p0);
    
    public abstract void setSnapshotMaxHeight(final int p0);
    
    public abstract void setSnapshotMaxWidth(final int p0);
    
    public abstract void setVideoBitRate(final int p0);
    
    public abstract void setVideoCodec(final VideoCodec p0);
    
    public abstract void setVideoMaxDuration(final int p0);
    
    public abstract void setVideoMaxSize(final long p0);
    
    public abstract void setVideoSizeSelector(final SizeSelector p0);
    
    public abstract void setWhiteBalance(final WhiteBalance p0);
    
    public abstract void setZoom(final float p0, final PointF[] p1, final boolean p2);
    
    public Task<Void> start() {
        CameraEngine.LOG.i(new Object[] { "START:", "scheduled. State:", this.getState() });
        final Task<Void> startEngine = this.startEngine();
        this.startBind();
        this.startPreview();
        return startEngine;
    }
    
    public abstract void startAutoFocus(final Gesture p0, final MeteringRegions p1, final PointF p2);
    
    public Task<Void> stop(final boolean b) {
        CameraEngine.LOG.i(new Object[] { "STOP:", "scheduled. State:", this.getState() });
        this.stopPreview(b);
        this.stopBind(b);
        return this.stopEngine(b);
    }
    
    public abstract void stopVideo();
    
    public abstract void takePicture(final PictureResult$Stub p0);
    
    public abstract void takePictureSnapshot(final PictureResult$Stub p0);
    
    public abstract void takeVideo(final VideoResult$Stub p0, final File p1, final FileDescriptor p2);
    
    public abstract void takeVideoSnapshot(final VideoResult$Stub p0, final File p1);
}
