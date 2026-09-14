package com.yalantis.ucrop.task;

import java.io.File;
import java.io.Closeable;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import com.yalantis.ucrop.util.FileUtils;
import android.graphics.Matrix;
import java.io.IOException;
import androidx.exifinterface.media.ExifInterface;
import android.util.Log;
import com.yalantis.ucrop.util.ImageHeaderParser;
import android.os.Build$VERSION;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ImageState;
import android.graphics.Bitmap;
import android.net.Uri;
import com.yalantis.ucrop.model.ExifInfo;
import android.graphics.RectF;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.graphics.Bitmap$CompressFormat;
import android.os.AsyncTask;

public class BitmapCropTask extends AsyncTask<Void, Void, Throwable>
{
    private static final String CONTENT_SCHEME = "content";
    private static final int MIN_CROPPED_HEIGHT = 1;
    private static final String TAG = "BitmapCropTask";
    private int cropOffsetX;
    private int cropOffsetY;
    private final Bitmap$CompressFormat mCompressFormat;
    private final int mCompressQuality;
    private final WeakReference<Context> mContext;
    private final BitmapCropCallback mCropCallback;
    private final RectF mCropRect;
    private int mCroppedImageHeight;
    private int mCroppedImageWidth;
    private float mCurrentAngle;
    private final RectF mCurrentImageRect;
    private float mCurrentScale;
    private final ExifInfo mExifInfo;
    private final String mImageInputPath;
    private final Uri mImageInputUri;
    private final String mImageOutputPath;
    private final Uri mImageOutputUri;
    private final int mMaxResultImageSizeX;
    private final int mMaxResultImageSizeY;
    private Bitmap mViewBitmap;
    
    public BitmapCropTask(final Context context, final Bitmap mViewBitmap, final ImageState imageState, final CropParameters cropParameters, final BitmapCropCallback mCropCallback) {
        this.mContext = (WeakReference<Context>)new WeakReference((Object)context);
        this.mViewBitmap = mViewBitmap;
        this.mCropRect = imageState.getCropRect();
        this.mCurrentImageRect = imageState.getCurrentImageRect();
        this.mCurrentScale = imageState.getCurrentScale();
        this.mCurrentAngle = imageState.getCurrentAngle();
        this.mMaxResultImageSizeX = cropParameters.getMaxResultImageSizeX();
        this.mMaxResultImageSizeY = cropParameters.getMaxResultImageSizeY();
        this.mCompressFormat = cropParameters.getCompressFormat();
        this.mCompressQuality = cropParameters.getCompressQuality();
        this.mImageInputPath = cropParameters.getImageInputPath();
        this.mImageOutputPath = cropParameters.getImageOutputPath();
        this.mImageInputUri = cropParameters.getContentImageInputUri();
        this.mImageOutputUri = cropParameters.getContentImageOutputUri();
        this.mExifInfo = cropParameters.getExifInfo();
        this.mCropCallback = mCropCallback;
    }
    
    private void checkValidityCropBounds() {
        if (this.cropOffsetX < 0) {
            this.cropOffsetX = 0;
            this.mCroppedImageWidth = this.mViewBitmap.getWidth();
        }
        if (this.cropOffsetY < 0) {
            this.cropOffsetY = 0;
            this.mCroppedImageHeight = this.mViewBitmap.getHeight();
        }
    }
    
