package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CSSTextScanner extends TextScanner
{
    static final Pattern PATTERN_BLOCK_COMMENTS;
    
    static {
        PATTERN_BLOCK_COMMENTS = Pattern.compile("(?s)/\\*.*?\\*/");
    }
    
    public CSSTextScanner(final String s) {
        super(CSSTextScanner.PATTERN_BLOCK_COMMENTS.matcher((CharSequence)s).replaceAll(""));
    }
    
    private int hexChar(final int n) {
        if (n >= 48 && n <= 57) {
            return n - 48;
        }
        int n2 = 65;
        if (n < 65 || n > 70) {
            n2 = 97;
            if (n < 97 || n > 102) {
                return -1;
            }
        }
        return n - n2 + 10;
    }
    
    private CSSTextScanner.CSSTextScanner$AnPlusB nextAnPlusB() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        if (!this.consume('(')) {
            return null;
        }
        this.skipWhitespace();
        final boolean consume = this.consume("odd");
        int n = 1;
        final boolean b = true;
        CSSTextScanner.CSSTextScanner$AnPlusB cssTextScanner$AnPlusB;
        if (consume) {
            cssTextScanner$AnPlusB = new CSSTextScanner.CSSTextScanner$AnPlusB(2, 1);
        }
        else {
            final boolean consume2 = this.consume("even");
            final int n2 = 0;
            if (consume2) {
                cssTextScanner$AnPlusB = new CSSTextScanner.CSSTextScanner$AnPlusB(2, 0);
            }
            else {
                int n3 = 0;
                Label_0120: {
                    if (!this.consume('+')) {
                        if (this.consume('-')) {
                            n3 = -1;
                            break Label_0120;
                        }
                    }
                    n3 = 1;
                }
                IntegerParser int1 = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
                if (int1 != null) {
                    this.position = int1.getEndPos();
                }
                IntegerParser int2;
                if (!this.consume('n') && !this.consume('N')) {
                    int2 = int1;
                    int1 = null;
                }
                else {
                    if (int1 == null) {
                        int1 = new IntegerParser(1L, this.position);
                    }
                    this.skipWhitespace();
                    boolean b2 = this.consume('+');
                    int n4 = b ? 1 : 0;
                    if (!b2) {
                        final boolean b3 = b2 = this.consume((char)45);
                        n4 = (b ? 1 : 0);
                        if (b3) {
                            n4 = -1;
                            b2 = b3;
                        }
                    }
                    if (b2) {
                        this.skipWhitespace();
                        int2 = IntegerParser.parseInt(this.input, this.position, this.inputLength, false);
                        if (int2 == null) {
                            this.position = position;
                            return null;
                        }
                        this.position = int2.getEndPos();
                    }
                    else {
                        int2 = null;
                    }
                    n = n3;
                    n3 = n4;
                }
                int n5;
                if (int1 == null) {
                    n5 = 0;
                }
                else {
                    n5 = n * int1.value();
                }
                int n6;
                if (int2 == null) {
                    n6 = n2;
                }
                else {
                    n6 = n3 * int2.value();
                }
                cssTextScanner$AnPlusB = new CSSTextScanner.CSSTextScanner$AnPlusB(n5, n6);
            }
        }
        this.skipWhitespace();
        if (this.consume(')')) {
            return cssTextScanner$AnPlusB;
        }
        this.position = position;
        return null;
    }
    
    private String nextAttribValue() {
        if (this.empty()) {
            return null;
        }
        final String nextQuotedString = this.nextQuotedString();
        if (nextQuotedString != null) {
            return nextQuotedString;
        }
        return this.nextIdentifier();
    }
    
    private List<String> nextIdentListParam() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        if (!this.consume('(')) {
            return null;
        }
        this.skipWhitespace();
        ArrayList list = null;
        ArrayList list2;
        do {
            final String nextIdentifier = this.nextIdentifier();
            if (nextIdentifier == null) {
                this.position = position;
                return null;
            }
            if ((list2 = list) == null) {
                list2 = new ArrayList();
            }
            list2.add((Object)nextIdentifier);
            this.skipWhitespace();
            list = list2;
        } while (this.skipCommaWhitespace());
        if (this.consume(')')) {
            return (List<String>)list2;
        }
        this.position = position;
        return null;
    }
    
    private List<CSSParser$Selector> nextPseudoNotParam() throws CSSParseException {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        if (!this.consume('(')) {
            return null;
        }
        this.skipWhitespace();
        final List<CSSParser$Selector> nextSelectorGroup = this.nextSelectorGroup();
        if (nextSelectorGroup == null) {
            this.position = position;
            return null;
        }
        if (!this.consume(')')) {
            this.position = position;
            return null;
        }
        for (final CSSParser$Selector cssParser$Selector : nextSelectorGroup) {
            if (cssParser$Selector.simpleSelectors == null) {
                break;
            }
            for (final CSSParser$SimpleSelector cssParser$SimpleSelector : cssParser$Selector.simpleSelectors) {
                if (cssParser$SimpleSelector.pseudos == null) {
                    break;
                }
                final Iterator iterator3 = cssParser$SimpleSelector.pseudos.iterator();
                while (iterator3.hasNext()) {
                    if (((CSSParser$PseudoClass)iterator3.next()) instanceof CSSParser.PseudoClassNot) {
                        return null;
                    }
                }
            }
        }
        return nextSelectorGroup;
    }
    
    private void parsePseudoClass(final CSSParser$Selector cssParser$Selector, final CSSParser$SimpleSelector cssParser$SimpleSelector) throws CSSParseException {
        final String nextIdentifier = this.nextIdentifier();
        if (nextIdentifier != null) {
            final CSSParser$PseudoClassIdents fromString = CSSParser$PseudoClassIdents.fromString(nextIdentifier);
            Object o = null;
            switch (CSSTextScanner$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$CSSParser$PseudoClassIdents[fromString.ordinal()]) {
                default: {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported pseudo class: ");
                    sb.append(nextIdentifier);
                    throw new CSSParseException(sb.toString());
                }
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24: {
                    final CSSParser.PseudoClassNotSupported pseudoClassNotSupported = new CSSParser.PseudoClassNotSupported(nextIdentifier);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassNotSupported;
                    break;
                }
                case 15: {
                    this.nextIdentListParam();
                    final CSSParser.PseudoClassNotSupported pseudoClassNotSupported2 = new CSSParser.PseudoClassNotSupported(nextIdentifier);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassNotSupported2;
                    break;
                }
                case 14: {
                    final CSSParser.PseudoClassTarget pseudoClassTarget = new CSSParser.PseudoClassTarget();
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassTarget;
                    break;
                }
                case 13: {
                    final List<CSSParser$Selector> nextPseudoNotParam = this.nextPseudoNotParam();
                    if (nextPseudoNotParam != null) {
                        final CSSParser.PseudoClassNot pseudoClassNot = new CSSParser.PseudoClassNot(nextPseudoNotParam);
                        cssParser$Selector.specificity = ((CSSParser.PseudoClassNot)pseudoClassNot).getSpecificity();
                        o = pseudoClassNot;
                        break;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Invalid or missing parameter section for pseudo class: ");
                    sb2.append(nextIdentifier);
                    throw new CSSParseException(sb2.toString());
                }
                case 9:
                case 10:
                case 11:
                case 12: {
                    final boolean b = fromString == CSSParser$PseudoClassIdents.nth_child || fromString == CSSParser$PseudoClassIdents.nth_of_type;
                    final boolean b2 = fromString == CSSParser$PseudoClassIdents.nth_of_type || fromString == CSSParser$PseudoClassIdents.nth_last_of_type;
                    final CSSTextScanner.CSSTextScanner$AnPlusB nextAnPlusB = this.nextAnPlusB();
                    if (nextAnPlusB != null) {
                        final CSSParser.PseudoClassAnPlusB pseudoClassAnPlusB = new CSSParser.PseudoClassAnPlusB(nextAnPlusB.a, nextAnPlusB.b, b, b2, cssParser$SimpleSelector.tag);
                        cssParser$Selector.addedAttributeOrPseudo();
                        o = pseudoClassAnPlusB;
                        break;
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("Invalid or missing parameter section for pseudo class: ");
                    sb3.append(nextIdentifier);
                    throw new CSSParseException(sb3.toString());
                }
                case 8: {
                    final CSSParser.PseudoClassEmpty pseudoClassEmpty = new CSSParser.PseudoClassEmpty();
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassEmpty;
                    break;
                }
                case 7: {
                    final CSSParser.PseudoClassRoot pseudoClassRoot = new CSSParser.PseudoClassRoot();
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassRoot;
                    break;
                }
                case 6: {
                    final CSSParser.PseudoClassOnlyChild pseudoClassOnlyChild = new CSSParser.PseudoClassOnlyChild(true, cssParser$SimpleSelector.tag);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassOnlyChild;
                    break;
                }
                case 5: {
                    final CSSParser.PseudoClassAnPlusB pseudoClassAnPlusB2 = new CSSParser.PseudoClassAnPlusB(0, 1, false, true, cssParser$SimpleSelector.tag);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassAnPlusB2;
                    break;
                }
                case 4: {
                    final CSSParser.PseudoClassAnPlusB pseudoClassAnPlusB3 = new CSSParser.PseudoClassAnPlusB(0, 1, true, true, cssParser$SimpleSelector.tag);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassAnPlusB3;
                    break;
                }
                case 3: {
                    final CSSParser.PseudoClassOnlyChild pseudoClassOnlyChild2 = new CSSParser.PseudoClassOnlyChild(false, null);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassOnlyChild2;
                    break;
                }
                case 2: {
                    final CSSParser.PseudoClassAnPlusB pseudoClassAnPlusB4 = new CSSParser.PseudoClassAnPlusB(0, 1, false, false, null);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassAnPlusB4;
                    break;
                }
                case 1: {
                    final CSSParser.PseudoClassAnPlusB pseudoClassAnPlusB5 = new CSSParser.PseudoClassAnPlusB(0, 1, true, false, null);
                    cssParser$Selector.addedAttributeOrPseudo();
                    o = pseudoClassAnPlusB5;
                    break;
                }
            }
            cssParser$SimpleSelector.addPseudo((CSSParser$PseudoClass)o);
            return;
        }
        throw new CSSParseException("Invalid pseudo class");
    }
    
    private int scanForIdentifier() {
        if (this.empty()) {
            return this.position;
        }
        final int position = this.position;
        int n = this.position;
        int n2;
        if ((n2 = this.input.charAt(this.position)) == 45) {
            n2 = this.advanceChar();
        }
        if ((n2 >= 65 && n2 <= 90) || (n2 >= 97 && n2 <= 122) || n2 == 45 || n2 == 95 || n2 >= 128) {
            for (int n3 = this.advanceChar(); (n3 >= 65 && n3 <= 90) || (n3 >= 97 && n3 <= 122) || (n3 >= 48 && n3 <= 57) || n3 == 45 || n3 == 95 || n3 >= 128; n3 = this.advanceChar()) {}
            n = this.position;
        }
        this.position = position;
        return n;
    }
    
    public String nextCSSString() {
        if (this.empty()) {
            return null;
        }
        final char char1 = this.input.charAt(this.position);
        if (char1 != '\'' && char1 != '\"') {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        ++this.position;
        int n = this.nextChar();
        while (n != -1 && n != char1) {
            char c;
            if ((c = (char)n) == '\\') {
                n = this.nextChar();
                if (n == -1) {
                    continue;
                }
                if (n == '\n' || n == '\r' || n == '\f') {
                    n = this.nextChar();
                    continue;
                }
                int hexChar = this.hexChar(n);
                c = (char)n;
                if (hexChar != -1) {
                    for (int i = 1; i <= 5; ++i) {
                        n = this.nextChar();
                        final int hexChar2 = this.hexChar(n);
                        if (hexChar2 == -1) {
                            break;
                        }
                        hexChar = hexChar * 16 + hexChar2;
                    }
                    sb.append((char)hexChar);
                    continue;
                }
            }
            sb.append((char)c);
            n = this.nextChar();
        }
        return sb.toString();
    }
    
    public String nextIdentifier() {
        final int scanForIdentifier = this.scanForIdentifier();
        if (scanForIdentifier == this.position) {
            return null;
        }
        final String substring = this.input.substring(this.position, scanForIdentifier);
        this.position = scanForIdentifier;
        return substring;
    }
    
    String nextLegacyURL() {
        final StringBuilder sb = new StringBuilder();
        while (!this.empty()) {
            final char char1 = this.input.charAt(this.position);
            if (char1 == '\'' || char1 == '\"' || char1 == '(' || char1 == ')' || this.isWhitespace((int)char1)) {
                break;
            }
            if (Character.isISOControl((int)char1)) {
                break;
            }
            ++this.position;
            char char2;
            if ((char2 = char1) == '\\') {
                if (this.empty()) {
                    continue;
                }
                char2 = this.input.charAt(this.position++);
                if (char2 == '\n' || char2 == '\r') {
                    continue;
                }
                if (char2 == '\f') {
                    continue;
                }
                int hexChar = this.hexChar(char2);
                if (hexChar != -1) {
                    for (int i = 1; i <= 5; ++i) {
                        if (this.empty()) {
                            break;
                        }
                        final int hexChar2 = this.hexChar(this.input.charAt(this.position));
                        if (hexChar2 == -1) {
                            break;
                        }
                        ++this.position;
                        hexChar = hexChar * 16 + hexChar2;
                    }
                    sb.append((char)hexChar);
                    continue;
                }
            }
            sb.append((char)char2);
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
    
    public String nextPropertyValue() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        int position2 = this.position;
        for (int n = this.input.charAt(this.position); n != -1 && n != 59 && n != 125 && n != 33 && !this.isEOL(n); n = this.advanceChar()) {
            if (!this.isWhitespace(n)) {
                position2 = this.position + 1;
            }
        }
        if (this.position > position) {
            return this.input.substring(position, position2);
        }
        this.position = position;
        return null;
    }
    
    public List<CSSParser$Selector> nextSelectorGroup() throws CSSParseException {
        if (this.empty()) {
            return null;
        }
        final ArrayList list = new ArrayList(1);
        CSSParser$Selector cssParser$Selector = new CSSParser$Selector();
        while (!this.empty() && this.nextSimpleSelector(cssParser$Selector)) {
            if (!this.skipCommaWhitespace()) {
                continue;
            }
            list.add((Object)cssParser$Selector);
            cssParser$Selector = new CSSParser$Selector();
        }
        if (!cssParser$Selector.isEmpty()) {
            list.add((Object)cssParser$Selector);
        }
        return (List<CSSParser$Selector>)list;
    }
    
    boolean nextSimpleSelector(final CSSParser$Selector cssParser$Selector) throws CSSParseException {
        if (this.empty()) {
            return false;
        }
        final int position = this.position;
        CSSParser$Combinator cssParser$Combinator = null;
        Label_0066: {
            if (!cssParser$Selector.isEmpty()) {
                if (this.consume('>')) {
                    cssParser$Combinator = CSSParser$Combinator.CHILD;
                    this.skipWhitespace();
                    break Label_0066;
                }
                if (this.consume('+')) {
                    cssParser$Combinator = CSSParser$Combinator.FOLLOWS;
                    this.skipWhitespace();
                    break Label_0066;
                }
            }
            cssParser$Combinator = null;
        }
        CSSParser$SimpleSelector cssParser$SimpleSelector;
        if (this.consume('*')) {
            cssParser$SimpleSelector = new CSSParser$SimpleSelector(cssParser$Combinator, (String)null);
        }
        else {
            final String nextIdentifier = this.nextIdentifier();
            if (nextIdentifier != null) {
                cssParser$SimpleSelector = new CSSParser$SimpleSelector(cssParser$Combinator, nextIdentifier);
                cssParser$Selector.addedElement();
            }
            else {
                cssParser$SimpleSelector = null;
            }
        }
        while (!this.empty()) {
            if (this.consume('.')) {
                CSSParser$SimpleSelector cssParser$SimpleSelector2;
                if ((cssParser$SimpleSelector2 = cssParser$SimpleSelector) == null) {
                    cssParser$SimpleSelector2 = new CSSParser$SimpleSelector(cssParser$Combinator, (String)null);
                }
                final String nextIdentifier2 = this.nextIdentifier();
                if (nextIdentifier2 == null) {
                    throw new CSSParseException("Invalid \".class\" simpleSelectors");
                }
                cssParser$SimpleSelector2.addAttrib("class", CSSParser$AttribOp.EQUALS, nextIdentifier2);
                cssParser$Selector.addedAttributeOrPseudo();
                cssParser$SimpleSelector = cssParser$SimpleSelector2;
            }
            else if (this.consume('#')) {
                CSSParser$SimpleSelector cssParser$SimpleSelector3;
                if ((cssParser$SimpleSelector3 = cssParser$SimpleSelector) == null) {
                    cssParser$SimpleSelector3 = new CSSParser$SimpleSelector(cssParser$Combinator, (String)null);
                }
                final String nextIdentifier3 = this.nextIdentifier();
                if (nextIdentifier3 == null) {
                    throw new CSSParseException("Invalid \"#id\" simpleSelectors");
                }
                cssParser$SimpleSelector3.addAttrib("id", CSSParser$AttribOp.EQUALS, nextIdentifier3);
                cssParser$Selector.addedIdAttribute();
                cssParser$SimpleSelector = cssParser$SimpleSelector3;
            }
            else if (this.consume('[')) {
                CSSParser$SimpleSelector cssParser$SimpleSelector4;
                if ((cssParser$SimpleSelector4 = cssParser$SimpleSelector) == null) {
                    cssParser$SimpleSelector4 = new CSSParser$SimpleSelector(cssParser$Combinator, (String)null);
                }
                this.skipWhitespace();
                final String nextIdentifier4 = this.nextIdentifier();
                if (nextIdentifier4 == null) {
                    throw new CSSParseException("Invalid attribute simpleSelectors");
                }
                this.skipWhitespace();
                CSSParser$AttribOp cssParser$AttribOp;
                if (this.consume('=')) {
                    cssParser$AttribOp = CSSParser$AttribOp.EQUALS;
                }
                else if (this.consume("~=")) {
                    cssParser$AttribOp = CSSParser$AttribOp.INCLUDES;
                }
                else if (this.consume("|=")) {
                    cssParser$AttribOp = CSSParser$AttribOp.DASHMATCH;
                }
                else {
                    cssParser$AttribOp = null;
                }
                String nextAttribValue;
                if (cssParser$AttribOp != null) {
                    this.skipWhitespace();
                    nextAttribValue = this.nextAttribValue();
                    if (nextAttribValue == null) {
                        throw new CSSParseException("Invalid attribute simpleSelectors");
                    }
                    this.skipWhitespace();
                }
                else {
                    nextAttribValue = null;
                }
                if (!this.consume(']')) {
                    throw new CSSParseException("Invalid attribute simpleSelectors");
                }
                CSSParser$AttribOp exists;
                if ((exists = cssParser$AttribOp) == null) {
                    exists = CSSParser$AttribOp.EXISTS;
                }
                cssParser$SimpleSelector4.addAttrib(nextIdentifier4, exists, nextAttribValue);
                cssParser$Selector.addedAttributeOrPseudo();
                cssParser$SimpleSelector = cssParser$SimpleSelector4;
            }
            else {
                if (!this.consume(':')) {
                    break;
                }
                CSSParser$SimpleSelector cssParser$SimpleSelector5;
                if ((cssParser$SimpleSelector5 = cssParser$SimpleSelector) == null) {
                    cssParser$SimpleSelector5 = new CSSParser$SimpleSelector(cssParser$Combinator, (String)null);
                }
                this.parsePseudoClass(cssParser$Selector, cssParser$SimpleSelector5);
                cssParser$SimpleSelector = cssParser$SimpleSelector5;
            }
        }
        if (cssParser$SimpleSelector != null) {
            cssParser$Selector.add(cssParser$SimpleSelector);
            return true;
        }
        this.position = position;
        return false;
    }
    
    public String nextURL() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        if (!this.consume("url(")) {
            return null;
        }
        this.skipWhitespace();
        String s;
        if ((s = this.nextCSSString()) == null) {
            s = this.nextLegacyURL();
        }
        if (s == null) {
            this.position = position;
            return null;
        }
        this.skipWhitespace();
        if (!this.empty() && !this.consume(")")) {
            this.position = position;
            return null;
        }
        return s;
    }
}
