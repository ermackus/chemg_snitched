package com.ut.mini.base;

import com.ut.mini.sdkevents.UTMI1010_2001Event;

public class UTMIVariables
{
    private static UTMIVariables a;
    private boolean S;
    private UTMI1010_2001Event a;
    private String ao;
    private String ar;
    private String as;
    
    static {
        UTMIVariables.a = new UTMIVariables();
    }
    
    public UTMIVariables() {
        this.ar = null;
        this.ao = null;
        this.as = null;
        this.a = null;
        this.S = false;
    }
    
    public static UTMIVariables getInstance() {
        return UTMIVariables.a;
    }
    
    public String getH5RefPage() {
        return this.ar;
    }
    
    public String getH5Url() {
        return this.as;
    }
    
    public String getRefPage() {
        return this.ao;
    }
    
    public UTMI1010_2001Event getUTMI1010_2001EventInstance() {
        synchronized (this) {
            return this.a;
        }
    }
    
    public boolean isAliyunOSPlatform() {
        synchronized (this) {
            return this.S;
        }
    }
    
    public void setH5RefPage(final String ar) {
        this.ar = ar;
    }
    
    public void setH5Url(final String as) {
        this.as = as;
    }
    
    public void setRefPage(final String ao) {
        this.ao = ao;
    }
    
    public void setToAliyunOSPlatform() {
        synchronized (this) {
            this.S = true;
        }
    }
    
    public void setUTMI1010_2001EventInstance(final UTMI1010_2001Event a) {
        synchronized (this) {
            this.a = a;
        }
    }
}
