package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitPic
{
    public static final String TAG = "LimitPic";
    private final OnExportPicCheck onExportPicCheck;
    
    public LimitPic(final OnExportPicCheck onExportPicCheck) {
        this.onExportPicCheck = onExportPicCheck;
    }
    
    public void checkExportPic(final Context context, final String s) {
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                if (((LimitConfigDbi)new LimitConfigDbiMpl()).getExportPicState()) {
                    final OnExportPicCheck onExportPicCheck = this.onExportPicCheck;
                    if (onExportPicCheck != null) {
                        onExportPicCheck.onExportPic();
                    }
                }
                else {
                    LimitDialog.showLoginDialog(context, s);
                }
            }
            else if (((LimitConfigDbi)limitLoginConfigDbiMpl).getExportPicState()) {
                final OnExportPicCheck onExportPicCheck2 = this.onExportPicCheck;
                if (onExportPicCheck2 != null) {
                    onExportPicCheck2.onExportPic();
                }
            }
            else {
                LimitDialog.showVipDialog(context, context.getString(2131820980));
            }
        }
        else if (((LimitConfigDbi)new LimitConfigDbiMpl()).getExportPicState()) {
            final OnExportPicCheck onExportPicCheck3 = this.onExportPicCheck;
            if (onExportPicCheck3 != null) {
                onExportPicCheck3.onExportPic();
            }
        }
        else {
            LimitDialog.showLoginDialog(context, s);
        }
    }
    
    public interface OnExportPicCheck
    {
        void onExportPic();
    }
}
