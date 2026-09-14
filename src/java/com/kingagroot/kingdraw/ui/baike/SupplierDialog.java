package com.kingagroot.kingdraw.ui.baike;

import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.text.style.ForegroundColorSpan;
import android.text.SpannableString;
import android.view.View;
import android.graphics.Color;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import com.goodsrc.library.utils.GsonUtil;
import android.content.Context;
import android.widget.TextView;
import com.kingagroot.kingdraw.model.SupplierModel;
import android.widget.LinearLayout;
import com.kingagroot.kingdraw.ui.baike.view.FlowLayout;
import java.util.ArrayList;
import android.app.Dialog;

public class SupplierDialog extends Dialog
{
    public static final int TYPE_QQ = 2;
    public static final int TYPE_TEL = 1;
    public static final int TYPE_WEBSITE = 0;
    private OnTextClickLister clickLister;
    private ArrayList<String> fax;
    private FlowLayout flFax;
    private FlowLayout flPrice;
    private FlowLayout flQq;
    private FlowLayout flRemark;
    private FlowLayout flTel;
    private LinearLayout llAbroad;
    private LinearLayout llDomestic;
    private LinearLayout llPrice;
    private ArrayList<String> price;
    private ArrayList<String> qq;
    private ArrayList<String> remark;
    private final SupplierModel supplierModel;
    private ArrayList<String> tel;
    private TextView tvBrand;
    private TextView tvCountry;
    private TextView tvEmail;
    private TextView tvHintPrice;
    private TextView tvPageInfo;
    private TextView tvProCode;
    private TextView tvPurity;
    private TextView tvTitle;
    private TextView tvWebsite;
    
    public SupplierDialog(final Context context, final String s) {
        super(context);
        this.supplierModel = (SupplierModel)GsonUtil.fromJson(s, (Class)SupplierModel.class);
    }
    
