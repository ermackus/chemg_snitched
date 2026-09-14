package com.kingagroot.kingdraw.core.view3d;

import android.view.MotionEvent;
import java.util.TimerTask;
import java.util.List;
import com.kingagroot.kingdraw.core.view3d.tool.GestureTool;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import java.util.Timer;
import com.kingagroot.kingdraw.core.view3d.tool.BaseTool;
import android.widget.FrameLayout;

public class Chem3DView extends FrameLayout
{
    private BaseTool baseTool;
    private boolean isRotate;
    private PaletteView paletteView;
    Timer timer;
    
    public Chem3DView(final Context context) {
        this(context, null);
    }
    
    public Chem3DView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public Chem3DView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.timer = null;
        this.isRotate = true;
        this.init();
    }
    
    private void init() {
        this.addView((View)(this.paletteView = new PaletteView(this.getContext())));
        this.baseTool = (BaseTool)new GestureTool(this.paletteView);
        this.paletteView.setElements((List)DataElements.getAllElements());
        this.rotateView();
    }
    
    private void rotateView() {
        if (this.isRotate) {
            final Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
            }
            (this.timer = new Timer()).schedule((TimerTask)new TimerTask(this) {
                final Chem3DView this$0;
                
                public void run() {
                    this.this$0.paletteView.rotate(0.0f, -0.01f, 0.0f);
                }
            }, 0L, 10L);
        }
    }
    
    public void onPause() {
        this.paletteView.onPause();
    }
    
    public void onResume() {
        this.paletteView.onResume();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final BaseTool baseTool = this.baseTool;
        if (baseTool != null) {
            baseTool.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            if (this.isRotate) {
                final Timer timer = this.timer;
                if (timer != null) {
                    timer.cancel();
                }
            }
        }
        else if (motionEvent.getAction() == 1) {
            this.rotateView();
        }
        return true;
    }
    
    public void setIsRotate(final boolean isRotate) {
        this.isRotate = isRotate;
        if (isRotate) {
            this.rotateView();
        }
        else {
            final Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
            }
        }
    }
    
    public void setModel(final int model) {
        this.paletteView.setModel(model);
    }
    
    public void showH(final boolean b) {
        final List filterElements = this.paletteView.getFilterElements();
        int i = 0;
        Label_0053: {
            if (filterElements != null) {
                for (int i = 0; i < filterElements.size(); ++i) {
                    if (((String)filterElements.get(i)).equals((Object)"H")) {
                        break Label_0053;
                    }
                }
            }
            i = -1;
        }
        if (b) {
            if (i != -1) {
                filterElements.remove(i);
            }
        }
        else if (i == -1) {
            filterElements.add((Object)"H");
        }
        this.paletteView.setFilterElement(filterElements);
    }
}
