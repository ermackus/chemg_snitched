package com.kingagroot.kingdraw.core.tool;

import android.text.TextUtils;

public class ClipboardData
{
    private static ClipboardData clipboardData;
    private String jsonData;
    
    private ClipboardData() {
    }
    
    public static ClipboardData getInstance() {
        if (ClipboardData.clipboardData == null) {
            ClipboardData.clipboardData = new ClipboardData();
        }
        return ClipboardData.clipboardData;
    }
    
    public void clearClipData() {
        this.jsonData = "";
    }
    
    public String getClipboardData() {
        return this.jsonData;
    }
    
    public boolean hasClipData() {
        return !TextUtils.isEmpty((CharSequence)this.jsonData);
    }
    
    void setClipboardData(final String jsonData) {
        this.jsonData = jsonData;
    }
}
