package com.kingagroot.component.ui.widget.richinput;

import android.view.View;
import android.widget.EditText;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.view.MotionEvent;
import android.app.Activity;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.LinearLayout;

public abstract class InputBaseMenu extends LinearLayout
{
    protected BaseExtendMenu baseExtendMenu;
    protected Context context;
    protected InputMethodManager inputManager;
    
    public InputBaseMenu(final Context context) {
        this(context, null);
    }
    
    public InputBaseMenu(final Context context, final AttributeSet set) {
        super(context, set);
        this.context = context;
        this.inputManager = (InputMethodManager)context.getSystemService("input_method");
    }
    
    protected void hideKeyBoard() {
        final Activity activity = (Activity)this.context;
        if (activity.getCurrentFocus() != null) {
            final InputMethodManager inputManager = this.inputManager;
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
            }
        }
    }
    
    public abstract boolean keyBoardEvent(final MotionEvent p0);
    
    public abstract void onPause();
    
    public abstract void onResume();
    
    public void setBaseExtendMenu(final BaseExtendMenu baseExtendMenu) {
        this.baseExtendMenu = baseExtendMenu;
    }
    
    public abstract void setContent(final boolean p0, final String p1, final FormatValue p2);
    
    protected void showKeyBoard(final EditText editText) {
        final InputMethodManager inputManager = this.inputManager;
        if (inputManager != null) {
            inputManager.showSoftInput((View)editText, 1);
        }
    }
}
