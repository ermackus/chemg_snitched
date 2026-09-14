package com.lzf.easyfloat.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.functions.Function1;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.lzf.easyfloat.EasyFloat$Builder;
import android.content.Context;
import com.lzf.easyfloat.anim.DefaultAnimator;
import com.lzf.easyfloat.R;
import com.lzf.easyfloat.interfaces.OnFloatAnimator;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.interfaces.OnTouchRangeListener;
import android.view.MotionEvent;
import com.lzf.easyfloat.EasyFloat;
import kotlin.Unit;
import com.lzf.easyfloat.widget.BaseSwitchView;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0002\u0010\u0010J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0002\u0010\u0010J<\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0007JF\u0010\u001c\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\nH\u0007J,\u0010 \u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\"\u0010#\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$" }, d2 = { "Lcom/lzf/easyfloat/utils/DragUtils;", "", "()V", "ADD_TAG", "", "CLOSE_TAG", "addView", "Lcom/lzf/easyfloat/widget/BaseSwitchView;", "closeView", "downX", "", "offset", "screenWidth", "", "dismissAdd", "", "()Lkotlin/Unit;", "dismissClose", "registerDragClose", "event", "Landroid/view/MotionEvent;", "listener", "Lcom/lzf/easyfloat/interfaces/OnTouchRangeListener;", "layoutId", "showPattern", "Lcom/lzf/easyfloat/enums/ShowPattern;", "appFloatAnimator", "Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;", "registerSwipeAdd", "slideOffset", "start", "end", "setAddView", "progress", "showAdd", "showClose", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class DragUtils
{
    private static final String ADD_TAG = "ADD_TAG";
    private static final String CLOSE_TAG = "CLOSE_TAG";
    public static final DragUtils INSTANCE;
    private static BaseSwitchView addView;
    private static BaseSwitchView closeView;
    private static float downX;
    private static float offset;
    private static int screenWidth;
    
    static {
        INSTANCE = new DragUtils();
    }
    
    private DragUtils() {
    }
    
    private final Unit dismissAdd() {
        return EasyFloat.Companion.dismiss$default(EasyFloat.Companion, "ADD_TAG", false, 2, null);
    }
    
    private final Unit dismissClose() {
        return EasyFloat.Companion.dismiss$default(EasyFloat.Companion, "CLOSE_TAG", false, 2, null);
    }
    
    public static /* synthetic */ void registerDragClose$default(final DragUtils dragUtils, final MotionEvent motionEvent, OnTouchRangeListener onTouchRangeListener, int default_close_layout, ShowPattern current_ACTIVITY, OnFloatAnimator onFloatAnimator, final int n, final Object o) {
        if ((n & 0x2) != 0x0) {
            onTouchRangeListener = null;
        }
        if ((n & 0x4) != 0x0) {
            default_close_layout = R.layout.default_close_layout;
        }
        if ((n & 0x8) != 0x0) {
            current_ACTIVITY = ShowPattern.CURRENT_ACTIVITY;
        }
        if ((n & 0x10) != 0x0) {
            onFloatAnimator = (OnFloatAnimator)new DefaultAnimator();
        }
        dragUtils.registerDragClose(motionEvent, onTouchRangeListener, default_close_layout, current_ACTIVITY, onFloatAnimator);
    }
    
    public static /* synthetic */ void registerSwipeAdd$default(final DragUtils dragUtils, final MotionEvent motionEvent, OnTouchRangeListener onTouchRangeListener, int default_add_layout, float n, float n2, float n3, final int n4, final Object o) {
        if ((n4 & 0x2) != 0x0) {
            onTouchRangeListener = null;
        }
        if ((n4 & 0x4) != 0x0) {
            default_add_layout = R.layout.default_add_layout;
        }
        if ((n4 & 0x8) != 0x0) {
            n = -1.0f;
        }
        if ((n4 & 0x10) != 0x0) {
            n2 = 0.1f;
        }
        if ((n4 & 0x20) != 0x0) {
            n3 = 0.5f;
        }
        dragUtils.registerSwipeAdd(motionEvent, onTouchRangeListener, default_add_layout, n, n2, n3);
    }
    
    private final void setAddView(final MotionEvent motionEvent, float n, final OnTouchRangeListener onTouchRangeListener, final int n2) {
        final BaseSwitchView addView = DragUtils.addView;
        if (addView != null) {
            addView.setTouchRangeListener(motionEvent, onTouchRangeListener);
            final float n3 = (float)addView.getWidth();
            n = 1 - n;
            addView.setTranslationX(n3 * n);
            addView.setTranslationY(addView.getWidth() * n);
        }
        if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            this.showAdd(n2);
        }
        else {
            this.dismissAdd();
        }
    }
    
    private final void showAdd(final int n) {
        if (EasyFloat.Companion.isShow("ADD_TAG")) {
            return;
        }
        EasyFloat$Builder.setGravity$default(EasyFloat$Builder.setLayout$default(EasyFloat.Companion.with((Context)LifecycleUtils.INSTANCE.getApplication()), n, (OnInvokeView)null, 2, (Object)null).setShowPattern(ShowPattern.CURRENT_ACTIVITY).setTag("ADD_TAG").setDragEnable(false).setSidePattern(SidePattern.BOTTOM), 8388693, 0, 0, 6, (Object)null).setAnimator((OnFloatAnimator)null).registerCallback((Function1)DragUtils$showAdd$1.INSTANCE).show();
    }
    
    private final void showClose(final int n, final ShowPattern showPattern, final OnFloatAnimator animator) {
        if (EasyFloat.Companion.isShow("CLOSE_TAG")) {
            return;
        }
        EasyFloat$Builder.setGravity$default(EasyFloat$Builder.setMatchParent$default(EasyFloat$Builder.setLayout$default(EasyFloat.Companion.with((Context)LifecycleUtils.INSTANCE.getApplication()), n, (OnInvokeView)null, 2, (Object)null).setShowPattern(showPattern), true, false, 2, (Object)null).setTag("CLOSE_TAG").setSidePattern(SidePattern.BOTTOM), 80, 0, 0, 6, (Object)null).setAnimator(animator).registerCallback((Function1)DragUtils$showClose$1.INSTANCE).show();
    }
    
    public final void registerDragClose(final MotionEvent motionEvent) {
        registerDragClose$default(this, motionEvent, null, 0, null, null, 30, null);
    }
    
    public final void registerDragClose(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener) {
        registerDragClose$default(this, motionEvent, onTouchRangeListener, 0, null, null, 28, null);
    }
    
    public final void registerDragClose(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n) {
        registerDragClose$default(this, motionEvent, onTouchRangeListener, n, null, null, 24, null);
    }
    
    public final void registerDragClose(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n, final ShowPattern showPattern) {
        registerDragClose$default(this, motionEvent, onTouchRangeListener, n, showPattern, null, 16, null);
    }
    
    public final void registerDragClose(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n, final ShowPattern showPattern, final OnFloatAnimator onFloatAnimator) {
        Intrinsics.checkNotNullParameter((Object)motionEvent, "event");
        Intrinsics.checkNotNullParameter((Object)showPattern, "showPattern");
        this.showClose(n, showPattern, onFloatAnimator);
        final BaseSwitchView closeView = DragUtils.closeView;
        if (closeView != null) {
            closeView.setTouchRangeListener(motionEvent, onTouchRangeListener);
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.dismissClose();
        }
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent) {
        registerSwipeAdd$default(this, motionEvent, null, 0, 0.0f, 0.0f, 0.0f, 62, null);
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener) {
        registerSwipeAdd$default(this, motionEvent, onTouchRangeListener, 0, 0.0f, 0.0f, 0.0f, 60, null);
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n) {
        registerSwipeAdd$default(this, motionEvent, onTouchRangeListener, n, 0.0f, 0.0f, 0.0f, 56, null);
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n, final float n2) {
        registerSwipeAdd$default(this, motionEvent, onTouchRangeListener, n, n2, 0.0f, 0.0f, 48, null);
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n, final float n2, final float n3) {
        registerSwipeAdd$default(this, motionEvent, onTouchRangeListener, n, n2, n3, 0.0f, 32, null);
    }
    
    public final void registerSwipeAdd(final MotionEvent motionEvent, final OnTouchRangeListener onTouchRangeListener, final int n, float offset, final float n2, final float n3) {
        if (motionEvent == null) {
            return;
        }
        if (offset != -1.0f) {
            if (offset >= n2) {
                this.setAddView(motionEvent, Math.min((offset - n2) / (n3 - n2), 1.0f), onTouchRangeListener, n);
            }
            else {
                this.dismissAdd();
            }
        }
        else {
            DragUtils.screenWidth = DisplayUtils.INSTANCE.getScreenWidth((Context)LifecycleUtils.INSTANCE.getApplication());
            DragUtils.offset = motionEvent.getRawX() / DragUtils.screenWidth;
            final int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        if (DragUtils.downX < DragUtils.screenWidth * n2) {
                            offset = DragUtils.offset;
                            if (offset >= n2) {
                                this.setAddView(motionEvent, Math.min((offset - n2) / (n3 - n2), 1.0f), onTouchRangeListener, n);
                                return;
                            }
                        }
                        this.dismissAdd();
                        return;
                    }
                    if (action != 3) {
                        return;
                    }
                }
                DragUtils.downX = 0.0f;
                this.setAddView(motionEvent, DragUtils.offset, onTouchRangeListener, n);
            }
            else {
                DragUtils.downX = motionEvent.getRawX();
            }
        }
    }
}
