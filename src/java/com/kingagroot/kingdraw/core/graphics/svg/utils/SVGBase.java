package com.kingagroot.kingdraw.core.graphics.svg.utils;

import android.graphics.Matrix;
import android.graphics.Picture;
import com.kingagroot.kingdraw.core.graphics.svg.RenderOptions;
import android.graphics.Canvas;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.graphics.svg.PreserveAspectRatio;
import android.graphics.Path;
import java.io.ByteArrayInputStream;
import android.content.res.Resources;
import android.content.Context;
import java.io.InputStream;
import java.io.IOException;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import android.content.res.AssetManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;

public class SVGBase
{
    private static final int DEFAULT_PICTURE_HEIGHT = 512;
    private static final int DEFAULT_PICTURE_WIDTH = 512;
    private static final double SQRT2 = 1.414213562373095;
    private static boolean enableInternalEntitiesSingleton = true;
    private static SVGExternalFileResolver externalFileResolverSingleton;
    private final CSSParser.Ruleset cssRules;
    private String desc;
    private final boolean enableInternalEntities;
    private final SVGExternalFileResolver externalFileResolver;
    private final Map<String, SVGBase.SVGBase$SvgElementBase> idToElementMap;
    private float renderDPI;
    private SVGBase.SVGBase$Svg rootElement;
    private String title;
    
    SVGBase(final boolean enableInternalEntities, final SVGExternalFileResolver externalFileResolver) {
        this.rootElement = null;
        this.title = "";
        this.desc = "";
        this.renderDPI = 96.0f;
        this.cssRules = new CSSParser.Ruleset();
        this.idToElementMap = (Map<String, SVGBase.SVGBase$SvgElementBase>)new HashMap();
        this.enableInternalEntities = enableInternalEntities;
        this.externalFileResolver = externalFileResolver;
    }
    
    protected static SVGParser createParser() {
        return new SVGParserImpl().setInternalEntitiesEnabled(SVGBase.enableInternalEntitiesSingleton).setExternalFileResolver(SVGBase.externalFileResolverSingleton);
    }
    
    private String cssQuotedString(final String s) {
        String s2;
        if (s.startsWith("\"") && s.endsWith("\"")) {
            s2 = s.substring(1, s.length() - 1).replace((CharSequence)"\\\"", (CharSequence)"\"");
        }
        else {
            s2 = s;
            if (s.startsWith("'")) {
                s2 = s;
                if (s.endsWith("'")) {
                    s2 = s.substring(1, s.length() - 1).replace((CharSequence)"\\'", (CharSequence)"'");
                }
            }
        }
        return s2.replace((CharSequence)"\\\n", (CharSequence)"").replace((CharSequence)"\\A", (CharSequence)"\n");
    }
    
    public static void deregisterExternalFileResolver() {
        SVGBase.externalFileResolverSingleton = null;
    }
    
    private Box getDocumentDimensions(float floatValue) {
        final Length width = this.rootElement.width;
        final Length height = this.rootElement.height;
        if (width != null && !width.isZero() && width.unit != Unit.percent && width.unit != Unit.em && width.unit != Unit.ex) {
            final float floatValue2 = width.floatValue(floatValue);
            if (height != null) {
                if (height.isZero() || height.unit == Unit.percent || height.unit == Unit.em || height.unit == Unit.ex) {
                    return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
                }
                floatValue = height.floatValue(floatValue);
            }
            else if (this.rootElement.viewBox != null) {
                floatValue = this.rootElement.viewBox.height * floatValue2 / this.rootElement.viewBox.width;
            }
            else {
                floatValue = floatValue2;
            }
            return new Box(0.0f, 0.0f, floatValue2, floatValue);
        }
        return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
    }
    
    private SVGBase.SVGBase$SvgElementBase getElementById(final SvgContainer svgContainer, final String s) {
        final SVGBase.SVGBase$SvgElementBase svgBase$SvgElementBase = (SVGBase.SVGBase$SvgElementBase)svgContainer;
        if (s.equals((Object)svgBase$SvgElementBase.id)) {
            return svgBase$SvgElementBase;
        }
        for (final SvgObject svgObject : svgContainer.getChildren()) {
            if (!(svgObject instanceof SVGBase.SVGBase$SvgElementBase)) {
                continue;
            }
            final SVGBase.SVGBase$SvgElementBase svgBase$SvgElementBase2 = (SVGBase.SVGBase$SvgElementBase)svgObject;
            if (s.equals((Object)svgBase$SvgElementBase2.id)) {
                return svgBase$SvgElementBase2;
            }
            if (!(svgObject instanceof SvgContainer)) {
                continue;
            }
            final SVGBase.SVGBase$SvgElementBase elementById = this.getElementById((SvgContainer)svgObject, s);
            if (elementById != null) {
                return elementById;
            }
        }
        return null;
    }
    
