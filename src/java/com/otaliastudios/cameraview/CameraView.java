package com.otaliastudios.cameraview;

import com.otaliastudios.cameraview.frame.Frame;
import android.graphics.RectF;
import com.otaliastudios.cameraview.size.SizeSelector;
import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import com.otaliastudios.cameraview.filter.NoFilter;
import com.otaliastudios.cameraview.markers.Marker;
import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import java.util.Iterator;
import com.otaliastudios.cameraview.preview.SurfaceCameraPreview;
import com.otaliastudios.cameraview.preview.GlCameraPreview;
import com.otaliastudios.cameraview.preview.TextureCameraPreview;
import com.otaliastudios.cameraview.engine.Camera1Engine;
import com.otaliastudios.cameraview.engine.Camera2Engine;
import android.graphics.Rect;
import com.otaliastudios.cameraview.internal.CropHelper;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.engine.offset.Reference;
import android.location.Location;
import com.otaliastudios.cameraview.preview.FilterCameraPreview;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.controls.Grid;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Control;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.OnLifecycleEvent;
import android.os.Build$VERSION;
import android.view.ViewGroup$LayoutParams;
import java.io.FileDescriptor;
import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.content.ContextWrapper;
import android.graphics.PointF;
import com.otaliastudios.cameraview.metering.MeteringRegions;
import com.otaliastudios.cameraview.filter.OneParameterFilter;
import com.otaliastudios.cameraview.filter.TwoParameterFilter;
import com.otaliastudios.cameraview.gesture.GestureFinder;
import com.otaliastudios.cameraview.engine.orchestrator.CameraState;
import android.content.res.TypedArray;
import com.otaliastudios.cameraview.internal.OrientationHelper$Callback;
import android.view.View;
import com.otaliastudios.cameraview.gesture.GestureFinder$Controller;
import android.os.Looper;
import com.otaliastudios.cameraview.filter.FilterParser;
import com.otaliastudios.cameraview.markers.MarkerParser;
import com.otaliastudios.cameraview.gesture.GestureParser;
import com.otaliastudios.cameraview.size.SizeSelectorParser;
import com.otaliastudios.cameraview.controls.ControlParser;
import com.otaliastudios.cameraview.overlay.Overlay;
import com.otaliastudios.cameraview.engine.CameraEngine$Callback;
import android.content.pm.PackageManager$NameNotFoundException;
import com.otaliastudios.cameraview.controls.Audio;
import java.util.concurrent.CopyOnWriteArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Handler;
import com.otaliastudios.cameraview.gesture.TapGestureFinder;
import android.media.MediaActionSound;
import com.otaliastudios.cameraview.gesture.ScrollGestureFinder;
import com.otaliastudios.cameraview.controls.Preview;
import com.otaliastudios.cameraview.gesture.PinchGestureFinder;
import com.otaliastudios.cameraview.filter.Filter;
import com.otaliastudios.cameraview.overlay.OverlayLayout;
import com.otaliastudios.cameraview.internal.OrientationHelper;
import com.otaliastudios.cameraview.markers.MarkerLayout;
import androidx.lifecycle.Lifecycle;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.GridLinesLayout;
import com.otaliastudios.cameraview.gesture.GestureAction;
import com.otaliastudios.cameraview.gesture.Gesture;
import java.util.HashMap;
import com.otaliastudios.cameraview.frame.FrameProcessor;
import java.util.List;
import java.util.concurrent.Executor;
import com.otaliastudios.cameraview.controls.Engine;
import com.otaliastudios.cameraview.preview.CameraPreview;
import com.otaliastudios.cameraview.engine.CameraEngine;
import com.otaliastudios.cameraview.markers.AutoFocusMarker;
import androidx.lifecycle.LifecycleObserver;
import android.widget.FrameLayout;

public class CameraView extends FrameLayout implements LifecycleObserver
{
    static final long DEFAULT_AUTOFOCUS_RESET_DELAY_MILLIS = 3000L;
    static final int DEFAULT_FRAME_PROCESSING_EXECUTORS = 1;
    static final int DEFAULT_FRAME_PROCESSING_POOL_SIZE = 2;
    static final boolean DEFAULT_PICTURE_METERING = true;
    static final boolean DEFAULT_PICTURE_SNAPSHOT_METERING = false;
    static final boolean DEFAULT_PLAY_SOUNDS = true;
    static final boolean DEFAULT_REQUEST_PERMISSIONS = true;
    static final boolean DEFAULT_USE_DEVICE_ORIENTATION = true;
    private static final CameraLogger LOG;
    public static final int PERMISSION_REQUEST_CODE = 16;
    private static final String TAG;
    private int mActiveGestures;
    private AutoFocusMarker mAutoFocusMarker;
    CameraCallbacks mCameraCallbacks;
    private CameraEngine mCameraEngine;
    private CameraPreview mCameraPreview;
    private Engine mEngine;
    private boolean mExperimental;
    private Executor mFrameProcessingExecutor;
    private int mFrameProcessingExecutors;
    List<FrameProcessor> mFrameProcessors;
    private HashMap<Gesture, GestureAction> mGestureMap;
    GridLinesLayout mGridLinesLayout;
    private boolean mInEditor;
    private boolean mKeepScreenOn;
    private Size mLastPreviewStreamSize;
    private Lifecycle mLifecycle;
    List<CameraListener> mListeners;
    MarkerLayout mMarkerLayout;
    private OrientationHelper mOrientationHelper;
    OverlayLayout mOverlayLayout;
    private Filter mPendingFilter;
    PinchGestureFinder mPinchGestureFinder;
    private boolean mPlaySounds;
    private Preview mPreview;
    private boolean mRequestPermissions;
    ScrollGestureFinder mScrollGestureFinder;
    private MediaActionSound mSound;
    TapGestureFinder mTapGestureFinder;
    private Handler mUiHandler;
    private boolean mUseDeviceOrientation;
    
    static {
        LOG = CameraLogger.create(TAG = CameraView.class.getSimpleName());
    }
    
    public CameraView(final Context context) {
        super(context, (AttributeSet)null);
        this.mGestureMap = (HashMap<Gesture, GestureAction>)new HashMap(4);
        this.mListeners = (List<CameraListener>)new CopyOnWriteArrayList();
        this.mFrameProcessors = (List<FrameProcessor>)new CopyOnWriteArrayList();
        this.initialize(context, null);
    }
    
