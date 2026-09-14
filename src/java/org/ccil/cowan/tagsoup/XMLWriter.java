package org.ccil.cowan.tagsoup;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Enumeration;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import java.util.Properties;
import java.io.Writer;
import org.xml.sax.helpers.NamespaceSupport;
import java.util.Hashtable;
import org.xml.sax.Attributes;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

public class XMLWriter extends XMLFilterImpl implements LexicalHandler
{
    public static final String CDATA_SECTION_ELEMENTS = "cdata-section-elements";
    public static final String DOCTYPE_PUBLIC = "doctype-public";
    public static final String DOCTYPE_SYSTEM = "doctype-system";
    public static final String ENCODING = "encoding";
    public static final String INDENT = "indent";
    public static final String MEDIA_TYPE = "media-type";
    public static final String METHOD = "method";
    public static final String OMIT_XML_DECLARATION = "omit-xml-declaration";
    public static final String STANDALONE = "standalone";
    public static final String VERSION = "version";
    private final Attributes EMPTY_ATTS;
    private String[] booleans;
    private boolean cdataElement;
    private Hashtable doneDeclTable;
    private int elementLevel;
    private boolean forceDTD;
    private Hashtable forcedDeclTable;
    private boolean hasOutputDTD;
    private boolean htmlMode;
    private NamespaceSupport nsSupport;
    private Writer output;
    private String outputEncoding;
    private Properties outputProperties;
    private String overridePublic;
    private String overrideSystem;
    private int prefixCounter;
    private Hashtable prefixTable;
    private String standalone;
    private boolean unicodeMode;
    private String version;
    
    public XMLWriter() {
        this.booleans = new String[] { "checked", "compact", "declare", "defer", "disabled", "ismap", "multiple", "nohref", "noresize", "noshade", "nowrap", "readonly", "selected" };
        this.EMPTY_ATTS = (Attributes)new AttributesImpl();
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.unicodeMode = false;
        this.outputEncoding = "";
        this.htmlMode = false;
        this.forceDTD = false;
        this.hasOutputDTD = false;
        this.overridePublic = null;
        this.overrideSystem = null;
        this.version = null;
        this.standalone = null;
        this.cdataElement = false;
        this.init(null);
    }
    
    public XMLWriter(final Writer writer) {
        this.booleans = new String[] { "checked", "compact", "declare", "defer", "disabled", "ismap", "multiple", "nohref", "noresize", "noshade", "nowrap", "readonly", "selected" };
        this.EMPTY_ATTS = (Attributes)new AttributesImpl();
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.unicodeMode = false;
        this.outputEncoding = "";
        this.htmlMode = false;
        this.forceDTD = false;
        this.hasOutputDTD = false;
        this.overridePublic = null;
        this.overrideSystem = null;
        this.version = null;
        this.standalone = null;
        this.cdataElement = false;
        this.init(writer);
    }
    
    public XMLWriter(final XMLReader xmlReader) {
        super(xmlReader);
        this.booleans = new String[] { "checked", "compact", "declare", "defer", "disabled", "ismap", "multiple", "nohref", "noresize", "noshade", "nowrap", "readonly", "selected" };
        this.EMPTY_ATTS = (Attributes)new AttributesImpl();
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.unicodeMode = false;
        this.outputEncoding = "";
        this.htmlMode = false;
        this.forceDTD = false;
        this.hasOutputDTD = false;
        this.overridePublic = null;
        this.overrideSystem = null;
        this.version = null;
        this.standalone = null;
        this.cdataElement = false;
        this.init(null);
    }
    
    public XMLWriter(final XMLReader xmlReader, final Writer writer) {
        super(xmlReader);
        this.booleans = new String[] { "checked", "compact", "declare", "defer", "disabled", "ismap", "multiple", "nohref", "noresize", "noshade", "nowrap", "readonly", "selected" };
        this.EMPTY_ATTS = (Attributes)new AttributesImpl();
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.unicodeMode = false;
        this.outputEncoding = "";
        this.htmlMode = false;
        this.forceDTD = false;
        this.hasOutputDTD = false;
        this.overridePublic = null;
        this.overrideSystem = null;
        this.version = null;
        this.standalone = null;
        this.cdataElement = false;
        this.init(writer);
    }
    
    private boolean booleanAttribute(final String s, final String s2, final String s3) {
        String substring = s;
        if (s == null) {
            final int index = s2.indexOf(58);
            substring = s;
            if (index != -1) {
                substring = s2.substring(index + 1, s2.length());
            }
        }
        if (!substring.equals((Object)s3)) {
            return false;
        }
        int n = 0;
        while (true) {
            final String[] booleans = this.booleans;
            if (n >= booleans.length) {
                return false;
            }
            if (substring.equals((Object)booleans[n])) {
                return true;
            }
            ++n;
        }
    }
    
