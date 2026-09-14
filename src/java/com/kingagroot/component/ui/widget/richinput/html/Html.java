package com.kingagroot.component.ui.widget.richinput.html;

import org.ccil.cowan.tagsoup.HTMLSchema;
import com.kingagroot.component.ui.widget.richinput.GUnderLineEnum;
import com.kingagroot.component.ui.widget.richinput.RichConfig;
import android.text.TextUtils;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import java.io.IOException;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.io.StringReader;
import org.xml.sax.SAXException;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import org.xml.sax.XMLReader;
import org.xml.sax.ContentHandler;
import com.kingagroot.kingdraw.core.Html.SymbolTable;
import com.kingagroot.component.ui.widget.richinput.GFontFamilyEnum;
import com.goodsrc.library.utils.StringUtils;
import android.graphics.Color;
import java.util.HashMap;
import android.util.Log;
import com.kingagroot.component.ui.widget.richinput.span.GChemSubSpan;
import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.GDrawTypeEnum;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GItalicSpan;
import com.kingagroot.component.ui.widget.richinput.GFontStyleEnum;
import com.kingagroot.component.ui.widget.richinput.span.GBlodSpan;
import com.kingagroot.component.ui.widget.richinput.span.GFontFamilySpan;
import com.kingagroot.component.ui.widget.richinput.span.GFontSizeSpan;
import com.kingagroot.component.ui.widget.richinput.GAlignEnum;
import android.text.style.CharacterStyle;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import android.text.Spanned;
import org.ccil.cowan.tagsoup.Parser;

public class Html
{
    public static HtmlParserResult fromHtml(String ex) {
        final Parser parser = new Parser();
        try {
            parser.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", (Object)HtmlParser.schema);
            Object o = ex;
            if (ex == null) {
                o = "";
            }
            ex = (SAXNotSupportedException)new Convert((String)o, parser);
            final HtmlParserResult htmlParserResult = new HtmlParserResult();
            htmlParserResult.setSpanned((Spanned)((Convert)ex).convert());
            htmlParserResult.setgAlignEnum(((Convert)ex).getgAlignEnum());
            return htmlParserResult;
        }
        catch (final SAXNotSupportedException ex) {}
        catch (final SAXNotRecognizedException ex2) {}
        throw new RuntimeException((Throwable)ex);
    }
    
    private static String getSpanEndHtml() {
        return "</span>";
    }
    
    private static String getSpanStartHtml(final CharacterStyle[] array, final GAlignEnum gAlignEnum) {
        final HStyle hStyle = new HStyle();
        for (int i = 0; i < array.length; ++i) {
            final CharacterStyle characterStyle = array[i];
            if (characterStyle instanceof GFontSizeSpan) {
                hStyle.size = ((GFontSizeSpan)characterStyle).getUnitySize();
            }
            else if (characterStyle instanceof GFontFamilySpan) {
                hStyle.font = ((GFontFamilySpan)characterStyle).getFamily();
            }
            else if (characterStyle instanceof GBlodSpan) {
                if (hStyle.fstyle == GFontStyleEnum.Italic.getCode()) {
                    hStyle.fstyle = GFontStyleEnum.BoldItalic.getCode();
                }
                else {
                    hStyle.fstyle = GFontStyleEnum.Bold.getCode();
                }
            }
            else if (characterStyle instanceof GItalicSpan) {
                if (hStyle.fstyle == GFontStyleEnum.Bold.getCode()) {
                    hStyle.fstyle = GFontStyleEnum.BoldItalic.getCode();
                }
                else {
                    hStyle.fstyle = GFontStyleEnum.Italic.getCode();
                }
            }
            else if (characterStyle instanceof GSuperscriptSpan) {
                hStyle.drawtype = GDrawTypeEnum.sup.getCode();
            }
            else if (characterStyle instanceof GSubscriptSpan) {
                hStyle.drawtype = GDrawTypeEnum.sub.getCode();
            }
            else if (characterStyle instanceof GChemStyleSpan) {
                if (hStyle.drawtype != GDrawTypeEnum.chemsub.getCode()) {
                    hStyle.drawtype = GDrawTypeEnum.chem.getCode();
                }
            }
            else if (characterStyle instanceof GChemSubSpan) {
                hStyle.drawtype = GDrawTypeEnum.chemsub.getCode();
            }
        }
        if (gAlignEnum == GAlignEnum.left) {
            hStyle.align = GAlignEnum.left.getCode();
        }
        else if (gAlignEnum == GAlignEnum.center) {
            hStyle.align = GAlignEnum.center.getCode();
        }
        else if (gAlignEnum == GAlignEnum.right) {
            hStyle.align = GAlignEnum.right.getCode();
        }
        toRGB(hStyle.color);
        final StringBuilder sb = new StringBuilder();
        sb.append("<span");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(" font=\"");
        sb2.append(hStyle.font);
        sb.append(sb2.toString());
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("\" size=\"");
        sb3.append(hStyle.size);
        sb.append(sb3.toString());
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("\" fstyle=\"");
        sb4.append(hStyle.fstyle);
        sb.append(sb4.toString());
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("\" align=\"");
        sb5.append(hStyle.align);
        sb.append(sb5.toString());
        final StringBuilder sb6 = new StringBuilder();
        sb6.append("\" drawtype=\"");
        sb6.append(hStyle.drawtype);
        sb.append(sb6.toString());
        sb.append("\">");
        Log.e("span", sb.toString());
        return sb.toString();
    }
    
