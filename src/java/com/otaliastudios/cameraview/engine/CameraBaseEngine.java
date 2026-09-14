package com.otaliastudios.cameraview.engine;

import java.io.FileDescriptor;
import java.io.File;
import com.otaliastudios.cameraview.preview.CameraPreview$SurfaceCallback;
import com.otaliastudios.cameraview.VideoResult$Stub;
import com.otaliastudios.cameraview.engine.orchestrator.CameraState;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.PictureResult$Stub;
import java.util.Iterator;
import com.otaliastudios.cameraview.size.AspectRatio;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import com.otaliastudios.cameraview.size.SizeSelectors;
import com.otaliastudios.cameraview.engine.offset.Reference;
import com.google.android.gms.tasks.Tasks;
import com.otaliastudios.cameraview.controls.WhiteBalance;
import com.otaliastudios.cameraview.video.VideoRecorder;
import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.preview.CameraPreview;
import com.otaliastudios.cameraview.size.SizeSelector;
import com.otaliastudios.cameraview.picture.PictureRecorder;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.overlay.Overlay;
import com.otaliastudios.cameraview.controls.Mode;
import android.location.Location;
import com.otaliastudios.cameraview.controls.Hdr;
import com.otaliastudios.cameraview.frame.FrameManager;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Facing;
import com.google.android.gms.tasks.Task;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.Audio;
import com.otaliastudios.cameraview.engine.offset.Angles;

public abstract class CameraBaseEngine extends CameraEngine
{
    protected static final int ALLOWED_EV_OPS = 20;
    protected static final int ALLOWED_ZOOM_OPS = 20;
    private final Angles mAngles;
    private Audio mAudio;
    private int mAudioBitRate;
    protected AudioCodec mAudioCodec;
    private long mAutoFocusResetDelayMillis;
    protected CameraOptions mCameraOptions;
    protected Size mCaptureSize;
    Task<Void> mExposureCorrectionTask;
    protected float mExposureCorrectionValue;
    private Facing mFacing;
    protected Flash mFlash;
    Task<Void> mFlashTask;
    private FrameManager mFrameManager;
    protected int mFrameProcessingFormat;
    private int mFrameProcessingMaxHeight;
    private int mFrameProcessingMaxWidth;
    private int mFrameProcessingPoolSize;
    protected Size mFrameProcessingSize;
    protected boolean mHasFrameProcessors;
    protected Hdr mHdr;
    Task<Void> mHdrTask;
    protected Location mLocation;
    Task<Void> mLocationTask;
    private Mode mMode;
    private Overlay mOverlay;
    protected PictureFormat mPictureFormat;
    protected boolean mPictureMetering;
    protected PictureRecorder mPictureRecorder;
    private SizeSelector mPictureSizeSelector;
    protected boolean mPictureSnapshotMetering;
    protected boolean mPlaySounds;
    Task<Void> mPlaySoundsTask;
    protected CameraPreview mPreview;
    protected float mPreviewFrameRate;
    private boolean mPreviewFrameRateExact;
    Task<Void> mPreviewFrameRateTask;
    protected Size mPreviewStreamSize;
    private SizeSelector mPreviewStreamSizeSelector;
    private int mSnapshotMaxHeight;
    private int mSnapshotMaxWidth;
    private int mVideoBitRate;
    protected VideoCodec mVideoCodec;
    private int mVideoMaxDuration;
    private long mVideoMaxSize;
    protected VideoRecorder mVideoRecorder;
    private SizeSelector mVideoSizeSelector;
    protected WhiteBalance mWhiteBalance;
    Task<Void> mWhiteBalanceTask;
    Task<Void> mZoomTask;
    protected float mZoomValue;
    
    protected CameraBaseEngine(final CameraEngine$Callback cameraEngine$Callback) {
        super(cameraEngine$Callback);
        this.mAngles = new Angles();
        this.mZoomTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mExposureCorrectionTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mFlashTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mWhiteBalanceTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mHdrTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mLocationTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mPlaySoundsTask = (Task<Void>)Tasks.forResult((Object)null);
        this.mPreviewFrameRateTask = (Task<Void>)Tasks.forResult((Object)null);
    }
    
