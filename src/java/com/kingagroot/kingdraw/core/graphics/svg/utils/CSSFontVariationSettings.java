package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.Iterator;
import java.text.DecimalFormat;
import java.util.Map$Entry;
import java.util.Map;
import java.util.HashMap;

public class CSSFontVariationSettings
{
    private static final String NORMAL = "normal";
    static final String VARIATION_ITALIC = "ital";
    static final Float VARIATION_ITALIC_VALUE_ON;
    static final String VARIATION_OBLIQUE = "slnt";
    static final Float VARIATION_OBLIQUE_VALUE_ON;
    static final String VARIATION_WEIGHT = "wght";
    static final String VARIATION_WIDTH = "wdth";
    private final HashMap<String, Float> settings;
    
    static {
        VARIATION_ITALIC_VALUE_ON = 1.0f;
        VARIATION_OBLIQUE_VALUE_ON = -14.0f;
    }
    
    public CSSFontVariationSettings() {
        this.settings = (HashMap<String, Float>)new HashMap();
    }
    
    public CSSFontVariationSettings(final CSSFontVariationSettings cssFontVariationSettings) {
        this.settings = (HashMap<String, Float>)new HashMap((Map)cssFontVariationSettings.settings);
    }
    
    private CSSFontVariationSettings(final HashMap<String, Float> settings) {
        this.settings = settings;
    }
    
    private static FontVariationEntry nextFeatureEntry(final TextScanner textScanner) {
        textScanner.skipWhitespace();
        final String nextQuotedString = textScanner.nextQuotedString();
        FontVariationEntry fontVariationEntry = null;
        if (nextQuotedString != null) {
            if (nextQuotedString.length() != 4) {
                fontVariationEntry = fontVariationEntry;
            }
            else {
                textScanner.skipWhitespace();
                if (textScanner.empty()) {
                    return null;
                }
                final Float value = textScanner.nextFloat();
                if (value == null) {
                    return null;
                }
                fontVariationEntry = new FontVariationEntry(nextQuotedString, value);
            }
        }
        return fontVariationEntry;
    }
    
    static CSSFontVariationSettings parseFontVariationSettings(final String s) {
        final CSSFontVariationSettings cssFontVariationSettings = new CSSFontVariationSettings();
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        if (textScanner.consume("normal")) {
            return null;
        }
        while (!textScanner.empty()) {
            final FontVariationEntry nextFeatureEntry = nextFeatureEntry(textScanner);
            if (nextFeatureEntry == null) {
                return null;
            }
            cssFontVariationSettings.settings.put((Object)nextFeatureEntry.name, (Object)nextFeatureEntry.val);
            textScanner.skipCommaWhitespace();
        }
        return cssFontVariationSettings;
    }
    
    public void addSetting(final String s, final float n) {
        this.settings.put((Object)s, (Object)n);
    }
    
    public void applySettings(final CSSFontVariationSettings cssFontVariationSettings) {
        if (cssFontVariationSettings == null) {
            return;
        }
        this.settings.putAll((Map)cssFontVariationSettings.settings);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Map$Entry map$Entry : this.settings.entrySet()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append("'");
            sb.append((String)map$Entry.getKey());
            sb.append("' ");
            sb.append(new DecimalFormat("#.##").format(map$Entry.getValue()));
        }
        return sb.toString();
    }
    
    private static class FontVariationEntry
    {
        String name;
        Float val;
        
        public FontVariationEntry(final String name, final Float val) {
            this.name = name;
            this.val = val;
        }
    }
}
