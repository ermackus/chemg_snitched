package com.otaliastudios.cameraview.video;

import android.media.MediaRecorder$OnErrorListener;
import android.media.MediaRecorder$OnInfoListener;
import com.otaliastudios.cameraview.internal.DeviceEncoders$VideoException;
import com.otaliastudios.cameraview.internal.DeviceEncoders$AudioException;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.DeviceEncoders;
import android.os.Build$VERSION;
import com.otaliastudios.cameraview.controls.AudioCodec;
import com.otaliastudios.cameraview.controls.VideoCodec;
import com.otaliastudios.cameraview.controls.Audio;
import com.otaliastudios.cameraview.VideoResult$Stub;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class FullVideoRecorder extends VideoRecorder
{
    protected static final CameraLogger LOG;
    private static final String TAG;
    protected MediaRecorder mMediaRecorder;
    private boolean mMediaRecorderPrepared;
    private CamcorderProfile mProfile;
    
    static {
        LOG = CameraLogger.create(TAG = FullVideoRecorder.class.getSimpleName());
    }
    
    FullVideoRecorder(final VideoRecorder$VideoResultListener videoRecorder$VideoResultListener) {
        super(videoRecorder$VideoResultListener);
    }
    
    private boolean prepareMediaRecorder(final VideoResult$Stub videoResult$Stub, final boolean b) {
        FullVideoRecorder.LOG.i(new Object[] { "prepareMediaRecorder:", "Preparing on thread", Thread.currentThread() });
        this.mMediaRecorder = new MediaRecorder();
        this.mProfile = this.getCamcorderProfile(videoResult$Stub);
        this.applyVideoSource(videoResult$Stub, this.mMediaRecorder);
        int audioChannels;
        if (videoResult$Stub.audio == Audio.ON) {
            audioChannels = this.mProfile.audioChannels;
        }
        else if (videoResult$Stub.audio == Audio.MONO) {
            audioChannels = 1;
        }
        else if (videoResult$Stub.audio == Audio.STEREO) {
            audioChannels = 2;
        }
        else {
            audioChannels = 0;
        }
        final boolean b2 = audioChannels > 0;
        if (b2) {
            this.mMediaRecorder.setAudioSource(0);
        }
        if (videoResult$Stub.videoCodec == VideoCodec.H_264) {
            this.mProfile.videoCodec = 2;
            this.mProfile.fileFormat = 2;
        }
        else if (videoResult$Stub.videoCodec == VideoCodec.H_263) {
            this.mProfile.videoCodec = 1;
            this.mProfile.fileFormat = 2;
        }
        if (videoResult$Stub.audioCodec == AudioCodec.AAC) {
            this.mProfile.audioCodec = 3;
        }
        else if (Build$VERSION.SDK_INT >= 16 && videoResult$Stub.audioCodec == AudioCodec.HE_AAC) {
            this.mProfile.audioCodec = 4;
        }
        else if (Build$VERSION.SDK_INT >= 16 && videoResult$Stub.audioCodec == AudioCodec.AAC_ELD) {
            this.mProfile.audioCodec = 5;
        }
        this.mMediaRecorder.setOutputFormat(this.mProfile.fileFormat);
        if (videoResult$Stub.videoFrameRate <= 0) {
            videoResult$Stub.videoFrameRate = this.mProfile.videoFrameRate;
        }
        if (videoResult$Stub.videoBitRate <= 0) {
            videoResult$Stub.videoBitRate = this.mProfile.videoBitRate;
        }
        if (videoResult$Stub.audioBitRate <= 0 && b2) {
            videoResult$Stub.audioBitRate = this.mProfile.audioBitRate;
        }
        if (b) {
            final int audioCodec = this.mProfile.audioCodec;
            String s2;
            final String s = s2 = "audio/3gpp";
            while (true) {
                switch (audioCodec) {
                    default: {
                        s2 = s;
                    }
                    case 1: {
                        final int videoCodec = this.mProfile.videoCodec;
                        final String s3 = "video/avc";
                        String s4;
                        if (videoCodec != 1) {
                            s4 = s3;
                            if (videoCodec != 2) {
                                if (videoCodec != 3) {
                                    if (videoCodec != 4) {
                                        if (videoCodec != 5) {
                                            s4 = s3;
                                        }
                                        else {
                                            s4 = "video/hevc";
                                        }
                                    }
                                    else {
                                        s4 = "video/x-vnd.on2.vp8";
                                    }
                                }
                                else {
                                    s4 = "video/mp4v-es";
                                }
                            }
                        }
                        else {
                            s4 = "video/3gpp";
                        }
                        final boolean b3 = videoResult$Stub.rotation % 180 != 0;
                        if (b3) {
                            videoResult$Stub.size = videoResult$Stub.size.flip();
                        }
                        int videoBitRate = 0;
                        final DeviceEncoders$VideoException ex = null;
                        int i = 0;
                        int audioBitRate = 0;
                        int supportedAudioBitRate = 0;
                        int n = 0;
                        final int n2 = 0;
                        final String s5 = s4;
                        Object size = ex;
                        while (i == 0) {
                            FullVideoRecorder.LOG.i(new Object[] { "prepareMediaRecorder:", "Checking DeviceEncoders...", "videoOffset:", n, "audioOffset:", n2 });
                            try {
                                final DeviceEncoders deviceEncoders = new DeviceEncoders(0, s5, s2, n, n2);
                                Object supportedVideoSize = null;
                                Label_0876: {
                                    try {
                                        supportedVideoSize = deviceEncoders.getSupportedVideoSize(videoResult$Stub.size);
                                        int supportedVideoBitRate = videoBitRate;
                                        int n3 = videoBitRate;
                                        try {
                                            videoBitRate = (n3 = (supportedVideoBitRate = deviceEncoders.getSupportedVideoBitRate(videoResult$Stub.videoBitRate)));
                                            final int supportedVideoFrameRate = deviceEncoders.getSupportedVideoFrameRate((Size)supportedVideoSize, videoResult$Stub.videoFrameRate);
                                            try {
                                                deviceEncoders.tryConfigureVideo(s5, (Size)supportedVideoSize, supportedVideoFrameRate, videoBitRate);
                                                supportedAudioBitRate = audioBitRate;
                                                if (b2) {
                                                    supportedAudioBitRate = deviceEncoders.getSupportedAudioBitRate(videoResult$Stub.audioBitRate);
                                                    try {
                                                        deviceEncoders.tryConfigureAudio(s2, supportedAudioBitRate, this.mProfile.audioSampleRate, audioChannels);
                                                    }
                                                    catch (final DeviceEncoders$AudioException size) {
                                                        audioBitRate = supportedAudioBitRate;
                                                        supportedAudioBitRate = supportedVideoFrameRate;
                                                        goto Label_0827;
                                                    }
                                                    catch (final DeviceEncoders$VideoException size) {
                                                        audioBitRate = supportedAudioBitRate;
                                                        supportedAudioBitRate = supportedVideoFrameRate;
                                                        break Label_0876;
                                                    }
                                                }
                                                i = 1;
                                                size = supportedVideoSize;
                                                audioBitRate = supportedAudioBitRate;
                                                supportedAudioBitRate = supportedVideoFrameRate;
                                            }
                                            catch (final DeviceEncoders$AudioException size) {
                                                supportedAudioBitRate = supportedVideoFrameRate;
                                            }
                                            catch (final DeviceEncoders$VideoException size) {
                                                supportedAudioBitRate = supportedVideoFrameRate;
                                            }
                                        }
                                        catch (final DeviceEncoders$AudioException size) {
                                            videoBitRate = supportedVideoBitRate;
                                        }
                                        catch (final DeviceEncoders$VideoException size) {
                                            videoBitRate = n3;
                                        }
                                    }
                                    catch (final DeviceEncoders$AudioException ex2) {
                                        supportedVideoSize = size;
                                        size = ex2;
                                    }
                                    catch (final DeviceEncoders$VideoException ex3) {
                                        supportedVideoSize = size;
                                        size = ex3;
                                    }
                                }
                                FullVideoRecorder.LOG.i(new Object[] { "prepareMediaRecorder:", "Got VideoException:", ((DeviceEncoders$VideoException)size).getMessage() });
                                ++n;
                                size = supportedVideoSize;
                                continue;
                            }
                            catch (final RuntimeException ex4) {
                                FullVideoRecorder.LOG.w(new Object[] { "prepareMediaRecorder:", "Could not respect encoders parameters.", "Trying again without checking encoders." });
                                return this.prepareMediaRecorder(videoResult$Stub, false);
                            }
                            break;
                        }
                        videoResult$Stub.size = (Size)size;
                        videoResult$Stub.videoBitRate = videoBitRate;
                        videoResult$Stub.audioBitRate = audioBitRate;
                        videoResult$Stub.videoFrameRate = supportedAudioBitRate;
                        if (b3) {
                            videoResult$Stub.size = videoResult$Stub.size.flip();
                            break;
                        }
                        break;
                    }
                    case 6: {
                        s2 = "audio/vorbis";
                        continue;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        s2 = "audio/mp4a-latm";
                        continue;
                    }
                    case 2: {
                        s2 = "audio/amr-wb";
                        continue;
                    }
                }
                break;
            }
        }
        final boolean b4 = videoResult$Stub.rotation % 180 != 0;
        final MediaRecorder mMediaRecorder = this.mMediaRecorder;
        final Size size2 = videoResult$Stub.size;
        int n4;
        if (b4) {
            n4 = size2.getHeight();
        }
        else {
            n4 = size2.getWidth();
        }
        int n5;
        if (b4) {
            n5 = videoResult$Stub.size.getWidth();
        }
        else {
            n5 = videoResult$Stub.size.getHeight();
        }
        mMediaRecorder.setVideoSize(n4, n5);
        this.mMediaRecorder.setVideoFrameRate(videoResult$Stub.videoFrameRate);
        this.mMediaRecorder.setVideoEncoder(this.mProfile.videoCodec);
        this.mMediaRecorder.setVideoEncodingBitRate(videoResult$Stub.videoBitRate);
        if (b2) {
            this.mMediaRecorder.setAudioChannels(audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(this.mProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(this.mProfile.audioCodec);
            this.mMediaRecorder.setAudioEncodingBitRate(videoResult$Stub.audioBitRate);
        }
        if (videoResult$Stub.location != null) {
            this.mMediaRecorder.setLocation((float)videoResult$Stub.location.getLatitude(), (float)videoResult$Stub.location.getLongitude());
        }
        if (videoResult$Stub.file != null) {
            this.mMediaRecorder.setOutputFile(videoResult$Stub.file.getAbsolutePath());
        }
        else {
            if (videoResult$Stub.fileDescriptor == null) {
                throw new IllegalStateException("file and fileDescriptor are both null.");
            }
            this.mMediaRecorder.setOutputFile(videoResult$Stub.fileDescriptor);
        }
        this.mMediaRecorder.setOrientationHint(videoResult$Stub.rotation);
        final MediaRecorder mMediaRecorder2 = this.mMediaRecorder;
        long maxFileSize;
        if (videoResult$Stub.maxSize <= 0L) {
            maxFileSize = videoResult$Stub.maxSize;
        }
        else {
            maxFileSize = Math.round(videoResult$Stub.maxSize / 0.9);
        }
        mMediaRecorder2.setMaxFileSize(maxFileSize);
        FullVideoRecorder.LOG.i(new Object[] { "prepareMediaRecorder:", "Increased max size from", videoResult$Stub.maxSize, "to", Math.round(videoResult$Stub.maxSize / 0.9) });
        this.mMediaRecorder.setMaxDuration(videoResult$Stub.maxDuration);
        this.mMediaRecorder.setOnInfoListener((MediaRecorder$OnInfoListener)new FullVideoRecorder$1(this));
        this.mMediaRecorder.setOnErrorListener((MediaRecorder$OnErrorListener)new FullVideoRecorder$2(this));
        try {
            this.mMediaRecorder.prepare();
            this.mMediaRecorderPrepared = true;
            this.mError = null;
            return true;
        }
        catch (final Exception mError) {
            FullVideoRecorder.LOG.w(new Object[] { "prepareMediaRecorder:", "Error while preparing media recorder.", mError });
            this.mMediaRecorderPrepared = false;
            this.mError = mError;
            return false;
        }
        throw new IllegalStateException("file and fileDescriptor are both null.");
    }
    
    protected abstract void applyVideoSource(final VideoResult$Stub p0, final MediaRecorder p1);
    
    protected abstract CamcorderProfile getCamcorderProfile(final VideoResult$Stub p0);
    
    protected void onStart() {
        if (!this.prepareMediaRecorder(this.mResult)) {
            this.mResult = null;
            this.stop(false);
            return;
        }
        try {
            this.mMediaRecorder.start();
            this.dispatchVideoRecordingStart();
        }
        catch (final Exception mError) {
            FullVideoRecorder.LOG.w(new Object[] { "start:", "Error while starting media recorder.", mError });
            this.mResult = null;
            this.mError = mError;
            this.stop(false);
        }
    }
    
    protected void onStop(final boolean b) {
        if (this.mMediaRecorder != null) {
            this.dispatchVideoRecordingEnd();
            try {
                FullVideoRecorder.LOG.i(new Object[] { "stop:", "Stopping MediaRecorder..." });
                this.mMediaRecorder.stop();
                FullVideoRecorder.LOG.i(new Object[] { "stop:", "Stopped MediaRecorder." });
            }
            catch (final Exception mError) {
                this.mResult = null;
                if (this.mError == null) {
                    FullVideoRecorder.LOG.w(new Object[] { "stop:", "Error while closing media recorder.", mError });
                    this.mError = mError;
                }
            }
            try {
                FullVideoRecorder.LOG.i(new Object[] { "stop:", "Releasing MediaRecorder..." });
                this.mMediaRecorder.release();
                FullVideoRecorder.LOG.i(new Object[] { "stop:", "Released MediaRecorder." });
            }
            catch (final Exception mError2) {
                this.mResult = null;
                if (this.mError == null) {
                    FullVideoRecorder.LOG.w(new Object[] { "stop:", "Error while releasing media recorder.", mError2 });
                    this.mError = mError2;
                }
            }
        }
        this.mProfile = null;
        this.mMediaRecorder = null;
        this.mMediaRecorderPrepared = false;
        this.dispatchResult();
    }
    
    protected final boolean prepareMediaRecorder(final VideoResult$Stub videoResult$Stub) {
        return this.mMediaRecorderPrepared || this.prepareMediaRecorder(videoResult$Stub, true);
    }
}
