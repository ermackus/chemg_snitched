package org.ccil.cowan.tagsoup;

import java.io.BufferedReader;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

public class PYXScanner implements Scanner
{
    public static void main(final String[] array) throws IOException, SAXException {
        new PYXScanner().scan((Reader)new InputStreamReader(System.in, "UTF-8"), new PYXWriter((Writer)new BufferedWriter((Writer)new OutputStreamWriter((OutputStream)System.out, "UTF-8"))));
    }
    
    @Override
    public void resetDocumentLocator(final String s, final String s2) {
    }
    
    @Override
    public void scan(final Reader reader, final ScanHandler scanHandler) throws IOException, SAXException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        char[] array = null;
        int n = 0;
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            final int length = line.length();
            char[] array2 = null;
            Label_0055: {
                if (array != null) {
                    array2 = array;
                    if (array.length >= length) {
                        break Label_0055;
                    }
                }
                array2 = new char[length];
            }
            line.getChars(0, length, array2, 0);
            final char c = array2[0];
            if (c != '(') {
                if (c != ')') {
                    if (c != '-') {
                        if (c != '?') {
                            if (c != 'A') {
                                if (c != 'E') {
                                    array = array2;
                                }
                                else {
                                    int n2;
                                    if ((n2 = n) != 0) {
                                        scanHandler.stagc(array2, 0, 0);
                                        n2 = 0;
                                    }
                                    scanHandler.entity(array2, 1, length - 1);
                                    array = array2;
                                    n = n2;
                                }
                            }
                            else {
                                final int index = line.indexOf(32);
                                scanHandler.aname(array2, 1, index - 1);
                                scanHandler.aval(array2, index + 1, length - index - 1);
                                array = array2;
                            }
                        }
                        else {
                            int n3;
                            if ((n3 = n) != 0) {
                                scanHandler.stagc(array2, 0, 0);
                                n3 = 0;
                            }
                            scanHandler.pi(array2, 1, length - 1);
                            array = array2;
                            n = n3;
                        }
                    }
                    else {
                        int n4;
                        if ((n4 = n) != 0) {
                            scanHandler.stagc(array2, 0, 0);
                            n4 = 0;
                        }
                        if (line.equals((Object)"-\\n")) {
                            array2[0] = '\n';
                            scanHandler.pcdata(array2, 0, 1);
                            array = array2;
                            n = n4;
                        }
                        else {
                            scanHandler.pcdata(array2, 1, length - 1);
                            array = array2;
                            n = n4;
                        }
                    }
                }
                else {
                    int n5;
                    if ((n5 = n) != 0) {
                        scanHandler.stagc(array2, 0, 0);
                        n5 = 0;
                    }
                    scanHandler.etag(array2, 1, length - 1);
                    array = array2;
                    n = n5;
                }
            }
            else {
                if (n != 0) {
                    scanHandler.stagc(array2, 0, 0);
                }
                scanHandler.gi(array2, 1, length - 1);
                n = 1;
                array = array2;
            }
        }
        scanHandler.eof(array, 0, 0);
    }
    
    @Override
    public void startCDATA() {
    }
}
