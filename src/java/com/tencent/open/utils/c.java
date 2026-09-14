package com.tencent.open.utils;

import android.graphics.Bitmap$CompressFormat;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import android.os.Environment;
import android.content.Context;
import java.io.InputStream;
import java.io.IOException;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.net.HttpURLConnection;
import android.os.Looper;
import android.graphics.Bitmap;
import android.os.Message;
import java.io.File;
import com.tencent.open.log.SLog;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.os.Handler;

public class c
{
    private static String c;
    private String a;
    private d b;
    private long d;
    private Handler e;
    private WeakReference<Activity> f;
    private Runnable g;
    
    public c(final Activity activity) {
        this.g = (Runnable)new Runnable() {
            final c a;
            
            public void run() {
                SLog.v("AsynLoadImg", "saveFileRunnable:");
                final String g = k.g(this.a.a);
                final StringBuilder sb = new StringBuilder();
                sb.append("share_qq_");
                sb.append(g);
                sb.append(".jpg");
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(com.tencent.open.utils.c.c);
                sb2.append(string);
                final String string2 = sb2.toString();
                final File file = new File(string2);
                final Message obtainMessage = this.a.e.obtainMessage();
                if (file.exists()) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = string2;
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("file exists: time:");
                    sb3.append(System.currentTimeMillis() - this.a.d);
                    SLog.v("AsynLoadImg", sb3.toString());
                }
                else {
                    final Bitmap a = com.tencent.open.utils.c.a(this.a.a);
                    boolean a2;
                    if (a != null) {
                        a2 = this.a.a(a, string);
                    }
                    else {
                        SLog.v("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                        a2 = false;
                    }
                    if (a2) {
                        obtainMessage.arg1 = 0;
                        obtainMessage.obj = string2;
                    }
                    else {
                        obtainMessage.arg1 = 1;
                    }
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("file not exists: download time:");
                    sb4.append(System.currentTimeMillis() - this.a.d);
                    SLog.v("AsynLoadImg", sb4.toString());
                }
                this.a.e.sendMessage(obtainMessage);
            }
        };
        this.f = (WeakReference<Activity>)new WeakReference((Object)activity);
        this.e = new Handler(this, activity.getMainLooper()) {
            final c a;
            
            public void handleMessage(final Message message) {
                final StringBuilder sb = new StringBuilder();
                sb.append("handleMessage:");
                sb.append(message.arg1);
                SLog.v("AsynLoadImg", sb.toString());
                if (message.arg1 == 0) {
                    this.a.b.a(message.arg1, (String)message.obj);
                }
                else {
                    this.a.b.a(message.arg1, (String)null);
                }
            }
        };
    }
    
    public static Bitmap a(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("getbitmap:");
        sb.append(s);
        SLog.v("AsynLoadImg", sb.toString());
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            final InputStream inputStream = httpURLConnection.getInputStream();
            final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("image download finished.");
            sb2.append(s);
            SLog.v("AsynLoadImg", sb2.toString());
            return decodeStream;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            SLog.v("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
            SLog.v("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }
    
    public void a(final String a, final d b) {
        SLog.v("AsynLoadImg", "--save---");
        if (a == null || a.equals((Object)"")) {
            b.a(1, (String)null);
            return;
        }
        if (!k.a()) {
            b.a(2, (String)null);
            return;
        }
        if (this.f.get() != null) {
            final Activity activity = (Activity)this.f.get();
            final File h = k.h((Context)activity, "Images");
            final File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (h == null) {
                SLog.e("AsynLoadImg", "externalImageFile is null");
                b.a(2, (String)null);
                return;
            }
            final StringBuilder sb = new StringBuilder();
            String s;
            if (i.d((Context)activity)) {
                s = h.getAbsolutePath();
            }
            else {
                s = externalStorageDirectory.getAbsolutePath();
            }
            sb.append(s);
            sb.append("/tmp/");
            com.tencent.open.utils.c.c = sb.toString();
        }
        this.d = System.currentTimeMillis();
        this.a = a;
        this.b = b;
        new Thread(this.g).start();
    }
    
    public boolean a(final Bitmap bitmap, String ex) {
        final String c = com.tencent.open.utils.c.c;
        final BufferedOutputStream bufferedOutputStream = null;
        Object o2;
        final Object o = o2 = null;
        BufferedOutputStream bufferedOutputStream2;
        try {
            try {
                o2 = o;
                final File file = new File(c);
                o2 = o;
                if (!file.exists()) {
                    o2 = o;
                    file.mkdir();
                }
                o2 = o;
                o2 = o;
                final StringBuilder sb = new StringBuilder();
                o2 = o;
                sb.append(c);
                o2 = o;
                sb.append((String)ex);
                o2 = o;
                final String string = sb.toString();
                o2 = o;
                o2 = o;
                final StringBuilder sb2 = new StringBuilder();
                o2 = o;
                sb2.append("saveFile:");
                o2 = o;
                sb2.append((String)ex);
                o2 = o;
                SLog.v("AsynLoadImg", sb2.toString());
                o2 = o;
                o2 = o;
                final File file2 = new File(string);
                o2 = o;
                o2 = o;
                o2 = o;
                final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                o2 = o;
                ex = (IOException)new BufferedOutputStream((OutputStream)fileOutputStream);
                try {
                    bitmap.compress(Bitmap$CompressFormat.JPEG, 80, (OutputStream)ex);
                    ((BufferedOutputStream)ex).flush();
                    try {
                        ((BufferedOutputStream)ex).close();
                    }
                    catch (final IOException ex2) {
                        ex2.printStackTrace();
                    }
                    return true;
                }
                catch (final IOException o2) {}
                finally {
                    o2 = ex;
                }
            }
            finally {}
        }
        catch (final IOException ex) {
            bufferedOutputStream2 = bufferedOutputStream;
        }
        ex.printStackTrace();
        SLog.e("AsynLoadImg", "saveFile bmp fail---", (Throwable)ex);
        if (bufferedOutputStream2 != null) {
            try {
                bufferedOutputStream2.close();
            }
            catch (final IOException ex3) {
                ex3.printStackTrace();
            }
        }
        return false;
        if (o2 != null) {
            try {
                ((BufferedOutputStream)o2).close();
            }
            catch (final IOException ex4) {
                ex4.printStackTrace();
            }
        }
    }
}
