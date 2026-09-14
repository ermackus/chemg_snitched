package com.kingagroot.kingdraw.utils.fontutil;

import android.os.Message;
import android.os.Handler;

final class MessageHandler extends Handler
{
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_ITEM_SELECTED = 3000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    final LoopView loopview;
    
    MessageHandler(final LoopView loopview) {
        this.loopview = loopview;
    }
    
    public final void handleMessage(final Message message) {
        final int what = message.what;
        if (what != 1000) {
            if (what != 2000) {
                if (what == 3000) {
                    this.loopview.onItemSelected();
                }
            }
            else {
                this.loopview.smoothScroll(LoopView.ACTION.FLING);
            }
        }
        else {
            this.loopview.invalidate();
        }
    }
}
