package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.HashMap;
import java.util.Map;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import java.util.List;

public class Style implements Cloneable
{
    static final float FONT_STRETCH_MIN = 0.0f;
    static final float FONT_STRETCH_NORMAL = 100.0f;
    static final float FONT_WEIGHT_BOLD = 700.0f;
    static final float FONT_WEIGHT_BOLDER = Float.MAX_VALUE;
    static final float FONT_WEIGHT_LIGHTER = Float.MIN_VALUE;
    static final float FONT_WEIGHT_MAX = 1000.0f;
    static final float FONT_WEIGHT_MIN = 1.0f;
    static final float FONT_WEIGHT_NORMAL = 400.0f;
    static final long SPECIFIED_CLIP = 1048576L;
    static final long SPECIFIED_CLIP_PATH = 268435456L;
    static final long SPECIFIED_CLIP_RULE = 536870912L;
    static final long SPECIFIED_COLOR = 4096L;
    static final long SPECIFIED_DIRECTION = 68719476736L;
    static final long SPECIFIED_DISPLAY = 16777216L;
    static final long SPECIFIED_FILL = 1L;
    static final long SPECIFIED_FILL_OPACITY = 4L;
    static final long SPECIFIED_FILL_RULE = 2L;
    static final long SPECIFIED_FONT_FAMILY = 8192L;
    static final long SPECIFIED_FONT_FEATURE_SETTINGS = 35184372088832L;
    static final long SPECIFIED_FONT_KERNING = 562949953421312L;
    static final long SPECIFIED_FONT_SIZE = 16384L;
    static final long SPECIFIED_FONT_STRETCH = 2251799813685248L;
    static final long SPECIFIED_FONT_STYLE = 65536L;
    static final long SPECIFIED_FONT_VARIANT_CAPS = 4398046511104L;
    static final long SPECIFIED_FONT_VARIANT_EAST_ASIAN = 17592186044416L;
    static final long SPECIFIED_FONT_VARIANT_LIGATURES = 1099511627776L;
    static final long SPECIFIED_FONT_VARIANT_NUMERIC = 8796093022208L;
    static final long SPECIFIED_FONT_VARIANT_POSITION = 2199023255552L;
    static final long SPECIFIED_FONT_VARIATION_SETTINGS = 1125899906842624L;
    static final long SPECIFIED_FONT_WEIGHT = 32768L;
    static final long SPECIFIED_GLYPH_ORIENTATION_VERTICAL = 140737488355328L;
    static final long SPECIFIED_IMAGE_RENDERING = 137438953472L;
    static final long SPECIFIED_ISOLATION = 274877906944L;
    static final long SPECIFIED_LETTER_SPACING = 4503599627370496L;
    static final long SPECIFIED_MARKER_END = 8388608L;
    static final long SPECIFIED_MARKER_MID = 4194304L;
    static final long SPECIFIED_MARKER_START = 2097152L;
    static final long SPECIFIED_MASK = 1073741824L;
    static final long SPECIFIED_MIX_BLEND_MODE = 549755813888L;
    static final long SPECIFIED_OPACITY = 2048L;
    static final long SPECIFIED_OVERFLOW = 524288L;
    private static final long SPECIFIED_RESET = -1159984767303681L;
    static final long SPECIFIED_SOLID_COLOR = 2147483648L;
    static final long SPECIFIED_SOLID_OPACITY = 4294967296L;
    static final long SPECIFIED_STOP_COLOR = 67108864L;
    static final long SPECIFIED_STOP_OPACITY = 134217728L;
    static final long SPECIFIED_STROKE = 8L;
    static final long SPECIFIED_STROKE_DASHARRAY = 512L;
    static final long SPECIFIED_STROKE_DASHOFFSET = 1024L;
    static final long SPECIFIED_STROKE_LINECAP = 64L;
    static final long SPECIFIED_STROKE_LINEJOIN = 128L;
    static final long SPECIFIED_STROKE_MITERLIMIT = 256L;
    static final long SPECIFIED_STROKE_OPACITY = 16L;
    static final long SPECIFIED_STROKE_WIDTH = 32L;
    static final long SPECIFIED_TEXT_ANCHOR = 262144L;
    static final long SPECIFIED_TEXT_DECORATION = 131072L;
    static final long SPECIFIED_TEXT_ORIENTATION = 281474976710656L;
    static final long SPECIFIED_VECTOR_EFFECT = 34359738368L;
    static final long SPECIFIED_VIEWPORT_FILL = 8589934592L;
    static final long SPECIFIED_VIEWPORT_FILL_OPACITY = 17179869184L;
    static final long SPECIFIED_VISIBILITY = 33554432L;
    static final long SPECIFIED_WORD_SPACING = 9007199254740992L;
    static final long SPECIFIED_WRITING_MODE = 70368744177664L;
    SVGBase.CSSClipRect clip;
    String clipPath;
    FillRule clipRule;
    SVGBase$Colour color;
    TextDirection direction;
    Boolean display;
    SVGBase.SvgPaint fill;
    Float fillOpacity;
    FillRule fillRule;
    List<String> fontFamily;
    CSSFontFeatureSettings fontFeatureSettings;
    FontKerning fontKerning;
    SVGBase.Length fontSize;
    Float fontStretch;
    FontStyle fontStyle;
    CSSFontFeatureSettings fontVariantCaps;
    CSSFontFeatureSettings fontVariantEastAsian;
    CSSFontFeatureSettings fontVariantLigatures;
    CSSFontFeatureSettings fontVariantNumeric;
    CSSFontFeatureSettings fontVariantPosition;
    CSSFontVariationSettings fontVariationSettings;
    Float fontWeight;
    GlypOrientationVertical glyphOrientationVertical;
    RenderQuality imageRendering;
    Isolation isolation;
    SVGBase.Length letterSpacing;
    String markerEnd;
    String markerMid;
    String markerStart;
    String mask;
    CSSBlendMode mixBlendMode;
    Float opacity;
    Boolean overflow;
    SVGBase.SvgPaint solidColor;
    Float solidOpacity;
    long specifiedFlags;
    SVGBase.SvgPaint stopColor;
    Float stopOpacity;
    SVGBase.SvgPaint stroke;
    SVGBase.Length[] strokeDashArray;
    SVGBase.Length strokeDashOffset;
    LineCap strokeLineCap;
    LineJoin strokeLineJoin;
    Float strokeMiterLimit;
    Float strokeOpacity;
    SVGBase.Length strokeWidth;
    TextAnchor textAnchor;
    TextDecoration textDecoration;
    TextOrientation textOrientation;
    VectorEffect vectorEffect;
    SVGBase.SvgPaint viewportFill;
    Float viewportFillOpacity;
    Boolean visibility;
    SVGBase.Length wordSpacing;
    WritingMode writingMode;
    
