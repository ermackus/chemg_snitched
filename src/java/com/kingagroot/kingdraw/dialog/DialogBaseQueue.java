package com.kingagroot.kingdraw.dialog;

import android.app.Dialog;

public interface DialogBaseQueue
{
    void addDailog(final Dialog p0);
    
    boolean idleShowDialog(final Dialog p0);
    
    void onDestory();
    
    Dialog popup();
    
    void showNext();
}
