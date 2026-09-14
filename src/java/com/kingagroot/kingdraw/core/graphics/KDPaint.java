package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Paint$Cap;
import android.graphics.Shader;
import android.graphics.RadialGradient;
import android.graphics.Shader$TileMode;
import android.graphics.PathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint$Style;
import android.graphics.Paint;

class KDPaint extends Paint
{
    public KDPaint() {
        this.setAntiAlias(true);
        this.setStyle(Paint$Style.STROKE);
    }
    
    public void setColor(final String s) {
        this.setColor(KDColor.parseColor(s));
    }
    
    public void setFill(final boolean b) {
        if (b) {
            this.setStyle(Paint$Style.FILL);
        }
        else {
            this.setStyle(Paint$Style.STROKE);
        }
    }
    
    public void setPathEffect(final float n, final float n2) {
        this.setPathEffect((PathEffect)new DashPathEffect(new float[] { n, n2 }, 0.0f));
    }
    
    public void setRadialGradient(final float n, final float n2, float n3, final String s, final String s2) {
        final int color = KDColor.parseColor(s);
        final int color2 = KDColor.parseColor(s2);
        if (n3 <= 0.0f) {
            n3 = 0.01f;
        }
        this.setShader((Shader)new RadialGradient(n, n2, n3, color, color2, Shader$TileMode.CLAMP));
    }
    
    public void setShadowLayer(final float n, final float n2, final float n3, final String s) {
        this.setShadowLayer(n, n2, n3, KDColor.parseColor(s));
    }
    
    public void setStrokeCap(final int n) {
        Paint$Cap strokeCap;
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    strokeCap = Paint$Cap.BUTT;
                }
                else {
                    strokeCap = Paint$Cap.SQUARE;
                }
            }
            else {
                strokeCap = Paint$Cap.ROUND;
            }
        }
        else {
            strokeCap = Paint$Cap.BUTT;
        }
        this.setStrokeCap(strokeCap);
    }
}
