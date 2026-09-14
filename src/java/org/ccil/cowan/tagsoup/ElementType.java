package org.ccil.cowan.tagsoup;

public class ElementType
{
    private AttributesImpl theAtts;
    private int theFlags;
    private String theLocalName;
    private int theMemberOf;
    private int theModel;
    private String theName;
    private String theNamespace;
    private ElementType theParent;
    private Schema theSchema;
    
    public ElementType(final String theName, final int theModel, final int theMemberOf, final int theFlags, final Schema theSchema) {
        this.theName = theName;
        this.theModel = theModel;
        this.theMemberOf = theMemberOf;
        this.theFlags = theFlags;
        this.theAtts = new AttributesImpl();
        this.theSchema = theSchema;
        this.theNamespace = this.namespace(theName, false);
        this.theLocalName = this.localName(theName);
    }
    
    public static String normalize(String trim) {
        if (trim == null) {
            return trim;
        }
        trim = trim.trim();
        if (trim.indexOf("  ") == -1) {
            return trim;
        }
        final int length = trim.length();
        final StringBuffer sb = new StringBuffer(length);
        int i = 0;
        int n = 0;
        while (i < length) {
            final char char1 = trim.charAt(i);
            if (char1 == ' ') {
                if (n == 0) {
                    sb.append(char1);
                }
                n = 1;
            }
            else {
                sb.append(char1);
                n = 0;
            }
            ++i;
        }
        return sb.toString();
    }
    
    public AttributesImpl atts() {
        return this.theAtts;
    }
    
    public boolean canContain(final ElementType elementType) {
        return (elementType.theMemberOf & this.theModel) != 0x0;
    }
    
    public int flags() {
        return this.theFlags;
    }
    
    public String localName() {
        return this.theLocalName;
    }
    
    public String localName(final String s) {
        final int index = s.indexOf(58);
        if (index == -1) {
            return s;
        }
        return s.substring(index + 1).intern();
    }
    
    public int memberOf() {
        return this.theMemberOf;
    }
    
    public int model() {
        return this.theModel;
    }
    
    public String name() {
        return this.theName;
    }
    
    public String namespace() {
        return this.theNamespace;
    }
    
    public String namespace(String s, final boolean b) {
        final int index = s.indexOf(58);
        if (index == -1) {
            if (b) {
                s = "";
            }
            else {
                s = this.theSchema.getURI();
            }
            return s;
        }
        s = s.substring(0, index);
        if (s.equals((Object)"xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("urn:x-prefix:");
        sb.append(s);
        return sb.toString().intern();
    }
    
    public ElementType parent() {
        return this.theParent;
    }
    
    public Schema schema() {
        return this.theSchema;
    }
    
    public void setAttribute(final String s, final String s2, final String s3) {
        this.setAttribute(this.theAtts, s, s2, s3);
    }
    
    public void setAttribute(final AttributesImpl attributesImpl, String s, String s2, final String s3) {
        if (!s.equals((Object)"xmlns")) {
            if (!s.startsWith("xmlns:")) {
                final String namespace = this.namespace(s, true);
                final String localName = this.localName(s);
                final int index = attributesImpl.getIndex(s);
                if (index == -1) {
                    final String intern = s.intern();
                    if (s2 == null) {
                        s = "CDATA";
                    }
                    else {
                        s = s2;
                    }
                    s2 = s3;
                    if (!s.equals((Object)"CDATA")) {
                        s2 = normalize(s3);
                    }
                    attributesImpl.addAttribute(namespace, localName, intern, s, s2);
                }
                else {
                    String type;
                    if ((type = s2) == null) {
                        type = attributesImpl.getType(index);
                    }
                    s2 = s3;
                    if (!type.equals((Object)"CDATA")) {
                        s2 = normalize(s3);
                    }
                    attributesImpl.setAttribute(index, namespace, localName, s, type, s2);
                }
            }
        }
    }
    
    public void setFlags(final int theFlags) {
        this.theFlags = theFlags;
    }
    
    public void setMemberOf(final int theMemberOf) {
        this.theMemberOf = theMemberOf;
    }
    
    public void setModel(final int theModel) {
        this.theModel = theModel;
    }
    
    public void setParent(final ElementType theParent) {
        this.theParent = theParent;
    }
}
