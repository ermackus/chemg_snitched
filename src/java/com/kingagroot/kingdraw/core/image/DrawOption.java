package com.kingagroot.kingdraw.core.image;

import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.RectF;

public interface DrawOption
{
    RectF drawBound(final RectF p0);
    
    void drawEnd(final Canvas p0);
    
    Matrix drawMatrix(final Matrix p0);
    
    void drawStart(final Canvas p0);
}