    public Style() {
        this.specifiedFlags = 0L;
    }
    
    static Style getDefaultStyle() {
        final Style style = new Style();
        style.fill = (SVGBase.SvgPaint)SVGBase$Colour.BLACK;
        style.fillRule = FillRule.NonZero;
        final Float value = 1.0f;
        style.fillOpacity = value;
        style.stroke = null;
        style.strokeOpacity = value;
        style.strokeWidth = new SVGBase.Length(1.0f);
        style.strokeLineCap = LineCap.Butt;
        style.strokeLineJoin = LineJoin.Miter;
        style.strokeMiterLimit = 4.0f;
        style.strokeDashArray = null;
        style.strokeDashOffset = SVGBase.Length.ZERO;
        style.opacity = value;
        style.color = SVGBase$Colour.BLACK;
        style.fontFamily = null;
        style.fontSize = new SVGBase.Length(12.0f, SVGBase.Unit.pt);
        style.fontWeight = 400.0f;
        style.fontStyle = FontStyle.normal;
        style.fontStretch = 100.0f;
        style.textDecoration = TextDecoration.None;
        style.direction = TextDirection.LTR;
        style.textAnchor = TextAnchor.Start;
        style.overflow = true;
        style.clip = null;
        style.markerStart = null;
        style.markerMid = null;
        style.markerEnd = null;
        style.display = Boolean.TRUE;
        style.visibility = Boolean.TRUE;
        style.stopColor = (SVGBase.SvgPaint)SVGBase$Colour.BLACK;
        style.stopOpacity = value;
        style.clipPath = null;
        style.clipRule = FillRule.NonZero;
        style.mask = null;
        style.solidColor = null;
        style.solidOpacity = value;
        style.viewportFill = null;
        style.viewportFillOpacity = value;
        style.vectorEffect = VectorEffect.None;
        style.imageRendering = RenderQuality.auto;
        style.isolation = Isolation.auto;
        style.mixBlendMode = CSSBlendMode.normal;
        style.fontKerning = FontKerning.auto;
        style.fontVariantLigatures = CSSFontFeatureSettings.LIGATURES_NORMAL;
        style.fontVariantPosition = CSSFontFeatureSettings.POSITION_ALL_OFF;
        style.fontVariantCaps = CSSFontFeatureSettings.CAPS_ALL_OFF;
        style.fontVariantNumeric = CSSFontFeatureSettings.NUMERIC_ALL_OFF;
        style.fontVariantEastAsian = CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF;
        style.fontFeatureSettings = CSSFontFeatureSettings.FONT_FEATURE_SETTINGS_NORMAL;
        style.fontVariationSettings = null;
        style.letterSpacing = SVGBase.Length.ZERO;
        style.wordSpacing = SVGBase.Length.ZERO;
        style.writingMode = WritingMode.horizontal_tb;
        style.glyphOrientationVertical = GlypOrientationVertical.auto;
        style.textOrientation = TextOrientation.mixed;
        style.specifiedFlags = -1159984767303681L;
        return style;
    }
    
