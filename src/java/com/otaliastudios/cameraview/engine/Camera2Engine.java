package com.otaliastudios.cameraview.engine;

import com.otaliastudios.cameraview.picture.PictureRecorder;
import com.otaliastudios.cameraview.video.VideoRecorder;
import com.otaliastudios.cameraview.gesture.Gesture;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.engine.orchestrator.CameraStateOrchestrator;
import android.graphics.PointF;
import com.otaliastudios.cameraview.video.SnapshotVideoRecorder;
import com.otaliastudios.cameraview.internal.CropHelper;
import com.otaliastudios.cameraview.picture.Snapshot2PictureRecorder;
import com.otaliastudios.cameraview.preview.RendererCameraPreview;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.engine.action.ActionCallback;
import com.otaliastudios.cameraview.engine.action.CompletionCallback;
import com.otaliastudios.cameraview.engine.offset.Axis;
import com.otaliastudios.cameraview.engine.offset.Reference;
import android.hardware.camera2.CameraDevice$StateCallback;
import com.otaliastudios.cameraview.CameraOptions;
import android.hardware.camera2.CameraCaptureSession$StateCallback;
import com.otaliastudios.cameraview.video.Full2VideoRecorder$PrepareException;
import android.graphics.SurfaceTexture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import com.google.android.gms.tasks.Tasks;
import android.view.SurfaceHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import com.otaliastudios.cameraview.picture.Full2PictureRecorder;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.frame.Frame;
import android.media.Image;
import com.otaliastudios.cameraview.frame.ImageFrameManager;
import com.otaliastudios.cameraview.frame.FrameManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.FpsRangeValidator;
import com.otaliastudios.cameraview.controls.Facing;
import java.util.Iterator;
import android.util.Pair;
import com.otaliastudios.cameraview.controls.Control;
import android.util.Rational;
import com.otaliastudios.cameraview.controls.Mode;
import java.util.ArrayList;
import com.otaliastudios.cameraview.engine.action.Actions;
import com.otaliastudios.cameraview.engine.meter.MeterResetAction;
import android.hardware.camera2.CaptureRequest$Key;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import java.util.Comparator;
import java.util.Arrays;
import android.util.Range;
import android.hardware.camera2.CameraCharacteristics$Key;
import android.graphics.Rect;
import com.otaliastudios.cameraview.video.Full2VideoRecorder;
import android.os.Handler;
import com.otaliastudios.cameraview.engine.orchestrator.CameraState;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import android.location.Location;
import com.otaliastudios.cameraview.controls.Flash;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.CameraException;
import android.hardware.camera2.CameraAccessException;
import com.otaliastudios.cameraview.metering.MeteringRegions;
import com.otaliastudios.cameraview.engine.action.LogAction;
import java.util.concurrent.CopyOnWriteArrayList;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession$CaptureCallback;
import android.hardware.camera2.CaptureRequest$Builder;
import com.otaliastudios.cameraview.engine.meter.MeterAction;
import com.otaliastudios.cameraview.engine.mappers.Camera2Mapper;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.TotalCaptureResult;
import com.otaliastudios.cameraview.VideoResult$Stub;
import android.view.Surface;
import android.media.ImageReader;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import com.otaliastudios.cameraview.engine.action.Action;
import java.util.List;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import android.media.ImageReader$OnImageAvailableListener;

public class Camera2Engine extends CameraBaseEngine implements ImageReader$OnImageAvailableListener, ActionHolder
{
    private static final int FRAME_PROCESSING_FORMAT = 35;
    static final long METER_TIMEOUT = 5000L;
    private static final long METER_TIMEOUT_SHORT = 2500L;
    private final List<Action> mActions;
    private CameraDevice mCamera;
    private CameraCharacteristics mCameraCharacteristics;
    private String mCameraId;
    private ImageReader mFrameProcessingReader;
    private Surface mFrameProcessingSurface;
    private VideoResult$Stub mFullVideoPendingStub;
    private TotalCaptureResult mLastRepeatingResult;
    private final CameraManager mManager;
    private final Camera2Mapper mMapper;
    private MeterAction mMeterAction;
    private final boolean mPictureCaptureStopsPreview;
    private ImageReader mPictureReader;
    private Surface mPreviewStreamSurface;
    private CaptureRequest$Builder mRepeatingRequestBuilder;
    private final CameraCaptureSession$CaptureCallback mRepeatingRequestCallback;
    private CameraCaptureSession mSession;
    
    public Camera2Engine(final CameraEngine$Callback cameraEngine$Callback) {
        super(cameraEngine$Callback);
        this.mMapper = Camera2Mapper.get();
        this.mPictureCaptureStopsPreview = false;
        this.mActions = (List<Action>)new CopyOnWriteArrayList();
        this.mRepeatingRequestCallback = (CameraCaptureSession$CaptureCallback)new Camera2Engine$1(this);
        this.mManager = (CameraManager)this.getCallback().getContext().getSystemService("camera");
        new LogAction().start((ActionHolder)this);
    }
    
    private void addRepeatingRequestBuilderSurfaces(final Surface... array) {
        this.mRepeatingRequestBuilder.addTarget(this.mPreviewStreamSurface);
        final Surface mFrameProcessingSurface = this.mFrameProcessingSurface;
        if (mFrameProcessingSurface != null) {
            this.mRepeatingRequestBuilder.addTarget(mFrameProcessingSurface);
        }
        for (final Surface surface : array) {
            if (surface == null) {
                throw new IllegalArgumentException("Should not add a null surface.");
            }
            this.mRepeatingRequestBuilder.addTarget(surface);
        }
    }
    