    private List<SvgObject> getElementsByTagName(final String s) {
        final ArrayList list = new ArrayList();
        this.getElementsByTagName((List<SvgObject>)list, (SvgObject)this.rootElement, s);
        return (List<SvgObject>)list;
    }
    
    private void getElementsByTagName(final List<SvgObject> list, final SvgObject svgObject, final String s) {
        if (svgObject.getNodeName().equals((Object)s)) {
            list.add((Object)svgObject);
        }
        if (svgObject instanceof SvgContainer) {
            final Iterator iterator = ((SvgContainer)svgObject).getChildren().iterator();
            while (iterator.hasNext()) {
                this.getElementsByTagName(list, (SvgObject)iterator.next(), s);
            }
        }
    }
    
    public static SVGBase getFromAsset(final AssetManager p0, final String p1) throws SVGParseException, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //     5: astore_0       
        //     6: invokestatic    com/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase.createParser:()Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGParser;
        //     9: aload_0        
        //    10: invokeinterface com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParser.parseStream:(Ljava/io/InputStream;)Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //    15: astore_1       
        //    16: aload_0        
        //    17: invokevirtual   java/io/InputStream.close:()V
        //    20: aload_1        
        //    21: areturn        
        //    22: astore_1       
        //    23: aload_0        
        //    24: invokevirtual   java/io/InputStream.close:()V
        //    27: aload_1        
        //    28: athrow         
        //    29: astore_0       
        //    30: goto            20
        //    33: astore_0       
        //    34: goto            27
        //    Exceptions:
        //  throws com.kingagroot.kingdraw.core.graphics.svg.SVGParseException
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      16     22     29     Any
        //  16     20     29     33     Ljava/io/IOException;
        //  23     27     33     37     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 21, Size: 21
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static SVGBase getFromInputStream(final InputStream inputStream) throws SVGParseException {
        return createParser().parseStream(inputStream);
    }
    
    public static SVGBase getFromResource(final Context context, final int n) throws SVGParseException {
        return getFromResource(context.getResources(), n);
    }
    
