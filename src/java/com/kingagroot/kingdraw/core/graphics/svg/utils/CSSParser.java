package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.Collections;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;

public class CSSParser
{
    static final String CLASS = "class";
    static final String CSS_MIME_TYPE = "text/css";
    static final String ID = "id";
    private static final int SPECIFICITY_ATTRIBUTE_OR_PSEUDOCLASS = 1000;
    private static final int SPECIFICITY_ELEMENT_OR_PSEUDOELEMENT = 1;
    private static final int SPECIFICITY_ID_ATTRIBUTE = 1000000;
    private static final String TAG = "CSSParser";
    private MediaType deviceMediaType;
    private SVGExternalFileResolver externalFileResolver;
    private boolean inMediaRule;
    private Source source;
    
    CSSParser() {
        this(MediaType.screen, Source.Document, null);
    }
    
    CSSParser(final MediaType deviceMediaType, final Source source, final SVGExternalFileResolver externalFileResolver) {
        this.inMediaRule = false;
        this.deviceMediaType = deviceMediaType;
        this.source = source;
        this.externalFileResolver = externalFileResolver;
    }
    
    CSSParser(final Source source, final SVGExternalFileResolver svgExternalFileResolver) {
        this(MediaType.screen, source, svgExternalFileResolver);
    }
    
    private static int getChildPosition(final List<SVGBase.SvgContainer> list, int n, final SVGBase$SvgElementBase svgBase$SvgElementBase) {
        final int n2 = 0;
        if (n < 0) {
            return 0;
        }
        if (list.get(n) != svgBase$SvgElementBase.parent) {
            return -1;
        }
        final Iterator iterator = svgBase$SvgElementBase.parent.getChildren().iterator();
        n = n2;
        while (iterator.hasNext()) {
            if (iterator.next() == svgBase$SvgElementBase) {
                return n;
            }
            ++n;
        }
        return -1;
    }
    
    static boolean mediaMatches(final String s, final MediaType mediaType) {
        final CSSTextScanner cssTextScanner = new CSSTextScanner(s);
        cssTextScanner.skipWhitespace();
        return mediaMatches(parseMediaList(cssTextScanner), mediaType);
    }
    
    private static boolean mediaMatches(final List<MediaType> list, final MediaType mediaType) {
        if (list.size() == 0) {
            return true;
        }
        for (final MediaType mediaType2 : list) {
            if (mediaType2 == MediaType.all || mediaType2 == mediaType) {
                return true;
            }
        }
        return false;
    }
    
    private void parseAtRule(final Ruleset ruleset, final CSSTextScanner cssTextScanner) throws CSSParseException {
        final String nextIdentifier = cssTextScanner.nextIdentifier();
        cssTextScanner.skipWhitespace();
        if (nextIdentifier != null) {
            if (!this.inMediaRule && nextIdentifier.equals((Object)"media")) {
                final List<MediaType> mediaList = parseMediaList(cssTextScanner);
                if (!cssTextScanner.consume('{')) {
                    throw new CSSParseException("Invalid @media rule: missing rule set");
                }
                cssTextScanner.skipWhitespace();
                if (mediaMatches(mediaList, this.deviceMediaType)) {
                    this.inMediaRule = true;
                    ruleset.addAll(this.parseRuleset(cssTextScanner));
                    this.inMediaRule = false;
                }
                else {
                    this.parseRuleset(cssTextScanner);
                }
                if (!cssTextScanner.empty()) {
                    if (!cssTextScanner.consume('}')) {
                        throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
                    }
                }
            }
            else if (!this.inMediaRule && nextIdentifier.equals((Object)"import")) {
                String s;
                if ((s = cssTextScanner.nextURL()) == null) {
                    s = cssTextScanner.nextCSSString();
                }
                if (s == null) {
                    throw new CSSParseException("Invalid @import rule: expected string or url()");
                }
                cssTextScanner.skipWhitespace();
                final List<MediaType> mediaList2 = parseMediaList(cssTextScanner);
                if (!cssTextScanner.empty() && !cssTextScanner.consume(';')) {
                    throw new CSSParseException("Invalid @media rule: expected '}' at end of rule set");
                }
                if (this.externalFileResolver != null && mediaMatches(mediaList2, this.deviceMediaType)) {
                    final String resolveCSSStyleSheet = this.externalFileResolver.resolveCSSStyleSheet(s);
                    if (resolveCSSStyleSheet == null) {
                        return;
                    }
                    ruleset.addAll(this.parse(resolveCSSStyleSheet));
                }
            }
            else {
                warn("Ignoring @%s rule", nextIdentifier);
                this.skipAtRule(cssTextScanner);
            }
            cssTextScanner.skipWhitespace();
            return;
        }
        throw new CSSParseException("Invalid '@' rule");
    }
    
