package com.kingagroot.kingdraw.core.graphics.svg;

import com.kingagroot.kingdraw.core.graphics.svg.utils.RenderOptionsBase;
import android.graphics.Picture;
import android.graphics.Canvas;
import java.util.Set;
import com.kingagroot.kingdraw.core.graphics.svg.utils.SVGBase$Svg;
import android.graphics.RectF;
import android.graphics.Path;
import android.content.res.Resources;
import android.content.Context;
import java.io.InputStream;
import java.io.IOException;
import android.content.res.AssetManager;
import com.kingagroot.kingdraw.core.graphics.svg.utils.SVGBase;

public class SVG
{
    private static final String VERSION = "1.5";
    private SVGBase base;
    
    private SVG(final SVGBase base) {
        this.base = base;
    }
    
    public static void deregisterExternalFileResolver() {
        SVGBase.deregisterExternalFileResolver();
    }
    
    public static SVG getFromAsset(final AssetManager assetManager, final String s) throws SVGParseException, IOException {
        return new SVG(SVGBase.getFromAsset(assetManager, s));
    }
    
    public static SVG getFromInputStream(final InputStream inputStream) throws SVGParseException {
        return new SVG(SVGBase.getFromInputStream(inputStream));
    }
    
    public static SVG getFromResource(final Context context, final int n) throws SVGParseException {
        return getFromResource(context.getResources(), n);
    }
    
    public static SVG getFromResource(final Resources resources, final int n) throws SVGParseException {
        return new SVG(SVGBase.getFromResource(resources, n));
    }
    
    public static SVG getFromString(final String s) throws SVGParseException {
        return new SVG(SVGBase.getFromString(s));
    }
    
    public static String getVersion() {
        return "1.5";
    }
    
    public static Path parsePath(final String s) {
        return SVGBase.parsePath(s);
    }
    
    public static void registerExternalFileResolver(final SVGExternalFileResolver svgExternalFileResolver) {
        SVGBase.registerExternalFileResolver(svgExternalFileResolver);
    }
    
    public static void setInternalEntitiesEnabled(final boolean internalEntitiesEnabled) {
        SVGBase.setInternalEntitiesEnabled(internalEntitiesEnabled);
    }
    
    public float getDocumentAspectRatio() {
        return this.base.getDocumentAspectRatio();
    }
    
    public String getDocumentDescription() {
        return this.base.getDocumentDescription();
    }
    
    public float getDocumentHeight() {
        return this.base.getDocumentWidth();
    }
    
    public PreserveAspectRatio getDocumentPreserveAspectRatio() {
        return this.base.getDocumentPreserveAspectRatio();
    }
    
    public String getDocumentSVGVersion() {
        return this.base.getDocumentSVGVersion();
    }
    
    public String getDocumentTitle() {
        return this.base.getDocumentTitle();
    }
    
    public RectF getDocumentViewBox() {
        return this.base.getDocumentViewBox();
    }
    
    public float getDocumentWidth() {
        return this.base.getDocumentWidth();
    }
    
    public SVGExternalFileResolver getExternalFileResolver() {
        return this.base.getExternalFileResolver();
    }
    
    public float getRenderDPI() {
        return this.base.getRenderDPI();
    }
    
    SVGBase$Svg getRootElement() {
        return this.base.getRootElement();
    }
    
    public Set<String> getViewList() {
        return this.base.getViewList();
    }
    
    public boolean isInternalEntitiesEnabled() {
        return this.base.isInternalEntitiesEnabled();
    }
    
    public void renderToCanvas(final Canvas canvas) {
        this.renderToCanvas(canvas, (RenderOptions)null);
    }
    
    public void renderToCanvas(final Canvas canvas, final RectF rectF) {
        this.base.renderToCanvas(canvas, rectF);
    }
    
    public void renderToCanvas(final Canvas canvas, final RenderOptions renderOptions) {
        this.base.renderToCanvas(canvas, renderOptions);
    }
    
    public Picture renderToPicture() {
        return this.base.renderToPicture(null);
    }
    
    public Picture renderToPicture(final int n, final int n2) {
        return this.renderToPicture(n, n2, null);
    }
    
    public Picture renderToPicture(final int n, final int n2, final RenderOptions renderOptions) {
        return this.base.renderToPicture(n, n2, (RenderOptionsBase)renderOptions);
    }
    
    public Picture renderToPicture(final RenderOptions renderOptions) {
        return this.base.renderToPicture((RenderOptionsBase)renderOptions);
    }
    
    public void renderViewToCanvas(final String s, final Canvas canvas) {
        this.renderToCanvas(canvas, RenderOptions.create().view(s));
    }
    
    public void renderViewToCanvas(final String s, final Canvas canvas, final RectF rectF) {
        this.base.renderViewToCanvas(s, canvas, rectF);
    }
    
    public Picture renderViewToPicture(final String s, final int n, final int n2) {
        return this.base.renderViewToPicture(s, n, n2);
    }
    
    public void setDocumentHeight(final float documentHeight) {
        this.base.setDocumentHeight(documentHeight);
    }
    
    public void setDocumentHeight(final String documentHeight) throws SVGParseException {
        this.base.setDocumentHeight(documentHeight);
    }
    
    public void setDocumentPreserveAspectRatio(final PreserveAspectRatio documentPreserveAspectRatio) {
        this.base.setDocumentPreserveAspectRatio(documentPreserveAspectRatio);
    }
    
    public void setDocumentViewBox(final float n, final float n2, final float n3, final float n4) {
        this.base.setDocumentViewBox(n, n2, n3, n4);
    }
    
    public void setDocumentWidth(final float documentWidth) {
        this.base.setDocumentWidth(documentWidth);
    }
    
    public void setDocumentWidth(final String documentWidth) throws SVGParseException {
        this.base.setDocumentWidth(documentWidth);
    }
    
    public void setRenderDPI(final float renderDPI) {
        this.base.setRenderDPI(renderDPI);
    }
}
