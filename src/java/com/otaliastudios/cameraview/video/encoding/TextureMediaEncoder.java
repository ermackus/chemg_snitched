package com.otaliastudios.cameraview.video.encoding;

import android.opengl.Matrix;
import com.otaliastudios.cameraview.filter.Filter;
import com.otaliastudios.cameraview.internal.Pool$Factory;
import com.otaliastudios.opengl.surface.EglWindowSurface;
import com.otaliastudios.cameraview.internal.Pool;
import com.otaliastudios.opengl.core.EglCore;
import com.otaliastudios.cameraview.internal.GlTextureDrawer;
import com.otaliastudios.cameraview.CameraLogger;

public class TextureMediaEncoder extends VideoMediaEncoder<TextureConfig>
{
    public static final String FILTER_EVENT = "filter";
    public static final String FRAME_EVENT = "frame";
    private static final CameraLogger LOG;
    private static final String TAG;
    private GlTextureDrawer mDrawer;
    private EglCore mEglCore;
    private long mFirstTimeUs;
    private Pool<TextureMediaEncoder.TextureMediaEncoder$Frame> mFramePool;
    private int mTransformRotation;
    private EglWindowSurface mWindow;
    
    static {
        LOG = CameraLogger.create(TAG = TextureMediaEncoder.class.getSimpleName());
    }
    
    public TextureMediaEncoder(final TextureConfig textureConfig) {
        super((VideoConfig)textureConfig.copy());
        this.mFramePool = (Pool<TextureMediaEncoder.TextureMediaEncoder$Frame>)new Pool(Integer.MAX_VALUE, (Pool$Factory)new TextureMediaEncoder$1(this));
        this.mFirstTimeUs = Long.MIN_VALUE;
    }
    
    private void onFilter(final Filter filter) {
        this.mDrawer.setFilter(filter);
    }
    
