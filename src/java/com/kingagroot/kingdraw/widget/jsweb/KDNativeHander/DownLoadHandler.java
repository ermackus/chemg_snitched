package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import java.util.Iterator;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import java.util.Map$Entry;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import java.util.HashMap;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import com.goodsrc.library.utils.GsonUtil;
import android.net.Uri;
import android.text.TextUtils;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;

public class DownLoadHandler extends KDNativeBaseHander
{
    public DownLoadHandler(final JsWebView webView) {
        this.webView = webView;
    }
    
    private String getFileNameByUrl(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("");
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
        final DownLoadHandler.DownLoadHandler$FileDownModel downLoadHandler$FileDownModel = (DownLoadHandler.DownLoadHandler$FileDownModel)GsonUtil.fromJson(s, (Class)DownLoadHandler.DownLoadHandler$FileDownModel.class);
        if (downLoadHandler$FileDownModel == null && downLoadHandler$FileDownModel.header == null) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)(s = downLoadHandler$FileDownModel.header.filename))) {
            s = this.getFileNameByUrl(downLoadHandler$FileDownModel.url);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(FileConfig.getDownCachePath());
        sb.append(File.separator);
        sb.append(s);
        final String string = sb.toString();
        final HashMap hashMap = new HashMap();
        hashMap.put((Object)"token", (Object)downLoadHandler$FileDownModel.header.token);
        this.downFile(downLoadHandler$FileDownModel.taskId, downLoadHandler$FileDownModel.url, string, (HashMap<String, String>)hashMap);
    }
    
    public void destroy() {
    }
    
    protected void downFile(final String s, final String s2, final String s3, final HashMap<String, String> hashMap) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams emptyParams = build.emptyParams(s2);
        for (final Map$Entry map$Entry : hashMap.entrySet()) {
            emptyParams.addHeader((String)map$Entry.getKey(), (String)map$Entry.getValue());
        }
        this.showLoading();
        this.start(s, s3);
        build.downLoad(emptyParams, s3, false, (RequestCallBack)new RequestCallBack<File>(this, s, s3) {
            final DownLoadHandler this$0;
            final String val$path;
            final String val$taskId;
            
            public void onError(final Exception ex, final String s) {
                super.onError(ex, s);
                this.this$0.dismissLoading();
                this.this$0.error(this.val$taskId, s);
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                super.onLoading(n, n2, b);
                this.this$0.progress(this.val$taskId, n, n2);
            }
            
            public void onSuccess(final File file) {
                this.this$0.dismissLoading();
                this.this$0.complete(this.val$taskId, this.val$path);
            }
        });
    }
    
    public String handerName() {
        return "download";
    }
}
