package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import android.content.Context;

public class LimitPalette
{
    public static final String TAG = "LimitPalette";
    private final OnJumpPalette onJumpPalette;
    
    public LimitPalette(final OnJumpPalette onJumpPalette) {
        this.onJumpPalette = onJumpPalette;
    }
    
    public void jumpPaletteCheck(final Context context) {
        final int n = (int)((DrawFileDataI)new DrawFileDataImpl()).getDrawFileAmount();
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                final int localFileLimitNum = ((LimitConfigDbi)new LimitConfigDbiMpl()).getLocalFileLimitNum();
                if (localFileLimitNum != -1) {
                    if (n >= localFileLimitNum) {
                        LimitDialog.showLoginDialog(context, context.getString(2131820971));
                    }
                    else {
                        final OnJumpPalette onJumpPalette = this.onJumpPalette;
                        if (onJumpPalette != null) {
                            onJumpPalette.onJumpPalette();
                        }
                    }
                }
                else {
                    final OnJumpPalette onJumpPalette2 = this.onJumpPalette;
                    if (onJumpPalette2 != null) {
                        onJumpPalette2.onJumpPalette();
                    }
                }
            }
            else {
                final int localFileLimitNum2 = ((LimitConfigDbi)limitLoginConfigDbiMpl).getLocalFileLimitNum();
                if (localFileLimitNum2 != -1) {
                    if (n >= localFileLimitNum2) {
                        final OnJumpPalette onJumpPalette3 = this.onJumpPalette;
                        if (onJumpPalette3 != null) {
                            onJumpPalette3.onShowVipDialog();
                        }
                    }
                    else {
                        final OnJumpPalette onJumpPalette4 = this.onJumpPalette;
                        if (onJumpPalette4 != null) {
                            onJumpPalette4.onJumpPalette();
                        }
                    }
                }
                else {
                    final OnJumpPalette onJumpPalette5 = this.onJumpPalette;
                    if (onJumpPalette5 != null) {
                        onJumpPalette5.onJumpPalette();
                    }
                }
            }
        }
        else {
            final int localFileLimitNum3 = ((LimitConfigDbi)new LimitConfigDbiMpl()).getLocalFileLimitNum();
            if (localFileLimitNum3 != -1) {
                if (n >= localFileLimitNum3) {
                    LimitDialog.showLoginDialog(context, context.getString(2131820971));
                }
                else {
                    final OnJumpPalette onJumpPalette6 = this.onJumpPalette;
                    if (onJumpPalette6 != null) {
                        onJumpPalette6.onJumpPalette();
                    }
                }
            }
            else {
                final OnJumpPalette onJumpPalette7 = this.onJumpPalette;
                if (onJumpPalette7 != null) {
                    onJumpPalette7.onJumpPalette();
                }
            }
        }
    }
    
    public interface OnJumpPalette
    {
        void onJumpPalette();
        
        void onShowVipDialog();
    }
}