    public CameraView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mGestureMap = (HashMap<Gesture, GestureAction>)new HashMap(4);
        this.mListeners = (List<CameraListener>)new CopyOnWriteArrayList();
        this.mFrameProcessors = (List<FrameProcessor>)new CopyOnWriteArrayList();
        this.initialize(context, set);
    }
    
    private void checkPermissionsManifestOrThrow(final Audio audio) {
        if (audio != Audio.ON && audio != Audio.MONO) {
            if (audio != Audio.STEREO) {
                return;
            }
        }
        try {
            final String[] requestedPermissions = this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 4096).requestedPermissions;
            for (int length = requestedPermissions.length, i = 0; i < length; ++i) {
                if (requestedPermissions[i].equals((Object)"android.permission.RECORD_AUDIO")) {
                    return;
                }
            }
            throw new IllegalStateException(CameraView.LOG.e(new Object[] { "Permission error: when audio is enabled (Audio.ON) the RECORD_AUDIO permission should be added to the app manifest file." }));
        }
        catch (final PackageManager$NameNotFoundException ex) {}
    }
    
    private void clearLifecycleObserver() {
        final Lifecycle mLifecycle = this.mLifecycle;
        if (mLifecycle != null) {
            mLifecycle.removeObserver((LifecycleObserver)this);
            this.mLifecycle = null;
        }
    }
    
    private void doInstantiateEngine() {
        CameraView.LOG.w(new Object[] { "doInstantiateEngine:", "instantiating. engine:", this.mEngine });
        final CameraEngine instantiateCameraEngine = this.instantiateCameraEngine(this.mEngine, (CameraEngine$Callback)this.mCameraCallbacks);
        this.mCameraEngine = instantiateCameraEngine;
        CameraView.LOG.w(new Object[] { "doInstantiateEngine:", "instantiated. engine:", instantiateCameraEngine.getClass().getSimpleName() });
        this.mCameraEngine.setOverlay((Overlay)this.mOverlayLayout);
    }
    
    private void initialize(final Context context, final AttributeSet set) {
        final boolean inEditMode = this.isInEditMode();
        this.mInEditor = inEditMode;
        if (inEditMode) {
            return;
        }
        this.setWillNotDraw(false);
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R$styleable.CameraView, 0, 0);
        final ControlParser controlParser = new ControlParser(context, obtainStyledAttributes);
        final boolean boolean1 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraPlaySounds, true);
        final boolean boolean2 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraUseDeviceOrientation, true);
        this.mExperimental = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraExperimental, false);
        this.mRequestPermissions = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraRequestPermissions, true);
        this.mPreview = controlParser.getPreview();
        this.mEngine = controlParser.getEngine();
        final int color = obtainStyledAttributes.getColor(R$styleable.CameraView_cameraGridColor, GridLinesLayout.DEFAULT_COLOR);
        final long videoMaxSize = (long)obtainStyledAttributes.getFloat(R$styleable.CameraView_cameraVideoMaxSize, 0.0f);
        final int integer = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraVideoMaxDuration, 0);
        final int integer2 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraVideoBitRate, 0);
        final int integer3 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraAudioBitRate, 0);
        final float float1 = obtainStyledAttributes.getFloat(R$styleable.CameraView_cameraPreviewFrameRate, 0.0f);
        final boolean boolean3 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraPreviewFrameRateExact, false);
        final long autoFocusResetDelay = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraAutoFocusResetDelay, 3000);
        final boolean boolean4 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraPictureMetering, true);
        final boolean boolean5 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraPictureSnapshotMetering, false);
        final int integer4 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraSnapshotMaxWidth, 0);
        final int integer5 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraSnapshotMaxHeight, 0);
        final int integer6 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraFrameProcessingMaxWidth, 0);
        final int integer7 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraFrameProcessingMaxHeight, 0);
        final int integer8 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraFrameProcessingFormat, 0);
        final int integer9 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraFrameProcessingPoolSize, 2);
        final int integer10 = obtainStyledAttributes.getInteger(R$styleable.CameraView_cameraFrameProcessingExecutors, 1);
        final boolean boolean6 = obtainStyledAttributes.getBoolean(R$styleable.CameraView_cameraDrawHardwareOverlays, false);
        final SizeSelectorParser sizeSelectorParser = new SizeSelectorParser(obtainStyledAttributes);
        final GestureParser gestureParser = new GestureParser(obtainStyledAttributes);
        final MarkerParser markerParser = new MarkerParser(obtainStyledAttributes);
        final FilterParser filterParser = new FilterParser(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        this.mCameraCallbacks = new CameraCallbacks();
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.mPinchGestureFinder = new PinchGestureFinder((GestureFinder$Controller)this.mCameraCallbacks);
        this.mTapGestureFinder = new TapGestureFinder((GestureFinder$Controller)this.mCameraCallbacks);
        this.mScrollGestureFinder = new ScrollGestureFinder((GestureFinder$Controller)this.mCameraCallbacks);
        this.mGridLinesLayout = new GridLinesLayout(context);
        this.mOverlayLayout = new OverlayLayout(context);
        this.mMarkerLayout = new MarkerLayout(context);
        this.addView((View)this.mGridLinesLayout);
        this.addView((View)this.mMarkerLayout);
        this.addView((View)this.mOverlayLayout);
        this.doInstantiateEngine();
        this.setPlaySounds(boolean1);
        this.setUseDeviceOrientation(boolean2);
        this.setGrid(controlParser.getGrid());
        this.setGridColor(color);
        this.setDrawHardwareOverlays(boolean6);
        this.setFacing(controlParser.getFacing());
        this.setFlash(controlParser.getFlash());
        this.setMode(controlParser.getMode());
        this.setWhiteBalance(controlParser.getWhiteBalance());
        this.setHdr(controlParser.getHdr());
        this.setAudio(controlParser.getAudio());
        this.setAudioBitRate(integer3);
        this.setAudioCodec(controlParser.getAudioCodec());
        this.setPictureSize(sizeSelectorParser.getPictureSizeSelector());
        this.setPictureMetering(boolean4);
        this.setPictureSnapshotMetering(boolean5);
        this.setPictureFormat(controlParser.getPictureFormat());
        this.setVideoSize(sizeSelectorParser.getVideoSizeSelector());
        this.setVideoCodec(controlParser.getVideoCodec());
        this.setVideoMaxSize(videoMaxSize);
        this.setVideoMaxDuration(integer);
        this.setVideoBitRate(integer2);
        this.setAutoFocusResetDelay(autoFocusResetDelay);
        this.setPreviewFrameRateExact(boolean3);
        this.setPreviewFrameRate(float1);
        this.setSnapshotMaxWidth(integer4);
        this.setSnapshotMaxHeight(integer5);
        this.setFrameProcessingMaxWidth(integer6);
        this.setFrameProcessingMaxHeight(integer7);
        this.setFrameProcessingFormat(integer8);
        this.setFrameProcessingPoolSize(integer9);
        this.setFrameProcessingExecutors(integer10);
        this.mapGesture(Gesture.TAP, gestureParser.getTapAction());
        this.mapGesture(Gesture.LONG_TAP, gestureParser.getLongTapAction());
        this.mapGesture(Gesture.PINCH, gestureParser.getPinchAction());
        this.mapGesture(Gesture.SCROLL_HORIZONTAL, gestureParser.getHorizontalScrollAction());
        this.mapGesture(Gesture.SCROLL_VERTICAL, gestureParser.getVerticalScrollAction());
        this.setAutoFocusMarker(markerParser.getAutoFocusMarker());
        this.setFilter(filterParser.getFilter());
        this.mOrientationHelper = new OrientationHelper(context, (OrientationHelper$Callback)this.mCameraCallbacks);
    }
    
    private boolean isClosed() {
        return this.mCameraEngine.getState() == CameraState.OFF && !this.mCameraEngine.isChangingState();
    }
    
    private String ms(final int n) {
        if (n == Integer.MIN_VALUE) {
            return "AT_MOST";
        }
        if (n == 0) {
            return "UNSPECIFIED";
        }
        if (n != 1073741824) {
            return null;
        }
        return "EXACTLY";
    }
    
    private void onGesture(final GestureFinder gestureFinder, final CameraOptions cameraOptions) {
        final Gesture gesture = gestureFinder.getGesture();
        final GestureAction gestureAction = (GestureAction)this.mGestureMap.get((Object)gesture);
        final PointF[] points = gestureFinder.getPoints();
        switch (CameraView$7.$SwitchMap$com$otaliastudios$cameraview$gesture$GestureAction[gestureAction.ordinal()]) {
            case 7: {
                if (!(this.getFilter() instanceof TwoParameterFilter)) {
                    break;
                }
                final TwoParameterFilter twoParameterFilter = (TwoParameterFilter)this.getFilter();
                final float parameter2 = twoParameterFilter.getParameter2();
                final float computeValue = gestureFinder.computeValue(parameter2, 0.0f, 1.0f);
                if (computeValue != parameter2) {
                    twoParameterFilter.setParameter2(computeValue);
                    break;
                }
                break;
            }
            case 6: {
                if (!(this.getFilter() instanceof OneParameterFilter)) {
                    break;
                }
                final OneParameterFilter oneParameterFilter = (OneParameterFilter)this.getFilter();
                final float parameter3 = oneParameterFilter.getParameter1();
                final float computeValue2 = gestureFinder.computeValue(parameter3, 0.0f, 1.0f);
                if (computeValue2 != parameter3) {
                    oneParameterFilter.setParameter1(computeValue2);
                    break;
                }
                break;
            }
            case 5: {
                final float exposureCorrectionValue = this.mCameraEngine.getExposureCorrectionValue();
                final float exposureCorrectionMinValue = cameraOptions.getExposureCorrectionMinValue();
                final float exposureCorrectionMaxValue = cameraOptions.getExposureCorrectionMaxValue();
                final float computeValue3 = gestureFinder.computeValue(exposureCorrectionValue, exposureCorrectionMinValue, exposureCorrectionMaxValue);
                if (computeValue3 != exposureCorrectionValue) {
                    this.mCameraEngine.setExposureCorrection(computeValue3, new float[] { exposureCorrectionMinValue, exposureCorrectionMaxValue }, points, true);
                    break;
                }
                break;
            }
            case 4: {
                final float zoomValue = this.mCameraEngine.getZoomValue();
                final float computeValue4 = gestureFinder.computeValue(zoomValue, 0.0f, 1.0f);
                if (computeValue4 != zoomValue) {
                    this.mCameraEngine.setZoom(computeValue4, points, true);
                    break;
                }
                break;
            }
            case 3: {
                this.mCameraEngine.startAutoFocus(gesture, MeteringRegions.fromPoint(new Size(this.getWidth(), this.getHeight()), points[0]), points[0]);
                break;
            }
            case 2: {
                this.takePicture();
                break;
            }
            case 1: {
                this.takePictureSnapshot();
                break;
            }
        }
    }
    
    private void playSound(final int n) {
        if (this.mPlaySounds) {
            if (this.mSound == null) {
                this.mSound = new MediaActionSound();
            }
            this.mSound.play(n);
        }
    }
    
    private void requestPermissions(final boolean b, final boolean b2) {
        Context context = this.getContext();
        Activity activity = null;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                activity = (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        final ArrayList list = new ArrayList();
        if (b) {
            ((List)list).add((Object)"android.permission.CAMERA");
        }
        if (b2) {
            ((List)list).add((Object)"android.permission.RECORD_AUDIO");
        }
        if (activity != null) {
            activity.requestPermissions((String[])((List)list).toArray((Object[])new String[0]), 16);
        }
    }
    
    private void takeVideo(final File file, final FileDescriptor fileDescriptor) {
        final VideoResult$Stub videoResult$Stub = new VideoResult$Stub();
        if (file != null) {
            this.mCameraEngine.takeVideo(videoResult$Stub, file, null);
        }
        else {
            if (fileDescriptor == null) {
                throw new IllegalStateException("file and fileDescriptor are both null.");
            }
            this.mCameraEngine.takeVideo(videoResult$Stub, null, fileDescriptor);
        }
        this.mUiHandler.post((Runnable)new CameraView$1(this));
    }
    
    private void takeVideo(final File file, final FileDescriptor fileDescriptor, final int videoMaxDuration) {
        this.addCameraListener(new CameraListener(this, this.getVideoMaxDuration()) {
            final CameraView this$0;
            final int val$old;
            
            public void onCameraError(final CameraException ex) {
                super.onCameraError(ex);
                if (ex.getReason() == 5) {
                    this.this$0.setVideoMaxDuration(this.val$old);
                    this.this$0.removeCameraListener(this);
                }
            }
            
            public void onVideoTaken(final VideoResult videoResult) {
                this.this$0.setVideoMaxDuration(this.val$old);
                this.this$0.removeCameraListener(this);
            }
        });
        this.setVideoMaxDuration(videoMaxDuration);
        this.takeVideo(file, fileDescriptor);
    }
    
    public void addCameraListener(final CameraListener cameraListener) {
        this.mListeners.add((Object)cameraListener);
    }
    
    public void addFrameProcessor(final FrameProcessor frameProcessor) {
        if (frameProcessor != null) {
            this.mFrameProcessors.add((Object)frameProcessor);
            if (this.mFrameProcessors.size() == 1) {
                this.mCameraEngine.setHasFrameProcessors(true);
            }
        }
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (!this.mInEditor && this.mOverlayLayout.isOverlay(viewGroup$LayoutParams)) {
            this.mOverlayLayout.addView(view, viewGroup$LayoutParams);
        }
        else {
            super.addView(view, n, viewGroup$LayoutParams);
        }
    }
    
    protected boolean checkPermissions(final Audio audio) {
        this.checkPermissionsManifestOrThrow(audio);
        if (Build$VERSION.SDK_INT < 23) {
            return true;
        }
        final Context context = this.getContext();
        final boolean b = audio == Audio.ON || audio == Audio.MONO || audio == Audio.STEREO;
        final boolean b2 = context.checkSelfPermission("android.permission.CAMERA") != 0;
        final boolean b3 = b && context.checkSelfPermission("android.permission.RECORD_AUDIO") != 0;
        if (!b2 && !b3) {
            return true;
        }
        if (this.mRequestPermissions) {
            this.requestPermissions(b2, b3);
        }
        return false;
    }
    
    public void clearCameraListeners() {
        this.mListeners.clear();
    }
    
    public void clearFrameProcessors() {
        final boolean b = this.mFrameProcessors.size() > 0;
        this.mFrameProcessors.clear();
        if (b) {
            this.mCameraEngine.setHasFrameProcessors(false);
        }
    }
    
    public void clearGesture(final Gesture gesture) {
        this.mapGesture(gesture, GestureAction.NONE);
    }
    
    @OnLifecycleEvent(Lifecycle$Event.ON_PAUSE)
    public void close() {
        if (this.mInEditor) {
            return;
        }
        this.mOrientationHelper.disable();
        this.mCameraEngine.stop(false);
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview != null) {
            mCameraPreview.onPause();
        }
    }
    
    @OnLifecycleEvent(Lifecycle$Event.ON_DESTROY)
    public void destroy() {
        if (this.mInEditor) {
            return;
        }
        this.clearCameraListeners();
        this.clearFrameProcessors();
        this.mCameraEngine.destroy(true);
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview != null) {
            mCameraPreview.onDestroy();
        }
    }
    
    void doInstantiatePreview() {
        CameraView.LOG.w(new Object[] { "doInstantiateEngine:", "instantiating. preview:", this.mPreview });
        final CameraPreview instantiatePreview = this.instantiatePreview(this.mPreview, this.getContext(), (ViewGroup)this);
        this.mCameraPreview = instantiatePreview;
        CameraView.LOG.w(new Object[] { "doInstantiateEngine:", "instantiated. preview:", instantiatePreview.getClass().getSimpleName() });
        this.mCameraEngine.setPreview(this.mCameraPreview);
        final Filter mPendingFilter = this.mPendingFilter;
        if (mPendingFilter != null) {
            this.setFilter(mPendingFilter);
            this.mPendingFilter = null;
        }
    }
    
    public FrameLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        if (!this.mInEditor && this.mOverlayLayout.isOverlay(set)) {
            return (FrameLayout$LayoutParams)this.mOverlayLayout.generateLayoutParams(set);
        }
        return super.generateLayoutParams(set);
    }
    
    public <T extends Control> T get(final Class<T> clazz) {
        if (clazz == Audio.class) {
            return (T)this.getAudio();
        }
        if (clazz == Facing.class) {
            return (T)this.getFacing();
        }
        if (clazz == Flash.class) {
            return (T)this.getFlash();
        }
        if (clazz == Grid.class) {
            return (T)this.getGrid();
        }
        if (clazz == Hdr.class) {
            return (T)this.getHdr();
        }
        if (clazz == Mode.class) {
            return (T)this.getMode();
        }
        if (clazz == WhiteBalance.class) {
            return (T)this.getWhiteBalance();
        }
        if (clazz == VideoCodec.class) {
            return (T)this.getVideoCodec();
        }
        if (clazz == AudioCodec.class) {
            return (T)this.getAudioCodec();
        }
        if (clazz == Preview.class) {
            return (T)this.getPreview();
        }
        if (clazz == Engine.class) {
            return (T)this.getEngine();
        }
        if (clazz == PictureFormat.class) {
            return (T)this.getPictureFormat();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Unknown control class: ");
        sb.append((Object)clazz);
        throw new IllegalArgumentException(sb.toString());
    }
    
    public Audio getAudio() {
        return this.mCameraEngine.getAudio();
    }
    
    public int getAudioBitRate() {
        return this.mCameraEngine.getAudioBitRate();
    }
    
    public AudioCodec getAudioCodec() {
        return this.mCameraEngine.getAudioCodec();
    }
    
    public long getAutoFocusResetDelay() {
        return this.mCameraEngine.getAutoFocusResetDelay();
    }
    
    public CameraOptions getCameraOptions() {
        return this.mCameraEngine.getCameraOptions();
    }
    
    public boolean getDrawHardwareOverlays() {
        return this.mOverlayLayout.getHardwareCanvasEnabled();
    }
    
    public Engine getEngine() {
        return this.mEngine;
    }
    
    public float getExposureCorrection() {
        return this.mCameraEngine.getExposureCorrectionValue();
    }
    
    public Facing getFacing() {
        return this.mCameraEngine.getFacing();
    }
    
    public Filter getFilter() {
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview == null) {
            return this.mPendingFilter;
        }
        if (mCameraPreview instanceof FilterCameraPreview) {
            return ((FilterCameraPreview)mCameraPreview).getCurrentFilter();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Filters are only supported by the GL_SURFACE preview. Current:");
        sb.append((Object)this.mPreview);
        throw new RuntimeException(sb.toString());
    }
    
    public Flash getFlash() {
        return this.mCameraEngine.getFlash();
    }
    
    public int getFrameProcessingExecutors() {
        return this.mFrameProcessingExecutors;
    }
    
    public int getFrameProcessingFormat() {
        return this.mCameraEngine.getFrameProcessingFormat();
    }
    
    public int getFrameProcessingMaxHeight() {
        return this.mCameraEngine.getFrameProcessingMaxHeight();
    }
    
    public int getFrameProcessingMaxWidth() {
        return this.mCameraEngine.getFrameProcessingMaxWidth();
    }
    
    public int getFrameProcessingPoolSize() {
        return this.mCameraEngine.getFrameProcessingPoolSize();
    }
    
    public GestureAction getGestureAction(final Gesture gesture) {
        return (GestureAction)this.mGestureMap.get((Object)gesture);
    }
    
    public Grid getGrid() {
        return this.mGridLinesLayout.getGridMode();
    }
    
    public int getGridColor() {
        return this.mGridLinesLayout.getGridColor();
    }
    
    public Hdr getHdr() {
        return this.mCameraEngine.getHdr();
    }
    
    public Location getLocation() {
        return this.mCameraEngine.getLocation();
    }
    
    public Mode getMode() {
        return this.mCameraEngine.getMode();
    }
    
    public PictureFormat getPictureFormat() {
        return this.mCameraEngine.getPictureFormat();
    }
    
    public boolean getPictureMetering() {
        return this.mCameraEngine.getPictureMetering();
    }
    
    public Size getPictureSize() {
        return this.mCameraEngine.getPictureSize(Reference.OUTPUT);
    }
    
    public boolean getPictureSnapshotMetering() {
        return this.mCameraEngine.getPictureSnapshotMetering();
    }
    
    public boolean getPlaySounds() {
        return this.mPlaySounds;
    }
    
    public Preview getPreview() {
        return this.mPreview;
    }
    
    public float getPreviewFrameRate() {
        return this.mCameraEngine.getPreviewFrameRate();
    }
    
    public boolean getPreviewFrameRateExact() {
        return this.mCameraEngine.getPreviewFrameRateExact();
    }
    
    public int getSnapshotMaxHeight() {
        return this.mCameraEngine.getSnapshotMaxHeight();
    }
    
    public int getSnapshotMaxWidth() {
        return this.mCameraEngine.getSnapshotMaxWidth();
    }
    
    public Size getSnapshotSize() {
        final int width = this.getWidth();
        Size size = null;
        if (width != 0) {
            if (this.getHeight() == 0) {
                size = size;
            }
            else {
                final Size uncroppedSnapshotSize = this.mCameraEngine.getUncroppedSnapshotSize(Reference.VIEW);
                if (uncroppedSnapshotSize == null) {
                    return null;
                }
                final Rect computeCrop = CropHelper.computeCrop(uncroppedSnapshotSize, AspectRatio.of(this.getWidth(), this.getHeight()));
                final Size size2 = size = new Size(computeCrop.width(), computeCrop.height());
                if (this.mCameraEngine.getAngles().flip(Reference.VIEW, Reference.OUTPUT)) {
                    return size2.flip();
                }
            }
        }
        return size;
    }
    
    public boolean getUseDeviceOrientation() {
        return this.mUseDeviceOrientation;
    }
    
    public int getVideoBitRate() {
        return this.mCameraEngine.getVideoBitRate();
    }
    
    public VideoCodec getVideoCodec() {
        return this.mCameraEngine.getVideoCodec();
    }
    
    public int getVideoMaxDuration() {
        return this.mCameraEngine.getVideoMaxDuration();
    }
    
    public long getVideoMaxSize() {
        return this.mCameraEngine.getVideoMaxSize();
    }
    
    public Size getVideoSize() {
        return this.mCameraEngine.getVideoSize(Reference.OUTPUT);
    }
    
    public WhiteBalance getWhiteBalance() {
        return this.mCameraEngine.getWhiteBalance();
    }
    
    public float getZoom() {
        return this.mCameraEngine.getZoomValue();
    }
    
    protected CameraEngine instantiateCameraEngine(final Engine engine, final CameraEngine$Callback cameraEngine$Callback) {
        if (this.mExperimental && engine == Engine.CAMERA2 && Build$VERSION.SDK_INT >= 21) {
            return (CameraEngine)new Camera2Engine(cameraEngine$Callback);
        }
        this.mEngine = Engine.CAMERA1;
        return (CameraEngine)new Camera1Engine(cameraEngine$Callback);
    }
    
    protected CameraPreview instantiatePreview(final Preview preview, final Context context, final ViewGroup viewGroup) {
        final int n = CameraView$7.$SwitchMap$com$otaliastudios$cameraview$controls$Preview[preview.ordinal()];
        if (n != 1) {
            if (n == 2) {
                if (this.isHardwareAccelerated()) {
                    return new TextureCameraPreview(context, viewGroup);
                }
            }
            this.mPreview = Preview.GL_SURFACE;
            return new GlCameraPreview(context, viewGroup);
        }
        return new SurfaceCameraPreview(context, viewGroup);
    }
    
    public boolean isOpened() {
        return this.mCameraEngine.getState().isAtLeast(CameraState.ENGINE) && this.mCameraEngine.getTargetState().isAtLeast(CameraState.ENGINE);
    }
    
    public boolean isTakingPicture() {
        return this.mCameraEngine.isTakingPicture();
    }
    
    public boolean isTakingVideo() {
        return this.mCameraEngine.isTakingVideo();
    }
    
    public boolean mapGesture(final Gesture gesture, GestureAction gestureAction) {
        final GestureAction none = GestureAction.NONE;
        if (gesture.isAssignableTo(gestureAction)) {
            this.mGestureMap.put((Object)gesture, (Object)gestureAction);
            final int n = CameraView$7.$SwitchMap$com$otaliastudios$cameraview$gesture$Gesture[gesture.ordinal()];
            if (n != 1) {
                if (n != 2 && n != 3) {
                    if (n == 4 || n == 5) {
                        this.mScrollGestureFinder.setActive(this.mGestureMap.get((Object)Gesture.SCROLL_HORIZONTAL) != none || this.mGestureMap.get((Object)Gesture.SCROLL_VERTICAL) != none);
                    }
                }
                else {
                    this.mTapGestureFinder.setActive(this.mGestureMap.get((Object)Gesture.TAP) != none || this.mGestureMap.get((Object)Gesture.LONG_TAP) != none);
                }
            }
            else {
                this.mPinchGestureFinder.setActive(this.mGestureMap.get((Object)Gesture.PINCH) != none);
            }
            this.mActiveGestures = 0;
            final Iterator iterator = this.mGestureMap.values().iterator();
            while (iterator.hasNext()) {
                gestureAction = (GestureAction)iterator.next();
                final int mActiveGestures = this.mActiveGestures;
                int n2;
                if (gestureAction == GestureAction.NONE) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                this.mActiveGestures = mActiveGestures + n2;
            }
            return true;
        }
        this.mapGesture(gesture, none);
        return false;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mInEditor) {
            return;
        }
        if (this.mCameraPreview == null) {
            this.doInstantiatePreview();
        }
    }
    
    protected void onDetachedFromWindow() {
        this.mLastPreviewStreamSize = null;
        super.onDetachedFromWindow();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.mActiveGestures > 0;
    }
    
    protected void onMeasure(int size, int size2) {
        if (this.mInEditor) {
            size = View$MeasureSpec.getSize(size);
            size2 = View$MeasureSpec.getSize(size2);
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(size, 1073741824), View$MeasureSpec.makeMeasureSpec(size2, 1073741824));
            return;
        }
        if ((this.mLastPreviewStreamSize = this.mCameraEngine.getPreviewStreamSize(Reference.VIEW)) == null) {
            CameraView.LOG.w(new Object[] { "onMeasure:", "surface is not ready. Calling default behavior." });
            super.onMeasure(size, size2);
            return;
        }
        final int mode = View$MeasureSpec.getMode(size);
        final int mode2 = View$MeasureSpec.getMode(size2);
        int n = View$MeasureSpec.getSize(size);
        int n2 = View$MeasureSpec.getSize(size2);
        final float n3 = (float)this.mLastPreviewStreamSize.getWidth();
        final float n4 = (float)this.mLastPreviewStreamSize.getHeight();
        final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
        int n6;
        int n7;
        if (!this.mCameraPreview.supportsCropping()) {
            int n5;
            if ((n5 = mode) == 1073741824) {
                n5 = Integer.MIN_VALUE;
            }
            n6 = n5;
            if ((n7 = mode2) == 1073741824) {
                n7 = Integer.MIN_VALUE;
                n6 = n5;
            }
        }
        else {
            int n8;
            if ((n8 = mode) == Integer.MIN_VALUE) {
                n8 = mode;
                if (layoutParams.width == -1) {
                    n8 = 1073741824;
                }
            }
            n6 = n8;
            if ((n7 = mode2) == Integer.MIN_VALUE) {
                n6 = n8;
                n7 = mode2;
                if (layoutParams.height == -1) {
                    n7 = 1073741824;
                    n6 = n8;
                }
            }
        }
        final CameraLogger log = CameraView.LOG;
        final StringBuilder sb = new StringBuilder();
        sb.append("requested dimensions are (");
        sb.append(n);
        sb.append("[");
        sb.append(this.ms(n6));
        sb.append("]x");
        sb.append(n2);
        sb.append("[");
        sb.append(this.ms(n7));
        sb.append("])");
        log.i(new Object[] { "onMeasure:", sb.toString() });
        final CameraLogger log2 = CameraView.LOG;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("(");
        sb2.append(n3);
        sb2.append("x");
        sb2.append(n4);
        sb2.append(")");
        log2.i(new Object[] { "onMeasure:", "previewSize is", sb2.toString() });
        if (n6 == 1073741824 && n7 == 1073741824) {
            final CameraLogger log3 = CameraView.LOG;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("(");
            sb3.append(n);
            sb3.append("x");
            sb3.append(n2);
            sb3.append(")");
            log3.i(new Object[] { "onMeasure:", "both are MATCH_PARENT or fixed value. We adapt.", "This means CROP_CENTER.", sb3.toString() });
            super.onMeasure(size, size2);
            return;
        }
        if (n6 == 0 && n7 == 0) {
            final CameraLogger log4 = CameraView.LOG;
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("(");
            sb4.append(n3);
            sb4.append("x");
            sb4.append(n4);
            sb4.append(")");
            log4.i(new Object[] { "onMeasure:", "both are completely free.", "We respect that and extend to the whole preview size.", sb4.toString() });
            super.onMeasure(View$MeasureSpec.makeMeasureSpec((int)n3, 1073741824), View$MeasureSpec.makeMeasureSpec((int)n4, 1073741824));
            return;
        }
        final float n9 = n4 / n3;
        if (n6 == 0 || n7 == 0) {
            if (n6 == 0) {
                size = 1;
            }
            else {
                size = 0;
            }
            if (size != 0) {
                n = Math.round(n2 / n9);
            }
            else {
                n2 = Math.round(n * n9);
            }
            final CameraLogger log5 = CameraView.LOG;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("(");
            sb5.append(n);
            sb5.append("x");
            sb5.append(n2);
            sb5.append(")");
            log5.i(new Object[] { "onMeasure:", "one dimension was free, we adapted it to fit the ratio.", sb5.toString() });
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), View$MeasureSpec.makeMeasureSpec(n2, 1073741824));
            return;
        }
        if (n6 != 1073741824 && n7 != 1073741824) {
            final float n10 = (float)n2;
            final float n11 = (float)n;
            if (n10 / n11 >= n9) {
                n2 = Math.round(n11 * n9);
            }
            else {
                n = Math.round(n10 / n9);
            }
            final CameraLogger log6 = CameraView.LOG;
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("(");
            sb6.append(n);
            sb6.append("x");
            sb6.append(n2);
            sb6.append(")");
            log6.i(new Object[] { "onMeasure:", "both dimension were AT_MOST.", "We fit the preview aspect ratio.", sb6.toString() });
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), View$MeasureSpec.makeMeasureSpec(n2, 1073741824));
            return;
        }
        if (n6 == Integer.MIN_VALUE) {
            size = 1;
        }
        else {
            size = 0;
        }
        if (size != 0) {
            n = Math.min(Math.round(n2 / n9), n);
        }
        else {
            n2 = Math.min(Math.round(n * n9), n2);
        }
        final CameraLogger log7 = CameraView.LOG;
        final StringBuilder sb7 = new StringBuilder();
        sb7.append("(");
        sb7.append(n);
        sb7.append("x");
        sb7.append(n2);
        sb7.append(")");
        log7.i(new Object[] { "onMeasure:", "one dimension was EXACTLY, another AT_MOST.", "We have TRIED to fit the aspect ratio, but it's not guaranteed.", sb7.toString() });
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), View$MeasureSpec.makeMeasureSpec(n2, 1073741824));
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (!this.isOpened()) {
            return true;
        }
        final CameraOptions cameraOptions = this.mCameraEngine.getCameraOptions();
        if (cameraOptions != null) {
            if (this.mPinchGestureFinder.onTouchEvent(motionEvent)) {
                CameraView.LOG.i(new Object[] { "onTouchEvent", "pinch!" });
                this.onGesture(this.mPinchGestureFinder, cameraOptions);
            }
            else if (this.mScrollGestureFinder.onTouchEvent(motionEvent)) {
                CameraView.LOG.i(new Object[] { "onTouchEvent", "scroll!" });
                this.onGesture(this.mScrollGestureFinder, cameraOptions);
            }
            else if (this.mTapGestureFinder.onTouchEvent(motionEvent)) {
                CameraView.LOG.i(new Object[] { "onTouchEvent", "tap!" });
                this.onGesture(this.mTapGestureFinder, cameraOptions);
            }
            return true;
        }
        throw new IllegalStateException("Options should not be null here.");
    }
    
    @OnLifecycleEvent(Lifecycle$Event.ON_RESUME)
    public void open() {
        if (this.mInEditor) {
            return;
        }
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview != null) {
            mCameraPreview.onResume();
        }
        if (this.checkPermissions(this.getAudio())) {
            this.mOrientationHelper.enable();
            this.mCameraEngine.getAngles().setDisplayOffset(this.mOrientationHelper.getLastDisplayOffset());
            this.mCameraEngine.start();
        }
    }
    
    public void removeCameraListener(final CameraListener cameraListener) {
        this.mListeners.remove((Object)cameraListener);
    }
    
    public void removeFrameProcessor(final FrameProcessor frameProcessor) {
        if (frameProcessor != null) {
            this.mFrameProcessors.remove((Object)frameProcessor);
            if (this.mFrameProcessors.size() == 0) {
                this.mCameraEngine.setHasFrameProcessors(false);
            }
        }
    }
    
    public void removeView(final View view) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!this.mInEditor && layoutParams != null && this.mOverlayLayout.isOverlay(layoutParams)) {
            this.mOverlayLayout.removeView(view);
        }
        else {
            super.removeView(view);
        }
    }
    
    public void set(final Control control) {
        if (control instanceof Audio) {
            this.setAudio((Audio)control);
        }
        else if (control instanceof Facing) {
            this.setFacing((Facing)control);
        }
        else if (control instanceof Flash) {
            this.setFlash((Flash)control);
        }
        else if (control instanceof Grid) {
            this.setGrid((Grid)control);
        }
        else if (control instanceof Hdr) {
            this.setHdr((Hdr)control);
        }
        else if (control instanceof Mode) {
            this.setMode((Mode)control);
        }
        else if (control instanceof WhiteBalance) {
            this.setWhiteBalance((WhiteBalance)control);
        }
        else if (control instanceof VideoCodec) {
            this.setVideoCodec((VideoCodec)control);
        }
        else if (control instanceof AudioCodec) {
            this.setAudioCodec((AudioCodec)control);
        }
        else if (control instanceof Preview) {
            this.setPreview((Preview)control);
        }
        else if (control instanceof Engine) {
            this.setEngine((Engine)control);
        }
        else if (control instanceof PictureFormat) {
            this.setPictureFormat((PictureFormat)control);
        }
    }
    
    public void setAudio(final Audio audio) {
        if (audio != this.getAudio() && !this.isClosed()) {
            if (this.checkPermissions(audio)) {
                this.mCameraEngine.setAudio(audio);
            }
            else {
                this.close();
            }
        }
        else {
            this.mCameraEngine.setAudio(audio);
        }
    }
    
    public void setAudioBitRate(final int audioBitRate) {
        this.mCameraEngine.setAudioBitRate(audioBitRate);
    }
    
    public void setAudioCodec(final AudioCodec audioCodec) {
        this.mCameraEngine.setAudioCodec(audioCodec);
    }
    
    public void setAutoFocusMarker(final AutoFocusMarker mAutoFocusMarker) {
        this.mAutoFocusMarker = mAutoFocusMarker;
        this.mMarkerLayout.onMarker(1, (Marker)mAutoFocusMarker);
    }
    
    public void setAutoFocusResetDelay(final long autoFocusResetDelay) {
        this.mCameraEngine.setAutoFocusResetDelay(autoFocusResetDelay);
    }
    
    public void setDrawHardwareOverlays(final boolean hardwareCanvasEnabled) {
        this.mOverlayLayout.setHardwareCanvasEnabled(hardwareCanvasEnabled);
    }
    
    public void setEngine(final Engine mEngine) {
        if (!this.isClosed()) {
            return;
        }
        this.mEngine = mEngine;
        final CameraEngine mCameraEngine = this.mCameraEngine;
        this.doInstantiateEngine();
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview != null) {
            this.mCameraEngine.setPreview(mCameraPreview);
        }
        this.setFacing(mCameraEngine.getFacing());
        this.setFlash(mCameraEngine.getFlash());
        this.setMode(mCameraEngine.getMode());
        this.setWhiteBalance(mCameraEngine.getWhiteBalance());
        this.setHdr(mCameraEngine.getHdr());
        this.setAudio(mCameraEngine.getAudio());
        this.setAudioBitRate(mCameraEngine.getAudioBitRate());
        this.setAudioCodec(mCameraEngine.getAudioCodec());
        this.setPictureSize(mCameraEngine.getPictureSizeSelector());
        this.setPictureFormat(mCameraEngine.getPictureFormat());
        this.setVideoSize(mCameraEngine.getVideoSizeSelector());
        this.setVideoCodec(mCameraEngine.getVideoCodec());
        this.setVideoMaxSize(mCameraEngine.getVideoMaxSize());
        this.setVideoMaxDuration(mCameraEngine.getVideoMaxDuration());
        this.setVideoBitRate(mCameraEngine.getVideoBitRate());
        this.setAutoFocusResetDelay(mCameraEngine.getAutoFocusResetDelay());
        this.setPreviewFrameRate(mCameraEngine.getPreviewFrameRate());
        this.setPreviewFrameRateExact(mCameraEngine.getPreviewFrameRateExact());
        this.setSnapshotMaxWidth(mCameraEngine.getSnapshotMaxWidth());
        this.setSnapshotMaxHeight(mCameraEngine.getSnapshotMaxHeight());
        this.setFrameProcessingMaxWidth(mCameraEngine.getFrameProcessingMaxWidth());
        this.setFrameProcessingMaxHeight(mCameraEngine.getFrameProcessingMaxHeight());
        this.setFrameProcessingFormat(0);
        this.setFrameProcessingPoolSize(mCameraEngine.getFrameProcessingPoolSize());
        this.mCameraEngine.setHasFrameProcessors(this.mFrameProcessors.isEmpty() ^ true);
    }
    
    public void setExperimental(final boolean mExperimental) {
        this.mExperimental = mExperimental;
    }
    
    public void setExposureCorrection(float n) {
        final CameraOptions cameraOptions = this.getCameraOptions();
        if (cameraOptions != null) {
            final float exposureCorrectionMinValue = cameraOptions.getExposureCorrectionMinValue();
            final float exposureCorrectionMaxValue = cameraOptions.getExposureCorrectionMaxValue();
            float n2 = n;
            if (n < exposureCorrectionMinValue) {
                n2 = exposureCorrectionMinValue;
            }
            n = n2;
            if (n2 > exposureCorrectionMaxValue) {
                n = exposureCorrectionMaxValue;
            }
            this.mCameraEngine.setExposureCorrection(n, new float[] { exposureCorrectionMinValue, exposureCorrectionMaxValue }, null, false);
        }
    }
    
    public void setFacing(final Facing facing) {
        this.mCameraEngine.setFacing(facing);
    }
    
    public void setFilter(final Filter filter) {
        final CameraPreview mCameraPreview = this.mCameraPreview;
        if (mCameraPreview == null) {
            this.mPendingFilter = filter;
        }
        else {
            final boolean b = filter instanceof NoFilter;
            final boolean b2 = mCameraPreview instanceof FilterCameraPreview;
            if (!b && !b2) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Filters are only supported by the GL_SURFACE preview. Current preview:");
                sb.append((Object)this.mPreview);
                throw new RuntimeException(sb.toString());
            }
            if (b2) {
                ((FilterCameraPreview)this.mCameraPreview).setFilter(filter);
            }
        }
    }
    
    public void setFlash(final Flash flash) {
        this.mCameraEngine.setFlash(flash);
    }
    
    public void setFrameProcessingExecutors(final int mFrameProcessingExecutors) {
        if (mFrameProcessingExecutors >= 1) {
            this.mFrameProcessingExecutors = mFrameProcessingExecutors;
            final ThreadPoolExecutor mFrameProcessingExecutor = new ThreadPoolExecutor(mFrameProcessingExecutors, mFrameProcessingExecutors, 4L, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue(), (ThreadFactory)new CameraView$6(this));
            mFrameProcessingExecutor.allowCoreThreadTimeOut(true);
            this.mFrameProcessingExecutor = (Executor)mFrameProcessingExecutor;
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Need at least 1 executor, got ");
        sb.append(mFrameProcessingExecutors);
        throw new IllegalArgumentException(sb.toString());
    }
    
    public void setFrameProcessingFormat(final int frameProcessingFormat) {
        this.mCameraEngine.setFrameProcessingFormat(frameProcessingFormat);
    }
    
    public void setFrameProcessingMaxHeight(final int frameProcessingMaxHeight) {
        this.mCameraEngine.setFrameProcessingMaxHeight(frameProcessingMaxHeight);
    }
    
    public void setFrameProcessingMaxWidth(final int frameProcessingMaxWidth) {
        this.mCameraEngine.setFrameProcessingMaxWidth(frameProcessingMaxWidth);
    }
    
    public void setFrameProcessingPoolSize(final int frameProcessingPoolSize) {
        this.mCameraEngine.setFrameProcessingPoolSize(frameProcessingPoolSize);
    }
    
    public void setGrid(final Grid gridMode) {
        this.mGridLinesLayout.setGridMode(gridMode);
    }
    
    public void setGridColor(final int gridColor) {
        this.mGridLinesLayout.setGridColor(gridColor);
    }
    
    public void setHdr(final Hdr hdr) {
        this.mCameraEngine.setHdr(hdr);
    }
    
    public void setLifecycleOwner(final LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner == null) {
            this.clearLifecycleObserver();
        }
        else {
            this.clearLifecycleObserver();
            (this.mLifecycle = lifecycleOwner.getLifecycle()).addObserver((LifecycleObserver)this);
        }
    }
    
    public void setLocation(final double latitude, final double longitude) {
        final Location location = new Location("Unknown");
        location.setTime(System.currentTimeMillis());
        location.setAltitude(0.0);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        this.mCameraEngine.setLocation(location);
    }
    
    public void setLocation(final Location location) {
        this.mCameraEngine.setLocation(location);
    }
    
    public void setMode(final Mode mode) {
        this.mCameraEngine.setMode(mode);
    }
    
    public void setPictureFormat(final PictureFormat pictureFormat) {
        this.mCameraEngine.setPictureFormat(pictureFormat);
    }
    
    public void setPictureMetering(final boolean pictureMetering) {
        this.mCameraEngine.setPictureMetering(pictureMetering);
    }
    
    public void setPictureSize(final SizeSelector pictureSizeSelector) {
        this.mCameraEngine.setPictureSizeSelector(pictureSizeSelector);
    }
    
    public void setPictureSnapshotMetering(final boolean pictureSnapshotMetering) {
        this.mCameraEngine.setPictureSnapshotMetering(pictureSnapshotMetering);
    }
    
    public void setPlaySounds(final boolean playSounds) {
        this.mPlaySounds = (playSounds && Build$VERSION.SDK_INT >= 16);
        this.mCameraEngine.setPlaySounds(playSounds);
    }
    
    public void setPreview(final Preview mPreview) {
        final Preview mPreview2 = this.mPreview;
        final int n = 1;
        if (mPreview != mPreview2) {
            this.mPreview = mPreview;
            int n2;
            if (this.getWindowToken() != null) {
                n2 = n;
            }
            else {
                n2 = 0;
            }
            if (n2 == 0) {
                final CameraPreview mCameraPreview = this.mCameraPreview;
                if (mCameraPreview != null) {
                    mCameraPreview.onDestroy();
                    this.mCameraPreview = null;
                }
            }
        }
    }
    
    public void setPreviewFrameRate(final float previewFrameRate) {
        this.mCameraEngine.setPreviewFrameRate(previewFrameRate);
    }
    
    public void setPreviewFrameRateExact(final boolean previewFrameRateExact) {
        this.mCameraEngine.setPreviewFrameRateExact(previewFrameRateExact);
    }
    
    public void setPreviewStreamSize(final SizeSelector previewStreamSizeSelector) {
        this.mCameraEngine.setPreviewStreamSizeSelector(previewStreamSizeSelector);
    }
    
    public void setRequestPermissions(final boolean mRequestPermissions) {
        this.mRequestPermissions = mRequestPermissions;
    }
    
    public void setSnapshotMaxHeight(final int snapshotMaxHeight) {
        this.mCameraEngine.setSnapshotMaxHeight(snapshotMaxHeight);
    }
    
    public void setSnapshotMaxWidth(final int snapshotMaxWidth) {
        this.mCameraEngine.setSnapshotMaxWidth(snapshotMaxWidth);
    }
    
    public void setUseDeviceOrientation(final boolean mUseDeviceOrientation) {
        this.mUseDeviceOrientation = mUseDeviceOrientation;
    }
    
    public void setVideoBitRate(final int videoBitRate) {
        this.mCameraEngine.setVideoBitRate(videoBitRate);
    }
    
    public void setVideoCodec(final VideoCodec videoCodec) {
        this.mCameraEngine.setVideoCodec(videoCodec);
    }
    
    public void setVideoMaxDuration(final int videoMaxDuration) {
        this.mCameraEngine.setVideoMaxDuration(videoMaxDuration);
    }
    
    public void setVideoMaxSize(final long videoMaxSize) {
        this.mCameraEngine.setVideoMaxSize(videoMaxSize);
    }
    
    public void setVideoSize(final SizeSelector videoSizeSelector) {
        this.mCameraEngine.setVideoSizeSelector(videoSizeSelector);
    }
    
    public void setWhiteBalance(final WhiteBalance whiteBalance) {
        this.mCameraEngine.setWhiteBalance(whiteBalance);
    }
    
    public void setZoom(float n) {
        float n2 = n;
        if (n < 0.0f) {
            n2 = 0.0f;
        }
        n = n2;
        if (n2 > 1.0f) {
            n = 1.0f;
        }
        this.mCameraEngine.setZoom(n, null, false);
    }
    
    public void startAutoFocus(final float n, final float n2) {
        if (n < 0.0f || n > this.getWidth()) {
            throw new IllegalArgumentException("x should be >= 0 and <= getWidth()");
        }
        if (n2 >= 0.0f && n2 <= this.getHeight()) {
            final Size size = new Size(this.getWidth(), this.getHeight());
            final PointF pointF = new PointF(n, n2);
            this.mCameraEngine.startAutoFocus(null, MeteringRegions.fromPoint(size, pointF), pointF);
            return;
        }
        throw new IllegalArgumentException("y should be >= 0 and <= getHeight()");
    }
    
    public void startAutoFocus(final RectF rectF) {
        if (new RectF(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight()).contains(rectF)) {
            this.mCameraEngine.startAutoFocus(null, MeteringRegions.fromArea(new Size(this.getWidth(), this.getHeight()), rectF), new PointF(rectF.centerX(), rectF.centerY()));
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Region is out of view bounds! ");
        sb.append((Object)rectF);
        throw new IllegalArgumentException(sb.toString());
    }
    
    public void stopVideo() {
        this.mCameraEngine.stopVideo();
        this.mUiHandler.post((Runnable)new CameraView$5(this));
    }
    
    public void takePicture() {
        this.mCameraEngine.takePicture(new PictureResult$Stub());
    }
    
    public void takePictureSnapshot() {
        this.mCameraEngine.takePictureSnapshot(new PictureResult$Stub());
    }
    
    public void takeVideo(final File file) {
        this.takeVideo(file, null);
    }
    
    public void takeVideo(final File file, final int n) {
        this.takeVideo(file, null, n);
    }
    
    public void takeVideo(final FileDescriptor fileDescriptor) {
        this.takeVideo(null, fileDescriptor);
    }
    
    public void takeVideo(final FileDescriptor fileDescriptor, final int n) {
        this.takeVideo(null, fileDescriptor, n);
    }
    
    public void takeVideoSnapshot(final File file) {
        this.mCameraEngine.takeVideoSnapshot(new VideoResult$Stub(), file);
        this.mUiHandler.post((Runnable)new CameraView$2(this));
    }
    
    public void takeVideoSnapshot(final File file, final int videoMaxDuration) {
        this.addCameraListener(new CameraListener(this, this.getVideoMaxDuration()) {
            final CameraView this$0;
            final int val$old;
            
            public void onCameraError(final CameraException ex) {
                super.onCameraError(ex);
                if (ex.getReason() == 5) {
                    this.this$0.setVideoMaxDuration(this.val$old);
                    this.this$0.removeCameraListener(this);
                }
            }
            
            public void onVideoTaken(final VideoResult videoResult) {
                this.this$0.setVideoMaxDuration(this.val$old);
                this.this$0.removeCameraListener(this);
            }
        });
        this.setVideoMaxDuration(videoMaxDuration);
        this.takeVideoSnapshot(file);
    }
    
    public Facing toggleFacing() {
        final int n = CameraView$7.$SwitchMap$com$otaliastudios$cameraview$controls$Facing[this.mCameraEngine.getFacing().ordinal()];
        if (n != 1) {
            if (n == 2) {
                this.setFacing(Facing.BACK);
            }
        }
        else {
            this.setFacing(Facing.FRONT);
        }
        return this.mCameraEngine.getFacing();
    }
    
    class CameraCallbacks implements CameraEngine$Callback, OrientationHelper$Callback, GestureFinder$Controller
    {
        private final CameraLogger LOG;
        private final String TAG;
        final CameraView this$0;
        
        CameraCallbacks(final CameraView this$0) {
            this.this$0 = this$0;
            final String simpleName = CameraCallbacks.class.getSimpleName();
            this.TAG = simpleName;
            this.LOG = CameraLogger.create(simpleName);
        }
        
        public void dispatchError(final CameraException ex) {
            this.LOG.i(new Object[] { "dispatchError", ex });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$13(this, ex));
        }
        
        public void dispatchFrame(final Frame frame) {
            this.LOG.v(new Object[] { "dispatchFrame:", frame.getTime(), "processors:", this.this$0.mFrameProcessors.size() });
            if (this.this$0.mFrameProcessors.isEmpty()) {
                frame.release();
            }
            else {
                this.this$0.mFrameProcessingExecutor.execute((Runnable)new CameraView$CameraCallbacks$12(this, frame));
            }
        }
        
        public void dispatchOnCameraClosed() {
            this.LOG.i(new Object[] { "dispatchOnCameraClosed" });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$2(this));
        }
        
        public void dispatchOnCameraOpened(final CameraOptions cameraOptions) {
            this.LOG.i(new Object[] { "dispatchOnCameraOpened", cameraOptions });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$1(this, cameraOptions));
        }
        
        public void dispatchOnExposureCorrectionChanged(final float n, final float[] array, final PointF[] array2) {
            this.LOG.i(new Object[] { "dispatchOnExposureCorrectionChanged", n });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$11(this, n, array, array2));
        }
        
        public void dispatchOnFocusEnd(final Gesture gesture, final boolean b, final PointF pointF) {
            this.LOG.i(new Object[] { "dispatchOnFocusEnd", gesture, b, pointF });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$8(this, b, gesture, pointF));
        }
        
        public void dispatchOnFocusStart(final Gesture gesture, final PointF pointF) {
            this.LOG.i(new Object[] { "dispatchOnFocusStart", gesture, pointF });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$7(this, pointF, gesture));
        }
        
        public void dispatchOnPictureShutter(final boolean b) {
            if (b && this.this$0.mPlaySounds) {
                this.this$0.playSound(0);
            }
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$4(this));
        }
        
        public void dispatchOnPictureTaken(final PictureResult$Stub pictureResult$Stub) {
            this.LOG.i(new Object[] { "dispatchOnPictureTaken", pictureResult$Stub });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$5(this, pictureResult$Stub));
        }
        
        public void dispatchOnVideoRecordingEnd() {
            this.LOG.i(new Object[] { "dispatchOnVideoRecordingEnd" });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$15(this));
        }
        
        public void dispatchOnVideoRecordingStart() {
            this.LOG.i(new Object[] { "dispatchOnVideoRecordingStart" });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$14(this));
        }
        
        public void dispatchOnVideoTaken(final VideoResult$Stub videoResult$Stub) {
            this.LOG.i(new Object[] { "dispatchOnVideoTaken", videoResult$Stub });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$6(this, videoResult$Stub));
        }
        
        public void dispatchOnZoomChanged(final float n, final PointF[] array) {
            this.LOG.i(new Object[] { "dispatchOnZoomChanged", n });
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$10(this, n, array));
        }
        
        public Context getContext() {
            return this.this$0.getContext();
        }
        
        public int getHeight() {
            return this.this$0.getHeight();
        }
        
        public int getWidth() {
            return this.this$0.getWidth();
        }
        
        public void onCameraPreviewStreamSizeChanged() {
            final Size previewStreamSize = this.this$0.mCameraEngine.getPreviewStreamSize(Reference.VIEW);
            if (previewStreamSize != null) {
                if (previewStreamSize.equals((Object)this.this$0.mLastPreviewStreamSize)) {
                    this.LOG.i(new Object[] { "onCameraPreviewStreamSizeChanged:", "swallowing because the preview size has not changed.", previewStreamSize });
                }
                else {
                    this.LOG.i(new Object[] { "onCameraPreviewStreamSizeChanged: posting a requestLayout call.", "Preview stream size:", previewStreamSize });
                    this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$3(this));
                }
                return;
            }
            throw new RuntimeException("Preview stream size should not be null here.");
        }
        
        public void onDeviceOrientationChanged(final int deviceOrientation) {
            this.LOG.i(new Object[] { "onDeviceOrientationChanged", deviceOrientation });
            final int lastDisplayOffset = this.this$0.mOrientationHelper.getLastDisplayOffset();
            if (!this.this$0.mUseDeviceOrientation) {
                this.this$0.mCameraEngine.getAngles().setDeviceOrientation((360 - lastDisplayOffset) % 360);
            }
            else {
                this.this$0.mCameraEngine.getAngles().setDeviceOrientation(deviceOrientation);
            }
            this.this$0.mUiHandler.post((Runnable)new CameraView$CameraCallbacks$9(this, (deviceOrientation + lastDisplayOffset) % 360));
        }
        
        public void onDisplayOffsetChanged() {
            if (this.this$0.isOpened()) {
                this.LOG.w(new Object[] { "onDisplayOffsetChanged", "restarting the camera." });
                this.this$0.close();
                this.this$0.open();
            }
        }
    }
}
