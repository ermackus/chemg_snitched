package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitStereoscopicConfiguration
{
    public static final String TAG = "LimitStereoscopicConfiguration";
    private final OnStereoscopicListener onStereoscopicListener;
    
    public LimitStereoscopicConfiguration(final OnStereoscopicListener onStereoscopicListener) {
        this.onStereoscopicListener = onStereoscopicListener;
    }
    
    public void checkStereoscopic(final Context context, final String s) {
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                if (((LimitConfigDbi)new LimitConfigDbiMpl()).getDimensionalSetting()) {
                    final OnStereoscopicListener onStereoscopicListener = this.onStereoscopicListener;
                    if (onStereoscopicListener != null) {
                        onStereoscopicListener.onStereoscopic();
                    }
                }
                else {
                    LimitDialog.showLoginDialog(context, s);
                }
            }
            else if (((LimitConfigDbi)limitLoginConfigDbiMpl).getDimensionalSetting()) {
                final OnStereoscopicListener onStereoscopicListener2 = this.onStereoscopicListener;
                if (onStereoscopicListener2 != null) {
                    onStereoscopicListener2.onStereoscopic();
                }
            }
            else {
                LimitDialog.showVipDialog(context, context.getString(2131820980));
            }
        }
        else if (((LimitConfigDbi)new LimitConfigDbiMpl()).getDimensionalSetting()) {
            final OnStereoscopicListener onStereoscopicListener3 = this.onStereoscopicListener;
            if (onStereoscopicListener3 != null) {
                onStereoscopicListener3.onStereoscopic();
            }
        }
        else {
            LimitDialog.showLoginDialog(context, s);
        }
    }
    
    public interface OnStereoscopicListener
    {
        void onStereoscopic();
    }
}
