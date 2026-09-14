package com.kingagroot.kingdraw.ui.workstation;

import java.util.List;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.IntentFilter;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.goodsrc.library.utils.UriUtil;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.BroadcastReceiver;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.widget.ImageButton;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.widget.jsweb.OnJsWebListener;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.ui.BaseFragment;

public class WorkStationAppFragment extends BaseFragment implements View$OnClickListener, OnJsWebListener
{
    private LocalBroadcastManager broadcastManager;
    private View contentView;
    private ImageButton ibtBack;
    private ImageButton ibtWorkstationClose;
    private ImageButton ibtWorkstationHide;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;
    BroadcastReceiver receiver;
    private WebStationActivity stationActivity;
    private SwipeRefreshLayout swipeFresh;
    private TextView tvWorkstationTitle;
    private String url;
    private JsWebView webview;
    
    public WorkStationAppFragment() {
        this.receiver = (BroadcastReceiver)new WorkStationAppFragment$1(this);
    }
    
    private void choosePic() {
        int language;
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_EN)) {
            language = 2;
        }
        else {
            language = 0;
        }
        PictureSelector.create((Fragment)this).openGallery(SelectMimeType.ofImage()).setImageEngine((ImageEngine)GlideEngine.createGlideEngine()).setLanguage(language).isDisplayCamera(true).setRequestedOrientation(-1).setImageSpanCount(4).setSelectionMode(1).isMaxSelectEnabledMask(true).isPageStrategy(true).isPreviewImage(true).isDirectReturnSingle(true).forResult(188);
    }
    
    private void initView(final View view) {
        this.ibtBack = (ImageButton)view.findViewById(2131296800);
        this.tvWorkstationTitle = (TextView)view.findViewById(2131297712);
        this.ibtWorkstationHide = (ImageButton)view.findViewById(2131296833);
        this.ibtWorkstationClose = (ImageButton)view.findViewById(2131296832);
        (this.swipeFresh = (SwipeRefreshLayout)view.findViewById(2131297436)).setOnRefreshListener((SwipeRefreshLayout$OnRefreshListener)new _$$Lambda$WorkStationAppFragment$6A0Yxv4QTQV0eG9KODLbEPWstM8(this));
        this.swipeFresh.setColorSchemeResources(new int[] { 2131099773, 2131099702 });
        this.ibtBack.setOnClickListener((View$OnClickListener)this);
        this.ibtWorkstationHide.setOnClickListener((View$OnClickListener)this);
        this.ibtWorkstationClose.setOnClickListener((View$OnClickListener)this);
        (this.webview = (JsWebView)view.findViewById(2131297764)).setOnJsWebListener((OnJsWebListener)this);
        this.webview.setSwipeFresh(this.swipeFresh);
        this.initWebSetting();
        this.webview.setWebChromeClient((WebChromeClient)new WorkStationAppFragment.WorkStationAppFragment$MyWebChromeClient(this, (WorkStationAppFragment$1)null));
    }
    
    private void initWebSetting() {
        final WebSettings settings = this.webview.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
    }
    
    private void onActivityResultAboveL(final Intent intent) {
        Uri[] array;
        if (intent != null) {
            array = new Uri[] { UriUtil.File2Uri(((LocalMedia)((List)PictureSelector.obtainSelectorList(intent)).get(0)).getRealPath()) };
        }
        else {
            array = null;
        }
        this.mUploadCallbackAboveL.onReceiveValue((Object)array);
        this.mUploadCallbackAboveL = null;
    }
    
    public boolean isWebGoBack() {
        return this.webview.canGoBack();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (this.mUploadMessage == null && this.mUploadCallbackAboveL == null) {
            return;
        }
        if (n2 != -1) {
            final ValueCallback<Uri[]> mUploadCallbackAboveL = this.mUploadCallbackAboveL;
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue((Object)null);
                this.mUploadCallbackAboveL = null;
            }
            final ValueCallback<Uri> mUploadMessage = this.mUploadMessage;
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue((Object)null);
                this.mUploadMessage = null;
            }
            return;
        }
        if (n == 188) {
            Uri file2Uri;
            if (intent != null) {
                file2Uri = UriUtil.File2Uri(((LocalMedia)((List)PictureSelector.obtainSelectorList(intent)).get(0)).getRealPath());
            }
            else {
                file2Uri = null;
            }
            if (this.mUploadCallbackAboveL != null) {
                this.onActivityResultAboveL(intent);
            }
            else {
                final ValueCallback<Uri> mUploadMessage2 = this.mUploadMessage;
                if (mUploadMessage2 != null) {
                    mUploadMessage2.onReceiveValue((Object)file2Uri);
                    this.mUploadMessage = null;
                }
            }
        }
    }
    
    public void onBackButtonShow(final boolean b) {
        if (b) {
            this.ibtBack.setVisibility(0);
        }
        else {
            this.ibtBack.setVisibility(8);
        }
    }
    
    public void onBackClick() {
        this.webview.evaluateJavascript("kd.canGoBack()", (ValueCallback)new _$$Lambda$WorkStationAppFragment$29WkdhaxvkGkSknqpjKvusJ713M(this));
    }
    
    public void onClick(final View view) {
        if (view == this.ibtBack) {
            this.onBackClick();
        }
        else if (view == this.ibtWorkstationHide) {
            final WebStationActivity stationActivity = this.stationActivity;
            if (stationActivity != null) {
                stationActivity.checkPermission();
            }
        }
        else if (view == this.ibtWorkstationClose) {
            final WebStationActivity stationActivity2 = this.stationActivity;
            if (stationActivity2 != null) {
                stationActivity2.closeActivity();
            }
        }
    }
    
    public void onCloseWeb() {
        final WebStationActivity stationActivity = this.stationActivity;
        if (stationActivity != null) {
            stationActivity.closeActivity();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this.getActivity());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("INTENT_ACTION_REFRESH");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.contentView == null) {
            this.initView(this.contentView = layoutInflater.inflate(2131493063, viewGroup, false));
            final Bundle arguments = this.getArguments();
            if (arguments != null) {
                this.url = arguments.getString("url");
                this.webview.setAppId(arguments.getString("appid"));
                this.webview.loadUrl(this.url);
            }
        }
        return this.contentView;
    }
    
    public void onDestroy() {
        super.onDestroy();
        final JsWebView webview = this.webview;
        if (webview != null) {
            webview.finish();
        }
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public void onGetDeviceData() {
    }
    
    public void onGetUserToken() {
    }
    
    public void onLoadFinish() {
        this.swipeFresh.setRefreshing(false);
    }
    
    public void onLogout() {
    }
    
    public void onOpenAppUrl(final String s) {
    }
    
    public void onOpenUrl(final String s) {
        final Intent intent = new Intent(this.getContext(), (Class)WebWorkActivity.class);
        intent.putExtra(WebWorkActivity.URL_DATA, s);
        this.startActivity(intent);
    }
    
    public void onSetBadge(final int n) {
    }
    
    public void onUpdateBadge() {
    }
    
    public void onUpdateTitle(final String text) {
        this.tvWorkstationTitle.setText((CharSequence)text);
    }
    
    public void onUserAccountChanged() {
    }
    
    public void onUserPwdChanged() {
    }
    
    public void setStationActivity(final WebStationActivity webStationActivity) {
        this.stationActivity = webStationActivity;
        final JsWebView webview = this.webview;
        if (webview != null) {
            webview.setActivity((Activity)webStationActivity);
        }
    }
    
    public void webViewGoBack() {
        this.webview.goBack();
    }
}