    private void copyExifForOutputFile(final Context context) throws IOException {
        final boolean hasContentScheme = BitmapLoadUtils.hasContentScheme(this.mImageInputUri);
        final boolean hasContentScheme2 = BitmapLoadUtils.hasContentScheme(this.mImageOutputUri);
        if (hasContentScheme && hasContentScheme2) {
            if (Build$VERSION.SDK_INT >= 21) {
                ImageHeaderParser.copyExif(context, this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageInputUri, this.mImageOutputUri);
            }
            else {
                Log.e("BitmapCropTask", "It is not possible to write exif info into file represented by \"content\" Uri if Android < LOLLIPOP");
            }
        }
        else if (hasContentScheme) {
            ImageHeaderParser.copyExif(context, this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageInputUri, this.mImageOutputPath);
        }
        else if (hasContentScheme2) {
            if (Build$VERSION.SDK_INT >= 21) {
                ImageHeaderParser.copyExif(context, new ExifInterface(this.mImageInputPath), this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageOutputUri);
            }
            else {
                Log.e("BitmapCropTask", "It is not possible to write exif info into file represented by \"content\" Uri if Android < LOLLIPOP");
            }
        }
        else {
            ImageHeaderParser.copyExif(new ExifInterface(this.mImageInputPath), this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageOutputPath);
        }
    }
    
    private boolean crop() throws IOException {
        final Context context = (Context)this.mContext.get();
        if (context == null) {
            return false;
        }
        if (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) {
            final float n = this.mCropRect.width() / this.mCurrentScale;
            final float n2 = this.mCropRect.height() / this.mCurrentScale;
            if (n > this.mMaxResultImageSizeX || n2 > this.mMaxResultImageSizeY) {
                final float min = Math.min(this.mMaxResultImageSizeX / n, this.mMaxResultImageSizeY / n2);
                final Bitmap mViewBitmap = this.mViewBitmap;
                final Bitmap scaledBitmap = Bitmap.createScaledBitmap(mViewBitmap, Math.round(mViewBitmap.getWidth() * min), Math.round(this.mViewBitmap.getHeight() * min), false);
                final Bitmap mViewBitmap2 = this.mViewBitmap;
                if (mViewBitmap2 != scaledBitmap) {
                    mViewBitmap2.recycle();
                }
                this.mViewBitmap = scaledBitmap;
                this.mCurrentScale /= min;
            }
        }
        if (this.mCurrentAngle != 0.0f) {
            final Matrix matrix = new Matrix();
            matrix.setRotate(this.mCurrentAngle, (float)(this.mViewBitmap.getWidth() / 2), (float)(this.mViewBitmap.getHeight() / 2));
            final Bitmap mViewBitmap3 = this.mViewBitmap;
            final Bitmap bitmap = Bitmap.createBitmap(mViewBitmap3, 0, 0, mViewBitmap3.getWidth(), this.mViewBitmap.getHeight(), matrix, true);
            final Bitmap mViewBitmap4 = this.mViewBitmap;
            if (mViewBitmap4 != bitmap) {
                mViewBitmap4.recycle();
            }
            this.mViewBitmap = bitmap;
        }
        this.cropOffsetX = Math.round((this.mCropRect.left - this.mCurrentImageRect.left) / this.mCurrentScale);
        this.cropOffsetY = Math.round((this.mCropRect.top - this.mCurrentImageRect.top) / this.mCurrentScale);
        this.mCroppedImageWidth = Math.round(this.mCropRect.width() / this.mCurrentScale);
        final int round = Math.round(this.mCropRect.height() / this.mCurrentScale);
        this.mCroppedImageHeight = round;
        final boolean shouldCrop = this.shouldCrop(this.mCroppedImageWidth, round);
        final StringBuilder sb = new StringBuilder();
        sb.append("Should crop: ");
        sb.append(shouldCrop);
        Log.i("BitmapCropTask", sb.toString());
        if (shouldCrop) {
            this.checkValidityCropBounds();
            this.saveImage(Bitmap.createBitmap(this.mViewBitmap, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight));
            if (this.mCompressFormat.equals((Object)Bitmap$CompressFormat.JPEG)) {
                this.copyExifForOutputFile(context);
            }
            return true;
        }
        if (Build$VERSION.SDK_INT >= 29 && FileUtils.isContent(this.mImageInputPath)) {
            FileUtils.writeFileFromIS(context.getContentResolver().openInputStream(Uri.parse(this.mImageInputPath)), (OutputStream)new FileOutputStream(this.mImageOutputPath));
        }
        else {
            FileUtils.copyFile(this.mImageInputPath, this.mImageOutputPath);
        }
        return false;
    }
    
