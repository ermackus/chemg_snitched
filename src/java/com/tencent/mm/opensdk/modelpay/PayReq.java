package com.tencent.mm.opensdk.modelpay;

import com.tencent.mm.opensdk.channel.a.a;
import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.modelbase.BaseReq;

public class PayReq extends BaseReq
{
    private static final int EXTDATA_MAX_LENGTH = 1024;
    private static final String TAG = "MicroMsg.PaySdk.PayReq";
    public String appId;
    public String extData;
    public String nonceStr;
    public PayReq.PayReq$Options options;
    public String packageValue;
    public String partnerId;
    public String prepayId;
    public String sign;
    public String signType;
    public String timeStamp;
    
    public boolean checkArgs() {
        final String appId = this.appId;
        String s;
        if (appId != null && appId.length() != 0) {
            final String partnerId = this.partnerId;
            if (partnerId != null && partnerId.length() != 0) {
                final String prepayId = this.prepayId;
                if (prepayId != null && prepayId.length() != 0) {
                    final String nonceStr = this.nonceStr;
                    if (nonceStr != null && nonceStr.length() != 0) {
                        final String timeStamp = this.timeStamp;
                        if (timeStamp != null && timeStamp.length() != 0) {
                            final String packageValue = this.packageValue;
                            if (packageValue != null && packageValue.length() != 0) {
                                final String sign = this.sign;
                                if (sign != null && sign.length() != 0) {
                                    final String extData = this.extData;
                                    if (extData == null || extData.length() <= 1024) {
                                        return true;
                                    }
                                    s = "checkArgs fail, extData length too long";
                                }
                                else {
                                    s = "checkArgs fail, invalid sign";
                                }
                            }
                            else {
                                s = "checkArgs fail, invalid packageValue";
                            }
                        }
                        else {
                            s = "checkArgs fail, invalid timeStamp";
                        }
                    }
                    else {
                        s = "checkArgs fail, invalid nonceStr";
                    }
                }
                else {
                    s = "checkArgs fail, invalid prepayId";
                }
            }
            else {
                s = "checkArgs fail, invalid partnerId";
            }
        }
        else {
            s = "checkArgs fail, invalid appId";
        }
        Log.e("MicroMsg.PaySdk.PayReq", s);
        return false;
    }
    
    public void fromBundle(final Bundle bundle) {
        super.fromBundle(bundle);
        this.appId = a.a(bundle, "_wxapi_payreq_appid");
        this.partnerId = a.a(bundle, "_wxapi_payreq_partnerid");
        this.prepayId = a.a(bundle, "_wxapi_payreq_prepayid");
        this.nonceStr = a.a(bundle, "_wxapi_payreq_noncestr");
        this.timeStamp = a.a(bundle, "_wxapi_payreq_timestamp");
        this.packageValue = a.a(bundle, "_wxapi_payreq_packagevalue");
        this.sign = a.a(bundle, "_wxapi_payreq_sign");
        this.extData = a.a(bundle, "_wxapi_payreq_extdata");
        this.signType = a.a(bundle, "_wxapi_payreq_sign_type");
        (this.options = new PayReq.PayReq$Options()).fromBundle(bundle);
    }
    
    public int getType() {
        return 5;
    }
    
    public void toBundle(final Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_wxapi_payreq_appid", this.appId);
        bundle.putString("_wxapi_payreq_partnerid", this.partnerId);
        bundle.putString("_wxapi_payreq_prepayid", this.prepayId);
        bundle.putString("_wxapi_payreq_noncestr", this.nonceStr);
        bundle.putString("_wxapi_payreq_timestamp", this.timeStamp);
        bundle.putString("_wxapi_payreq_packagevalue", this.packageValue);
        bundle.putString("_wxapi_payreq_sign", this.sign);
        bundle.putString("_wxapi_payreq_extdata", this.extData);
        bundle.putString("_wxapi_payreq_sign_type", this.signType);
        final PayReq.PayReq$Options options = this.options;
        if (options != null) {
            options.toBundle(bundle);
        }
    }
}
