package org.xutils.image;

import android.view.animation.Interpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import org.xutils.common.util.LogUtil;
import android.view.animation.Animation;
import java.lang.reflect.Method;

public final class ImageAnimationHelper
{
    private static final Method cloneMethod;
    
    static {
        Method cloneMethod2 = null;
        try {
            Animation.class.getDeclaredMethod("clone", (Class<?>[])new Class[0]).setAccessible(true);
        }
        finally {
            final Throwable t;
            LogUtil.w(t.getMessage(), t);
            cloneMethod2 = null;
        }
        cloneMethod = cloneMethod2;
    }
    
    private ImageAnimationHelper() {
    }
    
    public static void animationDisplay(final ImageView imageView, final Drawable imageDrawable, final Animation animation) {
        imageView.setImageDrawable(imageDrawable);
        final Method cloneMethod = ImageAnimationHelper.cloneMethod;
        if (cloneMethod != null && animation != null) {
            try {
                imageView.startAnimation((Animation)cloneMethod.invoke((Object)animation, new Object[0]));
            }
            finally {
                imageView.startAnimation(animation);
            }
        }
        else {
            imageView.startAnimation(animation);
        }
    }
    
    public static void fadeInDisplay(final ImageView imageView, final Drawable imageDrawable) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300L);
        alphaAnimation.setInterpolator((Interpolator)new DecelerateInterpolator());
        imageView.setImageDrawable(imageDrawable);
        imageView.startAnimation((Animation)alphaAnimation);
    }
}
