package org.xutils.http.cookie;

import android.text.TextUtils;
import java.net.HttpCookie;
import java.net.URI;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "cookie", onCreated = "CREATE UNIQUE INDEX index_cookie_unique ON cookie(\"name\",\"domain\",\"path\")")
final class CookieEntity
{
    private static final long MAX_EXPIRY;
    @Column(name = "comment")
    private String comment;
    @Column(name = "commentURL")
    private String commentURL;
    @Column(name = "discard")
    private boolean discard;
    @Column(name = "domain")
    private String domain;
    @Column(name = "expiry")
    private long expiry;
    @Column(isId = true, name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
    @Column(name = "portList")
    private String portList;
    @Column(name = "secure")
    private boolean secure;
    @Column(name = "uri")
    private String uri;
    @Column(name = "value")
    private String value;
    @Column(name = "version")
    private int version;
    
    static {
        MAX_EXPIRY = System.currentTimeMillis() + 3110400000000L;
    }
    
    public CookieEntity() {
        this.expiry = CookieEntity.MAX_EXPIRY;
        this.version = 1;
    }
    
    public CookieEntity(final URI uri, final HttpCookie httpCookie) {
        this.expiry = CookieEntity.MAX_EXPIRY;
        this.version = 1;
        String string;
        if (uri == null) {
            string = null;
        }
        else {
            string = uri.toString();
        }
        this.uri = string;
        this.name = httpCookie.getName();
        this.value = httpCookie.getValue();
        this.comment = httpCookie.getComment();
        this.commentURL = httpCookie.getCommentURL();
        this.discard = httpCookie.getDiscard();
        this.domain = httpCookie.getDomain();
        final long maxAge = httpCookie.getMaxAge();
        if (maxAge != -1L && maxAge > 0L) {
            final long expiry = maxAge * 1000L + System.currentTimeMillis();
            this.expiry = expiry;
            if (expiry < 0L) {
                this.expiry = CookieEntity.MAX_EXPIRY;
            }
        }
        else {
            this.expiry = -1L;
        }
        final String path = httpCookie.getPath();
        this.path = path;
        if (!TextUtils.isEmpty((CharSequence)path) && this.path.length() > 1 && this.path.endsWith("/")) {
            final String path2 = this.path;
            this.path = path2.substring(0, path2.length() - 1);
        }
        this.portList = httpCookie.getPortlist();
        this.secure = httpCookie.getSecure();
        this.version = httpCookie.getVersion();
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public boolean isExpired() {
        final long expiry = this.expiry;
        return expiry != -1L && expiry < System.currentTimeMillis();
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setUri(final String uri) {
        this.uri = uri;
    }
    
    public HttpCookie toHttpCookie() {
        final HttpCookie httpCookie = new HttpCookie(this.name, this.value);
        httpCookie.setComment(this.comment);
        httpCookie.setCommentURL(this.commentURL);
        httpCookie.setDiscard(this.discard);
        httpCookie.setDomain(this.domain);
        final long expiry = this.expiry;
        if (expiry == -1L) {
            httpCookie.setMaxAge(-1L);
        }
        else {
            httpCookie.setMaxAge((expiry - System.currentTimeMillis()) / 1000L);
        }
        httpCookie.setPath(this.path);
        httpCookie.setPortlist(this.portList);
        httpCookie.setSecure(this.secure);
        httpCookie.setVersion(this.version);
        return httpCookie;
    }
}
