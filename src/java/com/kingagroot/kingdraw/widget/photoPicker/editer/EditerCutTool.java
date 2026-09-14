package com.kingagroot.kingdraw.widget.photoPicker.editer;

import android.view.MotionEvent;
import android.graphics.Path;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Paint$Style;
import android.graphics.Paint$Cap;
import android.graphics.Paint;

public class EditerCutTool extends EditerBaseView
{
    private static final float BOARDLINE_PADDING = 50.0f;
    private static final int BOTTOM = 4;
    private static final int LEFT = 1;
    private static final int OUTSIDE = -1;
    private static final float RADIUS = 50.0f;
    private static final int RIGHT = 3;
    private static final int TOP = 2;
    EditerPoint Bottom;
    EditerPoint Top;
    float currentX;
    float currentY;
    EditerPoint left;
    Paint mBoardPaint;
    Paint mLinePaint;
    private int pointIndex;
    EditerPoint right;
    
    public EditerCutTool(final ImageEditView imageEditView) {
        super(imageEditView);
        this.pointIndex = -1;
        final RectF imageRect = imageEditView.getImageRect();
        this.left = new EditerPoint(imageRect.left, imageRect.centerY());
        this.Bottom = new EditerPoint(imageRect.centerX(), imageRect.bottom);
        this.right = new EditerPoint(imageRect.right, imageRect.centerY());
        this.Top = new EditerPoint(imageRect.centerX(), imageRect.top);
        (this.mBoardPaint = new Paint()).setColor(-1);
        this.mBoardPaint.setStrokeWidth(5.0f);
        this.mBoardPaint.setAntiAlias(true);
        this.mBoardPaint.setStrokeCap(Paint$Cap.ROUND);
        this.mBoardPaint.setStyle(Paint$Style.STROKE);
    }
    
    private float distance(float n, float n2, final float n3, final float n4) {
        n -= n3;
        n2 -= n4;
        return (float)Math.sqrt((double)(n * n + n2 * n2));
    }
    
    private RectF getBottomLineRect() {
        return new RectF(this.left.x, this.Bottom.y - 50.0f, this.right.x, this.Bottom.y + 50.0f);
    }
    
    private RectF getLeftLineRect() {
        return new RectF(this.left.x - 50.0f, this.Top.y, this.left.x + 50.0f, this.Bottom.y);
    }
    
    private RectF getRightLineRect() {
        return new RectF(this.right.x - 50.0f, this.Top.y, this.right.x + 50.0f, this.Bottom.y);
    }
    
    private RectF getTopLineRect() {
        return new RectF(this.left.x, this.Top.y - 50.0f, this.right.x, this.Top.y + 50.0f);
    }
    
    public void cancle() {
    }
    
    protected void onDraw(final Canvas canvas) {
        canvas.save();
        final Path path = new Path();
        path.moveTo(this.left.x, this.Top.y);
        path.lineTo(this.right.x, this.Top.y);
        path.lineTo(this.right.x, this.Bottom.y);
        path.lineTo(this.left.x, this.Bottom.y);
        path.lineTo(this.left.x, this.Top.y);
        canvas.drawPath(path, this.mBoardPaint);
        canvas.restore();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        final int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2) {
                final float n = this.currentX - x;
                final float n2 = this.currentY - y;
                final int pointIndex = this.pointIndex;
                if (pointIndex == 1) {
                    final EditerPoint left = this.left;
                    left.x -= n;
                }
                else if (pointIndex == 2) {
                    final EditerPoint top = this.Top;
                    top.y -= n2;
                }
                else if (pointIndex == 3) {
                    final EditerPoint right = this.right;
                    right.x -= n;
                }
                else if (pointIndex == 4) {
                    final EditerPoint bottom = this.Bottom;
                    bottom.y -= n2;
                }
                this.currentX = x;
                this.currentY = y;
                this.setNeedRefresh(true);
            }
        }
        else {
            this.pointIndex = -1;
            if (this.getLeftLineRect().contains(x, y)) {
                this.pointIndex = 1;
            }
            else if (this.getTopLineRect().contains(x, y)) {
                this.pointIndex = 2;
            }
            else if (this.getRightLineRect().contains(x, y)) {
                this.pointIndex = 3;
            }
            else if (this.getBottomLineRect().contains(x, y)) {
                this.pointIndex = 4;
            }
            this.currentX = x;
            this.currentY = y;
        }
        return true;
    }
    
    public void save() {
    }
}
