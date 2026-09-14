package com.kingagroot.kingdraw.core.data;

public class ProtocolConverter
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static native String json2dTojson3D(final String p0);
    
    public static native String jsonToSmiles(final String p0);
    
    public static native String mol2dTo3D(final String p0);
    
    public static native String molToFormula(final String p0);
    
    public static native String molToSmiles(final String p0);
    
    public static native String smiles2dTo3D(final String p0);
    
    public static DataOutModel smilesConvertTojson(final String s, final double n, final double n2) {
        final String smilesTojson = smilesTojson(s, n, n2);
        final DataOutModel dataOutModel = new DataOutModel();
        dataOutModel.parse(smilesTojson);
        return dataOutModel;
    }
    
    public static native String smilesToFormula(final String p0);
    
    public static native String smilesToMol(final String p0);
    
    public static native String smilesToMol(final String p0, final double p1, final double p2);
    
    public static native String smilesToMol(final String p0, final double p1, final double p2, final String p3);
    
    private static native String smilesTojson(final String p0, final double p1, final double p2);
}
