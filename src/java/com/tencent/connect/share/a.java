package com.tencent.connect.share;

import com.tencent.connect.common.Constants;
import com.tencent.open.utils.f;
import java.util.ArrayList;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import com.tencent.open.utils.k;
import com.tencent.open.utils.d;
import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import java.io.File;
import android.graphics.Bitmap$Config;
import com.tencent.open.log.SLog;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;

public class a
{
    public static final int a(final BitmapFactory$Options bitmapFactory$Options, int n, int n2) {
        final int b = b(bitmapFactory$Options, n, n2);
        if (b <= 8) {
            n = 1;
            while (true) {
                n2 = n;
                if (n >= b) {
                    break;
                }
                n <<= 1;
            }
        }
        else {
            n2 = (b + 7) / 8 * 8;
        }
        return n2;
    }
    
    private static Bitmap a(final Bitmap bitmap, final int n) {
        final Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        if (width <= height) {
            width = height;
        }
        final float n2 = n / (float)width;
        matrix.postScale(n2, n2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    
    public static final Bitmap a(final String s, final int n) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(s, bitmapFactory$Options);
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            SLog.e("openSDK_LOG.AsynScaleCompressImage", "scaleBitmap exception1:", (Throwable)outOfMemoryError);
        }
        final int outWidth = bitmapFactory$Options.outWidth;
        int outHeight = bitmapFactory$Options.outHeight;
        if (bitmapFactory$Options.mCancel || bitmapFactory$Options.outWidth == -1 || bitmapFactory$Options.outHeight == -1) {
            return null;
        }
        if (outWidth > outHeight) {
            outHeight = outWidth;
        }
        bitmapFactory$Options.inPreferredConfig = Bitmap$Config.RGB_565;
        if (outHeight > n) {
            bitmapFactory$Options.inSampleSize = a(bitmapFactory$Options, -1, n * n);
        }
        bitmapFactory$Options.inJustDecodeBounds = false;
        Bitmap decodeFile = null;
        Label_0162: {
            try {
                decodeFile = BitmapFactory.decodeFile(s, bitmapFactory$Options);
                break Label_0162;
            }
            catch (final OutOfMemoryError outOfMemoryError2) {
                SLog.e("openSDK_LOG.AsynScaleCompressImage", "scaleBitmap OutOfMemoryError:", (Throwable)outOfMemoryError2);
            }
            catch (final Exception ex) {
                SLog.e("openSDK_LOG.AsynScaleCompressImage", "scaleBitmap exception2:", (Throwable)ex);
            }
            decodeFile = null;
        }
        if (decodeFile == null) {
            SLog.e("openSDK_LOG.AsynScaleCompressImage", "scaleBitmap return null");
            return null;
        }
        final int outWidth2 = bitmapFactory$Options.outWidth;
        int outHeight2 = bitmapFactory$Options.outHeight;
        if (outWidth2 > outHeight2) {
            outHeight2 = outWidth2;
        }
        Bitmap a = decodeFile;
        if (outHeight2 > n) {
            a = a(decodeFile, n);
        }
        return a;
    }
    
