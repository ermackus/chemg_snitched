package com.kingagroot.kingdraw.ui.workstation;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "WorkStationModel")
public class WorkStationModel implements Serializable
{
    @Column(autoGen = false, isId = true, name = "AppID")
    private int AppID;
    @Column(name = "CategoryID")
    private int CategoryID;
    @Column(name = "FuallScreen")
    private int FuallScreen;
    @Column(name = "IsGroupApp")
    private int IsGroupApp;
    @Column(name = "Logo")
    private String Logo;
    @Column(name = "MinVersion")
    private String MinVersion;
    @Column(name = "NameCN")
    private String NameCN;
    @Column(name = "NameEN")
    private String NameEN;
    @Column(name = "NeedLogin")
    private int NeedLogin;
    @Column(name = "Order")
    private int Order;
    @Column(name = "Url")
    private String Url;
    
    public int getAppID() {
        return this.AppID;
    }
    
    public int getCategoryID() {
        return this.CategoryID;
    }
    
    public int getFuallScreen() {
        return this.FuallScreen;
    }
    
    public int getIsGroupApp() {
        return this.IsGroupApp;
    }
    
    public String getLogo() {
        return this.Logo;
    }
    
    public String getMinVersion() {
        return this.MinVersion;
    }
    
    public String getNameCN() {
        return this.NameCN;
    }
    
    public String getNameEN() {
        return this.NameEN;
    }
    
    public int getNeedLogin() {
        return this.NeedLogin;
    }
    
    public int getOrder() {
        return this.Order;
    }
    
    public String getUrl() {
        return this.Url;
    }
    
    public void setAppID(final int appID) {
        this.AppID = appID;
    }
    
    public void setCategoryID(final int categoryID) {
        this.CategoryID = categoryID;
    }
    
    public void setFuallScreen(final int fuallScreen) {
        this.FuallScreen = fuallScreen;
    }
    
    public void setIsGroupApp(final int isGroupApp) {
        this.IsGroupApp = isGroupApp;
    }
    
    public void setLogo(final String logo) {
        this.Logo = logo;
    }
    
    public void setMinVersion(final String minVersion) {
        this.MinVersion = minVersion;
    }
    
    public void setNameCN(final String nameCN) {
        this.NameCN = nameCN;
    }
    
    public void setNameEN(final String nameEN) {
        this.NameEN = nameEN;
    }
    
    public void setNeedLogin(final int needLogin) {
        this.NeedLogin = needLogin;
    }
    
    public void setOrder(final int order) {
        this.Order = order;
    }
    
    public void setUrl(final String url) {
        this.Url = url;
    }
}