    private Size getPreviewSurfaceSize(final Reference reference) {
        final CameraPreview mPreview = this.mPreview;
        if (mPreview == null) {
            return null;
        }
        Size size;
        if (this.getAngles().flip(Reference.VIEW, reference)) {
            size = mPreview.getSurfaceSize().flip();
        }
        else {
            size = mPreview.getSurfaceSize();
        }
        return size;
    }
    
    protected final Size computeCaptureSize() {
        return this.computeCaptureSize(this.mMode);
    }
    
    protected final Size computeCaptureSize(final Mode mode) {
        final boolean flip = this.getAngles().flip(Reference.SENSOR, Reference.VIEW);
        SizeSelector sizeSelector;
        Collection collection;
        if (mode == Mode.PICTURE) {
            sizeSelector = this.mPictureSizeSelector;
            collection = this.mCameraOptions.getSupportedPictureSizes();
        }
        else {
            sizeSelector = this.mVideoSizeSelector;
            collection = this.mCameraOptions.getSupportedVideoSizes();
        }
        final SizeSelector or = SizeSelectors.or(new SizeSelector[] { sizeSelector, SizeSelectors.biggest() });
        final ArrayList list = new ArrayList(collection);
        final Size size = (Size)or.select((List)list).get(0);
        if (((List)list).contains((Object)size)) {
            CameraBaseEngine.LOG.i(new Object[] { "computeCaptureSize:", "result:", size, "flip:", flip, "mode:", mode });
            Size flip2 = size;
            if (flip) {
                flip2 = size.flip();
            }
            return flip2;
        }
        throw new RuntimeException("SizeSelectors must not return Sizes other than those in the input list.");
    }
    
    protected final Size computeFrameProcessingSize() {
        final List<Size> frameProcessingAvailableSizes = this.getFrameProcessingAvailableSizes();
        final boolean flip = this.getAngles().flip(Reference.SENSOR, Reference.VIEW);
        final ArrayList list = new ArrayList(frameProcessingAvailableSizes.size());
        for (Size flip2 : frameProcessingAvailableSizes) {
            if (flip) {
                flip2 = flip2.flip();
            }
            ((List)list).add((Object)flip2);
        }
        AspectRatio aspectRatio2;
        final AspectRatio aspectRatio = aspectRatio2 = AspectRatio.of(this.mPreviewStreamSize.getWidth(), this.mPreviewStreamSize.getHeight());
        if (flip) {
            aspectRatio2 = aspectRatio.flip();
        }
        final int mFrameProcessingMaxWidth = this.mFrameProcessingMaxWidth;
        final int mFrameProcessingMaxHeight = this.mFrameProcessingMaxHeight;
        int n;
        if (mFrameProcessingMaxWidth <= 0 || (n = mFrameProcessingMaxWidth) == Integer.MAX_VALUE) {
            n = 640;
        }
        int n2;
        if (mFrameProcessingMaxHeight <= 0 || (n2 = mFrameProcessingMaxHeight) == Integer.MAX_VALUE) {
            n2 = 640;
        }
        final Size size = new Size(n, n2);
        CameraBaseEngine.LOG.i(new Object[] { "computeFrameProcessingSize:", "targetRatio:", aspectRatio2, "targetMaxSize:", size });
        final SizeSelector aspectRatio3 = SizeSelectors.aspectRatio(aspectRatio2, 0.0f);
        final SizeSelector and = SizeSelectors.and(new SizeSelector[] { SizeSelectors.maxHeight(size.getHeight()), SizeSelectors.maxWidth(size.getWidth()), SizeSelectors.biggest() });
        final Size size2 = (Size)SizeSelectors.or(new SizeSelector[] { SizeSelectors.and(new SizeSelector[] { aspectRatio3, and }), and, SizeSelectors.smallest() }).select((List)list).get(0);
        if (((List)list).contains((Object)size2)) {
            Size flip3 = size2;
            if (flip) {
                flip3 = size2.flip();
            }
            CameraBaseEngine.LOG.i(new Object[] { "computeFrameProcessingSize:", "result:", flip3, "flip:", flip });
            return flip3;
        }
        throw new RuntimeException("SizeSelectors must not return Sizes other than those in the input list.");
    }
    
