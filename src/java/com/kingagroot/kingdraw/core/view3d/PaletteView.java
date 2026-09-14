package com.kingagroot.kingdraw.core.view3d;

import com.kingagroot.kingdraw.core.view3d.utils.GDensityUtil;
import java.util.Collection;
import android.opengl.GLSurfaceView$Renderer;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.kingdraw.core.view3d.opengl.MyGlRender;
import com.kingagroot.kingdraw.core.view3d.tool.GMatrixTool;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import java.util.List;
import android.opengl.GLSurfaceView;

public class PaletteView extends GLSurfaceView implements PaletteBaseView
{
    private int Mode;
    private List<String> filters;
    List<KdFragment> fragments;
    private GMatrixTool matrixTool;
    MyGlRender renderer;
    
    public PaletteView(final Context context) {
        this(context, null);
    }
    
    public PaletteView(final Context context, final AttributeSet set) {
        super(context, set);
        this.fragments = (List<KdFragment>)new ArrayList();
        this.matrixTool = new GMatrixTool();
        this.Mode = 2;
        this.filters = (List<String>)new ArrayList();
        this.init();
    }
    
    private void init() {
        this.setEGLContextClientVersion(2);
        this.renderer = new MyGlRender((PaletteBaseView)this);
        this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer((GLSurfaceView$Renderer)this.renderer);
        this.setRenderMode(0);
    }
    
    public List<KdFragment> getElements() {
        return this.fragments;
    }
    
    public List<String> getFilterElements() {
        return this.filters;
    }
    
    public GMatrixTool getGMatrixTool() {
        return this.matrixTool;
    }
    
    public int getModel() {
        return this.Mode;
    }
    
    public void moveToCenter() {
        this.matrixTool.rest();
        this.requestRender();
    }
    
    public void rotate(final float n, final float n2, final float n3) {
        this.matrixTool.postRotate(n, n2, n3);
        this.requestRender();
    }
    
    public void setElements(final List<KdFragment> list) {
        this.fragments.clear();
        this.fragments.addAll((Collection)list);
        this.requestRender();
    }
    
    public void setFilterElement(final List<String> filters) {
        this.filters = filters;
        this.requestRender();
    }
    
    public void setModel(final int mode) {
        this.Mode = mode;
        this.requestRender();
    }
    
    public void setScale(final float n) {
        this.matrixTool.postScale(n, n, n);
        this.requestRender();
    }
    
    public void setTranslate(final float n, final float n2, final float n3) {
        this.matrixTool.postTranslate(-GDensityUtil.px2cm(n), GDensityUtil.px2cm(n2), GDensityUtil.px2cm(n3));
        this.requestRender();
    }
}
