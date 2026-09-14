package com.otaliastudios.cameraview.engine;

import com.otaliastudios.cameraview.video.VideoRecorder;
import com.otaliastudios.cameraview.picture.PictureRecorder;
import com.otaliastudios.cameraview.metering.MeteringRegions;
import com.otaliastudios.cameraview.gesture.Gesture;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.engine.orchestrator.CameraStateOrchestrator;
import android.graphics.PointF;
import android.graphics.Rect;
import com.otaliastudios.cameraview.video.SnapshotVideoRecorder;
import com.otaliastudios.cameraview.internal.CropHelper;
import com.otaliastudios.cameraview.video.Full1VideoRecorder;
import com.otaliastudios.cameraview.VideoResult$Stub;
import com.otaliastudios.cameraview.picture.Snapshot1PictureRecorder;
import com.otaliastudios.cameraview.picture.PictureRecorder$PictureResultListener;
import com.otaliastudios.cameraview.picture.SnapshotGlPictureRecorder;
import com.otaliastudios.cameraview.preview.RendererCameraPreview;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.picture.Full1PictureRecorder;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.engine.offset.Axis;
import com.otaliastudios.cameraview.engine.options.Camera1Options;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.otaliastudios.cameraview.CameraOptions;
import java.io.IOException;
import com.google.android.gms.tasks.Tasks;
import android.graphics.SurfaceTexture;
import android.view.SurfaceHolder;
import com.google.android.gms.tasks.Task;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.engine.orchestrator.CameraState;
import com.otaliastudios.cameraview.CameraException;
import android.hardware.Camera$Size;
import java.util.ArrayList;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.frame.FrameManager;
import com.otaliastudios.cameraview.frame.ByteBufferFrameManager;
import com.otaliastudios.cameraview.controls.Facing;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import android.hardware.Camera$CameraInfo;
import android.os.Build$VERSION;
import com.otaliastudios.cameraview.controls.Control;
import java.util.List;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import android.location.Location;
import com.otaliastudios.cameraview.controls.Flash;
import android.hardware.Camera$Parameters;
import com.otaliastudios.cameraview.engine.mappers.Camera1Mapper;
import android.hardware.Camera;
import com.otaliastudios.cameraview.frame.ByteBufferFrameManager$BufferCallback;
import android.hardware.Camera$ErrorCallback;
import android.hardware.Camera$PreviewCallback;

public class Camera1Engine extends CameraBaseEngine implements Camera$PreviewCallback, Camera$ErrorCallback, ByteBufferFrameManager$BufferCallback
{
    static final int AUTOFOCUS_END_DELAY_MILLIS = 2500;
    private static final String JOB_FOCUS_END = "focus end";
    private static final String JOB_FOCUS_RESET = "focus reset";
    private static final int PREVIEW_FORMAT = 17;
    private Camera mCamera;
    int mCameraId;
    private final Camera1Mapper mMapper;
    
    public Camera1Engine(final CameraEngine$Callback cameraEngine$Callback) {
        super(cameraEngine$Callback);
        this.mMapper = Camera1Mapper.get();
    }
    
    private void applyAllParameters(final Camera$Parameters camera$Parameters) {
        camera$Parameters.setRecordingHint(this.getMode() == Mode.VIDEO);
        this.applyDefaultFocus(camera$Parameters);
        this.applyFlash(camera$Parameters, Flash.OFF);
        this.applyLocation(camera$Parameters, null);
        this.applyWhiteBalance(camera$Parameters, WhiteBalance.AUTO);
        this.applyHdr(camera$Parameters, Hdr.OFF);
        this.applyZoom(camera$Parameters, 0.0f);
        this.applyExposureCorrection(camera$Parameters, 0.0f);
        this.applyPlaySounds(this.mPlaySounds);
        this.applyPreviewFrameRate(camera$Parameters, 0.0f);
    }
    
    private void applyDefaultFocus(final Camera$Parameters camera$Parameters) {
        final List supportedFocusModes = camera$Parameters.getSupportedFocusModes();
        if (this.getMode() == Mode.VIDEO && supportedFocusModes.contains((Object)"continuous-video")) {
            camera$Parameters.setFocusMode("continuous-video");
            return;
        }
        if (supportedFocusModes.contains((Object)"continuous-picture")) {
            camera$Parameters.setFocusMode("continuous-picture");
            return;
        }
        if (supportedFocusModes.contains((Object)"infinity")) {
            camera$Parameters.setFocusMode("infinity");
            return;
        }
        if (supportedFocusModes.contains((Object)"fixed")) {
            camera$Parameters.setFocusMode("fixed");
        }
    }
    
