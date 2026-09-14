package com.kingagroot.kingdraw.base;

import com.kingagroot.kingdraw.interfaces.DrawFileDataI;
import com.kingagroot.component.ui.db.GSGroupModelDBI;
import com.kingagroot.kingdraw.interfaces.SynFileDBI;
import com.kingagroot.kingdraw.interfaces.WhiteListDbi;
import com.kingagroot.component.ui.db.GFormatValueDBI;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import com.kingagroot.kingdraw.ui.StartActivity;
import java.io.IOException;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.kingdraw.model.FolderFileModel;
import com.kingagroot.kingdraw.interfaces.impl.DrawFileDataImpl;
import com.goodsrc.library.utils.ToastUtil;
import com.kingagroot.kingdraw.ui.account.model.UdidModel;
import com.goodsrc.library.utils.AES;
import java.util.Iterator;
import com.kingagroot.component.ui.model.GSGroupModel;
import com.kingagroot.component.ui.db.impl.GSGroupModelDBImpl;
import com.kingagroot.component.ui.model.RadicalsModel;
import com.goodsrc.library.utils.AppUtil;
import org.xutils.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.interfaces.impl.SynFileDBImpl;
import com.kingagroot.kingdraw.interfaces.LimitConfigDbi;
import com.kingagroot.kingdraw.config.DefaultLimitConfig;
import com.kingagroot.kingdraw.interfaces.impl.LimitLoginConfigDbiMpl;
import com.goodsrc.library.utils.NetworkUtil;
import com.kingagroot.kingdraw.http.HttpHeadUtils;
import android.os.Handler;
import com.goodsrc.library.utils.FileUtil;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.impl.WhiteListDbiMpl;
import com.kingagroot.kingdraw.model.WebWhiteModel;
import java.util.ArrayList;
import com.kingagroot.component.ui.utils.AccountUtils;
import com.kingagroot.kingdraw.utils.link.LoginLink;
import com.kingagroot.kingdraw.utils.link.UserInfoLink;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import com.kingagroot.kingdraw.utils.link.UserInfoLink$OnUserInfoListener;
import com.goodsrc.library.utils.DateTimeUtils;
import com.kingagroot.kingdraw.config.ShareData;
import com.kingagroot.kingdraw.utils.link.LoginLink$OnUserLoginListener;
import android.text.TextUtils;
import com.kingagroot.kingdraw.utils.link.LimitLink;
import com.kingagroot.kingdraw.utils.link.LimitLink$OnLimitConfigInfoListener;
import com.kingagroot.kingdraw.utils.link.CountryLink;
import com.kingagroot.kingdraw.utils.link.CountryLink$OnCountryFinishLister;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.utils.SPUtil;
import com.kingagroot.kingdraw.config.NetConfig;
import com.kingagroot.kingdraw.http.NewNetBean;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.component.ui.db.impl.GFormatValueDBImpl;
import com.kingagroot.kingdraw.gesture.GestureRecognize;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.webkit.WebView;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.CookieManager;
import android.os.Build$VERSION;
import com.kingagroot.kingdraw.interfaces.impl.WorkWindowDbiMpl;
import com.goodsrc.library.utils.AssetsUtil;
import com.kingagroot.kingdraw.config.FileConfig;
import java.io.File;
import com.kingagroot.kingdraw.utils.ShareFile;
import android.content.Context;

public class AppDataIniter extends DataInite
{
    private AppDataIniter.AppDataIniter$AppDataIniterLisener appDataIniterLisener;
    private final Context context;
    private int taskCount;
    private AppDataIniter.AppDataIniter$TaskState taskState;
    
    public AppDataIniter(final Context context) {
        this.taskState = AppDataIniter.AppDataIniter$TaskState.FINISHED;
        this.context = context;
    }
    
