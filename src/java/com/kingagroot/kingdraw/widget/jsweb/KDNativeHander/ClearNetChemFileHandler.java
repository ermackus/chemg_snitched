package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.goodsrc.library.utils.FileUtil;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import com.goodsrc.library.utils.GsonUtil;
import android.net.Uri;
import android.text.TextUtils;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;

public class ClearNetChemFileHandler extends KDNativeBaseHander
{
    public ClearNetChemFileHandler(final JsWebView webView) {
        this.webView = webView;
    }
    
    private void callback(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("kd.");
        sb.append("callbackData");
        sb.append("(");
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("'");
        sb2.append(s);
        sb2.append("'");
        sb.append(sb2.toString());
        sb.append(")");
        this.webView.post((Runnable)new ClearNetChemFileHandler$1(this, sb));
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
    
    public void action(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final ClearNetChemFileHandler.ClearNetChemFileHandler$FileDownModel clearNetChemFileHandler$FileDownModel = (ClearNetChemFileHandler.ClearNetChemFileHandler$FileDownModel)GsonUtil.fromJson(s, (Class)ClearNetChemFileHandler.ClearNetChemFileHandler$FileDownModel.class);
        if (clearNetChemFileHandler$FileDownModel != null) {
            if (clearNetChemFileHandler$FileDownModel.opts != null) {
                final boolean equals = clearNetChemFileHandler$FileDownModel.opts.type.equals((Object)"editor");
                s = "";
                if (equals) {
                    if (clearNetChemFileHandler$FileDownModel.opts.header != null) {
                        s = clearNetChemFileHandler$FileDownModel.opts.header.filename;
                    }
                    String fileNameByUrl = s;
                    if (TextUtils.isEmpty((CharSequence)s)) {
                        fileNameByUrl = this.getFileNameByUrl(clearNetChemFileHandler$FileDownModel.opts.downloadUrl);
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append(FileConfig.getDownCachePath());
                    sb.append(File.separator);
                    sb.append(fileNameByUrl);
                    FileUtil.deleteFile(sb.toString());
                    this.callback(clearNetChemFileHandler$FileDownModel.taskId);
                }
                else if (clearNetChemFileHandler$FileDownModel.opts.type.equals((Object)"search")) {
                    s = clearNetChemFileHandler$FileDownModel.opts.downloadUrl;
                    if (TextUtils.isEmpty((CharSequence)clearNetChemFileHandler$FileDownModel.opts.downloadUrl)) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(System.currentTimeMillis());
                        sb2.append("");
                        s = sb2.toString();
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append(s);
                    sb3.append(ProtocolTypeEnum.KDX.extension);
                    final String string = sb3.toString();
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append(FileConfig.getDownCachePath());
                    sb4.append(File.separator);
                    sb4.append(string);
                    FileUtil.deleteFile(sb4.toString());
                    this.callback(clearNetChemFileHandler$FileDownModel.taskId);
                }
            }
        }
    }
    
    public void destroy() {
    }
    
    public String handerName() {
        return "clearNetChemFile";
    }
}
