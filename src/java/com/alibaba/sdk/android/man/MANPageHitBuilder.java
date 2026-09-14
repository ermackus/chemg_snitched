package com.alibaba.sdk.android.man;

import com.alibaba.sdk.android.man.util.ToolKit;
import com.ut.mini.UTHitBuilders$UTHitBuilder;
import com.alibaba.sdk.android.man.util.UTWrapper;
import java.util.Map;
import com.ut.mini.UTHitBuilders$UTPageHitBuilder;

public class MANPageHitBuilder extends UTHitBuilders$UTPageHitBuilder
{
    public MANPageHitBuilder(final String s) {
        super(s);
    }
    
    public Map<String, String> build() {
        return (Map<String, String>)super.build();
    }
    
    public MANPageHitBuilder setDurationOnPage(final long durationOnPage) {
        super.setDurationOnPage(durationOnPage);
        UTWrapper.commitPageEvent("2");
        return this;
    }
    
    public MANPageHitBuilder setProperties(final Map<String, String> properties) {
        super.setProperties((Map)properties);
        UTWrapper.commitPageEvent("2");
        return this;
    }
    
    public MANPageHitBuilder setProperty(final String s, final String s2) {
        super.setProperty(s, s2);
        UTWrapper.commitPageEvent("2");
        return this;
    }
    
    public MANPageHitBuilder setReferPage(final String referPage) {
        if (!ToolKit.isNullOrEmpty(referPage)) {
            super.setReferPage(referPage);
            UTWrapper.commitPageEvent("2");
        }
        return this;
    }
}
