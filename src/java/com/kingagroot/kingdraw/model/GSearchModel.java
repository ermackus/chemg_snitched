package com.kingagroot.kingdraw.model;

import android.text.TextUtils;
import java.util.List;
import java.io.Serializable;

public class GSearchModel implements Serializable, Cloneable
{
    String formola;
    String key;
    String smiles;
    String structImagePath;
    List<String> tags;
    
    public GSearchModel clone() {
        try {
            final GSearchModel gSearchModel = (GSearchModel)super.clone();
            gSearchModel.setTags(this.tags);
            return gSearchModel;
        }
        catch (final CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getFormola() {
        return this.formola;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getSmiles() {
        return this.smiles;
    }
    
    public String getStructImagePath() {
        return this.structImagePath;
    }
    
    public List<String> getTags() {
        return this.tags;
    }
    
    public boolean isEmpty() {
        if (TextUtils.isEmpty((CharSequence)this.smiles) && TextUtils.isEmpty((CharSequence)this.key)) {
            final List<String> tags = this.tags;
            if (tags == null || tags.size() <= 0) {
                return true;
            }
        }
        return false;
    }
    
    public void setFormola(final String formola) {
        this.formola = formola;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setSmiles(final String smiles) {
        this.smiles = smiles;
    }
    
    public void setStructImagePath(final String structImagePath) {
        this.structImagePath = structImagePath;
    }
    
    public void setTags(final List<String> tags) {
        this.tags = tags;
    }
}