    private void checkShareAppImg() {
        if (!new File(ShareFile.shareImgPath).exists()) {
            AssetsUtil.copyAssetFile((Context)MApplication.getInstance(), "share_app.png", FileConfig.BASE_PATH, "share_app.png");
        }
    }
    
    private void checkSmiles() {
        this.taskStart();
        new Thread((Runnable)new _$$Lambda$AppDataIniter$o2Tt9_NtCkbufvb2xqRxIREBt_g(this)).start();
    }
    
    private void cleanWorkItem() {
        ((WorkWindowDbi)new WorkWindowDbiMpl()).cleanAllWorkItems();
    }
    
    private void clearCookie() {
        try {
            if (Build$VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().removeAllCookies((ValueCallback)null);
            }
            else {
                CookieSyncManager.createInstance(this.context);
                CookieManager.getInstance().removeAllCookie();
                CookieSyncManager.getInstance().sync();
            }
            final WebView webView = new WebView(this.context);
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            webView.clearSslPreferences();
            webView.clearView();
            webView.clearMatches();
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void clearDownCacheFile() {
        new Thread((Runnable)_$$Lambda$AppDataIniter$jVIBAl22Hs6ct9SJ5puJ6BgQSg0.INSTANCE).start();
    }
    
    private void databaseInit() {
        KingDrawConfig.CheckDBFile();
        GestureRecognize.CheckDBFile();
        ((GFormatValueDBI)new GFormatValueDBImpl()).initFormatData();
        this.upGSGroup();
    }
    
    private void getCountryByIp() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getCountryCodeByIp()), (RequestCallBack)new RequestCallBack<NewNetBean<String>>(this) {
            final AppDataIniter this$0;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onFinished() {
                super.onFinished();
            }
            
            public void onSuccess(final NewNetBean<String> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    SPUtil.setString("COUNTRY_IP", "ipCode", (String)newNetBean.getData());
                }
            }
        });
    }
    
    private void getCountryList() {
        new CountryLink((CountryLink$OnCountryFinishLister)new CountryLink$OnCountryFinishLister(this) {
            final AppDataIniter this$0;
            
            public void onFinish() {
            }
        }).getCountryList();
    }
    
    private void getLimitConfig(final int n) {
        new LimitLink(n, (LimitLink$OnLimitConfigInfoListener)new LimitLink$OnLimitConfigInfoListener(this) {
            final AppDataIniter this$0;
            
            public void onFinish() {
            }
        }).getLimitConfig();
    }
    
    private void getUserInfo() {
        if (!TextUtils.isEmpty((CharSequence)MApplication.getUserToken()) && !"-".equals((Object)MApplication.getUserToken())) {
            this.taskStart();
            new LoginLink((LoginLink$OnUserLoginListener)new LoginLink$OnUserLoginListener(this) {
                final AppDataIniter this$0;
                
                public void onFailure(final String s) {
                    if (ShareData.getLoginTime() != 0L && DateTimeUtils.calculationTimeDay(System.currentTimeMillis(), ShareData.getLoginTime()) > 30L) {
                        MApplication.getInstance().userLogout();
                    }
                    this.this$0.taskFinish();
                }
                
                public void onFinish() {
                }
                
                public void onSuccess() {
                    new UserInfoLink((UserInfoLink$OnUserInfoListener)new UserInfoLink$OnUserInfoListener(this) {
                        final AppDataIniter$4 this$1;
                        
                        public void onFailure(final String s) {
                            if (ShareData.getLoginTime() != 0L && DateTimeUtils.calculationTimeDay(System.currentTimeMillis(), ShareData.getLoginTime()) > 30L) {
                                MApplication.getInstance().userLogout();
                            }
                        }
                        
                        public void onFinish() {
                            this.this$1.this$0.taskFinish();
                        }
                        
                        public void onSuccess(final AccountUserModel accountUserModel) {
                            this.this$1.this$0.getLimitConfig(1);
                        }
                    }).getUserInfo(true);
                }
            }).userLogin(AccountUtils.getLoginUserName(), AccountUtils.getLoginPass(), AccountUtils.getLoginType(), AccountUtils.getLoginSuffix(), true);
        }
    }
    
    private void getWebWhiteList() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        build.request(build.params(NetConfig$BaseData.getWhiteList()), (RequestCallBack)new RequestCallBack<NewNetBean<ArrayList<WebWhiteModel>>>(this) {
            final AppDataIniter this$0;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onFinished() {
                super.onFinished();
            }
            
            public void onSuccess(final NewNetBean<ArrayList<WebWhiteModel>> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    ((WhiteListDbi)new WhiteListDbiMpl()).saveWhiteData((List<WebWhiteModel>)newNetBean.getData());
                }
            }
        });
    }
    
    private void maxDeylayTask() {
        new Handler().postDelayed((Runnable)new _$$Lambda$AppDataIniter$tWZzDlO8xNxCg5WldZzYGDWsVHI(this), 10000L);
    }
    
    private void minDeylayTask() {
        this.taskStart();
        new Handler().postDelayed((Runnable)new _$$Lambda$AppDataIniter$7SkPyx_C7ypDNNLrG859ixLM_Mk(this), 2000L);
    }
    
    private void needNetWorkMethod() {
        if (TextUtils.isEmpty((CharSequence)HttpHeadUtils.getUserDeviceId()) || "-".equals((Object)HttpHeadUtils.getUserDeviceId())) {
            this.getUdid();
        }
        this.getCountryList();
        this.getWebWhiteList();
        this.getCountryByIp();
        this.getLimitConfig(0);
        this.startAppRecord();
        if (MApplication.getInstance().isLogin()) {
            if (!NetworkUtil.isNetworkConnected(this.context)) {
                if (MApplication.getInstance().getAccountUserModel().getVipState() == 1) {
                    if (DefaultLimitConfig.overtime((LimitConfigDbi)new LimitLoginConfigDbiMpl())) {
                        MApplication.getInstance().userLogout();
                    }
                }
                else if (ShareData.getLoginTime() != 0L && DateTimeUtils.calculationTimeDay(System.currentTimeMillis(), ShareData.getLoginTime()) > 30L) {
                    MApplication.getInstance().userLogout();
                }
            }
            else if (!TextUtils.isEmpty((CharSequence)HttpHeadUtils.getUserDeviceId()) && !"-".equals((Object)HttpHeadUtils.getUserDeviceId())) {
                this.getUserInfo();
            }
        }
    }
    
    private void restartSyn() {
        ((SynFileDBI)new SynFileDBImpl()).restartSynFile();
    }
    
    private void startAppRecord() {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.getRecordAppStartUrl());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clientConfig", (Object)"android");
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<String>>(this) {
            final AppDataIniter this$0;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onFinished() {
                super.onFinished();
            }
            
            public void onSuccess(final NewNetBean<String> newNetBean) {
            }
        });
    }
    
    private void taskFinish() {
        synchronized (this) {
            final int taskCount = this.taskCount - 1;
            this.taskCount = taskCount;
            if (taskCount == 0 && this.appDataIniterLisener != null && this.taskState == AppDataIniter.AppDataIniter$TaskState.RUNNING) {
                this.appDataIniterLisener.onFinish();
                this.taskState = AppDataIniter.AppDataIniter$TaskState.FINISHED;
            }
        }
    }
    
    private void taskStart() {
        synchronized (this) {
            ++this.taskCount;
        }
    }
    
    private void upGSGroup() {
        final int gsGroupDBVersion = ShareData.getGSGroupDBVersion();
        final int versionCode = AppUtil.getVersionCode((Context)MApplication.getInstance());
        int n = gsGroupDBVersion;
        if (versionCode > ShareData.getLastBootVersion()) {
            n = gsGroupDBVersion;
            if (versionCode < 18) {
                n = 1;
            }
        }
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        final RequestParams params = build.params(NetConfig$BaseData.getRadicalsList());
        params.addParameter("versionNo", (Object)n);
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<RadicalsModel>>(this) {
            final AppDataIniter this$0;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onSuccess(final NewNetBean<RadicalsModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    ShareData.saveGSGroupDBVersion(((RadicalsModel)newNetBean.getData()).getVersionCode());
                    final GSGroupModelDBImpl gsGroupModelDBImpl = new GSGroupModelDBImpl();
                    final List datas = ((RadicalsModel)newNetBean.getData()).getDatas();
                    if (datas == null) {
                        return;
                    }
                    final Iterator iterator = datas.iterator();
                    while (iterator.hasNext()) {
                        ((GSGroupModelDBI)gsGroupModelDBImpl).updateFile((GSGroupModel)iterator.next(), false);
                    }
                }
            }
        });
    }
    
    public void cancel() {
        this.taskState = AppDataIniter.AppDataIniter$TaskState.CANCELED;
    }
    
    public void getUdid() {
        this.taskStart();
        final String randomString = HttpHeadUtils.getRandomString(8);
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.getUdid());
        final String encrypt = AES.encrypt(NetConfig.aesKey, randomString);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sourceData", (Object)randomString);
            jsonObject.put("encryptedDataString", (Object)encrypt);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<UdidModel>>(this) {
            final AppDataIniter this$0;
            
            public void onError(final Exception ex, final String s) {
            }
            
            public void onFinished() {
                super.onFinished();
                this.this$0.taskFinish();
            }
            
            public void onSuccess(final NewNetBean<UdidModel> newNetBean) {
                if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                    HttpHeadUtils.setUserDeviceId(((UdidModel)newNetBean.getData()).getUdid());
                }
                else {
                    ToastUtil.showShort((CharSequence)newNetBean.getMessage());
                }
            }
        });
    }
    
    public boolean isRunning() {
        return this.taskState == AppDataIniter.AppDataIniter$TaskState.RUNNING;
    }
    
    public void start(final AppDataIniter.AppDataIniter$AppDataIniterLisener appDataIniterLisener) {
        this.taskState = AppDataIniter.AppDataIniter$TaskState.RUNNING;
        this.appDataIniterLisener = appDataIniterLisener;
        this.minDeylayTask();
        this.maxDeylayTask();
        this.databaseInit();
        this.checkSmiles();
        this.checkShareAppImg();
        this.restartSyn();
        this.cleanWorkItem();
        this.clearCookie();
        this.uploadLog();
        this.clearDownCacheFile();
        this.needNetWorkMethod();
    }
    
    public void uploadLog() {
        final String log_PATH = FileConfig.LOG_PATH;
        final File file = new File(log_PATH);
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$BaseData.errLog());
        if (file.exists()) {
            final File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            if (listFiles.length > 0) {
                final ArrayList list = new ArrayList();
                ((List)list).add((Object)new KeyValue("deviceparameters", (Object)AppUtil.getHardwareinfo()));
                for (int i = 0; i < listFiles.length; ++i) {
                    final File file2 = listFiles[i];
                    final StringBuilder sb = new StringBuilder();
                    sb.append("file");
                    sb.append(i);
                    ((List)list).add((Object)new KeyValue(sb.toString(), (Object)file2));
                }
                params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
                build.request(params, (RequestCallBack)new RequestCallBack<NewNetBean<?>>(this, log_PATH) {
                    final AppDataIniter this$0;
                    final String val$pathLog;
                    
                    public void onError(final Exception ex, final String s) {
                    }
                    
                    public void onSuccess(final NewNetBean<?> newNetBean) {
                        if (newNetBean.getCode() == NetConfig.SUCCESS_CODE) {
                            FileUtil.deleteFile(this.val$pathLog);
                        }
                    }
                });
            }
        }
    }
}
