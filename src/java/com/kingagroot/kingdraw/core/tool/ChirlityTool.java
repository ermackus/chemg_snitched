package com.kingagroot.kingdraw.core.tool;

import com.kingagroot.kingdraw.core.data.DataOutModel;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import com.kingagroot.kingdraw.core.model.ChirlityAvliableEnum;

public class ChirlityTool
{
    static {
        System.loadLibrary("kingdrawCore-lib");
    }
    
    public static ChirlityAvliableEnum ChirlityAvailable(final String s) {
        final int chirlityAvailable = findChirlityAvailable(s);
        if (chirlityAvailable == 1) {
            return ChirlityAvliableEnum.Open;
        }
        if (chirlityAvailable == 2) {
            return ChirlityAvliableEnum.Close;
        }
        return ChirlityAvliableEnum.Disable;
    }
    
    public static native void closeChirlity(final String p0);
    
    public static String findChirlity(final String s) {
        final String convertFindChirlity = ProtocolUtils.convertFindChirlity(s);
        final DataOutModel dataOutModel = new DataOutModel();
        dataOutModel.parse(convertFindChirlity);
        if (dataOutModel.isSuccess()) {
            return dataOutModel.content;
        }
        return "";
    }
    
    private static native int findChirlityAvailable(final String p0);
    
    public static native void findChirlityCallback(final String p0, final String p1);
    
    public static native void showChirlity(final String p0);
}
