package com.kingagroot.kingdraw.widget.jsweb;

public interface OnJsWebListener
{
    void onBackButtonShow(final boolean p0);
    
    void onCloseWeb();
    
    void onGetDeviceData();
    
    void onGetUserToken();
    
    void onLoadFinish();
    
    void onLogout();
    
    void onOpenAppUrl(final String p0);
    
    void onOpenUrl(final String p0);
    
    void onSetBadge(final int p0);
    
    void onUpdateBadge();
    
    void onUpdateTitle(final String p0);
    
    void onUserAccountChanged();
    
    void onUserPwdChanged();
}
