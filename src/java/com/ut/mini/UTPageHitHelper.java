package com.ut.mini;

import android.util.Log;
import com.alibaba.mtl.log.c;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import com.ut.mini.base.UTMIVariables;
import com.alibaba.mtl.log.d.i;
import java.util.Iterator;
import java.util.List;
import android.net.Uri;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Map;

public class UTPageHitHelper
{
    private static UTPageHitHelper a;
    private Map<String, UTPageEventObject> A;
    private Map<String, String> B;
    private Map<Object, String> C;
    private boolean O;
    private Queue<UTPageEventObject> a;
    private String al;
    private String am;
    private Map<String, String> z;
    
    static {
        UTPageHitHelper.a = new UTPageHitHelper();
    }
    
    public UTPageHitHelper() {
        this.O = false;
        this.z = (Map<String, String>)new HashMap();
        this.A = (Map<String, UTPageEventObject>)new HashMap();
        this.al = null;
        this.B = (Map<String, String>)new HashMap();
        this.am = null;
        this.a = (Queue<UTPageEventObject>)new LinkedList();
        this.C = (Map<Object, String>)new HashMap();
    }
    
    private UTPageEventObject a(Object o) {
        synchronized (this) {
            final String a = this.a(o);
            if (this.A.containsKey((Object)a)) {
                return (UTPageEventObject)this.A.get((Object)a);
            }
            o = new UTPageEventObject();
            this.A.put((Object)a, o);
            ((UTPageEventObject)o).setCacheKey(a);
            return (UTPageEventObject)o;
        }
    }
    
    private static String a(final Uri uri) {
        if (uri != null) {
            final List queryParameters = uri.getQueryParameters("ttid");
            if (queryParameters != null) {
                for (final String s : queryParameters) {
                    if (!s.contains((CharSequence)"@")) {
                        if (s.contains((CharSequence)"%40")) {
                            continue;
                        }
                        return s;
                    }
                }
            }
        }
        return null;
    }
    
    private String a(Object o) {
        String simpleName;
        if (o instanceof String) {
            simpleName = (String)o;
        }
        else {
            simpleName = o.getClass().getSimpleName();
        }
        final int hashCode = o.hashCode();
        o = new StringBuilder();
        ((StringBuilder)o).append(simpleName);
        ((StringBuilder)o).append(hashCode);
        return ((StringBuilder)o).toString();
    }
    
    private void a(final String s, final UTPageEventObject utPageEventObject) {
        synchronized (this) {
            this.A.put((Object)s, (Object)utPageEventObject);
        }
    }
    
    private static String b(final Object o) {
        String s2;
        final String s = s2 = o.getClass().getSimpleName();
        if (s != null) {
            s2 = s;
            if (s.toLowerCase().endsWith("activity")) {
                s2 = s.substring(0, s.length() - 8);
            }
        }
        return s2;
    }
    
    private void b(final UTPageEventObject utPageEventObject) {
        synchronized (this) {
            if (this.A.containsKey((Object)utPageEventObject.getCacheKey())) {
                this.A.remove((Object)utPageEventObject.getCacheKey());
            }
        }
    }
    
    private void b(final Object o) {
        synchronized (this) {
            final String a = this.a(o);
            if (this.A.containsKey((Object)a)) {
                this.A.remove((Object)a);
            }
        }
    }
    
    public static UTPageHitHelper getInstance() {
        return UTPageHitHelper.a;
    }
    
    void a(UTPageEventObject utPageEventObject) {
        synchronized (this) {
            utPageEventObject.resetPropertiesWithoutSkipFlagAndH5Flag();
            if (!this.a.contains((Object)utPageEventObject)) {
                this.a.add((Object)utPageEventObject);
            }
            if (this.a.size() > 200) {
                for (int i = 0; i < 100; ++i) {
                    utPageEventObject = (UTPageEventObject)this.a.poll();
                    if (utPageEventObject != null && this.A.containsKey((Object)utPageEventObject.getCacheKey())) {
                        this.A.remove((Object)utPageEventObject.getCacheKey());
                    }
                }
            }
        }
    }
    
