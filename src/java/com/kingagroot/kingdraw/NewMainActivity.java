package com.kingagroot.kingdraw;

import com.kingagroot.kingdraw.interfaces.GroupListDBI;
import com.kingagroot.kingdraw.interfaces.impl.GroupListDBIMpl;
import com.kingagroot.kingdraw.ui.SynService;
import com.lzf.easyfloat.EasyFloat;
import com.goodsrc.library.utils.SPUtil;
import android.graphics.Color;
import com.goodsrc.ui.library.widget.AppManager;
import com.kingagroot.kingdraw.ui.account.LoginMainActivity;
import com.goodsrc.library.utils.NetworkUtil;
import android.view.MenuItem;
import android.view.KeyEvent;
import com.goodsrc.ui.library.MANServiceConfig;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import android.os.Bundle;
import android.os.Process;
import android.app.Dialog;
import com.kingagroot.kingdraw.dialog.DialogManager;
import com.kingagroot.kingdraw.config.NetConfig$Message;
import androidx.core.content.ContextCompat;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.qw.curtain.lib.CurtainFlow$CallBack;
import com.qw.curtain.lib.CurtainFlow$Builder;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.kingagroot.kingdraw.widget.WorkStationToolBar$OnToolBarClickTypeListener;
import com.kingagroot.kingdraw.model.GroupModel;
import com.kingagroot.kingdraw.utils.link.UserLogoutLink$OnUserLogOutListener;
import com.kingagroot.kingdraw.utils.link.UserLogoutLink;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.DialogInterface;
import java.util.Iterator;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import android.content.res.ColorStateList;
import com.goodsrc.library.utils.StatusBarUtil;
import com.kingagroot.kingdraw.base.AppDataIniter;
import android.content.IntentFilter;
import com.qw.curtain.lib.shape.Shape;
import com.qw.curtain.lib.shape.RoundShape;
import com.qw.curtain.lib.Padding;
import androidx.fragment.app.FragmentActivity;
import com.qw.curtain.lib.Curtain;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import android.net.Uri;
import android.content.Context;
import com.kingagroot.kingdraw.limit.LimitPalette$OnJumpPalette;
import com.kingagroot.kingdraw.limit.LimitPalette;
import android.app.Activity;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils$ShareFileDownLoad;
import com.kingagroot.kingdraw.utils.ShareFileTokenUtils$VerifyModel;
import android.text.TextUtils;
import android.content.Intent;
import android.os.Handler;
import com.kingagroot.kingdraw.config.ShareData;
import android.view.WindowManager$LayoutParams;
import com.kingagroot.kingdraw.model.MessageCountModel;
import java.util.List;
import com.kingagroot.kingdraw.ui.WorkWebFragment;
import com.kingagroot.kingdraw.widget.WorkStationToolBar;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.content.BroadcastReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kingagroot.kingdraw.ui.MessageWebFragment;
import com.kingagroot.kingdraw.ui.MeFragment;
import com.kingagroot.kingdraw.ui.MainFragment;
import com.kingagroot.kingdraw.base.HttpBroadcastReceiver;
import android.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.kingagroot.kingdraw.dialog.AppScoreDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView$OnNavigationItemSelectedListener;
import com.kingagroot.kingdraw.ui.jpushbase.JpushBaseActivity;

public class NewMainActivity extends JpushBaseActivity implements BottomNavigationView$OnNavigationItemSelectedListener
{
    public static final String INTENT_KEY_CHECKUP_DATA = "intent_key_checkup_data";
    public static final String INTENT_KEY_LOGIN_OUT = "intent_key_login_out";
    public static final String INTENT_KEY_LOGIN_RE_LOGIN = "intent_key_login_re_login";
    private static NewMainActivity mainActivity;
    private AppScoreDialog appScoreDialog;
    private LocalBroadcastManager broadcastManager;
    private AlertDialog dialog;
    private long exitTime;
    private int groupId;
    private HttpBroadcastReceiver httpBroadcastReceiver;
    private int itemId;
    private MainFragment mainFragment;
    private MeFragment meFragment;
    private MessageWebFragment messageFragment;
    private BottomNavigationView navView;
    BroadcastReceiver receiver;
    private View shadowView;
    private Toolbar toolbar;
    private WorkStationToolBar toolbarWork;
    private View viewTop;
    private WorkWebFragment workFragment;
    
