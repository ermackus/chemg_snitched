package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.core.tool.KDThreadTool;
import com.kingagroot.kingdraw.core.model.ThreadTimerModel;
import android.graphics.PointF;
import java.io.IOException;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.data.CleanUpUtils;
import java.util.Iterator;
import com.kingagroot.kingdraw.core.model.ModelUtils;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public class OnCoreCallBack
{
    private OnKingDrawViewListener kingDrawViewListener;
    private OnBridgeCallBack onBridgeCallBack;
    private List<OnCoreAvailableListener> onCoreActionAvailableListeners;
    
    public OnCoreCallBack() {
        this.onCoreActionAvailableListeners = (List<OnCoreAvailableListener>)new ArrayList();
    }
    
    private void FormatChanged(final String s) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, s) {
            final OnCoreCallBack this$0;
            final String val$format;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    final FormatValue formatValue = ModelUtils.formJson(this.val$format, FormatValue.class);
                    if (formatValue != null) {
                        this.this$0.kingDrawViewListener.onFormatChanged(formatValue);
                    }
                }
            }
        });
    }
    
    private void SelectMolecule(final float n, final float n2) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, n, n2) {
            final OnCoreCallBack this$0;
            final float val$x;
            final float val$y;
            
            public void run() {
                if (this.this$0.onBridgeCallBack != null) {
                    this.this$0.onBridgeCallBack.SelectMolecule(this.val$x, this.val$y);
                }
            }
        });
    }
    
    private void alignAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).alignAvailable(this.val$available);
                }
            }
        });
    }
    
    private void analysisAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).analysisAvailable(this.val$available);
                }
            }
        });
    }
    
    private void backStepCallBack(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).backStepCallBack(this.val$available);
                }
            }
        });
    }
    
    private String chemFormulaParse(final String s) {
        final OnBridgeCallBack onBridgeCallBack = this.onBridgeCallBack;
        if (onBridgeCallBack != null) {
            return onBridgeCallBack.chemFormulaParse(s);
        }
        return "";
    }
    
    private String cleanUp(final String s, final float n, final float n2) {
        return CleanUpUtils.cleanUp(s, n, n2);
    }
    
    private void cleanUpAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).cleanUpAvailable(this.val$available);
                }
            }
        });
    }
    
    private void configChange(final String s) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, s) {
            final OnCoreCallBack this$0;
            final String val$configStr;
            
            public void run() {
                final PaletteConfigModel paletteConfigModel = ModelUtils.formJson(this.val$configStr, PaletteConfigModel.class);
                if (this.this$0.kingDrawViewListener != null && paletteConfigModel != null) {
                    this.this$0.kingDrawViewListener.onConfigChange(paletteConfigModel);
                }
            }
        });
    }
    
    private void copyAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).copyAvailable(this.val$available);
                }
            }
        });
    }
    
    private void cutAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).cutAvailable(this.val$available);
                }
            }
        });
    }
    
    private void eraserAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).eraserAvailable(this.val$available);
                }
            }
        });
    }
    
    private String findChirlity(final String s) {
        final OnBridgeCallBack onBridgeCallBack = this.onBridgeCallBack;
        if (onBridgeCallBack != null) {
            return onBridgeCallBack.findChirlity(s);
        }
        return "";
    }
    
    private void findChirlityAsync(final String s) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, s) {
            final OnCoreCallBack this$0;
            final String val$json;
            
            public void run() {
                if (this.this$0.onBridgeCallBack != null) {
                    this.this$0.onBridgeCallBack.findChirlityAsync(this.val$json);
                }
            }
        });
    }
    
    private void forwardStepCallBack(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).forwardStepCallBack(this.val$available);
                }
            }
        });
    }
    
    private void longPressBlankSpace(final float n, final float n2) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, n, n2) {
            final OnCoreCallBack this$0;
            final float val$x;
            final float val$y;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.longPressBlankSpace(this.val$x, this.val$y);
                }
            }
        });
    }
    
    private void longPressSelectSpace() {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this) {
            final OnCoreCallBack this$0;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.longPressSelectSpace();
                }
            }
        });
    }
    
    private void mirrorAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).mirrorAvailable(this.val$available);
                }
            }
        });
    }
    
    private void onDrag(final boolean b) {
        final OnKingDrawViewListener kingDrawViewListener = this.kingDrawViewListener;
        if (kingDrawViewListener != null) {
            kingDrawViewListener.onDrag(b);
        }
    }
    
    private void onElementChanged(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$isEmpty;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.onElementChanged(this.val$isEmpty);
                }
            }
        });
    }
    
    private void onScaleChanged(final float n) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, n) {
            final OnCoreCallBack this$0;
            final float val$value;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.onScaleChanged(this.val$value);
                }
            }
        });
    }
    
    private void onSelectEleChanged(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$selected;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.onSelectEleChanged(this.val$selected);
                }
            }
        });
    }
    
    private void onStartInput(final boolean b, final String s, final float n, final float n2) {
        final OnKingDrawViewListener kingDrawViewListener = this.kingDrawViewListener;
        if (kingDrawViewListener != null) {
            kingDrawViewListener.onStartInput(b, s, n, n2);
        }
    }
    
    private void paletteChangedTool(final String s, final String s2) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, s, s2) {
            final OnCoreCallBack this$0;
            final String val$param;
            final String val$toolName;
            
            public void run() {
                if (this.this$0.kingDrawViewListener != null) {
                    this.this$0.kingDrawViewListener.paletteChangedTool(this.val$toolName, this.val$param);
                }
            }
        });
    }
    
    private String parseSUPContent(final String s) {
        return ProtocolReader.builder().setNeedKDJson(true).readByString(s).josn;
    }
    
    private String parseSUPKDXContent(String josn) {
        try {
            josn = ProtocolReader.builder().setNeedKDJson(true).readByPath(josn).josn;
            return josn;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private void recognizeGesture(final List<List<PointF>> list) {
        GestureCallBack.recognizeGesture((List)list);
    }
    
    private void scaleZoomInAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).scaleZoomInAvailable(this.val$available);
                }
            }
        });
    }
    
    private void scaleZoomOutAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).scaleZoomOutAvailable(this.val$available);
                }
            }
        });
    }
    
    private void show3DAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).show3DAvailable(this.val$available);
                }
            }
        });
    }
    
    private void stickAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).stickAvailable(this.val$available);
                }
            }
        });
    }
    
    private void struct2Name(final String s) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, s) {
            final OnCoreCallBack this$0;
            final String val$json;
            
            public void run() {
                if (this.this$0.onBridgeCallBack != null) {
                    this.this$0.onBridgeCallBack.struct2Name(this.val$json);
                }
            }
        });
    }
    
    private void struct2NameAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).struct2NameAvailable(this.val$available);
                }
            }
        });
    }
    
    private void threadCancel(final ThreadTimerModel threadTimerModel) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, threadTimerModel) {
            final OnCoreCallBack this$0;
            final ThreadTimerModel val$model;
            
            public void run() {
                KDThreadTool.getInstance().threadCancel(this.val$model);
            }
        });
    }
    
    private void threadStart(final ThreadTimerModel threadTimerModel) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, threadTimerModel) {
            final OnCoreCallBack this$0;
            final ThreadTimerModel val$model;
            
            public void run() {
                KDThreadTool.getInstance().threadStart(this.val$model);
            }
        });
    }
    
    private void trashAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).trashAvailable(this.val$available);
                }
            }
        });
    }
    
    public void addCoreAvailableListener(final OnCoreAvailableListener onCoreAvailableListener) {
        this.onCoreActionAvailableListeners.add((Object)onCoreAvailableListener);
    }
    
    public OnKingDrawViewListener getKingDrawViewListener() {
        return this.kingDrawViewListener;
    }
    
    public void saveAvailable(final boolean b) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, b) {
            final OnCoreCallBack this$0;
            final boolean val$available;
            
            public void run() {
                final Iterator iterator = this.this$0.onCoreActionAvailableListeners.iterator();
                while (iterator.hasNext()) {
                    ((OnCoreAvailableListener)iterator.next()).saveAvailable(this.val$available);
                }
            }
        });
    }
    
    public void setKingDrawViewListener(final OnKingDrawViewListener kingDrawViewListener) {
        this.kingDrawViewListener = kingDrawViewListener;
    }
    
    public void setOnBridgeCallBack(final OnBridgeCallBack onBridgeCallBack) {
        this.onBridgeCallBack = onBridgeCallBack;
    }
}
