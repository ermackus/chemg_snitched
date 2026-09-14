package com.otaliastudios.cameraview.preview;

import android.view.SurfaceHolder$Callback;
import android.opengl.GLSurfaceView$Renderer;
import com.otaliastudios.cameraview.R$id;
import com.otaliastudios.cameraview.R$layout;
import android.view.LayoutInflater;
import com.otaliastudios.cameraview.size.AspectRatio;
import java.util.concurrent.CopyOnWriteArraySet;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import java.util.Set;
import com.otaliastudios.cameraview.internal.GlTextureDrawer;
import com.otaliastudios.cameraview.filter.Filter;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;

public class GlCameraPreview extends CameraPreview<GLSurfaceView, SurfaceTexture> implements FilterCameraPreview, RendererCameraPreview
{
    float mCropScaleX;
    float mCropScaleY;
    private Filter mCurrentFilter;
    private boolean mDispatched;
    private SurfaceTexture mInputSurfaceTexture;
    private GlTextureDrawer mOutputTextureDrawer;
    private final Set<RendererFrameCallback> mRendererFrameCallbacks;
    private View mRootView;
    
    public GlCameraPreview(final Context context, final ViewGroup viewGroup) {
        super(context, viewGroup);
        this.mRendererFrameCallbacks = (Set<RendererFrameCallback>)new CopyOnWriteArraySet();
        this.mCropScaleX = 1.0f;
        this.mCropScaleY = 1.0f;
    }
    
    public void addRendererFrameCallback(final RendererFrameCallback rendererFrameCallback) {
        ((GLSurfaceView)this.getView()).queueEvent((Runnable)new GlCameraPreview$2(this, rendererFrameCallback));
    }
    
    protected void crop(final CameraPreview$CropCallback cameraPreview$CropCallback) {
        if (this.mInputStreamWidth > 0 && this.mInputStreamHeight > 0 && this.mOutputSurfaceWidth > 0 && this.mOutputSurfaceHeight > 0) {
            final AspectRatio of = AspectRatio.of(this.mOutputSurfaceWidth, this.mOutputSurfaceHeight);
            final AspectRatio of2 = AspectRatio.of(this.mInputStreamWidth, this.mInputStreamHeight);
            float n;
            float n2;
            if (of.toFloat() >= of2.toFloat()) {
                n = of.toFloat() / of2.toFloat();
                n2 = 1.0f;
            }
            else {
                n2 = of2.toFloat() / of.toFloat();
                n = 1.0f;
            }
            this.mCropping = (n2 > 1.02f || n > 1.02f);
            this.mCropScaleX = 1.0f / n2;
            this.mCropScaleY = 1.0f / n;
            ((GLSurfaceView)this.getView()).requestRender();
        }
        if (cameraPreview$CropCallback != null) {
            cameraPreview$CropCallback.onCrop();
        }
    }
    
    public Filter getCurrentFilter() {
        return this.mCurrentFilter;
    }
    
    public SurfaceTexture getOutput() {
        return this.mInputSurfaceTexture;
    }
    
    public Class<SurfaceTexture> getOutputClass() {
        return SurfaceTexture.class;
    }
    
    public View getRootView() {
        return this.mRootView;
    }
    
    protected int getTextureId() {
        final GlTextureDrawer mOutputTextureDrawer = this.mOutputTextureDrawer;
        int id;
        if (mOutputTextureDrawer != null) {
            id = mOutputTextureDrawer.getTexture().getId();
        }
        else {
            id = -1;
        }
        return id;
    }
    
    protected GlCameraPreview.GlCameraPreview$Renderer instantiateRenderer() {
        return new GlCameraPreview.GlCameraPreview$Renderer(this);
    }
    
    protected GLSurfaceView onCreateView(final Context context, final ViewGroup viewGroup) {
        final ViewGroup mRootView = (ViewGroup)LayoutInflater.from(context).inflate(R$layout.cameraview_gl_view, viewGroup, false);
        final GLSurfaceView glSurfaceView = (GLSurfaceView)mRootView.findViewById(R$id.gl_surface_view);
        final GlCameraPreview.GlCameraPreview$Renderer instantiateRenderer = this.instantiateRenderer();
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer((GLSurfaceView$Renderer)instantiateRenderer);
        glSurfaceView.setRenderMode(0);
        glSurfaceView.getHolder().addCallback((SurfaceHolder$Callback)new GlCameraPreview$1(this, glSurfaceView, instantiateRenderer));
        viewGroup.addView((View)mRootView, 0);
        this.mRootView = (View)mRootView;
        return glSurfaceView;
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.mRendererFrameCallbacks.clear();
    }
    
    public void onPause() {
        super.onPause();
        ((GLSurfaceView)this.getView()).onPause();
    }
    
    public void onResume() {
        super.onResume();
        ((GLSurfaceView)this.getView()).onResume();
    }
    
    public void removeRendererFrameCallback(final RendererFrameCallback rendererFrameCallback) {
        this.mRendererFrameCallbacks.remove((Object)rendererFrameCallback);
    }
    
    public void setFilter(final Filter mCurrentFilter) {
        this.mCurrentFilter = mCurrentFilter;
        if (this.hasSurface()) {
            mCurrentFilter.setSize(this.mOutputSurfaceWidth, this.mOutputSurfaceHeight);
        }
        ((GLSurfaceView)this.getView()).queueEvent((Runnable)new GlCameraPreview$3(this, mCurrentFilter));
    }
    
    public boolean supportsCropping() {
        return true;
    }
}
