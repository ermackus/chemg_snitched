package com.lzf.easyfloat.anim;

import com.lzf.easyfloat.interfaces.OnFloatAnimator;
import android.animation.Animator;
import kotlin.jvm.internal.Intrinsics;
import android.view.WindowManager;
import android.view.View;
import android.view.WindowManager$LayoutParams;
import com.lzf.easyfloat.data.FloatConfig;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\r\u001a\u0004\u0018\u00010\fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e" }, d2 = { "Lcom/lzf/easyfloat/anim/AnimatorManager;", "", "view", "Landroid/view/View;", "params", "Landroid/view/WindowManager$LayoutParams;", "windowManager", "Landroid/view/WindowManager;", "config", "Lcom/lzf/easyfloat/data/FloatConfig;", "(Landroid/view/View;Landroid/view/WindowManager$LayoutParams;Landroid/view/WindowManager;Lcom/lzf/easyfloat/data/FloatConfig;)V", "enterAnim", "Landroid/animation/Animator;", "exitAnim", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class AnimatorManager
{
    private final FloatConfig config;
    private final WindowManager$LayoutParams params;
    private final View view;
    private final WindowManager windowManager;
    
    public AnimatorManager(final View view, final WindowManager$LayoutParams params, final WindowManager windowManager, final FloatConfig config) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        Intrinsics.checkNotNullParameter((Object)params, "params");
        Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
        Intrinsics.checkNotNullParameter((Object)config, "config");
        this.view = view;
        this.params = params;
        this.windowManager = windowManager;
        this.config = config;
    }
    
    public final Animator enterAnim() {
        final OnFloatAnimator floatAnimator = this.config.getFloatAnimator();
        Animator enterAnim;
        if (floatAnimator != null) {
            enterAnim = floatAnimator.enterAnim(this.view, this.params, this.windowManager, this.config.getSidePattern());
        }
        else {
            enterAnim = null;
        }
        return enterAnim;
    }
    
    public final Animator exitAnim() {
        final OnFloatAnimator floatAnimator = this.config.getFloatAnimator();
        Animator exitAnim;
        if (floatAnimator != null) {
            exitAnim = floatAnimator.exitAnim(this.view, this.params, this.windowManager, this.config.getSidePattern());
        }
        else {
            exitAnim = null;
        }
        return exitAnim;
    }
}