    private static boolean isSame(final CharacterStyle[] array, final CharacterStyle[] array2) {
        final int length = array.length;
        final int length2 = array2.length;
        boolean b2;
        final boolean b = b2 = false;
        if (length == length2) {
            final HashMap hashMap = new HashMap();
            for (final CharacterStyle characterStyle : array) {
                hashMap.put((Object)characterStyle.getClass(), (Object)characterStyle);
            }
            for (final CharacterStyle characterStyle2 : array2) {
                b2 = b;
                if (!hashMap.containsKey((Object)characterStyle2.getClass())) {
                    return b2;
                }
                if (characterStyle2 instanceof GFontFamilySpan) {
                    if (!((GFontFamilySpan)characterStyle2).getFamily().equals((Object)((GFontFamilySpan)hashMap.get((Object)characterStyle2.getClass())).getFamily())) {
                        b2 = b;
                        return b2;
                    }
                }
                else if (characterStyle2 instanceof GFontSizeSpan && ((GFontSizeSpan)characterStyle2).getSize() != ((GFontSizeSpan)hashMap.get((Object)characterStyle2.getClass())).getSize()) {
                    b2 = b;
                    return b2;
                }
            }
            b2 = true;
        }
        return b2;
    }
    
    private static int nextSpanTransition(final Spanned spanned, int n, final int n2) {
        final int nextSpanTransition = spanned.nextSpanTransition(n, n2, (Class)CharacterStyle.class);
        final CharacterStyle[] array = (CharacterStyle[])spanned.getSpans(n, nextSpanTransition, (Class)CharacterStyle.class);
        int n3 = nextSpanTransition;
        int i = 1;
        n = nextSpanTransition;
        while (i != 0) {
            final int nextSpanTransition2 = spanned.nextSpanTransition(n3, n2, (Class)CharacterStyle.class);
            Label_0096: {
                if (isSame(array, (CharacterStyle[])spanned.getSpans(n3, nextSpanTransition2, (Class)CharacterStyle.class))) {
                    final int n4 = n = nextSpanTransition2;
                    if (nextSpanTransition2 < n2) {
                        break Label_0096;
                    }
                    n = n4;
                }
                i = 0;
            }
            n3 = nextSpanTransition2;
        }
        return n;
    }
    
    public static String toHtml(final Spanned spanned, final GAlignEnum gAlignEnum) {
        final StringBuilder sb = new StringBuilder();
        withinHtml(sb, spanned, gAlignEnum);
        return sb.toString();
    }
    
