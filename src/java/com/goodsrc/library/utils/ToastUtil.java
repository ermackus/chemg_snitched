package com.goodsrc.library.utils;

import com.goodsrc.library.core.LibraryApplication;
import android.widget.Toast;

public class ToastUtil
{
    static Toast toast;
    
    public ToastUtil() {
        System.out.println();
    }
    
    public static void hideToast() {
        final Toast toast = ToastUtil.toast;
        if (toast != null) {
            toast.cancel();
        }
    }
    
    public static void show(final CharSequence charSequence, int dip2px) {
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, dip2px)).setText(charSequence);
            dip2px = DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f);
            ToastUtil.toast.setGravity(81, 0, dip2px);
        }
        else {
            toast.setText(charSequence);
            ToastUtil.toast.setDuration(dip2px);
        }
        ToastUtil.toast.show();
    }
    
    public static void showFormatInfo(final CharSequence charSequence) {
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, 0)).setText(charSequence);
        }
        else {
            toast.setDuration(0);
            ToastUtil.toast.setText(charSequence);
        }
        ToastUtil.toast.setGravity(81, 0, DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f));
        ToastUtil.toast.show();
    }
    
    public static void showLong(int dip2px) {
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, 1)).setText(dip2px);
            dip2px = DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f);
            ToastUtil.toast.setGravity(81, 0, dip2px);
        }
        else {
            toast.setText(dip2px);
            ToastUtil.toast.setDuration(1);
        }
        ToastUtil.toast.show();
    }
    
    public static void showLong(final CharSequence charSequence) {
        if (charSequence == null) {
            return;
        }
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, 1)).setText(charSequence);
            ToastUtil.toast.setGravity(81, 0, DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f));
        }
        else {
            toast.setText(charSequence);
            ToastUtil.toast.setDuration(1);
        }
        ToastUtil.toast.show();
    }
    
    public static void showShort(int dip2px) {
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, 0)).setText(dip2px);
            dip2px = DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f);
            ToastUtil.toast.setGravity(81, 0, dip2px);
        }
        else {
            toast.setText(dip2px);
            ToastUtil.toast.setDuration(0);
        }
        ToastUtil.toast.show();
    }
    
    public static void showShort(final CharSequence charSequence) {
        if (charSequence == null) {
            return;
        }
        final Toast toast = ToastUtil.toast;
        if (toast == null) {
            (ToastUtil.toast = Toast.makeText(LibraryApplication.getContext(), (CharSequence)null, 0)).setText(charSequence);
            ToastUtil.toast.setGravity(81, 0, DisplayUtil.dip2px(LibraryApplication.getContext(), 40.0f));
        }
        else {
            toast.setText(charSequence);
            ToastUtil.toast.setDuration(0);
        }
        ToastUtil.toast.show();
    }
}