    protected final Size computePreviewStreamSize() {
        final List<Size> previewStreamAvailableSizes = this.getPreviewStreamAvailableSizes();
        final boolean flip = this.getAngles().flip(Reference.SENSOR, Reference.VIEW);
        final ArrayList list = new ArrayList(previewStreamAvailableSizes.size());
        for (Size flip2 : previewStreamAvailableSizes) {
            if (flip) {
                flip2 = flip2.flip();
            }
            ((List)list).add((Object)flip2);
        }
        final Size previewSurfaceSize = this.getPreviewSurfaceSize(Reference.VIEW);
        if (previewSurfaceSize == null) {
            throw new IllegalStateException("targetMinSize should not be null here.");
        }
        AspectRatio aspectRatio2;
        final AspectRatio aspectRatio = aspectRatio2 = AspectRatio.of(this.mCaptureSize.getWidth(), this.mCaptureSize.getHeight());
        if (flip) {
            aspectRatio2 = aspectRatio.flip();
        }
        CameraBaseEngine.LOG.i(new Object[] { "computePreviewStreamSize:", "targetRatio:", aspectRatio2, "targetMinSize:", previewSurfaceSize });
        final SizeSelector and = SizeSelectors.and(new SizeSelector[] { SizeSelectors.aspectRatio(aspectRatio2, 0.0f), SizeSelectors.biggest() });
        final SizeSelector and2 = SizeSelectors.and(new SizeSelector[] { SizeSelectors.minHeight(previewSurfaceSize.getHeight()), SizeSelectors.minWidth(previewSurfaceSize.getWidth()), SizeSelectors.smallest() });
        final SizeSelector or = SizeSelectors.or(new SizeSelector[] { SizeSelectors.and(new SizeSelector[] { and, and2 }), and2, and, SizeSelectors.biggest() });
        final SizeSelector mPreviewStreamSizeSelector = this.mPreviewStreamSizeSelector;
        SizeSelector or2 = or;
        if (mPreviewStreamSizeSelector != null) {
            or2 = SizeSelectors.or(new SizeSelector[] { mPreviewStreamSizeSelector, or });
        }
        final Size size = (Size)or2.select((List)list).get(0);
        if (((List)list).contains((Object)size)) {
            Size flip3 = size;
            if (flip) {
                flip3 = size.flip();
            }
            CameraBaseEngine.LOG.i(new Object[] { "computePreviewStreamSize:", "result:", flip3, "flip:", flip });
            return flip3;
        }
        throw new RuntimeException("SizeSelectors must not return Sizes other than those in the input list.");
    }
    
    public final Angles getAngles() {
        return this.mAngles;
    }
    
    public final Audio getAudio() {
        return this.mAudio;
    }
    
    public final int getAudioBitRate() {
        return this.mAudioBitRate;
    }
    
    public final AudioCodec getAudioCodec() {
        return this.mAudioCodec;
    }
    
    public final long getAutoFocusResetDelay() {
        return this.mAutoFocusResetDelayMillis;
    }
    
    public final CameraOptions getCameraOptions() {
        return this.mCameraOptions;
    }
    
    public final float getExposureCorrectionValue() {
        return this.mExposureCorrectionValue;
    }
    
    public final Facing getFacing() {
        return this.mFacing;
    }
    
    public final Flash getFlash() {
        return this.mFlash;
    }
    
    public FrameManager getFrameManager() {
        if (this.mFrameManager == null) {
            this.mFrameManager = this.instantiateFrameManager(this.mFrameProcessingPoolSize);
        }
        return this.mFrameManager;
    }
    
    protected abstract List<Size> getFrameProcessingAvailableSizes();
    
    public final int getFrameProcessingFormat() {
        return this.mFrameProcessingFormat;
    }
    
    public final int getFrameProcessingMaxHeight() {
        return this.mFrameProcessingMaxHeight;
    }
    
    public final int getFrameProcessingMaxWidth() {
        return this.mFrameProcessingMaxWidth;
    }
    
    public final int getFrameProcessingPoolSize() {
        return this.mFrameProcessingPoolSize;
    }
    
    public final Hdr getHdr() {
        return this.mHdr;
    }
    
    public final Location getLocation() {
        return this.mLocation;
    }
    
    public final Mode getMode() {
        return this.mMode;
    }
    
    public final Overlay getOverlay() {
        return this.mOverlay;
    }
    
    public final PictureFormat getPictureFormat() {
        return this.mPictureFormat;
    }
    
    public final boolean getPictureMetering() {
        return this.mPictureMetering;
    }
    
