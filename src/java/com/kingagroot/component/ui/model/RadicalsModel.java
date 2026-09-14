package com.kingagroot.component.ui.model;

import java.util.List;

public class RadicalsModel
{
    private List<GSGroupModel> datas;
    private int versionCode;
    
    public List<GSGroupModel> getDatas() {
        return this.datas;
    }
    
    public int getVersionCode() {
        return this.versionCode;
    }
    
    public void setDatas(final List<GSGroupModel> datas) {
        this.datas = datas;
    }
    
    public void setVersionCode(final int versionCode) {
        this.versionCode = versionCode;
    }
}
