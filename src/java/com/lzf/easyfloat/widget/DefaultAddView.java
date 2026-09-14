package com.lzf.easyfloat.widget;

import android.graphics.Path$Direction;
import android.graphics.Canvas;
import android.view.View;
import android.view.MotionEvent;
import kotlin.Unit;
import android.graphics.Paint$Style;
import android.graphics.Color;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Region;
import android.graphics.Path;
import android.graphics.Paint;
import com.lzf.easyfloat.interfaces.OnTouchRangeListener;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J(\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0014J\u001a\u0010%\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&" }, d2 = { "Lcom/lzf/easyfloat/widget/DefaultAddView;", "Lcom/lzf/easyfloat/widget/BaseSwitchView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "height", "", "inRange", "", "listener", "Lcom/lzf/easyfloat/interfaces/OnTouchRangeListener;", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "region", "Landroid/graphics/Region;", "totalRegion", "width", "zoomSize", "initPath", "", "initTouchRange", "event", "Landroid/view/MotionEvent;", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "setTouchRangeListener", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class DefaultAddView extends BaseSwitchView
{
    private HashMap _$_findViewCache;
    private float height;
    private boolean inRange;
    private OnTouchRangeListener listener;
    private Paint paint;
    private Path path;
    private Region region;
    private final Region totalRegion;
    private float width;
    private float zoomSize;
    
    public DefaultAddView(final Context context) {
        this(context, null, 0, 6, null);
    }
    
    public DefaultAddView(final Context context, final AttributeSet set) {
        this(context, set, 0, 4, null);
    }
    
    public DefaultAddView(final Context context, final AttributeSet set, final int n) {
        Intrinsics.checkNotNullParameter((Object)context, "context");
        super(context, set, n);
        this.path = new Path();
        this.region = new Region();
        this.totalRegion = new Region();
        this.zoomSize = 18.0f;
        this.initPath();
        this.setWillNotDraw(false);
    }
    
    private final void initPath() {
        final Paint paint = new Paint();
        paint.setColor(Color.parseColor("#AA000000"));
        paint.setStrokeWidth(10.0f);
        paint.setStyle(Paint$Style.FILL);
        paint.setAntiAlias(true);
        final Unit instance = Unit.INSTANCE;
        this.paint = paint;
    }
    
    private final boolean initTouchRange(final MotionEvent motionEvent) {
        final int[] array = new int[2];
        this.getLocationOnScreen(array);
        final boolean contains = this.region.contains((int)motionEvent.getRawX() - array[0], (int)motionEvent.getRawY() - array[1]);
        if (contains != this.inRange) {
            this.inRange = contains;
            this.invalidate();
        }
        final OnTouchRangeListener listener = this.listener;
        if (listener != null) {
            listener.touchInRange(contains, (BaseSwitchView)this);
        }
        if (motionEvent.getAction() == 1 && contains) {
            final OnTouchRangeListener listener2 = this.listener;
            if (listener2 != null) {
                listener2.touchUpInRange();
            }
        }
        return contains;
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
    
    protected void onDraw(final Canvas canvas) {
        this.path.reset();
        if (this.inRange) {
            final Path path = this.path;
            final float width = this.width;
            final float height = this.height;
            path.addCircle(width, height, Math.min(width, height), Path$Direction.CW);
        }
        else {
            final Path path2 = this.path;
            final float width2 = this.width;
            final float height2 = this.height;
            path2.addCircle(width2, height2, Math.min(width2, height2) - this.zoomSize, Path$Direction.CW);
            final Region totalRegion = this.totalRegion;
            final float zoomSize = this.zoomSize;
            totalRegion.set((int)zoomSize, (int)zoomSize, (int)this.width, (int)this.height);
            this.region.setPath(this.path, this.totalRegion);
        }
        if (canvas != null) {
            final Path path3 = this.path;
            final Paint paint = this.paint;
            if (paint == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paint");
            }
            canvas.drawPath(path3, paint);
        }
        super.onDraw(canvas);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        this.width = (float)n;
        this.height = (float)n2;
    }
    
    public void setTouchRangeListener(final MotionEvent motionEvent, final OnTouchRangeListener listener) {
        Intrinsics.checkNotNullParameter((Object)motionEvent, "event");
        this.listener = listener;
        this.initTouchRange(motionEvent);
    }
}
