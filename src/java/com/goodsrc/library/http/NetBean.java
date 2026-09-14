package com.goodsrc.library.http;

import java.util.ArrayList;

public class NetBean<T, K>
{
    private T data;
    private ArrayList<K> datas;
    private String info;
    private boolean isOk;
    private int pagecount;
    private int totalCount;
    private String type;
    
    public NetBean() {
        this.isOk = false;
        this.info = "";
        this.data = null;
        this.datas = null;
        this.pagecount = 1;
        this.type = "";
    }
    
    public T getData() {
        return this.data;
    }
    
    public ArrayList<K> getDatas() {
        return this.datas;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public int getPagecount() {
        return this.pagecount;
    }
    
    public int getTotalCount() {
        return this.totalCount;
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean isOk() {
        return this.isOk;
    }
    
    public void setData(final T data) {
        this.data = data;
    }
    
    public void setDatas(final ArrayList<K> datas) {
        this.datas = datas;
    }
    
    public void setInfo(final String info) {
        this.info = info;
    }
    
    public void setOk(final boolean isOk) {
        this.isOk = isOk;
    }
    
    public void setPagecount(final int pagecount) {
        this.pagecount = pagecount;
    }
    
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
