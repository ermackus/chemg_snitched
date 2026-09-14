package com.kingagroot.kingdraw.utils.fontutil;

final class InertiaTimerTask implements Runnable
{
    float a;
    final LoopView loopView;
    final float velocityY;
    
    InertiaTimerTask(final LoopView loopView, final float velocityY) {
        this.loopView = loopView;
        this.velocityY = velocityY;
        this.a = 2.14748365E9f;
    }
    
    public final void run() {
        if (this.a == 2.14748365E9f) {
            if (Math.abs(this.velocityY) > 2000.0f) {
                if (this.velocityY > 0.0f) {
                    this.a = 2000.0f;
                }
                else {
                    this.a = -2000.0f;
                }
            }
            else {
                this.a = this.velocityY;
            }
        }
        if (Math.abs(this.a) >= 0.0f && Math.abs(this.a) <= 20.0f) {
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(2000);
            return;
        }
        final int n = (int)(this.a * 10.0f / 1000.0f);
        final LoopView loopView = this.loopView;
        loopView.totalScrollY -= n;
        if (!this.loopView.isLoop) {
            final float n2 = this.loopView.lineSpacingMultiplier * this.loopView.maxTextHeight;
            if (this.loopView.totalScrollY <= (int)(-this.loopView.initPosition * n2)) {
                this.a = 40.0f;
                final LoopView loopView2 = this.loopView;
                loopView2.totalScrollY = (int)(-loopView2.initPosition * n2);
            }
            else if (this.loopView.totalScrollY >= (int)((this.loopView.items.size() - 1 - this.loopView.initPosition) * n2)) {
                final LoopView loopView3 = this.loopView;
                loopView3.totalScrollY = (int)((loopView3.items.size() - 1 - this.loopView.initPosition) * n2);
                this.a = -40.0f;
            }
        }
        final float a = this.a;
        if (a < 0.0f) {
            this.a = a + 20.0f;
        }
        else {
            this.a = a - 20.0f;
        }
        this.loopView.handler.sendEmptyMessage(1000);
    }
}
