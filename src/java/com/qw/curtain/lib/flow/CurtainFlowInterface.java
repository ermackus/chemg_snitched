package com.qw.curtain.lib.flow;

import android.view.View;

public interface CurtainFlowInterface
{
     <T extends View> T findViewInCurrentCurtain(final int p0);
    
    void finish();
    
    void pop();
    
    void push();
    
    void toCurtainById(final int p0);
}
