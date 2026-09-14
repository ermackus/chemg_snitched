package com.kingagroot.kingdraw.ui.account.model;

public class OccupationModel
{
    private String apply_key;
    private int id;
    private String lang;
    private String sort_id;
    private String values;
    
    public String getApply_key() {
        return this.apply_key;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getLang() {
        return this.lang;
    }
    
    public String getSort_id() {
        return this.sort_id;
    }
    
    public String getValues() {
        return this.values;
    }
    
    public void setApply_key(final String apply_key) {
        this.apply_key = apply_key;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setLang(final String lang) {
        this.lang = lang;
    }
    
    public void setSort_id(final String sort_id) {
        this.sort_id = sort_id;
    }
    
    public void setValues(final String values) {
        this.values = values;
    }
}
