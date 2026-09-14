package org.json.alipay;

import java.io.IOException;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.Reader;

public final class c
{
    public int a;
    public Reader b;
    public char c;
    public boolean d;
    
    public c(Reader b) {
        if (!b.markSupported()) {
            b = (Reader)new BufferedReader(b);
        }
        this.b = b;
        this.d = false;
        this.a = 0;
    }
    
    public c(final String s) {
        this((Reader)new StringReader(s));
    }
    
    private String a(final int n) {
        if (n == 0) {
            return "";
        }
        final char[] array = new char[n];
        final boolean d = this.d;
        int i = 0;
        if (d) {
            this.d = false;
            array[0] = this.c;
            i = 1;
        }
        while (i < n) {
            try {
                final int read = this.b.read(array, i, n - i);
                if (read != -1) {
                    i += read;
                    continue;
                }
            }
            catch (final IOException ex) {
                throw new JSONException((Throwable)ex);
            }
            break;
        }
        this.a += i;
        if (i >= n) {
            this.c = array[n - 1];
            return new String(array);
        }
        throw this.a("Substring bounds error");
    }
    
    public final JSONException a(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(this.toString());
        return new JSONException(sb.toString());
    }
    
    public final void a() {
        if (!this.d) {
            final int a = this.a;
            if (a > 0) {
                this.a = a - 1;
                this.d = true;
                return;
            }
        }
        throw new JSONException("Stepping back two steps is not supported");
    }
    
    public final char b() {
        if (this.d) {
            this.d = false;
            if (this.c != '\0') {
                ++this.a;
            }
            return this.c;
        }
        try {
            final int read = this.b.read();
            if (read <= 0) {
                return this.c = '\0';
            }
            ++this.a;
            return this.c = (char)read;
        }
        catch (final IOException ex) {
            throw new JSONException((Throwable)ex);
        }
    }
    
    public final char c() {
    Label_0000:
        while (true) {
            final char b = this.b();
            if (b == '/') {
                final char b2 = this.b();
                if (b2 == '*') {
                    while (true) {
                        final char b3 = this.b();
                        if (b3 == '\0') {
                            break;
                        }
                        if (b3 != '*') {
                            continue;
                        }
                        if (this.b() == '/') {
                            continue Label_0000;
                        }
                        this.a();
                    }
                    throw this.a("Unclosed comment");
                }
                if (b2 != '/') {
                    this.a();
                    return '/';
                }
                char b4;
                do {
                    b4 = this.b();
                    if (b4 != '\n' && b4 != '\r') {
                        continue;
                    }
                    break;
                } while (b4 != '\0');
            }
            else if (b == '#') {
                char b5;
                do {
                    b5 = this.b();
                    if (b5 != '\n' && b5 != '\r') {
                        continue;
                    }
                    break;
                } while (b5 != '\0');
            }
            else {
                if (b == '\0' || b > ' ') {
                    return b;
                }
                continue;
            }
        }
    }
    
