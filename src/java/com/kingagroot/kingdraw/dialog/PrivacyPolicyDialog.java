package com.kingagroot.kingdraw.dialog;

import android.os.Bundle;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import java.util.Map;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import java.util.HashMap;
import com.kingagroot.kingdraw.config.ServerInfoConfig;
import android.text.method.LinkMovementMethod;
import android.text.TextPaint;
import android.view.View;
import android.text.style.ClickableSpan;
import com.goodsrc.library.utils.LanguageTool;
import android.text.SpannableStringBuilder;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Context;
import android.widget.TextView;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class PrivacyPolicyDialog extends Dialog implements View$OnClickListener
{
    private Button btnCancel;
    private Button btnGotit;
    private OnPrivacyPolicyDialogListner onPrivacyPolicyDialogListner;
    private TextView tvContent;
    
    public PrivacyPolicyDialog(final Context context) {
        super(context, 2131886326);
    }
    
    private void initData() {
        final String string = this.getContext().getString(2131820613);
        final String language = LibraryApplication.getLanguage();
        final SpannableStringBuilder text = new SpannableStringBuilder((CharSequence)string);
        int n;
        int n2;
        int n3;
        int n4;
        if (language.equals((Object)LanguageTool.SER_ZH)) {
            n = string.indexOf("\u300a\u7528\u6237\u534f\u8bae\u300b");
            n2 = n + 6;
            n3 = string.indexOf("\u300a\u9690\u79c1\u653f\u7b56\u300b");
            n4 = n3 + 6;
        }
        else {
            n = string.indexOf("\u300aTerms of Service\u300b");
            n2 = n + 18;
            n3 = string.indexOf("\u300aPrivacy Policy\u300b");
            n4 = n3 + 16;
        }
        if (n > -1 && n < text.length() && n2 > n && n < text.length()) {
            text.setSpan((Object)new ClickableSpan(this) {
                final PrivacyPolicyDialog this$0;
                
                public void onClick(final View view) {
                    this.this$0.toYhxy();
                }
                
                public void updateDrawState(final TextPaint textPaint) {
                    textPaint.setColor(-15032321);
                    textPaint.setUnderlineText(false);
                }
            }, n, n2, 33);
        }
        if (n3 > -1 && n3 < text.length() && n4 > n3 && n4 < text.length()) {
            text.setSpan((Object)new ClickableSpan(this) {
                final PrivacyPolicyDialog this$0;
                
                public void onClick(final View view) {
                    this.this$0.toYsxy();
                }
                
                public void updateDrawState(final TextPaint textPaint) {
                    textPaint.setColor(-15032321);
                    textPaint.setUnderlineText(false);
                }
            }, n3, n4, 33);
        }
        this.tvContent.setText((CharSequence)text);
        this.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    private void initView() {
        this.btnGotit = (Button)this.findViewById(2131296433);
        this.tvContent = (TextView)this.findViewById(2131297550);
        this.btnCancel = (Button)this.findViewById(2131296413);
        this.btnGotit.setOnClickListener((View$OnClickListener)this);
        this.btnCancel.setOnClickListener((View$OnClickListener)this);
        this.setCanceledOnTouchOutside(false);
    }
    
    private void toYhxy() {
        final String serviceAgreement = ServerInfoConfig.getServiceAgreement();
        final HashMap hashMap = new HashMap(1);
        ((Map)hashMap).put((Object)"ContentType", (Object)serviceAgreement);
        final String url = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map<String, String>)hashMap);
        final Intent intent = new Intent(this.getContext(), (Class)WebViewActivity.class);
        intent.putExtra("intent_key_opensharfile", false);
        intent.putExtra("url_key", url);
        this.getContext().startActivity(intent);
    }
    
    private void toYsxy() {
        final String privacyPolicy = ServerInfoConfig.getPrivacyPolicy();
        final HashMap hashMap = new HashMap(1);
        ((Map)hashMap).put((Object)"ContentType", (Object)privacyPolicy);
        final String url = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map<String, String>)hashMap);
        final Intent intent = new Intent(this.getContext(), (Class)WebViewActivity.class);
        intent.putExtra("intent_key_opensharfile", false);
        intent.putExtra("url_key", url);
        this.getContext().startActivity(intent);
    }
    
    public void onClick(final View view) {
        if (view == this.btnGotit) {
            final OnPrivacyPolicyDialogListner onPrivacyPolicyDialogListner = this.onPrivacyPolicyDialogListner;
            if (onPrivacyPolicyDialogListner != null) {
                onPrivacyPolicyDialogListner.onAgree();
            }
        }
        else if (view == this.btnCancel) {
            final OnPrivacyPolicyDialogListner onPrivacyPolicyDialogListner2 = this.onPrivacyPolicyDialogListner;
            if (onPrivacyPolicyDialogListner2 != null) {
                onPrivacyPolicyDialogListner2.onRefuse();
            }
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493027);
        this.getWindow().setLayout(-1, -1);
        this.initView();
        this.initData();
    }
    
    public void setOnPrivacyPolicyDialogListner(final OnPrivacyPolicyDialogListner onPrivacyPolicyDialogListner) {
        this.onPrivacyPolicyDialogListner = onPrivacyPolicyDialogListner;
    }
    
    public interface OnPrivacyPolicyDialogListner
    {
        void onAgree();
        
        void onRefuse();
    }
}
