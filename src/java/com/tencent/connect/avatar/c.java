package com.tencent.connect.avatar;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.PointF;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

public class c extends ImageView
{
    final String a;
    public boolean b;
    private Matrix c;
    private Matrix d;
    private int e;
    private float f;
    private float g;
    private Bitmap h;
    private boolean i;
    private float j;
    private float k;
    private PointF l;
    private PointF m;
    private float n;
    private float o;
    private Rect p;
    
    public c(final Context context) {
        super(context);
        this.c = new Matrix();
        this.d = new Matrix();
        this.e = 0;
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = false;
        this.a = "TouchView";
        this.l = new PointF();
        this.m = new PointF();
        this.n = 1.0f;
        this.o = 0.0f;
        this.b = false;
        this.getDrawingRect(this.p = new Rect());
        this.a();
    }
    
    private float a(final MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        final float n = motionEvent.getX(0) - motionEvent.getX(1);
        final float n2 = motionEvent.getY(0) - motionEvent.getY(1);
        return (float)Math.sqrt((double)(n * n + n2 * n2));
    }
    
    private void a() {
    }
    
    private void a(final PointF pointF) {
        if (this.h == null) {
            return;
        }
        final float[] array = new float[9];
        this.c.getValues(array);
        final float n = array[2];
        final float n2 = array[5];
        final float n3 = array[0];
        final float n4 = (float)this.h.getWidth();
        final float n5 = (float)this.h.getHeight();
        final float n6 = this.p.left - n;
        final float n7 = 1.0f;
        float n8 = n6;
        if (n6 <= 1.0f) {
            n8 = 1.0f;
        }
        float n9;
        if ((n9 = n + n4 * n3 - this.p.right) <= 1.0f) {
            n9 = 1.0f;
        }
        final float n10 = this.p.width() * n8 / (n9 + n8);
        final float n11 = (float)this.p.left;
        final float n12 = this.p.top - n2;
        final float n13 = n2 + n5 * n3 - this.p.bottom;
        float n14 = n12;
        if (n12 <= 1.0f) {
            n14 = 1.0f;
        }
        float n15;
        if (n13 <= 1.0f) {
            n15 = n7;
        }
        else {
            n15 = n13;
        }
        pointF.set(n10 + n11, this.p.height() * n14 / (n15 + n14) + this.p.top);
    }
    
    private void b() {
        if (this.h == null) {
            return;
        }
        final float n = (float)this.p.width();
        final float n2 = (float)this.p.height();
        final float[] values = new float[9];
        this.c.getValues(values);
        float n3 = values[2];
        float n4 = values[5];
        boolean b = false;
        final float n5 = values[0];
        Object o = null;
        final float f = this.f;
        if (n5 > f) {
            final float o2 = f / n5;
            this.o = o2;
            this.c.postScale(o2, o2, this.m.x, this.m.y);
            this.setImageMatrix(this.c);
            final float o3 = this.o;
            o = new ScaleAnimation(1.0f / o3, 1.0f, 1.0f / o3, 1.0f, this.m.x, this.m.y);
        }
        else {
            final float g = this.g;
            if (n5 < g) {
                final float o4 = g / n5;
                this.o = o4;
                this.c.postScale(o4, o4, this.m.x, this.m.y);
                final float o5 = this.o;
                o = new ScaleAnimation(1.0f, o5, 1.0f, o5, this.m.x, this.m.y);
            }
            else {
                final float n6 = this.h.getWidth() * n5;
                final float n7 = this.h.getHeight() * n5;
                final float n8 = this.p.left - n3;
                final float n9 = this.p.top - n4;
                if (n8 < 0.0f) {
                    n3 = (float)this.p.left;
                    b = true;
                }
                if (n9 < 0.0f) {
                    n4 = (float)this.p.top;
                    b = true;
                }
                if (n6 - n8 < n) {
                    n3 = this.p.left - (n6 - n);
                    b = true;
                }
                if (n7 - n9 < n2) {
                    n4 = this.p.top - (n7 - n2);
                    b = true;
                }
                if (b) {
                    final float n10 = values[2];
                    final float n11 = values[5];
                    values[2] = n3;
                    values[5] = n4;
                    this.c.setValues(values);
                    this.setImageMatrix(this.c);
                    o = new TranslateAnimation(n10 - n3, 0.0f, n11 - n4, 0.0f);
                }
                else {
                    this.setImageMatrix(this.c);
                }
            }
        }
        if (o != null) {
            this.i = true;
            ((Animation)o).setDuration(300L);
            this.startAnimation((Animation)o);
            new Thread((Runnable)new Runnable(this) {
                final c a;
                
                public void run() {
                    try {
                        Thread.sleep(300L);
                    }
                    catch (final InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    this.a.post((Runnable)new Runnable(this) {
                        final c$1 a;
                        
                        public void run() {
                            this.a.a.clearAnimation();
                            this.a.a.b();
                        }
                    });
                    this.a.i = false;
                }
            }).start();
        }
    }
    
    private void c() {
        if (this.h == null) {
            return;
        }
        final float[] values = new float[9];
        this.c.getValues(values);
        final float max = Math.max(this.p.width() / (float)this.h.getWidth(), this.p.height() / (float)this.h.getHeight());
        this.j = this.p.left - (this.h.getWidth() * max - this.p.width()) / 2.0f;
        final float k = this.p.top - (this.h.getHeight() * max - this.p.height()) / 2.0f;
        this.k = k;
        values[2] = this.j;
        values[5] = k;
        values[0] = (values[4] = max);
        this.c.setValues(values);
        final float min = Math.min(2048.0f / this.h.getWidth(), 2048.0f / this.h.getHeight());
        this.f = min;
        this.g = max;
        if (min < max) {
            this.f = max;
        }
        this.setImageMatrix(this.c);
    }
    
    public void a(final Rect p) {
        this.p = p;
        if (this.h != null) {
            this.c();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.i) {
            return true;
        }
        final int n = motionEvent.getAction() & 0xFF;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 5) {
                        if (n != 6) {
                            return this.b = true;
                        }
                    }
                    else {
                        final float a = this.a(motionEvent);
                        this.n = a;
                        if (a > 10.0f) {
                            this.d.set(this.c);
                            this.a(this.m);
                            this.e = 2;
                            return this.b = true;
                        }
                        return this.b = true;
                    }
                }
                else {
                    final int e = this.e;
                    if (e == 1) {
                        this.c.set(this.d);
                        this.c.postTranslate(motionEvent.getX() - this.l.x, motionEvent.getY() - this.l.y);
                        this.setImageMatrix(this.c);
                        return this.b = true;
                    }
                    if (e == 2) {
                        final Matrix c = this.c;
                        c.set(c);
                        final float a2 = this.a(motionEvent);
                        if (a2 > 10.0f) {
                            this.c.set(this.d);
                            final float n2 = a2 / this.n;
                            this.c.postScale(n2, n2, this.m.x, this.m.y);
                        }
                        this.setImageMatrix(this.c);
                        return this.b = true;
                    }
                    return this.b = true;
                }
            }
            this.b();
            this.e = 0;
        }
        else {
            this.c.set(this.getImageMatrix());
            this.d.set(this.c);
            this.l.set(motionEvent.getX(), motionEvent.getY());
            this.e = 1;
        }
        return this.b = true;
    }
    
    public void setImageBitmap(final Bitmap h) {
        super.setImageBitmap(h);
        this.h = h;
        if (h != null) {
            this.h = h;
        }
    }
}
