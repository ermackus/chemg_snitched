package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.ui.NativeSearchPaletteActivity;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import android.app.Activity;
import java.io.Serializable;
import android.content.Intent;
import com.kingagroot.kingdraw.ui.NativePaletteActivity;
import com.goodsrc.library.utils.FileUtil;
import com.kingagroot.kingdraw.config.FileConfig;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.kingagroot.kingdraw.config.NetConfig;
import com.kingagroot.kingdraw.http.NewNetBean;
import com.goodsrc.library.http.RequestCallBack;
import org.xutils.http.body.RequestBody;
import java.util.List;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import java.io.File;
import java.util.ArrayList;
import java.util.Map$Entry;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import android.content.BroadcastReceiver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import android.graphics.Bitmap;
import com.kingagroot.component.ui.utils.GBitmapUtils;
import com.kingagroot.kingdraw.core.image.DrawOption;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.kingdraw.limit.LimitPicMark$OnExportPicMarkCheck;
import com.kingagroot.kingdraw.limit.LimitPicMark;
import com.kingagroot.kingdraw.core.image.ImageFileDrawOption;
import android.net.Uri;
import java.util.Iterator;
import java.util.HashMap;
import android.text.TextUtils;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import android.os.Handler;

public class OpenNetChemFileHandler extends KDNativeBaseHander
{
    public static final String INTENT_FILTER_OPENNETCHEMFILEHANDLER = "intent_filter_opennetchemfilehandler";
    private static final int MSG_WHAT = 19;
    private OpenNetChemFileHandler.OpenNetChemFileHandler$ChemFileReceiver chemFileReceiver;
    private OpenNetChemFileHandler.OpenNetChemFileHandler$FileDownModel fileDownModel;
    private Handler handler;
    private boolean isAction;
    private boolean isRegisterReceiver;
    
    public OpenNetChemFileHandler(final JsWebView webView) {
        this.isAction = false;
        this.isRegisterReceiver = false;
        this.handler = (Handler)new OpenNetChemFileHandler$3(this);
        this.webView = webView;
    }
    
