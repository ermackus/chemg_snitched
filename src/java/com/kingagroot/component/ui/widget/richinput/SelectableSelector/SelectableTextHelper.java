package com.kingagroot.component.ui.widget.richinput.SelectableSelector;

import com.kingagroot.component.ui.utils.GMathUtils;
import android.graphics.Paint$Style;
import android.graphics.Path;
import android.graphics.Canvas;
import android.widget.PopupWindow;
import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.TextView;
import android.text.Layout;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View$OnKeyListener;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.View$OnLongClickListener;
import android.widget.TextView$BufferType;
import android.widget.EditText;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.content.Context;

public class SelectableTextHelper
{
    private static final int DEFAULT_SELECTION_LENGTH = 1;
    private static final int DEFAULT_SHOW_DURATION = 100;
    private InsertCursorHandle insertCursorHandle;
    private boolean isHide;
    private boolean isHideWhenScroll;
    private final Context mContext;
    private final int mCursorHandleColor;
    private final int mCursorHandleSize;
    private CursorHandle mEndHandle;
    private ViewTreeObserver$OnPreDrawListener mOnPreDrawListener;
    private ViewTreeObserver$OnScrollChangedListener mOnScrollChangedListener;
    private final SelectMenu mOperateWindow;
    private final SelectionInfo mSelectionInfo;
    private final Runnable mShowSelectViewRunnable;
    private CursorHandle mStartHandle;
    private final EditText mTextView;
    private int mTouchX;
    private int mTouchY;
    
    public SelectableTextHelper(final Builder builder) {
        this.mSelectionInfo = new SelectionInfo();
        this.isHide = true;
        this.mShowSelectViewRunnable = (Runnable)new Runnable() {
            final SelectableTextHelper this$0;
            
            public void run() {
                if (this.this$0.isHide) {
                    return;
                }
                if (this.this$0.mOperateWindow != null) {
                    this.this$0.mOperateWindow.show(this.this$0.mSelectionInfo);
                }
                if (this.this$0.mStartHandle != null) {
                    final SelectableTextHelper this$0 = this.this$0;
                    this$0.showCursorHandle(this$0.mStartHandle);
                }
                if (this.this$0.mEndHandle != null) {
                    final SelectableTextHelper this$2 = this.this$0;
                    this$2.showCursorHandle(this$2.mEndHandle);
                }
            }
        };
        final EditText access$000 = builder.mTextView;
        this.mTextView = access$000;
        this.mContext = access$000.getContext();
        this.mCursorHandleColor = builder.mCursorHandleColor;
        this.mCursorHandleSize = TextLayoutUtil.dp2px(this.mContext, builder.mCursorHandleSizeInDp);
        (this.mOperateWindow = builder.selectMenu).setCursorHandleSize(this.mCursorHandleSize);
        this.init();
    }
    
    private CursorHandle getCursorHandle(final boolean b) {
        if (this.mStartHandle.isLeft == b) {
            return this.mStartHandle;
        }
        return this.mEndHandle;
    }
    