    public final Size getPictureSize(final Reference reference) {
        final Size mCaptureSize = this.mCaptureSize;
        if (mCaptureSize != null && this.mMode != Mode.VIDEO) {
            Size flip = mCaptureSize;
            if (this.getAngles().flip(Reference.SENSOR, reference)) {
                flip = mCaptureSize.flip();
            }
            return flip;
        }
        return null;
    }
    
    public final SizeSelector getPictureSizeSelector() {
        return this.mPictureSizeSelector;
    }
    
    public final boolean getPictureSnapshotMetering() {
        return this.mPictureSnapshotMetering;
    }
    
    public final CameraPreview getPreview() {
        return this.mPreview;
    }
    
    public final float getPreviewFrameRate() {
        return this.mPreviewFrameRate;
    }
    
    public final boolean getPreviewFrameRateExact() {
        return this.mPreviewFrameRateExact;
    }
    
    protected abstract List<Size> getPreviewStreamAvailableSizes();
    
    public final Size getPreviewStreamSize(final Reference reference) {
        final Size mPreviewStreamSize = this.mPreviewStreamSize;
        if (mPreviewStreamSize == null) {
            return null;
        }
        Size flip = mPreviewStreamSize;
        if (this.getAngles().flip(Reference.SENSOR, reference)) {
            flip = mPreviewStreamSize.flip();
        }
        return flip;
    }
    
    public final SizeSelector getPreviewStreamSizeSelector() {
        return this.mPreviewStreamSizeSelector;
    }
    
    public final int getSnapshotMaxHeight() {
        return this.mSnapshotMaxHeight;
    }
    
    public final int getSnapshotMaxWidth() {
        return this.mSnapshotMaxWidth;
    }
    
    public final Size getUncroppedSnapshotSize(final Reference reference) {
        final Size previewStreamSize = this.getPreviewStreamSize(reference);
        if (previewStreamSize == null) {
            return null;
        }
        final boolean flip = this.getAngles().flip(reference, Reference.VIEW);
        int n;
        if (flip) {
            n = this.mSnapshotMaxHeight;
        }
        else {
            n = this.mSnapshotMaxWidth;
        }
        int n2;
        if (flip) {
            n2 = this.mSnapshotMaxWidth;
        }
        else {
            n2 = this.mSnapshotMaxHeight;
        }
        int n3 = n;
        if (n <= 0) {
            n3 = Integer.MAX_VALUE;
        }
        int n4;
        if ((n4 = n2) <= 0) {
            n4 = Integer.MAX_VALUE;
        }
        final float float1 = AspectRatio.of(previewStreamSize).toFloat();
        if (AspectRatio.of(n3, n4).toFloat() >= float1) {
            final int min = Math.min(previewStreamSize.getHeight(), n4);
            return new Size((int)Math.floor((double)(min * float1)), min);
        }
        final int min2 = Math.min(previewStreamSize.getWidth(), n3);
        return new Size(min2, (int)Math.floor((double)(min2 / float1)));
    }
    
    public final int getVideoBitRate() {
        return this.mVideoBitRate;
    }
    
    public final VideoCodec getVideoCodec() {
        return this.mVideoCodec;
    }
    
    public final int getVideoMaxDuration() {
        return this.mVideoMaxDuration;
    }
    
    public final long getVideoMaxSize() {
        return this.mVideoMaxSize;
    }
    
    public final Size getVideoSize(final Reference reference) {
        final Size mCaptureSize = this.mCaptureSize;
        if (mCaptureSize != null && this.mMode != Mode.PICTURE) {
            Size flip = mCaptureSize;
            if (this.getAngles().flip(Reference.SENSOR, reference)) {
                flip = mCaptureSize.flip();
            }
            return flip;
        }
        return null;
    }
    
    public final SizeSelector getVideoSizeSelector() {
        return this.mVideoSizeSelector;
    }
    
    public final WhiteBalance getWhiteBalance() {
        return this.mWhiteBalance;
    }
    
    public final float getZoomValue() {
        return this.mZoomValue;
    }
    
    public final boolean hasFrameProcessors() {
        return this.mHasFrameProcessors;
    }
    
    protected abstract FrameManager instantiateFrameManager(final int p0);
    
    public final boolean isTakingPicture() {
        return this.mPictureRecorder != null;
    }
    
