package com.kingagroot.kingdraw.core.graphics.tiff.exceptions;

public class NotEnoughtMemoryException extends RuntimeException
{
    private int availableMemory;
    private int needMemory;
    
    public NotEnoughtMemoryException(final int availableMemory, final int needMemory) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Available memory is not enought to decode image. Available ");
        sb.append(availableMemory);
        sb.append(" bytes. Need ");
        sb.append(needMemory);
        sb.append(" bytes.");
        super(sb.toString());
        this.availableMemory = availableMemory;
        this.needMemory = needMemory;
    }
    
    public int getAvailableMemory() {
        return this.availableMemory;
    }
    
    public int getNeedMemory() {
        return this.needMemory;
    }
}
