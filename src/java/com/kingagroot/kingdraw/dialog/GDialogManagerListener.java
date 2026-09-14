package com.kingagroot.kingdraw.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;

abstract class GDialogManagerListener implements DialogInterface$OnDismissListener
{
    private DialogInterface$OnDismissListener onDismissListener;
    
    public GDialogManagerListener(final DialogInterface$OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
    
    public DialogInterface$OnDismissListener getOnDismissListener() {
        return this.onDismissListener;
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
    }
    
    public void setOnDismissListener(final DialogInterface$OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }
}
