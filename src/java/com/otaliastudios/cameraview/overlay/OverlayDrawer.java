package com.otaliastudios.cameraview.overlay;

import android.opengl.GLES20;
import android.graphics.Canvas;
import android.view.Surface$OutOfResourcesException;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.os.Build$VERSION;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.internal.GlTextureDrawer;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.otaliastudios.cameraview.internal.Issue514Workaround;
import com.otaliastudios.cameraview.CameraLogger;

public class OverlayDrawer
{
    private static final CameraLogger LOG;
    private static final String TAG;
    private Issue514Workaround mIssue514Workaround;
    private final Object mIssue514WorkaroundLock;
    private Overlay mOverlay;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    GlTextureDrawer mTextureDrawer;
    
    static {
        LOG = CameraLogger.create(TAG = OverlayDrawer.class.getSimpleName());
    }
    
    public OverlayDrawer(final Overlay mOverlay, final Size size) {
        this.mIssue514WorkaroundLock = new Object();
        this.mOverlay = mOverlay;
        this.mTextureDrawer = new GlTextureDrawer();
        (this.mSurfaceTexture = new SurfaceTexture(this.mTextureDrawer.getTexture().getId())).setDefaultBufferSize(size.getWidth(), size.getHeight());
        this.mSurface = new Surface(this.mSurfaceTexture);
        this.mIssue514Workaround = new Issue514Workaround(this.mTextureDrawer.getTexture().getId());
    }
    
    public void draw(final Overlay.Target target) {
        try {
            Canvas canvas;
            if (Build$VERSION.SDK_INT >= 23 && this.mOverlay.getHardwareCanvasEnabled()) {
                canvas = this.mSurface.lockHardwareCanvas();
            }
            else {
                canvas = this.mSurface.lockCanvas((Rect)null);
            }
            canvas.drawColor(0, PorterDuff$Mode.CLEAR);
            this.mOverlay.drawOn(target, canvas);
            this.mSurface.unlockCanvasAndPost(canvas);
        }
        catch (final Surface$OutOfResourcesException ex) {
            OverlayDrawer.LOG.w(new Object[] { "Got Surface.OutOfResourcesException while drawing video overlays", ex });
        }
        final Object mIssue514WorkaroundLock = this.mIssue514WorkaroundLock;
        synchronized (mIssue514WorkaroundLock) {
            this.mIssue514Workaround.beforeOverlayUpdateTexImage();
            this.mSurfaceTexture.updateTexImage();
            monitorexit(mIssue514WorkaroundLock);
            this.mSurfaceTexture.getTransformMatrix(this.mTextureDrawer.getTextureTransform());
        }
    }
    
    public float[] getTransform() {
        return this.mTextureDrawer.getTextureTransform();
    }
    
    public void release() {
        final Issue514Workaround mIssue514Workaround = this.mIssue514Workaround;
        if (mIssue514Workaround != null) {
            mIssue514Workaround.end();
            this.mIssue514Workaround = null;
        }
        final SurfaceTexture mSurfaceTexture = this.mSurfaceTexture;
        if (mSurfaceTexture != null) {
            mSurfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        final Surface mSurface = this.mSurface;
        if (mSurface != null) {
            mSurface.release();
            this.mSurface = null;
        }
        final GlTextureDrawer mTextureDrawer = this.mTextureDrawer;
        if (mTextureDrawer != null) {
            mTextureDrawer.release();
            this.mTextureDrawer = null;
        }
    }
    
    public void render(final long n) {
        GLES20.glDisable(2884);
        GLES20.glDisable(2929);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        final Object mIssue514WorkaroundLock = this.mIssue514WorkaroundLock;
        synchronized (mIssue514WorkaroundLock) {
            this.mTextureDrawer.draw(n);
        }
    }
}
