package com.alipay.android.app.helper;

public class Tid extends com.alipay.sdk.tid.Tid
{
    public Tid(final String s, final String s2, final long n) {
        super(s, s2, n);
    }
    
    public static Tid fromRealTidModel(final com.alipay.sdk.tid.Tid tid) {
        if (tid == null) {
            return null;
        }
        return new Tid(tid.getTid(), tid.getTidSeed(), tid.getTimestamp());
    }
}
