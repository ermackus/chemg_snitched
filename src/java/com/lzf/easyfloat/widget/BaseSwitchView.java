package com.lzf.easyfloat.widget;

import android.view.View;
import com.lzf.easyfloat.interfaces.OnTouchRangeListener;
import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import android.util.AttributeSet;
import android.content.Context;
import java.util.HashMap;
import kotlin.Metadata;
import android.widget.RelativeLayout;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH&¨\u0006\u000f" }, d2 = { "Lcom/lzf/easyfloat/widget/BaseSwitchView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "setTouchRangeListener", "", "event", "Landroid/view/MotionEvent;", "listener", "Lcom/lzf/easyfloat/interfaces/OnTouchRangeListener;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public abstract class BaseSwitchView extends RelativeLayout
{
    private HashMap _$_findViewCache;
    
    public BaseSwitchView(final Context context) {
        this(context, null, 0, 6, null);
    }
    
    public BaseSwitchView(final Context context, final AttributeSet set) {
        this(context, set, 0, 4, null);
    }
    
    public BaseSwitchView(final Context context, final AttributeSet set, final int n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        super(context, set, n);
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
    
    public abstract void setTouchRangeListener(final MotionEvent p0, final OnTouchRangeListener p1);
}
