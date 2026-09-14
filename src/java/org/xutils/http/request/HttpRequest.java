package org.xutils.http.request;

import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.HttpMethod;
import org.xutils.cache.LruDiskCache;
import java.net.URLDecoder;
import java.util.Map;
import java.net.URL;
import java.util.StringTokenizer;
import org.xutils.common.util.LogUtil;
import java.io.IOException;
import java.io.Closeable;
import org.xutils.common.util.IOUtil;
import java.util.Iterator;
import java.util.List;
import android.net.Uri;
import android.text.TextUtils;
import org.xutils.common.util.KeyValue;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.lang.reflect.Type;
import org.xutils.http.RequestParams;
import java.net.CookieStore;
import java.net.CookiePolicy;
import org.xutils.http.cookie.DbCookieStore;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.CookieManager;

public class HttpRequest extends UriRequest
{
    private static final CookieManager COOKIE_MANAGER;
    private String cacheKey;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private boolean isLoading;
    private int responseCode;
    
    static {
        COOKIE_MANAGER = new CookieManager((CookieStore)DbCookieStore.INSTANCE, CookiePolicy.ACCEPT_ALL);
    }
    
    HttpRequest(final RequestParams requestParams, final Type type) throws Throwable {
        super(requestParams, type);
        this.cacheKey = null;
        this.isLoading = false;
        this.inputStream = null;
        this.connection = null;
        this.responseCode = 0;
    }
    
    private static String toGMTString(final Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        final TimeZone timeZone = TimeZone.getTimeZone("GMT");
        simpleDateFormat.setTimeZone(timeZone);
        new GregorianCalendar(timeZone).setTimeInMillis(date.getTime());
        return simpleDateFormat.format(date);
    }
    
    @Override
    protected String buildQueryUrl(final RequestParams requestParams) {
        final String uri = requestParams.getUri();
        final StringBuilder sb = new StringBuilder(uri);
        if (!uri.contains((CharSequence)"?")) {
            sb.append("?");
        }
        else if (!uri.endsWith("?")) {
            sb.append("&");
        }
        final List queryStringParams = requestParams.getQueryStringParams();
        if (queryStringParams != null) {
            for (final KeyValue keyValue : queryStringParams) {
                final String key = keyValue.key;
                final String valueStr = keyValue.getValueStr();
                if (!TextUtils.isEmpty((CharSequence)key) && valueStr != null) {
                    sb.append(Uri.encode(key, requestParams.getCharset()));
                    sb.append("=");
                    sb.append(Uri.encode(valueStr, requestParams.getCharset()));
                    sb.append("&");
                }
            }
        }
        if (sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.charAt(sb.length() - 1) == '?') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    @Override
    public void clearCacheHeader() {
        this.params.setHeader("If-Modified-Since", null);
        this.params.setHeader("If-None-Match", null);
    }
    
    @Override
    public void close() throws IOException {
        final InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            IOUtil.closeQuietly((Closeable)inputStream);
            this.inputStream = null;
        }
        final HttpURLConnection connection = this.connection;
        if (connection != null) {
            connection.disconnect();
        }
    }
    
    @Override
    public String getCacheKey() {
        if (this.cacheKey == null) {
            final String cacheKey = this.params.getCacheKey();
            this.cacheKey = cacheKey;
            if (TextUtils.isEmpty((CharSequence)cacheKey)) {
                this.cacheKey = this.params.toString();
            }
        }
        return this.cacheKey;
    }
    
