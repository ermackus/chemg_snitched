package com.king.zxing.analyze;

import com.king.zxing.util.LogUtils;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.Result;
import com.google.zxing.LuminanceSource;
import com.google.zxing.DecodeHintType;
import java.util.Map;
import com.king.zxing.DecodeConfig;
import com.google.zxing.Reader;

public abstract class BarcodeFormatAnalyzer extends AreaRectAnalyzer
{
    private Reader mReader;
    
    public BarcodeFormatAnalyzer(final DecodeConfig decodeConfig) {
        super(decodeConfig);
        this.initReader();
    }
    
    public BarcodeFormatAnalyzer(final Map<DecodeHintType, Object> hints) {
        this(new DecodeConfig().setHints((Map)hints));
    }
    
    private Result decodeInternal(final LuminanceSource luminanceSource, final boolean b) {
        Result decode;
        try {
            decode = this.mReader.decode(new BinaryBitmap((Binarizer)new HybridBinarizer(luminanceSource)), (Map)this.mHints);
        }
        catch (final Exception ex) {
            decode = null;
        }
        Result decode2 = decode;
        if (!b || (decode2 = decode) != null) {
            return decode2;
        }
        try {
            decode2 = this.mReader.decode(new BinaryBitmap((Binarizer)new GlobalHistogramBinarizer(luminanceSource)), (Map)this.mHints);
            return decode2;
        }
        catch (final Exception ex2) {
            decode2 = decode;
            return decode2;
        }
    }
    
    private void initReader() {
        this.mReader = this.createReader();
    }
    
    @Override
    public Result analyze(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        Label_0346: {
            if (this.mReader == null) {
                break Label_0346;
            }
            while (true) {
                long currentTimeMillis = 0L;
                Object o = null;
                Object o2;
                try {
                    try {
                        currentTimeMillis = System.currentTimeMillis();
                        final PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(array, n, n2, n3, n4, n5, n6, false);
                        Object decodeInternal;
                        final Result result = (Result)(decodeInternal = this.decodeInternal((LuminanceSource)planarYUVLuminanceSource, this.isMultiDecode));
                        Label_0261: {
                            if (result == null) {
                                decodeInternal = result;
                                try {
                                    if (this.mDecodeConfig != null) {
                                        if ((o = result) == null) {
                                            o = result;
                                            if (this.mDecodeConfig.isSupportVerticalCode()) {
                                                o = new byte[array.length];
                                                for (int i = 0; i < n2; ++i) {
                                                    for (int j = 0; j < n; ++j) {
                                                        o[j * n2 + n2 - i - 1] = array[i * n + j];
                                                    }
                                                }
                                                o = this.decodeInternal((LuminanceSource)new PlanarYUVLuminanceSource((byte[])o, n2, n, n4, n3, n6, n5, false), this.mDecodeConfig.isSupportVerticalCodeMultiDecode());
                                            }
                                        }
                                        decodeInternal = o;
                                        if (this.mDecodeConfig.isSupportLuminanceInvert()) {
                                            o = this.decodeInternal(planarYUVLuminanceSource.invert(), this.mDecodeConfig.isSupportLuminanceInvertMultiDecode());
                                            break Label_0261;
                                        }
                                    }
                                }
                                catch (final Exception ex) {
                                    break Label_0334;
                                }
                            }
                            o = decodeInternal;
                        }
                        if (o != null) {
                            final long n7 = System.currentTimeMillis();
                            final StringBuilder sb = new(java.lang.StringBuilder.class)();
                            final StringBuilder sb3;
                            final StringBuilder sb2 = sb3 = sb;
                            new StringBuilder();
                            final StringBuilder sb4 = sb2;
                            final String s = "Found barcode in ";
                            sb4.append(s);
                            final StringBuilder sb5 = sb2;
                            final long n8 = n7;
                            final long n9 = currentTimeMillis;
                            final long n10 = n8 - n9;
                            sb5.append(n10);
                            final StringBuilder sb6 = sb2;
                            final String s2 = " ms";
                            sb6.append(s2);
                            final StringBuilder sb7 = sb2;
                            final String s3 = sb7.toString();
                            LogUtils.d(s3);
                            break Label_0334;
                        }
                        break Label_0334;
                    }
                    finally {
                        final Throwable t;
                        o2 = t;
                        this.mReader.reset();
                    }
                }
                catch (final Exception ex2) {
                    o2 = null;
                }
                try {
                    final long n7 = System.currentTimeMillis();
                    final StringBuilder sb = new(java.lang.StringBuilder.class)();
                    final StringBuilder sb3;
                    final StringBuilder sb2 = sb3 = sb;
                    new StringBuilder();
                    final StringBuilder sb4 = sb2;
                    final String s = "Found barcode in ";
                    sb4.append(s);
                    final StringBuilder sb5 = sb2;
                    final long n8 = n7;
                    final long n9 = currentTimeMillis;
                    final long n10 = n8 - n9;
                    sb5.append(n10);
                    final StringBuilder sb6 = sb2;
                    final String s2 = " ms";
                    sb6.append(s2);
                    final StringBuilder sb7 = sb2;
                    final String s3 = sb7.toString();
                    LogUtils.d(s3);
                    this.mReader.reset();
                    return (Result)o2;
                    o2 = null;
                    return (Result)o2;
                }
                catch (final Exception ex3) {
                    o2 = o;
                    continue;
                }
                break;
            }
        }
    }
    
    public abstract Reader createReader();
}
