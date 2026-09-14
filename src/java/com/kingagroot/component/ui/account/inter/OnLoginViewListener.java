package com.kingagroot.component.ui.account.inter;

import com.kingagroot.component.ui.model.CountryModel;

public interface OnLoginViewListener
{
    void onChooseCountryClick();
    
    void onEmailGetPasswordClick(final String p0);
    
    void onLoginClick(final String p0, final String p1, final int p2, final String p3, final boolean p4, final String p5);
    
    void onPhoneGetPasswordClick(final CountryModel p0, final String p1);
}
