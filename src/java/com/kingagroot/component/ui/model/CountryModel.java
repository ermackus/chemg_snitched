package com.kingagroot.component.ui.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "CountryModel")
public class CountryModel implements Serializable
{
    @Column(name = "countryCode")
    private String countryCode;
    @Column(isId = true, name = "id")
    private int id;
    @Column(name = "key")
    private String key;
    @Column(name = "lang")
    private String lang;
    @Column(name = "pinYinFirstCode")
    private String pinYinFirstCode;
    @Column(name = "rootKey")
    private String rootKey;
    @Column(name = "value")
    private String value;
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getLang() {
        return this.lang;
    }
    
    public String getPinYinFirstCode() {
        return this.pinYinFirstCode;
    }
    
    public String getRootKey() {
        return this.rootKey;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setLang(final String lang) {
        this.lang = lang;
    }
    
    public void setPinYinFirstCode(final String pinYinFirstCode) {
        this.pinYinFirstCode = pinYinFirstCode;
    }
    
    public void setRootKey(final String rootKey) {
        this.rootKey = rootKey;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
}
