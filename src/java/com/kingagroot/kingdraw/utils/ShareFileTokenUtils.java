package com.kingagroot.kingdraw.utils;

import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import android.content.DialogInterface;
import com.goodsrc.library.http.HttpManager;
import com.goodsrc.library.http.HttpManager$Builder;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.kingagroot.kingdraw.config.FileConfig;
import com.goodsrc.ui.library.BaseActivity;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import androidx.appcompat.app.AlertDialog$Builder;
import java.io.IOException;
import com.google.gson.Gson;
import com.kingagroot.kingdraw.http.NewNetBean;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import com.kingagroot.component.ui.widget.LoadingDialog;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnWindowFocusChangeListener;
import android.os.Build$VERSION;
import android.app.Activity;
import com.goodsrc.library.utils.AES;
import android.text.TextUtils;
import android.net.Uri;

public class ShareFileTokenUtils
{
    private static final String AES_KEY = "6A7D2E7087FA47278B7CBD87E47B82A0";
    public static final String CREATETIME_KEY = "CreateTime";
    public static final String FILEEXTENSION_KEY = "fileExtension";
    public static final String FILENAME_KEY = "fileName";
    public static final String FILEVER_KEY = "Ver";
    public static final String KINGDRAWID_KEY = "kingdrawId";
    private static String SHARE_STARTN = "/ClientService/WebContent/Share";
    private static String SHARE_STARTS = "/Service/Content/Share";
    private static boolean isHasFocus;
    
    public static VerifyModel GetShareUrlExtTokenParam(String queryParameter) {
        queryParameter = Uri.parse(dataFormat(queryParameter)).getQueryParameter("extToken");
        if (!TextUtils.isEmpty((CharSequence)queryParameter)) {
            return verifyParameter(AES.decrypt("6A7D2E7087FA47278B7CBD87E47B82A0", queryParameter));
        }
        return new VerifyModel();
    }
    
    public static void check(final Activity activity) {
        if (Build$VERSION.SDK_INT > 28) {
            final View decorView = activity.getWindow().getDecorView();
            final boolean b = false;
            ShareFileTokenUtils.isHasFocus = false;
            int n = b ? 1 : 0;
            if (decorView != null) {
                final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                n = (b ? 1 : 0);
                if (viewTreeObserver != null) {
                    n = 1;
                    viewTreeObserver.addOnWindowFocusChangeListener((ViewTreeObserver$OnWindowFocusChangeListener)new ViewTreeObserver$OnWindowFocusChangeListener(viewTreeObserver, activity) {
                        final Activity val$activity;
                        final ViewTreeObserver val$viewTreeObserver;
                        
                        public void onWindowFocusChanged(final boolean b) {
                            if (b) {
                                if (this.val$viewTreeObserver.isAlive()) {
                                    this.val$viewTreeObserver.removeOnWindowFocusChangeListener((ViewTreeObserver$OnWindowFocusChangeListener)this);
                                }
                                if (!ShareFileTokenUtils.isHasFocus) {
                                    ShareFileTokenUtils.isHasFocus = true;
                                    checkClipboardData(this.val$activity);
                                }
                            }
                        }
                    });
                }
            }
            if (n == 0) {
                new Handler().postDelayed((Runnable)new _$$Lambda$ShareFileTokenUtils$nI1a83rEzGdEha_9TMldanCEqOc(activity), 500L);
            }
        }
        else {
            checkClipboardData(activity);
        }
    }
    
    private static void checkClipboardData(final Activity activity) {
        try {
            final ClipData primaryClip = ((ClipboardManager)activity.getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip != null) {
                final CharSequence label = primaryClip.getDescription().getLabel();
                if (label != null && label.toString().equals((Object)"KINGDRAW_SHARE_LINK")) {
                    return;
                }
                VerifyModel verifyToken = null;
                Block_6: {
                    for (int i = 0; i < primaryClip.getItemCount(); ++i) {
                        verifyToken = verifyToken(String.valueOf((Object)primaryClip.getItemAt(i).getText()));
                        if (verifyToken.isCheck) {
                            break Block_6;
                        }
                    }
                    return;
                }
                clearClipboard();
                new ShareFileDownLoad(activity, verifyToken).showConfirmDialog();
            }
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void clearClipboard() {
        ((ClipboardManager)MApplication.getInstance().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)"", (CharSequence)""));
    }
    
    private static String dataFormat(final String s) {
        return s.replace((CharSequence)"+", (CharSequence)"%2b");
    }
    
    private static String getValue(final String s) {
        final String[] split = s.split("=");
        if (split != null && split.length > 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(split[0]);
            sb.append("=");
            return s.replace((CharSequence)sb.toString(), (CharSequence)"");
        }
        return "";
    }
    
