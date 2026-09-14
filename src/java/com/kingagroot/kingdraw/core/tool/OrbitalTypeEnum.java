package com.kingagroot.kingdraw.core.tool;

public enum OrbitalTypeEnum
{
    private static final OrbitalTypeEnum[] $VALUES;
    
    ORBITAL_CIRCLE_3D("2"), 
    ORBITAL_CIRCLE_BLACK("3"), 
    ORBITAL_CIRCLE_WHITE("1"), 
    ORBITAL_OVAL_3D("5"), 
    ORBITAL_OVAL_BLACK("6"), 
    ORBITAL_OVAL_WHITE("4"), 
    ORBITAL_PETALS_3D("8"), 
    ORBITAL_PETALS_3DS_WHITE("16"), 
    ORBITAL_PETALS_3DS_WHITES("20"), 
    ORBITAL_PETALS_3D_SMALLWHITE("12"), 
    ORBITAL_PETALS_3D_WHITE("10"), 
    ORBITAL_PETALS_BLACK("9"), 
    ORBITAL_PETALS_BLACKS_WHITE("17"), 
    ORBITAL_PETALS_BLACKS_WHITES("21"), 
    ORBITAL_PETALS_BLACK_SMALLWHITE("13"), 
    ORBITAL_PETALS_BLACK_WHITE("11"), 
    ORBITAL_PETALS_WHITE("7"), 
    ORBITAL_PETALS_WHITES_3D("18"), 
    ORBITAL_PETALS_WHITES_BLACK("19"), 
    ORBITAL_PETALS_WHITE_SMALL3D("14"), 
    ORBITAL_PETALS_WHITE_SMALLBLACK("15");
    
    public String orbitalType;
    
    private OrbitalTypeEnum(final String orbitalType) {
        this.orbitalType = orbitalType;
    }
}
