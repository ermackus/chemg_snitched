package com.kingagroot.kingdraw.core.tool;

import com.kingagroot.kingdraw.core.data.IUPACConverter;
import com.kingagroot.kingdraw.core.StructConvertListener;

public class Struct2NameTool
{
    private String paletteId;
    
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public Struct2NameTool(final String paletteId) {
        this.paletteId = paletteId;
    }
    
    private static native void dynamicStruct2NameCallBack(final String p0, final String p1);
    
    private static native boolean name2Struct(final String p0, final String p1);
    
    public static native boolean pasteName2Struct(final float p0, final float p1, final String p2, final String p3);
    
    private static native String structToName(final String p0);
    
    private static native void structToNameCallBack(final String p0, final String p1);
    
    public void dynamicStructToName(final String s, final StructConvertListener structConvertListener) {
        IUPACConverter.builder().setConvertListener((IUPACConverter.IUPACConverterListener)new Struct2NameTool$3(this, structConvertListener)).DynamicKdJsonToIUPACName(s);
    }
    
    public void nameToStruct(final String s, final float bondLength, final float radian, final StructConvertListener structConvertListener) {
        IUPACConverter.builder().setBondLength(bondLength).setRadian(radian).setConvertListener((IUPACConverter.IUPACConverterListener)new Struct2NameTool$4(this, structConvertListener)).IUPACNameToKdJson(new String[] { s });
    }
    
    public void structToName(final StructConvertListener structConvertListener) {
        IUPACConverter.builder().setConvertListener((IUPACConverter.IUPACConverterListener)new Struct2NameTool$1(this, structConvertListener)).KdJsonToIUPACName(structToName(this.paletteId));
    }
    
    public void structToName(final String s, final StructConvertListener structConvertListener) {
        IUPACConverter.builder().setConvertListener((IUPACConverter.IUPACConverterListener)new Struct2NameTool$2(this, structConvertListener)).KdJsonToIUPACName(s);
    }
}
