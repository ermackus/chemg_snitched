package com.ta.utdid2.a;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import com.ut.device.a;
import org.apache.http.client.methods.HttpPost;
import com.ta.utdid2.b.a.f;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import android.util.Log;
import com.ta.utdid2.b.a.d;
import org.json.JSONObject;
import android.text.TextUtils;
import android.content.Context;

public class b
{
    private static final String TAG;
    private static b a;
    private Object a;
    private Context mContext;
    
    static {
        TAG = b.class.getName();
        b.a = null;
    }
    
    public b(final Context mContext) {
        this.a = new Object();
        this.mContext = mContext;
    }
    
    public static b a(final Context context) {
        synchronized (b.class) {
            if (b.a == null) {
                b.a = new b(context);
            }
            return b.a;
        }
    }
    
    private static String a(String s, final String s2) {
        String string = s2;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.has("data")) {
                    final JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                    string = s2;
                    if (jsonObject2.has("action")) {
                        string = s2;
                        if (jsonObject2.has("aid")) {
                            s = jsonObject2.getString("action");
                            if (!s.equalsIgnoreCase("new")) {
                                string = s2;
                                if (!s.equalsIgnoreCase("changed")) {
                                    return string;
                                }
                            }
                            string = jsonObject2.getString("aid");
                        }
                    }
                }
                else {
                    string = s2;
                    if (jsonObject.has("isError")) {
                        string = s2;
                        if (jsonObject.has("status")) {
                            final String string2 = jsonObject.getString("isError");
                            s = jsonObject.getString("status");
                            string = s2;
                            if (string2.equalsIgnoreCase("true")) {
                                if (!s.equalsIgnoreCase("404")) {
                                    string = s2;
                                    if (!s.equalsIgnoreCase("401")) {
                                        return string;
                                    }
                                }
                                if (d.e) {
                                    final String tag = b.TAG;
                                    final StringBuilder sb = new StringBuilder("remove the AID, status:");
                                    sb.append(s);
                                    Log.d(tag, sb.toString());
                                }
                                string = "";
                            }
                        }
                    }
                }
            }
            catch (final Exception ex) {
                Log.e(b.TAG, ex.toString());
                string = s2;
            }
            catch (final JSONException ex2) {
                Log.e(b.TAG, ex2.toString());
                string = s2;
            }
        }
        return string;
    }
    
    private static String b(final String s, final String s2, String encode, final String s3) {
        final StringBuilder sb = new StringBuilder();
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
        }
        catch (final UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        sb.append("http://hydra.alibaba.com/");
        sb.append(s);
        sb.append("/get_aid/");
        sb.append("?");
        sb.append("auth[token]=");
        sb.append(s2);
        sb.append("&type=");
        sb.append("utdid");
        sb.append("&id=");
        sb.append(encode);
        sb.append("&aid=");
        sb.append(s3);
        return sb.toString();
    }
    
    public String a(String o, final String s, String s2, final String s3) {
        s2 = b((String)o, s, s2, s3);
        int n;
        if (f.b(this.mContext)) {
            n = 3000;
        }
        else {
            n = 1000;
        }
        if (d.e) {
            o = b.TAG;
            final StringBuilder sb = new StringBuilder("url:");
            sb.append(s2);
            sb.append("; timeout:");
            sb.append(n);
            Log.d((String)o, sb.toString());
        }
        o = new a(new HttpPost(s2));
        ((a)o).start();
        try {
            final Object a = this.a;
            synchronized (a) {
                this.a.wait(n);
            }
        }
        catch (final Exception ex) {
            Log.e(b.TAG, ex.toString());
        }
        s2 = ((a)o).b();
        if (d.e) {
            o = b.TAG;
            final StringBuilder sb2 = new StringBuilder("mLine:");
            sb2.append(s2);
            Log.d((String)o, sb2.toString());
        }
        return a(s2, s3);
    }
    
    public void a(final String s, final String s2, String tag, final String s3, final com.ut.device.a a) {
        final String b = b(s, s2, tag, s3);
        if (d.e) {
            tag = com.ta.utdid2.a.b.TAG;
            final StringBuilder sb = new StringBuilder("url:");
            sb.append(b);
            sb.append("; len:");
            sb.append(b.length());
            Log.d(tag, sb.toString());
        }
        new a(new HttpPost(b), a, s3, s, s2).start();
    }
    
    class a extends Thread
    {
        com.ut.device.a a;
        String a;
        HttpPost a;
        final b b;
        String b;
        String c;
        String d;
        
        public a(final b b, final HttpPost a) {
            this.b = b;
            this.a = "";
            this.d = "";
            this.a = a;
        }
        
        public a(final b b, final HttpPost a, final com.ut.device.a a2, final String b2, final String c, final String d) {
            this.b = b;
            this.a = "";
            this.d = "";
            this.a = a;
            this.a = a2;
            this.b = b2;
            this.c = c;
            this.d = d;
        }
        
        public String b() {
            return this.a;
        }
        
        public void run() {
            final com.ut.device.a a = this.a;
            if (a != null) {
                a.a(1000, this.b);
            }
            final DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            final BufferedReader bufferedReader = null;
            HttpResponse execute;
            try {
                execute = ((HttpClient)defaultHttpClient).execute((HttpUriRequest)this.a);
            }
            catch (final Exception ex) {
                final com.ut.device.a a2 = this.a;
                if (a2 != null) {
                    a2.a(1002, this.b);
                }
                Log.e(com.ta.utdid2.a.b.TAG, ex.toString());
                execute = null;
            }
            BufferedReader bufferedReader2 = null;
            Label_0127: {
                if (execute == null) {
                    break Label_0127;
                }
                try {
                    bufferedReader2 = new BufferedReader((Reader)new InputStreamReader(execute.getEntity().getContent(), Charset.forName("UTF-8")));
                    break Label_0127;
                    Log.e(com.ta.utdid2.a.b.TAG, "response is null!");
                    bufferedReader2 = bufferedReader;
                }
                catch (final Exception ex2) {
                    final com.ut.device.a a3 = this.a;
                    if (a3 != null) {
                        a3.a(1002, this.b);
                    }
                    Log.e(com.ta.utdid2.a.b.TAG, ex2.toString());
                    bufferedReader2 = bufferedReader;
                }
            }
            while (true) {
                if (bufferedReader2 != null) {
                    Label_0261: {
                        try {
                            while (true) {
                                final String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (com.ta.utdid2.b.a.d.e) {
                                    Log.d(com.ta.utdid2.a.b.TAG, line);
                                }
                                this.a = line;
                            }
                            break Label_0261;
                            Log.e(com.ta.utdid2.a.b.TAG, "BufferredReader is null!");
                        }
                        catch (final Exception ex3) {
                            final com.ut.device.a a4 = this.a;
                            if (a4 != null) {
                                a4.a(1002, this.b);
                            }
                            Log.e(com.ta.utdid2.a.b.TAG, ex3.toString());
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                            if (com.ta.utdid2.b.a.d.e) {
                                Log.d(com.ta.utdid2.a.b.TAG, "close the bufferreader");
                            }
                        }
                        catch (final IOException ex4) {
                            Log.e(com.ta.utdid2.a.b.TAG, ex4.toString());
                        }
                    }
                    if (this.a == null) {
                        final Object a5 = this.b.a;
                        synchronized (a5) {
                            this.b.a.notifyAll();
                            return;
                        }
                    }
                    final String b = a(this.a, this.b);
                    this.a.a(1001, b);
                    com.ta.utdid2.a.c.a(this.b.mContext, this.c, b, this.d);
                    return;
                }
                continue;
            }
        }
    }
}
