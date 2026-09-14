package com.tencent.mm.opensdk.diffdev;

import com.tencent.mm.opensdk.diffdev.a.a;
import com.tencent.mm.opensdk.utils.Log;

public class DiffDevOAuthFactory
{
    public static final int MAX_SUPPORTED_VERSION = 1;
    private static final String TAG = "MicroMsg.SDK.DiffDevOAuthFactory";
    public static final int VERSION_1 = 1;
    private static IDiffDevOAuth v1Instance;
    
    private DiffDevOAuthFactory() {
    }
    
    public static IDiffDevOAuth getDiffDevOAuth() {
        return getDiffDevOAuth(1);
    }
    
    public static IDiffDevOAuth getDiffDevOAuth(final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("getDiffDevOAuth, version = ");
        sb.append(n);
        Log.v("MicroMsg.SDK.DiffDevOAuthFactory", sb.toString());
        if (n > 1) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("getDiffDevOAuth fail, unsupported version = ");
            sb2.append(n);
            Log.e("MicroMsg.SDK.DiffDevOAuthFactory", sb2.toString());
            return null;
        }
        if (n != 1) {
            return null;
        }
        if (DiffDevOAuthFactory.v1Instance == null) {
            DiffDevOAuthFactory.v1Instance = (IDiffDevOAuth)new a();
        }
        return DiffDevOAuthFactory.v1Instance;
    }
}
