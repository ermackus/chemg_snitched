package com.kingagroot.kingdraw.core.data;

class DataParam
{
    private boolean content;
    private boolean formula;
    private String jpgPath;
    private boolean json;
    private String pngPath;
    private boolean smiles;
    private String thumbnailPath;
    private boolean type;
    
    DataParam() {
        this.json = false;
        this.smiles = false;
        this.formula = false;
        this.content = false;
        this.type = false;
        this.thumbnailPath = "";
        this.pngPath = "";
        this.jpgPath = "";
    }
    
    public String getJpgPath() {
        return this.jpgPath;
    }
    
    public String getPngPath() {
        return this.pngPath;
    }
    
    public String getThumbnailPath() {
        return this.thumbnailPath;
    }
    
    public boolean isContent() {
        return this.content;
    }
    
    public boolean isFormula() {
        return this.formula;
    }
    
    public boolean isJson() {
        return this.json;
    }
    
    public boolean isSmiles() {
        return this.smiles;
    }
    
    public boolean isType() {
        return this.type;
    }
    
    public void setContent(final boolean content) {
        this.content = content;
    }
    
    public void setFormula(final boolean formula) {
        this.formula = formula;
    }
    
    public void setJpgPath(final String jpgPath) {
        this.jpgPath = jpgPath;
    }
    
    public void setJson(final boolean json) {
        this.json = json;
    }
    
    public void setPngPath(final String pngPath) {
        this.pngPath = pngPath;
    }
    
    public void setSmiles(final boolean smiles) {
        this.smiles = smiles;
    }
    
    public void setThumbnailPath(final String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
    
    public void setType(final boolean type) {
        this.type = type;
    }
}
