package com.kingagroot.kingdraw.widget.jsweb.KDNativeHander;

import com.kingagroot.kingdraw.config.FileConfig;
import com.goodsrc.library.utils.GsonUtil;
import android.net.Uri;
import android.text.TextUtils;
import com.goodsrc.library.utils.ToastUtil;
import com.goodsrc.library.utils.FileUtil;
import java.util.Iterator;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import java.io.File;
import com.goodsrc.library.http.RequestCallBack;
import java.util.Map$Entry;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import java.util.HashMap;
import java.util.ArrayDeque;
import com.kingagroot.kingdraw.widget.jsweb.JsWebView;
import androidx.core.util.Pair;
import java.util.Queue;

public class BundleDownloadHandler extends KDNativeBaseHander
{
    private String RootPath;
    private BundleDownloadHandler.BundleDownloadHandler$FileDownModel fileDownModel;
    private int finishCount;
    private boolean isDownloadIng;
    private Queue<Pair<String, String>> tasks;
    private String token;
    
    public BundleDownloadHandler(final JsWebView webView) {
        this.tasks = (Queue<Pair<String, String>>)new ArrayDeque();
        this.finishCount = 0;
        this.token = "";
        this.RootPath = "";
        this.webView = webView;
    }
    
    private void downTask(final String s, final String s2, final HashMap<String, String> hashMap) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams emptyParams = build.emptyParams(s);
        for (final Map$Entry map$Entry : hashMap.entrySet()) {
            emptyParams.addHeader((String)map$Entry.getKey(), (String)map$Entry.getValue());
        }
        build.downLoad(emptyParams, s2, false, (RequestCallBack)new RequestCallBack<File>(this) {
            final BundleDownloadHandler this$0;
            
            public void onError(final Exception ex, final String s) {
                super.onError(ex, s);
                this.this$0.nextTask();
            }
            
            public void onLoading(final long n, final long n2, final boolean b) {
                super.onLoading(n, n2, b);
            }
            
            public void onSuccess(final File file) {
                this.this$0.finishCount++;
                this.this$0.nextTask();
            }
        });
    }
    
    private void finish(final boolean b, final String s) {
        this.complete(this.fileDownModel.taskId, s);
        this.isDownloadIng = false;
        this.dismissLoading();
        FileUtil.deleteFile(this.RootPath);
        if (b) {
            final StringBuilder sb = new StringBuilder();
            sb.append("\u4e0b\u8f7d\u6210\u529f\uff1a");
            sb.append(s);
            ToastUtil.showLong((CharSequence)sb.toString());
        }
        else {
            ToastUtil.showLong((CharSequence)"\u4e0b\u8f7d\u5931\u8d25");
        }
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
    
    private void nextTask() {
        final Pair pair = (Pair)this.tasks.poll();
        if (pair != null) {
            final HashMap hashMap = new HashMap();
            hashMap.put((Object)"token", (Object)this.token);
            this.downTask((String)pair.first, (String)pair.second, (HashMap<String, String>)hashMap);
        }
        else if (this.finishCount > 0) {
            new Thread((Runnable)new BundleDownloadHandler$1(this)).start();
        }
        else {
            this.finish(false, "");
        }
    }
    
    private String pathCombine(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(File.separator);
        sb.append(s2);
        return sb.toString();
    }
    
    private void startDown() {
        this.showLoading();
        this.isDownloadIng = true;
        this.start(this.fileDownModel.taskId, this.RootPath);
        if (this.tasks.size() > 0) {
            this.nextTask();
        }
        else {
            this.finish(false, "");
        }
    }
    
    public void action(String s) {
        if (this.isDownloadIng && TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final BundleDownloadHandler.BundleDownloadHandler$FileDownModel fileDownModel = (BundleDownloadHandler.BundleDownloadHandler$FileDownModel)GsonUtil.fromJson(s, (Class)BundleDownloadHandler.BundleDownloadHandler$FileDownModel.class);
        if ((this.fileDownModel = fileDownModel) == null && fileDownModel.header == null) {
            return;
        }
        if (TextUtils.isEmpty((CharSequence)(s = this.fileDownModel.header.filename))) {
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("");
            s = sb.toString();
        }
        this.finishCount = 0;
        this.token = this.fileDownModel.header.token;
        this.RootPath = this.pathCombine(FileConfig.getDownCachePath(), s);
        for (final BundleDownloadHandler.BundleDownloadHandler$FileModel bundleDownloadHandler$FileModel : this.fileDownModel.urls) {
            if (TextUtils.isEmpty((CharSequence)bundleDownloadHandler$FileModel.path)) {
                s = this.pathCombine(this.RootPath, this.getFileNameByUrl(bundleDownloadHandler$FileModel.url));
            }
            else {
                s = this.pathCombine(this.pathCombine(this.RootPath, bundleDownloadHandler$FileModel.path), this.getFileNameByUrl(bundleDownloadHandler$FileModel.url));
            }
            this.addTask(bundleDownloadHandler$FileModel.url, s);
        }
        this.startDown();
    }
    
    public void addTask(final String s, final String s2) {
        this.tasks.add((Object)new Pair((Object)s, (Object)s2));
    }
    
    public void destroy() {
    }
    
    public String handerName() {
        return "bundleDownload";
    }
}
