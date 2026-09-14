package com.kingagroot.kingdraw.core.graphics.tiff.exceptions;

public class DecodeTiffException extends RuntimeException
{
    private String aditionalInfo;
    private String fileName;
    
    public DecodeTiffException(final String fileName) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Could not decode tiff file ");
        sb.append(fileName);
        super(sb.toString());
        this.fileName = fileName;
    }
    
    public DecodeTiffException(final String fileName, final String aditionalInfo) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Could not decode tiff file ");
        sb.append(fileName);
        sb.append("\n");
        sb.append(aditionalInfo);
        super(sb.toString());
        this.fileName = fileName;
        this.aditionalInfo = aditionalInfo;
    }
    
    public String getAditionalInfo() {
        return this.aditionalInfo;
    }
    
    public String getFileName() {
        return this.fileName;
    }
}
