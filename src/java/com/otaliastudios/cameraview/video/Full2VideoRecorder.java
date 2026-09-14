package com.otaliastudios.cameraview.video;

import com.otaliastudios.cameraview.engine.action.ActionCallback;
import com.otaliastudios.cameraview.engine.action.CompletionCallback;
import com.otaliastudios.cameraview.engine.action.Action;
import android.hardware.camera2.CaptureRequest;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.CamcorderProfiles;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import com.otaliastudios.cameraview.VideoResult$Stub;
import com.otaliastudios.cameraview.engine.Camera2Engine;
import android.view.Surface;
import com.otaliastudios.cameraview.engine.action.ActionHolder;

public class Full2VideoRecorder extends FullVideoRecorder
{
    private final String mCameraId;
    private ActionHolder mHolder;
    private Surface mInputSurface;
    
    public Full2VideoRecorder(final Camera2Engine mHolder, final String mCameraId) {
        super((VideoRecorder$VideoResultListener)mHolder);
        this.mHolder = (ActionHolder)mHolder;
        this.mCameraId = mCameraId;
    }
    
    static /* synthetic */ void access$001(final Full2VideoRecorder full2VideoRecorder) {
        full2VideoRecorder.onStart();
    }
    
    protected void applyVideoSource(final VideoResult$Stub videoResult$Stub, final MediaRecorder mediaRecorder) {
        mediaRecorder.setVideoSource(2);
    }
    
    public Surface createInputSurface(final VideoResult$Stub videoResult$Stub) throws Full2VideoRecorder.Full2VideoRecorder$PrepareException {
        if (this.prepareMediaRecorder(videoResult$Stub)) {
            return this.mInputSurface = this.mMediaRecorder.getSurface();
        }
        throw new Full2VideoRecorder.Full2VideoRecorder$PrepareException(this, (Throwable)this.mError, (Full2VideoRecorder$1)null);
    }
    
    protected CamcorderProfile getCamcorderProfile(final VideoResult$Stub videoResult$Stub) {
        final int rotation = videoResult$Stub.rotation;
        Size size = videoResult$Stub.size;
        if (rotation % 180 != 0) {
            size = size.flip();
        }
        return CamcorderProfiles.get(this.mCameraId, size);
    }
    
    public Surface getInputSurface() {
        return this.mInputSurface;
    }
    
    protected void onStart() {
        final BaseAction baseAction = new BaseAction(this) {
            final Full2VideoRecorder this$0;
            
            public void onCaptureStarted(final ActionHolder actionHolder, final CaptureRequest captureRequest) {
                super.onCaptureStarted(actionHolder, captureRequest);
                final Object tag = actionHolder.getBuilder((Action)this).build().getTag();
                final Object tag2 = captureRequest.getTag();
                if (tag == null) {
                    if (tag2 != null) {
                        return;
                    }
                }
                else if (!tag.equals(tag2)) {
                    return;
                }
                this.setState(Integer.MAX_VALUE);
            }
        };
        ((Action)baseAction).addCallback((ActionCallback)new CompletionCallback(this) {
            final Full2VideoRecorder this$0;
            
            protected void onActionCompleted(final Action action) {
                Full2VideoRecorder.access$001(this.this$0);
            }
        });
        ((Action)baseAction).start(this.mHolder);
    }
}
