package com.kingagroot.kingdraw.core.view3d.element;

import com.kingagroot.kingdraw.core.view3d.utils.ColorUtil;
import android.graphics.Color;
import android.graphics.RectF;
import java.util.Collection;
import com.kingagroot.kingdraw.core.view3d.datas.PeriodicTable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import com.kingagroot.kingdraw.core.view3d.bean.KdAtom;

public class KdNode extends KdChemElement
{
    public static final String TYPE_R = "R";
    public static final String TYPE_UNDEFINED = "Undefined";
    private KdAtom atom;
    public float[] colorVer;
    public int hCount;
    public String html;
    public String label;
    private float openGlRadius;
    private KdPoint point;
    private List<KdProperty> propertys;
    public String type;
    
    public KdNode() {
        this.html = "";
        this.point = new KdPoint();
        this.propertys = (List<KdProperty>)new ArrayList();
        this.colorVer = new float[4];
    }
    
    private int existingValence() {
        final Iterator iterator = this.getRelationElements().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += ((KdBond)iterator.next()).getCovalent();
        }
        return n;
    }
    
    private int getChargeWithRadical() {
        final Iterator iterator = this.propertys.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += ((KdProperty)iterator.next()).getChg();
        }
        return n;
    }
    
    private KdAtom getQAtom() {
        final KdAtom atom = this.getAtom();
        if (atom != null && !this.isUndefind() && !this.isR()) {
            return PeriodicTable.getInstance().findAtomByIndex(atom.index - this.getChargeWithRadical());
        }
        return null;
    }
    
    private int getRadicalCount() {
        final Iterator iterator = this.propertys.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += ((KdProperty)iterator.next()).getRadCount();
        }
        return n;
    }
    
    public void addPropertys(final List<KdProperty> list) {
        if (list != null) {
            this.propertys.clear();
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                ((KdProperty)iterator.next()).index = this.index;
            }
            this.propertys.addAll((Collection)list);
        }
    }
    
    public KdAtom getAtom() {
        if (this.atom == null) {
            this.atom = PeriodicTable.getInstance().findAtomByName(this.type);
        }
        return this.atom;
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
    
    public float getOpenGlRadius() {
        return this.openGlRadius;
    }
    
    public KdPoint getPoint() {
        return this.point;
    }
    
    public List<KdProperty> getPropertys() {
        return this.propertys;
    }
    
    public float getSphereR_opengl() {
        return this.openGlRadius * 3.5f;
    }
    
    public boolean isCorrect() {
        final boolean undefind = this.isUndefind();
        final boolean b = false;
        if (undefind) {
            return false;
        }
        if (this.isR()) {
            return true;
        }
        final KdAtom qAtom = this.getQAtom();
        if (qAtom == null) {
            return true;
        }
        final int[] valenceArray = qAtom.getValenceArray();
        if (valenceArray != null && (valenceArray.length != 1 || valenceArray[0] != 0)) {
            final int hCount = this.hCount;
            final int existingValence = this.existingValence();
            final int length = valenceArray.length;
            int i = 0;
            while (true) {
                while (i < length) {
                    if (valenceArray[i] == hCount + existingValence) {
                        final boolean b2 = true;
                        if (!b2) {
                            return b2;
                        }
                        boolean b3 = b;
                        if (this.getRadicalCount() <= qAtom.outmostElectrons) {
                            b3 = true;
                        }
                        return b3;
                    }
                    else {
                        ++i;
                    }
                }
                final boolean b2 = false;
                continue;
            }
        }
        return true;
    }
    
    public boolean isR() {
        return "R".equals((Object)this.type);
    }
    
    public boolean isUndefind() {
        return "Undefined".equals((Object)this.type);
    }
    
    public void setOpenColor(final int n) {
        if ("C".equals((Object)this.type)) {
            this.colorVer = ColorUtil.IntColorRGB(Color.parseColor("#ff888888"));
        }
        else if ("H".equals((Object)this.type)) {
            this.colorVer = ColorUtil.IntColorRGB(Color.parseColor("#ffffffff"));
        }
        else {
            this.colorVer = ColorUtil.IntColorRGB(n);
        }
    }
    
    public void setOpenGlRadius(final float openGlRadius) {
        this.openGlRadius = openGlRadius;
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