    public final Object d() {
        final char c = this.c();
        Label_0337: {
            if (c == '\"') {
                break Label_0337;
            }
            Label_0324: {
                if (c == '[') {
                    break Label_0324;
                }
                Label_0311: {
                    if (c == '{') {
                        break Label_0311;
                    }
                    if (c == '\'') {
                        break Label_0337;
                    }
                    if (c == '(') {
                        break Label_0324;
                    }
                    final StringBuffer sb = new StringBuffer();
                    for (char b = c; b >= ' ' && ",:]}/\\\"[{;=#".indexOf((int)b) < 0; b = this.b()) {
                        sb.append(b);
                    }
                    this.a();
                    String s = sb.toString().trim();
                    Label_0304: {
                        if (s.equals((Object)"")) {
                            break Label_0304;
                        }
                        if (s.equalsIgnoreCase("true")) {
                            return Boolean.TRUE;
                        }
                        if (s.equalsIgnoreCase("false")) {
                            return Boolean.FALSE;
                        }
                        if (s.equalsIgnoreCase("null")) {
                            return org.json.alipay.b.a;
                        }
                        if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+') {
                            return s;
                        }
                        while (true) {
                            if (c != '0') {
                                break Label_0253;
                            }
                            Label_0234: {
                                if (s.length() <= 2) {
                                    break Label_0234;
                                }
                                if (s.charAt(1) != 'x') {
                                    if (s.charAt(1) != 'X') {
                                        break Label_0234;
                                    }
                                }
                                try {
                                    return new Integer(Integer.parseInt(s.substring(2), 16));
                                    Label_0517: {
                                        throw this.a("Unterminated string");
                                    }
                                Block_29_Outer:
                                    while (true) {
                                        char b2 = '\0';
                                        iftrue(Label_0489:)(b2 == 'n');
                                        Block_26: {
                                        Label_0433_Outer:
                                            while (true) {
                                                Block_28: {
                                                    break Block_28;
                                                    iftrue(Label_0459:)(b2 == 'x');
                                                Label_0346:
                                                    while (true) {
                                                        StringBuffer sb2 = null;
                                                    Label_0433:
                                                        while (true) {
                                                        Label_0466_Outer:
                                                            while (true) {
                                                                char b3 = '\0';
                                                                Block_23: {
                                                                    while (true) {
                                                                        Block_30: {
                                                                            break Block_30;
                                                                            iftrue(Label_0433:)((b2 = b3) != c);
                                                                            return sb2.toString();
                                                                            return new Integer(Integer.parseInt(s, 8));
                                                                            b2 = (char)Integer.parseInt(s, 16);
                                                                            break Label_0433;
                                                                            Label_0478:
                                                                            sb2.append('\r');
                                                                            b3 = this.b();
                                                                            iftrue(Label_0517:)(b3 == '\0' || b3 == '\n' || b3 == '\r');
                                                                            break Block_23;
                                                                            try {
                                                                                return new Integer(s);
                                                                            }
                                                                            catch (final Exception ex) {
                                                                                try {
                                                                                    return new Long(s);
                                                                                }
                                                                                catch (final Exception ex2) {
                                                                                    try {
                                                                                        return new Double(s);
                                                                                    }
                                                                                    catch (final Exception ex3) {
                                                                                        return s;
                                                                                    }
                                                                                }
                                                                            }
                                                                            throw this.a("Missing value");
                                                                            Label_0386:
                                                                            b2 = this.b();
                                                                            iftrue(Label_0506:)(b2 == 'b');
                                                                            break Block_26;
                                                                        }
                                                                        iftrue(Label_0453:)(b2 == 't');
                                                                        iftrue(Label_0443:)(b2 == 'u');
                                                                        break Label_0433;
                                                                        sb2 = new StringBuffer();
                                                                        continue Label_0346;
                                                                        Label_0453:
                                                                        b2 = '\t';
                                                                        break Label_0433;
                                                                        Label_0459:
                                                                        s = this.a(2);
                                                                        continue Label_0433_Outer;
                                                                        Label_0489:
                                                                        sb2.append('\n');
                                                                        continue Label_0346;
                                                                        sb2.append(b2);
                                                                        continue Label_0346;
                                                                        Label_0443:
                                                                        s = this.a(4);
                                                                        continue Label_0433_Outer;
                                                                    }
                                                                    this.a();
                                                                    return new a(this);
                                                                }
                                                                iftrue(Label_0386:)(b3 == '\\');
                                                                continue Label_0466_Outer;
                                                            }
                                                            Label_0500:
                                                            b2 = '\f';
                                                            continue Label_0433;
                                                        }
                                                        Label_0506:
                                                        sb2.append('\b');
                                                        continue Label_0346;
                                                    }
                                                }
                                                iftrue(Label_0478:)(b2 == 'r');
                                                continue;
                                            }
                                        }
                                        iftrue(Label_0500:)(b2 == 'f');
                                        continue Block_29_Outer;
                                    }
                                    this.a();
                                    return new b(this);
                                    throw this.a("Missing value");
                                }
                                catch (final Exception ex4) {
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder(" at character ");
        sb.append(this.a);
        return sb.toString();
    }
}
