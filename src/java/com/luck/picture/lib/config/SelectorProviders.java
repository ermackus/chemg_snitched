package com.luck.picture.lib.config;

import java.util.LinkedList;

public class SelectorProviders
{
    private static volatile SelectorProviders selectorProviders;
    private final LinkedList<SelectorConfig> selectionConfigsQueue;
    
    public SelectorProviders() {
        this.selectionConfigsQueue = (LinkedList<SelectorConfig>)new LinkedList();
    }
    
    public static SelectorProviders getInstance() {
        if (SelectorProviders.selectorProviders == null) {
            synchronized (SelectorProviders.class) {
                if (SelectorProviders.selectorProviders == null) {
                    SelectorProviders.selectorProviders = new SelectorProviders();
                }
            }
        }
        return SelectorProviders.selectorProviders;
    }
    
    public void addSelectorConfigQueue(final SelectorConfig selectorConfig) {
        this.selectionConfigsQueue.add((Object)selectorConfig);
    }
    
    public void destroy() {
        final SelectorConfig selectorConfig = this.getSelectorConfig();
        if (selectorConfig != null) {
            selectorConfig.destroy();
            this.selectionConfigsQueue.remove((Object)selectorConfig);
        }
    }
    
    public SelectorConfig getSelectorConfig() {
        SelectorConfig selectorConfig;
        if (this.selectionConfigsQueue.size() > 0) {
            selectorConfig = (SelectorConfig)this.selectionConfigsQueue.getLast();
        }
        else {
            selectorConfig = new SelectorConfig();
        }
        return selectorConfig;
    }
    
    public void reset() {
        for (int i = 0; i < this.selectionConfigsQueue.size(); ++i) {
            final SelectorConfig selectorConfig = (SelectorConfig)this.selectionConfigsQueue.get(i);
            if (selectorConfig != null) {
                selectorConfig.destroy();
            }
        }
        this.selectionConfigsQueue.clear();
    }
}
