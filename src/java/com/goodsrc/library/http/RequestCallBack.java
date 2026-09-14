package com.goodsrc.library.http;

import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.R;
import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class RequestCallBack<T>
{
    public Type mType;
    
    public RequestCallBack() {
        this.mType = getSuperclassTypeParameter(this.getClass());
    }
    
    public static Type getSuperclassTypeParameter(final Class<?> clazz) {
        final Type genericSuperclass = clazz.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return $Gson$Types.canonicalize(((ParameterizedType)genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }
    
    public void onCancelled() {
    }
    
    public void onError(final Exception ex, final String s) {
        ToastUtil.showShort(R.string.net_connect_error);
    }
    
    public void onFinished() {
    }
    
    public void onLoading(final long n, final long n2, final boolean b) {
    }
    
    public void onStarted() {
    }
    
    public abstract void onSuccess(final T p0);
    
    public void onWaiting() {
    }
}
