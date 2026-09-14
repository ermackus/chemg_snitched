package com.ut.mini;

import android.net.Uri;
import java.util.HashMap;
import com.ut.mini.base.UTMIVariables;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import android.text.TextUtils;
import java.util.Map;
import java.util.Date;

public class UTHybridHelper
{
    private static UTHybridHelper a;
    
    static {
        UTHybridHelper.a = new UTHybridHelper();
    }
    
    private void a(final Date date, final Map<String, String> map) {
        if (map != null) {
            if (map.size() != 0) {
                final String b = this.b((String)map.get((Object)"urlpagename"), (String)map.get((Object)"url"));
                if (b != null && !TextUtils.isEmpty((CharSequence)b)) {
                    final String s = (String)map.get((Object)"logkey");
                    if (s != null && !TextUtils.isEmpty((CharSequence)s)) {
                        Map map2 = null;
                        final String s2 = (String)map.get((Object)"utjstype");
                        map.remove((Object)"utjstype");
                        if (s2 != null && !s2.equals((Object)"0")) {
                            if (s2.equals((Object)"1")) {
                                map2 = this.f(map);
                            }
                        }
                        else {
                            map2 = this.e(map);
                        }
                        final UTOriginalCustomHitBuilder utOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder(b, 2101, s, (String)null, (String)null, map2);
                        final UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                        if (defaultTracker != null) {
                            defaultTracker.send((Map<String, String>)utOriginalCustomHitBuilder.build());
                        }
                        else {
                            i.a("h5Ctrl event error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
                        }
                        return;
                    }
                    i.a("h5Ctrl", new String[] { "logkey is null,return" });
                }
                else {
                    i.a("h5Ctrl", new String[] { "pageName is null,return" });
                }
            }
        }
    }
    
    private void a(final Date date, final Map<String, String> map, final Object o) {
        if (map != null) {
            if (map.size() != 0) {
                final String b = this.b((String)map.get((Object)"urlpagename"), (String)map.get((Object)"url"));
                if (b != null && !TextUtils.isEmpty((CharSequence)b)) {
                    final String refPage = UTMIVariables.getInstance().getRefPage();
                    final String s = (String)map.get((Object)"utjstype");
                    map.remove((Object)"utjstype");
                    Map map2;
                    if (s != null && !s.equals((Object)"0")) {
                        if (s.equals((Object)"1")) {
                            map2 = this.d(map);
                        }
                        else {
                            map2 = null;
                        }
                    }
                    else {
                        map2 = this.c(map);
                    }
                    int n = 2006;
                    if (UTPageHitHelper.getInstance().a(o)) {
                        n = 2001;
                    }
                    final UTOriginalCustomHitBuilder utOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder(b, n, refPage, (String)null, (String)null, map2);
                    if (2001 == n) {
                        UTMIVariables.getInstance().setRefPage(b);
                    }
                    final Map<String, String> c = UTPageHitHelper.getInstance().c();
                    if (c != null && c.size() > 0) {
                        utOriginalCustomHitBuilder.setProperties((Map)c);
                    }
                    final UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                    if (defaultTracker != null) {
                        defaultTracker.send((Map<String, String>)utOriginalCustomHitBuilder.build());
                    }
                    else {
                        i.a("h5Page event error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
                    }
                    UTPageHitHelper.getInstance().a(o);
                    return;
                }
                i.a("h5Page", (Object)"pageName is null,return");
            }
        }
    }
    
    private String b(String substring, final String s) {
        if (substring == null || TextUtils.isEmpty((CharSequence)substring)) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final int index = s.indexOf("?");
                if (index == -1) {
                    substring = s;
                }
                else {
                    substring = s.substring(0, index);
                }
            }
            else {
                substring = "";
            }
        }
        return substring;
    }
    
