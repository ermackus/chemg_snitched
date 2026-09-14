package com.kingagroot.kingdraw.core.graphics.svg.utils;

class IntegerParser
{
    private final int pos;
    private final long value;
    
    IntegerParser(final long value, final int pos) {
        this.value = value;
        this.pos = pos;
    }
    
    static IntegerParser parseHex(final String s, final int n, final int n2) {
        if (n >= n2) {
            return null;
        }
        long n3 = 0L;
        int i;
        for (i = n; i < n2; ++i) {
            int char1 = s.charAt(i);
            if (char1 >= 48 && char1 <= 57) {
                n3 = n3 * 16L + (char1 - 48);
            }
            else {
                long n4;
                if (char1 >= 65 && char1 <= 70) {
                    n4 = n3 * 16L;
                    char1 -= 65;
                }
                else {
                    if (char1 < 97 || char1 > 102) {
                        break;
                    }
                    n4 = n3 * 16L;
                    char1 -= 97;
                }
                n3 = n4 + char1 + 10L;
            }
            if (n3 > 4294967295L) {
                return null;
            }
        }
        if (i == n) {
            return null;
        }
        return new IntegerParser(n3, i);
    }
    
    static IntegerParser parseInt(final String s, int i, final int n, final boolean b) {
        if (i >= n) {
            return null;
        }
        final boolean b2 = false;
        final int n2 = 0;
        int n3 = b2 ? 1 : 0;
        int n4 = i;
        Label_0067: {
            if (b) {
                final char char1 = s.charAt(i);
                n3 = n2;
                if (char1 != '+') {
                    if (char1 != '-') {
                        n3 = (b2 ? 1 : 0);
                        n4 = i;
                        break Label_0067;
                    }
                    n3 = 1;
                }
                n4 = i + 1;
            }
        }
        long n5 = 0L;
        char char2;
        for (i = n4; i < n; ++i) {
            char2 = s.charAt(i);
            if (char2 < '0' || char2 > '9') {
                break;
            }
            if (n3 != 0) {
                if ((n5 = n5 * 10L - (char2 - '0')) < -2147483648L) {
                    return null;
                }
            }
            else if ((n5 = n5 * 10L + (char2 - '0')) > 2147483647L) {
                return null;
            }
        }
        if (i == n4) {
            return null;
        }
        return new IntegerParser(n5, i);
    }
    
    int getEndPos() {
        return this.pos;
    }
    
    public int value() {
        return (int)this.value;
    }
}
