package com.kingagroot.kingdraw.core.graphics;

import com.kingagroot.kingdraw.core.graphics.svg.PreserveAspectRatio;
import com.kingagroot.kingdraw.core.graphics.svg.RenderOptions;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import android.graphics.Paint;
import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.graphics.svg.SVG;

public class KDSVGDrawable extends KDDrawable
{
    private SVG svg;
    
    public KDSVGDrawable(final SVG svg) {
        this.svg = svg;
    }
    
    public void draw(final Canvas canvas, final Paint paint) {
        final SVG svg = this.svg;
        if (svg != null) {
            try {
                svg.setDocumentWidth("100%");
                this.svg.setDocumentHeight("100%");
            }
            catch (final SVGParseException ex) {
                ex.printStackTrace();
            }
            final RenderOptions renderOptions = new RenderOptions();
            renderOptions.viewPort(this.dst.left, this.dst.top, this.dst.width(), this.dst.height());
            renderOptions.preserveAspectRatio(PreserveAspectRatio.STRETCH);
            this.svg.renderToCanvas(canvas, renderOptions);
        }
    }
    
    public long getByteCount() {
        if (this.isAvailable()) {
            return 1024L;
        }
        return 0L;
    }
    
    public boolean isAvailable() {
        return this.svg != null;
    }
    
    public void release() {
        if (this.svg != null) {
            this.svg = null;
        }
    }
}
