package com.kingagroot.kingdraw.core.view3d.bean;

import android.text.TextUtils;

public class KdAtom
{
    public int colorHex;
    public int index;
    public String name;
    public int outmostElectrons;
    public float radius;
    public String valences;
    
    public KdAtom() {
        this.name = "";
        this.colorHex = 0;
    }
    
    public int[] getValenceArray() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.valences)) {
                final String[] split = this.valences.split(",");
                final int[] array = new int[split.length];
                for (int i = 0; i < split.length; ++i) {
                    array[i] = Integer.valueOf(split[i]);
                }
                return array;
            }
        }
        catch (final NumberFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