    private static String toRGB(final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(StringUtils.format("%02x", new Object[] { Color.red(n) }));
        sb.append(StringUtils.format("%02x", new Object[] { Color.green(n) }));
        sb.append(StringUtils.format("%02x", new Object[] { Color.blue(n) }));
        return sb.toString();
    }
    
    private static void withinHtml(final StringBuilder sb, final Spanned spanned, final GAlignEnum gAlignEnum) {
        int nextSpanTransition;
        for (int length = spanned.length(), i = 0; i < length; i = nextSpanTransition) {
            nextSpanTransition = nextSpanTransition(spanned, i, length);
            sb.append(getSpanStartHtml((CharacterStyle[])spanned.getSpans(i, nextSpanTransition, (Class)CharacterStyle.class), gAlignEnum));
            withinStyle(sb, spanned, i, nextSpanTransition);
            sb.append(getSpanEndHtml());
        }
    }
    
    private static void withinStyle(final StringBuilder sb, final Spanned spanned, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            final char char1 = spanned.charAt(i);
            if (char1 == '<') {
                sb.append("&lt;");
            }
            else if (char1 == '>') {
                sb.append("&gt;");
            }
            else if (char1 == ' ') {
                sb.append("&nbsp;");
            }
            else if (char1 != '\n' && char1 != '\r') {
                final GFontFamilySpan[] array = (GFontFamilySpan[])spanned.getSpans(n, n2, (Class)GFontFamilySpan.class);
                if (array != null && array.length >= 1) {
                    if (array[array.length - 1].getFamily().equals((Object)GFontFamilyEnum.Symbol.getFontFamily())) {
                        sb.append(SymbolTable.converToNormal(char1));
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append("<br/>");
            }
        }
    }
    
    private static class AlignType
    {
        int code;
        
        public AlignType(final int code) {
            this.code = code;
        }
    }
    
    private static class Convert implements ContentHandler
    {
        private GAlignEnum gAlignEnum;
        private XMLReader mReader;
        private String mSource;
        private SpannableStringBuilder mSpannableStringBuilder;
        
        public Convert(final String mSource, final Parser mReader) {
            this.gAlignEnum = GAlignEnum.left;
            this.mSource = mSource;
            this.mSpannableStringBuilder = new SpannableStringBuilder();
            this.mReader = (XMLReader)mReader;
        }
        
        private static <T> T getLast(final Spanned spanned, final Class<T> clazz) {
            final Object[] spans = spanned.getSpans(0, spanned.length(), (Class)clazz);
            if (spans.length == 0) {
                return null;
            }
            return (T)spans[spans.length - 1];
        }
        
        private static void setFamilySpanFromMark(final Spannable spannable, final Object o, final Object o2) {
            final int spanStart = spannable.getSpanStart(o);
            spannable.removeSpan(o);
            final int length = spannable.length();
            if (spanStart != length) {
                spannable.setSpan(o2, spanStart, length, 33);
            }
        }
        
        private static void setSpanFromMark(final Spannable spannable, final Object o, final Object... array) {
            final int spanStart = spannable.getSpanStart(o);
            spannable.removeSpan(o);
            final int length = spannable.length();
            if (spanStart != length) {
                for (int length2 = array.length, i = 0; i < length2; ++i) {
                    spannable.setSpan(array[i], spanStart, length, 33);
                }
            }
        }
        
        private static void start(final Editable editable, final Object o) {
            final int length = editable.length();
            editable.setSpan(o, length, length, 17);
        }
        
        public void characters(final char[] array, final int n, final int n2) throws SAXException {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n2; ++i) {
                final char c = array[i + n];
                if (c != ' ' && c != '\n') {
                    final FontFamily fontFamily = getLast((Spanned)this.mSpannableStringBuilder, FontFamily.class);
                    if (fontFamily != null && fontFamily.family.equals((Object)GFontFamilyEnum.Symbol.getFontFamily())) {
                        sb.append(SymbolTable.convertToSymbol(c));
                    }
                    else {
                        sb.append(c);
                    }
                }
                else {
                    final int length = sb.length();
                    int n3;
                    if (length == 0) {
                        final int length2 = this.mSpannableStringBuilder.length();
                        if (length2 == 0) {
                            n3 = 10;
                        }
                        else {
                            n3 = this.mSpannableStringBuilder.charAt(length2 - 1);
                        }
                    }
                    else {
                        n3 = sb.charAt(length - 1);
                    }
                    if (n3 != 32 && n3 != 10) {
                        sb.append(' ');
                    }
                }
            }
            this.mSpannableStringBuilder.append((CharSequence)sb);
        }
        
        public Spannable convert() {
            this.mReader.setContentHandler((ContentHandler)this);
            InputSource inputSource = null;
            try {
                final XMLReader mReader = this.mReader;
                inputSource = new InputSource((Reader)new StringReader(this.mSource));
                mReader.parse(inputSource);
                return (Spannable)this.mSpannableStringBuilder;
            }
            catch (final SAXException inputSource) {}
            catch (final IOException ex) {}
            throw new RuntimeException((Throwable)inputSource);
        }
        
        public void endDocument() throws SAXException {
        }
        
        public void endElement(final String s, final String s2, final String s3) throws SAXException {
            if (s2.equalsIgnoreCase("br")) {
                this.mSpannableStringBuilder.append('\n');
            }
            else if (s2.equalsIgnoreCase("span")) {
                final FontSize fontSize = getLast((Spanned)this.mSpannableStringBuilder, FontSize.class);
                if (fontSize != null) {
                    setSpanFromMark((Spannable)this.mSpannableStringBuilder, fontSize, new GFontSizeSpan(GDensityUtil.sp2px((float)fontSize.mFontSize)));
                }
                final FontFamily fontFamily = getLast((Spanned)this.mSpannableStringBuilder, FontFamily.class);
                if (fontFamily != null) {
                    setSpanFromMark((Spannable)this.mSpannableStringBuilder, fontFamily, new GFontFamilySpan(fontFamily.family));
                }
                final Fstyle fstyle = getLast((Spanned)this.mSpannableStringBuilder, Fstyle.class);
                if (fstyle != null) {
                    if (fstyle.code == GFontStyleEnum.Bold.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fstyle, new GBlodSpan());
                    }
                    else if (fstyle.code == GFontStyleEnum.Italic.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fstyle, new GItalicSpan());
                    }
                    else if (fstyle.code == GFontStyleEnum.BoldItalic.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fstyle, new GBlodSpan(), new GItalicSpan());
                    }
                    else {
                        this.mSpannableStringBuilder.removeSpan((Object)fstyle);
                    }
                }
                final FType fType = getLast((Spanned)this.mSpannableStringBuilder, FType.class);
                if (fType != null) {
                    if (fType.code == GDrawTypeEnum.sub.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fType, new GSubscriptSpan());
                    }
                    else if (fType.code == GDrawTypeEnum.sup.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fType, new GSuperscriptSpan());
                    }
                    else if (fType.code == GDrawTypeEnum.chem.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fType, new GChemStyleSpan());
                    }
                    else if (fType.code == GDrawTypeEnum.chemsub.getCode()) {
                        setSpanFromMark((Spannable)this.mSpannableStringBuilder, fType, new GChemStyleSpan(), new GChemSubSpan());
                    }
                    else {
                        this.mSpannableStringBuilder.removeSpan((Object)fType);
                    }
                }
            }
        }
        
        public void endPrefixMapping(final String s) throws SAXException {
        }
        
        public GAlignEnum getgAlignEnum() {
            return this.gAlignEnum;
        }
        
        public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        }
        
        public void processingInstruction(final String s, final String s2) throws SAXException {
        }
        
        public void setDocumentLocator(final Locator locator) {
        }
        
        public void skippedEntity(final String s) throws SAXException {
        }
        
        public void startDocument() throws SAXException {
        }
        
        public void startElement(String s, String value, String value2, final Attributes attributes) throws SAXException {
            if (value.equalsIgnoreCase("br")) {
                return;
            }
            if (!value.equalsIgnoreCase("span")) {
                return;
            }
            final Object value3 = attributes.getValue("", "font");
            final Object value4 = attributes.getValue("", "size");
            final Object value5 = attributes.getValue("", "fstyle");
            value2 = attributes.getValue("", "drawtype");
            attributes.getValue("", "color");
            value = attributes.getValue("", "align");
            attributes.getValue("", "uline");
            Label_0145: {
                if (!TextUtils.isEmpty((CharSequence)value3)) {
                    s = (String)value3;
                    if (GFontFamilyEnum.hasValue((String)value3)) {
                        break Label_0145;
                    }
                }
                s = RichConfig.DEFAULT_FONT_FAMILY;
            }
            start((Editable)this.mSpannableStringBuilder, new FontFamily(s));
            int n = 10;
            s = (String)value4;
            while (true) {
                try {
                    if (TextUtils.isEmpty((CharSequence)value4)) {
                        s = String.valueOf(10);
                    }
                    n = Integer.parseInt(s);
                    start((Editable)this.mSpannableStringBuilder, new FontSize(n));
                    s = (String)value5;
                    try {
                        if (TextUtils.isEmpty((CharSequence)value5)) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(GFontStyleEnum.Regular.getCode());
                            sb.append("");
                            s = sb.toString();
                        }
                        n = Integer.parseInt(s);
                    }
                    catch (final NumberFormatException ex) {
                        n = GFontStyleEnum.Regular.getCode();
                    }
                    start((Editable)this.mSpannableStringBuilder, new Fstyle(n));
                    s = value2;
                    try {
                        if (TextUtils.isEmpty((CharSequence)value2)) {
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(GDrawTypeEnum.normal.getCode());
                            sb2.append("");
                            s = sb2.toString();
                        }
                        n = Integer.parseInt(s);
                    }
                    catch (final NumberFormatException ex2) {
                        n = GDrawTypeEnum.normal.getCode();
                    }
                    start((Editable)this.mSpannableStringBuilder, new FType(n));
                    s = value;
                    try {
                        if (TextUtils.isEmpty((CharSequence)value)) {
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(GAlignEnum.left.getCode());
                            sb3.append("");
                            s = sb3.toString();
                        }
                        n = Integer.parseInt(s);
                    }
                    catch (final NumberFormatException ex3) {
                        n = GAlignEnum.left.getCode();
                    }
                    this.gAlignEnum = GAlignEnum.valueOfCode(n);
                }
                catch (final NumberFormatException ex4) {
                    continue;
                }
                break;
            }
        }
        
        public void startPrefixMapping(final String s, final String s2) throws SAXException {
        }
    }
    
    private static class FType
    {
        int code;
        
        public FType(final int code) {
            this.code = code;
        }
    }
    
    private static class FontFamily
    {
        String family;
        
        public FontFamily(final String family) {
            this.family = family;
        }
    }
    
    private static class FontSize
    {
        private int mFontSize;
        
        public FontSize(final int mFontSize) {
            this.mFontSize = mFontSize;
        }
    }
    
    private static class Fstyle
    {
        int code;
        
        public Fstyle(final int code) {
            this.code = code;
        }
    }
    
    private static class HStyle
    {
        public int align;
        public int color;
        public int drawtype;
        public String font;
        public int fstyle;
        public int size;
        public int uline;
        
        private HStyle() {
            this.font = RichConfig.DEFAULT_FONT_FAMILY;
            this.size = 10;
            this.fstyle = GFontStyleEnum.Regular.getCode();
            this.color = -16777216;
            this.align = GAlignEnum.left.getCode();
            this.drawtype = GDrawTypeEnum.normal.getCode();
            this.uline = GUnderLineEnum.normal.getCode();
        }
    }
    
    private static class HtmlParser
    {
        private static final HTMLSchema schema;
        
        static {
            schema = new HTMLSchema();
        }
    }
    
    private static class Underline
    {
        int code;
        
        public Underline(final int code) {
            this.code = code;
        }
    }
    
    private static class color
    {
        String color;
        
        public color(final String color) {
            this.color = color;
        }
    }
}