    public static List<String> parseClassAttribute(final String s) {
        final CSSTextScanner cssTextScanner = new CSSTextScanner(s);
        List list = null;
        while (!cssTextScanner.empty()) {
            final String nextToken = cssTextScanner.nextToken();
            if (nextToken == null) {
                continue;
            }
            Object o;
            if ((o = list) == null) {
                o = new ArrayList();
            }
            ((List)o).add((Object)nextToken);
            cssTextScanner.skipWhitespace();
            list = (List)o;
        }
        return (List<String>)list;
    }
    
    private Style parseDeclarations(final CSSTextScanner cssTextScanner) throws CSSParseException {
        final Style style = new Style();
        do {
            final String nextIdentifier = cssTextScanner.nextIdentifier();
            cssTextScanner.skipWhitespace();
            if (!cssTextScanner.consume(':')) {
                throw new CSSParseException("Expected ':'");
            }
            cssTextScanner.skipWhitespace();
            final String nextPropertyValue = cssTextScanner.nextPropertyValue();
            if (nextPropertyValue == null) {
                throw new CSSParseException("Expected property value");
            }
            cssTextScanner.skipWhitespace();
            if (cssTextScanner.consume('!')) {
                cssTextScanner.skipWhitespace();
                if (!cssTextScanner.consume("important")) {
                    throw new CSSParseException("Malformed rule set: found unexpected '!'");
                }
                cssTextScanner.skipWhitespace();
            }
            cssTextScanner.consume(';');
            Style.processStyleProperty(style, nextIdentifier, nextPropertyValue, false);
            cssTextScanner.skipWhitespace();
        } while (!cssTextScanner.empty() && !cssTextScanner.consume('}'));
        return style;
    }
    
    private static List<MediaType> parseMediaList(final CSSTextScanner cssTextScanner) {
        final ArrayList list = new ArrayList();
        while (!cssTextScanner.empty()) {
            final String nextWord = cssTextScanner.nextWord();
            if (nextWord == null) {
                break;
            }
            try {
                list.add((Object)MediaType.valueOf(nextWord));
            }
            catch (final IllegalArgumentException ex) {}
            if (!cssTextScanner.skipCommaWhitespace()) {
                break;
            }
        }
        return (List<MediaType>)list;
    }
    
    private boolean parseRule(final Ruleset ruleset, final CSSTextScanner cssTextScanner) throws CSSParseException {
        final List nextSelectorGroup = cssTextScanner.nextSelectorGroup();
        if (nextSelectorGroup == null || nextSelectorGroup.isEmpty()) {
            return false;
        }
        if (cssTextScanner.consume('{')) {
            cssTextScanner.skipWhitespace();
            final Style declarations = this.parseDeclarations(cssTextScanner);
            cssTextScanner.skipWhitespace();
            final Iterator iterator = nextSelectorGroup.iterator();
            while (iterator.hasNext()) {
                ruleset.add(new Rule((Selector)iterator.next(), declarations, this.source));
            }
            return true;
        }
        throw new CSSParseException("Malformed rule block: expected '{'");
    }
    
