package org.ccil.cowan.tagsoup;

import java.io.BufferedReader;
import java.io.PushbackReader;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import org.xml.sax.Locator;

public class HTMLScanner implements Scanner, Locator
{
    private static final int A_ADUP = 1;
    private static final int A_ADUP_SAVE = 2;
    private static final int A_ADUP_STAGC = 3;
    private static final int A_ANAME = 4;
    private static final int A_ANAME_ADUP = 5;
    private static final int A_ANAME_ADUP_STAGC = 6;
    private static final int A_AVAL = 7;
    private static final int A_AVAL_STAGC = 8;
    private static final int A_CDATA = 9;
    private static final int A_CMNT = 10;
    private static final int A_DECL = 11;
    private static final int A_EMPTYTAG = 12;
    private static final int A_ENTITY = 13;
    private static final int A_ENTITY_START = 14;
    private static final int A_ETAG = 15;
    private static final int A_GI = 16;
    private static final int A_GI_STAGC = 17;
    private static final int A_LT = 18;
    private static final int A_LT_PCDATA = 19;
    private static final int A_MINUS = 20;
    private static final int A_MINUS2 = 21;
    private static final int A_MINUS3 = 22;
    private static final int A_PCDATA = 23;
    private static final int A_PI = 24;
    private static final int A_PITARGET = 25;
    private static final int A_PITARGET_PI = 26;
    private static final int A_SAVE = 27;
    private static final int A_SKIP = 28;
    private static final int A_SP = 29;
    private static final int A_STAGC = 30;
    private static final int A_UNGET = 31;
    private static final int A_UNSAVE_PCDATA = 32;
    private static final int S_ANAME = 1;
    private static final int S_APOS = 2;
    private static final int S_AVAL = 3;
    private static final int S_BB = 4;
    private static final int S_BBC = 5;
    private static final int S_BBCD = 6;
    private static final int S_BBCDA = 7;
    private static final int S_BBCDAT = 8;
    private static final int S_BBCDATA = 9;
    private static final int S_CDATA = 10;
    private static final int S_CDATA2 = 11;
    private static final int S_CDSECT = 12;
    private static final int S_CDSECT1 = 13;
    private static final int S_CDSECT2 = 14;
    private static final int S_COM = 15;
    private static final int S_COM2 = 16;
    private static final int S_COM3 = 17;
    private static final int S_COM4 = 18;
    private static final int S_DECL = 19;
    private static final int S_DECL2 = 20;
    private static final int S_DONE = 21;
    private static final int S_EMPTYTAG = 22;
    private static final int S_ENT = 23;
    private static final int S_EQ = 24;
    private static final int S_ETAG = 25;
    private static final int S_GI = 26;
    private static final int S_NCR = 27;
    private static final int S_PCDATA = 28;
    private static final int S_PI = 29;
    private static final int S_PITARGET = 30;
    private static final int S_QUOT = 31;
    private static final int S_STAGC = 32;
    private static final int S_TAG = 33;
    private static final int S_TAGWS = 34;
    private static final int S_XNCR = 35;
    private static final String[] debug_actionnames;
    private static final String[] debug_statenames;
    private static int[] statetable;
    static short[][] statetableIndex;
    static int statetableIndexMaxChar;
    private int theCurrentColumn;
    private int theCurrentLine;
    private int theLastColumn;
    private int theLastLine;
    int theNextState;
    char[] theOutputBuffer;
    private String thePublicid;
    int theSize;
    int theState;
    private String theSystemid;
    int[] theWinMap;
    
