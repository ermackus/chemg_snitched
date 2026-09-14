package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.goodsrc.library.utils.ToastUtil;
import android.net.Uri;
import com.kingagroot.kingdraw.ui.baike.JsBaikeUtils;
import android.content.Intent;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class TencentAction extends BaseAction
{
    public TencentAction(final Context context) {
        super(context);
    }
    
    public void action(String string, final CallBackFunction callBackFunction) {
        final Intent intent = new Intent();
        if (JsBaikeUtils.isqqclientavailable(this.context)) {
            final StringBuilder sb = new StringBuilder();
            sb.append("mqqwpa://im/chat?chat_type=wpa&uin=");
            sb.append(string);
            string = sb.toString();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(string));
            this.context.startActivity(intent);
        }
        else {
            JsBaikeUtils.copyToClipboard(this.context, (CharSequence)string);
            ToastUtil.showShort((CharSequence)this.context.getString(2131821277));
        }
    }
}
