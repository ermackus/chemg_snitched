package org.xutils.cache;

import java.util.Date;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "disk_cache")
public final class DiskCacheEntity
{
    @Column(name = "etag")
    private String etag;
    @Column(name = "expires")
    private long expires;
    @Column(name = "hits")
    private long hits;
    @Column(isId = true, name = "id")
    private long id;
    @Column(name = "key", property = "UNIQUE")
    private String key;
    @Column(name = "lastAccess")
    private long lastAccess;
    @Column(name = "lastModify")
    private Date lastModify;
    @Column(name = "path")
    private String path;
    @Column(name = "textContent")
    private String textContent;
    
    public DiskCacheEntity() {
        this.expires = Long.MAX_VALUE;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public long getExpires() {
        return this.expires;
    }
    
    public long getHits() {
        return this.hits;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public long getLastAccess() {
        long n;
        if ((n = this.lastAccess) == 0L) {
            n = System.currentTimeMillis();
        }
        return n;
    }
    
    public Date getLastModify() {
        return this.lastModify;
    }
    
    String getPath() {
        return this.path;
    }
    
    public String getTextContent() {
        return this.textContent;
    }
    
    public void setEtag(final String etag) {
        this.etag = etag;
    }
    
    public void setExpires(final long expires) {
        this.expires = expires;
    }
    
    public void setHits(final long hits) {
        this.hits = hits;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setLastAccess(final long lastAccess) {
        this.lastAccess = lastAccess;
    }
    
    public void setLastModify(final Date lastModify) {
        this.lastModify = lastModify;
    }
    
    void setPath(final String path) {
        this.path = path;
    }
    
    public void setTextContent(final String textContent) {
        this.textContent = textContent;
    }
}
