package org.xutils.image;

import org.xutils.http.RequestParams;
import android.view.ViewGroup$LayoutParams;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import java.lang.reflect.Field;
import android.widget.ImageView;
import android.widget.ImageView$ScaleType;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap$Config;
import android.view.animation.Animation;

public class ImageOptions
{
    public static final ImageOptions DEFAULT;
    private Animation animation;
    private boolean autoRotate;
    private boolean circular;
    private boolean compress;
    private Bitmap$Config config;
    private boolean crop;
    private boolean fadeIn;
    private Drawable failureDrawable;
    private int failureDrawableId;
    private boolean forceLoadingDrawable;
    private int height;
    private boolean ignoreGif;
    private ImageView$ScaleType imageScaleType;
    private Drawable loadingDrawable;
    private int loadingDrawableId;
    private int maxHeight;
    private int maxWidth;
    private ParamsBuilder paramsBuilder;
    private ImageView$ScaleType placeholderScaleType;
    private int radius;
    private boolean square;
    private boolean useMemCache;
    private int width;
    
    static {
        DEFAULT = new ImageOptions();
    }
    
    protected ImageOptions() {
        this.maxWidth = 0;
        this.maxHeight = 0;
        this.width = 0;
        this.height = 0;
        this.crop = false;
        this.radius = 0;
        this.square = false;
        this.circular = false;
        this.autoRotate = false;
        this.compress = true;
        this.config = Bitmap$Config.RGB_565;
        this.ignoreGif = true;
        this.loadingDrawableId = 0;
        this.failureDrawableId = 0;
        this.loadingDrawable = null;
        this.failureDrawable = null;
        this.forceLoadingDrawable = true;
        this.placeholderScaleType = ImageView$ScaleType.CENTER_INSIDE;
        this.imageScaleType = ImageView$ScaleType.CENTER_CROP;
        this.fadeIn = false;
        this.animation = null;
        this.useMemCache = true;
    }
    
