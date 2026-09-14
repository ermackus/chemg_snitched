package com.kingagroot.kingdraw.core.view;

import android.os.Handler$Callback;
import java.util.Objects;
import android.os.Looper;
import java.lang.ref.WeakReference;
import android.os.Handler;
import android.os.Message;
import android.graphics.SurfaceTexture;
import android.view.TextureView$SurfaceTextureListener;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.PathEffect;
import android.graphics.DashPathEffect;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import android.graphics.PorterDuff$Mode;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import com.kingagroot.kingdraw.core.graphics.CanvasHolder;
import android.view.TextureView;

class GridLineView extends TextureView
{
    private static final int MSG_TYPE_UPDATA = 1;
    private CanvasHolder canvasHolder;
    private DrawThread drawThread;
    private boolean enable;
    private Paint paint;
    private Paint paintCenter;
    
    public GridLineView(final Context context) {
        super(context, (AttributeSet)null);
        this.enable = true;
        this.init();
    }
    
    private void drawGridLine() {
        if (!this.isAvailable()) {
            this.updataDelay(500L);
            return;
        }
        if (this.canvasHolder == null) {
            return;
        }
        final Canvas lockCanvas = this.lockCanvas();
        Matrix matrix;
        if ((matrix = this.canvasHolder.getMatrix()) == null) {
            matrix = new Matrix();
        }
        lockCanvas.concat(matrix);
        final PorterDuff$Mode clear = PorterDuff$Mode.CLEAR;
        final int n = 0;
        lockCanvas.drawColor(0, clear);
        final float scale = this.getScale(matrix);
        final float width = this.canvasHolder.getWidth();
        final float height = this.canvasHolder.getHeight();
        this.paint.reset();
        this.paint.setColor(-7829368);
        float n2;
        if (scale <= 0.25f) {
            n2 = GDensityUtil.cm2px(2.0f);
        }
        else {
            n2 = GDensityUtil.cm2px(1.0f);
            this.paint.setPathEffect((PathEffect)new DashPathEffect(new float[] { (float)GDensityUtil.dp2px(2.0f), (float)GDensityUtil.dp2px(2.0f) }, 0.0f));
        }
        this.paint.setStrokeJoin(Paint$Join.ROUND);
        this.paint.setStrokeCap(Paint$Cap.ROUND);
        final float[] array2;
        final float[] array = array2 = new float[2];
        array2[1] = (array2[0] = 0.0f);
        final float[] array3 = { width, height };
        final Matrix matrix2 = new Matrix();
        matrix.invert(matrix2);
        matrix2.mapPoints(array);
        matrix2.mapPoints(array3);
        final PointF pointF = new PointF(array[0], array[1]);
        final PointF pointF2 = new PointF(array3[0], array3[1]);
        final float x = pointF.x;
        final float y = pointF.y;
        final int n3 = (int)height;
        final int n4 = (int)width;
        final float n5 = (float)Math.floor((double)x);
        final float n6 = (float)Math.ceil((double)pointF2.x);
        final float n7 = (float)Math.floor((double)y);
        final float n8 = (float)Math.ceil((double)pointF2.y);
        final float n9 = (float)(n3 >> 1);
        if (n9 >= n7 && n9 <= n8) {
            lockCanvas.drawLine(n5, n9, n6, n9, this.paintCenter);
        }
        final float n10 = (float)(n4 >> 1);
        if (n10 >= n5 && n10 <= n6) {
            lockCanvas.drawLine(n10, n7, n10, n8, this.paintCenter);
        }
        final int n11 = (int)((pointF2.y - pointF.y) / n2 + 2.0f);
        final float y2 = pointF.y;
        final float y3 = pointF.y;
        for (int i = 0; i <= n11; ++i) {
            final float n12 = y2 + (n9 - y3) % n2 + i * n2;
            if (n12 != n9) {
                lockCanvas.drawLine(n5, n12, n6, n12, this.paint);
            }
        }
        final int n13 = (int)((pointF2.x - pointF.x) / n2 + 2.0f);
        final float x2 = pointF.x;
        final float x3 = pointF.x;
        for (int j = n; j <= n13; ++j) {
            final float n14 = x2 + (n10 - x3) % n2 + j * n2;
            if (n14 != n10) {
                lockCanvas.drawLine(n14, n7, n14, n8, this.paint);
            }
        }
        this.unlockCanvasAndPost(lockCanvas);
    }
    
