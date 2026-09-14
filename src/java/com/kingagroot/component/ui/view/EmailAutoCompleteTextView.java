package com.kingagroot.component.ui.view;

import android.view.MotionEvent;
import android.text.Editable;
import android.view.View$OnFocusChangeListener;
import android.widget.ListAdapter;
import com.kingagroot.component.ui.R$layout;
import com.kingagroot.component.ui.R$array;
import androidx.core.content.ContextCompat;
import com.kingagroot.component.ui.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public class EmailAutoCompleteTextView extends AppCompatAutoCompleteTextView implements TextWatcher
{
    private String[] emailList;
    public boolean hasFocus;
    private Drawable mClearDrawable;
    
    public EmailAutoCompleteTextView(final Context context) {
        this(context, null);
    }
    
    public EmailAutoCompleteTextView(final Context context, final AttributeSet set) {
        this(context, set, 16842862);
    }
    
    public EmailAutoCompleteTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(context);
    }
    
    private void init(final Context context) {
        final Drawable mClearDrawable = this.getCompoundDrawables()[2];
        this.mClearDrawable = mClearDrawable;
        if (mClearDrawable == null) {
            this.mClearDrawable = ContextCompat.getDrawable(this.getContext(), R$drawable.ic_baseline_cancel_24);
        }
        if (this.emailList == null) {
            this.emailList = this.getResources().getStringArray(R$array.recommend_mail_box);
        }
        final Drawable mClearDrawable2 = this.mClearDrawable;
        mClearDrawable2.setBounds(0, 0, mClearDrawable2.getIntrinsicWidth(), this.mClearDrawable.getIntrinsicHeight());
        this.setClearIcoVisible(false);
        this.addTextChangedListener((TextWatcher)this);
        this.setAdapter((ListAdapter)new EmailAutoCompleteTextView.EmailAutoCompleteTextView$EmailAutoCompleteAdapter(this, context, R$layout.item_email, this.emailList));
        this.setThreshold(1);
        this.setOnFocusChangeListener((View$OnFocusChangeListener)new EmailAutoCompleteTextView$1(this));
    }
    
    private void setClearIcoVisible(final boolean b) {
        Drawable mClearDrawable;
        if (b) {
            mClearDrawable = this.mClearDrawable;
        }
        else {
            mClearDrawable = null;
        }
        this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], mClearDrawable, this.getCompoundDrawables()[3]);
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        if (this.hasFocus) {
            this.setClearIcoVisible(this.length() > 0);
        }
        super.onTextChanged(charSequence, n, n2, n3);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.getCompoundDrawables()[2] != null && (motionEvent.getX() > this.getWidth() - this.getTotalPaddingRight() && motionEvent.getX() < this.getWidth() - this.getPaddingRight())) {
            this.setText((CharSequence)"");
        }
        return super.onTouchEvent(motionEvent);
    }
    
    protected void performFiltering(final CharSequence charSequence, final int n) {
        final String string = charSequence.toString();
        final int index = string.indexOf("@");
        if (index == -1) {
            if (string.matches("^[a-zA-Z0-9_]+$")) {
                super.performFiltering((CharSequence)"@", n);
            }
            else {
                this.dismissDropDown();
            }
        }
        else {
            super.performFiltering((CharSequence)string.substring(index), n);
        }
    }
    
    protected void replaceText(final CharSequence charSequence) {
        final String string = this.getText().toString();
        final int index = string.indexOf("@");
        String substring = string;
        if (index != -1) {
            substring = string.substring(0, index);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(substring);
        sb.append((Object)charSequence);
        super.replaceText((CharSequence)sb.toString());
    }
    
    public void setAdapterString(final String[] emailList) {
        if (emailList != null && emailList.length > 0) {
            this.emailList = emailList;
        }
    }
}