    protected static final String a(final Bitmap bitmap, String string, final String s) {
        final File file = new File(string);
        if (!file.exists()) {
            file.mkdirs();
        }
        final StringBuffer sb = new StringBuffer(string);
        sb.append(s);
        string = sb.toString();
        final File file2 = new File(string);
        if (file2.exists()) {
            file2.delete();
        }
        if (bitmap != null) {
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(Bitmap$CompressFormat.JPEG, 80, (OutputStream)fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                bitmap.recycle();
                return string;
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
            catch (final FileNotFoundException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }
    
    public static final void a(final Context context, final String s, final d d) {
        SLog.i("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage()");
        if (TextUtils.isEmpty((CharSequence)s)) {
            d.a(1, (String)null);
            return;
        }
        if (!k.a()) {
            d.a(2, (String)null);
            return;
        }
        new Thread((Runnable)new Runnable(s, new Handler(context.getMainLooper(), d) {
            final d a;
            
            public void handleMessage(final Message message) {
                final int what = message.what;
                if (what == 101) {
                    this.a.a(0, (ArrayList)message.obj);
                    return;
                }
                if (what != 102) {
                    super.handleMessage(message);
                    return;
                }
                this.a.a(message.arg1, (String)null);
            }
        }, context) {
            final String a;
            final Handler b;
            final Context c;
            
            public void run() {
                try {
                    final Bitmap a = com.tencent.connect.share.a.a(this.a, 840);
                    if (a != null) {
                        final File a2 = f.a("Images");
                        final String s = null;
                        String s2;
                        Object absolutePath;
                        if (a2 != null) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(a2.getAbsolutePath());
                            sb.append(File.separator);
                            sb.append(Constants.QQ_SHARE_TEMP_DIR);
                            sb.append(File.separator);
                            s2 = sb.toString();
                            absolutePath = null;
                        }
                        else {
                            final File d = f.d();
                            if (d == null) {
                                SLog.i("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage() getCacheDir = null,return error");
                                final Message obtainMessage = this.b.obtainMessage();
                                obtainMessage.arg1 = 102;
                                this.b.sendMessage(obtainMessage);
                                return;
                            }
                            absolutePath = d.getAbsolutePath();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append((String)absolutePath);
                            sb2.append(File.separator);
                            sb2.append(Constants.QQ_SHARE_TEMP_DIR);
                            sb2.append(File.separator);
                            s2 = sb2.toString();
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("scaleCompressImage() use cache dir=");
                            sb3.append(s2);
                            SLog.i("openSDK_LOG.AsynScaleCompressImage", sb3.toString());
                        }
                        final String g = k.g(this.a);
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append("share2qq_temp");
                        sb4.append(g);
                        sb4.append(".jpg");
                        final String string = sb4.toString();
                        String a3 = this.a;
                        if (!b(this.a, 840, 840)) {
                            SLog.i("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage() not out of bound,not compress!");
                        }
                        else {
                            SLog.i("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage() out of bound,compress!");
                            final String a4 = com.tencent.connect.share.a.a(a, s2, string);
                            if (!TextUtils.isEmpty((CharSequence)a4)) {
                                a3 = a4;
                            }
                        }
                        final boolean n = k.n(a3);
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append("scaleCompressImage() check file isAppSpecificDir=");
                        sb5.append(n);
                        SLog.i("openSDK_LOG.AsynScaleCompressImage", sb5.toString());
                        final ArrayList obj = new ArrayList(2);
                        String s3;
                        if (n) {
                            s3 = a3;
                        }
                        else {
                            s3 = s;
                            if (TextUtils.isEmpty((CharSequence)absolutePath)) {
                                final StringBuilder sb6 = new StringBuilder();
                                sb6.append(s2);
                                sb6.append(string);
                                final String string2 = sb6.toString();
                                final boolean a5 = k.a(this.c, a3, string2);
                                final StringBuilder sb7 = new StringBuilder();
                                sb7.append("scaleCompressImage() sd permission not denied. copy to app sepcific:");
                                sb7.append(string2);
                                sb7.append(",isSuccess=");
                                sb7.append(a5);
                                SLog.i("openSDK_LOG.AsynScaleCompressImage", sb7.toString());
                                s3 = s;
                                if (a5) {
                                    s3 = string2;
                                }
                            }
                        }
                        obj.add((Object)a3);
                        obj.add((Object)s3);
                        if (obj.size() >= 2 && (obj.get(0) != null || obj.get(1) != null)) {
                            final StringBuilder sb8 = new StringBuilder("scaleCompressImage() return success ! destFilePath=[");
                            sb8.append((String)obj.get(0));
                            sb8.append(",");
                            sb8.append((String)obj.get(1));
                            sb8.append("]");
                            SLog.i("openSDK_LOG.AsynScaleCompressImage", sb8.toString());
                            final Message obtainMessage2 = this.b.obtainMessage(101);
                            obtainMessage2.obj = obj;
                            this.b.sendMessage(obtainMessage2);
                            return;
                        }
                    }
                }
                catch (final Exception ex) {
                    SLog.e("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage runnable exception e:", (Throwable)ex);
                }
                SLog.d("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage() return failed!");
                final Message obtainMessage3 = this.b.obtainMessage(102);
                obtainMessage3.arg1 = 3;
                this.b.sendMessage(obtainMessage3);
            }
        }).start();
    }
    
    private static int b(final BitmapFactory$Options bitmapFactory$Options, final int n, final int n2) {
        final double n3 = bitmapFactory$Options.outWidth;
        final double n4 = bitmapFactory$Options.outHeight;
        int n5;
        if (n2 == -1) {
            n5 = 1;
        }
        else {
            n5 = (int)Math.ceil(Math.sqrt(n3 * n4 / n2));
        }
        int n6;
        if (n == -1) {
            n6 = 128;
        }
        else {
            final double n7 = n;
            n6 = (int)Math.min(Math.floor(n3 / n7), Math.floor(n4 / n7));
        }
        if (n6 < n5) {
            return n5;
        }
        if (n2 == -1 && n == -1) {
            return 1;
        }
        if (n == -1) {
            return n5;
        }
        return n6;
    }
    
    private static final boolean b(final String s, final int n, final int n2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(s, bitmapFactory$Options);
        }
        catch (final OutOfMemoryError outOfMemoryError) {
            SLog.e("openSDK_LOG.AsynScaleCompressImage", "isBitMapNeedToCompress exception:", (Throwable)outOfMemoryError);
        }
        final int outWidth = bitmapFactory$Options.outWidth;
        int outHeight = bitmapFactory$Options.outHeight;
        if (!bitmapFactory$Options.mCancel && bitmapFactory$Options.outWidth != -1 && bitmapFactory$Options.outHeight != -1) {
            int n3;
            if (outWidth > outHeight) {
                n3 = outWidth;
            }
            else {
                n3 = outHeight;
            }
            if (outWidth < outHeight) {
                outHeight = outWidth;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("longSide=");
            sb.append(n3);
            sb.append("shortSide=");
            sb.append(outHeight);
            SLog.d("openSDK_LOG.AsynScaleCompressImage", sb.toString());
            bitmapFactory$Options.inPreferredConfig = Bitmap$Config.RGB_565;
            return n3 > n2 || outHeight > n;
        }
        return false;
    }
}
