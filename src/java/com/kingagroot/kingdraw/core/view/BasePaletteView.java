package com.kingagroot.kingdraw.core.view;

import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.core.tool.AlignTypeEnum;
import com.kingagroot.kingdraw.core.model.NodeDirectEnum;
import com.kingagroot.kingdraw.core.PaletteManager;
import com.kingagroot.kingdraw.core.model.ModelUtils;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.graphics.CanvasController;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.OnCoreCallBack;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.core.model.ChemPropertyConfig;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

class BasePaletteView extends FrameLayout
{
    protected String paletteId;
    private int touchAction;
    private float touchX;
    private float touchY;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public BasePaletteView(final Context context) {
        super(context);
    }
    
    public BasePaletteView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public BasePaletteView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public BasePaletteView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    private static native boolean MergeGroupAvailability(final String p0);
    
    private static native boolean UnMergeGroupAvailability(final String p0);
    
    private static native void addChemPropertyText(final ChemPropertyConfig p0, final String p1);
    
    private static native void applyFormatValue(final String p0);
    
    private static native boolean backStep(final String p0);
    
    private static native void clear(final String p0);
    
    private static native void contractSup(final String p0);
    
    private static native boolean contractSupAvailability(final String p0);
    
    private static native void expandSup(final String p0);
    
    private static native boolean expandSupAvailability(final String p0);
    
    private static native boolean forwardStep(final String p0);
    
    private static native String getAllElementsJson(final String p0);
    
    private static native int getCShowType(final String p0);
    
    private static native boolean getColorAtom(final String p0);
    
    private static native String getFormatValue(final String p0);
    
    private static native String getJsonMaterial(final String p0);
    
    private static native float getPaletteScale(final String p0);
    
    private static native String getSelectedColor(final String p0);
    
    private static native String getSelectedElementsJson(final String p0);
    
    private static native String getSelectedMoleculeElementsJson(final String p0);
    
    private static native int haveR(final String p0);
    
    private static native boolean haveStruct(final String p0);
    
    private static native boolean isEmpty(final String p0);
    
    private static native boolean isSelectedElements(final String p0);
    
    private boolean isTouchCancel() {
        return this.touchAction == 1;
    }
    
    private static native void mergeGroup(final String p0);
    
    private static native boolean moleculeWrong(final String p0);
    
    private static native void onInputFinish(final String p0, final String p1);
    
    private static native void onRefresh(final String p0);
    
    private static native boolean onTouchEvent(final MotionEvent p0, final String p1);
    
    private static native void pasteStruct(final String p0, final boolean p1, final boolean p2, final String p3);
    
    private static native void pasteStructForPoint(final float p0, final float p1, final String p2, final boolean p3, final String p4);
    
    private static native void restPaletteMatrix(final String p0);
    
    private static native void setActionBack(final OnCoreCallBack p0, final String p1);
    
    private static native void setAlignType(final int p0, final String p1);
    
    private static native boolean setBaseTool(final String p0, final String p1, final String p2);
    
    private static native void setCShowType(final String p0, final boolean p1, final int p2);
    
    private static native void setCanvasRealBounds(final String p0, final float p1, final float p2, final float p3, final float p4);
    
    private static native void setColorAtom(final String p0, final boolean p1, final boolean p2);
    
    private static native void setFormatValue(final String p0, final String p1);
    
    private static native boolean setNodeDirect(final int p0, final String p1);
    
    private static native void setPaletteScale(final float p0, final float p1, final float p2, final String p3);
    
    private static native boolean setSelectMolecule(final float p0, final float p1, final String p2);
    
    private static native boolean setSelectedColor(final String p0, final String p1);
    
