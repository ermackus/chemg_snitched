package com.kingagroot.kingdraw.core.graphics.tiff.exceptions;

public class CantOpenFileException extends RuntimeException
{
    private String fileName;
    
    public CantOpenFileException(final String fileName) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Can't open file ");
        sb.append(fileName);
        super(sb.toString());
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return this.fileName;
    }
}
