package com.kingagroot.kingdraw.core.model;

public class ChemPropertyConfig
{
    public int decimalCount;
    public boolean enableElemAnal;
    public boolean enableExactMass;
    public boolean enableFormula;
    public boolean enableMolWt;
    public boolean enableMz;
    
    public ChemPropertyConfig() {
        this.enableFormula = true;
        this.enableExactMass = true;
        this.enableMolWt = true;
        this.enableElemAnal = true;
        this.enableMz = true;
        this.decimalCount = 5;
    }
}
