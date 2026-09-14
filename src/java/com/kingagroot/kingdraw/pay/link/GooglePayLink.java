package com.kingagroot.kingdraw.pay.link;

import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$UserOrder;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;

public class GooglePayLink
{
    private OnPayListener onPayListener;
    
    public GooglePayLink(final OnPayListener onPayListener) {
        this.onPayListener = onPayListener;
    }
    
    public void googlePay(final String s, final String s2) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$UserOrder.googlePay());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderId", (Object)s);
            jsonObject.put("purchaseToken", (Object)s2);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new GooglePayLink$1(this));
    }
    
    public interface OnPayListener
    {
        void onFinish();
        
        void onSuccess();
    }
}