    public final boolean isTakingVideo() {
        final VideoRecorder mVideoRecorder = this.mVideoRecorder;
        return mVideoRecorder != null && mVideoRecorder.isRecording();
    }
    
    public void onPictureResult(final PictureResult$Stub pictureResult$Stub, final Exception ex) {
        this.mPictureRecorder = null;
        if (pictureResult$Stub != null) {
            this.getCallback().dispatchOnPictureTaken(pictureResult$Stub);
        }
        else {
            CameraBaseEngine.LOG.e(new Object[] { "onPictureResult", "result is null: something went wrong.", ex });
            this.getCallback().dispatchError(new CameraException((Throwable)ex, 4));
        }
    }
    
    public void onPictureShutter(final boolean b) {
        this.getCallback().dispatchOnPictureShutter(b ^ true);
    }
    
    protected abstract void onPreviewStreamSizeChanged();
    
    protected void onStopVideo() {
        final VideoRecorder mVideoRecorder = this.mVideoRecorder;
        if (mVideoRecorder != null) {
            mVideoRecorder.stop(false);
        }
    }
    
    public final void onSurfaceChanged() {
        CameraBaseEngine.LOG.i(new Object[] { "onSurfaceChanged:", "Size is", this.getPreviewSurfaceSize(Reference.VIEW) });
        this.getOrchestrator().scheduleStateful("surface changed", CameraState.BIND, (Runnable)new CameraBaseEngine$8(this));
    }
    
    protected abstract void onTakePicture(final PictureResult$Stub p0, final boolean p1);
    
    protected abstract void onTakePictureSnapshot(final PictureResult$Stub p0, final AspectRatio p1, final boolean p2);
    
    protected abstract void onTakeVideo(final VideoResult$Stub p0);
    
    protected abstract void onTakeVideoSnapshot(final VideoResult$Stub p0, final AspectRatio p1);
    
    public void onVideoRecordingEnd() {
        this.getCallback().dispatchOnVideoRecordingEnd();
    }
    
    public void onVideoRecordingStart() {
        this.getCallback().dispatchOnVideoRecordingStart();
    }
    
    public void onVideoResult(final VideoResult$Stub videoResult$Stub, final Exception ex) {
        this.mVideoRecorder = null;
        if (videoResult$Stub != null) {
            this.getCallback().dispatchOnVideoTaken(videoResult$Stub);
        }
        else {
            CameraBaseEngine.LOG.e(new Object[] { "onVideoResult", "result is null: something went wrong.", ex });
            this.getCallback().dispatchError(new CameraException((Throwable)ex, 5));
        }
    }
    
    public final void setAudio(final Audio mAudio) {
        if (this.mAudio != mAudio) {
            if (this.isTakingVideo()) {
                CameraBaseEngine.LOG.w(new Object[] { "Audio setting was changed while recording. Changes will take place starting from next video" });
            }
            this.mAudio = mAudio;
        }
    }
    
    public final void setAudioBitRate(final int mAudioBitRate) {
        this.mAudioBitRate = mAudioBitRate;
    }
    
    public final void setAudioCodec(final AudioCodec mAudioCodec) {
        this.mAudioCodec = mAudioCodec;
    }
    
    public final void setAutoFocusResetDelay(final long mAutoFocusResetDelayMillis) {
        this.mAutoFocusResetDelayMillis = mAutoFocusResetDelayMillis;
    }
    
    public final void setFacing(final Facing mFacing) {
        final Facing mFacing2 = this.mFacing;
        if (mFacing != mFacing2) {
            this.mFacing = mFacing;
            this.getOrchestrator().scheduleStateful("facing", CameraState.ENGINE, (Runnable)new CameraBaseEngine$1(this, mFacing, mFacing2));
        }
    }
    
    public final void setFrameProcessingMaxHeight(final int mFrameProcessingMaxHeight) {
        this.mFrameProcessingMaxHeight = mFrameProcessingMaxHeight;
    }
    
    public final void setFrameProcessingMaxWidth(final int mFrameProcessingMaxWidth) {
        this.mFrameProcessingMaxWidth = mFrameProcessingMaxWidth;
    }
    
    public final void setFrameProcessingPoolSize(final int mFrameProcessingPoolSize) {
        this.mFrameProcessingPoolSize = mFrameProcessingPoolSize;
    }
    
