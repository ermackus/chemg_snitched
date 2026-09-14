package com.kingagroot.kingdraw.core.data.http;

import java.util.Iterator;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.URLEncoder;
import androidx.core.util.Pair;
import android.text.TextUtils;
import java.net.URL;
import java.net.HttpURLConnection;

public class HttpConnection
{
    public static RequestResult request(final RequestParams requestParams) {
        final RequestResult requestResult = new RequestResult();
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(requestParams.getBuildUri()).openConnection();
            httpURLConnection.setRequestMethod(requestParams.getRequestMethod());
            httpURLConnection.setConnectTimeout(requestParams.getConnectTimeout());
            httpURLConnection.setReadTimeout(requestParams.getReadTimeout());
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            if (!TextUtils.isEmpty((CharSequence)requestParams.getJsonContent())) {
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            }
            httpURLConnection.connect();
            if (TextUtils.isEmpty((CharSequence)requestParams.getJsonContent())) {
                final StringBuilder sb = new StringBuilder();
                for (final Pair pair : requestParams.getBodyParams()) {
                    sb.append((String)pair.first);
                    sb.append("=");
                    sb.append(URLEncoder.encode((String)pair.second, "UTF-8"));
                }
                final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(sb.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            else {
                final DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream2.writeBytes(requestParams.getJsonContent());
                dataOutputStream2.flush();
                dataOutputStream2.close();
            }
            final int responseCode = httpURLConnection.getResponseCode();
            final StringBuilder sb2 = new StringBuilder();
            if (responseCode != 200) {
                requestResult.isOk = false;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("http request error,errorCode:");
                sb3.append(responseCode);
                throw new IOException(sb3.toString());
            }
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb2.append(line);
            }
            bufferedReader.close();
            requestResult.isOk = true;
            requestResult.content = sb2.toString();
            httpURLConnection.disconnect();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            requestResult.isOk = false;
        }
        return requestResult;
    }
}
