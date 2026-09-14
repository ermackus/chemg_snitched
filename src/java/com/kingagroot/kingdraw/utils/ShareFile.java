package com.kingagroot.kingdraw.utils;

import com.kingagroot.kingdraw.core.data.DataOutModel;
import java.io.IOException;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.FileWriter$Builder;
import com.kingagroot.component.ui.utils.ShareImageOption;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.core.image.DrawOption;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.MThumbImageDrawOption;
import com.kingagroot.kingdraw.core.FileWriter;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.core.tool.ChemisAnalysisTool;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.text.TextUtils;
import com.kingagroot.kingdraw.config.Release;
import com.kingagroot.kingdraw.config.AppConfig;
import android.content.ComponentName;
import android.content.Intent;
import com.kingagroot.kingdraw.dialog.ShareFileDialog$OnShareFileDialogListener;
import com.kingagroot.kingdraw.dialog.ShareFileDialog;
import com.kingagroot.kingdraw.dialog.ShareSelectDialog$OnShareSelectDialogListener;
import com.kingagroot.kingdraw.dialog.ShareSelectDialog;
import com.tencent.tauth.IUiListener;
import android.os.Build$VERSION;
import android.os.Bundle;
import java.io.FileOutputStream;
import android.graphics.Matrix;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.MultipartBody;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import com.goodsrc.library.http.KDFileNameMap;
import android.util.Log;
import org.xutils.common.util.KeyValue;
import java.util.ArrayList;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import org.json.JSONException;
import java.util.List;
import java.util.Iterator;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel$EleAnal;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel$MzPercent;
import org.json.JSONArray;
import android.graphics.Bitmap;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import java.io.File;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.kingagroot.component.ui.model.ChemicalAnalysisModel;
import com.goodsrc.ui.library.BaseActivity;
import java.util.concurrent.Executors;
import android.os.Message;
import android.os.Looper;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.component.ui.widget.LoadingDialog;
import android.os.Handler;
import java.util.concurrent.Executor;
import android.content.Context;
import org.json.JSONObject;
import com.tencent.tauth.Tencent;

