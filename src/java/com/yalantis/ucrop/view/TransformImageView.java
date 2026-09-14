package com.yalantis.ucrop.view;

import com.yalantis.ucrop.util.FileUtils;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.util.RectUtils;
import android.graphics.RectF;
import android.widget.ImageView$ScaleType;
import com.yalantis.ucrop.util.FastBitmapDrawable;
import android.util.Log;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import android.graphics.Bitmap;
import com.yalantis.ucrop.UCropImageEngine$OnCallbackListener;
import com.yalantis.ucrop.UCropDevelopConfig;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.net.Uri;
import com.yalantis.ucrop.model.ExifInfo;
import android.graphics.Matrix;
import androidx.appcompat.widget.AppCompatImageView;

public class TransformImageView extends AppCompatImageView
{
    private static final int MATRIX_VALUES_COUNT = 9;
    private static final int RECT_CENTER_POINT_COORDS = 2;
    private static final int RECT_CORNER_POINTS_COORDS = 8;
    private static final String TAG = "TransformImageView";
    protected boolean mBitmapDecoded;
    protected boolean mBitmapLaidOut;
    protected final float[] mCurrentImageCenter;
    protected final float[] mCurrentImageCorners;
    protected Matrix mCurrentImageMatrix;
    private ExifInfo mExifInfo;
    private String mImageInputPath;
    private Uri mImageInputUri;
    private String mImageOutputPath;
    private Uri mImageOutputUri;
    private float[] mInitialImageCenter;
    private float[] mInitialImageCorners;
    private final float[] mMatrixValues;
    private int mMaxBitmapSize;
    protected int mThisHeight;
    protected int mThisWidth;
    protected TransformImageListener mTransformImageListener;
    
    public TransformImageView(final Context context) {
        this(context, null);
    }
    
