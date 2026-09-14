package com.goodsrc.ui.library.widget;

import android.graphics.Typeface;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.goodsrc.ui.library.R;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;
import android.view.View;

public class SideBar extends View
{
    public static String[] b;
    private int choose;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private final Paint paint;
    
    static {
        SideBar.b = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };
    }
    
    public SideBar(final Context context) {
        super(context);
        this.choose = -1;
        this.paint = new Paint();
    }
    
    public SideBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.choose = -1;
        this.paint = new Paint();
    }
    
    public SideBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.choose = -1;
        this.paint = new Paint();
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final float y = motionEvent.getY();
        final int choose = this.choose;
        final OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.onTouchingLetterChangedListener;
        final int choose2 = (int)(y / this.getHeight() * SideBar.b.length);
        if (action != 1) {
            this.setBackgroundResource(R.drawable.sidebar_background);
            if (choose != choose2 && choose2 >= 0) {
                final String[] b = SideBar.b;
                if (choose2 < b.length) {
                    if (onTouchingLetterChangedListener != null) {
                        onTouchingLetterChangedListener.onTouchingLetterChanged(b[choose2]);
                    }
                    final TextView mTextDialog = this.mTextDialog;
                    if (mTextDialog != null) {
                        mTextDialog.setText((CharSequence)SideBar.b[choose2]);
                        this.mTextDialog.setVisibility(0);
                    }
                    this.choose = choose2;
                    this.invalidate();
                }
            }
        }
        else {
            this.setBackgroundDrawable((Drawable)new ColorDrawable(0));
            this.choose = -1;
            this.invalidate();
            final TextView mTextDialog2 = this.mTextDialog;
            if (mTextDialog2 != null) {
                mTextDialog2.setVisibility(4);
            }
        }
        return true;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final int height = this.getHeight();
        final int width = this.getWidth();
        final int n = height / SideBar.b.length;
        for (int i = 0; i < SideBar.b.length; ++i) {
            this.paint.setColor(Color.rgb(33, 65, 98));
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(this.getResources().getDimension(R.dimen.friend_item_right_text));
            if (i == this.choose) {
                this.paint.setColor(Color.parseColor("#3399ff"));
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(SideBar.b[i], width / 2 - this.paint.measureText(SideBar.b[i]) / 2.0f, (float)(n * i + n), this.paint);
            this.paint.reset();
        }
    }
    
    public void setOnTouchingLetterChangedListener(final OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
    
    public void setTextView(final TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }
    
    public interface OnTouchingLetterChangedListener
    {
        void onTouchingLetterChanged(final String p0);
    }
}
