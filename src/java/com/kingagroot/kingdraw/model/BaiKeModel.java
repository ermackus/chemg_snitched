package com.kingagroot.kingdraw.model;

public class BaiKeModel
{
    private int code;
    private String content;
    private String formula;
    private String json;
    private String smiles;
    
    public int getCode() {
        return this.code;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public String getFormula() {
        return this.formula;
    }
    
    public String getJson() {
        return this.json;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public void setCode(final int code) {
        this.code = code;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public void setFormula(final String formula) {
        this.formula = formula;
    }
    
    public void setJson(final String json) {
        this.json = json;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
}
