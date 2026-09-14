package com.kingagroot.kingdraw.pay.link;

import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$UserOrder;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class OrderCreateLink
{
    private OnOrderCreateListener onOrderCreateListener;
    
    public OrderCreateLink(final OnOrderCreateListener onOrderCreateListener) {
        this.onOrderCreateListener = onOrderCreateListener;
    }
    
    public void createOrder(final String s, final String s2, final String s3, final String s4) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$UserOrder.createOrder());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("payWay", (Object)s);
            jsonObject.put("skuId", (Object)s2);
            jsonObject.put("spuId", (Object)s3);
            jsonObject.put("userId", (Object)s4);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new OrderCreateLink$1(this));
    }
    
    public interface OnOrderCreateListener
    {
        void onCreateSuccess(final String p0);
        
        void onFinish();
    }
    
    private class OrderInfoModel
    {
        private String orderId;
        final OrderCreateLink this$0;
        
        private OrderInfoModel(final OrderCreateLink this$0) {
            this.this$0 = this$0;
        }
        
        public String getOrderId() {
            return this.orderId;
        }
        
        public void setOrderId(final String orderId) {
            this.orderId = orderId;
        }
    }
}
