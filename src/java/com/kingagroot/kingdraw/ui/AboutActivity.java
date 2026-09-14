package com.kingagroot.kingdraw.ui;

import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import com.goodsrc.library.utils.StringUtils;
import android.os.Bundle;
import com.kingagroot.kingdraw.utils.UpdateUtil$OnUpdateAppListener;
import com.kingagroot.kingdraw.utils.UpdateUtil;
import com.kingagroot.kingdraw.config.APIConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.content.Context;
import android.content.Intent;
import java.util.Map;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import java.util.HashMap;
import com.kingagroot.kingdraw.config.ServerInfoConfig;
import android.view.View;
import java.util.Calendar;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class AboutActivity extends ToolBarActivity implements View$OnClickListener
{
    private RelativeLayout rlAppUpdate;
    RelativeLayout rlContactUs;
    private RelativeLayout rlDisclaimer;
    RelativeLayout rlPrivacy;
    RelativeLayout rlUserSer;
    TextView tvRelease;
    TextView tvVersion;
    
    public static int getYear() {
        return Calendar.getInstance().get(1);
    }
    
    public void onClick(final View view) {
        if (view == this.rlUserSer) {
            final String serviceAgreement = ServerInfoConfig.getServiceAgreement();
            final HashMap hashMap = new HashMap(1);
            ((Map)hashMap).put((Object)"ContentType", (Object)serviceAgreement);
            final String url = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map)hashMap);
            final Intent intent = new Intent((Context)this, (Class)WebViewActivity.class);
            intent.putExtra("url_key", url);
            this.startActivity(intent);
        }
        else if (view == this.rlPrivacy) {
            final String privacyPolicy = ServerInfoConfig.getPrivacyPolicy();
            final HashMap hashMap2 = new HashMap(1);
            ((Map)hashMap2).put((Object)"ContentType", (Object)privacyPolicy);
            final String url2 = NewHttpManager.getUrl(NetConfig$BaseData.agreement(), (Map)hashMap2);
            final Intent intent2 = new Intent((Context)this, (Class)WebViewActivity.class);
            intent2.putExtra("url_key", url2);
            this.startActivity(intent2);
        }
        else if (view == this.rlContactUs) {
            this.startActivity(new Intent((Context)this, (Class)ContactUsActivity.class));
        }
        else if (view == this.rlDisclaimer) {
            final Intent intent3 = new Intent((Context)this, (Class)WebViewActivity.class);
            intent3.putExtra("title_key", this.getString(2131820777));
            if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
                intent3.putExtra("url_key", APIConfig.DISCLAIMER_CN);
            }
            else {
                intent3.putExtra("url_key", APIConfig.DISCLAIMER_EN);
            }
            this.startActivity(intent3);
        }
        else if (view == this.rlAppUpdate) {
            new UpdateUtil((Context)this, false, (UpdateUtil$OnUpdateAppListener)_$$Lambda$AboutActivity$gY87zJ9aUG_ErF5VRCRUO_s8T70.INSTANCE).checkVersion(true);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820578);
        this.setContentView(2131492892);
        this.tvVersion = (TextView)this.findViewById(2131297687);
        this.rlUserSer = (RelativeLayout)this.findViewById(2131297325);
        this.rlPrivacy = (RelativeLayout)this.findViewById(2131297307);
        this.rlContactUs = (RelativeLayout)this.findViewById(2131297287);
        this.rlAppUpdate = (RelativeLayout)this.findViewById(2131297283);
        this.tvRelease = (TextView)this.findViewById(2131297263);
        this.rlDisclaimer = (RelativeLayout)this.findViewById(2131297289);
        final TextView textView = (TextView)this.findViewById(2131297547);
        this.tvVersion.setText((CharSequence)StringUtils.format(this.getResources().getString(2131821508), new Object[] { "3.6.1", "20240325" }));
        textView.setText((CharSequence)StringUtils.format(this.getResources().getString(2131820945), new Object[] { getYear() }));
        if (AppConfig.RELEASE == Release.STANDARD) {
            this.tvRelease.setVisibility(8);
        }
        else {
            this.tvRelease.setVisibility(0);
            this.tvRelease.setText((CharSequence)AppConfig.RELEASE.name);
        }
        this.rlAppUpdate.setVisibility(8);
        this.rlUserSer.setOnClickListener((View$OnClickListener)this);
        this.rlPrivacy.setOnClickListener((View$OnClickListener)this);
        this.rlContactUs.setOnClickListener((View$OnClickListener)this);
        this.rlDisclaimer.setOnClickListener((View$OnClickListener)this);
        this.rlAppUpdate.setOnClickListener((View$OnClickListener)this);
    }
}
