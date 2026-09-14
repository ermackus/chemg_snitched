package com.lzf.easyfloat.core;

import com.lzf.easyfloat.enums.SidePattern;
import kotlin.jvm.functions.Function2;
import android.view.ViewGroup$LayoutParams;
import android.view.MotionEvent;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.WindowManager;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.utils.DisplayUtils;
import android.view.WindowManager$LayoutParams;
import kotlin.jvm.functions.Function1;
import com.lzf.easyfloat.interfaces.FloatCallbacks;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import kotlin.Unit;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import android.graphics.Rect;
import android.content.Context;
import com.lzf.easyfloat.data.FloatConfig;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0018\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0002J \u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0002J&\u0010,\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010-\u001a\u00020.2\u0006\u0010*\u001a\u00020+2\u0006\u0010&\u001a\u00020'J\u001e\u0010,\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020+R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/" }, d2 = { "Lcom/lzf/easyfloat/core/TouchUtils;", "", "context", "Landroid/content/Context;", "config", "Lcom/lzf/easyfloat/data/FloatConfig;", "(Landroid/content/Context;Lcom/lzf/easyfloat/data/FloatConfig;)V", "bottomBorder", "", "bottomDistance", "getConfig", "()Lcom/lzf/easyfloat/data/FloatConfig;", "getContext", "()Landroid/content/Context;", "emptyHeight", "lastX", "", "lastY", "leftBorder", "leftDistance", "location", "", "minX", "minY", "parentHeight", "parentRect", "Landroid/graphics/Rect;", "parentWidth", "rightBorder", "rightDistance", "statusBarHeight", "topBorder", "topDistance", "dragEnd", "", "view", "Landroid/view/View;", "initBoarderValue", "params", "Landroid/view/WindowManager$LayoutParams;", "initDistanceValue", "sideAnim", "windowManager", "Landroid/view/WindowManager;", "updateFloat", "event", "Landroid/view/MotionEvent;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class TouchUtils
{
    private int bottomBorder;
    private int bottomDistance;
    private final FloatConfig config;
    private final Context context;
    private int emptyHeight;
    private float lastX;
    private float lastY;
    private int leftBorder;
    private int leftDistance;
    private final int[] location;
    private int minX;
    private int minY;
    private int parentHeight;
    private Rect parentRect;
    private int parentWidth;
    private int rightBorder;
    private int rightDistance;
    private int statusBarHeight;
    private int topBorder;
    private int topDistance;
    
    public TouchUtils(final Context context, final FloatConfig config) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        Intrinsics.checkNotNullParameter((Object)config, "config");
        this.context = context;
        this.config = config;
        this.parentRect = new Rect();
        this.location = new int[2];
    }
    
    private final void dragEnd(final View view) {
        this.config.setAnim(false);
        final OnFloatCallbacks callbacks = this.config.getCallbacks();
        if (callbacks != null) {
            callbacks.dragEnd(view);
        }
        final FloatCallbacks floatCallbacks = this.config.getFloatCallbacks();
        if (floatCallbacks != null) {
            final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
            if (builder != null) {
                final Function1<View, Unit> dragEnd$easyfloat_release = builder.getDragEnd$easyfloat_release();
                if (dragEnd$easyfloat_release != null) {
                    final Unit unit = (Unit)dragEnd$easyfloat_release.invoke((Object)view);
                }
            }
        }
    }
    
    private final void initBoarderValue(final View view, final WindowManager$LayoutParams windowManager$LayoutParams) {
        this.parentWidth = DisplayUtils.INSTANCE.getScreenWidth(this.context);
        this.parentHeight = this.config.getDisplayHeight().getDisplayRealHeight(this.context);
        view.getLocationOnScreen(this.location);
        int statusBarHeight;
        if (this.location[1] > windowManager$LayoutParams.y) {
            statusBarHeight = this.statusBarHeight(view);
        }
        else {
            statusBarHeight = 0;
        }
        this.statusBarHeight = statusBarHeight;
        this.emptyHeight = this.parentHeight - view.getHeight() - this.statusBarHeight;
        this.leftBorder = Math.max(0, this.config.getLeftBorder());
        this.rightBorder = Math.min(this.parentWidth, this.config.getRightBorder()) - view.getWidth();
        int topBorder;
        if (this.config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY) {
            if (this.config.getImmersionStatusBar()) {
                topBorder = this.config.getTopBorder();
            }
            else {
                topBorder = this.config.getTopBorder() + this.statusBarHeight(view);
            }
        }
        else if (this.config.getImmersionStatusBar()) {
            topBorder = this.config.getTopBorder() - this.statusBarHeight(view);
        }
        else {
            topBorder = this.config.getTopBorder();
        }
        this.topBorder = topBorder;
        int bottomBorder;
        if (this.config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY) {
            if (this.config.getImmersionStatusBar()) {
                bottomBorder = Math.min(this.emptyHeight, this.config.getBottomBorder() - view.getHeight());
            }
            else {
                bottomBorder = Math.min(this.emptyHeight, this.config.getBottomBorder() + this.statusBarHeight(view) - view.getHeight());
            }
        }
        else if (this.config.getImmersionStatusBar()) {
            bottomBorder = Math.min(this.emptyHeight, this.config.getBottomBorder() - this.statusBarHeight(view) - view.getHeight());
        }
        else {
            bottomBorder = Math.min(this.emptyHeight, this.config.getBottomBorder() - view.getHeight());
        }
        this.bottomBorder = bottomBorder;
    }
    
    private final void initDistanceValue(final WindowManager$LayoutParams windowManager$LayoutParams) {
        this.leftDistance = windowManager$LayoutParams.x - this.leftBorder;
        this.rightDistance = this.rightBorder - windowManager$LayoutParams.x;
        this.topDistance = windowManager$LayoutParams.y - this.topBorder;
        this.bottomDistance = this.bottomBorder - windowManager$LayoutParams.y;
        this.minX = Math.min(this.leftDistance, this.rightDistance);
        this.minY = Math.min(this.topDistance, this.bottomDistance);
    }
    
    private final void sideAnim(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager) {
        this.initDistanceValue(windowManager$LayoutParams);
        int n = 0;
        boolean b = false;
        Label_0256: {
            Label_0253: {
                int n2 = 0;
                int n3 = 0;
                Label_0237: {
                    switch (TouchUtils$WhenMappings.$EnumSwitchMapping$2[this.config.getSidePattern().ordinal()]) {
                        default: {
                            return;
                        }
                        case 7: {
                            if (this.minX < this.minY) {
                                if (this.leftDistance < this.rightDistance) {
                                    n = this.leftBorder;
                                    break Label_0253;
                                }
                                n2 = windowManager$LayoutParams.x;
                                n3 = this.rightDistance;
                                break Label_0237;
                            }
                            else {
                                if (this.topDistance < this.bottomDistance) {
                                    n = this.topBorder;
                                    break;
                                }
                                n = this.bottomBorder;
                                break;
                            }
                            break;
                        }
                        case 6: {
                            if (this.topDistance < this.bottomDistance) {
                                n = this.topBorder;
                                break;
                            }
                            n = this.bottomBorder;
                            break;
                        }
                        case 5: {
                            n = this.bottomBorder;
                            break;
                        }
                        case 4: {
                            n = this.topBorder;
                            break;
                        }
                        case 3: {
                            if (this.leftDistance < this.rightDistance) {
                                n = this.leftBorder;
                                break Label_0253;
                            }
                            n2 = windowManager$LayoutParams.x;
                            n3 = this.rightDistance;
                            break Label_0237;
                        }
                        case 2: {
                            n2 = windowManager$LayoutParams.x;
                            n3 = this.rightDistance;
                            break Label_0237;
                        }
                        case 1: {
                            n = this.leftBorder;
                            break Label_0253;
                        }
                    }
                    b = false;
                    break Label_0256;
                }
                n = n2 + n3;
            }
            b = true;
        }
        int n4;
        if (b) {
            n4 = windowManager$LayoutParams.x;
        }
        else {
            n4 = windowManager$LayoutParams.y;
        }
        final ValueAnimator ofInt = ValueAnimator.ofInt(new int[] { n4, n });
        ofInt.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new TouchUtils$sideAnim.TouchUtils$sideAnim$1(b, windowManager$LayoutParams, windowManager, view, ofInt));
        ofInt.addListener((Animator$AnimatorListener)new TouchUtils$sideAnim.TouchUtils$sideAnim$2(this, view));
        ofInt.start();
    }
    
    private final int statusBarHeight(final View view) {
        return DisplayUtils.INSTANCE.statusBarHeight(view);
    }
    
    public final FloatConfig getConfig() {
        return this.config;
    }
    
    public final Context getContext() {
        return this.context;
    }
    
    public final void updateFloat(final View view, final MotionEvent motionEvent, final WindowManager windowManager, final WindowManager$LayoutParams windowManager$LayoutParams) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        Intrinsics.checkNotNullParameter((Object)motionEvent, "event");
        Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
        Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
        final OnFloatCallbacks callbacks = this.config.getCallbacks();
        if (callbacks != null) {
            callbacks.touchEvent(view, motionEvent);
        }
        final FloatCallbacks floatCallbacks = this.config.getFloatCallbacks();
        if (floatCallbacks != null) {
            final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
            if (builder != null) {
                final Function2<View, MotionEvent, Unit> touchEvent$easyfloat_release = builder.getTouchEvent$easyfloat_release();
                if (touchEvent$easyfloat_release != null) {
                    final Unit unit = (Unit)touchEvent$easyfloat_release.invoke((Object)view, (Object)motionEvent);
                }
            }
        }
        final boolean dragEnable = this.config.getDragEnable();
        final boolean b = false;
        final int n = 0;
        if (dragEnable && !this.config.isAnim()) {
            final int n2 = motionEvent.getAction() & 0xFF;
            if (n2 != 0) {
                if (n2 != 1) {
                    if (n2 != 2) {
                        if (n2 != 3) {
                            return;
                        }
                    }
                    else {
                        if (motionEvent.getRawX() < this.leftBorder || motionEvent.getRawX() > this.rightBorder + view.getWidth() || motionEvent.getRawY() < this.topBorder || motionEvent.getRawY() > this.bottomBorder + view.getHeight()) {
                            return;
                        }
                        final float n3 = motionEvent.getRawX() - this.lastX;
                        final float n4 = motionEvent.getRawY() - this.lastY;
                        if (!this.config.isDrag() && n3 * n3 + n4 * n4 < 81) {
                            return;
                        }
                        this.config.setDrag(true);
                        final int n5 = windowManager$LayoutParams.x + (int)n3;
                        final int n6 = windowManager$LayoutParams.y + (int)n4;
                        final int leftBorder = this.leftBorder;
                        int n8 = 0;
                        Label_0365: {
                            int n7;
                            if (n5 < leftBorder) {
                                n7 = leftBorder;
                            }
                            else {
                                final int rightBorder = this.rightBorder;
                                if ((n8 = n5) <= rightBorder) {
                                    break Label_0365;
                                }
                                n7 = rightBorder;
                            }
                            n8 = n7;
                        }
                        int statusBarHeight = n6;
                        if (this.config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY && (statusBarHeight = n6) < this.statusBarHeight(view)) {
                            statusBarHeight = n6;
                            if (!this.config.getImmersionStatusBar()) {
                                statusBarHeight = this.statusBarHeight(view);
                            }
                        }
                        int n9 = this.topBorder;
                        if (statusBarHeight >= n9) {
                            if (statusBarHeight < 0) {
                                if (this.config.getImmersionStatusBar()) {
                                    final int statusBarHeight2 = this.statusBarHeight;
                                    if ((n9 = statusBarHeight) < -statusBarHeight2) {
                                        n9 = -statusBarHeight2;
                                    }
                                }
                                else {
                                    n9 = 0;
                                }
                            }
                            else {
                                final int bottomBorder = this.bottomBorder;
                                if ((n9 = statusBarHeight) > bottomBorder) {
                                    n9 = bottomBorder;
                                }
                            }
                        }
                        final SidePattern sidePattern = this.config.getSidePattern();
                        int y = n9;
                        int x = b ? 1 : 0;
                    Label_0903:
                        while (true) {
                            int n10 = 0;
                            int n11 = 0;
                        Label_0892:
                            while (true) {
                                switch (TouchUtils$WhenMappings.$EnumSwitchMapping$0[sidePattern.ordinal()]) {
                                    default: {
                                        x = n8;
                                        y = n9;
                                        break Label_0903;
                                    }
                                    case 7: {
                                        this.leftDistance = (int)motionEvent.getRawX();
                                        this.rightDistance = this.parentWidth - (int)motionEvent.getRawX();
                                        this.topDistance = (int)motionEvent.getRawY() - this.parentRect.top;
                                        this.bottomDistance = this.parentHeight + this.parentRect.top - (int)motionEvent.getRawY();
                                        this.minX = Math.min(this.leftDistance, this.rightDistance);
                                        final int min = Math.min(this.topDistance, this.bottomDistance);
                                        this.minY = min;
                                        final int minX = this.minX;
                                        if (minX < min) {
                                            if (this.leftDistance == minX) {
                                                y = n9;
                                                x = (b ? 1 : 0);
                                                break Label_0903;
                                            }
                                            n10 = this.parentWidth;
                                            n11 = view.getWidth();
                                            break Label_0892;
                                        }
                                        else {
                                            if (this.topDistance == min) {
                                                n9 = n;
                                                break;
                                            }
                                            n9 = this.emptyHeight;
                                            break;
                                        }
                                        break;
                                    }
                                    case 6: {
                                        final float rawY = motionEvent.getRawY();
                                        final float n12 = (float)this.parentRect.top;
                                        final float n13 = 2;
                                        final int parentHeight = this.parentHeight;
                                        n9 = n;
                                        if ((rawY - n12) * n13 > parentHeight) {
                                            n9 = parentHeight - view.getHeight();
                                            break;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        final float rawX = motionEvent.getRawX();
                                        final float n14 = 2;
                                        final int parentWidth = this.parentWidth;
                                        y = n9;
                                        x = (b ? 1 : 0);
                                        if (rawX * n14 > parentWidth) {
                                            x = parentWidth - view.getWidth();
                                            y = n9;
                                        }
                                        break Label_0903;
                                    }
                                    case 4: {
                                        n9 = this.emptyHeight;
                                        break;
                                    }
                                    case 2: {
                                        n10 = this.parentWidth;
                                        n11 = view.getWidth();
                                        break Label_0892;
                                    }
                                    case 1: {
                                        windowManager$LayoutParams.x = x;
                                        windowManager$LayoutParams.y = y;
                                        windowManager.updateViewLayout(view, (ViewGroup$LayoutParams)windowManager$LayoutParams);
                                        final OnFloatCallbacks callbacks2 = this.config.getCallbacks();
                                        if (callbacks2 != null) {
                                            callbacks2.drag(view, motionEvent);
                                        }
                                        final FloatCallbacks floatCallbacks2 = this.config.getFloatCallbacks();
                                        if (floatCallbacks2 != null) {
                                            final FloatCallbacks.Builder builder2 = floatCallbacks2.getBuilder();
                                            if (builder2 != null) {
                                                final Function2<View, MotionEvent, Unit> drag$easyfloat_release = builder2.getDrag$easyfloat_release();
                                                if (drag$easyfloat_release != null) {
                                                    final Unit unit2 = (Unit)drag$easyfloat_release.invoke((Object)view, (Object)motionEvent);
                                                }
                                            }
                                        }
                                        this.lastX = motionEvent.getRawX();
                                        this.lastY = motionEvent.getRawY();
                                        return;
                                    }
                                    case 3: {
                                        x = n8;
                                        y = 0;
                                        continue Label_0903;
                                    }
                                }
                                continue;
                            }
                            x = n10 - n11;
                            y = n9;
                            continue Label_0903;
                        }
                    }
                }
                if (!this.config.isDrag()) {
                    return;
                }
                final OnFloatCallbacks callbacks3 = this.config.getCallbacks();
                if (callbacks3 != null) {
                    callbacks3.drag(view, motionEvent);
                }
                final FloatCallbacks floatCallbacks3 = this.config.getFloatCallbacks();
                if (floatCallbacks3 != null) {
                    final FloatCallbacks.Builder builder3 = floatCallbacks3.getBuilder();
                    if (builder3 != null) {
                        final Function2<View, MotionEvent, Unit> drag$easyfloat_release2 = builder3.getDrag$easyfloat_release();
                        if (drag$easyfloat_release2 != null) {
                            final Unit unit3 = (Unit)drag$easyfloat_release2.invoke((Object)view, (Object)motionEvent);
                        }
                    }
                }
                switch (TouchUtils$WhenMappings.$EnumSwitchMapping$1[this.config.getSidePattern().ordinal()]) {
                    default: {
                        final OnFloatCallbacks callbacks4 = this.config.getCallbacks();
                        if (callbacks4 != null) {
                            callbacks4.dragEnd(view);
                        }
                        final FloatCallbacks floatCallbacks4 = this.config.getFloatCallbacks();
                        if (floatCallbacks4 == null) {
                            break;
                        }
                        final FloatCallbacks.Builder builder4 = floatCallbacks4.getBuilder();
                        if (builder4 == null) {
                            break;
                        }
                        final Function1<View, Unit> dragEnd$easyfloat_release = builder4.getDragEnd$easyfloat_release();
                        if (dragEnd$easyfloat_release != null) {
                            final Unit unit4 = (Unit)dragEnd$easyfloat_release.invoke((Object)view);
                            break;
                        }
                        break;
                    }
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7: {
                        this.sideAnim(view, windowManager$LayoutParams, windowManager);
                        break;
                    }
                }
            }
            else {
                this.config.setDrag(false);
                this.lastX = motionEvent.getRawX();
                this.lastY = motionEvent.getRawY();
                this.initBoarderValue(view, windowManager$LayoutParams);
            }
            return;
        }
        this.config.setDrag(false);
    }
    
    public final void updateFloat(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
        Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
        this.initBoarderValue(view, windowManager$LayoutParams);
        this.sideAnim(view, windowManager$LayoutParams, windowManager);
    }
}
