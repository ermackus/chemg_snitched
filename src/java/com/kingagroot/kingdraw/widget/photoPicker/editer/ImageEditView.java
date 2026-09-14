package com.kingagroot.kingdraw.widget.photoPicker.editer;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.content.Context;
import android.view.SurfaceHolder;
import android.graphics.Canvas;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceView;

public class ImageEditView extends SurfaceView implements SurfaceHolder$Callback, Runnable
{
    private EditerBaseView editerBaseView;
    private boolean invalidate;
    private Canvas mCanvas;
    private SurfaceHolder mHolder;
    private final EditerDrawTool phtotoDrawTool;
    private long refreshTime;
    private boolean runing;
    private Thread thread;
    
    public ImageEditView(final Context context) {
        this(context, null);
    }
    
    public ImageEditView(final Context context, final AttributeSet set) {
        super(context, set);
        this.phtotoDrawTool = new EditerDrawTool(this);
        this.runing = true;
        this.initView();
    }
    
    private void initView() {
        (this.mHolder = this.getHolder()).addCallback((SurfaceHolder$Callback)this);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }
    
    public RectF getImageRect() {
        return this.phtotoDrawTool.getImageRectF();
    }
    
    public void onPause() {
        this.runing = false;
    }
    
    public void onResume() {
        this.runing = true;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final EditerBaseView editerBaseView = this.editerBaseView;
        boolean onTouchEvent;
        if (!(onTouchEvent = (editerBaseView != null && editerBaseView.onTouchEvent(motionEvent)))) {
            onTouchEvent = super.onTouchEvent(motionEvent);
        }
        return onTouchEvent;
    }
    
    public void run() {
        while (this.runing) {
            if (this.invalidate) {
                try {
                    try {
                        (this.mCanvas = this.mHolder.lockCanvas()).drawColor(-16777216);
                        this.phtotoDrawTool.onDraw(this.mCanvas);
                        if (this.editerBaseView != null) {
                            this.editerBaseView.onDraw(this.mCanvas);
                        }
                    }
                    finally {}
                }
                catch (final Exception ex) {
                    ex.printStackTrace();
                }
                this.mHolder.unlockCanvasAndPost(this.mCanvas);
                this.invalidate = false;
                continue;
                this.mHolder.unlockCanvasAndPost(this.mCanvas);
            }
        }
    }
    
    public void setInvalidate() {
        this.invalidate = true;
    }
    
    public void setPhoto(final Bitmap bitmap) {
        this.phtotoDrawTool.setBitmap(bitmap);
        this.invalidate = true;
        this.editerBaseView = (EditerBaseView)new EditerCutTool(this);
    }
    
    public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
    }
    
    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
        (this.thread = new Thread((Runnable)this)).start();
    }
    
    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
    }
}
