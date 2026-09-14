package com.lzf.easyfloat.interfaces;

import kotlin.jvm.internal.Intrinsics;
import android.animation.Animator;
import com.lzf.easyfloat.enums.SidePattern;
import android.view.WindowManager;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J*\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r" }, d2 = { "Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;", "", "enterAnim", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "params", "Landroid/view/WindowManager$LayoutParams;", "windowManager", "Landroid/view/WindowManager;", "sidePattern", "Lcom/lzf/easyfloat/enums/SidePattern;", "exitAnim", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public interface OnFloatAnimator
{
    Animator enterAnim(final View p0, final WindowManager$LayoutParams p1, final WindowManager p2, final SidePattern p3);
    
    Animator exitAnim(final View p0, final WindowManager$LayoutParams p1, final WindowManager p2, final SidePattern p3);
    
    @Metadata(bv = { 1, 0, 3 }, k = 3, mv = { 1, 4, 1 })
    public static final class DefaultImpls
    {
        public static Animator enterAnim(final OnFloatAnimator onFloatAnimator, final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern) {
            Intrinsics.checkNotNullParameter((Object)view, "view");
            Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
            Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
            Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
            return null;
        }
        
        public static Animator exitAnim(final OnFloatAnimator onFloatAnimator, final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern) {
            Intrinsics.checkNotNullParameter((Object)view, "view");
            Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
            Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
            Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
            return null;
        }
    }
}
