package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitPedia
{
    public static final String TAG = "LimitPedia";
    private final OnPediaListener onPediaListener;
    
    public LimitPedia(final OnPediaListener onPediaListener) {
        this.onPediaListener = onPediaListener;
    }
    
    public void checkPedia(final Context context, final String s) {
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                if (((LimitConfigDbi)new LimitConfigDbiMpl()).getEncyclopediaAvailable()) {
                    final OnPediaListener onPediaListener = this.onPediaListener;
                    if (onPediaListener != null) {
                        onPediaListener.onOpenPedia();
                    }
                }
                else {
                    LimitDialog.showLoginDialog(context, s);
                }
            }
            else if (((LimitConfigDbi)limitLoginConfigDbiMpl).getEncyclopediaAvailable()) {
                final OnPediaListener onPediaListener2 = this.onPediaListener;
                if (onPediaListener2 != null) {
                    onPediaListener2.onOpenPedia();
                }
            }
            else {
                LimitDialog.showVipDialog(context, context.getString(2131820980));
            }
        }
        else if (((LimitConfigDbi)new LimitConfigDbiMpl()).getEncyclopediaAvailable()) {
            final OnPediaListener onPediaListener3 = this.onPediaListener;
            if (onPediaListener3 != null) {
                onPediaListener3.onOpenPedia();
            }
        }
        else {
            LimitDialog.showLoginDialog(context, s);
        }
    }
    
    public interface OnPediaListener
    {
        void onOpenPedia();
    }
}
