package org.xutils.http;

import java.io.IOException;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.LogUtil;
import java.io.ByteArrayInputStream;
import org.xutils.http.body.InputStreamBody;
import org.xutils.http.body.FileBody;
import org.xutils.http.body.UrlEncodedParamsBody;
import org.xutils.http.body.StringBody;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.xutils.http.body.BodyItemWrapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map$Entry;
import org.json.JSONArray;
import java.util.LinkedHashMap;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Collection;
import android.text.TextUtils;
import java.util.ArrayList;
import org.xutils.http.body.RequestBody;
import org.xutils.common.util.KeyValue;
import java.util.List;

abstract class BaseParams
{
    private boolean asJsonContent;
    private String bodyContent;
    private final List<KeyValue> bodyParams;
    private String charset;
    private final List<KeyValue> fileParams;
    private final List<Header> headers;
    private HttpMethod method;
    private boolean multipart;
    private final List<KeyValue> queryStringParams;
    private RequestBody requestBody;
    
    BaseParams() {
        this.charset = "UTF-8";
        this.multipart = false;
        this.asJsonContent = false;
        this.headers = (List<Header>)new ArrayList();
        this.queryStringParams = (List<KeyValue>)new ArrayList();
        this.bodyParams = (List<KeyValue>)new ArrayList();
        this.fileParams = (List<KeyValue>)new ArrayList();
    }
    
    private void checkBodyParams() {
        synchronized (this) {
            if (this.bodyParams.isEmpty()) {
                return;
            }
            if (!HttpMethod.permitsRequestBody(this.method) || !TextUtils.isEmpty((CharSequence)this.bodyContent) || this.requestBody != null) {
                this.queryStringParams.addAll((Collection)this.bodyParams);
                this.bodyParams.clear();
            }
            if (!this.bodyParams.isEmpty() && (this.multipart || this.fileParams.size() > 0)) {
                this.fileParams.addAll((Collection)this.bodyParams);
                this.bodyParams.clear();
            }
            if (this.asJsonContent && !this.bodyParams.isEmpty()) {
                try {
                    JSONObject jsonObject;
                    if (!TextUtils.isEmpty((CharSequence)this.bodyContent)) {
                        jsonObject = new JSONObject(this.bodyContent);
                    }
                    else {
                        jsonObject = new JSONObject();
                    }
                    this.params2Json(jsonObject, this.bodyParams);
                    this.bodyContent = jsonObject.toString();
                    this.bodyParams.clear();
                }
                catch (final JSONException ex) {
                    throw new RuntimeException((Throwable)ex);
                }
            }
        }
    }
    
    private void params2Json(final JSONObject jsonObject, final List<KeyValue> list) throws JSONException {
        final HashSet set = new HashSet(list.size());
        final LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        for (int i = 0; i < list.size(); ++i) {
            final KeyValue keyValue = (KeyValue)list.get(i);
            final String key = keyValue.key;
            if (!TextUtils.isEmpty((CharSequence)key)) {
                Object o;
                if (linkedHashMap.containsKey((Object)key)) {
                    o = linkedHashMap.get((Object)key);
                }
                else {
                    o = new JSONArray();
                    linkedHashMap.put((Object)key, o);
                }
                ((JSONArray)o).put(RequestParamsHelper.parseJSONObject(keyValue.value));
                if (keyValue instanceof ArrayItem) {
                    set.add((Object)key);
                }
            }
        }
        for (final Map$Entry map$Entry : linkedHashMap.entrySet()) {
            final String s = (String)map$Entry.getKey();
            final JSONArray jsonArray = (JSONArray)map$Entry.getValue();
            if (jsonArray.length() <= 1 && !set.contains((Object)s)) {
                jsonObject.put(s, jsonArray.get(0));
            }
            else {
                jsonObject.put(s, (Object)jsonArray);
            }
        }
    }
    
    public void addBodyParameter(final String s, final File file) {
        this.addBodyParameter(s, file, null, null);
    }
    
