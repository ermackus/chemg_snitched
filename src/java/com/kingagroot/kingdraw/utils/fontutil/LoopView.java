package com.kingagroot.kingdraw.utils.fontutil;

import java.util.concurrent.TimeUnit;
import android.view.MotionEvent;
import android.graphics.Canvas;
import java.util.ArrayList;
import android.graphics.Typeface;
import android.content.res.TypedArray;
import com.kingagroot.kingdraw.R$styleable;
import android.view.GestureDetector$OnGestureListener;
import android.util.AttributeSet;
import java.util.concurrent.Executors;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Paint;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.List;
import android.os.Handler;
import android.view.GestureDetector;
import java.util.HashMap;
import android.content.Context;
import android.view.View;

public class LoopView extends View
{
    private static final float DEFAULT_LINE_SPACE = 2.0f;
    private static final int DEFAULT_TEXT_SIZE;
    private static final int DEFAULT_VISIBIE_ITEMS = 9;
    int centerTextColor;
    int change;
    private Context context;
    int dividerColor;
    HashMap<Integer, IndexString> drawingStrings;
    int firstLineY;
    private GestureDetector flingGestureDetector;
    int halfCircumference;
    Handler handler;
    int initPosition;
    boolean isLoop;
    List<IndexString> items;
    int itemsVisibleCount;
    float lineSpacingMultiplier;
    ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private int mOffset;
    int maxTextHeight;
    int measuredHeight;
    int measuredWidth;
    OnItemSelectedListener onItemSelectedListener;
    int outerTextColor;
    private int paddingLeft;
    private int paddingRight;
    private Paint paintCenterText;
    private Paint paintIndicator;
    private Paint paintOuterText;
    int preCurrentIndex;
    private float previousY;
    int radius;
    private float scaleX;
    int secondLineY;
    private int selectedItem;
    long startTime;
    private final Rect tempRect;
    int textSize;
    int totalScrollY;
    
    static {
        DEFAULT_TEXT_SIZE = (int)(Resources.getSystem().getDisplayMetrics().density * 15.0f);
    }
    
    public LoopView(final Context context) {
        super(context);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.initLoopView(context, null);
    }
    
    public LoopView(final Context context, final AttributeSet set) {
        super(context, set);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.initLoopView(context, set);
    }
    
