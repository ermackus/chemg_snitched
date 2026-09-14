package com.kingagroot.kingdraw.core.graphics.svg.utils;

import com.kingagroot.kingdraw.core.graphics.svg.CSS;
import com.kingagroot.kingdraw.core.graphics.svg.PreserveAspectRatio;

public class RenderOptionsBase
{
    String css;
    CSSParser.Ruleset cssRuleset;
    PreserveAspectRatio preserveAspectRatio;
    String targetId;
    SVGBase.Box viewBox;
    String viewId;
    SVGBase.Box viewPort;
    
    public RenderOptionsBase() {
        this.css = null;
        this.cssRuleset = null;
        this.preserveAspectRatio = null;
        this.targetId = null;
        this.viewBox = null;
        this.viewId = null;
        this.viewPort = null;
    }
    
    public RenderOptionsBase(final RenderOptionsBase renderOptionsBase) {
        this.css = null;
        this.cssRuleset = null;
        this.preserveAspectRatio = null;
        this.targetId = null;
        this.viewBox = null;
        this.viewId = null;
        this.viewPort = null;
        if (renderOptionsBase == null) {
            return;
        }
        this.css = renderOptionsBase.css;
        this.cssRuleset = renderOptionsBase.cssRuleset;
        this.preserveAspectRatio = renderOptionsBase.preserveAspectRatio;
        this.viewBox = renderOptionsBase.viewBox;
        this.viewId = renderOptionsBase.viewId;
        this.viewPort = renderOptionsBase.viewPort;
        this.targetId = renderOptionsBase.targetId;
    }
    
    public static RenderOptionsBase create() {
        return new RenderOptionsBase();
    }
    
    public RenderOptionsBase css(final CSS css) {
        this.cssRuleset = css.cssRuleset;
        this.css = null;
        return this;
    }
    
    public RenderOptionsBase css(final String css) {
        this.css = css;
        this.cssRuleset = null;
        return this;
    }
    
    public boolean hasCss() {
        final String css = this.css;
        return (css != null && css.trim().length() > 0) || this.cssRuleset != null;
    }
    
    public boolean hasPreserveAspectRatio() {
        return this.preserveAspectRatio != null;
    }
    
    public boolean hasTarget() {
        return this.targetId != null;
    }
    
    public boolean hasView() {
        return this.viewId != null;
    }
    
    public boolean hasViewBox() {
        return this.viewBox != null;
    }
    
    public boolean hasViewPort() {
        return this.viewPort != null;
    }
    
    public RenderOptionsBase preserveAspectRatio(final PreserveAspectRatio preserveAspectRatio) {
        this.preserveAspectRatio = preserveAspectRatio;
        return this;
    }
    
    public RenderOptionsBase target(final String targetId) {
        this.targetId = targetId;
        return this;
    }
    
    public RenderOptionsBase view(final String viewId) {
        this.viewId = viewId;
        return this;
    }
    
    public RenderOptionsBase viewBox(final float n, final float n2, final float n3, final float n4) {
        this.viewBox = new SVGBase.Box(n, n2, n3, n4);
        return this;
    }
    
    public RenderOptionsBase viewPort(final float n, final float n2, final float n3, final float n4) {
        this.viewPort = new SVGBase.Box(n, n2, n3, n4);
        return this;
    }
}