    public NewMainActivity() {
        this.exitTime = 0L;
        this.receiver = (BroadcastReceiver)new NewMainActivity$1(this);
        NewMainActivity.mainActivity = this;
    }
    
    private void bgAlpha(final float alpha) {
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.alpha = alpha;
        this.getWindow().addFlags(2);
        this.getWindow().setAttributes(attributes);
    }
    
    private void checkAppScore() {
        if (ShareData.needToAppScore() && ShareData.isUsedPalette()) {
            new Handler().postDelayed((Runnable)new _$$Lambda$NewMainActivity$1ziW7GDuld2npx6DPBmW9dZu3S4(this), 1000L);
        }
    }
    
    private void checkOpenFile(final Intent intent) {
        final String action = intent.getAction();
        if ("android.intent.action.VIEW".equals((Object)action)) {
            final Uri data = intent.getData();
            final String queryParameter = data.getQueryParameter("kingdrawId");
            final String queryParameter2 = data.getQueryParameter("fileExtension");
            final String queryParameter3 = data.getQueryParameter("fileName");
            final String queryParameter4 = data.getQueryParameter("CreateTime");
            if (!TextUtils.isEmpty((CharSequence)queryParameter)) {
                final ShareFileTokenUtils$VerifyModel shareFileTokenUtils$VerifyModel = new ShareFileTokenUtils$VerifyModel();
                shareFileTokenUtils$VerifyModel.isCheck = true;
                shareFileTokenUtils$VerifyModel.fileextension = queryParameter2;
                shareFileTokenUtils$VerifyModel.fileName = queryParameter3;
                shareFileTokenUtils$VerifyModel.fileOssid = queryParameter;
                shareFileTokenUtils$VerifyModel.creatTime = queryParameter4;
                new ShareFileTokenUtils$ShareFileDownLoad((Activity)this, shareFileTokenUtils$VerifyModel).dowloadShareFile();
            }
            else {
                new LimitPalette((LimitPalette$OnJumpPalette)new NewMainActivity$6(this, action, intent)).jumpPaletteCheck((Context)this);
            }
        }
    }
    
