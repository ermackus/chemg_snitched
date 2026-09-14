package com.alibaba.sdk.android.man.network;

import java.util.Iterator;
import android.util.Log;
import com.alibaba.sdk.android.man.util.ToolKit;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import java.util.HashMap;
import com.alibaba.sdk.android.man.util.MANLog;
import java.util.Map;

public class NetworkEvent
{
    public static final String TAG = "MAN_NetworkEvent";
    private long connectTime;
    private long firstByteRT;
    private long loadBytes;
    Map<String, String> property;
    private long requestRT;
    private a requestStatus;
    private long requestTimeStart;
    
    public NetworkEvent() {
        this.requestTimeStart = -1L;
        this.requestRT = -1L;
        this.connectTime = -1L;
        this.firstByteRT = -1L;
        this.loadBytes = 0L;
    }
    
    public void addMANEventProperty(final Map<String, String> property) {
        final Map<String, String> property2 = this.property;
        if (property2 == null) {
            this.property = property;
        }
        else {
            property2.putAll((Map)property);
        }
    }
    
    public void connectionEnd() {
        if (this.connectTime != -1L) {
            return;
        }
        this.connectTime = System.currentTimeMillis() - this.requestTimeStart;
        final StringBuilder sb = new StringBuilder();
        sb.append("[connectionEnd] requestTimeStart : ");
        sb.append(this.requestTimeStart);
        MANLog.Logd("MAN_NetworkEvent", sb.toString());
    }
    
    public void firstByteEnd() {
        if (this.firstByteRT != -1L) {
            return;
        }
        this.firstByteRT = System.currentTimeMillis() - this.requestTimeStart;
        final StringBuilder sb = new StringBuilder();
        sb.append("[firstByteEnd] - ");
        sb.append(this.firstByteRT);
        MANLog.Logd("MAN_NetworkEvent", sb.toString());
    }
    
    public boolean isAdvancedStat() {
        return this.connectTime != -1L || this.firstByteRT != -1L;
    }
    
    public boolean isDefineErrorCode(final int n) {
        return (n >= 1001 && n <= 1010) || (n >= 2001 && n <= 2010);
    }
    
    public void reportNetworkInfo() {
        if (this.property == null) {
            this.property = (Map<String, String>)new HashMap();
        }
        if (this.requestStatus == a.a) {
            EventCommitTool.commitEvent(3002, "MAS_NET_SIG_REQUEST", this.property);
        }
        else if (this.requestStatus == a.b) {
            EventCommitTool.commitEvent(3004, "MAS_NET_ERR", this.property);
        }
        else if (this.requestStatus == a.c) {}
    }
    
    public void requestEndNormally(final long loadBytes) {
        if (this.requestTimeStart != -1L && this.requestRT == -1L) {
            this.loadBytes = loadBytes;
            this.requestRT = System.currentTimeMillis() - this.requestTimeStart;
            if (this.property == null) {
                this.property = (Map<String, String>)new HashMap();
            }
            if (this.property.containsKey((Object)"Host")) {
                final String s = (String)this.property.get((Object)"Host");
                if (!ToolKit.isHost(s) && !ToolKit.isIp(s)) {
                    this.property.remove((Object)"Host");
                }
            }
            if (this.connectTime != -1L) {
                final StringBuilder sb = new StringBuilder();
                sb.append("connect: ");
                sb.append(this.connectTime);
                Log.d("man", sb.toString());
                this.property.put((Object)"singleConnectTime", (Object)String.valueOf(this.connectTime));
            }
            if (this.firstByteRT != -1L) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("connect: ");
                sb2.append(this.firstByteRT);
                Log.d("man", sb2.toString());
                this.property.put((Object)"firstPacketRT", (Object)String.valueOf(this.firstByteRT));
            }
            if (this.requestRT != -1L) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("connect: ");
                sb3.append(this.requestRT);
                Log.d("man", sb3.toString());
                this.property.put((Object)"singleRequestRT", (Object)String.valueOf(this.requestRT));
            }
            if (this.loadBytes >= 0L) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("loadBytes: ");
                sb4.append(this.loadBytes);
                Log.d("man", sb4.toString());
                this.property.put((Object)"singleRequestBytes", (Object)String.valueOf(this.loadBytes));
            }
            this.requestStatus = a.a;
            return;
        }
        MANLog.Loge("MAN_NetworkEvent", "[requestEnd] - illegal state");
        this.requestStatus = a.c;
    }
    
    public void requestEndWithError(final Map<String, String> property) {
        if (this.requestTimeStart != -1L && property != null) {
            if (this.property != null) {
                for (final String s : property.keySet()) {
                    if (s != null && property.get((Object)s) != null) {
                        this.property.put((Object)s.toString(), (Object)((String)property.get((Object)s)).toString());
                    }
                }
            }
            else {
                this.property = property;
            }
            if (this.property.containsKey((Object)"ErrorCode")) {
                final String s2 = (String)this.property.get((Object)"ErrorCode");
                try {
                    if (!this.isDefineErrorCode(Integer.parseInt(s2))) {
                        this.property.remove((Object)"ErrorCode");
                    }
                }
                catch (final NumberFormatException ex) {
                    this.property.remove((Object)"ErrorCode");
                }
            }
            this.requestStatus = a.b;
            return;
        }
        this.requestStatus = a.c;
    }
    
    public void requestStart() {
        this.requestTimeStart = System.currentTimeMillis();
    }
    
    private enum a
    {
        a;
        
        private static final a[] a;
        
        b, 
        c;
    }
}
