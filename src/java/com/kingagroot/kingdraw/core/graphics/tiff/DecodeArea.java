package com.kingagroot.kingdraw.core.graphics.tiff;

public class DecodeArea
{
    public int height;
    public int width;
    public int x;
    public int y;
    
    public DecodeArea() {
    }
    
    public DecodeArea(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
