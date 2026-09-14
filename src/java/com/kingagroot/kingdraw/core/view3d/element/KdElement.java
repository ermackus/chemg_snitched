package com.kingagroot.kingdraw.core.view3d.element;

import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import android.graphics.RectF;

public abstract class KdElement
{
    protected RectF bound;
    public String colorHex;
    public int index;
    public int layerIndex;
    
    public KdElement() {
        this.colorHex = "0xFF000000";
    }
    
    public abstract RectF getBound();
    
    public abstract KdPoint getCenter();
    
    public void translate(final float n, final float n2, final float n3) {
        final RectF bound = this.bound;
        if (bound != null) {
            bound.left -= n;
            final RectF bound2 = this.bound;
            bound2.right -= n;
            final RectF bound3 = this.bound;
            bound3.top -= n2;
            final RectF bound4 = this.bound;
            bound4.bottom -= n2;
        }
    }
}
