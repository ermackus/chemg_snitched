package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class UserLogoutLink
{
    private final OnUserLogOutListener onUserLogOutListener;
    
    public UserLogoutLink(final OnUserLogOutListener onUserLogOutListener) {
        this.onUserLogOutListener = onUserLogOutListener;
    }
    
    public void userLogOut() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$Account.getLogoutUrl()), (RequestCallBack)new UserLogoutLink$1(this));
    }
    
    public interface OnUserLogOutListener
    {
        void onSuccess();
    }
}