    static {
        HTMLScanner.statetable = $d2j$hex$dd7f458b$decode_I("010000002f0000000500000016000000010000003d0000000400000003000000010000003e000000060000001c00000001000000000000001b0000000100000001000000ffffffff060000001500000001000000200000000400000018000000010000000a0000000400000018000000010000000900000004000000180000000200000027000000070000002200000002000000000000001b0000000200000002000000ffffffff080000001500000002000000200000001d00000002000000020000000a0000001d0000000200000002000000090000001d0000000200000003000000220000001c0000001f00000003000000270000001c00000002000000030000003e000000080000001c00000003000000000000001b0000002000000003000000ffffffff080000001500000003000000200000001c00000003000000030000000a0000001c0000000300000003000000090000001c0000000300000004000000430000001c0000000500000004000000000000001c0000001300000004000000ffffffff1c0000001500000005000000440000001c0000000600000005000000000000001c0000001300000005000000ffffffff1c0000001500000006000000410000001c0000000700000006000000000000001c0000001300000006000000ffffffff1c0000001500000007000000540000001c0000000800000007000000000000001c0000001300000007000000ffffffff1c0000001500000008000000410000001c0000000900000008000000000000001c0000001300000008000000ffffffff1c00000015000000090000005b0000001c0000000c00000009000000000000001c0000001300000009000000ffffffff1c000000150000000a0000003c0000001b0000000b0000000a000000000000001b0000000a0000000a000000ffffffff17000000150000000b0000002f00000020000000190000000b000000000000001b0000000a0000000b000000ffffffff20000000150000000c0000005d0000001b0000000d0000000c000000000000001b0000000c0000000c000000ffffffff1c000000150000000d0000005d0000001b0000000e0000000d000000000000001b0000000c0000000d000000ffffffff1c000000150000000e0000003e000000090000001c0000000e0000005d0000001b0000000e0000000e000000000000001b0000000c0000000e000000ffffffff1c000000150000000f0000002d0000001c000000100000000f000000000000001b000000100000000f000000ffffffff0a00000015000000100000002d0000001c0000001100000010000000000000001b0000001000000010000000ffffffff0a00000015000000110000002d0000001c000000120000001100000000000000140000001000000011000000ffffffff0a00000015000000120000002d0000001600000012000000120000003e0000000a0000001c0000001200000000000000150000001000000012000000ffffffff0a00000015000000130000002d0000001c0000000f000000130000003e0000001c0000001c000000130000005b0000001c0000000400000013000000000000001b0000001400000013000000ffffffff1c00000015000000140000003e0000000b0000001c00000014000000000000001b0000001400000014000000ffffffff1c00000015000000160000003e0000000c0000001c00000016000000000000001b0000000100000016000000200000001c00000022000000160000000a0000001c0000002200000016000000090000001c0000002200000017000000000000000d0000001700000017000000ffffffff0d00000015000000180000003d0000001c00000003000000180000003e000000030000001c0000001800000000000000020000000100000018000000ffffffff030000001500000018000000200000001c00000018000000180000000a0000001c0000001800000018000000090000001c00000018000000190000003e0000000f0000001c00000019000000000000001b0000001900000019000000ffffffff0f0000001500000019000000200000001c00000019000000190000000a0000001c0000001900000019000000090000001c000000190000001a0000002f0000001c000000160000001a0000003e000000110000001c0000001a000000000000001b0000001a0000001a000000ffffffff1c000000150000001a0000002000000010000000220000001a0000000a00000010000000220000001a0000000900000010000000220000001b000000000000000d0000001b0000001b000000ffffffff0d000000150000001c000000260000000e000000170000001c0000003c00000017000000210000001c000000000000001b0000001c0000001c000000ffffffff17000000150000001d0000003e000000180000001c0000001d000000000000001b0000001d0000001d000000ffffffff18000000150000001e0000003e0000001a0000001c0000001e000000000000001b0000001e0000001e000000ffffffff1a000000150000001e00000020000000190000001d0000001e0000000a000000190000001d0000001e00000009000000190000001d0000001f0000002200000007000000220000001f000000000000001b0000001f0000001f000000ffffffff08000000150000001f000000200000001d0000001f0000001f0000000a0000001d0000001f0000001f000000090000001d0000001f000000200000003e000000080000001c00000020000000000000001b0000002000000020000000ffffffff080000001500000020000000200000000700000022000000200000000a00000007000000220000002000000009000000070000002200000021000000210000001c00000013000000210000002f0000001c00000019000000210000003c0000001b00000021000000210000003f0000001c0000001e00000021000000000000001b0000001a00000021000000ffffffff13000000150000002100000020000000120000001c000000210000000a000000120000001c0000002100000009000000120000001c000000220000002f0000001c00000016000000220000003e0000001e0000001c00000022000000000000001b0000000100000022000000ffffffff1e0000001500000022000000200000001c00000022000000220000000a0000001c0000002200000022000000090000001c0000002200000023000000000000000d0000002300000023000000ffffffff0d00000015000000");
        debug_actionnames = new String[] { "", "A_ADUP", "A_ADUP_SAVE", "A_ADUP_STAGC", "A_ANAME", "A_ANAME_ADUP", "A_ANAME_ADUP_STAGC", "A_AVAL", "A_AVAL_STAGC", "A_CDATA", "A_CMNT", "A_DECL", "A_EMPTYTAG", "A_ENTITY", "A_ENTITY_START", "A_ETAG", "A_GI", "A_GI_STAGC", "A_LT", "A_LT_PCDATA", "A_MINUS", "A_MINUS2", "A_MINUS3", "A_PCDATA", "A_PI", "A_PITARGET", "A_PITARGET_PI", "A_SAVE", "A_SKIP", "A_SP", "A_STAGC", "A_UNGET", "A_UNSAVE_PCDATA" };
        debug_statenames = new String[] { "", "S_ANAME", "S_APOS", "S_AVAL", "S_BB", "S_BBC", "S_BBCD", "S_BBCDA", "S_BBCDAT", "S_BBCDATA", "S_CDATA", "S_CDATA2", "S_CDSECT", "S_CDSECT1", "S_CDSECT2", "S_COM", "S_COM2", "S_COM3", "S_COM4", "S_DECL", "S_DECL2", "S_DONE", "S_EMPTYTAG", "S_ENT", "S_EQ", "S_ETAG", "S_GI", "S_NCR", "S_PCDATA", "S_PI", "S_PITARGET", "S_QUOT", "S_STAGC", "S_TAG", "S_TAGWS", "S_XNCR" };
        int n = 0;
        int n2 = -1;
        int n3 = -1;
        while (true) {
            final int[] statetable = HTMLScanner.statetable;
            if (n >= statetable.length) {
                break;
            }
            int n4;
            if (statetable[n] > (n4 = n3)) {
                n4 = statetable[n];
            }
            final int[] statetable2 = HTMLScanner.statetable;
            final int n5 = n + 1;
            int n6;
            if (statetable2[n5] > (n6 = n2)) {
                n6 = statetable2[n5];
            }
            n += 4;
            n2 = n6;
            n3 = n4;
        }
        HTMLScanner.statetableIndexMaxChar = n2 + 1;
        HTMLScanner.statetableIndex = new short[n3 + 1][n2 + 3];
        for (int i = 0; i <= n3; ++i) {
            for (int j = -2; j <= n2; ++j) {
                int n7 = 0;
                int n8 = 0;
                int n9 = -1;
                Label_0646: {
                    while (true) {
                        final int[] statetable3 = HTMLScanner.statetable;
                        if (n7 >= statetable3.length) {
                            break;
                        }
                        int n10;
                        int n11;
                        if (i != statetable3[n7]) {
                            n10 = n8;
                            n11 = n9;
                            if (n8 != 0) {
                                break;
                            }
                        }
                        else {
                            final int n12 = n7 + 1;
                            if (statetable3[n12] == 0) {
                                n10 = statetable3[n7 + 2];
                                n11 = n7;
                            }
                            else {
                                n10 = n8;
                                n11 = n9;
                                if (statetable3[n12] == j) {
                                    final int n13 = statetable3[n7 + 2];
                                    break Label_0646;
                                }
                            }
                        }
                        n7 += 4;
                        n8 = n10;
                        n9 = n11;
                    }
                    n7 = n9;
                }
                HTMLScanner.statetableIndex[i][j + 2] = (short)n7;
            }
        }
    }
    
