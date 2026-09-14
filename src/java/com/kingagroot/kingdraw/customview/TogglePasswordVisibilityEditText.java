package com.kingagroot.kingdraw.customview;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.text.method.HideReturnsTransformationMethod;
import android.view.MotionEvent;
import com.goodsrc.ui.library.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatEditText;

public class TogglePasswordVisibilityEditText extends AppCompatEditText
{
    private boolean visibility;
    private Drawable visibilityDrawable;
    
    public TogglePasswordVisibilityEditText(final Context context) {
        this(context, null);
    }
    
    public TogglePasswordVisibilityEditText(final Context context, final AttributeSet set) {
        this(context, set, 16842862);
    }
    
    public TogglePasswordVisibilityEditText(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.visibility = false;
        this.init();
    }
    
    private void init() {
        final Drawable visibilityDrawable = this.getCompoundDrawables()[2];
        this.visibilityDrawable = visibilityDrawable;
        if (visibilityDrawable == null) {
            this.visibilityDrawable = this.getResources().getDrawable(R$drawable.ic_baseline_visibility_off_24);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.getCompoundDrawables()[2] != null && (motionEvent.getX() > this.getWidth() - (this.visibilityDrawable.getIntrinsicWidth() + this.getCompoundPaddingRight()) && motionEvent.getX() < this.getWidth() - (this.getTotalPaddingRight() - this.getCompoundPaddingRight()))) {
            final boolean visibility = this.visibility ^ true;
            this.visibility = visibility;
            if (visibility) {
                this.visibilityDrawable = this.getResources().getDrawable(R$drawable.ic_baseline_visibility_24);
                this.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
            }
            else {
                this.visibilityDrawable = this.getResources().getDrawable(R$drawable.ic_baseline_visibility_off_24);
                this.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
            }
            final Editable text = this.getText();
            if (text instanceof Spannable) {
                Selection.setSelection((Spannable)text, ((Spannable)text).length());
            }
            final Drawable visibilityDrawable = this.visibilityDrawable;
            visibilityDrawable.setBounds(0, 0, visibilityDrawable.getMinimumWidth(), this.visibilityDrawable.getMinimumHeight());
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], this.visibilityDrawable, this.getCompoundDrawables()[3]);
        }
        return super.onTouchEvent(motionEvent);
    }
}
