package com.lzf.easyfloat.widget;

import android.view.MotionEvent;
import kotlin.jvm.functions.Function0;
import com.lzf.easyfloat.interfaces.FloatCallbacks;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import kotlin.Unit;
import com.lzf.easyfloat.utils.InputMethodUtils;
import android.view.KeyEvent;
import android.view.View;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import android.util.AttributeSet;
import android.content.Context;
import com.lzf.easyfloat.interfaces.OnFloatTouchListener;
import com.lzf.easyfloat.data.FloatConfig;
import java.util.HashMap;
import kotlin.Metadata;
import android.widget.FrameLayout;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001:\u0001'B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u0019\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001fH\u0016J0\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0014J\u0012\u0010&\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001fH\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006(" }, d2 = { "Lcom/lzf/easyfloat/widget/ParentFrameLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "config", "Lcom/lzf/easyfloat/data/FloatConfig;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Lcom/lzf/easyfloat/data/FloatConfig;Landroid/util/AttributeSet;I)V", "isCreated", "", "layoutListener", "Lcom/lzf/easyfloat/widget/ParentFrameLayout$OnLayoutListener;", "getLayoutListener", "()Lcom/lzf/easyfloat/widget/ParentFrameLayout$OnLayoutListener;", "setLayoutListener", "(Lcom/lzf/easyfloat/widget/ParentFrameLayout$OnLayoutListener;)V", "touchListener", "Lcom/lzf/easyfloat/interfaces/OnFloatTouchListener;", "getTouchListener", "()Lcom/lzf/easyfloat/interfaces/OnFloatTouchListener;", "setTouchListener", "(Lcom/lzf/easyfloat/interfaces/OnFloatTouchListener;)V", "dispatchKeyEventPreIme", "event", "Landroid/view/KeyEvent;", "onDetachedFromWindow", "", "onInterceptTouchEvent", "Landroid/view/MotionEvent;", "onLayout", "changed", "left", "top", "right", "bottom", "onTouchEvent", "OnLayoutListener", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class ParentFrameLayout extends FrameLayout
{
    private HashMap _$_findViewCache;
    private final FloatConfig config;
    private boolean isCreated;
    private OnLayoutListener layoutListener;
    private OnFloatTouchListener touchListener;
    
    public ParentFrameLayout(final Context context, final FloatConfig config, final AttributeSet set, final int n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        Intrinsics.checkNotNullParameter((Object)config, "config");
        super(context, set, n);
        this.config = config;
    }
    
    public void _$_clearFindViewByIdCache() {
        final HashMap $_findViewCache = this._$_findViewCache;
        if ($_findViewCache != null) {
            $_findViewCache.clear();
        }
    }
    
    public View _$_findCachedViewById(final int n) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View viewById;
        if ((viewById = (View)this._$_findViewCache.get((Object)n)) == null) {
            viewById = ((View)this).findViewById(n);
            this._$_findViewCache.put((Object)n, (Object)viewById);
        }
        return viewById;
    }
    
    public boolean dispatchKeyEventPreIme(final KeyEvent keyEvent) {
        if (this.config.getHasEditText() && keyEvent != null && keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
            InputMethodUtils.closedInputMethod(this.config.getFloatTag());
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }
    
    public final OnLayoutListener getLayoutListener() {
        return this.layoutListener;
    }
    
    public final OnFloatTouchListener getTouchListener() {
        return this.touchListener;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        final OnFloatCallbacks callbacks = this.config.getCallbacks();
        if (callbacks != null) {
            callbacks.dismiss();
        }
        final FloatCallbacks floatCallbacks = this.config.getFloatCallbacks();
        if (floatCallbacks != null) {
            final FloatCallbacks.Builder builder = floatCallbacks.getBuilder();
            if (builder != null) {
                final Function0<Unit> dismiss$easyfloat_release = builder.getDismiss$easyfloat_release();
                if (dismiss$easyfloat_release != null) {
                    final Unit unit = (Unit)dismiss$easyfloat_release.invoke();
                }
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent != null) {
            final OnFloatTouchListener touchListener = this.touchListener;
            if (touchListener != null) {
                touchListener.onTouch(motionEvent);
            }
        }
        return this.config.isDrag() || super.onInterceptTouchEvent(motionEvent);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (!this.isCreated) {
            this.isCreated = true;
            final OnLayoutListener layoutListener = this.layoutListener;
            if (layoutListener != null) {
                layoutListener.onLayout();
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent != null) {
            final OnFloatTouchListener touchListener = this.touchListener;
            if (touchListener != null) {
                touchListener.onTouch(motionEvent);
            }
        }
        return this.config.isDrag() || super.onTouchEvent(motionEvent);
    }
    
    public final void setLayoutListener(final OnLayoutListener layoutListener) {
        this.layoutListener = layoutListener;
    }
    
    public final void setTouchListener(final OnFloatTouchListener touchListener) {
        this.touchListener = touchListener;
    }
    
    @Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004" }, d2 = { "Lcom/lzf/easyfloat/widget/ParentFrameLayout$OnLayoutListener;", "", "onLayout", "", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
    public interface OnLayoutListener
    {
        void onLayout();
    }
}
