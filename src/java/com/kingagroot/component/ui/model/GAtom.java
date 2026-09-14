package com.kingagroot.component.ui.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GAtom")
public class GAtom implements Serializable
{
    @Column(name = "abundances")
    private String abundances;
    @Column(name = "boilingPoint")
    private float boilingPoint;
    @Column(name = "colorHex")
    public int colorHex;
    @Column(name = "density")
    private float density;
    @Column(name = "eleLevel")
    private int eleLevel;
    @Column(name = "electronegativity")
    private float electronegativity;
    @Column(name = "groupCode")
    private int groupCode;
    @Column(name = "gIndex")
    public int index;
    @Column(name = "ionizationPotential")
    private float ionizationPotential;
    @Column(name = "isCollect")
    private boolean isCollect;
    @Column(name = "meltingPoint")
    private float meltingPoint;
    @Column(name = "name")
    public String name;
    @Column(name = "nameEn")
    private String nameEn;
    @Column(name = "nameZh")
    private String nameZh;
    @Column(name = "outmostElectrons")
    private int outmostElectrons;
    @Column(name = "radius")
    public float radius;
    @Column(isId = true, name = "rowid")
    private int rowid;
    @Column(name = "valences")
    private String valences;
    @Column(name = "weight")
    private float weight;
    
    public GAtom() {
        this.name = "";
        this.nameZh = "";
        this.nameEn = "";
        this.colorHex = 0;
    }
    
    public String getAbundances() {
        return this.abundances;
    }
    
    public float getBoilingPoint() {
        return this.boilingPoint;
    }
    
    public int getColorHex() {
        return this.colorHex;
    }
    
    public float getDensity() {
        return this.density;
    }
    
    public int getEleLevel() {
        return this.eleLevel;
    }
    
    public float getElectronegativity() {
        return this.electronegativity;
    }
    
    public int getGroupCode() {
        return this.groupCode;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public float getIonizationPotential() {
        return this.ionizationPotential;
    }
    
    public float getMeltingPoint() {
        return this.meltingPoint;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameEn() {
        return this.nameEn;
    }
    
    public String getNameZh() {
        return this.nameZh;
    }
    
    public int getOutmostElectrons() {
        return this.outmostElectrons;
    }
    
    public float getRadius() {
        return this.radius;
    }
    
    public String getValences() {
        return this.valences;
    }
    
    public float getWeight() {
        return this.weight;
    }
    
    public boolean isCollect() {
        return this.isCollect;
    }
    
    public void setAbundances(final String abundances) {
        this.abundances = abundances;
    }
    
    public void setBoilingPoint(final float boilingPoint) {
        this.boilingPoint = boilingPoint;
    }
    
    public void setCollect(final boolean isCollect) {
        this.isCollect = isCollect;
    }
    
    public void setColorHex(final int colorHex) {
        this.colorHex = colorHex;
    }
    
    public void setDensity(final float density) {
        this.density = density;
    }
    
    public void setEleLevel(final int eleLevel) {
        this.eleLevel = eleLevel;
    }
    
    public void setElectronegativity(final float electronegativity) {
        this.electronegativity = electronegativity;
    }
    
    public void setGroupCode(final int groupCode) {
        this.groupCode = groupCode;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public void setIonizationPotential(final float ionizationPotential) {
        this.ionizationPotential = ionizationPotential;
    }
    
    public void setMeltingPoint(final float meltingPoint) {
        this.meltingPoint = meltingPoint;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setNameEn(final String nameEn) {
        this.nameEn = nameEn;
    }
    
    public void setNameZh(final String nameZh) {
        this.nameZh = nameZh;
    }
    
    public void setOutmostElectrons(final int outmostElectrons) {
        this.outmostElectrons = outmostElectrons;
    }
    
    public void setRadius(final float radius) {
        this.radius = radius;
    }
    
    public void setValences(final String valences) {
        this.valences = valences;
    }
    
    public void setWeight(final float weight) {
        this.weight = weight;
    }
}
