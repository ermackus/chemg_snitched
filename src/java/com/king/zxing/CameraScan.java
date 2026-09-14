package com.king.zxing;

import com.king.zxing.config.CameraConfig;
import com.king.zxing.analyze.Analyzer;
import android.view.View;
import android.content.Intent;

public abstract class CameraScan implements ICamera, ICameraControl
{
    public static int LENS_FACING_BACK = 1;
    public static int LENS_FACING_FRONT = 0;
    public static String SCAN_RESULT = "SCAN_RESULT";
    private boolean isNeedAutoZoom;
    private boolean isNeedTouchZoom;
    
    public CameraScan() {
        this.isNeedAutoZoom = false;
        this.isNeedTouchZoom = true;
    }
    
    public static String parseScanResult(final Intent intent) {
        if (intent != null) {
            return intent.getStringExtra(CameraScan.SCAN_RESULT);
        }
        return null;
    }
    
    public abstract CameraScan bindFlashlightView(final View p0);
    
    protected boolean isNeedAutoZoom() {
        return this.isNeedAutoZoom;
    }
    
    protected boolean isNeedTouchZoom() {
        return this.isNeedTouchZoom;
    }
    
    public abstract CameraScan setAnalyzeImage(final boolean p0);
    
    public abstract CameraScan setAnalyzer(final Analyzer p0);
    
    public abstract CameraScan setBrightLightLux(final float p0);
    
    public abstract CameraScan setCameraConfig(final CameraConfig p0);
    
    public abstract CameraScan setDarkLightLux(final float p0);
    
    public CameraScan setNeedAutoZoom(final boolean isNeedAutoZoom) {
        this.isNeedAutoZoom = isNeedAutoZoom;
        return this;
    }
    
    public CameraScan setNeedTouchZoom(final boolean isNeedTouchZoom) {
        this.isNeedTouchZoom = isNeedTouchZoom;
        return this;
    }
    
    public abstract CameraScan setOnScanResultCallback(final CameraScan.CameraScan$OnScanResultCallback p0);
    
    public abstract CameraScan setPlayBeep(final boolean p0);
    
    public abstract CameraScan setVibrate(final boolean p0);
}
