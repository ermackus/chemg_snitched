package com.ut.mini.plugin;

import android.text.TextUtils;
import android.os.Bundle;
import android.app.Activity;
import android.os.Message;
import java.util.Iterator;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.b;
import com.ut.mini.core.appstatus.UTMCAppStatusRegHelper;
import android.os.Build$VERSION;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.os.Handler;
import android.os.HandlerThread;
import com.ut.mini.core.appstatus.UTMCAppStatusCallbacks;

public class UTPluginMgr implements UTMCAppStatusCallbacks
{
    public static final String PARTNERPLUGIN_UTPREF = "com.ut.mini.perf.UTPerfPlugin";
    private static UTPluginMgr a;
    private HandlerThread b;
    private Handler mHandler;
    private List<UTPlugin> n;
    private List<String> o;
    private List<String> p;
    private List<UTPlugin> q;
    
    static {
        UTPluginMgr.a = new UTPluginMgr();
    }
    
    private UTPluginMgr() {
        this.b = null;
        this.mHandler = null;
        this.n = (List<UTPlugin>)new LinkedList();
        this.o = (List<String>)new ArrayList();
        this.p = (List<String>)new UTPluginMgr$1(this);
        this.q = (List<UTPlugin>)new LinkedList();
        if (Build$VERSION.SDK_INT >= 14) {
            UTMCAppStatusRegHelper.registerAppStatusCallbacks((UTMCAppStatusCallbacks)this);
        }
    }
    
    private void K() {
        (this.b = new HandlerThread("UT-PLUGIN-ASYNC")).start();
        this.mHandler = (Handler)new UTPluginMgr$2(this, this.b.getLooper());
    }
    
    private UTPluginContext a() {
        final UTPluginContext utPluginContext = new UTPluginContext();
        utPluginContext.setContext(com.alibaba.mtl.log.b.a().getContext());
        if (i.l()) {
            utPluginContext.setDebugLogFlag(i.l());
        }
        return utPluginContext;
    }
    
    private void a(final int n, final UTPluginContextValueDispatchDelegate utPluginContextValueDispatchDelegate) {
        monitorenter(this);
        if (utPluginContextValueDispatchDelegate == null) {
            monitorexit(this);
            return;
        }
        try {
            for (final UTPlugin utPlugin : this.q) {
                utPluginContextValueDispatchDelegate.onPluginContextValueChange(utPlugin.getPluginContext());
                utPlugin.onPluginContextValueUpdate(n);
            }
        }
        finally {
            monitorexit(this);
        }
    }
    
    private boolean a(final int n, final int[] array) {
        boolean b = false;
        int i = 0;
        if (array != null) {
            final int length = array.length;
            b = false;
            while (i < length) {
                if (array[i] == n) {
                    b = true;
                }
                ++i;
            }
        }
        return b;
    }
    
    public static UTPluginMgr getInstance() {
        return UTPluginMgr.a;
    }
    
    public boolean dispatchPluginMsg(final int n, final Object o) {
        synchronized (this) {
            if (this.mHandler == null) {
                this.K();
            }
            boolean b = false;
            boolean b2 = false;
            if (this.q.size() > 0) {
                final Iterator iterator = this.q.iterator();
                while (true) {
                    b = b2;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    final UTPlugin utPlugin = (UTPlugin)iterator.next();
                    final int[] returnRequiredMsgIds = utPlugin.returnRequiredMsgIds();
                    if (returnRequiredMsgIds == null || !this.a(n, returnRequiredMsgIds)) {
                        continue;
                    }
                    Label_0227: {
                        if (n != 1) {
                            if (this.n == null || !this.n.contains((Object)utPlugin)) {
                                final UTPluginMgr.UTPluginMgr$a obj = new UTPluginMgr.UTPluginMgr$a((UTPluginMgr$1)null);
                                obj.g(n);
                                obj.c(o);
                                obj.a(utPlugin);
                                final Message obtain = Message.obtain();
                                obtain.what = 1;
                                obtain.obj = obj;
                                this.mHandler.sendMessage(obtain);
                                break Label_0227;
                            }
                        }
                        try {
                            if (o instanceof UTPluginMsgDispatchDelegate) {
                                final UTPluginMsgDispatchDelegate utPluginMsgDispatchDelegate = (UTPluginMsgDispatchDelegate)o;
                                if (utPluginMsgDispatchDelegate.isMatchPlugin(utPlugin)) {
                                    utPlugin.onPluginMsgArrivedFromSDK(n, utPluginMsgDispatchDelegate.getDispatchObject(utPlugin));
                                }
                            }
                            else {
                                utPlugin.onPluginMsgArrivedFromSDK(n, o);
                            }
                            b2 = true;
                        }
                        finally {
                            final Throwable t;
                            t.printStackTrace();
                        }
                    }
                }
            }
            return b;
        }
    }
    
    public boolean isPartnerPluginExist(final String s) {
        return this.o.contains((Object)s);
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityDestroyed(final Activity activity) {
    }
    
    public void onActivityPaused(final Activity activity) {
    }
    
    public void onActivityResumed(final Activity activity) {
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
    }
    
    public void onActivityStopped(final Activity activity) {
    }
    
    public void onSwitchBackground() {
        this.dispatchPluginMsg(2, null);
    }
    
    public void onSwitchForeground() {
        this.dispatchPluginMsg(8, null);
    }
    
    public void registerPlugin(final UTPlugin utPlugin, final boolean b) {
        monitorenter(this);
        if (utPlugin != null) {
            try {
                if (!this.q.contains((Object)utPlugin)) {
                    utPlugin.a(this.a());
                    this.q.add((Object)utPlugin);
                    if (!b) {
                        this.n.add((Object)utPlugin);
                    }
                    utPlugin.onRegistered();
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    public void runPartnerPlugin() {
        final List<String> p = this.p;
        if (p != null && p.size() > 0) {
            for (final String className : this.p) {
                if (!TextUtils.isEmpty((CharSequence)className)) {
                    try {
                        final Object instance = Class.forName(className).newInstance();
                        if (!(instance instanceof UTPlugin)) {
                            continue;
                        }
                        this.registerPlugin((UTPlugin)instance, true);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("runPartnerPlugin[OK]:");
                        sb.append(className);
                        i.a(sb.toString(), new String[0]);
                        this.o.add((Object)className);
                    }
                    catch (final IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    catch (final InstantiationException ex2) {
                        ex2.printStackTrace();
                    }
                    catch (final ClassNotFoundException ex3) {}
                }
            }
        }
    }
    
    public void unregisterPlugin(final UTPlugin utPlugin) {
        monitorenter(this);
        Label_0041: {
            if (utPlugin == null) {
                break Label_0041;
            }
            try {
                if (this.q.contains((Object)utPlugin)) {
                    this.q.remove((Object)utPlugin);
                    utPlugin.onUnRegistered();
                    utPlugin.a((UTPluginContext)null);
                }
                if (this.n != null && this.n.contains((Object)utPlugin)) {
                    this.n.remove((Object)utPlugin);
                }
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    public void updatePluginContextValue(final int n) {
        if (n == 1) {
            this.a(n, (UTPluginContextValueDispatchDelegate)new UTPluginContextValueDispatchDelegate(this) {
                final UTPluginMgr b;
                
                public void onPluginContextValueChange(final UTPluginContext utPluginContext) {
                    utPluginContext.setDebugLogFlag(i.l());
                }
            });
        }
    }
}