    private void addFaxTextView(final String text) {
        final TextView textView = new TextView(this.getContext());
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -2);
        layoutParams.setMargins(this.getContext().getResources().getDimensionPixelSize(2131165268), 5, 8, 5);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        textView.setText((CharSequence)text);
        textView.setTextColor(Color.parseColor("#FF666666"));
        textView.setTextSize(16.0f);
        this.flFax.addView((View)textView);
    }
    
    private void addPriceTextView(final String s) {
        final TextView textView = new TextView(this.getContext());
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -2);
        layoutParams.setMargins(this.getContext().getResources().getDimensionPixelSize(2131165268), 5, 8, 5);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        textView.setText((CharSequence)s);
        if ("-".equals((Object)s)) {
            textView.setTextColor(Color.parseColor("#FF666666"));
        }
        else if (s.endsWith("|")) {
            final SpannableString text = new SpannableString((CharSequence)s);
            text.setSpan((Object)new ForegroundColorSpan(Color.parseColor("#FFFF2727")), 0, text.length() - 1, 33);
            textView.setText((CharSequence)text);
        }
        else {
            textView.setText((CharSequence)s);
            textView.setTextColor(Color.parseColor("#FFFF2727"));
        }
        textView.setTextSize(16.0f);
        this.flPrice.addView((View)textView);
    }
    
    private void addQqTextView(final String text) {
        final TextView textView = new TextView(this.getContext());
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -2);
        layoutParams.setMargins(this.getContext().getResources().getDimensionPixelSize(2131165268), 5, 8, 5);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        textView.setText((CharSequence)text);
        textView.setTextIsSelectable(true);
        if ("-".equals((Object)text)) {
            textView.setTextColor(Color.parseColor("#FF666666"));
        }
        else {
            textView.setTextColor(Color.parseColor("#FF0097FF"));
            this.initEvents(textView, 2);
        }
        textView.setTextSize(16.0f);
        this.flQq.addView((View)textView);
    }
    
    private void addRemarkTextView(final String text) {
        final TextView textView = new TextView(this.getContext());
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -2);
        layoutParams.setMargins(this.getContext().getResources().getDimensionPixelSize(2131165268), 5, 8, 5);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        textView.setText((CharSequence)text);
        textView.setTextColor(Color.parseColor("#FF666666"));
        textView.setTextSize(16.0f);
        this.flRemark.addView((View)textView);
    }
    
    private void addTelTextView(final String text) {
        final TextView textView = new TextView(this.getContext());
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -2);
        layoutParams.setMargins(this.getContext().getResources().getDimensionPixelSize(2131165268), 5, 8, 5);
        textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        textView.setText((CharSequence)text);
        textView.setTextIsSelectable(true);
        if ("-".equals((Object)text)) {
            textView.setTextColor(Color.parseColor("#FF666666"));
        }
        else {
            textView.setTextColor(Color.parseColor("#FF0097FF"));
            this.initEvents(textView, 1);
        }
        textView.setTextSize(16.0f);
        this.flTel.addView((View)textView);
    }
    
    private void initEvents(final TextView textView, final int n) {
        textView.setFocusableInTouchMode(false);
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener(this, textView, n) {
            final SupplierDialog this$0;
            final TextView val$tv;
            final int val$type;
            
            public void onClick(final View view) {
                final String string = this.val$tv.getText().toString();
                if (this.this$0.clickLister != null && !TextUtils.isEmpty((CharSequence)string)) {
                    this.this$0.clickLister.onClick(string, this.val$type);
                }
            }
        });
    }
    
    private void initView() {
        this.tvTitle = (TextView)this.findViewById(2131297675);
        final ImageButton imageButton = (ImageButton)this.findViewById(2131296803);
        this.tvWebsite = (TextView)this.findViewById(2131297704);
        this.flTel = (FlowLayout)this.findViewById(2131296705);
        this.flFax = (FlowLayout)this.findViewById(2131296691);
        this.flQq = (FlowLayout)this.findViewById(2131296699);
        this.flRemark = (FlowLayout)this.findViewById(2131296701);
        this.flPrice = (FlowLayout)this.findViewById(2131296698);
        this.tvPurity = (TextView)this.findViewById(2131297642);
        this.tvPageInfo = (TextView)this.findViewById(2131297633);
        this.tvBrand = (TextView)this.findViewById(2131297541);
        this.tvProCode = (TextView)this.findViewById(2131297640);
        this.tvCountry = (TextView)this.findViewById(2131297555);
        this.tvEmail = (TextView)this.findViewById(2131297573);
        this.llDomestic = (LinearLayout)this.findViewById(2131296992);
        this.llAbroad = (LinearLayout)this.findViewById(2131296982);
        this.llPrice = (LinearLayout)this.findViewById(2131297021);
        this.tvHintPrice = (TextView)this.findViewById(2131297610);
        imageButton.setOnClickListener((View$OnClickListener)new View$OnClickListener(this) {
            final SupplierDialog this$0;
            
            public void onClick(final View view) {
                this.this$0.dismiss();
            }
        });
        this.setData();
    }
    
    private void setData() {
        this.tvTitle.setText((CharSequence)this.supplierModel.getName());
        if (this.supplierModel.getType() == 1) {
            this.llDomestic.setVisibility(0);
            this.llPrice.setVisibility(0);
            this.llAbroad.setVisibility(8);
        }
        else {
            this.llDomestic.setVisibility(8);
            this.llPrice.setVisibility(8);
            this.llAbroad.setVisibility(0);
        }
        if (this.supplierModel.getPage() != null && !this.supplierModel.getPage().isEmpty()) {
            this.tvWebsite.setText((CharSequence)this.supplierModel.getPage());
            this.tvWebsite.setTextColor(Color.parseColor("#FF0097FF"));
            this.initEvents(this.tvWebsite, 0);
        }
        else {
            this.tvWebsite.setText((CharSequence)"-");
            this.tvWebsite.setTextColor(Color.parseColor("#FF666666"));
        }
        if (this.supplierModel.getPurity() != null && !this.supplierModel.getPurity().isEmpty()) {
            this.tvPurity.setText((CharSequence)this.supplierModel.getPurity());
        }
        else {
            this.tvPurity.setText((CharSequence)"-");
        }
        if (this.supplierModel.getPackingInformation() != null && !this.supplierModel.getPackingInformation().isEmpty()) {
            this.tvPageInfo.setText((CharSequence)this.supplierModel.getPackingInformation());
        }
        else {
            this.tvPageInfo.setText((CharSequence)"-");
        }
        if (this.supplierModel.getBrand() != null && !this.supplierModel.getBrand().isEmpty()) {
            this.tvBrand.setText((CharSequence)this.supplierModel.getBrand());
        }
        else {
            this.tvBrand.setText((CharSequence)"-");
        }
        if (this.supplierModel.getProductCode() != null && !this.supplierModel.getProductCode().isEmpty()) {
            this.tvProCode.setText((CharSequence)this.supplierModel.getProductCode());
        }
        else {
            this.tvProCode.setText((CharSequence)"-");
        }
        if (this.supplierModel.getCountry() != null && !this.supplierModel.getCountry().isEmpty()) {
            this.tvCountry.setText((CharSequence)this.supplierModel.getCountry());
        }
        else {
            this.tvCountry.setText((CharSequence)"-");
        }
        if (this.supplierModel.getEmail() != null && !this.supplierModel.getEmail().isEmpty()) {
            this.tvEmail.setText((CharSequence)this.supplierModel.getEmail());
        }
        else {
            this.tvEmail.setText((CharSequence)"-");
        }
    }
    
    private void setFaxDatas(final SupplierModel supplierModel) {
        this.fax = (ArrayList<String>)new ArrayList();
        final int n = 0;
        for (int i = 0; i < supplierModel.getFax().length; ++i) {
            this.fax.add((Object)supplierModel.getFax()[i]);
        }
        if (this.fax.size() >= 1) {
            int j = n;
            if (!TextUtils.isEmpty((CharSequence)this.fax.get(0))) {
                while (j < this.fax.size()) {
                    this.addFaxTextView((String)this.fax.get(j));
                    ++j;
                }
                return;
            }
        }
        this.addFaxTextView("-");
    }
    
    private void setPriceDatas(final SupplierModel supplierModel) {
        this.price = (ArrayList<String>)new ArrayList();
        final int n = 0;
        for (int i = 0; i < supplierModel.getPrice().length; ++i) {
            this.price.add((Object)supplierModel.getPrice()[i]);
        }
        if (this.price.size() < 1) {
            this.addPriceTextView("-");
            this.tvHintPrice.setVisibility(8);
        }
        else {
            this.tvHintPrice.setVisibility(0);
            for (int j = n; j < this.price.size(); ++j) {
                if (j == this.price.size() - 1) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("¥");
                    sb.append((String)this.price.get(j));
                    this.addPriceTextView(sb.toString());
                }
                else {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("¥");
                    sb2.append((String)this.price.get(j));
                    sb2.append(" |");
                    this.addPriceTextView(sb2.toString());
                }
            }
        }
    }
    
    private void setQqDatas(final SupplierModel supplierModel) {
        this.qq = (ArrayList<String>)new ArrayList();
        final int n = 0;
        for (int i = 0; i < supplierModel.getQq().length; ++i) {
            this.qq.add((Object)supplierModel.getQq()[i]);
        }
        if (this.qq.size() >= 1) {
            int j = n;
            if (!TextUtils.isEmpty((CharSequence)this.qq.get(0))) {
                while (j < this.qq.size()) {
                    this.addQqTextView((String)this.qq.get(j));
                    ++j;
                }
                return;
            }
        }
        this.addQqTextView("-");
    }
    
    private void setReMarkDatas(final SupplierModel supplierModel) {
        this.remark = (ArrayList<String>)new ArrayList();
        if (supplierModel.getRemark() != null && !TextUtils.isEmpty((CharSequence)supplierModel.getRemark())) {
            this.remark.add((Object)supplierModel.getRemark());
        }
        if (this.remark.size() < 1) {
            this.addRemarkTextView("-");
        }
        else {
            for (int i = 0; i < this.remark.size(); ++i) {
                this.addRemarkTextView((String)this.remark.get(i));
            }
        }
    }
    
    private void setTelDatas(final SupplierModel supplierModel) {
        this.tel = (ArrayList<String>)new ArrayList();
        final int n = 0;
        for (int i = 0; i < supplierModel.getTel().length; ++i) {
            this.tel.add((Object)supplierModel.getTel()[i]);
        }
        if (this.tel.size() >= 1) {
            int j = n;
            if (!TextUtils.isEmpty((CharSequence)this.tel.get(0))) {
                while (j < this.tel.size()) {
                    this.addTelTextView((String)this.tel.get(j));
                    ++j;
                }
                return;
            }
        }
        this.addTelTextView("-");
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.setContentView(2131493028);
        this.setCanceledOnTouchOutside(false);
        this.initView();
        this.setTelDatas(this.supplierModel);
        this.setFaxDatas(this.supplierModel);
        this.setQqDatas(this.supplierModel);
        this.setReMarkDatas(this.supplierModel);
        this.setPriceDatas(this.supplierModel);
    }
    
    public void setTextClick(final OnTextClickLister clickLister) {
        this.clickLister = clickLister;
    }
    
    public void show() {
        super.show();
        final int width = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay().getWidth();
        final WindowManager$LayoutParams attributes = this.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = (int)(width * 0.6);
        attributes.height = -2;
        this.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        this.getWindow().setAttributes(attributes);
    }
    
    public interface OnTextClickLister
    {
        void onClick(final String p0, final int p1);
    }
}
