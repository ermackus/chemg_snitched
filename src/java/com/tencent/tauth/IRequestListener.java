package com.tencent.tauth;

import java.net.SocketTimeoutException;
import java.net.MalformedURLException;
import org.json.JSONException;
import java.io.IOException;
import com.tencent.open.utils.HttpUtils;
import org.json.JSONObject;

public interface IRequestListener
{
    void onComplete(final JSONObject p0);
    
    void onHttpStatusException(final HttpUtils.HttpStatusException p0);
    
    void onIOException(final IOException p0);
    
    void onJSONException(final JSONException p0);
    
    void onMalformedURLException(final MalformedURLException p0);
    
    void onNetworkUnavailableException(final HttpUtils.NetworkUnavailableException p0);
    
    void onSocketTimeoutException(final SocketTimeoutException p0);
    
    void onUnknowException(final Exception p0);
}
