package org.xutils.http;

import org.xutils.common.Callback$CancelledException;
import org.xutils.common.task.Priority;
import org.xutils.x;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.common.Callback;
import org.xutils.common.util.IOUtil;
import java.io.Closeable;
import java.util.Iterator;
import java.util.Map$Entry;
import android.text.TextUtils;
import java.io.File;
import org.xutils.http.request.UriRequestFactory;
import org.xutils.common.Callback$Cancelable;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.request.UriRequest;
import org.xutils.common.Callback$ProgressCallback;
import org.xutils.common.Callback$PrepareCallback;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import org.xutils.common.Callback$CommonCallback;
import org.xutils.common.Callback$CacheCallback;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.task.AbsTask;

public class HttpTask<ResultType> extends AbsTask<ResultType> implements ProgressHandler
{
    static final boolean $assertionsDisabled = false;
    private static final PriorityExecutor CACHE_EXECUTOR;
    private static final HashMap<String, WeakReference<HttpTask<?>>> DOWNLOAD_TASK;
    private static final int FLAG_CACHE = 2;
    private static final int FLAG_PROGRESS = 3;
    private static final int FLAG_REQUEST_CREATED = 1;
    private static final PriorityExecutor HTTP_EXECUTOR;
    private static final int MAX_FILE_LOAD_WORKER = 3;
    private static final AtomicInteger sCurrFileLoadCount;
    private Callback$CacheCallback<ResultType> cacheCallback;
    private final Object cacheLock;
    private final Callback$CommonCallback<ResultType> callback;
    private final Executor executor;
    private volatile boolean hasException;
    private long lastUpdateTime;
    private Type loadType;
    private long loadingUpdateMaxTimeSpan;
    private RequestParams params;
    private Callback$PrepareCallback prepareCallback;
    private Callback$ProgressCallback progressCallback;
    private Object rawResult;
    private UriRequest request;
    private RequestInterceptListener requestInterceptListener;
    private HttpTask.HttpTask$RequestWorker requestWorker;
    private RequestTracker tracker;
    private volatile Boolean trustCache;
    
    static {
        sCurrFileLoadCount = new AtomicInteger(0);
        DOWNLOAD_TASK = new HashMap(1);
        HTTP_EXECUTOR = new PriorityExecutor(5, true);
        CACHE_EXECUTOR = new PriorityExecutor(5, true);
    }
    
    public HttpTask(final RequestParams params, final Callback$Cancelable callback$Cancelable, final Callback$CommonCallback<ResultType> callback) {
        super(callback$Cancelable);
        this.hasException = false;
        this.rawResult = null;
        this.trustCache = null;
        this.cacheLock = new Object();
        this.loadingUpdateMaxTimeSpan = 300L;
        this.params = params;
        this.callback = callback;
        if (callback instanceof Callback$CacheCallback) {
            this.cacheCallback = (Callback$CacheCallback<ResultType>)callback;
        }
        if (callback instanceof Callback$PrepareCallback) {
            this.prepareCallback = (Callback$PrepareCallback)callback;
        }
        if (callback instanceof Callback$ProgressCallback) {
            this.progressCallback = (Callback$ProgressCallback)callback;
        }
        if (callback instanceof RequestInterceptListener) {
            this.requestInterceptListener = (RequestInterceptListener)callback;
        }
        RequestTracker requestTracker;
        if ((requestTracker = params.getRequestTracker()) == null) {
            if (callback instanceof RequestTracker) {
                requestTracker = (RequestTracker)callback;
            }
            else {
                requestTracker = UriRequestFactory.getDefaultTracker();
            }
        }
        if (requestTracker != null) {
            this.tracker = (RequestTracker)new RequestTrackerWrapper(requestTracker);
        }
        if (params.getExecutor() != null) {
            this.executor = params.getExecutor();
        }
        else if (this.cacheCallback != null) {
            this.executor = (Executor)HttpTask.CACHE_EXECUTOR;
        }
        else {
            this.executor = (Executor)HttpTask.HTTP_EXECUTOR;
        }
    }
    
