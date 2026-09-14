package org.xutils.image;

final class MemCacheKey
{
    public final ImageOptions options;
    public final String url;
    
    public MemCacheKey(final String url, final ImageOptions options) {
        this.url = url;
        this.options = options;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final MemCacheKey memCacheKey = (MemCacheKey)o;
            return this.url.equals((Object)memCacheKey.url) && this.options.equals(memCacheKey.options);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.url.hashCode() * 31 + this.options.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.url);
        sb.append(this.options.toString());
        return sb.toString();
    }
}