    private float getScale(final Matrix matrix) {
        final float[] array = new float[9];
        matrix.getValues(array);
        return array[0];
    }
    
    private void init() {
        (this.paint = new Paint()).setColor(-7829368);
        this.paint.setPathEffect((PathEffect)new DashPathEffect(new float[] { (float)GDensityUtil.dp2px(2.0f), (float)GDensityUtil.dp2px(2.0f) }, 0.0f));
        this.paint.setStrokeJoin(Paint$Join.ROUND);
        this.paint.setStrokeCap(Paint$Cap.ROUND);
        (this.paintCenter = new Paint()).setColor(-16777216);
        this.paintCenter.setStrokeJoin(Paint$Join.ROUND);
        this.paintCenter.setStrokeCap(Paint$Cap.ROUND);
        this.setOpaque(false);
        this.setSurfaceTextureListener((TextureView$SurfaceTextureListener)new TextureView$SurfaceTextureListener(this) {
            final GridLineView this$0;
            
            public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
                surfaceTexture.setDefaultBufferSize(0, 0);
                this.this$0.updata();
            }
            
            public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
                return false;
            }
            
            public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
                this.this$0.updata();
            }
            
            public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
            }
        });
        (this.drawThread = new DrawThread(this)).start();
    }
    
    private void updataDelay(final long n) {
        if (this.enable) {
            final DrawThread drawThread = this.drawThread;
            if (drawThread != null && !drawThread.hasMessages(1)) {
                final Message message = new Message();
                message.what = 1;
                this.drawThread.sendMessageDelayed(message, n);
            }
        }
    }
    
    public boolean isEnable() {
        return this.enable;
    }
    
    public void onDestroy() {
        final DrawThread drawThread = this.drawThread;
        if (drawThread != null) {
            drawThread.removeMessages(1);
            this.drawThread.onDestroy();
        }
    }
    
    public void setCanvasHolder(final CanvasHolder canvasHolder) {
        this.canvasHolder = canvasHolder;
    }
    
    public void setEnable(final boolean enable) {
        if (!(this.enable = enable)) {
            this.setVisibility(8);
            final DrawThread drawThread = this.drawThread;
            if (drawThread != null) {
                drawThread.removeMessages(1);
            }
        }
        else {
            this.setVisibility(0);
            this.updata();
        }
    }
    
    public void updata() {
        this.updataDelay(0L);
    }
    
    private static class DrawThread extends Thread
    {
        private Handler handler;
        private WeakReference<GridLineView> reference;
        
        public DrawThread(final GridLineView gridLineView) {
            this.reference = (WeakReference<GridLineView>)new WeakReference((Object)gridLineView);
        }
        
        public boolean hasMessages(final int n) {
            final Handler handler = this.handler;
            return handler != null && handler.hasMessages(n);
        }
        
        public void onDestroy() {
            this.handler.getLooper().quit();
            this.reference.clear();
        }
        
        public void removeMessages(final int n) {
            final Handler handler = this.handler;
            if (handler != null) {
                handler.removeMessages(n);
            }
        }
        
        public void run() {
            super.run();
            Looper.prepare();
            this.handler = new Handler((Looper)Objects.requireNonNull((Object)Looper.myLooper()), (Handler$Callback)new Handler$Callback(this) {
                final DrawThread this$0;
                
                public boolean handleMessage(final Message message) {
                    if (message.what == 1) {
                        final GridLineView gridLineView = (GridLineView)this.this$0.reference.get();
                        if (gridLineView != null) {
                            gridLineView.drawGridLine();
                        }
                    }
                    return false;
                }
            });
            Looper.loop();
        }
        
        public void sendMessageDelayed(final Message message, final long n) {
            final Handler handler = this.handler;
            if (handler != null) {
                handler.sendMessageDelayed(message, n);
            }
        }
    }
}
