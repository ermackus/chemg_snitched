package com.kingagroot.kingdraw.core;

import com.kingagroot.kingdraw.gesture.OnGestureListener;
import com.kingagroot.kingdraw.gesture.GestureRecognize;
import android.graphics.PointF;
import java.util.List;

public class GestureCallBack
{
    private static GestureRecognizeResultCallBack resultCallBack;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    private static native void recognitionGestureFinished(final List<List<PointF>> p0, final int p1);
    
    private static native void recognitionGestureFinishedNew(final List<List<PointF>> p0, final String p1, final String p2);
    
    public static void recognizeGesture(final List<List<PointF>> list) {
        GestureRecognize.recognize((List)list, (OnGestureListener)new GestureCallBack$1((List)list));
    }
    
    public static void setResultCallBack(final GestureRecognizeResultCallBack resultCallBack) {
        GestureCallBack.resultCallBack = resultCallBack;
    }
}
