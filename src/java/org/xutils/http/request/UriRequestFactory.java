package org.xutils.http.request;

import android.text.TextUtils;
import java.lang.reflect.Type;
import org.xutils.http.RequestParams;
import org.xutils.common.util.LogUtil;
import org.xutils.http.app.RequestTracker;
import java.util.HashMap;

public final class UriRequestFactory
{
    private static final HashMap<String, Class<? extends UriRequest>> SCHEME_CLS_MAP;
    private static Class<? extends RequestTracker> defaultTrackerCls;
    
    static {
        SCHEME_CLS_MAP = new HashMap();
    }
    
    private UriRequestFactory() {
    }
    
    public static RequestTracker getDefaultTracker() {
        RequestTracker requestTracker = null;
        try {
            if (UriRequestFactory.defaultTrackerCls != null) {
                requestTracker = (RequestTracker)UriRequestFactory.defaultTrackerCls.newInstance();
            }
            return requestTracker;
        }
        finally {
            final Throwable t;
            LogUtil.e(t.getMessage(), t);
            return null;
        }
    }
    
    public static UriRequest getUriRequest(final RequestParams requestParams, final Type type) throws Throwable {
        final String uri = requestParams.getUri();
        final int index = uri.indexOf(":");
        String substring;
        if (index > 0) {
            substring = uri.substring(0, index);
        }
        else if (uri.startsWith("/")) {
            substring = "file";
        }
        else {
            substring = null;
        }
        if (TextUtils.isEmpty((CharSequence)substring)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("The url not be support: ");
            sb.append(uri);
            throw new IllegalArgumentException(sb.toString());
        }
        final Class clazz = (Class)UriRequestFactory.SCHEME_CLS_MAP.get((Object)substring);
        if (clazz != null) {
            return (UriRequest)clazz.getConstructor(RequestParams.class, Class.class).newInstance(new Object[] { requestParams, type });
        }
        if (substring.startsWith("http")) {
            return new HttpRequest(requestParams, type);
        }
        if (substring.equals((Object)"assets")) {
            return new AssetsRequest(requestParams, type);
        }
        if (substring.equals((Object)"file")) {
            return new LocalFileRequest(requestParams, type);
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("The url not be support: ");
        sb2.append(uri);
        throw new IllegalArgumentException(sb2.toString());
    }
    
    public static void registerDefaultTrackerClass(final Class<? extends RequestTracker> defaultTrackerCls) {
        UriRequestFactory.defaultTrackerCls = defaultTrackerCls;
    }
    
    public static void registerRequestClass(final String s, final Class<? extends UriRequest> clazz) {
        UriRequestFactory.SCHEME_CLS_MAP.put((Object)s, (Object)clazz);
    }
}