    private boolean applyExposureCorrection(final Camera$Parameters camera$Parameters, float mExposureCorrectionValue) {
        if (this.mCameraOptions.isExposureCorrectionSupported()) {
            final float exposureCorrectionMaxValue = this.mCameraOptions.getExposureCorrectionMaxValue();
            final float exposureCorrectionMinValue = this.mCameraOptions.getExposureCorrectionMinValue();
            mExposureCorrectionValue = this.mExposureCorrectionValue;
            if (mExposureCorrectionValue < exposureCorrectionMinValue) {
                mExposureCorrectionValue = exposureCorrectionMinValue;
            }
            else if (mExposureCorrectionValue > exposureCorrectionMaxValue) {
                mExposureCorrectionValue = exposureCorrectionMaxValue;
            }
            this.mExposureCorrectionValue = mExposureCorrectionValue;
            camera$Parameters.setExposureCompensation((int)(this.mExposureCorrectionValue / camera$Parameters.getExposureCompensationStep()));
            return true;
        }
        this.mExposureCorrectionValue = mExposureCorrectionValue;
        return false;
    }
    
    private boolean applyFlash(final Camera$Parameters camera$Parameters, final Flash mFlash) {
        if (this.mCameraOptions.supports((Control)this.mFlash)) {
            camera$Parameters.setFlashMode(this.mMapper.mapFlash(this.mFlash));
            return true;
        }
        this.mFlash = mFlash;
        return false;
    }
    
    private boolean applyHdr(final Camera$Parameters camera$Parameters, final Hdr mHdr) {
        if (this.mCameraOptions.supports((Control)this.mHdr)) {
            camera$Parameters.setSceneMode(this.mMapper.mapHdr(this.mHdr));
            return true;
        }
        this.mHdr = mHdr;
        return false;
    }
    
    private boolean applyLocation(final Camera$Parameters camera$Parameters, final Location location) {
        if (this.mLocation != null) {
            camera$Parameters.setGpsLatitude(this.mLocation.getLatitude());
            camera$Parameters.setGpsLongitude(this.mLocation.getLongitude());
            camera$Parameters.setGpsAltitude(this.mLocation.getAltitude());
            camera$Parameters.setGpsTimestamp(this.mLocation.getTime());
            camera$Parameters.setGpsProcessingMethod(this.mLocation.getProvider());
        }
        return true;
    }
    
    private boolean applyPlaySounds(final boolean mPlaySounds) {
        if (Build$VERSION.SDK_INT >= 17) {
            final Camera$CameraInfo camera$CameraInfo = new Camera$CameraInfo();
            Camera.getCameraInfo(this.mCameraId, camera$CameraInfo);
            if (camera$CameraInfo.canDisableShutterSound) {
                try {
                    return this.mCamera.enableShutterSound(this.mPlaySounds);
                }
                catch (final RuntimeException ex) {
                    return false;
                }
            }
        }
        if (this.mPlaySounds) {
            return true;
        }
        this.mPlaySounds = mPlaySounds;
        return false;
    }
    
    private boolean applyPreviewFrameRate(final Camera$Parameters camera$Parameters, final float mPreviewFrameRate) {
        final List supportedPreviewFpsRange = camera$Parameters.getSupportedPreviewFpsRange();
        this.sortRanges((List<int[]>)supportedPreviewFpsRange);
        if (this.mPreviewFrameRate == 0.0f) {
            for (final int[] array : supportedPreviewFpsRange) {
                final float n = array[0] / 1000.0f;
                final float n2 = array[1] / 1000.0f;
                if ((n <= 30.0f && 30.0f <= n2) || (n <= 24.0f && 24.0f <= n2)) {
                    camera$Parameters.setPreviewFpsRange(array[0], array[1]);
                    return true;
                }
            }
        }
        else {
            this.mPreviewFrameRate = Math.min(this.mPreviewFrameRate, this.mCameraOptions.getPreviewFrameRateMaxValue());
            this.mPreviewFrameRate = Math.max(this.mPreviewFrameRate, this.mCameraOptions.getPreviewFrameRateMinValue());
            for (final int[] array2 : supportedPreviewFpsRange) {
                final float n3 = array2[0] / 1000.0f;
                final float n4 = array2[1] / 1000.0f;
                final float n5 = (float)Math.round(this.mPreviewFrameRate);
                if (n3 <= n5 && n5 <= n4) {
                    camera$Parameters.setPreviewFpsRange(array2[0], array2[1]);
                    return true;
                }
            }
        }
        this.mPreviewFrameRate = mPreviewFrameRate;
        return false;
    }
    
