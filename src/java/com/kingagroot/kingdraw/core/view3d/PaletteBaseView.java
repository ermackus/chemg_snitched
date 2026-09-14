package com.kingagroot.kingdraw.core.view3d;

import com.kingagroot.kingdraw.core.view3d.tool.GMatrixTool;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import java.util.List;

public interface PaletteBaseView
{
    List<KdFragment> getElements();
    
    List<String> getFilterElements();
    
    GMatrixTool getGMatrixTool();
    
    int getModel();
    
    void moveToCenter();
    
    void rotate(final float p0, final float p1, final float p2);
    
    void setElements(final List<KdFragment> p0);
    
    void setFilterElement(final List<String> p0);
    
    void setScale(final float p0);
    
    void setTranslate(final float p0, final float p1, final float p2);
}
