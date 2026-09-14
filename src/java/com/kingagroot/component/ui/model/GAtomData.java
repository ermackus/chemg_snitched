package com.kingagroot.component.ui.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "GAtom")
public class GAtomData
{
    @Column(name = "abundances")
    private String abundances;
    @Column(name = "boilingPoint")
    private float boilingPoint;
    @Column(name = "colorHex")
    public int colorHex;
    @Column(name = "density")
    private float density;
    @Column(name = "electronegativity")
    private float electronegativity;
    @Column(name = "gIndex")
    public int gIndex;
    @Column(name = "ionizationPotential")
    private float ionizationPotential;
    @Column(name = "meltingPoint")
    private float meltingPoint;
    @Column(name = "name")
    public String name;
    @Column(name = "nameEn")
    private final String nameEn;
    @Column(name = "nameZh")
    private final String nameZh;
    @Column(name = "radius")
    public float radius;
    @Column(name = "valences")
    private String valences;
    @Column(name = "weight")
    private float weight;
    
    public GAtomData() {
        this.nameZh = "";
        this.colorHex = 0;
        this.nameEn = "";
        this.name = "";
    }
}
