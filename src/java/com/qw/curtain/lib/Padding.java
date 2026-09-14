package com.qw.curtain.lib;

import android.util.SparseArray;

public class Padding
{
    static final int ALL = 1;
    static final int BOTTOM = 8;
    static final int LEFT = 2;
    static final int RIGHT = 6;
    static final int TOP = 4;
    private SparseArray<Integer> paddingArrays;
    
    Padding() {
        this.paddingArrays = (SparseArray<Integer>)new SparseArray(4);
    }
    
    Padding(final int n) {
        (this.paddingArrays = (SparseArray<Integer>)new SparseArray(1)).append(1, (Object)n);
    }
    
    public static Padding all(final int n) {
        return new Padding(n);
    }
    
    public static Padding only(final int n) {
        return only(n, 0, 0, 0);
    }
    
    public static Padding only(final int n, final int n2) {
        return only(n, n2, 0, 0);
    }
    
    public static Padding only(final int n, final int n2, final int n3) {
        return only(n, n2, n3, 0);
    }
    
    public static Padding only(final int n, final int n2, final int n3, final int n4) {
        return new Padding().appendPadding(2, n).appendPadding(4, n2).appendPadding(6, n3).appendPadding(8, n4);
    }
    
    Padding appendPadding(final int n, final int n2) {
        this.paddingArrays.append(n, (Object)n2);
        return this;
    }
    
    int getSizeByDirection(final int n) {
        return (int)this.paddingArrays.get(n, (Object)0);
    }
    
    boolean isAll() {
        final int size = this.paddingArrays.size();
        boolean b = true;
        if (size != 1) {
            b = false;
        }
        return b;
    }
}
