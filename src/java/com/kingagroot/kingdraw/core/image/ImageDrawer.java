package com.kingagroot.kingdraw.core.image;

import android.graphics.RectF;
import com.kingagroot.kingdraw.core.model.ModelUtils;
import com.kingagroot.kingdraw.core.graphics.BaseCanvasHolder;
import com.kingagroot.kingdraw.core.view.DrawBaseView;
import com.kingagroot.kingdraw.core.graphics.ImageCanvasHolder;
import com.kingagroot.kingdraw.core.graphics.CanvasController;
import com.kingagroot.kingdraw.core.model.FormatValue;

class ImageDrawer extends BaseImageDrawer
{
    private String pid;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public ImageDrawer(final FormatValue formatValue) {
        final CanvasController canvasController = new CanvasController();
        (this.canvasHolder = new ImageCanvasHolder()).setDrawView((DrawBaseView)this);
        canvasController.addCanvas((BaseCanvasHolder)this.canvasHolder);
        this.pid = createImagePalette(canvasController, ModelUtils.toJson((Object)formatValue));
    }
    
    private static native void applyFormatValue(final String p0);
    
    private static native String createImagePalette(final CanvasController p0, final String p1);
    
    private static native void deleteImagePalette(final String p0);
    
    private static native float[] getBounds(final String p0);
    
    private static native void setFileContent(final String p0, final String p1, final String p2);
    
    private static native void setFormatValue(final String p0, final String p1);
    
    public void applyFormatValue() {
        applyFormatValue(this.pid);
    }
    
    public RectF getBounds() {
        final float[] bounds = getBounds(this.pid);
        return new RectF(bounds[0], bounds[1], bounds[2], bounds[3]);
    }
    
    public String getPaletteId() {
        return this.pid;
    }
    
    public void onDestroy() {
        super.onDestroy();
        deleteImagePalette(this.pid);
    }
    
    public void setFileContent(final String s, final FormatValue formatValue) {
        setFileContent(s, ModelUtils.toJson((Object)formatValue), this.pid);
    }
    
    public void setFormatValue(final FormatValue formatValue) {
        setFormatValue(ModelUtils.toJson((Object)formatValue), this.pid);
    }
}
