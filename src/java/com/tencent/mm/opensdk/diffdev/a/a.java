package com.tencent.mm.opensdk.diffdev.a;

import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Looper;
import java.util.Iterator;
import java.util.Collection;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import java.util.ArrayList;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import java.util.List;
import android.os.Handler;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;

public class a implements IDiffDevOAuth
{
    private Handler a;
    private List<OAuthListener> b;
    private b c;
    private OAuthListener d;
    
    public a() {
        this.a = null;
        this.b = (List<OAuthListener>)new ArrayList();
        this.d = (OAuthListener)new OAuthListener() {
            final a a;
            
            public void onAuthFinish(final OAuthErrCode oAuthErrCode, final String s) {
                Log.d("MicroMsg.SDK.ListenerWrapper", String.format("onAuthFinish, errCode = %s, authCode = %s", new Object[] { oAuthErrCode.toString(), s }));
                this.a.c = null;
                final ArrayList list = new ArrayList();
                list.addAll((Collection)this.a.b);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ((OAuthListener)iterator.next()).onAuthFinish(oAuthErrCode, s);
                }
            }
            
            public void onAuthGotQrcode(final String s, final byte[] array) {
                final StringBuilder sb = new StringBuilder();
                sb.append("onAuthGotQrcode, qrcodeImgPath = ");
                sb.append(s);
                Log.d("MicroMsg.SDK.ListenerWrapper", sb.toString());
                final ArrayList list = new ArrayList();
                list.addAll((Collection)this.a.b);
                final Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ((OAuthListener)iterator.next()).onAuthGotQrcode(s, array);
                }
            }
            
            public void onQrcodeScanned() {
                Log.d("MicroMsg.SDK.ListenerWrapper", "onQrcodeScanned");
                if (this.a.a != null) {
                    this.a.a.post((Runnable)new a$a$a(this));
                }
            }
        };
    }
    
    public void addListener(final OAuthListener oAuthListener) {
        if (!this.b.contains((Object)oAuthListener)) {
            this.b.add((Object)oAuthListener);
        }
    }
    
    public boolean auth(final String s, final String s2, final String s3, final String s4, final String s5, final OAuthListener oAuthListener) {
        final StringBuilder sb = new StringBuilder();
        sb.append("start auth, appId = ");
        sb.append(s);
        Log.i("MicroMsg.SDK.DiffDevOAuth", sb.toString());
        if (s == null || s.length() <= 0 || s2 == null || s2.length() <= 0) {
            Log.d("MicroMsg.SDK.DiffDevOAuth", String.format("auth fail, invalid argument, appId = %s, scope = %s", new Object[] { s, s2 }));
            return false;
        }
        if (this.a == null) {
            this.a = new Handler(Looper.getMainLooper());
        }
        if (!this.b.contains((Object)oAuthListener)) {
            this.b.add((Object)oAuthListener);
        }
        if (this.c != null) {
            Log.d("MicroMsg.SDK.DiffDevOAuth", "auth, already running, no need to start auth again");
            return true;
        }
        final b c = new b(s, s2, s3, s4, s5, this.d);
        this.c = c;
        if (Build$VERSION.SDK_INT >= 11) {
            ((AsyncTask)c).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        }
        else {
            ((AsyncTask)c).execute((Object[])new Void[0]);
        }
        return true;
    }
    
    public void detach() {
        Log.i("MicroMsg.SDK.DiffDevOAuth", "detach");
        this.b.clear();
        this.stopAuth();
    }
    
    public void removeAllListeners() {
        this.b.clear();
    }
    
    public void removeListener(final OAuthListener oAuthListener) {
        this.b.remove((Object)oAuthListener);
    }
    
    public boolean stopAuth() {
        Log.i("MicroMsg.SDK.DiffDevOAuth", "stopAuth");
        boolean b;
        try {
            b = (this.c == null || this.c.a());
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("stopAuth fail, ex = ");
            sb.append(ex.getMessage());
            Log.w("MicroMsg.SDK.DiffDevOAuth", sb.toString());
            b = false;
        }
        this.c = null;
        return b;
    }
}
