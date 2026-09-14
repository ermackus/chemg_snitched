package com.kingagroot.kingdraw.model;

public class LimitServiceModel
{
    public static final String TAG = "LimitServiceModel";
    private LimitHintModel hints;
    private String settings;
    private String url;
    
    public LimitHintModel getHints() {
        return this.hints;
    }
    
    public String getSettings() {
        return this.settings;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setHints(final LimitHintModel hints) {
        this.hints = hints;
    }
    
    public void setSettings(final String settings) {
        this.settings = settings;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
}
