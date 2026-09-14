package com.kingagroot.kingdraw.core.view3d.utils;

import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;

public class GMath
{
    public static KdPoint calc3DPointOnExtensionOfSegment(final KdPoint kdPoint, final KdPoint kdPoint2, final float n) {
        final double n2 = kdPoint.x;
        final double n3 = kdPoint.y;
        final double n4 = kdPoint.z;
        final double n5 = kdPoint2.x;
        final double n6 = kdPoint2.y;
        final double n7 = kdPoint2.z;
        final double n8 = n2 - n5;
        final double n9 = n3 - n6;
        final double n10 = n4 - n7;
        final double sqrt = Math.sqrt(n8 * n8 + n9 * n9 + n10 * n10);
        final double n11 = n;
        return new KdPoint((float)((n5 - n2) * n11 / sqrt + n2), (float)((n6 - n3) * n11 / sqrt + n3), (float)(n11 * (n7 - n4) / sqrt + n4));
    }
    
    public static boolean calc3DTriangleMidpoint(final KdPoint kdPoint, final KdPoint kdPoint2, final KdPoint kdPoint3, final KdPoint kdPoint4) {
        final double n = kdPoint.x;
        final double n2 = kdPoint.y;
        final double n3 = kdPoint.z;
        final double n4 = kdPoint2.x;
        final double n5 = kdPoint2.y;
        final double n6 = kdPoint2.z;
        final double n7 = kdPoint3.x;
        final double n8 = kdPoint3.y;
        final double n9 = kdPoint3.z;
        final double n10 = n2 * n6 - n5 * n3 - n2 * n9 + n8 * n3 + n5 * n9 - n8 * n6;
        final double n11 = -(n * n6 - n4 * n3 - n * n9 + n7 * n3 + n4 * n9 - n7 * n6);
        final double n12 = n * n5;
        final double n13 = n4 * n2;
        final double n14 = n * n8;
        final double n15 = n7 * n2;
        final double n16 = n4 * n8;
        final double n17 = n7 * n5;
        final double n18 = n12 - n13 - n14 + n15 + n16 - n17;
        final double n19 = -(n12 * n9 - n14 * n6 - n13 * n9 + n16 * n3 + n15 * n6 - n17 * n3);
        final double n20 = (n4 - n) * 2.0;
        final double n21 = (n5 - n2) * 2.0;
        final double n22 = (n6 - n3) * 2.0;
        final double n23 = n * n + n2 * n2 + n3 * n3;
        final double n24 = n23 - n4 * n4 - n5 * n5 - n6 * n6;
        final double n25 = (n7 - n) * 2.0;
        final double n26 = (n8 - n2) * 2.0;
        final double n27 = (n9 - n3) * 2.0;
        final double n28 = n23 - n7 * n7 - n8 * n8 - n9 * n9;
        final double n29 = -(n11 * n22 * n28 - n11 * n27 * n24 - n21 * n18 * n28 + n21 * n27 * n19 + n26 * n18 * n24 - n26 * n22 * n19);
        final double n30 = n10 * n21;
        final double n31 = n10 * n26;
        final double n32 = n20 * n11;
        final double n33 = n26 * n20;
        final double n34 = n11 * n25;
        final double n35 = n21 * n25;
        final double n36 = n30 * n27 - n31 * n22 - n32 * n27 + n33 * n18 + n34 * n22 - n35 * n18;
        final double n37 = n29 / n36;
        final double n38 = (n10 * n22 * n28 - n10 * n27 * n24 - n20 * n18 * n28 + n20 * n27 * n19 + n18 * n25 * n24 - n25 * n22 * n19) / n36;
        final double n39 = -(n30 * n28 - n31 * n24 - n32 * n28 + n33 * n19 + n34 * n24 - n35 * n19) / n36;
        if (!Double.isNaN(n37) && !Double.isNaN(n38) && !Double.isNaN(n39)) {
            kdPoint4.x = (float)n37;
            kdPoint4.y = (float)n38;
            kdPoint4.z = (float)n39;
            return true;
        }
        return false;
    }
}
