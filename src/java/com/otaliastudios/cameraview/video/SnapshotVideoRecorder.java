package com.otaliastudios.cameraview.video;

import com.otaliastudios.cameraview.video.encoding.TextureMediaEncoder$Frame;
import com.otaliastudios.cameraview.video.encoding.AudioMediaEncoder;
import com.otaliastudios.cameraview.video.encoding.TextureMediaEncoder;
import android.opengl.EGL14;
import com.otaliastudios.cameraview.internal.DeviceEncoders$VideoException;
import com.otaliastudios.cameraview.internal.DeviceEncoders$AudioException;
import com.otaliastudios.cameraview.internal.DeviceEncoders;
import com.otaliastudios.cameraview.controls.Audio;
import com.otaliastudios.cameraview.video.encoding.AudioConfig;
import com.otaliastudios.cameraview.video.encoding.TextureConfig;
import android.graphics.SurfaceTexture;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.overlay.Overlay$Target;
import com.otaliastudios.cameraview.engine.CameraEngine;
import com.otaliastudios.cameraview.preview.RendererCameraPreview;
import com.otaliastudios.cameraview.overlay.OverlayDrawer;
import com.otaliastudios.cameraview.overlay.Overlay;
import com.otaliastudios.cameraview.video.encoding.MediaEncoderEngine;
import com.otaliastudios.cameraview.filter.Filter;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.video.encoding.MediaEncoderEngine$Listener;
import com.otaliastudios.cameraview.preview.RendererFrameCallback;

public class SnapshotVideoRecorder extends VideoRecorder implements RendererFrameCallback, MediaEncoderEngine$Listener
{
    private static final int DEFAULT_AUDIO_BITRATE = 64000;
    private static final int DEFAULT_VIDEO_FRAMERATE = 30;
    private static final CameraLogger LOG;
    private static final int STATE_NOT_RECORDING = 1;
    private static final int STATE_RECORDING = 0;
    private static final String TAG;
    private Filter mCurrentFilter;
    private int mCurrentState;
    private int mDesiredState;
    private MediaEncoderEngine mEncoderEngine;
    private final Object mEncoderEngineLock;
    private boolean mHasOverlay;
    private Overlay mOverlay;
    private OverlayDrawer mOverlayDrawer;
    private RendererCameraPreview mPreview;
    private int mTextureId;
    
    static {
        LOG = CameraLogger.create(TAG = SnapshotVideoRecorder.class.getSimpleName());
    }
    
    public SnapshotVideoRecorder(final CameraEngine cameraEngine, final RendererCameraPreview mPreview, final Overlay mOverlay) {
        super((VideoRecorder$VideoResultListener)cameraEngine);
        this.mEncoderEngineLock = new Object();
        boolean mHasOverlay = true;
        this.mCurrentState = 1;
        this.mDesiredState = 1;
        this.mTextureId = 0;
        this.mPreview = mPreview;
        this.mOverlay = mOverlay;
        if (mOverlay == null || !mOverlay.drawsOn(Overlay$Target.VIDEO_SNAPSHOT)) {
            mHasOverlay = false;
        }
        this.mHasOverlay = mHasOverlay;
    }
    
    private static int estimateVideoBitRate(final Size size, final int n) {
        return (int)(size.getWidth() * 0.07f * size.getHeight() * n);
    }
    
    public void onEncodingEnd(final int n, final Exception mError) {
        if (mError != null) {
            SnapshotVideoRecorder.LOG.e(new Object[] { "Error onEncodingEnd", mError });
            this.mResult = null;
            this.mError = mError;
        }
        else if (n == 1) {
            SnapshotVideoRecorder.LOG.i(new Object[] { "onEncodingEnd because of max duration." });
            this.mResult.endReason = 2;
        }
        else if (n == 2) {
            SnapshotVideoRecorder.LOG.i(new Object[] { "onEncodingEnd because of max size." });
            this.mResult.endReason = 1;
        }
        else {
            SnapshotVideoRecorder.LOG.i(new Object[] { "onEncodingEnd because of user." });
        }
        this.mCurrentState = 1;
        this.mDesiredState = 1;
        this.mPreview.removeRendererFrameCallback((RendererFrameCallback)this);
        this.mPreview = null;
        final OverlayDrawer mOverlayDrawer = this.mOverlayDrawer;
        if (mOverlayDrawer != null) {
            mOverlayDrawer.release();
            this.mOverlayDrawer = null;
        }
        final Object mEncoderEngineLock = this.mEncoderEngineLock;
        synchronized (mEncoderEngineLock) {
            this.mEncoderEngine = null;
            monitorexit(mEncoderEngineLock);
            this.dispatchResult();
        }
    }
    
