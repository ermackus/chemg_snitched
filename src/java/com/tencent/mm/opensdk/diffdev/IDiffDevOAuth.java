package com.tencent.mm.opensdk.diffdev;

public interface IDiffDevOAuth
{
    void addListener(final OAuthListener p0);
    
    boolean auth(final String p0, final String p1, final String p2, final String p3, final String p4, final OAuthListener p5);
    
    void detach();
    
    void removeAllListeners();
    
    void removeListener(final OAuthListener p0);
    
    boolean stopAuth();
}
