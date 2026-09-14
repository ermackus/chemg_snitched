package com.kingagroot.component.ui.view;

import android.view.MotionEvent;
import android.view.View;
import android.text.Editable;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View$OnFocusChangeListener;
import androidx.appcompat.widget.AppCompatEditText;

public class ClearEditText extends AppCompatEditText implements View$OnFocusChangeListener, TextWatcher
{
    private Drawable clearDrawable;
    private boolean hasFocus;
    
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
        final Drawable clearDrawable = this.getCompoundDrawables()[2];
        this.clearDrawable = clearDrawable;
        if (clearDrawable == null) {
            this.clearDrawable = ContextCompat.getDrawable(this.getContext(), R$drawable.ic_baseline_cancel_24);
        }
        this.clearDrawable.setBounds(0, 0, 60, 60);
        this.setClearIconVisible(false);
        this.setOnFocusChangeListener((View$OnFocusChangeListener)this);
        this.addTextChangedListener((TextWatcher)this);
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onFocusChange(final View view, final boolean hasFocus) {
        this.hasFocus = hasFocus;
        final boolean b = false;
        if (hasFocus) {
            boolean clearIconVisible = b;
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
        if (this.hasFocus) {
            this.setClearIconVisible(charSequence.length() > 0);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        boolean b = true;
        if (action == 1 && this.getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() <= this.getWidth() - this.getTotalPaddingRight() || motionEvent.getX() >= this.getWidth() - this.getPaddingRight()) {
                b = false;
            }
            if (b) {
                this.setText((CharSequence)"");
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    protected void setClearIconVisible(final boolean b) {
        Drawable clearDrawable;
        if (b) {
            clearDrawable = this.clearDrawable;
        }
        else {
            clearDrawable = null;
        }
        this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], clearDrawable, this.getCompoundDrawables()[3]);
    }
}