    private void checkVersionAvailable() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getCheckVersionIsNotAvailable()), (RequestCallBack)new NewMainActivity$3(this));
    }
    
    private void exit() {
        if (System.currentTimeMillis() - this.exitTime > 2000L) {
            ToastUtil.showShort(2131821473);
            this.exitTime = System.currentTimeMillis();
        }
        else {
            this.finish();
            System.exit(0);
        }
    }
    
    public static NewMainActivity getMainActivity() {
        return NewMainActivity.mainActivity;
    }
    
    private Curtain getStepOneGuide(final View view) {
        return new Curtain((FragmentActivity)this).with(view).withPadding(view, Padding.all(-16)).withShape(view, (Shape)new RoundShape(12.0f)).setTopView(2131493228);
    }
    
    private Curtain getStepTwoGuide(final View view) {
        return new Curtain((FragmentActivity)this).with(view).withPadding(view, Padding.all(-16)).withShape(view, (Shape)new RoundShape(12.0f)).setTopView(2131493230);
    }
    
    private void initData() {
        this.getUnReadCount();
        this.checkOpenFile(this.getIntent());
        this.broadcastManager = LocalBroadcastManager.getInstance((Context)this);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("kingagroot.intent.action.http_relogin");
        final HttpBroadcastReceiver httpBroadcastReceiver = new HttpBroadcastReceiver();
        this.httpBroadcastReceiver = httpBroadcastReceiver;
        this.broadcastManager.registerReceiver((BroadcastReceiver)httpBroadcastReceiver, intentFilter);
        AppDataIniter.getMobileInfo((Context)this);
        try {
            "android.intent.action.VIEW".equals((Object)this.getIntent().getAction());
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        final IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("QUIT_GROUP");
        intentFilter2.addAction("USER_LOGIN");
        this.broadcastManager.registerReceiver(this.receiver, intentFilter);
    }
    
    private void initState() {
        StatusBarUtil.setTransparent((Context)this);
        StatusBarUtil.setTextDark((Context)this, true);
    }
    
    private void initView() {
        this.toolbar = (Toolbar)this.findViewById(2131297503);
        this.toolbarWork = (WorkStationToolBar)this.findViewById(2131297509);
        this.viewTop = this.findViewById(2131297740);
        this.shadowView = this.findViewById(2131297368);
        this.toolbar.setTitle((CharSequence)this.getString(2131821012));
        this.toolbar.setTitleTextColor(-16777216);
        this.toolbar.setPadding(0, 20, 0, 0);
        this.setSupportActionBar(this.toolbar);
        (this.navView = (BottomNavigationView)this.findViewById(2131297100)).setItemIconTintList((ColorStateList)null);
        this.mainFragment = new MainFragment();
        this.workFragment = new WorkWebFragment();
        this.messageFragment = new MessageWebFragment();
        this.meFragment = new MeFragment();
        this.navView.getMenu().getItem(0).setChecked(true);
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        final FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        final Iterator iterator = supportFragmentManager.getFragments().iterator();
        while (iterator.hasNext()) {
            beginTransaction.remove((Fragment)iterator.next());
        }
        beginTransaction.commitNowAllowingStateLoss();
        this.onFragmentChange(2131297104);
        this.navView.setOnNavigationItemSelectedListener((BottomNavigationView$OnNavigationItemSelectedListener)this);
    }
    
    private void onFragmentChange(final int n) {
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        final FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        final Iterator iterator = supportFragmentManager.getFragments().iterator();
        while (iterator.hasNext()) {
            beginTransaction.hide((Fragment)iterator.next());
        }
        switch (n) {
            case 2131297105: {
                final WorkWebFragment workFragment = (WorkWebFragment)this.getSupportFragmentManager().findFragmentByTag("\u5de5\u4f5c\u53f0");
                this.workFragment = workFragment;
                if (workFragment == null) {
                    beginTransaction.add(2131297051, (Fragment)(this.workFragment = new WorkWebFragment()), "\u5de5\u4f5c\u53f0");
                    break;
                }
                beginTransaction.show((Fragment)workFragment);
                break;
            }
            case 2131297104: {
                final MainFragment mainFragment = (MainFragment)this.getSupportFragmentManager().findFragmentByTag("\u7ed3\u6784\u5f0f");
                this.mainFragment = mainFragment;
                if (mainFragment == null) {
                    beginTransaction.add(2131297051, (Fragment)(this.mainFragment = new MainFragment()), "\u7ed3\u6784\u5f0f");
                }
                else {
                    beginTransaction.show((Fragment)mainFragment);
                }
                this.toolbar.setTitle((CharSequence)this.getString(2131821010));
                break;
            }
            case 2131297103: {
                final MessageWebFragment messageFragment = (MessageWebFragment)this.getSupportFragmentManager().findFragmentByTag("\u6d88\u606f");
                this.messageFragment = messageFragment;
                if (messageFragment == null) {
                    beginTransaction.add(2131297051, (Fragment)(this.messageFragment = new MessageWebFragment()), "\u6d88\u606f");
                }
                else {
                    beginTransaction.show((Fragment)messageFragment);
                }
                this.toolbar.setTitle((CharSequence)this.getString(2131821009));
                break;
            }
            case 2131297102: {
                final MeFragment meFragment = (MeFragment)this.getSupportFragmentManager().findFragmentByTag("\u6211");
                this.meFragment = meFragment;
                if (meFragment == null) {
                    beginTransaction.add(2131297051, (Fragment)(this.meFragment = new MeFragment()), "\u6211");
                }
                else {
                    beginTransaction.show((Fragment)meFragment);
                }
                this.toolbar.setTitle((CharSequence)this.getString(2131821007));
                break;
            }
        }
        beginTransaction.commitNowAllowingStateLoss();
    }
    
    private void setGroupHide() {
        this.toolbar.setTitle((CharSequence)this.getString(2131821011));
        this.toolbarWork.setVisibility(8);
    }
    
    private void setMessageCount(final List<MessageCountModel> list) {
        final Iterator iterator = list.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final MessageCountModel messageCountModel = (MessageCountModel)iterator.next();
            n += messageCountModel.getUdidUnreadCount() + messageCountModel.getUuidUnreadCount();
        }
        this.displayItemNum(2, n);
    }
    
    private void setWorkGroupToolBar(final GroupModel groupModel) {
        final WorkWebFragment workFragment = this.workFragment;
        if (workFragment != null) {
            workFragment.webRefresh();
        }
        this.toolbarWork.setVisibility(0);
        this.toolbarWork.setTvTitle(groupModel.getCompanyName());
        this.toolbarWork.setLogoIcon(groupModel.getLogo());
        this.toolbarWork.setToolbarRightTextVisible(groupModel.getAuthority());
        this.toolbarWork.setToolBarClickListener((WorkStationToolBar$OnToolBarClickTypeListener)new NewMainActivity$5(this));
    }
    
    private void showCheckVersionDialog(final String message) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)message).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$NewMainActivity$6P7qtd_r3LBbX6aZz8RpuKU3SKc(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)new _$$Lambda$NewMainActivity$9I_iNNnt8SjWByAKG2iw3kqtd6A(this));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
    }
    
    private void showGuide() {
        final BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView)this.navView.getChildAt(0);
        new CurtainFlow$Builder().with(1, this.getStepOneGuide(bottomNavigationMenuView.getChildAt(1))).with(2, this.getStepTwoGuide(bottomNavigationMenuView.getChildAt(3))).create().start((CurtainFlow$CallBack)new NewMainActivity$2(this));
    }
    
    private void showLogoutDialog() {
        final AlertDialog$Builder setPositiveButton = new AlertDialog$Builder((Context)this).setTitle(2131820936).setMessage(2131820995).setPositiveButton(2131820731, (DialogInterface$OnClickListener)_$$Lambda$NewMainActivity$Il0MLQig8Jaop4LOV9MrLwd5Teo.INSTANCE);
        if (this.dialog == null) {
            this.dialog = setPositiveButton.create();
        }
        final AlertDialog dialog = this.dialog;
        if (dialog != null && !dialog.isShowing()) {
            this.dialog.show();
        }
    }
    
    public void displayItemNum(final int n, final int n2) {
        try {
            if (n > this.navView.getItemIconSize() || n < 0) {
                return;
            }
            final BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView)this.navView.getChildAt(0);
            final BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView)bottomNavigationMenuView.getChildAt(n);
            View view;
            if ((view = bottomNavigationItemView.getChildAt(2)) == null) {
                view = LayoutInflater.from(this.navView.getContext()).inflate(2131492959, (ViewGroup)bottomNavigationMenuView, false);
                bottomNavigationItemView.addView(view);
            }
            final TextView textView = (TextView)view.findViewById(2131297624);
            textView.setBackground(ContextCompat.getDrawable((Context)this, 2131231346));
            if (n2 <= 0) {
                textView.setVisibility(8);
            }
            else {
                textView.setVisibility(0);
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void getUnReadCount() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$Message.getMessageTypeAndMessageUnreadCount()), (RequestCallBack)new NewMainActivity$4(this));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492918);
        this.initState();
        this.initView();
        this.initData();
        this.checkVersionAvailable();
        if (ShareData.getShowPrivacyPolicyStatus()) {
            MANServiceConfig.init(this.getApplication(), AppConfig.RELEASE != Release.STANDARD);
        }
        MApplication.getInstance().startMsgMqtt();
    }
    
    protected void onDestroy() {
        super.onDestroy();
        DialogManager.getInstance().onDestory((Activity)this);
        final LocalBroadcastManager broadcastManager = this.broadcastManager;
        if (broadcastManager != null) {
            final BroadcastReceiver receiver = this.receiver;
            if (receiver != null) {
                broadcastManager.unregisterReceiver(receiver);
            }
        }
        final LocalBroadcastManager broadcastManager2 = this.broadcastManager;
        if (broadcastManager2 != null) {
            final HttpBroadcastReceiver httpBroadcastReceiver = this.httpBroadcastReceiver;
            if (httpBroadcastReceiver != null) {
                broadcastManager2.unregisterReceiver((BroadcastReceiver)httpBroadcastReceiver);
            }
        }
        this.closeFloatWindow();
        MApplication.getInstance().stopMsgMqtt();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.exit();
            return false;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        this.itemId = itemId;
        if (itemId != 2131297104 && itemId != 2131297102) {
            this.shadowView.setVisibility(0);
        }
        else {
            this.shadowView.setVisibility(8);
        }
        if (this.itemId == 2131297102) {
            this.toolbar.setVisibility(8);
            this.viewTop.setVisibility(8);
        }
        else {
            this.toolbar.setVisibility(0);
            this.viewTop.setVisibility(0);
        }
        if (this.itemId == 2131297105) {
            this.setGroupInfo();
        }
        else {
            this.setGroupHide();
        }
        this.onFragmentChange(this.itemId);
        return true;
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.checkOpenFile(intent);
        intent.getBooleanExtra("intent_key_checkup_data", false);
        if (intent.getBooleanExtra("intent_key_login_out", false)) {
            this.navView.getMenu().getItem(0).setChecked(true);
            this.onFragmentChange(2131297104);
            this.toolbarWork.setVisibility(8);
            this.toolbar.setVisibility(0);
            this.showLogoutDialog();
        }
        if (intent.getBooleanExtra("intent_key_login_re_login", false)) {
            if (NetworkUtil.isNetworkConnected((Context)this)) {
                this.startActivity(new Intent((Context)this, (Class)LoginMainActivity.class));
            }
            else {
                final androidx.appcompat.app.AlertDialog$Builder alertDialog$Builder = new androidx.appcompat.app.AlertDialog$Builder((Context)AppManager.getInstance().getLastActivity());
                alertDialog$Builder.setTitle(2131821524).setMessage((CharSequence)this.getString(2131821066)).setPositiveButton(2131820731, (DialogInterface$OnClickListener)_$$Lambda$NewMainActivity$Il7UnlB1Fk7PgltmQ4iZGlGl6oI.INSTANCE);
                final androidx.appcompat.app.AlertDialog create = alertDialog$Builder.create();
                create.setCanceledOnTouchOutside(false);
                create.setCancelable(false);
                create.show();
                create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
            }
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        MANServiceConfig.pageDisAppear((Activity)this);
    }
    
    public void onResume() {
        super.onResume();
        this.checkAppScore();
        if (!SPUtil.getBooleanDefault("FLOAT_PEDIA", false) && !SPUtil.getBooleanDefault("FLOAT_TEMPLATE", false) && !SPUtil.getBooleanDefault("FLOAT_WEB_STATION", false)) {
            this.setDialogDismiss();
            EasyFloat.dismiss();
        }
        MANServiceConfig.pageAppear((Activity)this);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (b && MApplication.getInstance().isLogin()) {
            try {
                this.startService(new Intent((Context)this, (Class)SynService.class));
            }
            catch (final IllegalStateException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void reStart(final Context context) {
        final Intent intent = new Intent(context, (Class)NewMainActivity.class);
        intent.setFlags(268468224);
        context.startActivity(intent);
        this.closeFloatWindow();
    }
    
    public void setGroupInfo() {
        if (this.itemId == 2131297105) {
            this.toolbarWork.setVisibility(0);
            if (MApplication.getInstance().isLogin()) {
                final GroupListDBIMpl groupListDBIMpl = new GroupListDBIMpl();
                this.groupId = ShareData.getGroupDefault();
                final List groupList = ((GroupListDBI)groupListDBIMpl).getGroupList();
                if (groupList != null && groupList.size() > 0) {
                    final GroupModel defaultGroup = ((GroupListDBI)groupListDBIMpl).getDefaultGroup(this.groupId);
                    if (defaultGroup != null) {
                        this.setWorkGroupToolBar(defaultGroup);
                    }
                    else {
                        final GroupModel workGroupToolBar = (GroupModel)groupList.get(0);
                        if (workGroupToolBar != null) {
                            ShareData.setGroupDefault(workGroupToolBar.getGroupID());
                            this.setWorkGroupToolBar(workGroupToolBar);
                        }
                        else {
                            this.setGroupHide();
                        }
                    }
                }
                else {
                    this.setGroupHide();
                }
            }
            else {
                this.setGroupHide();
            }
        }
    }
}