    private static int getImageViewFieldValue(final ImageView imageView, final String s) {
        final int n = 0;
        try {
            final Field declaredField = ImageView.class.getDeclaredField(s);
            declaredField.setAccessible(true);
            final int intValue = (int)declaredField.get((Object)imageView);
            int n2 = n;
            if (intValue > 0) {
                n2 = n;
                if (intValue < Integer.MAX_VALUE) {
                    n2 = intValue;
                }
            }
            return n2;
        }
        finally {
            return n;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ImageOptions imageOptions = (ImageOptions)o;
        if (this.maxWidth != imageOptions.maxWidth) {
            return false;
        }
        if (this.maxHeight != imageOptions.maxHeight) {
            return false;
        }
        if (this.width != imageOptions.width) {
            return false;
        }
        if (this.height != imageOptions.height) {
            return false;
        }
        if (this.crop != imageOptions.crop) {
            return false;
        }
        if (this.radius != imageOptions.radius) {
            return false;
        }
        if (this.square != imageOptions.square) {
            return false;
        }
        if (this.circular != imageOptions.circular) {
            return false;
        }
        if (this.autoRotate != imageOptions.autoRotate) {
            return false;
        }
        if (this.compress != imageOptions.compress) {
            return false;
        }
        if (this.config != imageOptions.config) {
            b = false;
        }
        return b;
    }
    
    public Animation getAnimation() {
        return this.animation;
    }
    
    public Bitmap$Config getConfig() {
        return this.config;
    }
    
    public Drawable getFailureDrawable(final ImageView imageView) {
        if (this.failureDrawable == null && this.failureDrawableId > 0 && imageView != null) {
            try {
                this.failureDrawable = imageView.getResources().getDrawable(this.failureDrawableId);
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
        }
        return this.failureDrawable;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public ImageView$ScaleType getImageScaleType() {
        return this.imageScaleType;
    }
    
    public Drawable getLoadingDrawable(final ImageView imageView) {
        if (this.loadingDrawable == null && this.loadingDrawableId > 0 && imageView != null) {
            try {
                this.loadingDrawable = imageView.getResources().getDrawable(this.loadingDrawableId);
            }
            finally {
                final Throwable t;
                LogUtil.e(t.getMessage(), t);
            }
        }
        return this.loadingDrawable;
    }
    
    public int getMaxHeight() {
        return this.maxHeight;
    }
    
    public int getMaxWidth() {
        return this.maxWidth;
    }
    
    public ParamsBuilder getParamsBuilder() {
        return this.paramsBuilder;
    }
    
    public ImageView$ScaleType getPlaceholderScaleType() {
        return this.placeholderScaleType;
    }
    
    public int getRadius() {
        return this.radius;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int hashCode() {
        final int maxWidth = this.maxWidth;
        final int maxHeight = this.maxHeight;
        final int width = this.width;
        final int height = this.height;
        final int crop = this.crop ? 1 : 0;
        final int radius = this.radius;
        final int square = this.square ? 1 : 0;
        final int circular = this.circular ? 1 : 0;
        final int autoRotate = this.autoRotate ? 1 : 0;
        final int compress = this.compress ? 1 : 0;
        final Bitmap$Config config = this.config;
        int hashCode;
        if (config != null) {
            hashCode = config.hashCode();
        }
        else {
            hashCode = 0;
        }
        return (((((((((maxWidth * 31 + maxHeight) * 31 + width) * 31 + height) * 31 + crop) * 31 + radius) * 31 + square) * 31 + circular) * 31 + autoRotate) * 31 + compress) * 31 + hashCode;
    }
    
    public boolean isAutoRotate() {
        return this.autoRotate;
    }
    
    public boolean isCircular() {
        return this.circular;
    }
    
    public boolean isCompress() {
        return this.compress;
    }
    
    public boolean isCrop() {
        return this.crop;
    }
    
    public boolean isFadeIn() {
        return this.fadeIn;
    }
    
    public boolean isForceLoadingDrawable() {
        return this.forceLoadingDrawable;
    }
    
    public boolean isIgnoreGif() {
        return this.ignoreGif;
    }
    
    public boolean isSquare() {
        return this.square;
    }
    
    public boolean isUseMemCache() {
        return this.useMemCache;
    }
    
    final void optimizeMaxSize(final ImageView imageView) {
        final int width = this.width;
        if (width > 0) {
            final int height = this.height;
            if (height > 0) {
                this.maxWidth = width;
                this.maxHeight = height;
                return;
            }
        }
        final int screenWidth = DensityUtil.getScreenWidth();
        final int screenHeight = DensityUtil.getScreenHeight();
        if (this.width < 0) {
            this.maxWidth = screenWidth * 3 / 2;
            this.compress = false;
        }
        if (this.height < 0) {
            this.maxHeight = screenHeight * 3 / 2;
            this.compress = false;
        }
        if (imageView == null && this.maxWidth <= 0 && this.maxHeight <= 0) {
            this.maxWidth = screenWidth;
            this.maxHeight = screenHeight;
        }
        else {
            final int maxWidth = this.maxWidth;
            final int maxHeight = this.maxHeight;
            int n = maxWidth;
            int imageViewFieldValue = maxHeight;
            if (imageView != null) {
                final ViewGroup$LayoutParams layoutParams = imageView.getLayoutParams();
                int n2 = maxWidth;
                int height2 = maxHeight;
                if (layoutParams != null) {
                    int n3;
                    if ((n3 = maxWidth) <= 0) {
                        if (layoutParams.width > 0) {
                            final int width2 = n3 = layoutParams.width;
                            if (this.width <= 0) {
                                this.width = width2;
                                n3 = width2;
                            }
                        }
                        else {
                            n3 = maxWidth;
                            if (layoutParams.width != -2) {
                                n3 = imageView.getWidth();
                            }
                        }
                    }
                    n2 = n3;
                    if ((height2 = maxHeight) <= 0) {
                        if (layoutParams.height > 0) {
                            final int height3 = layoutParams.height;
                            n2 = n3;
                            height2 = height3;
                            if (this.height <= 0) {
                                this.height = height3;
                                n2 = n3;
                                height2 = height3;
                            }
                        }
                        else {
                            n2 = n3;
                            height2 = maxHeight;
                            if (layoutParams.height != -2) {
                                height2 = imageView.getHeight();
                                n2 = n3;
                            }
                        }
                    }
                }
                int imageViewFieldValue2;
                if ((imageViewFieldValue2 = n2) <= 0) {
                    imageViewFieldValue2 = getImageViewFieldValue(imageView, "mMaxWidth");
                }
                n = imageViewFieldValue2;
                if ((imageViewFieldValue = height2) <= 0) {
                    imageViewFieldValue = getImageViewFieldValue(imageView, "mMaxHeight");
                    n = imageViewFieldValue2;
                }
            }
            int maxWidth2;
            if (n <= 0) {
                maxWidth2 = screenWidth;
            }
            else {
                maxWidth2 = n;
            }
            if (imageViewFieldValue <= 0) {
                imageViewFieldValue = screenHeight;
            }
            this.maxWidth = maxWidth2;
            this.maxHeight = imageViewFieldValue;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_");
        sb.append(this.maxWidth);
        sb.append("_");
        sb.append(this.maxHeight);
        sb.append("_");
        sb.append(this.width);
        sb.append("_");
        sb.append(this.height);
        sb.append("_");
        sb.append(this.radius);
        sb.append("_");
        sb.append((Object)this.config);
        sb.append("_");
        sb.append((int)(this.crop ? 1 : 0));
        sb.append((int)(this.square ? 1 : 0));
        sb.append((int)(this.circular ? 1 : 0));
        sb.append((int)(this.autoRotate ? 1 : 0));
        sb.append((int)(this.compress ? 1 : 0));
        return sb.toString();
    }
    
    public static class Builder
    {
        protected ImageOptions options;
        
        public Builder() {
            this.newImageOptions();
        }
        
        public ImageOptions build() {
            return this.options;
        }
        
        protected void newImageOptions() {
            this.options = new ImageOptions();
        }
        
        public Builder setAnimation(final Animation animation) {
            this.options.animation = animation;
            return this;
        }
        
        public Builder setAutoRotate(final boolean b) {
            this.options.autoRotate = b;
            return this;
        }
        
        public Builder setCircular(final boolean b) {
            this.options.circular = b;
            return this;
        }
        
        public Builder setConfig(final Bitmap$Config bitmap$Config) {
            this.options.config = bitmap$Config;
            return this;
        }
        
        public Builder setCrop(final boolean b) {
            this.options.crop = b;
            return this;
        }
        
        public Builder setFadeIn(final boolean b) {
            this.options.fadeIn = b;
            return this;
        }
        
        public Builder setFailureDrawable(final Drawable drawable) {
            this.options.failureDrawable = drawable;
            return this;
        }
        
        public Builder setFailureDrawableId(final int n) {
            this.options.failureDrawableId = n;
            return this;
        }
        
        public Builder setForceLoadingDrawable(final boolean b) {
            this.options.forceLoadingDrawable = b;
            return this;
        }
        
        public Builder setIgnoreGif(final boolean b) {
            this.options.ignoreGif = b;
            return this;
        }
        
        public Builder setImageScaleType(final ImageView$ScaleType imageView$ScaleType) {
            this.options.imageScaleType = imageView$ScaleType;
            return this;
        }
        
        public Builder setLoadingDrawable(final Drawable drawable) {
            this.options.loadingDrawable = drawable;
            return this;
        }
        
        public Builder setLoadingDrawableId(final int n) {
            this.options.loadingDrawableId = n;
            return this;
        }
        
        public Builder setParamsBuilder(final ParamsBuilder paramsBuilder) {
            this.options.paramsBuilder = paramsBuilder;
            return this;
        }
        
        public Builder setPlaceholderScaleType(final ImageView$ScaleType imageView$ScaleType) {
            this.options.placeholderScaleType = imageView$ScaleType;
            return this;
        }
        
        public Builder setRadius(final int n) {
            this.options.radius = n;
            return this;
        }
        
        public Builder setSize(final int n, final int n2) {
            this.options.width = n;
            this.options.height = n2;
            return this;
        }
        
        public Builder setSquare(final boolean b) {
            this.options.square = b;
            return this;
        }
        
        public Builder setUseMemCache(final boolean b) {
            this.options.useMemCache = b;
            return this;
        }
    }
    
    public interface ParamsBuilder
    {
        RequestParams buildParams(final RequestParams p0, final ImageOptions p1);
    }
}
