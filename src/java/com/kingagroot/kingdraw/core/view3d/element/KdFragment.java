package com.kingagroot.kingdraw.core.view3d.element;

import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import android.graphics.RectF;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class KdFragment extends KdElement
{
    private List<KdElement> childElemnts;
    
    public KdFragment() {
        this.childElemnts = (List<KdElement>)new ArrayList();
    }
    
    public void addChildElements(final List<KdElement> list) {
        if (list != null) {
            this.childElemnts.addAll((Collection)list);
        }
    }
    
    public boolean containNode() {
        final Iterator iterator = this.childElemnts.iterator();
        while (iterator.hasNext()) {
            if (((KdElement)iterator.next()) instanceof KdNode) {
                return true;
            }
        }
        return false;
    }
    
    public RectF getBound() {
        if (this.bound == null) {
            final Iterator iterator = this.childElemnts.iterator();
            float max = -3.4028235E38f;
            float max2 = -3.4028235E38f;
            float min = Float.MAX_VALUE;
            float min2 = Float.MAX_VALUE;
            while (iterator.hasNext()) {
                final RectF bound = ((KdElement)iterator.next()).getBound();
                min = Math.min(bound.left, min);
                min2 = Math.min(bound.top, min2);
                max = Math.max(bound.right, max);
                max2 = Math.max(bound.bottom, max2);
            }
            this.bound = new RectF(min, min2, max, max2);
        }
        return this.bound;
    }
    
    public KdPoint getCenter() {
        final RectF bound = this.getBound();
        return new KdPoint(bound.centerX(), bound.centerY());
    }
    
    public List<KdElement> getChildElemnts() {
        return this.childElemnts;
    }
    
    public boolean isCorrect() {
        for (final KdElement kdElement : this.childElemnts) {
            if (kdElement instanceof KdNode && !((KdNode)kdElement).isCorrect()) {
                return false;
            }
        }
        return true;
    }
    
    public void translate(final float n, final float n2, final float n3) {
        super.translate(n, n2, n3);
        final Iterator iterator = this.childElemnts.iterator();
        while (iterator.hasNext()) {
            ((KdElement)iterator.next()).translate(n, n2, n3);
        }
    }
}
