package com.kingagroot.kingdraw.widget;

import android.text.Spanned;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.text.InputFilter;

public class GTagInputFilter implements InputFilter
{
    private static String regex = "[#,;']";
    int maxLength;
    
    public GTagInputFilter() {
        this.maxLength = 20;
    }
    
    public static String filter(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return "";
        }
        return s.replaceAll(GTagInputFilter.regex, "");
    }
    
    private String getContnet(String string) {
        final char[] charArray = string.toCharArray();
        final int length = charArray.length;
        int i = 0;
        string = "";
        int n = 0;
        while (i < length) {
            final char c = charArray[i];
            if (!this.isChineseByBlock(c) && !this.isChinesePuctuation(c)) {
                ++n;
            }
            else {
                n += 2;
            }
            if (n > this.maxLength) {
                break;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(c);
            string = sb.toString();
            ++i;
        }
        return string;
    }
    
    private boolean isChineseByBlock(final char c) {
        final Character$UnicodeBlock of = Character$UnicodeBlock.of(c);
        final Character$UnicodeBlock cjk_UNIFIED_IDEOGRAPHS = Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
        final boolean b = true;
        if (of == cjk_UNIFIED_IDEOGRAPHS || of == Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character$UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character$UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
            return true;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            boolean b2 = b;
            if (of != Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C) {
                b2 = (of == Character$UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D && b);
            }
            return b2;
        }
        return false;
    }
    
    private boolean isChinesePuctuation(final char c) {
        final Character$UnicodeBlock of = Character$UnicodeBlock.of(c);
        final Character$UnicodeBlock general_PUNCTUATION = Character$UnicodeBlock.GENERAL_PUNCTUATION;
        boolean b = true;
        if (of == general_PUNCTUATION || of == Character$UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character$UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character$UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            if (of != Character$UnicodeBlock.VERTICAL_FORMS) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    public CharSequence filter(final CharSequence charSequence, final int n, final int n2, final Spanned spanned, final int n3, final int n4) {
        final String string = charSequence.toString();
        final String string2 = spanned.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append(string2);
        sb.append(string);
        String s2;
        final String s = s2 = this.getContnet(sb.toString());
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            s2 = s.substring(string2.length());
        }
        Object replaceAll = s2;
        if (s2.matches(GTagInputFilter.regex)) {
            replaceAll = s2.replaceAll(GTagInputFilter.regex, "");
        }
        return (CharSequence)replaceAll;
    }
}
