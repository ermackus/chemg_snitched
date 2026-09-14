package com.kingagroot.kingdraw.core.view3d.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import android.opengl.GLES20;
import javax.microedition.khronos.opengles.GL10;
import java.util.Iterator;
import java.util.List;
import com.kingagroot.kingdraw.core.view3d.bean.KdBondTypeEnum;
import com.kingagroot.kingdraw.core.view3d.element.KdBond;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.element.KdChemElement;
import com.kingagroot.kingdraw.core.view3d.PaletteBaseView;
import android.opengl.GLSurfaceView$Renderer;

public class MyGlRender implements GLSurfaceView$Renderer
{
    private FragTool fragTool;
    private PaletteBaseView paletteBaseView;
    
    public MyGlRender(final PaletteBaseView paletteBaseView) {
        this.paletteBaseView = paletteBaseView;
    }
    
    private boolean isFilter(final KdChemElement kdChemElement) {
        final List<String> filterElements = this.paletteBaseView.getFilterElements();
        if (filterElements != null) {
            if (kdChemElement instanceof KdNode) {
                final KdNode kdNode = (KdNode)kdChemElement;
                if (!kdNode.isR()) {
                    if (!kdNode.isUndefind()) {
                        final Iterator iterator = filterElements.iterator();
                        while (iterator.hasNext()) {
                            if (kdNode.type.equals((Object)iterator.next())) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
            if (kdChemElement instanceof KdBond) {
                final KdBond kdBond = (KdBond)kdChemElement;
                if (kdBond.type == KdBondTypeEnum.G_ANY_BOND.code) {
                    return false;
                }
                for (final KdNode kdNode2 : kdBond.getRelationElements()) {
                    final Iterator iterator3 = filterElements.iterator();
                    while (iterator3.hasNext()) {
                        if (kdNode2.type.equals((Object)iterator3.next())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void onDrawFrame(final GL10 gl10) {
        GLES20.glClear(16640);
        this.fragTool.drawStart();
        final int model = this.paletteBaseView.getModel();
        final Iterator iterator = this.paletteBaseView.getElements().iterator();
        while (iterator.hasNext()) {
            final List childElemnts = ((KdFragment)iterator.next()).getChildElemnts();
            for (int i = 0; i < childElemnts.size(); ++i) {
                final KdChemElement kdChemElement = (KdChemElement)childElemnts.get(i);
                if (this.isFilter(kdChemElement)) {
                    this.fragTool.draw(kdChemElement, model);
                }
            }
        }
        this.fragTool.drawEnd();
    }
    
    public void onSurfaceChanged(final GL10 gl10, final int n, final int n2) {
        final int max = Math.max(n, n2);
        final int min = Math.min(n, n2);
        GLES20.glViewport(0, 0, n, n2);
        final float n3 = max / (float)min;
        if (n >= n2) {
            this.paletteBaseView.getGMatrixTool().ortho(n3 * -5.0f, n3 * 5.0f, -5.0f, 5.0f, -50.0f, 100.0f);
        }
        else {
            this.paletteBaseView.getGMatrixTool().ortho(-5.0f, 5.0f, n3 * -5.0f, n3 * 5.0f, -50.0f, 100.0f);
        }
        this.paletteBaseView.getGMatrixTool().setCamera(0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }
    
    public void onSurfaceCreated(final GL10 gl10, final EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(2929);
        GLES20.glDepthFunc(515);
        GLES20.glEnable(2884);
        GLES20.glCullFace(1028);
        GLES20.glFrontFace(2304);
        (this.fragTool = new FragTool()).setVaryTools(this.paletteBaseView.getGMatrixTool());
    }
}
