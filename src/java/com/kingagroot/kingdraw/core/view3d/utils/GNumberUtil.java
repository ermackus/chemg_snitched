package com.kingagroot.kingdraw.core.view3d.utils;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;

public final class GNumberUtil
{
    public static String format(final double n) {
        return format("0.00", n);
    }
    
    public static String format(final String s, final double n) {
        final DecimalFormat decimalFormat = new DecimalFormat(s);
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat.format(n);
    }
    
    public static String format(final DecimalFormat decimalFormat, final double n) {
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat.format(n);
    }
    
    public static double keepPrecision(final double n, final int n2) {
        return new BigDecimal(n).setScale(n2, 4).doubleValue();
    }
    
    public static float keepPrecision(final float n, final int n2) {
        if (Float.valueOf(n).isNaN()) {
            return 0.0f;
        }
        return new BigDecimal((double)n).setScale(n2, 4).floatValue();
    }
    
    public static String keepPrecision(final Number n, final int n2) {
        return keepPrecision(String.valueOf((Object)n), n2);
    }
    
    public static String keepPrecision(final String s, final int n) {
        return new BigDecimal(s).setScale(n, 4).toPlainString();
    }
}
