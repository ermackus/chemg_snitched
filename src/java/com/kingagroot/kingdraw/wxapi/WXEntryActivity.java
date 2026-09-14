package com.kingagroot.kingdraw.wxapi;

import com.tencent.mm.opensdk.modelpay.PayReq;
import android.widget.Toast;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import android.net.Uri;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils$VerifyModel;
import com.kingagroot.kingdraw.ui.StartActivity;
import android.net.Uri$Builder;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX$Req;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import android.content.Intent;
import android.content.Context;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import android.os.Bundle;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import android.app.Activity;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler
{
    private IWXAPI api;
    
    private void paySuccess(final String s) {
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.api = WXAPIFactory.createWXAPI((Context)this, "wxdbab8ce2bc2c4ed2", false);
        try {
            this.api.handleIntent(this.getIntent(), (IWXAPIEventHandler)this);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.api.handleIntent(intent, (IWXAPIEventHandler)this);
    }
    
    public void onReq(final BaseReq baseReq) {
        if (baseReq instanceof ShowMessageFromWX$Req) {
            final ShowMessageFromWX$Req showMessageFromWX$Req = (ShowMessageFromWX$Req)baseReq;
            if (showMessageFromWX$Req.message != null) {
                final ShareFileTokenUtils$VerifyModel verifyParameter = ShareFileTokenUtils.verifyParameter(showMessageFromWX$Req.message.messageExt);
                if (verifyParameter.isCheck) {
                    final Uri build = new Uri$Builder().appendQueryParameter("kingdrawId", verifyParameter.fileOssid).appendQueryParameter("fileName", verifyParameter.fileName).appendQueryParameter("fileExtension", verifyParameter.fileextension).appendQueryParameter("CreateTime", verifyParameter.creatTime).build();
                    final Intent intent = new Intent((Context)this, (Class)StartActivity.class);
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(build);
                    this.startActivity(intent);
                }
            }
        }
    }
    
    public void onResp(final BaseResp baseResp) {
        final String extData = ((PayResp)baseResp).extData;
        if (baseResp.getType() == 5) {
            final int errCode = baseResp.errCode;
            if (errCode != -2) {
                if (errCode != -1) {
                    if (errCode == 0) {
                        this.paySuccess(extData);
                    }
                }
                else {
                    Toast.makeText((Context)this, (CharSequence)"\u652f\u4ed8\u9519\u8bef", 0).show();
                }
            }
            else {
                Toast.makeText((Context)this, (CharSequence)"\u7528\u6237\u53d6\u6d88\u4e86\u8ba2\u5355", 0).show();
            }
            this.finish();
        }
    }
    
    public void startActivity(final Intent intent) {
        super.startActivity(intent);
    }
    
    public void wxpaly() {
        final PayReq payReq = new PayReq();
        payReq.appId = "wxdbab8ce2bc2c4ed2";
        payReq.nonceStr = "iuWeKvt82vAGzxH3";
        payReq.partnerId = "1900000109";
        payReq.prepayId = "1101000000140415649af9fc314aa427";
        payReq.packageValue = "Sign=WXPay";
        payReq.timeStamp = "\u652f\u4ed8\u65f6\u95f4";
        payReq.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        this.api.sendReq((BaseReq)payReq);
    }
}
