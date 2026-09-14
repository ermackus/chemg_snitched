package com.yalantis.ucrop.util;

public final class CubicEasing
{
    public static float easeIn(float n, final float n2, final float n3, final float n4) {
        n /= n4;
        return n3 * n * n * n + n2;
    }
    
    public static float easeInOut(float n, final float n2, float n3, final float n4) {
        n /= n4 / 2.0f;
        n3 /= 2.0f;
        if (n < 1.0f) {
            n *= n3 * n * n;
        }
        else {
            n -= 2.0f;
            n = n3 * (n * n * n + 2.0f);
        }
        return n + n2;
    }
    
    public static float easeOut(float n, final float n2, final float n3, final float n4) {
        n = n / n4 - 1.0f;
        return n3 * (n * n * n + 1.0f) + n2;
    }
}
