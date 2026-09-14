package org.ccil.cowan.tagsoup;

import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.Reader;

public interface Scanner
{
    void resetDocumentLocator(final String p0, final String p1);
    
    void scan(final Reader p0, final ScanHandler p1) throws IOException, SAXException;
    
    void startCDATA();
}
