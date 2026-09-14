package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;

public interface OnKingDrawViewListener
{
    void longPressBlankSpace(final float p0, final float p1);
    
    void longPressSelectSpace();
    
    void onConfigChange(final PaletteConfigModel p0);
    
    void onDrag(final boolean p0);
    
    void onElementChanged(final boolean p0);
    
    void onFormatChanged(final FormatValue p0);
    
    void onKingDrawViewAvailable();
    
    void onScaleChanged(final float p0);
    
    void onSelectEleChanged(final boolean p0);
    
    void onStartInput(final boolean p0, final String p1, final float p2, final float p3);
    
    void paletteChangedTool(final String p0, final String p1);
}
