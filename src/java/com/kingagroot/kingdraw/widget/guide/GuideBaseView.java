package com.kingagroot.kingdraw.widget.guide;

public interface GuideBaseView
{
    void addObserver(final GuideBaseView p0);
    
    void dismiss();
    
    void next();
    
    void setBackground(final int p0);
    
    void setBackgroundAlpha(final float p0);
    
    void show();
}