    private Ruleset parseRuleset(final CSSTextScanner cssTextScanner) {
        final Ruleset ruleset = new Ruleset();
        try {
            while (!cssTextScanner.empty()) {
                if (cssTextScanner.consume("<!--")) {
                    continue;
                }
                if (cssTextScanner.consume("-->")) {
                    continue;
                }
                if (cssTextScanner.consume('@')) {
                    this.parseAtRule(ruleset, cssTextScanner);
                }
                else {
                    if (this.parseRule(ruleset, cssTextScanner)) {
                        continue;
                    }
                    break;
                }
            }
        }
        catch (final CSSParseException ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("CSS parser terminated early due to error: ");
            sb.append(ex.getMessage());
            Log.e("CSSParser", sb.toString());
        }
        return ruleset;
    }
    
    private static boolean ruleMatch(final RuleMatchContext ruleMatchContext, final Selector selector, final int n, final List<SVGBase.SvgContainer> list, int i, final SVGBase$SvgElementBase svgBase$SvgElementBase) {
        final SimpleSelector value = selector.get(n);
        if (!selectorMatch(ruleMatchContext, value, svgBase$SvgElementBase)) {
            return false;
        }
        if (value.combinator == Combinator.DESCENDANT) {
            if (n == 0) {
                return true;
            }
            while (i >= 0) {
                if (ruleMatchOnAncestors(ruleMatchContext, selector, n - 1, list, i)) {
                    return true;
                }
                --i;
            }
            return false;
        }
        else {
            if (value.combinator == Combinator.CHILD) {
                return ruleMatchOnAncestors(ruleMatchContext, selector, n - 1, list, i);
            }
            final int childPosition = getChildPosition(list, i, svgBase$SvgElementBase);
            return childPosition > 0 && ruleMatch(ruleMatchContext, selector, n - 1, list, i, (SVGBase$SvgElementBase)svgBase$SvgElementBase.parent.getChildren().get(childPosition - 1));
        }
    }
    
    static boolean ruleMatch(final RuleMatchContext ruleMatchContext, final Selector selector, final SVGBase$SvgElementBase svgBase$SvgElementBase) {
        if (selector.size() == 1) {
            return selectorMatch(ruleMatchContext, selector.get(0), svgBase$SvgElementBase);
        }
        final ArrayList list = new ArrayList();
        for (SVGBase.SvgContainer svgContainer = svgBase$SvgElementBase.parent; svgContainer != null; svgContainer = ((SVGBase.SvgObject)svgContainer).parent) {
            ((List)list).add((Object)svgContainer);
        }
        Collections.reverse((List)list);
        return ruleMatch(ruleMatchContext, selector, selector.size() - 1, (List<SVGBase.SvgContainer>)list, ((List)list).size() - 1, svgBase$SvgElementBase);
    }
    
    private static boolean ruleMatchOnAncestors(final RuleMatchContext ruleMatchContext, final Selector selector, final int n, final List<SVGBase.SvgContainer> list, int i) {
        final SimpleSelector value = selector.get(n);
        final SVGBase$SvgElementBase svgBase$SvgElementBase = (SVGBase$SvgElementBase)list.get(i);
        if (!selectorMatch(ruleMatchContext, value, svgBase$SvgElementBase)) {
            return false;
        }
        if (value.combinator == Combinator.DESCENDANT) {
            if (n == 0) {
                return true;
            }
            while (i > 0) {
                if (ruleMatchOnAncestors(ruleMatchContext, selector, n - 1, list, --i)) {
                    return true;
                }
            }
            return false;
        }
        else {
            if (value.combinator == Combinator.CHILD) {
                return ruleMatchOnAncestors(ruleMatchContext, selector, n - 1, list, i - 1);
            }
            final int childPosition = getChildPosition(list, i, svgBase$SvgElementBase);
            return childPosition > 0 && ruleMatch(ruleMatchContext, selector, n - 1, list, i, (SVGBase$SvgElementBase)svgBase$SvgElementBase.parent.getChildren().get(childPosition - 1));
        }
    }
    
