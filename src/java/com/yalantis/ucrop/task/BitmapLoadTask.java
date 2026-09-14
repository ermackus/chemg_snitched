package com.yalantis.ucrop.task;

import java.io.InputStream;
import android.graphics.Matrix;
import com.yalantis.ucrop.model.ExifInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import java.io.IOException;
import okio.Sink;
import java.io.OutputStream;
import okio.BufferedSource;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import java.io.Closeable;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import okio.Okio;
import okhttp3.Request$Builder;
import com.yalantis.ucrop.OkHttpClientStore;
import android.util.Log;
import android.net.Uri;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import android.os.AsyncTask;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult>
{
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final WeakReference<Context> mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;
    
    public BitmapLoadTask(final Context context, final Uri mInputUri, final Uri mOutputUri, final int mRequiredWidth, final int mRequiredHeight, final BitmapLoadCallback mBitmapLoadCallback) {
        this.mContext = (WeakReference<Context>)new WeakReference((Object)context);
        this.mInputUri = mInputUri;
        this.mOutputUri = mOutputUri;
        this.mRequiredWidth = mRequiredWidth;
        this.mRequiredHeight = mRequiredHeight;
        this.mBitmapLoadCallback = mBitmapLoadCallback;
    }
    
    private void downloadFile(Uri sink, final Uri uri) throws NullPointerException, IOException {
        Log.d("BitmapWorkerTask", "downloadFile");
        if (uri == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        final Context context = (Context)this.mContext.get();
        if (context == null) {
            throw new NullPointerException("Context is null");
        }
        final OkHttpClient client = OkHttpClientStore.INSTANCE.getClient();
        Object o = null;
        final Uri uri2 = null;
        Closeable closeable;
        Response response2;
        try {
            Response execute = client.newCall(new Request$Builder().url(sink.toString()).build()).execute();
            try {
                final BufferedSource source = execute.body().source();
                sink = uri2;
                try {
                    final OutputStream openOutputStream = context.getContentResolver().openOutputStream(uri);
                    if (openOutputStream != null) {
                        sink = uri2;
                        final Sink sink2 = (Sink)(sink = (Uri)Okio.sink(openOutputStream));
                        source.readAll(sink2);
                        BitmapLoadUtils.close((Closeable)source);
                        BitmapLoadUtils.close((Closeable)sink2);
                        if (execute != null) {
                            BitmapLoadUtils.close((Closeable)execute.body());
                        }
                        client.dispatcher().cancelAll();
                        this.mInputUri = this.mOutputUri;
                        return;
                    }
                    sink = uri2;
                    sink = uri2;
                    final NullPointerException ex = new NullPointerException("OutputStream for given output Uri is null");
                    sink = uri2;
                    throw ex;
                }
                finally {
                    o = source;
                    final Response response;
                    execute = response;
                }
            }
            finally {}
        }
        finally {
            closeable = null;
            response2 = null;
        }
        BitmapLoadUtils.close((Closeable)o);
        BitmapLoadUtils.close(closeable);
        if (response2 != null) {
            BitmapLoadUtils.close((Closeable)response2.body());
        }
        client.dispatcher().cancelAll();
        this.mInputUri = this.mOutputUri;
    }
    
    private void processInputUri() throws NullPointerException, IOException {
        final String scheme = this.mInputUri.getScheme();
        final StringBuilder sb = new StringBuilder();
        sb.append("Uri scheme: ");
        sb.append(scheme);
        Log.d("BitmapWorkerTask", sb.toString());
        if (!"http".equals((Object)scheme)) {
            if (!"https".equals((Object)scheme)) {
                if ("file".equals((Object)scheme)) {
                    return;
                }
                if ("content".equals((Object)scheme)) {
                    return;
                }
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid Uri scheme ");
                sb2.append(scheme);
                Log.e("BitmapWorkerTask", sb2.toString());
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("Invalid Uri scheme");
                sb3.append(scheme);
                throw new IllegalArgumentException(sb3.toString());
            }
        }
        try {
            this.downloadFile(this.mInputUri, this.mOutputUri);
            return;
        }
        catch (final IOException scheme) {}
        catch (final NullPointerException ex) {}
        Log.e("BitmapWorkerTask", "Downloading failed", (Throwable)scheme);
        throw scheme;
    }
    
    protected BitmapWorkerResult doInBackground(Void... decodeStream) {
        final Context context = (Context)this.mContext.get();
        if (context == null) {
            return new BitmapWorkerResult((Exception)new NullPointerException("context is null"));
        }
        if (this.mInputUri == null) {
            return new BitmapWorkerResult((Exception)new NullPointerException("Input Uri cannot be null"));
        }
        try {
            this.processInputUri();
            final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
            bitmapFactory$Options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(context.getContentResolver().openInputStream(this.mInputUri), (Rect)null, bitmapFactory$Options);
                bitmapFactory$Options.inSampleSize = BitmapLoadUtils.computeSize(bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight);
            }
            catch (final Exception decodeStream) {
                decodeStream.printStackTrace();
            }
            int i = 0;
            bitmapFactory$Options.inJustDecodeBounds = false;
            decodeStream = null;
            while (i == 0) {
                Object o = decodeStream;
                try {
                    final InputStream openInputStream = context.getContentResolver().openInputStream(this.mInputUri);
                    try {
                        final Object o2 = decodeStream = (Exception)BitmapFactory.decodeStream(openInputStream, (Rect)null, bitmapFactory$Options);
                        if (bitmapFactory$Options.outWidth != -1) {
                            decodeStream = (Exception)o2;
                            if (bitmapFactory$Options.outHeight != -1) {
                                o = o2;
                                BitmapLoadUtils.close((Closeable)openInputStream);
                                o = o2;
                                if (BitmapLoadUtils.checkSize((Bitmap)o2, bitmapFactory$Options)) {
                                    decodeStream = (Exception)o2;
                                    continue;
                                }
                                i = 1;
                                decodeStream = (Exception)o2;
                                continue;
                            }
                        }
                        decodeStream = (Exception)o2;
                        decodeStream = (Exception)o2;
                        o = new(java.lang.IllegalArgumentException.class)();
                        decodeStream = (Exception)o2;
                        decodeStream = (Exception)o2;
                        final StringBuilder sb = new StringBuilder();
                        decodeStream = (Exception)o2;
                        sb.append("Bounds for bitmap could not be retrieved from the Uri: [");
                        decodeStream = (Exception)o2;
                        sb.append((Object)this.mInputUri);
                        decodeStream = (Exception)o2;
                        sb.append("]");
                        decodeStream = (Exception)o2;
                        new IllegalArgumentException(sb.toString());
                        decodeStream = (Exception)o2;
                        final BitmapWorkerResult bitmapWorkerResult = new BitmapWorkerResult((Exception)o);
                        o = o2;
                        BitmapLoadUtils.close((Closeable)openInputStream);
                        return bitmapWorkerResult;
                    }
                    finally {
                        o = decodeStream;
                        BitmapLoadUtils.close((Closeable)openInputStream);
                        o = decodeStream;
                    }
                }
                catch (final IOException decodeStream) {
                    Log.e("BitmapWorkerTask", "doInBackground: ImageDecoder.createSource: ", (Throwable)decodeStream);
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Bitmap could not be decoded from the Uri: [");
                    sb2.append((Object)this.mInputUri);
                    sb2.append("]");
                    return new BitmapWorkerResult((Exception)new IllegalArgumentException(sb2.toString(), (Throwable)decodeStream));
                }
                catch (OutOfMemoryError decodeStream) {
                    Log.e("BitmapWorkerTask", "doInBackground: BitmapFactory.decodeFileDescriptor: ", (Throwable)decodeStream);
                    bitmapFactory$Options.inSampleSize *= 2;
                    decodeStream = (Exception)o;
                    continue;
                }
                break;
            }
            if (decodeStream == null) {
                decodeStream = (Exception)new StringBuilder();
                ((StringBuilder)decodeStream).append("Bitmap could not be decoded from the Uri: [");
                ((StringBuilder)decodeStream).append((Object)this.mInputUri);
                ((StringBuilder)decodeStream).append("]");
                return new BitmapWorkerResult((Exception)new IllegalArgumentException(((StringBuilder)decodeStream).toString()));
            }
            final int exifOrientation = BitmapLoadUtils.getExifOrientation(context, this.mInputUri);
            final int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
            final int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
            final ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
            final Matrix matrix = new Matrix();
            if (exifToDegrees != 0) {
                matrix.preRotate((float)exifToDegrees);
            }
            if (exifToTranslation != 1) {
                matrix.postScale((float)exifToTranslation, 1.0f);
            }
            if (!matrix.isIdentity()) {
                return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap((Bitmap)decodeStream, matrix), exifInfo);
            }
            return new BitmapWorkerResult((Bitmap)decodeStream, exifInfo);
        }
        catch (final IOException decodeStream) {}
        catch (final NullPointerException ex) {}
        return new BitmapWorkerResult(decodeStream);
    }
    
    protected void onPostExecute(final BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(bitmapWorkerResult.mBitmapResult, bitmapWorkerResult.mExifInfo, this.mInputUri, this.mOutputUri);
        }
        else {
            this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
        }
    }
    
    public static class BitmapWorkerResult
    {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;
        
        public BitmapWorkerResult(final Bitmap mBitmapResult, final ExifInfo mExifInfo) {
            this.mBitmapResult = mBitmapResult;
            this.mExifInfo = mExifInfo;
        }
        
        public BitmapWorkerResult(final Exception mBitmapWorkerException) {
            this.mBitmapWorkerException = mBitmapWorkerException;
        }
    }
}