    private boolean applyWhiteBalance(final Camera$Parameters camera$Parameters, final WhiteBalance mWhiteBalance) {
        if (this.mCameraOptions.supports((Control)this.mWhiteBalance)) {
            camera$Parameters.setWhiteBalance(this.mMapper.mapWhiteBalance(this.mWhiteBalance));
            camera$Parameters.remove("auto-whitebalance-lock");
            return true;
        }
        this.mWhiteBalance = mWhiteBalance;
        return false;
    }
    
    private boolean applyZoom(final Camera$Parameters parameters, float mZoomValue) {
        if (this.mCameraOptions.isZoomSupported()) {
            mZoomValue = (float)parameters.getMaxZoom();
            parameters.setZoom((int)(this.mZoomValue * mZoomValue));
            this.mCamera.setParameters(parameters);
            return true;
        }
        this.mZoomValue = mZoomValue;
        return false;
    }
    
    private void sortRanges(final List<int[]> list) {
        if (this.getPreviewFrameRateExact() && this.mPreviewFrameRate != 0.0f) {
            Collections.sort((List)list, (Comparator)new Camera1Engine$9(this));
        }
        else {
            Collections.sort((List)list, (Comparator)new Camera1Engine$10(this));
        }
    }
    
    protected boolean collectCameraInfo(final Facing facing) {
        final int mapFacing = this.mMapper.mapFacing(facing);
        Camera1Engine.LOG.i(new Object[] { "collectCameraInfo", "Facing:", facing, "Internal:", mapFacing, "Cameras:", Camera.getNumberOfCameras() });
        final Camera$CameraInfo camera$CameraInfo = new Camera$CameraInfo();
        for (int numberOfCameras = Camera.getNumberOfCameras(), i = 0; i < numberOfCameras; ++i) {
            Camera.getCameraInfo(i, camera$CameraInfo);
            if (camera$CameraInfo.facing == mapFacing) {
                this.getAngles().setSensorOffset(facing, camera$CameraInfo.orientation);
                this.mCameraId = i;
                return true;
            }
        }
        return false;
    }
    
    public ByteBufferFrameManager getFrameManager() {
        return (ByteBufferFrameManager)super.getFrameManager();
    }
    
    @Override
    protected List<Size> getFrameProcessingAvailableSizes() {
        return (List<Size>)Collections.singletonList((Object)this.mPreviewStreamSize);
    }
    
    @Override
    protected List<Size> getPreviewStreamAvailableSizes() {
        try {
            final List supportedPreviewSizes = this.mCamera.getParameters().getSupportedPreviewSizes();
            final ArrayList list = new ArrayList(supportedPreviewSizes.size());
            for (final Camera$Size camera$Size : supportedPreviewSizes) {
                final Size size = new Size(camera$Size.width, camera$Size.height);
                if (!((List)list).contains((Object)size)) {
                    ((List)list).add((Object)size);
                }
            }
            Camera1Engine.LOG.i(new Object[] { "getPreviewStreamAvailableSizes:", list });
            return (List<Size>)list;
        }
        catch (final Exception ex) {
            Camera1Engine.LOG.e(new Object[] { "getPreviewStreamAvailableSizes:", "Failed to compute preview size. Camera params is empty" });
            throw new CameraException((Throwable)ex, 2);
        }
    }
    
    @Override
    protected FrameManager instantiateFrameManager(final int n) {
        return (FrameManager)new ByteBufferFrameManager(n, (ByteBufferFrameManager$BufferCallback)this);
    }
    
    public void onBufferAvailable(final byte[] array) {
        if (this.getState().isAtLeast(CameraState.ENGINE) && this.getTargetState().isAtLeast(CameraState.ENGINE)) {
            this.mCamera.addCallbackBuffer(array);
        }
    }
    