    private static boolean selectorMatch(final RuleMatchContext ruleMatchContext, final SimpleSelector simpleSelector, final SVGBase$SvgElementBase svgBase$SvgElementBase) {
        if (simpleSelector.tag != null && !simpleSelector.tag.equals((Object)svgBase$SvgElementBase.getNodeName().toLowerCase(Locale.US))) {
            return false;
        }
        if (simpleSelector.attribs != null) {
            for (int size = simpleSelector.attribs.size(), i = 0; i < size; ++i) {
                final Attrib attrib = (Attrib)simpleSelector.attribs.get(i);
                final String name = attrib.name;
                int n = -1;
                final int hashCode = name.hashCode();
                if (hashCode != 3355) {
                    if (hashCode == 94742904) {
                        if (name.equals((Object)"class")) {
                            n = 1;
                        }
                    }
                }
                else if (name.equals((Object)"id")) {
                    n = 0;
                }
                if (n != 0) {
                    if (n != 1) {
                        return false;
                    }
                    if (svgBase$SvgElementBase.classNames == null) {
                        return false;
                    }
                    if (!svgBase$SvgElementBase.classNames.contains((Object)attrib.value)) {
                        return false;
                    }
                }
                else if (!attrib.value.equals((Object)svgBase$SvgElementBase.id)) {
                    return false;
                }
            }
        }
        if (simpleSelector.pseudos != null) {
            for (int size2 = simpleSelector.pseudos.size(), j = 0; j < size2; ++j) {
                if (!((PseudoClass)simpleSelector.pseudos.get(j)).matches(ruleMatchContext, svgBase$SvgElementBase)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void skipAtRule(final CSSTextScanner cssTextScanner) {
        int n = 0;
        while (!cssTextScanner.empty()) {
            final int intValue = cssTextScanner.nextChar();
            if (intValue == 59 && n == 0) {
                return;
            }
            if (intValue == 123) {
                ++n;
            }
            else {
                if (intValue == 125 && n > 0 && --n == 0) {
                    break;
                }
                continue;
            }
        }
    }
    
    private static void warn(final String s, final Object... array) {
        Log.w("CSSParser", String.format(s, array));
    }
    
    Ruleset parse(final String s) {
        final CSSTextScanner cssTextScanner = new CSSTextScanner(s);
        cssTextScanner.skipWhitespace();
        return this.parseRuleset(cssTextScanner);
    }
    
    private static class Attrib
    {
        public final String name;
        final AttribOp operation;
        public final String value;
        
        Attrib(final String name, final AttribOp operation, final String value) {
            this.name = name;
            this.operation = operation;
            this.value = value;
        }
    }
    
    enum AttribOp
    {
        private static final AttribOp[] $VALUES;
        
        DASHMATCH, 
        EQUALS, 
        EXISTS, 
        INCLUDES;
    }
    
    enum Combinator
    {
        private static final Combinator[] $VALUES;
        
        CHILD, 
        DESCENDANT, 
        FOLLOWS;
    }
    
    enum MediaType
    {
        private static final MediaType[] $VALUES;
        
        all, 
        aural, 
        braille, 
        embossed, 
        handheld, 
        print, 
        projection, 
        screen, 
        speech, 
        tty, 
        tv;
    }
    
    interface PseudoClass
    {
        boolean matches(final RuleMatchContext p0, final SVGBase$SvgElementBase p1);
    }
    
    enum PseudoClassIdents
    {
        private static final PseudoClassIdents[] $VALUES;
        
        UNSUPPORTED, 
        active;
        
        private static final Map<String, PseudoClassIdents> cache;
        
        checked, 
        disabled, 
        empty, 
        enabled, 
        first_child, 
        first_of_type, 
        focus, 
        hover, 
        indeterminate, 
        lang, 
        last_child, 
        last_of_type, 
        link, 
        not, 
        nth_child, 
        nth_last_child, 
        nth_last_of_type, 
        nth_of_type, 
        only_child, 
        only_of_type, 
        root, 
        target, 
        visited;
        
        static {
            int i = 0;
            cache = (Map)new HashMap();
            for (PseudoClassIdents[] values = values(); i < values.length; ++i) {
                final PseudoClassIdents pseudoClassIdents = values[i];
                if (pseudoClassIdents != PseudoClassIdents.UNSUPPORTED) {
                    PseudoClassIdents.cache.put((Object)pseudoClassIdents.name().replace('_', '-'), (Object)pseudoClassIdents);
                }
            }
        }
        
        public static PseudoClassIdents fromString(final String s) {
            final PseudoClassIdents pseudoClassIdents = (PseudoClassIdents)PseudoClassIdents.cache.get((Object)s);
            if (pseudoClassIdents != null) {
                return pseudoClassIdents;
            }
            return PseudoClassIdents.UNSUPPORTED;
        }
    }
    
    public static class Rule
    {
        final Selector selector;
        final Source source;
        final Style style;
        
        Rule(final Selector selector, final Style style, final Source source) {
            this.selector = selector;
            this.style = style;
            this.source = source;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append((Object)this.selector);
            sb.append(" {...} (src=");
            sb.append((Object)this.source);
            sb.append(")");
            return sb.toString();
        }
    }
    
    static class RuleMatchContext
    {
        SVGBase$SvgElementBase targetElement;
        
        @Override
        public String toString() {
            final SVGBase$SvgElementBase targetElement = this.targetElement;
            if (targetElement != null) {
                return String.format("<%s id=\"%s\">", new Object[] { targetElement.getNodeName(), this.targetElement.id });
            }
            return "";
        }
    }
    
    public static class Ruleset
    {
        private List<Rule> rules;
        
        public Ruleset() {
            this.rules = null;
        }
        
        void add(final Rule rule) {
            if (this.rules == null) {
                this.rules = (List<Rule>)new LinkedList();
            }
            final ListIterator listIterator = this.rules.listIterator();
            while (listIterator.hasNext()) {
                final int nextIndex = listIterator.nextIndex();
                if (((Rule)listIterator.next()).selector.specificity > rule.selector.specificity) {
                    this.rules.add(nextIndex, (Object)rule);
                    return;
                }
            }
            this.rules.add((Object)rule);
        }
        
        public void addAll(final Ruleset ruleset) {
            if (ruleset.rules == null) {
                return;
            }
            if (this.rules == null) {
                this.rules = (List<Rule>)new LinkedList();
            }
            final Iterator iterator = ruleset.rules.iterator();
            while (iterator.hasNext()) {
                this.add((Rule)iterator.next());
            }
        }
        
        public List<Rule> getRules() {
            return this.rules;
        }
        
        public boolean isEmpty() {
            final List<Rule> rules = this.rules;
            return rules == null || rules.isEmpty();
        }
        
        public void removeFromSource(final Source source) {
            final List<Rule> rules = this.rules;
            if (rules == null) {
                return;
            }
            final Iterator iterator = rules.iterator();
            while (iterator.hasNext()) {
                if (((Rule)iterator.next()).source == source) {
                    iterator.remove();
                }
            }
        }
        
        int ruleCount() {
            final List<Rule> rules = this.rules;
            int size;
            if (rules != null) {
                size = rules.size();
            }
            else {
                size = 0;
            }
            return size;
        }
        
        @Override
        public String toString() {
            if (this.rules == null) {
                return "";
            }
            final StringBuilder sb = new StringBuilder();
            final Iterator iterator = this.rules.iterator();
            while (iterator.hasNext()) {
                sb.append(((Rule)iterator.next()).toString());
                sb.append('\n');
            }
            return sb.toString();
        }
    }
    
    static class Selector
    {
        List<SimpleSelector> simpleSelectors;
        int specificity;
        
        Selector() {
            this.simpleSelectors = null;
            this.specificity = 0;
        }
        
        void add(final SimpleSelector simpleSelector) {
            if (this.simpleSelectors == null) {
                this.simpleSelectors = (List<SimpleSelector>)new ArrayList();
            }
            this.simpleSelectors.add((Object)simpleSelector);
        }
        
        void addedAttributeOrPseudo() {
            this.specificity += 1000;
        }
        
        void addedElement() {
            ++this.specificity;
        }
        
        void addedIdAttribute() {
            this.specificity += 1000000;
        }
        
        SimpleSelector get(final int n) {
            return (SimpleSelector)this.simpleSelectors.get(n);
        }
        
        boolean isEmpty() {
            final List<SimpleSelector> simpleSelectors = this.simpleSelectors;
            return simpleSelectors == null || simpleSelectors.isEmpty();
        }
        
        int size() {
            final List<SimpleSelector> simpleSelectors = this.simpleSelectors;
            int size;
            if (simpleSelectors == null) {
                size = 0;
            }
            else {
                size = simpleSelectors.size();
            }
            return size;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            final Iterator iterator = this.simpleSelectors.iterator();
            while (iterator.hasNext()) {
                sb.append((Object)iterator.next());
                sb.append(' ');
            }
            sb.append('[');
            sb.append(this.specificity);
            sb.append(']');
            return sb.toString();
        }
    }
    
    static class SimpleSelector
    {
        List<Attrib> attribs;
        Combinator combinator;
        List<PseudoClass> pseudos;
        String tag;
        
        SimpleSelector(Combinator descendant, final String tag) {
            this.attribs = null;
            this.pseudos = null;
            if (descendant == null) {
                descendant = Combinator.DESCENDANT;
            }
            this.combinator = descendant;
            this.tag = tag;
        }
        
        void addAttrib(final String s, final AttribOp attribOp, final String s2) {
            if (this.attribs == null) {
                this.attribs = (List<Attrib>)new ArrayList();
            }
            this.attribs.add((Object)new Attrib(s, attribOp, s2));
        }
        
        void addPseudo(final PseudoClass pseudoClass) {
            if (this.pseudos == null) {
                this.pseudos = (List<PseudoClass>)new ArrayList();
            }
            this.pseudos.add((Object)pseudoClass);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            if (this.combinator == Combinator.CHILD) {
                sb.append("> ");
            }
            else if (this.combinator == Combinator.FOLLOWS) {
                sb.append("+ ");
            }
            String tag;
            if ((tag = this.tag) == null) {
                tag = "*";
            }
            sb.append(tag);
            final List<Attrib> attribs = this.attribs;
            if (attribs != null) {
                for (final Attrib attrib : attribs) {
                    sb.append('[');
                    sb.append(attrib.name);
                    final int n = CSSParser$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$CSSParser$AttribOp[attrib.operation.ordinal()];
                    if (n != 1) {
                        if (n != 2) {
                            if (n == 3) {
                                sb.append("|=");
                                sb.append(attrib.value);
                            }
                        }
                        else {
                            sb.append("~=");
                            sb.append(attrib.value);
                        }
                    }
                    else {
                        sb.append('=');
                        sb.append(attrib.value);
                    }
                    sb.append(']');
                }
            }
            final List<PseudoClass> pseudos = this.pseudos;
            if (pseudos != null) {
                for (final PseudoClass pseudoClass : pseudos) {
                    sb.append(':');
                    sb.append((Object)pseudoClass);
                }
            }
            return sb.toString();
        }
    }
    
    public enum Source
    {
        private static final Source[] $VALUES;
        
        Document, 
        RenderOptions;
    }
}
