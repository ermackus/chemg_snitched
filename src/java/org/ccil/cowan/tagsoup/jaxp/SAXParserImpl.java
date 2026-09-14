package org.ccil.cowan.tagsoup.jaxp;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXException;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import org.ccil.cowan.tagsoup.Parser;
import javax.xml.parsers.SAXParser;

public class SAXParserImpl extends SAXParser
{
    final Parser parser;
    
    protected SAXParserImpl() {
        this.parser = new Parser();
    }
    
    public static SAXParserImpl newInstance(final Map map) throws SAXException {
        final SAXParserImpl saxParserImpl = new SAXParserImpl();
        if (map != null) {
            for (final Map$Entry map$Entry : map.entrySet()) {
                saxParserImpl.setFeature((String)map$Entry.getKey(), (boolean)map$Entry.getValue());
            }
        }
        return saxParserImpl;
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.parser.getFeature(s);
    }
    
    public org.xml.sax.Parser getParser() throws SAXException {
        return (org.xml.sax.Parser)new SAX1ParserAdapter((XMLReader)this.parser);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.parser.getProperty(s);
    }
    
    public XMLReader getXMLReader() {
        return (XMLReader)this.parser;
    }
    
    public boolean isNamespaceAware() {
        try {
            return this.parser.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (final SAXException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public boolean isValidating() {
        try {
            return this.parser.getFeature("http://xml.org/sax/features/validation");
        }
        catch (final SAXException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.parser.setFeature(s, b);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.parser.setProperty(s, o);
    }
}
