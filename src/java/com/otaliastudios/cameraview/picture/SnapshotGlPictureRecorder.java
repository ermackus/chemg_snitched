package com.otaliastudios.cameraview.picture;

import android.graphics.Bitmap$CompressFormat;
import android.opengl.Matrix;
import com.otaliastudios.opengl.surface.EglWindowSurface;
import com.otaliastudios.opengl.core.EglCore;
import android.opengl.EGLContext;
import com.otaliastudios.cameraview.preview.RendererFrameCallback;
import android.graphics.Rect;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.CropHelper;
import com.otaliastudios.cameraview.internal.WorkerHandler;
import android.opengl.EGL14;
import android.graphics.SurfaceTexture;
import com.otaliastudios.cameraview.filter.Filter;
import com.otaliastudios.cameraview.overlay.Overlay$Target;
import com.otaliastudios.cameraview.PictureResult$Stub;
import com.otaliastudios.cameraview.internal.GlTextureDrawer;
import com.otaliastudios.cameraview.preview.RendererCameraPreview;
import com.otaliastudios.cameraview.overlay.OverlayDrawer;
import com.otaliastudios.cameraview.overlay.Overlay;
import com.otaliastudios.cameraview.size.AspectRatio;

public class SnapshotGlPictureRecorder extends SnapshotPictureRecorder
{
    private boolean mHasOverlay;
    private AspectRatio mOutputRatio;
    private Overlay mOverlay;
    private OverlayDrawer mOverlayDrawer;
    private RendererCameraPreview mPreview;
    private GlTextureDrawer mTextureDrawer;
    
    public SnapshotGlPictureRecorder(final PictureResult$Stub pictureResult$Stub, final PictureRecorder$PictureResultListener pictureRecorder$PictureResultListener, final RendererCameraPreview mPreview, final AspectRatio mOutputRatio, final Overlay mOverlay) {
        super(pictureResult$Stub, pictureRecorder$PictureResultListener);
        this.mPreview = mPreview;
        this.mOutputRatio = mOutputRatio;
        this.mOverlay = mOverlay;
        this.mHasOverlay = (mOverlay != null && mOverlay.drawsOn(Overlay$Target.PICTURE_SNAPSHOT));
    }
    
    protected void dispatchResult() {
        this.mOutputRatio = null;
        super.dispatchResult();
    }
    
    protected void onRendererFilterChanged(final Filter filter) {
        this.mTextureDrawer.setFilter(filter.copy());
    }
    
    protected void onRendererFrame(final SurfaceTexture surfaceTexture, final int n, final float n2, final float n3) {
        WorkerHandler.execute((Runnable)new SnapshotGlPictureRecorder$2(this, surfaceTexture, n, n2, n3, EGL14.eglGetCurrentContext()));
    }
    
    protected void onRendererTextureCreated(final int n) {
        this.mTextureDrawer = new GlTextureDrawer(n);
        final Rect computeCrop = CropHelper.computeCrop(this.mResult.size, this.mOutputRatio);
        this.mResult.size = new Size(computeCrop.width(), computeCrop.height());
        if (this.mHasOverlay) {
            this.mOverlayDrawer = new OverlayDrawer(this.mOverlay, this.mResult.size);
        }
    }
    
    public void take() {
        this.mPreview.addRendererFrameCallback((RendererFrameCallback)new SnapshotGlPictureRecorder$1(this));
    }
    
    protected void takeFrame(final SurfaceTexture surfaceTexture, final int n, final float n2, final float n3, final EGLContext eglContext) {
        final SurfaceTexture surfaceTexture2 = new SurfaceTexture(9999);
        surfaceTexture2.setDefaultBufferSize(this.mResult.size.getWidth(), this.mResult.size.getHeight());
        final EglCore eglCore = new EglCore(eglContext, 1);
        final EglWindowSurface eglWindowSurface = new EglWindowSurface(eglCore, surfaceTexture2);
        eglWindowSurface.makeCurrent();
        final float[] textureTransform = this.mTextureDrawer.getTextureTransform();
        surfaceTexture.getTransformMatrix(textureTransform);
        Matrix.translateM(textureTransform, 0, (1.0f - n2) / 2.0f, (1.0f - n3) / 2.0f, 0.0f);
        Matrix.scaleM(textureTransform, 0, n2, n3, 1.0f);
        Matrix.translateM(textureTransform, 0, 0.5f, 0.5f, 0.0f);
        Matrix.rotateM(textureTransform, 0, (float)(n + this.mResult.rotation), 0.0f, 0.0f, 1.0f);
        Matrix.scaleM(textureTransform, 0, 1.0f, -1.0f, 1.0f);
        Matrix.translateM(textureTransform, 0, -0.5f, -0.5f, 0.0f);
        if (this.mHasOverlay) {
            this.mOverlayDrawer.draw(Overlay$Target.PICTURE_SNAPSHOT);
            Matrix.translateM(this.mOverlayDrawer.getTransform(), 0, 0.5f, 0.5f, 0.0f);
            Matrix.rotateM(this.mOverlayDrawer.getTransform(), 0, (float)this.mResult.rotation, 0.0f, 0.0f, 1.0f);
            Matrix.scaleM(this.mOverlayDrawer.getTransform(), 0, 1.0f, -1.0f, 1.0f);
            Matrix.translateM(this.mOverlayDrawer.getTransform(), 0, -0.5f, -0.5f, 0.0f);
        }
        this.mResult.rotation = 0;
        final long n4 = surfaceTexture.getTimestamp() / 1000L;
        SnapshotGlPictureRecorder.LOG.i(new Object[] { "takeFrame:", "timestampUs:", n4 });
        this.mTextureDrawer.draw(n4);
        if (this.mHasOverlay) {
            this.mOverlayDrawer.render(n4);
        }
        this.mResult.data = eglWindowSurface.toByteArray(Bitmap$CompressFormat.JPEG);
        eglWindowSurface.release();
        this.mTextureDrawer.release();
        surfaceTexture2.release();
        if (this.mHasOverlay) {
            this.mOverlayDrawer.release();
        }
        eglCore.release();
        this.dispatchResult();
    }
}
