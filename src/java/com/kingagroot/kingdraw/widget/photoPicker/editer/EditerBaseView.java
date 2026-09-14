package com.kingagroot.kingdraw.widget.photoPicker.editer;

import android.view.MotionEvent;
import android.graphics.Canvas;

public abstract class EditerBaseView
{
    protected ImageEditView editView;
    protected boolean needRefresh;
    
    public EditerBaseView(final ImageEditView editView) {
        this.editView = editView;
    }
    
    public abstract void cancle();
    
    public ImageEditView getEditView() {
        return this.editView;
    }
    
    public boolean isNeedRefresh() {
        return this.needRefresh;
    }
    
    protected abstract void onDraw(final Canvas p0);
    
    public abstract boolean onTouchEvent(final MotionEvent p0);
    
    public abstract void save();
    
    public void setEditView(final ImageEditView editView) {
        this.editView = editView;
    }
    
    public void setNeedRefresh(final boolean needRefresh) {
        this.needRefresh = needRefresh;
        if (needRefresh) {
            this.editView.setInvalidate();
        }
    }
}