    public TransformImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public TransformImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mCurrentImageCorners = new float[8];
        this.mCurrentImageCenter = new float[2];
        this.mMatrixValues = new float[9];
        this.mCurrentImageMatrix = new Matrix();
        this.mBitmapDecoded = false;
        this.mBitmapLaidOut = false;
        this.mMaxBitmapSize = 0;
        this.init();
    }
    
    private void updateCurrentImagePoints() {
        this.mCurrentImageMatrix.mapPoints(this.mCurrentImageCorners, this.mInitialImageCorners);
        this.mCurrentImageMatrix.mapPoints(this.mCurrentImageCenter, this.mInitialImageCenter);
    }
    
    private void useCustomLoaderCrop(final Uri uri, final Uri uri2) {
        final int[] maxImageSize = BitmapLoadUtils.getMaxImageSize(this.getContext(), uri);
        if (maxImageSize[0] > 0 && maxImageSize[1] > 0) {
            UCropDevelopConfig.imageEngine.loadImage(this.getContext(), uri, maxImageSize[0], maxImageSize[1], (UCropImageEngine$OnCallbackListener)new UCropImageEngine$OnCallbackListener<Bitmap>(this, uri, uri2) {
                final TransformImageView this$0;
                final Uri val$imageUri;
                final Uri val$outputUri;
                
                public void onCall(Bitmap copy) {
                    if (copy == null) {
                        this.this$0.useDefaultLoaderCrop(this.val$imageUri, this.val$outputUri);
                    }
                    else {
                        copy = copy.copy(copy.getConfig(), true);
                        this.this$0.setBitmapLoadedResult(copy, new ExifInfo(0, 0, 0), this.val$imageUri, this.val$outputUri);
                    }
                }
            });
        }
        else {
            this.useDefaultLoaderCrop(uri, uri2);
        }
    }
    
    private void useDefaultLoaderCrop(final Uri uri, final Uri uri2) {
        final int maxBitmapSize = this.getMaxBitmapSize();
        BitmapLoadUtils.decodeBitmapInBackground(this.getContext(), uri, uri2, maxBitmapSize, maxBitmapSize, (BitmapLoadCallback)new BitmapLoadCallback(this) {
            final TransformImageView this$0;
            
            public void onBitmapLoaded(final Bitmap bitmap, final ExifInfo exifInfo, final Uri uri, final Uri uri2) {
                this.this$0.setBitmapLoadedResult(bitmap, exifInfo, uri, uri2);
            }
            
            public void onFailure(final Exception ex) {
                Log.e("TransformImageView", "onFailure: setImageUri", (Throwable)ex);
                if (this.this$0.mTransformImageListener != null) {
                    this.this$0.mTransformImageListener.onLoadFailure(ex);
                }
            }
        });
    }
    
    public float getCurrentAngle() {
        return this.getMatrixAngle(this.mCurrentImageMatrix);
    }
    
    public float getCurrentScale() {
        return this.getMatrixScale(this.mCurrentImageMatrix);
    }
    
    public ExifInfo getExifInfo() {
        return this.mExifInfo;
    }
    
    public String getImageInputPath() {
        return this.mImageInputPath;
    }
    
    public Uri getImageInputUri() {
        return this.mImageInputUri;
    }
    
    public String getImageOutputPath() {
        return this.mImageOutputPath;
    }
    
    public Uri getImageOutputUri() {
        return this.mImageOutputUri;
    }
    
    public float getMatrixAngle(final Matrix matrix) {
        return (float)(-(Math.atan2((double)this.getMatrixValue(matrix, 1), (double)this.getMatrixValue(matrix, 0)) * 57.29577951308232));
    }
    
    public float getMatrixScale(final Matrix matrix) {
        return (float)Math.sqrt(Math.pow((double)this.getMatrixValue(matrix, 0), 2.0) + Math.pow((double)this.getMatrixValue(matrix, 3), 2.0));
    }
    
    protected float getMatrixValue(final Matrix matrix, final int n) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[n];
    }
    
    public int getMaxBitmapSize() {
        if (this.mMaxBitmapSize <= 0) {
            this.mMaxBitmapSize = BitmapLoadUtils.calculateMaxBitmapSize(this.getContext());
        }
        return this.mMaxBitmapSize;
    }
    
    public Bitmap getViewBitmap() {
        if (this.getDrawable() != null && this.getDrawable() instanceof FastBitmapDrawable) {
            return ((FastBitmapDrawable)this.getDrawable()).getBitmap();
        }
        return null;
    }
    
    protected void init() {
        this.setScaleType(ImageView$ScaleType.MATRIX);
    }
    
    protected void onImageLaidOut() {
        final Drawable drawable = this.getDrawable();
        if (drawable == null) {
            return;
        }
        final float n = (float)drawable.getIntrinsicWidth();
        final float n2 = (float)drawable.getIntrinsicHeight();
        Log.d("TransformImageView", String.format("Image size: [%d:%d]", new Object[] { (int)n, (int)n2 }));
        final RectF rectF = new RectF(0.0f, 0.0f, n, n2);
        this.mInitialImageCorners = RectUtils.getCornersFromRect(rectF);
        this.mInitialImageCenter = RectUtils.getCenterFromRect(rectF);
        this.mBitmapLaidOut = true;
        final TransformImageListener mTransformImageListener = this.mTransformImageListener;
        if (mTransformImageListener != null) {
            mTransformImageListener.onLoadComplete();
        }
    }
    
    protected void onLayout(final boolean b, int width, int paddingRight, int paddingBottom, int paddingLeft) {
        super.onLayout(b, width, paddingRight, paddingBottom, paddingLeft);
        if (b || (this.mBitmapDecoded && !this.mBitmapLaidOut)) {
            paddingLeft = this.getPaddingLeft();
            final int paddingTop = this.getPaddingTop();
            width = this.getWidth();
            paddingRight = this.getPaddingRight();
            final int height = this.getHeight();
            paddingBottom = this.getPaddingBottom();
            this.mThisWidth = width - paddingRight - paddingLeft;
            this.mThisHeight = height - paddingBottom - paddingTop;
            this.onImageLaidOut();
        }
    }
    
    public void postRotate(final float n, final float n2, final float n3) {
        if (n != 0.0f) {
            this.mCurrentImageMatrix.postRotate(n, n2, n3);
            this.setImageMatrix(this.mCurrentImageMatrix);
            final TransformImageListener mTransformImageListener = this.mTransformImageListener;
            if (mTransformImageListener != null) {
                mTransformImageListener.onRotate(this.getMatrixAngle(this.mCurrentImageMatrix));
            }
        }
    }
    
    public void postScale(final float n, final float n2, final float n3) {
        if (n != 0.0f) {
            this.mCurrentImageMatrix.postScale(n, n, n2, n3);
            this.setImageMatrix(this.mCurrentImageMatrix);
            final TransformImageListener mTransformImageListener = this.mTransformImageListener;
            if (mTransformImageListener != null) {
                mTransformImageListener.onScale(this.getMatrixScale(this.mCurrentImageMatrix));
            }
        }
    }
    
    public void postTranslate(final float n, final float n2) {
        if (n != 0.0f || n2 != 0.0f) {
            this.mCurrentImageMatrix.postTranslate(n, n2);
            this.setImageMatrix(this.mCurrentImageMatrix);
        }
    }
    
    protected void printMatrix(final String s, final Matrix matrix) {
        final float matrixValue = this.getMatrixValue(matrix, 2);
        final float matrixValue2 = this.getMatrixValue(matrix, 5);
        final float matrixScale = this.getMatrixScale(matrix);
        final float matrixAngle = this.getMatrixAngle(matrix);
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(": matrix: { x: ");
        sb.append(matrixValue);
        sb.append(", y: ");
        sb.append(matrixValue2);
        sb.append(", scale: ");
        sb.append(matrixScale);
        sb.append(", angle: ");
        sb.append(matrixAngle);
        sb.append(" }");
        Log.d("TransformImageView", sb.toString());
    }
    
    public void setBitmapLoadedResult(final Bitmap imageBitmap, final ExifInfo mExifInfo, final Uri mImageInputUri, final Uri mImageOutputUri) {
        this.mImageInputUri = mImageInputUri;
        this.mImageOutputUri = mImageOutputUri;
        String mImageInputPath;
        if (FileUtils.isContent(mImageInputUri.toString())) {
            mImageInputPath = mImageInputUri.toString();
        }
        else {
            mImageInputPath = mImageInputUri.getPath();
        }
        this.mImageInputPath = mImageInputPath;
        String mImageOutputPath;
        if (mImageOutputUri != null) {
            if (FileUtils.isContent(mImageOutputUri.toString())) {
                mImageOutputPath = mImageOutputUri.toString();
            }
            else {
                mImageOutputPath = mImageOutputUri.getPath();
            }
        }
        else {
            mImageOutputPath = null;
        }
        this.mImageOutputPath = mImageOutputPath;
        this.mExifInfo = mExifInfo;
        this.mBitmapDecoded = true;
        this.setImageBitmap(imageBitmap);
    }
    
    public void setImageBitmap(final Bitmap bitmap) {
        this.setImageDrawable((Drawable)new FastBitmapDrawable(bitmap));
    }
    
    public void setImageMatrix(final Matrix imageMatrix) {
        super.setImageMatrix(imageMatrix);
        this.mCurrentImageMatrix.set(imageMatrix);
        this.updateCurrentImagePoints();
    }
    
    public void setImageUri(final Uri uri, final Uri uri2, final boolean b) {
        if (UCropDevelopConfig.imageEngine != null && b) {
            this.useCustomLoaderCrop(uri, uri2);
        }
        else {
            this.useDefaultLoaderCrop(uri, uri2);
        }
    }
    
    public void setMaxBitmapSize(final int mMaxBitmapSize) {
        this.mMaxBitmapSize = mMaxBitmapSize;
    }
    
    public void setScaleType(final ImageView$ScaleType scaleType) {
        if (scaleType == ImageView$ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        }
        else {
            Log.w("TransformImageView", "Invalid ScaleType. Only ScaleType.MATRIX can be used");
        }
    }
    
    public void setTransformImageListener(final TransformImageListener mTransformImageListener) {
        this.mTransformImageListener = mTransformImageListener;
    }
    
    public interface TransformImageListener
    {
        void onLoadComplete();
        
        void onLoadFailure(final Exception p0);
        
        void onRotate(final float p0);
        
        void onScale(final float p0);
    }
}