    public HTMLScanner() {
        this.theOutputBuffer = new char[200];
        this.theWinMap = new int[] { 8364, 65533, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 65533, 381, 65533, 65533, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 65533, 382, 376 };
    }
    
    public static void main(final String[] array) throws IOException, SAXException {
        final HTMLScanner htmlScanner = new HTMLScanner();
        final InputStreamReader inputStreamReader = new InputStreamReader(System.in, "UTF-8");
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter((OutputStream)System.out, "UTF-8");
        htmlScanner.scan((Reader)inputStreamReader, new PYXWriter((Writer)outputStreamWriter));
        ((Writer)outputStreamWriter).close();
    }
    
    private void mark() {
        this.theLastColumn = this.theCurrentColumn;
        this.theLastLine = this.theCurrentLine;
    }
    
    private static String nicechar(final int n) {
        if (n == 10) {
            return "\\n";
        }
        if (n < 32) {
            final StringBuffer sb = new StringBuffer();
            sb.append("0x");
            sb.append(Integer.toHexString(n));
            return sb.toString();
        }
        final StringBuffer sb2 = new StringBuffer();
        sb2.append("'");
        sb2.append((char)n);
        sb2.append("'");
        return sb2.toString();
    }
    
    private void save(final int n, final ScanHandler scanHandler) throws IOException, SAXException {
        final int theSize = this.theSize;
        final char[] theOutputBuffer = this.theOutputBuffer;
        if (theSize >= theOutputBuffer.length - 20) {
            final int theState = this.theState;
            if (theState != 28 && theState != 10) {
                final char[] theOutputBuffer2 = new char[theOutputBuffer.length * 2];
                System.arraycopy((Object)theOutputBuffer, 0, (Object)theOutputBuffer2, 0, theSize + 1);
                this.theOutputBuffer = theOutputBuffer2;
            }
            else {
                scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                this.theSize = 0;
            }
        }
        this.theOutputBuffer[this.theSize++] = (char)n;
    }
    
