package org.ccil.cowan.tagsoup;

import java.util.HashMap;

public abstract class Schema
{
    public static final int F_CDATA = 2;
    public static final int F_NOFORCE = 4;
    public static final int F_RESTART = 1;
    public static final int M_ANY = -1;
    public static final int M_EMPTY = 0;
    public static final int M_PCDATA = 1073741824;
    public static final int M_ROOT = Integer.MIN_VALUE;
    private HashMap theElementTypes;
    private HashMap theEntities;
    private String thePrefix;
    private ElementType theRoot;
    private String theURI;
    
    public Schema() {
        this.theEntities = new HashMap();
        this.theElementTypes = new HashMap();
        this.theURI = "";
        this.thePrefix = "";
        this.theRoot = null;
    }
    
    public void attribute(final String s, final String s2, final String s3, final String s4) {
        final ElementType elementType = this.getElementType(s);
        if (elementType != null) {
            elementType.setAttribute(s2, s3, s4);
            return;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("Attribute ");
        sb.append(s2);
        sb.append(" specified for unknown element type ");
        sb.append(s);
        throw new Error(sb.toString());
    }
    
    public void elementType(final String s, final int n, final int n2, final int n3) {
        final ElementType theRoot = new ElementType(s, n, n2, n3, this);
        this.theElementTypes.put((Object)s.toLowerCase(), (Object)theRoot);
        if (n2 == Integer.MIN_VALUE) {
            this.theRoot = theRoot;
        }
    }
    
    public void entity(final String s, final int n) {
        this.theEntities.put((Object)s, (Object)new Integer(n));
    }
    
    public ElementType getElementType(final String s) {
        return (ElementType)this.theElementTypes.get((Object)s.toLowerCase());
    }
    
    public int getEntity(final String s) {
        final Integer n = (Integer)this.theEntities.get((Object)s);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public String getPrefix() {
        return this.thePrefix;
    }
    
    public String getURI() {
        return this.theURI;
    }
    
    public void parent(final String s, final String s2) {
        final ElementType elementType = this.getElementType(s);
        final ElementType elementType2 = this.getElementType(s2);
        if (elementType == null) {
            final StringBuffer sb = new StringBuffer();
            sb.append("No child ");
            sb.append(s);
            sb.append(" for parent ");
            sb.append(s2);
            throw new Error(sb.toString());
        }
        if (elementType2 != null) {
            elementType.setParent(elementType2);
            return;
        }
        final StringBuffer sb2 = new StringBuffer();
        sb2.append("No parent ");
        sb2.append(s2);
        sb2.append(" for child ");
        sb2.append(s);
        throw new Error(sb2.toString());
    }
    
    public ElementType rootElementType() {
        return this.theRoot;
    }
    
    public void setPrefix(final String thePrefix) {
        this.thePrefix = thePrefix;
    }
    
    public void setURI(final String theURI) {
        this.theURI = theURI;
    }
}
