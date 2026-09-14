package com.kingagroot.kingdraw.core.Html;

import android.graphics.Canvas;

public abstract class BaseText
{
    protected float heigth;
    protected boolean isMeasure;
    protected float width;
    
    public BaseText() {
        this.isMeasure = false;
    }
    
    public abstract void draw(final Canvas p0, final float p1, final float p2);
    
    public float getHeigth() {
        if (!this.isMeasure) {
            this.measure();
        }
        return this.heigth;
    }
    
    public float getWidth() {
        if (!this.isMeasure) {
            this.measure();
        }
        return this.width;
    }
    
    public void measure() {
        if (!this.isMeasure) {
            this.isMeasure = true;
            this.onMeasure();
        }
    }
    
    public void measure(final boolean b) {
        if (!this.isMeasure) {
            this.isMeasure = true;
            this.onMeasure(b);
        }
    }
    
    protected void onMeasure() {
    }
    
    protected void onMeasure(final boolean b) {
    }
    
    protected void parse(final String s) {
    }
}
