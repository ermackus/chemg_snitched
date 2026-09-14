package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GTagModel")
public class GTagModel implements Serializable
{
    @Column(autoGen = true, isId = true, name = "Id")
    int Id;
    @Column(name = "createTime")
    long createTime;
    @Column(name = "isSelect")
    boolean isSelect;
    @Column(name = "name")
    String name;
    @Column(name = "selectTime")
    long selectTime;
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getSelectTime() {
        return this.selectTime;
    }
    
    public boolean isSelect() {
        return this.isSelect;
    }
    
    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSelect(final boolean isSelect) {
        this.isSelect = isSelect;
    }
    
    public void setSelectTime(final long selectTime) {
        this.selectTime = selectTime;
    }
}