    static void processStyleProperty(final Style style, String functionalIRI, final String s, final boolean b) {
        if (s.length() == 0) {
            return;
        }
        if (s.equals((Object)"inherit")) {
            return;
        }
        Label_1938: {
            switch (Style$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGAttr.fromString(functionalIRI).ordinal()]) {
                default: {
                    return;
                }
                case 54: {
                    final SVGBase.Length letterOrWordSpacing = SVGParserImpl.parseLetterOrWordSpacing(s);
                    style.wordSpacing = letterOrWordSpacing;
                    if (letterOrWordSpacing != null) {
                        style.specifiedFlags |= 0x20000000000000L;
                    }
                    return;
                }
                case 53: {
                    final SVGBase.Length letterOrWordSpacing2 = SVGParserImpl.parseLetterOrWordSpacing(s);
                    style.letterSpacing = letterOrWordSpacing2;
                    if (letterOrWordSpacing2 != null) {
                        style.specifiedFlags |= 0x10000000000000L;
                    }
                    return;
                }
                case 52: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariationSettings = CSSFontVariationSettings.parseFontVariationSettings(s)) != null) {
                        style.specifiedFlags |= 0x4000000000000L;
                    }
                    return;
                }
                case 51: {
                    if (b) {
                        return;
                    }
                    if ((style.fontFeatureSettings = CSSFontFeatureSettings.parseFontFeatureSettings(s)) != null) {
                        style.specifiedFlags |= 0x200000000000L;
                    }
                    return;
                }
                case 50: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariantEastAsian = CSSFontFeatureSettings.parseEastAsian(s)) != null) {
                        style.specifiedFlags |= 0x100000000000L;
                    }
                    return;
                }
                case 49: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariantNumeric = CSSFontFeatureSettings.parseVariantNumeric(s)) != null) {
                        style.specifiedFlags |= 0x80000000000L;
                    }
                    return;
                }
                case 48: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariantCaps = CSSFontFeatureSettings.parseVariantCaps(s)) != null) {
                        style.specifiedFlags |= 0x40000000000L;
                    }
                    return;
                }
                case 47: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariantPosition = CSSFontFeatureSettings.parseVariantPosition(s)) != null) {
                        style.specifiedFlags |= 0x20000000000L;
                    }
                    return;
                }
                case 46: {
                    if (b) {
                        return;
                    }
                    if ((style.fontVariantLigatures = CSSFontFeatureSettings.parseVariantLigatures(s)) != null) {
                        style.specifiedFlags |= 0x10000000000L;
                    }
                    return;
                }
                case 45: {
                    if (b) {
                        return;
                    }
                    CSSFontFeatureSettings.parseFontVariant(style, s);
                    return;
                }
                case 44: {
                    if (b) {
                        return;
                    }
                    if ((style.fontKerning = CSSFontFeatureSettings.parseFontKerning(s)) != null) {
                        style.specifiedFlags |= 0x2000000000000L;
                    }
                    return;
                }
                case 43: {
                    if (b) {
                        return;
                    }
                    if ((style.mixBlendMode = CSSBlendMode.fromString(s)) != null) {
                        style.specifiedFlags |= 0x8000000000L;
                    }
                    return;
                }
                case 42: {
                    if (b) {
                        return;
                    }
                    if ((style.isolation = SVGParserImpl.parseIsolation(s)) != null) {
                        style.specifiedFlags |= 0x4000000000L;
                    }
                    return;
                }
                case 41: {
                    final RenderQuality renderQuality = SVGParserImpl.parseRenderQuality(s);
                    style.imageRendering = renderQuality;
                    if (renderQuality != null) {
                        style.specifiedFlags |= 0x2000000000L;
                    }
                    return;
                }
                case 40: {
                    final VectorEffect vectorEffect = SVGParserImpl.parseVectorEffect(s);
                    style.vectorEffect = vectorEffect;
                    if (vectorEffect != null) {
                        style.specifiedFlags |= 0x800000000L;
                    }
                    return;
                }
                case 39: {
                    style.viewportFillOpacity = SVGParserImpl.parseOpacity(s);
                    style.specifiedFlags |= 0x400000000L;
                    return;
                }
                case 38: {
                    if (s.equals((Object)"currentColor")) {
                        style.viewportFill = (SVGBase.SvgPaint)SVGBase$CurrentColor.getInstance();
                    }
                    else {
                        style.viewportFill = (SVGBase.SvgPaint)SVGParserImpl.parseColour(s);
                    }
                    style.specifiedFlags |= 0x200000000L;
                    return;
                }
                case 37: {
                    if (!b) {
                        return;
                    }
                    style.solidOpacity = SVGParserImpl.parseOpacity(s);
                    style.specifiedFlags |= 0x100000000L;
                    return;
                }
                case 36: {
                    if (!b) {
                        return;
                    }
                    if (s.equals((Object)"currentColor")) {
                        style.solidColor = (SVGBase.SvgPaint)SVGBase$CurrentColor.getInstance();
                    }
                    else {
                        style.solidColor = (SVGBase.SvgPaint)SVGParserImpl.parseColour(s);
                    }
                    style.specifiedFlags |= 0x80000000L;
                    return;
                }
                case 35: {
                    style.mask = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.specifiedFlags |= 0x40000000L;
                    return;
                }
                case 34: {
                    style.clipRule = SVGParserImpl.parseFillRule(s);
                    style.specifiedFlags |= 0x20000000L;
                    return;
                }
                case 33: {
                    style.clipPath = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.specifiedFlags |= 0x10000000L;
                    return;
                }
                case 32: {
                    final SVGBase.CSSClipRect clip = SVGParserImpl.parseClip(s);
                    style.clip = clip;
                    if (clip != null) {
                        style.specifiedFlags |= 0x100000L;
                    }
                    return;
                }
                case 31: {
                    style.stopOpacity = SVGParserImpl.parseOpacity(s);
                    style.specifiedFlags |= 0x8000000L;
                    return;
                }
                case 30: {
                    if (s.equals((Object)"currentColor")) {
                        style.stopColor = (SVGBase.SvgPaint)SVGBase$CurrentColor.getInstance();
                    }
                    else {
                        style.stopColor = (SVGBase.SvgPaint)SVGParserImpl.parseColour(s);
                    }
                    style.specifiedFlags |= 0x4000000L;
                    return;
                }
                case 29: {
                    if (s.indexOf(124) >= 0) {
                        return;
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append('|');
                    sb.append(s);
                    sb.append('|');
                    if (!"|visible|hidden|collapse|".contains((CharSequence)sb.toString())) {
                        return;
                    }
                    style.visibility = s.equals((Object)"visible");
                    style.specifiedFlags |= 0x2000000L;
                    return;
                }
                case 28: {
                    if (s.indexOf(124) >= 0) {
                        return;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append('|');
                    sb2.append(s);
                    sb2.append('|');
                    if (!"|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|".contains((CharSequence)sb2.toString())) {
                        return;
                    }
                    style.display = (s.equals((Object)"none") ^ true);
                    style.specifiedFlags |= 0x1000000L;
                    return;
                }
                case 27: {
                    style.markerEnd = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.specifiedFlags |= 0x800000L;
                    return;
                }
                case 26: {
                    style.markerMid = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.specifiedFlags |= 0x400000L;
                    return;
                }
                case 25: {
                    style.markerStart = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.specifiedFlags |= 0x200000L;
                    return;
                }
                case 24: {
                    functionalIRI = SVGParserImpl.parseFunctionalIRI(s, functionalIRI);
                    style.markerStart = functionalIRI;
                    style.markerMid = functionalIRI;
                    style.markerEnd = functionalIRI;
                    style.specifiedFlags |= 0xE00000L;
                    return;
                }
                case 23: {
                    final Boolean overflow = SVGParserImpl.parseOverflow(s);
                    style.overflow = overflow;
                    if (overflow != null) {
                        style.specifiedFlags |= 0x80000L;
                    }
                    return;
                }
                case 22: {
                    final TextAnchor textAnchor = SVGParserImpl.parseTextAnchor(s);
                    style.textAnchor = textAnchor;
                    if (textAnchor != null) {
                        style.specifiedFlags |= 0x40000L;
                    }
                    return;
                }
                case 21: {
                    final TextDirection textDirection = SVGParserImpl.parseTextDirection(s);
                    style.direction = textDirection;
                    if (textDirection != null) {
                        style.specifiedFlags |= 0x1000000000L;
                    }
                    return;
                }
                case 20: {
                    final TextDecoration textDecoration = SVGParserImpl.parseTextDecoration(s);
                    style.textDecoration = textDecoration;
                    if (textDecoration != null) {
                        style.specifiedFlags |= 0x20000L;
                    }
                    return;
                }
                case 19: {
                    final Float fontStretch = SVGParserImpl.parseFontStretch(s);
                    style.fontStretch = fontStretch;
                    if (fontStretch != null) {
                        style.specifiedFlags |= 0x8000000000000L;
                    }
                    return;
                }
                case 18: {
                    final FontStyle fontStyle = SVGParserImpl.parseFontStyle(s);
                    style.fontStyle = fontStyle;
                    if (fontStyle != null) {
                        style.specifiedFlags |= 0x10000L;
                    }
                    return;
                }
                case 17: {
                    final Float fontWeight = SVGParserImpl.parseFontWeight(s);
                    style.fontWeight = fontWeight;
                    if (fontWeight != null) {
                        style.specifiedFlags |= 0x8000L;
                    }
                    return;
                }
                case 16: {
                    final SVGBase.Length fontSize = SVGParserImpl.parseFontSize(s);
                    style.fontSize = fontSize;
                    if (fontSize != null) {
                        style.specifiedFlags |= 0x4000L;
                    }
                    return;
                }
                case 15: {
                    final List fontFamily = SVGParserImpl.parseFontFamily(s);
                    style.fontFamily = (List<String>)fontFamily;
                    if (fontFamily != null) {
                        style.specifiedFlags |= 0x2000L;
                    }
                    return;
                }
                case 14: {
                    if (b) {
                        return;
                    }
                    SVGParserImpl.parseFont(style, s);
                    return;
                }
                case 13: {
                    style.color = SVGParserImpl.parseColour(s);
                    style.specifiedFlags |= 0x1000L;
                    return;
                }
                case 12: {
                    style.opacity = SVGParserImpl.parseOpacity(s);
                    style.specifiedFlags |= 0x800L;
                    return;
                }
                case 11: {
                    break Label_1938;
                }
                case 10: {
                    break Label_1938;
                }
                case 9: {
                    break Label_1938;
                }
                case 8: {
                    break Label_1938;
                }
                case 7: {
                    break Label_1938;
                }
                case 6: {
                    break Label_1938;
                }
                case 5: {
                    break Label_1938;
                }
                case 4: {
                    break Label_1938;
                }
                case 3: {
                    break Label_1938;
                }
                case 2: {
                    break Label_1938;
                }
                case 1: {
                    Label_1967: {
                        break Label_1967;
                        try {
                            style.strokeDashOffset = SVGParserImpl.parseLength(s);
                            style.specifiedFlags |= 0x400L;
                            Label_1991: {
                                return;
                            }
                            Block_51: {
                                while (true) {
                                    Block_52: {
                                        while (true) {
                                            while (true) {
                                                style.specifiedFlags |= 0x2L;
                                                return;
                                                final SVGBase.SvgPaint paintSpecifier = SVGParserImpl.parsePaintSpecifier(s);
                                                style.stroke = paintSpecifier;
                                                iftrue(Label_1991:)(paintSpecifier == null);
                                                Block_54: {
                                                    break Block_54;
                                                    style.strokeDashArray = null;
                                                    style.specifiedFlags |= 0x200L;
                                                    return;
                                                    final LineCap strokeLineCap = SVGParserImpl.parseStrokeLineCap(s);
                                                    style.strokeLineCap = strokeLineCap;
                                                    iftrue(Label_1991:)(strokeLineCap == null);
                                                    break Block_52;
                                                }
                                                style.specifiedFlags |= 0x8L;
                                                return;
                                                style.specifiedFlags |= 0x10L;
                                                return;
                                                final Float opacity = SVGParserImpl.parseOpacity(s);
                                                style.fillOpacity = opacity;
                                                iftrue(Label_1991:)(opacity == null);
                                                style.specifiedFlags |= 0x4L;
                                                return;
                                                final FillRule fillRule = SVGParserImpl.parseFillRule(s);
                                                style.fillRule = fillRule;
                                                iftrue(Label_1991:)(fillRule == null);
                                                continue;
                                            }
                                            iftrue(Label_1715:)(!"none".equals((Object)s));
                                            continue;
                                        }
                                        final SVGBase.SvgPaint paintSpecifier2 = SVGParserImpl.parsePaintSpecifier(s);
                                        style.fill = paintSpecifier2;
                                        iftrue(Label_1991:)(paintSpecifier2 == null);
                                        style.specifiedFlags |= 0x1L;
                                        return;
                                    }
                                    style.specifiedFlags |= 0x40L;
                                    return;
                                    final LineJoin strokeLineJoin = SVGParserImpl.parseStrokeLineJoin(s);
                                    style.strokeLineJoin = strokeLineJoin;
                                    iftrue(Label_1991:)(strokeLineJoin == null);
                                    break Block_51;
                                    final Float opacity2 = SVGParserImpl.parseOpacity(s);
                                    style.strokeOpacity = opacity2;
                                    iftrue(Label_1991:)(opacity2 == null);
                                    continue;
                                }
                                style.strokeMiterLimit = SVGParserImpl.parseFloat(s);
                                style.specifiedFlags |= 0x100L;
                                return;
                            }
                            style.specifiedFlags |= 0x80L;
                            return;
                            while (true) {
                                style.specifiedFlags |= 0x200L;
                                return;
                                Label_1715:
                                iftrue(Label_1991:)((style.strokeDashArray = SVGParserImpl.parseStrokeDashArray(s)) == null);
                                continue;
                            }
                            style.strokeWidth = SVGParserImpl.parseLength(s);
                            style.specifiedFlags |= 0x20L;
                            return;
                        }
                        catch (final SVGParseException ex) {
                            return;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        final Style style = (Style)super.clone();
        final SVGBase.Length[] strokeDashArray = this.strokeDashArray;
        if (strokeDashArray != null) {
            style.strokeDashArray = strokeDashArray.clone();
        }
        return style;
    }
    
    void resetNonInheritingProperties(final boolean b) {
        this.display = Boolean.TRUE;
        Boolean overflow;
        if (b) {
            overflow = Boolean.TRUE;
        }
        else {
            overflow = Boolean.FALSE;
        }
        this.overflow = overflow;
        this.clip = null;
        this.clipPath = null;
        this.opacity = 1.0f;
        this.stopColor = (SVGBase.SvgPaint)SVGBase$Colour.BLACK;
        this.stopOpacity = 1.0f;
        this.mask = null;
        this.solidColor = null;
        this.solidOpacity = 1.0f;
        this.viewportFill = null;
        this.viewportFillOpacity = 1.0f;
        this.vectorEffect = VectorEffect.None;
        this.isolation = Isolation.auto;
        this.mixBlendMode = CSSBlendMode.normal;
    }
    
    public enum CSSBlendMode
    {
        private static final CSSBlendMode[] $VALUES;
        
        UNSUPPORTED;
        
        private static final Map<String, CSSBlendMode> cache;
        
        color, 
        color_burn, 
        color_dodge, 
        darken, 
        difference, 
        exclusion, 
        hard_light, 
        hue, 
        lighten, 
        luminosity, 
        multiply, 
        normal, 
        overlay, 
        saturation, 
        screen, 
        soft_light;
        
        static {
            int i = 0;
            cache = (Map)new HashMap();
            for (CSSBlendMode[] values = values(); i < values.length; ++i) {
                final CSSBlendMode cssBlendMode = values[i];
                if (cssBlendMode != CSSBlendMode.UNSUPPORTED) {
                    CSSBlendMode.cache.put((Object)cssBlendMode.name().replace('_', '-'), (Object)cssBlendMode);
                }
            }
        }
        
        public static CSSBlendMode fromString(final String s) {
            final CSSBlendMode cssBlendMode = (CSSBlendMode)CSSBlendMode.cache.get((Object)s);
            if (cssBlendMode != null) {
                return cssBlendMode;
            }
            return CSSBlendMode.UNSUPPORTED;
        }
    }
    
    public enum FillRule
    {
        private static final FillRule[] $VALUES;
        
        EvenOdd, 
        NonZero;
    }
    
    public enum FontKerning
    {
        private static final FontKerning[] $VALUES;
        
        auto, 
        none, 
        normal;
    }
    
    public enum FontStyle
    {
        private static final FontStyle[] $VALUES;
        
        italic, 
        normal, 
        oblique;
    }
    
    public enum GlypOrientationVertical
    {
        private static final GlypOrientationVertical[] $VALUES;
        
        angle0, 
        angle180, 
        angle270, 
        angle90, 
        auto;
    }
    
    public enum Isolation
    {
        private static final Isolation[] $VALUES;
        
        auto, 
        isolate;
    }
    
    public enum LineCap
    {
        private static final LineCap[] $VALUES;
        
        Butt, 
        Round, 
        Square;
    }
    
    public enum LineJoin
    {
        private static final LineJoin[] $VALUES;
        
        Bevel, 
        Miter, 
        Round;
    }
    
    public enum RenderQuality
    {
        private static final RenderQuality[] $VALUES;
        
        auto, 
        optimizeQuality, 
        optimizeSpeed;
    }
    
    public enum TextAnchor
    {
        private static final TextAnchor[] $VALUES;
        
        End, 
        Middle, 
        Start;
    }
    
    public enum TextDecoration
    {
        private static final TextDecoration[] $VALUES;
        
        Blink, 
        LineThrough, 
        None, 
        Overline, 
        Underline;
    }
    
    public enum TextDirection
    {
        private static final TextDirection[] $VALUES;
        
        LTR, 
        RTL;
    }
    
    public enum TextOrientation
    {
        private static final TextOrientation[] $VALUES;
        
        mixed, 
        sideways, 
        upright;
    }
    
    public enum VectorEffect
    {
        private static final VectorEffect[] $VALUES;
        
        NonScalingStroke, 
        None;
    }
    
    public enum WritingMode
    {
        private static final WritingMode[] $VALUES;
        
        horizontal_tb, 
        lr, 
        lr_tb, 
        rl, 
        rl_tb, 
        tb, 
        tb_rl, 
        vertical_lr, 
        vertical_rl;
    }
}
