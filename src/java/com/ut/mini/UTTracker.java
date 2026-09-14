package com.ut.mini;

import android.net.Uri;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.a;
import com.ut.mini.base.UTMIVariables;
import com.alibaba.mtl.log.d.q;
import android.text.TextUtils;
import java.util.Iterator;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UTTracker
{
    private static Pattern a;
    private Map<String, String> D;
    private String aq;
    private String g;
    
    static {
        UTTracker.a = Pattern.compile("(\\|\\||[\t\r\n])+");
    }
    
    public UTTracker() {
        this.aq = null;
        this.D = (Map<String, String>)new HashMap();
    }
    
    private static String b(final String s) {
        return d(s);
    }
    
    private static String d(final String s) {
        String replaceAll = s;
        if (s != null) {
            replaceAll = s;
            if (!"".equals((Object)s)) {
                replaceAll = UTTracker.a.matcher((CharSequence)s).replaceAll("");
            }
        }
        return replaceAll;
    }
    
    private static void d(final Map<String, String> map) {
        if (map != null) {
            if (map.containsKey((Object)"_field_os")) {
                final String s = (String)map.get((Object)"_field_os");
                map.remove((Object)"_field_os");
                map.put((Object)LogField.OS.toString(), (Object)s);
            }
            if (map.containsKey((Object)"_field_os_version")) {
                final String s2 = (String)map.get((Object)"_field_os_version");
                map.remove((Object)"_field_os_version");
                map.put((Object)LogField.OSVERSION.toString(), (Object)s2);
            }
        }
    }
    
    private static void f(final Map<String, String> map) {
        if (map != null) {
            if (map.containsKey((Object)LogField.IMEI.toString())) {
                map.remove((Object)LogField.IMEI.toString());
            }
            if (map.containsKey((Object)LogField.IMSI.toString())) {
                map.remove((Object)LogField.IMSI.toString());
            }
            if (map.containsKey((Object)LogField.CARRIER.toString())) {
                map.remove((Object)LogField.CARRIER.toString());
            }
            if (map.containsKey((Object)LogField.ACCESS.toString())) {
                map.remove((Object)LogField.ACCESS.toString());
            }
            if (map.containsKey((Object)LogField.ACCESS_SUBTYPE.toString())) {
                map.remove((Object)LogField.ACCESS_SUBTYPE.toString());
            }
            if (map.containsKey((Object)LogField.CHANNEL.toString())) {
                map.remove((Object)LogField.CHANNEL.toString());
            }
            if (map.containsKey((Object)LogField.LL_USERNICK.toString())) {
                map.remove((Object)LogField.LL_USERNICK.toString());
            }
            if (map.containsKey((Object)LogField.USERNICK.toString())) {
                map.remove((Object)LogField.USERNICK.toString());
            }
            if (map.containsKey((Object)LogField.LL_USERID.toString())) {
                map.remove((Object)LogField.LL_USERID.toString());
            }
            if (map.containsKey((Object)LogField.USERID.toString())) {
                map.remove((Object)LogField.USERID.toString());
            }
            if (map.containsKey((Object)LogField.SDKVERSION.toString())) {
                map.remove((Object)LogField.SDKVERSION.toString());
            }
            if (map.containsKey((Object)LogField.START_SESSION_TIMESTAMP.toString())) {
                map.remove((Object)LogField.START_SESSION_TIMESTAMP.toString());
            }
            if (map.containsKey((Object)LogField.UTDID.toString())) {
                map.remove((Object)LogField.UTDID.toString());
            }
            if (map.containsKey((Object)LogField.SDKTYPE.toString())) {
                map.remove((Object)LogField.SDKTYPE.toString());
            }
            if (map.containsKey((Object)LogField.RESERVE2.toString())) {
                map.remove((Object)LogField.RESERVE2.toString());
            }
            if (map.containsKey((Object)LogField.RESERVE3.toString())) {
                map.remove((Object)LogField.RESERVE3.toString());
            }
            if (map.containsKey((Object)LogField.RESERVE4.toString())) {
                map.remove((Object)LogField.RESERVE4.toString());
            }
            if (map.containsKey((Object)LogField.RESERVE5.toString())) {
                map.remove((Object)LogField.RESERVE5.toString());
            }
            if (map.containsKey((Object)LogField.RESERVES.toString())) {
                map.remove((Object)LogField.RESERVES.toString());
            }
            if (map.containsKey((Object)LogField.RECORD_TIMESTAMP.toString())) {
                map.remove((Object)LogField.RECORD_TIMESTAMP.toString());
            }
        }
    }
    
    private Map<String, String> g(final Map<String, String> map) {
        if (map != null) {
            final HashMap hashMap = new HashMap();
            final Iterator iterator = map.keySet().iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    final String s = (String)iterator.next();
                    if (s != null) {
                        ((Map)hashMap).put((Object)s, (Object)b((String)map.get((Object)s)));
                    }
                }
            }
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    private static void g(final Map<String, String> map) {
        map.put((Object)LogField.SDKTYPE.toString(), (Object)"mini");
    }
    
    private static void h(final Map<String, String> map) {
        final HashMap hashMap = new HashMap();
        if (map.containsKey((Object)"_track_id")) {
            final String s = (String)map.get((Object)"_track_id");
            map.remove((Object)"_track_id");
            if (!TextUtils.isEmpty((CharSequence)s)) {
                ((Map)hashMap).put((Object)"_tkid", (Object)s);
            }
        }
        if (((Map)hashMap).size() > 0) {
            map.put((Object)LogField.RESERVES.toString(), (Object)q.d((Map)hashMap));
        }
        if (!map.containsKey((Object)LogField.PAGE.toString())) {
            map.put((Object)LogField.PAGE.toString(), (Object)"UT");
        }
    }
    
    public String getGlobalProperty(String s) {
        monitorenter(this);
        if (s != null) {
            try {
                s = (String)this.D.get((Object)s);
                return s;
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
        return null;
    }
    
    public void pageAppear(final Object o) {
        UTPageHitHelper.getInstance().pageAppear(o);
    }
    
    public void pageAppear(final Object o, final String s) {
        UTPageHitHelper.getInstance().pageAppear(o, s);
    }
    
    public void pageAppearDonotSkip(final Object o) {
        UTPageHitHelper.getInstance().a(o, null, true);
    }
    
    public void pageAppearDonotSkip(final Object o, final String s) {
        UTPageHitHelper.getInstance().a(o, s, true);
    }
    
    public void pageDisAppear(final Object o) {
        UTPageHitHelper.getInstance().pageDisAppear(o);
    }
    
    void q(final String aq) {
        this.aq = aq;
    }
    
    protected void r(final String g) {
        this.g = g;
    }
    
    public void removeGlobalProperty(final String s) {
        monitorenter(this);
        if (s != null) {
            try {
                if (this.D.containsKey((Object)s)) {
                    this.D.remove((Object)s);
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    public void send(final Map<String, String> map) {
        if (map != null) {
            final HashMap hashMap = new HashMap();
            ((Map)hashMap).putAll((Map)this.D);
            ((Map)hashMap).putAll((Map)map);
            if (!TextUtils.isEmpty((CharSequence)this.g)) {
                ((Map)hashMap).put((Object)LogField.APPKEY.toString(), (Object)this.g);
            }
            final Map<String, String> g = this.g((Map<String, String>)hashMap);
            if (!TextUtils.isEmpty((CharSequence)this.aq)) {
                g.put((Object)"_track_id", (Object)this.aq);
            }
            UTMIVariables.getInstance().isAliyunOSPlatform();
            f(g);
            d(g);
            g(g);
            h(g);
            com.alibaba.mtl.log.a.a((String)g.remove((Object)LogField.PAGE.toString()), (String)g.remove((Object)LogField.EVENTID.toString()), (String)g.remove((Object)LogField.ARG1.toString()), (String)g.remove((Object)LogField.ARG2.toString()), (String)g.remove((Object)LogField.ARG3.toString()), (Map)g);
        }
    }
    
    public void setGlobalProperty(final String s, final String s2) {
        synchronized (this) {
            if (!TextUtils.isEmpty((CharSequence)s) && s2 != null) {
                this.D.put((Object)s, (Object)s2);
            }
            else {
                i.a("setGlobalProperty", (Object)"key is null or key is empty or value is null,please check it!");
            }
        }
    }
    
    public void skipPage(final Object o) {
        UTPageHitHelper.getInstance().skipPage(o);
    }
    
    public void updateNextPageProperties(final Map<String, String> map) {
        UTPageHitHelper.getInstance().updateNextPageProperties(map);
    }
    
    public void updatePageName(final Object o, final String s) {
        UTPageHitHelper.getInstance().updatePageName(o, s);
    }
    
    public void updatePageProperties(final Object o, final Map<String, String> map) {
        UTPageHitHelper.getInstance().updatePageProperties(o, map);
    }
    
    public void updatePageStatus(final Object o, final UTPageStatus utPageStatus) {
        UTPageHitHelper.getInstance().updatePageStatus(o, utPageStatus);
    }
    
    public void updatePageUrl(final Object o, final Uri uri) {
        UTPageHitHelper.getInstance().updatePageUrl(o, uri);
    }
}