    private void onFrame(final TextureMediaEncoder.TextureMediaEncoder$Frame textureMediaEncoder$Frame) {
        if (!this.shouldRenderFrame(TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame))) {
            this.mFramePool.recycle((Object)textureMediaEncoder$Frame);
            return;
        }
        if (this.mFrameNumber == 1) {
            this.notifyFirstFrameMillis(textureMediaEncoder$Frame.timestampMillis);
        }
        if (this.mFirstTimeUs == Long.MIN_VALUE) {
            this.mFirstTimeUs = TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame);
        }
        if (!this.hasReachedMaxLength() && TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame) - this.mFirstTimeUs > this.getMaxLengthUs()) {
            TextureMediaEncoder.LOG.w(new Object[] { "onEvent -", "frameNumber:", this.mFrameNumber, "timestampUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame), "firstTimeUs:", this.mFirstTimeUs, "- reached max length! deltaUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame) - this.mFirstTimeUs });
            this.notifyMaxLengthReached();
        }
        TextureMediaEncoder.LOG.i(new Object[] { "onEvent -", "frameNumber:", this.mFrameNumber, "timestampUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame), "hasReachedMaxLength:", this.hasReachedMaxLength(), "thread:", Thread.currentThread(), "- draining." });
        this.drainOutput(false);
        TextureMediaEncoder.LOG.i(new Object[] { "onEvent -", "frameNumber:", this.mFrameNumber, "timestampUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame), "hasReachedMaxLength:", this.hasReachedMaxLength(), "thread:", Thread.currentThread(), "- drawing." });
        final float[] transform = textureMediaEncoder$Frame.transform;
        final float scaleX = ((TextureConfig)this.mConfig).scaleX;
        final float scaleY = ((TextureConfig)this.mConfig).scaleY;
        Matrix.translateM(transform, 0, (1.0f - scaleX) / 2.0f, (1.0f - scaleY) / 2.0f, 0.0f);
        Matrix.scaleM(transform, 0, scaleX, scaleY, 1.0f);
        Matrix.translateM(transform, 0, 0.5f, 0.5f, 0.0f);
        Matrix.rotateM(transform, 0, (float)this.mTransformRotation, 0.0f, 0.0f, 1.0f);
        Matrix.translateM(transform, 0, -0.5f, -0.5f, 0.0f);
        if (((TextureConfig)this.mConfig).hasOverlay()) {
            ((TextureConfig)this.mConfig).overlayDrawer.draw(((TextureConfig)this.mConfig).overlayTarget);
            Matrix.translateM(((TextureConfig)this.mConfig).overlayDrawer.getTransform(), 0, 0.5f, 0.5f, 0.0f);
            Matrix.rotateM(((TextureConfig)this.mConfig).overlayDrawer.getTransform(), 0, (float)((TextureConfig)this.mConfig).overlayRotation, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(((TextureConfig)this.mConfig).overlayDrawer.getTransform(), 0, -0.5f, -0.5f, 0.0f);
        }
        TextureMediaEncoder.LOG.i(new Object[] { "onEvent -", "frameNumber:", this.mFrameNumber, "timestampUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame), "hasReachedMaxLength:", this.hasReachedMaxLength(), "thread:", Thread.currentThread(), "- gl rendering." });
        this.mDrawer.setTextureTransform(transform);
        this.mDrawer.draw(TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame));
        if (((TextureConfig)this.mConfig).hasOverlay()) {
            ((TextureConfig)this.mConfig).overlayDrawer.render(TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame));
        }
        this.mWindow.setPresentationTime(textureMediaEncoder$Frame.timestampNanos);
        this.mWindow.swapBuffers();
        this.mFramePool.recycle((Object)textureMediaEncoder$Frame);
        TextureMediaEncoder.LOG.i(new Object[] { "onEvent -", "frameNumber:", this.mFrameNumber, "timestampUs:", TextureMediaEncoder.TextureMediaEncoder$Frame.access$100(textureMediaEncoder$Frame), "hasReachedMaxLength:", this.hasReachedMaxLength(), "thread:", Thread.currentThread(), "- gl rendered." });
    }
    
    public TextureMediaEncoder.TextureMediaEncoder$Frame acquireFrame() {
        if (!this.mFramePool.isEmpty()) {
            return (TextureMediaEncoder.TextureMediaEncoder$Frame)this.mFramePool.get();
        }
        throw new RuntimeException("Need more frames than this! Please increase the pool size.");
    }
    
    protected void onEvent(final String s, final Object o) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0052: {
            if (hashCode != -1274492040) {
                if (hashCode == 97692013) {
                    if (s.equals((Object)"frame")) {
                        n = 1;
                        break Label_0052;
                    }
                }
            }
            else if (s.equals((Object)"filter")) {
                n = 0;
                break Label_0052;
            }
            n = -1;
        }
        if (n != 0) {
            if (n == 1) {
                this.onFrame((TextureMediaEncoder.TextureMediaEncoder$Frame)o);
            }
        }
        else {
            this.onFilter((Filter)o);
        }
    }
    
    protected void onPrepare(final MediaEncoderEngine$Controller mediaEncoderEngine$Controller, final long n) {
        this.mTransformRotation = ((TextureConfig)this.mConfig).rotation;
        ((TextureConfig)this.mConfig).rotation = 0;
        super.onPrepare(mediaEncoderEngine$Controller, n);
        final EglCore mEglCore = new EglCore(((TextureConfig)this.mConfig).eglContext, 1);
        this.mEglCore = mEglCore;
        (this.mWindow = new EglWindowSurface(mEglCore, this.mSurface, true)).makeCurrent();
        this.mDrawer = new GlTextureDrawer(((TextureConfig)this.mConfig).textureId);
    }
    
    protected void onStopped() {
        super.onStopped();
        this.mFramePool.clear();
        final EglWindowSurface mWindow = this.mWindow;
        if (mWindow != null) {
            mWindow.release();
            this.mWindow = null;
        }
        final GlTextureDrawer mDrawer = this.mDrawer;
        if (mDrawer != null) {
            mDrawer.release();
            this.mDrawer = null;
        }
        final EglCore mEglCore = this.mEglCore;
        if (mEglCore != null) {
            mEglCore.release();
            this.mEglCore = null;
        }
    }
    
    protected boolean shouldRenderFrame(final long n) {
        if (!super.shouldRenderFrame(n)) {
            TextureMediaEncoder.LOG.i(new Object[] { "shouldRenderFrame - Dropping frame because of super()" });
            return false;
        }
        if (this.mFrameNumber <= 10) {
            return true;
        }
        if (this.getPendingEvents("frame") > 2) {
            TextureMediaEncoder.LOG.i(new Object[] { "shouldRenderFrame - Dropping, we already have too many pending events:", this.getPendingEvents("frame") });
            return false;
        }
        return true;
    }
}