    private String doPrefix(final String s, String s2, final boolean b) {
        final NamespaceSupport nsSupport = this.nsSupport;
        final String s3 = "";
        final String uri = nsSupport.getURI("");
        final boolean equals = "".equals((Object)s);
        final String s4 = null;
        if (equals) {
            if (b && uri != null) {
                this.nsSupport.declarePrefix("", "");
            }
            return null;
        }
        String prefix;
        if (b && uri != null && s.equals((Object)uri)) {
            prefix = "";
        }
        else {
            prefix = this.nsSupport.getPrefix(s);
        }
        if (prefix != null) {
            return prefix;
        }
        final String s5 = (String)this.doneDeclTable.get((Object)s);
        String s6 = null;
        Label_0161: {
            if ((s6 = s5) != null) {
                if ((b && uri == null) || !"".equals((Object)s5)) {
                    s6 = s5;
                    if (this.nsSupport.getURI(s5) == null) {
                        break Label_0161;
                    }
                }
                s6 = null;
            }
        }
        String s9 = null;
        Label_0242: {
            String s7;
            if ((s7 = s6) == null) {
                final String s8 = (String)this.prefixTable.get((Object)s);
                if ((s7 = s8) != null) {
                    if (!b || uri != null) {
                        s9 = s4;
                        if ("".equals((Object)s8)) {
                            break Label_0242;
                        }
                    }
                    s7 = s8;
                    if (this.nsSupport.getURI(s8) != null) {
                        s9 = s4;
                        break Label_0242;
                    }
                }
            }
            s9 = s7;
        }
        while (true) {
            Label_0300: {
                if (s9 != null || s2 == null || "".equals((Object)s2)) {
                    break Label_0300;
                }
                final int index = s2.indexOf(58);
                if (index == -1) {
                    if (!b || uri != null) {
                        break Label_0300;
                    }
                    s2 = s3;
                }
                else {
                    s2 = s2.substring(0, index);
                }
                while (s2 == null || this.nsSupport.getURI(s2) != null) {
                    final StringBuffer sb = new StringBuffer();
                    sb.append("__NS");
                    sb.append(++this.prefixCounter);
                    s2 = sb.toString();
                }
                this.nsSupport.declarePrefix(s2, s);
                this.doneDeclTable.put((Object)s, (Object)s2);
                return s2;
            }
            s2 = s9;
            continue;
        }
    }
    
    private void forceNSDecls() {
        final Enumeration keys = this.forcedDeclTable.keys();
        while (keys.hasMoreElements()) {
            this.doPrefix((String)keys.nextElement(), null, true);
        }
    }
    
    private void init(final Writer output) {
        this.setOutput(output);
        this.nsSupport = new NamespaceSupport();
        this.prefixTable = new Hashtable();
        this.forcedDeclTable = new Hashtable();
        this.doneDeclTable = new Hashtable();
        this.outputProperties = new Properties();
    }
    
    private void write(final char c) throws SAXException {
        try {
            this.output.write((int)c);
        }
        catch (final IOException ex) {
            throw new SAXException((Exception)ex);
        }
    }
    
    private void write(final String s) throws SAXException {
        try {
            this.output.write(s);
        }
        catch (final IOException ex) {
            throw new SAXException((Exception)ex);
        }
    }
    
    private void writeAttributes(final Attributes attributes) throws SAXException {
        for (int length = attributes.getLength(), i = 0; i < length; ++i) {
            final char[] charArray = attributes.getValue(i).toCharArray();
            this.write(' ');
            this.writeName(attributes.getURI(i), attributes.getLocalName(i), attributes.getQName(i), false);
            if (this.htmlMode && this.booleanAttribute(attributes.getLocalName(i), attributes.getQName(i), attributes.getValue(i))) {
                break;
            }
            this.write("=\"");
            this.writeEsc(charArray, 0, charArray.length, true);
            this.write('\"');
        }
    }
    
    private void writeEsc(final char[] array, final int n, final int n2, final boolean b) throws SAXException {
        for (int i = n; i < n + n2; ++i) {
            final char c = array[i];
            if (c != '\"') {
                if (c != '&') {
                    if (c != '<') {
                        if (c != '>') {
                            if (!this.unicodeMode && array[i] > '\u007f') {
                                this.write("&#");
                                this.write(Integer.toString((int)array[i]));
                                this.write(';');
                            }
                            else {
                                this.write(array[i]);
                            }
                        }
                        else {
                            this.write("&gt;");
                        }
                    }
                    else {
                        this.write("&lt;");
                    }
                }
                else {
                    this.write("&amp;");
                }
            }
            else if (b) {
                this.write("&quot;");
            }
            else {
                this.write('\"');
            }
        }
    }
    
