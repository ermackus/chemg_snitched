package com.kingagroot.kingdraw.core.graphics.svg.utils;

class NumberParser
{
    private static final float[] negativePowersOf10;
    private static final float[] positivePowersOf10;
    private int pos;
    
    static {
        positivePowersOf10 = new float[] { 1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f, 9.9999998E10f, 1.0E12f, 9.9999998E12f, 1.0E14f, 9.9999999E14f, 1.00000003E16f, 9.9999998E16f, 9.9999998E17f, 1.0E19f, 1.0E20f, 1.0E21f, 1.0E22f, 1.0E23f, 1.0E24f, 1.0E25f, 1.0E26f, 1.0E27f, 1.0E28f, 1.0E29f, 1.0E30f, 1.0E31f, 1.0E32f, 1.0E33f, 1.0E34f, 1.0E35f, 1.0E36f, 1.0E37f, 1.0E38f };
        negativePowersOf10 = new float[] { 1.0f, 0.1f, 0.01f, 0.001f, 1.0E-4f, 1.0E-5f, 1.0E-6f, 1.0E-7f, 1.0E-8f, 1.0E-9f, 1.0E-10f, 1.0E-11f, 1.0E-12f, 1.0E-13f, 1.0E-14f, 1.0E-15f, 1.0E-16f, 1.0E-17f, 1.0E-18f, 1.0E-19f, 1.0E-20f, 1.0E-21f, 1.0E-22f, 1.0E-23f, 1.0E-24f, 1.0E-25f, 1.0E-26f, 1.0E-27f, 1.0E-28f, 1.0E-29f, 1.0E-30f, 1.0E-31f, 1.0E-32f, 1.0E-33f, 1.0E-34f, 1.0E-35f, 1.0E-36f, 1.0E-37f, 1.0E-38f };
    }
    
    int getEndPos() {
        return this.pos;
    }
    
    float parseNumber(final String s, int n, int n2) {
        this.pos = n;
        if (n >= n2) {
            return Float.NaN;
        }
        n = s.charAt(n);
        int n3 = 0;
        Label_0057: {
            if (n != 43) {
                if (n != 45) {
                    n3 = 0;
                    break Label_0057;
                }
                n = 1;
            }
            else {
                n = 0;
            }
            ++this.pos;
            n3 = n;
        }
        final int pos = this.pos;
        long n4 = 0L;
        int n5 = 0;
        n = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        while (true) {
            final int pos2 = this.pos;
            if (pos2 >= n2) {
                break;
            }
            final char char1 = s.charAt(pos2);
            if (char1 == '0') {
                if (n5 == 0) {
                    ++n6;
                }
                else {
                    ++n;
                }
            }
            else if (char1 >= '1' && char1 <= '9') {
                int i;
                for (i = n; i > 0; --i) {
                    if (n4 > 922337203685477580L) {
                        return Float.NaN;
                    }
                    n4 *= 10L;
                }
                if (n4 > 922337203685477580L) {
                    return Float.NaN;
                }
                final long n9 = n4 * 10L + (char1 - '0');
                n5 = n5 + n + 1;
                n = i;
                n4 = n9;
                if (n9 < 0L) {
                    return Float.NaN;
                }
            }
            else {
                if (char1 != '.') {
                    break;
                }
                if (n7 != 0) {
                    break;
                }
                n8 = this.pos - pos;
                n7 = 1;
            }
            ++this.pos;
        }
        if (n7 != 0 && this.pos == n8 + 1) {
            return Float.NaN;
        }
        int n10;
        if ((n10 = n5) == 0) {
            if (n6 == 0) {
                return Float.NaN;
            }
            n10 = 1;
        }
        int n11 = n;
        if (n7 != 0) {
            n11 = n8 - n6 - n10;
        }
        final int pos3 = this.pos;
        n = n11;
        Label_0616: {
            if (pos3 < n2) {
                final char char2 = s.charAt(pos3);
                if (char2 != 'E') {
                    n = n11;
                    if (char2 != 'e') {
                        break Label_0616;
                    }
                }
                n = this.pos + 1;
                if ((this.pos = n) == n2) {
                    return Float.NaN;
                }
                n = s.charAt(n);
                int n12 = 0;
                boolean b = false;
                Label_0503: {
                    Label_0497: {
                        if (n != 43) {
                            if (n != 45) {
                                switch (n) {
                                    default: {
                                        --this.pos;
                                        n12 = 0;
                                        b = true;
                                        break Label_0503;
                                    }
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57: {
                                        n = 0;
                                        break Label_0497;
                                    }
                                }
                            }
                            else {
                                n = 1;
                            }
                        }
                        else {
                            n = 0;
                        }
                        ++this.pos;
                    }
                    b = false;
                    n12 = n;
                }
                n = n11;
                if (!b) {
                    final int pos4 = this.pos;
                    n = 0;
                    while (true) {
                        final int pos5 = this.pos;
                        if (pos5 >= n2) {
                            break;
                        }
                        final char char3 = s.charAt(pos5);
                        if (char3 < '0' || char3 > '9') {
                            break;
                        }
                        if (n > 214748364) {
                            return Float.NaN;
                        }
                        n = n * 10 + (char3 - '0');
                        ++this.pos;
                    }
                    if (this.pos == pos4) {
                        return Float.NaN;
                    }
                    if (n12 != 0) {
                        n = n11 - n;
                    }
                    else {
                        n += n11;
                    }
                }
            }
        }
        n2 = n10 + n;
        if (n2 <= 39 && n2 >= -44) {
            float n14;
            float n13 = n14 = (float)n4;
            Label_0727: {
                if (n4 != 0L) {
                    float n15;
                    if (n > 0) {
                        n15 = NumberParser.positivePowersOf10[n];
                    }
                    else {
                        n14 = n13;
                        if (n >= 0) {
                            break Label_0727;
                        }
                        float n16 = n13;
                        if ((n2 = n) < -38) {
                            n16 = (float)(n13 * 1.0E-20);
                            n2 = n + 20;
                        }
                        final float n17 = NumberParser.negativePowersOf10[-n2];
                        n13 = n16;
                        n15 = n17;
                    }
                    n14 = n13 * n15;
                }
            }
            float n18 = n14;
            if (n3 != 0) {
                n18 = -n14;
            }
            return n18;
        }
        return Float.NaN;
    }
}
