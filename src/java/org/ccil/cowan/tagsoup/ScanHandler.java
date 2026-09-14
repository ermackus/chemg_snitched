package org.ccil.cowan.tagsoup;

import org.xml.sax.SAXException;

public interface ScanHandler
{
    void adup(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void aname(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void aval(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void cdsect(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void cmnt(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void decl(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void entity(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void eof(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void etag(final char[] p0, final int p1, final int p2) throws SAXException;
    
    int getEntity();
    
    void gi(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void pcdata(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void pi(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void pitarget(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void stagc(final char[] p0, final int p1, final int p2) throws SAXException;
    
    void stage(final char[] p0, final int p1, final int p2) throws SAXException;
}