    public static SVGBase getFromResource(final Resources p0, final int p1) throws SVGParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iload_1        
        //     2: invokevirtual   android/content/res/Resources.openRawResource:(I)Ljava/io/InputStream;
        //     5: astore_0       
        //     6: invokestatic    com/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase.createParser:()Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGParser;
        //     9: aload_0        
        //    10: invokeinterface com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParser.parseStream:(Ljava/io/InputStream;)Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //    15: astore_2       
        //    16: aload_0        
        //    17: invokevirtual   java/io/InputStream.close:()V
        //    20: aload_2        
        //    21: areturn        
        //    22: astore_2       
        //    23: aload_0        
        //    24: invokevirtual   java/io/InputStream.close:()V
        //    27: aload_2        
        //    28: athrow         
        //    29: astore_0       
        //    30: goto            20
        //    33: astore_0       
        //    34: goto            27
        //    Exceptions:
        //  throws com.kingagroot.kingdraw.core.graphics.svg.SVGParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      16     22     29     Any
        //  16     20     29     33     Ljava/io/IOException;
        //  23     27     33     37     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 21, Size: 21
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.b(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:2125)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:21)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static SVGBase getFromString(final String s) throws SVGParseException {
        return createParser().parseStream((InputStream)new ByteArrayInputStream(s.getBytes()));
    }
    
    public static Path parsePath(final String s) {
        return new SVGAndroidRenderer$PathConverter(SVGParserImpl.parsePath(s)).getPath();
    }
    
    public static void registerExternalFileResolver(final SVGExternalFileResolver externalFileResolverSingleton) {
        SVGBase.externalFileResolverSingleton = externalFileResolverSingleton;
    }
    
    public static void setInternalEntitiesEnabled(final boolean enableInternalEntitiesSingleton) {
        SVGBase.enableInternalEntitiesSingleton = enableInternalEntitiesSingleton;
    }
    
    void addCSSRules(final CSSParser.Ruleset ruleset) {
        this.cssRules.addAll(ruleset);
    }
    
    void clearRenderCSSRules() {
        this.cssRules.removeFromSource(CSSParser.Source.RenderOptions);
    }
    
    List<CSSParser.Rule> getCSSRules() {
        return this.cssRules.getRules();
    }
    
    public float getDocumentAspectRatio() {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            final Length width = rootElement.width;
            final Length height = this.rootElement.height;
            float n;
            float n2;
            if (width != null && height != null && width.unit != Unit.percent && height.unit != Unit.percent) {
                if (width.isZero() || height.isZero()) {
                    return -1.0f;
                }
                n = width.floatValue(this.renderDPI);
                n2 = height.floatValue(this.renderDPI);
            }
            else {
                if (this.rootElement.viewBox == null || this.rootElement.viewBox.width == 0.0f || this.rootElement.viewBox.height == 0.0f) {
                    return -1.0f;
                }
                n = this.rootElement.viewBox.width;
                n2 = this.rootElement.viewBox.height;
            }
            return n / n2;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public String getDocumentDescription() {
        if (this.rootElement != null) {
            return this.desc;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public float getDocumentHeight() {
        if (this.rootElement != null) {
            return this.getDocumentDimensions(this.renderDPI).height;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public PreserveAspectRatio getDocumentPreserveAspectRatio() {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        if (rootElement.preserveAspectRatio == null) {
            return null;
        }
        return this.rootElement.preserveAspectRatio;
    }
    
    public String getDocumentSVGVersion() {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            return rootElement.version;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public String getDocumentTitle() {
        if (this.rootElement != null) {
            return this.title;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public RectF getDocumentViewBox() {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        if (rootElement.viewBox == null) {
            return null;
        }
        return this.rootElement.viewBox.toRectF();
    }
    
    public float getDocumentWidth() {
        if (this.rootElement != null) {
            return this.getDocumentDimensions(this.renderDPI).width;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    SVGBase.SVGBase$SvgElementBase getElementById(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.equals((Object)this.rootElement.id)) {
            return (SVGBase.SVGBase$SvgElementBase)this.rootElement;
        }
        if (this.idToElementMap.containsKey((Object)s)) {
            return (SVGBase.SVGBase$SvgElementBase)this.idToElementMap.get((Object)s);
        }
        final SVGBase.SVGBase$SvgElementBase elementById = this.getElementById((SvgContainer)this.rootElement, s);
        this.idToElementMap.put((Object)s, (Object)elementById);
        return elementById;
    }
    
    public SVGExternalFileResolver getExternalFileResolver() {
        return this.externalFileResolver;
    }
    
    public float getRenderDPI() {
        return this.renderDPI;
    }
    
    public SVGBase.SVGBase$Svg getRootElement() {
        return this.rootElement;
    }
    
    public Set<String> getViewList() {
        if (this.rootElement != null) {
            final List<SvgObject> elementsByTagName = this.getElementsByTagName("view");
            final HashSet set = new HashSet(elementsByTagName.size());
            for (final SVGBase.SVGBase$View svgBase$View : elementsByTagName) {
                if (svgBase$View.id != null) {
                    ((Set)set).add((Object)svgBase$View.id);
                }
                else {
                    Log.w("AndroidSVG", "getViewList(): found a <view> without an id attribute");
                }
            }
            return (Set<String>)set;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    boolean hasCSSRules() {
        return this.cssRules.isEmpty() ^ true;
    }
    
    public boolean isInternalEntitiesEnabled() {
        return this.enableInternalEntities;
    }
    
    public void renderToCanvas(final Canvas canvas) {
        this.renderToCanvas(canvas, (RenderOptions)null);
    }
    
    public void renderToCanvas(final Canvas canvas, final RectF rectF) {
        final RenderOptions renderOptions = new RenderOptions();
        if (rectF != null) {
            renderOptions.viewPort(rectF.left, rectF.top, rectF.width(), rectF.height());
        }
        else {
            renderOptions.viewPort(0.0f, 0.0f, (float)canvas.getWidth(), (float)canvas.getHeight());
        }
        new SVGAndroidRenderer(canvas, this.renderDPI, this.externalFileResolver).renderDocument(this, (RenderOptionsBase)renderOptions);
    }
    
    public void renderToCanvas(final Canvas canvas, final RenderOptions renderOptions) {
        RenderOptions renderOptions2 = renderOptions;
        if (renderOptions == null) {
            renderOptions2 = new RenderOptions();
        }
        if (!renderOptions2.hasViewPort()) {
            renderOptions2.viewPort(0.0f, 0.0f, (float)canvas.getWidth(), (float)canvas.getHeight());
        }
        new SVGAndroidRenderer(canvas, this.renderDPI, this.externalFileResolver).renderDocument(this, (RenderOptionsBase)renderOptions2);
    }
    
    public Picture renderToPicture() {
        return this.renderToPicture(null);
    }
    
    public Picture renderToPicture(final int n, final int n2) {
        return this.renderToPicture(n, n2, null);
    }
    
    public Picture renderToPicture(final int n, final int n2, final RenderOptionsBase renderOptionsBase) {
        final Picture picture = new Picture();
        final Canvas beginRecording = picture.beginRecording(n, n2);
        RenderOptionsBase renderOptionsBase2 = null;
        Label_0070: {
            if (renderOptionsBase != null) {
                renderOptionsBase2 = renderOptionsBase;
                if (renderOptionsBase.viewPort != null) {
                    break Label_0070;
                }
            }
            RenderOptionsBase renderOptionsBase3;
            if (renderOptionsBase == null) {
                renderOptionsBase3 = new RenderOptionsBase();
            }
            else {
                renderOptionsBase3 = new RenderOptionsBase(renderOptionsBase);
            }
            renderOptionsBase3.viewPort(0.0f, 0.0f, (float)n, (float)n2);
            renderOptionsBase2 = renderOptionsBase3;
        }
        new SVGAndroidRenderer(beginRecording, this.renderDPI, this.externalFileResolver).renderDocument(this, renderOptionsBase2);
        picture.endRecording();
        return picture;
    }
    
    public Picture renderToPicture(final RenderOptionsBase renderOptionsBase) {
        Object o;
        if (renderOptionsBase != null && renderOptionsBase.hasViewBox()) {
            o = renderOptionsBase.viewBox;
        }
        else {
            o = this.rootElement.viewBox;
        }
        if (renderOptionsBase != null && renderOptionsBase.hasViewPort()) {
            return this.renderToPicture((int)Math.ceil((double)renderOptionsBase.viewPort.maxX()), (int)Math.ceil((double)renderOptionsBase.viewPort.maxY()), renderOptionsBase);
        }
        if (this.rootElement.width != null && this.rootElement.width.unit != Unit.percent && this.rootElement.height != null && this.rootElement.height.unit != Unit.percent) {
            return this.renderToPicture((int)Math.ceil((double)this.rootElement.width.floatValue(this.renderDPI)), (int)Math.ceil((double)this.rootElement.height.floatValue(this.renderDPI)), renderOptionsBase);
        }
        if (this.rootElement.width != null && o != null) {
            final float floatValue = this.rootElement.width.floatValue(this.renderDPI);
            return this.renderToPicture((int)Math.ceil((double)floatValue), (int)Math.ceil((double)(((Box)o).height * floatValue / ((Box)o).width)), renderOptionsBase);
        }
        if (this.rootElement.height != null && o != null) {
            final float floatValue2 = this.rootElement.height.floatValue(this.renderDPI);
            return this.renderToPicture((int)Math.ceil((double)(((Box)o).width * floatValue2 / ((Box)o).height)), (int)Math.ceil((double)floatValue2), renderOptionsBase);
        }
        return this.renderToPicture(512, 512, renderOptionsBase);
    }
    
    public void renderViewToCanvas(final String s, final Canvas canvas) {
        this.renderToCanvas(canvas, RenderOptions.create().view(s));
    }
    
    public void renderViewToCanvas(final String s, final Canvas canvas, final RectF rectF) {
        final RenderOptions view = RenderOptions.create().view(s);
        if (rectF != null) {
            view.viewPort(rectF.left, rectF.top, rectF.width(), rectF.height());
        }
        this.renderToCanvas(canvas, view);
    }
    
    public Picture renderViewToPicture(final String s, final int n, final int n2) {
        final RenderOptions renderOptions = new RenderOptions();
        renderOptions.view(s).viewPort(0.0f, 0.0f, (float)n, (float)n2);
        final Picture picture = new Picture();
        new SVGAndroidRenderer(picture.beginRecording(n, n2), this.renderDPI, this.externalFileResolver).renderDocument(this, (RenderOptionsBase)renderOptions);
        picture.endRecording();
        return picture;
    }
    
    SvgObject resolveIRI(String cssQuotedString) {
        if (cssQuotedString == null) {
            return null;
        }
        cssQuotedString = this.cssQuotedString(cssQuotedString);
        if (cssQuotedString.length() > 1 && cssQuotedString.startsWith("#")) {
            return (SvgObject)this.getElementById(cssQuotedString.substring(1));
        }
        return null;
    }
    
    void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public void setDocumentHeight(final float n) {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.height = new Length(n);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setDocumentHeight(final String s) throws SVGParseException {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.height = SVGParserImpl.parseLength(s);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setDocumentPreserveAspectRatio(final PreserveAspectRatio preserveAspectRatio) {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.preserveAspectRatio = preserveAspectRatio;
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setDocumentViewBox(final float n, final float n2, final float n3, final float n4) {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.viewBox = new Box(n, n2, n3, n4);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setDocumentWidth(final float n) {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.width = new Length(n);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setDocumentWidth(final String s) throws SVGParseException {
        final SVGBase.SVGBase$Svg rootElement = this.rootElement;
        if (rootElement != null) {
            rootElement.width = SVGParserImpl.parseLength(s);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }
    
    public void setRenderDPI(final float renderDPI) {
        this.renderDPI = renderDPI;
    }
    
    void setRootElement(final SVGBase.SVGBase$Svg rootElement) {
        this.rootElement = rootElement;
    }
    
    void setTitle(final String title) {
        this.title = title;
    }
    
    static class Box
    {
        float height;
        float minX;
        float minY;
        float width;
        
        Box(final float minX, final float minY, final float width, final float height) {
            this.minX = minX;
            this.minY = minY;
            this.width = width;
            this.height = height;
        }
        
        Box(final Box box) {
            this.minX = box.minX;
            this.minY = box.minY;
            this.width = box.width;
            this.height = box.height;
        }
        
        static Box fromLimits(final float n, final float n2, final float n3, final float n4) {
            return new Box(n, n2, n3 - n, n4 - n2);
        }
        
        float maxX() {
            return this.minX + this.width;
        }
        
        float maxY() {
            return this.minY + this.height;
        }
        
        RectF toRectF() {
            return new RectF(this.minX, this.minY, this.maxX(), this.maxY());
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(this.minX);
            sb.append(" ");
            sb.append(this.minY);
            sb.append(" ");
            sb.append(this.width);
            sb.append(" ");
            sb.append(this.height);
            sb.append("]");
            return sb.toString();
        }
        
        void union(final Box box) {
            final float minX = box.minX;
            if (minX < this.minX) {
                this.minX = minX;
            }
            final float minY = box.minY;
            if (minY < this.minY) {
                this.minY = minY;
            }
            if (box.maxX() > this.maxX()) {
                this.width = box.maxX() - this.minX;
            }
            if (box.maxY() > this.maxY()) {
                this.height = box.maxY() - this.minY;
            }
        }
    }
    
    public static class CSSClipRect
    {
        final Length bottom;
        final Length left;
        final Length right;
        final Length top;
        
        CSSClipRect(final Length top, final Length right, final Length bottom, final Length left) {
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }
    }
    
    enum GradientSpread
    {
        private static final GradientSpread[] $VALUES;
        
        pad, 
        reflect, 
        repeat;
    }
    
    interface HasTransform
    {
        void setTransform(final Matrix p0);
    }
    
    public static class Length implements Cloneable
    {
        static final Length ZERO;
        final Unit unit;
        final float value;
        
        static {
            ZERO = new Length(0.0f);
        }
        
        public Length(final float value) {
            this.value = value;
            this.unit = Unit.px;
        }
        
        public Length(final float value, final Unit unit) {
            this.value = value;
            this.unit = unit;
        }
        
        float floatValue() {
            return this.value;
        }
        
        float floatValue(final float n) {
            final int n2 = SVGBase$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGBase$Unit[this.unit.ordinal()];
            if (n2 == 3) {
                return this.value * n;
            }
            if (n2 == 4) {
                return this.value * n / 2.54f;
            }
            if (n2 == 5) {
                return this.value * n / 25.4f;
            }
            if (n2 == 6) {
                return this.value * n / 72.0f;
            }
            if (n2 != 7) {
                return this.value;
            }
            return this.value * n / 6.0f;
        }
        
        float floatValue(final SVGAndroidRenderer svgAndroidRenderer) {
            if (this.unit != Unit.percent) {
                return this.floatValueX(svgAndroidRenderer);
            }
            final Box currentViewPortInUserUnits = svgAndroidRenderer.getCurrentViewPortInUserUnits();
            if (currentViewPortInUserUnits == null) {
                return this.value;
            }
            final float width = currentViewPortInUserUnits.width;
            final float height = currentViewPortInUserUnits.height;
            if (width == height) {
                return this.value * width / 100.0f;
            }
            return this.value * (float)(Math.sqrt((double)(width * width + height * height)) / 1.414213562373095) / 100.0f;
        }
        
        float floatValue(final SVGAndroidRenderer svgAndroidRenderer, final float n) {
            if (this.unit == Unit.percent) {
                return this.value * n / 100.0f;
            }
            return this.floatValueX(svgAndroidRenderer);
        }
        
        float floatValueX(final SVGAndroidRenderer svgAndroidRenderer) {
            switch (SVGBase$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGBase$Unit[this.unit.ordinal()]) {
                default: {
                    return this.value;
                }
                case 8: {
                    final Box currentViewPortInUserUnits = svgAndroidRenderer.getCurrentViewPortInUserUnits();
                    if (currentViewPortInUserUnits == null) {
                        return this.value;
                    }
                    return this.value * currentViewPortInUserUnits.width / 100.0f;
                }
                case 7: {
                    return this.value * svgAndroidRenderer.getDPI() / 6.0f;
                }
                case 6: {
                    return this.value * svgAndroidRenderer.getDPI() / 72.0f;
                }
                case 5: {
                    return this.value * svgAndroidRenderer.getDPI() / 25.4f;
                }
                case 4: {
                    return this.value * svgAndroidRenderer.getDPI() / 2.54f;
                }
                case 3: {
                    return this.value * svgAndroidRenderer.getDPI();
                }
                case 2: {
                    return this.value * svgAndroidRenderer.getCurrentFontXHeight();
                }
                case 1: {
                    return this.value * svgAndroidRenderer.getCurrentFontSize();
                }
            }
        }
        
        float floatValueY(final SVGAndroidRenderer svgAndroidRenderer) {
            if (this.unit != Unit.percent) {
                return this.floatValueX(svgAndroidRenderer);
            }
            final Box currentViewPortInUserUnits = svgAndroidRenderer.getCurrentViewPortInUserUnits();
            if (currentViewPortInUserUnits == null) {
                return this.value;
            }
            return this.value * currentViewPortInUserUnits.height / 100.0f;
        }
        
        boolean isNegative() {
            return this.value < 0.0f;
        }
        
        boolean isZero() {
            return this.value == 0.0f;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(this.value));
            sb.append((Object)this.unit);
            return sb.toString();
        }
    }
    
    interface NotDirectlyRendered
    {
    }
    
    interface PathInterface
    {
        void arcTo(final float p0, final float p1, final float p2, final boolean p3, final boolean p4, final float p5, final float p6);
        
        void close();
        
        void cubicTo(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
        
        void lineTo(final float p0, final float p1);
        
        void moveTo(final float p0, final float p1);
        
        void quadTo(final float p0, final float p1, final float p2, final float p3);
    }
    
    interface SvgConditional
    {
        String getRequiredExtensions();
        
        Set<String> getRequiredFeatures();
        
        Set<String> getRequiredFonts();
        
        Set<String> getRequiredFormats();
        
        Set<String> getSystemLanguage();
        
        void setRequiredExtensions(final String p0);
        
        void setRequiredFeatures(final Set<String> p0);
        
        void setRequiredFonts(final Set<String> p0);
        
        void setRequiredFormats(final Set<String> p0);
        
        void setSystemLanguage(final Set<String> p0);
    }
    
    public interface SvgContainer
    {
        void addChild(final SvgObject p0) throws SVGParseException;
        
        List<SvgObject> getChildren();
    }
    
    public static class SvgObject
    {
        SVGBase document;
        SvgContainer parent;
        
        String getNodeName() {
            return "";
        }
    }
    
    public abstract static class SvgPaint implements Cloneable
    {
    }
    
    interface TextChild
    {
        TextRoot getTextRoot();
        
        void setTextRoot(final TextRoot p0);
    }
    
    interface TextRoot
    {
    }
    
    enum Unit
    {
        private static final Unit[] $VALUES;
        
        cm, 
        em, 
        ex, 
        in, 
        mm, 
        pc, 
        percent, 
        pt, 
        px;
    }
}
