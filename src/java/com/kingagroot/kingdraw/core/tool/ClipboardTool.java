package com.kingagroot.kingdraw.core.tool;

import android.view.MotionEvent;
import org.json.JSONException;
import android.content.ClipData;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.content.ClipboardManager;
import org.json.JSONObject;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.graphics.PointF;

public class ClipboardTool extends DragTool
{
    private float TOUCHSLOP;
    private String paletteId;
    private boolean pasteEnable;
    private PointF touchDownPoint;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public ClipboardTool(final KingDrawView kingDrawView) {
        super(kingDrawView);
        this.touchDownPoint = new PointF();
        this.paletteId = kingDrawView.getPaletteId();
        final float n = (float)ViewConfiguration.get(kingDrawView.getContext()).getScaledWindowTouchSlop();
        this.TOUCHSLOP = n * n;
        this.cloneSelected();
    }
    
    private static native String cloneSelected(final String p0);
    
    private static native boolean cloneSelectedAvailability(final String p0);
    
    private static native String cutSelected(final String p0);
    
    private static native boolean cutSelectedAvailability(final String p0);
    
    private static native void pasteStruct(final PointF p0, final String p1, final String p2);
    
    private static native void pasteText(final PointF p0, final String p1, final String p2);
    
    public void cloneSelected() {
        final String cloneSelected = cloneSelected(this.paletteId);
        try {
            if (TextUtils.isEmpty((CharSequence)cloneSelected)) {
                return;
            }
            final JSONObject jsonObject = new JSONObject(cloneSelected);
            final String string = jsonObject.getString("json");
            final String string2 = jsonObject.getString("text");
            if (!TextUtils.isEmpty((CharSequence)string)) {
                ClipboardData.getInstance().setClipboardData(string);
            }
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                final ClipboardManager clipboardManager = (ClipboardManager)KingDrawConfig.getContext().getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence)"text", (CharSequence)string2));
                }
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onStop() {
        ClipboardData.getInstance().clearClipData();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        final int action = motionEvent.getAction();
        if (action == 0) {
            this.touchDownPoint.x = motionEvent.getX();
            this.touchDownPoint.y = motionEvent.getY();
            this.pasteEnable = true;
        }
        else if (action == 2) {
            final float n = motionEvent.getX() - this.touchDownPoint.x;
            final float n2 = motionEvent.getY() - this.touchDownPoint.y;
            if (n * n + n2 * n2 > (double)this.TOUCHSLOP) {
                this.pasteEnable = false;
            }
        }
        if (this.pasteEnable && action == 1 && ClipboardData.getInstance().hasClipData()) {
            pasteStruct(new PointF(motionEvent.getX(), motionEvent.getY()), ClipboardData.getInstance().getClipboardData(), this.paletteId);
        }
        return false;
    }
}
