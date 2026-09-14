package com.kingagroot.kingdraw.core.graphics.svg.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.ContentHandler;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import android.graphics.Matrix;
import java.util.HashMap;
import com.kingagroot.kingdraw.core.graphics.svg.PreserveAspectRatio;
import android.util.Log;
import java.util.Locale;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import org.xml.sax.Attributes;
import android.os.Build$VERSION;
import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;
import java.util.regex.Pattern;

class SVGParserImpl implements SVGParser
{
    static final String CURRENTCOLOR = "currentColor";
    public static final int ENTITY_WATCH_BUFFER_SIZE = 4096;
    private static final String FEATURE_STRING_PREFIX = "http://www.w3.org/TR/SVG11/feature#";
    private static final boolean FORCE_SAX_ON_EARLY_ANDROIDS;
    static final String NONE = "none";
    private static final Pattern PATTERN_BLOCK_COMMENTS;
    private static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    private static final String TAG = "SVGParser";
    static final String VALID_DISPLAY_VALUES = "|inline|block|list-item|run-in|compact|marker|table|inline-table|table-row-group|table-header-group|table-footer-group|table-row|table-column-group|table-column|table-cell|table-caption|none|";
    static final String VALID_VISIBILITY_VALUES = "|visible|hidden|collapse|";
    private static final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";
    public static final String XML_STYLESHEET_ATTR_ALTERNATE = "alternate";
    public static final String XML_STYLESHEET_ATTR_ALTERNATE_NO = "no";
    public static final String XML_STYLESHEET_ATTR_HREF = "href";
    public static final String XML_STYLESHEET_ATTR_MEDIA = "media";
    public static final String XML_STYLESHEET_ATTR_MEDIA_ALL = "all";
    public static final String XML_STYLESHEET_ATTR_TYPE = "type";
    private static final String XML_STYLESHEET_PROCESSING_INSTRUCTION = "xml-stylesheet";
    private SVGBase$SvgContainer currentElement;
    private boolean enableInternalEntities;
    private SVGExternalFileResolver externalFileResolver;
    private int ignoreDepth;
    private boolean ignoring;
    private boolean inMetadataElement;
    private boolean inStyleElement;
    private StringBuilder metadataElementContents;
    private SVGParserImpl.SVGParserImpl$SVGElem metadataTag;
    private StringBuilder styleElementContents;
    private SVGBase svgDocument;
    
    static {
        FORCE_SAX_ON_EARLY_ANDROIDS = (Build$VERSION.SDK_INT < 15);
        PATTERN_BLOCK_COMMENTS = Pattern.compile("/\\*.*?\\*/");
    }
    
    SVGParserImpl() {
        this.svgDocument = null;
        this.currentElement = null;
        this.enableInternalEntities = true;
        this.externalFileResolver = null;
        this.ignoring = false;
        this.inMetadataElement = false;
        this.metadataTag = null;
        this.metadataElementContents = null;
        this.inStyleElement = false;
        this.styleElementContents = null;
    }
    
    private void a(final Attributes attributes) throws SVGParseException {
        this.debug("<a>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$A currentElement = new SVGBase$A();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesA(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void appendToTextContainer(final String s) throws SVGParseException {
        final SVGBase$SvgConditionalContainer svgBase$SvgConditionalContainer = (SVGBase$SvgConditionalContainer)this.currentElement;
        final int size = svgBase$SvgConditionalContainer.getChildren().size();
        SVGBase$SvgObject svgBase$SvgObject;
        if (size == 0) {
            svgBase$SvgObject = null;
        }
        else {
            svgBase$SvgObject = (SVGBase$SvgObject)svgBase$SvgConditionalContainer.getChildren().get(size - 1);
        }
        if (svgBase$SvgObject instanceof SVGBase.TextSequence) {
            final StringBuilder sb = new StringBuilder();
            final SVGBase.TextSequence textSequence = (SVGBase.TextSequence)svgBase$SvgObject;
            sb.append(textSequence.text);
            sb.append(s);
            textSequence.text = sb.toString();
        }
        else {
            this.currentElement.addChild((SVGBase$SvgObject)new SVGBase.TextSequence(s));
        }
    }
    
    private void circle(final Attributes attributes) throws SVGParseException {
        this.debug("<circle>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Circle svgBase$Circle = new SVGBase$Circle();
            svgBase$Circle.document = this.svgDocument;
            svgBase$Circle.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Circle, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Circle, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Circle, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Circle, attributes);
            this.parseAttributesCircle(svgBase$Circle, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Circle);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private static int clamp255(final float n) {
        int round;
        if (n < 0.0f) {
            round = 0;
        }
        else if (n > 255.0f) {
            round = 255;
        }
        else {
            round = Math.round(n);
        }
        return round;
    }
    
    private void clipPath(final Attributes attributes) throws SVGParseException {
        this.debug("<clipPath>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$ClipPath currentElement = new SVGBase$ClipPath();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesClipPath(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void debug(final String s, final Object... array) {
    }
    
    private void defs(final Attributes attributes) throws SVGParseException {
        this.debug("<defs>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Defs currentElement = new SVGBase$Defs();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void dumpNode(final SVGBase$SvgObject svgBase$SvgObject, final String s) {
    }
    
    private void ellipse(final Attributes attributes) throws SVGParseException {
        this.debug("<ellipse>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Ellipse svgBase$Ellipse = new SVGBase$Ellipse();
            svgBase$Ellipse.document = this.svgDocument;
            svgBase$Ellipse.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Ellipse, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Ellipse, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Ellipse, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Ellipse, attributes);
            this.parseAttributesEllipse(svgBase$Ellipse, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Ellipse);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void endDocument() {
    }
    
    private void endElement(final String s, String s2, final String s3) throws SVGParseException {
        if (this.ignoring) {
            if (--this.ignoreDepth == 0) {
                this.ignoring = false;
            }
            return;
        }
        if (!"http://www.w3.org/2000/svg".equals((Object)s) && !"".equals((Object)s)) {
            return;
        }
        if (s2.length() <= 0) {
            s2 = s3;
        }
        final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGElem[SVGParserImpl.SVGParserImpl$SVGElem.fromString(s2).ordinal()];
        if (n != 1 && n != 2 && n != 3 && n != 4 && n != 5 && n != 13 && n != 14) {
            switch (n) {
                default: {
                    return;
                }
                case 30: {
                    final StringBuilder styleElementContents = this.styleElementContents;
                    if (styleElementContents != null) {
                        this.inStyleElement = false;
                        this.parseCSSStyleSheet(styleElementContents.toString());
                        this.styleElementContents.setLength(0);
                    }
                    return;
                }
                case 22:
                case 23: {
                    this.inMetadataElement = false;
                    if (this.metadataElementContents != null) {
                        if (this.metadataTag == SVGParserImpl.SVGParserImpl$SVGElem.title) {
                            this.svgDocument.setTitle(this.metadataElementContents.toString());
                        }
                        else if (this.metadataTag == SVGParserImpl.SVGParserImpl$SVGElem.desc) {
                            this.svgDocument.setDesc(this.metadataElementContents.toString());
                        }
                        this.metadataElementContents.setLength(0);
                    }
                    return;
                }
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 31: {
                    break;
                }
            }
        }
        final SVGBase$SvgContainer currentElement = this.currentElement;
        if (currentElement == null) {
            throw new SVGParseException(String.format("Unbalanced end element </%s> found", new Object[] { s2 }));
        }
        this.currentElement = ((SVGBase$SvgObject)currentElement).parent;
    }
    
    private void g(final Attributes attributes) throws SVGParseException {
        this.debug("<g>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Group currentElement = new SVGBase$Group();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void handleProcessingInstruction(String string, final Map<String, String> map) {
        if (string.equals((Object)"xml-stylesheet") && this.externalFileResolver != null) {
            if (map.get((Object)"type") != null && !"text/css".equals(map.get((Object)"type"))) {
                return;
            }
            if (map.get((Object)"alternate") != null && !"no".equals(map.get((Object)"alternate"))) {
                return;
            }
            string = (String)map.get((Object)"href");
            if (string != null) {
                final String resolveCSSStyleSheet = this.externalFileResolver.resolveCSSStyleSheet(string);
                if (resolveCSSStyleSheet == null) {
                    return;
                }
                final String s = (String)map.get((Object)"media");
                string = resolveCSSStyleSheet;
                if (s != null) {
                    string = resolveCSSStyleSheet;
                    if (!"all".equals((Object)s.trim())) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("@media ");
                        sb.append(s);
                        sb.append(" { ");
                        sb.append(resolveCSSStyleSheet);
                        sb.append("}");
                        string = sb.toString();
                    }
                }
                this.parseCSSStyleSheet(string);
            }
        }
    }
    
    private static int hslToRgb(float n, float n2, float hueToRgb) {
        final float n3 = 0.0f;
        final float n4 = n % 360.0f;
        if (n >= 0.0f) {
            n = n4;
        }
        else {
            n = n4 + 360.0f;
        }
        final float n5 = n / 60.0f;
        n = n2 / 100.0f;
        n2 = hueToRgb / 100.0f;
        if (n < 0.0f) {
            n = 0.0f;
        }
        else {
            n = Math.min(n, 1.0f);
        }
        if (n2 < 0.0f) {
            n2 = n3;
        }
        else {
            n2 = Math.min(n2, 1.0f);
        }
        if (n2 <= 0.5f) {
            n = (n + 1.0f) * n2;
        }
        else {
            n = n2 + n - n * n2;
        }
        final float n6 = n2 * 2.0f - n;
        hueToRgb = hueToRgb(n6, n, n5 + 2.0f);
        n2 = hueToRgb(n6, n, n5);
        n = hueToRgb(n6, n, n5 - 2.0f);
        return clamp255(n * 256.0f) | (clamp255(hueToRgb * 256.0f) << 16 | clamp255(n2 * 256.0f) << 8);
    }
    
    private static float hueToRgb(final float n, float n2, float n3) {
        float n4 = n3;
        if (n3 < 0.0f) {
            n4 = n3 + 6.0f;
        }
        n3 = n4;
        if (n4 >= 6.0f) {
            n3 = n4 - 6.0f;
        }
        if (n3 < 1.0f) {
            n2 = (n2 - n) * n3;
        }
        else {
            if (n3 < 3.0f) {
                return n2;
            }
            if (n3 >= 4.0f) {
                return n;
            }
            n2 = (n2 - n) * (4.0f - n3);
        }
        return n2 + n;
    }
    
    private void image(final Attributes attributes) throws SVGParseException {
        this.debug("<image>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Image currentElement = new SVGBase$Image();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesImage(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void line(final Attributes attributes) throws SVGParseException {
        this.debug("<line>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Line svgBase$Line = new SVGBase$Line();
            svgBase$Line.document = this.svgDocument;
            svgBase$Line.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Line, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Line, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Line, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Line, attributes);
            this.parseAttributesLine(svgBase$Line, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Line);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void linearGradient(final Attributes attributes) throws SVGParseException {
        this.debug("<linearGradient>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$SvgLinearGradient currentElement = new SVGBase$SvgLinearGradient();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesGradient((SVGBase$GradientElement)currentElement, attributes);
            this.parseAttributesLinearGradient(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void marker(final Attributes attributes) throws SVGParseException {
        this.debug("<marker>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Marker currentElement = new SVGBase$Marker();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesViewBox((SVGBase$SvgViewBoxContainer)currentElement, attributes);
            this.parseAttributesMarker(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void mask(final Attributes attributes) throws SVGParseException {
        this.debug("<mask>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Mask currentElement = new SVGBase$Mask();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesMask(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void parseAttributesA(final SVGBase$A svgBase$A, final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            if (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()] == 6) {
                if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                    svgBase$A.href = trim;
                }
            }
        }
    }
    
    private void parseAttributesCircle(final SVGBase$Circle svgBase$Circle, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 14: {
                    svgBase$Circle.r = parseLength(trim);
                    if (!svgBase$Circle.r.isNegative()) {
                        break;
                    }
                    throw new SVGParseException("Invalid <circle> element. r cannot be negative");
                }
                case 13: {
                    svgBase$Circle.cy = parseLength(trim);
                    break;
                }
                case 12: {
                    svgBase$Circle.cx = parseLength(trim);
                    break;
                }
            }
        }
    }
    
    private void parseAttributesClipPath(final SVGBase$ClipPath svgBase$ClipPath, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            if (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()] == 38) {
                if ("objectBoundingBox".equals((Object)trim)) {
                    svgBase$ClipPath.clipPathUnitsAreUser = false;
                }
                else {
                    if (!"userSpaceOnUse".equals((Object)trim)) {
                        throw new SVGParseException("Invalid value for attribute clipPathUnits");
                    }
                    svgBase$ClipPath.clipPathUnitsAreUser = true;
                }
            }
        }
    }
    
    private void parseAttributesConditional(final SVGBase$SvgConditional svgBase$SvgConditional, final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 25: {
                    final List<String> fontFamily = parseFontFamily(trim);
                    HashSet requiredFonts;
                    if (fontFamily != null) {
                        requiredFonts = new HashSet((Collection)fontFamily);
                    }
                    else {
                        requiredFonts = new HashSet(0);
                    }
                    svgBase$SvgConditional.setRequiredFonts((Set)requiredFonts);
                    break;
                }
                case 24: {
                    svgBase$SvgConditional.setRequiredFormats((Set)parseRequiredFormats(trim));
                    break;
                }
                case 23: {
                    svgBase$SvgConditional.setSystemLanguage((Set)parseSystemLanguage(trim));
                    break;
                }
                case 22: {
                    svgBase$SvgConditional.setRequiredExtensions(trim);
                    break;
                }
                case 21: {
                    svgBase$SvgConditional.setRequiredFeatures((Set)parseRequiredFeatures(trim));
                    break;
                }
            }
        }
    }
    
    private void parseAttributesCore(final SVGBase.SvgElementBase svgElementBase, final Attributes attributes) throws SVGParseException {
        int i = 0;
        while (i < attributes.getLength()) {
            final String qName = attributes.getQName(i);
            if (qName.equals((Object)"id") || qName.equals((Object)"xml:id")) {
                svgElementBase.id = attributes.getValue(i).trim();
                break;
            }
            if (qName.equals((Object)"xml:space")) {
                final String trim = attributes.getValue(i).trim();
                if ("default".equals((Object)trim)) {
                    svgElementBase.spacePreserve = Boolean.FALSE;
                    break;
                }
                if ("preserve".equals((Object)trim)) {
                    svgElementBase.spacePreserve = Boolean.TRUE;
                    break;
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("Invalid value for \"xml:space\" attribute: ");
                sb.append(trim);
                throw new SVGParseException(sb.toString());
            }
            else {
                ++i;
            }
        }
    }
    
    private void parseAttributesEllipse(final SVGBase$Ellipse svgBase$Ellipse, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 13: {
                    svgBase$Ellipse.cy = parseLength(trim);
                    break;
                }
                case 12: {
                    svgBase$Ellipse.cx = parseLength(trim);
                    break;
                }
                case 11: {
                    svgBase$Ellipse.ry = parseLength(trim);
                    if (!svgBase$Ellipse.ry.isNegative()) {
                        break;
                    }
                    throw new SVGParseException("Invalid <ellipse> element. ry cannot be negative");
                }
                case 10: {
                    svgBase$Ellipse.rx = parseLength(trim);
                    if (!svgBase$Ellipse.rx.isNegative()) {
                        break;
                    }
                    throw new SVGParseException("Invalid <ellipse> element. rx cannot be negative");
                }
            }
        }
    }
    
    private void parseAttributesGradient(final SVGBase$GradientElement svgBase$GradientElement, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 6) {
                switch (n) {
                    case 34: {
                        try {
                            svgBase$GradientElement.spreadMethod = SVGBase$GradientSpread.valueOf(trim);
                            break;
                        }
                        catch (final IllegalArgumentException ex) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("Invalid spreadMethod attribute. \"");
                            sb.append(trim);
                            sb.append("\" is not a valid value.");
                            throw new SVGParseException(sb.toString());
                        }
                    }
                    case 33: {
                        svgBase$GradientElement.gradientTransform = this.parseTransformList(trim);
                        break;
                    }
                    case 32: {
                        if ("objectBoundingBox".equals((Object)trim)) {
                            svgBase$GradientElement.gradientUnitsAreUser = false;
                            break;
                        }
                        if ("userSpaceOnUse".equals((Object)trim)) {
                            svgBase$GradientElement.gradientUnitsAreUser = true;
                            break;
                        }
                        throw new SVGParseException("Invalid value for attribute gradientUnits");
                    }
                }
            }
            else if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                svgBase$GradientElement.href = trim;
            }
        }
    }
    