    private void writeNSDecls() throws SAXException {
        final Enumeration declaredPrefixes = this.nsSupport.getDeclaredPrefixes();
        while (declaredPrefixes.hasMoreElements()) {
            final String s = (String)declaredPrefixes.nextElement();
            String uri;
            if ((uri = this.nsSupport.getURI(s)) == null) {
                uri = "";
            }
            final char[] charArray = uri.toCharArray();
            this.write(' ');
            if ("".equals((Object)s)) {
                this.write("xmlns=\"");
            }
            else {
                this.write("xmlns:");
                this.write(s);
                this.write("=\"");
            }
            this.writeEsc(charArray, 0, charArray.length, true);
            this.write('\"');
        }
    }
    
    private void writeName(String doPrefix, final String s, final String s2, final boolean b) throws SAXException {
        doPrefix = this.doPrefix(doPrefix, s2, b);
        if (doPrefix != null && !"".equals((Object)doPrefix)) {
            this.write(doPrefix);
            this.write(':');
        }
        if (s != null && !"".equals((Object)s)) {
            this.write(s);
        }
        else {
            this.write(s2.substring(s2.indexOf(58) + 1, s2.length()));
        }
    }
    
    public void characters(final String s) throws SAXException {
        final char[] charArray = s.toCharArray();
        this.characters(charArray, 0, charArray.length);
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (!this.cdataElement) {
            this.writeEsc(array, n, n2, false);
        }
        else {
            for (int i = n; i < n + n2; ++i) {
                this.write(array[i]);
            }
        }
        super.characters(array, n, n2);
    }
    
    public void comment(final char[] array, final int n, final int n2) throws SAXException {
        this.write("<!--");
        int n3 = n;
        while (true) {
            final int n4 = n + n2;
            if (n3 >= n4) {
                break;
            }
            this.write(array[n3]);
            if (array[n3] == '-') {
                final int n5 = n3 + 1;
                if (n5 <= n4 && array[n5] == '-') {
                    this.write(' ');
                }
            }
            ++n3;
        }
        this.write("-->");
    }
    
    public void dataElement(final String s, final String s2) throws SAXException {
        this.dataElement("", s, "", this.EMPTY_ATTS, s2);
    }
    
    public void dataElement(final String s, final String s2, final String s3) throws SAXException {
        this.dataElement(s, s2, "", this.EMPTY_ATTS, s3);
    }
    
    public void dataElement(final String s, final String s2, final String s3, final Attributes attributes, final String s4) throws SAXException {
        this.startElement(s, s2, s3, attributes);
        this.characters(s4);
        this.endElement(s, s2, s3);
    }
    
    public void emptyElement(final String s) throws SAXException {
        this.emptyElement("", s, "", this.EMPTY_ATTS);
    }
    
    public void emptyElement(final String s, final String s2) throws SAXException {
        this.emptyElement(s, s2, "", this.EMPTY_ATTS);
    }
    
    public void emptyElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        this.nsSupport.pushContext();
        this.write('<');
        this.writeName(s, s2, s3, true);
        this.writeAttributes(attributes);
        if (this.elementLevel == 1) {
            this.forceNSDecls();
        }
        this.writeNSDecls();
        this.write("/>");
        super.startElement(s, s2, s3, attributes);
        super.endElement(s, s2, s3);
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        this.write('\n');
        super.endDocument();
        try {
            this.flush();
        }
        catch (final IOException ex) {
            throw new SAXException((Exception)ex);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        this.endElement("", s, "");
    }
    
    public void endElement(final String s, final String s2) throws SAXException {
        this.endElement(s, s2, "");
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        if (!this.htmlMode || (!s.equals((Object)"http://www.w3.org/1999/xhtml") && !s.equals((Object)"")) || (!s3.equals((Object)"area") && !s3.equals((Object)"base") && !s3.equals((Object)"basefont") && !s3.equals((Object)"br") && !s3.equals((Object)"col") && !s3.equals((Object)"frame") && !s3.equals((Object)"hr") && !s3.equals((Object)"img") && !s3.equals((Object)"input") && !s3.equals((Object)"isindex") && !s3.equals((Object)"link") && !s3.equals((Object)"meta") && !s3.equals((Object)"param"))) {
            this.write("</");
            this.writeName(s, s2, s3, true);
            this.write('>');
        }
        this.cdataElement = false;
        super.endElement(s, s2, s3);
        this.nsSupport.popContext();
        --this.elementLevel;
    }
    
    public void endEntity(final String s) throws SAXException {
    }
    
    public void flush() throws IOException {
        this.output.flush();
    }
    
    public void forceNSDecl(final String s) {
        this.forcedDeclTable.put((Object)s, (Object)Boolean.TRUE);
    }
    
    public void forceNSDecl(final String s, final String s2) {
        this.setPrefix(s, s2);
        this.forceNSDecl(s);
    }
    
