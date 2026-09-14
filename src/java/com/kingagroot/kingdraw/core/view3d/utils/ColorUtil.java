package com.kingagroot.kingdraw.core.view3d.utils;

public class ColorUtil
{
    public static float[] IntColorRGB(final int n) {
        return new float[] { ((0xFF0000 & n) >> 16) / 255.0f, ((0xFF00 & n) >> 8) / 255.0f, (n & 0xFF) / 255.0f, 1.0f };
    }
}
