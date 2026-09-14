package com.luck.picture.lib.photoview;

import android.view.View;

class Compat
{
    public static void postOnAnimation(final View view, final Runnable runnable) {
        postOnAnimationJellyBean(view, runnable);
    }
    
    private static void postOnAnimationJellyBean(final View view, final Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