    public void onError(int n, final Camera camera) {
        final CameraLogger log = Camera1Engine.LOG;
        final int n2 = 0;
        final RuntimeException ex = new RuntimeException(log.e(new Object[] { "Internal Camera1 error.", n }));
        if (n != 1 && n != 2 && n != 100) {
            n = n2;
        }
        else {
            n = 3;
        }
        throw new CameraException((Throwable)ex, n);
    }
    
    public void onPreviewFrame(final byte[] array, final Camera camera) {
        if (array == null) {
            return;
        }
        final Frame frame = this.getFrameManager().getFrame((Object)array, System.currentTimeMillis());
        if (frame != null) {
            this.getCallback().dispatchFrame(frame);
        }
    }
    
    @Override
    protected void onPreviewStreamSizeChanged() {
        this.restartPreview();
    }
    
    protected Task<Void> onStartBind() {
        Camera1Engine.LOG.i(new Object[] { "onStartBind:", "Started" });
        try {
            if (this.mPreview.getOutputClass() == SurfaceHolder.class) {
                this.mCamera.setPreviewDisplay((SurfaceHolder)this.mPreview.getOutput());
            }
            else {
                if (this.mPreview.getOutputClass() != SurfaceTexture.class) {
                    throw new RuntimeException("Unknown CameraPreview output class.");
                }
                this.mCamera.setPreviewTexture((SurfaceTexture)this.mPreview.getOutput());
            }
            this.mCaptureSize = this.computeCaptureSize();
            this.mPreviewStreamSize = this.computePreviewStreamSize();
            Camera1Engine.LOG.i(new Object[] { "onStartBind:", "Returning" });
            return (Task<Void>)Tasks.forResult((Object)null);
        }
        catch (final IOException ex) {
            Camera1Engine.LOG.e(new Object[] { "onStartBind:", "Failed to bind.", ex });
            throw new CameraException((Throwable)ex, 2);
        }
    }
    
    protected Task<CameraOptions> onStartEngine() {
        try {
            final Camera open = Camera.open(this.mCameraId);
            this.mCamera = open;
            if (open != null) {
                open.setErrorCallback((Camera$ErrorCallback)this);
                Camera1Engine.LOG.i(new Object[] { "onStartEngine:", "Applying default parameters." });
                try {
                    final Camera$Parameters parameters = this.mCamera.getParameters();
                    this.mCameraOptions = (CameraOptions)new Camera1Options(parameters, this.mCameraId, this.getAngles().flip(Reference.SENSOR, Reference.VIEW));
                    this.applyAllParameters(parameters);
                    this.mCamera.setParameters(parameters);
                    try {
                        this.mCamera.setDisplayOrientation(this.getAngles().offset(Reference.SENSOR, Reference.VIEW, Axis.ABSOLUTE));
                        Camera1Engine.LOG.i(new Object[] { "onStartEngine:", "Ended" });
                        return (Task<CameraOptions>)Tasks.forResult((Object)this.mCameraOptions);
                    }
                    catch (final Exception ex) {
                        Camera1Engine.LOG.e(new Object[] { "onStartEngine:", "Failed to connect. Can't set display orientation, maybe preview already exists?" });
                        throw new CameraException(1);
                    }
                }
                catch (final Exception ex2) {
                    Camera1Engine.LOG.e(new Object[] { "onStartEngine:", "Failed to connect. Problem with camera params" });
                    throw new CameraException((Throwable)ex2, 1);
                }
            }
            Camera1Engine.LOG.e(new Object[] { "onStartEngine:", "Failed to connect. Camera is null, maybe in use by another app or already released?" });
            throw new CameraException(1);
        }
        catch (final Exception ex3) {
            Camera1Engine.LOG.e(new Object[] { "onStartEngine:", "Failed to connect. Maybe in use by another app?" });
            throw new CameraException((Throwable)ex3, 1);
        }
    }
    
