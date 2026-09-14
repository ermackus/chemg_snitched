package com.kingagroot.kingdraw.core.view3d.element;

import com.kingagroot.kingdraw.core.view3d.bean.KdPropertyTypeEnum;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;

public class KdProperty extends KdElement
{
    public int aIndex;
    public float angle;
    private int chg;
    private KdPoint point;
    public int type;
    
    public KdProperty() {
        this.point = new KdPoint();
    }
    
    public RectF getBound() {
        if (this.bound == null) {
            this.bound = new RectF(this.point.x, this.point.y, this.point.x, this.point.y);
        }
        return this.bound;
    }
    
    public KdPoint getCenter() {
        return this.point;
    }
    
    public int getChg() {
        if (this.type == KdPropertyTypeEnum.GRADICAL.code) {
            return -1;
        }
        if (this.type == KdPropertyTypeEnum.GRADICALANION.code) {
            return -2;
        }
        if (this.type == KdPropertyTypeEnum.CHARGE.code) {
            return this.chg;
        }
        return 0;
    }
    
    public KdPoint getPoint() {
        return this.point;
    }
    
    public int getRadCount() {
        if (this.type == KdPropertyTypeEnum.GLONEPAIR.code) {
            return 2;
        }
        if (this.type != KdPropertyTypeEnum.GRADICAL.code && this.type != KdPropertyTypeEnum.GRADICALANION.code && this.type != KdPropertyTypeEnum.GRADICALCATION.code) {
            return 0;
        }
        return 1;
    }
    
    public void setChg(final int chg) {
        this.chg = chg;
    }
    
    public void setPoint(final KdPoint kdPoint) {
        if (kdPoint != null) {
            this.point.x = kdPoint.x;
            this.point.y = kdPoint.y;
            this.point.z = kdPoint.z;
        }
    }
    
    public void translate(final float n, final float n2, final float n3) {
        super.translate(n, n2, n3);
        final KdPoint point = this.point;
        point.x -= n;
        final KdPoint point2 = this.point;
        point2.y -= n2;
        final KdPoint point3 = this.point;
        point3.z -= n3;
    }
}