    public static VerifyModel verifyParameter(String s) {
        final VerifyModel verifyModel = new VerifyModel();
        final String[] split = s.split("&");
        String s2 = "";
        String fileOssid;
        String fileextension;
        String creatTime;
        String fileName;
        String ver;
        if (split != null) {
            final int length = split.length;
            String s3 = "";
            String s4 = "";
            String value;
            s = (value = s4);
            int n = 0;
            while (true) {
                fileOssid = s2;
                fileextension = s3;
                creatTime = s4;
                fileName = s;
                ver = value;
                if (n >= length) {
                    break;
                }
                final String s5 = split[n];
                String value2;
                String value3;
                String value4;
                String value5;
                if (s5.contains((CharSequence)"kingdrawId=")) {
                    value2 = getValue(s5);
                    value3 = s3;
                    value4 = s4;
                    value5 = s;
                }
                else if (s5.contains((CharSequence)"fileExtension=")) {
                    value3 = getValue(s5);
                    value2 = s2;
                    value4 = s4;
                    value5 = s;
                }
                else if (s5.contains((CharSequence)"fileName=")) {
                    value5 = getValue(s5);
                    value2 = s2;
                    value3 = s3;
                    value4 = s4;
                }
                else if (s5.contains((CharSequence)"CreateTime=")) {
                    value4 = getValue(s5);
                    value2 = s2;
                    value3 = s3;
                    value5 = s;
                }
                else {
                    value2 = s2;
                    value3 = s3;
                    value4 = s4;
                    value5 = s;
                    if (s5.contains((CharSequence)"Ver=")) {
                        value = getValue(s5);
                        value5 = s;
                        value4 = s4;
                        value3 = s3;
                        value2 = s2;
                    }
                }
                ++n;
                s2 = value2;
                s3 = value3;
                s4 = value4;
                s = value5;
            }
        }
        else {
            fileextension = "";
            creatTime = "";
            s = (fileName = (ver = creatTime));
            fileOssid = s2;
        }
        if (!TextUtils.isEmpty((CharSequence)fileOssid) && !TextUtils.isEmpty((CharSequence)fileextension) && !TextUtils.isEmpty((CharSequence)creatTime)) {
            verifyModel.isCheck = true;
            verifyModel.creatTime = creatTime;
            verifyModel.fileextension = fileextension;
            verifyModel.fileName = fileName;
            verifyModel.fileOssid = fileOssid;
            verifyModel.ver = ver;
        }
        return verifyModel;
    }
    
    private static VerifyModel verifyToken(String ver) {
        VerifyModel verifyModel = new VerifyModel();
        if (ver.contains((CharSequence)ShareFileTokenUtils.SHARE_STARTS) || ver.contains((CharSequence)ShareFileTokenUtils.SHARE_STARTN)) {
            final VerifyModel getShareUrlExtTokenParam = GetShareUrlExtTokenParam(ver);
            if (getShareUrlExtTokenParam != null) {
                getShareUrlExtTokenParam.shareUrl = ver;
            }
            ver = getShareUrlExtTokenParam.ver;
            verifyModel = getShareUrlExtTokenParam;
            if (!TextUtils.isEmpty((CharSequence)ver)) {
                verifyModel = getShareUrlExtTokenParam;
                if (HttpHeadUtils.shareVersionCode < Integer.parseInt(ver)) {
                    ToastUtil.showShort(2131821381);
                    verifyModel = getShareUrlExtTokenParam;
                }
            }
        }
        return verifyModel;
    }
    
    public static class ShareFileDownLoad
    {
        private final Activity activity;
        private LoadingDialog mProgressDialog;
        private final VerifyModel verifyModel;
        
        public ShareFileDownLoad(final Activity activity, final VerifyModel verifyModel) {
            this.activity = activity;
            this.verifyModel = verifyModel;
        }
        
        private boolean DownFileCheck(File file) {
            if (file.exists()) {
                try {
                    final BufferedReader bufferedReader = new BufferedReader((Reader)new FileReader(file));
                    final String trim = bufferedReader.readLine().trim();
                    bufferedReader.close();
                    if (!TextUtils.isEmpty((CharSequence)trim)) {
                        file = null;
                        try {
                            file = (File)new Gson().fromJson(trim, (Class)NewNetBean.class);
                        }
                        catch (final Exception ex) {}
                        if (file != null && ((NewNetBean)file).getCode() == 500) {
                            ToastUtil.showShort((CharSequence)((NewNetBean)file).getMessage());
                            return false;
                        }
                    }
                }
                catch (final IOException ex2) {
                    ex2.printStackTrace();
                }
                return true;
            }
            ToastUtil.showShort(2131821378);
            return false;
        }
        
