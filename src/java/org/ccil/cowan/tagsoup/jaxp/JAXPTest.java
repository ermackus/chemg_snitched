package org.ccil.cowan.tagsoup.jaxp;

import org.w3c.dom.Document;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class JAXPTest
{
    public static void main(final String[] array) throws Exception {
        new JAXPTest().test(array);
    }
    
    private void test(final String[] array) throws Exception {
        if (array.length != 1) {
            final PrintStream err = System.err;
            final StringBuffer sb = new StringBuffer();
            sb.append("Usage: java ");
            sb.append((Object)this.getClass());
            sb.append(" [input-file]");
            err.println(sb.toString());
            System.exit(1);
        }
        final File file = new File(array[0]);
        System.setProperty("javax.xml.parsers.SAXParserFactory", "org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl");
        final SAXParserFactory instance = SAXParserFactory.newInstance();
        final PrintStream out = System.out;
        final StringBuffer sb2 = new StringBuffer();
        sb2.append("Ok, SAX factory JAXP creates is: ");
        sb2.append((Object)instance);
        out.println(sb2.toString());
        System.out.println("Let's parse...");
        instance.newSAXParser().parse(file, new DefaultHandler());
        System.out.println("Done. And then DOM build:");
        final Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        final PrintStream out2 = System.out;
        final StringBuffer sb3 = new StringBuffer();
        sb3.append("Succesfully built DOM tree from '");
        sb3.append((Object)file);
        sb3.append("', -> ");
        sb3.append((Object)parse);
        out2.println(sb3.toString());
    }
}
