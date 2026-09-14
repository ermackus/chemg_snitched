package com.kingagroot.kingdraw.core.view3d.utils;

import java.util.List;
import java.util.Iterator;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.bean.KdBondTypeEnum;
import com.kingagroot.kingdraw.core.view3d.element.KdBond;

public class OpenGlUtils
{
    public static void applyRotation(final float[] array, float n, float n2, final float n3) {
        if (n != 0.0f) {
            final double n4 = n;
            final float n5 = (float)Math.cos(n4);
            n = (float)Math.sin(n4);
            final float[] array3;
            final float[] array2 = array3 = new float[16];
            array3[0] = 1.0f;
            array3[2] = (array3[1] = 0.0f);
            array3[4] = (array3[3] = 0.0f);
            array3[5] = 1.0f;
            array3[7] = (array3[6] = 0.0f);
            array3[9] = (array3[8] = 0.0f);
            array3[10] = 1.0f;
            array3[12] = (array3[11] = 0.0f);
            array3[14] = (array3[13] = 0.0f);
            array3[15] = 1.0f;
            array2[5] = n5;
            array2[6] = -n;
            array2[9] = n;
            array2[10] = n5;
            multiplyMatrix(array2, array, array);
        }
        if (n2 != 0.0f) {
            final double n6 = n2;
            n = (float)Math.cos(n6);
            n2 = (float)Math.sin(n6);
            final float[] array5;
            final float[] array4 = array5 = new float[16];
            array5[0] = 1.0f;
            array5[2] = (array5[1] = 0.0f);
            array5[4] = (array5[3] = 0.0f);
            array5[5] = 1.0f;
            array5[7] = (array5[6] = 0.0f);
            array5[9] = (array5[8] = 0.0f);
            array5[10] = 1.0f;
            array5[12] = (array5[11] = 0.0f);
            array5[14] = (array5[13] = 0.0f);
            array5[15] = 1.0f;
            array4[0] = n;
            array4[8] = -(array4[2] = n2);
            array4[10] = n;
            multiplyMatrix(array4, array, array);
        }
        if (n3 != 0.0f) {
            final double n7 = n3;
            n2 = (float)Math.cos(n7);
            n = (float)Math.sin(n7);
            final float[] array7;
            final float[] array6 = array7 = new float[16];
            array7[0] = 1.0f;
            array7[2] = (array7[1] = 0.0f);
            array7[4] = (array7[3] = 0.0f);
            array7[5] = 1.0f;
            array7[7] = (array7[6] = 0.0f);
            array7[9] = (array7[8] = 0.0f);
            array7[10] = 1.0f;
            array7[12] = (array7[11] = 0.0f);
            array7[14] = (array7[13] = 0.0f);
            array7[15] = 1.0f;
            array6[0] = n2;
            array6[1] = -n;
            array6[4] = n;
            array6[5] = n2;
            multiplyMatrix(array6, array, array);
        }
    }
    
    private static void copyMatrix(final float[] array, final float[] array2) {
        System.arraycopy((Object)array, 0, (Object)array2, 0, 16);
    }
    