    protected Task<Void> onStartPreview() {
        Camera1Engine.LOG.i(new Object[] { "onStartPreview", "Dispatching onCameraPreviewStreamSizeChanged." });
        this.getCallback().onCameraPreviewStreamSizeChanged();
        final Size previewStreamSize = this.getPreviewStreamSize(Reference.VIEW);
        if (previewStreamSize != null) {
            this.mPreview.setStreamSize(previewStreamSize.getWidth(), previewStreamSize.getHeight());
            this.mPreview.setDrawRotation(0);
            try {
                final Camera$Parameters parameters = this.mCamera.getParameters();
                parameters.setPreviewFormat(17);
                parameters.setPreviewSize(this.mPreviewStreamSize.getWidth(), this.mPreviewStreamSize.getHeight());
                if (this.getMode() == Mode.PICTURE) {
                    parameters.setPictureSize(this.mCaptureSize.getWidth(), this.mCaptureSize.getHeight());
                }
                else {
                    final Size computeCaptureSize = this.computeCaptureSize(Mode.PICTURE);
                    parameters.setPictureSize(computeCaptureSize.getWidth(), computeCaptureSize.getHeight());
                }
                try {
                    this.mCamera.setParameters(parameters);
                    this.mCamera.setPreviewCallbackWithBuffer((Camera$PreviewCallback)null);
                    this.mCamera.setPreviewCallbackWithBuffer((Camera$PreviewCallback)this);
                    this.getFrameManager().setUp(17, this.mPreviewStreamSize, this.getAngles());
                    Camera1Engine.LOG.i(new Object[] { "onStartPreview", "Starting preview with startPreview()." });
                    try {
                        this.mCamera.startPreview();
                        Camera1Engine.LOG.i(new Object[] { "onStartPreview", "Started preview." });
                        return (Task<Void>)Tasks.forResult((Object)null);
                    }
                    catch (final Exception ex) {
                        Camera1Engine.LOG.e(new Object[] { "onStartPreview", "Failed to start preview.", ex });
                        throw new CameraException((Throwable)ex, 2);
                    }
                }
                catch (final Exception ex2) {
                    Camera1Engine.LOG.e(new Object[] { "onStartPreview:", "Failed to set params for camera. Maybe incorrect parameter put in params?" });
                    throw new CameraException((Throwable)ex2, 2);
                }
            }
            catch (final Exception ex3) {
                Camera1Engine.LOG.e(new Object[] { "onStartPreview:", "Failed to get params from camera. Maybe low level problem with camera or camera has already released?" });
                throw new CameraException((Throwable)ex3, 2);
            }
        }
        throw new IllegalStateException("previewStreamSize should not be null at this point.");
    }
    
