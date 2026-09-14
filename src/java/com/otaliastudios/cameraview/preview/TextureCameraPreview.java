package com.otaliastudios.cameraview.preview;

import java.util.concurrent.ExecutionException;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.view.TextureView$SurfaceTextureListener;
import com.otaliastudios.cameraview.R$id;
import com.otaliastudios.cameraview.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.graphics.SurfaceTexture;
import android.view.TextureView;

public class TextureCameraPreview extends CameraPreview<TextureView, SurfaceTexture>
{
    private View mRootView;
    
    public TextureCameraPreview(final Context context, final ViewGroup viewGroup) {
        super(context, viewGroup);
    }
    
    protected void crop(final CameraPreview$CropCallback cameraPreview$CropCallback) {
        ((TextureView)this.getView()).post((Runnable)new TextureCameraPreview$2(this, cameraPreview$CropCallback));
    }
    
    public SurfaceTexture getOutput() {
        return ((TextureView)this.getView()).getSurfaceTexture();
    }
    
    public Class<SurfaceTexture> getOutputClass() {
        return SurfaceTexture.class;
    }
    
    public View getRootView() {
        return this.mRootView;
    }
    
    protected TextureView onCreateView(final Context context, final ViewGroup viewGroup) {
        final View inflate = LayoutInflater.from(context).inflate(R$layout.cameraview_texture_view, viewGroup, false);
        viewGroup.addView(inflate, 0);
        final TextureView textureView = (TextureView)inflate.findViewById(R$id.texture_view);
        textureView.setSurfaceTextureListener((TextureView$SurfaceTextureListener)new TextureCameraPreview$1(this));
        this.mRootView = inflate;
        return textureView;
    }
    
    public void setDrawRotation(final int drawRotation) {
        super.setDrawRotation(drawRotation);
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((TextureView)this.getView()).post((Runnable)new TextureCameraPreview$3(this, drawRotation, taskCompletionSource));
        try {
            Tasks.await(taskCompletionSource.getTask());
        }
        catch (final InterruptedException | ExecutionException ex) {}
    }
    
    public boolean supportsCropping() {
        return true;
    }
}
