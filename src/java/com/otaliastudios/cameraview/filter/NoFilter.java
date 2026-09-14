package com.otaliastudios.cameraview.filter;

public final class NoFilter extends BaseFilter
{
    public String getFragmentShader() {
        return this.createDefaultFragmentShader();
    }
}