    protected Task<Void> onStopBind() {
        this.mPreviewStreamSize = null;
        this.mCaptureSize = null;
        try {
            if (this.mPreview.getOutputClass() == SurfaceHolder.class) {
                this.mCamera.setPreviewDisplay((SurfaceHolder)null);
            }
            else {
                if (this.mPreview.getOutputClass() != SurfaceTexture.class) {
                    throw new RuntimeException("Unknown CameraPreview output class.");
                }
                this.mCamera.setPreviewTexture((SurfaceTexture)null);
            }
        }
        catch (final IOException ex) {
            Camera1Engine.LOG.e(new Object[] { "onStopBind", "Could not release surface", ex });
        }
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    protected Task<Void> onStopEngine() {
        Camera1Engine.LOG.i(new Object[] { "onStopEngine:", "About to clean up." });
        this.getOrchestrator().remove("focus reset");
        this.getOrchestrator().remove("focus end");
        if (this.mCamera != null) {
            try {
                Camera1Engine.LOG.i(new Object[] { "onStopEngine:", "Clean up.", "Releasing camera." });
                this.mCamera.release();
                Camera1Engine.LOG.i(new Object[] { "onStopEngine:", "Clean up.", "Released camera." });
            }
            catch (final Exception ex) {
                Camera1Engine.LOG.w(new Object[] { "onStopEngine:", "Clean up.", "Exception while releasing camera.", ex });
            }
            this.mCamera = null;
            this.mCameraOptions = null;
        }
        this.mVideoRecorder = null;
        this.mCameraOptions = null;
        this.mCamera = null;
        Camera1Engine.LOG.w(new Object[] { "onStopEngine:", "Clean up.", "Returning." });
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    protected Task<Void> onStopPreview() {
        Camera1Engine.LOG.i(new Object[] { "onStopPreview:", "Started." });
        if (this.mVideoRecorder != null) {
            this.mVideoRecorder.stop(true);
            this.mVideoRecorder = null;
        }
        this.mPictureRecorder = null;
        this.getFrameManager().release();
        Camera1Engine.LOG.i(new Object[] { "onStopPreview:", "Releasing preview buffers." });
        this.mCamera.setPreviewCallbackWithBuffer((Camera$PreviewCallback)null);
        try {
            Camera1Engine.LOG.i(new Object[] { "onStopPreview:", "Stopping preview." });
            this.mCamera.stopPreview();
            Camera1Engine.LOG.i(new Object[] { "onStopPreview:", "Stopped preview." });
        }
        catch (final Exception ex) {
            Camera1Engine.LOG.e(new Object[] { "stopPreview", "Could not stop preview", ex });
        }
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    @Override
    protected void onTakePicture(final PictureResult$Stub pictureResult$Stub, final boolean b) {
        Camera1Engine.LOG.i(new Object[] { "onTakePicture:", "executing." });
        pictureResult$Stub.rotation = this.getAngles().offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR);
        pictureResult$Stub.size = this.getPictureSize(Reference.OUTPUT);
        (this.mPictureRecorder = (PictureRecorder)new Full1PictureRecorder(pictureResult$Stub, this, this.mCamera)).take();
        Camera1Engine.LOG.i(new Object[] { "onTakePicture:", "executed." });
    }
    
    @Override
    protected void onTakePictureSnapshot(final PictureResult$Stub pictureResult$Stub, final AspectRatio aspectRatio, final boolean b) {
        Camera1Engine.LOG.i(new Object[] { "onTakePictureSnapshot:", "executing." });
        pictureResult$Stub.size = this.getUncroppedSnapshotSize(Reference.OUTPUT);
        if (this.mPreview instanceof RendererCameraPreview && Build$VERSION.SDK_INT >= 19) {
            pictureResult$Stub.rotation = this.getAngles().offset(Reference.VIEW, Reference.OUTPUT, Axis.ABSOLUTE);
            this.mPictureRecorder = (PictureRecorder)new SnapshotGlPictureRecorder(pictureResult$Stub, (PictureRecorder$PictureResultListener)this, (RendererCameraPreview)this.mPreview, aspectRatio, this.getOverlay());
        }
        else {
            pictureResult$Stub.rotation = this.getAngles().offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR);
            this.mPictureRecorder = (PictureRecorder)new Snapshot1PictureRecorder(pictureResult$Stub, this, this.mCamera, aspectRatio);
        }
        this.mPictureRecorder.take();
        Camera1Engine.LOG.i(new Object[] { "onTakePictureSnapshot:", "executed." });
    }
    
    @Override
    protected void onTakeVideo(final VideoResult$Stub videoResult$Stub) {
        videoResult$Stub.rotation = this.getAngles().offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR);
        Size size;
        if (this.getAngles().flip(Reference.SENSOR, Reference.OUTPUT)) {
            size = this.mCaptureSize.flip();
        }
        else {
            size = this.mCaptureSize;
        }
        videoResult$Stub.size = size;
        try {
            this.mCamera.unlock();
            (this.mVideoRecorder = (VideoRecorder)new Full1VideoRecorder(this, this.mCamera, this.mCameraId)).start(videoResult$Stub);
        }
        catch (final Exception ex) {
            this.onVideoResult(null, ex);
        }
    }
    
    @Override
    protected void onTakeVideoSnapshot(final VideoResult$Stub videoResult$Stub, final AspectRatio aspectRatio) {
        if (!(this.mPreview instanceof RendererCameraPreview)) {
            throw new IllegalStateException("Video snapshots are only supported with GL_SURFACE.");
        }
        if (Build$VERSION.SDK_INT < 18) {
            throw new IllegalStateException("Video snapshots are only supported on API 18+.");
        }
        final RendererCameraPreview rendererCameraPreview = (RendererCameraPreview)this.mPreview;
        final Size uncroppedSnapshotSize = this.getUncroppedSnapshotSize(Reference.OUTPUT);
        if (uncroppedSnapshotSize != null) {
            final Rect computeCrop = CropHelper.computeCrop(uncroppedSnapshotSize, aspectRatio);
            videoResult$Stub.size = new Size(computeCrop.width(), computeCrop.height());
            videoResult$Stub.rotation = this.getAngles().offset(Reference.VIEW, Reference.OUTPUT, Axis.ABSOLUTE);
            videoResult$Stub.videoFrameRate = Math.round(this.mPreviewFrameRate);
            Camera1Engine.LOG.i(new Object[] { "onTakeVideoSnapshot", "rotation:", videoResult$Stub.rotation, "size:", videoResult$Stub.size });
            (this.mVideoRecorder = (VideoRecorder)new SnapshotVideoRecorder((CameraEngine)this, rendererCameraPreview, this.getOverlay())).start(videoResult$Stub);
            return;
        }
        throw new IllegalStateException("outputSize should not be null.");
    }
    
