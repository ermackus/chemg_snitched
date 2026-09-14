package com.king.zxing;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.zxing.BarcodeFormat;
import java.util.EnumMap;
import com.google.zxing.DecodeHintType;
import java.util.Map;

public final class DecodeFormatManager
{
    public static final Map<DecodeHintType, Object> ALL_HINTS;
    public static final Map<DecodeHintType, Object> CODE_128_HINTS;
    public static final Map<DecodeHintType, Object> DEFAULT_HINTS;
    public static final Map<DecodeHintType, Object> ONE_DIMENSIONAL_HINTS;
    public static final Map<DecodeHintType, Object> QR_CODE_HINTS;
    public static final Map<DecodeHintType, Object> TWO_DIMENSIONAL_HINTS;
    
    static {
        ALL_HINTS = (Map)new EnumMap((Class)DecodeHintType.class);
        CODE_128_HINTS = createDecodeHint(BarcodeFormat.CODE_128);
        QR_CODE_HINTS = createDecodeHint(BarcodeFormat.QR_CODE);
        ONE_DIMENSIONAL_HINTS = (Map)new EnumMap((Class)DecodeHintType.class);
        TWO_DIMENSIONAL_HINTS = (Map)new EnumMap((Class)DecodeHintType.class);
        DEFAULT_HINTS = (Map)new EnumMap((Class)DecodeHintType.class);
        addDecodeHintTypes(DecodeFormatManager.ALL_HINTS, getAllFormats());
        addDecodeHintTypes(DecodeFormatManager.ONE_DIMENSIONAL_HINTS, getOneDimensionalFormats());
        addDecodeHintTypes(DecodeFormatManager.TWO_DIMENSIONAL_HINTS, getTwoDimensionalFormats());
        addDecodeHintTypes(DecodeFormatManager.DEFAULT_HINTS, getDefaultFormats());
    }
    
    private static void addDecodeHintTypes(final Map<DecodeHintType, Object> map, final List<BarcodeFormat> list) {
        map.put((Object)DecodeHintType.POSSIBLE_FORMATS, (Object)list);
        map.put((Object)DecodeHintType.TRY_HARDER, (Object)Boolean.TRUE);
        map.put((Object)DecodeHintType.CHARACTER_SET, (Object)"UTF-8");
    }
    
    public static Map<DecodeHintType, Object> createDecodeHint(final BarcodeFormat barcodeFormat) {
        final EnumMap enumMap = new EnumMap((Class)DecodeHintType.class);
        addDecodeHintTypes((Map<DecodeHintType, Object>)enumMap, singletonList(barcodeFormat));
        return (Map<DecodeHintType, Object>)enumMap;
    }
    
    public static Map<DecodeHintType, Object> createDecodeHints(final BarcodeFormat... array) {
        final EnumMap enumMap = new EnumMap((Class)DecodeHintType.class);
        addDecodeHintTypes((Map<DecodeHintType, Object>)enumMap, (List<BarcodeFormat>)Arrays.asList((Object[])array));
        return (Map<DecodeHintType, Object>)enumMap;
    }
    
    private static List<BarcodeFormat> getAllFormats() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)BarcodeFormat.AZTEC);
        ((List)list).add((Object)BarcodeFormat.CODABAR);
        ((List)list).add((Object)BarcodeFormat.CODE_39);
        ((List)list).add((Object)BarcodeFormat.CODE_93);
        ((List)list).add((Object)BarcodeFormat.CODE_128);
        ((List)list).add((Object)BarcodeFormat.DATA_MATRIX);
        ((List)list).add((Object)BarcodeFormat.EAN_8);
        ((List)list).add((Object)BarcodeFormat.EAN_13);
        ((List)list).add((Object)BarcodeFormat.ITF);
        ((List)list).add((Object)BarcodeFormat.MAXICODE);
        ((List)list).add((Object)BarcodeFormat.PDF_417);
        ((List)list).add((Object)BarcodeFormat.QR_CODE);
        ((List)list).add((Object)BarcodeFormat.RSS_14);
        ((List)list).add((Object)BarcodeFormat.RSS_EXPANDED);
        ((List)list).add((Object)BarcodeFormat.UPC_A);
        ((List)list).add((Object)BarcodeFormat.UPC_E);
        ((List)list).add((Object)BarcodeFormat.UPC_EAN_EXTENSION);
        return (List<BarcodeFormat>)list;
    }
    
    private static List<BarcodeFormat> getDefaultFormats() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)BarcodeFormat.QR_CODE);
        ((List)list).add((Object)BarcodeFormat.UPC_A);
        ((List)list).add((Object)BarcodeFormat.EAN_13);
        ((List)list).add((Object)BarcodeFormat.CODE_128);
        return (List<BarcodeFormat>)list;
    }
    
    private static List<BarcodeFormat> getOneDimensionalFormats() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)BarcodeFormat.CODABAR);
        ((List)list).add((Object)BarcodeFormat.CODE_39);
        ((List)list).add((Object)BarcodeFormat.CODE_93);
        ((List)list).add((Object)BarcodeFormat.CODE_128);
        ((List)list).add((Object)BarcodeFormat.EAN_8);
        ((List)list).add((Object)BarcodeFormat.EAN_13);
        ((List)list).add((Object)BarcodeFormat.ITF);
        ((List)list).add((Object)BarcodeFormat.RSS_14);
        ((List)list).add((Object)BarcodeFormat.RSS_EXPANDED);
        ((List)list).add((Object)BarcodeFormat.UPC_A);
        ((List)list).add((Object)BarcodeFormat.UPC_E);
        ((List)list).add((Object)BarcodeFormat.UPC_EAN_EXTENSION);
        return (List<BarcodeFormat>)list;
    }
    
    private static List<BarcodeFormat> getTwoDimensionalFormats() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)BarcodeFormat.AZTEC);
        ((List)list).add((Object)BarcodeFormat.DATA_MATRIX);
        ((List)list).add((Object)BarcodeFormat.MAXICODE);
        ((List)list).add((Object)BarcodeFormat.PDF_417);
        ((List)list).add((Object)BarcodeFormat.QR_CODE);
        return (List<BarcodeFormat>)list;
    }
    
    private static <T> List<T> singletonList(final T t) {
        return (List<T>)Collections.singletonList((Object)t);
    }
}