    public void addBodyParameter(final String s, final Object o, final String s2) {
        this.addBodyParameter(s, o, s2, null);
    }
    
    public void addBodyParameter(final String s, final Object o, final String s2, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s2) && TextUtils.isEmpty((CharSequence)s3)) {
            this.fileParams.add((Object)new KeyValue(s, o));
        }
        else {
            this.fileParams.add((Object)new KeyValue(s, new BodyItemWrapper(o, s2, s3)));
        }
    }
    
    public void addBodyParameter(final String s, final String bodyContent) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.bodyParams.add((Object)new KeyValue(s, bodyContent));
        }
        else {
            this.bodyContent = bodyContent;
        }
    }
    
    public void addHeader(final String s, final String s2) {
        this.headers.add((Object)new Header(s, s2, false));
    }
    
    public void addParameter(final String s, Object next) {
        if (next == null) {
            return;
        }
        final HttpMethod method = this.method;
        final int n = 0;
        final int n2 = 0;
        int i = 0;
        if (method != null && !HttpMethod.permitsRequestBody(method)) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                if (next instanceof List) {
                    final Iterator iterator = ((List)next).iterator();
                    while (iterator.hasNext()) {
                        this.queryStringParams.add((Object)new ArrayItem(s, iterator.next()));
                    }
                }
                else if (next.getClass().isArray()) {
                    while (i < Array.getLength(next)) {
                        this.queryStringParams.add((Object)new ArrayItem(s, Array.get(next, i)));
                        ++i;
                    }
                }
                else {
                    this.queryStringParams.add((Object)new KeyValue(s, next));
                }
            }
        }
        else if (!TextUtils.isEmpty((CharSequence)s)) {
            if (!(next instanceof File) && !(next instanceof InputStream) && !(next instanceof byte[])) {
                if (next instanceof List) {
                    final Iterator iterator2 = ((List)next).iterator();
                    while (iterator2.hasNext()) {
                        next = iterator2.next();
                        this.bodyParams.add((Object)new ArrayItem(s, next));
                    }
                }
                else if (next instanceof JSONArray) {
                    final JSONArray jsonArray = (JSONArray)next;
                    for (int length = jsonArray.length(), j = n; j < length; ++j) {
                        this.bodyParams.add((Object)new ArrayItem(s, jsonArray.opt(j)));
                    }
                }
                else if (next.getClass().isArray()) {
                    for (int length2 = Array.getLength(next), k = n2; k < length2; ++k) {
                        this.bodyParams.add((Object)new ArrayItem(s, Array.get(next, k)));
                    }
                }
                else {
                    this.bodyParams.add((Object)new KeyValue(s, next));
                }
            }
            else {
                this.fileParams.add((Object)new KeyValue(s, next));
            }
        }
        else {
            this.bodyContent = next.toString();
        }
    }
    
    public void addQueryStringParameter(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.queryStringParams.add((Object)new KeyValue(s, s2));
        }
    }
    
    public void clearParams() {
        this.queryStringParams.clear();
        this.bodyParams.clear();
        this.fileParams.clear();
        this.bodyContent = null;
        this.requestBody = null;
    }
    
    public String getBodyContent() {
        this.checkBodyParams();
        return this.bodyContent;
    }
    
    public List<KeyValue> getBodyParams() {
        this.checkBodyParams();
        return (List<KeyValue>)new ArrayList((Collection)this.bodyParams);
    }
    
    public String getCharset() {
        return this.charset;
    }
    
    public List<KeyValue> getFileParams() {
        this.checkBodyParams();
        return (List<KeyValue>)new ArrayList((Collection)this.fileParams);
    }
    
    public List<Header> getHeaders() {
        return (List<Header>)new ArrayList((Collection)this.headers);
    }
    
    public HttpMethod getMethod() {
        return this.method;
    }
    
    public List<KeyValue> getParams(final String s) {
        final ArrayList list = new ArrayList();
        for (final KeyValue keyValue : this.queryStringParams) {
            if (s == null && keyValue.key == null) {
                ((List)list).add((Object)keyValue);
            }
            else {
                if (s == null || !s.equals((Object)keyValue.key)) {
                    continue;
                }
                ((List)list).add((Object)keyValue);
            }
        }
        for (final KeyValue keyValue2 : this.bodyParams) {
            if (s == null && keyValue2.key == null) {
                ((List)list).add((Object)keyValue2);
            }
            else {
                if (s == null || !s.equals((Object)keyValue2.key)) {
                    continue;
                }
                ((List)list).add((Object)keyValue2);
            }
        }
        for (final KeyValue keyValue3 : this.fileParams) {
            if (s == null && keyValue3.key == null) {
                ((List)list).add((Object)keyValue3);
            }
            else {
                if (s == null || !s.equals((Object)keyValue3.key)) {
                    continue;
                }
                ((List)list).add((Object)keyValue3);
            }
        }
        return (List<KeyValue>)list;
    }
    
    public List<KeyValue> getQueryStringParams() {
        this.checkBodyParams();
        return (List<KeyValue>)new ArrayList((Collection)this.queryStringParams);
    }
    
    public RequestBody getRequestBody() throws IOException {
        this.checkBodyParams();
        final RequestBody requestBody = this.requestBody;
        if (requestBody != null) {
            return requestBody;
        }
        final boolean empty = TextUtils.isEmpty((CharSequence)this.bodyContent);
        final StringBody stringBody = null;
        Object o;
        if (!empty) {
            o = new StringBody(this.bodyContent, this.charset);
        }
        else if (!this.multipart && this.fileParams.size() <= 0) {
            o = stringBody;
            if (this.bodyParams.size() > 0) {
                o = new UrlEncodedParamsBody(this.bodyParams, this.charset);
            }
        }
        else if (!this.multipart && this.fileParams.size() == 1) {
            final Iterator iterator = this.fileParams.iterator();
            o = stringBody;
            if (iterator.hasNext()) {
                Object o2 = ((KeyValue)iterator.next()).value;
                String contentType;
                if (o2 instanceof BodyItemWrapper) {
                    final BodyItemWrapper bodyItemWrapper = (BodyItemWrapper)o2;
                    o2 = bodyItemWrapper.getValue();
                    contentType = bodyItemWrapper.getContentType();
                }
                else {
                    contentType = null;
                }
                if (o2 instanceof File) {
                    o = new FileBody((File)o2, contentType);
                }
                else if (o2 instanceof InputStream) {
                    o = new InputStreamBody((InputStream)o2, contentType);
                }
                else if (o2 instanceof byte[]) {
                    o = new InputStreamBody((InputStream)new ByteArrayInputStream((byte[])o2), contentType);
                }
                else if (o2 instanceof String) {
                    final StringBody stringBody2 = new StringBody((String)o2, this.charset);
                    stringBody2.setContentType(contentType);
                    o = stringBody2;
                }
                else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Some params will be ignored for: ");
                    sb.append(this.toString());
                    LogUtil.w(sb.toString());
                    o = stringBody;
                }
            }
        }
        else {
            this.multipart = true;
            o = new MultipartBody((List)this.fileParams, this.charset);
        }
        return (RequestBody)o;
    }
    
    public String getStringParameter(final String s) {
        for (final KeyValue keyValue : this.queryStringParams) {
            if (s == null && keyValue.key == null) {
                return keyValue.getValueStr();
            }
            if (s != null && s.equals((Object)keyValue.key)) {
                return keyValue.getValueStr();
            }
        }
        for (final KeyValue keyValue2 : this.bodyParams) {
            if (s == null && keyValue2.key == null) {
                return keyValue2.getValueStr();
            }
            if (s != null && s.equals((Object)keyValue2.key)) {
                return keyValue2.getValueStr();
            }
        }
        return null;
    }
    
    public List<KeyValue> getStringParams() {
        final ArrayList list = new ArrayList(this.queryStringParams.size() + this.bodyParams.size());
        ((List)list).addAll((Collection)this.queryStringParams);
        ((List)list).addAll((Collection)this.bodyParams);
        return (List<KeyValue>)list;
    }
    
    public boolean isAsJsonContent() {
        return this.asJsonContent;
    }
    
    public boolean isMultipart() {
        return this.multipart;
    }
    
    public void removeParameter(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Iterator iterator = this.queryStringParams.iterator();
            while (iterator.hasNext()) {
                if (s.equals((Object)((KeyValue)iterator.next()).key)) {
                    iterator.remove();
                }
            }
            final Iterator iterator2 = this.bodyParams.iterator();
            while (iterator2.hasNext()) {
                if (s.equals((Object)((KeyValue)iterator2.next()).key)) {
                    iterator2.remove();
                }
            }
            final Iterator iterator3 = this.fileParams.iterator();
            while (iterator3.hasNext()) {
                if (s.equals((Object)((KeyValue)iterator3.next()).key)) {
                    iterator3.remove();
                }
            }
        }
        else {
            this.bodyContent = null;
        }
    }
    
    public void setAsJsonContent(final boolean asJsonContent) {
        this.asJsonContent = asJsonContent;
    }
    
    public void setBodyContent(final String bodyContent) {
        this.bodyContent = bodyContent;
    }
    
    public void setCharset(final String charset) {
        if (!TextUtils.isEmpty((CharSequence)charset)) {
            this.charset = charset;
        }
    }
    
    public void setHeader(final String s, final String s2) {
        final Header header = new Header(s, s2, true);
        final Iterator iterator = this.headers.iterator();
        while (iterator.hasNext()) {
            if (s.equals((Object)((KeyValue)iterator.next()).key)) {
                iterator.remove();
            }
        }
        this.headers.add((Object)header);
    }
    
    public void setMethod(final HttpMethod method) {
        this.method = method;
    }
    
    public void setMultipart(final boolean multipart) {
        this.multipart = multipart;
    }
    
    public void setRequestBody(final RequestBody requestBody) {
        this.requestBody = requestBody;
    }
    
    public String toJSONString() {
        final ArrayList list = new ArrayList(this.queryStringParams.size() + this.bodyParams.size());
        ((List)list).addAll((Collection)this.queryStringParams);
        ((List)list).addAll((Collection)this.bodyParams);
        try {
            JSONObject jsonObject;
            if (!TextUtils.isEmpty((CharSequence)this.bodyContent)) {
                jsonObject = new JSONObject(this.bodyContent);
            }
            else {
                jsonObject = new JSONObject();
            }
            this.params2Json(jsonObject, (List<KeyValue>)list);
            return jsonObject.toString();
        }
        catch (final JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public String toString() {
        this.checkBodyParams();
        final StringBuilder sb = new StringBuilder();
        if (!this.queryStringParams.isEmpty()) {
            for (final KeyValue keyValue : this.queryStringParams) {
                sb.append(keyValue.key);
                sb.append("=");
                sb.append(keyValue.value);
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (HttpMethod.permitsRequestBody(this.method)) {
            sb.append("<");
            if (!TextUtils.isEmpty((CharSequence)this.bodyContent)) {
                sb.append(this.bodyContent);
            }
            else if (!this.bodyParams.isEmpty()) {
                for (final KeyValue keyValue2 : this.bodyParams) {
                    sb.append(keyValue2.key);
                    sb.append("=");
                    sb.append(keyValue2.value);
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(">");
        }
        return sb.toString();
    }
    
    public static final class ArrayItem extends KeyValue
    {
        public ArrayItem(final String s, final Object o) {
            super(s, o);
        }
    }
    
    public static final class Header extends KeyValue
    {
        public final boolean setHeader;
        
        public Header(final String s, final String s2, final boolean setHeader) {
            super(s, s2);
            this.setHeader = setHeader;
        }
    }
}
