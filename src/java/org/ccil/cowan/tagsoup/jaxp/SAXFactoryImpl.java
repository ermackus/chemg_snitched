package org.ccil.cowan.tagsoup.jaxp;

import java.util.LinkedHashMap;
import org.xml.sax.SAXException;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import javax.xml.parsers.SAXParserFactory;

public class SAXFactoryImpl extends SAXParserFactory
{
    private HashMap features;
    private SAXParserImpl prototypeParser;
    
    public SAXFactoryImpl() {
        this.prototypeParser = null;
        this.features = null;
    }
    
    private SAXParserImpl getPrototype() {
        if (this.prototypeParser == null) {
            this.prototypeParser = new SAXParserImpl();
        }
        return this.prototypeParser;
    }
    
    public boolean getFeature(final String s) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        return this.getPrototype().getFeature(s);
    }
    
    public SAXParser newSAXParser() throws ParserConfigurationException {
        try {
            return SAXParserImpl.newInstance((Map)this.features);
        }
        catch (final SAXException ex) {
            throw new ParserConfigurationException(ex.getMessage());
        }
    }
    
    public void setFeature(final String s, final boolean b) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        this.getPrototype().setFeature(s, b);
        if (this.features == null) {
            this.features = (HashMap)new LinkedHashMap();
        }
        final HashMap features = this.features;
        Boolean b2;
        if (b) {
            b2 = Boolean.TRUE;
        }
        else {
            b2 = Boolean.FALSE;
        }
        features.put((Object)s, (Object)b2);
    }
}
