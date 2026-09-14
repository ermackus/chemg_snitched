package com.kingagroot.component.ui.account.inter;

public interface OnRegisterViewClick
{
    void onChooseCountryClick(final int p0);
    
    void onEmailClick();
    
    void onPrivacyClick();
    
    void onRegisterClick(final int p0, final int p1, final String p2, final String p3, final String p4, final String p5);
    
    void onSendCodeClick(final int p0, final String p1, final String p2);
    
    void onServiceClick();
}
