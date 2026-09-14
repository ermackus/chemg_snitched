package com.kingagroot.kingdraw.limit;

import com.kingagroot.kingdraw.interfaces.impl.LimitConfigDbiMpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.Context;

public class LimitSearch
{
    public static final String TAG = "LimitSearch";
    private final OnFileSearch onFileSearch;
    
    public LimitSearch(final OnFileSearch onFileSearch) {
        this.onFileSearch = onFileSearch;
    }
    
    public void checkSearch(final Context context, final String s) {
        if (MApplication.getInstance().isLogin()) {
            final LimitLoginConfigDbiMpl limitLoginConfigDbiMpl = new LimitLoginConfigDbiMpl();
            if (!NetworkUtil.isNetworkConnected(context) && DefaultLimitConfig.overtime((LimitConfigDbi)limitLoginConfigDbiMpl)) {
                MApplication.getInstance().userLogout();
                if (((LimitConfigDbi)new LimitConfigDbiMpl()).getFileSearchState()) {
                    final OnFileSearch onFileSearch = this.onFileSearch;
                    if (onFileSearch != null) {
                        onFileSearch.onSearch();
                    }
                }
                else {
                    LimitDialog.showLoginDialog(context, s);
                }
            }
            else if (((LimitConfigDbi)limitLoginConfigDbiMpl).getFileSearchState()) {
                final OnFileSearch onFileSearch2 = this.onFileSearch;
                if (onFileSearch2 != null) {
                    onFileSearch2.onSearch();
                }
            }
            else {
                LimitDialog.showVipDialog(context, context.getString(2131820980));
            }
        }
        else if (((LimitConfigDbi)new LimitConfigDbiMpl()).getFileSearchState()) {
            final OnFileSearch onFileSearch3 = this.onFileSearch;
            if (onFileSearch3 != null) {
                onFileSearch3.onSearch();
            }
        }
        else {
            LimitDialog.showLoginDialog(context, s);
        }
    }
    
    public interface OnFileSearch
    {
        void onSearch();
    }
}
