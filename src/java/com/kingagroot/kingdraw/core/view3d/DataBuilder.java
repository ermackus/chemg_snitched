package com.kingagroot.kingdraw.core.view3d;

import com.kingagroot.kingdraw.core.view3d.utils.ElementUtil;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.view3d.bean.KdAtom;
import java.util.Iterator;
import com.kingagroot.kingdraw.core.view3d.utils.OpenGlUtils;
import com.kingagroot.kingdraw.core.view3d.element.KdBond;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.element.KdElement;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import java.util.List;

public class DataBuilder
{
    List<KdFragment> fragmentCache;
    JsonConvertReader reader;
    JsonConvertWriter writer;
    
    public DataBuilder() {
        this.reader = new JsonConvertReader();
        this.writer = new JsonConvertWriter();
        this.fragmentCache = (List<KdFragment>)new ArrayList();
    }
    
    private void build3D(final List<KdFragment> list) {
        final ArrayList list2 = new ArrayList();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            final List<KdElement> read = this.reader.read(ProtocolConverter.json2dTojson3D(this.writer.write((KdFragment)iterator.next())));
            if (read != null && !read.isEmpty()) {
                final KdFragment kdFragment = new KdFragment();
                kdFragment.addChildElements((List)read);
                ((List)list2).add((Object)kdFragment);
            }
        }
        this.transToRight((List<KdFragment>)list2);
        final Iterator iterator2 = ((List)list2).iterator();
        while (iterator2.hasNext()) {
            final List childElemnts = ((KdFragment)iterator2.next()).getChildElemnts();
            final ArrayList list3 = new ArrayList();
            final int n = 0;
            int n2 = 0;
            int i;
            while (true) {
                i = n;
                if (n2 >= childElemnts.size()) {
                    break;
                }
                final KdElement kdElement = (KdElement)childElemnts.get(n2);
                if (kdElement instanceof KdNode) {
                    final KdNode kdNode = (KdNode)kdElement;
                    if (!"C".equals((Object)kdNode.type) && kdNode.getAtom() != null) {
                        kdNode.setOpenGlRadius(kdNode.getAtom().radius * 0.4f / 70.0f);
                    }
                    else {
                        kdNode.setOpenGlRadius(0.4f);
                    }
                    kdNode.setOpenColor(kdNode.getAtom().colorHex);
                }
                else if (kdElement instanceof KdBond) {
                    ((List)list3).add((Object)kdElement);
                }
                ++n2;
            }
            while (i < ((List)list3).size()) {
                final KdBond kdBond = (KdBond)((List)list3).get(i);
                kdBond.calc();
                kdBond.setCenter(OpenGlUtils.getPointCenter(kdBond));
                ++i;
            }
        }
        DataElements.addElements((List<KdFragment>)list2);
    }
    
    private KdNode getLeftElement(final KdFragment kdFragment) {
        final Iterator iterator = kdFragment.getChildElemnts().iterator();
        KdNode kdNode = null;
        while (iterator.hasNext()) {
            final KdElement kdElement = (KdElement)iterator.next();
            if (kdElement instanceof KdNode) {
                final KdNode kdNode2 = (KdNode)kdElement;
                if (kdNode != null) {
                    if (kdNode.getPoint().x < kdNode2.getPoint().x) {
                        continue;
                    }
                }
                kdNode = kdNode2;
            }
        }
        return kdNode;
    }
    
    private float getLength(final KdFragment kdFragment, final KdFragment kdFragment2) {
        final KdNode rightElement = this.getRightElement(kdFragment);
        final KdNode leftElement = this.getLeftElement(kdFragment2);
        final float n = 0.0f;
        float n2 = 0.0f;
        float n3 = n;
        if (rightElement != null) {
            n3 = n;
            if (leftElement != null) {
                final KdAtom atom = rightElement.getAtom();
                final KdAtom atom2 = leftElement.getAtom();
                float n4;
                if (atom != null) {
                    n4 = atom.radius / 70.0f;
                }
                else {
                    n4 = 0.0f;
                }
                if (atom2 != null) {
                    n2 = atom2.radius / 70.0f;
                }
                n3 = n2 * 0.5f + n4 * 0.5f;
            }
        }
        float n5 = n3;
        if (n3 < 1.0f) {
            n5 = 1.0f;
        }
        return n5;
    }
    
    private KdNode getRightElement(final KdFragment kdFragment) {
        final Iterator iterator = kdFragment.getChildElemnts().iterator();
        KdNode kdNode = null;
        while (iterator.hasNext()) {
            final KdElement kdElement = (KdElement)iterator.next();
            if (kdElement instanceof KdNode) {
                final KdNode kdNode2 = (KdNode)kdElement;
                if (kdNode != null) {
                    if (kdNode.getPoint().x > kdNode2.getPoint().x) {
                        continue;
                    }
                }
                kdNode = kdNode2;
            }
        }
        return kdNode;
    }
    
    private void transToRight(final List<KdFragment> list) {
        if (list.size() > 1) {
            int i = 0;
            float right = 0.0f;
            while (i < list.size()) {
                final KdFragment kdFragment = (KdFragment)list.get(i);
                final RectF bound = kdFragment.getBound();
                final KdPoint center = kdFragment.getCenter();
                if (i == 0) {
                    right = bound.right;
                }
                else {
                    final float n = right + this.getLength((KdFragment)list.get(i - 1), kdFragment) + (bound.right - bound.left) / 2.0f;
                    kdFragment.translate(-(n - center.x), 0.0f, 0.0f);
                    right = n + (bound.right - bound.left) / 2.0f;
                }
                ++i;
            }
            final Iterator iterator = list.iterator();
            float n2 = -3.4028235E38f;
            float bottom = -3.4028235E38f;
            float n3 = Float.MAX_VALUE;
            float n4 = Float.MAX_VALUE;
            while (iterator.hasNext()) {
                final RectF bound2 = ((KdFragment)iterator.next()).getBound();
                float left = n3;
                if (bound2.left < n3) {
                    left = bound2.left;
                }
                float right2 = n2;
                if (bound2.right > n2) {
                    right2 = bound2.right;
                }
                float top = n4;
                if (bound2.top < n4) {
                    top = bound2.top;
                }
                n2 = right2;
                n3 = left;
                n4 = top;
                if (bound2.bottom > bottom) {
                    bottom = bound2.bottom;
                    n2 = right2;
                    n3 = left;
                    n4 = top;
                }
            }
            final float n5 = (n2 + n3) / 2.0f;
            final float n6 = (bottom + n4) / 2.0f;
            final Iterator iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((KdFragment)iterator2.next()).translate(n5, n6, 0.0f);
            }
        }
    }
    
    public void buildJson(String json2dTojson3D) {
        json2dTojson3D = ProtocolConverter.json2dTojson3D(json2dTojson3D);
        final List<KdFragment> elementsInFragments = ElementUtil.elementsInFragments(this.reader.read(json2dTojson3D));
        this.transToRight(elementsInFragments);
        final Iterator iterator = elementsInFragments.iterator();
        while (iterator.hasNext()) {
            final List childElemnts = ((KdFragment)iterator.next()).getChildElemnts();
            final ArrayList list = new ArrayList();
            final int n = 0;
            int n2 = 0;
            int i;
            while (true) {
                i = n;
                if (n2 >= childElemnts.size()) {
                    break;
                }
                final KdElement kdElement = (KdElement)childElemnts.get(n2);
                if (kdElement instanceof KdNode) {
                    final KdNode kdNode = (KdNode)kdElement;
                    if (!"C".equals((Object)kdNode.type) && kdNode.getAtom() != null) {
                        kdNode.setOpenGlRadius(kdNode.getAtom().radius * 0.4f / 70.0f);
                    }
                    else {
                        kdNode.setOpenGlRadius(0.4f);
                    }
                    if (kdNode.getAtom() != null) {
                        kdNode.setOpenColor(kdNode.getAtom().colorHex);
                    }
                    else {
                        kdNode.setOpenColor(-16777216);
                    }
                }
                else if (kdElement instanceof KdBond) {
                    ((List)list).add((Object)kdElement);
                }
                ++n2;
            }
            while (i < ((List)list).size()) {
                final KdBond kdBond = (KdBond)((List)list).get(i);
                kdBond.calc();
                kdBond.setCenter(OpenGlUtils.getPointCenter(kdBond));
                ++i;
            }
        }
        DataElements.addElements(elementsInFragments);
    }
    
    @Deprecated
    public boolean checkCorrect() {
        final Iterator iterator = this.fragmentCache.iterator();
        while (iterator.hasNext()) {
            if (!((KdFragment)iterator.next()).isCorrect()) {
                return false;
            }
        }
        return true;
    }
}