    private void applyAllParameters(final CaptureRequest$Builder captureRequest$Builder, final CaptureRequest$Builder captureRequest$Builder2) {
        Camera2Engine.LOG.i(new Object[] { "applyAllParameters:", "called for tag", captureRequest$Builder.build().getTag() });
        captureRequest$Builder.set(CaptureRequest.CONTROL_MODE, (Object)1);
        this.applyDefaultFocus(captureRequest$Builder);
        this.applyFlash(captureRequest$Builder, Flash.OFF);
        this.applyLocation(captureRequest$Builder, null);
        this.applyWhiteBalance(captureRequest$Builder, WhiteBalance.AUTO);
        this.applyHdr(captureRequest$Builder, Hdr.OFF);
        this.applyZoom(captureRequest$Builder, 0.0f);
        this.applyExposureCorrection(captureRequest$Builder, 0.0f);
        this.applyPreviewFrameRate(captureRequest$Builder, 0.0f);
        if (captureRequest$Builder2 != null) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_REGIONS, (Object)captureRequest$Builder2.get(CaptureRequest.CONTROL_AF_REGIONS));
            captureRequest$Builder.set(CaptureRequest.CONTROL_AE_REGIONS, (Object)captureRequest$Builder2.get(CaptureRequest.CONTROL_AE_REGIONS));
            captureRequest$Builder.set(CaptureRequest.CONTROL_AWB_REGIONS, (Object)captureRequest$Builder2.get(CaptureRequest.CONTROL_AWB_REGIONS));
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)captureRequest$Builder2.get(CaptureRequest.CONTROL_AF_MODE));
        }
    }
    
    private void applyRepeatingRequestBuilder(final boolean b, final int n) {
        if (this.getState() != CameraState.PREVIEW || this.isChangingState()) {
            if (b) {
                return;
            }
        }
        try {
            this.mSession.setRepeatingRequest(this.mRepeatingRequestBuilder.build(), this.mRepeatingRequestCallback, (Handler)null);
        }
        catch (final IllegalStateException ex) {
            Camera2Engine.LOG.e(new Object[] { "applyRepeatingRequestBuilder: session is invalid!", ex, "checkStarted:", b, "currentThread:", Thread.currentThread().getName(), "state:", this.getState(), "targetState:", this.getTargetState() });
            throw new CameraException(3);
        }
        catch (final CameraAccessException ex2) {
            throw new CameraException((Throwable)ex2, n);
        }
    }
    
    private CameraException createCameraException(final int n) {
        int n3;
        final int n2 = n3 = 1;
        if (n != 1) {
            n3 = n2;
            if (n != 2) {
                n3 = n2;
                if (n != 3) {
                    n3 = n2;
                    if (n != 4) {
                        n3 = n2;
                        if (n != 5) {
                            n3 = 0;
                        }
                    }
                }
            }
        }
        return new CameraException(n3);
    }
    
    private CameraException createCameraException(final CameraAccessException ex) {
        final int reason = ex.getReason();
        final int n = 3;
        if (reason != 1) {
            int n2 = n;
            if (reason == 2) {
                return new CameraException((Throwable)ex, n2);
            }
            n2 = n;
            if (reason == 3) {
                return new CameraException((Throwable)ex, n2);
            }
            if (reason != 4 && reason != 5) {
                n2 = 0;
                return new CameraException((Throwable)ex, n2);
            }
        }
        int n2 = 1;
        return new CameraException((Throwable)ex, n2);
    }
    
    private MeterAction createMeterAction(final MeteringRegions meteringRegions) {
        final MeterAction mMeterAction = this.mMeterAction;
        if (mMeterAction != null) {
            mMeterAction.abort((ActionHolder)this);
        }
        this.applyFocusForMetering(this.mRepeatingRequestBuilder);
        return this.mMeterAction = new MeterAction(this, meteringRegions, meteringRegions == null);
    }
    
    private CaptureRequest$Builder createRepeatingRequestBuilder(final int n) throws CameraAccessException {
        final CaptureRequest$Builder mRepeatingRequestBuilder = this.mRepeatingRequestBuilder;
        (this.mRepeatingRequestBuilder = this.mCamera.createCaptureRequest(n)).setTag((Object)n);
        this.applyAllParameters(this.mRepeatingRequestBuilder, mRepeatingRequestBuilder);
        return this.mRepeatingRequestBuilder;
    }
    
    private void doTakeVideo(final VideoResult$Stub videoResult$Stub) {
        if (this.mVideoRecorder instanceof Full2VideoRecorder) {
            final Full2VideoRecorder full2VideoRecorder = (Full2VideoRecorder)this.mVideoRecorder;
            try {
                this.createRepeatingRequestBuilder(3);
                this.addRepeatingRequestBuilderSurfaces(full2VideoRecorder.getInputSurface());
                this.applyRepeatingRequestBuilder(true, 3);
                this.mVideoRecorder.start(videoResult$Stub);
                return;
            }
            catch (final CameraException ex) {
                this.onVideoResult(null, (Exception)ex);
                throw ex;
            }
            catch (final CameraAccessException ex2) {
                this.onVideoResult(null, (Exception)ex2);
                throw this.createCameraException(ex2);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("doTakeVideo called, but video recorder is not a Full2VideoRecorder! ");
        sb.append((Object)this.mVideoRecorder);
        throw new IllegalStateException(sb.toString());
    }
    
    private Rect getZoomRect(float n, float n2) {
        final Rect rect = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<Rect>)CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, new Rect());
        final int n3 = (int)(rect.width() / n2);
        final int n4 = (int)(rect.height() / n2);
        final int width = rect.width();
        final int height = rect.height();
        final float n5 = (float)(width - n3);
        --n;
        --n2;
        final int n6 = (int)(n5 * n / n2 / 2.0f);
        final int n7 = (int)((height - n4) * n / n2 / 2.0f);
        return new Rect(n6, n7, rect.width() - n6, rect.height() - n7);
    }
    
    private void maybeRestorePreviewTemplateAfterVideo() {
        if ((int)this.mRepeatingRequestBuilder.build().getTag() != this.getRepeatingRequestDefaultTemplate()) {
            try {
                this.createRepeatingRequestBuilder(this.getRepeatingRequestDefaultTemplate());
                this.addRepeatingRequestBuilderSurfaces(new Surface[0]);
                this.applyRepeatingRequestBuilder();
            }
            catch (final CameraAccessException ex) {
                throw this.createCameraException(ex);
            }
        }
    }
    
    private <T> T readCharacteristic(final CameraCharacteristics cameraCharacteristics, final CameraCharacteristics$Key<T> cameraCharacteristics$Key, T t) {
        final Object value = cameraCharacteristics.get((CameraCharacteristics$Key)cameraCharacteristics$Key);
        if (value != null) {
            t = (T)value;
        }
        return t;
    }
    
    private void removeRepeatingRequestBuilderSurfaces() {
        this.mRepeatingRequestBuilder.removeTarget(this.mPreviewStreamSurface);
        final Surface mFrameProcessingSurface = this.mFrameProcessingSurface;
        if (mFrameProcessingSurface != null) {
            this.mRepeatingRequestBuilder.removeTarget(mFrameProcessingSurface);
        }
    }
    
    private void sortFrameRateRanges(final Range<Integer>[] array) {
        Arrays.sort((Object[])array, (Comparator)new Camera2Engine$18(this, this.getPreviewFrameRateExact() && this.mPreviewFrameRate != 0.0f));
    }
    
    private void unlockAndResetMetering() {
        Actions.sequence(new BaseAction[] { new BaseAction(this) {
                final Camera2Engine this$0;
                
                protected void onStart(final ActionHolder actionHolder) {
                    super.onStart(actionHolder);
                    this.this$0.applyDefaultFocus(actionHolder.getBuilder((Action)this));
                    final CaptureRequest$Builder builder = actionHolder.getBuilder((Action)this);
                    final CaptureRequest$Key control_AE_LOCK = CaptureRequest.CONTROL_AE_LOCK;
                    final Boolean value = false;
                    builder.set(control_AE_LOCK, (Object)value);
                    actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AWB_LOCK, (Object)value);
                    actionHolder.applyBuilder((Action)this);
                    this.setState(Integer.MAX_VALUE);
                }
            }, new MeterResetAction() }).start((ActionHolder)this);
    }
    
    public void addAction(final Action action) {
        if (!this.mActions.contains((Object)action)) {
            this.mActions.add((Object)action);
        }
    }
    
    public void applyBuilder(final Action action) {
        this.applyRepeatingRequestBuilder();
    }
    
    public void applyBuilder(final Action action, final CaptureRequest$Builder captureRequest$Builder) throws CameraAccessException {
        if (this.getState() == CameraState.PREVIEW && !this.isChangingState()) {
            this.mSession.capture(captureRequest$Builder.build(), this.mRepeatingRequestCallback, (Handler)null);
        }
    }
    
    protected void applyDefaultFocus(final CaptureRequest$Builder captureRequest$Builder) {
        final CameraCharacteristics$Key control_AF_AVAILABLE_MODES = CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES;
        int i = 0;
        final Integer value = 0;
        final int[] array = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<int[]>)control_AF_AVAILABLE_MODES, new int[0]);
        final ArrayList list = new ArrayList();
        while (i < array.length) {
            ((List)list).add((Object)array[i]);
            ++i;
        }
        if (this.getMode() == Mode.VIDEO && ((List)list).contains((Object)3)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)3);
            return;
        }
        if (((List)list).contains((Object)4)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)4);
            return;
        }
        if (((List)list).contains((Object)1)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)1);
            return;
        }
        if (((List)list).contains((Object)value)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)value);
            captureRequest$Builder.set(CaptureRequest.LENS_FOCUS_DISTANCE, (Object)0.0f);
        }
    }
    
    protected boolean applyExposureCorrection(final CaptureRequest$Builder captureRequest$Builder, final float mExposureCorrectionValue) {
        if (this.mCameraOptions.isExposureCorrectionSupported()) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, (Object)Math.round(this.mExposureCorrectionValue * this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<Rational>)CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP, new Rational(1, 1)).floatValue()));
            return true;
        }
        this.mExposureCorrectionValue = mExposureCorrectionValue;
        return false;
    }
    
    protected boolean applyFlash(final CaptureRequest$Builder captureRequest$Builder, final Flash mFlash) {
        if (this.mCameraOptions.supports((Control)this.mFlash)) {
            final int[] array = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<int[]>)CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES, new int[0]);
            final ArrayList list = new ArrayList();
            for (int length = array.length, i = 0; i < length; ++i) {
                ((List)list).add((Object)array[i]);
            }
            for (final Pair pair : this.mMapper.mapFlash(this.mFlash)) {
                if (((List)list).contains(pair.first)) {
                    Camera2Engine.LOG.i(new Object[] { "applyFlash: setting CONTROL_AE_MODE to", pair.first });
                    Camera2Engine.LOG.i(new Object[] { "applyFlash: setting FLASH_MODE to", pair.second });
                    captureRequest$Builder.set(CaptureRequest.CONTROL_AE_MODE, (Object)pair.first);
                    captureRequest$Builder.set(CaptureRequest.FLASH_MODE, (Object)pair.second);
                    return true;
                }
            }
        }
        this.mFlash = mFlash;
        return false;
    }
    
    protected void applyFocusForMetering(final CaptureRequest$Builder captureRequest$Builder) {
        final CameraCharacteristics$Key control_AF_AVAILABLE_MODES = CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES;
        int i = 0;
        final int[] array = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<int[]>)control_AF_AVAILABLE_MODES, new int[0]);
        final ArrayList list = new ArrayList();
        while (i < array.length) {
            ((List)list).add((Object)array[i]);
            ++i;
        }
        if (((List)list).contains((Object)1)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)1);
            return;
        }
        if (this.getMode() == Mode.VIDEO && ((List)list).contains((Object)3)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)3);
            return;
        }
        if (((List)list).contains((Object)4)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AF_MODE, (Object)4);
        }
    }
    
    protected boolean applyHdr(final CaptureRequest$Builder captureRequest$Builder, final Hdr mHdr) {
        if (this.mCameraOptions.supports((Control)this.mHdr)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_SCENE_MODE, (Object)this.mMapper.mapHdr(this.mHdr));
            return true;
        }
        this.mHdr = mHdr;
        return false;
    }
    
    protected boolean applyLocation(final CaptureRequest$Builder captureRequest$Builder, final Location location) {
        if (this.mLocation != null) {
            captureRequest$Builder.set(CaptureRequest.JPEG_GPS_LOCATION, (Object)this.mLocation);
        }
        return true;
    }
    
    protected boolean applyPreviewFrameRate(final CaptureRequest$Builder captureRequest$Builder, final float mPreviewFrameRate) {
        final Range[] array = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<Range<Integer>[]>)CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES, (Range<Integer>[])new Range[0]);
        this.sortFrameRateRanges((Range<Integer>[])array);
        if (this.mPreviewFrameRate == 0.0f) {
            for (final Range range : this.filterFrameRateRanges((Range<Integer>[])array)) {
                if (range.contains((Comparable)30) || range.contains((Comparable)24)) {
                    captureRequest$Builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, (Object)range);
                    return true;
                }
            }
        }
        else {
            this.mPreviewFrameRate = Math.min(this.mPreviewFrameRate, this.mCameraOptions.getPreviewFrameRateMaxValue());
            this.mPreviewFrameRate = Math.max(this.mPreviewFrameRate, this.mCameraOptions.getPreviewFrameRateMinValue());
            for (final Range range2 : this.filterFrameRateRanges((Range<Integer>[])array)) {
                if (range2.contains((Comparable)Math.round(this.mPreviewFrameRate))) {
                    captureRequest$Builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, (Object)range2);
                    return true;
                }
            }
        }
        this.mPreviewFrameRate = mPreviewFrameRate;
        return false;
    }
    
    protected void applyRepeatingRequestBuilder() {
        this.applyRepeatingRequestBuilder(true, 3);
    }
    
    protected boolean applyWhiteBalance(final CaptureRequest$Builder captureRequest$Builder, final WhiteBalance mWhiteBalance) {
        if (this.mCameraOptions.supports((Control)this.mWhiteBalance)) {
            captureRequest$Builder.set(CaptureRequest.CONTROL_AWB_MODE, (Object)this.mMapper.mapWhiteBalance(this.mWhiteBalance));
            return true;
        }
        this.mWhiteBalance = mWhiteBalance;
        return false;
    }
    
    protected boolean applyZoom(final CaptureRequest$Builder captureRequest$Builder, float floatValue) {
        if (this.mCameraOptions.isZoomSupported()) {
            floatValue = this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<Float>)CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, 1.0f);
            captureRequest$Builder.set(CaptureRequest.SCALER_CROP_REGION, (Object)this.getZoomRect(this.mZoomValue * (floatValue - 1.0f) + 1.0f, floatValue));
            return true;
        }
        this.mZoomValue = floatValue;
        return false;
    }
    
    protected final boolean collectCameraInfo(final Facing p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/otaliastudios/cameraview/engine/Camera2Engine.mMapper:Lcom/otaliastudios/cameraview/engine/mappers/Camera2Mapper;
        //     4: aload_1        
        //     5: invokevirtual   com/otaliastudios/cameraview/engine/mappers/Camera2Mapper.mapFacing:(Lcom/otaliastudios/cameraview/controls/Facing;)I
        //     8: istore_3       
        //     9: aload_0        
        //    10: getfield        com/otaliastudios/cameraview/engine/Camera2Engine.mManager:Landroid/hardware/camera2/CameraManager;
        //    13: invokevirtual   android/hardware/camera2/CameraManager.getCameraIdList:()[Ljava/lang/String;
        //    16: astore          6
        //    18: getstatic       com/otaliastudios/cameraview/engine/Camera2Engine.LOG:Lcom/otaliastudios/cameraview/CameraLogger;
        //    21: bipush          7
        //    23: anewarray       Ljava/lang/Object;
        //    26: dup            
        //    27: iconst_0       
        //    28: ldc_w           "collectCameraInfo"
        //    31: aastore        
        //    32: dup            
        //    33: iconst_1       
        //    34: ldc_w           "Facing:"
        //    37: aastore        
        //    38: dup            
        //    39: iconst_2       
        //    40: aload_1        
        //    41: aastore        
        //    42: dup            
        //    43: iconst_3       
        //    44: ldc_w           "Internal:"
        //    47: aastore        
        //    48: dup            
        //    49: iconst_4       
        //    50: iload_3        
        //    51: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    54: aastore        
        //    55: dup            
        //    56: iconst_5       
        //    57: ldc_w           "Cameras:"
        //    60: aastore        
        //    61: dup            
        //    62: bipush          6
        //    64: aload           6
        //    66: arraylength    
        //    67: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    70: aastore        
        //    71: invokevirtual   com/otaliastudios/cameraview/CameraLogger.i:([Ljava/lang/Object;)Ljava/lang/String;
        //    74: pop            
        //    75: aload           6
        //    77: arraylength    
        //    78: istore          4
        //    80: iconst_0       
        //    81: istore_2       
        //    82: iload_2        
        //    83: iload           4
        //    85: if_icmpge       174
        //    88: aload           6
        //    90: iload_2        
        //    91: aaload         
        //    92: astore          8
        //    94: aload_0        
        //    95: getfield        com/otaliastudios/cameraview/engine/Camera2Engine.mManager:Landroid/hardware/camera2/CameraManager;
        //    98: aload           8
        //   100: invokevirtual   android/hardware/camera2/CameraManager.getCameraCharacteristics:(Ljava/lang/String;)Landroid/hardware/camera2/CameraCharacteristics;
        //   103: astore          7
        //   105: iload_3        
        //   106: aload_0        
        //   107: aload           7
        //   109: getstatic       android/hardware/camera2/CameraCharacteristics.LENS_FACING:Landroid/hardware/camera2/CameraCharacteristics$Key;
        //   112: bipush          -99
        //   114: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   117: invokespecial   com/otaliastudios/cameraview/engine/Camera2Engine.readCharacteristic:(Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)Ljava/lang/Object;
        //   120: checkcast       Ljava/lang/Integer;
        //   123: invokevirtual   java/lang/Integer.intValue:()I
        //   126: if_icmpne       168
        //   129: aload_0        
        //   130: aload           8
        //   132: putfield        com/otaliastudios/cameraview/engine/Camera2Engine.mCameraId:Ljava/lang/String;
        //   135: aload_0        
        //   136: aload           7
        //   138: getstatic       android/hardware/camera2/CameraCharacteristics.SENSOR_ORIENTATION:Landroid/hardware/camera2/CameraCharacteristics$Key;
        //   141: iconst_0       
        //   142: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   145: invokespecial   com/otaliastudios/cameraview/engine/Camera2Engine.readCharacteristic:(Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)Ljava/lang/Object;
        //   148: checkcast       Ljava/lang/Integer;
        //   151: invokevirtual   java/lang/Integer.intValue:()I
        //   154: istore          5
        //   156: aload_0        
        //   157: invokevirtual   com/otaliastudios/cameraview/engine/Camera2Engine.getAngles:()Lcom/otaliastudios/cameraview/engine/offset/Angles;
        //   160: aload_1        
        //   161: iload           5
        //   163: invokevirtual   com/otaliastudios/cameraview/engine/offset/Angles.setSensorOffset:(Lcom/otaliastudios/cameraview/controls/Facing;I)V
        //   166: iconst_1       
        //   167: ireturn        
        //   168: iinc            2, 1
        //   171: goto            82
        //   174: iconst_0       
        //   175: ireturn        
        //   176: astore_1       
        //   177: aload_0        
        //   178: aload_1        
        //   179: invokespecial   com/otaliastudios/cameraview/engine/Camera2Engine.createCameraException:(Landroid/hardware/camera2/CameraAccessException;)Lcom/otaliastudios/cameraview/CameraException;
        //   182: athrow         
        //   183: astore          7
        //   185: goto            168
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                            
        //  -----  -----  -----  -----  ------------------------------------------------
        //  9      18     176    183    Landroid/hardware/camera2/CameraAccessException;
        //  94     166    183    188    Landroid/hardware/camera2/CameraAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected List<Range<Integer>> filterFrameRateRanges(final Range<Integer>[] array) {
        final ArrayList list = new ArrayList();
        final int round = Math.round(this.mCameraOptions.getPreviewFrameRateMinValue());
        final int round2 = Math.round(this.mCameraOptions.getPreviewFrameRateMaxValue());
        for (final Range<Integer> range : array) {
            if (range.contains((Comparable)round) || range.contains((Comparable)round2)) {
                if (FpsRangeValidator.validate((Range)range)) {
                    ((List)list).add((Object)range);
                }
            }
        }
        return (List<Range<Integer>>)list;
    }
    
    public CaptureRequest$Builder getBuilder(final Action action) {
        return this.mRepeatingRequestBuilder;
    }
    
    public CameraCharacteristics getCharacteristics(final Action action) {
        return this.mCameraCharacteristics;
    }
    
    @Override
    protected List<Size> getFrameProcessingAvailableSizes() {
        try {
            final StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)this.mManager.getCameraCharacteristics(this.mCameraId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap != null) {
                final android.util.Size[] outputSizes = streamConfigurationMap.getOutputSizes(this.mFrameProcessingFormat);
                final ArrayList list = new ArrayList(outputSizes.length);
                for (final android.util.Size size : outputSizes) {
                    final Size size2 = new Size(size.getWidth(), size.getHeight());
                    if (!((List)list).contains((Object)size2)) {
                        ((List)list).add((Object)size2);
                    }
                }
                return (List<Size>)list;
            }
            throw new RuntimeException("StreamConfigurationMap is null. Should not happen.");
        }
        catch (final CameraAccessException ex) {
            throw this.createCameraException(ex);
        }
    }
    
    public TotalCaptureResult getLastResult(final Action action) {
        return this.mLastRepeatingResult;
    }
    
    @Override
    protected List<Size> getPreviewStreamAvailableSizes() {
        try {
            final StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)this.mManager.getCameraCharacteristics(this.mCameraId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap != null) {
                final android.util.Size[] outputSizes = streamConfigurationMap.getOutputSizes(this.mPreview.getOutputClass());
                final ArrayList list = new ArrayList(outputSizes.length);
                for (final android.util.Size size : outputSizes) {
                    final Size size2 = new Size(size.getWidth(), size.getHeight());
                    if (!((List)list).contains((Object)size2)) {
                        ((List)list).add((Object)size2);
                    }
                }
                return (List<Size>)list;
            }
            throw new RuntimeException("StreamConfigurationMap is null. Should not happen.");
        }
        catch (final CameraAccessException ex) {
            throw this.createCameraException(ex);
        }
    }
    
    protected int getRepeatingRequestDefaultTemplate() {
        return 1;
    }
    
    @Override
    protected FrameManager instantiateFrameManager(final int n) {
        return (FrameManager)new ImageFrameManager(n);
    }
    
    public void onImageAvailable(final ImageReader imageReader) {
        Camera2Engine.LOG.v(new Object[] { "onImageAvailable:", "trying to acquire Image." });
        Image acquireLatestImage;
        try {
            acquireLatestImage = imageReader.acquireLatestImage();
        }
        catch (final Exception ex) {
            acquireLatestImage = null;
        }
        if (acquireLatestImage == null) {
            Camera2Engine.LOG.w(new Object[] { "onImageAvailable:", "failed to acquire Image!" });
        }
        else if (this.getState() == CameraState.PREVIEW && !this.isChangingState()) {
            final Frame frame = this.getFrameManager().getFrame((Object)acquireLatestImage, System.currentTimeMillis());
            if (frame != null) {
                Camera2Engine.LOG.v(new Object[] { "onImageAvailable:", "Image acquired, dispatching." });
                this.getCallback().dispatchFrame(frame);
            }
            else {
                Camera2Engine.LOG.i(new Object[] { "onImageAvailable:", "Image acquired, but no free frames. DROPPING." });
            }
        }
        else {
            Camera2Engine.LOG.i(new Object[] { "onImageAvailable:", "Image acquired in wrong state. Closing it now." });
            acquireLatestImage.close();
        }
    }
    
    @Override
    public void onPictureResult(final PictureResult$Stub pictureResult$Stub, final Exception ex) {
        final boolean b = this.mPictureRecorder instanceof Full2PictureRecorder;
        super.onPictureResult(pictureResult$Stub, ex);
        if ((b && this.getPictureMetering()) || (!b && this.getPictureSnapshotMetering())) {
            this.getOrchestrator().scheduleStateful("reset metering after picture", CameraState.PREVIEW, (Runnable)new Camera2Engine$9(this));
        }
    }
    
    @Override
    protected void onPreviewStreamSizeChanged() {
        Camera2Engine.LOG.i(new Object[] { "onPreviewStreamSizeChanged:", "Calling restartBind()." });
        this.restartBind();
    }
    
    protected Task<Void> onStartBind() {
        Camera2Engine.LOG.i(new Object[] { "onStartBind:", "Started" });
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mCaptureSize = this.computeCaptureSize();
        this.mPreviewStreamSize = this.computePreviewStreamSize();
        final ArrayList list = new ArrayList();
        final Class outputClass = this.mPreview.getOutputClass();
        final Object output = this.mPreview.getOutput();
        Label_0205: {
            if (outputClass == SurfaceHolder.class) {
                try {
                    Camera2Engine.LOG.i(new Object[] { "onStartBind:", "Waiting on UI thread..." });
                    Tasks.await(Tasks.call((Callable)new Camera2Engine$3(this, output)));
                    this.mPreviewStreamSurface = ((SurfaceHolder)output).getSurface();
                    break Label_0205;
                }
                catch (final InterruptedException taskCompletionSource) {}
                catch (final ExecutionException ex) {}
                throw new CameraException((Throwable)taskCompletionSource, 1);
            }
            if (outputClass != SurfaceTexture.class) {
                throw new RuntimeException("Unknown CameraPreview output class.");
            }
            final SurfaceTexture surfaceTexture = (SurfaceTexture)output;
            surfaceTexture.setDefaultBufferSize(this.mPreviewStreamSize.getWidth(), this.mPreviewStreamSize.getHeight());
            this.mPreviewStreamSurface = new Surface(surfaceTexture);
        }
        ((List)list).add((Object)this.mPreviewStreamSurface);
        if (this.getMode() == Mode.VIDEO && this.mFullVideoPendingStub != null) {
            final Full2VideoRecorder mVideoRecorder = new Full2VideoRecorder(this, this.mCameraId);
            try {
                ((List)list).add((Object)mVideoRecorder.createInputSurface(this.mFullVideoPendingStub));
                this.mVideoRecorder = (VideoRecorder)mVideoRecorder;
            }
            catch (final Full2VideoRecorder$PrepareException ex2) {
                throw new CameraException((Throwable)ex2, 1);
            }
        }
        if (this.getMode() == Mode.PICTURE) {
            final int n = Camera2Engine$24.$SwitchMap$com$otaliastudios$cameraview$controls$PictureFormat[this.mPictureFormat.ordinal()];
            int n2;
            if (n != 1) {
                if (n != 2) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Unknown format:");
                    sb.append((Object)this.mPictureFormat);
                    throw new IllegalArgumentException(sb.toString());
                }
                n2 = 32;
            }
            else {
                n2 = 256;
            }
            final ImageReader instance = ImageReader.newInstance(this.mCaptureSize.getWidth(), this.mCaptureSize.getHeight(), n2, 2);
            this.mPictureReader = instance;
            ((List)list).add((Object)instance.getSurface());
        }
        if (this.hasFrameProcessors()) {
            this.mFrameProcessingSize = this.computeFrameProcessingSize();
            (this.mFrameProcessingReader = ImageReader.newInstance(this.mFrameProcessingSize.getWidth(), this.mFrameProcessingSize.getHeight(), this.mFrameProcessingFormat, this.getFrameProcessingPoolSize() + 1)).setOnImageAvailableListener((ImageReader$OnImageAvailableListener)this, (Handler)null);
            ((List)list).add((Object)(this.mFrameProcessingSurface = this.mFrameProcessingReader.getSurface()));
        }
        else {
            this.mFrameProcessingReader = null;
            this.mFrameProcessingSize = null;
            this.mFrameProcessingSurface = null;
        }
        try {
            this.mCamera.createCaptureSession((List)list, (CameraCaptureSession$StateCallback)new Camera2Engine$4(this, taskCompletionSource), (Handler)null);
            return (Task<Void>)taskCompletionSource.getTask();
        }
        catch (final CameraAccessException ex3) {
            throw this.createCameraException(ex3);
        }
        throw new RuntimeException("Unknown CameraPreview output class.");
    }
    
    protected Task<CameraOptions> onStartEngine() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            this.mManager.openCamera(this.mCameraId, (CameraDevice$StateCallback)new Camera2Engine$2(this, taskCompletionSource), (Handler)null);
            return (Task<CameraOptions>)taskCompletionSource.getTask();
        }
        catch (final CameraAccessException ex) {
            throw this.createCameraException(ex);
        }
    }
    
    protected Task<Void> onStartPreview() {
        Camera2Engine.LOG.i(new Object[] { "onStartPreview:", "Dispatching onCameraPreviewStreamSizeChanged." });
        this.getCallback().onCameraPreviewStreamSizeChanged();
        final Size previewStreamSize = this.getPreviewStreamSize(Reference.VIEW);
        if (previewStreamSize != null) {
            this.mPreview.setStreamSize(previewStreamSize.getWidth(), previewStreamSize.getHeight());
            this.mPreview.setDrawRotation(this.getAngles().offset(Reference.BASE, Reference.VIEW, Axis.ABSOLUTE));
            if (this.hasFrameProcessors()) {
                this.getFrameManager().setUp(this.mFrameProcessingFormat, this.mFrameProcessingSize, this.getAngles());
            }
            Camera2Engine.LOG.i(new Object[] { "onStartPreview:", "Starting preview." });
            this.addRepeatingRequestBuilderSurfaces(new Surface[0]);
            this.applyRepeatingRequestBuilder(false, 2);
            Camera2Engine.LOG.i(new Object[] { "onStartPreview:", "Started preview." });
            final VideoResult$Stub mFullVideoPendingStub = this.mFullVideoPendingStub;
            if (mFullVideoPendingStub != null) {
                this.mFullVideoPendingStub = null;
                this.getOrchestrator().scheduleStateful("do take video", CameraState.PREVIEW, (Runnable)new Camera2Engine$5(this, mFullVideoPendingStub));
            }
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            new BaseAction(this, taskCompletionSource) {
                final Camera2Engine this$0;
                final TaskCompletionSource val$task;
                
                public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
                    super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
                    this.setState(Integer.MAX_VALUE);
                    this.val$task.trySetResult((Object)null);
                }
            }.start((ActionHolder)this);
            return (Task<Void>)taskCompletionSource.getTask();
        }
        throw new IllegalStateException("previewStreamSize should not be null at this point.");
    }
    
    protected Task<Void> onStopBind() {
        Camera2Engine.LOG.i(new Object[] { "onStopBind:", "About to clean up." });
        this.mFrameProcessingSurface = null;
        this.mPreviewStreamSurface = null;
        this.mPreviewStreamSize = null;
        this.mCaptureSize = null;
        this.mFrameProcessingSize = null;
        final ImageReader mFrameProcessingReader = this.mFrameProcessingReader;
        if (mFrameProcessingReader != null) {
            mFrameProcessingReader.close();
            this.mFrameProcessingReader = null;
        }
        final ImageReader mPictureReader = this.mPictureReader;
        if (mPictureReader != null) {
            mPictureReader.close();
            this.mPictureReader = null;
        }
        this.mSession.close();
        this.mSession = null;
        Camera2Engine.LOG.i(new Object[] { "onStopBind:", "Returning." });
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    protected Task<Void> onStopEngine() {
        try {
            Camera2Engine.LOG.i(new Object[] { "onStopEngine:", "Clean up.", "Releasing camera." });
            this.mCamera.close();
            Camera2Engine.LOG.i(new Object[] { "onStopEngine:", "Clean up.", "Released camera." });
        }
        catch (final Exception ex) {
            Camera2Engine.LOG.w(new Object[] { "onStopEngine:", "Clean up.", "Exception while releasing camera.", ex });
        }
        this.mCamera = null;
        Camera2Engine.LOG.i(new Object[] { "onStopEngine:", "Aborting actions." });
        final Iterator iterator = this.mActions.iterator();
        while (iterator.hasNext()) {
            ((Action)iterator.next()).abort((ActionHolder)this);
        }
        this.mCameraCharacteristics = null;
        this.mCameraOptions = null;
        this.mVideoRecorder = null;
        this.mRepeatingRequestBuilder = null;
        Camera2Engine.LOG.w(new Object[] { "onStopEngine:", "Returning." });
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    protected Task<Void> onStopPreview() {
        Camera2Engine.LOG.i(new Object[] { "onStopPreview:", "Started." });
        if (this.mVideoRecorder != null) {
            this.mVideoRecorder.stop(true);
            this.mVideoRecorder = null;
        }
        this.mPictureRecorder = null;
        if (this.hasFrameProcessors()) {
            this.getFrameManager().release();
        }
        this.removeRepeatingRequestBuilderSurfaces();
        this.mLastRepeatingResult = null;
        Camera2Engine.LOG.i(new Object[] { "onStopPreview:", "Returning." });
        return (Task<Void>)Tasks.forResult((Object)null);
    }
    
    @Override
    protected void onTakePicture(final PictureResult$Stub pictureResult$Stub, final boolean b) {
        if (b) {
            Camera2Engine.LOG.i(new Object[] { "onTakePicture:", "doMetering is true. Delaying." });
            final BaseAction timeout = Actions.timeout(2500L, (BaseAction)this.createMeterAction(null));
            ((Action)timeout).addCallback((ActionCallback)new CompletionCallback(this, pictureResult$Stub) {
                final Camera2Engine this$0;
                final PictureResult$Stub val$stub;
                
                protected void onActionCompleted(final Action action) {
                    this.this$0.setPictureMetering(false);
                    this.this$0.takePicture(this.val$stub);
                    this.this$0.setPictureMetering(true);
                }
            });
            ((Action)timeout).start((ActionHolder)this);
            return;
        }
        Camera2Engine.LOG.i(new Object[] { "onTakePicture:", "doMetering is false. Performing." });
        pictureResult$Stub.rotation = this.getAngles().offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR);
        pictureResult$Stub.size = this.getPictureSize(Reference.OUTPUT);
        try {
            final CaptureRequest$Builder captureRequest = this.mCamera.createCaptureRequest(2);
            this.applyAllParameters(captureRequest, this.mRepeatingRequestBuilder);
            (this.mPictureRecorder = (PictureRecorder)new Full2PictureRecorder(pictureResult$Stub, this, captureRequest, this.mPictureReader)).take();
        }
        catch (final CameraAccessException ex) {
            throw this.createCameraException(ex);
        }
    }
    
    @Override
    protected void onTakePictureSnapshot(final PictureResult$Stub pictureResult$Stub, final AspectRatio aspectRatio, final boolean b) {
        if (b) {
            Camera2Engine.LOG.i(new Object[] { "onTakePictureSnapshot:", "doMetering is true. Delaying." });
            final BaseAction timeout = Actions.timeout(2500L, (BaseAction)this.createMeterAction(null));
            ((Action)timeout).addCallback((ActionCallback)new CompletionCallback(this, pictureResult$Stub) {
                final Camera2Engine this$0;
                final PictureResult$Stub val$stub;
                
                protected void onActionCompleted(final Action action) {
                    this.this$0.setPictureSnapshotMetering(false);
                    this.this$0.takePictureSnapshot(this.val$stub);
                    this.this$0.setPictureSnapshotMetering(true);
                }
            });
            ((Action)timeout).start((ActionHolder)this);
        }
        else {
            Camera2Engine.LOG.i(new Object[] { "onTakePictureSnapshot:", "doMetering is false. Performing." });
            if (!(this.mPreview instanceof RendererCameraPreview)) {
                throw new RuntimeException("takePictureSnapshot with Camera2 is only supported with Preview.GL_SURFACE");
            }
            pictureResult$Stub.size = this.getUncroppedSnapshotSize(Reference.OUTPUT);
            pictureResult$Stub.rotation = this.getAngles().offset(Reference.VIEW, Reference.OUTPUT, Axis.ABSOLUTE);
            (this.mPictureRecorder = (PictureRecorder)new Snapshot2PictureRecorder(pictureResult$Stub, this, (RendererCameraPreview)this.mPreview, aspectRatio)).take();
        }
    }
    
    @Override
    protected void onTakeVideo(final VideoResult$Stub mFullVideoPendingStub) {
        Camera2Engine.LOG.i(new Object[] { "onTakeVideo", "called." });
        mFullVideoPendingStub.rotation = this.getAngles().offset(Reference.SENSOR, Reference.OUTPUT, Axis.RELATIVE_TO_SENSOR);
        Size size;
        if (this.getAngles().flip(Reference.SENSOR, Reference.OUTPUT)) {
            size = this.mCaptureSize.flip();
        }
        else {
            size = this.mCaptureSize;
        }
        mFullVideoPendingStub.size = size;
        Camera2Engine.LOG.w(new Object[] { "onTakeVideo", "calling restartBind." });
        this.mFullVideoPendingStub = mFullVideoPendingStub;
        this.restartBind();
    }
    
    @Override
    protected void onTakeVideoSnapshot(final VideoResult$Stub videoResult$Stub, final AspectRatio aspectRatio) {
        if (!(this.mPreview instanceof RendererCameraPreview)) {
            throw new IllegalStateException("Video snapshots are only supported with GL_SURFACE.");
        }
        final RendererCameraPreview rendererCameraPreview = (RendererCameraPreview)this.mPreview;
        final Size uncroppedSnapshotSize = this.getUncroppedSnapshotSize(Reference.OUTPUT);
        if (uncroppedSnapshotSize != null) {
            final Rect computeCrop = CropHelper.computeCrop(uncroppedSnapshotSize, aspectRatio);
            videoResult$Stub.size = new Size(computeCrop.width(), computeCrop.height());
            videoResult$Stub.rotation = this.getAngles().offset(Reference.VIEW, Reference.OUTPUT, Axis.ABSOLUTE);
            videoResult$Stub.videoFrameRate = Math.round(this.mPreviewFrameRate);
            Camera2Engine.LOG.i(new Object[] { "onTakeVideoSnapshot", "rotation:", videoResult$Stub.rotation, "size:", videoResult$Stub.size });
            (this.mVideoRecorder = (VideoRecorder)new SnapshotVideoRecorder((CameraEngine)this, rendererCameraPreview, this.getOverlay())).start(videoResult$Stub);
            return;
        }
        throw new IllegalStateException("outputSize should not be null.");
    }
    
    @Override
    public void onVideoRecordingEnd() {
        super.onVideoRecordingEnd();
        if (!(this.mVideoRecorder instanceof Full2VideoRecorder) || this.readCharacteristic((android.hardware.camera2.CameraCharacteristics$Key<Integer>)CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, -1) != 2) {
            return;
        }
        Camera2Engine.LOG.w(new Object[] { "Applying the Issue549 workaround.", Thread.currentThread() });
        this.maybeRestorePreviewTemplateAfterVideo();
        Camera2Engine.LOG.w(new Object[] { "Applied the Issue549 workaround. Sleeping..." });
        while (true) {
            try {
                Thread.sleep(600L);
                Camera2Engine.LOG.w(new Object[] { "Applied the Issue549 workaround. Slept!" });
            }
            catch (final InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void onVideoResult(final VideoResult$Stub videoResult$Stub, final Exception ex) {
        super.onVideoResult(videoResult$Stub, ex);
        this.getOrchestrator().scheduleStateful("restore preview template", CameraState.BIND, (Runnable)new Camera2Engine$10(this));
    }
    
     <T> T readCharacteristic(final CameraCharacteristics$Key<T> cameraCharacteristics$Key, final T t) {
        return this.readCharacteristic(this.mCameraCharacteristics, cameraCharacteristics$Key, t);
    }
    
    public void removeAction(final Action action) {
        this.mActions.remove((Object)action);
    }
    
    public void setExposureCorrection(final float mExposureCorrectionValue, final float[] array, final PointF[] array2, final boolean b) {
        final float mExposureCorrectionValue2 = this.mExposureCorrectionValue;
        this.mExposureCorrectionValue = mExposureCorrectionValue;
        this.getOrchestrator().trim("exposure correction", 20);
        this.mExposureCorrectionTask = (Task<Void>)this.getOrchestrator().scheduleStateful("exposure correction", CameraState.ENGINE, (Runnable)new Camera2Engine$16(this, mExposureCorrectionValue2, b, mExposureCorrectionValue, array, array2));
    }
    
    public void setFlash(final Flash mFlash) {
        final Flash mFlash2 = this.mFlash;
        this.mFlash = mFlash;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("flash (");
        sb.append((Object)mFlash);
        sb.append(")");
        this.mFlashTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera2Engine$11(this, mFlash2, mFlash));
    }
    
    public void setFrameProcessingFormat(final int n) {
        if (this.mFrameProcessingFormat == 0) {
            this.mFrameProcessingFormat = 35;
        }
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("frame processing format (");
        sb.append(n);
        sb.append(")");
        orchestrator.schedule(sb.toString(), true, (Runnable)new Camera2Engine$21(this, n));
    }
    
    public void setHasFrameProcessors(final boolean b) {
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("has frame processors (");
        sb.append(b);
        sb.append(")");
        orchestrator.schedule(sb.toString(), true, (Runnable)new Camera2Engine$20(this, b));
    }
    
    public void setHdr(final Hdr mHdr) {
        final Hdr mHdr2 = this.mHdr;
        this.mHdr = mHdr;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("hdr (");
        sb.append((Object)mHdr);
        sb.append(")");
        this.mHdrTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera2Engine$14(this, mHdr2));
    }
    
    public void setLocation(final Location mLocation) {
        final Location mLocation2 = this.mLocation;
        this.mLocation = mLocation;
        this.mLocationTask = (Task<Void>)this.getOrchestrator().scheduleStateful("location", CameraState.ENGINE, (Runnable)new Camera2Engine$12(this, mLocation2));
    }
    
    public void setPictureFormat(final PictureFormat mPictureFormat) {
        if (mPictureFormat != this.mPictureFormat) {
            this.mPictureFormat = mPictureFormat;
            final CameraStateOrchestrator orchestrator = this.getOrchestrator();
            final StringBuilder sb = new StringBuilder();
            sb.append("picture format (");
            sb.append((Object)mPictureFormat);
            sb.append(")");
            orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera2Engine$19(this));
        }
    }
    
    public void setPlaySounds(final boolean mPlaySounds) {
        this.mPlaySounds = mPlaySounds;
        this.mPlaySoundsTask = (Task<Void>)Tasks.forResult((Object)null);
    }
    
    public void setPreviewFrameRate(final float mPreviewFrameRate) {
        final float mPreviewFrameRate2 = this.mPreviewFrameRate;
        this.mPreviewFrameRate = mPreviewFrameRate;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("preview fps (");
        sb.append(mPreviewFrameRate);
        sb.append(")");
        this.mPreviewFrameRateTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera2Engine$17(this, mPreviewFrameRate2));
    }
    
    public void setWhiteBalance(final WhiteBalance mWhiteBalance) {
        final WhiteBalance mWhiteBalance2 = this.mWhiteBalance;
        this.mWhiteBalance = mWhiteBalance;
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("white balance (");
        sb.append((Object)mWhiteBalance);
        sb.append(")");
        this.mWhiteBalanceTask = (Task<Void>)orchestrator.scheduleStateful(sb.toString(), CameraState.ENGINE, (Runnable)new Camera2Engine$13(this, mWhiteBalance2));
    }
    
    public void setZoom(final float mZoomValue, final PointF[] array, final boolean b) {
        final float mZoomValue2 = this.mZoomValue;
        this.mZoomValue = mZoomValue;
        this.getOrchestrator().trim("zoom", 20);
        this.mZoomTask = (Task<Void>)this.getOrchestrator().scheduleStateful("zoom", CameraState.ENGINE, (Runnable)new Camera2Engine$15(this, mZoomValue2, b, mZoomValue, array));
    }
    
    public void startAutoFocus(final Gesture gesture, final MeteringRegions meteringRegions, final PointF pointF) {
        final CameraStateOrchestrator orchestrator = this.getOrchestrator();
        final StringBuilder sb = new StringBuilder();
        sb.append("autofocus (");
        sb.append((Object)gesture);
        sb.append(")");
        orchestrator.scheduleStateful(sb.toString(), CameraState.PREVIEW, (Runnable)new Camera2Engine$22(this, gesture, pointF, meteringRegions));
    }
}
