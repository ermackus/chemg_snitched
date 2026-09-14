package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$Group;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class GroupListLink
{
    private final OnGroupListener onGroupListener;
    
    public GroupListLink(final OnGroupListener onGroupListener) {
        this.onGroupListener = onGroupListener;
    }
    
    public void getUserGroupList() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$Group.getUserGroupsList()), (RequestCallBack)new GroupListLink$1(this));
    }
    
    public interface OnGroupListener
    {
        void onFinish();
    }
}
