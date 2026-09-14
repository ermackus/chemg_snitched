package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitSupExport
{
    public static final String TAG = "LimitSupExport";
    private final OnSupExportListener onSupExportListener;
    
    public LimitSupExport(final OnSupExportListener onSupExportListener) {
        this.onSupExportListener = onSupExportListener;
    }
    
    public void checkSupExport(final Context context, final String s) {
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                if (((LimitConfigDbi)new LimitConfigDbiMpl()).getGroupOpenState()) {
                    final OnSupExportListener onSupExportListener = this.onSupExportListener;
                    if (onSupExportListener != null) {
                        onSupExportListener.onSupExport();
                    }
                }
                else {
                    LimitDialog.showLoginDialog(context, s);
                }
            }
            else if (((LimitConfigDbi)limitLoginConfigDbiMpl).getGroupOpenState()) {
                final OnSupExportListener onSupExportListener2 = this.onSupExportListener;
                if (onSupExportListener2 != null) {
                    onSupExportListener2.onSupExport();
                }
            }
            else {
                LimitDialog.showVipDialog(context, context.getString(2131820980));
            }
        }
        else if (((LimitConfigDbi)new LimitConfigDbiMpl()).getGroupOpenState()) {
            final OnSupExportListener onSupExportListener3 = this.onSupExportListener;
            if (onSupExportListener3 != null) {
                onSupExportListener3.onSupExport();
            }
        }
        else {
            LimitDialog.showLoginDialog(context, s);
        }
    }
    
    public interface OnSupExportListener
    {
        void onSupExport();
    }
}
