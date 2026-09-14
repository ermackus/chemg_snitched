package com.king.zxing;

import com.google.zxing.DecodeHintType;
import java.util.Map;
import android.graphics.Rect;

public class DecodeConfig
{
    public static final float DEFAULT_AREA_RECT_RATIO = 0.8f;
    private Rect analyzeAreaRect;
    private int areaRectHorizontalOffset;
    private float areaRectRatio;
    private int areaRectVerticalOffset;
    private Map<DecodeHintType, Object> hints;
    private boolean isFullAreaScan;
    private boolean isMultiDecode;
    private boolean isSupportLuminanceInvert;
    private boolean isSupportLuminanceInvertMultiDecode;
    private boolean isSupportVerticalCode;
    private boolean isSupportVerticalCodeMultiDecode;
    
    public DecodeConfig() {
        this.hints = DecodeFormatManager.DEFAULT_HINTS;
        this.isMultiDecode = true;
        this.isFullAreaScan = false;
        this.areaRectRatio = 0.8f;
    }
    
    public Rect getAnalyzeAreaRect() {
        return this.analyzeAreaRect;
    }
    
    public int getAreaRectHorizontalOffset() {
        return this.areaRectHorizontalOffset;
    }
    
    public float getAreaRectRatio() {
        return this.areaRectRatio;
    }
    
    public int getAreaRectVerticalOffset() {
        return this.areaRectVerticalOffset;
    }
    
    public Map<DecodeHintType, Object> getHints() {
        return this.hints;
    }
    
    public boolean isFullAreaScan() {
        return this.isFullAreaScan;
    }
    
    public boolean isMultiDecode() {
        return this.isMultiDecode;
    }
    
    public boolean isSupportLuminanceInvert() {
        return this.isSupportLuminanceInvert;
    }
    
    public boolean isSupportLuminanceInvertMultiDecode() {
        return this.isSupportLuminanceInvertMultiDecode;
    }
    
    public boolean isSupportVerticalCode() {
        return this.isSupportVerticalCode;
    }
    
    public boolean isSupportVerticalCodeMultiDecode() {
        return this.isSupportVerticalCodeMultiDecode;
    }
    
    public DecodeConfig setAnalyzeAreaRect(final Rect analyzeAreaRect) {
        this.analyzeAreaRect = analyzeAreaRect;
        return this;
    }
    
    public DecodeConfig setAreaRectHorizontalOffset(final int areaRectHorizontalOffset) {
        this.areaRectHorizontalOffset = areaRectHorizontalOffset;
        return this;
    }
    
    public DecodeConfig setAreaRectRatio(final float areaRectRatio) {
        this.areaRectRatio = areaRectRatio;
        return this;
    }
    
    public DecodeConfig setAreaRectVerticalOffset(final int areaRectVerticalOffset) {
        this.areaRectVerticalOffset = areaRectVerticalOffset;
        return this;
    }
    
    public DecodeConfig setFullAreaScan(final boolean isFullAreaScan) {
        this.isFullAreaScan = isFullAreaScan;
        return this;
    }
    
    public DecodeConfig setHints(final Map<DecodeHintType, Object> hints) {
        this.hints = hints;
        return this;
    }
    
    public DecodeConfig setMultiDecode(final boolean isMultiDecode) {
        this.isMultiDecode = isMultiDecode;
        return this;
    }
    
    public DecodeConfig setSupportLuminanceInvert(final boolean isSupportLuminanceInvert) {
        this.isSupportLuminanceInvert = isSupportLuminanceInvert;
        return this;
    }
    
    public DecodeConfig setSupportLuminanceInvertMultiDecode(final boolean isSupportLuminanceInvertMultiDecode) {
        this.isSupportLuminanceInvertMultiDecode = isSupportLuminanceInvertMultiDecode;
        return this;
    }
    
    public DecodeConfig setSupportVerticalCode(final boolean isSupportVerticalCode) {
        this.isSupportVerticalCode = isSupportVerticalCode;
        return this;
    }
    
    public DecodeConfig setSupportVerticalCodeMultiDecode(final boolean isSupportVerticalCodeMultiDecode) {
        this.isSupportVerticalCodeMultiDecode = isSupportVerticalCodeMultiDecode;
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DecodeConfig{hints=");
        sb.append((Object)this.hints);
        sb.append(", isMultiDecode=");
        sb.append(this.isMultiDecode);
        sb.append(", isSupportLuminanceInvert=");
        sb.append(this.isSupportLuminanceInvert);
        sb.append(", isSupportLuminanceInvertMultiDecode=");
        sb.append(this.isSupportLuminanceInvertMultiDecode);
        sb.append(", isSupportVerticalCode=");
        sb.append(this.isSupportVerticalCode);
        sb.append(", isSupportVerticalCodeMultiDecode=");
        sb.append(this.isSupportVerticalCodeMultiDecode);
        sb.append(", analyzeAreaRect=");
        sb.append((Object)this.analyzeAreaRect);
        sb.append(", isFullAreaScan=");
        sb.append(this.isFullAreaScan);
        sb.append(", areaRectRatio=");
        sb.append(this.areaRectRatio);
        sb.append(", areaRectVerticalOffset=");
        sb.append(this.areaRectVerticalOffset);
        sb.append(", areaRectHorizontalOffset=");
        sb.append(this.areaRectHorizontalOffset);
        sb.append('}');
        return sb.toString();
    }
}
