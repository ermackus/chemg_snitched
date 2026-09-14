package com.kingagroot.kingdraw.ui;

import android.view.MenuItem;
import android.view.Menu;
import android.content.Context;
import com.goodsrc.library.utils.LanguageSPUtil;
import android.widget.RadioButton;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.goodsrc.ui.library.BaseActivity;
import com.kingagroot.kingdraw.utils.link.UserInfoLink$OnUserInfoListener;
import com.kingagroot.kingdraw.utils.link.UserInfoLink;
import android.text.TextUtils;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.kingdraw.utils.link.CountryLink$OnCountryFinishLister;
import com.kingagroot.kingdraw.utils.link.CountryLink;
import com.kingagroot.component.ui.widget.LoadingDialog;
import android.widget.RadioGroup$OnCheckedChangeListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class LanguageActivity extends ToolBarActivity implements RadioGroup$OnCheckedChangeListener
{
    int locale;
    LanguageActivity.LanguageActivity$TimeCount time;
    LoadingDialog waitingDialog;
    
    public LanguageActivity() {
        this.time = new LanguageActivity.LanguageActivity$TimeCount(this, 1000L, 1000L);
    }
    
    private void getCountryList() {
        new CountryLink((CountryLink$OnCountryFinishLister)_$$Lambda$LanguageActivity$l_IwZ1MeXmdFge_5ez1zFMKnv2w.INSTANCE).getCountryList();
    }
    
    private void getUserInfo() {
        if (!TextUtils.isEmpty((CharSequence)MApplication.getUserToken()) && !"-".equals((Object)MApplication.getUserToken())) {
            new UserInfoLink((UserInfoLink$OnUserInfoListener)new LanguageActivity$1(this)).getUserInfo(false);
        }
    }
    
    private void showWaitingDialog() {
        (this.waitingDialog = new LoadingDialog((BaseActivity)this)).setTextMessage(this.getString(2131820964));
        this.waitingDialog.setCancelable(false);
        this.waitingDialog.show();
    }
    
    public void onCheckedChanged(final RadioGroup radioGroup, final int n) {
        if (n != 2131297237) {
            if (n != 2131297241) {
                this.locale = 0;
            }
            else {
                this.locale = 2;
            }
        }
        else {
            this.locale = 1;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820965);
        this.setContentView(2131492917);
        final RadioGroup radioGroup = (RadioGroup)this.findViewById(2131297277);
        final RadioButton radioButton = (RadioButton)this.findViewById(2131297233);
        final RadioButton radioButton2 = (RadioButton)this.findViewById(2131297237);
        final RadioButton radioButton3 = (RadioButton)this.findViewById(2131297241);
        final int selectLanguage = LanguageSPUtil.getInstance((Context)this).getSelectLanguage();
        this.locale = selectLanguage;
        if (selectLanguage != 1) {
            if (selectLanguage != 2) {
                radioButton.setChecked(true);
            }
            else {
                radioButton3.setChecked(true);
            }
        }
        else {
            radioButton2.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)this);
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, (CharSequence)this.getString(2131820866)).setShowAsAction(2);
        return true;
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.time.cancel();
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            this.time.start();
            this.showWaitingDialog();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
