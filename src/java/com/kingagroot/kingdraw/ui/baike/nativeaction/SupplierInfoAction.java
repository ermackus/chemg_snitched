package com.kingagroot.kingdraw.ui.baike.nativeaction;

import com.goodsrc.library.utils.ToastUtil;
import android.net.Uri;
import com.kingagroot.kingdraw.ui.baike.JsBaikeUtils;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.baike.SupplierDialog$OnTextClickLister;
import com.kingagroot.kingdraw.ui.baike.SupplierDialog;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import android.content.Context;

public class SupplierInfoAction extends BaseAction
{
    public SupplierInfoAction(final Context context) {
        super(context);
    }
    
    public void action(final String s, final CallBackFunction callBackFunction) {
        final SupplierDialog supplierDialog = new SupplierDialog(this.context, s);
        supplierDialog.show();
        supplierDialog.setTextClick((SupplierDialog$OnTextClickLister)new SupplierDialog$OnTextClickLister(this) {
            final SupplierInfoAction this$0;
            
            public void onClick(String string, final int n) {
                final Intent intent = new Intent();
                if (n != 0) {
                    if (n != 1) {
                        if (n == 2) {
                            if (JsBaikeUtils.isqqclientavailable(this.this$0.context)) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append("mqqwpa://im/chat?chat_type=wpa&uin=");
                                sb.append(string);
                                string = sb.toString();
                                intent.setAction("android.intent.action.VIEW");
                                intent.setData(Uri.parse(string));
                                this.this$0.context.startActivity(intent);
                            }
                            else {
                                JsBaikeUtils.copyToClipboard(this.this$0.context, (CharSequence)string);
                                ToastUtil.showShort((CharSequence)this.this$0.context.getString(2131821277));
                            }
                        }
                    }
                    else {
                        intent.setAction("android.intent.action.DIAL");
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("tel:");
                        sb2.append(string);
                        intent.setData(Uri.parse(sb2.toString()));
                        this.this$0.context.startActivity(intent);
                    }
                }
                else {
                    intent.setAction("android.intent.action.VIEW");
                    String string2 = string;
                    if (!string.startsWith("https://")) {
                        string2 = string;
                        if (!string.startsWith("http://")) {
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("http://");
                            sb3.append(string);
                            string2 = sb3.toString();
                        }
                    }
                    intent.setData(Uri.parse(string2));
                    this.this$0.context.startActivity(intent);
                }
            }
        });
    }
}
