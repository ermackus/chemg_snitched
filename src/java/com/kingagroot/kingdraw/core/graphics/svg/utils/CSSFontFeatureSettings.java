package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CSSFontFeatureSettings
{
    static CSSFontFeatureSettings CAPS_ALL_OFF;
    private static CSSFontFeatureSettings CAPS_SMALL_CAPS;
    static CSSFontFeatureSettings EAST_ASIAN_ALL_OFF;
    public static final CSSFontFeatureSettings ERROR;
    public static final String FEATURE_AFRC = "afrc";
    private static final String FEATURE_C2PC = "c2pc";
    private static final String FEATURE_C2SC = "c2sc";
    public static final String FEATURE_CALT = "calt";
    public static final String FEATURE_CLIG = "clig";
    public static final String FEATURE_DLIG = "dlig";
    public static final String FEATURE_FRAC = "frac";
    public static final String FEATURE_FWID = "fwid";
    public static final String FEATURE_HLIG = "hlig";
    public static final String FEATURE_JP04 = "jp04";
    public static final String FEATURE_JP78 = "jp78";
    public static final String FEATURE_JP83 = "jp83";
    public static final String FEATURE_JP90 = "jp90";
    public static final String FEATURE_KERN = "kern";
    public static final String FEATURE_LIGA = "liga";
    public static final String FEATURE_LNUM = "lnum";
    private static final String FEATURE_OFF = "off";
    private static final String FEATURE_ON = "on";
    public static final String FEATURE_ONUM = "onum";
    public static final String FEATURE_ORDN = "ordn";
    private static final String FEATURE_PCAP = "pcap";
    public static final String FEATURE_PNUM = "pnum";
    public static final String FEATURE_PWID = "pwid";
    public static final String FEATURE_RUBY = "ruby";
    private static final String FEATURE_SMCP = "smcp";
    public static final String FEATURE_SMPL = "smpl";
    private static final String FEATURE_SUBS = "subs";
    private static final String FEATURE_SUPS = "sups";
    private static final String FEATURE_TITL = "titl";
    public static final String FEATURE_TNUM = "tnum";
    public static final String FEATURE_TRAD = "trad";
    private static final String FEATURE_UNIC = "unic";
    public static final String FEATURE_ZERO = "zero";
    public static final CSSFontFeatureSettings FONT_FEATURE_SETTINGS_NORMAL;
    private static final String FONT_VARIANT_ALL_PETITE_CAPS = "all-petite-caps";
    private static final String FONT_VARIANT_ALL_SMALL_CAPS = "all-small-caps";
    private static final String FONT_VARIANT_AUTO = "auto";
    private static final String FONT_VARIANT_COMMON_LIGATURES = "common-ligatures";
    private static final String FONT_VARIANT_CONTEXTUAL_LIGATURES = "contextual";
    private static final String FONT_VARIANT_DIAGONAL_FRACTIONS = "diagonal-fractions";
    private static final String FONT_VARIANT_DISCRETIONARY_LIGATURES = "discretionary-ligatures";
    private static final String FONT_VARIANT_FULL_WIDTH = "full-width";
    private static final String FONT_VARIANT_HISTORICAL_LIGATURES = "historical-ligatures";
    private static final String FONT_VARIANT_JIS04 = "jis04";
    private static final String FONT_VARIANT_JIS78 = "jis78";
    private static final String FONT_VARIANT_JIS83 = "jis83";
    private static final String FONT_VARIANT_JIS90 = "jis90";
    private static final String FONT_VARIANT_LINING_NUMS = "lining-nums";
    private static final String FONT_VARIANT_NONE = "none";
    static final String FONT_VARIANT_NORMAL = "normal";
    private static final String FONT_VARIANT_NO_COMMON_LIGATURES = "no-common-ligatures";
    private static final String FONT_VARIANT_NO_CONTEXTUAL_LIGATURES = "no-contextual";
    private static final String FONT_VARIANT_NO_DISCRETIONARY_LIGATURES = "no-discretionary-ligatures";
    private static final String FONT_VARIANT_NO_HISTORICAL_LIGATURES = "no-historical-ligatures";
    private static final String FONT_VARIANT_OLDSTYLE_NUMS = "oldstyle-nums";
    private static final String FONT_VARIANT_ORDINAL = "ordinal";
    private static final String FONT_VARIANT_PETITE_CAPS = "petite-caps";
    private static final String FONT_VARIANT_PROPORTIONAL_NUMS = "proportional-nums";
    private static final String FONT_VARIANT_PROPORTIONAL_WIDTH = "proportional-width";
    private static final String FONT_VARIANT_RUBY = "ruby";
    private static final String FONT_VARIANT_SIMPLIFIED = "simplified";
    private static final String FONT_VARIANT_SLASHED_ZERO = "slashed-zero";
    static final String FONT_VARIANT_SMALL_CAPS = "small-caps";
    private static final String FONT_VARIANT_STACKED_FRACTIONS = "stacked-fractions";
    private static final String FONT_VARIANT_SUB = "sub";
    private static final String FONT_VARIANT_SUPER = "super";
    private static final String FONT_VARIANT_TABULAR_NUMS = "tabular-nums";
    private static final String FONT_VARIANT_TITLING_CAPS = "titling-caps";
    private static final String FONT_VARIANT_TRADITIONAL = "traditional";
    private static final String FONT_VARIANT_UNICASE = "unicase";
    private static CSSFontFeatureSettings LIGATURES_ALL_OFF;
    static CSSFontFeatureSettings LIGATURES_NORMAL;
    static CSSFontFeatureSettings NUMERIC_ALL_OFF;
    static CSSFontFeatureSettings POSITION_ALL_OFF;
    private static final String TOKEN_ERROR = "ERR";
    private static final int VALUE_OFF = 0;
    private static final int VALUE_ON = 1;
    private final HashMap<String, Integer> settings;
    
    static {
        FONT_FEATURE_SETTINGS_NORMAL = makeDefaultSettings();
        ERROR = new CSSFontFeatureSettings((HashMap<String, Integer>)null);
        CSSFontFeatureSettings.LIGATURES_NORMAL = null;
        CSSFontFeatureSettings.LIGATURES_ALL_OFF = null;
        CSSFontFeatureSettings.POSITION_ALL_OFF = null;
        CSSFontFeatureSettings.CAPS_ALL_OFF = null;
        CSSFontFeatureSettings.CAPS_SMALL_CAPS = null;
        CSSFontFeatureSettings.NUMERIC_ALL_OFF = null;
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF = null;
        final HashMap<String, Integer> settings = (CSSFontFeatureSettings.LIGATURES_NORMAL = new CSSFontFeatureSettings()).settings;
        final Integer value = 1;
        settings.put((Object)"liga", (Object)value);
        CSSFontFeatureSettings.LIGATURES_NORMAL.settings.put((Object)"clig", (Object)value);
        final HashMap<String, Integer> settings2 = CSSFontFeatureSettings.LIGATURES_NORMAL.settings;
        final Integer value2 = 0;
        settings2.put((Object)"dlig", (Object)value2);
        CSSFontFeatureSettings.LIGATURES_NORMAL.settings.put((Object)"hlig", (Object)value2);
        CSSFontFeatureSettings.LIGATURES_NORMAL.settings.put((Object)"calt", (Object)value);
        (CSSFontFeatureSettings.POSITION_ALL_OFF = new CSSFontFeatureSettings()).settings.put((Object)"subs", (Object)value2);
        CSSFontFeatureSettings.POSITION_ALL_OFF.settings.put((Object)"sups", (Object)value2);
        (CSSFontFeatureSettings.CAPS_ALL_OFF = new CSSFontFeatureSettings()).settings.put((Object)"smcp", (Object)value2);
        CSSFontFeatureSettings.CAPS_ALL_OFF.settings.put((Object)"c2sc", (Object)value2);
        CSSFontFeatureSettings.CAPS_ALL_OFF.settings.put((Object)"pcap", (Object)value2);
        CSSFontFeatureSettings.CAPS_ALL_OFF.settings.put((Object)"c2pc", (Object)value2);
        CSSFontFeatureSettings.CAPS_ALL_OFF.settings.put((Object)"unic", (Object)value2);
        CSSFontFeatureSettings.CAPS_ALL_OFF.settings.put((Object)"titl", (Object)value2);
        (CSSFontFeatureSettings.NUMERIC_ALL_OFF = new CSSFontFeatureSettings()).settings.put((Object)"lnum", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"onum", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"pnum", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"tnum", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"frac", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"afrc", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"ordn", (Object)value2);
        CSSFontFeatureSettings.NUMERIC_ALL_OFF.settings.put((Object)"zero", (Object)value2);
        (CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF = new CSSFontFeatureSettings()).settings.put((Object)"jp78", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"jp83", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"jp90", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"jp04", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"smpl", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"trad", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"fwid", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"pwid", (Object)value2);
        CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF.settings.put((Object)"ruby", (Object)value2);
    }
    
    public CSSFontFeatureSettings() {
        this.settings = (HashMap<String, Integer>)new HashMap();
    }
    
    public CSSFontFeatureSettings(final CSSFontFeatureSettings cssFontFeatureSettings) {
        this.settings = (HashMap<String, Integer>)new HashMap((Map)cssFontFeatureSettings.settings);
    }
    
    private CSSFontFeatureSettings(final HashMap<String, Integer> settings) {
        this.settings = settings;
    }
    
    private void addSettings(final String s, final String s2, final int n) {
        this.settings.put((Object)s, (Object)n);
        this.settings.put((Object)s2, (Object)n);
    }
    
    private static int containsOnce(final List<String> list, final String s) {
        if (list.remove((Object)s)) {
            int n;
            if (list.contains((Object)s)) {
                n = 2;
            }
            else {
                n = 1;
            }
            return n;
        }
        return 0;
    }
    
    private static String containsOneOf(final List<String> list, final String... array) {
        final int length = array.length;
        String s = null;
        String s3;
        for (int i = 0; i < length; ++i, s = s3) {
            final String s2 = array[i];
            if ((s3 = s) == null) {
                s3 = s;
                if (list.remove((Object)s2)) {
                    s3 = s2;
                }
            }
            if (list.contains((Object)s2)) {
                return "ERR";
            }
        }
        return s;
    }
    
    private static int containsWhich(final List<String> list, final String s, final String s2) {
        final boolean remove = list.remove((Object)s);
        int n = 3;
        if (remove) {
            int n2 = n;
            if (!list.contains((Object)s)) {
                if (list.contains((Object)s2)) {
                    n2 = n;
                }
                else {
                    n2 = 1;
                }
            }
            return n2;
        }
        if (list.remove((Object)s2)) {
            if (!list.contains((Object)s2)) {
                n = 2;
            }
            return n;
        }
        return 0;
    }
    
    private static void ensureLigaturesNone() {
        if (CSSFontFeatureSettings.LIGATURES_ALL_OFF != null) {
            return;
        }
        final CSSFontFeatureSettings ligatures_ALL_OFF = new CSSFontFeatureSettings();
        ligatures_ALL_OFF.settings.put((Object)"liga", (Object)0);
        ligatures_ALL_OFF.settings.put((Object)"clig", (Object)0);
        ligatures_ALL_OFF.settings.put((Object)"dlig", (Object)0);
        ligatures_ALL_OFF.settings.put((Object)"hlig", (Object)0);
        ligatures_ALL_OFF.settings.put((Object)"calt", (Object)0);
        CSSFontFeatureSettings.LIGATURES_ALL_OFF = ligatures_ALL_OFF;
    }
    
    private void ensurePositionNormal() {
        if (CSSFontFeatureSettings.POSITION_ALL_OFF == null) {
            final CSSFontFeatureSettings position_ALL_OFF = new CSSFontFeatureSettings();
            position_ALL_OFF.settings.put((Object)"subs", (Object)0);
            position_ALL_OFF.settings.put((Object)"sups", (Object)0);
            CSSFontFeatureSettings.POSITION_ALL_OFF = position_ALL_OFF;
        }
    }
    
    private static List<String> extractTokensAsList(final String s) {
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        if (textScanner.empty()) {
            return null;
        }
        final ArrayList list = new ArrayList();
        while (!textScanner.empty()) {
            list.add((Object)textScanner.nextToken());
            textScanner.skipWhitespace();
        }
        return (List<String>)list;
    }
    
    private static final CSSFontFeatureSettings makeDefaultSettings() {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings();
        final HashMap<String, Integer> settings = cssFontFeatureSettings.settings;
        final Integer value = 1;
        settings.put((Object)"rlig", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"liga", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"clig", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"calt", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"locl", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"ccmp", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"mark", (Object)value);
        cssFontFeatureSettings.settings.put((Object)"mkmk", (Object)value);
        return cssFontFeatureSettings;
    }
    
    static CSSFontFeatureSettings makeSmallCaps() {
        if (CSSFontFeatureSettings.CAPS_SMALL_CAPS == null) {
            (CSSFontFeatureSettings.CAPS_SMALL_CAPS = new CSSFontFeatureSettings()).settings.put((Object)"smcp", (Object)1);
            CSSFontFeatureSettings.CAPS_SMALL_CAPS.settings.put((Object)"c2sc", (Object)0);
            CSSFontFeatureSettings.CAPS_SMALL_CAPS.settings.put((Object)"pcap", (Object)0);
            CSSFontFeatureSettings.CAPS_SMALL_CAPS.settings.put((Object)"c2pc", (Object)0);
            CSSFontFeatureSettings.CAPS_SMALL_CAPS.settings.put((Object)"unic", (Object)0);
            CSSFontFeatureSettings.CAPS_SMALL_CAPS.settings.put((Object)"titl", (Object)0);
        }
        return CSSFontFeatureSettings.CAPS_SMALL_CAPS;
    }
    
    private static FontFeatureEntry nextFeatureEntry(final TextScanner textScanner) {
        textScanner.skipWhitespace();
        final String nextQuotedString = textScanner.nextQuotedString();
        if (nextQuotedString != null && nextQuotedString.length() == 4) {
            textScanner.skipWhitespace();
            int intValue = 1;
            if (!textScanner.empty()) {
                final Integer nextInteger = textScanner.nextInteger(false);
                if (nextInteger == null) {
                    if (textScanner.consume("off")) {
                        intValue = 0;
                    }
                    else {
                        textScanner.consume("on");
                        intValue = intValue;
                    }
                }
                else {
                    intValue = nextInteger;
                }
            }
            return new FontFeatureEntry(nextQuotedString, intValue);
        }
        return null;
    }
    
    static CSSFontFeatureSettings parseEastAsian(final String s) {
        if (s.equals((Object)"normal")) {
            return CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF;
        }
        final List<String> tokensAsList = extractTokensAsList(s);
        if (tokensAsList == null) {
            return null;
        }
        final CSSFontFeatureSettings variantEastAsianSpecial = parseVariantEastAsianSpecial(tokensAsList);
        if (variantEastAsianSpecial != null && variantEastAsianSpecial != CSSFontFeatureSettings.ERROR && tokensAsList.size() <= 0) {
            return variantEastAsianSpecial;
        }
        return null;
    }
    
    static CSSFontFeatureSettings parseFontFeatureSettings(final String s) {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings();
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        while (!textScanner.empty()) {
            final FontFeatureEntry nextFeatureEntry = nextFeatureEntry(textScanner);
            if (nextFeatureEntry == null) {
                return null;
            }
            cssFontFeatureSettings.settings.put((Object)nextFeatureEntry.name, (Object)nextFeatureEntry.val);
            textScanner.skipCommaWhitespace();
        }
        return cssFontFeatureSettings;
    }
    
    static Style.FontKerning parseFontKerning(final String s) {
        final int hashCode = s.hashCode();
        int n = 0;
        Label_0073: {
            if (hashCode != -1039745817) {
                if (hashCode != 3005871) {
                    if (hashCode == 3387192) {
                        if (s.equals((Object)"none")) {
                            n = 2;
                            break Label_0073;
                        }
                    }
                }
                else if (s.equals((Object)"auto")) {
                    n = 0;
                    break Label_0073;
                }
            }
            else if (s.equals((Object)"normal")) {
                n = 1;
                break Label_0073;
            }
            n = -1;
        }
        if (n == 0) {
            return Style.FontKerning.auto;
        }
        if (n == 1) {
            return Style.FontKerning.normal;
        }
        if (n != 2) {
            return null;
        }
        return Style.FontKerning.none;
    }
    
    static void parseFontVariant(final Style style, final String s) {
        if (s.equals((Object)"normal")) {
            style.fontVariantLigatures = CSSFontFeatureSettings.LIGATURES_NORMAL;
            style.fontVariantPosition = CSSFontFeatureSettings.POSITION_ALL_OFF;
            style.fontVariantCaps = CSSFontFeatureSettings.CAPS_ALL_OFF;
            style.fontVariantNumeric = CSSFontFeatureSettings.NUMERIC_ALL_OFF;
            style.fontVariantEastAsian = CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF;
            style.specifiedFlags |= 0x1F0000000000L;
            return;
        }
        if (s.equals((Object)"none")) {
            ensureLigaturesNone();
            style.fontVariantLigatures = CSSFontFeatureSettings.LIGATURES_ALL_OFF;
            style.fontVariantPosition = CSSFontFeatureSettings.POSITION_ALL_OFF;
            style.fontVariantCaps = CSSFontFeatureSettings.CAPS_ALL_OFF;
            style.fontVariantNumeric = CSSFontFeatureSettings.NUMERIC_ALL_OFF;
            style.fontVariantEastAsian = CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF;
            style.specifiedFlags |= 0x1F0000000000L;
            return;
        }
        final List<String> tokensAsList = extractTokensAsList(s);
        if (tokensAsList == null) {
            return;
        }
        final CSSFontFeatureSettings variantLigaturesSpecial = parseVariantLigaturesSpecial(tokensAsList);
        if (variantLigaturesSpecial == CSSFontFeatureSettings.ERROR) {
            return;
        }
        final int size = tokensAsList.size();
        CSSFontFeatureSettings variantEastAsianSpecial = null;
        CSSFontFeatureSettings variantPositionSpecial;
        if (size > 0) {
            if ((variantPositionSpecial = parseVariantPositionSpecial(tokensAsList)) == CSSFontFeatureSettings.ERROR) {
                return;
            }
        }
        else {
            variantPositionSpecial = null;
        }
        CSSFontFeatureSettings variantCapsSpecial;
        if (tokensAsList.size() > 0) {
            if ((variantCapsSpecial = parseVariantCapsSpecial(tokensAsList)) == CSSFontFeatureSettings.ERROR) {
                return;
            }
        }
        else {
            variantCapsSpecial = null;
        }
        CSSFontFeatureSettings variantNumericSpecial;
        if (tokensAsList.size() > 0) {
            if ((variantNumericSpecial = parseVariantNumericSpecial(tokensAsList)) == CSSFontFeatureSettings.ERROR) {
                return;
            }
        }
        else {
            variantNumericSpecial = null;
        }
        if (tokensAsList.size() > 0 && (variantEastAsianSpecial = parseVariantEastAsianSpecial(tokensAsList)) == CSSFontFeatureSettings.ERROR) {
            return;
        }
        if (variantLigaturesSpecial != null) {
            style.fontVariantLigatures = variantLigaturesSpecial;
            style.specifiedFlags |= 0x10000000000L;
        }
        if (variantPositionSpecial != null) {
            style.fontVariantPosition = variantPositionSpecial;
            style.specifiedFlags |= 0x20000000000L;
        }
        if (variantCapsSpecial != null) {
            style.fontVariantCaps = variantCapsSpecial;
            style.specifiedFlags |= 0x40000000000L;
        }
        if (variantNumericSpecial != null) {
            style.fontVariantNumeric = variantNumericSpecial;
            style.specifiedFlags |= 0x80000000000L;
        }
        if (variantEastAsianSpecial != null) {
            style.fontVariantEastAsian = variantEastAsianSpecial;
            style.specifiedFlags |= 0x100000000000L;
        }
    }
    
    static CSSFontFeatureSettings parseVariantCaps(final String s) {
        if (s.equals((Object)"normal")) {
            return CSSFontFeatureSettings.CAPS_ALL_OFF;
        }
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.CAPS_ALL_OFF);
        CSSFontFeatureSettings cssFontFeatureSettings2;
        if (setCapsFeature(cssFontFeatureSettings, s)) {
            cssFontFeatureSettings2 = cssFontFeatureSettings;
        }
        else {
            cssFontFeatureSettings2 = null;
        }
        return cssFontFeatureSettings2;
    }
    
    private static CSSFontFeatureSettings parseVariantCapsSpecial(final List<String> list) {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.CAPS_ALL_OFF);
        final String containsOne = containsOneOf(list, "small-caps", "all-small-caps", "petite-caps", "all-petite-caps", "unicase", "titling-caps");
        if (containsOne == "ERR") {
            return CSSFontFeatureSettings.ERROR;
        }
        if (containsOne == null) {
            return null;
        }
        setCapsFeature(cssFontFeatureSettings, containsOne);
        return cssFontFeatureSettings;
    }
    
    private static CSSFontFeatureSettings parseVariantEastAsianSpecial(final List<String> list) {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.EAST_ASIAN_ALL_OFF);
        final String containsOne = containsOneOf(list, "jis78", "jis83", "jis90", "jis04", "simplified", "traditional");
        int n = 0;
        final int n2 = 0;
        final int n3 = 1;
        final Integer value = 1;
        if (containsOne != null) {
            int n4 = 0;
            Label_0251: {
                switch (containsOne.hashCode()) {
                    case 101133611: {
                        if (containsOne.equals((Object)"jis90")) {
                            n4 = 2;
                            break Label_0251;
                        }
                        break;
                    }
                    case 101133583: {
                        if (containsOne.equals((Object)"jis83")) {
                            n4 = 1;
                            break Label_0251;
                        }
                        break;
                    }
                    case 101133557: {
                        if (containsOne.equals((Object)"jis78")) {
                            n4 = n2;
                            break Label_0251;
                        }
                        break;
                    }
                    case 101133336: {
                        if (containsOne.equals((Object)"jis04")) {
                            n4 = 3;
                            break Label_0251;
                        }
                        break;
                    }
                    case 68933: {
                        if (containsOne.equals((Object)"ERR")) {
                            n4 = 6;
                            break Label_0251;
                        }
                        break;
                    }
                    case -1427350696: {
                        if (containsOne.equals((Object)"simplified")) {
                            n4 = 4;
                            break Label_0251;
                        }
                        break;
                    }
                    case -2137707097: {
                        if (containsOne.equals((Object)"traditional")) {
                            n4 = 5;
                            break Label_0251;
                        }
                        break;
                    }
                }
                n4 = -1;
            }
            switch (n4) {
                case 6: {
                    return CSSFontFeatureSettings.ERROR;
                }
                case 5: {
                    cssFontFeatureSettings.settings.put((Object)"trad", (Object)value);
                    break;
                }
                case 4: {
                    cssFontFeatureSettings.settings.put((Object)"smpl", (Object)value);
                    break;
                }
                case 3: {
                    cssFontFeatureSettings.settings.put((Object)"jp04", (Object)value);
                    break;
                }
                case 2: {
                    cssFontFeatureSettings.settings.put((Object)"jp90", (Object)value);
                    break;
                }
                case 1: {
                    cssFontFeatureSettings.settings.put((Object)"jp83", (Object)value);
                    break;
                }
                case 0: {
                    cssFontFeatureSettings.settings.put((Object)"jp78", (Object)value);
                    break;
                }
            }
            n = 1;
        }
        final int containsWhich = containsWhich(list, "full-width", "proportional-width");
        Label_0460: {
            if (containsWhich != 1) {
                if (containsWhich != 2) {
                    if (containsWhich != 3) {
                        break Label_0460;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"pwid", (Object)value);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"fwid", (Object)value);
            }
            n = 1;
        }
        final int containsOnce = containsOnce(list, "ruby");
        if (containsOnce != 1) {
            if (containsOnce == 2) {
                return CSSFontFeatureSettings.ERROR;
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"ruby", (Object)value);
            n = n3;
        }
        CSSFontFeatureSettings cssFontFeatureSettings2;
        if (n != 0) {
            cssFontFeatureSettings2 = cssFontFeatureSettings;
        }
        else {
            cssFontFeatureSettings2 = null;
        }
        return cssFontFeatureSettings2;
    }
    
    static CSSFontFeatureSettings parseVariantLigatures(final String s) {
        if (s.equals((Object)"normal")) {
            return CSSFontFeatureSettings.LIGATURES_NORMAL;
        }
        if (s.equals((Object)"none")) {
            ensureLigaturesNone();
            return CSSFontFeatureSettings.LIGATURES_ALL_OFF;
        }
        final List<String> tokensAsList = extractTokensAsList(s);
        if (tokensAsList == null) {
            return null;
        }
        ensureLigaturesNone();
        final CSSFontFeatureSettings variantLigaturesSpecial = parseVariantLigaturesSpecial(tokensAsList);
        if (variantLigaturesSpecial != null && variantLigaturesSpecial != CSSFontFeatureSettings.ERROR && tokensAsList.size() <= 0) {
            return variantLigaturesSpecial;
        }
        return null;
    }
    
    private static CSSFontFeatureSettings parseVariantLigaturesSpecial(final List<String> list) {
        ensureLigaturesNone();
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.LIGATURES_ALL_OFF);
        final int containsWhich = containsWhich(list, "common-ligatures", "no-common-ligatures");
        final boolean b = true;
        int n = 0;
        Label_0075: {
            if (containsWhich != 1) {
                if (containsWhich != 2) {
                    if (containsWhich != 3) {
                        n = 0;
                        break Label_0075;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.addSettings("clig", "liga", 0);
                }
            }
            else {
                cssFontFeatureSettings.addSettings("clig", "liga", 1);
            }
            n = 1;
        }
        final int containsWhich2 = containsWhich(list, "discretionary-ligatures", "no-discretionary-ligatures");
        Label_0141: {
            if (containsWhich2 != 1) {
                if (containsWhich2 != 2) {
                    if (containsWhich2 != 3) {
                        break Label_0141;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"dlig", (Object)0);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"dlig", (Object)1);
            }
            n = 1;
        }
        final int containsWhich3 = containsWhich(list, "historical-ligatures", "no-historical-ligatures");
        Label_0207: {
            if (containsWhich3 != 1) {
                if (containsWhich3 != 2) {
                    if (containsWhich3 != 3) {
                        break Label_0207;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"hlig", (Object)0);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"hlig", (Object)1);
            }
            n = 1;
        }
        final int containsWhich4 = containsWhich(list, "contextual", "no-contextual");
        if (containsWhich4 != 1) {
            if (containsWhich4 != 2) {
                if (containsWhich4 == 3) {
                    return CSSFontFeatureSettings.ERROR;
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"calt", (Object)0);
                n = (b ? 1 : 0);
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"calt", (Object)1);
            n = (b ? 1 : 0);
        }
        CSSFontFeatureSettings cssFontFeatureSettings2;
        if (n != 0) {
            cssFontFeatureSettings2 = cssFontFeatureSettings;
        }
        else {
            cssFontFeatureSettings2 = null;
        }
        return cssFontFeatureSettings2;
    }
    
    static CSSFontFeatureSettings parseVariantNumeric(final String s) {
        if (s.equals((Object)"normal")) {
            return CSSFontFeatureSettings.NUMERIC_ALL_OFF;
        }
        final List<String> tokensAsList = extractTokensAsList(s);
        if (tokensAsList == null) {
            return null;
        }
        final CSSFontFeatureSettings variantNumericSpecial = parseVariantNumericSpecial(tokensAsList);
        if (variantNumericSpecial != null && variantNumericSpecial != CSSFontFeatureSettings.ERROR && tokensAsList.size() <= 0) {
            return variantNumericSpecial;
        }
        return null;
    }
    
    private static CSSFontFeatureSettings parseVariantNumericSpecial(final List<String> list) {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.NUMERIC_ALL_OFF);
        final int containsWhich = containsWhich(list, "lining-nums", "oldstyle-nums");
        final int n = 1;
        int n2 = 0;
        Label_0082: {
            if (containsWhich != 1) {
                if (containsWhich != 2) {
                    if (containsWhich != 3) {
                        n2 = 0;
                        break Label_0082;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"onum", (Object)1);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"lnum", (Object)1);
            }
            n2 = 1;
        }
        final int containsWhich2 = containsWhich(list, "proportional-nums", "tabular-nums");
        Label_0148: {
            if (containsWhich2 != 1) {
                if (containsWhich2 != 2) {
                    if (containsWhich2 != 3) {
                        break Label_0148;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"tnum", (Object)1);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"pnum", (Object)1);
            }
            n2 = 1;
        }
        final int containsWhich3 = containsWhich(list, "diagonal-fractions", "stacked-fractions");
        Label_0214: {
            if (containsWhich3 != 1) {
                if (containsWhich3 != 2) {
                    if (containsWhich3 != 3) {
                        break Label_0214;
                    }
                    return CSSFontFeatureSettings.ERROR;
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"afrc", (Object)1);
                }
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"frac", (Object)1);
            }
            n2 = 1;
        }
        final int containsOnce = containsOnce(list, "ordinal");
        if (containsOnce != 1) {
            if (containsOnce == 2) {
                return CSSFontFeatureSettings.ERROR;
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"ordn", (Object)1);
            n2 = 1;
        }
        final int containsOnce2 = containsOnce(list, "slashed-zero");
        if (containsOnce2 != 1) {
            if (containsOnce2 == 2) {
                return CSSFontFeatureSettings.ERROR;
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"zero", (Object)1);
            n2 = n;
        }
        CSSFontFeatureSettings cssFontFeatureSettings2;
        if (n2 != 0) {
            cssFontFeatureSettings2 = cssFontFeatureSettings;
        }
        else {
            cssFontFeatureSettings2 = null;
        }
        return cssFontFeatureSettings2;
    }
    
    static CSSFontFeatureSettings parseVariantPosition(final String s) {
        if (s.equals((Object)"normal")) {
            return CSSFontFeatureSettings.POSITION_ALL_OFF;
        }
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.POSITION_ALL_OFF);
        int n = -1;
        final int hashCode = s.hashCode();
        if (hashCode != 114240) {
            if (hashCode == 109801339) {
                if (s.equals((Object)"super")) {
                    n = 1;
                }
            }
        }
        else if (s.equals((Object)"sub")) {
            n = 0;
        }
        if (n != 0) {
            if (n != 1) {
                return null;
            }
            cssFontFeatureSettings.settings.put((Object)"sups", (Object)1);
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"subs", (Object)1);
        }
        return cssFontFeatureSettings;
    }
    
    private static CSSFontFeatureSettings parseVariantPositionSpecial(final List<String> list) {
        final CSSFontFeatureSettings cssFontFeatureSettings = new CSSFontFeatureSettings(CSSFontFeatureSettings.POSITION_ALL_OFF);
        final int containsWhich = containsWhich(list, "sub", "super");
        boolean b = true;
        if (containsWhich != 1) {
            if (containsWhich != 2) {
                if (containsWhich == 3) {
                    return CSSFontFeatureSettings.ERROR;
                }
                b = false;
            }
            else {
                cssFontFeatureSettings.settings.put((Object)"sups", (Object)1);
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"subs", (Object)1);
        }
        CSSFontFeatureSettings cssFontFeatureSettings2;
        if (b) {
            cssFontFeatureSettings2 = cssFontFeatureSettings;
        }
        else {
            cssFontFeatureSettings2 = null;
        }
        return cssFontFeatureSettings2;
    }
    
    private static boolean setCapsFeature(final CSSFontFeatureSettings cssFontFeatureSettings, final String s) {
        final int hashCode = s.hashCode();
        final Integer value = 1;
        int n = 0;
        Label_0157: {
            switch (hashCode) {
                case 1183323111: {
                    if (s.equals((Object)"small-caps")) {
                        n = 0;
                        break Label_0157;
                    }
                    break;
                }
                case 1173329959: {
                    if (s.equals((Object)"all-petite-caps")) {
                        n = 3;
                        break Label_0157;
                    }
                    break;
                }
                case -155552173: {
                    if (s.equals((Object)"petite-caps")) {
                        n = 2;
                        break Label_0157;
                    }
                    break;
                }
                case -287029216: {
                    if (s.equals((Object)"unicase")) {
                        n = 4;
                        break Label_0157;
                    }
                    break;
                }
                case -436377709: {
                    if (s.equals((Object)"all-small-caps")) {
                        n = 1;
                        break Label_0157;
                    }
                    break;
                }
                case -718866279: {
                    if (s.equals((Object)"titling-caps")) {
                        n = 5;
                        break Label_0157;
                    }
                    break;
                }
            }
            n = -1;
        }
        if (n != 0) {
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n != 4) {
                            if (n != 5) {
                                return false;
                            }
                            cssFontFeatureSettings.settings.put((Object)"titl", (Object)value);
                        }
                        else {
                            cssFontFeatureSettings.settings.put((Object)"unic", (Object)value);
                        }
                    }
                    else {
                        cssFontFeatureSettings.addSettings("pcap", "c2pc", 1);
                    }
                }
                else {
                    cssFontFeatureSettings.settings.put((Object)"pcap", (Object)value);
                }
            }
            else {
                cssFontFeatureSettings.addSettings("smcp", "c2sc", 1);
            }
        }
        else {
            cssFontFeatureSettings.settings.put((Object)"smcp", (Object)value);
        }
        return true;
    }
    
    public void applyKerning(final Style.FontKerning fontKerning) {
        if (fontKerning == Style.FontKerning.none) {
            this.settings.put((Object)"kern", (Object)0);
        }
        else {
            this.settings.put((Object)"kern", (Object)1);
        }
    }
    
    public void applySettings(final CSSFontFeatureSettings cssFontFeatureSettings) {
        if (cssFontFeatureSettings == null) {
            return;
        }
        this.settings.putAll((Map)cssFontFeatureSettings.settings);
    }
    
    public boolean hasSettings() {
        return this.settings.size() > 0;
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
            sb.append(map$Entry.getValue());
        }
        return sb.toString();
    }
    
    private static class FontFeatureEntry
    {
        String name;
        int val;
        
        public FontFeatureEntry(final String name, final int val) {
            this.name = name;
            this.val = val;
        }
    }
}
