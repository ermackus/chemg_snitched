package com.kingagroot.kingdraw.core.view3d.element;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.kingagroot.kingdraw.core.view3d.bean.KdBondTypeEnum;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import android.graphics.RectF;

public class KdBond extends KdChemElement
{
    public int align;
    public float angleXY;
    public float angleXZ;
    public float[] center;
    public float[] colorVer1;
    public float[] colorVer2;
    public float length;
    public int type;
    
    public KdBond() {
        this.colorVer1 = new float[4];
        this.colorVer2 = new float[4];
        this.center = new float[3];
    }
    
    private static float calcAngle(float n, float n2, float n3, float n4, final float n5, final float n6) {
        n3 -= n;
        n4 -= n2;
        n = n5 - n;
        n2 = n6 - n2;
        final double n7 = (n3 * n + n4 * n2) / (Math.sqrt((double)(n3 * n3 + n4 * n4)) * Math.sqrt((double)(n * n + n2 * n2)));
        double n8;
        if (n3 * n2 - n4 * n > 0.0) {
            n8 = 1.0;
        }
        else {
            n8 = -1.0;
        }
        double n9;
        if (n7 > 1.0) {
            n9 = 1.0;
        }
        else {
            n9 = n7;
            if (n7 < -1.0) {
                n9 = -1.0;
            }
        }
        return (float)(Math.acos(n9) * n8);
    }
    
    public void calc() {
        final KdNode kdNode = (KdNode)this.getRelationElements().get(0);
        final KdNode kdNode2 = (KdNode)this.getRelationElements().get(1);
        this.colorVer1 = kdNode.colorVer;
        this.colorVer2 = kdNode2.colorVer;
        this.setLength((float)Math.sqrt((double)(float)(Math.pow((double)(kdNode.getPoint().x - kdNode2.getPoint().x), 2.0) + Math.pow((double)(kdNode.getPoint().y - kdNode2.getPoint().y), 2.0) + Math.pow((double)(kdNode.getPoint().z - kdNode2.getPoint().z), 2.0))));
        final float n = kdNode2.getPoint().x - kdNode.getPoint().x;
        final float n2 = kdNode2.getPoint().y - kdNode.getPoint().y;
        final float n3 = kdNode2.getPoint().z - kdNode.getPoint().z;
        this.setAngleXY((float)(calcAngle(0.0f, 0.0f, 1.0f, 0.0f, n, n2) * 180.0f / 3.141592653589793));
        final float n4 = (float)Math.sqrt((double)(n * n + n2 * n2));
        float n5;
        if (Math.sqrt((double)n3) <= 1.0E-4) {
            n5 = 0.0f;
        }
        else {
            n5 = (float)Math.atan((double)(n3 / n4));
        }
        float abs;
        if (n3 > 0.0f) {
            abs = -Math.abs(n5);
        }
        else {
            abs = Math.abs(n5);
        }
        this.setAngleXZ((float)(abs * 180.0f / 3.141592653589793));
    }
    
    public RectF getBound() {
        if (this.bound == null) {
            this.bound = new RectF();
            final KdNode startNode = this.getStartNode();
            final KdNode endNode = this.getEndNode();
            final RectF bound = startNode.getBound();
            final RectF bound2 = endNode.getBound();
            this.bound.left = Math.min(bound.left, bound2.left);
            this.bound.top = Math.min(bound.top, bound2.top);
            this.bound.right = Math.max(bound.right, bound2.right);
            this.bound.bottom = Math.max(bound.bottom, bound2.bottom);
        }
        return this.bound;
    }
    
    public KdPoint getCenter() {
        final KdNode startNode = this.getStartNode();
        final KdNode endNode = this.getEndNode();
        return new KdPoint((startNode.getPoint().x + endNode.getPoint().x) / 2.0f, (startNode.getPoint().y + endNode.getPoint().y) / 2.0f, (startNode.getPoint().z + endNode.getPoint().z) / 2.0f);
    }
    
    public int getCovalent() {
        return KdBondTypeEnum.valueOf(this.type).covalent;
    }
    
    public KdNode getEndNode() {
        final List relationElements = this.getRelationElements();
        if (relationElements.size() > 1) {
            return (KdNode)relationElements.get(1);
        }
        return null;
    }
    
    public List<KdBond> getOtherGBond(final KdNode kdNode) {
        final ArrayList list = new ArrayList();
        for (final KdChemElement kdChemElement : kdNode.getRelationElements()) {
            if (!kdChemElement.equals(this) && kdChemElement instanceof KdBond) {
                ((List)list).add((Object)kdChemElement);
            }
        }
        return (List<KdBond>)list;
    }
    
    public KdNode getOtherNode(final KdNode kdNode) {
        for (final KdChemElement kdChemElement : this.getRelationElements()) {
            if (kdChemElement != kdNode) {
                return (KdNode)kdChemElement;
            }
        }
        return null;
    }
    
    public KdNode getStartNode() {
        final List relationElements = this.getRelationElements();
        if (relationElements.size() > 0) {
            return (KdNode)relationElements.get(0);
        }
        return null;
    }
    
    public void setAngleXY(final float angleXY) {
        this.angleXY = angleXY;
    }
    
    public void setAngleXZ(final float angleXZ) {
        this.angleXZ = angleXZ;
    }
    
    public void setCenter(final float[] center) {
        this.center = center;
    }
    
    public void setLength(final float length) {
        this.length = length;
    }
}
