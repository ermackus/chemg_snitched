package com.kingagroot.kingdraw.model;

import java.io.Serializable;

public class BkFileModel implements Serializable
{
    private String name;
    private String smiles;
    
    public String getName() {
        return this.name;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
}
