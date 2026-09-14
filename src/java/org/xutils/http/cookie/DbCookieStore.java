package org.xutils.http.cookie;

import org.xutils.db.table.DbModel;
import java.util.Iterator;
import org.xutils.db.Selector;
import android.text.TextUtils;
import java.util.ArrayList;
import java.net.HttpCookie;
import java.util.List;
import java.net.URI;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.x;
import org.xutils.config.DbConfigs;
import org.xutils.common.task.PriorityExecutor;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import java.net.CookieStore;

public enum DbCookieStore implements CookieStore
{
    private static final DbCookieStore[] $VALUES;
    
    INSTANCE;
    
    private static final int LIMIT_COUNT = 5000;
    private static final long TRIM_TIME_SPAN = 1000L;
    private final DbManager db;
    private long lastTrimTime;
    private final Executor trimExecutor;
    
    private DbCookieStore() {
        this.trimExecutor = (Executor)new PriorityExecutor(1, true);
        this.lastTrimTime = 0L;
        final DbManager db = x.getDb(DbConfigs.COOKIE.getConfig());
        this.db = db;
        try {
            db.delete(CookieEntity.class, WhereBuilder.b("expiry", "=", -1L));
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
    }
    
    private URI getEffectiveURI(URI uri) {
        try {
            uri = new URI("http", uri.getHost(), uri.getPath(), (String)null, (String)null);
            return uri;
        }
        finally {
            return uri;
        }
    }
    
    private void trimSize() {
        this.trimExecutor.execute((Runnable)new Runnable(this) {
            final DbCookieStore this$0;
            
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.this$0.lastTrimTime < 1000L) {
                    return;
                }
                this.this$0.lastTrimTime = currentTimeMillis;
                try {
                    this.this$0.db.delete(CookieEntity.class, WhereBuilder.b("expiry", "<", System.currentTimeMillis()).and("expiry", "!=", -1L));
                }
                finally {
                    final Throwable t;
                    LogUtil.e(t.getMessage(), t);
                }
                try {
                    final int n = (int)this.this$0.db.selector(CookieEntity.class).count();
                    if (n > 5010) {
                        final java.util.List<CookieEntity> all = this.this$0.db.selector(CookieEntity.class).where("expiry", "!=", -1L).orderBy("expiry", false).limit(n - 5000).findAll();
                        if (all != null) {
                            this.this$0.db.delete(all);
                        }
                    }
                }
                finally {
                    final Throwable t2;
                    LogUtil.e(t2.getMessage(), t2);
                }
            }
        });
    }
    
    public void add(URI effectiveURI, final HttpCookie httpCookie) {
        if (httpCookie == null) {
            return;
        }
        effectiveURI = this.getEffectiveURI(effectiveURI);
        try {
            this.db.replace(new CookieEntity(effectiveURI, httpCookie));
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        this.trimSize();
    }
    
    public List<HttpCookie> get(final URI uri) {
        if (uri != null) {
            final URI effectiveURI = this.getEffectiveURI(uri);
            final ArrayList list = new ArrayList();
            try {
                final Selector<CookieEntity> selector = this.db.selector(CookieEntity.class);
                final WhereBuilder b = WhereBuilder.b();
                final String host = effectiveURI.getHost();
                if (!TextUtils.isEmpty((CharSequence)host)) {
                    final WhereBuilder b2 = WhereBuilder.b("domain", "=", host);
                    final StringBuilder sb = new StringBuilder();
                    sb.append(".");
                    sb.append(host);
                    final WhereBuilder or = b2.or("domain", "=", sb.toString());
                    final int index = host.indexOf(".");
                    final int lastIndex = host.lastIndexOf(".");
                    if (index > 0 && lastIndex > index) {
                        final String substring = host.substring(index, host.length());
                        if (!TextUtils.isEmpty((CharSequence)substring)) {
                            or.or("domain", "=", substring);
                        }
                    }
                    b.and(or);
                }
                String s = effectiveURI.getPath();
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    final WhereBuilder or2 = WhereBuilder.b("path", "=", s).or("path", "=", "/").or("path", "=", null);
                    for (int i = s.lastIndexOf("/"); i > 0; i = s.lastIndexOf("/")) {
                        s = s.substring(0, i);
                        or2.or("path", "=", s);
                    }
                    b.and(or2);
                }
                b.or("uri", "=", effectiveURI.toString());
                final java.util.List<CookieEntity> all = selector.where(b).findAll();
                if (all != null) {
                    for (final CookieEntity cookieEntity : all) {
                        if (!cookieEntity.isExpired()) {
                            ((List)list).add((Object)cookieEntity.toHttpCookie());
                        }
                    }
                }
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
            return (List<HttpCookie>)list;
        }
        throw new NullPointerException("uri is null");
    }
    
    public List<HttpCookie> getCookies() {
        final ArrayList list = new ArrayList();
        try {
            final java.util.List<CookieEntity> all = this.db.findAll(CookieEntity.class);
            if (all != null) {
                for (final CookieEntity cookieEntity : all) {
                    if (!cookieEntity.isExpired()) {
                        ((List)list).add((Object)cookieEntity.toHttpCookie());
                    }
                }
            }
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        return (List<HttpCookie>)list;
    }
    
    public List<URI> getURIs() {
        final ArrayList list = new ArrayList();
        try {
            final List<DbModel> all = this.db.selector(CookieEntity.class).select("uri").findAll();
            if (all != null) {
                final Iterator iterator = all.iterator();
                while (iterator.hasNext()) {
                    final String string = ((DbModel)iterator.next()).getString("uri");
                    if (!TextUtils.isEmpty((CharSequence)string)) {
                        try {
                            ((List)list).add((Object)new URI(string));
                        }
                        finally {
                            final Throwable t;
                            LogUtil.e(t.getMessage(), t);
                            try {
                                this.db.delete(CookieEntity.class, WhereBuilder.b("uri", "=", string));
                                continue;
                            }
                            finally {
                                final Throwable t2;
                                LogUtil.e(t2.getMessage(), t2);
                                continue;
                            }
                        }
                    }
                }
            }
        }
        finally {
            final Throwable t3;
            LogUtil.e(t3.getMessage(), t3);
        }
        return (List<URI>)list;
    }
    
    public boolean remove(final URI uri, final HttpCookie httpCookie) {
        if (httpCookie == null) {
            return true;
        }
        boolean b2 = false;
        try {
            final WhereBuilder b = WhereBuilder.b("name", "=", httpCookie.getName());
            final String domain = httpCookie.getDomain();
            if (!TextUtils.isEmpty((CharSequence)domain)) {
                b.and("domain", "=", domain);
            }
            final String path = httpCookie.getPath();
            if (!TextUtils.isEmpty((CharSequence)path)) {
                String substring = path;
                if (path.length() > 1) {
                    substring = path;
                    if (path.endsWith("/")) {
                        substring = path.substring(0, path.length() - 1);
                    }
                }
                b.and("path", "=", substring);
            }
            this.db.delete(CookieEntity.class, b);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            b2 = false;
        }
        return b2;
    }
    
    public boolean removeAll() {
        try {
            this.db.delete(CookieEntity.class);
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
        }
        return true;
    }
}
