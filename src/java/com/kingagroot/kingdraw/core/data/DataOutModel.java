package com.kingagroot.kingdraw.core.data;

import org.json.JSONException;
import org.json.JSONObject;

public class DataOutModel
{
    int code;
    public String content;
    String errorInfo;
    public String formula;
    public String josn;
    public String jpgPath;
    public String pngPath;
    public String smiles;
    public String thumbPath;
    public int type;
    
    public DataOutModel() {
        this.code = 1;
        this.josn = "";
        this.smiles = "";
        this.content = "";
        this.formula = "";
        this.type = -1;
        this.thumbPath = "";
        this.pngPath = "";
        this.jpgPath = "";
    }
    
    public boolean isSuccess() {
        return this.code == 0;
    }
    
    public void parse(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("code")) {
                this.code = jsonObject.getInt("code");
            }
            if (jsonObject.has("errorInfo")) {
                this.errorInfo = jsonObject.getString("errorInfo");
            }
            if (jsonObject.has("json")) {
                this.josn = jsonObject.getString("json");
            }
            if (jsonObject.has("content")) {
                this.content = jsonObject.getString("content");
            }
            if (jsonObject.has("formula")) {
                this.formula = jsonObject.getString("formula");
            }
            if (jsonObject.has("type")) {
                this.type = jsonObject.getInt("type");
            }
            if (jsonObject.has("smiles")) {
                this.smiles = jsonObject.getString("smiles");
            }
            if (jsonObject.has("thumbnail")) {
                this.thumbPath = jsonObject.getString("thumbnail");
            }
            if (jsonObject.has("png")) {
                this.pngPath = jsonObject.getString("png");
            }
            if (jsonObject.has("jpg")) {
                this.jpgPath = jsonObject.getString("jpg");
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
    }
}
