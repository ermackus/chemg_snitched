package com.kingagroot.kingdraw.core.graphics.svg;

import com.kingagroot.kingdraw.core.graphics.svg.utils.RenderOptionsBase;

public class RenderOptions extends RenderOptionsBase
{
    public RenderOptions() {
    }
    
    public RenderOptions(final RenderOptions renderOptions) {
        super((RenderOptionsBase)renderOptions);
    }
    
    public static RenderOptions create() {
        return new RenderOptions();
    }
    
    public RenderOptions css(final CSS css) {
        return (RenderOptions)super.css(css);
    }
    
    public RenderOptions css(final String s) {
        return (RenderOptions)super.css(s);
    }
    
    public boolean hasCss() {
        return super.hasCss();
    }
    
    public boolean hasPreserveAspectRatio() {
        return super.hasPreserveAspectRatio();
    }
    
    public boolean hasTarget() {
        return super.hasTarget();
    }
    
    public boolean hasView() {
        return super.hasView();
    }
    
    public boolean hasViewBox() {
        return super.hasViewBox();
    }
    
    public boolean hasViewPort() {
        return super.hasViewPort();
    }
    
    public RenderOptions preserveAspectRatio(final PreserveAspectRatio preserveAspectRatio) {
        return (RenderOptions)super.preserveAspectRatio(preserveAspectRatio);
    }
    
    public RenderOptions target(final String s) {
        return (RenderOptions)super.target(s);
    }
    
    public RenderOptions view(final String s) {
        return (RenderOptions)super.view(s);
    }
    
    public RenderOptions viewBox(final float n, final float n2, final float n3, final float n4) {
        return (RenderOptions)super.viewBox(n, n2, n3, n4);
    }
    
    public RenderOptions viewPort(final float n, final float n2, final float n3, final float n4) {
        return (RenderOptions)super.viewPort(n, n2, n3, n4);
    }
}
