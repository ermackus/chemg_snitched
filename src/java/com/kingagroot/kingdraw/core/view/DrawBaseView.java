package com.kingagroot.kingdraw.core.view;

import android.graphics.Canvas;

public interface DrawBaseView
{
    void endDraw(final Canvas p0);
    
    boolean isAvailable();
    
    Canvas startDraw();
    
    void updataNotification();
}
