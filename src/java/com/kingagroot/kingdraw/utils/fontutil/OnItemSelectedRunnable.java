package com.kingagroot.kingdraw.utils.fontutil;

final class OnItemSelectedRunnable implements Runnable
{
    final LoopView loopView;
    
    OnItemSelectedRunnable(final LoopView loopView) {
        this.loopView = loopView;
    }
    
    public final void run() {
        this.loopView.onItemSelectedListener.onItemSelected(this.loopView.getSelectedItem());
    }
}