    private Map<String, String> c(final Map<String, String> map) {
        if (map != null && map.size() != 0) {
            final HashMap hashMap = new HashMap();
            final String s = (String)map.get((Object)"url");
            final String s2 = "";
            String s3;
            if (s == null) {
                s3 = "";
            }
            else {
                s3 = s;
            }
            hashMap.put((Object)"_h5url", (Object)s3);
            if (s != null) {
                final Uri parse = Uri.parse(s);
                final String queryParameter = parse.getQueryParameter("spm");
                if (queryParameter != null && !TextUtils.isEmpty((CharSequence)queryParameter)) {
                    hashMap.put((Object)"spm", (Object)queryParameter);
                }
                else {
                    hashMap.put((Object)"spm", (Object)"0.0.0.0");
                }
                final String queryParameter2 = parse.getQueryParameter("scm");
                if (queryParameter2 != null && !TextUtils.isEmpty((CharSequence)queryParameter2)) {
                    hashMap.put((Object)"scm", (Object)queryParameter2);
                }
            }
            else {
                hashMap.put((Object)"spm", (Object)"0.0.0.0");
            }
            String s4;
            if ((s4 = (String)map.get((Object)"spmcnt")) == null) {
                s4 = "";
            }
            hashMap.put((Object)"_spmcnt", (Object)s4);
            String s5;
            if ((s5 = (String)map.get((Object)"spmpre")) == null) {
                s5 = "";
            }
            hashMap.put((Object)"_spmpre", (Object)s5);
            String s6;
            if ((s6 = (String)map.get((Object)"lzsid")) == null) {
                s6 = "";
            }
            hashMap.put((Object)"_lzsid", (Object)s6);
            String s7;
            if ((s7 = (String)map.get((Object)"extendargs")) == null) {
                s7 = "";
            }
            hashMap.put((Object)"_h5ea", (Object)s7);
            String s8 = (String)map.get((Object)"cna");
            if (s8 == null) {
                s8 = s2;
            }
            hashMap.put((Object)"_cna", (Object)s8);
            hashMap.put((Object)"_ish5", (Object)"1");
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    private Map<String, String> d(final Map<String, String> map) {
        if (map != null && map.size() != 0) {
            final HashMap hashMap = new HashMap();
            final String s = (String)map.get((Object)"url");
            final String s2 = "";
            String s3;
            if ((s3 = s) == null) {
                s3 = "";
            }
            hashMap.put((Object)"_h5url", (Object)s3);
            String s4 = (String)map.get((Object)"extendargs");
            if (s4 == null) {
                s4 = s2;
            }
            hashMap.put((Object)"_h5ea", (Object)s4);
            hashMap.put((Object)"_ish5", (Object)"1");
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    private Map<String, String> e(final Map<String, String> map) {
        if (map != null && map.size() != 0) {
            final HashMap hashMap = new HashMap();
            final String s = (String)map.get((Object)"logkeyargs");
            final String s2 = "";
            String s3;
            if ((s3 = s) == null) {
                s3 = "";
            }
            ((Map)hashMap).put((Object)"_lka", (Object)s3);
            String s4;
            if ((s4 = (String)map.get((Object)"cna")) == null) {
                s4 = "";
            }
            ((Map)hashMap).put((Object)"_cna", (Object)s4);
            String s5 = (String)map.get((Object)"extendargs");
            if (s5 == null) {
                s5 = s2;
            }
            ((Map)hashMap).put((Object)"_h5ea", (Object)s5);
            ((Map)hashMap).put((Object)"_ish5", (Object)"1");
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    private Map<String, String> f(final Map<String, String> map) {
        if (map != null && map.size() != 0) {
            final HashMap hashMap = new HashMap();
            String s;
            if ((s = (String)map.get((Object)"extendargs")) == null) {
                s = "";
            }
            ((Map)hashMap).put((Object)"_h5ea", (Object)s);
            ((Map)hashMap).put((Object)"_ish5", (Object)"1");
            return (Map<String, String>)hashMap;
        }
        return null;
    }
    
    public static UTHybridHelper getInstance() {
        return UTHybridHelper.a;
    }
    
    public void h5UT(final Map<String, String> map, final Object o) {
        if (map == null || map.size() == 0) {
            i.a("h5UT", (Object)"dataMap is empty");
            return;
        }
        final String s = (String)map.get((Object)"functype");
        if (s == null) {
            i.a("h5UT", (Object)"funcType is null");
            return;
        }
        final String s2 = (String)map.get((Object)"utjstype");
        if (s2 != null && !s2.equals((Object)"0") && !s2.equals((Object)"1")) {
            i.a("h5UT", (Object)"utjstype should be 1 or 0 or null");
            return;
        }
        map.remove((Object)"functype");
        final Date date = new Date();
        if (s.equals((Object)"2001")) {
            this.a(date, map, o);
        }
        else if (s.equals((Object)"2101")) {
            this.a(date, map);
        }
    }
    
    public void setH5Url(final String h5Url) {
        if (h5Url != null) {
            UTMIVariables.getInstance().setH5Url(h5Url);
        }
    }
}
