package com.kingagroot.kingdraw.core.graphics;

import com.kingagroot.kingdraw.core.Html.BaseText;
import com.kingagroot.kingdraw.core.Html.cache.TextGroupCache;
import com.kingagroot.kingdraw.core.Html.TextGroup;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.RectF;

abstract class BaseCanvasHolder
{
    private RectF Bounds;
    private String Tag;
    protected Canvas canvas;
    protected Matrix matrix;
    
    BaseCanvasHolder() {
        this.canvas = null;
        this.Tag = "PaletteView";
        this.Bounds = new RectF(0.0f, 0.0f, 1000.0f, 1000.0f);
    }
    
    public void concat(final Matrix matrix) {
        synchronized (this) {
            this.matrix = matrix;
            if (this.canvas != null) {
                this.canvas.concat(matrix);
            }
        }
    }
    
    public void drawArc(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final boolean b, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                this.canvas.drawArc(new RectF(n, n2, n3, n4), (float)Math.toDegrees((double)n5), (float)Math.toDegrees((double)n6), b, paint);
            }
        }
    }
    
    public void drawCircle(final float n, final float n2, final float n3, final float n4, final float n5, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                final Path path = new Path();
                path.addOval(new RectF(n - n3, n2 - n4, n3 + n, n4 + n2), Path$Direction.CW);
                final Matrix matrix = new Matrix();
                matrix.postRotate((float)Math.toDegrees((double)n5), n, n2);
                path.transform(matrix);
                this.canvas.drawPath(path, paint);
            }
        }
    }
    
    public abstract void drawEnd();
    
    public void drawHtml(final String s, final float n, final float n2, final float n3, final boolean b) {
        synchronized (this) {
            if (this.canvas != null) {
                TextGroup textGroup;
                if (b) {
                    if ((textGroup = (TextGroup)TextGroupCache.getNodeTextCacheInstance().getTextLine(s)) == null) {
                        textGroup = new TextGroup(s);
                        TextGroupCache.getNodeTextCacheInstance().addCache(s, (BaseText)textGroup);
                    }
                    textGroup.measure(b);
                }
                else {
                    if ((textGroup = (TextGroup)TextGroupCache.getTextCacheInstance().getTextLine(s)) == null) {
                        textGroup = new TextGroup(s);
                        TextGroupCache.getTextCacheInstance().addCache(s, (BaseText)textGroup);
                    }
                    textGroup.measure();
                }
                this.canvas.save();
                final Matrix matrix = new Matrix();
                matrix.postRotate((float)Math.toDegrees((double)n3), n, n2);
                this.canvas.concat(matrix);
                textGroup.draw(this.canvas, n, n2);
                this.canvas.restore();
            }
        }
    }
    
    public void drawImage(final String s, float n, float n2, final float n3, final float n4, final float n5, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                paint.setFilterBitmap(true);
                paint.setDither(true);
                final KDDrawable load = BitmapLoader.getInstance().load(s);
                if (load != null) {
                    load.setBounds(n, n2, n3, n4);
                    this.canvas.save();
                    final Matrix matrix = new Matrix();
                    n = (n + n3) / 2.0f;
                    n2 = (n2 + n4) / 2.0f;
                    matrix.postRotate((float)Math.toDegrees((double)n5), n, n2);
                    this.canvas.concat(matrix);
                    load.draw(this.canvas, paint);
                    this.canvas.restore();
                }
            }
        }
    }
    
    public void drawLine(final float n, final float n2, final float n3, final float n4, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                this.canvas.drawLine(n, n2, n3, n4, paint);
            }
        }
    }
    
    public void drawPath(final KDPath kdPath, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                this.canvas.drawPath((Path)kdPath, paint);
            }
        }
    }
    
    public void drawRect(final float n, final float n2, final float n3, final float n4, final float n5, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                final Path path = new Path();
                final RectF rectF = new RectF(n, n2, n3, n4);
                path.addRect(rectF, Path$Direction.CW);
                final Matrix matrix = new Matrix();
                matrix.postRotate((float)Math.toDegrees((double)n5), rectF.centerX(), rectF.centerY());
                path.transform(matrix);
                this.canvas.drawPath(path, paint);
            }
        }
    }
    
    public void drawRoundRect(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final Paint paint) {
        synchronized (this) {
            if (this.canvas != null) {
                final Path path = new Path();
                final RectF rectF = new RectF(n, n2, n3, n4);
                path.addRoundRect(rectF, new float[] { n5, n6, n5, n6, n5, n6, n5, n6 }, Path$Direction.CW);
                final Matrix matrix = new Matrix();
                matrix.postRotate((float)Math.toDegrees((double)n7), rectF.centerX(), rectF.centerY());
                path.transform(matrix);
                this.canvas.drawPath(path, paint);
            }
        }
    }
    
    public abstract void drawStart();
    
    public float[] getBounds() {
        synchronized (this) {
            final float left = this.Bounds.left;
            final float top = this.Bounds.top;
            final float right = this.Bounds.right;
            final float bottom = this.Bounds.bottom;
            monitorexit(this);
            return new float[] { left, top, right, bottom };
        }
    }
    
    public float[] getCenter() {
        synchronized (this) {
            final float centerX = this.Bounds.centerX();
            final float centerY = this.Bounds.centerY();
            monitorexit(this);
            return new float[] { centerX, centerY };
        }
    }
    
    public float getHeight() {
        synchronized (this) {
            return this.Bounds.height();
        }
    }
    
    public Matrix getMatrix() {
        synchronized (this) {
            return this.matrix;
        }
    }
    
    public String getTag() {
        return this.Tag;
    }
    
    public float getWidth() {
        synchronized (this) {
            return this.Bounds.width();
        }
    }
    
    public void matrixChanged(final KDMatrix matrix) {
        synchronized (this) {
            this.matrix = matrix;
        }
    }
    
    public void onDestroy() {
        synchronized (this) {
            BitmapLoader.recycle();
        }
    }
    
    public void setBounds(final RectF bounds) {
        synchronized (this) {
            this.Bounds = bounds;
        }
    }
    
    public void setTag(final String tag) {
        this.Tag = tag;
    }
}
