package com.kingagroot.kingdraw.widget;

import android.view.View$OnTouchListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.text.TextWatcher;
import android.os.Handler;
import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;

public class RegionNumberEditText extends AppCompatEditText
{
    private final Context context;
    private final Runnable delayRun;
    private int digits;
    private final Handler handler;
    private float max;
    private float min;
    String str;
    private final TextWatcher textWatcher;
    
    public RegionNumberEditText(final Context context) {
        super(context);
        this.digits = 6;
        this.textWatcher = (TextWatcher)new RegionNumberEditText$2(this);
        this.handler = new Handler();
        this.delayRun = (Runnable)new RegionNumberEditText$3(this);
        this.context = context;
        this.setImeOptions(6);
    }
    
    public RegionNumberEditText(final Context context, final AttributeSet set) {
        super(context, set);
        this.digits = 6;
        this.textWatcher = (TextWatcher)new RegionNumberEditText$2(this);
        this.handler = new Handler();
        this.delayRun = (Runnable)new RegionNumberEditText$3(this);
        this.context = context;
        this.setImeOptions(6);
    }
    
    public RegionNumberEditText(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.digits = 6;
        this.textWatcher = (TextWatcher)new RegionNumberEditText$2(this);
        this.handler = new Handler();
        this.delayRun = (Runnable)new RegionNumberEditText$3(this);
        this.context = context;
        this.setImeOptions(6);
    }
    
    private String removeZero(final String s) {
        String replaceAll = s;
        if (s.indexOf(".") > 0) {
            replaceAll = s.replaceAll("0+?$", "").replaceAll("[.]$", "");
        }
        return replaceAll;
    }
    
    private void setEditText(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.setText((CharSequence)String.valueOf(this.min));
            this.setSelection(this.getText().toString().length());
        }
        else {
            final String removeZero = this.removeZero(s);
            String s2 = s;
            if (!removeZero.equals((Object)s)) {
                this.setText((CharSequence)removeZero);
                s2 = removeZero;
            }
            final float floatValue = Float.valueOf(s2);
            final float max = this.max;
            if (floatValue > max) {
                this.setText((CharSequence)String.valueOf(max));
            }
            else {
                final float min = this.min;
                if (floatValue < min) {
                    this.setText((CharSequence)String.valueOf(min));
                }
            }
            this.setSelection(this.getText().toString().length());
        }
        this.setCursorVisible(false);
    }
    
    public boolean onTextContextMenuItem(final int n) {
        return n == 16908322 || super.onTextContextMenuItem(n);
    }
    
    public void setRegion(final float max, final float min, final int digits) {
        this.max = max;
        this.min = min;
        this.digits = digits;
    }
    
    public void setTextType(final int n) {
        if (n != 0) {
            if (n == 1) {
                this.setInputType(8194);
            }
        }
        else {
            this.setInputType(2);
        }
    }
    
    public void setTextWatcher() {
        this.addTextChangedListener(this.textWatcher);
        this.setOnTouchListener((View$OnTouchListener)new RegionNumberEditText$1(this));
    }
}
