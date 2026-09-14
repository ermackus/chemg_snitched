package com.kingagroot.kingdraw.ui;

import android.os.Bundle;
import android.net.Uri;
import com.goodsrc.library.utils.ToastUtil;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.account.AccountWebActivity;
import android.view.View;
import com.kingagroot.kingdraw.utils.link.UserLogoutLink$OnUserLogOutListener;
import com.kingagroot.kingdraw.utils.link.UserLogoutLink;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.content.Context;
import com.goodsrc.library.utils.LanguageSPUtil;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.kingdraw.NewMainActivity;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class AppSettingActivity extends ToolBarActivity implements View$OnClickListener
{
    private Button btnLogout;
    boolean isLogin;
    private RelativeLayout rlSettingAccount;
    private RelativeLayout rlSettingGateway;
    private RelativeLayout rlSettingLaboratory;
    private RelativeLayout rlSettingLanguage;
    private RelativeLayout rlSettingSub;
    private TextView tvSettingLanguage;
    
    private void init() {
        this.rlSettingAccount = (RelativeLayout)this.findViewById(2131297313);
        this.rlSettingLanguage = (RelativeLayout)this.findViewById(2131297316);
        this.rlSettingLaboratory = (RelativeLayout)this.findViewById(2131297315);
        this.tvSettingLanguage = (TextView)this.findViewById(2131297658);
        this.rlSettingGateway = (RelativeLayout)this.findViewById(2131297314);
        this.rlSettingSub = (RelativeLayout)this.findViewById(2131297318);
        this.btnLogout = (Button)this.findViewById(2131296437);
        this.rlSettingAccount.setOnClickListener((View$OnClickListener)this);
        this.rlSettingLanguage.setOnClickListener((View$OnClickListener)this);
        this.rlSettingLaboratory.setOnClickListener((View$OnClickListener)this);
        this.rlSettingGateway.setOnClickListener((View$OnClickListener)this);
        this.rlSettingSub.setOnClickListener((View$OnClickListener)this);
        this.btnLogout.setOnClickListener((View$OnClickListener)this);
        this.setText();
        this.setSubState();
    }
    
    private void logout() {
        MApplication.getInstance().userLogout();
        this.btnLogout.setVisibility(8);
        this.isLogin = false;
        NewMainActivity.getMainActivity().onResume();
    }
    
    private void setSubState() {
        if (MApplication.getInstance().isLogin()) {
            final AccountUserModel accountUserModel = MApplication.getInstance().getAccountUserModel();
            if (accountUserModel != null) {
                if (accountUserModel.getVipState() == 1) {
                    this.rlSettingSub.setVisibility(0);
                }
                else {
                    this.rlSettingSub.setVisibility(8);
                }
            }
            else {
                this.rlSettingSub.setVisibility(8);
            }
        }
        else {
            this.rlSettingSub.setVisibility(8);
        }
    }
    
    private void setText() {
        final int selectLanguage = LanguageSPUtil.getInstance((Context)this).getSelectLanguage();
        String text;
        if (selectLanguage != 1) {
            if (selectLanguage != 2) {
                text = this.getResources().getString(2131820874);
            }
            else {
                text = this.getResources().getString(2131820798);
            }
        }
        else {
            text = this.getResources().getString(2131820686);
        }
        this.tvSettingLanguage.setText((CharSequence)text);
    }
    
    private void showDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)this.getString(2131820602));
        alertDialog$Builder.setPositiveButton((CharSequence)this.getString(2131820588), (DialogInterface$OnClickListener)new _$$Lambda$AppSettingActivity$Zg4ZqYJsgZeEWHfvuhleyqWC4po(this));
        alertDialog$Builder.setNegativeButton((CharSequence)this.getString(2131820661), (DialogInterface$OnClickListener)_$$Lambda$AppSettingActivity$FyL_NhYxaMaOnUyodMGIm4x13WM.INSTANCE);
        alertDialog$Builder.show();
    }
    
    private void userLogOut() {
        new UserLogoutLink((UserLogoutLink$OnUserLogOutListener)new _$$Lambda$AppSettingActivity$diquW60AsPPfXPkb4xhWSvPmwnI(this)).userLogOut();
    }
    
    public void onClick(final View view) {
        if (view == this.rlSettingAccount) {
            if (this.isLogin) {
                this.startActivity(new Intent((Context)this, (Class)AccountWebActivity.class));
            }
            else {
                ToastUtil.showShort((CharSequence)this.getString(2131821089));
            }
        }
        else if (view == this.rlSettingLanguage) {
            this.startActivity(new Intent((Context)this, (Class)LanguageActivity.class));
        }
        else if (view == this.rlSettingLaboratory) {
            this.startActivity(new Intent((Context)this, (Class)LabActivity.class));
        }
        else if (view == this.btnLogout) {
            this.showDialog();
        }
        else if (view == this.rlSettingGateway) {
            this.startActivity(new Intent((Context)this, (Class)GatewaySettingActivity.class));
        }
        else if (view == this.rlSettingSub) {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/account/subscriptions"));
            this.startActivity(intent);
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493035);
        this.setTitle(2131821365);
        this.init();
    }
    
    public void onResume() {
        super.onResume();
        if (MApplication.getInstance().getAccountUserModel() != null) {
            this.btnLogout.setVisibility(0);
            this.isLogin = true;
        }
        else {
            this.btnLogout.setVisibility(8);
            this.isLogin = false;
        }
    }
}