    void a(final Object o) {
        monitorenter(this);
        if (o != null) {
            try {
                final UTPageEventObject a = this.a(o);
                if (a.getPageStatus() != null) {
                    a.setH5Called();
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    void a(final Object o, String pageName, final boolean b) {
        monitorenter(this);
        Label_0384: {
            if (o == null) {
                break Label_0384;
            }
            try {
                final String a = this.a(o);
                if (a != null && a.equals((Object)this.al)) {
                    return;
                }
                if (this.al != null) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Last page requires leave(");
                    sb.append(this.al);
                    sb.append(").");
                    i.a("lost 2001", (Object)sb.toString());
                }
                final UTPageEventObject a2 = this.a(o);
                if (!b && a2.isSkipPage()) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("page name:");
                    sb2.append(o.getClass().getSimpleName());
                    i.a("skip page[pageAppear]", new String[] { sb2.toString() });
                    return;
                }
                final String h5Url = UTMIVariables.getInstance().getH5Url();
                if (h5Url != null) {
                    try {
                        this.z.put((Object)"spm", (Object)Uri.parse(h5Url).getQueryParameter("spm"));
                    }
                    finally {
                        final Throwable t;
                        t.printStackTrace();
                    }
                    UTMIVariables.getInstance().setH5Url(null);
                }
                final String b2 = b(o);
                if (TextUtils.isEmpty((CharSequence)pageName)) {
                    pageName = b2;
                }
                if (!TextUtils.isEmpty((CharSequence)a2.getPageName())) {
                    pageName = a2.getPageName();
                }
                a2.setPageName(this.am = pageName);
                a2.setPageStayTimstamp(SystemClock.elapsedRealtime());
                a2.setRefPage(UTMIVariables.getInstance().getRefPage());
                a2.setPageAppearCalled();
                if (this.B != null) {
                    final Map<String, String> pageProperties = a2.getPageProperties();
                    if (pageProperties == null) {
                        a2.setPageProperties(this.B);
                    }
                    else {
                        final HashMap pageProperties2 = new HashMap();
                        ((Map)pageProperties2).putAll((Map)pageProperties);
                        ((Map)pageProperties2).putAll((Map)this.B);
                        a2.setPageProperties((Map<String, String>)pageProperties2);
                    }
                }
                this.B = null;
                this.al = this.a(o);
                this.b(a2);
                this.a(this.a(o), a2);
                return;
                i.a("pageAppear", (Object)"The page object should not be null");
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    boolean a(final Object o) {
        monitorenter(this);
        if (o != null) {
            try {
                final UTPageEventObject a = this.a(o);
                if (a.getPageStatus() != null && a.getPageStatus() == UTPageStatus.UT_H5_IN_WebView) {
                    return true;
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
        return false;
    }
    
    Map<String, String> c() {
        synchronized (this) {
            if (this.B != null && this.B.size() > 0) {
                final HashMap hashMap = new HashMap();
                ((Map)hashMap).putAll((Map)this.B);
                this.B.clear();
                return (Map<String, String>)hashMap;
            }
            return null;
        }
    }
    
    public String getCurrentPageName() {
        return this.am;
    }
    
    @Deprecated
    public void pageAppear(final Object o) {
        synchronized (this) {
            this.a(o, null, false);
        }
    }
    
    void pageAppear(final Object o, final String s) {
        synchronized (this) {
            this.a(o, s, false);
        }
    }
    
    void pageAppearByAuto(final Activity activity) {
        if (this.O) {
            return;
        }
        this.pageAppear(activity);
    }
    
    @Deprecated
    public void pageDisAppear(final Object o) {
        monitorenter(this);
        Label_0812: {
            if (o == null) {
                break Label_0812;
            }
            try {
                if (this.al == null) {
                    return;
                }
                final UTPageEventObject a = this.a(o);
                if (a.isPageAppearCalled()) {
                    if (a.getPageStatus() != null && UTPageStatus.UT_H5_IN_WebView == a.getPageStatus() && a.isH5Called()) {
                        this.a(a);
                        return;
                    }
                    final long elapsedRealtime = SystemClock.elapsedRealtime();
                    final long pageStayTimstamp = a.getPageStayTimstamp();
                    if (a.getPageUrl() == null && o instanceof Activity && ((Activity)o).getIntent() != null) {
                        a.setPageUrl(((Activity)o).getIntent().getData());
                    }
                    final String pageName = a.getPageName();
                    final String refPage = a.getRefPage();
                    Object o2 = null;
                    Label_0164: {
                        if (refPage != null) {
                            o2 = refPage;
                            if (refPage.length() != 0) {
                                break Label_0164;
                            }
                        }
                        o2 = "-";
                    }
                    Object z;
                    if ((z = this.z) == null) {
                        z = new HashMap();
                    }
                    if (a.getPageProperties() != null) {
                        ((Map)z).putAll((Map)a.getPageProperties());
                    }
                    String refPage2 = pageName;
                    Object referPage = o2;
                    Map properties = (Map)z;
                    if (o instanceof IUTPageTrack) {
                        final IUTPageTrack iutPageTrack = (IUTPageTrack)o;
                        final String referPage2 = iutPageTrack.getReferPage();
                        if (!TextUtils.isEmpty((CharSequence)referPage2)) {
                            o2 = referPage2;
                        }
                        final Map<String, String> pageProperties = iutPageTrack.getPageProperties();
                        Map z2 = (Map)z;
                        if (pageProperties != null) {
                            z2 = (Map)z;
                            if (pageProperties.size() > 0) {
                                this.z.putAll((Map)pageProperties);
                                z2 = this.z;
                            }
                        }
                        final String pageName2 = iutPageTrack.getPageName();
                        refPage2 = pageName;
                        referPage = o2;
                        properties = z2;
                        if (!TextUtils.isEmpty((CharSequence)pageName2)) {
                            refPage2 = pageName2;
                            properties = z2;
                            referPage = o2;
                        }
                    }
                    final Uri pageUrl = a.getPageUrl();
                    if (pageUrl != null) {
                        try {
                            final HashMap hashMap = new HashMap();
                            final String queryParameter = pageUrl.getQueryParameter("spm");
                            final boolean empty = TextUtils.isEmpty((CharSequence)queryParameter);
                            Uri parse = pageUrl;
                            String queryParameter2 = queryParameter;
                            if (empty) {
                                parse = pageUrl;
                                try {
                                    final Uri uri = parse = Uri.parse(URLDecoder.decode(pageUrl.toString(), "UTF-8"));
                                    queryParameter2 = uri.getQueryParameter("spm");
                                    parse = uri;
                                }
                                catch (final UnsupportedEncodingException ex) {
                                    ex.printStackTrace();
                                    queryParameter2 = queryParameter;
                                }
                            }
                            if (!TextUtils.isEmpty((CharSequence)queryParameter2)) {
                                int n = 0;
                                if (this.C.containsKey(o)) {
                                    n = n;
                                    if (queryParameter2.equals(this.C.get(o))) {
                                        n = 1;
                                    }
                                }
                                if (n == 0) {
                                    ((Map)hashMap).put((Object)"spm", (Object)queryParameter2);
                                    this.C.put(o, (Object)queryParameter2);
                                }
                            }
                            final String queryParameter3 = parse.getQueryParameter("scm");
                            if (!TextUtils.isEmpty((CharSequence)queryParameter3)) {
                                ((Map)hashMap).put((Object)"scm", (Object)queryParameter3);
                            }
                            final String a2 = a(parse);
                            if (!TextUtils.isEmpty((CharSequence)a2)) {
                                c.a().e(a2);
                            }
                            if (((Map)hashMap).size() > 0) {
                                properties.putAll((Map)hashMap);
                            }
                        }
                        finally {
                            final Throwable t;
                            t.printStackTrace();
                        }
                    }
                    final UTHitBuilders$UTPageHitBuilder utHitBuilders$UTPageHitBuilder = new UTHitBuilders$UTPageHitBuilder(refPage2);
                    utHitBuilders$UTPageHitBuilder.setReferPage((String)referPage).setDurationOnPage(elapsedRealtime - pageStayTimstamp).setProperties(properties);
                    UTMIVariables.getInstance().setRefPage(refPage2);
                    final UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                    if (defaultTracker != null) {
                        defaultTracker.send((Map<String, String>)utHitBuilders$UTPageHitBuilder.build());
                    }
                    else {
                        i.a("Record page event error", (Object)"Fatal Error,must call setRequestAuthentication method first.");
                    }
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Please call pageAppear first(");
                    sb.append(b(o));
                    sb.append(").");
                    i.a("UT", (Object)sb.toString());
                }
                this.z = (Map<String, String>)new HashMap();
                if (a.isSkipPage()) {
                    this.a(a);
                }
                else if (a.getPageStatus() != null && UTPageStatus.UT_H5_IN_WebView == a.getPageStatus()) {
                    this.a(a);
                }
                else {
                    this.b(o);
                }
                this.al = null;
                this.am = null;
                return;
                i.a("pageDisAppear", (Object)"The page object should not be null");
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    void pageDisAppearByAuto(final Activity activity) {
        if (this.O) {
            return;
        }
        this.pageDisAppear(activity);
    }
    
    void skipPage(final Object o) {
        monitorenter(this);
        if (o == null) {
            monitorexit(this);
            return;
        }
        try {
            this.a(o).setToSkipPage();
        }
        finally {
            monitorexit(this);
        }
    }
    
    @Deprecated
    public void turnOffAutoPageTrack() {
        synchronized (this) {
            this.O = true;
        }
    }
    
    void updateNextPageProperties(final Map<String, String> map) {
        monitorenter(this);
        if (map != null) {
            try {
                final HashMap b = new HashMap();
                ((Map)b).putAll((Map)map);
                this.B = (Map<String, String>)b;
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    void updatePageName(final Object o, final String s) {
        monitorenter(this);
        if (o != null) {
            try {
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    this.a(o).setPageName(s);
                    this.am = s;
                    return;
                }
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    void updatePageProperties(final Object o, final Map<String, String> map) {
        monitorenter(this);
        Label_0099: {
            if (o == null || map == null) {
                break Label_0099;
            }
            try {
                if (map.size() == 0) {
                    i.a("updatePageProperties", (Object)"failed to update project, parameters should not be null and the map should not be empty");
                    return;
                }
                final HashMap pageProperties = new HashMap();
                ((Map)pageProperties).putAll((Map)map);
                final UTPageEventObject a = this.a(o);
                final Map<String, String> pageProperties2 = a.getPageProperties();
                if (pageProperties2 == null) {
                    a.setPageProperties((Map<String, String>)pageProperties);
                }
                else {
                    final HashMap pageProperties3 = new HashMap();
                    ((Map)pageProperties3).putAll((Map)pageProperties2);
                    ((Map)pageProperties3).putAll((Map)pageProperties);
                    a.setPageProperties((Map<String, String>)pageProperties3);
                }
            }
            finally {
                monitorexit(this);
            }
        }
    }
    
    @Deprecated
    public void updatePageProperties(final Map<String, String> map) {
        monitorenter(this);
        if (map != null) {
            try {
                this.z.putAll((Map)map);
            }
            finally {
                monitorexit(this);
            }
        }
        monitorexit(this);
    }
    
    void updatePageStatus(final Object o, final UTPageStatus pageStatus) {
        monitorenter(this);
        if (o != null) {
            if (pageStatus != null) {
                try {
                    this.a(o).setPageStatus(pageStatus);
                    return;
                }
                finally {
                    monitorexit(this);
                }
            }
        }
        monitorexit(this);
    }
    
    void updatePageUrl(final Object o, final Uri pageUrl) {
        monitorenter(this);
        if (o != null) {
            if (pageUrl != null) {
                try {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("url");
                    sb.append(pageUrl.toString());
                    Log.i("url", sb.toString());
                    this.a(o).setPageUrl(pageUrl);
                    return;
                }
                finally {
                    monitorexit(this);
                }
            }
        }
        monitorexit(this);
    }
    
    public static class UTPageEventObject
    {
        private long A;
        private boolean P;
        private boolean Q;
        private boolean R;
        private Uri a;
        private UTPageStatus a;
        private String an;
        private String ao;
        private String ap;
        private Map<String, String> z;
        
        public UTPageEventObject() {
            this.z = (Map<String, String>)new HashMap();
            this.A = 0L;
            this.a = null;
            this.an = null;
            this.ao = null;
            this.a = null;
            this.P = false;
            this.Q = false;
            this.R = false;
            this.ap = null;
        }
        
        public String getCacheKey() {
            return this.ap;
        }
        
        public String getPageName() {
            return this.an;
        }
        
        public Map<String, String> getPageProperties() {
            return this.z;
        }
        
        public UTPageStatus getPageStatus() {
            return this.a;
        }
        
        public long getPageStayTimstamp() {
            return this.A;
        }
        
        public Uri getPageUrl() {
            return this.a;
        }
        
        public String getRefPage() {
            return this.ao;
        }
        
        public boolean isH5Called() {
            return this.R;
        }
        
        public boolean isPageAppearCalled() {
            return this.P;
        }
        
        public boolean isSkipPage() {
            return this.Q;
        }
        
        public void resetPropertiesWithoutSkipFlagAndH5Flag() {
            this.z = (Map<String, String>)new HashMap();
            this.A = 0L;
            this.a = null;
            this.an = null;
            this.ao = null;
            final UTPageStatus a = this.a;
            if (a == null || a != UTPageStatus.UT_H5_IN_WebView) {
                this.a = null;
            }
            this.P = false;
            this.R = false;
        }
        
        public void setCacheKey(final String ap) {
            this.ap = ap;
        }
        
        public void setH5Called() {
            this.R = true;
        }
        
        public void setPageAppearCalled() {
            this.P = true;
        }
        
        public void setPageName(final String an) {
            this.an = an;
        }
        
        public void setPageProperties(final Map<String, String> z) {
            this.z = z;
        }
        
        public void setPageStatus(final UTPageStatus a) {
            this.a = a;
        }
        
        public void setPageStayTimstamp(final long a) {
            this.A = a;
        }
        
        public void setPageUrl(final Uri a) {
            this.a = a;
        }
        
        public void setRefPage(final String ao) {
            this.ao = ao;
        }
        
        public void setToSkipPage() {
            this.Q = true;
        }
    }
}
