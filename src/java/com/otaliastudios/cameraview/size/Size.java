package com.otaliastudios.cameraview.size;

public class Size implements Comparable<Size>
{
    private final int mHeight;
    private final int mWidth;
    
    public Size(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public int compareTo(final Size size) {
        return this.mWidth * this.mHeight - size.mWidth * size.mHeight;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        boolean b2 = b;
        if (o instanceof Size) {
            final Size size = (Size)o;
            b2 = b;
            if (this.mWidth == size.mWidth) {
                b2 = b;
                if (this.mHeight == size.mHeight) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public Size flip() {
        return new Size(this.mHeight, this.mWidth);
    }
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public int getWidth() {
        return this.mWidth;
    }
    
    @Override
    public int hashCode() {
        final int mHeight = this.mHeight;
        final int mWidth = this.mWidth;
        return mHeight ^ (mWidth >>> 16 | mWidth << 16);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mWidth);
        sb.append("x");
        sb.append(this.mHeight);
        return sb.toString();
    }
}
