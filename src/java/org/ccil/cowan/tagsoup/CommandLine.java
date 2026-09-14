package org.ccil.cowan.tagsoup;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.ext.LexicalHandler;
import java.io.OutputStreamWriter;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import org.xml.sax.ContentHandler;
import java.io.Writer;
import java.util.Hashtable;

public class CommandLine
{
    static Hashtable options;
    private static String theOutputEncoding;
    private static Parser theParser;
    private static HTMLSchema theSchema;
    
    static {
        (CommandLine.options = new Hashtable()).put((Object)"--nocdata", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--files", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--reuse", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--nons", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--nobogons", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--any", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--emptybogons", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--norootbogons", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--pyxin", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--lexical", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--pyx", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--html", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--method=", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--doctype-public=", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--doctype-system=", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--output-encoding=", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--omit-xml-declaration", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--encoding=", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--help", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--version", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--nodefaults", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--nocolons", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--norestart", (Object)Boolean.FALSE);
        CommandLine.options.put((Object)"--ignorable", (Object)Boolean.FALSE);
        CommandLine.theParser = null;
        CommandLine.theSchema = null;
        CommandLine.theOutputEncoding = null;
    }
    
    private static ContentHandler chooseContentHandler(final Writer writer) {
        if (hasOption(CommandLine.options, "--pyx")) {
            return (ContentHandler)new PYXWriter(writer);
        }
        final XMLWriter xmlWriter = new XMLWriter(writer);
        if (hasOption(CommandLine.options, "--html")) {
            xmlWriter.setOutputProperty("method", "html");
            xmlWriter.setOutputProperty("omit-xml-declaration", "yes");
        }
        if (hasOption(CommandLine.options, "--method=")) {
            final String s = (String)CommandLine.options.get((Object)"--method=");
            if (s != null) {
                xmlWriter.setOutputProperty("method", s);
            }
        }
        if (hasOption(CommandLine.options, "--doctype-public=")) {
            final String s2 = (String)CommandLine.options.get((Object)"--doctype-public=");
            if (s2 != null) {
                xmlWriter.setOutputProperty("doctype-public", s2);
            }
        }
        if (hasOption(CommandLine.options, "--doctype-system=")) {
            final String s3 = (String)CommandLine.options.get((Object)"--doctype-system=");
            if (s3 != null) {
                xmlWriter.setOutputProperty("doctype-system", s3);
            }
        }
        if (hasOption(CommandLine.options, "--output-encoding=")) {
            final String theOutputEncoding = (String)CommandLine.options.get((Object)"--output-encoding=");
            if ((CommandLine.theOutputEncoding = theOutputEncoding) != null) {
                xmlWriter.setOutputProperty("encoding", theOutputEncoding);
            }
        }
        if (hasOption(CommandLine.options, "--omit-xml-declaration")) {
            xmlWriter.setOutputProperty("omit-xml-declaration", "yes");
        }
        xmlWriter.setPrefix(CommandLine.theSchema.getURI(), "");
        return (ContentHandler)xmlWriter;
    }
    
    private static void doHelp() {
        System.err.print("usage: java -jar tagsoup-*.jar ");
        System.err.print(" [ ");
        final Enumeration keys = CommandLine.options.keys();
        int n = 1;
        while (keys.hasMoreElements()) {
            if (n == 0) {
                System.err.print("| ");
            }
            n = 0;
            final String s = (String)keys.nextElement();
            System.err.print(s);
            if (s.endsWith("=")) {
                System.err.print("?");
            }
            System.err.print(" ");
        }
        System.err.println("]*");
    }
    
    private static int getopts(final Hashtable hashtable, final String[] array) {
        int i;
        for (i = 0; i < array.length; ++i) {
            final String s = array[i];
            Object substring = null;
            if (s.charAt(0) != '-') {
                break;
            }
            int index = s.indexOf(61);
            String substring2 = s;
            if (index != -1) {
                ++index;
                substring = s.substring(index, s.length());
                substring2 = s.substring(0, index);
            }
            if (hashtable.containsKey((Object)substring2)) {
                if (substring == null) {
                    hashtable.put((Object)substring2, (Object)Boolean.TRUE);
                }
                else {
                    hashtable.put((Object)substring2, substring);
                }
            }
            else {
                System.err.print("Unknown option ");
                System.err.println(substring2);
                System.exit(1);
            }
        }
        return i;
    }
    
    private static boolean hasOption(final Hashtable hashtable, final String s) {
        return Boolean.getBoolean(s) || hashtable.get((Object)s) != Boolean.FALSE;
    }
    
