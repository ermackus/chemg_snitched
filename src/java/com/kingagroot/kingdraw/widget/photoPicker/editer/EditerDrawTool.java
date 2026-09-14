package com.kingagroot.kingdraw.widget.photoPicker.editer;

import android.graphics.Matrix$ScaleToFit;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.graphics.Canvas;
import com.goodsrc.library.utils.SystemUtils;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;

public class EditerDrawTool extends EditerBaseView
{
    RectF ImageRectF;
    Drawable drawable;
    int drawableHeight;
    int drawableWidth;
    Matrix mMatrix;
    int viewHeight;
    int viewWidth;
    
    public EditerDrawTool(final ImageEditView imageEditView) {
        super(imageEditView);
        this.viewWidth = SystemUtils.getScreenWidth(imageEditView.getContext());
        this.viewHeight = SystemUtils.getScreenHeight(imageEditView.getContext());
        this.mMatrix = new Matrix();
    }
    
    public void cancle() {
    }
    
    public RectF getImageRectF() {
        final RectF imageRectF = new RectF(0.0f, 0.0f, (float)this.drawableWidth, (float)this.drawableHeight);
        this.ImageRectF = imageRectF;
        this.mMatrix.mapRect(imageRectF);
        return this.ImageRectF;
    }
    
    public void onDraw(final Canvas canvas) {
        canvas.save();
        canvas.setMatrix(this.mMatrix);
        this.drawable.draw(canvas);
        canvas.restore();
        this.setNeedRefresh(false);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return false;
    }
    
    public void save() {
    }
    
    public void setBitmap(final Bitmap bitmap) {
        final BitmapDrawable drawable = new BitmapDrawable(this.editView.getResources(), bitmap);
        this.drawable = (Drawable)drawable;
        this.drawableWidth = ((Drawable)drawable).getIntrinsicWidth();
        final int intrinsicHeight = this.drawable.getIntrinsicHeight();
        this.drawableHeight = intrinsicHeight;
        this.drawable.setBounds(0, 0, this.drawableWidth, intrinsicHeight);
        final float min = Math.min(1.0f, Math.min((float)(this.viewWidth / this.drawableWidth), (float)(this.viewHeight / this.drawableHeight)));
        this.mMatrix.reset();
        this.mMatrix.postScale(min, min);
        this.mMatrix.postTranslate((this.viewWidth - this.drawableWidth * min) / 2.0f, (this.viewHeight - this.drawableHeight * min) / 2.0f);
        this.mMatrix.setRectToRect(new RectF(0.0f, 0.0f, (float)this.drawableWidth, (float)this.drawableHeight), new RectF(0.0f, 0.0f, (float)this.viewWidth, (float)this.viewHeight), Matrix$ScaleToFit.CENTER);
        this.setNeedRefresh(true);
    }
}
