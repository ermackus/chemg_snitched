package com.kingagroot.component.ui.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "GAtom")
public class GAtom3D
{
    @Column(name = "colorHex")
    public int colorHex;
    @Column(name = "gIndex")
    public int index;
    @Column(name = "name")
    public String name;
    @Column(name = "outmostElectrons")
    private int outmostElectrons;
    @Column(name = "radius")
    public float radius;
    @Column(name = "valences")
    private String valences;
    
    public GAtom3D() {
        this.name = "";
        this.colorHex = 0;
    }
}
