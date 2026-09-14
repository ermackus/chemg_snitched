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
import com.google.zxing.MultiFormatReader;

public class MultiFormatAnalyzer extends AreaRectAnalyzer
{
    MultiFormatReader mReader;
    
    public MultiFormatAnalyzer() {
        this((DecodeConfig)null);
    }
    
    public MultiFormatAnalyzer(final DecodeConfig decodeConfig) {
        super(decodeConfig);
        this.initReader();
    }
    
    public MultiFormatAnalyzer(final Map<DecodeHintType, Object> hints) {
        this(new DecodeConfig().setHints((Map)hints));
    }
    
    private Result decodeInternal(final LuminanceSource luminanceSource, final boolean b) {
        Result decodeWithState;
        try {
            decodeWithState = this.mReader.decodeWithState(new BinaryBitmap((Binarizer)new HybridBinarizer(luminanceSource)));
        }
        catch (final Exception ex) {
            decodeWithState = null;
        }
        Result decodeWithState2 = decodeWithState;
        if (!b || (decodeWithState2 = decodeWithState) != null) {
            return decodeWithState2;
        }
        try {
            decodeWithState2 = this.mReader.decodeWithState(new BinaryBitmap((Binarizer)new GlobalHistogramBinarizer(luminanceSource)));
            return decodeWithState2;
        }
        catch (final Exception ex2) {
            decodeWithState2 = decodeWithState;
            return decodeWithState2;
        }
    }
    
    private void initReader() {
        this.mReader = new MultiFormatReader();
    }
    
    @Override
    public Result analyze(byte[] decodeInternal, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        while (true) {
            long currentTimeMillis = 0L;
            try {
                try {
                    currentTimeMillis = System.currentTimeMillis();
                    this.mReader.setHints((Map)this.mHints);
                    final PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource((byte[])(Object)decodeInternal, n, n2, n3, n4, n5, n6, false);
                    Object decodeInternal2;
                    final Result result = (Result)(decodeInternal2 = this.decodeInternal((LuminanceSource)planarYUVLuminanceSource, this.isMultiDecode));
                    Label_0273: {
                        if (result == null) {
                            Object o = result;
                            decodeInternal2 = result;
                            try {
                                if (this.mDecodeConfig != null) {
                                    Object decodeInternal3;
                                    if ((decodeInternal3 = result) == null) {
                                        decodeInternal3 = result;
                                        o = result;
                                        if (this.mDecodeConfig.isSupportVerticalCode()) {
                                            o = result;
                                            decodeInternal3 = new byte[decodeInternal.length];
                                            for (int i = 0; i < n2; ++i) {
                                                for (int j = 0; j < n; ++j) {
                                                    decodeInternal3[j * n2 + n2 - i - 1] = decodeInternal[i * n + j];
                                                }
                                            }
                                            o = result;
                                            o = result;
                                            final PlanarYUVLuminanceSource planarYUVLuminanceSource2 = new PlanarYUVLuminanceSource((byte[])decodeInternal3, n2, n, n4, n3, n6, n5, false);
                                            o = result;
                                            decodeInternal3 = this.decodeInternal((LuminanceSource)planarYUVLuminanceSource2, this.mDecodeConfig.isSupportVerticalCodeMultiDecode());
                                        }
                                    }
                                    if ((decodeInternal2 = decodeInternal3) == null) {
                                        o = decodeInternal3;
                                        decodeInternal2 = decodeInternal3;
                                        if (this.mDecodeConfig.isSupportLuminanceInvert()) {
                                            o = decodeInternal3;
                                            decodeInternal = (Exception)this.decodeInternal(planarYUVLuminanceSource.invert(), this.mDecodeConfig.isSupportLuminanceInvertMultiDecode());
                                            break Label_0273;
                                        }
                                    }
                                }
                            }
                            catch (final Exception ex) {
                                final Object decodeInternal3 = o;
                                break Label_0350;
                            }
                        }
                        decodeInternal = (Exception)decodeInternal2;
                    }
                    Object decodeInternal3 = decodeInternal;
                    if (decodeInternal != null) {
                        final long n7 = System.currentTimeMillis();
                        final StringBuilder sb = new(java.lang.StringBuilder.class)();
                        final StringBuilder sb2;
                        decodeInternal3 = (sb2 = sb);
                        new StringBuilder();
                        final byte[] array = (byte[])decodeInternal3;
                        final String s = "Found barcode in ";
                        ((StringBuilder)(Object)array).append(s);
                        final byte[] array2 = (byte[])decodeInternal3;
                        final long n8 = n7;
                        final long n9 = currentTimeMillis;
                        final long n10 = n8 - n9;
                        ((StringBuilder)(Object)array2).append(n10);
                        final byte[] array3 = (byte[])decodeInternal3;
                        final String s2 = " ms";
                        ((StringBuilder)(Object)array3).append(s2);
                        final byte[] array4 = (byte[])decodeInternal3;
                        final String s3 = ((StringBuilder)(Object)array4).toString();
                        LogUtils.d(s3);
                        decodeInternal3 = decodeInternal;
                        break Label_0350;
                    }
                    break Label_0350;
                }
                finally {
                    final Exception ex2;
                    decodeInternal = ex2;
                    this.mReader.reset();
                }
            }
            catch (final Exception decodeInternal) {
                final Object decodeInternal3 = null;
            }
            try {
                final long n7 = System.currentTimeMillis();
                final StringBuilder sb = new(java.lang.StringBuilder.class)();
                final StringBuilder sb2;
                Object decodeInternal3 = sb2 = sb;
                new StringBuilder();
                final byte[] array = (byte[])decodeInternal3;
                final String s = "Found barcode in ";
                ((StringBuilder)(Object)array).append(s);
                final byte[] array2 = (byte[])decodeInternal3;
                final long n8 = n7;
                final long n9 = currentTimeMillis;
                final long n10 = n8 - n9;
                ((StringBuilder)(Object)array2).append(n10);
                final byte[] array3 = (byte[])decodeInternal3;
                final String s2 = " ms";
                ((StringBuilder)(Object)array3).append(s2);
                final byte[] array4 = (byte[])decodeInternal3;
                final String s3 = ((StringBuilder)(Object)array4).toString();
                LogUtils.d(s3);
                decodeInternal3 = decodeInternal;
                this.mReader.reset();
                return (Result)decodeInternal3;
            }
            catch (final Exception ex3) {
                final Object decodeInternal3 = decodeInternal;
                continue;
            }
            break;
        }
    }
}
