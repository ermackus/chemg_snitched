package org.ccil.cowan.tagsoup;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.Locator;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.util.HashMap;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler implements ScanHandler, XMLReader, LexicalHandler
{
    public static final String CDATAElementsFeature = "http://www.ccil.org/~cowan/tagsoup/features/cdata-elements";
    private static boolean DEFAULT_BOGONS_EMPTY = false;
    private static boolean DEFAULT_CDATA_ELEMENTS = true;
    private static boolean DEFAULT_DEFAULT_ATTRIBUTES = true;
    private static boolean DEFAULT_IGNORABLE_WHITESPACE = false;
    private static boolean DEFAULT_IGNORE_BOGONS = false;
    private static boolean DEFAULT_NAMESPACES = true;
    private static boolean DEFAULT_RESTART_ELEMENTS = true;
    private static boolean DEFAULT_ROOT_BOGONS = true;
    private static boolean DEFAULT_TRANSLATE_COLONS = false;
    public static final String XML11Feature = "http://xml.org/sax/features/xml-1.1";
    public static final String autoDetectorProperty = "http://www.ccil.org/~cowan/tagsoup/properties/auto-detector";
    public static final String bogonsEmptyFeature = "http://www.ccil.org/~cowan/tagsoup/features/bogons-empty";
    public static final String defaultAttributesFeature = "http://www.ccil.org/~cowan/tagsoup/features/default-attributes";
    private static char[] etagchars;
    public static final String externalGeneralEntitiesFeature = "http://xml.org/sax/features/external-general-entities";
    public static final String externalParameterEntitiesFeature = "http://xml.org/sax/features/external-parameter-entities";
    public static final String ignorableWhitespaceFeature = "http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace";
    public static final String ignoreBogonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons";
    public static final String isStandaloneFeature = "http://xml.org/sax/features/is-standalone";
    private static String legal;
    public static final String lexicalHandlerParameterEntitiesFeature = "http://xml.org/sax/features/lexical-handler/parameter-entities";
    public static final String lexicalHandlerProperty = "http://xml.org/sax/properties/lexical-handler";
    public static final String namespacePrefixesFeature = "http://xml.org/sax/features/namespace-prefixes";
    public static final String namespacesFeature = "http://xml.org/sax/features/namespaces";
    public static final String resolveDTDURIsFeature = "http://xml.org/sax/features/resolve-dtd-uris";
    public static final String restartElementsFeature = "http://www.ccil.org/~cowan/tagsoup/features/restart-elements";
    public static final String rootBogonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/root-bogons";
    public static final String scannerProperty = "http://www.ccil.org/~cowan/tagsoup/properties/scanner";
    public static final String schemaProperty = "http://www.ccil.org/~cowan/tagsoup/properties/schema";
    public static final String stringInterningFeature = "http://xml.org/sax/features/string-interning";
    public static final String translateColonsFeature = "http://www.ccil.org/~cowan/tagsoup/features/translate-colons";
    public static final String unicodeNormalizationCheckingFeature = "http://xml.org/sax/features/unicode-normalization-checking";
    public static final String useAttributes2Feature = "http://xml.org/sax/features/use-attributes2";
    public static final String useEntityResolver2Feature = "http://xml.org/sax/features/use-entity-resolver2";
    public static final String useLocator2Feature = "http://xml.org/sax/features/use-locator2";
    public static final String validationFeature = "http://xml.org/sax/features/validation";
    public static final String xmlnsURIsFeature = "http://xml.org/sax/features/xmlns-uris";
    private boolean CDATAElements;
    private boolean bogonsEmpty;
    private boolean defaultAttributes;
    private boolean ignorableWhitespace;
    private boolean ignoreBogons;
    private boolean namespaces;
    private boolean restartElements;
    private boolean rootBogons;
    private String theAttributeName;
    private AutoDetector theAutoDetector;
    private char[] theCommentBuffer;
    private ContentHandler theContentHandler;
    private DTDHandler theDTDHandler;
    private boolean theDoctypeIsPresent;
    private String theDoctypeName;
    private String theDoctypePublicId;
    private String theDoctypeSystemId;
    private int theEntity;
    private EntityResolver theEntityResolver;
    private ErrorHandler theErrorHandler;
    private HashMap theFeatures;
    private LexicalHandler theLexicalHandler;
    private Element theNewElement;
    private Element thePCDATA;
    private String thePITarget;
    private Element theSaved;
    private Scanner theScanner;
    private Schema theSchema;
    private Element theStack;
    private boolean translateColons;
    private boolean virginStack;
    
    static {
        Parser.etagchars = new char[] { '<', '/', '>' };
        Parser.legal = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-'()+,./:=?;!*#@$_%";
    }
    
    public Parser() {
        this.theContentHandler = (ContentHandler)this;
        this.theLexicalHandler = (LexicalHandler)this;
        this.theDTDHandler = (DTDHandler)this;
        this.theErrorHandler = (ErrorHandler)this;
        this.theEntityResolver = (EntityResolver)this;
        this.namespaces = Parser.DEFAULT_NAMESPACES;
        this.ignoreBogons = Parser.DEFAULT_IGNORE_BOGONS;
        this.bogonsEmpty = Parser.DEFAULT_BOGONS_EMPTY;
        this.rootBogons = Parser.DEFAULT_ROOT_BOGONS;
        this.defaultAttributes = Parser.DEFAULT_DEFAULT_ATTRIBUTES;
        this.translateColons = Parser.DEFAULT_TRANSLATE_COLONS;
        this.restartElements = Parser.DEFAULT_RESTART_ELEMENTS;
        this.ignorableWhitespace = Parser.DEFAULT_IGNORABLE_WHITESPACE;
        this.CDATAElements = Parser.DEFAULT_CDATA_ELEMENTS;
        (this.theFeatures = new HashMap()).put((Object)"http://xml.org/sax/features/namespaces", (Object)truthValue(Parser.DEFAULT_NAMESPACES));
        this.theFeatures.put((Object)"http://xml.org/sax/features/namespace-prefixes", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/external-general-entities", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/external-parameter-entities", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/is-standalone", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/lexical-handler/parameter-entities", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/resolve-dtd-uris", (Object)Boolean.TRUE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/string-interning", (Object)Boolean.TRUE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/use-attributes2", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/use-locator2", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/use-entity-resolver2", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/validation", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/xmlns-uris", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/xmlns-uris", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://xml.org/sax/features/xml-1.1", (Object)Boolean.FALSE);
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons", (Object)truthValue(Parser.DEFAULT_IGNORE_BOGONS));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", (Object)truthValue(Parser.DEFAULT_BOGONS_EMPTY));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/root-bogons", (Object)truthValue(Parser.DEFAULT_ROOT_BOGONS));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/default-attributes", (Object)truthValue(Parser.DEFAULT_DEFAULT_ATTRIBUTES));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/translate-colons", (Object)truthValue(Parser.DEFAULT_TRANSLATE_COLONS));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/restart-elements", (Object)truthValue(Parser.DEFAULT_RESTART_ELEMENTS));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace", (Object)truthValue(Parser.DEFAULT_IGNORABLE_WHITESPACE));
        this.theFeatures.put((Object)"http://www.ccil.org/~cowan/tagsoup/features/cdata-elements", (Object)truthValue(Parser.DEFAULT_CDATA_ELEMENTS));
        this.theNewElement = null;
        this.theAttributeName = null;
        this.theDoctypeIsPresent = false;
        this.theDoctypePublicId = null;
        this.theDoctypeSystemId = null;
        this.theDoctypeName = null;
        this.thePITarget = null;
        this.theStack = null;
        this.theSaved = null;
        this.thePCDATA = null;
        this.theEntity = 0;
        this.virginStack = true;
        this.theCommentBuffer = new char[2000];
    }
    
    private String cleanPublicid(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        int i = 0;
        int n = 1;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (Parser.legal.indexOf((int)char1) != -1) {
                sb.append(char1);
                n = 0;
            }
            else if (n == 0) {
                sb.append(' ');
                n = 1;
            }
            ++i;
        }
        return sb.toString().trim();
    }
    
    private String expandEntities(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        int i = 0;
        int n = 0;
        int n2 = -1;
        while (i < length) {
            final char char1 = s.charAt(i);
            int n3 = n + 1;
            array[n] = char1;
            if (char1 == '&' && n2 == -1) {
                n2 = n3;
            }
            else if (n2 != -1) {
                if (!Character.isLetter(char1) && !Character.isDigit(char1)) {
                    if (char1 != '#') {
                        if (char1 == ';') {
                            final int lookupEntity = this.lookupEntity(array, n2, n3 - n2 - 1);
                            if (lookupEntity > 65535) {
                                final int n4 = lookupEntity - 65536;
                                array[n2 - 1] = (char)((n4 >> 10) + 55296);
                                array[n2] = (char)((n4 & 0x3FF) + 56320);
                                n3 = n2 + 1;
                            }
                            else if (lookupEntity != 0) {
                                array[n2 - 1] = (char)lookupEntity;
                                n3 = n2;
                            }
                        }
                        n2 = -1;
                    }
                }
            }
            ++i;
            n = n3;
        }
        return new String(array, 0, n);
    }
    
    private boolean foreign(final String s, final String s2) {
        return !s.equals((Object)"") && !s2.equals((Object)"") && !s2.equals((Object)this.theSchema.getURI());
    }
    
    private InputStream getInputStream(final String s, final String s2) throws IOException, SAXException {
        final StringBuffer sb = new StringBuffer();
        sb.append(System.getProperty("user.dir"));
        sb.append("/.");
        return new URL(new URL("file", "", sb.toString()), s2).openConnection().getInputStream();
    }
    
    private Reader getReader(InputSource inputSource) throws SAXException, IOException {
        final Object characterStream = inputSource.getCharacterStream();
        final Object byteStream = inputSource.getByteStream();
        final String encoding = inputSource.getEncoding();
        final String publicId = inputSource.getPublicId();
        final String systemId = inputSource.getSystemId();
        inputSource = (InputSource)characterStream;
        if (characterStream == null) {
            if ((inputSource = (InputSource)byteStream) == null) {
                inputSource = (InputSource)this.getInputStream(publicId, systemId);
            }
            if (encoding == null) {
                inputSource = (InputSource)this.theAutoDetector.autoDetectingReader((InputStream)inputSource);
            }
            else {
                try {
                    inputSource = (InputSource)new InputStreamReader((InputStream)inputSource, encoding);
                }
                catch (final UnsupportedEncodingException ex) {
                    inputSource = (InputSource)new InputStreamReader((InputStream)inputSource);
                }
            }
        }
        return (Reader)inputSource;
    }
    
    private int lookupEntity(final char[] array, int n, final int n2) {
        if (n2 < 1) {
            return 0;
        }
        if (array[n] == '#') {
            Label_0073: {
                if (n2 > 1) {
                    final int n3 = n + 1;
                    if (array[n3] != 'x') {
                        if (array[n3] != 'X') {
                            break Label_0073;
                        }
                    }
                    try {
                        n = Integer.parseInt(new String(array, n + 2, n2 - 2), 16);
                        return n;
                    }
                    catch (final NumberFormatException ex) {
                        return 0;
                    }
                }
                try {
                    n = Integer.parseInt(new String(array, n + 1, n2 - 1), 10);
                    return n;
                }
                catch (final NumberFormatException ex2) {
                    return 0;
                }
            }
        }
        return this.theSchema.getEntity(new String(array, n, n2));
    }
    
    private String makeName(final char[] array, int length, int n) {
        final StringBuffer sb = new StringBuffer(n + 2);
        int n2 = 1;
        int n3 = 0;
        int n4 = length;
        while (true) {
            char c = '_';
            if (n <= 0) {
                break;
            }
            final char c2 = array[n4];
            int n5 = 0;
            Label_0185: {
                if (!Character.isLetter(c2) && c2 != '_') {
                    if (!Character.isDigit(c2) && c2 != '-' && c2 != '.') {
                        length = n2;
                        n5 = n3;
                        if (c2 != ':') {
                            break Label_0185;
                        }
                        length = n2;
                        if ((n5 = n3) == 0) {
                            if (n2 != 0) {
                                sb.append('_');
                            }
                            if (!this.translateColons) {
                                c = c2;
                            }
                            sb.append(c);
                            length = 1;
                            n5 = 1;
                        }
                        break Label_0185;
                    }
                    else {
                        if (n2 != 0) {
                            sb.append('_');
                        }
                        sb.append(c2);
                    }
                }
                else {
                    sb.append(c2);
                }
                length = 0;
                n5 = n3;
            }
            ++n4;
            --n;
            n2 = length;
            n3 = n5;
        }
        length = sb.length();
        if (length == 0 || sb.charAt(length - 1) == ':') {
            sb.append('_');
        }
        return sb.toString().intern();
    }
    
    private void pop() throws SAXException {
        final Element theStack = this.theStack;
        if (theStack == null) {
            return;
        }
        final String name = theStack.name();
        String localName = this.theStack.localName();
        String namespace = this.theStack.namespace();
        final String prefix = this.prefixOf(name);
        if (!this.namespaces) {
            localName = "";
            namespace = "";
        }
        this.theContentHandler.endElement(namespace, localName, name);
        if (this.foreign(prefix, namespace)) {
            this.theContentHandler.endPrefixMapping(prefix);
        }
        final AttributesImpl atts = this.theStack.atts();
        for (int i = ((Attributes)atts).getLength() - 1; i >= 0; --i) {
            final String uri = ((Attributes)atts).getURI(i);
            final String prefix2 = this.prefixOf(((Attributes)atts).getQName(i));
            if (this.foreign(prefix2, uri)) {
                this.theContentHandler.endPrefixMapping(prefix2);
            }
        }
        this.theStack = this.theStack.next();
    }
    
    private String prefixOf(String substring) {
        final int index = substring.indexOf(58);
        if (index != -1) {
            substring = substring.substring(0, index);
        }
        else {
            substring = "";
        }
        return substring;
    }
    
    private void push(final Element theStack) throws SAXException {
        final String name = theStack.name();
        String localName = theStack.localName();
        String namespace = theStack.namespace();
        final String prefix = this.prefixOf(name);
        theStack.clean();
        if (!this.namespaces) {
            localName = "";
            namespace = "";
        }
        if (this.virginStack && localName.equalsIgnoreCase(this.theDoctypeName)) {
            try {
                this.theEntityResolver.resolveEntity(this.theDoctypePublicId, this.theDoctypeSystemId);
            }
            catch (final IOException ex) {}
        }
        if (this.foreign(prefix, namespace)) {
            this.theContentHandler.startPrefixMapping(prefix, namespace);
        }
        final AttributesImpl atts = theStack.atts();
        for (int length = ((Attributes)atts).getLength(), i = 0; i < length; ++i) {
            final String uri = ((Attributes)atts).getURI(i);
            final String prefix2 = this.prefixOf(((Attributes)atts).getQName(i));
            if (this.foreign(prefix2, uri)) {
                this.theContentHandler.startPrefixMapping(prefix2, uri);
            }
        }
        this.theContentHandler.startElement(namespace, localName, name, (Attributes)theStack.atts());
        theStack.setNext(this.theStack);
        this.theStack = theStack;
        this.virginStack = false;
        if (this.CDATAElements && (theStack.flags() & 0x2) != 0x0) {
            this.theScanner.startCDATA();
        }
    }
    
    private void rectify(Element next) throws SAXException {
        Element element;
        while (true) {
            for (element = this.theStack; element != null && !element.canContain((Element)next); element = element.next()) {}
            if (element != null) {
                break;
            }
            final ElementType parent = ((Element)next).parent();
            if (parent == null) {
                break;
            }
            final Element element2 = new Element(parent, this.defaultAttributes);
            element2.setNext((Element)next);
            next = element2;
        }
        if (element == null) {
            return;
        }
        Object o;
        while (true) {
            final Element theStack = this.theStack;
            o = next;
            if (theStack == element) {
                break;
            }
            o = next;
            if (theStack == null) {
                break;
            }
            o = next;
            if (theStack.next() == null) {
                break;
            }
            if (this.theStack.next().next() == null) {
                o = next;
                break;
            }
            this.restartablyPop();
        }
        while (o != null) {
            next = ((Element)o).next();
            if (!((Element)o).name().equals((Object)"<pcdata>")) {
                this.push((Element)o);
            }
            this.restart((Element)next);
            o = next;
        }
        this.theNewElement = null;
    }
    
    private void restart(final Element element) throws SAXException {
        while (true) {
            final Element theSaved = this.theSaved;
            if (theSaved == null || !this.theStack.canContain(theSaved) || (element != null && !this.theSaved.canContain(element))) {
                break;
            }
            final Element next = this.theSaved.next();
            this.push(this.theSaved);
            this.theSaved = next;
        }
    }
    
    private void restartablyPop() throws SAXException {
        final Element theStack = this.theStack;
        this.pop();
        if (this.restartElements && (theStack.flags() & 0x1) != 0x0) {
            theStack.anonymize();
            theStack.setNext(this.theSaved);
            this.theSaved = theStack;
        }
    }
    
    private void setup() {
        if (this.theSchema == null) {
            this.theSchema = new HTMLSchema();
        }
        if (this.theScanner == null) {
            this.theScanner = new HTMLScanner();
        }
        if (this.theAutoDetector == null) {
            this.theAutoDetector = new Parser$1(this);
        }
        this.theStack = new Element(this.theSchema.getElementType("<root>"), this.defaultAttributes);
        this.thePCDATA = new Element(this.theSchema.getElementType("<pcdata>"), this.defaultAttributes);
        this.theNewElement = null;
        this.theAttributeName = null;
        this.thePITarget = null;
        this.theSaved = null;
        this.theEntity = 0;
        this.virginStack = true;
        this.theDoctypeSystemId = null;
        this.theDoctypePublicId = null;
        this.theDoctypeName = null;
    }
    
    private static String[] split(final String s) throws IllegalArgumentException {
        final String trim = s.trim();
        if (trim.length() == 0) {
            return new String[0];
        }
        final ArrayList list = new ArrayList();
        final int length = trim.length();
        int i = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        while (i < length) {
            final char char1 = trim.charAt(i);
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            Label_0258: {
                if (n2 == 0 && char1 == '\'' && n3 != 92) {
                    n4 ^= 0x1;
                    n5 = n;
                    n6 = n2;
                    n7 = n4;
                    if (n >= 0) {
                        break Label_0258;
                    }
                }
                else if (n4 == 0 && char1 == '\"' && n3 != 92) {
                    n2 ^= 0x1;
                    n5 = n;
                    n6 = n2;
                    n7 = n4;
                    if (n >= 0) {
                        break Label_0258;
                    }
                }
                else {
                    n5 = n;
                    n6 = n2;
                    if ((n7 = n4) != 0) {
                        break Label_0258;
                    }
                    n5 = n;
                    n6 = n2;
                    n7 = n4;
                    if (n2 != 0) {
                        break Label_0258;
                    }
                    if (Character.isWhitespace(char1)) {
                        if (n >= 0) {
                            list.add((Object)trim.substring(n, i));
                        }
                        n5 = -1;
                        n6 = n2;
                        n7 = n4;
                        break Label_0258;
                    }
                    n5 = n;
                    n6 = n2;
                    n7 = n4;
                    if (n >= 0) {
                        break Label_0258;
                    }
                    n5 = n;
                    n6 = n2;
                    n7 = n4;
                    if (char1 == ' ') {
                        break Label_0258;
                    }
                }
                n5 = i;
                n6 = n2;
                n7 = n4;
            }
            ++i;
            final char c = char1;
            n = n5;
            n2 = n6;
            n3 = c;
            n4 = n7;
        }
        list.add((Object)trim.substring(n, i));
        return (String[])list.toArray((Object[])new String[0]);
    }
    
    private static String trimquotes(final String s) {
        if (s == null) {
            return s;
        }
        final int length = s.length();
        if (length == 0) {
            return s;
        }
        final char char1 = s.charAt(0);
        String substring = s;
        if (char1 == s.charAt(length - 1)) {
            if (char1 != '\'') {
                substring = s;
                if (char1 != '\"') {
                    return substring;
                }
            }
            substring = s.substring(1, s.length() - 1);
        }
        return substring;
    }
    
    private static Boolean truthValue(final boolean b) {
        Boolean b2;
        if (b) {
            b2 = Boolean.TRUE;
        }
        else {
            b2 = Boolean.FALSE;
        }
        return b2;
    }
    
    public void adup(final char[] array, final int n, final int n2) throws SAXException {
        final Element theNewElement = this.theNewElement;
        if (theNewElement != null) {
            final String theAttributeName = this.theAttributeName;
            if (theAttributeName != null) {
                theNewElement.setAttribute(theAttributeName, null, theAttributeName);
                this.theAttributeName = null;
            }
        }
    }
    
    public void aname(final char[] array, final int n, final int n2) throws SAXException {
        if (this.theNewElement == null) {
            return;
        }
        this.theAttributeName = this.makeName(array, n, n2).toLowerCase();
    }
    
    public void aval(final char[] array, final int n, final int n2) throws SAXException {
        if (this.theNewElement != null) {
            if (this.theAttributeName != null) {
                this.theNewElement.setAttribute(this.theAttributeName, null, this.expandEntities(new String(array, n, n2)));
                this.theAttributeName = null;
            }
        }
    }
    
    public void cdsect(final char[] array, final int n, final int n2) throws SAXException {
        this.theLexicalHandler.startCDATA();
        this.pcdata(array, n, n2);
        this.theLexicalHandler.endCDATA();
    }
    
    public void cmnt(final char[] array, final int n, final int n2) throws SAXException {
        this.theLexicalHandler.comment(array, n, n2);
    }
    
    public void comment(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void decl(final char[] array, int length, final int n) throws SAXException {
        final String[] split = split(new String(array, length, n));
        length = split.length;
        String s = null;
        String theDoctypeName = null;
        String s2 = null;
        Label_0138: {
            if (length > 0 && "DOCTYPE".equalsIgnoreCase(split[0])) {
                if (this.theDoctypeIsPresent) {
                    return;
                }
                this.theDoctypeIsPresent = true;
                if (split.length > 1) {
                    theDoctypeName = split[1];
                    if (split.length > 3 && "SYSTEM".equals((Object)split[2])) {
                        s2 = split[3];
                        break Label_0138;
                    }
                    if (split.length <= 3 || !"PUBLIC".equals((Object)split[2])) {
                        s2 = null;
                        break Label_0138;
                    }
                    s = split[3];
                    if (split.length > 4) {
                        s2 = split[4];
                        break Label_0138;
                    }
                    s2 = "";
                    break Label_0138;
                }
            }
            s2 = null;
            theDoctypeName = null;
        }
        final String trimquotes = trimquotes(s);
        final String trimquotes2 = trimquotes(s2);
        if (theDoctypeName == null) {
            return;
        }
        final String cleanPublicid = this.cleanPublicid(trimquotes);
        this.theLexicalHandler.startDTD(theDoctypeName, cleanPublicid, trimquotes2);
        this.theLexicalHandler.endDTD();
        this.theDoctypeName = theDoctypeName;
        this.theDoctypePublicId = cleanPublicid;
        final Scanner theScanner = this.theScanner;
        if (!(theScanner instanceof Locator)) {
            return;
        }
        this.theDoctypeSystemId = ((Locator)theScanner).getSystemId();
        try {
            this.theDoctypeSystemId = new URL(new URL(this.theDoctypeSystemId), trimquotes2).toString();
        }
        catch (final Exception ex) {}
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endEntity(final String s) throws SAXException {
    }
    
    public void entity(final char[] array, final int n, final int n2) throws SAXException {
        this.theEntity = this.lookupEntity(array, n, n2);
    }
    
    public void eof(final char[] array, final int n, final int n2) throws SAXException {
        if (this.virginStack) {
            this.rectify(this.thePCDATA);
        }
        while (this.theStack.next() != null) {
            this.pop();
        }
        if (!this.theSchema.getURI().equals((Object)"")) {
            this.theContentHandler.endPrefixMapping(this.theSchema.getPrefix());
        }
        this.theContentHandler.endDocument();
    }
    
    public void etag(final char[] array, final int n, final int n2) throws SAXException {
        if (this.etag_cdata(array, n, n2)) {
            return;
        }
        this.etag_basic(array, n, n2);
    }
    
    public void etag_basic(final char[] array, int n, final int n2) throws SAXException {
        this.theNewElement = null;
        String s;
        if (n2 != 0) {
            final ElementType elementType = this.theSchema.getElementType(this.makeName(array, n, n2));
            if (elementType == null) {
                return;
            }
            s = elementType.name();
        }
        else {
            s = this.theStack.name();
        }
        n = 0;
        Element element;
        for (element = this.theStack; element != null && !element.name().equals((Object)s); element = element.next()) {
            if ((element.flags() & 0x4) != 0x0) {
                n = 1;
            }
        }
        if (element == null) {
            return;
        }
        if (element.next() != null) {
            if (element.next().next() != null) {
                if (n != 0) {
                    element.preclose();
                }
                else {
                    while (this.theStack != element) {
                        this.restartablyPop();
                    }
                    this.pop();
                }
                while (this.theStack.isPreclosed()) {
                    this.pop();
                }
                this.restart(null);
            }
        }
    }
    
    public boolean etag_cdata(final char[] array, final int n, final int n2) throws SAXException {
        final String name = this.theStack.name();
        if (this.CDATAElements && (this.theStack.flags() & 0x2) != 0x0) {
            boolean b2;
            final boolean b = b2 = (n2 == name.length());
            if (b) {
                int n3 = 0;
                while (true) {
                    b2 = b;
                    if (n3 >= n2) {
                        break;
                    }
                    if (Character.toLowerCase(array[n + n3]) != Character.toLowerCase(name.charAt(n3))) {
                        b2 = false;
                        break;
                    }
                    ++n3;
                }
            }
            if (!b2) {
                this.theContentHandler.characters(Parser.etagchars, 0, 2);
                this.theContentHandler.characters(array, n, n2);
                this.theContentHandler.characters(Parser.etagchars, 2, 1);
                this.theScanner.startCDATA();
                return true;
            }
        }
        return false;
    }
    
    public ContentHandler getContentHandler() {
        ContentHandler theContentHandler;
        if ((theContentHandler = this.theContentHandler) == this) {
            theContentHandler = null;
        }
        return theContentHandler;
    }
    
    public DTDHandler getDTDHandler() {
        DTDHandler theDTDHandler;
        if ((theDTDHandler = this.theDTDHandler) == this) {
            theDTDHandler = null;
        }
        return theDTDHandler;
    }
    
    public int getEntity() {
        return this.theEntity;
    }
    
    public EntityResolver getEntityResolver() {
        EntityResolver theEntityResolver;
        if ((theEntityResolver = this.theEntityResolver) == this) {
            theEntityResolver = null;
        }
        return theEntityResolver;
    }
    
    public ErrorHandler getErrorHandler() {
        ErrorHandler theErrorHandler;
        if ((theErrorHandler = this.theErrorHandler) == this) {
            theErrorHandler = null;
        }
        return theErrorHandler;
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        final Boolean b = (Boolean)this.theFeatures.get((Object)s);
        if (b != null) {
            return b;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Unknown feature ");
        sb.append(s);
        throw new SAXNotRecognizedException(sb.toString());
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.equals((Object)"http://xml.org/sax/properties/lexical-handler")) {
            LexicalHandler theLexicalHandler;
            if ((theLexicalHandler = this.theLexicalHandler) == this) {
                theLexicalHandler = null;
            }
            return theLexicalHandler;
        }
        if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/scanner")) {
            return this.theScanner;
        }
        if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/schema")) {
            return this.theSchema;
        }
        if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/auto-detector")) {
            return this.theAutoDetector;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Unknown property ");
        sb.append(s);
        throw new SAXNotRecognizedException(sb.toString());
    }
    
    public void gi(final char[] array, int n, int n2) throws SAXException {
        if (this.theNewElement != null) {
            return;
        }
        final String name = this.makeName(array, n, n2);
        if (name == null) {
            return;
        }
        ElementType elementType;
        if ((elementType = this.theSchema.getElementType(name)) == null) {
            if (this.ignoreBogons) {
                return;
            }
            final boolean bogonsEmpty = this.bogonsEmpty;
            n2 = -1;
            if (bogonsEmpty) {
                n = 0;
            }
            else {
                n = -1;
            }
            if (!this.rootBogons) {
                n2 = Integer.MAX_VALUE;
            }
            this.theSchema.elementType(name, n, n2, 0);
            if (!this.rootBogons) {
                final Schema theSchema = this.theSchema;
                theSchema.parent(name, theSchema.rootElementType().name());
            }
            elementType = this.theSchema.getElementType(name);
        }
        this.theNewElement = new Element(elementType, this.defaultAttributes);
    }
    
    public void parse(final String s) throws IOException, SAXException {
        this.parse(new InputSource(s));
    }
    
    public void parse(final InputSource inputSource) throws IOException, SAXException {
        this.setup();
        final Reader reader = this.getReader(inputSource);
        this.theContentHandler.startDocument();
        this.theScanner.resetDocumentLocator(inputSource.getPublicId(), inputSource.getSystemId());
        final Scanner theScanner = this.theScanner;
        if (theScanner instanceof Locator) {
            this.theContentHandler.setDocumentLocator((Locator)theScanner);
        }
        if (!this.theSchema.getURI().equals((Object)"")) {
            this.theContentHandler.startPrefixMapping(this.theSchema.getPrefix(), this.theSchema.getURI());
        }
        this.theScanner.scan(reader, this);
    }
    
    public void pcdata(final char[] array, final int n, final int n2) throws SAXException {
        if (n2 == 0) {
            return;
        }
        boolean b = true;
        for (int i = 0; i < n2; ++i) {
            if (!Character.isWhitespace(array[n + i])) {
                b = false;
            }
        }
        if (b && !this.theStack.canContain(this.thePCDATA)) {
            if (this.ignorableWhitespace) {
                this.theContentHandler.ignorableWhitespace(array, n, n2);
            }
        }
        else {
            this.rectify(this.thePCDATA);
            this.theContentHandler.characters(array, n, n2);
        }
    }
    
    public void pi(final char[] array, final int n, final int n2) throws SAXException {
        if (this.theNewElement == null) {
            final String thePITarget = this.thePITarget;
            if (thePITarget != null) {
                if ("xml".equalsIgnoreCase(thePITarget)) {
                    return;
                }
                int n3;
                if ((n3 = n2) > 0) {
                    n3 = n2;
                    if (array[n2 - 1] == '?') {
                        n3 = n2 - 1;
                    }
                }
                this.theContentHandler.processingInstruction(this.thePITarget, new String(array, n, n3));
                this.thePITarget = null;
            }
        }
    }
    
    public void pitarget(final char[] array, final int n, final int n2) throws SAXException {
        if (this.theNewElement != null) {
            return;
        }
        this.thePITarget = this.makeName(array, n, n2).replace(':', '_');
    }
    
    public void setContentHandler(final ContentHandler contentHandler) {
        Object theContentHandler = contentHandler;
        if (contentHandler == null) {
            theContentHandler = this;
        }
        this.theContentHandler = (ContentHandler)theContentHandler;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        Object theDTDHandler = dtdHandler;
        if (dtdHandler == null) {
            theDTDHandler = this;
        }
        this.theDTDHandler = (DTDHandler)theDTDHandler;
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        Object theEntityResolver = entityResolver;
        if (entityResolver == null) {
            theEntityResolver = this;
        }
        this.theEntityResolver = (EntityResolver)theEntityResolver;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        Object theErrorHandler = errorHandler;
        if (errorHandler == null) {
            theErrorHandler = this;
        }
        this.theErrorHandler = (ErrorHandler)theErrorHandler;
    }
    
    public void setFeature(final String s, final boolean cdataElements) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (this.theFeatures.get((Object)s) != null) {
            if (cdataElements) {
                this.theFeatures.put((Object)s, (Object)Boolean.TRUE);
            }
            else {
                this.theFeatures.put((Object)s, (Object)Boolean.FALSE);
            }
            if (s.equals((Object)"http://xml.org/sax/features/namespaces")) {
                this.namespaces = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons")) {
                this.ignoreBogons = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/bogons-empty")) {
                this.bogonsEmpty = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/root-bogons")) {
                this.rootBogons = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/default-attributes")) {
                this.defaultAttributes = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/translate-colons")) {
                this.translateColons = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/restart-elements")) {
                this.restartElements = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace")) {
                this.ignorableWhitespace = cdataElements;
            }
            else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/features/cdata-elements")) {
                this.CDATAElements = cdataElements;
            }
            return;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Unknown feature ");
        sb.append(s);
        throw new SAXNotRecognizedException(sb.toString());
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.equals((Object)"http://xml.org/sax/properties/lexical-handler")) {
            if (o == null) {
                this.theLexicalHandler = (LexicalHandler)this;
            }
            else {
                if (!(o instanceof LexicalHandler)) {
                    throw new SAXNotSupportedException("Your lexical handler is not a LexicalHandler");
                }
                this.theLexicalHandler = (LexicalHandler)o;
            }
        }
        else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/scanner")) {
            if (!(o instanceof Scanner)) {
                throw new SAXNotSupportedException("Your scanner is not a Scanner");
            }
            this.theScanner = (Scanner)o;
        }
        else if (s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/schema")) {
            if (!(o instanceof Schema)) {
                throw new SAXNotSupportedException("Your schema is not a Schema");
            }
            this.theSchema = (Schema)o;
        }
        else {
            if (!s.equals((Object)"http://www.ccil.org/~cowan/tagsoup/properties/auto-detector")) {
                final StringBuffer sb = new StringBuffer();
                sb.append("Unknown property ");
                sb.append(s);
                throw new SAXNotRecognizedException(sb.toString());
            }
            if (!(o instanceof AutoDetector)) {
                throw new SAXNotSupportedException("Your auto-detector is not an AutoDetector");
            }
            this.theAutoDetector = (AutoDetector)o;
        }
    }
    
    public void stagc(final char[] array, final int n, final int n2) throws SAXException {
        final Element theNewElement = this.theNewElement;
        if (theNewElement == null) {
            return;
        }
        this.rectify(theNewElement);
        if (this.theStack.model() == 0) {
            this.etag_basic(array, n, n2);
        }
    }
    
    public void stage(final char[] array, final int n, final int n2) throws SAXException {
        final Element theNewElement = this.theNewElement;
        if (theNewElement == null) {
            return;
        }
        this.rectify(theNewElement);
        this.etag_basic(array, n, n2);
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void startDTD(final String s, final String s2, final String s3) throws SAXException {
    }
    
    public void startEntity(final String s) throws SAXException {
    }
}
