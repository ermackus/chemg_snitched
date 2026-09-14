package com.kingagroot.kingdraw.utils.link;

import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class VipLimitLink
{
    public static final String TAG = "VipLimitLink";
    private OnVipLimitConfigListener onVipLimitConfigListener;
    
    public VipLimitLink(final OnVipLimitConfigListener onVipLimitConfigListener) {
        this.onVipLimitConfigListener = onVipLimitConfigListener;
    }
    
    public void getVipLimitConfig() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getVipLimitConfig()), (RequestCallBack)new VipLimitLink$1(this));
    }
    
    public interface OnVipLimitConfigListener
    {
        void onFinish();
    }
}
