package com.kingagroot.kingdraw.model;

import android.text.TextUtils;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "GroupModel")
public class GroupModel
{
    @Column(name = "authority")
    private int authority;
    @Column(name = "companyName")
    private String companyName;
    @Column(autoGen = false, isId = true, name = "groupID")
    private int groupID;
    @Column(name = "groupOpenID")
    private String groupOpenID;
    @Column(name = "logo")
    private String logo;
    @Column(name = "status")
    private int status;
    
    public int getAuthority() {
        return this.authority;
    }
    
    public String getCompanyName() {
        return this.companyName.trim();
    }
    
    public int getGroupID() {
        return this.groupID;
    }
    
    public String getGroupOpenID() {
        return this.groupOpenID;
    }
    
    public String getLogo() {
        if (!TextUtils.isEmpty((CharSequence)this.logo)) {
            return this.logo.trim();
        }
        return this.logo;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setAuthority(final int authority) {
        this.authority = authority;
    }
    
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }
    
    public void setGroupID(final int groupID) {
        this.groupID = groupID;
    }
    
    public void setGroupOpenID(final String groupOpenID) {
        this.groupOpenID = groupOpenID;
    }
    
    public void setLogo(final String logo) {
        this.logo = logo;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
}
