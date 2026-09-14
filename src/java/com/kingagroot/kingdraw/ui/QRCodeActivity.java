package com.kingagroot.kingdraw.ui;

import android.content.Intent;
import android.text.TextUtils;
import com.goodsrc.library.utils.NetworkUtil;
import com.google.zxing.Result;
import com.kingagroot.kingdraw.base.MApplication;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;
import com.king.zxing.analyze.Analyzer;
import com.king.zxing.analyze.MultiFormatAnalyzer;
import com.king.zxing.DecodeFormatManager;
import com.king.zxing.DecodeConfig;
import androidx.appcompat.app.AlertDialog;
import android.graphics.Color;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import androidx.appcompat.app.AlertDialog$Builder;
import android.widget.ImageButton;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.king.zxing.CaptureActivity;

public class QRCodeActivity extends CaptureActivity
{
    public static final String TAG = "QRCodeActivity";
    private AccountUserModel accountUserModel;
    private ImageButton ibtBack;
    
    private void showErrDialog(final String message) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle(2131821281).setMessage((CharSequence)message).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$QRCodeActivity$IfvThxkHp4sfJKxYjWZ3_QGudd8(this));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        create.show();
        create.getButton(-1).setTextColor(Color.parseColor("#e13e3f"));
    }
    
    public int getLayoutId() {
        return 2131492927;
    }
    
    public void initCameraScan() {
        super.initCameraScan();
        final DecodeConfig decodeConfig = new DecodeConfig();
        decodeConfig.setHints(DecodeFormatManager.QR_CODE_HINTS).setFullAreaScan(false).setAreaRectRatio(0.8f).setAreaRectVerticalOffset(0).setAreaRectHorizontalOffset(0);
        this.getCameraScan().setVibrate(true).setNeedAutoZoom(true).setAnalyzer((Analyzer)new MultiFormatAnalyzer(decodeConfig));
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.ibtBack = (ImageButton)this.findViewById(2131296800)).setOnClickListener((View$OnClickListener)new _$$Lambda$QRCodeActivity$dJWT5lukQm_zxUcCw6PYYacE1xo(this));
        this.accountUserModel = MApplication.getInstance().getAccountUserModel();
    }
    
    public boolean onScanResultCallback(final Result result) {
        this.getCameraScan().setAnalyzeImage(false);
        final String valueByName = NetworkUtil.getValueByName(result.getText(), "to");
        final String valueByName2 = NetworkUtil.getValueByName(result.getText(), "uuid");
        if (!TextUtils.isEmpty((CharSequence)valueByName) && "vip".equals((Object)valueByName)) {
            if (valueByName2.equals((Object)this.accountUserModel.getUuid())) {
                this.startActivity(new Intent((Context)this, (Class)VipPayActivity.class));
                this.finish();
            }
            else {
                this.showErrDialog(this.getString(2131821279));
            }
        }
        else {
            this.showErrDialog(this.getString(2131821280));
        }
        return true;
    }
}