public class ShareFile
{
    private static Tencent mTencent;
    public static final String shareImgPath;
    private JSONObject chemAttr;
    private final Context context;
    private String drawFilePath;
    private final Executor executor;
    private String fileExtension;
    private String fileName;
    private final Handler handler;
    private final LoadingRunable loadingRunable;
    LoadingDialog mProgressDialog;
    private String picturePath;
    private String thumPicturePath;
    
    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(FileConfig.BASE_PATH);
        sb.append("share_app.png");
        shareImgPath = sb.toString();
    }
    
    public ShareFile(final Context context) {
        this.handler = new Handler(Looper.getMainLooper()) {
            final ShareFile this$0;
            
            public void handleMessage(final Message message) {
                super.handleMessage(message);
            }
        };
        this.chemAttr = new JSONObject();
        this.context = context;
        this.loadingRunable = new LoadingRunable();
        this.executor = (Executor)Executors.newFixedThreadPool(1);
        (this.mProgressDialog = new LoadingDialog((BaseActivity)context)).setTextMessage(context.getString(2131821383));
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.setOnTouchOutside(false);
    }
    
    private boolean checkAppInstall(final Context context, final String s) {
        boolean b2;
        final boolean b = b2 = false;
        if (s != null) {
            if (s.isEmpty()) {
                b2 = b;
            }
            else {
                PackageInfo packageInfo;
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(s, 0);
                }
                catch (final PackageManager$NameNotFoundException ex) {
                    ex.printStackTrace();
                    packageInfo = null;
                }
                b2 = b;
                if (packageInfo != null) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    private boolean clearDir() {
        final File file = new File(FileConfig.SHARE_PATH);
        final boolean exists = file.exists();
        int i = 0;
        if (exists) {
            for (File[] listFiles = file.listFiles(); i < listFiles.length; ++i) {
                listFiles[i].delete();
            }
            return true;
        }
        return false;
    }
    
    private void copyToClipboard(final String s) {
        ((ClipboardManager)this.context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)"KINGDRAW_SHARE_LINK", (CharSequence)s));
    }
    
    private void dismissLoading() {
        final LoadingDialog mProgressDialog = this.mProgressDialog;
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
    
    private void error(final String message) {
        this.handler.removeCallbacks((Runnable)this.loadingRunable);
        if (!((Activity)this.context).isFinishing()) {
            this.dismissLoading();
            new AlertDialog$Builder(this.context).setTitle(2131821374).setMessage((CharSequence)message).setPositiveButton(2131821386, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this) {
                final ShareFile this$0;
                
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    this.this$0.reshareFile();
                }
            }).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
        }
    }
    
    private static String getBase64String(final String s) {
        return Base64.encodeToString(s.getBytes(StandardCharsets.UTF_8), 2);
    }
    
    private static byte[] getBitmapByteData(final String s) {
        try {
            if (!new File(s).exists()) {
                return null;
            }
            final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
            bitmapFactory$Options.inSampleSize = 1;
            final Bitmap decodeFile = BitmapFactory.decodeFile(s, bitmapFactory$Options);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeFile.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)byteArrayOutputStream);
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            decodeFile.recycle();
            byteArrayOutputStream.close();
            return byteArray;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private JSONObject getChemAttr(final ChemicalAnalysisModel chemicalAnalysisModel) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("formula", (Object)chemicalAnalysisModel.getFormula().toString());
            jsonObject.put("exactMass", chemicalAnalysisModel.getExactMass());
            final JSONArray jsonArray = new JSONArray();
            for (final ChemicalAnalysisModel$MzPercent chemicalAnalysisModel$MzPercent : chemicalAnalysisModel.getMzPercent()) {
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("mz", chemicalAnalysisModel$MzPercent.getMz());
                jsonObject2.put("percent", chemicalAnalysisModel$MzPercent.getPercent());
                jsonArray.put((Object)jsonObject2);
            }
            jsonObject.put("mzPercent", (Object)jsonArray);
            final List eleAnal = chemicalAnalysisModel.getEleAnal();
            final int size = eleAnal.size();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; ++i) {
                final ChemicalAnalysisModel$EleAnal chemicalAnalysisModel$EleAnal = (ChemicalAnalysisModel$EleAnal)eleAnal.get(i);
                if (i != 0) {
                    sb.append(";");
                }
                sb.append(chemicalAnalysisModel$EleAnal.getEleName());
                sb.append(",");
                sb.append(chemicalAnalysisModel$EleAnal.getPercent());
            }
            jsonObject.put("elemAnal", (Object)sb.toString());
            jsonObject.put("molWt", chemicalAnalysisModel.getMolWt());
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }
    
    private JSONObject getEmptyChemAttr() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("formula", (Object)"");
            jsonObject.put("exactMass", 0);
            final JSONArray jsonArray = new JSONArray();
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("mz", 0);
            jsonObject2.put("percent", 100);
            jsonArray.put((Object)jsonObject2);
            jsonObject.put("mzPercent", (Object)jsonArray);
            jsonObject.put("elemAnal", (Object)"");
            jsonObject.put("molWt", 0);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }
    
    private String getKingDrawId(final String s) {
        final String[] split = s.split("\\?");
        if (split != null && split.length >= 2) {
            final String[] split2 = split[1].split("\\&");
            if (split2 != null && split2.length >= 1) {
                for (final String s2 : split2) {
                    if (s2.startsWith("id=")) {
                        final String[] split3 = s2.split("\\=");
                        if (split3 != null && split3.length >= 2) {
                            return split3[1];
                        }
                    }
                }
            }
        }
        return "";
    }
    
    private void getShareUrl() {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.share());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("FileName", (Object)this.fileName);
            jsonObject.put("FileExtension", (Object)this.fileExtension);
            jsonObject.put("ChemAttr", (Object)this.chemAttr);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("data", (Object)jsonObject.toString()));
        ((List)list).add((Object)new KeyValue("oss", (Object)new File(this.drawFilePath)));
        ((List)list).add((Object)new KeyValue("structpic", (Object)new File(this.picturePath)));
        Log.e("share", list.toString());
        HttpURLConnection.setFileNameMap((FileNameMap)new KDFileNameMap());
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        build.request(params, (RequestCallBack)new ShareFile$6(this));
    }
    
    private static String getSquareThumb(String absolutePath, int n) {
        try {
            if (!new File(absolutePath).exists()) {
                return "";
            }
            final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
            bitmapFactory$Options.inSampleSize = 1;
            final Bitmap decodeFile = BitmapFactory.decodeFile(absolutePath, bitmapFactory$Options);
            int outWidth = bitmapFactory$Options.outWidth;
            final int outHeight = bitmapFactory$Options.outHeight;
            final Matrix matrix = new Matrix();
            int n2;
            if (outWidth > n) {
                n2 = (outWidth - n) / 2;
                outWidth = n;
            }
            else {
                n2 = 0;
            }
            int n3;
            if (outHeight > n) {
                n3 = (outHeight - n) / 2;
            }
            else {
                n = outHeight;
                n3 = 0;
            }
            final Bitmap bitmap = Bitmap.createBitmap(decodeFile, n2, n3, outWidth, n, matrix, false);
            final StringBuilder sb = new StringBuilder();
            sb.append(FileConfig.SHARE_PATH);
            sb.append(System.currentTimeMillis());
            sb.append("_thumb.");
            sb.append((Object)Bitmap$CompressFormat.PNG);
            final String string = sb.toString();
            final File file = new File(string);
            final File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)fileOutputStream);
            fileOutputStream.close();
            decodeFile.recycle();
            bitmap.recycle();
            if (file.length() / 1024.0f > 128.0f) {
                absolutePath = BimpCompressUtils.imageCompress(string).getAbsolutePath();
                return absolutePath;
            }
            return string;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    private static byte[] getcheck() {
        return new byte[] { 48, 99, 97, 51, 50, 98, 49, 99, 101, 50, 57, 100, 100, 98, 97, 99, 97, 101, 101, 99, 102, 97, 98, 55, 57, 54, 48, 100, 52, 100, 101, 53 };
    }
    
    private void reshareFile() {
        this.start();
        this.getShareUrl();
    }
    
    private static void sharQQ(final Context context, final String s, final String s2, final String s3, final String s4) {
        final Bundle bundle = new Bundle();
        bundle.putString("title", s3);
        bundle.putString("targetUrl", s);
        bundle.putString("summary", s4);
        bundle.putString("imageUrl", s2);
        bundle.putString("appName", "KingDraw");
        bundle.putInt("req_type", 1);
        bundle.putInt("cflag", 0);
        if (ShareFile.mTencent == null) {
            if (Build$VERSION.SDK_INT >= 29) {
                ShareFile.mTencent = Tencent.createInstance("101566542", context, "com.kingagroot.kingdraw.fileProvider");
            }
            else {
                ShareFile.mTencent = Tencent.createInstance("101566542", context);
            }
        }
        final Tencent mTencent = ShareFile.mTencent;
        if (mTencent != null) {
            mTencent.shareToQQ((Activity)context, bundle, (IUiListener)new ShareFile$2());
        }
    }
    
    private void shareApp(final String s, final String s2, final String s3, final String s4) {
        final ShareSelectDialog shareSelectDialog = new ShareSelectDialog(this.context, "*/*");
        shareSelectDialog.setOnShareSelectDialogListener((ShareSelectDialog$OnShareSelectDialogListener)new ShareFile$8(this, s, s2, s3, s4));
        shareSelectDialog.show();
    }
    
    private void shareFile(final String s, final String s2) {
        final ShareFileDialog shareFileDialog = new ShareFileDialog(this.context);
        shareFileDialog.setOnShareFileDialogListener((ShareFileDialog$OnShareFileDialogListener)new ShareFile$9(this, s, s2));
        shareFileDialog.show();
    }
    
    private void shareToMinProgram(final String s, final String s2, final String s3, final String s4) {
        final String kingDrawId = this.getKingDrawId(s);
        final ShareFileTokenUtils.VerifyModel getShareUrlExtTokenParam = ShareFileTokenUtils.GetShareUrlExtTokenParam(s);
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity"));
        intent.setFlags(402653184);
        final Bundle bundle = new Bundle();
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 36);
        bundle.putString("_message_token", "OpenSdkToken@1556412511511");
        bundle.putString("_wxapi_basereq_transaction", "webpage1556412511488");
        bundle.putInt("_wxobject_sdkVer", 0);
        final StringBuilder sb = new StringBuilder();
        sb.append("pages/index/index.html?kingdrawId=");
        sb.append(kingDrawId);
        sb.append("&ver=");
        sb.append(getShareUrlExtTokenParam.ver);
        bundle.putString("_wxminiprogram_path", sb.toString());
        if (AppConfig.RELEASE == Release.STANDARD) {
            bundle.putInt("_wxminiprogram_type", 0);
        }
        else {
            bundle.putInt("_wxminiprogram_type", 2);
        }
        bundle.putString("_wxobject_title", s3);
        bundle.putString("_wxobject_description", s4);
        bundle.putString("_mmessage_appPackage", "com.kingagroot.kingdraw");
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            bundle.putByteArray("_wxobject_thumbdata", getBitmapByteData(s2));
        }
        bundle.putString("_wxobject_message_action", (String)null);
        bundle.putString("_wxobject_message_ext", (String)null);
        bundle.putString("_wxapi_sendmessagetowx_req_use_open_id", (String)null);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_wxminiprogram_username", "gh_2bef07f39545@app");
        bundle.putString("_mmessage_content", "weixin://sendreq?appid=wxdbab8ce2bc2c4ed2");
        bundle.putString("_wxapi_basereq_openid", (String)null);
        bundle.putByteArray("_mmessage_checksum", getcheck());
        bundle.putString("_wxobject_mediatagname", (String)null);
        bundle.putInt("_mmessage_sdkVersion", 620953856);
        bundle.putString("_wxminiprogram_webpageurl", s);
        bundle.putString("_wxobject_identifier_", "com.tencent.mm.sdk.openapi.WXMiniProgramObject");
        bundle.putBoolean("_wxminiprogram_withsharetiket", false);
        intent.putExtras(bundle);
        this.context.startActivity(intent);
    }
    
    private void shareToWeiXin(final int n, final String s, final String s2, final String s3, final String s4) {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity"));
        intent.setFlags(402653184);
        final Bundle bundle = new Bundle();
        bundle.putInt("_wxapi_sendmessagetowx_req_media_type", 5);
        bundle.putString("_message_token", "OpenSdkToken@1553749861602");
        bundle.putString("_wxapi_basereq_transaction", "webpage1553748220133");
        bundle.putInt("_wxobject_sdkVer", 0);
        bundle.putString("_wxobject_description", s4);
        bundle.putString("_mmessage_appPackage", "com.kingagroot.kingdraw");
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            bundle.putByteArray("_wxobject_thumbdata", getBitmapByteData(s2));
        }
        bundle.putString("_wxwebpageobject_webpageUrl", s);
        bundle.putInt("_wxapi_command_type", 2);
        bundle.putString("_mmessage_content", "weixin://sendreq?appid=wxdbab8ce2bc2c4ed2");
        bundle.putString("_wxobject_title", s3);
        bundle.putInt("_wxapi_sendmessagetowx_req_scene", n);
        bundle.putInt("_mmessage_sdkVersion", 620953856);
        bundle.putString("_wxobject_identifier_", "com.tencent.mm.sdk.openapi.WXWebpageObject");
        bundle.putByteArray("_mmessage_checksum", getcheck());
        intent.putExtras(bundle);
        this.context.startActivity(intent);
    }
    
    private void showLoading() {
        final LoadingDialog mProgressDialog = this.mProgressDialog;
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }
    
    private void start() {
        this.handler.postDelayed((Runnable)this.loadingRunable, 200L);
    }
    
    private void success() {
        this.handler.removeCallbacks((Runnable)this.loadingRunable);
        this.dismissLoading();
    }
    
    public void shareApp() {
        String s;
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_ZH)) {
            s = "http://www.kingdraw.com";
        }
        else {
            s = "http://www.kingdraw.com/indexen?name=index";
        }
        this.shareApp(s, getSquareThumb(ShareFile.shareImgPath, 900), "KingDraw", this.context.getString(2131821379));
    }
    
    public void shareFile(final KingDrawView kingDrawView, final String s, final ProtocolTypeEnum protocolTypeEnum) {
        new AlertDialog$Builder(this.context).setTitle(2131821373).setMessage(2131821372).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this, kingDrawView, s, protocolTypeEnum) {
            final ShareFile this$0;
            final KingDrawView val$kingDrawView;
            final String val$oldFileName;
            final ProtocolTypeEnum val$protocolTypeEnum;
            
            public void onClick(final DialogInterface dialogInterface, final int n) {
                this.this$0.start();
                this.this$0.executor.execute((Runnable)new Runnable(this) {
                    final ShareFile$3 this$1;
                    
                    public void run() {
                        this.this$1.this$0.clearDir();
                        final File file = new File(FileConfig.SHARE_PATH);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        final String chemisAnalysisWithAllElements = new ChemisAnalysisTool(this.this$1.val$kingDrawView.getPaletteId()).chemisAnalysisWithAllElements();
                        if (TextUtils.isEmpty((CharSequence)chemisAnalysisWithAllElements)) {
                            this.this$1.this$0.chemAttr = this.this$1.this$0.getEmptyChemAttr();
                        }
                        else {
                            this.this$1.this$0.chemAttr = this.this$1.this$0.getChemAttr((ChemicalAnalysisModel)GsonUtil.fromJson(chemisAnalysisWithAllElements, (Class)ChemicalAnalysisModel.class));
                        }
                        String s;
                        if (TextUtils.isEmpty((CharSequence)this.this$1.val$oldFileName)) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(System.currentTimeMillis());
                            sb.append("");
                            s = sb.toString();
                        }
                        else {
                            s = this.this$1.val$oldFileName;
                        }
                        this.this$1.this$0.fileExtension = this.this$1.val$protocolTypeEnum.extension;
                        final ShareFile this$0 = this.this$1.this$0;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(s);
                        sb2.append(this.this$1.val$protocolTypeEnum.extension);
                        this$0.fileName = sb2.toString();
                        final ShareFile this$2 = this.this$1.this$0;
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(FileConfig.SHARE_PATH);
                        sb3.append(this.this$1.this$0.fileName);
                        this$2.drawFilePath = sb3.toString();
                        final StringBuilder sb4 = new StringBuilder();
                        sb4.append(System.currentTimeMillis());
                        sb4.append("_thumb");
                        final String string = sb4.toString();
                        final ShareFile this$3 = this.this$1.this$0;
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append(FileConfig.SHARE_PATH);
                        sb5.append(string);
                        sb5.append(".");
                        sb5.append((Object)Bitmap$CompressFormat.PNG);
                        this$3.thumPicturePath = sb5.toString();
                        final FileWriter$Builder setNeedKDJson = FileWriter.getBuilder().withView(this.this$1.val$kingDrawView).setNeedKDJson(true);
                        if (this.this$1.val$protocolTypeEnum == ProtocolTypeEnum.KDX) {
                            final FormatValue formatValue = this.this$1.val$kingDrawView.getFormatValue();
                            final String allElementsJson = this.this$1.val$kingDrawView.getAllElementsJson();
                            final StringBuilder sb6 = new StringBuilder();
                            sb6.append("thumb_");
                            sb6.append(s);
                            final String string2 = sb6.toString();
                            final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
                            drawOption.setMark(false);
                            final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
                            final boolean saveToFile = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)drawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string2);
                            imageDrawBuilder.onDestroy();
                            String filePath;
                            if (saveToFile) {
                                filePath = imageDrawBuilder.getFilePath();
                                FileUtil.copyFile(filePath, this.this$1.this$0.thumPicturePath);
                            }
                            else {
                                filePath = null;
                            }
                            final StringBuilder sb7 = new StringBuilder();
                            sb7.append("png_");
                            sb7.append(s);
                            final String string3 = sb7.toString();
                            final ImageFileDrawOption imageFileDrawOption = new ImageFileDrawOption();
                            new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(imageFileDrawOption)).checkExportPic(this.this$1.this$0.context);
                            final boolean saveToFile2 = imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string3);
                            imageDrawBuilder.onDestroy();
                            String filePath2;
                            if (saveToFile2) {
                                filePath2 = imageDrawBuilder.getFilePath();
                            }
                            else {
                                filePath2 = null;
                            }
                            final StringBuilder sb8 = new StringBuilder();
                            sb8.append("jpg_");
                            sb8.append(s);
                            final boolean saveToFile3 = imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setFormatValue(formatValue).setDrawWithJson(allElementsJson).setDrawOption((DrawOption)imageFileDrawOption).saveToFile(FileConfig.DRAW_FILE_PIC_PATH, sb8.toString());
                            imageDrawBuilder.onDestroy();
                            String filePath3;
                            if (saveToFile3) {
                                filePath3 = imageDrawBuilder.getFilePath();
                            }
                            else {
                                filePath3 = null;
                            }
                            setNeedKDJson.setKDXImageSource(filePath, filePath2, filePath3);
                        }
                        setNeedKDJson.writeToFile(FileConfig.SHARE_PATH, s, this.this$1.val$protocolTypeEnum);
                        FormatValue formatValue2;
                        if (this.this$1.val$protocolTypeEnum == ProtocolTypeEnum.KDX) {
                            formatValue2 = this.this$1.val$kingDrawView.getFormatValue();
                        }
                        else {
                            formatValue2 = new GFormatValueDBImpl().readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f).formatValue();
                        }
                        final String json = setNeedKDJson.getJson();
                        final StringBuilder sb9 = new StringBuilder();
                        sb9.append(System.currentTimeMillis());
                        sb9.append("");
                        final String string4 = sb9.toString();
                        final ShareFile this$4 = this.this$1.this$0;
                        final StringBuilder sb10 = new StringBuilder();
                        sb10.append(FileConfig.SHARE_PATH);
                        sb10.append(string4);
                        sb10.append(".");
                        sb10.append((Object)Bitmap$CompressFormat.JPEG);
                        this$4.picturePath = sb10.toString();
                        final ShareImageOption drawOption2 = new ShareImageOption();
                        drawOption2.setMark(false);
                        final ImageDrawBuilder setDrawOption = new ImageDrawBuilder().setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setDrawWithJson(json).setFormatValue(formatValue2).setProofStatus(false).setBackgroundColor(-1).setDrawOption((DrawOption)drawOption2);
                        setDrawOption.saveToFile(FileConfig.SHARE_PATH, string4);
                        setDrawOption.onDestroy();
                        if (this.this$1.val$protocolTypeEnum != ProtocolTypeEnum.KDX) {
                            final MThumbImageDrawOption drawOption3 = new MThumbImageDrawOption();
                            drawOption3.setMark(false);
                            final ImageDrawBuilder setDrawOption2 = new ImageDrawBuilder().setCompressFormat(Bitmap$CompressFormat.PNG, 100).setDrawWithJson(json).setFormatValue(formatValue2).setProofStatus(false).setBackgroundColor(-1).setDrawOption((DrawOption)drawOption3);
                            setDrawOption2.saveToFile(FileConfig.SHARE_PATH, string);
                            setDrawOption2.onDestroy();
                        }
                        this.this$1.this$0.thumPicturePath = getSquareThumb(this.this$1.this$0.thumPicturePath, 900);
                        this.this$1.this$0.getShareUrl();
                    }
                });
            }
        }).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
    }
    
    public void shareFile(final FolderFileModel folderFileModel) {
        if (!NetworkUtil.isNetworkConnected(this.context)) {
            new AlertDialog$Builder(this.context).setTitle(2131821524).setMessage(2131821067).setPositiveButton(2131821068, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this) {
                final ShareFile this$0;
                
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    this.this$0.context.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                }
            }).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
            return;
        }
        new AlertDialog$Builder(this.context).setTitle(2131821373).setMessage(2131821372).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener(this, folderFileModel) {
            final ShareFile this$0;
            final FolderFileModel val$folderFileModel;
            
            public void onClick(final DialogInterface dialogInterface, final int n) {
                this.this$0.start();
                this.this$0.executor.execute((Runnable)new Runnable(this) {
                    final ShareFile$5 this$1;
                    
                    public void run() {
                        this.this$1.this$0.clearDir();
                        this.this$1.this$0.drawFilePath = this.this$1.val$folderFileModel.getFilePath();
                        this.this$1.this$0.picturePath = this.this$1.val$folderFileModel.getPicPath();
                        this.this$1.this$0.fileName = this.this$1.val$folderFileModel.getFileName();
                        this.this$1.this$0.fileExtension = this.this$1.val$folderFileModel.getFileExtension();
                        this.this$1.this$0.chemAttr = null;
                        try {
                            final DataOutModel byPath = ProtocolReader.builder().setNeedKDJson(true).readByPath(this.this$1.this$0.drawFilePath);
                            final StringBuilder sb = new StringBuilder();
                            sb.append(System.currentTimeMillis());
                            sb.append("");
                            final String string = sb.toString();
                            final ShareFile this$0 = this.this$1.this$0;
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(FileConfig.SHARE_PATH);
                            sb2.append(string);
                            sb2.append(".");
                            sb2.append((Object)Bitmap$CompressFormat.JPEG);
                            this$0.picturePath = sb2.toString();
                            final ShareImageOption drawOption = new ShareImageOption();
                            drawOption.setMark(false);
                            final ImageDrawBuilder setBackgroundColor = new ImageDrawBuilder().setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setDrawWithJson(byPath.josn).setProofStatus(false).setDrawOption((DrawOption)drawOption).setBackgroundColor(-1);
                            setBackgroundColor.saveToFile(FileConfig.SHARE_PATH, string);
                            final String chemisAnalysisWithAllElements = new ChemisAnalysisTool(setBackgroundColor.getPaletteId()).chemisAnalysisWithAllElements();
                            if (TextUtils.isEmpty((CharSequence)chemisAnalysisWithAllElements)) {
                                this.this$1.this$0.chemAttr = this.this$1.this$0.getEmptyChemAttr();
                            }
                            else {
                                this.this$1.this$0.chemAttr = this.this$1.this$0.getChemAttr((ChemicalAnalysisModel)GsonUtil.fromJson(chemisAnalysisWithAllElements, (Class)ChemicalAnalysisModel.class));
                            }
                            setBackgroundColor.onDestroy();
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(System.currentTimeMillis());
                            sb3.append("2");
                            final String string2 = sb3.toString();
                            final ShareFile this$2 = this.this$1.this$0;
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append(FileConfig.SHARE_PATH);
                            sb4.append(string2);
                            sb4.append(".");
                            sb4.append((Object)Bitmap$CompressFormat.PNG);
                            this$2.thumPicturePath = sb4.toString();
                            final MThumbImageDrawOption drawOption2 = new MThumbImageDrawOption();
                            drawOption2.setMark(false);
                            final ImageDrawBuilder setDrawOption = new ImageDrawBuilder().setCompressFormat(Bitmap$CompressFormat.PNG, 100).setDrawWithJson(byPath.josn).setProofStatus(false).setBackgroundColor(-1).setDrawOption((DrawOption)drawOption2);
                            setDrawOption.saveToFile(FileConfig.SHARE_PATH, string2);
                            setDrawOption.onDestroy();
                            this.this$1.this$0.thumPicturePath = getSquareThumb(this.this$1.this$0.thumPicturePath, 900);
                        }
                        catch (final IOException ex) {
                            ex.printStackTrace();
                        }
                        this.this$1.this$0.getShareUrl();
                    }
                });
            }
        }).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
    }
    
    private class LinkUrl
    {
        public String linkUrl;
        final ShareFile this$0;
        
        private LinkUrl(final ShareFile this$0) {
            this.this$0 = this$0;
        }
        
        public String getLinkUrl() {
            return this.linkUrl;
        }
        
        public void setLinkUrl(final String linkUrl) {
            this.linkUrl = linkUrl;
        }
    }
    
    private class LoadingRunable implements Runnable
    {
        final ShareFile this$0;
        
        private LoadingRunable(final ShareFile this$0) {
            this.this$0 = this$0;
        }
        
        public void run() {
            this.this$0.showLoading();
        }
    }
}
