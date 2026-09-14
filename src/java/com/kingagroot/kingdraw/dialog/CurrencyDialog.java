package com.kingagroot.kingdraw.dialog;

import android.os.Bundle;
import android.view.View;
import com.goodsrc.library.utils.KeyBoardUtils;
import android.text.TextUtils;
import android.text.InputFilter$LengthFilter;
import android.text.InputFilter;
import android.text.Html;
import android.widget.RadioGroup$OnCheckedChangeListener;
import android.view.View$OnClickListener;
import android.content.Context;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.RelativeLayout;
import android.widget.RadioGroup;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;

public class CurrencyDialog extends AlertDialog
{
    private final int MAX_LENGTH;
    int TYPE;
    private Button btnCancel;
    private Button btnDelete;
    private Button btnWordFormat;
    private String editnameStr;
    private EditText etEditName;
    String format;
    private LinearLayout llEdit;
    private String messageStr;
    private CurrencyDialog.CurrencyDialog$onNoOnclickListener noOnclickListener;
    private String noStr;
    private RadioGroup rgpFormat;
    private RelativeLayout rlFormat;
    TextWatcher textWatcher;
    private String titleStr;
    private TextView tvContext;
    private TextView tvEditNum;
    private TextView tvFormatExplain;
    private TextView tvTitle;
    private CurrencyDialog.CurrencyDialog$onYesOnclickListener yesOnclickListener;
    private String yesStr;
    
    public CurrencyDialog(final Context context, final int type) {
        super(context);
        this.format = this.getContext().getResources().getString(2131820820);
        this.MAX_LENGTH = 50;
        this.textWatcher = (TextWatcher)new CurrencyDialog$3(this);
        this.TYPE = type;
    }
    
    public CurrencyDialog(final Context context, final int n, final int type) {
        super(context, n);
        this.format = this.getContext().getResources().getString(2131820820);
        this.MAX_LENGTH = 50;
        this.textWatcher = (TextWatcher)new CurrencyDialog$3(this);
        this.TYPE = type;
    }
    
    private void init() {
        this.btnCancel = (Button)this.findViewById(2131296413);
        this.btnDelete = (Button)this.findViewById(2131296419);
        this.tvTitle = (TextView)this.findViewById(2131297675);
        this.tvContext = (TextView)this.findViewById(2131297551);
        this.llEdit = (LinearLayout)this.findViewById(2131296993);
        this.tvEditNum = (TextView)this.findViewById(2131297569);
        this.etEditName = (EditText)this.findViewById(2131296633);
        this.rlFormat = (RelativeLayout)this.findViewById(2131297291);
        this.rgpFormat = (RadioGroup)this.findViewById(2131297276);
        this.tvFormatExplain = (TextView)this.findViewById(2131297598);
        (this.btnWordFormat = (Button)this.findViewById(2131296465)).setOnClickListener((View$OnClickListener)new CurrencyDialog$1(this));
        this.rgpFormat.setOnCheckedChangeListener((RadioGroup$OnCheckedChangeListener)new CurrencyDialog$2(this));
        this.tvFormatExplain.setText((CharSequence)Html.fromHtml(this.format));
        this.etEditName.setFilters(new InputFilter[] { (InputFilter)new InputFilter$LengthFilter(50) });
        this.etEditName.addTextChangedListener(this.textWatcher);
        final TextView tvEditNum = this.tvEditNum;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.etEditName.getText().length());
        sb.append("/");
        sb.append(50);
        tvEditNum.setText((CharSequence)sb.toString());
        this.setV(this.TYPE);
        this.getWindow().clearFlags(131072);
    }
    
    private void initData() {
        final String titleStr = this.titleStr;
        if (titleStr != null) {
            this.tvTitle.setText((CharSequence)titleStr);
        }
        final String messageStr = this.messageStr;
        if (messageStr != null) {
            this.tvContext.setText((CharSequence)messageStr);
        }
        final String yesStr = this.yesStr;
        if (yesStr != null) {
            this.btnDelete.setText((CharSequence)yesStr);
        }
        final String noStr = this.noStr;
        if (noStr != null) {
            this.btnCancel.setText((CharSequence)noStr);
        }
        final String editnameStr = this.editnameStr;
        if (editnameStr != null) {
            this.etEditName.setText((CharSequence)editnameStr);
            if (!TextUtils.isEmpty((CharSequence)this.editnameStr)) {
                this.etEditName.setSelection(this.etEditName.getText().toString().length());
            }
        }
    }
    
    private void initEvent() {
        this.btnDelete.setOnClickListener((View$OnClickListener)new CurrencyDialog$4(this));
        this.btnCancel.setOnClickListener((View$OnClickListener)new CurrencyDialog$5(this));
    }
    
    public void dismiss() {
        KeyBoardUtils.hidInput((View)this.etEditName);
        super.dismiss();
    }
    
    public String getString() {
        return this.editnameStr = this.etEditName.getText().toString();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131493019);
        this.setCanceledOnTouchOutside(false);
        this.init();
        this.initData();
        this.initEvent();
    }
    
    public void setMessage(final String messageStr) {
        this.messageStr = messageStr;
    }
    
    public void setName(final String editnameStr) {
        this.editnameStr = editnameStr;
    }
    
    public void setNoOnclickListener(final String noStr, final CurrencyDialog.CurrencyDialog$onNoOnclickListener noOnclickListener) {
        if (noStr != null) {
            this.noStr = noStr;
        }
        this.noOnclickListener = noOnclickListener;
    }
    
    public void setTitle(final String titleStr) {
        super.setTitle((CharSequence)null);
        this.titleStr = titleStr;
    }
    
    public void setV(int type) {
        type = this.TYPE;
        if (type != 0) {
            if (type != 1) {
                if (type == 2) {
                    this.rlFormat.setVisibility(0);
                    this.tvContext.setVisibility(8);
                    this.llEdit.setVisibility(0);
                }
            }
            else {
                this.rlFormat.setVisibility(8);
                this.tvContext.setVisibility(8);
                this.llEdit.setVisibility(0);
            }
        }
        else {
            this.tvContext.setVisibility(0);
            this.llEdit.setVisibility(8);
            this.rlFormat.setVisibility(8);
        }
    }
    
    public void setYesOnclickListener(final String yesStr, final CurrencyDialog.CurrencyDialog$onYesOnclickListener yesOnclickListener) {
        if (yesStr != null) {
            this.yesStr = yesStr;
        }
        this.yesOnclickListener = yesOnclickListener;
    }
    
    public void show() {
        super.show();
        KeyBoardUtils.showInput((View)this.etEditName);
    }
}