    public static float[] getPointCenter(final KdBond kdBond) {
        float[] array;
        if (kdBond.type != KdBondTypeEnum.G_TRIPLE_BOND.code && kdBond.type != KdBondTypeEnum.G_DOUBLE_BOND.code && kdBond.type != KdBondTypeEnum.G_DOUBLE_EITHER_BOND.code) {
            final KdPoint center = kdBond.getCenter();
            array = new float[] { center.x, center.y, center.z };
        }
        else {
            final KdNode kdNode = (KdNode)kdBond.getRelationElements().get(0);
            final KdNode kdNode2 = (KdNode)kdBond.getRelationElements().get(1);
            final ArrayList list = new ArrayList();
            final Iterator iterator = kdBond.getOtherGBond(kdNode).iterator();
            while (iterator.hasNext()) {
                final KdNode otherNode = ((KdBond)iterator.next()).getOtherNode(kdNode);
                if (otherNode != null) {
                    ((List)list).add((Object)otherNode);
                }
            }
            final Iterator iterator2 = kdBond.getOtherGBond(kdNode2).iterator();
            while (iterator2.hasNext()) {
                ((List)list).add((Object)((KdBond)iterator2.next()).getOtherNode(kdNode2));
            }
            final KdPoint point = kdNode.getPoint();
            final KdPoint point2 = kdNode2.getPoint();
            KdPoint kdPoint = new KdPoint();
            final Iterator iterator3 = ((List)list).iterator();
            int calc3DTriangleMidpoint = 0;
            while (iterator3.hasNext()) {
                final boolean b = (calc3DTriangleMidpoint = (GMath.calc3DTriangleMidpoint(point, point2, ((KdNode)iterator3.next()).getPoint(), kdPoint) ? 1 : 0)) != 0;
                if (b) {
                    calc3DTriangleMidpoint = (b ? 1 : 0);
                    break;
                }
            }
            int calc3DTriangleMidpoint2;
            if ((calc3DTriangleMidpoint2 = calc3DTriangleMidpoint) == 0) {
                calc3DTriangleMidpoint2 = (GMath.calc3DTriangleMidpoint(point, point2, new KdPoint(point.x, point.y + 0.5f, point.z), kdPoint) ? 1 : 0);
            }
            if (calc3DTriangleMidpoint2 == 0) {
                kdPoint = new KdPoint(point.x, point.y + 0.5f, point.z);
            }
            final KdPoint center2 = kdBond.getCenter();
            final KdPoint calc3DPointOnExtensionOfSegment = GMath.calc3DPointOnExtensionOfSegment(center2, kdPoint, 0.2f);
            final KdPoint calc3DPointOnExtensionOfSegment2 = GMath.calc3DPointOnExtensionOfSegment(center2, kdPoint, -0.2f);
            if (kdBond.type != KdBondTypeEnum.G_DOUBLE_BOND.code && kdBond.type != KdBondTypeEnum.G_DOUBLE_EITHER_BOND.code) {
                array = new float[] { center2.x, center2.y, center2.z, calc3DPointOnExtensionOfSegment.x, calc3DPointOnExtensionOfSegment.y, calc3DPointOnExtensionOfSegment.z, calc3DPointOnExtensionOfSegment2.x, calc3DPointOnExtensionOfSegment2.y, calc3DPointOnExtensionOfSegment2.z };
            }
            else {
                array = new float[] { calc3DPointOnExtensionOfSegment.x, calc3DPointOnExtensionOfSegment.y, calc3DPointOnExtensionOfSegment.z, calc3DPointOnExtensionOfSegment2.x, calc3DPointOnExtensionOfSegment2.y, calc3DPointOnExtensionOfSegment2.z };
            }
        }
        return array;
    }
    
    private static void multiplyMatrix(final float[] array, final float[] array2, final float[] array3) {
        copyMatrix(new float[] { array[0] * array2[0] + array[4] * array2[1] + array[8] * array2[2] + array[12] * array2[3], array[1] * array2[0] + array[5] * array2[1] + array[9] * array2[2] + array[13] * array2[3], array[2] * array2[0] + array[6] * array2[1] + array[10] * array2[2] + array[14] * array2[3], array[3] * array2[0] + array[7] * array2[1] + array[11] * array2[2] + array[15] * array2[3], array[0] * array2[4] + array[4] * array2[5] + array[8] * array2[6] + array[12] * array2[7], array[1] * array2[4] + array[5] * array2[5] + array[9] * array2[6] + array[13] * array2[7], array[2] * array2[4] + array[6] * array2[5] + array[10] * array2[6] + array[14] * array2[7], array[3] * array2[4] + array[7] * array2[5] + array[11] * array2[6] + array[15] * array2[7], array[0] * array2[8] + array[4] * array2[9] + array[8] * array2[10] + array[12] * array2[11], array[1] * array2[8] + array[5] * array2[9] + array[9] * array2[10] + array[13] * array2[11], array[2] * array2[8] + array[6] * array2[9] + array[10] * array2[10] + array[14] * array2[11], array[3] * array2[8] + array[7] * array2[9] + array[11] * array2[10] + array[15] * array2[11], array[0] * array2[12] + array[4] * array2[13] + array[8] * array2[14] + array[12] * array2[15], array[1] * array2[12] + array[5] * array2[13] + array[9] * array2[14] + array[13] * array2[15], array[2] * array2[12] + array[6] * array2[13] + array[10] * array2[14] + array[14] * array2[15], array[3] * array2[12] + array[7] * array2[13] + array[11] * array2[14] + array[15] * array2[15] }, array3);
    }
}