    public void onEncodingStart() {
    }
    
    public void onEncodingStop() {
        this.dispatchVideoRecordingEnd();
    }
    
    public void onRendererFilterChanged(Filter copy) {
        copy = copy.copy();
        (this.mCurrentFilter = copy).setSize(this.mResult.size.getWidth(), this.mResult.size.getHeight());
        final Object mEncoderEngineLock = this.mEncoderEngineLock;
        synchronized (mEncoderEngineLock) {
            if (this.mEncoderEngine != null) {
                this.mEncoderEngine.notify("filter", (Object)this.mCurrentFilter);
            }
        }
    }
    
    public void onRendererFrame(final SurfaceTexture surfaceTexture, final int n, final float scaleX, final float scaleY) {
        Label_1221: {
            if (this.mCurrentState == 1 && this.mDesiredState == 0) {
                SnapshotVideoRecorder.LOG.i(new Object[] { "Starting the encoder engine." });
                if (this.mResult.videoFrameRate <= 0) {
                    this.mResult.videoFrameRate = 30;
                }
                if (this.mResult.videoBitRate <= 0) {
                    this.mResult.videoBitRate = estimateVideoBitRate(this.mResult.size, this.mResult.videoFrameRate);
                }
                if (this.mResult.audioBitRate <= 0) {
                    this.mResult.audioBitRate = 64000;
                }
                String mimeType = "";
                final int n2 = SnapshotVideoRecorder$1.$SwitchMap$com$otaliastudios$cameraview$controls$VideoCodec[this.mResult.videoCodec.ordinal()];
                if (n2 != 1) {
                    if (n2 != 2) {
                        if (n2 == 3) {
                            mimeType = "video/avc";
                        }
                    }
                    else {
                        mimeType = "video/avc";
                    }
                }
                else {
                    mimeType = "video/3gpp";
                }
                String s = "";
                final int n3 = SnapshotVideoRecorder$1.$SwitchMap$com$otaliastudios$cameraview$controls$AudioCodec[this.mResult.audioCodec.ordinal()];
                if (n3 != 1 && n3 != 2 && n3 != 3) {
                    if (n3 == 4) {
                        s = "audio/mp4a-latm";
                    }
                }
                else {
                    s = "audio/mp4a-latm";
                }
                final TextureConfig textureConfig = new TextureConfig();
                final AudioConfig audioConfig = new AudioConfig();
                int channels;
                if (this.mResult.audio == Audio.ON) {
                    channels = audioConfig.channels;
                }
                else if (this.mResult.audio == Audio.MONO) {
                    channels = 1;
                }
                else if (this.mResult.audio == Audio.STEREO) {
                    channels = 2;
                }
                else {
                    channels = 0;
                }
                final boolean b = channels > 0;
                Object supportedVideoSize = null;
                Object size = null;
                int i = 0;
                int n4 = 0;
                final int n5 = 0;
                int videoBitRate = 0;
                int n6 = 0;
                int supportedAudioBitRate = 0;
            Label_0846:
                while (true) {
                    while (i == 0) {
                        SnapshotVideoRecorder.LOG.i(new Object[] { "Checking DeviceEncoders...", "videoOffset:", n4, "audioOffset:", n5 });
                        try {
                            new DeviceEncoders(0, mimeType, s, n4, n5);
                            final DeviceEncoders deviceEncoders = new DeviceEncoders(1, mimeType, s, n4, n5);
                            try {
                                supportedVideoSize = deviceEncoders.getSupportedVideoSize(this.mResult.size);
                                try {
                                    int supportedVideoBitRate = deviceEncoders.getSupportedVideoBitRate(this.mResult.videoBitRate);
                                    try {
                                        videoBitRate = deviceEncoders.getSupportedVideoFrameRate((Size)supportedVideoSize, this.mResult.videoFrameRate);
                                        Label_0609: {
                                            try {
                                                deviceEncoders.tryConfigureVideo(mimeType, (Size)supportedVideoSize, videoBitRate, supportedVideoBitRate);
                                                supportedAudioBitRate = n6;
                                                if (b) {
                                                    supportedAudioBitRate = deviceEncoders.getSupportedAudioBitRate(this.mResult.audioBitRate);
                                                    try {
                                                        deviceEncoders.tryConfigureAudio(s, supportedAudioBitRate, audioConfig.samplingFrequency, channels);
                                                    }
                                                    catch (final DeviceEncoders$AudioException size) {
                                                        n6 = supportedAudioBitRate;
                                                        goto Label_0584;
                                                    }
                                                    catch (final DeviceEncoders$VideoException size) {
                                                        n6 = supportedAudioBitRate;
                                                        break Label_0609;
                                                    }
                                                }
                                                size = supportedVideoSize;
                                                n6 = supportedVideoBitRate;
                                                supportedVideoBitRate = videoBitRate;
                                                i = 1;
                                                supportedVideoSize = deviceEncoders;
                                                videoBitRate = n6;
                                                n6 = supportedAudioBitRate;
                                                supportedAudioBitRate = supportedVideoBitRate;
                                                continue;
                                            }
                                            catch (final DeviceEncoders$AudioException ex) {}
                                            catch (final DeviceEncoders$VideoException ex2) {}
                                        }
                                        final Size size2 = (Size)supportedVideoSize;
                                        supportedAudioBitRate = videoBitRate;
                                        supportedVideoSize = size;
                                        size = size2;
                                        videoBitRate = supportedVideoBitRate;
                                    }
                                    catch (final DeviceEncoders$AudioException ex3) {
                                        size = supportedVideoSize;
                                        videoBitRate = supportedVideoBitRate;
                                        supportedVideoSize = ex3;
                                    }
                                    catch (DeviceEncoders$VideoException size) {
                                        final Size size3 = (Size)supportedVideoSize;
                                        videoBitRate = supportedVideoBitRate;
                                        supportedVideoSize = size;
                                        size = size3;
                                    }
                                }
                                catch (DeviceEncoders$AudioException size) {
                                    final Size size4 = (Size)supportedVideoSize;
                                    supportedVideoSize = size;
                                    size = size4;
                                }
                                catch (final DeviceEncoders$VideoException ex4) {
                                    size = supportedVideoSize;
                                    supportedVideoSize = ex4;
                                }
                            }
                            catch (final DeviceEncoders$AudioException ex5) {}
                            catch (final DeviceEncoders$VideoException ex6) {}
                            SnapshotVideoRecorder.LOG.i(new Object[] { "Got VideoException:", ((DeviceEncoders$VideoException)supportedVideoSize).getMessage() });
                            ++n4;
                            supportedVideoSize = deviceEncoders;
                            continue;
                        }
                        catch (final RuntimeException ex7) {
                            SnapshotVideoRecorder.LOG.w(new Object[] { "Could not respect encoders parameters.", "Going on again without checking encoders, possibly failing." });
                            size = this.mResult.size;
                            videoBitRate = this.mResult.videoBitRate;
                            final int videoFrameRate = this.mResult.videoFrameRate;
                            final int audioBitRate = this.mResult.audioBitRate;
                            break Label_0846;
                        }
                        break;
                        this.mResult.size = (Size)size;
                        this.mResult.videoBitRate = videoBitRate;
                        int audioBitRate = 0;
                        this.mResult.audioBitRate = audioBitRate;
                        int videoFrameRate = 0;
                        this.mResult.videoFrameRate = videoFrameRate;
                        textureConfig.width = this.mResult.size.getWidth();
                        textureConfig.height = this.mResult.size.getHeight();
                        textureConfig.bitRate = this.mResult.videoBitRate;
                        textureConfig.frameRate = this.mResult.videoFrameRate;
                        textureConfig.rotation = n + this.mResult.rotation;
                        textureConfig.mimeType = mimeType;
                        textureConfig.encoder = ((DeviceEncoders)supportedVideoSize).getVideoEncoder();
                        textureConfig.textureId = this.mTextureId;
                        textureConfig.scaleX = scaleX;
                        textureConfig.scaleY = scaleY;
                        textureConfig.eglContext = EGL14.eglGetCurrentContext();
                        if (this.mHasOverlay) {
                            textureConfig.overlayTarget = Overlay$Target.VIDEO_SNAPSHOT;
                            textureConfig.overlayDrawer = this.mOverlayDrawer;
                            textureConfig.overlayRotation = this.mResult.rotation;
                        }
                        final TextureMediaEncoder textureMediaEncoder = new TextureMediaEncoder(textureConfig);
                        this.mResult.rotation = 0;
                        this.mCurrentFilter.setSize(this.mResult.size.getWidth(), this.mResult.size.getWidth());
                        AudioMediaEncoder audioMediaEncoder;
                        if (b) {
                            audioConfig.bitRate = this.mResult.audioBitRate;
                            audioConfig.channels = channels;
                            audioConfig.encoder = ((DeviceEncoders)supportedVideoSize).getAudioEncoder();
                            audioMediaEncoder = new AudioMediaEncoder(audioConfig);
                        }
                        else {
                            audioMediaEncoder = null;
                        }
                        final Object mEncoderEngineLock = this.mEncoderEngineLock;
                        synchronized (mEncoderEngineLock) {
                            (this.mEncoderEngine = new MediaEncoderEngine(this.mResult.file, (VideoMediaEncoder)textureMediaEncoder, audioMediaEncoder, this.mResult.maxDuration, this.mResult.maxSize, (MediaEncoderEngine$Listener)this)).notify("filter", (Object)this.mCurrentFilter);
                            this.mEncoderEngine.start();
                            monitorexit(mEncoderEngineLock);
                            this.mCurrentState = 0;
                        }
                        break Label_1221;
                    }
                    final int n7 = n6;
                    final int videoFrameRate = supportedAudioBitRate;
                    final int audioBitRate = n7;
                    continue Label_0846;
                }
            }
        }
        if (this.mCurrentState == 0) {
            SnapshotVideoRecorder.LOG.i(new Object[] { "scheduling frame." });
            final Object mEncoderEngineLock2 = this.mEncoderEngineLock;
            synchronized (mEncoderEngineLock2) {
                if (this.mEncoderEngine != null) {
                    SnapshotVideoRecorder.LOG.i(new Object[] { "dispatching frame." });
                    final TextureMediaEncoder$Frame acquireFrame = ((TextureMediaEncoder)this.mEncoderEngine.getVideoEncoder()).acquireFrame();
                    acquireFrame.timestampNanos = surfaceTexture.getTimestamp();
                    acquireFrame.timestampMillis = System.currentTimeMillis();
                    surfaceTexture.getTransformMatrix(acquireFrame.transform);
                    this.mEncoderEngine.notify("frame", (Object)acquireFrame);
                }
            }
        }
        if (this.mCurrentState == 0 && this.mDesiredState == 1) {
            SnapshotVideoRecorder.LOG.i(new Object[] { "Stopping the encoder engine." });
            this.mCurrentState = 1;
            final Object mEncoderEngineLock3 = this.mEncoderEngineLock;
            synchronized (mEncoderEngineLock3) {
                if (this.mEncoderEngine != null) {
                    this.mEncoderEngine.stop();
                    this.mEncoderEngine = null;
                }
            }
        }
    }
    
    public void onRendererTextureCreated(final int mTextureId) {
        this.mTextureId = mTextureId;
        if (this.mHasOverlay) {
            this.mOverlayDrawer = new OverlayDrawer(this.mOverlay, this.mResult.size);
        }
    }
    
    protected void onStart() {
        this.mPreview.addRendererFrameCallback((RendererFrameCallback)this);
        this.mDesiredState = 0;
        this.dispatchVideoRecordingStart();
    }
    
    protected void onStop(final boolean b) {
        if (b) {
            SnapshotVideoRecorder.LOG.i(new Object[] { "Stopping the encoder engine from isCameraShutdown." });
            this.mDesiredState = 1;
            this.mCurrentState = 1;
            final Object mEncoderEngineLock = this.mEncoderEngineLock;
            synchronized (mEncoderEngineLock) {
                if (this.mEncoderEngine != null) {
                    this.mEncoderEngine.stop();
                    this.mEncoderEngine = null;
                }
                return;
            }
        }
        this.mDesiredState = 1;
    }
}
