package com.otaliastudios.cameraview.video;

import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class VideoRecorder
{
    private static final CameraLogger LOG;
    private static final int STATE_IDLE = 0;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_STOPPING = 2;
    private static final String TAG;
    protected Exception mError;
    private final VideoResultListener mListener;
    VideoResult.Stub mResult;
    private int mState;
    private final Object mStateLock;
    
    static {
        LOG = CameraLogger.create(TAG = VideoRecorder.class.getSimpleName());
    }
    
    VideoRecorder(final VideoResultListener mListener) {
        this.mStateLock = new Object();
        this.mListener = mListener;
        this.mState = 0;
    }
    
    protected final void dispatchResult() {
        final Object mStateLock = this.mStateLock;
        synchronized (mStateLock) {
            if (!this.isRecording()) {
                VideoRecorder.LOG.w(new Object[] { "dispatchResult:", "Called, but not recording! Aborting." });
                return;
            }
            VideoRecorder.LOG.i(new Object[] { "dispatchResult:", "Changed state to STATE_IDLE." });
            this.mState = 0;
            monitorexit(mStateLock);
            this.onDispatchResult();
            VideoRecorder.LOG.i(new Object[] { "dispatchResult:", "About to dispatch result:", this.mResult, this.mError });
            final VideoResultListener mListener = this.mListener;
            if (mListener != null) {
                mListener.onVideoResult(this.mResult, this.mError);
            }
            this.mResult = null;
            this.mError = null;
        }
    }
    
    protected void dispatchVideoRecordingEnd() {
        VideoRecorder.LOG.i(new Object[] { "dispatchVideoRecordingEnd:", "About to dispatch." });
        final VideoResultListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onVideoRecordingEnd();
        }
    }
    
    protected void dispatchVideoRecordingStart() {
        VideoRecorder.LOG.i(new Object[] { "dispatchVideoRecordingStart:", "About to dispatch." });
        final VideoResultListener mListener = this.mListener;
        if (mListener != null) {
            mListener.onVideoRecordingStart();
        }
    }
    
    public boolean isRecording() {
        final Object mStateLock = this.mStateLock;
        synchronized (mStateLock) {
            return this.mState != 0;
        }
    }
    
    protected void onDispatchResult() {
    }
    
    protected abstract void onStart();
    
    protected abstract void onStop(final boolean p0);
    
    public final void start(final VideoResult.Stub mResult) {
        final Object mStateLock = this.mStateLock;
        synchronized (mStateLock) {
            if (this.mState != 0) {
                VideoRecorder.LOG.e(new Object[] { "start:", "called twice, or while stopping! Ignoring. state:", this.mState });
                return;
            }
            VideoRecorder.LOG.i(new Object[] { "start:", "Changed state to STATE_RECORDING" });
            this.mState = 1;
            monitorexit(mStateLock);
            this.mResult = mResult;
            this.onStart();
        }
    }
    
    public final void stop(final boolean b) {
        final Object mStateLock = this.mStateLock;
        synchronized (mStateLock) {
            if (this.mState == 0) {
                VideoRecorder.LOG.e(new Object[] { "stop:", "called twice, or called before start! Ignoring. isCameraShutdown:", b });
                return;
            }
            VideoRecorder.LOG.i(new Object[] { "stop:", "Changed state to STATE_STOPPING" });
            this.mState = 2;
            monitorexit(mStateLock);
            this.onStop(b);
        }
    }
    
    public interface VideoResultListener
    {
        void onVideoRecordingEnd();
        
        void onVideoRecordingStart();
        
        void onVideoResult(final VideoResult.Stub p0, final Exception p1);
    }
}
