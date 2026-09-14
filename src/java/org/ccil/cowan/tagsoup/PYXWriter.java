package org.ccil.cowan.tagsoup;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import java.io.Writer;
import java.io.PrintWriter;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class PYXWriter implements ScanHandler, ContentHandler, LexicalHandler
{
    private static char[] dummy;
    private String attrName;
    private PrintWriter theWriter;
    
    static {
        PYXWriter.dummy = new char[1];
    }
    
    public PYXWriter(final Writer writer) {
        if (writer instanceof PrintWriter) {
            this.theWriter = (PrintWriter)writer;
        }
        else {
            this.theWriter = new PrintWriter(writer);
        }
    }
    
    @Override
    public void adup(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.println(this.attrName);
        this.attrName = null;
    }
    
    @Override
    public void aname(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.print('A');
        this.theWriter.write(array, n, n2);
        this.theWriter.print(' ');
        this.attrName = new String(array, n, n2);
    }
    
    @Override
    public void aval(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.write(array, n, n2);
        this.theWriter.println();
        this.attrName = null;
    }
    
    @Override
    public void cdsect(final char[] array, final int n, final int n2) throws SAXException {
        this.pcdata(array, n, n2);
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        this.pcdata(array, n, n2);
    }
    
    @Override
    public void cmnt(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void comment(final char[] array, final int n, final int n2) throws SAXException {
        this.cmnt(array, n, n2);
    }
    
    @Override
    public void decl(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void endDTD() throws SAXException {
    }
    
    public void endDocument() throws SAXException {
        this.theWriter.close();
    }
    
    public void endElement(final String s, String s2, final String s3) throws SAXException {
        if (s3.length() != 0) {
            s2 = s3;
        }
        this.theWriter.print(')');
        this.theWriter.println(s2);
    }
    
    public void endEntity(final String s) throws SAXException {
    }
    
    public void endPrefixMapping(final String s) throws SAXException {
    }
    
    @Override
    public void entity(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    @Override
    public void eof(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.close();
    }
    
    @Override
    public void etag(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.print(')');
        this.theWriter.write(array, n, n2);
        this.theWriter.println();
    }
    
    @Override
    public int getEntity() {
        return 0;
    }
    
    @Override
    public void gi(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.print('(');
        this.theWriter.write(array, n, n2);
        this.theWriter.println();
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
        this.characters(array, n, n2);
    }
    
    @Override
    public void pcdata(final char[] array, final int n, final int n2) throws SAXException {
        if (n2 == 0) {
            return;
        }
        int n3 = 0;
        int n4 = n;
        while (true) {
            int n5 = n4;
            if (n5 >= n2 + n) {
                break;
            }
            int n6;
            if (array[n5] == '\n') {
                if (n3 != 0) {
                    this.theWriter.println();
                }
                this.theWriter.println("-\\n");
                n6 = 0;
            }
            else {
                if (n3 == 0) {
                    this.theWriter.print('-');
                }
                final char c = array[n5];
                if (c != '\t') {
                    if (c != '\\') {
                        this.theWriter.print(array[n5]);
                    }
                    else {
                        this.theWriter.print("\\\\");
                    }
                }
                else {
                    this.theWriter.print("\\t");
                }
                n6 = 1;
            }
            ++n5;
            n3 = n6;
            n4 = n5;
        }
        if (n3 != 0) {
            this.theWriter.println();
        }
    }
    
    @Override
    public void pi(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.write(array, n, n2);
        this.theWriter.println();
    }
    
    @Override
    public void pitarget(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.print('?');
        this.theWriter.write(array, n, n2);
        this.theWriter.write(32);
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
        this.theWriter.print('?');
        this.theWriter.print(s);
        this.theWriter.print(' ');
        this.theWriter.println(s2);
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void skippedEntity(final String s) throws SAXException {
    }
    
    @Override
    public void stagc(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    @Override
    public void stage(final char[] array, final int n, final int n2) throws SAXException {
        this.theWriter.println("!");
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void startDTD(final String s, final String s2, final String s3) throws SAXException {
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void startElement(String s, String s2, final String s3, final Attributes attributes) throws SAXException {
        if (s3.length() != 0) {
            s2 = s3;
        }
        this.theWriter.print('(');
        this.theWriter.println(s2);
        for (int length = attributes.getLength(), i = 0; i < length; ++i) {
            s2 = (s = attributes.getQName(i));
            if (s2.length() == 0) {
                s = attributes.getLocalName(i);
            }
            this.theWriter.print('A');
            this.theWriter.print(s);
            this.theWriter.print(' ');
            this.theWriter.println(attributes.getValue(i));
        }
    }
    
    public void startEntity(final String s) throws SAXException {
    }
    
    public void startPrefixMapping(final String s, final String s2) throws SAXException {
    }
}
