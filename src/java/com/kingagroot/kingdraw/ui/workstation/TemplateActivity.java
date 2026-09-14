package com.kingagroot.kingdraw.ui.workstation;

import android.view.KeyEvent;
import android.os.Bundle;
import android.os.Build$VERSION;
import com.lzf.easyfloat.interfaces.OnPermissionResult;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.kingagroot.kingdraw.ui.NativePaletteActivity;
import com.kingagroot.kingdraw.ui.NativeSearchPaletteActivity;
import com.kingagroot.component.ui.ElementTableActivity;
import com.kingagroot.kingdraw.ui.SUPTableActivity;
import com.kingagroot.kingdraw.ui.OpenGlMainActivity;
import com.kingagroot.kingdraw.ui.baike.SearchPediaActivity;
import com.kingagroot.kingdraw.ui.SupPaletteActivity;
import com.kingagroot.kingdraw.ui.NewSearchPaletteActivity;
import com.kingagroot.kingdraw.palette.NewPaletteRotateActivity;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.EasyFloat;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.kingdraw.config.NetConfig;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import android.content.DialogInterface$OnClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lzf.easyfloat.permission.PermissionUtils;
import android.app.Activity;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils$ShareFileDownLoad;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils$VerifyModel;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.content.Context;
import android.webkit.WebView;
import android.net.Uri;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import android.widget.TextView;
import java.util.List;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.floatwindow.FloatBaseActivity;

public class TemplateActivity extends FloatBaseActivity implements View$OnClickListener
{
    private static TemplateActivity templateActivity;
    private FrameLayout flWeb;
    private ImageButton ibtBack;
    private ImageButton ibtTemplateClose;
    private ImageButton ibtTemplateHide;
    private WorkStationModel model;
    private final List<String> titleList;
    private TextView tvTemplateTitle;
    private final String urlIntercept;
    private final List<String> urlList;
    private WorkWindowDbi workWindowDbi;
    
    public TemplateActivity() {
        this.urlList = (List<String>)new ArrayList();
        this.titleList = (List<String>)new ArrayList();
        this.urlIntercept = "Service/Content/Share_Android2";
        TemplateActivity.templateActivity = this;
    }
    
    private void addWeb(final String s) {
        final WebView webView = new WebView((Context)this);
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient((WebViewClient)new TemplateActivity$1(this));
        webView.loadUrl(s);
        this.flWeb.addView((View)webView);
        webView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        if (this.flWeb.getChildCount() > 1) {
            this.ibtBack.setVisibility(0);
        }
        else {
            this.ibtBack.setVisibility(8);
        }
    }
    
    private void checkOpenFile(final Uri uri) {
        final String queryParameter = uri.getQueryParameter("kingdrawId");
        final String queryParameter2 = uri.getQueryParameter("fileExtension");
        final String queryParameter3 = uri.getQueryParameter("fileName");
        final String queryParameter4 = uri.getQueryParameter("CreateTime");
        if (!TextUtils.isEmpty((CharSequence)queryParameter)) {
            final ShareFileTokenUtils$VerifyModel shareFileTokenUtils$VerifyModel = new ShareFileTokenUtils$VerifyModel();
            shareFileTokenUtils$VerifyModel.isCheck = true;
            shareFileTokenUtils$VerifyModel.fileextension = queryParameter2;
            shareFileTokenUtils$VerifyModel.fileName = queryParameter3;
            shareFileTokenUtils$VerifyModel.fileOssid = queryParameter;
            shareFileTokenUtils$VerifyModel.creatTime = queryParameter4;
            new ShareFileTokenUtils$ShareFileDownLoad((Activity)this, shareFileTokenUtils$VerifyModel).dowloadShareFile();
        }
    }
    
