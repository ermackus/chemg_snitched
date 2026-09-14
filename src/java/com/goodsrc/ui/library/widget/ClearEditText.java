package com.goodsrc.ui.library.widget;

import android.view.MotionEvent;
import android.view.View;
import android.text.Editable;
import android.view.animation.Interpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;
import com.goodsrc.ui.library.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View$OnFocusChangeListener;
import androidx.appcompat.widget.AppCompatEditText;

public class ClearEditText extends AppCompatEditText implements View$OnFocusChangeListener, TextWatcher
{
    private Drawable mClearDrawable;
    
    public ClearEditText(final Context context) {
        this(context, null);
    }
    
    public ClearEditText(final Context context, final AttributeSet set) {
        this(context, set, 16842862);
    }
    
    public ClearEditText(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        final Drawable mClearDrawable = this.getCompoundDrawables()[2];
        this.mClearDrawable = mClearDrawable;
        if (mClearDrawable == null) {
            this.mClearDrawable = this.getResources().getDrawable(R.drawable.ic_edittext_clear_nor);
        }
        final Drawable mClearDrawable2 = this.mClearDrawable;
        mClearDrawable2.setBounds(0, 0, mClearDrawable2.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        this.setClearIconVisible(false);
        this.setOnFocusChangeListener((View$OnFocusChangeListener)this);
        this.addTextChangedListener((TextWatcher)this);
    }
    
    public static Animation shakeAnimation(final int n) {
        final TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        ((Animation)translateAnimation).setInterpolator((Interpolator)new CycleInterpolator((float)n));
        ((Animation)translateAnimation).setDuration(1000L);
        return (Animation)translateAnimation;
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onFocusChange(final View view, final boolean b) {
        final boolean b2 = false;
        if (b) {
            boolean clearIconVisible = b2;
            if (this.getText().length() > 0) {
                clearIconVisible = true;
            }
            this.setClearIconVisible(clearIconVisible);
        }
        else {
            this.setClearIconVisible(false);
        }
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        this.setClearIconVisible(charSequence.length() > 0);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.getCompoundDrawables()[2] != null) {
            final int action = motionEvent.getAction();
            boolean b = true;
            if (action == 1) {
                if (motionEvent.getX() <= this.getWidth() - this.getPaddingRight() - this.mClearDrawable.getIntrinsicWidth() || motionEvent.getX() >= this.getWidth() - this.getPaddingRight()) {
                    b = false;
                }
                if (b) {
                    this.setText((CharSequence)"");
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    protected void setClearIconVisible(final boolean b) {
        Drawable mClearDrawable;
        if (b) {
            mClearDrawable = this.mClearDrawable;
        }
        else {
            mClearDrawable = null;
        }
        this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], mClearDrawable, this.getCompoundDrawables()[3]);
    }
    
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }
}
