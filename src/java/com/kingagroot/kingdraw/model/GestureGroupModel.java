package com.kingagroot.kingdraw.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GestureGroupModel")
public class GestureGroupModel implements Serializable
{
    @Column(autoGen = false, isId = true, name = "bondId")
    private int bondId;
    @Column(name = "bondRes")
    private String bondRes;
    @Column(name = "gestureCoreId")
    private int gestureCoreId;
    @Column(name = "gestureId")
    private int gestureId;
    @Column(name = "gestureRes")
    private String gestureRes;
    @Column(name = "isBind")
    private boolean isBind;
    @Column(name = "param")
    private String param;
    @Column(name = "toolName")
    private String toolName;
    
    public int getBondId() {
        return this.bondId;
    }
    
    public String getBondRes() {
        return this.bondRes;
    }
    
    public int getGestureCoreId() {
        return this.gestureCoreId;
    }
    
    public int getGestureId() {
        return this.gestureId;
    }
    
    public String getGestureRes() {
        return this.gestureRes;
    }
    
    public String getParam() {
        return this.param;
    }
    
    public String getToolName() {
        return this.toolName;
    }
    
    public boolean isBind() {
        return this.isBind;
    }
    
    public void setBind(final boolean isBind) {
        this.isBind = isBind;
    }
    
    public void setBondId(final int bondId) {
        this.bondId = bondId;
    }
    
    public void setBondRes(final String bondRes) {
        this.bondRes = bondRes;
    }
    
    public void setGestureCoreId(final int gestureCoreId) {
        this.gestureCoreId = gestureCoreId;
    }
    
    public void setGestureId(final int gestureId) {
        this.gestureId = gestureId;
    }
    
    public void setGestureRes(final String gestureRes) {
        this.gestureRes = gestureRes;
    }
    
    public void setParam(final String param) {
        this.param = param;
    }
    
    public void setToolName(final String toolName) {
        this.toolName = toolName;
    }
}