    @Override
    public long getContentLength() {
        final HttpURLConnection connection = this.connection;
        long n = 0L;
        Label_0057: {
            if (connection == null) {
                break Label_0057;
            }
            try {
                n = connection.getContentLength();
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
            long n2 = n;
            if (n >= 1L) {
                return n2;
            }
            try {
                int n3 = this.getInputStream().available();
                n2 = n3;
                return n2;
                n3 = this.getInputStream().available();
                return n3;
            }
            finally {
                n2 = n;
                return n2;
            }
        }
    }
    
    @Override
    public String getETag() {
        final HttpURLConnection connection = this.connection;
        if (connection == null) {
            return null;
        }
        return connection.getHeaderField("ETag");
    }
    
    @Override
    public long getExpiration() {
        final HttpURLConnection connection = this.connection;
        final long n = -1L;
        if (connection == null) {
            return -1L;
        }
        final String headerField = connection.getHeaderField("Cache-Control");
        long n2 = n;
        Label_0161: {
            if (!TextUtils.isEmpty((CharSequence)headerField)) {
                final StringTokenizer stringTokenizer = new StringTokenizer(headerField, ",");
                String lowerCase;
                do {
                    n2 = n;
                    if (!stringTokenizer.hasMoreTokens()) {
                        break Label_0161;
                    }
                    lowerCase = stringTokenizer.nextToken().trim().toLowerCase();
                } while (!lowerCase.startsWith("max-age"));
                final int index = lowerCase.indexOf(61);
                n2 = n;
                if (index > 0) {
                    try {
                        final long long1 = Long.parseLong(lowerCase.substring(index + 1).trim());
                        if (long1 > 0L) {
                            final long n3 = System.currentTimeMillis() + long1 * 1000L;
                        }
                    }
                    finally {
                        final Throwable t;
                        LogUtil.e(t.getMessage(), t);
                        n2 = n;
                    }
                }
            }
        }
        long expiration = n2;
        if (n2 <= 0L) {
            expiration = this.connection.getExpiration();
        }
        long n4 = expiration;
        if (expiration <= 0L) {
            n4 = expiration;
            if (this.params.getCacheMaxAge() > 0L) {
                n4 = System.currentTimeMillis() + this.params.getCacheMaxAge();
            }
        }
        long n5 = n4;
        if (n4 <= 0L) {
            n5 = Long.MAX_VALUE;
        }
        return n5;
    }
    
    @Override
    public long getHeaderFieldDate(final String s, final long n) {
        final HttpURLConnection connection = this.connection;
        if (connection == null) {
            return n;
        }
        return connection.getHeaderFieldDate(s, n);
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        final HttpURLConnection connection = this.connection;
        if (connection != null && this.inputStream == null) {
            InputStream inputStream;
            if (connection.getResponseCode() >= 400) {
                inputStream = this.connection.getErrorStream();
            }
            else {
                inputStream = this.connection.getInputStream();
            }
            this.inputStream = inputStream;
        }
        return this.inputStream;
    }
    
    @Override
    public long getLastModified() {
        return this.getHeaderFieldDate("Last-Modified", System.currentTimeMillis());
    }
    
    @Override
    public String getRequestUri() {
        final String queryUrl = this.queryUrl;
        final HttpURLConnection connection = this.connection;
        String string = queryUrl;
        if (connection != null) {
            final URL url = connection.getURL();
            string = queryUrl;
            if (url != null) {
                string = url.toString();
            }
        }
        return string;
    }
    
    @Override
    public int getResponseCode() throws IOException {
        if (this.connection != null) {
            return this.responseCode;
        }
        if (this.getInputStream() != null) {
            return 200;
        }
        return 404;
    }
    
    @Override
    public String getResponseHeader(final String s) {
        final HttpURLConnection connection = this.connection;
        if (connection == null) {
            return null;
        }
        return connection.getHeaderField(s);
    }
    
    @Override
    public Map<String, List<String>> getResponseHeaders() {
        final HttpURLConnection connection = this.connection;
        if (connection == null) {
            return null;
        }
        return (Map<String, List<String>>)connection.getHeaderFields();
    }
    
    @Override
    public String getResponseMessage() throws IOException {
        final HttpURLConnection connection = this.connection;
        if (connection != null) {
            return URLDecoder.decode(connection.getResponseMessage(), this.params.getCharset());
        }
        return null;
    }
    
    @Override
    public boolean isLoading() {
        return this.isLoading;
    }
    
    @Override
    public Object loadResult() throws Throwable {
        this.isLoading = true;
        return super.loadResult();
    }
    
    @Override
    public Object loadResultFromCache() throws Throwable {
        this.isLoading = true;
        final DiskCacheEntity value = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(this.getCacheKey());
        if (value != null) {
            if (HttpMethod.permitsCache(this.params.getMethod())) {
                final Date lastModify = value.getLastModify();
                if (lastModify.getTime() > 0L) {
                    this.params.setHeader("If-Modified-Since", toGMTString(lastModify));
                }
                final String etag = value.getEtag();
                if (!TextUtils.isEmpty((CharSequence)etag)) {
                    this.params.setHeader("If-None-Match", etag);
                }
            }
            return this.loader.loadFromCache(value);
        }
        return null;
    }
    
    @Override
    public void sendRequest() throws Throwable {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_0       
        //     2: putfield        org/xutils/http/request/HttpRequest.isLoading:Z
        //     5: aload_0        
        //     6: iconst_0       
        //     7: putfield        org/xutils/http/request/HttpRequest.responseCode:I
        //    10: new             Ljava/net/URL;
        //    13: dup            
        //    14: aload_0        
        //    15: getfield        org/xutils/http/request/HttpRequest.queryUrl:Ljava/lang/String;
        //    18: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    21: astore          5
        //    23: aload_0        
        //    24: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //    27: invokevirtual   org/xutils/http/RequestParams.getProxy:()Ljava/net/Proxy;
        //    30: astore          6
        //    32: aload           6
        //    34: ifnull          54
        //    37: aload_0        
        //    38: aload           5
        //    40: aload           6
        //    42: invokevirtual   java/net/URL.openConnection:(Ljava/net/Proxy;)Ljava/net/URLConnection;
        //    45: checkcast       Ljava/net/HttpURLConnection;
        //    48: putfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //    51: goto            66
        //    54: aload_0        
        //    55: aload           5
        //    57: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    60: checkcast       Ljava/net/HttpURLConnection;
        //    63: putfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //    66: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    69: bipush          19
        //    71: if_icmpge       87
        //    74: aload_0        
        //    75: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //    78: ldc_w           "Connection"
        //    81: ldc_w           "close"
        //    84: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    87: aload_0        
        //    88: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //    91: aload_0        
        //    92: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //    95: invokevirtual   org/xutils/http/RequestParams.getReadTimeout:()I
        //    98: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   101: aload_0        
        //   102: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   105: aload_0        
        //   106: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   109: invokevirtual   org/xutils/http/RequestParams.getConnectTimeout:()I
        //   112: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   115: aload_0        
        //   116: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   119: astore          6
        //   121: aload_0        
        //   122: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   125: invokevirtual   org/xutils/http/RequestParams.getRedirectHandler:()Lorg/xutils/http/app/RedirectHandler;
        //   128: ifnonnull       136
        //   131: iconst_1       
        //   132: istore_2       
        //   133: goto            138
        //   136: iconst_0       
        //   137: istore_2       
        //   138: aload           6
        //   140: iload_2        
        //   141: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //   144: aload_0        
        //   145: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   148: instanceof      Ljavax/net/ssl/HttpsURLConnection;
        //   151: ifeq            180
        //   154: aload_0        
        //   155: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   158: invokevirtual   org/xutils/http/RequestParams.getSslSocketFactory:()Ljavax/net/ssl/SSLSocketFactory;
        //   161: astore          6
        //   163: aload           6
        //   165: ifnull          180
        //   168: aload_0        
        //   169: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   172: checkcast       Ljavax/net/ssl/HttpsURLConnection;
        //   175: aload           6
        //   177: invokevirtual   javax/net/ssl/HttpsURLConnection.setSSLSocketFactory:(Ljavax/net/ssl/SSLSocketFactory;)V
        //   180: aload_0        
        //   181: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   184: invokevirtual   org/xutils/http/RequestParams.isUseCookie:()Z
        //   187: ifeq            273
        //   190: getstatic       org/xutils/http/request/HttpRequest.COOKIE_MANAGER:Ljava/net/CookieManager;
        //   193: astore          8
        //   195: aload           5
        //   197: invokevirtual   java/net/URL.toURI:()Ljava/net/URI;
        //   200: astore          6
        //   202: new             Ljava/util/HashMap;
        //   205: astore          7
        //   207: aload           7
        //   209: iconst_0       
        //   210: invokespecial   java/util/HashMap.<init>:(I)V
        //   213: aload           8
        //   215: aload           6
        //   217: aload           7
        //   219: invokevirtual   java/net/CookieManager.get:(Ljava/net/URI;Ljava/util/Map;)Ljava/util/Map;
        //   222: ldc_w           "Cookie"
        //   225: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   230: checkcast       Ljava/util/List;
        //   233: astore          6
        //   235: aload           6
        //   237: ifnull          273
        //   240: aload_0        
        //   241: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   244: ldc_w           "Cookie"
        //   247: ldc_w           ";"
        //   250: aload           6
        //   252: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   255: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   258: goto            273
        //   261: astore          6
        //   263: aload           6
        //   265: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   268: aload           6
        //   270: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   273: aload_0        
        //   274: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   277: invokevirtual   org/xutils/http/RequestParams.getHeaders:()Ljava/util/List;
        //   280: astore          6
        //   282: aload           6
        //   284: ifnull          384
        //   287: aload           6
        //   289: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   294: astore          8
        //   296: aload           8
        //   298: invokeinterface java/util/Iterator.hasNext:()Z
        //   303: ifeq            384
        //   306: aload           8
        //   308: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   313: checkcast       Lorg/xutils/http/BaseParams$Header;
        //   316: astore          6
        //   318: aload           6
        //   320: getfield        org/xutils/http/BaseParams$Header.key:Ljava/lang/String;
        //   323: astore          7
        //   325: aload           6
        //   327: invokevirtual   org/xutils/http/BaseParams$Header.getValueStr:()Ljava/lang/String;
        //   330: astore          9
        //   332: aload           7
        //   334: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   337: ifne            296
        //   340: aload           9
        //   342: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   345: ifne            296
        //   348: aload           6
        //   350: getfield        org/xutils/http/BaseParams$Header.setHeader:Z
        //   353: ifeq            370
        //   356: aload_0        
        //   357: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   360: aload           7
        //   362: aload           9
        //   364: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   367: goto            296
        //   370: aload_0        
        //   371: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   374: aload           7
        //   376: aload           9
        //   378: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   381: goto            296
        //   384: aload_0        
        //   385: getfield        org/xutils/http/request/HttpRequest.requestInterceptListener:Lorg/xutils/http/app/RequestInterceptListener;
        //   388: ifnull          401
        //   391: aload_0        
        //   392: getfield        org/xutils/http/request/HttpRequest.requestInterceptListener:Lorg/xutils/http/app/RequestInterceptListener;
        //   395: aload_0        
        //   396: invokeinterface org/xutils/http/app/RequestInterceptListener.beforeRequest:(Lorg/xutils/http/request/UriRequest;)V
        //   401: aload_0        
        //   402: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   405: invokevirtual   org/xutils/http/RequestParams.getMethod:()Lorg/xutils/http/HttpMethod;
        //   408: astore          8
        //   410: aload_0        
        //   411: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   414: aload           8
        //   416: invokevirtual   org/xutils/http/HttpMethod.toString:()Ljava/lang/String;
        //   419: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   422: goto            457
        //   425: astore          6
        //   427: ldc             Ljava/net/HttpURLConnection;.class
        //   429: ldc_w           "method"
        //   432: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //   435: astore          7
        //   437: aload           7
        //   439: iconst_1       
        //   440: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //   443: aload           7
        //   445: aload_0        
        //   446: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   449: aload           8
        //   451: invokevirtual   org/xutils/http/HttpMethod.toString:()Ljava/lang/String;
        //   454: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   457: aload           8
        //   459: invokestatic    org/xutils/http/HttpMethod.permitsRequestBody:(Lorg/xutils/http/HttpMethod;)Z
        //   462: ifeq            642
        //   465: aload_0        
        //   466: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   469: invokevirtual   org/xutils/http/RequestParams.getRequestBody:()Lorg/xutils/http/body/RequestBody;
        //   472: astore          7
        //   474: aload           7
        //   476: ifnull          642
        //   479: aload           7
        //   481: instanceof      Lorg/xutils/http/body/ProgressBody;
        //   484: ifeq            501
        //   487: aload           7
        //   489: checkcast       Lorg/xutils/http/body/ProgressBody;
        //   492: aload_0        
        //   493: getfield        org/xutils/http/request/HttpRequest.progressHandler:Lorg/xutils/http/ProgressHandler;
        //   496: invokeinterface org/xutils/http/body/ProgressBody.setProgressHandler:(Lorg/xutils/http/ProgressHandler;)V
        //   501: aload           7
        //   503: invokeinterface org/xutils/http/body/RequestBody.getContentType:()Ljava/lang/String;
        //   508: astore          6
        //   510: aload           6
        //   512: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   515: ifne            530
        //   518: aload_0        
        //   519: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   522: ldc_w           "Content-Type"
        //   525: aload           6
        //   527: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   530: aload           7
        //   532: invokeinterface org/xutils/http/body/RequestBody.getContentLength:()J
        //   537: lstore_3       
        //   538: lload_3        
        //   539: lconst_0       
        //   540: lcmp           
        //   541: ifge            557
        //   544: aload_0        
        //   545: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   548: ldc_w           262144
        //   551: invokevirtual   java/net/HttpURLConnection.setChunkedStreamingMode:(I)V
        //   554: goto            606
        //   557: lload_3        
        //   558: ldc2_w          2147483647
        //   561: lcmp           
        //   562: ifge            577
        //   565: aload_0        
        //   566: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   569: lload_3        
        //   570: l2i            
        //   571: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   574: goto            606
        //   577: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   580: bipush          19
        //   582: if_icmplt       596
        //   585: aload_0        
        //   586: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   589: lload_3        
        //   590: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(J)V
        //   593: goto            606
        //   596: aload_0        
        //   597: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   600: ldc_w           262144
        //   603: invokevirtual   java/net/HttpURLConnection.setChunkedStreamingMode:(I)V
        //   606: aload_0        
        //   607: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   610: ldc_w           "Content-Length"
        //   613: lload_3        
        //   614: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   617: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   620: aload_0        
        //   621: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   624: iconst_1       
        //   625: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   628: aload           7
        //   630: aload_0        
        //   631: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   634: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   637: invokeinterface org/xutils/http/body/RequestBody.writeTo:(Ljava/io/OutputStream;)V
        //   642: aload_0        
        //   643: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   646: invokevirtual   org/xutils/http/RequestParams.isUseCookie:()Z
        //   649: ifeq            694
        //   652: aload_0        
        //   653: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   656: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   659: astore          6
        //   661: aload           6
        //   663: ifnull          694
        //   666: getstatic       org/xutils/http/request/HttpRequest.COOKIE_MANAGER:Ljava/net/CookieManager;
        //   669: aload           5
        //   671: invokevirtual   java/net/URL.toURI:()Ljava/net/URI;
        //   674: aload           6
        //   676: invokevirtual   java/net/CookieManager.put:(Ljava/net/URI;Ljava/util/Map;)V
        //   679: goto            694
        //   682: astore          5
        //   684: aload           5
        //   686: invokevirtual   java/lang/Throwable.getMessage:()Ljava/lang/String;
        //   689: aload           5
        //   691: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   694: aload_0        
        //   695: aload_0        
        //   696: getfield        org/xutils/http/request/HttpRequest.connection:Ljava/net/HttpURLConnection;
        //   699: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   702: putfield        org/xutils/http/request/HttpRequest.responseCode:I
        //   705: aload_0        
        //   706: getfield        org/xutils/http/request/HttpRequest.requestInterceptListener:Lorg/xutils/http/app/RequestInterceptListener;
        //   709: ifnull          722
        //   712: aload_0        
        //   713: getfield        org/xutils/http/request/HttpRequest.requestInterceptListener:Lorg/xutils/http/app/RequestInterceptListener;
        //   716: aload_0        
        //   717: invokeinterface org/xutils/http/app/RequestInterceptListener.afterRequest:(Lorg/xutils/http/request/UriRequest;)V
        //   722: aload_0        
        //   723: getfield        org/xutils/http/request/HttpRequest.responseCode:I
        //   726: istore_1       
        //   727: iload_1        
        //   728: sipush          204
        //   731: if_icmpeq       840
        //   734: iload_1        
        //   735: sipush          205
        //   738: if_icmpeq       840
        //   741: iload_1        
        //   742: sipush          300
        //   745: if_icmpge       754
        //   748: aload_0        
        //   749: iconst_1       
        //   750: putfield        org/xutils/http/request/HttpRequest.isLoading:Z
        //   753: return         
        //   754: new             Lorg/xutils/ex/HttpException;
        //   757: dup            
        //   758: aload_0        
        //   759: getfield        org/xutils/http/request/HttpRequest.responseCode:I
        //   762: aload_0        
        //   763: invokevirtual   org/xutils/http/request/HttpRequest.getResponseMessage:()Ljava/lang/String;
        //   766: invokespecial   org/xutils/ex/HttpException.<init>:(ILjava/lang/String;)V
        //   769: astore          5
        //   771: aload           5
        //   773: aload_0        
        //   774: invokevirtual   org/xutils/http/request/HttpRequest.getInputStream:()Ljava/io/InputStream;
        //   777: aload_0        
        //   778: getfield        org/xutils/http/request/HttpRequest.params:Lorg/xutils/http/RequestParams;
        //   781: invokevirtual   org/xutils/http/RequestParams.getCharset:()Ljava/lang/String;
        //   784: invokestatic    org/xutils/common/util/IOUtil.readStr:(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;
        //   787: invokevirtual   org/xutils/ex/HttpException.setResult:(Ljava/lang/String;)V
        //   790: new             Ljava/lang/StringBuilder;
        //   793: dup            
        //   794: invokespecial   java/lang/StringBuilder.<init>:()V
        //   797: astore          6
        //   799: aload           6
        //   801: aload           5
        //   803: invokevirtual   org/xutils/ex/HttpException.toString:()Ljava/lang/String;
        //   806: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   809: pop            
        //   810: aload           6
        //   812: ldc_w           ", url: "
        //   815: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   818: pop            
        //   819: aload           6
        //   821: aload_0        
        //   822: getfield        org/xutils/http/request/HttpRequest.queryUrl:Ljava/lang/String;
        //   825: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   828: pop            
        //   829: aload           6
        //   831: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   834: invokestatic    org/xutils/common/util/LogUtil.e:(Ljava/lang/String;)V
        //   837: aload           5
        //   839: athrow         
        //   840: new             Lorg/xutils/ex/HttpException;
        //   843: dup            
        //   844: aload_0        
        //   845: getfield        org/xutils/http/request/HttpRequest.responseCode:I
        //   848: aload_0        
        //   849: invokevirtual   org/xutils/http/request/HttpRequest.getResponseMessage:()Ljava/lang/String;
        //   852: invokespecial   org/xutils/ex/HttpException.<init>:(ILjava/lang/String;)V
        //   855: athrow         
        //   856: astore          5
        //   858: aload           6
        //   860: athrow         
        //   861: astore          6
        //   863: goto            790
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  190    235    261    273    Any
        //  240    258    261    273    Any
        //  410    422    425    457    Ljava/net/ProtocolException;
        //  427    457    856    861    Any
        //  652    661    682    694    Any
        //  666    679    682    694    Any
        //  771    790    861    866    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0790:
        //     at q5.p.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:150)
        //     at q5.p.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:470)
        //     at u5.m.d(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:30)
        //     at u5.i.g(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:23)
        //     at u5.i.f(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:159)
        //     at u5.i.j(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:619)
        //     at u5.i.k(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:13)
        //     at u5.i.i(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:29)
        //     at s5.b.a(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:90)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.decompileWithProcyon(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:367)
        //     at com.thesourceofcode.jadec.decompilers.JavaExtractionWorker.doWork(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:162)
        //     at com.thesourceofcode.jadec.decompilers.BaseDecompiler.withAttempt(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:3)
        //     at z6.a.run(r8-map-id-5336d296fbf3284427aba3c9406dc63d81d5d24d9edcf157bc560c004a742559:31)
        //     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        //     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        //     at java.lang.Thread.run(Thread.java:920)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