    private void setTouchCancel() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.onTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 1, this.touchX, this.touchY, 0));
    }
    
    private static native void translatePalette(final float p0, final float p1, final String p2);
    
    private static native void unMergeGroup(final String p0);
    
    public boolean MergeGroupAvailability() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && MergeGroupAvailability(this.paletteId);
    }
    
    public boolean UnMergeGroupAvailability() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && UnMergeGroupAvailability(this.paletteId);
    }
    
    public void addChemPropertyText(final ChemPropertyConfig chemPropertyConfig) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            addChemPropertyText(chemPropertyConfig, this.paletteId);
        }
    }
    
    public void applyFormatValue() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            applyFormatValue(this.paletteId);
        }
    }
    
    public boolean backStep() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            if (!this.isTouchCancel()) {
                this.setTouchCancel();
            }
            return backStep(this.paletteId);
        }
        return false;
    }
    
    public void clear() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            if (!this.isTouchCancel()) {
                this.setTouchCancel();
            }
            clear(this.paletteId);
        }
    }
    
    public void contractSup() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            contractSup(this.paletteId);
        }
    }
    
    public boolean contractSupAvailability() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && contractSupAvailability(this.paletteId);
    }
    
    protected String createPalette(final CanvasController canvasController, final FormatValue formatValue, final PaletteConfigModel paletteConfigModel) {
        return this.paletteId = PaletteManager.createPalette(canvasController, ModelUtils.toJson(formatValue), paletteConfigModel);
    }
    
    public void expandSup() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            expandSup(this.paletteId);
        }
    }
    
    public boolean expandSupAvailability() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && expandSupAvailability(this.paletteId);
    }
    
    public boolean forwardStep() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            if (!this.isTouchCancel()) {
                this.setTouchCancel();
            }
            return forwardStep(this.paletteId);
        }
        return false;
    }
    
    public String getAllElementsJson() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getAllElementsJson(this.paletteId);
        }
        return "";
    }
    
    public int getCShowType() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getCShowType(this.paletteId);
        }
        return 0;
    }
    
    public boolean getColorAtom() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && getColorAtom(this.paletteId);
    }
    
    public FormatValue getFormatValue() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return ModelUtils.formJson(getFormatValue(this.paletteId), FormatValue.class);
        }
        return null;
    }
    
    public String getJsonMaterial() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getJsonMaterial(this.paletteId);
        }
        return "";
    }
    
    public String getPaletteId() {
        return this.paletteId;
    }
    
    public float getPaletteScale() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getPaletteScale(this.paletteId);
        }
        return 1.0f;
    }
    
    public String getSelectedColor() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getSelectedColor(this.paletteId);
        }
        return "#FF000000";
    }
    
    public String getSelectedElementsJson() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getSelectedElementsJson(this.paletteId);
        }
        return "";
    }
    
    public String getSelectedMoleculeElementsJson() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return getSelectedMoleculeElementsJson(this.paletteId);
        }
        return "";
    }
    
    public int haveR() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            return haveR(this.paletteId);
        }
        return 0;
    }
    
    public boolean haveStruct() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && haveStruct(this.paletteId);
    }
    
    public boolean isEmpty() {
        return TextUtils.isEmpty((CharSequence)this.paletteId) || isEmpty(this.paletteId);
    }
    
    public boolean isSelectedElements() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && isSelectedElements(this.paletteId);
    }
    
    public void mergeGroup() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            mergeGroup(this.paletteId);
        }
    }
    
    public boolean moleculeWrong() {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && moleculeWrong(this.paletteId);
    }
    
    public void onDestroy() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            PaletteManager.deletePalette(this.paletteId);
        }
    }
    
    public void onInputFinish(final String s) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            onInputFinish(s, this.paletteId);
        }
    }
    
    void onRefresh() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            onRefresh(this.paletteId);
        }
    }
    
    public void onResume() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            PaletteManager.stickPalette(this.paletteId);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.touchX = motionEvent.getX();
        this.touchY = motionEvent.getY();
        this.touchAction = motionEvent.getAction();
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && onTouchEvent(motionEvent, this.paletteId);
    }
    
    public void pasteStruct(final float n, final float n2, final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            pasteStructForPoint(n, n2, s, b, this.paletteId);
        }
    }
    
    public void pasteStruct(final String s, final boolean b) {
        this.pasteStruct(s, b, false);
    }
    
    public void pasteStruct(final String s, final boolean b, final boolean b2) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            pasteStruct(s, b, b2, this.paletteId);
        }
    }
    
    public void restPaletteMatrix() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            restPaletteMatrix(this.paletteId);
        }
    }
    
    protected void setActionBack(final OnCoreCallBack onCoreCallBack) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setActionBack(onCoreCallBack, this.paletteId);
        }
    }
    
    public void setCShowType(final boolean b, final int n) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setCShowType(this.paletteId, b, n);
        }
    }
    
    public void setCanvasBounds(final float n, final float n2, final float n3, final float n4) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setCanvasRealBounds(this.paletteId, n, n2, n3, n4);
        }
    }
    
    public void setColorAtom(final boolean b, final boolean b2) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setColorAtom(this.paletteId, b, b2);
        }
    }
    
    public void setFormatValue(final FormatValue formatValue) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setFormatValue(ModelUtils.toJson(formatValue), this.paletteId);
        }
    }
    
    public boolean setNodeDirect(final NodeDirectEnum nodeDirectEnum) {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && setNodeDirect(nodeDirectEnum.getCode(), this.paletteId);
    }
    
    public void setPaletteScale(final float n, final float n2, final float n3) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setPaletteScale(n, n2, n3, this.paletteId);
        }
    }
    
    public void setSelectElementAlignType(final AlignTypeEnum alignTypeEnum) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            setAlignType(Integer.parseInt(alignTypeEnum.alignType), this.paletteId);
        }
    }
    
    protected boolean setSelectMolecule(final float n, final float n2) {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && setSelectMolecule(n, n2, this.paletteId);
    }
    
    public boolean setSelectedColor(final String s) {
        return !TextUtils.isEmpty((CharSequence)this.paletteId) && setSelectedColor(s, this.paletteId);
    }
    
    public boolean setTool(final ToolNameEnum toolNameEnum, final String s) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            if (!this.isTouchCancel()) {
                this.setTouchCancel();
            }
            return setBaseTool(toolNameEnum.name, s, this.paletteId);
        }
        return false;
    }
    
    public void translatePalette(final float n, final float n2) {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            translatePalette(n, n2, this.paletteId);
        }
    }
    
    public void unMergeGroup() {
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            unMergeGroup(this.paletteId);
        }
    }
}