    private void checkDownloadTask() {
        if (File.class == this.loadType) {
            final HashMap<String, WeakReference<HttpTask<?>>> download_TASK = HttpTask.DOWNLOAD_TASK;
            synchronized (download_TASK) {
                final String saveFilePath = this.params.getSaveFilePath();
                if (!TextUtils.isEmpty((CharSequence)saveFilePath)) {
                    final WeakReference weakReference = (WeakReference)HttpTask.DOWNLOAD_TASK.get((Object)saveFilePath);
                    if (weakReference != null) {
                        final HttpTask httpTask = (HttpTask)weakReference.get();
                        if (httpTask != null) {
                            httpTask.cancel();
                            httpTask.closeRequestSync();
                        }
                        HttpTask.DOWNLOAD_TASK.remove((Object)saveFilePath);
                    }
                    HttpTask.DOWNLOAD_TASK.put((Object)saveFilePath, (Object)new WeakReference((Object)this));
                }
                if (HttpTask.DOWNLOAD_TASK.size() > 3) {
                    final Iterator iterator = HttpTask.DOWNLOAD_TASK.entrySet().iterator();
                    while (iterator.hasNext()) {
                        final WeakReference weakReference2 = (WeakReference)((Map$Entry)iterator.next()).getValue();
                        if (weakReference2 == null || weakReference2.get() == null) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }
    
    private void clearRawResult() {
        final Object rawResult = this.rawResult;
        if (rawResult instanceof Closeable) {
            IOUtil.closeQuietly((Closeable)rawResult);
        }
        this.rawResult = null;
    }
    
    private void closeRequestSync() {
        this.clearRawResult();
        IOUtil.closeQuietly((Closeable)this.request);
    }
    
    private UriRequest createNewRequest() throws Throwable {
        this.params.init();
        final UriRequest uriRequest = UriRequestFactory.getUriRequest(this.params, this.loadType);
        uriRequest.setCallingClassLoader(this.callback.getClass().getClassLoader());
        uriRequest.setProgressHandler((ProgressHandler)this);
        this.loadingUpdateMaxTimeSpan = this.params.getLoadingUpdateMaxTimeSpan();
        this.update(1, new Object[] { uriRequest });
        return uriRequest;
    }
    
    private void resolveLoadType() {
        final Class<? extends Callback$CommonCallback> class1 = this.callback.getClass();
        final Callback$CommonCallback<ResultType> callback = this.callback;
        if (callback instanceof Callback.TypedCallback) {
            this.loadType = ((Callback.TypedCallback)callback).getLoadType();
        }
        else if (callback instanceof Callback$PrepareCallback) {
            this.loadType = ParameterizedTypeUtil.getParameterizedType((Type)class1, (Class)Callback$PrepareCallback.class, 0);
        }
        else {
            this.loadType = ParameterizedTypeUtil.getParameterizedType((Type)class1, (Class)Callback$CommonCallback.class, 0);
        }
    }
    
    protected void cancelWorks() {
        x.task().run((Runnable)new HttpTask$2(this));
    }
    
    protected ResultType doBackground() throws Throwable {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //     4: ifne            1130
        //     7: aload_0        
        //     8: invokespecial   org/xutils/http/HttpTask.resolveLoadType:()V
        //    11: aload_0        
        //    12: aload_0        
        //    13: invokespecial   org/xutils/http/HttpTask.createNewRequest:()Lorg/xutils/http/request/UriRequest;
        //    16: putfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //    19: aload_0        
        //    20: invokespecial   org/xutils/http/HttpTask.checkDownloadTask:()V
        //    23: aload_0        
        //    24: getfield        org/xutils/http/HttpTask.params:Lorg/xutils/http/RequestParams;
        //    27: invokevirtual   org/xutils/http/RequestParams.getHttpRetryHandler:()Lorg/xutils/http/app/HttpRetryHandler;
        //    30: astore          4
        //    32: aload           4
        //    34: astore          8
        //    36: aload           4
        //    38: ifnonnull       50
        //    41: new             Lorg/xutils/http/app/HttpRetryHandler;
        //    44: dup            
        //    45: invokespecial   org/xutils/http/app/HttpRetryHandler.<init>:()V
        //    48: astore          8
        //    50: aload           8
        //    52: aload_0        
        //    53: getfield        org/xutils/http/HttpTask.params:Lorg/xutils/http/RequestParams;
        //    56: invokevirtual   org/xutils/http/RequestParams.getMaxRetryCount:()I
        //    59: invokevirtual   org/xutils/http/app/HttpRetryHandler.setMaxRetryCount:(I)V
        //    62: aload_0        
        //    63: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //    66: ifne            1119
        //    69: aload_0        
        //    70: getfield        org/xutils/http/HttpTask.cacheCallback:Lorg/xutils/common/Callback$CacheCallback;
        //    73: ifnull          367
        //    76: aload_0        
        //    77: getfield        org/xutils/http/HttpTask.params:Lorg/xutils/http/RequestParams;
        //    80: invokevirtual   org/xutils/http/RequestParams.getMethod:()Lorg/xutils/http/HttpMethod;
        //    83: invokestatic    org/xutils/http/HttpMethod.permitsCache:(Lorg/xutils/http/HttpMethod;)Z
        //    86: ifeq            367
        //    89: aload_0        
        //    90: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //    93: new             Ljava/lang/StringBuilder;
        //    96: astore          4
        //    98: aload           4
        //   100: invokespecial   java/lang/StringBuilder.<init>:()V
        //   103: aload           4
        //   105: ldc_w           "load cache: "
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: pop            
        //   112: aload           4
        //   114: aload_0        
        //   115: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   118: invokevirtual   org/xutils/http/request/UriRequest.getRequestUri:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: pop            
        //   125: aload           4
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: invokestatic    org/xutils/common/util/LogUtil.d:(Ljava/lang/String;)V
        //   133: aload_0        
        //   134: aload_0        
        //   135: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   138: invokevirtual   org/xutils/http/request/UriRequest.loadResultFromCache:()Ljava/lang/Object;
        //   141: putfield        org/xutils/http/HttpTask.rawResult:Ljava/lang/Object;
        //   144: goto            157
        //   147: astore          4
        //   149: ldc_w           "load disk cache error"
        //   152: aload           4
        //   154: invokestatic    org/xutils/common/util/LogUtil.w:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   157: aload_0        
        //   158: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   161: ifne            352
        //   164: aload_0        
        //   165: getfield        org/xutils/http/HttpTask.rawResult:Ljava/lang/Object;
        //   168: astore          5
        //   170: aload           5
        //   172: ifnull          367
        //   175: aload_0        
        //   176: getfield        org/xutils/http/HttpTask.prepareCallback:Lorg/xutils/common/Callback$PrepareCallback;
        //   179: astore          6
        //   181: aload           5
        //   183: astore          4
        //   185: aload           6
        //   187: ifnull          237
        //   190: aload           6
        //   192: aload           5
        //   194: invokeinterface org/xutils/common/Callback$PrepareCallback.prepare:(Ljava/lang/Object;)Ljava/lang/Object;
        //   199: astore          4
        //   201: aload_0        
        //   202: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   205: goto            237
        //   208: astore          4
        //   210: ldc_w           "prepare disk cache error"
        //   213: aload           4
        //   215: invokestatic    org/xutils/common/util/LogUtil.w:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   218: aload_0        
        //   219: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   222: aconst_null    
        //   223: astore          4
        //   225: goto            237
        //   228: astore          4
        //   230: aload_0        
        //   231: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   234: aload           4
        //   236: athrow         
        //   237: aload_0        
        //   238: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   241: ifne            341
        //   244: aload           4
        //   246: astore          5
        //   248: aload           4
        //   250: ifnull          370
        //   253: aload_0        
        //   254: iconst_2       
        //   255: iconst_1       
        //   256: anewarray       Ljava/lang/Object;
        //   259: dup            
        //   260: iconst_0       
        //   261: aload           4
        //   263: aastore        
        //   264: invokevirtual   org/xutils/http/HttpTask.update:(I[Ljava/lang/Object;)V
        //   267: aload_0        
        //   268: getfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   271: ifnonnull       325
        //   274: aload_0        
        //   275: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //   278: astore          5
        //   280: aload           5
        //   282: dup            
        //   283: astore          10
        //   285: monitorenter   
        //   286: aload_0        
        //   287: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //   290: invokevirtual   java/lang/Object.wait:()V
        //   293: aload           10
        //   295: monitorexit    
        //   296: goto            267
        //   299: astore          4
        //   301: new             Lorg/xutils/common/Callback$CancelledException;
        //   304: astore          4
        //   306: aload           4
        //   308: ldc_w           "cancelled before request"
        //   311: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   314: aload           4
        //   316: athrow         
        //   317: astore          4
        //   319: aload           10
        //   321: monitorexit    
        //   322: aload           4
        //   324: athrow         
        //   325: aload           4
        //   327: astore          5
        //   329: aload_0        
        //   330: getfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   333: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   336: ifeq            370
        //   339: aconst_null    
        //   340: areturn        
        //   341: new             Lorg/xutils/common/Callback$CancelledException;
        //   344: dup            
        //   345: ldc_w           "cancelled before request"
        //   348: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   351: athrow         
        //   352: aload_0        
        //   353: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   356: new             Lorg/xutils/common/Callback$CancelledException;
        //   359: dup            
        //   360: ldc_w           "cancelled before request"
        //   363: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   366: athrow         
        //   367: aconst_null    
        //   368: astore          5
        //   370: aload_0        
        //   371: getfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   374: ifnonnull       385
        //   377: aload_0        
        //   378: iconst_0       
        //   379: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   382: putfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   385: aload           5
        //   387: ifnonnull       397
        //   390: aload_0        
        //   391: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   394: invokevirtual   org/xutils/http/request/UriRequest.clearCacheHeader:()V
        //   397: aload_0        
        //   398: getfield        org/xutils/http/HttpTask.callback:Lorg/xutils/common/Callback$CommonCallback;
        //   401: astore          4
        //   403: aload           4
        //   405: instanceof      Lorg/xutils/common/Callback$ProxyCacheCallback;
        //   408: ifeq            426
        //   411: aload           4
        //   413: checkcast       Lorg/xutils/common/Callback$ProxyCacheCallback;
        //   416: invokeinterface org/xutils/common/Callback$ProxyCacheCallback.onlyCache:()Z
        //   421: ifeq            426
        //   424: aconst_null    
        //   425: areturn        
        //   426: aconst_null    
        //   427: astore          9
        //   429: aconst_null    
        //   430: astore          4
        //   432: iconst_1       
        //   433: istore_3       
        //   434: iconst_0       
        //   435: istore_1       
        //   436: iload_3        
        //   437: ifeq            1085
        //   440: aload           4
        //   442: astore          5
        //   444: aload           4
        //   446: astore          6
        //   448: aload_0        
        //   449: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   452: ifne            896
        //   455: aload           4
        //   457: astore          5
        //   459: aload           4
        //   461: astore          6
        //   463: aload_0        
        //   464: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   467: invokevirtual   org/xutils/http/request/UriRequest.close:()V
        //   470: aload_0        
        //   471: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   474: new             Ljava/lang/StringBuilder;
        //   477: astore          5
        //   479: aload           5
        //   481: invokespecial   java/lang/StringBuilder.<init>:()V
        //   484: aload           5
        //   486: ldc_w           "load: "
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: pop            
        //   493: aload           5
        //   495: aload_0        
        //   496: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   499: invokevirtual   org/xutils/http/request/UriRequest.getRequestUri:()Ljava/lang/String;
        //   502: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   505: pop            
        //   506: aload           5
        //   508: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   511: invokestatic    org/xutils/common/util/LogUtil.d:(Ljava/lang/String;)V
        //   514: new             Lorg/xutils/http/HttpTask$RequestWorker;
        //   517: astore          5
        //   519: aload           5
        //   521: aload_0        
        //   522: aconst_null    
        //   523: invokespecial   org/xutils/http/HttpTask$RequestWorker.<init>:(Lorg/xutils/http/HttpTask;Lorg/xutils/http/HttpTask$1;)V
        //   526: aload_0        
        //   527: aload           5
        //   529: putfield        org/xutils/http/HttpTask.requestWorker:Lorg/xutils/http/HttpTask$RequestWorker;
        //   532: aload           5
        //   534: invokevirtual   org/xutils/http/HttpTask$RequestWorker.request:()V
        //   537: aload_0        
        //   538: getfield        org/xutils/http/HttpTask.requestWorker:Lorg/xutils/http/HttpTask$RequestWorker;
        //   541: getfield        org/xutils/http/HttpTask$RequestWorker.ex:Ljava/lang/Throwable;
        //   544: ifnonnull       808
        //   547: aload_0        
        //   548: getfield        org/xutils/http/HttpTask.requestWorker:Lorg/xutils/http/HttpTask$RequestWorker;
        //   551: getfield        org/xutils/http/HttpTask$RequestWorker.result:Ljava/lang/Object;
        //   554: astore          7
        //   556: aload_0        
        //   557: aload           7
        //   559: putfield        org/xutils/http/HttpTask.rawResult:Ljava/lang/Object;
        //   562: aload           4
        //   564: astore          5
        //   566: aload           4
        //   568: astore          6
        //   570: aload_0        
        //   571: getfield        org/xutils/http/HttpTask.prepareCallback:Lorg/xutils/common/Callback$PrepareCallback;
        //   574: ifnull          693
        //   577: aload           4
        //   579: astore          5
        //   581: aload           4
        //   583: astore          6
        //   585: aload_0        
        //   586: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   589: istore_3       
        //   590: iload_3        
        //   591: ifne            653
        //   594: aload_0        
        //   595: getfield        org/xutils/http/HttpTask.prepareCallback:Lorg/xutils/common/Callback$PrepareCallback;
        //   598: aload_0        
        //   599: getfield        org/xutils/http/HttpTask.rawResult:Ljava/lang/Object;
        //   602: invokeinterface org/xutils/common/Callback$PrepareCallback.prepare:(Ljava/lang/Object;)Ljava/lang/Object;
        //   607: astore          7
        //   609: aload           7
        //   611: astore          5
        //   613: aload           7
        //   615: astore          6
        //   617: aload_0        
        //   618: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   621: aload           7
        //   623: astore          4
        //   625: goto            697
        //   628: astore          7
        //   630: aload           4
        //   632: astore          5
        //   634: aload           4
        //   636: astore          6
        //   638: aload_0        
        //   639: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   642: aload           4
        //   644: astore          5
        //   646: aload           4
        //   648: astore          6
        //   650: aload           7
        //   652: athrow         
        //   653: aload           4
        //   655: astore          5
        //   657: aload           4
        //   659: astore          6
        //   661: new             Lorg/xutils/common/Callback$CancelledException;
        //   664: astore          7
        //   666: aload           4
        //   668: astore          5
        //   670: aload           4
        //   672: astore          6
        //   674: aload           7
        //   676: ldc_w           "cancelled before request"
        //   679: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   682: aload           4
        //   684: astore          5
        //   686: aload           4
        //   688: astore          6
        //   690: aload           7
        //   692: athrow         
        //   693: aload           7
        //   695: astore          4
        //   697: aload           4
        //   699: astore          5
        //   701: aload           4
        //   703: astore          6
        //   705: aload_0        
        //   706: getfield        org/xutils/http/HttpTask.cacheCallback:Lorg/xutils/common/Callback$CacheCallback;
        //   709: ifnull          748
        //   712: aload           4
        //   714: astore          5
        //   716: aload           4
        //   718: astore          6
        //   720: aload_0        
        //   721: getfield        org/xutils/http/HttpTask.params:Lorg/xutils/http/RequestParams;
        //   724: invokevirtual   org/xutils/http/RequestParams.getMethod:()Lorg/xutils/http/HttpMethod;
        //   727: invokestatic    org/xutils/http/HttpMethod.permitsCache:(Lorg/xutils/http/HttpMethod;)Z
        //   730: ifeq            748
        //   733: aload           4
        //   735: astore          5
        //   737: aload           4
        //   739: astore          6
        //   741: aload_0        
        //   742: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   745: invokevirtual   org/xutils/http/request/UriRequest.save2Cache:()V
        //   748: aload           4
        //   750: astore          5
        //   752: aload           4
        //   754: astore          6
        //   756: aload_0        
        //   757: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   760: ifne            768
        //   763: iconst_0       
        //   764: istore_3       
        //   765: goto            436
        //   768: aload           4
        //   770: astore          5
        //   772: aload           4
        //   774: astore          6
        //   776: new             Lorg/xutils/common/Callback$CancelledException;
        //   779: astore          7
        //   781: aload           4
        //   783: astore          5
        //   785: aload           4
        //   787: astore          6
        //   789: aload           7
        //   791: ldc_w           "cancelled after request"
        //   794: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   797: aload           4
        //   799: astore          5
        //   801: aload           4
        //   803: astore          6
        //   805: aload           7
        //   807: athrow         
        //   808: aload_0        
        //   809: getfield        org/xutils/http/HttpTask.requestWorker:Lorg/xutils/http/HttpTask$RequestWorker;
        //   812: getfield        org/xutils/http/HttpTask$RequestWorker.ex:Ljava/lang/Throwable;
        //   815: athrow         
        //   816: astore          7
        //   818: aload           4
        //   820: astore          5
        //   822: aload           4
        //   824: astore          6
        //   826: aload_0        
        //   827: invokespecial   org/xutils/http/HttpTask.clearRawResult:()V
        //   830: aload           4
        //   832: astore          5
        //   834: aload           4
        //   836: astore          6
        //   838: aload_0        
        //   839: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   842: ifeq            885
        //   845: aload           4
        //   847: astore          5
        //   849: aload           4
        //   851: astore          6
        //   853: new             Lorg/xutils/common/Callback$CancelledException;
        //   856: astore          7
        //   858: aload           4
        //   860: astore          5
        //   862: aload           4
        //   864: astore          6
        //   866: aload           7
        //   868: ldc_w           "cancelled during request"
        //   871: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   874: aload           4
        //   876: astore          5
        //   878: aload           4
        //   880: astore          6
        //   882: aload           7
        //   884: athrow         
        //   885: aload           4
        //   887: astore          5
        //   889: aload           4
        //   891: astore          6
        //   893: aload           7
        //   895: athrow         
        //   896: aload           4
        //   898: astore          5
        //   900: aload           4
        //   902: astore          6
        //   904: new             Lorg/xutils/common/Callback$CancelledException;
        //   907: astore          7
        //   909: aload           4
        //   911: astore          5
        //   913: aload           4
        //   915: astore          6
        //   917: aload           7
        //   919: ldc_w           "cancelled before request"
        //   922: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //   925: aload           4
        //   927: astore          5
        //   929: aload           4
        //   931: astore          6
        //   933: aload           7
        //   935: athrow         
        //   936: astore          6
        //   938: aload_0        
        //   939: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   942: invokevirtual   org/xutils/http/request/UriRequest.getResponseCode:()I
        //   945: istore_2       
        //   946: iload_2        
        //   947: sipush          204
        //   950: if_icmpeq       1033
        //   953: iload_2        
        //   954: sipush          205
        //   957: if_icmpeq       1033
        //   960: iload_2        
        //   961: sipush          304
        //   964: if_icmpeq       1033
        //   967: aload           6
        //   969: astore          4
        //   971: aload_0        
        //   972: invokevirtual   org/xutils/http/HttpTask.isCancelled:()Z
        //   975: ifeq            1002
        //   978: aload           6
        //   980: astore          4
        //   982: aload           6
        //   984: instanceof      Lorg/xutils/common/Callback$CancelledException;
        //   987: ifne            1002
        //   990: new             Lorg/xutils/common/Callback$CancelledException;
        //   993: dup            
        //   994: ldc_w           "canceled by user"
        //   997: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //  1000: astore          4
        //  1002: aload_0        
        //  1003: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //  1006: astore          6
        //  1008: iinc            1, 1
        //  1011: aload           8
        //  1013: aload           6
        //  1015: aload           4
        //  1017: iload_1        
        //  1018: invokevirtual   org/xutils/http/app/HttpRetryHandler.canRetry:(Lorg/xutils/http/request/UriRequest;Ljava/lang/Throwable;I)Z
        //  1021: istore_3       
        //  1022: aload           4
        //  1024: astore          9
        //  1026: aload           5
        //  1028: astore          4
        //  1030: goto            436
        //  1033: aconst_null    
        //  1034: areturn        
        //  1035: astore          4
        //  1037: new             Ljava/lang/StringBuilder;
        //  1040: dup            
        //  1041: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1044: astore          4
        //  1046: aload           4
        //  1048: ldc_w           "Http Redirect:"
        //  1051: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1054: pop            
        //  1055: aload           4
        //  1057: aload_0        
        //  1058: getfield        org/xutils/http/HttpTask.params:Lorg/xutils/http/RequestParams;
        //  1061: invokevirtual   org/xutils/http/RequestParams.getUri:()Ljava/lang/String;
        //  1064: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1067: pop            
        //  1068: aload           4
        //  1070: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1073: invokestatic    org/xutils/common/util/LogUtil.w:(Ljava/lang/String;)V
        //  1076: iconst_1       
        //  1077: istore_3       
        //  1078: aload           6
        //  1080: astore          4
        //  1082: goto            436
        //  1085: aload           9
        //  1087: ifnull          1116
        //  1090: aload           4
        //  1092: ifnonnull       1116
        //  1095: aload_0        
        //  1096: getfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //  1099: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1102: ifeq            1108
        //  1105: goto            1116
        //  1108: aload_0        
        //  1109: iconst_1       
        //  1110: putfield        org/xutils/http/HttpTask.hasException:Z
        //  1113: aload           9
        //  1115: athrow         
        //  1116: aload           4
        //  1118: areturn        
        //  1119: new             Lorg/xutils/common/Callback$CancelledException;
        //  1122: dup            
        //  1123: ldc_w           "cancelled before request"
        //  1126: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //  1129: athrow         
        //  1130: new             Lorg/xutils/common/Callback$CancelledException;
        //  1133: dup            
        //  1134: ldc_w           "cancelled before request"
        //  1137: invokespecial   org/xutils/common/Callback$CancelledException.<init>:(Ljava/lang/String;)V
        //  1140: athrow         
        //  1141: astore          6
        //  1143: goto            293
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Signature:
        //  ()TResultType;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                 
        //  -----  -----  -----  -----  -------------------------------------
        //  89     144    147    157    Any
        //  190    201    208    237    Any
        //  210    218    228    237    Any
        //  286    293    299    317    Ljava/lang/InterruptedException;
        //  286    293    1141   1146   Any
        //  293    296    317    325    Any
        //  301    317    317    325    Any
        //  319    322    317    325    Any
        //  448    455    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  448    455    936    1035   Any
        //  463    470    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  463    470    936    1035   Any
        //  470    562    816    896    Any
        //  570    577    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  570    577    936    1035   Any
        //  585    590    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  585    590    936    1035   Any
        //  594    609    628    653    Any
        //  617    621    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  617    621    936    1035   Any
        //  638    642    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  638    642    936    1035   Any
        //  650    653    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  650    653    936    1035   Any
        //  661    666    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  661    666    936    1035   Any
        //  674    682    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  674    682    936    1035   Any
        //  690    693    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  690    693    936    1035   Any
        //  705    712    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  705    712    936    1035   Any
        //  720    733    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  720    733    936    1035   Any
        //  741    748    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  741    748    936    1035   Any
        //  756    763    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  756    763    936    1035   Any
        //  776    781    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  776    781    936    1035   Any
        //  789    797    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  789    797    936    1035   Any
        //  805    808    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  805    808    936    1035   Any
        //  808    816    816    896    Any
        //  826    830    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  826    830    936    1035   Any
        //  838    845    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  838    845    936    1035   Any
        //  853    858    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  853    858    936    1035   Any
        //  866    874    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  866    874    936    1035   Any
        //  882    885    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  882    885    936    1035   Any
        //  893    896    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  893    896    936    1035   Any
        //  904    909    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  904    909    936    1035   Any
        //  917    925    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  917    925    936    1035   Any
        //  933    936    1035   1085   Lorg/xutils/ex/HttpRedirectException;
        //  933    936    936    1035   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0293:
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
    
    public Executor getExecutor() {
        return this.executor;
    }
    
    public Priority getPriority() {
        return this.params.getPriority();
    }
    
    protected boolean isCancelFast() {
        return this.params.isCancelFast();
    }
    
    protected void onCancelled(final Callback$CancelledException ex) {
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onCancelled(this.request);
        }
        this.callback.onCancelled(ex);
    }
    
    protected void onError(final Throwable t, final boolean b) {
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onError(this.request, t, b);
        }
        this.callback.onError(t, b);
    }
    
    protected void onFinished() {
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onFinished(this.request);
        }
        x.task().run((Runnable)new HttpTask$1(this));
        this.callback.onFinished();
    }
    
    protected void onStarted() {
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onStart(this.params);
        }
        final Callback$ProgressCallback progressCallback = this.progressCallback;
        if (progressCallback != null) {
            progressCallback.onStarted();
        }
    }
    
    protected void onSuccess(final ResultType resultType) {
        if (this.hasException) {
            return;
        }
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onSuccess(this.request, (Object)resultType);
        }
        this.callback.onSuccess((Object)resultType);
    }
    