    public static void main(final String[] array) throws IOException, SAXException {
        int i = getopts(CommandLine.options, array);
        if (hasOption(CommandLine.options, "--help")) {
            doHelp();
            return;
        }
        if (hasOption(CommandLine.options, "--version")) {
            System.err.println("TagSoup version 1.2.1");
            return;
        }
        if (array.length == i) {
            process("", (OutputStream)System.out);
        }
        else {
            int j = i;
            if (hasOption(CommandLine.options, "--files")) {
                while (i < array.length) {
                    final String s = array[i];
                    final int lastIndex = s.lastIndexOf(46);
                    String s2;
                    if (lastIndex == -1) {
                        final StringBuffer sb = new StringBuffer();
                        sb.append(s);
                        sb.append(".xhtml");
                        s2 = sb.toString();
                    }
                    else if (s.endsWith(".xhtml")) {
                        final StringBuffer sb2 = new StringBuffer();
                        sb2.append(s);
                        sb2.append("_");
                        s2 = sb2.toString();
                    }
                    else {
                        final StringBuffer sb3 = new StringBuffer();
                        sb3.append(s.substring(0, lastIndex));
                        sb3.append(".xhtml");
                        s2 = sb3.toString();
                    }
                    final PrintStream err = System.err;
                    final StringBuffer sb4 = new StringBuffer();
                    sb4.append("src: ");
                    sb4.append(s);
                    sb4.append(" dst: ");
                    sb4.append(s2);
                    err.println(sb4.toString());
                    process(s, (OutputStream)new FileOutputStream(s2));
                    ++i;
                }
            }
            else {
                while (j < array.length) {
                    final PrintStream err2 = System.err;
                    final StringBuffer sb5 = new StringBuffer();
                    sb5.append("src: ");
                    sb5.append(array[j]);
                    err2.println(sb5.toString());
                    process(array[j], (OutputStream)System.out);
                    ++j;
                }
            }
        }
    }
    
    private static void process(String s, final OutputStream outputStream) throws IOException, SAXException {
        Parser theParser;
        if (hasOption(CommandLine.options, "--reuse")) {
            if (CommandLine.theParser == null) {
                CommandLine.theParser = new Parser();
            }
            theParser = CommandLine.theParser;
        }
        else {
            theParser = new Parser();
        }
        ((XMLReader)theParser).setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", CommandLine.theSchema = new HTMLSchema());
        if (hasOption(CommandLine.options, "--nocdata")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements", false);
        }
        if (hasOption(CommandLine.options, "--nons") || hasOption(CommandLine.options, "--html")) {
            ((XMLReader)theParser).setFeature("http://xml.org/sax/features/namespaces", false);
        }
        if (hasOption(CommandLine.options, "--nobogons")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons", true);
        }
        if (hasOption(CommandLine.options, "--any")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", false);
        }
        else if (hasOption(CommandLine.options, "--emptybogons")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", true);
        }
        if (hasOption(CommandLine.options, "--norootbogons")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/root-bogons", false);
        }
        if (hasOption(CommandLine.options, "--nodefaults")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/default-attributes", false);
        }
        if (hasOption(CommandLine.options, "--nocolons")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/translate-colons", true);
        }
        if (hasOption(CommandLine.options, "--norestart")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/restart-elements", false);
        }
        if (hasOption(CommandLine.options, "--ignorable")) {
            ((XMLReader)theParser).setFeature("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace", true);
        }
        if (hasOption(CommandLine.options, "--pyxin")) {
            ((XMLReader)theParser).setProperty("http://www.ccil.org/~cowan/tagsoup/properties/scanner", new PYXScanner());
        }
        OutputStreamWriter outputStreamWriter;
        if (CommandLine.theOutputEncoding == null) {
            outputStreamWriter = new OutputStreamWriter(outputStream);
        }
        else {
            outputStreamWriter = new OutputStreamWriter(outputStream, CommandLine.theOutputEncoding);
        }
        final ContentHandler chooseContentHandler = chooseContentHandler((Writer)outputStreamWriter);
        ((XMLReader)theParser).setContentHandler(chooseContentHandler);
        if (hasOption(CommandLine.options, "--lexical") && chooseContentHandler instanceof LexicalHandler) {
            ((XMLReader)theParser).setProperty("http://xml.org/sax/properties/lexical-handler", chooseContentHandler);
        }
        final InputSource inputSource = new InputSource();
        if (s != "") {
            inputSource.setSystemId(s);
        }
        else {
            inputSource.setByteStream(System.in);
        }
        if (hasOption(CommandLine.options, "--encoding=")) {
            s = (String)CommandLine.options.get((Object)"--encoding=");
            if (s != null) {
                inputSource.setEncoding(s);
            }
        }
        ((XMLReader)theParser).parse(inputSource);
    }
}
