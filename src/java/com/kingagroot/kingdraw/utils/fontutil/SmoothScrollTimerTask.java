package com.kingagroot.kingdraw.utils.fontutil;

final class SmoothScrollTimerTask implements Runnable
{
    final LoopView loopView;
    int offset;
    int realOffset;
    int realTotalOffset;
    
    SmoothScrollTimerTask(final LoopView loopView, final int offset) {
        this.loopView = loopView;
        this.offset = offset;
        this.realTotalOffset = Integer.MAX_VALUE;
        this.realOffset = 0;
    }
    
    public final void run() {
        if (this.realTotalOffset == Integer.MAX_VALUE) {
            this.realTotalOffset = this.offset;
        }
        final int realTotalOffset = this.realTotalOffset;
        if ((this.realOffset = (int)(realTotalOffset * 0.1f)) == 0) {
            if (realTotalOffset < 0) {
                this.realOffset = -1;
            }
            else {
                this.realOffset = 1;
            }
        }
        if (Math.abs(this.realTotalOffset) <= 0) {
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(3000);
        }
        else {
            final LoopView loopView = this.loopView;
            loopView.totalScrollY += this.realOffset;
            this.loopView.handler.sendEmptyMessage(1000);
            this.realTotalOffset -= this.realOffset;
        }
    }
}
