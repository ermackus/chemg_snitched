package com.lzf.easyfloat.core;

import kotlin.jvm.functions.Function1;
import com.lzf.easyfloat.utils.Logger;
import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import kotlin.Pair;
import com.lzf.easyfloat.utils.DisplayUtils;
import android.os.Build$VERSION;
import com.lzf.easyfloat.enums.ShowPattern;
import android.view.Window;
import com.lzf.easyfloat.utils.LifecycleUtils;
import android.app.Activity;
import android.os.IBinder;
import android.animation.Animator$AnimatorListener;
import com.lzf.easyfloat.anim.AnimatorManager;
import kotlin.jvm.functions.Function3;
import com.lzf.easyfloat.interfaces.FloatCallbacks;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import kotlin.Unit;
import com.lzf.easyfloat.utils.InputMethodUtils;
import android.widget.EditText;
import com.lzf.easyfloat.interfaces.OnFloatTouchListener;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import kotlin.jvm.internal.DefaultConstructorMarker;
import android.util.AttributeSet;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import android.view.WindowManager;
import android.view.WindowManager$LayoutParams;
import com.lzf.easyfloat.widget.ParentFrameLayout;
import android.animation.Animator;
import android.content.Context;
import com.lzf.easyfloat.data.FloatConfig;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0002J\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020,H\u0002J\u0010\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020*H\u0002J\u0006\u00100\u001a\u00020'J\n\u00101\u001a\u0004\u0018\u000102H\u0002J\b\u00103\u001a\u00020'H\u0002J\b\u00104\u001a\u00020'H\u0002J\u0010\u00105\u001a\u00020'2\b\b\u0002\u00106\u001a\u00020,J\b\u00107\u001a\u00020'H\u0002J\u0012\u00108\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0003J\u0018\u00109\u001a\u00020'2\u0006\u0010:\u001a\u00020\u00162\b\b\u0002\u0010;\u001a\u00020,J\u0012\u0010<\u001a\u00020'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\u0016\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006@" }, d2 = { "Lcom/lzf/easyfloat/core/FloatingWindowHelper;", "", "context", "Landroid/content/Context;", "config", "Lcom/lzf/easyfloat/data/FloatConfig;", "(Landroid/content/Context;Lcom/lzf/easyfloat/data/FloatConfig;)V", "getConfig", "()Lcom/lzf/easyfloat/data/FloatConfig;", "setConfig", "(Lcom/lzf/easyfloat/data/FloatConfig;)V", "getContext", "()Landroid/content/Context;", "enterAnimator", "Landroid/animation/Animator;", "frameLayout", "Lcom/lzf/easyfloat/widget/ParentFrameLayout;", "getFrameLayout", "()Lcom/lzf/easyfloat/widget/ParentFrameLayout;", "setFrameLayout", "(Lcom/lzf/easyfloat/widget/ParentFrameLayout;)V", "lastLayoutMeasureHeight", "", "lastLayoutMeasureWidth", "params", "Landroid/view/WindowManager$LayoutParams;", "getParams", "()Landroid/view/WindowManager$LayoutParams;", "setParams", "(Landroid/view/WindowManager$LayoutParams;)V", "touchUtils", "Lcom/lzf/easyfloat/core/TouchUtils;", "windowManager", "Landroid/view/WindowManager;", "getWindowManager", "()Landroid/view/WindowManager;", "setWindowManager", "(Landroid/view/WindowManager;)V", "addView", "", "checkEditText", "view", "Landroid/view/View;", "createWindow", "", "createWindowInner", "enterAnim", "floatingView", "exitAnim", "getToken", "Landroid/os/IBinder;", "initEditText", "initParams", "remove", "force", "setChangedListener", "setGravity", "setVisible", "visible", "needShow", "traverseViewGroup", "updateFloat", "x", "y", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class FloatingWindowHelper
{
    private FloatConfig config;
    private final Context context;
    private Animator enterAnimator;
    private ParentFrameLayout frameLayout;
    private int lastLayoutMeasureHeight;
    private int lastLayoutMeasureWidth;
    public WindowManager$LayoutParams params;
    private TouchUtils touchUtils;
    public WindowManager windowManager;
    
    public FloatingWindowHelper(final Context context, final FloatConfig config) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        Intrinsics.checkNotNullParameter((Object)config, "config");
        this.context = context;
        this.config = config;
        this.lastLayoutMeasureWidth = -1;
        this.lastLayoutMeasureHeight = -1;
    }
    
    private final void addView() {
        final ParentFrameLayout frameLayout = new ParentFrameLayout(this.context, this.config, null, 0, 12, null);
        this.frameLayout = frameLayout;
        if (frameLayout != null) {
            frameLayout.setTag((Object)this.config.getFloatTag());
        }
        View view = this.config.getLayoutView();
        Label_0111: {
            if (view != null) {
                final ParentFrameLayout frameLayout2 = this.frameLayout;
                if (frameLayout2 != null) {
                    frameLayout2.addView(view);
                }
                if (view != null) {
                    break Label_0111;
                }
            }
            final LayoutInflater from = LayoutInflater.from(this.context);
            final Integer layoutId = this.config.getLayoutId();
            Intrinsics.checkNotNull((Object)layoutId);
            view = from.inflate((int)layoutId, (ViewGroup)this.frameLayout, true);
        }
        Intrinsics.checkNotNullExpressionValue((Object)view, "floatingView");
        view.setVisibility(4);
        final WindowManager windowManager = this.windowManager;
        if (windowManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("windowManager");
        }
        final View view2 = (View)this.frameLayout;
        final WindowManager$LayoutParams params = this.params;
        if (params == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
        }
        windowManager.addView(view2, (ViewGroup$LayoutParams)params);
        final ParentFrameLayout frameLayout3 = this.frameLayout;
        if (frameLayout3 != null) {
            frameLayout3.setTouchListener((OnFloatTouchListener)new FloatingWindowHelper$addView$1(this));
        }
        final ParentFrameLayout frameLayout4 = this.frameLayout;
        if (frameLayout4 != null) {
            frameLayout4.setLayoutListener((ParentFrameLayout.OnLayoutListener)new FloatingWindowHelper$addView$2(this, view));
        }
        this.setChangedListener();
    }
    
    private final void checkEditText(final View view) {
        if (view instanceof EditText) {
            InputMethodUtils.INSTANCE.initInputMethod$easyfloat_release((EditText)view, this.config.getFloatTag());
        }
    }
    
    private final boolean createWindowInner() {
        boolean b = true;
        try {
            this.touchUtils = new TouchUtils(this.context, this.config);
            this.initParams();
            this.addView();
            this.config.setShow(true);
        }
        catch (final Exception ex) {
            final OnFloatCallbacks callbacks = this.config.getCallbacks();
            if (callbacks != null) {
                callbacks.createdResult(false, String.valueOf((Object)ex), null);
            }
            final FloatCallbacks floatCallbacks = this.config.getFloatCallbacks();
            if (floatCallbacks != null) {
                final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
                if (builder != null) {
                    final Function3<Boolean, String, View, Unit> createdResult$easyfloat_release = builder.getCreatedResult$easyfloat_release();
                    if (createdResult$easyfloat_release != null) {
                        final Unit unit = (Unit)createdResult$easyfloat_release.invoke((Object)false, (Object)String.valueOf((Object)ex), (Object)null);
                    }
                }
            }
            b = false;
        }
        return b;
    }
    
    private final void enterAnim(final View view) {
        if (this.frameLayout != null) {
            if (!this.config.isAnim()) {
                final ParentFrameLayout frameLayout = this.frameLayout;
                Intrinsics.checkNotNull((Object)frameLayout);
                final View view2 = (View)frameLayout;
                final WindowManager$LayoutParams params = this.params;
                if (params == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                final WindowManager windowManager = this.windowManager;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                }
                Animator enterAnim = new AnimatorManager(view2, params, windowManager, this.config).enterAnim();
                if (enterAnim != null) {
                    final WindowManager$LayoutParams params2 = this.params;
                    if (params2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("params");
                    }
                    params2.flags = 552;
                    enterAnim.addListener((Animator$AnimatorListener)new FloatingWindowHelper$enterAnim$$inlined$apply$lambda.FloatingWindowHelper$enterAnim$$inlined$apply$lambda$1(this, view));
                    enterAnim.start();
                    final Unit instance = Unit.INSTANCE;
                }
                else {
                    enterAnim = null;
                }
                this.enterAnimator = enterAnim;
                if (enterAnim == null) {
                    view.setVisibility(0);
                    final WindowManager windowManager2 = this.windowManager;
                    if (windowManager2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                    }
                    final WindowManager$LayoutParams params3 = this.params;
                    if (params3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("params");
                    }
                    windowManager2.updateViewLayout(view, (ViewGroup$LayoutParams)params3);
                }
            }
        }
    }
    
    private final IBinder getToken() {
        final Context context = this.context;
        Activity topActivity;
        if (context instanceof Activity) {
            topActivity = (Activity)context;
        }
        else {
            topActivity = LifecycleUtils.INSTANCE.getTopActivity();
        }
        if (topActivity != null) {
            final Window window = topActivity.getWindow();
            if (window != null) {
                final View decorView = window.getDecorView();
                if (decorView != null) {
                    return decorView.getWindowToken();
                }
            }
        }
        return null;
    }
    
    private final void initEditText() {
        if (this.config.getHasEditText()) {
            final ParentFrameLayout frameLayout = this.frameLayout;
            if (frameLayout != null) {
                this.traverseViewGroup((View)frameLayout);
            }
        }
    }
    
    private final void initParams() {
        final Object systemService = this.context.getSystemService("window");
        if (systemService != null) {
            this.windowManager = (WindowManager)systemService;
            final WindowManager$LayoutParams params = new WindowManager$LayoutParams();
            if (this.config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY) {
                params.type = 1000;
                params.token = this.getToken();
            }
            else {
                int type;
                if (Build$VERSION.SDK_INT >= 26) {
                    type = 2038;
                }
                else {
                    type = 2002;
                }
                params.type = type;
            }
            params.format = 1;
            params.gravity = 8388659;
            int flags;
            if (this.config.getImmersionStatusBar()) {
                flags = 552;
            }
            else {
                flags = 40;
            }
            params.flags = flags;
            final boolean widthMatch = this.config.getWidthMatch();
            final int n = -1;
            int width;
            if (widthMatch) {
                width = -1;
            }
            else {
                width = -2;
            }
            params.width = width;
            int height;
            if (this.config.getHeightMatch()) {
                height = n;
            }
            else {
                height = -2;
            }
            params.height = height;
            if (this.config.getImmersionStatusBar() && this.config.getHeightMatch()) {
                params.height = DisplayUtils.INSTANCE.getScreenHeight(this.context);
            }
            if (true ^ Intrinsics.areEqual((Object)this.config.getLocationPair(), (Object)new Pair((Object)0, (Object)0))) {
                params.x = ((Number)this.config.getLocationPair().getFirst()).intValue();
                params.y = ((Number)this.config.getLocationPair().getSecond()).intValue();
            }
            final Unit instance = Unit.INSTANCE;
            this.params = params;
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
    }
    
    public static /* synthetic */ void remove$default(final FloatingWindowHelper floatingWindowHelper, boolean b, final int n, final Object o) {
        if ((n & 0x1) != 0x0) {
            b = false;
        }
        floatingWindowHelper.remove(b);
    }
    
    private final void setChangedListener() {
        final ParentFrameLayout frameLayout = this.frameLayout;
        if (frameLayout != null) {
            final ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new FloatingWindowHelper$setChangedListener$$inlined$apply$lambda.FloatingWindowHelper$setChangedListener$$inlined$apply$lambda$1(frameLayout, this));
            }
        }
    }
    
    private final void setGravity(final View view) {
        final Pair<Integer, Integer> locationPair = this.config.getLocationPair();
        int statusBarHeight = 0;
        final Integer value = 0;
        if (!(Intrinsics.areEqual((Object)locationPair, (Object)new Pair((Object)value, (Object)value)) ^ true)) {
            if (view != null) {
                final Rect rect = new Rect();
                final WindowManager windowManager = this.windowManager;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                }
                windowManager.getDefaultDisplay().getRectSize(rect);
                final int[] array = new int[2];
                view.getLocationOnScreen(array);
                final int n = array[1];
                final WindowManager$LayoutParams params = this.params;
                if (params == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                if (n > params.y) {
                    statusBarHeight = DisplayUtils.INSTANCE.statusBarHeight(view);
                }
                final int n2 = this.config.getDisplayHeight().getDisplayRealHeight(this.context) - statusBarHeight;
                switch (this.config.getGravity()) {
                    case 85:
                    case 8388693: {
                        final WindowManager$LayoutParams params2 = this.params;
                        if (params2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params2.x = rect.right - view.getWidth();
                        final WindowManager$LayoutParams params3 = this.params;
                        if (params3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params3.y = n2 - view.getHeight();
                        break;
                    }
                    case 81: {
                        final WindowManager$LayoutParams params4 = this.params;
                        if (params4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params4.x = rect.right - view.getWidth() >> 1;
                        final WindowManager$LayoutParams params5 = this.params;
                        if (params5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params5.y = n2 - view.getHeight();
                        break;
                    }
                    case 80:
                    case 83:
                    case 8388691: {
                        final WindowManager$LayoutParams params6 = this.params;
                        if (params6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params6.y = n2 - view.getHeight();
                        break;
                    }
                    case 21:
                    case 8388629: {
                        final WindowManager$LayoutParams params7 = this.params;
                        if (params7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params7.x = rect.right - view.getWidth();
                        final WindowManager$LayoutParams params8 = this.params;
                        if (params8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params8.y = n2 - view.getHeight() >> 1;
                        break;
                    }
                    case 17: {
                        final WindowManager$LayoutParams params9 = this.params;
                        if (params9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params9.x = rect.right - view.getWidth() >> 1;
                        final WindowManager$LayoutParams params10 = this.params;
                        if (params10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params10.y = n2 - view.getHeight() >> 1;
                        break;
                    }
                    case 16:
                    case 19:
                    case 8388627: {
                        final WindowManager$LayoutParams params11 = this.params;
                        if (params11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params11.y = n2 - view.getHeight() >> 1;
                        break;
                    }
                    case 5:
                    case 53:
                    case 8388613:
                    case 8388661: {
                        final WindowManager$LayoutParams params12 = this.params;
                        if (params12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params12.x = rect.right - view.getWidth();
                        break;
                    }
                    case 1:
                    case 49: {
                        final WindowManager$LayoutParams params13 = this.params;
                        if (params13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params13.x = rect.right - view.getWidth() >> 1;
                        break;
                    }
                }
                final WindowManager$LayoutParams params14 = this.params;
                if (params14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                params14.x += ((Number)this.config.getOffsetPair().getFirst()).intValue();
                final WindowManager$LayoutParams params15 = this.params;
                if (params15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                params15.y += ((Number)this.config.getOffsetPair().getSecond()).intValue();
                if (this.config.getImmersionStatusBar()) {
                    if (this.config.getShowPattern() != ShowPattern.CURRENT_ACTIVITY) {
                        final WindowManager$LayoutParams params16 = this.params;
                        if (params16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("params");
                        }
                        params16.y -= statusBarHeight;
                    }
                }
                else if (this.config.getShowPattern() == ShowPattern.CURRENT_ACTIVITY) {
                    final WindowManager$LayoutParams params17 = this.params;
                    if (params17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("params");
                    }
                    params17.y += statusBarHeight;
                }
                final WindowManager windowManager2 = this.windowManager;
                if (windowManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                }
                final WindowManager$LayoutParams params18 = this.params;
                if (params18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                windowManager2.updateViewLayout(view, (ViewGroup$LayoutParams)params18);
            }
        }
    }
    
    private final void traverseViewGroup(View child) {
        if (child != null) {
            if (child instanceof ViewGroup) {
                int i = 0;
                for (ViewGroup viewGroup = (ViewGroup)child; i < viewGroup.getChildCount(); ++i) {
                    child = viewGroup.getChildAt(i);
                    if (child instanceof ViewGroup) {
                        this.traverseViewGroup(child);
                    }
                    else {
                        Intrinsics.checkNotNullExpressionValue((Object)child, "child");
                        this.checkEditText(child);
                    }
                }
            }
            else {
                this.checkEditText(child);
            }
        }
    }
    
    public final boolean createWindow() {
        boolean b;
        if (this.getToken() == null) {
            final Context context = this.context;
            Activity topActivity;
            if (context instanceof Activity) {
                topActivity = (Activity)context;
            }
            else {
                topActivity = LifecycleUtils.INSTANCE.getTopActivity();
            }
            if (topActivity != null) {
                final View viewById = topActivity.findViewById(16908290);
                if (viewById != null) {
                    b = viewById.post((Runnable)new FloatingWindowHelper$createWindow.FloatingWindowHelper$createWindow$1(this));
                    return b;
                }
            }
            b = false;
        }
        else {
            b = this.createWindowInner();
        }
        return b;
    }
    
    public final void exitAnim() {
        if (this.frameLayout != null) {
            if (!this.config.isAnim() || this.enterAnimator != null) {
                final Animator enterAnimator = this.enterAnimator;
                if (enterAnimator != null) {
                    enterAnimator.cancel();
                }
                final ParentFrameLayout frameLayout = this.frameLayout;
                Intrinsics.checkNotNull((Object)frameLayout);
                final View view = (View)frameLayout;
                final WindowManager$LayoutParams params = this.params;
                if (params == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                final WindowManager windowManager = this.windowManager;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                }
                final Animator exitAnim = new AnimatorManager(view, params, windowManager, this.config).exitAnim();
                if (exitAnim == null) {
                    remove$default(this, false, 1, null);
                }
                else {
                    if (this.config.isAnim()) {
                        return;
                    }
                    this.config.setAnim(true);
                    final WindowManager$LayoutParams params2 = this.params;
                    if (params2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("params");
                    }
                    params2.flags = 552;
                    exitAnim.addListener((Animator$AnimatorListener)new FloatingWindowHelper$exitAnim.FloatingWindowHelper$exitAnim$1(this));
                    exitAnim.start();
                }
            }
        }
    }
    
    public final FloatConfig getConfig() {
        return this.config;
    }
    
    public final Context getContext() {
        return this.context;
    }
    
    public final ParentFrameLayout getFrameLayout() {
        return this.frameLayout;
    }
    
    public final WindowManager$LayoutParams getParams() {
        final WindowManager$LayoutParams params = this.params;
        if (params == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
        }
        return params;
    }
    
    public final WindowManager getWindowManager() {
        final WindowManager windowManager = this.windowManager;
        if (windowManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("windowManager");
        }
        return windowManager;
    }
    
    public final void remove(final boolean b) {
        try {
            this.config.setAnim(false);
            FloatingWindowManager.INSTANCE.remove(this.config.getFloatTag());
            final WindowManager windowManager = this.windowManager;
            if (windowManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("windowManager");
            }
            if (b) {
                windowManager.removeViewImmediate((View)this.frameLayout);
            }
            else {
                windowManager.removeView((View)this.frameLayout);
            }
        }
        catch (final Exception ex) {
            final Logger instance = Logger.INSTANCE;
            final StringBuilder sb = new StringBuilder();
            sb.append("\u6d6e\u7a97\u5173\u95ed\u51fa\u73b0\u5f02\u5e38\uff1a");
            sb.append((Object)ex);
            instance.e(sb.toString());
        }
    }
    
    public final void setConfig(final FloatConfig config) {
        Intrinsics.checkNotNullParameter((Object)config, "<set-?>");
        this.config = config;
    }
    
    public final void setFrameLayout(final ParentFrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }
    
    public final void setParams(final WindowManager$LayoutParams params) {
        Intrinsics.checkNotNullParameter((Object)params, "<set-?>");
        this.params = params;
    }
    
    public final void setVisible(final int visibility, final boolean needShow$easyfloat_release) {
        final ParentFrameLayout frameLayout = this.frameLayout;
        if (frameLayout != null) {
            Intrinsics.checkNotNull((Object)frameLayout);
            if (frameLayout.getChildCount() >= 1) {
                this.config.setNeedShow$easyfloat_release(needShow$easyfloat_release);
                final ParentFrameLayout frameLayout2 = this.frameLayout;
                Intrinsics.checkNotNull((Object)frameLayout2);
                frameLayout2.setVisibility(visibility);
                final ParentFrameLayout frameLayout3 = this.frameLayout;
                Intrinsics.checkNotNull((Object)frameLayout3);
                final View child = frameLayout3.getChildAt(0);
                if (visibility == 0) {
                    this.config.setShow(true);
                    final OnFloatCallbacks callbacks = this.config.getCallbacks();
                    if (callbacks != null) {
                        Intrinsics.checkNotNullExpressionValue((Object)child, "view");
                        callbacks.show(child);
                    }
                    final FloatCallbacks floatCallbacks = this.config.getFloatCallbacks();
                    if (floatCallbacks != null) {
                        final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
                        if (builder != null) {
                            final Function1<View, Unit> show$easyfloat_release = builder.getShow$easyfloat_release();
                            if (show$easyfloat_release != null) {
                                Intrinsics.checkNotNullExpressionValue((Object)child, "view");
                                final Unit unit = (Unit)show$easyfloat_release.invoke((Object)child);
                            }
                        }
                    }
                }
                else {
                    this.config.setShow(false);
                    final OnFloatCallbacks callbacks2 = this.config.getCallbacks();
                    if (callbacks2 != null) {
                        Intrinsics.checkNotNullExpressionValue((Object)child, "view");
                        callbacks2.hide(child);
                    }
                    final FloatCallbacks floatCallbacks2 = this.config.getFloatCallbacks();
                    if (floatCallbacks2 != null) {
                        final FloatCallbacks.Builder builder2 = floatCallbacks2.getBuilder();
                        if (builder2 != null) {
                            final Function1<View, Unit> hide$easyfloat_release = builder2.getHide$easyfloat_release();
                            if (hide$easyfloat_release != null) {
                                Intrinsics.checkNotNullExpressionValue((Object)child, "view");
                                final Unit unit2 = (Unit)hide$easyfloat_release.invoke((Object)child);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public final void setWindowManager(final WindowManager windowManager) {
        Intrinsics.checkNotNullParameter((Object)windowManager, "<set-?>");
        this.windowManager = windowManager;
    }
    
    public final void updateFloat(final int x, final int y) {
        final ParentFrameLayout frameLayout = this.frameLayout;
        if (frameLayout != null) {
            if (x == -1 && y == -1) {
                frameLayout.postDelayed((Runnable)new FloatingWindowHelper$updateFloat$$inlined$let$lambda.FloatingWindowHelper$updateFloat$$inlined$let$lambda$1(frameLayout, this, x, y), 200L);
            }
            else {
                final WindowManager$LayoutParams params = this.params;
                if (params == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                params.x = x;
                final WindowManager$LayoutParams params2 = this.params;
                if (params2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                params2.y = y;
                final WindowManager windowManager = this.windowManager;
                if (windowManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("windowManager");
                }
                final View view = (View)frameLayout;
                final WindowManager$LayoutParams params3 = this.params;
                if (params3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("params");
                }
                windowManager.updateViewLayout(view, (ViewGroup$LayoutParams)params3);
            }
        }
    }
}