        private void dismissLoading() {
            final LoadingDialog mProgressDialog = this.mProgressDialog;
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
        
        private void showConfirmDialog() {
            new AlertDialog$Builder((Context)this.activity).setTitle(2131821524).setMessage(2131821385).setPositiveButton(2131821384, (DialogInterface$OnClickListener)new _$$Lambda$ShareFileTokenUtils$ShareFileDownLoad$7xEAn5d1yJ2fhtGSIYKfWqu3H3I(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
        
        private void showLoading(final String textMessage) {
            if (this.mProgressDialog == null) {
                this.mProgressDialog = new LoadingDialog((BaseActivity)this.activity);
            }
            if (this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setOnTouchOutside(false);
            this.mProgressDialog.setTextMessage(textMessage);
            this.mProgressDialog.show();
        }
        
        public void dowloadNewShareFile() {
            this.showLoading(MApplication.getInstance().getString(2131821523));
            String s;
            if (TextUtils.isEmpty((CharSequence)this.verifyModel.fileName)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(DrawFileUtil.getAutoName());
                sb.append(this.verifyModel.fileextension);
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(FileConfig.SHARE_PATH);
                sb2.append(string);
                s = sb2.toString();
            }
            else {
                final DrawFileDataImpl drawFileDataImpl = new DrawFileDataImpl();
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(FileConfig.SHARE_PATH);
                sb3.append(((DrawFileDataI)drawFileDataImpl).getCopyFileName(this.verifyModel.fileName.replace((CharSequence)"***", (CharSequence)"&")));
                s = sb3.toString();
            }
            new Handler().postDelayed((Runnable)new Runnable(this, s) {
                final ShareFileDownLoad this$0;
                final String val$filePath;
                
                public void run() {
                    final NewHttpManager build = new NewHttpManager$Builder().build();
                    final RequestParams params = build.params(NetConfig$BaseData.picDownLoad());
                    params.addBodyParameter("fileOSSID", this.this$0.verifyModel.fileOssid);
                    params.addBodyParameter("createTime", this.this$0.verifyModel.creatTime);
                    build.downLoad(params, this.val$filePath, true, (RequestCallBack)new ShareFileTokenUtils$ShareFileDownLoad$1$1(this));
                }
            }, 1000L);
        }
        
        public void dowloadOldShareFileWith() {
            this.showLoading(MApplication.getInstance().getString(2131821523));
            String s;
            if (TextUtils.isEmpty((CharSequence)this.verifyModel.fileName)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(DrawFileUtil.getAutoName());
                sb.append(this.verifyModel.fileextension);
                final String string = sb.toString();
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(FileConfig.SHARE_PATH);
                sb2.append(string);
                s = sb2.toString();
            }
            else {
                final DrawFileDataImpl drawFileDataImpl = new DrawFileDataImpl();
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(FileConfig.SHARE_PATH);
                sb3.append(((DrawFileDataI)drawFileDataImpl).getCopyFileName(this.verifyModel.fileName.replace((CharSequence)"***", (CharSequence)"&")));
                s = sb3.toString();
            }
            new Handler().postDelayed((Runnable)new Runnable(this, s) {
                final ShareFileDownLoad this$0;
                final String val$filePath;
                
                public void run() {
                    final HttpManager build = new HttpManager$Builder().build();
                    final RequestParams params = build.params(NetConfig$BaseData.picDownLoad());
                    params.addBodyParameter("fileOSSID", this.this$0.verifyModel.fileOssid);
                    params.addBodyParameter("createTime", this.this$0.verifyModel.creatTime);
                    build.downLoad(params, this.val$filePath, true, (RequestCallBack)new ShareFileTokenUtils$ShareFileDownLoad$2$1(this));
                }
            }, 1000L);
        }
        
        public void dowloadShareFile() {
            if (this.verifyModel.shareUrl.contains((CharSequence)ShareFileTokenUtils.SHARE_STARTS)) {
                this.dowloadOldShareFileWith();
            }
            else {
                this.dowloadNewShareFile();
            }
        }
    }
    
    public static class VerifyModel
    {
        public String creatTime;
        public String fileName;
        public String fileOssid;
        public String fileextension;
        public boolean isCheck;
        public String shareUrl;
        public String ver;
        
        public VerifyModel() {
            this.shareUrl = "";
            this.isCheck = false;
            this.fileOssid = "";
            this.fileextension = "";
            this.fileName = "";
            this.creatTime = "";
            this.ver = "1";
        }
    }
}
