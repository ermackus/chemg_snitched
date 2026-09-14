package com.kingagroot.component.ui.utils;

public class GMathUtils
{
    public static float distanceTwoPoint(final double n, final double n2, final double n3, final double n4) {
        return (float)Math.sqrt(Math.pow(n - n3, 2.0) + Math.pow(n2 - n4, 2.0));
    }
    
    public static double[] rotatedPoint(double cos, double n, final double n2, final double n3, final double n4) {
        final double n5 = cos - n2;
        cos = Math.cos(n4);
        n -= n3;
        return new double[] { cos * n5 - Math.sin(n4) * n + n2, n5 * Math.sin(n4) + n * Math.cos(n4) + n3 };
    }
}
