package com.ta.utdid2.c.a;

import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CoderResult;
import java.nio.CharBuffer;
import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.nio.ByteBuffer;
import java.io.Writer;
import java.io.OutputStream;
import org.xmlpull.v1.XmlSerializer;

class a implements XmlSerializer
{
    private static final String[] a;
    private OutputStream a;
    private Writer a;
    private ByteBuffer a;
    private CharsetEncoder a;
    private final char[] a;
    private boolean f;
    private int mPos;
    
    static {
        a = new String[] { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null };
    }
    
    a() {
        this.a = new char[8192];
        this.a = ByteBuffer.allocate(8192);
    }
    
    private void a(final String s) throws IOException {
        final int length = s.length();
        final String[] a = com.ta.utdid2.c.a.a.a;
        final char c = (char)a.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (char1 < c) {
                final String s2 = a[char1];
                if (s2 != null) {
                    if (n < i) {
                        this.a(s, n, i - n);
                    }
                    n = i + 1;
                    this.append(s2);
                }
            }
            ++i;
        }
        if (n < i) {
            this.a(s, n, i - n);
        }
    }
    
    private void a(final String s, int i, int n) throws IOException {
        if (n > 8192) {
            int n3;
            for (int n2 = i + n; i < n2; i = n3) {
                n3 = i + 8192;
                if (n3 < n2) {
                    n = 8192;
                }
                else {
                    n = n2 - i;
                }
                this.a(s, i, n);
            }
            return;
        }
        int n4;
        if ((n4 = this.mPos) + n > 8192) {
            this.flush();
            n4 = this.mPos;
        }
        s.getChars(i, i + n, this.a, n4);
        this.mPos = n4 + n;
    }
    
    private void a(final char[] array, final int n, final int n2) throws IOException {
        final String[] a = com.ta.utdid2.c.a.a.a;
        final char c = (char)a.length;
        int n3 = n;
        int i;
        for (i = n; i < n2 + n; ++i) {
            final char c2 = array[i];
            if (c2 < c) {
                final String s = a[c2];
                if (s != null) {
                    if (n3 < i) {
                        this.append(array, n3, i - n3);
                    }
                    n3 = i + 1;
                    this.append(s);
                }
            }
        }
        if (n3 < i) {
            this.append(array, n3, i - n3);
        }
    }
    
    private void append(final char c) throws IOException {
        int n;
        if ((n = this.mPos) >= 8191) {
            this.flush();
            n = this.mPos;
        }
        this.a[n] = c;
        this.mPos = n + 1;
    }
    
    private void append(final String s) throws IOException {
        this.a(s, 0, s.length());
    }
    
    private void append(final char[] array, int i, int n) throws IOException {
        if (n > 8192) {
            int n3;
            for (int n2 = i + n; i < n2; i = n3) {
                n3 = i + 8192;
                if (n3 < n2) {
                    n = 8192;
                }
                else {
                    n = n2 - i;
                }
                this.append(array, i, n);
            }
            return;
        }
        int n4;
        if ((n4 = this.mPos) + n > 8192) {
            this.flush();
            n4 = this.mPos;
        }
        System.arraycopy((Object)array, i, (Object)this.a, n4, n);
        this.mPos = n4 + n;
    }
    
    private void b() throws IOException {
        final int position = this.a.position();
        if (position > 0) {
            this.a.flip();
            this.a.write(this.a.array(), 0, position);
            this.a.clear();
        }
    }
    
    public XmlSerializer attribute(final String s, final String s2, final String s3) throws IOException, IllegalArgumentException, IllegalStateException {
        this.append(' ');
        if (s != null) {
            this.append(s);
            this.append(':');
        }
        this.append(s2);
        this.append("=\"");
        this.a(s3);
        this.append('\"');
        return (XmlSerializer)this;
    }
    
    public void cdsect(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void comment(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void docdecl(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
        this.flush();
    }
    
    public XmlSerializer endTag(final String s, final String s2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f) {
            this.append(" />\n");
        }
        else {
            this.append("</");
            if (s != null) {
                this.append(s);
                this.append(':');
            }
            this.append(s2);
            this.append(">\n");
        }
        this.f = false;
        return (XmlSerializer)this;
    }
    
    public void entityRef(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void flush() throws IOException {
        final int mPos = this.mPos;
        if (mPos > 0) {
            Label_0121: {
                if (this.a != null) {
                    CharBuffer wrap;
                    CoderResult coderResult;
                    for (wrap = CharBuffer.wrap(this.a, 0, mPos), coderResult = this.a.encode(wrap, this.a, true); !coderResult.isError(); coderResult = this.a.encode(wrap, this.a, true)) {
                        if (!coderResult.isOverflow()) {
                            this.b();
                            this.a.flush();
                            break Label_0121;
                        }
                        this.b();
                    }
                    throw new IOException(coderResult.toString());
                }
                this.a.write(this.a, 0, mPos);
                this.a.flush();
            }
            this.mPos = 0;
        }
    }
    
    public int getDepth() {
        throw new UnsupportedOperationException();
    }
    
    public boolean getFeature(final String s) {
        throw new UnsupportedOperationException();
    }
    
    public String getName() {
        throw new UnsupportedOperationException();
    }
    
    public String getNamespace() {
        throw new UnsupportedOperationException();
    }
    
    public String getPrefix(final String s, final boolean b) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }
    
    public Object getProperty(final String s) {
        throw new UnsupportedOperationException();
    }
    
    public void ignorableWhitespace(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void processingInstruction(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void setFeature(final String s, final boolean b) throws IllegalArgumentException, IllegalStateException {
        if (s.equals((Object)"http://xmlpull.org/v1/doc/features.html#indent-output")) {
            return;
        }
        throw new UnsupportedOperationException();
    }
    
    public void setOutput(final OutputStream a, final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        if (a != null) {
            try {
                this.a = Charset.forName(s).newEncoder();
                this.a = a;
                return;
            }
            catch (final UnsupportedCharsetException ex) {
                throw (UnsupportedEncodingException)new UnsupportedEncodingException(s).initCause((Throwable)ex);
            }
            catch (final IllegalCharsetNameException ex2) {
                throw (UnsupportedEncodingException)new UnsupportedEncodingException(s).initCause((Throwable)ex2);
            }
        }
        throw new IllegalArgumentException();
    }
    
    public void setOutput(final Writer a) throws IOException, IllegalArgumentException, IllegalStateException {
        this.a = a;
    }
    
    public void setPrefix(final String s, final String s2) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void setProperty(final String s, final Object o) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }
    
    public void startDocument(String s, final Boolean b) throws IOException, IllegalArgumentException, IllegalStateException {
        final StringBuilder sb = new StringBuilder("<?xml version='1.0' encoding='utf-8' standalone='");
        if (b) {
            s = "yes";
        }
        else {
            s = "no";
        }
        sb.append(s);
        sb.append("' ?>\n");
        this.append(sb.toString());
    }
    
    public XmlSerializer startTag(final String s, final String s2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f) {
            this.append(">\n");
        }
        this.append('<');
        if (s != null) {
            this.append(s);
            this.append(':');
        }
        this.append(s2);
        this.f = true;
        return (XmlSerializer)this;
    }
    
    public XmlSerializer text(final String s) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f) {
            this.append(">");
            this.f = false;
        }
        this.a(s);
        return (XmlSerializer)this;
    }
    
    public XmlSerializer text(final char[] array, final int n, final int n2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f) {
            this.append(">");
            this.f = false;
        }
        this.a(array, n, n2);
        return (XmlSerializer)this;
    }
}
