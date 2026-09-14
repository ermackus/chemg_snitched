package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "SearchHisModel")
public class SearchHisModel
{
    @Column(name = "creatTime")
    long creatTime;
    @Column(name = "data")
    String data;
    @Column(isId = true, name = "id")
    int id;
    @Column(name = "type")
    int type;
    
    public long getCreatTime() {
        return this.creatTime;
    }
    
    public String getData() {
        return this.data;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setCreatTime(final long creatTime) {
        this.creatTime = creatTime;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
}
