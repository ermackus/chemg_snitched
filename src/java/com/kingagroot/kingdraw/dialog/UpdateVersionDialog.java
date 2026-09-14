package com.kingagroot.kingdraw.dialog;

import android.os.Bundle;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.WebViewActivity;
import android.view.View;
import android.util.Base64;
import android.os.Build$VERSION;
import android.content.Context;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Button;
import com.kingagroot.kingdraw.model.AppUpdateModel;
import android.view.View$OnClickListener;
import android.app.Dialog;

public class UpdateVersionDialog extends Dialog implements View$OnClickListener
{
    private final AppUpdateModel appUpdateModel;
    private Button btnDetail;
    private Button btnUpdate;
    private final OnUpdateListener onUpdateListener;
    private TextView tvVersion;
    private WebView tvVersionContent;
    private TextView tvVersionType;
    
    public UpdateVersionDialog(final Context context, final AppUpdateModel appUpdateModel, final OnUpdateListener onUpdateListener) {
        super(context, 2131886326);
        this.appUpdateModel = appUpdateModel;
        this.onUpdateListener = onUpdateListener;
    }
    
    private void initView() {
        this.tvVersion = (TextView)this.findViewById(2131297687);
        this.tvVersionContent = (WebView)this.findViewById(2131297689);
        this.btnDetail = (Button)this.findViewById(2131296422);
        this.btnUpdate = (Button)this.findViewById(2131296462);
        this.tvVersionType = (TextView)this.findViewById(2131297690);
        this.btnDetail.setOnClickListener((View$OnClickListener)this);
        this.btnUpdate.setOnClickListener((View$OnClickListener)this);
        this.tvVersionContent.getSettings().setDefaultTextEncodingName("UTF-8");
        this.setVersion(String.valueOf((Object)this.appUpdateModel.getTitle()));
        this.setVersionContent(this.appUpdateModel.getIntroduction());
        this.setCanceledOnTouchOutside(true);
    }
    
    private void setVersion(final String text) {
        this.tvVersionType.setText((CharSequence)text);
        this.tvVersionType.setTextColor(-16606236);
        this.tvVersion.setTextColor(-16606236);
    }
    
    private void setVersionContent(String encodeToString) {
        if (Build$VERSION.SDK_INT >= 28) {
            encodeToString = Base64.encodeToString(encodeToString.getBytes(), 1);
            this.tvVersionContent.loadData(encodeToString, "text/html", "base64");
        }
        else {
            this.tvVersionContent.loadData(encodeToString, "text/html; charset=UTF-8", (String)null);
        }
    }
    
    public void onClick(final View view) {
        if (view == this.btnDetail) {
            final Intent intent = new Intent(this.getContext(), (Class)WebViewActivity.class);
            intent.putExtra("title_key", this.getContext().getString(2131820947));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_ZH);
            }
            else {
                intent.putExtra("url_key", APIConfig.NEW_VERSION_EN);
            }
            this.getContext().startActivity(intent);
        }
        else if (view == this.btnUpdate) {
            this.onUpdateListener.onUpdate();
            this.dismiss();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493030);
        this.getWindow().setLayout(-1, -1);
        this.initView();
    }
    
    public void show() {
        super.show();
    }
    
    public interface OnUpdateListener
    {
        void onUpdate();
    }
}
