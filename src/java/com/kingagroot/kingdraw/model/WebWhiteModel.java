package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "WebWhiteModel")
public class WebWhiteModel
{
    @Column(autoGen = false, isId = true, name = "id")
    private int id;
    @Column(name = "state")
    private int state;
    @Column(name = "url")
    private String url;
    
    public int getId() {
        return this.id;
    }
    
    public int getState() {
        return this.state;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setState(final int state) {
        this.state = state;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
}
