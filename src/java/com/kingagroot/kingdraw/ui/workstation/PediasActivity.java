package com.kingagroot.kingdraw.ui.workstation;

import com.goodsrc.ui.library.MANServiceConfig;
import android.os.Bundle;
import android.os.Build$VERSION;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.lzf.easyfloat.interfaces.OnPermissionResult;
import android.app.Activity;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.ui.baike.bridgeHandler.NativeOpenHandler;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
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
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import android.text.TextUtils;
import com.kingagroot.kingdraw.ui.baike.dbi.SearchDbiMpl;
import com.github.lzyzsd.jsbridge.Message;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.kingagroot.kingdraw.ui.baike.SearchType;
import com.kingagroot.kingdraw.ui.baike.SearchDataType;
import com.kingagroot.kingdraw.config.APIConfig;
import android.content.DialogInterface$OnClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.Context;
import com.lzf.easyfloat.permission.PermissionUtils;
import java.util.ArrayList;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import android.widget.TextView;
import java.util.List;
import com.kingagroot.kingdraw.ui.baike.dbi.SearchDbi;
import com.kingagroot.kingdraw.ui.baike.bridgeHandler.SearchCommandHandler;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import com.kingagroot.kingdraw.floatwindow.FloatBaseActivity;

public class PediasActivity extends FloatBaseActivity implements View$OnClickListener
{
    static final boolean $assertionsDisabled = false;
    public static String PALETTE_SMILES = "SMILES";
    public static String YY_OVERRIDE_SCHEMA = "yy://";
    public static String YY_RETURN_DATA;
    private static PediasActivity pediasActivity;
    private FrameLayout flPedia;
    private ImageButton ibtBack;
    private ImageButton ibtPediaClose;
    private ImageButton ibtPediaHide;
    private WorkStationModel model;
    private SearchCommandHandler searchCommandHandler;
    private SearchDbi searchDbi;
    private final List<String> titleList;
    private TextView tvPediaTitle;
    private final List<String> urlList;
    private WorkWindowDbi workWindowDbi;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(PediasActivity.YY_OVERRIDE_SCHEMA);
        sb.append("return/");
        PediasActivity.YY_RETURN_DATA = sb.toString();
    }
    
    public PediasActivity() {
        this.urlList = (List<String>)new ArrayList();
        this.titleList = (List<String>)new ArrayList();
        PediasActivity.pediasActivity = this;
    }
    
    private void checkPermission() {
        if (PermissionUtils.checkPermission((Context)this)) {
            this.showWindow();
        }
        else {
            new MaterialAlertDialogBuilder((Context)this, 2131886086).setTitle(2131820936).setMessage(2131820870).setPositiveButton(2131821118, (DialogInterface$OnClickListener)new _$$Lambda$PediasActivity$3YFaQS2O9ZGqYSVlN_kiAXr2fzM(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
    }
    
    private WorkStationModel getDefaultModel() {
        final WorkStationModel workStationModel = new WorkStationModel();
        workStationModel.setAppID(38);
        workStationModel.setCategoryID(6);
        workStationModel.setNameCN("\u5316\u5408\u7269\u767e\u79d1");
        workStationModel.setNameEN("KDpedia");
        workStationModel.setLogo("http://wk-res.wk.kingdraw.com/AppLogo/1c88c2e14685485aab3195531106c953.png");
        workStationModel.setUrl(this.urlFormat(APIConfig.API_BK_HOME));
        workStationModel.setFuallScreen(1);
        workStationModel.setOrder(1);
        workStationModel.setNeedLogin(0);
        workStationModel.setIsGroupApp(0);
        return workStationModel;
    }
    
    public static PediasActivity getIntense() {
        return PediasActivity.pediasActivity;
    }
    
    private SearchDataType getSearchType(final String s) {
        if (SearchType.isPositiveInteger(s)) {
            return SearchDataType.RecordNumber;
        }
        if (SearchType.isSmiles(s)) {
            return SearchDataType.SMILES;
        }
        if (SearchType.isInChI(s)) {
            return SearchDataType.InChI;
        }
        if (SearchType.ismf(s)) {
            return SearchDataType.MF;
        }
        return SearchDataType.TEXT;
    }
    
    private void initSearchMessage(final BridgeWebView bridgeWebView, final String s) {
        this.searchCommandHandler.setType(this.setDataShowBySearchType(s));
        this.searchCommandHandler.setText(s);
        this.searchCommandHandler.setMax(0.0f);
        this.searchCommandHandler.setMin(0.0f);
        final Message creatMessage = this.searchCommandHandler.creatMessage();
        final ArrayList startupMessage = new ArrayList();
        ((List)startupMessage).add((Object)creatMessage);
        bridgeWebView.setStartupMessage((List)startupMessage);
    }
    
    private void initView() {
        this.ibtBack = (ImageButton)this.findViewById(2131296800);
        this.tvPediaTitle = (TextView)this.findViewById(2131297636);
        this.ibtPediaHide = (ImageButton)this.findViewById(2131296815);
        this.ibtPediaClose = (ImageButton)this.findViewById(2131296814);
        this.flPedia = (FrameLayout)this.findViewById(2131296696);
        this.ibtPediaHide.setOnClickListener((View$OnClickListener)this);
        this.ibtPediaClose.setOnClickListener((View$OnClickListener)this);
        this.ibtBack.setOnClickListener((View$OnClickListener)this);
        this.setTitleVisibility(false);
        this.searchDbi = (SearchDbi)new SearchDbiMpl();
        final String stringExtra = this.getIntent().getStringExtra(PediasActivity.PALETTE_SMILES);
        if (TextUtils.isEmpty((CharSequence)stringExtra)) {
            this.urlLoad(this.urlFormat(APIConfig.API_BK_HOME), null);
        }
        else {
            this.urlLoad(this.urlFormat(APIConfig.API_BK_LIST), stringExtra);
        }
        this.workWindowDbi = (WorkWindowDbi)new WorkWindowDbiMpl();
        this.model = this.getDefaultModel();
    }
    
    private String setDataShowBySearchType(String s) {
        final int n = PediasActivity$4.$SwitchMap$com$kingagroot$kingdraw$ui$baike$SearchDataType[this.getSearchType(s).ordinal()];
        if (n != 1 && n != 2) {
            if (n != 3) {
                if (n != 4) {
                    if (n != 5) {
                        s = null;
                    }
                    else {
                        s = "4";
                    }
                }
                else {
                    s = "5";
                }
            }
            else {
                s = "7";
            }
        }
        else {
            s = "1";
        }
        return s;
    }
    
    private void setTitleVisibility(final boolean b) {
        if (b) {
            this.ibtBack.setVisibility(0);
            this.tvPediaTitle.setVisibility(0);
        }
        else {
            this.ibtBack.setVisibility(8);
            this.tvPediaTitle.setVisibility(8);
        }
    }
    
    private void showWindow() {
        if (!SPUtil.getBooleanDefault("FLOAT_PEDIA", false) && !SPUtil.getBooleanDefault("FLOAT_TEMPLATE", false) && !SPUtil.getBooleanDefault("FLOAT_WEB_STATION", false)) {
            EasyFloat.with((Context)this).setShowPattern(ShowPattern.FOREGROUND).setImmersionStatusBar(false).setGravity(8388613, 0, 500).setSidePattern(SidePattern.RESULT_HORIZONTAL).setFilter(new Class[] { PediasActivity.class, TemplateActivity.class, WebStationActivity.class, NewPaletteRotateActivity.class, NewSearchPaletteActivity.class, SupPaletteActivity.class, SearchPediaActivity.class, OpenGlMainActivity.class, SUPTableActivity.class, ElementTableActivity.class, WebWorkActivity.class, NativeSearchPaletteActivity.class, NativePaletteActivity.class }).setLayout(2131493034, (OnInvokeView)new _$$Lambda$PediasActivity$ZSto45p9ebALEfbEQPM8UG1PebU(this)).registerCallbacks((OnFloatCallbacks)new PediasActivity$3(this)).show();
        }
        else {
            this.moveTaskToBack(false);
            this.workWindowDbi.addWorkItem(this.model);
            SPUtil.setBooleanDefault("FLOAT_PEDIA", true);
        }
    }
    
    private String urlFormat(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        if (!LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            sb.append("?lang=en-us");
        }
        else {
            sb.append("?lang=zh-cn");
        }
        sb.append("&platform=3");
        return sb.toString();
    }
    
    private void urlLoad(final String s, final String s2) {
        final BridgeWebView bridgeWebView = new BridgeWebView((Context)this);
        this.searchCommandHandler = new SearchCommandHandler(bridgeWebView);
        bridgeWebView.setDefaultHandler((BridgeHandler)new DefaultHandler());
        bridgeWebView.setWebChromeClient(new WebChromeClient());
        bridgeWebView.getSettings().setDomStorageEnabled(true);
        if (s2 != null && !TextUtils.isEmpty((CharSequence)s2)) {
            this.initSearchMessage(bridgeWebView, s2);
            this.searchDbi.addSearchData(s2);
        }
        this.setTitleVisibility(s.equals((Object)this.urlFormat(APIConfig.API_BK_HOME)) ^ true);
        bridgeWebView.setWebViewClient((WebViewClient)new PediasActivity$1(this, bridgeWebView));
        bridgeWebView.setWebChromeClient((WebChromeClient)new PediasActivity$2(this));
        bridgeWebView.loadUrl(s);
        Label_0245: {
            if (!s.equals((Object)this.urlFormat(APIConfig.API_BK_LIST))) {
                if (!this.urlList.isEmpty()) {
                    final List<String> urlList = this.urlList;
                    if (((String)urlList.get(urlList.size() - 1)).equals((Object)s)) {
                        break Label_0245;
                    }
                }
                this.urlList.add((Object)s);
                bridgeWebView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
                if (this.flPedia.getChildCount() > 1) {
                    final FrameLayout flPedia = this.flPedia;
                    ((BridgeWebView)flPedia.getChildAt(flPedia.getChildCount() - 1)).onPause();
                }
                this.flPedia.addView((View)bridgeWebView);
            }
        }
        bridgeWebView.registerHandler("nativeopen", (BridgeHandler)new NativeOpenHandler((Context)this));
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1001) {
            String stringExtra;
            if (intent != null) {
                stringExtra = intent.getStringExtra("result");
            }
            else {
                stringExtra = null;
            }
            if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                this.urlLoad(this.urlFormat(APIConfig.API_BK_LIST), stringExtra);
            }
        }
        else if (n == 1002 && intent != null) {
            final GFileSearchModel gFileSearchModel = (GFileSearchModel)intent.getSerializableExtra("reslult_key_GSearchModel");
            if (gFileSearchModel != null) {
                final String smiles = gFileSearchModel.getSmiles();
                if (!TextUtils.isEmpty((CharSequence)smiles) && !"error".equals((Object)smiles)) {
                    this.searchDbi.addSearchData(smiles);
                    this.urlLoad(this.urlFormat(APIConfig.API_BK_LIST), smiles);
                }
            }
        }
    }
    
    public void onBackPressed() {
        final int childCount = this.flPedia.getChildCount();
        final List<String> urlList = this.urlList;
        final boolean b = false;
        boolean titleVisibility = false;
        if (urlList != null && urlList.size() > 0) {
            final List<String> urlList2 = this.urlList;
            if (((String)urlList2.get(urlList2.size() - 1)).startsWith(APIConfig.API_BK_LIST)) {
                this.flPedia.removeAllViews();
                this.urlList.clear();
                this.titleList.clear();
                this.urlLoad(this.urlFormat(APIConfig.API_BK_HOME), null);
            }
            else {
                if (this.urlList.contains((Object)this.urlFormat(APIConfig.API_BK_HOME))) {
                    if (childCount > 2) {
                        titleVisibility = true;
                    }
                    this.setTitleVisibility(titleVisibility);
                }
                if (childCount > 1) {
                    this.flPedia.removeViewAt(childCount - 1);
                    if (this.flPedia.getChildCount() > 1) {
                        final FrameLayout flPedia = this.flPedia;
                        ((BridgeWebView)flPedia.getChildAt(flPedia.getChildCount() - 1)).onResume();
                    }
                    final List<String> urlList3 = this.urlList;
                    urlList3.remove(urlList3.size() - 1);
                    final List<String> titleList = this.titleList;
                    titleList.remove(titleList.size() - 1);
                    if (!this.titleList.isEmpty()) {
                        final TextView tvPediaTitle = this.tvPediaTitle;
                        final List<String> titleList2 = this.titleList;
                        tvPediaTitle.setText((CharSequence)titleList2.get(titleList2.size() - 1));
                    }
                }
                else {
                    this.moveTaskToBack(true);
                }
            }
        }
        else {
            if (this.urlList.contains((Object)this.urlFormat(APIConfig.API_BK_HOME))) {
                boolean titleVisibility2 = b;
                if (childCount > 2) {
                    titleVisibility2 = true;
                }
                this.setTitleVisibility(titleVisibility2);
            }
            if (childCount > 1) {
                this.flPedia.removeViewAt(childCount - 1);
                final List<String> urlList4 = this.urlList;
                urlList4.remove(urlList4.size() - 1);
                final List<String> titleList3 = this.titleList;
                titleList3.remove(titleList3.size() - 1);
                if (!this.titleList.isEmpty()) {
                    final TextView tvPediaTitle2 = this.tvPediaTitle;
                    final List<String> titleList4 = this.titleList;
                    tvPediaTitle2.setText((CharSequence)titleList4.get(titleList4.size() - 1));
                }
            }
            else {
                this.moveTaskToBack(true);
            }
        }
    }
    
    public void onClick(final View view) {
        if (view == this.ibtPediaHide) {
            this.checkPermission();
        }
        else if (view == this.ibtPediaClose) {
            if (Build$VERSION.SDK_INT >= 21) {
                this.finishAndRemoveTask();
            }
            else {
                this.finish();
            }
            SPUtil.setBooleanDefault("FLOAT_PEDIA", false);
            if (!SPUtil.getBooleanDefault("FLOAT_TEMPLATE", false)) {
                EasyFloat.dismiss();
            }
            this.workWindowDbi.deleteWorkItem(this.model);
        }
        else if (view == this.ibtBack) {
            this.onBackPressed();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492923);
        this.initView();
    }
    
    @Override
    protected void onDestroy() {
        SPUtil.setBooleanDefault("FLOAT_PEDIA", false);
        this.flPedia.removeAllViews();
        super.onDestroy();
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        final String stringExtra = intent.getStringExtra(PediasActivity.PALETTE_SMILES);
        if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
            this.searchDbi.addSearchData(stringExtra);
            this.urlLoad(this.urlFormat(APIConfig.API_BK_LIST), stringExtra);
        }
    }
    
    protected void onPause() {
        super.onPause();
        MANServiceConfig.pageDisAppear((Activity)this);
    }
    
    protected void onResume() {
        super.onResume();
        MANServiceConfig.pageAppear((Activity)this);
    }
    
    public void setViewTitle(final String text) {
        this.tvPediaTitle.setText((CharSequence)text);
        this.titleList.add((Object)text);
    }
}
