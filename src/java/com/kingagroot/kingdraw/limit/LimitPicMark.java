package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitPicMark
{
    public static final String TAG = "LimitPicMark";
    private final OnExportPicMarkCheck onExportPicMarkCheck;
    
    public LimitPicMark(final OnExportPicMarkCheck onExportPicMarkCheck) {
        this.onExportPicMarkCheck = onExportPicMarkCheck;
    }
    
    public void checkExportPic(final Context context) {
        Object o;
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = (LimitLoginConfigDbiMpl)(o = new LimitLoginConfigDbiMpl());
            if (!NetworkUtil.isNetworkConnected(context)) {
                o = limitLoginConfigDbiMpl;
                if (DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                    MApplication.getInstance().userLogout();
                    o = new LimitConfigDbiMpl();
                }
            }
        }
        else {
            o = new LimitConfigDbiMpl();
        }
        if (((LimitConfigDbi)o).getPicWatermarkState()) {
            final OnExportPicMarkCheck onExportPicMarkCheck = this.onExportPicMarkCheck;
            if (onExportPicMarkCheck != null) {
                onExportPicMarkCheck.onExportMarkPic(true);
            }
        }
        else {
            final OnExportPicMarkCheck onExportPicMarkCheck2 = this.onExportPicMarkCheck;
            if (onExportPicMarkCheck2 != null) {
                onExportPicMarkCheck2.onExportMarkPic(false);
            }
        }
    }
    
    public interface OnExportPicMarkCheck
    {
        void onExportMarkPic(final boolean p0);
    }
}
