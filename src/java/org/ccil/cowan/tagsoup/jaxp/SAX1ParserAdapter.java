package org.ccil.cowan.tagsoup.jaxp;

import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXNotSupportedException;
import java.util.Locale;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.InputSource;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Parser;

public class SAX1ParserAdapter implements Parser
{
    final XMLReader xmlReader;
    
    public SAX1ParserAdapter(final XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }
    
    public void parse(final String s) throws SAXException {
        try {
            this.xmlReader.parse(s);
        }
        catch (final IOException ex) {
            throw new SAXException((Exception)ex);
        }
    }
    
    public void parse(final InputSource inputSource) throws SAXException {
        try {
            this.xmlReader.parse(inputSource);
        }
        catch (final IOException ex) {
            throw new SAXException((Exception)ex);
        }
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.xmlReader.setDTDHandler(dtdHandler);
    }
    
    public void setDocumentHandler(final DocumentHandler documentHandler) {
        this.xmlReader.setContentHandler((ContentHandler)new DocHandlerWrapper(documentHandler));
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.xmlReader.setEntityResolver(entityResolver);
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.xmlReader.setErrorHandler(errorHandler);
    }
    
    public void setLocale(final Locale locale) throws SAXException {
        throw new SAXNotSupportedException("TagSoup does not implement setLocale() method");
    }
    
    static final class AttributesWrapper implements AttributeList
    {
        Attributes attrs;
        
        public AttributesWrapper() {
        }
        
        public int getLength() {
            return this.attrs.getLength();
        }
        
        public String getName(final int n) {
            String s;
            if ((s = this.attrs.getQName(n)) == null) {
                s = this.attrs.getLocalName(n);
            }
            return s;
        }
        
        public String getType(final int n) {
            return this.attrs.getType(n);
        }
        
        public String getType(final String s) {
            return this.attrs.getType(s);
        }
        
        public String getValue(final int n) {
            return this.attrs.getValue(n);
        }
        
        public String getValue(final String s) {
            return this.attrs.getValue(s);
        }
        
        public void setAttributes(final Attributes attrs) {
            this.attrs = attrs;
        }
    }
    
    static final class DocHandlerWrapper implements ContentHandler
    {
        final DocumentHandler docHandler;
        final AttributesWrapper mAttrWrapper;
        
        DocHandlerWrapper(final DocumentHandler docHandler) {
            this.mAttrWrapper = new AttributesWrapper();
            this.docHandler = docHandler;
        }
        
        public void characters(final char[] array, final int n, final int n2) throws SAXException {
            this.docHandler.characters(array, n, n2);
        }
        
        public void endDocument() throws SAXException {
            this.docHandler.endDocument();
        }
        
        public void endElement(final String s, String s2, final String s3) throws SAXException {
            if (s3 != null) {
                s2 = s3;
            }
            this.docHandler.endElement(s2);
        }
        
        public void endPrefixMapping(final String s) {
        }
        
        public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
            this.docHandler.ignorableWhitespace(array, n, n2);
        }
        
        public void processingInstruction(final String s, final String s2) throws SAXException {
            this.docHandler.processingInstruction(s, s2);
        }
        
        public void setDocumentLocator(final Locator documentLocator) {
            this.docHandler.setDocumentLocator(documentLocator);
        }
        
        public void skippedEntity(final String s) {
        }
        
        public void startDocument() throws SAXException {
            this.docHandler.startDocument();
        }
        
        public void startElement(final String s, String s2, final String s3, final Attributes attributes) throws SAXException {
            if (s3 != null) {
                s2 = s3;
            }
            this.mAttrWrapper.setAttributes(attributes);
            this.docHandler.startElement(s2, (AttributeList)this.mAttrWrapper);
        }
        
        public void startPrefixMapping(final String s, final String s2) {
        }
    }
}