    private void unread(final PushbackReader pushbackReader, final int n) throws IOException {
        if (n != -1) {
            pushbackReader.unread(n);
        }
    }
    
    public int getColumnNumber() {
        return this.theLastColumn;
    }
    
    public int getLineNumber() {
        return this.theLastLine;
    }
    
    public String getPublicId() {
        return this.thePublicid;
    }
    
    public String getSystemId() {
        return this.theSystemid;
    }
    
    @Override
    public void resetDocumentLocator(final String thePublicid, final String theSystemid) {
        this.thePublicid = thePublicid;
        this.theSystemid = theSystemid;
        this.theCurrentColumn = 0;
        this.theCurrentLine = 0;
        this.theLastColumn = 0;
        this.theLastLine = 0;
    }
    
    @Override
    public void scan(final Reader reader, final ScanHandler scanHandler) throws IOException, SAXException {
        this.theState = 28;
        PushbackReader pushbackReader;
        if (reader instanceof BufferedReader) {
            pushbackReader = new PushbackReader(reader, 5);
        }
        else {
            pushbackReader = new PushbackReader((Reader)new BufferedReader(reader), 5);
        }
        final int read = pushbackReader.read();
        if (read != 65279) {
            this.unread(pushbackReader, read);
        }
    Label_1680_Outer:
        while (this.theState != 21) {
            int read2;
            final int n = read2 = pushbackReader.read();
            if (n >= 128 && (read2 = n) <= 159) {
                read2 = this.theWinMap[n - 128];
            }
            int n2;
            if ((n2 = read2) == 13) {
                final int read3 = pushbackReader.read();
                if ((n2 = read3) != 10) {
                    this.unread(pushbackReader, read3);
                    n2 = 10;
                }
            }
            if (n2 == 10) {
                ++this.theCurrentLine;
                this.theCurrentColumn = 0;
            }
            else {
                ++this.theCurrentColumn;
            }
            if (n2 < 32 && n2 != 10 && n2 != 9 && n2 != -1) {
                continue;
            }
            int n3;
            if (n2 >= -1 && n2 < HTMLScanner.statetableIndexMaxChar) {
                n3 = n2;
            }
            else {
                n3 = -2;
            }
            final short n4 = HTMLScanner.statetableIndex[this.theState][n3 + 2];
            int n5;
            if (n4 != -1) {
                final int[] statetable = HTMLScanner.statetable;
                n5 = statetable[n4 + 2];
                this.theNextState = statetable[n4 + 3];
            }
            else {
                n5 = 0;
            }
            while (true) {
                switch (n5) {
                    default: {
                        final StringBuffer sb = new StringBuffer();
                        sb.append("Can't process state ");
                        sb.append(n5);
                        throw new Error(sb.toString());
                    }
                    case 13: {
                        this.mark();
                        final char c = (char)n2;
                        if (this.theState == 23 && c == '#') {
                            this.theNextState = 27;
                            this.save(n2, scanHandler);
                            break Label_1680;
                        }
                        if (this.theState == 27 && (c == 'x' || c == 'X')) {
                            this.theNextState = 35;
                            this.save(n2, scanHandler);
                            break Label_1680;
                        }
                        if (this.theState == 23 && Character.isLetterOrDigit(c)) {
                            this.save(n2, scanHandler);
                            break Label_1680;
                        }
                        if (this.theState == 27 && Character.isDigit(c)) {
                            this.save(n2, scanHandler);
                            break Label_1680;
                        }
                        if (this.theState == 35 && (Character.isDigit(c) || "abcdefABCDEF".indexOf((int)c) != -1)) {
                            this.save(n2, scanHandler);
                            break Label_1680;
                        }
                        scanHandler.entity(this.theOutputBuffer, 1, this.theSize - 1);
                        final int entity = scanHandler.getEntity();
                        if (entity != 0) {
                            this.theSize = 0;
                            int n6 = entity;
                            if (entity >= 128 && (n6 = entity) <= 159) {
                                n6 = this.theWinMap[entity - 128];
                            }
                            if (n6 >= 32) {
                                if (n6 < 55296 || n6 > 57343) {
                                    if (n6 <= 65535) {
                                        this.save(n6, scanHandler);
                                    }
                                    else {
                                        final int n7 = n6 - 65536;
                                        this.save((n7 >> 10) + 55296, scanHandler);
                                        this.save((n7 & 0x3FF) + 56320, scanHandler);
                                    }
                                }
                            }
                            if (n2 != 59) {
                                this.unread(pushbackReader, n2);
                                --this.theCurrentColumn;
                            }
                        }
                        else {
                            this.unread(pushbackReader, n2);
                            --this.theCurrentColumn;
                        }
                        this.theNextState = 28;
                        break Label_1680;
                    }
                    case 28: {
                        this.theState = this.theNextState;
                        continue Label_1680_Outer;
                    }
                    case 32: {
                        final int theSize = this.theSize;
                        if (theSize > 0) {
                            this.theSize = theSize - 1;
                        }
                        scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 31: {
                        this.unread(pushbackReader, n2);
                        --this.theCurrentColumn;
                        continue;
                    }
                    case 30: {
                        scanHandler.stagc(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 29: {
                        this.save(32, scanHandler);
                        continue;
                    }
                    case 27: {
                        this.save(n2, scanHandler);
                        continue;
                    }
                    case 26: {
                        scanHandler.pitarget(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.pi(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 25: {
                        scanHandler.pitarget(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 24: {
                        this.mark();
                        scanHandler.pi(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 23: {
                        this.mark();
                        scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 22: {
                        this.save(45, scanHandler);
                        this.save(32, scanHandler);
                        continue;
                    }
                    case 21: {
                        this.save(45, scanHandler);
                        this.save(32, scanHandler);
                    }
                    case 20: {
                        this.save(45, scanHandler);
                        this.save(n2, scanHandler);
                        continue;
                    }
                    case 19: {
                        this.mark();
                        this.save(60, scanHandler);
                        scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 18: {
                        this.mark();
                        this.save(60, scanHandler);
                        this.save(n2, scanHandler);
                        continue;
                    }
                    case 17: {
                        scanHandler.gi(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.stagc(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 16: {
                        scanHandler.gi(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 15: {
                        scanHandler.etag(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 14: {
                        scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        this.save(n2, scanHandler);
                        continue;
                    }
                    case 12: {
                        this.mark();
                        final int theSize2 = this.theSize;
                        if (theSize2 > 0) {
                            scanHandler.gi(this.theOutputBuffer, 0, theSize2);
                        }
                        this.theSize = 0;
                        scanHandler.stage(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 11: {
                        scanHandler.decl(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 10: {
                        this.mark();
                        scanHandler.cmnt(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 9: {
                        this.mark();
                        final int theSize3 = this.theSize;
                        if (theSize3 > 1) {
                            this.theSize = theSize3 - 2;
                        }
                        scanHandler.pcdata(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 8: {
                        scanHandler.aval(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.stagc(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 7: {
                        scanHandler.aval(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 6: {
                        scanHandler.aname(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.adup(this.theOutputBuffer, 0, 0);
                        scanHandler.stagc(this.theOutputBuffer, 0, this.theSize);
                        continue;
                    }
                    case 5: {
                        scanHandler.aname(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.adup(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 4: {
                        scanHandler.aname(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 3: {
                        scanHandler.adup(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        scanHandler.stagc(this.theOutputBuffer, 0, 0);
                        continue;
                    }
                    case 2: {
                        scanHandler.adup(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        this.save(n2, scanHandler);
                        continue;
                    }
                    case 1: {
                        scanHandler.adup(this.theOutputBuffer, 0, this.theSize);
                        this.theSize = 0;
                        continue;
                    }
                    case 0: {
                        final StringBuffer sb2 = new StringBuffer();
                        sb2.append("HTMLScanner can't cope with ");
                        sb2.append(Integer.toString(n2));
                        sb2.append(" in state ");
                        sb2.append(Integer.toString(this.theState));
                        throw new Error(sb2.toString());
                    }
                }
                break;
            }
        }
        scanHandler.eof(this.theOutputBuffer, 0, 0);
    }
    
    @Override
    public void startCDATA() {
        this.theNextState = 10;
    }
}
