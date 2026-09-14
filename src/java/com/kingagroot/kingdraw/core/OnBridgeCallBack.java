package com.kingagroot.kingdraw.core;

public interface OnBridgeCallBack
{
    void SelectMolecule(final float p0, final float p1);
    
    String chemFormulaParse(final String p0);
    
    String findChirlity(final String p0);
    
    void findChirlityAsync(final String p0);
    
    void struct2Name(final String p0);
}
