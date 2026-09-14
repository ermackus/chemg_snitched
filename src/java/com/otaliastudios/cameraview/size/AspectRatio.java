package com.otaliastudios.cameraview.size;

import java.util.HashMap;

public class AspectRatio implements Comparable<AspectRatio>
{
    static final HashMap<String, AspectRatio> sCache;
    private final int mX;
    private final int mY;
    
    static {
        sCache = new HashMap(16);
    }
    
    private AspectRatio(final int mx, final int my) {
        this.mX = mx;
        this.mY = my;
    }
    
    private static int gcd(int n, int n2) {
        int n3;
        while (true) {
            n3 = n;
            n = n2;
            if (n == 0) {
                break;
            }
            n2 = n3 % n;
        }
        return n3;
    }
    
    public static AspectRatio of(int n, final int n2) {
        final int gcd = gcd(n, n2);
        int n3 = n;
        if (gcd > 0) {
            n3 = n / gcd;
        }
        n = n2;
        if (gcd > 0) {
            n = n2 / gcd;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(n3);
        sb.append(":");
        sb.append(n);
        final String string = sb.toString();
        AspectRatio aspectRatio;
        if ((aspectRatio = (AspectRatio)AspectRatio.sCache.get((Object)string)) == null) {
            aspectRatio = new AspectRatio(n3, n);
            AspectRatio.sCache.put((Object)string, (Object)aspectRatio);
        }
        return aspectRatio;
    }
    
    public static AspectRatio of(final Size size) {
        return of(size.getWidth(), size.getHeight());
    }
    
    public static AspectRatio parse(final String s) {
        final String[] split = s.split(":");
        if (split.length == 2) {
            return of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        throw new NumberFormatException("Illegal AspectRatio string. Must be x:y");
    }
    
    public int compareTo(final AspectRatio aspectRatio) {
        return Float.compare(this.toFloat(), aspectRatio.toFloat());
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        boolean b2 = b;
        if (o instanceof AspectRatio) {
            b2 = b;
            if (this.toFloat() == ((AspectRatio)o).toFloat()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public AspectRatio flip() {
        return of(this.mY, this.mX);
    }
    
    public int getX() {
        return this.mX;
    }
    
    public int getY() {
        return this.mY;
    }
    
    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.toFloat());
    }
    
    public boolean matches(final Size size) {
        return this.equals(of(size));
    }
    
    public boolean matches(final Size size, final float n) {
        return Math.abs(this.toFloat() - of(size).toFloat()) <= n;
    }
    
    public float toFloat() {
        return this.mX / (float)this.mY;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mX);
        sb.append(":");
        sb.append(this.mY);
        return sb.toString();
    }
}
