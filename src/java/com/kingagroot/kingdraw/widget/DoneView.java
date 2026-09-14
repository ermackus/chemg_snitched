package com.kingagroot.kingdraw.widget;

import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import com.goodsrc.library.utils.DisplayUtil;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Path;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class DoneView extends View
{
    public static long ANIMTIME = 500L;
    private static final int errorColor = -2015681;
    private static final int errorPadding = 14;
    private static int strokeWidth = 6;
    private static final int succesPadding = 4;
    private static final int successcolor = -16723422;
    private boolean Animing;
    private final Point endPoint;
    Paint errorPaint;
    private final Point errorPointLB;
    private final Point errorPointLT;
    private final Point errorPointRB;
    private final Point errorPointRT;
    private boolean isDone;
    private int mSize;
    private final Point middlePoint;
    Path path;
    private final Point startPoint;
    private long startTime;
    private int step;
    Paint successPaint;
    private int type;
    
    public DoneView(final Context context) {
        super(context);
        this.startPoint = new Point();
        this.middlePoint = new Point();
        this.endPoint = new Point();
        this.errorPointRT = new Point();
        this.errorPointLB = new Point();
        this.errorPointLT = new Point();
        this.errorPointRB = new Point();
    }
    
    public DoneView(final Context context, final AttributeSet set) {
        super(context, set);
        this.startPoint = new Point();
        this.middlePoint = new Point();
        this.endPoint = new Point();
        this.errorPointRT = new Point();
        this.errorPointLB = new Point();
        this.errorPointLT = new Point();
        this.errorPointRB = new Point();
        DoneView.strokeWidth = DisplayUtil.dip2px(context, 2.0f);
        (this.successPaint = new Paint()).setStyle(Paint$Style.STROKE);
        this.successPaint.setStrokeWidth((float)DoneView.strokeWidth);
        this.successPaint.setAntiAlias(true);
        this.successPaint.setStrokeCap(Paint$Cap.ROUND);
        this.successPaint.setColor(-16723422);
        (this.errorPaint = new Paint()).setStyle(Paint$Style.STROKE);
        this.errorPaint.setStrokeWidth((float)DoneView.strokeWidth);
        this.errorPaint.setAntiAlias(true);
        this.errorPaint.setStrokeCap(Paint$Cap.ROUND);
        this.errorPaint.setColor(-2015681);
        this.path = new Path();
        this.initPoint();
    }
    
    private void drawAnimError(final Canvas canvas) {
        final int step = this.step;
        if (step == 1) {
            final float n = (float)((this.errorPointRT.x - this.errorPointLB.x) * (System.currentTimeMillis() - this.startTime) / (DoneView.ANIMTIME / 2L));
            final float n2 = this.errorPointRT.x - n;
            final float n3 = this.errorPointRT.y + n;
            float n4 = n2;
            if (n2 <= this.errorPointLB.x) {
                n4 = (float)this.errorPointLB.x;
            }
            float n5 = n3;
            if (n3 >= this.errorPointLB.y) {
                n5 = (float)this.errorPointLB.y;
            }
            this.path.lineTo(n4, n5);
            if (System.currentTimeMillis() - this.startTime >= DoneView.ANIMTIME / 2L) {
                this.startTime = System.currentTimeMillis();
                this.step = 2;
                this.path.moveTo((float)this.errorPointLT.x, (float)this.errorPointLT.y);
            }
        }
        else if (step == 2) {
            final float n6 = (float)((this.errorPointRB.x - this.errorPointLT.x) * (System.currentTimeMillis() - this.startTime) / (DoneView.ANIMTIME / 2L));
            final float n7 = this.errorPointLT.x + n6;
            final float n8 = this.errorPointLT.y + n6;
            float n9 = n7;
            if (n7 >= this.errorPointRB.x) {
                n9 = (float)this.errorPointRB.x;
            }
            float n10 = n8;
            if (n8 >= this.errorPointRB.y) {
                n10 = (float)this.errorPointRB.y;
            }
            this.path.lineTo(n9, n10);
            if (System.currentTimeMillis() - this.startTime >= DoneView.ANIMTIME / 2L) {
                this.isDone = true;
                this.step = 3;
            }
        }
    }
    
    private void drawAnimSucc(final Canvas canvas) {
        final int step = this.step;
        if (step == 1) {
            final float n = (float)((this.middlePoint.x - this.startPoint.x) * (System.currentTimeMillis() - this.startTime) / (DoneView.ANIMTIME / 3L));
            final float n2 = this.startPoint.x + n;
            float n3 = n + this.startPoint.y;
            float n4 = n2;
            if (n2 >= this.middlePoint.x) {
                n4 = (float)this.middlePoint.x;
                n3 = (float)this.middlePoint.y;
                this.step = 2;
                this.startTime = System.currentTimeMillis();
            }
            this.path.lineTo(n4, n3);
        }
        else if (step == 2) {
            final float n5 = (float)((this.endPoint.x - this.middlePoint.x) * (System.currentTimeMillis() - this.startTime) / (DoneView.ANIMTIME * 2L / 3L));
            final float n6 = this.middlePoint.x + n5;
            final float n7 = this.middlePoint.y - n5;
            float n8 = n6;
            if (n6 > this.endPoint.x) {
                n8 = (float)this.endPoint.x;
            }
            float n9 = n7;
            if (n7 < this.endPoint.y) {
                n9 = (float)this.endPoint.y;
            }
            this.path.lineTo(n8, n9);
            if (n8 >= this.endPoint.x) {
                this.isDone = true;
                this.step = 3;
                this.path.rewind();
                this.path.moveTo((float)this.startPoint.x, (float)this.startPoint.y);
                this.path.lineTo((float)this.middlePoint.x, (float)this.middlePoint.y);
                this.path.lineTo((float)this.endPoint.x, (float)this.endPoint.y);
            }
        }
    }
    
    private void drawSuccess(final Canvas canvas) {
        this.path.rewind();
        this.path.moveTo((float)this.startPoint.x, (float)this.startPoint.y);
        this.path.lineTo((float)this.middlePoint.x, (float)this.middlePoint.y);
        this.path.lineTo((float)this.endPoint.x, (float)this.endPoint.y);
    }
    
    private void initPoint() {
        this.mSize = DisplayUtil.dip2px(this.getContext(), 20.0f);
        this.startPoint.x = 4;
        this.startPoint.y = this.mSize / 2 + 4;
        this.middlePoint.x = this.mSize / 4;
        this.middlePoint.y = this.mSize * 3 / 4;
        this.endPoint.x = this.mSize * 3 / 4;
        this.endPoint.y = this.mSize * 1 / 4;
        this.errorPointRT.x = this.mSize - 14;
        this.errorPointRT.y = 14;
        this.errorPointLB.x = 14;
        this.errorPointLB.y = this.mSize - 14;
        this.errorPointLT.x = 14;
        this.errorPointLT.y = 14;
        this.errorPointRB.x = this.mSize - 14;
        this.errorPointRB.y = this.mSize - 14;
    }
    
    public void drawError(final Canvas canvas) {
        this.path.rewind();
        this.path.moveTo((float)this.errorPointRT.x, (float)this.errorPointRT.y);
        this.path.lineTo((float)this.errorPointLB.x, (float)this.errorPointLB.y);
        this.path.moveTo((float)this.errorPointLT.x, (float)this.errorPointLT.y);
        this.path.lineTo((float)this.errorPointRB.x, (float)this.errorPointRB.y);
    }
    
    public void fail() {
        this.type = 2;
        this.Animing = false;
        this.isDone = true;
        this.invalidate();
    }
    
    public void normal() {
        this.type = 3;
        this.Animing = false;
        this.isDone = true;
        this.path.rewind();
        this.invalidate();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final int type = this.type;
        if (type == 1) {
            if (this.Animing) {
                this.drawAnimSucc(canvas);
            }
            else {
                this.drawSuccess(canvas);
            }
            canvas.drawPath(this.path, this.successPaint);
        }
        else if (type == 2) {
            if (this.Animing) {
                this.drawAnimError(canvas);
            }
            else {
                this.drawError(canvas);
            }
            canvas.drawPath(this.path, this.errorPaint);
        }
        else if (type == 3) {
            final Paint paint = new Paint();
            paint.setColor(0);
            canvas.drawPath(this.path, paint);
        }
        if (!this.isDone && this.step != 0) {
            this.invalidate();
        }
    }
    
    public void startAnim() {
        this.type = 2;
    }
    
    public void startErrorAnim() {
        this.type = 2;
        this.isDone = false;
        this.Animing = true;
        this.step = 1;
        this.path.rewind();
        this.startTime = System.currentTimeMillis();
        this.path.moveTo((float)this.errorPointRT.x, (float)this.errorPointRT.y);
        this.invalidate();
    }
    
    public void startSuccessAnim() {
        this.type = 1;
        this.isDone = false;
        this.Animing = true;
        this.step = 1;
        this.path.rewind();
        this.startTime = System.currentTimeMillis();
        this.path.moveTo((float)this.startPoint.x, (float)this.startPoint.y);
        this.invalidate();
    }
    
    public void success() {
        this.type = 1;
        this.Animing = false;
        this.isDone = true;
        this.invalidate();
    }
}
