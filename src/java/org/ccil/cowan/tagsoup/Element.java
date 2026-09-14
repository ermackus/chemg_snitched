package org.ccil.cowan.tagsoup;

import org.xml.sax.Attributes;

public class Element
{
    private boolean preclosed;
    private AttributesImpl theAtts;
    private Element theNext;
    private ElementType theType;
    
    public Element(final ElementType theType, final boolean b) {
        this.theType = theType;
        if (b) {
            this.theAtts = new AttributesImpl((Attributes)theType.atts());
        }
        else {
            this.theAtts = new AttributesImpl();
        }
        this.theNext = null;
        this.preclosed = false;
    }
    
    public void anonymize() {
        for (int i = this.theAtts.getLength() - 1; i >= 0; --i) {
            if (this.theAtts.getType(i).equals((Object)"ID") || this.theAtts.getQName(i).equals((Object)"name")) {
                this.theAtts.removeAttribute(i);
            }
        }
    }
    
    public AttributesImpl atts() {
        return this.theAtts;
    }
    
    public boolean canContain(final Element element) {
        return this.theType.canContain(element.theType);
    }
    
    public void clean() {
        for (int i = this.theAtts.getLength() - 1; i >= 0; --i) {
            final String localName = this.theAtts.getLocalName(i);
            if (this.theAtts.getValue(i) == null || localName == null || localName.length() == 0) {
                this.theAtts.removeAttribute(i);
            }
        }
    }
    
    public int flags() {
        return this.theType.flags();
    }
    
    public boolean isPreclosed() {
        return this.preclosed;
    }
    
    public String localName() {
        return this.theType.localName();
    }
    
    public int memberOf() {
        return this.theType.memberOf();
    }
    
    public int model() {
        return this.theType.model();
    }
    
    public String name() {
        return this.theType.name();
    }
    
    public String namespace() {
        return this.theType.namespace();
    }
    
    public Element next() {
        return this.theNext;
    }
    
    public ElementType parent() {
        return this.theType.parent();
    }
    
    public void preclose() {
        this.preclosed = true;
    }
    
    public void setAttribute(final String s, final String s2, final String s3) {
        this.theType.setAttribute(this.theAtts, s, s2, s3);
    }
    
    public void setNext(final Element theNext) {
        this.theNext = theNext;
    }
    
    public ElementType type() {
        return this.theType;
    }
}
