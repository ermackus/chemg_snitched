package com.kingagroot.kingdraw.pay.link;

import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$UserOrder;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class OrderInfoLink
{
    public void orderInfo(final String s) {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        final RequestParams params = build.params(NetConfig$UserOrder.orderInfo());
        params.addBodyParameter("orderId", s);
        build.request(params, (RequestCallBack)new OrderInfoLink$1(this));
    }
}
