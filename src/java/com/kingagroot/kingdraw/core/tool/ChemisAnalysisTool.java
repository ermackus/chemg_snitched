package com.kingagroot.kingdraw.core.tool;

import android.text.TextUtils;

public class ChemisAnalysisTool
{
    private String paletteId;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public ChemisAnalysisTool(final String paletteId) {
        this.paletteId = paletteId;
    }
    
    private static native String chemisAnalysisWithAllElements(final String p0);
    
    private static native String chemisAnalysisWithSelectElements(final String p0);
    
    public String chemisAnalysisWithAllElements() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return chemisAnalysisWithAllElements(this.paletteId);
        }
        return "";
    }
    
    public String chemisAnalysisWithSelectElements() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return chemisAnalysisWithSelectElements(this.paletteId);
        }
        return "";
    }
}