    private void init() {
        final EditText mTextView = this.mTextView;
        mTextView.setText((CharSequence)mTextView.getText(), TextView$BufferType.SPANNABLE);
        this.mTextView.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener(this) {
            final SelectableTextHelper this$0;
            
            public boolean onLongClick(final View view) {
                this.this$0.cancelInsertCursor();
                final SelectableTextHelper this$0 = this.this$0;
                this$0.showSelectView(this$0.mTouchX, this.this$0.mTouchY);
                return true;
            }
        });
        this.mTextView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener(this) {
            final SelectableTextHelper this$0;
            
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                this.this$0.mTouchX = (int)motionEvent.getX();
                this.this$0.mTouchY = (int)motionEvent.getY();
                if (motionEvent.getAction() == 1) {
                    this.this$0.postShowInsertCursor(100L);
                }
                return false;
            }
        });
        this.mTextView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SelectableTextHelper this$0;
            
            public void onClick(final View view) {
                this.this$0.resetSelectionInfo();
                this.this$0.hideSelectView();
            }
        });
        this.mTextView.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)new View$OnAttachStateChangeListener(this) {
            final SelectableTextHelper this$0;
            
            public void onViewAttachedToWindow(final View view) {
            }
            
            public void onViewDetachedFromWindow(final View view) {
                this.this$0.destroy();
            }
        });
        this.mOnPreDrawListener = (ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener(this) {
            final SelectableTextHelper this$0;
            
            public boolean onPreDraw() {
                if (this.this$0.isHideWhenScroll) {
                    this.this$0.isHideWhenScroll = false;
                    this.this$0.postShowSelectView(100);
                }
                return true;
            }
        };
        this.mTextView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        this.mOnScrollChangedListener = (ViewTreeObserver$OnScrollChangedListener)new ViewTreeObserver$OnScrollChangedListener(this) {
            final SelectableTextHelper this$0;
            
            public void onScrollChanged() {
                if (!this.this$0.isHideWhenScroll && !this.this$0.isHide) {
                    this.this$0.isHideWhenScroll = true;
                    if (this.this$0.mOperateWindow != null) {
                        this.this$0.mOperateWindow.dismiss();
                    }
                    if (this.this$0.mStartHandle != null && this.this$0.mStartHandle.isShowing()) {
                        this.this$0.mStartHandle.updateCursorHandle();
                    }
                    if (this.this$0.mEndHandle != null && this.this$0.mEndHandle.isShowing()) {
                        this.this$0.mEndHandle.updateCursorHandle();
                    }
                }
            }
        };
        this.mTextView.getViewTreeObserver().addOnScrollChangedListener(this.mOnScrollChangedListener);
        this.onKeyListener();
    }
    
    private void onKeyListener() {
        this.mTextView.setOnKeyListener((View$OnKeyListener)new View$OnKeyListener(this) {
            final SelectableTextHelper this$0;
            
            public boolean onKey(final View view, int keyCode, final KeyEvent keyEvent) {
                keyCode = keyEvent.getKeyCode();
                if (keyEvent.getAction() == 1 && keyCode == 67) {
                    this.this$0.destroy();
                }
                return false;
            }
        });
    }
    
    private void postShowInsertCursor(final long n) {
        new Handler().postDelayed((Runnable)new Runnable(this) {
            final SelectableTextHelper this$0;
            
            public void run() {
                if (this.this$0.insertCursorHandle == null) {
                    final SelectableTextHelper this$0 = this.this$0;
                    final SelectableTextHelper this$2 = this.this$0;
                    this$0.insertCursorHandle = this$2.new InsertCursorHandle(this$2.mContext);
                }
                this.this$0.insertCursorHandle.show();
            }
        }, n);
    }
    
    private void postShowSelectView(final int n) {
        this.mTextView.removeCallbacks(this.mShowSelectViewRunnable);
        if (n <= 0) {
            this.mShowSelectViewRunnable.run();
        }
        else {
            this.mTextView.postDelayed(this.mShowSelectViewRunnable, (long)n);
        }
    }
    
    private void showCursorHandle(final CursorHandle cursorHandle) {
        final Layout layout = this.mTextView.getLayout();
        int n;
        if (cursorHandle.isLeft) {
            n = this.mSelectionInfo.mStart;
        }
        else {
            n = this.mSelectionInfo.mEnd;
        }
        cursorHandle.show((int)layout.getPrimaryHorizontal(n), layout.getLineBottom(layout.getLineForOffset(n)));
    }
    
    private void showInsertMenu() {
        if (this.insertCursorHandle == null) {
            this.insertCursorHandle = new InsertCursorHandle(this.mContext);
        }
        this.insertCursorHandle.show();
        this.insertCursorHandle.showMenu();
    }
    
    private void showSelectView(int preciseOffset, final int n) {
        this.hideSelectView();
        this.resetSelectionInfo();
        this.isHide = false;
        final EditText mTextView = this.mTextView;
        preciseOffset = TextLayoutUtil.getPreciseOffset((TextView)mTextView, preciseOffset, n + mTextView.getScrollY());
        if (!TextUtils.isEmpty((CharSequence)this.mTextView.getText().toString()) && preciseOffset < this.mTextView.getText().length()) {
            this.selectText(preciseOffset, preciseOffset + 1);
            this.showCursorHandle();
            final SelectMenu mOperateWindow = this.mOperateWindow;
            if (mOperateWindow != null) {
                mOperateWindow.show(this.mSelectionInfo);
            }
            return;
        }
        this.showInsertMenu();
    }
    
    public void cancelInsertCursor() {
        final InsertCursorHandle insertCursorHandle = this.insertCursorHandle;
        if (insertCursorHandle != null) {
            insertCursorHandle.dismiss();
        }
    }
    
    public void cancelSelectMenu() {
        final SelectMenu mOperateWindow = this.mOperateWindow;
        if (mOperateWindow != null && mOperateWindow.isShowing()) {
            this.mOperateWindow.dismiss();
        }
    }
    
    public void destroy() {
        this.mTextView.getViewTreeObserver().removeOnScrollChangedListener(this.mOnScrollChangedListener);
        this.mTextView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        this.resetSelectionInfo();
        this.hideSelectView();
        this.mStartHandle = null;
        this.mEndHandle = null;
    }
    
    public SelectionInfo getSelectionInfo() {
        return this.mSelectionInfo;
    }
    
    public void hideSelectView() {
        this.isHide = true;
        final CursorHandle mStartHandle = this.mStartHandle;
        if (mStartHandle != null) {
            mStartHandle.dismiss();
        }
        final CursorHandle mEndHandle = this.mEndHandle;
        if (mEndHandle != null) {
            mEndHandle.dismiss();
        }
        final SelectMenu mOperateWindow = this.mOperateWindow;
        if (mOperateWindow != null) {
            mOperateWindow.dismiss();
        }
        final InsertCursorHandle insertCursorHandle = this.insertCursorHandle;
        if (insertCursorHandle != null) {
            insertCursorHandle.dismiss();
        }
    }
    
    public void resetSelectionInfo() {
        this.mSelectionInfo.mStart = 0;
        this.mSelectionInfo.mEnd = 0;
        this.mTextView.setSelected(false);
    }
    
    public void selectText(int mEnd, final int mEnd2) {
        if (mEnd != -1) {
            this.mSelectionInfo.mStart = mEnd;
        }
        if (mEnd2 != -1) {
            this.mSelectionInfo.mEnd = mEnd2;
        }
        if (this.mSelectionInfo.mStart > this.mSelectionInfo.mEnd) {
            mEnd = this.mSelectionInfo.mStart;
            final SelectionInfo mSelectionInfo = this.mSelectionInfo;
            mSelectionInfo.mStart = mSelectionInfo.mEnd;
            this.mSelectionInfo.mEnd = mEnd;
        }
        mEnd = this.mTextView.getText().length();
        if (this.mSelectionInfo.mEnd > mEnd) {
            this.mSelectionInfo.mEnd = mEnd;
        }
        this.mTextView.setSelection(this.mSelectionInfo.mStart, this.mSelectionInfo.mEnd);
    }
    
    public void showCursorHandle() {
        if (this.mStartHandle == null) {
            this.mStartHandle = new CursorHandle(true);
        }
        if (this.mEndHandle == null) {
            this.mEndHandle = new CursorHandle(false);
        }
        final InsertCursorHandle insertCursorHandle = this.insertCursorHandle;
        if (insertCursorHandle != null && insertCursorHandle.isShowing()) {
            this.insertCursorHandle.dismiss();
        }
        this.showCursorHandle(this.mStartHandle);
        this.showCursorHandle(this.mEndHandle);
    }
    
    public void updateCursorHandle() {
        final CursorHandle mStartHandle = this.mStartHandle;
        if (mStartHandle != null) {
            mStartHandle.updateCursorHandle();
        }
        final CursorHandle mEndHandle = this.mEndHandle;
        if (mEndHandle != null) {
            mEndHandle.updateCursorHandle();
        }
    }
    
    public static class Builder
    {
        private int mCursorHandleColor;
        private float mCursorHandleSizeInDp;
        private final EditText mTextView;
        private SelectMenu selectMenu;
        
        public Builder(final EditText mTextView) {
            this.mCursorHandleColor = -15500842;
            this.mCursorHandleSizeInDp = 24.0f;
            this.mTextView = mTextView;
        }
        
        public SelectableTextHelper build() {
            return new SelectableTextHelper(this);
        }
        
        public Builder setCursorHandleColor(final int mCursorHandleColor) {
            this.mCursorHandleColor = mCursorHandleColor;
            return this;
        }
        
        public Builder setCursorHandleSizeInDp(final float mCursorHandleSizeInDp) {
            this.mCursorHandleSizeInDp = mCursorHandleSizeInDp;
            return this;
        }
        
        public Builder setSelectMenu(final SelectMenu selectMenu) {
            this.selectMenu = selectMenu;
            return this;
        }
    }
    
    public class CursorHandle extends View
    {
        private final boolean isLeft;
        private boolean isTouch;
        private int mAdjustX;
        private int mAdjustY;
        private final int mCircleRadius;
        private final int mHeight;
        private final int mPadding;
        private final Paint mPaint;
        private final PopupWindow mPopupWindow;
        private final int[] mTempCoors;
        private final int mWidth;
        final SelectableTextHelper this$0;
        
        public CursorHandle(final SelectableTextHelper this$0, final boolean isLeft) {
            this.this$0 = this$0;
            super(this$0.mContext);
            final int mCircleRadius = this.this$0.mCursorHandleSize / 2;
            this.mCircleRadius = mCircleRadius;
            this.mWidth = mCircleRadius * 2;
            this.mHeight = mCircleRadius * 2;
            this.mPadding = 25;
            this.mTempCoors = new int[2];
            this.isLeft = isLeft;
            (this.mPaint = new Paint(1)).setColor(this$0.mCursorHandleColor);
            (this.mPopupWindow = new PopupWindow((View)this)).setClippingEnabled(false);
            this.mPopupWindow.setWidth(this.mWidth + 50);
            this.mPopupWindow.setHeight(this.mHeight + 12);
            this.invalidate();
        }
        
        private boolean checkViewAre(final float n, final float n2) {
            final int height = this.this$0.mTextView.getHeight();
            if (n2 >= 0.0f) {
                final int[] mTempCoors = this.mTempCoors;
                if (mTempCoors[1] <= n2 && n2 < mTempCoors[1] + height) {
                    return true;
                }
            }
            return false;
        }
        
        private void updateCursorHandle() {
            this.this$0.mTextView.getLocationInWindow(this.mTempCoors);
            final Layout layout = this.this$0.mTextView.getLayout();
            if (this.isLeft) {
                try {
                    final int n = (int)layout.getPrimaryHorizontal(this.this$0.mSelectionInfo.mStart) - this.mWidth + this.getExtraX();
                    final int n2 = layout.getLineBottom(layout.getLineForOffset(this.this$0.mSelectionInfo.mStart)) + this.getExtraY();
                    if (!this.isTouch && !this.checkViewAre((float)n, (float)n2)) {
                        if (this.mPopupWindow.isShowing()) {
                            this.mPopupWindow.dismiss();
                        }
                    }
                    else {
                        int n3;
                        if ((n3 = n2) < this.mTempCoors[1]) {
                            n3 = this.mTempCoors[1];
                        }
                        int n4;
                        if ((n4 = n3) > this.mTempCoors[1] + this.this$0.mTextView.getHeight()) {
                            n4 = this.mTempCoors[1] + this.this$0.mTextView.getHeight();
                        }
                        if (this.mPopupWindow.isShowing()) {
                            this.mPopupWindow.update(n, n4, -1, -1);
                        }
                        else {
                            this.mPopupWindow.showAtLocation((View)this.this$0.mTextView, 0, n, n4);
                        }
                    }
                }
                catch (final IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                try {
                    final int n5 = (int)layout.getPrimaryHorizontal(this.this$0.mSelectionInfo.mEnd) + this.getExtraX();
                    final int n6 = layout.getLineBottom(layout.getLineForOffset(this.this$0.mSelectionInfo.mEnd)) + this.getExtraY();
                    if (!this.isTouch && !this.checkViewAre((float)n5, (float)n6)) {
                        if (this.mPopupWindow.isShowing()) {
                            this.mPopupWindow.dismiss();
                        }
                    }
                    else {
                        int n7;
                        if ((n7 = n6) < this.mTempCoors[1]) {
                            n7 = this.mTempCoors[1];
                        }
                        int n8;
                        if ((n8 = n7) > this.mTempCoors[1] + this.this$0.mTextView.getHeight()) {
                            n8 = this.mTempCoors[1] + this.this$0.mTextView.getHeight();
                        }
                        if (this.mPopupWindow.isShowing()) {
                            this.mPopupWindow.update(n5, n8, -1, -1);
                        }
                        else {
                            this.mPopupWindow.showAtLocation((View)this.this$0.mTextView, 0, n5, n8);
                        }
                    }
                }
                catch (final IndexOutOfBoundsException ex2) {
                    ex2.printStackTrace();
                }
            }
            if (this.this$0.mOperateWindow != null && this.this$0.mOperateWindow.isShowing()) {
                this.this$0.mOperateWindow.updata(this.this$0.mSelectionInfo);
            }
        }
        
        public void dismiss() {
            this.mPopupWindow.dismiss();
        }
        
        public int getExtraX() {
            return this.mTempCoors[0] - 25 + this.this$0.mTextView.getPaddingLeft();
        }
        
        public int getExtraY() {
            return this.mTempCoors[1] + this.this$0.mTextView.getPaddingTop() - this.this$0.mTextView.getScrollY();
        }
        
        public boolean isShowing() {
            return this.mPopupWindow.isShowing();
        }
        
        protected void onDraw(final Canvas canvas) {
            final int mCircleRadius = this.mCircleRadius;
            canvas.drawCircle((float)(mCircleRadius + 25), (float)mCircleRadius, (float)mCircleRadius, this.mPaint);
            if (this.isLeft) {
                final int mCircleRadius2 = this.mCircleRadius;
                canvas.drawRect((float)(mCircleRadius2 + 25), 0.0f, (float)(mCircleRadius2 * 2 + 25), (float)mCircleRadius2, this.mPaint);
            }
            else {
                final int mCircleRadius3 = this.mCircleRadius;
                canvas.drawRect(25.0f, 0.0f, (float)(mCircleRadius3 + 25), (float)mCircleRadius3, this.mPaint);
            }
        }
        
        public boolean onTouchEvent(final MotionEvent motionEvent) {
            final int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        this.this$0.mOperateWindow.dismiss();
                        this.update((int)motionEvent.getRawX() + this.mAdjustX - this.mWidth, (int)motionEvent.getRawY() + this.mAdjustY - this.mHeight);
                        return true;
                    }
                    if (action != 3) {
                        return true;
                    }
                }
                this.this$0.mOperateWindow.show(this.this$0.mSelectionInfo);
                this.isTouch = false;
            }
            else {
                this.isTouch = true;
                this.mAdjustX = (int)motionEvent.getX();
                this.mAdjustY = (int)motionEvent.getY();
                this.this$0.mTextView.cancelLongPress();
            }
            return true;
        }
        
        public void show(int n, int n2) {
            this.this$0.mTextView.getLocationInWindow(this.mTempCoors);
            int mWidth;
            if (this.isLeft) {
                mWidth = this.mWidth;
            }
            else {
                mWidth = 0;
            }
            n = n - mWidth + this.getExtraX();
            n2 += this.getExtraY();
            if (this.checkViewAre((float)n, (float)n2)) {
                this.mPopupWindow.showAtLocation((View)this.this$0.mTextView, 0, n, n2);
            }
        }
        
        public void update(int n, int hysteresisOffset) {
            this.this$0.mTextView.getLocationInWindow(this.mTempCoors);
            int n2;
            if (this.isLeft) {
                n2 = this.this$0.mSelectionInfo.mStart;
            }
            else {
                n2 = this.this$0.mSelectionInfo.mEnd;
            }
            hysteresisOffset = TextLayoutUtil.getHysteresisOffset((TextView)this.this$0.mTextView, n, hysteresisOffset - this.mTempCoors[1] + this.this$0.mTextView.getScrollY(), n2);
            final int length = this.this$0.mTextView.getText().length();
            n = hysteresisOffset;
            if (hysteresisOffset > 0 && (n = hysteresisOffset) <= length) {
                n = hysteresisOffset;
                if (this.this$0.mTextView.getText().charAt(hysteresisOffset - 1) == '\n') {
                    n = hysteresisOffset - 1;
                }
            }
            if (n != n2) {
                if (this.isLeft) {
                    if ((hysteresisOffset = n) >= this.this$0.mSelectionInfo.mEnd) {
                        hysteresisOffset = this.this$0.mSelectionInfo.mEnd - 1;
                    }
                    final CursorHandle access$2300 = this.this$0.getCursorHandle(false);
                    this.this$0.selectText(hysteresisOffset, -1);
                    access$2300.updateCursorHandle();
                    this.updateCursorHandle();
                }
                else {
                    if ((hysteresisOffset = n) <= this.this$0.mSelectionInfo.mStart) {
                        hysteresisOffset = this.this$0.mSelectionInfo.mStart + 1;
                    }
                    final CursorHandle access$2301 = this.this$0.getCursorHandle(true);
                    this.this$0.selectText(-1, hysteresisOffset);
                    access$2301.updateCursorHandle();
                    this.updateCursorHandle();
                }
            }
        }
    }
    
    public class InsertCursorHandle extends View implements View$OnClickListener
    {
        private static final long DELAYMILLIS = 4000L;
        private final int cursorHeight;
        private final int mCircleRadius;
        private final int mHeight;
        private final Paint mPaint;
        private final PopupWindow mPopupWindow;
        private final int mWidth;
        private final Path path;
        private Runnable postDismissRunable;
        final SelectableTextHelper this$0;
        
        public InsertCursorHandle(final SelectableTextHelper this$0, final Context context) {
            this.this$0 = this$0;
            super(context);
            final int mCircleRadius = this$0.mCursorHandleSize / 2;
            this.mCircleRadius = mCircleRadius;
            this.cursorHeight = mCircleRadius / 2;
            this.mWidth = this$0.mCursorHandleSize;
            this.mHeight = this$0.mCursorHandleSize + this.cursorHeight;
            (this.mPaint = new Paint(1)).setColor(this$0.mCursorHandleColor);
            this.mPaint.setStyle(Paint$Style.FILL);
            final int mCircleRadius2 = this.mCircleRadius;
            final double n = mCircleRadius2;
            final double n2 = this.cursorHeight + mCircleRadius2;
            final double n3 = mCircleRadius2 + n;
            final double[] rotatedPoint = GMathUtils.rotatedPoint(n3, n2, n, n2, Math.toRadians(150.0));
            final double[] rotatedPoint2 = GMathUtils.rotatedPoint(n3, n2, n, n2, Math.toRadians(30.0));
            (this.path = new Path()).moveTo((float)this.mCircleRadius, 0.0f);
            this.path.lineTo((float)rotatedPoint[0], (float)rotatedPoint[1]);
            this.path.lineTo((float)rotatedPoint2[0], (float)rotatedPoint2[1]);
            this.path.close();
            (this.mPopupWindow = new PopupWindow((View)this)).setClippingEnabled(false);
            this.mPopupWindow.setWidth(this.mWidth);
            this.mPopupWindow.setHeight(this.mHeight);
            this.setOnClickListener((View$OnClickListener)this);
            this.invalidate();
        }
        
        private int getExtraX() {
            final int[] array = new int[2];
            this.this$0.mTextView.getLocationInWindow(array);
            return array[0];
        }
        
        private int getExtraY() {
            final int[] array = new int[2];
            this.this$0.mTextView.getLocationInWindow(array);
            return array[1] + this.this$0.mTextView.getPaddingTop() - this.this$0.mTextView.getScrollY();
        }
        
        public void dismiss() {
            if (this.postDismissRunable != null) {
                this.this$0.mTextView.removeCallbacks(this.postDismissRunable);
            }
            if (this.mPopupWindow.isShowing()) {
                this.mPopupWindow.dismiss();
            }
        }
        
        public void draw(final Canvas canvas) {
            super.draw(canvas);
            final int mCircleRadius = this.mCircleRadius;
            canvas.drawCircle((float)mCircleRadius, (float)(this.cursorHeight + mCircleRadius), (float)mCircleRadius, this.mPaint);
            canvas.drawPath(this.path, this.mPaint);
        }
        
        public boolean isShowing() {
            final PopupWindow mPopupWindow = this.mPopupWindow;
            return mPopupWindow != null && mPopupWindow.isShowing();
        }
        
        public void onClick(final View view) {
            this.showMenu();
        }
        
        public void show() {
            final int selectionStart = this.this$0.mTextView.getSelectionStart();
            if (selectionStart == this.this$0.mTextView.getSelectionEnd()) {
                this.this$0.mSelectionInfo.mStart = selectionStart;
                this.this$0.mSelectionInfo.mEnd = selectionStart;
                final Layout layout = this.this$0.mTextView.getLayout();
                final int n = (int)layout.getPrimaryHorizontal(selectionStart);
                final int lineBottom = layout.getLineBottom(layout.getLineForOffset(selectionStart));
                if (this.this$0.insertCursorHandle == null) {
                    final SelectableTextHelper this$0 = this.this$0;
                    final SelectableTextHelper this$2 = this.this$0;
                    this$0.insertCursorHandle = this$2.new InsertCursorHandle(this$2.mContext);
                }
                final int n2 = n + this.getExtraX();
                final int n3 = lineBottom + this.getExtraY();
                if (this.postDismissRunable != null) {
                    this.this$0.mTextView.removeCallbacks(this.postDismissRunable);
                }
                this.postDismissRunable = (Runnable)new DismissRunable();
                this.this$0.mTextView.postDelayed(this.postDismissRunable, 4000L);
                if (this.mPopupWindow.isShowing()) {
                    this.mPopupWindow.update(n2, n3, -1, -1);
                }
                else {
                    this.mPopupWindow.showAtLocation((View)this.this$0.mTextView, 0, n2, n3);
                }
            }
        }
        
        public void showMenu() {
            this.this$0.mOperateWindow.enableSelectAll(true);
            this.this$0.mOperateWindow.enableCopy(false);
            this.this$0.mOperateWindow.show(this.this$0.mSelectionInfo);
        }
        
        private class DismissRunable implements Runnable
        {
            final InsertCursorHandle this$1;
            
            private DismissRunable(final InsertCursorHandle this$1) {
                this.this$1 = this$1;
            }
            
            public void run() {
                this.this$1.mPopupWindow.dismiss();
            }
        }
    }
}
