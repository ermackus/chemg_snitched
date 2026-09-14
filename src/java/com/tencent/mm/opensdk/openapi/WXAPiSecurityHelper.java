package com.tencent.mm.opensdk.openapi;

import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX$Req;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import java.util.Random;
import java.util.Iterator;
import java.util.Collection;
import org.json.JSONArray;
import android.content.SharedPreferences$Editor;
import org.json.JSONObject;
import android.os.Handler;
import android.os.Looper;
import java.util.TimerTask;
import java.util.Timer;
import com.tencent.mm.opensdk.utils.b;
import android.net.Uri;
import com.tencent.mm.opensdk.utils.Log;
import android.content.SharedPreferences;
import java.util.concurrent.CopyOnWriteArraySet;
import android.content.Context;

class WXAPiSecurityHelper
{
    private static final int MAX_STORE_KEY = 100;
    private static final int MAX_STORE_VALUE = 2048;
    private static final String SECURITY_KEY_TIMESTAMP_SECOND = "security_key_timestamp_second";
    private static final String STORE_VALUE_DATA = "security_key_resp";
    private static final String TAG = "MicroMsg.SDK.WXAPiSecurityHelper";
    private final Context context;
    private final CopyOnWriteArraySet<String> overtimeSyncReqSet;
    private final SharedPreferences sp;
    
    public WXAPiSecurityHelper(final Context context) {
        this.overtimeSyncReqSet = (CopyOnWriteArraySet<String>)new CopyOnWriteArraySet();
        this.context = context;
        this.sp = context.getSharedPreferences("__wx_opensdk_sp__", 0);
    }
    
