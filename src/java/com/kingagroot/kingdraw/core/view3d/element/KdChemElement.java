package com.kingagroot.kingdraw.core.view3d.element;

import java.util.ArrayList;
import java.util.List;

public abstract class KdChemElement extends KdElement
{
    private List<KdChemElement> relationElements;
    
    public KdChemElement() {
        this.relationElements = (List<KdChemElement>)new ArrayList();
    }
    
    public void addRelate(final KdChemElement kdChemElement) {
        this.relationElements.add((Object)kdChemElement);
    }
    
    public List<KdChemElement> getRelationElements() {
        return this.relationElements;
    }
}
