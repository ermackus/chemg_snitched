package com.kingagroot.kingdraw.core.view;

import android.view.MotionEvent;
import android.graphics.Canvas;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.os.CountDownTimer;
import com.kingagroot.kingdraw.core.graphics.MagnifierCanvasHolder;
import android.graphics.RectF;
import android.animation.AnimatorSet;
import android.widget.FrameLayout;

public class MagnifierView extends FrameLayout implements DrawBaseView
{
    private static final int COUNTTIMER_DURATION = 300;
    private static final int SHOW_DURATION = 200;
    private static final String TAG = "MagnifierView";
    private AnimatorSet animatorShow;
    RectF bond;
    MagnifierCanvasHolder canvasHolder;
    private CountDownTimer countDownTimer;
    private MagnifierView.MagnifierView$MirrorView mirrorView;
    private BasePaletteView paletteView;
    
    public MagnifierView(final Context context) {
        this(context, null);
    }
    
    public MagnifierView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public MagnifierView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.bond = new RectF(0.0f, 0.0f, 100.0f, 100.0f);
        this.init();
    }
    
    private void init() {
        (this.canvasHolder = new MagnifierCanvasHolder((DrawBaseView)this)).setBounds(this.bond);
        this.canvasHolder.setTag("MagnifierView");
        this.addView((View)(this.mirrorView = new MagnifierView.MagnifierView$MirrorView(this, this.getContext())));
        this.animatorShow = new AnimatorSet();
        this.setVisibility(4);
        this.countDownTimer = (CountDownTimer)new MagnifierView$1(this, 300L, 300L);
    }
    
    private void startShowAnim() {
        this.setVisibility(0);
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this, "alpha", new float[] { 0.0f, 1.0f });
        ofFloat.addListener((Animator$AnimatorListener)new MagnifierView$2(this));
        this.animatorShow.setDuration(200L);
        this.animatorShow.play((Animator)ofFloat);
        this.animatorShow.start();
    }
    
    public void contactPaletteView(final BasePaletteView paletteView) {
        this.paletteView = paletteView;
    }
    
    public void dismiss() {
        this.animatorShow.cancel();
        this.countDownTimer.cancel();
        this.setVisibility(8);
    }
    
    public final void endDraw(final Canvas canvas) {
        this.mirrorView.unlockCanvasAndPost(canvas);
    }
    
    public final MagnifierCanvasHolder getCanvas() {
        return this.canvasHolder;
    }
    
    public final boolean isAvailable() {
        return this.mirrorView.isAvailable();
    }
    
    public boolean isShow() {
        return this.getVisibility() == 0;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    this.canvasHolder.setTouchPoint(motionEvent.getX(), motionEvent.getY());
                    return super.onTouchEvent(motionEvent);
                }
                if (action != 3) {
                    motionEvent.getAction();
                    return super.onTouchEvent(motionEvent);
                }
            }
            this.dismiss();
        }
        else {
            this.show();
            this.canvasHolder.setTouchPoint(motionEvent.getX(), motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void show() {
        this.countDownTimer.start();
    }
    
    public final Canvas startDraw() {
        return this.mirrorView.lockCanvas();
    }
    
    public void updataNotification() {
    }
}