    @Override
    public void onVideoResult(final VideoResult$Stub videoResult$Stub, final Exception ex) {
        super.onVideoResult(videoResult$Stub, ex);
        if (videoResult$Stub == null) {
            this.mCamera.lock();
        }
    }
    
    public void setExposureCorrection(final float mExposureCorrectionValue, final float[] array, final PointF[] array2, final boolean b) {
        final float mExposureCorrectionValue2 = this.mExposureCorrectionValue;
        this.mExposureCorrectionValue = mExposureCorrectionValue;
        this.getOrchestrator().trim("exposure correction", 20);
        this.mExposureCorrectionTask = (Task<Void>)this.getOrchestrator().scheduleStateful("exposure correction", CameraState.ENGINE, (Runnable)new Camera1Engine$6(this, mExposureCorrectionValue2, b, array, array2));
    }
    
    public void setFlash(final Flash mFlash) {
        final Flash mFlash2 = this.mFlash;
        this.mFlash = mFlash;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("flash (");
        sb.append((Object)mFlash);
        sb.append(")");
        this.mFlashTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera1Engine$1(this, mFlash2));
    }
    
    public void setFrameProcessingFormat(final int n) {
        this.mFrameProcessingFormat = 17;
    }
    
    public void setHasFrameProcessors(final boolean mHasFrameProcessors) {
        this.mHasFrameProcessors = mHasFrameProcessors;
    }
    
    public void setHdr(final Hdr mHdr) {
        final Hdr mHdr2 = this.mHdr;
        this.mHdr = mHdr;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("hdr (");
        sb.append((Object)mHdr);
        sb.append(")");
        this.mHdrTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera1Engine$4(this, mHdr2));
    }
    
    public void setLocation(final Location mLocation) {
        final Location mLocation2 = this.mLocation;
        this.mLocation = mLocation;
        this.mLocationTask = (Task<Void>)this.getOrchestrator().scheduleStateful("location", CameraState.ENGINE, (Runnable)new Camera1Engine$2(this, mLocation2));
    }
    
    public void setPictureFormat(final PictureFormat mPictureFormat) {
        if (mPictureFormat == PictureFormat.JPEG) {
            this.mPictureFormat = mPictureFormat;
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Unsupported picture format: ");
        sb.append((Object)mPictureFormat);
        throw new UnsupportedOperationException(sb.toString());
    }
    
    public void setPlaySounds(final boolean mPlaySounds) {
        final boolean mPlaySounds2 = this.mPlaySounds;
        this.mPlaySounds = mPlaySounds;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("play sounds (");
        sb.append(mPlaySounds);
        sb.append(")");
        this.mPlaySoundsTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera1Engine$7(this, mPlaySounds2));
    }
    
    public void setPreviewFrameRate(final float mPreviewFrameRate) {
        this.mPreviewFrameRate = mPreviewFrameRate;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("preview fps (");
        sb.append(mPreviewFrameRate);
        sb.append(")");
        this.mPreviewFrameRateTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera1Engine$8(this, mPreviewFrameRate));
    }
    
    public void setWhiteBalance(final WhiteBalance mWhiteBalance) {
        final WhiteBalance mWhiteBalance2 = this.mWhiteBalance;
        this.mWhiteBalance = mWhiteBalance;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("white balance (");
        sb.append((Object)mWhiteBalance);
        sb.append(")");
        this.mWhiteBalanceTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera1Engine$3(this, mWhiteBalance2));
    }
    
    public void setZoom(final float mZoomValue, final PointF[] array, final boolean b) {
        final float mZoomValue2 = this.mZoomValue;
        this.mZoomValue = mZoomValue;
        this.getOrchestrator().trim("zoom", 20);
        this.mZoomTask = (Task<Void>)this.getOrchestrator().scheduleStateful("zoom", CameraState.ENGINE, (Runnable)new Camera1Engine$5(this, mZoomValue2, b, array));
    }
    
    public void startAutoFocus(final Gesture gesture, final MeteringRegions meteringRegions, final PointF pointF) {
        this.getOrchestrator().scheduleStateful("auto focus", CameraState.BIND, (Runnable)new Camera1Engine$11(this, meteringRegions, gesture, pointF));
    }
}
