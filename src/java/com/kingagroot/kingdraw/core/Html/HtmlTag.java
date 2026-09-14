package com.kingagroot.kingdraw.core.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

class HtmlTag
{
    public static final String SPAN = "span";
    public static final String TEXT = "text";
    private HashMap<String, String> attr;
    private List<HtmlTag> childTags;
    public String name;
    public String text;
    
    HtmlTag() {
        this.text = "";
        this.attr = (HashMap<String, String>)new HashMap();
        this.childTags = (List<HtmlTag>)new ArrayList();
    }
    
    public void addChild(final HtmlTag htmlTag) {
        this.childTags.add((Object)htmlTag);
    }
    
    public String getAttr(final String s) {
        return (String)this.attr.get((Object)s);
    }
    
    public List<HtmlTag> getChildTags() {
        return this.childTags;
    }
    
    public void setAttr(final HashMap<String, String> attr) {
        this.attr = attr;
    }
}
