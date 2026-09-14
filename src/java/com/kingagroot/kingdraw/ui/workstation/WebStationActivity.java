package com.kingagroot.kingdraw.ui.workstation;

import android.view.KeyEvent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View$OnClickListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import com.lzf.easyfloat.interfaces.OnPermissionResult;
import android.app.Activity;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import java.util.List;
import android.content.DialogInterface$OnClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lzf.easyfloat.permission.PermissionUtils;
import android.os.Build$VERSION;
import androidx.fragment.app.FragmentActivity;
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
import android.content.Context;
import com.lzf.easyfloat.EasyFloat;
import com.goodsrc.library.utils.SPUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.floatwindow.FloatBaseActivity;

public class WebStationActivity extends FloatBaseActivity
{
    public static String MODEL_DATA = "MODEL_DATA";
    private static WebStationActivity webStationActivity;
    private WorkStationAppFragment appFragment;
    private LocalBroadcastManager broadcastManager;
    private WorkStationModel model;
    BroadcastReceiver receiver;
    private WorkWindowDbi workWindowDbi;
    
    public WebStationActivity() {
        this.receiver = (BroadcastReceiver)new WebStationActivity$1(this);
        WebStationActivity.webStationActivity = this;
    }
    
    public static WebStationActivity getIntense() {
        return WebStationActivity.webStationActivity;
    }
    
    private void initView() {
        this.workWindowDbi = (WorkWindowDbi)new WorkWindowDbiMpl();
        final WorkStationModel model = (WorkStationModel)this.getIntent().getSerializableExtra(WebStationActivity.MODEL_DATA);
        this.model = model;
        if (model != null) {
            final String url = model.getUrl();
            final StringBuilder sb = new StringBuilder();
            sb.append(this.model.getAppID());
            sb.append("");
            this.initWebLoad(url, sb.toString());
        }
    }
    
    private void initWebLoad(final String s, final String s2) {
        this.appFragment = WorkWebCountView.createAppFragment(s, s2);
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        beginTransaction.add(2131296707, (Fragment)this.appFragment, "url");
        beginTransaction.commitNowAllowingStateLoss();
        this.appFragment.setStationActivity(this);
    }
    
    private void showWindow() {
        if (!SPUtil.getBooleanDefault("FLOAT_PEDIA", false) && !SPUtil.getBooleanDefault("FLOAT_TEMPLATE", false) && !SPUtil.getBooleanDefault("FLOAT_WEB_STATION", false)) {
            EasyFloat.with((Context)this).setShowPattern(ShowPattern.FOREGROUND).setImmersionStatusBar(false).setGravity(8388613, 0, 500).setSidePattern(SidePattern.RESULT_HORIZONTAL).setFilter(new Class[] { PediasActivity.class, TemplateActivity.class, WebStationActivity.class, NewPaletteRotateActivity.class, NewSearchPaletteActivity.class, SupPaletteActivity.class, SearchPediaActivity.class, OpenGlMainActivity.class, SUPTableActivity.class, ElementTableActivity.class, WebWorkActivity.class, NativeSearchPaletteActivity.class, NativePaletteActivity.class }).setLayout(2131493034, (OnInvokeView)new _$$Lambda$WebStationActivity$pjyT0WVVVCvQk3_eQGRBwlJmoOg(this)).registerCallbacks((OnFloatCallbacks)new WebStationActivity$2(this)).show();
        }
        else {
            WorkWebCountView.clearViewParent((FragmentActivity)this, this.appFragment);
            SPUtil.setBooleanDefault("FLOAT_WEB_STATION", true);
            this.workWindowDbi.addWorkItem(this.model);
            this.finish();
        }
    }
    
    public void backToWork() {
        WorkWebCountView.remove(this.appFragment);
        if (Build$VERSION.SDK_INT >= 21) {
            this.finishAndRemoveTask();
        }
    }
    
    public void checkPermission() {
        if (PermissionUtils.checkPermission((Context)this)) {
            this.showWindow();
        }
        else {
            new MaterialAlertDialogBuilder((Context)this, 2131886086).setTitle(2131820936).setMessage(2131820870).setPositiveButton(2131821118, (DialogInterface$OnClickListener)new _$$Lambda$WebStationActivity$rVhGaFs2s68N0j_1s2qOouxhhzw(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
    }
    
    public void closeActivity() {
        WorkWebCountView.remove(this.appFragment);
        final List allWorkItems = this.workWindowDbi.getAllWorkItems();
        if (allWorkItems != null && allWorkItems.size() > 1) {
            this.workWindowDbi.deleteWorkItem(this.model);
        }
        else {
            SPUtil.setBooleanDefault("FLOAT_WEB_STATION", false);
            if (!SPUtil.getBooleanDefault("FLOAT_WEB_STATION", false)) {
                EasyFloat.dismiss();
            }
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.finishAndRemoveTask();
        }
    }
    
    public void onBackPressed() {
        if (this.appFragment.isWebGoBack()) {
            this.appFragment.webViewGoBack();
        }
        else {
            WorkWebCountView.remove(this.appFragment);
            super.onBackPressed();
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492941);
        this.initView();
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("QUIT_GROUP");
        intentFilter.addAction("USER_LOGOUT");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            final WorkStationAppFragment appFragment = this.appFragment;
            if (appFragment != null) {
                appFragment.onBackClick();
            }
        }
        return super.onKeyDown(n, keyEvent);
    }
}
