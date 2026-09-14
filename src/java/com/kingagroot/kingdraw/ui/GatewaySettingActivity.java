package com.kingagroot.kingdraw.ui;

import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.text.SpannableString;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import com.kingagroot.kingdraw.config.ShareData;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.component.ui.utils.AccountUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class GatewaySettingActivity extends ToolBarActivity implements View$OnClickListener
{
    public static final String TAG = "GatewaySettingActivity";
    private AppCompatButton btnGatewayTest;
    private EditText etGatewayIp;
    private EditText etGatewayPort;
    private String ip;
    private LinearLayout llGatewayInfo;
    private Menu menuSave;
    private String port;
    private SwitchCompat switchGateway;
    private TextView tvGatewayErr;
    
    private void checkMenu() {
        final Menu menuSave = this.menuSave;
        if (menuSave != null) {
            final boolean b = false;
            final MenuItem item = menuSave.getItem(0);
            boolean visible = b;
            if (this.switchGateway.isChecked()) {
                visible = b;
                if (this.getGatewayInfo()) {
                    visible = true;
                }
            }
            item.setVisible(visible);
        }
    }
    
    private boolean getGatewayInfo() {
        this.ip = this.etGatewayIp.getText().toString();
        this.port = this.etGatewayPort.getText().toString();
        if (TextUtils.isEmpty((CharSequence)this.ip) || !AccountUtils.isIpAddress(this.ip)) {
            ToastUtil.showShort((CharSequence)"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u5730\u5740");
            return false;
        }
        if (!TextUtils.isEmpty((CharSequence)this.port) && AccountUtils.isNetPort(Integer.parseInt(this.port))) {
            return true;
        }
        ToastUtil.showShort((CharSequence)"\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7aef\u53e3");
        return false;
    }
    
    private void init() {
        this.switchGateway = (SwitchCompat)this.findViewById(2131297442);
        this.llGatewayInfo = (LinearLayout)this.findViewById(2131297002);
        this.etGatewayIp = (EditText)this.findViewById(2131296636);
        this.etGatewayPort = (EditText)this.findViewById(2131296637);
        this.tvGatewayErr = (TextView)this.findViewById(2131297604);
        this.btnGatewayTest = (AppCompatButton)this.findViewById(2131296430);
        this.switchGateway.setChecked(ShareData.getGatewayState());
        if (ShareData.getGatewayState()) {
            this.llGatewayInfo.setVisibility(0);
        }
        else {
            this.llGatewayInfo.setVisibility(8);
        }
        this.switchGateway.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new _$$Lambda$GatewaySettingActivity$UgsR3weZbVshFiuUKm3BW1ygcFg(this));
        this.btnGatewayTest.setOnClickListener((View$OnClickListener)this);
    }
    
    private void saveGatewayData() {
        ShareData.setGatewayIp(this.ip);
        ShareData.setGatewayPort(this.port);
    }
    
    public void onClick(final View view) {
        if (view == this.btnGatewayTest && this.getGatewayInfo()) {
            if (AccountUtils.checkIpPort(this.ip, Integer.parseInt(this.port))) {
                this.tvGatewayErr.setVisibility(0);
                this.tvGatewayErr.setText((CharSequence)this.getString(2131820762));
            }
            else {
                this.tvGatewayErr.setVisibility(0);
                final TextView tvGatewayErr = this.tvGatewayErr;
                final String string = this.getString(2131820891);
                final StringBuilder sb = new StringBuilder();
                sb.append(this.ip);
                sb.append(":");
                sb.append(this.port);
                tvGatewayErr.setText((CharSequence)String.format(string, new Object[] { sb.toString() }));
            }
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492908);
        this.init();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuItem add = menu.add(0, 0, 0, (CharSequence)this.getString(2131821338));
        final SpannableString title = new SpannableString(add.getTitle());
        title.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#E13E3F")), 0, title.length(), 0);
        add.setTitle((CharSequence)title);
        add.setShowAsAction(2);
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 0) {
            if (AccountUtils.checkIpPort(this.ip, Integer.parseInt(this.port))) {
                this.tvGatewayErr.setVisibility(8);
                this.saveGatewayData();
            }
            else {
                this.tvGatewayErr.setVisibility(0);
                final TextView tvGatewayErr = this.tvGatewayErr;
                final String string = this.getString(2131820891);
                final StringBuilder sb = new StringBuilder();
                sb.append(this.ip);
                sb.append(":");
                sb.append(this.port);
                tvGatewayErr.setText((CharSequence)String.format(string, new Object[] { sb.toString() }));
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public boolean onPrepareOptionsMenu(final Menu menuSave) {
        this.menuSave = menuSave;
        this.checkMenu();
        return super.onPrepareOptionsMenu(menuSave);
    }
}
