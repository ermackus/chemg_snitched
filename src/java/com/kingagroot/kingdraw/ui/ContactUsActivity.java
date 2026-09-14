package com.kingagroot.kingdraw.ui;

import com.hjq.permissions.OnPermissionCallback;
import android.os.Build$VERSION;
import com.hjq.permissions.XXPermissions;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.view.View$OnClickListener;
import android.text.Html;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.view.View;
import android.graphics.BitmapFactory;
import android.content.DialogInterface;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.utils.DrawFileUtil;
import android.graphics.Bitmap$CompressFormat;
import java.util.List;
import android.graphics.Bitmap;
import android.content.Context;
import androidx.core.content.ContextCompat;
import android.widget.TextView;
import com.goodsrc.ui.library.ToolBarActivity;

public class ContactUsActivity extends ToolBarActivity
{
    String strBaiduInfo;
    String strQqInfo;
    String strTwitterInfo;
    String strWechatInfo;
    String strWeiboInfo;
    TextView tvBaiduInfo;
    TextView tvQqInfo;
    TextView tvSavePic;
    TextView tvTel;
    TextView tvWechatInfo;
    TextView tvWeiboInfo;
    
    private void initView() {
        final TextView textView = (TextView)this.findViewById(2131297710);
        final TextView textView2 = (TextView)this.findViewById(2131297708);
        final TextView textView3 = (TextView)this.findViewById(2131297674);
        final TextView textView4 = (TextView)this.findViewById(2131297649);
        final TextView textView5 = (TextView)this.findViewById(2131297631);
        final TextView textView6 = (TextView)this.findViewById(2131297678);
        textView.setBackground(ContextCompat.getDrawable((Context)this, 2131231211));
        textView2.setBackground(ContextCompat.getDrawable((Context)this, 2131231210));
        textView3.setBackground(ContextCompat.getDrawable((Context)this, 2131231208));
        textView4.setBackground(ContextCompat.getDrawable((Context)this, 2131231209));
        textView5.setBackground(ContextCompat.getDrawable((Context)this, 2131231209));
        textView6.setBackground(ContextCompat.getDrawable((Context)this, 2131231210));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setTitle(2131820734);
        this.setContentView(2131492900);
        this.tvWechatInfo = (TextView)this.findViewById(2131297706);
        this.tvWeiboInfo = (TextView)this.findViewById(2131297707);
        this.tvBaiduInfo = (TextView)this.findViewById(2131297539);
        this.tvQqInfo = (TextView)this.findViewById(2131297648);
        this.tvSavePic = (TextView)this.findViewById(2131297655);
        this.tvTel = (TextView)this.findViewById(2131297668);
        final TextView textView = (TextView)this.findViewById(2131297677);
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(2131296988);
        final LinearLayout linearLayout2 = (LinearLayout)this.findViewById(2131296996);
        this.strWechatInfo = this.getResources().getString(2131821526);
        this.strWeiboInfo = this.getResources().getString(2131821528);
        this.strBaiduInfo = this.getResources().getString(2131820621);
        this.strQqInfo = this.getResources().getString(2131821278);
        this.strTwitterInfo = this.getResources().getString(2131821475);
        this.tvWechatInfo.setText((CharSequence)Html.fromHtml(this.strWechatInfo));
        this.tvWeiboInfo.setText((CharSequence)Html.fromHtml(this.strWeiboInfo));
        this.tvBaiduInfo.setText((CharSequence)Html.fromHtml(this.strBaiduInfo));
        this.tvQqInfo.setText((CharSequence)Html.fromHtml(this.strQqInfo));
        textView.setText((CharSequence)Html.fromHtml(this.strTwitterInfo));
        this.tvSavePic.setOnClickListener((View$OnClickListener)new _$$Lambda$ContactUsActivity$vDbAxBZLZz5AZGVSXxRjv7X06uY(this));
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
        }
        else {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
        }
        this.initView();
    }
    
    public void saveImageToGallery(final Bitmap bitmap) {
        final XXPermissions with = XXPermissions.with((Context)this);
        if (Build$VERSION.SDK_INT >= 33) {
            with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        with.request((OnPermissionCallback)new _$$Lambda$ContactUsActivity$G9ndYLOnh616j6oEiLU43nMDmg4(bitmap));
    }
}
