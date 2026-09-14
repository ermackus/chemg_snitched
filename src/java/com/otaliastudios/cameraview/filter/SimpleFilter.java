package com.otaliastudios.cameraview.filter;

public final class SimpleFilter extends BaseFilter
{
    private final String fragmentShader;
    
    public SimpleFilter(final String fragmentShader) {
        this.fragmentShader = fragmentShader;
    }
    
    public String getFragmentShader() {
        return this.fragmentShader;
    }
    
    protected BaseFilter onCopy() {
        return new SimpleFilter(this.fragmentShader);
    }
}