    public String getOutputProperty(final String s) {
        return this.outputProperties.getProperty(s);
    }
    
    public String getPrefix(final String s) {
        return (String)this.prefixTable.get((Object)s);
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        this.writeEsc(array, n, n2, false);
        super.ignorableWhitespace(array, n, n2);
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        this.write("<?");
        this.write(s);
        this.write(' ');
        this.write(s2);
        this.write("?>");
        if (this.elementLevel < 1) {
            this.write('\n');
        }
        super.processingInstruction(s, s2);
    }
    
    public void reset() {
        this.elementLevel = 0;
        this.prefixCounter = 0;
        this.nsSupport.reset();
    }
    
    public void setOutput(final Writer output) {
        if (output == null) {
            this.output = (Writer)new OutputStreamWriter((OutputStream)System.out);
        }
        else {
            this.output = output;
        }
    }
    
    public void setOutputProperty(final String s, final String standalone) {
        this.outputProperties.setProperty(s, standalone);
        if (s.equals((Object)"encoding")) {
            this.outputEncoding = standalone;
            this.unicodeMode = standalone.substring(0, 3).equalsIgnoreCase("utf");
        }
        else if (s.equals((Object)"method")) {
            this.htmlMode = standalone.equals((Object)"html");
        }
        else if (s.equals((Object)"doctype-public")) {
            this.overridePublic = standalone;
            this.forceDTD = true;
        }
        else if (s.equals((Object)"doctype-system")) {
            this.overrideSystem = standalone;
            this.forceDTD = true;
        }
        else if (s.equals((Object)"version")) {
            this.version = standalone;
        }
        else if (s.equals((Object)"standalone")) {
            this.standalone = standalone;
        }
    }
    
    public void setPrefix(final String s, final String s2) {
        this.prefixTable.put((Object)s, (Object)s2);
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void startDTD(String s, String s2, String s3) throws SAXException {
        if (s == null) {
            return;
        }
        if (this.hasOutputDTD) {
            return;
        }
        this.hasOutputDTD = true;
        this.write("<!DOCTYPE ");
        this.write(s);
        if ((s = s3) == null) {
            s = "";
        }
        s3 = this.overrideSystem;
        if (s3 != null) {
            s = s3;
        }
        char c = '\"';
        char c2;
        if (s.indexOf(34) != -1) {
            c2 = '\'';
        }
        else {
            c2 = '\"';
        }
        s3 = this.overridePublic;
        if (s3 != null) {
            s2 = s3;
        }
        if (s2 != null && !"".equals((Object)s2)) {
            if (s2.indexOf(34) != -1) {
                c = '\'';
            }
            this.write(" PUBLIC ");
            this.write(c);
            this.write(s2);
            this.write(c);
            this.write(' ');
        }
        else {
            this.write(" SYSTEM ");
        }
        this.write(c2);
        this.write(s);
        this.write(c2);
        this.write(">\n");
    }
    
    public void startDocument() throws SAXException {
        this.reset();
        if (!"yes".equals((Object)this.outputProperties.getProperty("omit-xml-declaration", "no"))) {
            this.write("<?xml");
            if (this.version == null) {
                this.write(" version=\"1.0\"");
            }
            else {
                this.write(" version=\"");
                this.write(this.version);
                this.write("\"");
            }
            final String outputEncoding = this.outputEncoding;
            if (outputEncoding != null && outputEncoding != "") {
                this.write(" encoding=\"");
                this.write(this.outputEncoding);
                this.write("\"");
            }
            if (this.standalone == null) {
                this.write(" standalone=\"yes\"?>\n");
            }
            else {
                this.write(" standalone=\"");
                this.write(this.standalone);
                this.write("\"");
            }
        }
        super.startDocument();
    }
    
    public void startElement(final String s) throws SAXException {
        this.startElement("", s, "", this.EMPTY_ATTS);
    }
    
    public void startElement(final String s, final String s2) throws SAXException {
        this.startElement(s, s2, "", this.EMPTY_ATTS);
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        ++this.elementLevel;
        this.nsSupport.pushContext();
        if (this.forceDTD && !this.hasOutputDTD) {
            String s4;
            if (s2 == null) {
                s4 = s3;
            }
            else {
                s4 = s2;
            }
            this.startDTD(s4, "", "");
        }
        this.write('<');
        this.writeName(s, s2, s3, true);
        this.writeAttributes(attributes);
        if (this.elementLevel == 1) {
            this.forceNSDecls();
        }
        this.writeNSDecls();
        this.write('>');
        if (this.htmlMode && (s3.equals((Object)"script") || s3.equals((Object)"style"))) {
            this.cdataElement = true;
        }
        super.startElement(s, s2, s3, attributes);
    }
    
    public void startEntity(final String s) throws SAXException {
    }
}