    public LoopView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.scaleX = 1.05f;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.initLoopView(context, set);
    }
    
    private int getTextX(final String s, final Paint paint, final Rect rect) {
        paint.getTextBounds(s, 0, s.length(), rect);
        final int n = (int)(rect.width() * this.scaleX);
        final int measuredWidth = this.measuredWidth;
        final int paddingLeft = this.paddingLeft;
        return (measuredWidth - paddingLeft - n) / 2 + paddingLeft;
    }
    
    private void initLoopView(final Context context, final AttributeSet set) {
        this.context = context;
        this.handler = new MessageHandler(this);
        (this.flingGestureDetector = new GestureDetector(context, (GestureDetector$OnGestureListener)new LoopViewGestureListener(this))).setIsLongpressEnabled(false);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.androidWheelView);
        this.textSize = obtainStyledAttributes.getInteger(8, LoopView.DEFAULT_TEXT_SIZE);
        this.textSize *= (int)Resources.getSystem().getDisplayMetrics().density;
        this.lineSpacingMultiplier = obtainStyledAttributes.getFloat(5, 2.0f);
        this.centerTextColor = obtainStyledAttributes.getInteger(0, -13553359);
        this.outerTextColor = obtainStyledAttributes.getInteger(6, -5263441);
        this.dividerColor = obtainStyledAttributes.getInteger(1, -3815995);
        final int integer = obtainStyledAttributes.getInteger(4, 9);
        this.itemsVisibleCount = integer;
        if (integer % 2 == 0) {
            this.itemsVisibleCount = 9;
        }
        this.isLoop = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
        this.drawingStrings = (HashMap<Integer, IndexString>)new HashMap();
        this.totalScrollY = 0;
        this.initPosition = -1;
        this.initPaints();
    }
    
    private void initPaints() {
        (this.paintOuterText = new Paint()).setColor(this.outerTextColor);
        this.paintOuterText.setAntiAlias(true);
        this.paintOuterText.setTypeface(Typeface.MONOSPACE);
        this.paintOuterText.setTextSize((float)this.textSize);
        (this.paintCenterText = new Paint()).setColor(this.centerTextColor);
        this.paintCenterText.setAntiAlias(true);
        this.paintCenterText.setTextScaleX(this.scaleX);
        this.paintCenterText.setTypeface(Typeface.MONOSPACE);
        this.paintCenterText.setTextSize((float)this.textSize);
        (this.paintIndicator = new Paint()).setColor(this.dividerColor);
        this.paintIndicator.setAntiAlias(true);
    }
    
    private void remeasure() {
        if (this.items == null) {
            return;
        }
        this.measuredWidth = this.getMeasuredWidth();
        final int measuredHeight = this.getMeasuredHeight();
        this.measuredHeight = measuredHeight;
        if (this.measuredWidth != 0) {
            if (measuredHeight != 0) {
                this.paddingLeft = this.getPaddingLeft();
                final int paddingRight = this.getPaddingRight();
                this.paddingRight = paddingRight;
                this.measuredWidth -= paddingRight;
                this.paintCenterText.getTextBounds("\u661f\u671f", 0, 2, this.tempRect);
                this.maxTextHeight = this.tempRect.height();
                final int measuredHeight2 = this.measuredHeight;
                final int halfCircumference = (int)(measuredHeight2 * 3.141592653589793 / 2.0);
                this.halfCircumference = halfCircumference;
                final float n = (float)halfCircumference;
                final float lineSpacingMultiplier = this.lineSpacingMultiplier;
                final int maxTextHeight = (int)(n / ((this.itemsVisibleCount - 1) * lineSpacingMultiplier));
                this.maxTextHeight = maxTextHeight;
                this.radius = measuredHeight2 / 2;
                this.firstLineY = (int)((measuredHeight2 - maxTextHeight * lineSpacingMultiplier) / 2.0f);
                this.secondLineY = (int)((measuredHeight2 + lineSpacingMultiplier * maxTextHeight) / 2.0f);
                if (this.initPosition == -1) {
                    if (this.isLoop) {
                        this.initPosition = (this.items.size() + 1) / 2;
                    }
                    else {
                        this.initPosition = 0;
                    }
                }
                this.preCurrentIndex = this.initPosition;
            }
        }
    }
    
    public void cancelFuture() {
        final ScheduledFuture<?> mFuture = this.mFuture;
        if (mFuture != null && !mFuture.isCancelled()) {
            this.mFuture.cancel(true);
            this.mFuture = null;
        }
    }
    
    public List<IndexString> convertData(final List<String> list) {
        final ArrayList list2 = new ArrayList();
        for (int i = 0; i < list.size(); ++i) {
            ((List)list2).add((Object)new IndexString(i, (String)list.get(i)));
        }
        return (List<IndexString>)list2;
    }
    
    public final int getSelectedItem() {
        return this.selectedItem;
    }
    
    protected void onDraw(final Canvas canvas) {
        final List<IndexString> items = this.items;
        if (items == null) {
            return;
        }
        final int change = (int)(this.totalScrollY / (this.lineSpacingMultiplier * this.maxTextHeight));
        this.change = change;
        final int preCurrentIndex = this.initPosition + change % items.size();
        this.preCurrentIndex = preCurrentIndex;
        if (!this.isLoop) {
            if (preCurrentIndex < 0) {
                this.preCurrentIndex = 0;
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex = this.items.size() - 1;
            }
        }
        else {
            if (preCurrentIndex < 0) {
                this.preCurrentIndex += this.items.size();
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex -= this.items.size();
            }
        }
        final int n = (int)(this.totalScrollY % (this.lineSpacingMultiplier * this.maxTextHeight));
        int n2 = 0;
        while (true) {
            final int itemsVisibleCount = this.itemsVisibleCount;
            if (n2 >= itemsVisibleCount) {
                break;
            }
            int n3 = this.preCurrentIndex - (itemsVisibleCount / 2 - n2);
            if (this.isLoop) {
                int i;
                while ((i = n3) < 0) {
                    n3 += this.items.size();
                }
                while (i > this.items.size() - 1) {
                    i -= this.items.size();
                }
                this.drawingStrings.put((Object)n2, (Object)this.items.get(i));
            }
            else if (n3 < 0) {
                this.drawingStrings.put((Object)n2, (Object)new IndexString());
            }
            else if (n3 > this.items.size() - 1) {
                this.drawingStrings.put((Object)n2, (Object)new IndexString());
            }
            else {
                this.drawingStrings.put((Object)n2, (Object)this.items.get(n3));
            }
            ++n2;
        }
        final float n4 = (float)this.paddingLeft;
        final int firstLineY = this.firstLineY;
        canvas.drawLine(n4, (float)firstLineY, (float)this.measuredWidth, (float)firstLineY, this.paintIndicator);
        final float n5 = (float)this.paddingLeft;
        final int secondLineY = this.secondLineY;
        canvas.drawLine(n5, (float)secondLineY, (float)this.measuredWidth, (float)secondLineY, this.paintIndicator);
        for (int j = 0; j < this.itemsVisibleCount; ++j) {
            canvas.save();
            final float n6 = this.maxTextHeight * this.lineSpacingMultiplier;
            final double n7 = (j * n6 - n) * 3.141592653589793 / this.halfCircumference;
            if (n7 < 3.141592653589793 && n7 > 0.0) {
                final int n8 = (int)(this.radius - Math.cos(n7) * this.radius - Math.sin(n7) * this.maxTextHeight / 2.0);
                canvas.translate(0.0f, (float)n8);
                canvas.scale(1.0f, (float)Math.sin(n7));
                final int firstLineY2 = this.firstLineY;
                if (n8 <= firstLineY2 && this.maxTextHeight + n8 >= firstLineY2) {
                    canvas.save();
                    canvas.clipRect(0, 0, this.measuredWidth, this.firstLineY - n8);
                    canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, this.firstLineY - n8, this.measuredWidth, (int)n6);
                    canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintCenterText, this.tempRect), (float)this.maxTextHeight, this.paintCenterText);
                    canvas.restore();
                }
                else {
                    final int secondLineY2 = this.secondLineY;
                    if (n8 <= secondLineY2 && this.maxTextHeight + n8 >= secondLineY2) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.secondLineY - n8);
                        canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintCenterText, this.tempRect), (float)this.maxTextHeight, this.paintCenterText);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.secondLineY - n8, this.measuredWidth, (int)n6);
                        canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                        canvas.restore();
                    }
                    else if (n8 >= this.firstLineY && this.maxTextHeight + n8 <= this.secondLineY) {
                        canvas.clipRect(0, 0, this.measuredWidth, (int)n6);
                        canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintCenterText, this.tempRect), (float)this.maxTextHeight, this.paintCenterText);
                        this.selectedItem = this.items.indexOf(this.drawingStrings.get((Object)j));
                    }
                    else {
                        canvas.clipRect(0, 0, this.measuredWidth, (int)n6);
                        canvas.drawText(((IndexString)this.drawingStrings.get((Object)j)).string, (float)this.getTextX(((IndexString)this.drawingStrings.get((Object)j)).string, this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                    }
                }
                canvas.restore();
            }
            else {
                canvas.restore();
            }
        }
    }
    
    protected final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            this.postDelayed((Runnable)new OnItemSelectedRunnable(this), 200L);
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.remeasure();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean onTouchEvent = this.flingGestureDetector.onTouchEvent(motionEvent);
        final float n = this.lineSpacingMultiplier * this.maxTextHeight;
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 2) {
                if (!onTouchEvent) {
                    final float y = motionEvent.getY();
                    final int radius = this.radius;
                    this.mOffset = (int)(((int)((Math.acos((double)((radius - y) / radius)) * this.radius + n / 2.0f) / n) - this.itemsVisibleCount / 2) * n - (this.totalScrollY % n + n) % n);
                    if (System.currentTimeMillis() - this.startTime > 120L) {
                        this.smoothScroll(ACTION.DAGGLE);
                    }
                    else {
                        this.smoothScroll(ACTION.CLICK);
                    }
                }
                if (this.getParent() != null) {
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            else {
                final float previousY = this.previousY;
                final float rawY = motionEvent.getRawY();
                this.previousY = motionEvent.getRawY();
                this.totalScrollY += (int)(previousY - rawY);
                if (!this.isLoop) {
                    final float n2 = -this.initPosition * n;
                    final float n3 = (this.items.size() - 1 - this.initPosition) * n;
                    final int totalScrollY = this.totalScrollY;
                    if (totalScrollY < n2) {
                        this.totalScrollY = (int)n2;
                    }
                    else if (totalScrollY > n3) {
                        this.totalScrollY = (int)n3;
                    }
                }
            }
        }
        else {
            this.startTime = System.currentTimeMillis();
            this.cancelFuture();
            this.previousY = motionEvent.getRawY();
            if (this.getParent() != null) {
                this.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        this.invalidate();
        return true;
    }
    
    protected final void scrollBy(final float n) {
        this.cancelFuture();
        this.mFuture = (ScheduledFuture<?>)this.mExecutor.scheduleWithFixedDelay((Runnable)new InertiaTimerTask(this, n), 0L, (long)10, TimeUnit.MILLISECONDS);
    }
    
    public void setCenterTextColor(final int n) {
        this.centerTextColor = n;
        this.paintCenterText.setColor(n);
    }
    
    public void setCurrentPosition(final int initPosition) {
        final List<IndexString> items = this.items;
        if (items != null) {
            if (!items.isEmpty()) {
                final int size = this.items.size();
                if (initPosition >= 0 && initPosition < size && initPosition != this.selectedItem) {
                    this.initPosition = initPosition;
                    this.totalScrollY = 0;
                    this.mOffset = 0;
                    this.invalidate();
                }
            }
        }
    }
    
    public void setDividerColor(final int n) {
        this.dividerColor = n;
        this.paintIndicator.setColor(n);
    }
    
    public final void setInitPosition(final int initPosition) {
        if (initPosition < 0) {
            this.initPosition = 0;
        }
        else {
            final List<IndexString> items = this.items;
            if (items != null && items.size() > initPosition) {
                this.initPosition = initPosition;
            }
        }
    }
    
    public final void setItems(final List<String> list) {
        this.items = this.convertData(list);
        this.remeasure();
        this.invalidate();
    }
    
    public void setItemsVisibleCount(final int itemsVisibleCount) {
        if (itemsVisibleCount % 2 == 0) {
            return;
        }
        if (itemsVisibleCount != this.itemsVisibleCount) {
            this.itemsVisibleCount = itemsVisibleCount;
            this.drawingStrings = (HashMap<Integer, IndexString>)new HashMap();
        }
    }
    
    public void setLineSpacingMultiplier(final float lineSpacingMultiplier) {
        if (lineSpacingMultiplier > 1.0f) {
            this.lineSpacingMultiplier = lineSpacingMultiplier;
        }
    }
    
    public final void setListener(final OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
    
    public void setNotLoop() {
        this.isLoop = false;
    }
    
    public void setOuterTextColor(final int n) {
        this.outerTextColor = n;
        this.paintOuterText.setColor(n);
    }
    
    public void setScaleX(final float scaleX) {
        this.scaleX = scaleX;
    }
    
    public final void setTextSize(final float n) {
        if (n > 0.0f) {
            final int textSize = (int)(this.context.getResources().getDisplayMetrics().density * n);
            this.textSize = textSize;
            this.paintOuterText.setTextSize((float)textSize);
            this.paintCenterText.setTextSize((float)this.textSize);
        }
    }
    
    void smoothScroll(final ACTION action) {
        this.cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            final float n = this.lineSpacingMultiplier * this.maxTextHeight;
            final int mOffset = (int)((this.totalScrollY % n + n) % n);
            this.mOffset = mOffset;
            if (mOffset > n / 2.0f) {
                this.mOffset = (int)(n - mOffset);
            }
            else {
                this.mOffset = -mOffset;
            }
        }
        this.mFuture = (ScheduledFuture<?>)this.mExecutor.scheduleWithFixedDelay((Runnable)new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
    }
    
    public enum ACTION
    {
        private static final ACTION[] $VALUES;
        
        CLICK, 
        DAGGLE, 
        FLING;
    }
    
    class IndexString
    {
        private int index;
        private final String string;
        final LoopView this$0;
        
        public IndexString(final LoopView this$0) {
            this.this$0 = this$0;
            this.string = "";
        }
        
        public IndexString(final LoopView this$0, final int index, final String string) {
            this.this$0 = this$0;
            this.index = index;
            this.string = string;
        }
    }
}
