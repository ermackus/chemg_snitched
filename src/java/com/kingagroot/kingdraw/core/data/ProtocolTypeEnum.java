package com.kingagroot.kingdraw.core.data;

public enum ProtocolTypeEnum
{
    private static final ProtocolTypeEnum[] $VALUES;
    
    CDX(4, ".cdx"), 
    KDX(5, ".kdx"), 
    KING(1, ".king"), 
    MOL_V2000(2, ".mol"), 
    MOL_V3000(3, ".mol");
    
    public int code;
    public String extension;
    
    private ProtocolTypeEnum(final int code, final String extension) {
        this.code = code;
        this.extension = extension;
    }
    
    public static ProtocolTypeEnum valueOfCode(final int n) {
        for (final ProtocolTypeEnum protocolTypeEnum : values()) {
            if (protocolTypeEnum.code == n) {
                return protocolTypeEnum;
            }
        }
        return ProtocolTypeEnum.KDX;
    }
}