    private void checkPermission() {
        if (PermissionUtils.checkPermission((Context)this)) {
            this.showWindow();
        }
        else {
            new MaterialAlertDialogBuilder((Context)this, 2131886086).setTitle(2131820936).setMessage(2131820870).setPositiveButton(2131821118, (DialogInterface$OnClickListener)new _$$Lambda$TemplateActivity$yzju3PwL6S9Y_JXifoUaBcr6X1w(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
    }
    
    public static TemplateActivity getIntense() {
        return TemplateActivity.templateActivity;
    }
    
    private void initView() {
        this.ibtBack = (ImageButton)this.findViewById(2131296800);
        this.tvTemplateTitle = (TextView)this.findViewById(2131297670);
        this.ibtTemplateHide = (ImageButton)this.findViewById(2131296831);
        this.ibtTemplateClose = (ImageButton)this.findViewById(2131296830);
        this.flWeb = (FrameLayout)this.findViewById(2131296707);
        this.ibtBack.setOnClickListener((View$OnClickListener)this);
        this.ibtTemplateClose.setOnClickListener((View$OnClickListener)this);
        this.ibtTemplateHide.setOnClickListener((View$OnClickListener)this);
        this.workWindowDbi = (WorkWindowDbi)new WorkWindowDbiMpl();
        this.model = (WorkStationModel)this.getIntent().getSerializableExtra(WebStationActivity.MODEL_DATA);
        String s;
        if (!LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            s = NetConfig.templateEnUrl;
        }
        else {
            s = NetConfig.templateCnUrl;
        }
        this.addWeb(s);
    }
    
    private void showWindow() {
        if (!SPUtil.getBooleanDefault("FLOAT_PEDIA", false) && !SPUtil.getBooleanDefault("FLOAT_TEMPLATE", false) && !SPUtil.getBooleanDefault("FLOAT_WEB_STATION", false)) {
            EasyFloat.with((Context)this).setShowPattern(ShowPattern.FOREGROUND).setImmersionStatusBar(false).setGravity(8388613, 0, 500).setSidePattern(SidePattern.RESULT_HORIZONTAL).setFilter(new Class[] { PediasActivity.class, TemplateActivity.class, WebStationActivity.class, NewPaletteRotateActivity.class, NewSearchPaletteActivity.class, SupPaletteActivity.class, SearchPediaActivity.class, OpenGlMainActivity.class, SUPTableActivity.class, ElementTableActivity.class, WebWorkActivity.class, NativeSearchPaletteActivity.class, NativePaletteActivity.class }).setLayout(2131493034, (OnInvokeView)new TemplateActivity$3(this)).registerCallbacks((OnFloatCallbacks)new TemplateActivity$2(this)).show();
        }
        else {
            this.moveTaskToBack(false);
            this.workWindowDbi.addWorkItem(this.model);
            SPUtil.setBooleanDefault("FLOAT_TEMPLATE", true);
        }
    }
    
    public void onBackPressed() {
        final int childCount = this.flWeb.getChildCount();
        if (childCount > 2) {
            this.ibtBack.setVisibility(0);
        }
        else {
            this.ibtBack.setVisibility(8);
        }
        if (childCount > 1) {
            this.flWeb.removeViewAt(childCount - 1);
            final int n = this.urlList.size() - 1;
            if (n != -1) {
                this.urlList.remove(n);
            }
            final int n2 = this.titleList.size() - 1;
            if (n2 != -1) {
                this.titleList.remove(n2);
            }
            if (!this.titleList.isEmpty()) {
                this.tvTemplateTitle.setText((CharSequence)this.titleList.get(this.titleList.size() - 1));
            }
        }
        else {
            this.moveTaskToBack(true);
        }
    }
    
    public void onClick(final View view) {
        if (view == this.ibtBack) {
            this.onBackPressed();
        }
        else if (view == this.ibtTemplateClose) {
            if (Build$VERSION.SDK_INT >= 21) {
                this.finishAndRemoveTask();
            }
            else {
                this.finish();
            }
            SPUtil.setBooleanDefault("FLOAT_TEMPLATE", false);
            if (!SPUtil.getBooleanDefault("FLOAT_PEDIA", false)) {
                EasyFloat.dismiss();
            }
            this.workWindowDbi.deleteWorkItem(this.model);
        }
        else if (view == this.ibtTemplateHide) {
            this.checkPermission();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492935);
        this.initView();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtil.setBooleanDefault("FLOAT_TEMPLATE", false);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
}
