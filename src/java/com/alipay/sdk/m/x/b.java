package com.alipay.sdk.m.x;

import com.alipay.sdk.m.u.e;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnKeyListener;
import android.app.Dialog;
import android.text.TextUtils;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;

public class b
{
    public static AlertDialog$Builder a(final Context context, final String s, final String s2, final DialogInterface$OnClickListener dialogInterface$OnClickListener, final String s3, final DialogInterface$OnClickListener dialogInterface$OnClickListener2) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        if (!TextUtils.isEmpty((CharSequence)s3) && dialogInterface$OnClickListener2 != null) {
            alertDialog$Builder.setPositiveButton((CharSequence)s3, dialogInterface$OnClickListener2);
        }
        if (!TextUtils.isEmpty((CharSequence)s2) && dialogInterface$OnClickListener != null) {
            alertDialog$Builder.setNegativeButton((CharSequence)s2, dialogInterface$OnClickListener);
        }
        return alertDialog$Builder;
    }
    
    public static Dialog a(final Context context, String create, final String message, final String s, final DialogInterface$OnClickListener dialogInterface$OnClickListener, final String s2, final DialogInterface$OnClickListener dialogInterface$OnClickListener2) {
        final AlertDialog$Builder a = a(context, create, s, dialogInterface$OnClickListener, s2, dialogInterface$OnClickListener2);
        a.setTitle((CharSequence)create);
        a.setMessage((CharSequence)message);
        create = (String)a.create();
        ((Dialog)create).setCanceledOnTouchOutside(false);
        ((Dialog)create).setOnKeyListener((DialogInterface$OnKeyListener)new DialogInterface$OnKeyListener() {
            public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
                return n == 4;
            }
        });
        try {
            ((Dialog)create).show();
        }
        finally {
            final Throwable t;
            e.a("mspl", "showDialog ", t);
        }
        return (Dialog)create;
    }
}
