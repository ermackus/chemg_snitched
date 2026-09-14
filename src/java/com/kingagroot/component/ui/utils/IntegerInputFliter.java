package com.kingagroot.component.ui.utils;

import android.text.Spanned;
import java.util.regex.Pattern;
import android.text.InputFilter;

public class IntegerInputFliter implements InputFilter
{
    private int MAX;
    private final Pattern mPattern;
    
    public IntegerInputFliter() {
        this.MAX = 255;
        this.mPattern = Pattern.compile("\\d*");
    }
    
    public CharSequence filter(final CharSequence charSequence, int n, int max, final Spanned spanned, final int n2, final int n3) {
        final String string = charSequence.toString();
        final String string2 = spanned.toString();
        if (this.mPattern.matcher((CharSequence)string).matches()) {
            n = string2.length();
            final StringBuilder sb = new StringBuilder();
            sb.append(string2.substring(0, n2));
            sb.append(string);
            sb.append(string2.substring(n3, n));
            final String string3 = sb.toString();
            try {
                n = Integer.parseInt(string3);
                max = this.MAX;
                if (n <= max) {
                    return charSequence;
                }
            }
            catch (final NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return (CharSequence)"";
    }
    
    public void setMAX(final int max) {
        this.MAX = max;
    }
}
