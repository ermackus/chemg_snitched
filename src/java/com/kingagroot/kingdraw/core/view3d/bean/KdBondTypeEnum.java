package com.kingagroot.kingdraw.core.view3d.bean;

public enum KdBondTypeEnum
{
    private static final KdBondTypeEnum[] $VALUES;
    
    G_ANY_BOND(8, 1), 
    G_BOLD_BOND(12, 1), 
    G_BOLD_DOUBLE_BOND(17, 2), 
    G_COORDINATE_BOND(9, 1), 
    G_DASHED_BOND(10, 1), 
    G_DASHED_DOUBLE_BOND(4, 2), 
    G_DATIVE_BOND(21, 0), 
    G_DOUBLE_AROMATIC_BOND(7, 2), 
    G_DOUBLE_BOND(2, 2), 
    G_DOUBLE_EITHER_BOND(16, 2), 
    G_HASHED_BOND(14, 1), 
    G_HASHED_WEDGED_BOND(13, 1), 
    G_HOLLOW_WEDGED_BOND(15, 1), 
    G_HYDROGEN_BOND(18, 0), 
    G_ION_BOND(19, 0), 
    G_SINGLE_AROMATIC_BOND(6, 2), 
    G_SINGLE_BOND(1, 1), 
    G_SINGLE_DOUBLE_BOND(5, 2), 
    G_TRIPLE_BOND(3, 3), 
    G_WAVY_BOND(20, 1), 
    G_WEDGE_BOND(11, 1);
    
    public int code;
    public int covalent;
    
    private KdBondTypeEnum(final int code, final int covalent) {
        this.code = code;
        this.covalent = covalent;
    }
    
    public static KdBondTypeEnum valueOf(final int n) {
        for (final KdBondTypeEnum kdBondTypeEnum : values()) {
            if (kdBondTypeEnum.code == n) {
                return kdBondTypeEnum;
            }
        }
        return KdBondTypeEnum.G_SINGLE_BOND;
    }
}
