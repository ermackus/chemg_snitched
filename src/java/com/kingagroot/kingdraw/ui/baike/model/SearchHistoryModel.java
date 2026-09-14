package com.kingagroot.kingdraw.ui.baike.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "SearchHistoryModel")
public class SearchHistoryModel
{
    @Column(name = "createTime")
    private long createTime;
    @Column(isId = true, name = "id")
    int id;
    @Column(name = "searchName")
    private String searchName;
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getSearchName() {
        return this.searchName;
    }
    
    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setSearchName(final String searchName) {
        this.searchName = searchName;
    }
}
