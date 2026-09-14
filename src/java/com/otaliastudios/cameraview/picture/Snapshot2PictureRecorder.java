package com.otaliastudios.cameraview.picture;

import android.hardware.camera2.CaptureRequest$Builder;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import com.otaliastudios.cameraview.engine.action.ActionCallback;
import com.otaliastudios.cameraview.engine.action.CompletionCallback;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.engine.action.Actions;
import com.otaliastudios.cameraview.engine.lock.LockAction;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.preview.RendererCameraPreview;
import com.otaliastudios.cameraview.engine.Camera2Engine;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.engine.action.ActionHolder;
import com.otaliastudios.cameraview.engine.action.Action;

public class Snapshot2PictureRecorder extends SnapshotGlPictureRecorder
{
    private static final long LOCK_TIMEOUT = 2500L;
    private final Action mAction;
    private final boolean mActionNeeded;
    private final ActionHolder mHolder;
    private Integer mOriginalAeMode;
    private Integer mOriginalFlashMode;
    
    public Snapshot2PictureRecorder(final PictureResult$Stub pictureResult$Stub, final Camera2Engine mHolder, final RendererCameraPreview rendererCameraPreview, final AspectRatio aspectRatio) {
        super(pictureResult$Stub, (PictureRecorder$PictureResultListener)mHolder, rendererCameraPreview, aspectRatio, mHolder.getOverlay());
        this.mHolder = (ActionHolder)mHolder;
        final BaseAction timeout = Actions.timeout(2500L, (BaseAction)new LockAction());
        final boolean b = false;
        Integer n = null;
        (this.mAction = (Action)Actions.sequence(new BaseAction[] { timeout, new FlashAction() })).addCallback((ActionCallback)new CompletionCallback(this) {
            final Snapshot2PictureRecorder this$0;
            
            protected void onActionCompleted(final Action action) {
                SnapshotPictureRecorder.LOG.i(new Object[] { "Taking picture with super.take()." });
                this.this$0.take();
            }
        });
        final TotalCaptureResult lastResult = this.mHolder.getLastResult(this.mAction);
        if (lastResult == null) {
            Snapshot2PictureRecorder.LOG.w(new Object[] { "Picture snapshot requested very early, before the first preview frame.", "Metering might not work as intended." });
        }
        if (lastResult != null) {
            n = (Integer)((CaptureResult)lastResult).get(CaptureResult.CONTROL_AE_STATE);
        }
        boolean mActionNeeded = b;
        if (mHolder.getPictureSnapshotMetering()) {
            mActionNeeded = b;
            if (n != null) {
                mActionNeeded = b;
                if (n == 4) {
                    mActionNeeded = true;
                }
            }
        }
        this.mActionNeeded = mActionNeeded;
        this.mOriginalAeMode = (Integer)this.mHolder.getBuilder(this.mAction).get(CaptureRequest.CONTROL_AE_MODE);
        this.mOriginalFlashMode = (Integer)this.mHolder.getBuilder(this.mAction).get(CaptureRequest.FLASH_MODE);
    }
    
    @Override
    protected void dispatchResult() {
        new ResetFlashAction().start(this.mHolder);
        super.dispatchResult();
    }
    
    @Override
    public void take() {
        if (!this.mActionNeeded) {
            Snapshot2PictureRecorder.LOG.i(new Object[] { "take:", "Engine does no metering or needs no flash.", "Taking fast snapshot." });
            super.take();
        }
        else {
            Snapshot2PictureRecorder.LOG.i(new Object[] { "take:", "Engine needs flash. Starting action" });
            this.mAction.start(this.mHolder);
        }
    }
    
    private class FlashAction extends BaseAction
    {
        final Snapshot2PictureRecorder this$0;
        
        private FlashAction(final Snapshot2PictureRecorder this$0) {
            this.this$0 = this$0;
        }
        
        public void onCaptureCompleted(final ActionHolder actionHolder, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
            super.onCaptureCompleted(actionHolder, captureRequest, totalCaptureResult);
            final Integer n = (Integer)totalCaptureResult.get(CaptureResult.FLASH_STATE);
            if (n == null) {
                SnapshotPictureRecorder.LOG.w(new Object[] { "FlashAction:", "Waiting flash, but flashState is null!", "Taking snapshot." });
                this.setState(Integer.MAX_VALUE);
            }
            else if (n == 3) {
                SnapshotPictureRecorder.LOG.i(new Object[] { "FlashAction:", "Waiting flash and we have FIRED state!", "Taking snapshot." });
                this.setState(Integer.MAX_VALUE);
            }
            else {
                SnapshotPictureRecorder.LOG.i(new Object[] { "FlashAction:", "Waiting flash but flashState is", n, ". Waiting..." });
            }
        }
        
        protected void onStart(final ActionHolder actionHolder) {
            super.onStart(actionHolder);
            SnapshotPictureRecorder.LOG.i(new Object[] { "FlashAction:", "Parameters locked, opening torch." });
            actionHolder.getBuilder((Action)this).set(CaptureRequest.FLASH_MODE, (Object)2);
            actionHolder.getBuilder((Action)this).set(CaptureRequest.CONTROL_AE_MODE, (Object)1);
            actionHolder.applyBuilder((Action)this);
        }
    }
    
    private class ResetFlashAction extends BaseAction
    {
        final Snapshot2PictureRecorder this$0;
        
        private ResetFlashAction(final Snapshot2PictureRecorder this$0) {
            this.this$0 = this$0;
        }
        
        protected void onStart(final ActionHolder actionHolder) {
            super.onStart(actionHolder);
            try {
                SnapshotPictureRecorder.LOG.i(new Object[] { "ResetFlashAction:", "Reverting the flash changes." });
                final CaptureRequest$Builder builder = actionHolder.getBuilder((Action)this);
                builder.set(CaptureRequest.CONTROL_AE_MODE, (Object)1);
                builder.set(CaptureRequest.FLASH_MODE, (Object)0);
                actionHolder.applyBuilder((Action)this, builder);
                builder.set(CaptureRequest.CONTROL_AE_MODE, (Object)this.this$0.mOriginalAeMode);
                builder.set(CaptureRequest.FLASH_MODE, (Object)this.this$0.mOriginalFlashMode);
                actionHolder.applyBuilder((Action)this);
            }
            catch (final CameraAccessException ex) {}
        }
    }
}
