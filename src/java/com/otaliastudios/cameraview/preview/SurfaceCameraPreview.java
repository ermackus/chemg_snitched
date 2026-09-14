package com.otaliastudios.cameraview.preview;

import android.view.SurfaceHolder$Callback;
import com.otaliastudios.cameraview.R$id;
import com.otaliastudios.cameraview.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import com.otaliastudios.cameraview.CameraLogger;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceCameraPreview extends CameraPreview<SurfaceView, SurfaceHolder>
{
    private static final CameraLogger LOG;
    private boolean mDispatched;
    private View mRootView;
    
    static {
        LOG = CameraLogger.create(SurfaceCameraPreview.class.getSimpleName());
    }
    
    public SurfaceCameraPreview(final Context context, final ViewGroup viewGroup) {
        super(context, viewGroup);
    }
    
    public SurfaceHolder getOutput() {
        return ((SurfaceView)this.getView()).getHolder();
    }
    
    public Class<SurfaceHolder> getOutputClass() {
        return SurfaceHolder.class;
    }
    
    public View getRootView() {
        return this.mRootView;
    }
    
    protected SurfaceView onCreateView(final Context context, final ViewGroup viewGroup) {
        final View inflate = LayoutInflater.from(context).inflate(R$layout.cameraview_surface_view, viewGroup, false);
        viewGroup.addView(inflate, 0);
        final SurfaceView surfaceView = (SurfaceView)inflate.findViewById(R$id.surface_view);
        final SurfaceHolder holder = surfaceView.getHolder();
        holder.setType(3);
        holder.addCallback((SurfaceHolder$Callback)new SurfaceCameraPreview$1(this));
        this.mRootView = inflate;
        return surfaceView;
    }
}
