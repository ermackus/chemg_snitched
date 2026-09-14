package com.yalantis.ucrop;

import okhttp3.OkHttpClient;

public class OkHttpClientStore
{
    public static final OkHttpClientStore INSTANCE;
    private OkHttpClient client;
    
    static {
        INSTANCE = new OkHttpClientStore();
    }
    
    private OkHttpClientStore() {
    }
    
    public OkHttpClient getClient() {
        if (this.client == null) {
            this.client = new OkHttpClient();
        }
        return this.client;
    }
    
    void setClient(final OkHttpClient client) {
        this.client = client;
    }
}
