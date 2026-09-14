package com.kingagroot.kingdraw.core.graphics;

import android.graphics.Path;

public class KDPath extends Path
{
    public void cubicTo(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        super.cubicTo(n, n2, n3, n4, n5, n6);
    }
    
    public void lineTo(final float n, final float n2) {
        super.lineTo(n, n2);
    }
    
    public void moveTo(final float n, final float n2) {
        super.moveTo(n, n2);
    }
    
    public void quadTo(final float n, final float n2, final float n3, final float n4) {
        super.quadTo(n, n2, n3, n4);
    }
}
