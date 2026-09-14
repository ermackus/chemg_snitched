package com.ut.mini.internal;

public class CustomDNS
{
    private IDnsResolver a;
    
    private CustomDNS() {
        this.a = null;
    }
    
    public static CustomDNS instance() {
        return a.a;
    }
    
    public String[] resolveUrl(final String s) {
        final IDnsResolver a = this.a;
        String[] resolveUrl;
        if (a != null) {
            resolveUrl = a.resolveUrl(s);
        }
        else {
            resolveUrl = null;
        }
        return resolveUrl;
    }
    
    public void setDnsResolver(final IDnsResolver a) {
        this.a = a;
    }
    
    public interface IDnsResolver
    {
        String[] resolveUrl(final String p0);
    }
    
    private static class a
    {
        private static final CustomDNS a;
        
        static {
            a = new CustomDNS(null);
        }
    }
}