    public final void setMode(final Mode mMode) {
        if (mMode != this.mMode) {
            this.mMode = mMode;
            this.getOrchestrator().scheduleStateful("mode", CameraState.ENGINE, (Runnable)new CameraBaseEngine$2(this));
        }
    }
    
    public final void setOverlay(final Overlay mOverlay) {
        this.mOverlay = mOverlay;
    }
    
    public final void setPictureMetering(final boolean mPictureMetering) {
        this.mPictureMetering = mPictureMetering;
    }
    
    public final void setPictureSizeSelector(final SizeSelector mPictureSizeSelector) {
        this.mPictureSizeSelector = mPictureSizeSelector;
    }
    
    public final void setPictureSnapshotMetering(final boolean mPictureSnapshotMetering) {
        this.mPictureSnapshotMetering = mPictureSnapshotMetering;
    }
    
    public final void setPreview(final CameraPreview mPreview) {
        final CameraPreview mPreview2 = this.mPreview;
        if (mPreview2 != null) {
            mPreview2.setSurfaceCallback((CameraPreview$SurfaceCallback)null);
        }
        (this.mPreview = mPreview).setSurfaceCallback((CameraPreview$SurfaceCallback)this);
    }
    
    public final void setPreviewFrameRateExact(final boolean mPreviewFrameRateExact) {
        this.mPreviewFrameRateExact = mPreviewFrameRateExact;
    }
    
    public final void setPreviewStreamSizeSelector(final SizeSelector mPreviewStreamSizeSelector) {
        this.mPreviewStreamSizeSelector = mPreviewStreamSizeSelector;
    }
    
    public final void setSnapshotMaxHeight(final int mSnapshotMaxHeight) {
        this.mSnapshotMaxHeight = mSnapshotMaxHeight;
    }
    
    public final void setSnapshotMaxWidth(final int mSnapshotMaxWidth) {
        this.mSnapshotMaxWidth = mSnapshotMaxWidth;
    }
    
    public final void setVideoBitRate(final int mVideoBitRate) {
        this.mVideoBitRate = mVideoBitRate;
    }
    
    public final void setVideoCodec(final VideoCodec mVideoCodec) {
        this.mVideoCodec = mVideoCodec;
    }
    
    public final void setVideoMaxDuration(final int mVideoMaxDuration) {
        this.mVideoMaxDuration = mVideoMaxDuration;
    }
    
    public final void setVideoMaxSize(final long mVideoMaxSize) {
        this.mVideoMaxSize = mVideoMaxSize;
    }
    
    public final void setVideoSizeSelector(final SizeSelector mVideoSizeSelector) {
        this.mVideoSizeSelector = mVideoSizeSelector;
    }
    
    protected final boolean shouldResetAutoFocus() {
        final long mAutoFocusResetDelayMillis = this.mAutoFocusResetDelayMillis;
        return mAutoFocusResetDelayMillis > 0L && mAutoFocusResetDelayMillis != Long.MAX_VALUE;
    }
    
    public final void stopVideo() {
        this.getOrchestrator().schedule("stop video", true, (Runnable)new CameraBaseEngine$7(this));
    }
    
    public void takePicture(final PictureResult$Stub pictureResult$Stub) {
        this.getOrchestrator().scheduleStateful("take picture", CameraState.BIND, (Runnable)new CameraBaseEngine$3(this, pictureResult$Stub, this.mPictureMetering));
    }
    
    public void takePictureSnapshot(final PictureResult$Stub pictureResult$Stub) {
        this.getOrchestrator().scheduleStateful("take picture snapshot", CameraState.BIND, (Runnable)new CameraBaseEngine$4(this, pictureResult$Stub, this.mPictureSnapshotMetering));
    }
    
    public final void takeVideo(final VideoResult$Stub videoResult$Stub, final File file, final FileDescriptor fileDescriptor) {
        this.getOrchestrator().scheduleStateful("take video", CameraState.BIND, (Runnable)new CameraBaseEngine$5(this, file, videoResult$Stub, fileDescriptor));
    }
    
    public final void takeVideoSnapshot(final VideoResult$Stub videoResult$Stub, final File file) {
        this.getOrchestrator().scheduleStateful("take video snapshot", CameraState.BIND, (Runnable)new CameraBaseEngine$6(this, videoResult$Stub, file));
    }
}