    protected void onUpdate(final int p0, final Object... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_1       
        //     2: if_icmpeq       198
        //     5: iload_1        
        //     6: iconst_2       
        //     7: if_icmpeq       84
        //    10: iload_1        
        //    11: iconst_3       
        //    12: if_icmpeq       18
        //    15: goto            219
        //    18: aload_0        
        //    19: getfield        org/xutils/http/HttpTask.progressCallback:Lorg/xutils/common/Callback$ProgressCallback;
        //    22: astore_3       
        //    23: aload_3        
        //    24: ifnull          219
        //    27: aload_2        
        //    28: arraylength    
        //    29: iconst_3       
        //    30: if_icmpne       219
        //    33: aload_3        
        //    34: aload_2        
        //    35: iconst_0       
        //    36: aaload         
        //    37: checkcast       Ljava/lang/Number;
        //    40: invokevirtual   java/lang/Number.longValue:()J
        //    43: aload_2        
        //    44: iconst_1       
        //    45: aaload         
        //    46: checkcast       Ljava/lang/Number;
        //    49: invokevirtual   java/lang/Number.longValue:()J
        //    52: aload_2        
        //    53: iconst_2       
        //    54: aaload         
        //    55: checkcast       Ljava/lang/Boolean;
        //    58: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    61: invokeinterface org/xutils/common/Callback$ProgressCallback.onLoading:(JJZ)V
        //    66: goto            219
        //    69: astore_2       
        //    70: aload_0        
        //    71: getfield        org/xutils/http/HttpTask.callback:Lorg/xutils/common/Callback$CommonCallback;
        //    74: aload_2        
        //    75: iconst_1       
        //    76: invokeinterface org/xutils/common/Callback$CommonCallback.onError:(Ljava/lang/Throwable;Z)V
        //    81: goto            219
        //    84: aload_0        
        //    85: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //    88: astore_3       
        //    89: aload_3        
        //    90: dup            
        //    91: astore          4
        //    93: monitorenter   
        //    94: aload_2        
        //    95: iconst_0       
        //    96: aaload         
        //    97: astore_2       
        //    98: aload_0        
        //    99: getfield        org/xutils/http/HttpTask.tracker:Lorg/xutils/http/app/RequestTracker;
        //   102: ifnull          119
        //   105: aload_0        
        //   106: getfield        org/xutils/http/HttpTask.tracker:Lorg/xutils/http/app/RequestTracker;
        //   109: aload_0        
        //   110: getfield        org/xutils/http/HttpTask.request:Lorg/xutils/http/request/UriRequest;
        //   113: aload_2        
        //   114: invokeinterface org/xutils/http/app/RequestTracker.onCache:(Lorg/xutils/http/request/UriRequest;Ljava/lang/Object;)V
        //   119: aload_0        
        //   120: aload_0        
        //   121: getfield        org/xutils/http/HttpTask.cacheCallback:Lorg/xutils/common/Callback$CacheCallback;
        //   124: aload_2        
        //   125: invokeinterface org/xutils/common/Callback$CacheCallback.onCache:(Ljava/lang/Object;)Z
        //   130: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   133: putfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   136: aload_0        
        //   137: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //   140: astore_2       
        //   141: aload_2        
        //   142: invokevirtual   java/lang/Object.notifyAll:()V
        //   145: goto            176
        //   148: astore_2       
        //   149: aload_0        
        //   150: iconst_0       
        //   151: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   154: putfield        org/xutils/http/HttpTask.trustCache:Ljava/lang/Boolean;
        //   157: aload_0        
        //   158: getfield        org/xutils/http/HttpTask.callback:Lorg/xutils/common/Callback$CommonCallback;
        //   161: aload_2        
        //   162: iconst_1       
        //   163: invokeinterface org/xutils/common/Callback$CommonCallback.onError:(Ljava/lang/Throwable;Z)V
        //   168: aload_0        
        //   169: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //   172: astore_2       
        //   173: goto            141
        //   176: aload           4
        //   178: monitorexit    
        //   179: goto            219
        //   182: astore_2       
        //   183: aload_0        
        //   184: getfield        org/xutils/http/HttpTask.cacheLock:Ljava/lang/Object;
        //   187: invokevirtual   java/lang/Object.notifyAll:()V
        //   190: aload_2        
        //   191: athrow         
        //   192: astore_2       
        //   193: aload           4
        //   195: monitorexit    
        //   196: aload_2        
        //   197: athrow         
        //   198: aload_0        
        //   199: getfield        org/xutils/http/HttpTask.tracker:Lorg/xutils/http/app/RequestTracker;
        //   202: astore_3       
        //   203: aload_3        
        //   204: ifnull          219
        //   207: aload_3        
        //   208: aload_2        
        //   209: iconst_0       
        //   210: aaload         
        //   211: checkcast       Lorg/xutils/http/request/UriRequest;
        //   214: invokeinterface org/xutils/http/app/RequestTracker.onRequestCreated:(Lorg/xutils/http/request/UriRequest;)V
        //   219: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  33     66     69     84     Any
        //  98     119    148    192    Any
        //  119    136    148    192    Any
        //  136    141    192    198    Any
        //  141    145    192    198    Any
        //  149    168    182    192    Any
        //  168    173    192    198    Any
        //  176    179    192    198    Any
        //  183    192    192    198    Any
        //  193    196    192    198    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0141:
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
    
    protected void onWaiting() {
        final RequestTracker tracker = this.tracker;
        if (tracker != null) {
            tracker.onWaiting(this.params);
        }
        final Callback$ProgressCallback progressCallback = this.progressCallback;
        if (progressCallback != null) {
            progressCallback.onWaiting();
        }
    }
    
    public String toString() {
        return this.params.toString();
    }
    
    public boolean updateProgress(long currentTimeMillis, final long n, final boolean b) {
        final boolean cancelled = this.isCancelled();
        boolean b3;
        final boolean b2 = b3 = false;
        if (!cancelled) {
            if (this.isFinished()) {
                b3 = b2;
            }
            else {
                if (this.progressCallback != null && this.request != null && currentTimeMillis > 0L) {
                    long n2 = currentTimeMillis;
                    if (currentTimeMillis < n) {
                        n2 = n;
                    }
                    if (b) {
                        this.lastUpdateTime = System.currentTimeMillis();
                        this.update(3, new Object[] { n2, n, this.request.isLoading() });
                    }
                    else {
                        currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.lastUpdateTime >= this.loadingUpdateMaxTimeSpan) {
                            this.lastUpdateTime = currentTimeMillis;
                            this.update(3, new Object[] { n2, n, this.request.isLoading() });
                        }
                    }
                }
                b3 = b2;
                if (!this.isCancelled()) {
                    b3 = b2;
                    if (!this.isFinished()) {
                        b3 = true;
                    }
                }
            }
        }
        return b3;
    }
}