    private void saveImage(Bitmap bitmap) {
        final Context context = (Context)this.mContext.get();
        if (context == null) {
            return;
        }
        Object o = null;
        Object o2 = null;
        Label_0151: {
            try {
                final OutputStream openOutputStream = context.getContentResolver().openOutputStream(this.mImageOutputUri);
                try {
                    o2 = new ByteArrayOutputStream();
                    try {
                        bitmap.compress(this.mCompressFormat, this.mCompressQuality, (OutputStream)o2);
                        openOutputStream.write(((ByteArrayOutputStream)o2).toByteArray());
                        bitmap.recycle();
                        BitmapLoadUtils.close((Closeable)openOutputStream);
                    }
                    catch (final IOException o) {}
                }
                catch (final IOException o) {}
                o2 = openOutputStream;
            }
            catch (final IOException o) {
                bitmap = null;
            }
            finally {
                bitmap = null;
                o2 = o;
                o = bitmap;
                break Label_0151;
            }
            try {
                Log.e("BitmapCropTask", ((IOException)o).getLocalizedMessage());
                BitmapLoadUtils.close((Closeable)o2);
                BitmapLoadUtils.close((Closeable)bitmap);
                return;
            }
            finally {
                o = bitmap;
            }
        }
        BitmapLoadUtils.close((Closeable)o2);
        BitmapLoadUtils.close((Closeable)o);
        throw;
    }
    
    private boolean shouldCrop(int round, final int n) {
        round = Math.round(Math.max(round, n) / 1000.0f);
        final boolean b = true;
        if (this.mMaxResultImageSizeX > 0) {
            final boolean b2 = b;
            if (this.mMaxResultImageSizeY > 0) {
                return b2;
            }
        }
        final float abs = Math.abs(this.mCropRect.left - this.mCurrentImageRect.left);
        final float n2 = (float)(round + 1);
        boolean b2 = b;
        if (abs <= n2) {
            b2 = b;
            if (Math.abs(this.mCropRect.top - this.mCurrentImageRect.top) <= n2) {
                b2 = b;
                if (Math.abs(this.mCropRect.bottom - this.mCurrentImageRect.bottom) <= n2) {
                    b2 = b;
                    if (Math.abs(this.mCropRect.right - this.mCurrentImageRect.right) <= n2) {
                        b2 = (this.mCurrentAngle != 0.0f && b);
                    }
                }
            }
        }
        return b2;
    }
    
    protected Throwable doInBackground(final Void... array) {
        final Bitmap mViewBitmap = this.mViewBitmap;
        if (mViewBitmap == null) {
            return (Throwable)new NullPointerException("ViewBitmap is null");
        }
        if (mViewBitmap.isRecycled()) {
            return (Throwable)new NullPointerException("ViewBitmap is recycled");
        }
        if (this.mCurrentImageRect.isEmpty()) {
            return (Throwable)new NullPointerException("CurrentImageRect is empty");
        }
        if (this.mImageOutputUri == null) {
            return (Throwable)new NullPointerException("ImageOutputUri is null");
        }
        try {
            this.crop();
            this.mViewBitmap = null;
            return null;
        }
        finally {
            return;
        }
    }
    
    protected void onPostExecute(final Throwable t) {
        final BitmapCropCallback mCropCallback = this.mCropCallback;
        if (mCropCallback != null) {
            if (t == null) {
                Uri uri;
                if (BitmapLoadUtils.hasContentScheme(this.mImageOutputUri)) {
                    uri = this.mImageOutputUri;
                }
                else {
                    uri = Uri.fromFile(new File(this.mImageOutputPath));
                }
                this.mCropCallback.onBitmapCropped(uri, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight);
            }
            else {
                mCropCallback.onCropFailure(t);
            }
        }
    }
}
