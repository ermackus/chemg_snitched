package com.goodsrc.library.utils;

import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.view.View;

public class KeyBoardUtils
{
    public static void hidInput(final View view) {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    
    public static void showInput(final View view) {
        new Handler().postDelayed((Runnable)new Runnable(view) {
            final View val$view;
            
            public void run() {
                ((InputMethodManager)this.val$view.getContext().getSystemService("input_method")).showSoftInput(this.val$view, 2);
            }
        }, 300L);
    }
}
