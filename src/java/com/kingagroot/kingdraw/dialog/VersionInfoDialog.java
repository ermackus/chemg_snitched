package com.kingagroot.kingdraw.dialog;

import android.os.Bundle;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import android.view.View;
import android.content.Context;
import com.kingagroot.kingdraw.model.VersionModel;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class VersionInfoDialog extends Dialog implements View$OnClickListener
{
    private Button btnDetail;
    private Button btnGotit;
    private final OnVersionInfoListener onVersionInfoListener;
    private TextView tvVersion;
    private WebView tvVersionContent;
    private TextView tvVersionType;
    private final VersionModel versionModel;
    
    public VersionInfoDialog(final Context context, final VersionModel versionModel, final OnVersionInfoListener onVersionInfoListener) {
        super(context, 2131886326);
        this.versionModel = versionModel;
        this.onVersionInfoListener = onVersionInfoListener;
    }
    
    private void initView() {
        this.tvVersion = (TextView)this.findViewById(2131297687);
        this.tvVersionContent = (WebView)this.findViewById(2131297689);
        this.btnGotit = (Button)this.findViewById(2131296433);
        this.tvVersionType = (TextView)this.findViewById(2131297690);
        this.btnDetail = (Button)this.findViewById(2131296422);
        this.btnGotit.setOnClickListener((View$OnClickListener)this);
        this.btnDetail.setOnClickListener((View$OnClickListener)this);
        this.setVersion(String.valueOf((Object)this.versionModel.getTitle()));
        this.setVersionContent(this.versionModel.getIntro());
        this.setCanceledOnTouchOutside(true);
    }
    
    private void setVersion(final String text) {
        this.tvVersionType.setText((CharSequence)text);
        this.tvVersion.setTextColor(-16777216);
        this.tvVersionType.setTextColor(-16777216);
    }
    
    private void setVersionContent(final String s) {
        this.tvVersionContent.loadData(s, "text/html; charset=UTF-8", (String)null);
    }
    
    public void onClick(final View view) {
        if (view == this.btnGotit) {
            this.dismiss();
            this.onVersionInfoListener.onGotIt();
        }
        else if (view == this.btnDetail) {
            final Intent intent = new Intent(this.getContext(), (Class)WebViewActivity.class);
            intent.putExtra("title_key", this.getContext().getString(2131820947));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_ZH);
            }
            else {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_EN);
            }
            this.getContext().startActivity(intent);
            this.dismiss();
            this.onVersionInfoListener.onGotIt();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493023);
        this.getWindow().setLayout(-1, -1);
        this.initView();
    }
    
    public void show() {
        super.show();
    }
    
    public interface OnVersionInfoListener
    {
        void onGotIt();
    }
}
