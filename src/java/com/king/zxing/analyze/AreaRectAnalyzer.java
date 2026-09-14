package com.king.zxing.analyze;

import android.graphics.Rect;
import com.google.zxing.Result;
import com.king.zxing.DecodeFormatManager;
import com.google.zxing.DecodeHintType;
import java.util.Map;
import com.king.zxing.DecodeConfig;

public abstract class AreaRectAnalyzer extends ImageAnalyzer
{
    boolean isMultiDecode;
    private int mAreaRectHorizontalOffset;
    private float mAreaRectRatio;
    private int mAreaRectVerticalOffset;
    DecodeConfig mDecodeConfig;
    Map<DecodeHintType, ?> mHints;
    
    public AreaRectAnalyzer(final DecodeConfig mDecodeConfig) {
        this.isMultiDecode = true;
        this.mAreaRectRatio = 0.8f;
        this.mAreaRectHorizontalOffset = 0;
        this.mAreaRectVerticalOffset = 0;
        this.mDecodeConfig = mDecodeConfig;
        if (mDecodeConfig != null) {
            this.mHints = (Map<DecodeHintType, ?>)mDecodeConfig.getHints();
            this.isMultiDecode = mDecodeConfig.isMultiDecode();
            this.mAreaRectRatio = mDecodeConfig.getAreaRectRatio();
            this.mAreaRectHorizontalOffset = mDecodeConfig.getAreaRectHorizontalOffset();
            this.mAreaRectVerticalOffset = mDecodeConfig.getAreaRectVerticalOffset();
        }
        else {
            this.mHints = (Map<DecodeHintType, ?>)DecodeFormatManager.DEFAULT_HINTS;
        }
    }
    
    public Result analyze(final byte[] array, final int n, final int n2) {
        final DecodeConfig mDecodeConfig = this.mDecodeConfig;
        if (mDecodeConfig != null) {
            if (mDecodeConfig.isFullAreaScan()) {
                return this.analyze(array, n, n2, 0, 0, n, n2);
            }
            final Rect analyzeAreaRect = this.mDecodeConfig.getAnalyzeAreaRect();
            if (analyzeAreaRect != null) {
                return this.analyze(array, n, n2, analyzeAreaRect.left, analyzeAreaRect.top, analyzeAreaRect.width(), analyzeAreaRect.height());
            }
        }
        final int n3 = (int)(Math.min(n, n2) * this.mAreaRectRatio);
        return this.analyze(array, n, n2, (n - n3) / 2 + this.mAreaRectHorizontalOffset, (n2 - n3) / 2 + this.mAreaRectVerticalOffset, n3, n3);
    }
    
    public abstract Result analyze(final byte[] p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
}
