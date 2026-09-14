package com.kingagroot.kingdraw.model;

import java.io.Serializable;

public class ConfigModel implements Serializable
{
    private String ConfigType;
    private String Id;
    private String Key;
    private String Value;
    
    public String getConfigType() {
        return this.ConfigType;
    }
    
    public String getId() {
        return this.Id;
    }
    
    public String getKey() {
        return this.Key;
    }
    
    public String getValue() {
        return this.Value;
    }
    
    public void setConfigType(final String configType) {
        this.ConfigType = configType;
    }
    
    public void setId(final String id) {
        this.Id = id;
    }
    
    public void setKey(final String key) {
        this.Key = key;
    }
    
    public void setValue(final String value) {
        this.Value = value;
    }
}