    private void formateData(final SearchPaletteResultModel searchPaletteResultModel) {
        final OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialModel openNetChemFileHandler$MaterialModel = (OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialModel)GsonUtil.fromJson(searchPaletteResultModel.material_data, (Class)OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialModel.class);
        int n = 0;
        final int n2 = 0;
        String material_smiles;
        String material_smarts;
        if (openNetChemFileHandler$MaterialModel != null && openNetChemFileHandler$MaterialModel.materials != null) {
            String s = "";
            String s2 = "";
            for (int i = 0; i < openNetChemFileHandler$MaterialModel.materials.size(); ++i) {
                final OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel openNetChemFileHandler$MaterialItemModel = (OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel)openNetChemFileHandler$MaterialModel.materials.get(i);
                final String smart = this.getSmart(openNetChemFileHandler$MaterialItemModel.kdjson);
                final String smiles = this.getSmiles(openNetChemFileHandler$MaterialItemModel.kdjson);
                if (i != 0) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(s2);
                    sb.append(".");
                    sb.append(smart);
                    s2 = sb.toString();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(s);
                    sb2.append(".");
                    sb2.append(smiles);
                    s = sb2.toString();
                }
                else {
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(s2);
                    sb3.append(smart);
                    s2 = sb3.toString();
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(s);
                    sb4.append(smiles);
                    s = sb4.toString();
                }
            }
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(s);
            sb5.append(">");
            String s3 = sb5.toString();
            final StringBuilder sb6 = new StringBuilder();
            sb6.append(s2);
            sb6.append(">");
            String s4 = sb6.toString();
            for (int j = 0; j < openNetChemFileHandler$MaterialModel.middles.size(); ++j) {
                final OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel openNetChemFileHandler$MaterialItemModel2 = (OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel)openNetChemFileHandler$MaterialModel.middles.get(j);
                final String smart2 = this.getSmart(openNetChemFileHandler$MaterialItemModel2.kdjson);
                final String smiles2 = this.getSmiles(openNetChemFileHandler$MaterialItemModel2.kdjson);
                if (j != 0) {
                    final StringBuilder sb7 = new StringBuilder();
                    sb7.append(s4);
                    sb7.append(".");
                    sb7.append(smart2);
                    s4 = sb7.toString();
                    final StringBuilder sb8 = new StringBuilder();
                    sb8.append(s3);
                    sb8.append(".");
                    sb8.append(smiles2);
                    s3 = sb8.toString();
                }
                else {
                    final StringBuilder sb9 = new StringBuilder();
                    sb9.append(s4);
                    sb9.append(smart2);
                    s4 = sb9.toString();
                    final StringBuilder sb10 = new StringBuilder();
                    sb10.append(s3);
                    sb10.append(smiles2);
                    s3 = sb10.toString();
                }
            }
            final StringBuilder sb11 = new StringBuilder();
            sb11.append(s3);
            sb11.append(">");
            final String string = sb11.toString();
            final StringBuilder sb12 = new StringBuilder();
            sb12.append(s4);
            sb12.append(">");
            String s5 = sb12.toString();
            final long n3 = openNetChemFileHandler$MaterialModel.results.size();
            int n4 = 0;
            String s6 = string;
            while (true) {
                material_smiles = s6;
                material_smarts = s5;
                if (n4 >= n3) {
                    break;
                }
                final OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel openNetChemFileHandler$MaterialItemModel3 = (OpenNetChemFileHandler.OpenNetChemFileHandler$MaterialItemModel)openNetChemFileHandler$MaterialModel.results.get(n4);
                final String smart3 = this.getSmart(openNetChemFileHandler$MaterialItemModel3.kdjson);
                final String smiles3 = this.getSmiles(openNetChemFileHandler$MaterialItemModel3.kdjson);
                if (n4 != 0) {
                    final StringBuilder sb13 = new StringBuilder();
                    sb13.append(s5);
                    sb13.append(".");
                    sb13.append(smart3);
                    s5 = sb13.toString();
                    final StringBuilder sb14 = new StringBuilder();
                    sb14.append(s6);
                    sb14.append(".");
                    sb14.append(smiles3);
                    s6 = sb14.toString();
                }
                else {
                    final StringBuilder sb15 = new StringBuilder();
                    sb15.append(s5);
                    sb15.append(smart3);
                    s5 = sb15.toString();
                    final StringBuilder sb16 = new StringBuilder();
                    sb16.append(s6);
                    sb16.append(smiles3);
                    s6 = sb16.toString();
                }
                ++n4;
            }
        }
        else {
            material_smiles = "";
            material_smarts = "";
        }
        searchPaletteResultModel.material_smiles = material_smiles;
        searchPaletteResultModel.material_smarts = material_smarts;
        final OpenNetChemFileHandler.OpenNetChemFileHandler$FileDownModel fileDownModel = this.fileDownModel;
        if (fileDownModel != null) {
            if (fileDownModel.opts.dataScope != null) {
                final Iterator iterator = this.fileDownModel.opts.dataScope.iterator();
                int n5 = n2;
                while (true) {
                    n = n5;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    final String s7 = (String)iterator.next();
                    if (s7.equals((Object)"png")) {
                        searchPaletteResultModel.image = this.getPng(searchPaletteResultModel.kdjson);
                    }
                    else if (s7.equals((Object)"jpeg")) {
                        if (!TextUtils.isEmpty((CharSequence)searchPaletteResultModel.image)) {
                            continue;
                        }
                        searchPaletteResultModel.image = this.getJpg(searchPaletteResultModel.kdjson);
                    }
                    else {
                        if (!s7.equals((Object)"kdx")) {
                            continue;
                        }
                        n5 = 1;
                    }
                }
            }
            if (n != 0) {
                final HashMap hashMap = new HashMap();
                hashMap.put((Object)"token", (Object)this.fileDownModel.opts.header.token);
                this.uploadFile(this.fileDownModel.taskId, this.fileDownModel.opts.uploadUrl, searchPaletteResultModel, (HashMap<String, String>)hashMap);
            }
            else {
                this.complete(this.fileDownModel.taskId, searchPaletteResultModel, "");
            }
        }
    }
    
    private String getFileNameByUrl(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append(".kdx");
        String s2 = sb.toString();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            s2 = Uri.parse(s).getLastPathSegment();
        }
        return s2;
    }
    
    private String getJpg(String bitmaptoString) {
        final ImageFileDrawOption drawOption = new ImageFileDrawOption();
        new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(drawOption)).checkExportPic(this.webView.getContext());
        final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
        imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setProofStatus(false).setDrawWithJson(bitmaptoString).setDrawOption((DrawOption)drawOption);
        final Bitmap drawBitmap = imageDrawBuilder.getDrawBitmap();
        if (drawBitmap != null) {
            bitmaptoString = GBitmapUtils.bitmaptoString(drawBitmap, 100);
            drawBitmap.recycle();
        }
        else {
            bitmaptoString = "";
        }
        imageDrawBuilder.onDestroy();
        return bitmaptoString;
    }
    
    private String getPng(String bitmaptoString) {
        final ImageFileDrawOption drawOption = new ImageFileDrawOption();
        new LimitPicMark((LimitPicMark$OnExportPicMarkCheck)new _$$Lambda$xonrcth9s59cgP_NhALdiyNzsvU(drawOption)).checkExportPic(this.webView.getContext());
        final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
        imageDrawBuilder.setBackgroundColor(0).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setDrawWithJson(bitmaptoString).setDrawOption((DrawOption)drawOption);
        final Bitmap drawBitmap = imageDrawBuilder.getDrawBitmap();
        if (drawBitmap != null) {
            bitmaptoString = GBitmapUtils.bitmaptoString(drawBitmap, 100);
            drawBitmap.recycle();
        }
        else {
            bitmaptoString = "";
        }
        imageDrawBuilder.onDestroy();
        return bitmaptoString;
    }
    
    private String getSmart(final String s) {
        final OpenNetChemFileHandler.OpenNetChemFileHandler$SmartsModel openNetChemFileHandler$SmartsModel = (OpenNetChemFileHandler.OpenNetChemFileHandler$SmartsModel)GsonUtil.fromJson(ProtocolUtils.convertKDJson2Smarts(s), (Class)OpenNetChemFileHandler.OpenNetChemFileHandler$SmartsModel.class);
        if (openNetChemFileHandler$SmartsModel != null) {
            return openNetChemFileHandler$SmartsModel.smarts;
        }
        return "";
    }
    
    private String getSmiles(final String s) {
        final OpenNetChemFileHandler.OpenNetChemFileHandler$SmilesModel openNetChemFileHandler$SmilesModel = (OpenNetChemFileHandler.OpenNetChemFileHandler$SmilesModel)GsonUtil.fromJson(ProtocolConverter.jsonToSmiles(s), (Class)OpenNetChemFileHandler.OpenNetChemFileHandler$SmilesModel.class);
        if (openNetChemFileHandler$SmilesModel != null) {
            return openNetChemFileHandler$SmilesModel.smiles;
        }
        return "";
    }
    
    private void registerReceiver() {
        if (!this.isRegisterReceiver) {
            this.isRegisterReceiver = true;
            this.chemFileReceiver = new OpenNetChemFileHandler.OpenNetChemFileHandler$ChemFileReceiver(this, (OpenNetChemFileHandler$1)null);
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("intent_filter_opennetchemfilehandler");
            LocalBroadcastManager.getInstance(this.webView.getContext()).registerReceiver((BroadcastReceiver)this.chemFileReceiver, intentFilter);
        }
    }
    
    private void uploadFile(final String s, final String s2, final SearchPaletteResultModel searchPaletteResultModel, final HashMap<String, String> hashMap) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams emptyParams = build.emptyParams(s2);
        for (final Map$Entry map$Entry : hashMap.entrySet()) {
            emptyParams.addHeader((String)map$Entry.getKey(), (String)map$Entry.getValue());
        }
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new KeyValue("files", (Object)new File(searchPaletteResultModel.kdx)));
        emptyParams.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        this.start(s, searchPaletteResultModel.kdx);
        this.showLoading();
        build.request(emptyParams, (RequestCallBack)new RequestCallBack<NewNetBean<String>>(this, s, searchPaletteResultModel) {
            final OpenNetChemFileHandler this$0;
            final SearchPaletteResultModel val$resultModel;
            final String val$taskId;
            
            public void onError(final Exception ex, final String s) {
                this.this$0.dismissLoading();
                this.this$0.complete(this.val$taskId, this.val$resultModel, "");
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                super.onLoading(n, n2, b);
                this.this$0.progress(this.val$taskId, n, n2);
            }
            
            public void onSuccess(final NewNetBean<String> newNetBean) {
                this.this$0.dismissLoading();
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    this.this$0.complete(this.val$taskId, this.val$resultModel, (String)newNetBean.getData());
                }
                else {
                    this.this$0.complete(this.val$taskId, this.val$resultModel, "");
                }
            }
        });
    }
    
    public void UnregisterReceiver() {
        if (this.chemFileReceiver != null && this.isRegisterReceiver) {
            LocalBroadcastManager.getInstance(this.webView.getContext()).unregisterReceiver((BroadcastReceiver)this.chemFileReceiver);
            this.chemFileReceiver = null;
        }
    }
    
    public void action(String s) {
        if (this.isAction) {
            return;
        }
        this.isAction = true;
        this.fileDownModel = null;
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final OpenNetChemFileHandler.OpenNetChemFileHandler$FileDownModel fileDownModel = (OpenNetChemFileHandler.OpenNetChemFileHandler$FileDownModel)GsonUtil.fromJson(s, (Class)OpenNetChemFileHandler.OpenNetChemFileHandler$FileDownModel.class);
        if ((this.fileDownModel = fileDownModel) != null) {
            if (fileDownModel.opts.header != null) {
                this.registerReceiver();
                if (this.fileDownModel.opts.type.equals((Object)"editor")) {
                    if (TextUtils.isEmpty((CharSequence)(s = this.fileDownModel.opts.header.filename))) {
                        s = this.getFileNameByUrl(this.fileDownModel.opts.downloadUrl);
                    }
                    FileUtil.creatOrExistDir(new File(FileConfig.getDownCachePath()));
                    final StringBuilder sb = new StringBuilder();
                    sb.append(FileConfig.getDownCachePath());
                    sb.append(File.separator);
                    sb.append(s);
                    final String string = sb.toString();
                    if (TextUtils.isEmpty((CharSequence)this.fileDownModel.opts.downloadUrl)) {
                        final NativeHandlerParam nativeHandlerParam = new NativeHandlerParam();
                        nativeHandlerParam.taskId = this.fileDownModel.taskId;
                        nativeHandlerParam.dataScope = this.fileDownModel.opts.dataScope;
                        nativeHandlerParam.fileName = s;
                        nativeHandlerParam.filePath = string;
                        final Intent intent = new Intent(this.webView.getNewContext(), (Class)NativePaletteActivity.class);
                        intent.putExtra("intent_key_nativehandlerparam", (Serializable)nativeHandlerParam);
                        ((Activity)this.webView.getNewContext()).startActivity(intent);
                        this.isAction = false;
                    }
                    else {
                        final HashMap hashMap = new HashMap();
                        hashMap.put((Object)"token", (Object)this.fileDownModel.opts.header.token);
                        this.downFile(this.fileDownModel.taskId, this.fileDownModel.opts.downloadUrl, string, s, (HashMap<String, String>)hashMap);
                    }
                }
                else if (this.fileDownModel.opts.type.equals((Object)"search")) {
                    FileUtil.creatOrExistDir(new File(FileConfig.getSearchKDXCachePath()));
                    s = this.fileDownModel.opts.downloadUrl;
                    if (TextUtils.isEmpty((CharSequence)this.fileDownModel.opts.downloadUrl)) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(System.currentTimeMillis());
                        sb2.append("");
                        s = sb2.toString();
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(s);
                    sb3.append(ProtocolTypeEnum.KDX.extension);
                    s = sb3.toString();
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(FileConfig.getDownCachePath());
                    sb4.append(File.separator);
                    sb4.append(s);
                    final String string2 = sb4.toString();
                    final NativeHandlerParam nativeHandlerParam2 = new NativeHandlerParam();
                    nativeHandlerParam2.taskId = this.fileDownModel.taskId;
                    nativeHandlerParam2.dataScope = this.fileDownModel.opts.dataScope;
                    nativeHandlerParam2.fileName = s;
                    nativeHandlerParam2.filePath = string2;
                    final Intent intent2 = new Intent(this.webView.getNewContext(), (Class)NativeSearchPaletteActivity.class);
                    intent2.putExtra("intent_key_nativehandlerparam", (Serializable)nativeHandlerParam2);
                    ((Activity)this.webView.getNewContext()).startActivity(intent2);
                    this.isAction = false;
                }
            }
        }
    }
    
    protected void complete(final String s, final SearchPaletteResultModel searchPaletteResultModel, final String s2) {
        if (this.webView == null) {
            return;
        }
        String string;
        try {
            final JSONObject jsonObject = new JSONObject();
            for (final String s3 : this.fileDownModel.opts.dataScope) {
                if (s3.equals((Object)"png")) {
                    if (jsonObject.has("image")) {
                        jsonObject.remove("image");
                    }
                    jsonObject.put("image", (Object)searchPaletteResultModel.image);
                }
                else if (s3.equals((Object)"jpeg")) {
                    if (jsonObject.has("image")) {
                        continue;
                    }
                    jsonObject.put("image", (Object)searchPaletteResultModel.image);
                }
                else if (s3.equals((Object)"kdx")) {
                    jsonObject.put("kdx", (Object)s2);
                }
                else if (s3.equals((Object)"mol")) {
                    jsonObject.put("mol", (Object)searchPaletteResultModel.mol);
                }
                else if (s3.equals((Object)"smiles")) {
                    jsonObject.put("smiles", (Object)searchPaletteResultModel.smiles);
                }
                else if (s3.equals((Object)"material-data")) {
                    jsonObject.put("material-data", (Object)searchPaletteResultModel.material_data);
                }
                else if (s3.equals((Object)"material-smiles")) {
                    jsonObject.put("material-smiles", (Object)searchPaletteResultModel.material_smiles);
                }
                else {
                    if (!s3.equals((Object)"material-smarts")) {
                        continue;
                    }
                    jsonObject.put("material-smarts", (Object)searchPaletteResultModel.material_smarts);
                }
            }
            string = jsonObject.toString();
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
            string = "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("kd");
        sb.append(".");
        sb.append("callbackStatusData");
        sb.append("(");
        sb.append("'");
        sb.append(s);
        sb.append("'");
        sb.append(",");
        sb.append("'complete'");
        sb.append(",");
        sb.append(string);
        sb.append(")");
        this.webView.post((Runnable)new OpenNetChemFileHandler$4(this, sb));
    }
    
    public void destroy() {
        this.UnregisterReceiver();
    }
    
    protected void downFile(final String s, final String s2, final String s3, final String s4, final HashMap<String, String> hashMap) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams emptyParams = build.emptyParams(s2);
        for (final Map$Entry map$Entry : hashMap.entrySet()) {
            emptyParams.addHeader((String)map$Entry.getKey(), (String)map$Entry.getValue());
        }
        this.showLoading();
        this.start(s, s3);
        build.downLoad(emptyParams, s3, false, (RequestCallBack)new RequestCallBack<File>(this, s4, s3, s) {
            final OpenNetChemFileHandler this$0;
            final String val$fileName;
            final String val$path;
            final String val$taskId;
            
            public void onError(final Exception ex, final String s) {
                this.this$0.dismissLoading();
                final NativeHandlerParam nativeHandlerParam = new NativeHandlerParam();
                nativeHandlerParam.dataScope = this.this$0.fileDownModel.opts.dataScope;
                nativeHandlerParam.fileName = this.val$fileName;
                nativeHandlerParam.filePath = this.val$path;
                final Intent intent = new Intent(this.this$0.webView.getNewContext(), (Class)NativePaletteActivity.class);
                intent.putExtra("intent_key_nativehandlerparam", (Serializable)nativeHandlerParam);
                ((Activity)this.this$0.webView.getNewContext()).startActivity(intent);
                this.this$0.isAction = false;
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                super.onLoading(n, n2, b);
                this.this$0.progress(this.val$taskId, n, n2);
            }
            
            public void onSuccess(final File file) {
                this.this$0.dismissLoading();
                final NativeHandlerParam nativeHandlerParam = new NativeHandlerParam();
                nativeHandlerParam.taskId = this.this$0.fileDownModel.taskId;
                nativeHandlerParam.dataScope = this.this$0.fileDownModel.opts.dataScope;
                nativeHandlerParam.fileName = this.val$fileName;
                nativeHandlerParam.filePath = this.val$path;
                final Intent intent = new Intent(this.this$0.webView.getNewContext(), (Class)NativePaletteActivity.class);
                intent.putExtra("intent_key_nativehandlerparam", (Serializable)nativeHandlerParam);
                ((Activity)this.this$0.webView.getNewContext()).startActivity(intent);
                this.this$0.isAction = false;
            }
        });
    }
    
    public String handerName() {
        return "openNetChemFile";
    }
}
