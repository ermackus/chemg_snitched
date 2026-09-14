package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class UserInfoLink
{
    private final OnUserInfoListener onUserInfoListener;
    
    public UserInfoLink(final OnUserInfoListener onUserInfoListener) {
        this.onUserInfoListener = onUserInfoListener;
    }
    
    private void getUserGroupList() {
        new GroupListLink((GroupListLink.OnGroupListener)_$$Lambda$UserInfoLink$JGb1rZHD4VxBJRp7pcVLwh0wI7k.INSTANCE).getUserGroupList();
    }
    
    public void getUserInfo(final boolean b) {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$Account.getUserInfo()), (RequestCallBack)new UserInfoLink$1(this, b));
    }
    
    public interface OnUserInfoListener
    {
        void onFailure(final String p0);
        
        void onFinish();
        
        void onSuccess(final AccountUserModel p0);
    }
}
