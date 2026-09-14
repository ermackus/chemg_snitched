package com.kingagroot.kingdraw.model;

public enum FileType
{
    private static final FileType[] $VALUES;
    
    CDX(".cdx", 4), 
    JPG(".jpg", 8), 
    KDX(".kdx", 5), 
    KING(".king", 1), 
    MOL_V2000(".mol", 2), 
    MOL_V3000(".mol", 3), 
    PNG(".png", 7);
    
    public int code;
    public String extension;
    
    private FileType(final String extension, final int code) {
        this.extension = extension;
        this.code = code;
    }
    
    public static FileType valueByExt(final String s) {
        for (final FileType fileType : values()) {
            if (fileType.extension.equals((Object)s)) {
                return fileType;
            }
        }
        return null;
    }
    
    public static FileType valueOfByType(final String s) {
        if (s.equals((Object)"MOL")) {
            return FileType.MOL_V3000;
        }
        return valueOf(s);
    }
    
    public static FileType valueOfCode(final int n) {
        for (final FileType fileType : values()) {
            if (fileType.code == n) {
                return fileType;
            }
        }
        return FileType.KING;
    }
}
