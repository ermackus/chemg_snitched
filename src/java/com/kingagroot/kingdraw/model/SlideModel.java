package com.kingagroot.kingdraw.model;

public class SlideModel
{
    private String nc_token;
    private String scene;
    private String sessionid;
    private String sig;
    
    public String getNc_token() {
        return this.nc_token;
    }
    
    public String getScene() {
        return this.scene;
    }
    
    public String getSessionid() {
        return this.sessionid;
    }
    
    public String getSig() {
        return this.sig;
    }
    
    public void setNc_token(final String nc_token) {
        this.nc_token = nc_token;
    }
    
    public void setScene(final String scene) {
        this.scene = scene;
    }
    
    public void setSessionid(final String sessionid) {
        this.sessionid = sessionid;
    }
    
    public void setSig(final String sig) {
        this.sig = sig;
    }
}