    private int checkRuleCanPass(String s, final PromiseShareRule promiseShareRule) {
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "checkRuleCanPass, start check!");
        if (promiseShareRule.orgJsonData.length() > 2048) {
            Log.w("MicroMsg.SDK.WXAPiSecurityHelper", "response json is too long!");
            return 0;
        }
        final int state = promiseShareRule.state;
        if (state == 2) {
            s = "checkRuleCanPass, not in rule";
        }
        else {
            if (state != 1 || !promiseShareRule.appidInRule.equals((Object)promiseShareRule.reqAppid)) {
                Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "checkRuleCanPass, unknown");
                return 0;
            }
            if (promiseShareRule.urlRuleList.size() == 0) {
                s = "checkRuleCanPass, urlRuleList empty!";
            }
            else {
                final boolean checkUrlParametersLegal = this.checkUrlParametersLegal(s, promiseShareRule);
                final StringBuilder sb = new StringBuilder();
                sb.append("checkRuleCanPass, urlCheckResult = ");
                sb.append(checkUrlParametersLegal);
                Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
                if (!checkUrlParametersLegal) {
                    Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "checkRuleCanPass, no pass");
                    return 2;
                }
                s = "checkRuleCanPass, pass";
            }
        }
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", s);
        return 1;
    }
    
    private boolean checkUrlParametersLegal(String parse, final PromiseShareRule promiseShareRule) {
        parse = (String)Uri.parse(parse);
        final String host = ((Uri)parse).getHost();
        if (b.b(host)) {
            Log.i("MicroMsg.SDK.WXAPiSecurityHelper", "checkUrlParameters, host empty!");
            return false;
        }
        for (int i = 0; i < promiseShareRule.urlRuleList.size(); ++i) {
            final UrlRule urlRule = (UrlRule)promiseShareRule.urlRuleList.get(i);
            if (host.equals((Object)urlRule.host)) {
                int j = 0;
                int n = 0;
                try {
                    while (j < urlRule.mustQueryKey.size()) {
                        final String s = (String)urlRule.mustQueryKey.get(j);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("checkRuleCanPass, key = ");
                        sb.append(s);
                        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
                        int n2 = n;
                        if (!b.b(((Uri)parse).getQueryParameter(s))) {
                            n2 = n + 1;
                        }
                        ++j;
                        n = n2;
                    }
                    final boolean b = n == urlRule.mustQueryKey.size();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("checkRuleCanPass, rule.host = ");
                    sb2.append(urlRule.host);
                    sb2.append(", queryOk = ");
                    sb2.append(b);
                    Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb2.toString());
                    return b;
                }
                catch (final Exception ex) {
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("checkRuleCanPass, parse fail, e = %s");
                    sb3.append(ex.getMessage());
                    Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb3.toString());
                }
            }
        }
        return false;
    }
    
    private void doRequest(final PassContext passContext, final IHttpCheckCallback httpCheckCallback) {
        b.b.submit((Runnable)new Runnable(this, passContext, httpCheckCallback) {
            final WXAPiSecurityHelper this$0;
            final IHttpCheckCallback val$callback;
            final PassContext val$passContext;
            
            public void run() {
                final WXAPiSecurityHelper this$0 = this.this$0;
                final PassContext val$passContext = this.val$passContext;
                this.val$callback.onHttpCheckFinish(this.this$0.convert2ShareRule(this.val$passContext.appid, this$0.postHttpRequest(val$passContext.appid, val$passContext.version, val$passContext.localRule.buffer).toString()));
            }
        });
    }
    
    private void doRequestAsync(final PassContext passContext) {
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "doRequestAsync");
        this.doRequest(passContext, (IHttpCheckCallback)new WXAPiSecurityHelper$3(this));
    }
    
    private void doRequestSync(final PassContext passContext, final ISecuritySyncCheck securitySyncCheck) {
        final StringBuilder sb = new StringBuilder();
        sb.append("requestId = ");
        sb.append(passContext.reqSessionId);
        Log.i("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
        final Timer timer = new Timer(passContext.reqSessionId);
        timer.schedule((TimerTask)new TimerTask(this, passContext, securitySyncCheck) {
            final WXAPiSecurityHelper this$0;
            final PassContext val$passContext;
            final ISecuritySyncCheck val$syncCallback;
            
            public void run() {
                final StringBuilder sb = new StringBuilder();
                sb.append("sync request overtime, requestId = ");
                sb.append(this.val$passContext.reqSessionId);
                Log.i("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
                this.this$0.overtimeSyncReqSet.add((Object)this.val$passContext.reqSessionId);
                final ISecuritySyncCheck val$syncCallback = this.val$syncCallback;
                if (val$syncCallback != null) {
                    val$syncCallback.onSyncCheckFinish(true);
                }
            }
        }, passContext.localRule.getLegalUserWaitTime());
        this.doRequest(passContext, (IHttpCheckCallback)new WXAPiSecurityHelper$5(this, timer, passContext, securitySyncCheck));
    }
    
    private void extraSecurityCheckDoCallback(final ISecurityCheck securityCheck, final boolean b) {
        if (securityCheck != null) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable(this, securityCheck, b) {
                final WXAPiSecurityHelper this$0;
                final boolean val$canPass;
                final ISecurityCheck val$checkCallback;
                
                public void run() {
                    Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "has got result, callback on Main Thread.");
                    this.val$checkCallback.onCheckFinish(this.val$canPass);
                }
            });
        }
    }
    
    private String getStoreKey(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("security_key_appid_");
        sb.append(s);
        return sb.toString();
    }
    
    private void storeCheckResp(final String s, final PromiseShareRule promiseShareRule) {
        final String cookStoreJson = this.cookStoreJson(s, promiseShareRule);
        final StringBuilder sb = new StringBuilder();
        sb.append("cookStoreJson = ");
        sb.append(cookStoreJson);
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
        if (b.b(cookStoreJson)) {
            Log.w("MicroMsg.SDK.WXAPiSecurityHelper", "cookStoreJson get null!");
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("security_key_timestamp_second", System.currentTimeMillis() / 1000L);
            jsonObject.put("security_key_resp", (Object)cookStoreJson);
            this.commitSp(s, jsonObject.toString());
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("storeCheckResp fail, ex = ");
            sb2.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb2.toString());
        }
    }
    
    private void storeIfNecessary(final PromiseShareRule promiseShareRule) {
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "storeIfNecessary");
        String s;
        if (!b.b(promiseShareRule.reqAppid) && promiseShareRule.reqAppid.length() <= 100) {
            if (!b.b(promiseShareRule.orgJsonData) && promiseShareRule.orgJsonData.length() <= 2048) {
                if (promiseShareRule.state == 1 && promiseShareRule.reqAppid.equals((Object)promiseShareRule.appidInRule)) {
                    Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "storeIfNecessary, in rule");
                    this.storeCheckResp(promiseShareRule.reqAppid, promiseShareRule);
                }
                if (promiseShareRule.state == 2) {
                    Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "storeIfNecessary, not rule");
                    this.storeCheckResp(promiseShareRule.reqAppid, promiseShareRule);
                }
                return;
            }
            s = "store fail! response json illegal!";
        }
        else {
            s = "store fail! reqAppid illegal!";
        }
        Log.i("MicroMsg.SDK.WXAPiSecurityHelper", s);
    }
    
    protected void commitSp(final String s, final String s2) {
        final SharedPreferences$Editor edit = this.sp.edit();
        edit.putString(this.getStoreKey(s), s2);
        edit.commit();
    }
    
    protected PromiseShareRule convert2ShareRule(final String reqAppid, final String orgJsonData) {
        final PromiseShareRule promiseShareRule = new PromiseShareRule(null);
        promiseShareRule.reqAppid = reqAppid;
        promiseShareRule.orgJsonData = orgJsonData;
        if (b.b(orgJsonData)) {
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "convert2ShareRule: jsonRespData is empty");
            return promiseShareRule;
        }
        try {
            final JSONObject jsonObject = new JSONObject(orgJsonData);
            promiseShareRule.state = jsonObject.optInt("state", 0);
            promiseShareRule.appidInRule = jsonObject.optString("appid_rule", "");
            promiseShareRule.userWaitTimeMs = jsonObject.optLong("wait_time", 0L);
            promiseShareRule.nextRequestIntervalSecond = jsonObject.optLong("interval", 0L);
            promiseShareRule.buffer = jsonObject.optString("buffer", "");
            final JSONArray optJSONArray = jsonObject.optJSONArray("rules");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject jsonObject2 = optJSONArray.getJSONObject(i);
                    final UrlRule urlRule = new UrlRule(null);
                    urlRule.host = jsonObject2.optString("host");
                    final JSONArray optJSONArray2 = jsonObject2.optJSONArray("querys");
                    if (optJSONArray2 != null) {
                        for (int j = 0; j < optJSONArray2.length(); ++j) {
                            if (!b.b(optJSONArray2.optString(j, ""))) {
                                urlRule.mustQueryKey.add((Object)optJSONArray2.optString(j, ""));
                            }
                        }
                    }
                    promiseShareRule.urlRuleList.add((Object)urlRule);
                }
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("coverJson2ShareRule fail, ex = ");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
        }
        return promiseShareRule;
    }
    
    protected String cookStoreJson(String s, final PromiseShareRule promiseShareRule) {
        final String s2 = "";
        if (promiseShareRule != null) {
            if (!b.b(s)) {
                try {
                    if (promiseShareRule.state == 2) {
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("state", promiseShareRule.state);
                        jsonObject.put("interval", promiseShareRule.getLegalReqInterval());
                        if (promiseShareRule.buffer.length() > 64) {
                            s = "";
                        }
                        else {
                            s = promiseShareRule.buffer;
                        }
                        jsonObject.put("buffer", (Object)s);
                        s = jsonObject.toString();
                    }
                    else {
                        s = s2;
                        if (promiseShareRule.state == 1) {
                            s = this.covertShareRule2Json(promiseShareRule);
                        }
                    }
                    return s;
                }
                catch (final Exception ex) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("storeCheckResp fail, ex = ");
                    sb.append(ex.getMessage());
                    Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
                }
            }
        }
        return "";
    }
    
    protected String covertShareRule2Json(final PromiseShareRule promiseShareRule) {
        final String s = "";
        if (promiseShareRule == null) {
            return "";
        }
        String string;
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("state", promiseShareRule.state);
            jsonObject.put("appid_rule", (Object)promiseShareRule.appidInRule);
            jsonObject.put("wait_time", promiseShareRule.userWaitTimeMs);
            jsonObject.put("interval", promiseShareRule.nextRequestIntervalSecond);
            String buffer;
            if (promiseShareRule.buffer.length() > 64) {
                buffer = "";
            }
            else {
                buffer = promiseShareRule.buffer;
            }
            jsonObject.put("buffer", (Object)buffer);
            final JSONArray jsonArray = new JSONArray();
            for (final UrlRule urlRule : promiseShareRule.urlRuleList) {
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("host", (Object)urlRule.host);
                jsonObject2.put("querys", (Object)new JSONArray((Collection)urlRule.mustQueryKey));
                jsonArray.put((Object)jsonObject2);
            }
            jsonObject.put("rules", (Object)jsonArray);
            string = jsonObject.toString();
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("covertShareRule2Json fail, ex = ");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
            string = s;
        }
        return string;
    }
    
    public int doExtraSecurityCheck(final String appid, final String version, final String inputUrl, final ISecurityCheck securityCheck) {
        Log.i("MicroMsg.SDK.WXAPiSecurityHelper", "doExtraSecurityCheck: start!");
        if (b.b(appid) || b.b(inputUrl)) {
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", "doExtraSecurityCheck: appid or inputurl is empty!");
            return 0;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final PassContext passContext = new PassContext(null);
        final StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis);
        sb.append("");
        sb.append(new Random().nextInt(9999));
        passContext.reqSessionId = String.format("%s", new Object[] { sb.toString() });
        passContext.appid = appid;
        passContext.inputUrl = inputUrl;
        passContext.version = version;
        final WXSecurityData storedData = this.getStoredData(appid);
        if (this.context.getPackageManager().checkPermission("android.permission.INTERNET", this.context.getPackageName()) != 0) {
            Log.w("MicroMsg.SDK.WXAPiSecurityHelper", "doExtraSecurityCheck: No Internet permission!");
            return 0;
        }
        if (!storedData.isBasicParamsAllowed()) {
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", "doExtraSecurityCheck: local data illegal!");
            this.doRequestAsync(passContext);
            return 0;
        }
        final PromiseShareRule convert2ShareRule = this.convert2ShareRule(passContext.appid, storedData.respDataJson);
        passContext.localRule = convert2ShareRule;
        final boolean b = (storedData.lastStoreTimeStampSecond + convert2ShareRule.getLegalReqInterval()) * 1000L < System.currentTimeMillis();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("doExtraSecurityCheck: needDoNextReq: ");
        sb2.append(b);
        sb2.append(", last req time stamp:");
        sb2.append(storedData.lastStoreTimeStampSecond);
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb2.toString());
        if (!b) {
            final int checkRuleCanPass = this.checkRuleCanPass(passContext.inputUrl, passContext.localRule);
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("doExtraSecurityCheck: no needDoNextReq, read local rule: ");
            sb3.append(checkRuleCanPass);
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb3.toString());
            if (checkRuleCanPass == 2) {
                return 1;
            }
            return 0;
        }
        else {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("doExtraSecurityCheck: needDoNextReq, state: ");
            sb4.append(passContext.localRule.state);
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb4.toString());
            if (passContext.localRule.state != 1) {
                this.doRequestAsync(passContext);
                return 0;
            }
            this.doRequestSync(passContext, (ISecuritySyncCheck)new WXAPiSecurityHelper$1(this, securityCheck));
            return 2;
        }
    }
    
    public String extractMayNeedDoSecurityCheckUrl(final String s, final BaseReq baseReq) {
        if (baseReq.getType() != 2) {
            return "";
        }
        final SendMessageToWX$Req sendMessageToWX$Req = (SendMessageToWX$Req)baseReq;
        if (sendMessageToWX$Req.message.getType() != 5) {
            return "";
        }
        final WXWebpageObject wxWebpageObject = (WXWebpageObject)sendMessageToWX$Req.message.mediaObject;
        if (b.b(wxWebpageObject.webpageUrl)) {
            Log.i("MicroMsg.SDK.WXAPiSecurityHelper", "webpageUrl empty, don't need check.");
            return "";
        }
        Log.i("MicroMsg.SDK.WXAPiSecurityHelper", "need check.");
        return wxWebpageObject.webpageUrl;
    }
    
    protected String getLocalStoredJson(final String s) {
        return this.getStoredData(s).respDataJson;
    }
    
    protected WXSecurityData getStoredData(String string) {
        final WXSecurityData wxSecurityData = new WXSecurityData(null);
        final StringBuilder sb = new StringBuilder();
        sb.append("getStoredData, appid = ");
        sb.append(string);
        Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
        try {
            if (!this.sp.contains(this.getStoreKey(string))) {
                string = "getStoredData, fail, not exist!";
            }
            else {
                final JSONObject jsonObject = new JSONObject(this.sp.getString(this.getStoreKey(string), ""));
                final long optLong = jsonObject.optLong("security_key_timestamp_second");
                final String optString = jsonObject.optString("security_key_resp");
                wxSecurityData.appid = string;
                wxSecurityData.lastStoreTimeStampSecond = optLong;
                wxSecurityData.respDataJson = optString;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("getStoredData, Ok, appid = ");
                sb2.append(string);
                sb2.append("timeStampSecond: ");
                sb2.append(wxSecurityData.lastStoreTimeStampSecond);
                string = sb2.toString();
            }
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", string);
        }
        catch (final Exception ex) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("getStoredData fail, ex = ");
            sb3.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb3.toString());
        }
        return wxSecurityData;
    }
    
    protected StringBuffer postHttpRequest(String format, final String s, String line) {
        final StringBuffer sb = new StringBuffer();
        try {
            final String encode = URLEncoder.encode(format, "UTF-8");
            final String encode2 = URLEncoder.encode(line, "UTF-8");
            format = String.format("https://mp.weixin.qq.com/publicpoc/opensdkconf?action=GetShareConf&appid=%s&sdkVersion=%s&buffer=%s", new Object[] { encode, s, encode2 });
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("request http, url = ");
            sb2.append(format);
            sb2.append(", appid = ");
            sb2.append(encode);
            sb2.append(", version = ");
            sb2.append(s);
            sb2.append(", buffer = ");
            sb2.append(encode2);
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb2.toString());
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(format).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            final int responseCode = httpURLConnection.getResponseCode();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("http respCode = ");
            sb3.append(responseCode);
            Log.i("MicroMsg.SDK.WXAPiSecurityHelper", sb3.toString());
            if (responseCode == 200) {
                final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("http response = ");
                sb4.append((Object)sb);
                Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb4.toString());
                bufferedReader.close();
            }
            httpURLConnection.disconnect();
        }
        catch (final Exception ex) {
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("http request fail, ex = ");
            sb5.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXAPiSecurityHelper", sb5.toString());
        }
        return sb;
    }
    
    private static class CheckRuleResult
    {
        public static final int NoPass = 2;
        public static final int Pass = 1;
        public static final int Unknown = 0;
    }
    
    public static class ExtraSecurityCheckRes
    {
        public static final int Directly_NoPass = 1;
        public static final int Directly_Pass = 0;
        public static final int Need_Deep_Check = 2;
    }
    
    interface IHttpCheckCallback
    {
        void onHttpCheckFinish(final PromiseShareRule p0);
    }
    
    interface ISecurityCheck
    {
        void onCheckFinish(final boolean p0);
    }
    
    interface ISecuritySyncCheck
    {
        void onSyncCheckFinish(final boolean p0);
    }
    
    private static class PassContext
    {
        String appid;
        String inputUrl;
        PromiseShareRule localRule;
        String reqSessionId;
        String version;
        
        private PassContext() {
            this.reqSessionId = "";
            this.appid = "";
            this.inputUrl = "";
            this.version = "";
            this.localRule = new PromiseShareRule(null);
        }
    }
    
    private static class PromiseShareRule
    {
        String appidInRule;
        String buffer;
        public long nextRequestIntervalSecond;
        String orgJsonData;
        String reqAppid;
        int state;
        List<UrlRule> urlRuleList;
        long userWaitTimeMs;
        
        private PromiseShareRule() {
            this.orgJsonData = "";
            this.reqAppid = "";
            this.appidInRule = "";
            this.urlRuleList = (List<UrlRule>)new ArrayList();
            this.buffer = "";
        }
        
        public long getLegalReqInterval() {
            long nextRequestIntervalSecond = this.nextRequestIntervalSecond;
            if (nextRequestIntervalSecond <= 0L || nextRequestIntervalSecond >= 86400L) {
                nextRequestIntervalSecond = 3600L;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("getLegalReqInterval = ");
            sb.append(nextRequestIntervalSecond);
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
            return nextRequestIntervalSecond;
        }
        
        public long getLegalUserWaitTime() {
            long userWaitTimeMs = this.userWaitTimeMs;
            if (userWaitTimeMs <= 100L || userWaitTimeMs >= 60000L) {
                userWaitTimeMs = 5000L;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("getLegalUserWaitTime = ");
            sb.append(userWaitTimeMs);
            Log.d("MicroMsg.SDK.WXAPiSecurityHelper", sb.toString());
            return userWaitTimeMs;
        }
    }
    
    private static class RuleState
    {
        public static final int InRule = 1;
        public static final int None = 0;
        public static final int NotInRule = 2;
    }
    
    private static class UrlRule
    {
        String host;
        List<String> mustQueryKey;
        
        private UrlRule() {
            this.host = "";
            this.mustQueryKey = (List<String>)new ArrayList();
        }
    }
    
    private static class WXSecurityData
    {
        String appid;
        long lastStoreTimeStampSecond;
        String respDataJson;
        
        private WXSecurityData() {
            this.appid = "";
            this.respDataJson = "";
        }
        
        public boolean isBasicParamsAllowed() {
            final long lastStoreTimeStampSecond = this.lastStoreTimeStampSecond;
            final boolean b = false;
            final boolean b2 = lastStoreTimeStampSecond > 0L && lastStoreTimeStampSecond < System.currentTimeMillis() / 1000L;
            final boolean b3 = com.tencent.mm.opensdk.utils.b.b(this.appid);
            final boolean b4 = com.tencent.mm.opensdk.utils.b.b(this.respDataJson);
            boolean b5 = b;
            if (b2) {
                b5 = b;
                if (b3 ^ true) {
                    b5 = b;
                    if (b4 ^ true) {
                        b5 = true;
                    }
                }
            }
            return b5;
        }
    }
}
