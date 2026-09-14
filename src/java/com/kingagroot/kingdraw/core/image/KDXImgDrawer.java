package com.kingagroot.kingdraw.core.image;

import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.graphics.BaseCanvasHolder;
import com.kingagroot.kingdraw.core.view.DrawBaseView;
import com.kingagroot.kingdraw.core.graphics.ImageCanvasHolder;
import com.kingagroot.kingdraw.core.graphics.CanvasController;
import android.graphics.RectF;

public class KDXImgDrawer extends BaseImageDrawer
{
    private RectF bonds;
    
    public KDXImgDrawer() {
        this.bonds = new RectF();
        this.canvasController = new CanvasController();
        (this.canvasHolder = new ImageCanvasHolder()).setDrawView((DrawBaseView)this);
        this.canvasController.addCanvas((BaseCanvasHolder)this.canvasHolder);
    }
    
    public RectF getBounds() {
        return this.bonds;
    }
    
    public String getPaletteId() {
        return "";
    }
    
    public void setBonds(final float n, final float n2, final float n3, final float n4) {
        this.bonds = new RectF(n, n2, n3, n4);
    }
    
    public void setFileContent(final String s, final FormatValue formatValue) {
    }
}