    private void parseAttributesImage(final SVGBase$Image svgBase$Image, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n != 6) {
                                if (n == 7) {
                                    parsePreserveAspectRatio((SVGBase$SvgPreserveAspectRatioContainer)svgBase$Image, trim);
                                }
                            }
                            else if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                                svgBase$Image.href = trim;
                            }
                        }
                        else {
                            svgBase$Image.height = parseLength(trim);
                            if (svgBase$Image.height.isNegative()) {
                                throw new SVGParseException("Invalid <use> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Image.width = parseLength(trim);
                        if (svgBase$Image.width.isNegative()) {
                            throw new SVGParseException("Invalid <use> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Image.y = parseLength(trim);
                }
            }
            else {
                svgBase$Image.x = parseLength(trim);
            }
        }
    }
    
    private void parseAttributesLine(final SVGBase$Line svgBase$Line, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 18: {
                    svgBase$Line.y2 = parseLength(trim);
                    break;
                }
                case 17: {
                    svgBase$Line.x2 = parseLength(trim);
                    break;
                }
                case 16: {
                    svgBase$Line.y1 = parseLength(trim);
                    break;
                }
                case 15: {
                    svgBase$Line.x1 = parseLength(trim);
                    break;
                }
            }
        }
    }
    
    private void parseAttributesLinearGradient(final SVGBase$SvgLinearGradient svgBase$SvgLinearGradient, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 18: {
                    svgBase$SvgLinearGradient.y2 = parseLength(trim);
                    break;
                }
                case 17: {
                    svgBase$SvgLinearGradient.x2 = parseLength(trim);
                    break;
                }
                case 16: {
                    svgBase$SvgLinearGradient.y1 = parseLength(trim);
                    break;
                }
                case 15: {
                    svgBase$SvgLinearGradient.x1 = parseLength(trim);
                    break;
                }
            }
        }
    }
    
    private void parseAttributesMarker(final SVGBase$Marker svgBase$Marker, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()]) {
                case 31: {
                    if ("auto".equals((Object)trim)) {
                        svgBase$Marker.orient = Float.NaN;
                        break;
                    }
                    svgBase$Marker.orient = parseFloat(trim);
                    break;
                }
                case 30: {
                    if ("strokeWidth".equals((Object)trim)) {
                        svgBase$Marker.markerUnitsAreUser = false;
                        break;
                    }
                    if ("userSpaceOnUse".equals((Object)trim)) {
                        svgBase$Marker.markerUnitsAreUser = true;
                        break;
                    }
                    throw new SVGParseException("Invalid value for attribute markerUnits");
                }
                case 29: {
                    svgBase$Marker.markerHeight = parseLength(trim);
                    if (!svgBase$Marker.markerHeight.isNegative()) {
                        break;
                    }
                    throw new SVGParseException("Invalid <marker> element. markerHeight cannot be negative");
                }
                case 28: {
                    svgBase$Marker.markerWidth = parseLength(trim);
                    if (!svgBase$Marker.markerWidth.isNegative()) {
                        break;
                    }
                    throw new SVGParseException("Invalid <marker> element. markerWidth cannot be negative");
                }
                case 27: {
                    svgBase$Marker.refY = parseLength(trim);
                    break;
                }
                case 26: {
                    svgBase$Marker.refX = parseLength(trim);
                    break;
                }
            }
        }
    }
    
    private void parseAttributesMask(final SVGBase$Mask svgBase$Mask, final Attributes attributes) throws SVGParseException {
        int i = 0;
        final Boolean value = false;
        while (i < attributes.getLength()) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n != 43) {
                                if (n == 44) {
                                    if ("objectBoundingBox".equals((Object)trim)) {
                                        svgBase$Mask.maskContentUnitsAreUser = value;
                                    }
                                    else {
                                        if (!"userSpaceOnUse".equals((Object)trim)) {
                                            throw new SVGParseException("Invalid value for attribute maskContentUnits");
                                        }
                                        svgBase$Mask.maskContentUnitsAreUser = true;
                                    }
                                }
                            }
                            else if ("objectBoundingBox".equals((Object)trim)) {
                                svgBase$Mask.maskUnitsAreUser = value;
                            }
                            else {
                                if (!"userSpaceOnUse".equals((Object)trim)) {
                                    throw new SVGParseException("Invalid value for attribute maskUnits");
                                }
                                svgBase$Mask.maskUnitsAreUser = true;
                            }
                        }
                        else {
                            svgBase$Mask.height = parseLength(trim);
                            if (svgBase$Mask.height.isNegative()) {
                                throw new SVGParseException("Invalid <mask> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Mask.width = parseLength(trim);
                        if (svgBase$Mask.width.isNegative()) {
                            throw new SVGParseException("Invalid <mask> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Mask.y = parseLength(trim);
                }
            }
            else {
                svgBase$Mask.x = parseLength(trim);
            }
            ++i;
        }
    }
    
    private void parseAttributesPath(final SVGBase$Path svgBase$Path, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 8) {
                if (n == 9) {
                    svgBase$Path.pathLength = parseFloat(trim);
                    if (svgBase$Path.pathLength < 0.0f) {
                        throw new SVGParseException("Invalid <path> element. pathLength cannot be negative");
                    }
                }
            }
            else {
                svgBase$Path.d = parsePath(trim);
            }
        }
    }
    
    private void parseAttributesPattern(final SVGBase$Pattern svgBase$Pattern, final Attributes attributes) throws SVGParseException {
        int i = 0;
        final Boolean value = false;
        while (i < attributes.getLength()) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n != 6) {
                                switch (n) {
                                    case 42: {
                                        svgBase$Pattern.patternTransform = this.parseTransformList(trim);
                                        break;
                                    }
                                    case 41: {
                                        if ("objectBoundingBox".equals((Object)trim)) {
                                            svgBase$Pattern.patternContentUnitsAreUser = value;
                                            break;
                                        }
                                        if ("userSpaceOnUse".equals((Object)trim)) {
                                            svgBase$Pattern.patternContentUnitsAreUser = true;
                                            break;
                                        }
                                        throw new SVGParseException("Invalid value for attribute patternContentUnits");
                                    }
                                    case 40: {
                                        if ("objectBoundingBox".equals((Object)trim)) {
                                            svgBase$Pattern.patternUnitsAreUser = value;
                                            break;
                                        }
                                        if ("userSpaceOnUse".equals((Object)trim)) {
                                            svgBase$Pattern.patternUnitsAreUser = true;
                                            break;
                                        }
                                        throw new SVGParseException("Invalid value for attribute patternUnits");
                                    }
                                }
                            }
                            else if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                                svgBase$Pattern.href = trim;
                            }
                        }
                        else {
                            svgBase$Pattern.height = parseLength(trim);
                            if (svgBase$Pattern.height.isNegative()) {
                                throw new SVGParseException("Invalid <pattern> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Pattern.width = parseLength(trim);
                        if (svgBase$Pattern.width.isNegative()) {
                            throw new SVGParseException("Invalid <pattern> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Pattern.y = parseLength(trim);
                }
            }
            else {
                svgBase$Pattern.x = parseLength(trim);
            }
            ++i;
        }
    }
    
    private void parseAttributesPolyLine(final SVGBase$PolyLine svgBase$PolyLine, final Attributes attributes, final String s) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)) == SVGParserImpl.SVGParserImpl$SVGAttr.points) {
                final TextScanner textScanner = new TextScanner(attributes.getValue(i));
                final ArrayList list = new ArrayList();
                textScanner.skipWhitespace();
                while (!textScanner.empty()) {
                    final float nextFloat = textScanner.nextFloat();
                    if (Float.isNaN(nextFloat)) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("Invalid <");
                        sb.append(s);
                        sb.append("> points attribute. Non-coordinate content found in list.");
                        throw new SVGParseException(sb.toString());
                    }
                    textScanner.skipCommaWhitespace();
                    final float nextFloat2 = textScanner.nextFloat();
                    if (Float.isNaN(nextFloat2)) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid <");
                        sb2.append(s);
                        sb2.append("> points attribute. There should be an even number of coordinates.");
                        throw new SVGParseException(sb2.toString());
                    }
                    textScanner.skipCommaWhitespace();
                    ((List)list).add((Object)nextFloat);
                    ((List)list).add((Object)nextFloat2);
                }
                svgBase$PolyLine.points = new float[((List)list).size()];
                final Iterator iterator = ((List)list).iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    svgBase$PolyLine.points[n] = (float)iterator.next();
                    ++n;
                }
            }
        }
    }
    
    private void parseAttributesRadialGradient(final SVGBase$SvgRadialGradient svgBase$SvgRadialGradient, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 35) {
                if (n != 36) {
                    switch (n) {
                        case 14: {
                            svgBase$SvgRadialGradient.r = parseLength(trim);
                            if (!svgBase$SvgRadialGradient.r.isNegative()) {
                                break;
                            }
                            throw new SVGParseException("Invalid <radialGradient> element. r cannot be negative");
                        }
                        case 13: {
                            svgBase$SvgRadialGradient.cy = parseLength(trim);
                            break;
                        }
                        case 12: {
                            svgBase$SvgRadialGradient.cx = parseLength(trim);
                            break;
                        }
                    }
                }
                else {
                    svgBase$SvgRadialGradient.fy = parseLength(trim);
                }
            }
            else {
                svgBase$SvgRadialGradient.fx = parseLength(trim);
            }
        }
    }
    
    private void parseAttributesRect(final SVGBase$Rect svgBase$Rect, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n != 10) {
                                if (n == 11) {
                                    svgBase$Rect.ry = parseLength(trim);
                                    if (svgBase$Rect.ry.isNegative()) {
                                        throw new SVGParseException("Invalid <rect> element. ry cannot be negative");
                                    }
                                }
                            }
                            else {
                                svgBase$Rect.rx = parseLength(trim);
                                if (svgBase$Rect.rx.isNegative()) {
                                    throw new SVGParseException("Invalid <rect> element. rx cannot be negative");
                                }
                            }
                        }
                        else {
                            svgBase$Rect.height = parseLength(trim);
                            if (svgBase$Rect.height.isNegative()) {
                                throw new SVGParseException("Invalid <rect> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Rect.width = parseLength(trim);
                        if (svgBase$Rect.width.isNegative()) {
                            throw new SVGParseException("Invalid <rect> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Rect.y = parseLength(trim);
                }
            }
            else {
                svgBase$Rect.x = parseLength(trim);
            }
        }
    }
    
    private void parseAttributesSVG(final SVGBase$Svg svgBase$Svg, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n == 5) {
                                svgBase$Svg.version = trim;
                            }
                        }
                        else {
                            svgBase$Svg.height = parseLength(trim);
                            if (svgBase$Svg.height.isNegative()) {
                                throw new SVGParseException("Invalid <svg> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Svg.width = parseLength(trim);
                        if (svgBase$Svg.width.isNegative()) {
                            throw new SVGParseException("Invalid <svg> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Svg.y = parseLength(trim);
                }
            }
            else {
                svgBase$Svg.x = parseLength(trim);
            }
        }
    }
    
    private void parseAttributesStop(final SVGBase$Stop svgBase$Stop, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            if (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()] == 37) {
                svgBase$Stop.offset = this.parseGradientOffset(trim);
            }
        }
    }
    
    private void parseAttributesStyle(final SVGBase.SvgElementBase svgElementBase, final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            if (trim.length() != 0) {
                final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
                if (n != 45) {
                    if (n != 46) {
                        if (svgElementBase.baseStyle == null) {
                            svgElementBase.baseStyle = new Style();
                        }
                        Style.processStyleProperty(svgElementBase.baseStyle, attributes.getLocalName(i), attributes.getValue(i).trim(), true);
                    }
                    else {
                        svgElementBase.classNames = (List<String>)CSSParser.parseClassAttribute(trim);
                    }
                }
                else {
                    parseStyle(svgElementBase, trim);
                }
            }
        }
    }
    
    private void parseAttributesTRef(final SVGBase$TRef svgBase$TRef, final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            if (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()] == 6) {
                if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                    svgBase$TRef.href = trim;
                }
            }
        }
    }
    
    private void parseAttributesTextPath(final SVGBase$TextPath svgBase$TextPath, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 6) {
                if (n == 39) {
                    svgBase$TextPath.startOffset = parseLength(trim);
                }
            }
            else if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                svgBase$TextPath.href = trim;
            }
        }
    }
    
    private void parseAttributesTextPosition(final SVGBase$TextPositionedContainer svgBase$TextPositionedContainer, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 19) {
                        if (n == 20) {
                            svgBase$TextPositionedContainer.dy = parseLengthList(trim);
                        }
                    }
                    else {
                        svgBase$TextPositionedContainer.dx = parseLengthList(trim);
                    }
                }
                else {
                    svgBase$TextPositionedContainer.y = parseLengthList(trim);
                }
            }
            else {
                svgBase$TextPositionedContainer.x = parseLengthList(trim);
            }
        }
    }
    
    private void parseAttributesTransform(final SVGBase$HasTransform svgBase$HasTransform, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)) == SVGParserImpl.SVGParserImpl$SVGAttr.transform) {
                svgBase$HasTransform.setTransform(this.parseTransformList(attributes.getValue(i)));
            }
        }
    }
    
    private void parseAttributesUse(final SVGBase$Use svgBase$Use, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n == 6) {
                                if ("".equals((Object)attributes.getURI(i)) || "http://www.w3.org/1999/xlink".equals((Object)attributes.getURI(i))) {
                                    svgBase$Use.href = trim;
                                }
                            }
                        }
                        else {
                            svgBase$Use.height = parseLength(trim);
                            if (svgBase$Use.height.isNegative()) {
                                throw new SVGParseException("Invalid <use> element. height cannot be negative");
                            }
                        }
                    }
                    else {
                        svgBase$Use.width = parseLength(trim);
                        if (svgBase$Use.width.isNegative()) {
                            throw new SVGParseException("Invalid <use> element. width cannot be negative");
                        }
                    }
                }
                else {
                    svgBase$Use.y = parseLength(trim);
                }
            }
            else {
                svgBase$Use.x = parseLength(trim);
            }
        }
    }
    
    private void parseAttributesViewBox(final SVGBase$SvgViewBoxContainer svgBase$SvgViewBoxContainer, final Attributes attributes) throws SVGParseException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String trim = attributes.getValue(i).trim();
            final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
            if (n != 7) {
                if (n == 47) {
                    svgBase$SvgViewBoxContainer.viewBox = parseViewBox(trim);
                }
            }
            else {
                parsePreserveAspectRatio((SVGBase$SvgPreserveAspectRatioContainer)svgBase$SvgViewBoxContainer, trim);
            }
        }
    }
    
    private void parseCSSStyleSheet(final String s) {
        this.svgDocument.addCSSRules(new CSSParser(CSSParser$MediaType.screen, CSSParser$Source.Document, this.externalFileResolver).parse(s));
    }
    
    static SVGBase$CSSClipRect parseClip(final String s) {
        if ("auto".equals((Object)s)) {
            return null;
        }
        if (!s.startsWith("rect(")) {
            return null;
        }
        final TextScanner textScanner = new TextScanner(s.substring(5));
        textScanner.skipWhitespace();
        final SVGBase$Length lengthOrAuto = parseLengthOrAuto(textScanner);
        textScanner.skipCommaWhitespace();
        final SVGBase$Length lengthOrAuto2 = parseLengthOrAuto(textScanner);
        textScanner.skipCommaWhitespace();
        final SVGBase$Length lengthOrAuto3 = parseLengthOrAuto(textScanner);
        textScanner.skipCommaWhitespace();
        final SVGBase$Length lengthOrAuto4 = parseLengthOrAuto(textScanner);
        textScanner.skipWhitespace();
        if (!textScanner.consume(')') && !textScanner.empty()) {
            return null;
        }
        return new SVGBase$CSSClipRect(lengthOrAuto, lengthOrAuto2, lengthOrAuto3, lengthOrAuto4);
    }
    
    static SVGBase.Colour parseColour(final String s) {
        final char char1 = s.charAt(0);
        int n = 5;
        if (char1 != '#') {
            final String lowerCase = s.toLowerCase(Locale.US);
            final boolean startsWith = lowerCase.startsWith("rgba(");
            float n2 = Float.NaN;
            if (!startsWith && !lowerCase.startsWith("rgb(")) {
                final boolean startsWith2 = lowerCase.startsWith("hsla(");
                if (startsWith2 || lowerCase.startsWith("hsl(")) {
                    if (!startsWith2) {
                        n = 4;
                    }
                    final TextScanner textScanner = new TextScanner(s.substring(n));
                    textScanner.skipWhitespace();
                    final float nextFloat = textScanner.nextFloat();
                    if (!Float.isNaN(nextFloat)) {
                        textScanner.consume("deg");
                        final boolean skipCommaWhitespace = textScanner.skipCommaWhitespace();
                        final float nextFloat2 = textScanner.nextFloat();
                        if (!Float.isNaN(nextFloat2)) {
                            if (!textScanner.consume('%')) {
                                return SVGBase.Colour.BLACK;
                            }
                            if (skipCommaWhitespace) {
                                if (!textScanner.skipCommaWhitespace()) {
                                    return SVGBase.Colour.BLACK;
                                }
                            }
                            else {
                                textScanner.skipWhitespace();
                            }
                            final float nextFloat3 = textScanner.nextFloat();
                            if (!Float.isNaN(nextFloat3)) {
                                if (!textScanner.consume('%')) {
                                    return SVGBase.Colour.BLACK;
                                }
                                if (skipCommaWhitespace) {
                                    if (textScanner.skipCommaWhitespace()) {
                                        n2 = textScanner.nextFloat();
                                    }
                                }
                                else {
                                    textScanner.skipWhitespace();
                                    if (textScanner.consume('/')) {
                                        textScanner.skipWhitespace();
                                        n2 = textScanner.nextFloat();
                                    }
                                }
                                textScanner.skipWhitespace();
                                if (!textScanner.consume(')')) {
                                    return SVGBase.Colour.BLACK;
                                }
                                if (Float.isNaN(n2)) {
                                    return new SVGBase.Colour(hslToRgb(nextFloat, nextFloat2, nextFloat3) | 0xFF000000);
                                }
                                return new SVGBase.Colour(hslToRgb(nextFloat, nextFloat2, nextFloat3) | clamp255(n2 * 256.0f) << 24);
                            }
                        }
                    }
                }
            }
            else {
                if (!startsWith) {
                    n = 4;
                }
                final TextScanner textScanner2 = new TextScanner(s.substring(n));
                textScanner2.skipWhitespace();
                final float nextFloat4 = textScanner2.nextFloat();
                if (!Float.isNaN(nextFloat4)) {
                    float n3 = nextFloat4;
                    if (textScanner2.consume('%')) {
                        n3 = nextFloat4 * 256.0f / 100.0f;
                    }
                    final boolean skipCommaWhitespace2 = textScanner2.skipCommaWhitespace();
                    final float nextFloat5 = textScanner2.nextFloat();
                    if (!Float.isNaN(nextFloat5)) {
                        float n4 = nextFloat5;
                        if (textScanner2.consume('%')) {
                            n4 = nextFloat5 * 256.0f / 100.0f;
                        }
                        if (skipCommaWhitespace2) {
                            if (!textScanner2.skipCommaWhitespace()) {
                                return SVGBase.Colour.BLACK;
                            }
                        }
                        else {
                            textScanner2.skipWhitespace();
                        }
                        final float nextFloat6 = textScanner2.nextFloat();
                        if (!Float.isNaN(nextFloat6)) {
                            float n5 = nextFloat6;
                            if (textScanner2.consume('%')) {
                                n5 = nextFloat6 * 256.0f / 100.0f;
                            }
                            if (skipCommaWhitespace2) {
                                if (textScanner2.skipCommaWhitespace()) {
                                    n2 = textScanner2.nextFloat();
                                }
                            }
                            else {
                                textScanner2.skipWhitespace();
                                if (textScanner2.consume('/')) {
                                    textScanner2.skipWhitespace();
                                    n2 = textScanner2.nextFloat();
                                }
                            }
                            textScanner2.skipWhitespace();
                            if (!textScanner2.consume(')')) {
                                return SVGBase.Colour.BLACK;
                            }
                            if (Float.isNaN(n2)) {
                                return new SVGBase.Colour(clamp255(n3) << 16 | 0xFF000000 | clamp255(n4) << 8 | clamp255(n5));
                            }
                            return new SVGBase.Colour(clamp255(n3) << 16 | clamp255(n2 * 256.0f) << 24 | clamp255(n4) << 8 | clamp255(n5));
                        }
                    }
                }
            }
            return parseColourKeyword(lowerCase);
        }
        final IntegerParser hex = IntegerParser.parseHex(s, 1, s.length());
        if (hex == null) {
            return SVGBase.Colour.BLACK;
        }
        final int endPos = hex.getEndPos();
        if (endPos == 4) {
            final int value = hex.value();
            final int n6 = value & 0xF00;
            final int n7 = value & 0xF0;
            final int n8 = value & 0xF;
            return new SVGBase.Colour(n8 | (n6 << 8 | (0xFF000000 | n6 << 12) | n7 << 8 | n7 << 4 | n8 << 4));
        }
        if (endPos == 5) {
            final int value2 = hex.value();
            final int n9 = 0xF000 & value2;
            final int n10 = value2 & 0xF00;
            final int n11 = value2 & 0xF0;
            final int n12 = value2 & 0xF;
            return new SVGBase.Colour(n12 << 24 | n12 << 28 | n9 << 8 | n9 << 4 | n10 << 4 | n10 | n11 | n11 >> 4);
        }
        if (endPos == 7) {
            return new SVGBase.Colour(hex.value() | 0xFF000000);
        }
        if (endPos != 9) {
            return SVGBase.Colour.BLACK;
        }
        return new SVGBase.Colour(hex.value() >>> 8 | hex.value() << 24);
    }
    
    private static SVGBase.Colour parseColourKeyword(final String s) {
        final Integer value = SVGParserImpl.SVGParserImpl$ColourKeywords.get(s);
        SVGBase.Colour black;
        if (value == null) {
            black = SVGBase.Colour.BLACK;
        }
        else {
            black = new SVGBase.Colour(value);
        }
        return black;
    }
    
    private static SVGBase$SvgPaint parseColourSpecifer(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0052: {
            if (hashCode != 3387192) {
                if (hashCode == 1442907498) {
                    if (s.equals((Object)"currentColor")) {
                        n = 1;
                        break Label_0052;
                    }
                }
            }
            else if (s.equals((Object)"none")) {
                n = 0;
                break Label_0052;
            }
            n = -1;
        }
        if (n == 0) {
            return (SVGBase$SvgPaint)SVGBase.Colour.TRANSPARENT;
        }
        if (n != 1) {
            return parseColour(s);
        }
        return (SVGBase$SvgPaint)SVGBase.CurrentColor.getInstance();
    }
    
    static Style$FillRule parseFillRule(final String s) {
        if ("nonzero".equals((Object)s)) {
            return Style$FillRule.NonZero;
        }
        if ("evenodd".equals((Object)s)) {
            return Style$FillRule.EvenOdd;
        }
        return null;
    }
    
    static float parseFloat(final String s) throws SVGParseException {
        final int length = s.length();
        if (length != 0) {
            return parseFloat(s, 0, length);
        }
        throw new SVGParseException("Invalid float value (empty string)");
    }
    
    private static float parseFloat(final String s, final int n, final int n2) throws SVGParseException {
        final float number = new NumberParser().parseNumber(s, n, n2);
        if (!Float.isNaN(number)) {
            return number;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Invalid float value: ");
        sb.append(s);
        throw new SVGParseException(sb.toString());
    }
    
    static void parseFont(final Style style, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append('|');
        sb.append(s);
        sb.append('|');
        if ("|caption|icon|menu|message-box|small-caption|status-bar|".contains((CharSequence)sb.toString())) {
            return;
        }
        final TextScanner textScanner = new TextScanner(s);
        Float value = null;
        final Boolean b = null;
        Boolean value3;
        Object value2 = value3 = b;
        Object fontStyle = b;
        String nextToken;
        while (true) {
            nextToken = textScanner.nextToken('/');
            textScanner.skipWhitespace();
            if (nextToken == null) {
                return;
            }
            if (value != null && fontStyle != null) {
                break;
            }
            if (nextToken.equals((Object)"normal")) {
                continue;
            }
            if (value == null && SVGParserImpl.SVGParserImpl$FontWeightKeywords.contains(nextToken)) {
                value = SVGParserImpl.SVGParserImpl$FontWeightKeywords.get(nextToken);
            }
            else {
                Style$FontStyle style$FontStyle;
                if ((style$FontStyle = (Style$FontStyle)fontStyle) == null) {
                    fontStyle = parseFontStyle(nextToken);
                    if ((style$FontStyle = (Style$FontStyle)fontStyle) != null) {
                        continue;
                    }
                }
                if (value3 == null && nextToken.equals((Object)"small-caps")) {
                    value3 = true;
                    fontStyle = style$FontStyle;
                }
                else {
                    fontStyle = style$FontStyle;
                    if (value2 != null) {
                        break;
                    }
                    fontStyle = style$FontStyle;
                    if (!SVGParserImpl.SVGParserImpl$FontStretchKeywords.contains(nextToken)) {
                        break;
                    }
                    value2 = SVGParserImpl.SVGParserImpl$FontStretchKeywords.get(nextToken);
                    fontStyle = style$FontStyle;
                }
            }
        }
        final SVGBase$Length fontSize = parseFontSize(nextToken);
        if (textScanner.consume('/')) {
            textScanner.skipWhitespace();
            final String nextToken2 = textScanner.nextToken();
            if (nextToken2 != null) {
                try {
                    parseLength(nextToken2);
                }
                catch (final SVGParseException ex) {
                    return;
                }
            }
            textScanner.skipWhitespace();
        }
        style.fontFamily = parseFontFamily(textScanner.restOfText());
        style.fontSize = fontSize;
        float floatValue;
        if (value == null) {
            floatValue = 400.0f;
        }
        else {
            floatValue = value;
        }
        style.fontWeight = floatValue;
        Style$FontStyle normal = (Style$FontStyle)fontStyle;
        if (fontStyle == null) {
            normal = Style$FontStyle.normal;
        }
        style.fontStyle = normal;
        float floatValue2;
        if (value2 == null) {
            floatValue2 = 100.0f;
        }
        else {
            floatValue2 = (float)value2;
        }
        style.fontStretch = floatValue2;
        style.fontKerning = Style$FontKerning.auto;
        style.fontVariantLigatures = CSSFontFeatureSettings.LIGATURES_NORMAL;
        style.fontVariantPosition = CSSFontFeatureSettings.POSITION_ALL_OFF;
        style.fontVariantCaps = CSSFontFeatureSettings.CAPS_ALL_OFF;
        if (value3 == Boolean.TRUE) {
            style.fontVariantCaps = CSSFontFeatureSettings.makeSmallCaps();
        }
        style.fontVariantNumeric = CSSFontFeatureSettings.NUMERIC_ALL_OFF;
        style.fontVariantEastAsian = CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF;
        style.fontFeatureSettings = CSSFontFeatureSettings.FONT_FEATURE_SETTINGS_NORMAL;
        style.fontVariationSettings = null;
        style.specifiedFlags |= 0xE3F000001E000L;
    }
    
    static List<String> parseFontFamily(final String s) {
        final TextScanner textScanner = new TextScanner(s);
        List<String> list = null;
        Object o;
        do {
            String s2;
            if ((s2 = textScanner.nextQuotedString()) == null) {
                s2 = textScanner.nextTokenWithWhitespace(',');
            }
            if (s2 == null) {
                return list;
            }
            if ((o = list) == null) {
                o = new ArrayList();
            }
            ((List)o).add((Object)s2);
            textScanner.skipCommaWhitespace();
            list = (List<String>)o;
        } while (!textScanner.empty());
        list = (List<String>)o;
        return list;
    }
    
    static SVGBase$Length parseFontSize(final String s) {
        try {
            SVGBase$Length svgBase$Length;
            if ((svgBase$Length = SVGParserImpl.SVGParserImpl$FontSizeKeywords.get(s)) == null) {
                svgBase$Length = parseLength(s);
            }
            return svgBase$Length;
        }
        catch (final SVGParseException ex) {
            return null;
        }
    }
    
    static Float parseFontStretch(final String s) {
        Float n;
        if ((n = SVGParserImpl.SVGParserImpl$FontStretchKeywords.get(s)) == null) {
            final TextScanner textScanner = new TextScanner(s);
            n = textScanner.nextFloat();
            if (!textScanner.consume('%')) {
                return null;
            }
            textScanner.skipWhitespace();
            if (!textScanner.empty()) {
                return null;
            }
            if (n < 0.0f) {
                return null;
            }
        }
        return n;
    }
    
    static Style$FontStyle parseFontStyle(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0076: {
            if (hashCode != -1657669071) {
                if (hashCode != -1178781136) {
                    if (hashCode == -1039745817) {
                        if (s.equals((Object)"normal")) {
                            n = 1;
                            break Label_0076;
                        }
                    }
                }
                else if (s.equals((Object)"italic")) {
                    n = 0;
                    break Label_0076;
                }
            }
            else if (s.equals((Object)"oblique")) {
                n = 2;
                break Label_0076;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$FontStyle.italic;
        }
        if (n == 1) {
            return Style$FontStyle.normal;
        }
        if (n != 2) {
            return null;
        }
        return Style$FontStyle.oblique;
    }
    
    static Float parseFontWeight(final String s) {
        Float n;
        if ((n = SVGParserImpl.SVGParserImpl$FontWeightKeywords.get(s)) == null) {
            final TextScanner textScanner = new TextScanner(s);
            n = textScanner.nextFloat();
            textScanner.skipWhitespace();
            if (!textScanner.empty()) {
                return null;
            }
            if (n < 1.0f || n > 1000.0f) {
                return null;
            }
        }
        return n;
    }
    
    static String parseFunctionalIRI(final String s, final String s2) {
        if (s.equals((Object)"none")) {
            return null;
        }
        if (!s.startsWith("url(")) {
            return null;
        }
        if (s.endsWith(")")) {
            return s.substring(4, s.length() - 1).trim();
        }
        return s.substring(4).trim();
    }
    
    private Float parseGradientOffset(final String s) throws SVGParseException {
        if (s.length() != 0) {
            int length = s.length();
            final int length2 = s.length();
            boolean b = true;
            if (s.charAt(length2 - 1) == '%') {
                --length;
            }
            else {
                b = false;
            }
            try {
                final float float1 = parseFloat(s, 0, length);
                final float n = 100.0f;
                float n2 = float1;
                if (b) {
                    n2 = float1 / 100.0f;
                }
                if (n2 < 0.0f) {
                    n2 = 0.0f;
                }
                else if (n2 > 100.0f) {
                    n2 = n;
                }
                return n2;
            }
            catch (final NumberFormatException ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Invalid offset value in <stop>: ");
                sb.append(s);
                throw new SVGParseException(sb.toString(), (Exception)ex);
            }
        }
        throw new SVGParseException("Invalid offset value in <stop> (empty string)");
    }
    
    static Style$Isolation parseIsolation(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0054: {
            if (hashCode != 3005871) {
                if (hashCode == 2096783531) {
                    if (s.equals((Object)"isolate")) {
                        n = 1;
                        break Label_0054;
                    }
                }
            }
            else if (s.equals((Object)"auto")) {
                n = 0;
                break Label_0054;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$Isolation.auto;
        }
        if (n != 1) {
            return null;
        }
        return Style$Isolation.isolate;
    }
    
    static SVGBase$Length parseLength(final String s) throws SVGParseException {
        if (s.length() != 0) {
            final int length = s.length();
            final SVGBase$Unit px = SVGBase$Unit.px;
            final char char1 = s.charAt(length - 1);
            int n;
            SVGBase$Unit svgBase$Unit;
            if (char1 == '%') {
                n = length - 1;
                svgBase$Unit = SVGBase$Unit.percent;
            }
            else {
                n = length;
                svgBase$Unit = px;
                if (length > 2) {
                    n = length;
                    svgBase$Unit = px;
                    if (Character.isLetter(char1)) {
                        n = length;
                        svgBase$Unit = px;
                        if (Character.isLetter(s.charAt(length - 2))) {
                            n = length - 2;
                            final String substring = s.substring(n);
                            try {
                                svgBase$Unit = SVGBase$Unit.valueOf(substring.toLowerCase(Locale.US));
                            }
                            catch (final IllegalArgumentException ex) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append("Invalid length unit specifier: ");
                                sb.append(s);
                                throw new SVGParseException(sb.toString());
                            }
                        }
                    }
                }
            }
            try {
                return new SVGBase$Length(parseFloat(s, 0, n), svgBase$Unit);
            }
            catch (final NumberFormatException ex2) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid length value: ");
                sb2.append(s);
                throw new SVGParseException(sb2.toString(), (Exception)ex2);
            }
        }
        throw new SVGParseException("Invalid length value (empty string)");
    }
    
    private static List<SVGBase$Length> parseLengthList(final String s) throws SVGParseException {
        if (s.length() != 0) {
            final ArrayList list = new ArrayList(1);
            final TextScanner textScanner = new TextScanner(s);
            textScanner.skipWhitespace();
            while (!textScanner.empty()) {
                final float nextFloat = textScanner.nextFloat();
                if (Float.isNaN(nextFloat)) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Invalid length list value: ");
                    sb.append(textScanner.ahead());
                    throw new SVGParseException(sb.toString());
                }
                SVGBase$Unit svgBase$Unit;
                if ((svgBase$Unit = textScanner.nextUnit()) == null) {
                    svgBase$Unit = SVGBase$Unit.px;
                }
                ((List)list).add((Object)new SVGBase$Length(nextFloat, svgBase$Unit));
                textScanner.skipCommaWhitespace();
            }
            return (List<SVGBase$Length>)list;
        }
        throw new SVGParseException("Invalid length list (empty string)");
    }
    
    private static SVGBase$Length parseLengthOrAuto(final TextScanner textScanner) {
        if (textScanner.consume("auto")) {
            return SVGBase$Length.ZERO;
        }
        return textScanner.nextLength();
    }
    
    static SVGBase$Length parseLetterOrWordSpacing(final String s) {
        if ("normal".equals((Object)s)) {
            return SVGBase$Length.ZERO;
        }
        final SVGBase$Length svgBase$Length = null;
        try {
            SVGBase$Length length = parseLength(s);
            if (length.unit == SVGBase$Unit.percent) {
                length = svgBase$Length;
            }
            return length;
        }
        catch (final SVGParseException ex) {
            return svgBase$Length;
        }
    }
    
    static Float parseOpacity(final String s) {
        try {
            final float float1 = parseFloat(s);
            float min = 0.0f;
            if (float1 >= 0.0f) {
                min = Math.min(float1, 1.0f);
            }
            return min;
        }
        catch (final SVGParseException ex) {
            return null;
        }
    }
    
    static Boolean parseOverflow(final String s) {
        int n = 0;
        Label_0113: {
            switch (s.hashCode()) {
                case 466743410: {
                    if (s.equals((Object)"visible")) {
                        n = 0;
                        break Label_0113;
                    }
                    break;
                }
                case 3005871: {
                    if (s.equals((Object)"auto")) {
                        n = 1;
                        break Label_0113;
                    }
                    break;
                }
                case -907680051: {
                    if (s.equals((Object)"scroll")) {
                        n = 3;
                        break Label_0113;
                    }
                    break;
                }
                case -1217487446: {
                    if (s.equals((Object)"hidden")) {
                        n = 2;
                        break Label_0113;
                    }
                    break;
                }
            }
            n = -1;
        }
        if (n == 0 || n == 1) {
            return Boolean.TRUE;
        }
        if (n != 2 && n != 3) {
            return null;
        }
        return Boolean.FALSE;
    }
    
    static SVGBase$SvgPaint parsePaintSpecifier(final String s) {
        if (!s.startsWith("url(")) {
            return parseColourSpecifer(s);
        }
        final int index = s.indexOf(")");
        final SVGBase$SvgPaint svgBase$SvgPaint = null;
        if (index != -1) {
            final String trim = s.substring(4, index).trim();
            final String trim2 = s.substring(index + 1).trim();
            SVGBase$SvgPaint colourSpecifer = svgBase$SvgPaint;
            if (trim2.length() > 0) {
                colourSpecifer = parseColourSpecifer(trim2);
            }
            return new SVGBase.PaintReference(trim, colourSpecifer);
        }
        return new SVGBase.PaintReference(s.substring(4).trim(), null);
    }
    
    protected static SVGBase.PathDefinition parsePath(final String s) {
        final TextScanner textScanner = new TextScanner(s);
        final SVGBase.PathDefinition pathDefinition = new SVGBase.PathDefinition();
        if (textScanner.empty()) {
            return pathDefinition;
        }
        int n = textScanner.nextChar();
        if (n != 77 && n != 109) {
            return pathDefinition;
        }
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        float n7 = 0.0f;
        while (true) {
            textScanner.skipWhitespace();
            final int n8 = 108;
            Label_1560: {
                float n18 = 0.0f;
                float n19 = 0.0f;
                float n20 = 0.0f;
                float n21 = 0.0f;
                Label_1392: {
                    Label_0269: {
                        switch (n) {
                            default: {
                                return pathDefinition;
                            }
                            case 90:
                            case 122: {
                                pathDefinition.close();
                                n2 = (n3 = n6);
                                n4 = n7;
                                break;
                            }
                            case 86:
                            case 118: {
                                final float nextFloat = textScanner.nextFloat();
                                if (Float.isNaN(nextFloat)) {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("Bad path coords for ");
                                    sb.append((char)n);
                                    sb.append(" path segment");
                                    Log.e("SVGParser", sb.toString());
                                    return pathDefinition;
                                }
                                float n9 = nextFloat;
                                if (n == 118) {
                                    n9 = nextFloat + n4;
                                }
                                n4 = n9;
                                pathDefinition.lineTo(n2, n4);
                                break;
                            }
                            case 84:
                            case 116: {
                                final float n10 = n2 * 2.0f - n3;
                                final float n11 = 2.0f * n4 - n5;
                                final float nextFloat2 = textScanner.nextFloat();
                                final float checkedNextFloat = textScanner.checkedNextFloat(nextFloat2);
                                if (Float.isNaN(checkedNextFloat)) {
                                    final StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Bad path coords for ");
                                    sb2.append((char)n);
                                    sb2.append(" path segment");
                                    Log.e("SVGParser", sb2.toString());
                                    return pathDefinition;
                                }
                                float n12 = nextFloat2;
                                float n13 = checkedNextFloat;
                                if (n == 116) {
                                    n12 = nextFloat2 + n2;
                                    n13 = checkedNextFloat + n4;
                                }
                                n2 = n12;
                                n4 = n13;
                                pathDefinition.quadTo(n10, n11, n2, n4);
                                n3 = n10;
                                n5 = n11;
                                break Label_0269;
                            }
                            case 83:
                            case 115: {
                                final float nextFloat3 = textScanner.nextFloat();
                                final float checkedNextFloat2 = textScanner.checkedNextFloat(nextFloat3);
                                final float checkedNextFloat3 = textScanner.checkedNextFloat(checkedNextFloat2);
                                final float checkedNextFloat4 = textScanner.checkedNextFloat(checkedNextFloat3);
                                if (Float.isNaN(checkedNextFloat4)) {
                                    final StringBuilder sb3 = new StringBuilder();
                                    sb3.append("Bad path coords for ");
                                    sb3.append((char)n);
                                    sb3.append(" path segment");
                                    Log.e("SVGParser", sb3.toString());
                                    return pathDefinition;
                                }
                                float n14 = nextFloat3;
                                float n15 = checkedNextFloat2;
                                float n16 = checkedNextFloat3;
                                float n17 = checkedNextFloat4;
                                if (n == 115) {
                                    n16 = checkedNextFloat3 + n2;
                                    n17 = checkedNextFloat4 + n4;
                                    n14 = nextFloat3 + n2;
                                    n15 = checkedNextFloat2 + n4;
                                }
                                pathDefinition.cubicTo(n2 * 2.0f - n3, 2.0f * n4 - n5, n14, n15, n16, n17);
                                n18 = n14;
                                n19 = n16;
                                n20 = n15;
                                n21 = n17;
                                break Label_1392;
                            }
                            case 81:
                            case 113: {
                                final float nextFloat4 = textScanner.nextFloat();
                                final float checkedNextFloat5 = textScanner.checkedNextFloat(nextFloat4);
                                final float checkedNextFloat6 = textScanner.checkedNextFloat(checkedNextFloat5);
                                final float checkedNextFloat7 = textScanner.checkedNextFloat(checkedNextFloat6);
                                if (Float.isNaN(checkedNextFloat7)) {
                                    final StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Bad path coords for ");
                                    sb4.append((char)n);
                                    sb4.append(" path segment");
                                    Log.e("SVGParser", sb4.toString());
                                    return pathDefinition;
                                }
                                float n22 = nextFloat4;
                                n5 = checkedNextFloat5;
                                float n23 = checkedNextFloat6;
                                float n24 = checkedNextFloat7;
                                if (n == 113) {
                                    n23 = checkedNextFloat6 + n2;
                                    n24 = checkedNextFloat7 + n4;
                                    n22 = nextFloat4 + n2;
                                    n5 = checkedNextFloat5 + n4;
                                }
                                n2 = n23;
                                n4 = n24;
                                pathDefinition.quadTo(n22, n5, n2, n4);
                                n3 = n22;
                                break Label_0269;
                            }
                            case 77:
                            case 109: {
                                final float nextFloat5 = textScanner.nextFloat();
                                final float checkedNextFloat8 = textScanner.checkedNextFloat(nextFloat5);
                                if (Float.isNaN(checkedNextFloat8)) {
                                    final StringBuilder sb5 = new StringBuilder();
                                    sb5.append("Bad path coords for ");
                                    sb5.append((char)n);
                                    sb5.append(" path segment");
                                    Log.e("SVGParser", sb5.toString());
                                    return pathDefinition;
                                }
                                float n25 = nextFloat5;
                                float n26 = checkedNextFloat8;
                                if (n == 109) {
                                    n25 = nextFloat5;
                                    n26 = checkedNextFloat8;
                                    if (!pathDefinition.isEmpty()) {
                                        n25 = nextFloat5 + n2;
                                        n26 = checkedNextFloat8 + n4;
                                    }
                                }
                                n2 = n25;
                                n4 = n26;
                                pathDefinition.moveTo(n2, n4);
                                if (n == 109) {
                                    n = n8;
                                }
                                else {
                                    n = 76;
                                }
                                n3 = (n6 = n2);
                                n5 = (n7 = n4);
                                break Label_0269;
                            }
                            case 76:
                            case 108: {
                                final float nextFloat6 = textScanner.nextFloat();
                                final float checkedNextFloat9 = textScanner.checkedNextFloat(nextFloat6);
                                if (Float.isNaN(checkedNextFloat9)) {
                                    final StringBuilder sb6 = new StringBuilder();
                                    sb6.append("Bad path coords for ");
                                    sb6.append((char)n);
                                    sb6.append(" path segment");
                                    Log.e("SVGParser", sb6.toString());
                                    return pathDefinition;
                                }
                                float n27 = nextFloat6;
                                float n28 = checkedNextFloat9;
                                if (n == 108) {
                                    n27 = nextFloat6 + n2;
                                    n28 = checkedNextFloat9 + n4;
                                }
                                n2 = n27;
                                n4 = n28;
                                pathDefinition.lineTo(n2, n4);
                                n3 = n2;
                                break;
                            }
                            case 72:
                            case 104: {
                                final float nextFloat7 = textScanner.nextFloat();
                                if (Float.isNaN(nextFloat7)) {
                                    final StringBuilder sb7 = new StringBuilder();
                                    sb7.append("Bad path coords for ");
                                    sb7.append((char)n);
                                    sb7.append(" path segment");
                                    Log.e("SVGParser", sb7.toString());
                                    return pathDefinition;
                                }
                                float n29 = nextFloat7;
                                if (n == 104) {
                                    n29 = nextFloat7 + n2;
                                }
                                n2 = n29;
                                pathDefinition.lineTo(n2, n4);
                                n3 = n2;
                                break Label_0269;
                            }
                            case 67:
                            case 99: {
                                final float nextFloat8 = textScanner.nextFloat();
                                final float checkedNextFloat10 = textScanner.checkedNextFloat(nextFloat8);
                                final float checkedNextFloat11 = textScanner.checkedNextFloat(checkedNextFloat10);
                                final float checkedNextFloat12 = textScanner.checkedNextFloat(checkedNextFloat11);
                                final float checkedNextFloat13 = textScanner.checkedNextFloat(checkedNextFloat12);
                                final float checkedNextFloat14 = textScanner.checkedNextFloat(checkedNextFloat13);
                                if (Float.isNaN(checkedNextFloat14)) {
                                    final StringBuilder sb8 = new StringBuilder();
                                    sb8.append("Bad path coords for ");
                                    sb8.append((char)n);
                                    sb8.append(" path segment");
                                    Log.e("SVGParser", sb8.toString());
                                    return pathDefinition;
                                }
                                float n30 = nextFloat8;
                                float n31 = checkedNextFloat10;
                                float n32 = checkedNextFloat11;
                                float n33 = checkedNextFloat12;
                                n19 = checkedNextFloat13;
                                n21 = checkedNextFloat14;
                                if (n == 99) {
                                    n19 = checkedNextFloat13 + n2;
                                    n21 = checkedNextFloat14 + n4;
                                    n30 = nextFloat8 + n2;
                                    n31 = checkedNextFloat10 + n4;
                                    n32 = checkedNextFloat11 + n2;
                                    n33 = checkedNextFloat12 + n4;
                                }
                                n18 = n32;
                                n20 = n33;
                                pathDefinition.cubicTo(n30, n31, n18, n20, n19, n21);
                                break Label_1392;
                            }
                            case 65:
                            case 97: {
                                final float nextFloat9 = textScanner.nextFloat();
                                final float checkedNextFloat15 = textScanner.checkedNextFloat(nextFloat9);
                                final float checkedNextFloat16 = textScanner.checkedNextFloat(checkedNextFloat15);
                                final Boolean checkedNextFlag = textScanner.checkedNextFlag((Object)checkedNextFloat16);
                                final Boolean checkedNextFlag2 = textScanner.checkedNextFlag((Object)checkedNextFlag);
                                final float checkedNextFloat17 = textScanner.checkedNextFloat(checkedNextFlag2);
                                final float checkedNextFloat18 = textScanner.checkedNextFloat(checkedNextFloat17);
                                if (!Float.isNaN(checkedNextFloat18) && nextFloat9 >= 0.0f && checkedNextFloat15 >= 0.0f) {
                                    float n34 = checkedNextFloat17;
                                    float n35 = checkedNextFloat18;
                                    if (n == 97) {
                                        n34 = checkedNextFloat17 + n2;
                                        n35 = checkedNextFloat18 + n4;
                                    }
                                    pathDefinition.arcTo(nextFloat9, checkedNextFloat15, checkedNextFloat16, checkedNextFlag, checkedNextFlag2, n34, n35);
                                    final float n36;
                                    n2 = (n36 = n34);
                                    n5 = n35;
                                    n4 = n35;
                                    n3 = n36;
                                    break Label_1560;
                                }
                                final StringBuilder sb9 = new StringBuilder();
                                sb9.append("Bad path coords for ");
                                sb9.append((char)n);
                                sb9.append(" path segment");
                                Log.e("SVGParser", sb9.toString());
                                return pathDefinition;
                            }
                        }
                        n5 = n4;
                    }
                    break Label_1560;
                }
                final float n37 = n20;
                n2 = n19;
                n3 = n18;
                n4 = n21;
                n5 = n37;
            }
            textScanner.skipCommaWhitespace();
            if (textScanner.empty()) {
                return pathDefinition;
            }
            if (!textScanner.hasLetter()) {
                continue;
            }
            n = textScanner.nextChar();
        }
    }
    
    private static void parsePreserveAspectRatio(final SVGBase$SvgPreserveAspectRatioContainer svgBase$SvgPreserveAspectRatioContainer, final String s) throws SVGParseException {
        svgBase$SvgPreserveAspectRatioContainer.preserveAspectRatio = PreserveAspectRatio.of(s);
    }
    
    private Map<String, String> parseProcessingInstructionAttributes(final TextScanner textScanner) {
        final HashMap hashMap = new HashMap();
        textScanner.skipWhitespace();
        for (String s = textScanner.nextToken('='); s != null; s = textScanner.nextToken('=')) {
            textScanner.consume('=');
            hashMap.put((Object)s, (Object)textScanner.nextQuotedString());
            textScanner.skipWhitespace();
        }
        return (Map<String, String>)hashMap;
    }
    
    static Style$RenderQuality parseRenderQuality(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0076: {
            if (hashCode != -933002398) {
                if (hashCode != 3005871) {
                    if (hashCode == 362741610) {
                        if (s.equals((Object)"optimizeSpeed")) {
                            n = 2;
                            break Label_0076;
                        }
                    }
                }
                else if (s.equals((Object)"auto")) {
                    n = 0;
                    break Label_0076;
                }
            }
            else if (s.equals((Object)"optimizeQuality")) {
                n = 1;
                break Label_0076;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$RenderQuality.auto;
        }
        if (n == 1) {
            return Style$RenderQuality.optimizeQuality;
        }
        if (n != 2) {
            return null;
        }
        return Style$RenderQuality.optimizeSpeed;
    }
    
    private static Set<String> parseRequiredFeatures(String nextToken) {
        final TextScanner textScanner = new TextScanner(nextToken);
        final HashSet set = new HashSet();
        while (!textScanner.empty()) {
            nextToken = textScanner.nextToken();
            if (nextToken.startsWith("http://www.w3.org/TR/SVG11/feature#")) {
                set.add((Object)nextToken.substring(35));
            }
            else {
                set.add((Object)"UNSUPPORTED");
            }
            textScanner.skipWhitespace();
        }
        return (Set<String>)set;
    }
    
    private static Set<String> parseRequiredFormats(final String s) {
        final TextScanner textScanner = new TextScanner(s);
        final HashSet set = new HashSet();
        while (!textScanner.empty()) {
            set.add((Object)textScanner.nextToken());
            textScanner.skipWhitespace();
        }
        return (Set<String>)set;
    }
    
    static SVGBase$Length[] parseStrokeDashArray(final String s) {
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        if (textScanner.empty()) {
            return null;
        }
        final SVGBase$Length nextLength = textScanner.nextLength();
        if (nextLength == null) {
            return null;
        }
        if (nextLength.isNegative()) {
            return null;
        }
        float floatValue = nextLength.floatValue();
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)nextLength);
        while (!textScanner.empty()) {
            textScanner.skipCommaWhitespace();
            final SVGBase$Length nextLength2 = textScanner.nextLength();
            if (nextLength2 == null) {
                return null;
            }
            if (nextLength2.isNegative()) {
                return null;
            }
            ((List)list).add((Object)nextLength2);
            floatValue += nextLength2.floatValue();
        }
        if (floatValue == 0.0f) {
            return null;
        }
        return (SVGBase$Length[])((List)list).toArray((Object[])new SVGBase$Length[0]);
    }
    
    static Style$LineCap parseStrokeLineCap(final String s) {
        if ("butt".equals((Object)s)) {
            return Style$LineCap.Butt;
        }
        if ("round".equals((Object)s)) {
            return Style$LineCap.Round;
        }
        if ("square".equals((Object)s)) {
            return Style$LineCap.Square;
        }
        return null;
    }
    
    static Style$LineJoin parseStrokeLineJoin(final String s) {
        if ("miter".equals((Object)s)) {
            return Style$LineJoin.Miter;
        }
        if ("round".equals((Object)s)) {
            return Style$LineJoin.Round;
        }
        if ("bevel".equals((Object)s)) {
            return Style$LineJoin.Bevel;
        }
        return null;
    }
    
    private static void parseStyle(final SVGBase.SvgElementBase svgElementBase, String nextPropertyValue) {
        final CSSTextScanner cssTextScanner = new CSSTextScanner(SVGParserImpl.PATTERN_BLOCK_COMMENTS.matcher((CharSequence)nextPropertyValue).replaceAll(""));
        while (!cssTextScanner.empty()) {
            cssTextScanner.skipWhitespace();
            final String nextIdentifier = cssTextScanner.nextIdentifier();
            cssTextScanner.skipWhitespace();
            if (cssTextScanner.consume(';')) {
                continue;
            }
            if (!cssTextScanner.consume(':')) {
                break;
            }
            cssTextScanner.skipWhitespace();
            nextPropertyValue = cssTextScanner.nextPropertyValue();
            if (nextPropertyValue == null) {
                continue;
            }
            cssTextScanner.skipWhitespace();
            if (!cssTextScanner.empty() && !cssTextScanner.consume(';')) {
                continue;
            }
            if (svgElementBase.style == null) {
                svgElementBase.style = new Style();
            }
            Style.processStyleProperty(svgElementBase.style, nextIdentifier, nextPropertyValue, false);
            cssTextScanner.skipWhitespace();
        }
    }
    
    private static Set<String> parseSystemLanguage(String substring) {
        final TextScanner textScanner = new TextScanner(substring);
        final HashSet set = new HashSet();
        while (!textScanner.empty()) {
            final String nextToken = textScanner.nextToken();
            final int index = nextToken.indexOf(45);
            substring = nextToken;
            if (index != -1) {
                substring = nextToken.substring(0, index);
            }
            set.add((Object)new Locale(substring, "", "").getLanguage());
            textScanner.skipWhitespace();
        }
        return (Set<String>)set;
    }
    
    static Style$TextAnchor parseTextAnchor(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0076: {
            if (hashCode != -1074341483) {
                if (hashCode != 100571) {
                    if (hashCode == 109757538) {
                        if (s.equals((Object)"start")) {
                            n = 0;
                            break Label_0076;
                        }
                    }
                }
                else if (s.equals((Object)"end")) {
                    n = 2;
                    break Label_0076;
                }
            }
            else if (s.equals((Object)"middle")) {
                n = 1;
                break Label_0076;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$TextAnchor.Start;
        }
        if (n == 1) {
            return Style$TextAnchor.Middle;
        }
        if (n != 2) {
            return null;
        }
        return Style$TextAnchor.End;
    }
    
    static Style$TextDecoration parseTextDecoration(final String s) {
        int n = 0;
        Label_0135: {
            switch (s.hashCode()) {
                case 529818312: {
                    if (s.equals((Object)"overline")) {
                        n = 2;
                        break Label_0135;
                    }
                    break;
                }
                case 93826908: {
                    if (s.equals((Object)"blink")) {
                        n = 4;
                        break Label_0135;
                    }
                    break;
                }
                case 3387192: {
                    if (s.equals((Object)"none")) {
                        n = 0;
                        break Label_0135;
                    }
                    break;
                }
                case -1026963764: {
                    if (s.equals((Object)"underline")) {
                        n = 1;
                        break Label_0135;
                    }
                    break;
                }
                case -1171789332: {
                    if (s.equals((Object)"line-through")) {
                        n = 3;
                        break Label_0135;
                    }
                    break;
                }
            }
            n = -1;
        }
        if (n == 0) {
            return Style$TextDecoration.None;
        }
        if (n == 1) {
            return Style$TextDecoration.Underline;
        }
        if (n == 2) {
            return Style$TextDecoration.Overline;
        }
        if (n == 3) {
            return Style$TextDecoration.LineThrough;
        }
        if (n != 4) {
            return null;
        }
        return Style$TextDecoration.Blink;
    }
    
    static Style$TextDirection parseTextDirection(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0054: {
            if (hashCode != 107498) {
                if (hashCode == 113258) {
                    if (s.equals((Object)"rtl")) {
                        n = 1;
                        break Label_0054;
                    }
                }
            }
            else if (s.equals((Object)"ltr")) {
                n = 0;
                break Label_0054;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$TextDirection.LTR;
        }
        if (n != 1) {
            return null;
        }
        return Style$TextDirection.RTL;
    }
    
    private Matrix parseTransformList(final String s) throws SVGParseException {
        final Matrix matrix = new Matrix();
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        while (!textScanner.empty()) {
            final String nextFunction = textScanner.nextFunction();
            if (nextFunction == null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Bad transform function encountered in transform list: ");
                sb.append(s);
                throw new SVGParseException(sb.toString());
            }
            int n = -1;
            switch (nextFunction.hashCode()) {
                case 1052832078: {
                    if (nextFunction.equals((Object)"translate")) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 109493391: {
                    if (nextFunction.equals((Object)"skewY")) {
                        n = 5;
                        break;
                    }
                    break;
                }
                case 109493390: {
                    if (nextFunction.equals((Object)"skewX")) {
                        n = 4;
                        break;
                    }
                    break;
                }
                case 109250890: {
                    if (nextFunction.equals((Object)"scale")) {
                        n = 2;
                        break;
                    }
                    break;
                }
                case -925180581: {
                    if (nextFunction.equals((Object)"rotate")) {
                        n = 3;
                        break;
                    }
                    break;
                }
                case -1081239615: {
                    if (nextFunction.equals((Object)"matrix")) {
                        n = 0;
                        break;
                    }
                    break;
                }
            }
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (n != 3) {
                            if (n != 4) {
                                if (n != 5) {
                                    final StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Invalid transform list fn: ");
                                    sb2.append(nextFunction);
                                    sb2.append(")");
                                    throw new SVGParseException(sb2.toString());
                                }
                                textScanner.skipWhitespace();
                                final float nextFloat = textScanner.nextFloat();
                                textScanner.skipWhitespace();
                                if (Float.isNaN(nextFloat) || !textScanner.consume(')')) {
                                    final StringBuilder sb3 = new StringBuilder();
                                    sb3.append("Invalid transform list: ");
                                    sb3.append(s);
                                    throw new SVGParseException(sb3.toString());
                                }
                                matrix.preSkew(0.0f, (float)Math.tan(Math.toRadians((double)nextFloat)));
                            }
                            else {
                                textScanner.skipWhitespace();
                                final float nextFloat2 = textScanner.nextFloat();
                                textScanner.skipWhitespace();
                                if (Float.isNaN(nextFloat2) || !textScanner.consume(')')) {
                                    final StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Invalid transform list: ");
                                    sb4.append(s);
                                    throw new SVGParseException(sb4.toString());
                                }
                                matrix.preSkew((float)Math.tan(Math.toRadians((double)nextFloat2)), 0.0f);
                            }
                        }
                        else {
                            textScanner.skipWhitespace();
                            final float nextFloat3 = textScanner.nextFloat();
                            final float possibleNextFloat = textScanner.possibleNextFloat();
                            final float possibleNextFloat2 = textScanner.possibleNextFloat();
                            textScanner.skipWhitespace();
                            if (Float.isNaN(nextFloat3) || !textScanner.consume(')')) {
                                final StringBuilder sb5 = new StringBuilder();
                                sb5.append("Invalid transform list: ");
                                sb5.append(s);
                                throw new SVGParseException(sb5.toString());
                            }
                            if (Float.isNaN(possibleNextFloat)) {
                                matrix.preRotate(nextFloat3);
                            }
                            else {
                                if (Float.isNaN(possibleNextFloat2)) {
                                    final StringBuilder sb6 = new StringBuilder();
                                    sb6.append("Invalid transform list: ");
                                    sb6.append(s);
                                    throw new SVGParseException(sb6.toString());
                                }
                                matrix.preRotate(nextFloat3, possibleNextFloat, possibleNextFloat2);
                            }
                        }
                    }
                    else {
                        textScanner.skipWhitespace();
                        final float nextFloat4 = textScanner.nextFloat();
                        final float possibleNextFloat3 = textScanner.possibleNextFloat();
                        textScanner.skipWhitespace();
                        if (Float.isNaN(nextFloat4) || !textScanner.consume(')')) {
                            final StringBuilder sb7 = new StringBuilder();
                            sb7.append("Invalid transform list: ");
                            sb7.append(s);
                            throw new SVGParseException(sb7.toString());
                        }
                        if (Float.isNaN(possibleNextFloat3)) {
                            matrix.preScale(nextFloat4, nextFloat4);
                        }
                        else {
                            matrix.preScale(nextFloat4, possibleNextFloat3);
                        }
                    }
                }
                else {
                    textScanner.skipWhitespace();
                    final float nextFloat5 = textScanner.nextFloat();
                    final float possibleNextFloat4 = textScanner.possibleNextFloat();
                    textScanner.skipWhitespace();
                    if (Float.isNaN(nextFloat5) || !textScanner.consume(')')) {
                        final StringBuilder sb8 = new StringBuilder();
                        sb8.append("Invalid transform list: ");
                        sb8.append(s);
                        throw new SVGParseException(sb8.toString());
                    }
                    if (Float.isNaN(possibleNextFloat4)) {
                        matrix.preTranslate(nextFloat5, 0.0f);
                    }
                    else {
                        matrix.preTranslate(nextFloat5, possibleNextFloat4);
                    }
                }
            }
            else {
                textScanner.skipWhitespace();
                final float nextFloat6 = textScanner.nextFloat();
                textScanner.skipCommaWhitespace();
                final float nextFloat7 = textScanner.nextFloat();
                textScanner.skipCommaWhitespace();
                final float nextFloat8 = textScanner.nextFloat();
                textScanner.skipCommaWhitespace();
                final float nextFloat9 = textScanner.nextFloat();
                textScanner.skipCommaWhitespace();
                final float nextFloat10 = textScanner.nextFloat();
                textScanner.skipCommaWhitespace();
                final float nextFloat11 = textScanner.nextFloat();
                textScanner.skipWhitespace();
                if (Float.isNaN(nextFloat11) || !textScanner.consume(')')) {
                    final StringBuilder sb9 = new StringBuilder();
                    sb9.append("Invalid transform list: ");
                    sb9.append(s);
                    throw new SVGParseException(sb9.toString());
                }
                final Matrix matrix2 = new Matrix();
                matrix2.setValues(new float[] { nextFloat6, nextFloat8, nextFloat10, nextFloat7, nextFloat9, nextFloat11, 0.0f, 0.0f, 1.0f });
                matrix.preConcat(matrix2);
            }
            if (textScanner.empty()) {
                break;
            }
            textScanner.skipCommaWhitespace();
        }
        return matrix;
    }
    
    private void parseUsingSAX(final InputStream inputStream) throws SVGParseException {
        try {
            final SAXParserFactory instance = SAXParserFactory.newInstance();
            if (!SVGParserImpl.FORCE_SAX_ON_EARLY_ANDROIDS) {
                instance.setFeature("http://xml.org/sax/features/external-general-entities", false);
                instance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            }
            final XMLReader xmlReader = instance.newSAXParser().getXMLReader();
            final SVGParserImpl.SVGParserImpl$SAXHandler contentHandler = new SVGParserImpl.SVGParserImpl$SAXHandler(this, (SVGParserImpl$1)null);
            xmlReader.setContentHandler((ContentHandler)contentHandler);
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", (Object)contentHandler);
            xmlReader.parse(new InputSource(inputStream));
        }
        catch (final IOException ex) {
            throw new SVGParseException("Stream error", (Exception)ex);
        }
        catch (final SAXException ex2) {
            throw new SVGParseException("SVG parse error", (Exception)ex2);
        }
        catch (final ParserConfigurationException ex3) {
            throw new SVGParseException("XML parser problem", (Exception)ex3);
        }
    }
    
    private void parseUsingXmlPullParser(final InputStream inputStream) throws SVGParseException {
        try {
            final XmlPullParser pullParser = Xml.newPullParser();
            final SVGParserImpl.SVGParserImpl$XPPAttributesWrapper svgParserImpl$XPPAttributesWrapper = new SVGParserImpl.SVGParserImpl$XPPAttributesWrapper(pullParser);
            pullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
            pullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            pullParser.setInput(inputStream, (String)null);
            for (int i = pullParser.getEventType(); i != 1; i = pullParser.nextToken()) {
                if (i != 0) {
                    if (i != 8) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i != 5) {
                                        if (i == 6) {
                                            this.text(pullParser.getText());
                                        }
                                    }
                                    else {
                                        this.text(pullParser.getText());
                                    }
                                }
                                else {
                                    final int[] array = new int[2];
                                    this.text(pullParser.getTextCharacters(array), array[0], array[1]);
                                }
                            }
                            else {
                                String s = pullParser.getName();
                                if (pullParser.getPrefix() != null) {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append(pullParser.getPrefix());
                                    sb.append(':');
                                    sb.append(s);
                                    s = sb.toString();
                                }
                                this.endElement(pullParser.getNamespace(), pullParser.getName(), s);
                            }
                        }
                        else {
                            String s2 = pullParser.getName();
                            if (pullParser.getPrefix() != null) {
                                final StringBuilder sb2 = new StringBuilder();
                                sb2.append(pullParser.getPrefix());
                                sb2.append(':');
                                sb2.append(s2);
                                s2 = sb2.toString();
                            }
                            this.startElement(pullParser.getNamespace(), pullParser.getName(), s2, (Attributes)svgParserImpl$XPPAttributesWrapper);
                        }
                    }
                    else {
                        final TextScanner textScanner = new TextScanner(pullParser.getText());
                        this.handleProcessingInstruction(textScanner.nextToken(), this.parseProcessingInstructionAttributes(textScanner));
                    }
                }
                else {
                    this.startDocument();
                }
            }
            this.endDocument();
        }
        catch (final IOException ex) {
            throw new SVGParseException("Stream error", (Exception)ex);
        }
        catch (final XmlPullParserException ex2) {
            throw new SVGParseException("XML parser problem", (Exception)ex2);
        }
    }
    
    static Style$VectorEffect parseVectorEffect(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0053: {
            if (hashCode != 3387192) {
                if (hashCode == 1629199934) {
                    if (s.equals((Object)"non-scaling-stroke")) {
                        n = 1;
                        break Label_0053;
                    }
                }
            }
            else if (s.equals((Object)"none")) {
                n = 0;
                break Label_0053;
            }
            n = -1;
        }
        if (n == 0) {
            return Style$VectorEffect.None;
        }
        if (n != 1) {
            return null;
        }
        return Style$VectorEffect.NonScalingStroke;
    }
    
    private static SVGBase$Box parseViewBox(final String s) throws SVGParseException {
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        final float nextFloat = textScanner.nextFloat();
        textScanner.skipCommaWhitespace();
        final float nextFloat2 = textScanner.nextFloat();
        textScanner.skipCommaWhitespace();
        final float nextFloat3 = textScanner.nextFloat();
        textScanner.skipCommaWhitespace();
        final float nextFloat4 = textScanner.nextFloat();
        if (Float.isNaN(nextFloat) || Float.isNaN(nextFloat2) || Float.isNaN(nextFloat3) || Float.isNaN(nextFloat4)) {
            throw new SVGParseException("Invalid viewBox definition - should have four numbers");
        }
        if (nextFloat3 < 0.0f) {
            throw new SVGParseException("Invalid viewBox. width cannot be negative");
        }
        if (nextFloat4 >= 0.0f) {
            return new SVGBase$Box(nextFloat, nextFloat2, nextFloat3, nextFloat4);
        }
        throw new SVGParseException("Invalid viewBox. height cannot be negative");
    }
    
    private void path(final Attributes attributes) throws SVGParseException {
        this.debug("<path>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Path svgBase$Path = new SVGBase$Path();
            svgBase$Path.document = this.svgDocument;
            svgBase$Path.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Path, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Path, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Path, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Path, attributes);
            this.parseAttributesPath(svgBase$Path, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Path);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void pattern(final Attributes attributes) throws SVGParseException {
        this.debug("<pattern>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Pattern currentElement = new SVGBase$Pattern();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesViewBox((SVGBase$SvgViewBoxContainer)currentElement, attributes);
            this.parseAttributesPattern(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void polygon(final Attributes attributes) throws SVGParseException {
        this.debug("<polygon>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Polygon svgBase$Polygon = new SVGBase$Polygon();
            svgBase$Polygon.document = this.svgDocument;
            svgBase$Polygon.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Polygon, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Polygon, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Polygon, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Polygon, attributes);
            this.parseAttributesPolyLine((SVGBase$PolyLine)svgBase$Polygon, attributes, "polygon");
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Polygon);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void polyline(final Attributes attributes) throws SVGParseException {
        this.debug("<polyline>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$PolyLine svgBase$PolyLine = new SVGBase$PolyLine();
            svgBase$PolyLine.document = this.svgDocument;
            svgBase$PolyLine.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$PolyLine, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$PolyLine, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$PolyLine, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$PolyLine, attributes);
            this.parseAttributesPolyLine(svgBase$PolyLine, attributes, "polyline");
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$PolyLine);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void radialGradient(final Attributes attributes) throws SVGParseException {
        this.debug("<radialGradient>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$SvgRadialGradient currentElement = new SVGBase$SvgRadialGradient();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesGradient((SVGBase$GradientElement)currentElement, attributes);
            this.parseAttributesRadialGradient(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void rect(final Attributes attributes) throws SVGParseException {
        this.debug("<rect>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Rect svgBase$Rect = new SVGBase$Rect();
            svgBase$Rect.document = this.svgDocument;
            svgBase$Rect.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Rect, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Rect, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)svgBase$Rect, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Rect, attributes);
            this.parseAttributesRect(svgBase$Rect, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$Rect);
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void solidColor(final Attributes attributes) throws SVGParseException {
        this.debug("<solidColor>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$SolidColor currentElement = new SVGBase$SolidColor();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void startDocument() {
        this.svgDocument = new SVGBase(this.enableInternalEntities, this.externalFileResolver);
    }
    
    private void startElement(final String s, String s2, final String s3, final Attributes attributes) throws SVGParseException {
        if (this.ignoring) {
            ++this.ignoreDepth;
            return;
        }
        if (!"http://www.w3.org/2000/svg".equals((Object)s) && !"".equals((Object)s)) {
            return;
        }
        if (s2.length() <= 0) {
            s2 = s3;
        }
        final SVGParserImpl.SVGParserImpl$SVGElem fromString = SVGParserImpl.SVGParserImpl$SVGElem.fromString(s2);
        switch (SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGElem[fromString.ordinal()]) {
            default: {
                this.ignoring = true;
                this.ignoreDepth = 1;
                break;
            }
            case 31: {
                this.solidColor(attributes);
                break;
            }
            case 30: {
                this.style(attributes);
                break;
            }
            case 29: {
                this.mask(attributes);
                break;
            }
            case 28: {
                this.view(attributes);
                break;
            }
            case 27: {
                this.image(attributes);
                break;
            }
            case 26: {
                this.pattern(attributes);
                break;
            }
            case 25: {
                this.textPath(attributes);
                break;
            }
            case 24: {
                this.clipPath(attributes);
                break;
            }
            case 22:
            case 23: {
                this.inMetadataElement = true;
                this.metadataTag = fromString;
                break;
            }
            case 21: {
                this.stop(attributes);
                break;
            }
            case 20: {
                this.radialGradient(attributes);
                break;
            }
            case 19: {
                this.linearGradient(attributes);
                break;
            }
            case 18: {
                this.marker(attributes);
                break;
            }
            case 17: {
                this.symbol(attributes);
                break;
            }
            case 16: {
                this.zwitch(attributes);
                break;
            }
            case 15: {
                this.tref(attributes);
                break;
            }
            case 14: {
                this.tspan(attributes);
                break;
            }
            case 13: {
                this.text(attributes);
                break;
            }
            case 12: {
                this.polygon(attributes);
                break;
            }
            case 11: {
                this.polyline(attributes);
                break;
            }
            case 10: {
                this.line(attributes);
                break;
            }
            case 9: {
                this.ellipse(attributes);
                break;
            }
            case 8: {
                this.circle(attributes);
                break;
            }
            case 7: {
                this.rect(attributes);
                break;
            }
            case 6: {
                this.path(attributes);
                break;
            }
            case 5: {
                this.use(attributes);
                break;
            }
            case 4: {
                this.a(attributes);
                break;
            }
            case 3: {
                this.defs(attributes);
                break;
            }
            case 2: {
                this.g(attributes);
                break;
            }
            case 1: {
                this.svg(attributes);
                break;
            }
        }
    }
    
    private void stop(final Attributes attributes) throws SVGParseException {
        this.debug("<stop>", new Object[0]);
        final SVGBase$SvgContainer currentElement = this.currentElement;
        if (currentElement == null) {
            throw new SVGParseException("Invalid document. Root element must be <svg>");
        }
        if (currentElement instanceof SVGBase$GradientElement) {
            final SVGBase$Stop currentElement2 = new SVGBase$Stop();
            currentElement2.document = this.svgDocument;
            currentElement2.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement2, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement2, attributes);
            this.parseAttributesStop(currentElement2, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement2);
            this.currentElement = (SVGBase$SvgContainer)currentElement2;
            return;
        }
        throw new SVGParseException("Invalid document. <stop> elements are only valid inside <linearGradient> or <radialGradient> elements.");
    }
    
    private void style(final Attributes attributes) throws SVGParseException {
        int i = 0;
        this.debug("<style>", new Object[0]);
        if (this.currentElement != null) {
            String s = "all";
            boolean equals = true;
            while (i < attributes.getLength()) {
                final String trim = attributes.getValue(i).trim();
                final int n = SVGParserImpl$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$SVGParserImpl$SVGAttr[SVGParserImpl.SVGParserImpl$SVGAttr.fromString(attributes.getLocalName(i)).ordinal()];
                if (n != 48) {
                    if (n == 49) {
                        s = trim;
                    }
                }
                else {
                    equals = trim.equals((Object)"text/css");
                }
                ++i;
            }
            if (equals && CSSParser.mediaMatches(s, CSSParser$MediaType.screen)) {
                this.inStyleElement = true;
            }
            else {
                this.ignoring = true;
                this.ignoreDepth = 1;
            }
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void svg(final Attributes attributes) throws SVGParseException {
        this.debug("<svg>", new Object[0]);
        final SVGBase$Svg svgBase$Svg = new SVGBase$Svg();
        svgBase$Svg.document = this.svgDocument;
        svgBase$Svg.parent = this.currentElement;
        this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$Svg, attributes);
        this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$Svg, attributes);
        this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$Svg, attributes);
        this.parseAttributesViewBox((SVGBase$SvgViewBoxContainer)svgBase$Svg, attributes);
        this.parseAttributesSVG(svgBase$Svg, attributes);
        final SVGBase$SvgContainer currentElement = this.currentElement;
        if (currentElement == null) {
            this.svgDocument.setRootElement(svgBase$Svg);
        }
        else {
            currentElement.addChild((SVGBase$SvgObject)svgBase$Svg);
        }
        this.currentElement = (SVGBase$SvgContainer)svgBase$Svg;
    }
    
    private void symbol(final Attributes attributes) throws SVGParseException {
        this.debug("<symbol>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Symbol currentElement = new SVGBase$Symbol();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesViewBox((SVGBase$SvgViewBoxContainer)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void text(final String s) throws SVGParseException {
        if (this.ignoring) {
            return;
        }
        if (this.inMetadataElement) {
            if (this.metadataElementContents == null) {
                this.metadataElementContents = new StringBuilder(s.length());
            }
            this.metadataElementContents.append(s);
        }
        else if (this.inStyleElement) {
            if (this.styleElementContents == null) {
                this.styleElementContents = new StringBuilder(s.length());
            }
            this.styleElementContents.append(s);
        }
        else if (this.currentElement instanceof SVGBase$TextContainer) {
            this.appendToTextContainer(s);
        }
    }
    
    private void text(final Attributes attributes) throws SVGParseException {
        this.debug("<text>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Text currentElement = new SVGBase$Text();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesTextPosition((SVGBase$TextPositionedContainer)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void text(final char[] array, final int n, final int n2) throws SVGParseException {
        if (this.ignoring) {
            return;
        }
        if (this.inMetadataElement) {
            if (this.metadataElementContents == null) {
                this.metadataElementContents = new StringBuilder(n2);
            }
            this.metadataElementContents.append(array, n, n2);
        }
        else if (this.inStyleElement) {
            if (this.styleElementContents == null) {
                this.styleElementContents = new StringBuilder(n2);
            }
            this.styleElementContents.append(array, n, n2);
        }
        else if (this.currentElement instanceof SVGBase$TextContainer) {
            this.appendToTextContainer(new String(array, n, n2));
        }
    }
    
    private void textPath(final Attributes attributes) throws SVGParseException {
        this.debug("<textPath>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$TextPath currentElement = new SVGBase$TextPath();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesTextPath(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            if (currentElement.parent instanceof SVGBase$TextRoot) {
                currentElement.setTextRoot((SVGBase$TextRoot)currentElement.parent);
            }
            else {
                currentElement.setTextRoot(((SVGBase$TextChild)currentElement.parent).getTextRoot());
            }
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void tref(final Attributes attributes) throws SVGParseException {
        this.debug("<tref>", new Object[0]);
        final SVGBase$SvgContainer currentElement = this.currentElement;
        if (currentElement == null) {
            throw new SVGParseException("Invalid document. Root element must be <svg>");
        }
        if (currentElement instanceof SVGBase$TextContainer) {
            final SVGBase$TRef svgBase$TRef = new SVGBase$TRef();
            svgBase$TRef.document = this.svgDocument;
            svgBase$TRef.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)svgBase$TRef, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)svgBase$TRef, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)svgBase$TRef, attributes);
            this.parseAttributesTRef(svgBase$TRef, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)svgBase$TRef);
            if (svgBase$TRef.parent instanceof SVGBase$TextRoot) {
                svgBase$TRef.setTextRoot((SVGBase$TextRoot)svgBase$TRef.parent);
            }
            else {
                svgBase$TRef.setTextRoot(((SVGBase$TextChild)svgBase$TRef.parent).getTextRoot());
            }
            return;
        }
        throw new SVGParseException("Invalid document. <tref> elements are only valid inside <text> or <tspan> elements.");
    }
    
    private void tspan(final Attributes attributes) throws SVGParseException {
        this.debug("<tspan>", new Object[0]);
        final SVGBase$SvgContainer currentElement = this.currentElement;
        if (currentElement == null) {
            throw new SVGParseException("Invalid document. Root element must be <svg>");
        }
        if (currentElement instanceof SVGBase$TextContainer) {
            final SVGBase$TSpan currentElement2 = new SVGBase$TSpan();
            currentElement2.document = this.svgDocument;
            currentElement2.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement2, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement2, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement2, attributes);
            this.parseAttributesTextPosition((SVGBase$TextPositionedContainer)currentElement2, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement2);
            this.currentElement = (SVGBase$SvgContainer)currentElement2;
            if (currentElement2.parent instanceof SVGBase$TextRoot) {
                currentElement2.setTextRoot((SVGBase$TextRoot)currentElement2.parent);
            }
            else {
                currentElement2.setTextRoot(((SVGBase$TextChild)currentElement2.parent).getTextRoot());
            }
            return;
        }
        throw new SVGParseException("Invalid document. <tspan> elements are only valid inside <text> or other <tspan> elements.");
    }
    
    private void use(final Attributes attributes) throws SVGParseException {
        this.debug("<use>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Use currentElement = new SVGBase$Use();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesUse(currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void view(final Attributes attributes) throws SVGParseException {
        this.debug("<view>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$View currentElement = new SVGBase$View();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.parseAttributesViewBox((SVGBase$SvgViewBoxContainer)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    private void zwitch(final Attributes attributes) throws SVGParseException {
        this.debug("<switch>", new Object[0]);
        if (this.currentElement != null) {
            final SVGBase$Switch currentElement = new SVGBase$Switch();
            currentElement.document = this.svgDocument;
            currentElement.parent = this.currentElement;
            this.parseAttributesCore((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesStyle((SVGBase.SvgElementBase)currentElement, attributes);
            this.parseAttributesTransform((SVGBase$HasTransform)currentElement, attributes);
            this.parseAttributesConditional((SVGBase$SvgConditional)currentElement, attributes);
            this.currentElement.addChild((SVGBase$SvgObject)currentElement);
            this.currentElement = (SVGBase$SvgContainer)currentElement;
            return;
        }
        throw new SVGParseException("Invalid document. Root element must be <svg>");
    }
    
    public SVGBase parseStream(final InputStream p0) throws SVGParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_1        
        //     4: invokevirtual   java/io/InputStream.markSupported:()Z
        //     7: ifne            20
        //    10: new             Ljava/io/BufferedInputStream;
        //    13: dup            
        //    14: aload_1        
        //    15: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    18: astore          4
        //    20: aload           4
        //    22: iconst_3       
        //    23: invokevirtual   java/io/InputStream.mark:(I)V
        //    26: aload           4
        //    28: invokevirtual   java/io/InputStream.read:()I
        //    31: istore_3       
        //    32: aload           4
        //    34: invokevirtual   java/io/InputStream.read:()I
        //    37: istore_2       
        //    38: aload           4
        //    40: invokevirtual   java/io/InputStream.reset:()V
        //    43: aload           4
        //    45: astore_1       
        //    46: iload_3        
        //    47: iload_2        
        //    48: bipush          8
        //    50: ishl           
        //    51: iadd           
        //    52: ldc_w           35615
        //    55: if_icmpne       80
        //    58: new             Ljava/io/BufferedInputStream;
        //    61: astore_1       
        //    62: new             Ljava/util/zip/GZIPInputStream;
        //    65: astore          5
        //    67: aload           5
        //    69: aload           4
        //    71: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //    74: aload_1        
        //    75: aload           5
        //    77: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    80: getstatic       com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.FORCE_SAX_ON_EARLY_ANDROIDS:Z
        //    83: ifeq            128
        //    86: aload_0        
        //    87: ldc_w           "Forcing SAX parser for this version of Android"
        //    90: iconst_0       
        //    91: anewarray       Ljava/lang/Object;
        //    94: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    97: aload_0        
        //    98: aload_1        
        //    99: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.parseUsingSAX:(Ljava/io/InputStream;)V
        //   102: aload_0        
        //   103: getfield        com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.svgDocument:Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //   106: astore          4
        //   108: aload_1        
        //   109: invokevirtual   java/io/InputStream.close:()V
        //   112: goto            125
        //   115: astore_1       
        //   116: ldc             "SVGParser"
        //   118: ldc_w           "Exception thrown closing input stream"
        //   121: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   124: pop            
        //   125: aload           4
        //   127: areturn        
        //   128: aload_0        
        //   129: getfield        com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.enableInternalEntities:Z
        //   132: ifeq            227
        //   135: aload_1        
        //   136: sipush          4096
        //   139: invokevirtual   java/io/InputStream.mark:(I)V
        //   142: sipush          4096
        //   145: newarray        B
        //   147: astore          4
        //   149: aload_1        
        //   150: aload           4
        //   152: invokevirtual   java/io/InputStream.read:([B)I
        //   155: istore_2       
        //   156: new             Ljava/lang/String;
        //   159: astore          5
        //   161: aload           5
        //   163: aload           4
        //   165: iconst_0       
        //   166: iload_2        
        //   167: invokespecial   java/lang/String.<init>:([BII)V
        //   170: aload_1        
        //   171: invokevirtual   java/io/InputStream.reset:()V
        //   174: aload           5
        //   176: ldc_w           "<!ENTITY "
        //   179: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   182: ifeq            227
        //   185: aload_0        
        //   186: ldc_w           "Switching to SAX parser to process entities"
        //   189: iconst_0       
        //   190: anewarray       Ljava/lang/Object;
        //   193: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   196: aload_0        
        //   197: aload_1        
        //   198: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.parseUsingSAX:(Ljava/io/InputStream;)V
        //   201: aload_0        
        //   202: getfield        com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.svgDocument:Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //   205: astore          4
        //   207: aload_1        
        //   208: invokevirtual   java/io/InputStream.close:()V
        //   211: goto            224
        //   214: astore_1       
        //   215: ldc             "SVGParser"
        //   217: ldc_w           "Exception thrown closing input stream"
        //   220: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   223: pop            
        //   224: aload           4
        //   226: areturn        
        //   227: aload_0        
        //   228: aload_1        
        //   229: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.parseUsingXmlPullParser:(Ljava/io/InputStream;)V
        //   232: aload_0        
        //   233: getfield        com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.svgDocument:Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //   236: astore          4
        //   238: aload_1        
        //   239: invokevirtual   java/io/InputStream.close:()V
        //   242: goto            255
        //   245: astore_1       
        //   246: ldc             "SVGParser"
        //   248: ldc_w           "Exception thrown closing input stream"
        //   251: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   254: pop            
        //   255: aload           4
        //   257: areturn        
        //   258: astore          4
        //   260: goto            307
        //   263: astore          4
        //   265: ldc             "SVGParser"
        //   267: ldc_w           "Error occurred while performing check for entities.  File may not be parsed correctly if it contains entity definitions."
        //   270: aload           4
        //   272: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   275: pop            
        //   276: aload_0        
        //   277: aload_1        
        //   278: invokespecial   com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.parseUsingXmlPullParser:(Ljava/io/InputStream;)V
        //   281: aload_0        
        //   282: getfield        com/kingagroot/kingdraw/core/graphics/svg/utils/SVGParserImpl.svgDocument:Lcom/kingagroot/kingdraw/core/graphics/svg/utils/SVGBase;
        //   285: astore          4
        //   287: aload_1        
        //   288: invokevirtual   java/io/InputStream.close:()V
        //   291: goto            304
        //   294: astore_1       
        //   295: ldc             "SVGParser"
        //   297: ldc_w           "Exception thrown closing input stream"
        //   300: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   303: pop            
        //   304: aload           4
        //   306: areturn        
        //   307: aload_1        
        //   308: invokevirtual   java/io/InputStream.close:()V
        //   311: goto            324
        //   314: astore_1       
        //   315: ldc             "SVGParser"
        //   317: ldc_w           "Exception thrown closing input stream"
        //   320: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   323: pop            
        //   324: aload           4
        //   326: athrow         
        //   327: astore_1       
        //   328: aload           4
        //   330: astore_1       
        //   331: goto            80
        //    Exceptions:
        //  throws com.kingagroot.kingdraw.core.graphics.svg.SVGParseException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  20     43     327    334    Ljava/io/IOException;
        //  58     80     327    334    Ljava/io/IOException;
        //  80     108    263    307    Ljava/io/IOException;
        //  80     108    258    327    Any
        //  108    112    115    125    Ljava/io/IOException;
        //  128    207    263    307    Ljava/io/IOException;
        //  128    207    258    327    Any
        //  207    211    214    224    Ljava/io/IOException;
        //  227    238    263    307    Ljava/io/IOException;
        //  227    238    258    327    Any
        //  238    242    245    255    Ljava/io/IOException;
        //  265    287    258    327    Any
        //  287    291    294    304    Ljava/io/IOException;
        //  307    311    314    324    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 164, Size: 164
        //     at java.util.ArrayList.get(ArrayList.java:437)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:714)
        //     at q5.g.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:284)
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
    
    public SVGParser setExternalFileResolver(final SVGExternalFileResolver externalFileResolver) {
        this.externalFileResolver = externalFileResolver;
        return (SVGParser)this;
    }
    
    public SVGParser setInternalEntitiesEnabled(final boolean enableInternalEntities) {
        this.enableInternalEntities = enableInternalEntities;
        return (SVGParser)this;
    }
}
