package com.lzf.easyfloat.utils;

import android.view.View$OnTouchListener;
import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.internal.Intrinsics;
import android.widget.EditText;
import com.lzf.easyfloat.core.FloatingWindowHelper;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.lzf.easyfloat.core.FloatingWindowManager;
import kotlin.jvm.JvmStatic;
import kotlin.Unit;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007J!\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0001¢\u0006\u0002\b\u000bJ\u001c\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\r" }, d2 = { "Lcom/lzf/easyfloat/utils/InputMethodUtils;", "", "()V", "closedInputMethod", "", "tag", "", "(Ljava/lang/String;)Lkotlin/Unit;", "initInputMethod", "editText", "Landroid/widget/EditText;", "initInputMethod$easyfloat_release", "openInputMethod", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class InputMethodUtils
{
    public static final InputMethodUtils INSTANCE;
    
    static {
        INSTANCE = new InputMethodUtils();
    }
    
    private InputMethodUtils() {
    }
    
    @JvmStatic
    public static final Unit closedInputMethod() {
        return closedInputMethod$default(null, 1, null);
    }
    
    @JvmStatic
    public static final Unit closedInputMethod(final String s) {
        final FloatingWindowHelper helper = FloatingWindowManager.INSTANCE.getHelper(s);
        Unit instance;
        if (helper != null) {
            helper.getParams().flags = 40;
            helper.getWindowManager().updateViewLayout((View)helper.getFrameLayout(), (ViewGroup$LayoutParams)helper.getParams());
            instance = Unit.INSTANCE;
        }
        else {
            instance = null;
        }
        return instance;
    }
    
    public static /* synthetic */ Unit closedInputMethod$default(String s, final int n, final Object o) {
        if ((n & 0x1) != 0x0) {
            s = null;
        }
        return closedInputMethod(s);
    }
    
    @JvmStatic
    public static final void openInputMethod(final EditText editText) {
        openInputMethod$default(editText, null, 2, null);
    }
    
    @JvmStatic
    public static final void openInputMethod(final EditText editText, final String s) {
        Intrinsics.checkNotNullParameter((Object)editText, "editText");
        final FloatingWindowHelper helper = FloatingWindowManager.INSTANCE.getHelper(s);
        if (helper != null) {
            helper.getParams().flags = 32;
            helper.getWindowManager().updateViewLayout((View)helper.getFrameLayout(), (ViewGroup$LayoutParams)helper.getParams());
        }
        new Handler(Looper.getMainLooper()).postDelayed((Runnable)new InputMethodUtils$openInputMethod.InputMethodUtils$openInputMethod$2(editText), 100L);
    }
    
    public static /* synthetic */ void openInputMethod$default(final EditText editText, String s, final int n, final Object o) {
        if ((n & 0x2) != 0x0) {
            s = null;
        }
        openInputMethod(editText, s);
    }
    
    public final void initInputMethod$easyfloat_release(final EditText editText, final String s) {
        Intrinsics.checkNotNullParameter((Object)editText, "editText");
        editText.setOnTouchListener((View$OnTouchListener)new InputMethodUtils$initInputMethod.InputMethodUtils$initInputMethod$1(editText, s));
    }
}
