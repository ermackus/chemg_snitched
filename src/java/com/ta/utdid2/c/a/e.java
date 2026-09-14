package com.ta.utdid2.c.a;

import java.util.Iterator;
import java.util.Map$Entry;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;
import android.util.Xml;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;

class e
{
    public static final Object a(final XmlPullParser xmlPullParser, final String[] array) throws XmlPullParserException, IOException {
        int i = xmlPullParser.getEventType();
        while (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    try {
                        i = xmlPullParser.next();
                        if (i != 1) {
                            continue;
                        }
                        throw new XmlPullParserException("Unexpected end of document");
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb = new StringBuilder("Unexpected call next(): ");
                        sb.append(xmlPullParser.getName());
                        throw new XmlPullParserException(sb.toString());
                    }
                }
                final StringBuilder sb2 = new StringBuilder("Unexpected text: ");
                sb2.append(xmlPullParser.getText());
                throw new XmlPullParserException(sb2.toString());
            }
            final StringBuilder sb3 = new StringBuilder("Unexpected end tag at: ");
            sb3.append(xmlPullParser.getName());
            throw new XmlPullParserException(sb3.toString());
        }
        return b(xmlPullParser, array);
    }
    
    public static final ArrayList a(final XmlPullParser xmlPullParser, final String s, final String[] array) throws XmlPullParserException, IOException {
        final ArrayList list = new ArrayList();
        int n = xmlPullParser.getEventType();
        while (true) {
            if (n == 2) {
                list.add(b(xmlPullParser, array));
            }
            else if (n == 3) {
                if (xmlPullParser.getName().equals((Object)s)) {
                    return list;
                }
                final StringBuilder sb = new StringBuilder("Expected ");
                sb.append(s);
                sb.append(" end tag at: ");
                sb.append(xmlPullParser.getName());
                throw new XmlPullParserException(sb.toString());
            }
            n = xmlPullParser.next();
            if (n != 1) {
                continue;
            }
            final StringBuilder sb2 = new StringBuilder("Document ended before ");
            sb2.append(s);
            sb2.append(" end tag");
            throw new XmlPullParserException(sb2.toString());
        }
    }
    
    public static final HashMap a(final InputStream inputStream) throws XmlPullParserException, IOException {
        final XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inputStream, (String)null);
        return (HashMap)a(pullParser, new String[1]);
    }
    
    public static final HashMap a(final XmlPullParser xmlPullParser, final String s, final String[] array) throws XmlPullParserException, IOException {
        final HashMap hashMap = new HashMap();
        int n = xmlPullParser.getEventType();
        while (true) {
            if (n == 2) {
                final Object b = b(xmlPullParser, array);
                if (array[0] == null) {
                    final StringBuilder sb = new StringBuilder("Map value without name attribute: ");
                    sb.append(xmlPullParser.getName());
                    throw new XmlPullParserException(sb.toString());
                }
                hashMap.put((Object)array[0], b);
            }
            else if (n == 3) {
                if (xmlPullParser.getName().equals((Object)s)) {
                    return hashMap;
                }
                final StringBuilder sb2 = new StringBuilder("Expected ");
                sb2.append(s);
                sb2.append(" end tag at: ");
                sb2.append(xmlPullParser.getName());
                throw new XmlPullParserException(sb2.toString());
            }
            n = xmlPullParser.next();
            if (n != 1) {
                continue;
            }
            final StringBuilder sb3 = new StringBuilder("Document ended before ");
            sb3.append(s);
            sb3.append(" end tag");
            throw new XmlPullParserException(sb3.toString());
        }
    }
    
    public static final void a(final Object o, final String s, final XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (o == null) {
            xmlSerializer.startTag((String)null, "null");
            if (s != null) {
                xmlSerializer.attribute((String)null, "name", s);
            }
            xmlSerializer.endTag((String)null, "null");
            return;
        }
        if (o instanceof String) {
            xmlSerializer.startTag((String)null, "string");
            if (s != null) {
                xmlSerializer.attribute((String)null, "name", s);
            }
            xmlSerializer.text(o.toString());
            xmlSerializer.endTag((String)null, "string");
            return;
        }
        String s2;
        if (o instanceof Integer) {
            s2 = "int";
        }
        else if (o instanceof Long) {
            s2 = "long";
        }
        else if (o instanceof Float) {
            s2 = "float";
        }
        else if (o instanceof Double) {
            s2 = "double";
        }
        else if (o instanceof Boolean) {
            s2 = "boolean";
        }
        else {
            if (o instanceof byte[]) {
                a((byte[])o, s, xmlSerializer);
                return;
            }
            if (o instanceof int[]) {
                a((int[])o, s, xmlSerializer);
                return;
            }
            if (o instanceof Map) {
                a((Map)o, s, xmlSerializer);
                return;
            }
            if (o instanceof List) {
                a((List)o, s, xmlSerializer);
                return;
            }
            if (o instanceof CharSequence) {
                xmlSerializer.startTag((String)null, "string");
                if (s != null) {
                    xmlSerializer.attribute((String)null, "name", s);
                }
                xmlSerializer.text(o.toString());
                xmlSerializer.endTag((String)null, "string");
                return;
            }
            final StringBuilder sb = new StringBuilder("writeValueXml: unable to write value ");
            sb.append(o);
            throw new RuntimeException(sb.toString());
        }
        xmlSerializer.startTag((String)null, s2);
        if (s != null) {
            xmlSerializer.attribute((String)null, "name", s);
        }
        xmlSerializer.attribute((String)null, "value", o.toString());
        xmlSerializer.endTag((String)null, s2);
    }
    
    public static final void a(final List list, final String s, final XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (list == null) {
            xmlSerializer.startTag((String)null, "null");
            xmlSerializer.endTag((String)null, "null");
            return;
        }
        xmlSerializer.startTag((String)null, "list");
        if (s != null) {
            xmlSerializer.attribute((String)null, "name", s);
        }
        for (int size = list.size(), i = 0; i < size; ++i) {
            a(list.get(i), null, xmlSerializer);
        }
        xmlSerializer.endTag((String)null, "list");
    }
    
    public static final void a(final Map map, final OutputStream outputStream) throws XmlPullParserException, IOException {
        final a a = new a();
        ((XmlSerializer)a).setOutput(outputStream, "utf-8");
        ((XmlSerializer)a).startDocument(null, true);
        ((XmlSerializer)a).setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        a(map, null, (XmlSerializer)a);
        ((XmlSerializer)a).endDocument();
    }
    
    public static final void a(final Map map, final String s, final XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (map == null) {
            xmlSerializer.startTag((String)null, "null");
            xmlSerializer.endTag((String)null, "null");
            return;
        }
        final Iterator iterator = map.entrySet().iterator();
        xmlSerializer.startTag((String)null, "map");
        if (s != null) {
            xmlSerializer.attribute((String)null, "name", s);
        }
        while (iterator.hasNext()) {
            final Map$Entry map$Entry = (Map$Entry)iterator.next();
            a(map$Entry.getValue(), (String)map$Entry.getKey(), xmlSerializer);
        }
        xmlSerializer.endTag((String)null, "map");
    }
    
    public static final void a(final byte[] array, final String s, final XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (array == null) {
            xmlSerializer.startTag((String)null, "null");
            xmlSerializer.endTag((String)null, "null");
            return;
        }
        xmlSerializer.startTag((String)null, "byte-array");
        if (s != null) {
            xmlSerializer.attribute((String)null, "name", s);
        }
        final int length = array.length;
        xmlSerializer.attribute((String)null, "num", Integer.toString(length));
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (final byte b : array) {
            int n = b >> 4;
            if (n >= 10) {
                n = n + 97 - 10;
            }
            else {
                n += 48;
            }
            sb.append(n);
            int n2 = b & 0xFF;
            if (n2 >= 10) {
                n2 = n2 + 97 - 10;
            }
            else {
                n2 += 48;
            }
            sb.append(n2);
        }
        xmlSerializer.text(sb.toString());
        xmlSerializer.endTag((String)null, "byte-array");
    }
    
    public static final void a(final int[] array, final String s, final XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (array == null) {
            xmlSerializer.startTag((String)null, "null");
            xmlSerializer.endTag((String)null, "null");
            return;
        }
        xmlSerializer.startTag((String)null, "int-array");
        if (s != null) {
            xmlSerializer.attribute((String)null, "name", s);
        }
        final int length = array.length;
        xmlSerializer.attribute((String)null, "num", Integer.toString(length));
        for (int i = 0; i < length; ++i) {
            xmlSerializer.startTag((String)null, "item");
            xmlSerializer.attribute((String)null, "value", Integer.toString(array[i]));
            xmlSerializer.endTag((String)null, "item");
        }
        xmlSerializer.endTag((String)null, "int-array");
    }
    
    public static final int[] a(final XmlPullParser xmlPullParser, final String s, final String[] array) throws XmlPullParserException, IOException {
        try {
            final int[] array2 = new int[Integer.parseInt(xmlPullParser.getAttributeValue((String)null, "num"))];
            int n = 0;
            int n2 = xmlPullParser.getEventType();
            Label_0241: {
                Label_0176: {
                    while (true) {
                        int n3 = 0;
                        Label_0222: {
                            if (n2 == 2) {
                                if (xmlPullParser.getName().equals((Object)"item")) {
                                    try {
                                        array2[n] = Integer.parseInt(xmlPullParser.getAttributeValue((String)null, "value"));
                                        n3 = n;
                                        break Label_0222;
                                    }
                                    catch (final NumberFormatException ex) {
                                        throw new XmlPullParserException("Not a number in value attribute in item");
                                    }
                                    catch (final NullPointerException ex2) {
                                        throw new XmlPullParserException("Need value attribute in item");
                                    }
                                    break;
                                }
                                break;
                            }
                            else {
                                n3 = n;
                                if (n2 == 3) {
                                    if (xmlPullParser.getName().equals((Object)s)) {
                                        return array2;
                                    }
                                    if (!xmlPullParser.getName().equals((Object)"item")) {
                                        break Label_0176;
                                    }
                                    n3 = n + 1;
                                }
                            }
                        }
                        n2 = xmlPullParser.next();
                        if (n2 == 1) {
                            break Label_0241;
                        }
                        n = n3;
                    }
                    final StringBuilder sb = new StringBuilder("Expected item tag at: ");
                    sb.append(xmlPullParser.getName());
                    throw new XmlPullParserException(sb.toString());
                }
                final StringBuilder sb2 = new StringBuilder("Expected ");
                sb2.append(s);
                sb2.append(" end tag at: ");
                sb2.append(xmlPullParser.getName());
                throw new XmlPullParserException(sb2.toString());
            }
            final StringBuilder sb3 = new StringBuilder("Document ended before ");
            sb3.append(s);
            sb3.append(" end tag");
            throw new XmlPullParserException(sb3.toString());
        }
        catch (final NumberFormatException ex3) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
        catch (final NullPointerException ex4) {
            throw new XmlPullParserException("Need num attribute in byte-array");
        }
    }
    
    private static final Object b(final XmlPullParser xmlPullParser, final String[] array) throws XmlPullParserException, IOException {
        Object o = null;
        final String attributeValue = xmlPullParser.getAttributeValue((String)null, "name");
        final String name = xmlPullParser.getName();
        if (!name.equals((Object)"null")) {
            if (name.equals((Object)"string")) {
                String string = "";
                while (true) {
                    final int next = xmlPullParser.next();
                    if (next == 1) {
                        throw new XmlPullParserException("Unexpected end of document in <string>");
                    }
                    if (next == 3) {
                        if (xmlPullParser.getName().equals((Object)"string")) {
                            array[0] = attributeValue;
                            return string;
                        }
                        final StringBuilder sb = new StringBuilder("Unexpected end tag in <string>: ");
                        sb.append(xmlPullParser.getName());
                        throw new XmlPullParserException(sb.toString());
                    }
                    else if (next == 4) {
                        final StringBuilder sb2 = new StringBuilder(String.valueOf((Object)string));
                        sb2.append(xmlPullParser.getText());
                        string = sb2.toString();
                    }
                    else {
                        if (next != 2) {
                            continue;
                        }
                        final StringBuilder sb3 = new StringBuilder("Unexpected start tag in <string>: ");
                        sb3.append(xmlPullParser.getName());
                        throw new XmlPullParserException(sb3.toString());
                    }
                }
            }
            else if (name.equals((Object)"int")) {
                o = Integer.parseInt(xmlPullParser.getAttributeValue((String)null, "value"));
            }
            else if (name.equals((Object)"long")) {
                o = Long.valueOf(xmlPullParser.getAttributeValue((String)null, "value"));
            }
            else if (name.equals((Object)"float")) {
                o = new Float(xmlPullParser.getAttributeValue((String)null, "value"));
            }
            else if (name.equals((Object)"double")) {
                o = new Double(xmlPullParser.getAttributeValue((String)null, "value"));
            }
            else if (name.equals((Object)"boolean")) {
                o = Boolean.valueOf(xmlPullParser.getAttributeValue((String)null, "value"));
            }
            else {
                if (name.equals((Object)"int-array")) {
                    xmlPullParser.next();
                    final int[] a = a(xmlPullParser, "int-array", array);
                    array[0] = attributeValue;
                    return a;
                }
                if (name.equals((Object)"map")) {
                    xmlPullParser.next();
                    final HashMap a2 = a(xmlPullParser, "map", array);
                    array[0] = attributeValue;
                    return a2;
                }
                if (name.equals((Object)"list")) {
                    xmlPullParser.next();
                    final ArrayList a3 = a(xmlPullParser, "list", array);
                    array[0] = attributeValue;
                    return a3;
                }
                final StringBuilder sb4 = new StringBuilder("Unknown tag: ");
                sb4.append(name);
                throw new XmlPullParserException(sb4.toString());
            }
        }
        while (true) {
            final int next2 = xmlPullParser.next();
            if (next2 == 1) {
                final StringBuilder sb5 = new StringBuilder("Unexpected end of document in <");
                sb5.append(name);
                sb5.append(">");
                throw new XmlPullParserException(sb5.toString());
            }
            if (next2 == 3) {
                if (xmlPullParser.getName().equals((Object)name)) {
                    array[0] = attributeValue;
                    return o;
                }
                final StringBuilder sb6 = new StringBuilder("Unexpected end tag in <");
                sb6.append(name);
                sb6.append(">: ");
                sb6.append(xmlPullParser.getName());
                throw new XmlPullParserException(sb6.toString());
            }
            else {
                if (next2 == 4) {
                    final StringBuilder sb7 = new StringBuilder("Unexpected text in <");
                    sb7.append(name);
                    sb7.append(">: ");
                    sb7.append(xmlPullParser.getName());
                    throw new XmlPullParserException(sb7.toString());
                }
                if (next2 != 2) {
                    continue;
                }
                final StringBuilder sb8 = new StringBuilder("Unexpected start tag in <");
                sb8.append(name);
                sb8.append(">: ");
                sb8.append(xmlPullParser.getName());
                throw new XmlPullParserException(sb8.toString());
            }
        }
    }
}
