package org.xutils.http.loader;

import org.xutils.http.RequestParams;
import android.text.TextUtils;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;
import org.xutils.common.util.IOUtil;
import org.xutils.http.app.InputStreamResponseParser;
import java.io.InputStream;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.common.util.ParameterizedTypeUtil;
import java.util.List;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.ParameterizedType;
import org.xutils.http.app.ResponseParser;
import java.lang.reflect.Type;

class ObjectLoader extends Loader<Object>
{
    private String charset;
    private final Class<?> objectClass;
    private final Type objectType;
    private final ResponseParser parser;
    private String resultStr;
    
    public ObjectLoader(final Type objectType) {
        this.charset = "UTF-8";
        this.resultStr = null;
        this.objectType = objectType;
        if (objectType instanceof ParameterizedType) {
            this.objectClass = (Class)((ParameterizedType)objectType).getRawType();
        }
        else {
            if (objectType instanceof TypeVariable) {
                final StringBuilder sb = new StringBuilder();
                sb.append("not support callback type ");
                sb.append(objectType.toString());
                throw new IllegalArgumentException(sb.toString());
            }
            this.objectClass = (Class)objectType;
        }
        if (List.class.equals(this.objectClass)) {
            final Type parameterizedType = ParameterizedTypeUtil.getParameterizedType(this.objectType, List.class, 0);
            Class clazz;
            if (parameterizedType instanceof ParameterizedType) {
                clazz = (Class)((ParameterizedType)parameterizedType).getRawType();
            }
            else {
                if (parameterizedType instanceof TypeVariable) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("not support callback type ");
                    sb2.append(parameterizedType.toString());
                    throw new IllegalArgumentException(sb2.toString());
                }
                clazz = (Class)parameterizedType;
            }
            final HttpResponse httpResponse = clazz.getAnnotation(HttpResponse.class);
            if (httpResponse != null) {
                try {
                    this.parser = (ResponseParser)httpResponse.parser().newInstance();
                    return;
                }
                finally {
                    final Throwable t;
                    throw new RuntimeException("create parser error", t);
                }
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("not found @HttpResponse from ");
            sb3.append((Object)parameterizedType);
            throw new IllegalArgumentException(sb3.toString());
        }
        final HttpResponse httpResponse2 = this.objectClass.getAnnotation(HttpResponse.class);
        Label_0279: {
            if (httpResponse2 == null) {
                break Label_0279;
            }
            try {
                this.parser = (ResponseParser)httpResponse2.parser().newInstance();
                return;
            }
            finally {
                final Throwable t2;
                throw new RuntimeException("create parser error", t2);
            }
        }
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("not found @HttpResponse from ");
        sb4.append((Object)this.objectType);
        throw new IllegalArgumentException(sb4.toString());
    }
    
    @Override
    public Object load(final InputStream inputStream) throws Throwable {
        final ResponseParser parser = this.parser;
        Object o;
        if (parser instanceof InputStreamResponseParser) {
            o = ((InputStreamResponseParser)parser).parse(this.objectType, this.objectClass, inputStream);
        }
        else {
            final String str = IOUtil.readStr(inputStream, this.charset);
            this.resultStr = str;
            o = this.parser.parse(this.objectType, this.objectClass, str);
        }
        return o;
    }
    
    @Override
    public Object load(final UriRequest uriRequest) throws Throwable {
        try {
            uriRequest.sendRequest();
            this.parser.checkResponse(uriRequest);
            return this.load(uriRequest.getInputStream());
        }
        finally {
            this.parser.checkResponse(uriRequest);
        }
    }
    
    @Override
    public Object loadFromCache(final DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            final String textContent = diskCacheEntity.getTextContent();
            if (!TextUtils.isEmpty((CharSequence)textContent)) {
                return this.parser.parse(this.objectType, this.objectClass, textContent);
            }
        }
        return null;
    }
    
    @Override
    public Loader<Object> newInstance() {
        throw new IllegalAccessError("use constructor create ObjectLoader.");
    }
    
    @Override
    public void save2Cache(final UriRequest uriRequest) {
        this.saveStringCache(uriRequest, this.resultStr);
    }
    
    @Override
    public void setParams(final RequestParams requestParams) {
        if (requestParams != null) {
            final String charset = requestParams.getCharset();
            if (!TextUtils.isEmpty((CharSequence)charset)) {
                this.charset = charset;
            }
        }
    }
}
