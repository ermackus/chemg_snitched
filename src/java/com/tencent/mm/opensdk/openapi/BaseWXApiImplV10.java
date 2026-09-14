package com.tencent.mm.opensdk.openapi;

import com.tencent.mm.opensdk.modelmsg.WXMediaMessage$IMediaObject;
import com.tencent.mm.opensdk.utils.ILog;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX$IWXSceneDataObject;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXStateSceneDataObject;
import com.tencent.mm.opensdk.channel.a.a$a;
import android.content.pm.PackageInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.modelmsg.LaunchFromWX;
import com.tencent.mm.opensdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;
import com.tencent.mm.opensdk.modelbiz.CreateChatroom;
import com.tencent.mm.opensdk.modelbiz.JoinChatroom;
import com.tencent.mm.opensdk.modelbiz.ChooseCardFromWXCardPackage;
import com.tencent.mm.opensdk.modelbiz.HandleScanResult;
import com.tencent.mm.opensdk.modelpay.JumpToOfflinePay;
import com.tencent.mm.opensdk.modelpay.WXJointPay;
import com.tencent.mm.opensdk.modelmsg.SendTdiAuth;
import com.tencent.mm.opensdk.modelbiz.WXChannelBind;
import com.tencent.mm.opensdk.channel.a.a;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import com.tencent.mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage;
import com.tencent.mm.opensdk.modelbiz.WXQRCodePay;
import com.tencent.mm.opensdk.modelbiz.WXPreloadMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXPreloadMiniProgramEnvironment;
import com.tencent.mm.opensdk.modelbiz.WXOpenCustomerServiceChat;
import java.util.HashMap;
import java.util.Map;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgramWithToken;
import android.content.ContentResolver;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import java.net.URLEncoder;
import com.tencent.mm.opensdk.modelbiz.WXChannelStartLive;
import com.tencent.mm.opensdk.modelbiz.WXChannelShareVideo;
import com.tencent.mm.opensdk.modelbiz.WXChannelOpenProfile;
import com.tencent.mm.opensdk.modelbiz.WXChannelOpenLive;
import com.tencent.mm.opensdk.modelbiz.WXChannelOpenFeed;
import com.tencent.mm.opensdk.modelbiz.WXChannelOpenEvent;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import android.os.Handler;
import android.app.PendingIntent$OnFinished;
import android.content.Intent;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import com.tencent.mm.opensdk.modelbiz.SubscribeMiniProgramMsg;
import com.tencent.mm.opensdk.modelbiz.WXNontaxPay;
import com.tencent.mm.opensdk.modelbiz.WXPayInsurance;
import com.tencent.mm.opensdk.modelbiz.WXInvoiceAuthInsert;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.modelbiz.WXChannelJumpUrlInfo;
import com.tencent.mm.opensdk.modelbiz.WXChannelJumpMiniProgramInfo;
import com.tencent.mm.opensdk.modelbiz.WXChannelBaseJumpInfo;
import org.json.JSONObject;
import com.tencent.mm.opensdk.modelbiz.IWXChannelJumpInfo;
import com.tencent.mm.opensdk.channel.MMessageActV2;
import com.tencent.mm.opensdk.channel.MMessageActV2$Args;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.mm.opensdk.utils.b;
import com.tencent.mm.opensdk.utils.Log;
import android.content.Context;

class BaseWXApiImplV10 implements IWXAPI
{
    protected static final String TAG = "MicroMsg.SDK.WXApiImplV10";
    private static String wxappPayEntryClassname;
    protected String appId;
    protected boolean checkSignature;
    protected Context context;
    protected boolean detached;
    private int launchMode;
    private WXAPiSecurityHelper securityHelper;
    private int wxSdkVersion;
    
    BaseWXApiImplV10(final Context context, final String appId, final boolean checkSignature, final int launchMode) {
        this.checkSignature = false;
        this.detached = false;
        this.launchMode = 2;
        final StringBuilder sb = new StringBuilder();
        sb.append("<init>, appId = ");
        sb.append(appId);
        sb.append(", checkSignature = ");
        sb.append(checkSignature);
        sb.append(", launchMode = ");
        sb.append(launchMode);
        Log.d("MicroMsg.SDK.WXApiImplV10", sb.toString());
        this.context = context;
        this.appId = appId;
        this.checkSignature = checkSignature;
        this.launchMode = launchMode;
        b.a = context.getApplicationContext();
        this.securityHelper = new WXAPiSecurityHelper(context.getApplicationContext());
    }
    
    private void callbackReq(final SendReqCallback sendReqCallback, final boolean b) {
        if (sendReqCallback != null) {
            sendReqCallback.onSendFinish(b);
        }
    }
    
    private boolean checkSumConsistent(final byte[] array, final byte[] array2) {
        String s;
        if (array != null && array.length != 0 && array2 != null && array2.length != 0) {
            if (array.length == array2.length) {
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != array2[i]) {
                        return false;
                    }
                }
                return true;
            }
            s = "checkSumConsistent fail, length is different";
        }
        else {
            s = "checkSumConsistent fail, invalid arguments";
        }
        Log.e("MicroMsg.SDK.WXApiImplV10", s);
        return false;
    }
    
    private boolean createChatroom(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_create_chatroom_group_id", ""), bundle.getString("_wxapi_create_chatroom_chatroom_name", ""), bundle.getString("_wxapi_create_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_create_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean doLaunchApp(final Bundle bundle) {
        final MMessageActV2$Args mMessageActV2$Args = new MMessageActV2$Args();
        mMessageActV2$Args.bundle = bundle;
        final StringBuilder sb = new StringBuilder();
        sb.append("weixin://sendreq?appid=");
        sb.append(this.appId);
        mMessageActV2$Args.content = sb.toString();
        mMessageActV2$Args.targetPkgName = "com.tencent.mm";
        mMessageActV2$Args.targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
        mMessageActV2$Args.launchMode = this.launchMode;
        try {
            final String tokenFromWX = this.getTokenFromWX(this.context);
            if (tokenFromWX != null) {
                mMessageActV2$Args.token = tokenFromWX;
            }
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("getTokenFromWX fail, exception = ");
            sb2.append((Object)ex);
            Log.e("MicroMsg.SDK.WXApiImplV10", sb2.toString());
        }
        return MMessageActV2.send(this.context, mMessageActV2$Args);
    }
    
    private String finderShareVideoJumpInfoToString(final IWXChannelJumpInfo iwxChannelJumpInfo) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("jumpType", iwxChannelJumpInfo.type());
            if (iwxChannelJumpInfo instanceof WXChannelBaseJumpInfo) {
                jsonObject.put("wording", (Object)((WXChannelBaseJumpInfo)iwxChannelJumpInfo).wording);
                jsonObject.put("extra", (Object)((WXChannelBaseJumpInfo)iwxChannelJumpInfo).extra);
                String s;
                String s2;
                if (iwxChannelJumpInfo instanceof WXChannelJumpMiniProgramInfo) {
                    jsonObject.put("username", (Object)((WXChannelJumpMiniProgramInfo)iwxChannelJumpInfo).username);
                    s = ((WXChannelJumpMiniProgramInfo)iwxChannelJumpInfo).path;
                    s2 = "path";
                }
                else {
                    if (!(iwxChannelJumpInfo instanceof WXChannelJumpUrlInfo)) {
                        return jsonObject.toString();
                    }
                    s = ((WXChannelJumpUrlInfo)iwxChannelJumpInfo).url;
                    s2 = "url";
                }
                jsonObject.put(s2, (Object)s);
            }
            return jsonObject.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    private String getTokenFromWX(final Context context) {
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/genTokenForOpenSdk"), (String[])null, (String)null, new String[] { this.appId, "638065664" }, (String)null);
        if (query != null && query.moveToFirst()) {
            final String string = query.getString(0);
            final StringBuilder sb = new StringBuilder();
            sb.append("getTokenFromWX token is ");
            sb.append(string);
            Log.i("MicroMsg.SDK.WXApiImplV10", sb.toString());
            query.close();
            return string;
        }
        Log.e("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX , token is null , if your app targetSdkVersion >= 30, include 'com.tencent.mm' in a set of <package> elements inside the <queries> element");
        return null;
    }
    
    private boolean handleWxInternalRespType(final String s, final IWXAPIEventHandler iwxapiEventHandler) {
        final StringBuilder sb = new StringBuilder();
        sb.append("handleWxInternalRespType, extInfo = ");
        sb.append(s);
        Log.i("MicroMsg.SDK.WXApiImplV10", sb.toString());
        try {
            final Uri parse = Uri.parse(s);
            final String queryParameter = parse.getQueryParameter("wx_internal_resptype");
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("handleWxInternalRespType, respType = ");
            sb2.append(queryParameter);
            Log.i("MicroMsg.SDK.WXApiImplV10", sb2.toString());
            if (b.b(queryParameter)) {
                Log.e("MicroMsg.SDK.WXApiImplV10", "handleWxInternalRespType fail, respType is null");
                return false;
            }
            if (queryParameter.equals((Object)"subscribemessage")) {
                final SubscribeMessage.Resp resp = new SubscribeMessage.Resp();
                final String queryParameter2 = parse.getQueryParameter("ret");
                if (queryParameter2 != null && queryParameter2.length() > 0) {
                    resp.errCode = b.a(queryParameter2, 0);
                }
                resp.openId = parse.getQueryParameter("openid");
                resp.templateID = parse.getQueryParameter("template_id");
                resp.scene = b.a(parse.getQueryParameter("scene"), 0);
                resp.action = parse.getQueryParameter("action");
                resp.reserved = parse.getQueryParameter("reserved");
                iwxapiEventHandler.onResp((BaseResp)resp);
                return true;
            }
            if (queryParameter.contains((CharSequence)"invoice_auth_insert")) {
                final WXInvoiceAuthInsert.Resp resp2 = new WXInvoiceAuthInsert.Resp();
                final String queryParameter3 = parse.getQueryParameter("ret");
                if (queryParameter3 != null && queryParameter3.length() > 0) {
                    resp2.errCode = b.a(queryParameter3, 0);
                }
                resp2.wxOrderId = parse.getQueryParameter("wx_order_id");
                iwxapiEventHandler.onResp((BaseResp)resp2);
                return true;
            }
            if (queryParameter.contains((CharSequence)"payinsurance")) {
                final WXPayInsurance.Resp resp3 = new WXPayInsurance.Resp();
                final String queryParameter4 = parse.getQueryParameter("ret");
                if (queryParameter4 != null && queryParameter4.length() > 0) {
                    resp3.errCode = b.a(queryParameter4, 0);
                }
                resp3.wxOrderId = parse.getQueryParameter("wx_order_id");
                iwxapiEventHandler.onResp((BaseResp)resp3);
                return true;
            }
            if (queryParameter.contains((CharSequence)"nontaxpay")) {
                final WXNontaxPay.Resp resp4 = new WXNontaxPay.Resp();
                final String queryParameter5 = parse.getQueryParameter("ret");
                if (queryParameter5 != null && queryParameter5.length() > 0) {
                    resp4.errCode = b.a(queryParameter5, 0);
                }
                resp4.wxOrderId = parse.getQueryParameter("wx_order_id");
                iwxapiEventHandler.onResp((BaseResp)resp4);
                return true;
            }
            if ("subscribeminiprogrammsg".equals((Object)queryParameter) || "5".equals((Object)queryParameter)) {
                final SubscribeMiniProgramMsg.Resp resp5 = new SubscribeMiniProgramMsg.Resp();
                final String queryParameter6 = parse.getQueryParameter("ret");
                if (queryParameter6 != null && queryParameter6.length() > 0) {
                    resp5.errCode = b.a(queryParameter6, 0);
                }
                resp5.openId = parse.getQueryParameter("openid");
                resp5.unionId = parse.getQueryParameter("unionid");
                resp5.nickname = parse.getQueryParameter("nickname");
                resp5.errStr = parse.getQueryParameter("errmsg");
                iwxapiEventHandler.onResp((BaseResp)resp5);
                return true;
            }
            Log.e("MicroMsg.SDK.WXApiImplV10", "this open sdk version not support the request type");
        }
        catch (final Exception ex) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("handleWxInternalRespType fail, ex = ");
            sb3.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXApiImplV10", sb3.toString());
        }
        return false;
    }
    
    private boolean joinChatroom(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_join_chatroom_group_id", ""), bundle.getString("_wxapi_join_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_join_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private void launchWXIfNeed() {
        if (Build$VERSION.SDK_INT >= 29 && this.launchMode == 2) {
            this.launchWXUsingPendingIntent();
        }
        else {
            this.openWXApp();
        }
    }
    
    private void launchWXUsingPendingIntent() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        }
        if (!this.isWXAppInstalled()) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "openWXApp failed, not installed or signature check failed");
            return;
        }
        try {
            Log.i("MicroMsg.SDK.WXApiImplV10", "launchWXUsingPendingIntent");
            PendingIntent.getActivity(this.context, 1, this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"), 134217728).send(this.context, 2, (Intent)null, (PendingIntent$OnFinished)new BaseWXApiImplV10$3(this), (Handler)null);
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("launchWXUsingPendingIntent pendingIntent send failed: ");
            sb.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXApiImplV10", sb.toString());
            this.openWXApp();
        }
    }
    
    private boolean sendAddCardToWX(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendChooseCardFromWX(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/chooseCardFromWX"), (String[])null, (String)null, new String[] { bundle.getString("_wxapi_choose_card_from_wx_card_app_id"), bundle.getString("_wxapi_choose_card_from_wx_card_location_id"), bundle.getString("_wxapi_choose_card_from_wx_card_sign_type"), bundle.getString("_wxapi_choose_card_from_wx_card_card_sign"), bundle.getString("_wxapi_choose_card_from_wx_card_time_stamp"), bundle.getString("_wxapi_choose_card_from_wx_card_nonce_str"), bundle.getString("_wxapi_choose_card_from_wx_card_card_id"), bundle.getString("_wxapi_choose_card_from_wx_card_card_type"), bundle.getString("_wxapi_choose_card_from_wx_card_can_multi_select"), bundle.getString("_wxapi_basereq_transaction") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderBind(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderBind");
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderBind"), (String[])null, (String)null, new String[] { this.appId, baseReq.openId }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderOpenEvent(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenEvent");
        final WXChannelOpenEvent.Req req = (WXChannelOpenEvent.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenEvent"), (String[])null, (String)null, new String[] { this.appId, req.username, req.eventId, req.extInfo }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderOpenFeed(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenFeed");
        final WXChannelOpenFeed.Req req = (WXChannelOpenFeed.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenFeed"), (String[])null, (String)null, new String[] { this.appId, req.feedID, req.nonceID, String.valueOf(req.notGetReleatedList) }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderOpenLive(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenLive");
        final WXChannelOpenLive.Req req = (WXChannelOpenLive.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenLive"), (String[])null, (String)null, new String[] { this.appId, req.feedID, req.nonceID }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderOpenProfile(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenProfile");
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenProfile"), (String[])null, (String)null, new String[] { this.appId, ((WXChannelOpenProfile.Req)baseReq).userName }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderShareVideo(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderShareVideo");
        final WXChannelShareVideo.Req req = (WXChannelShareVideo.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderShareVideo"), (String[])null, (String)null, new String[] { this.appId, req.videoPath, "", "", req.extData, this.finderShareVideoJumpInfoToString(req.jumpInfo) }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendFinderStartLive(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        Log.i("MicroMsg.SDK.WXApiImplV10", "sendFinderStartLive");
        final WXChannelStartLive.Req req = (WXChannelStartLive.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderStartLive"), (String[])null, (String)null, new String[] { this.appId, req.liveJsonInfo, req.openId }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendHandleScanResult(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/handleScanResult"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_scan_qrcode_result") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendInvoiceAuthInsert(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[])null, (String)null, new String[] { this.appId, String.valueOf(2), URLEncoder.encode(String.format("url=%s", new Object[] { URLEncoder.encode(((WXInvoiceAuthInsert.Req)baseReq).url) })) }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendJumpToOfflinePayReq(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToOfflinePay"), (String[])null, (String)null, new String[] { this.appId }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendLaunchWXMiniprogram(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXLaunchMiniProgram.Req req = (WXLaunchMiniProgram.Req)baseReq;
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogram");
        final String appId = this.appId;
        final String userName = req.userName;
        final String path = req.path;
        final StringBuilder sb = new StringBuilder();
        sb.append(req.miniprogramType);
        sb.append("");
        final Cursor query = contentResolver.query(parse, (String[])null, (String)null, new String[] { appId, userName, path, sb.toString(), req.extData }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendLaunchWXMiniprogramWithToken(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogramWithToken"), (String[])null, (String)null, new String[] { this.appId, ((WXLaunchMiniProgramWithToken.Req)baseReq).token }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendNonTaxPay(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[])null, (String)null, new String[] { this.appId, String.valueOf(3), URLEncoder.encode(String.format("url=%s", new Object[] { URLEncoder.encode(((WXNontaxPay.Req)baseReq).url) })) }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenBusiLuckyMoney(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenBusinessView(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXOpenBusinessView.Req req = (WXOpenBusinessView.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessView"), (String[])null, (String)null, new String[] { this.appId, req.businessType, req.query, req.extInfo, req.transaction, req.openId }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenBusinessWebview(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXOpenBusinessWebview.Req req = (WXOpenBusinessWebview.Req)baseReq;
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessWebview");
        final HashMap<String, String> queryInfo = req.queryInfo;
        String string;
        if (queryInfo != null && queryInfo.size() > 0) {
            string = new JSONObject((Map)req.queryInfo).toString();
        }
        else {
            string = "";
        }
        final String appId = this.appId;
        final StringBuilder sb = new StringBuilder();
        sb.append(req.businessType);
        sb.append("");
        final Cursor query = contentResolver.query(parse, (String[])null, (String)null, new String[] { appId, sb.toString(), string }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenCustomerServiceChat(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXOpenCustomerServiceChat.Req req = (WXOpenCustomerServiceChat.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openCustomerServiceChat"), (String[])null, (String)null, new String[] { this.appId, req.corpId, req.url }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenRankListReq(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), (String[])null, (String)null, new String[0], (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendOpenWebview(final Context context, final Bundle bundle) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), (String[])null, (String)null, new String[] { this.appId, bundle.getString("_wxapi_jump_to_webview_url"), bundle.getString("_wxapi_basereq_transaction") }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendPayInSurance(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[])null, (String)null, new String[] { this.appId, String.valueOf(4), URLEncoder.encode(String.format("url=%s", new Object[] { URLEncoder.encode(((WXPayInsurance.Req)baseReq).url) })) }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendPayReq(final Context context, final Bundle bundle) {
        if (BaseWXApiImplV10.wxappPayEntryClassname == null) {
            BaseWXApiImplV10.wxappPayEntryClassname = new MMSharedPreferences(context).getString("_wxapp_pay_entry_classname_", (String)null);
            final StringBuilder sb = new StringBuilder();
            sb.append("pay, set wxappPayEntryClassname = ");
            sb.append(BaseWXApiImplV10.wxappPayEntryClassname);
            Log.d("MicroMsg.SDK.WXApiImplV10", sb.toString());
            if (BaseWXApiImplV10.wxappPayEntryClassname == null) {
                try {
                    BaseWXApiImplV10.wxappPayEntryClassname = context.getPackageManager().getApplicationInfo("com.tencent.mm", 128).metaData.getString("com.tencent.mm.BuildInfo.OPEN_SDK_PAY_ENTRY_CLASSNAME", (String)null);
                }
                catch (final Exception ex) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("get from metaData failed : ");
                    sb2.append(ex.getMessage());
                    Log.e("MicroMsg.SDK.WXApiImplV10", sb2.toString());
                }
            }
            if (BaseWXApiImplV10.wxappPayEntryClassname == null) {
                Log.e("MicroMsg.SDK.WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        final MMessageActV2$Args mMessageActV2$Args = new MMessageActV2$Args();
        mMessageActV2$Args.bundle = bundle;
        mMessageActV2$Args.targetPkgName = "com.tencent.mm";
        mMessageActV2$Args.targetClassName = BaseWXApiImplV10.wxappPayEntryClassname;
        mMessageActV2$Args.launchMode = this.launchMode;
        try {
            final String tokenFromWX = this.getTokenFromWX(context);
            if (tokenFromWX != null) {
                mMessageActV2$Args.token = tokenFromWX;
            }
        }
        catch (final Exception ex2) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("getTokenFromWX fail, exception = ");
            sb3.append((Object)ex2);
            Log.e("MicroMsg.SDK.WXApiImplV10", sb3.toString());
        }
        return MMessageActV2.send(context, mMessageActV2$Args);
    }
    
    private boolean sendPreloadWXMiniProgramEnvironment(final Context context, final BaseReq baseReq) {
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/preloadWXMiniprogramEnvironment"), (String[])null, (String)null, new String[] { this.appId, ((WXPreloadMiniProgramEnvironment.Req)baseReq).extData }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendPreloadWXMiniprogram(final Context context, final BaseReq baseReq) {
        final WXPreloadMiniProgram.Req req = (WXPreloadMiniProgram.Req)baseReq;
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/preloadWXMiniprogram");
        final String appId = this.appId;
        final String userName = req.userName;
        final String path = req.path;
        final StringBuilder sb = new StringBuilder();
        sb.append(req.miniprogramType);
        sb.append("");
        final Cursor query = contentResolver.query(parse, (String[])null, (String)null, new String[] { appId, userName, path, sb.toString(), req.extData }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendQRCodePayReq(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXQRCodePay.Req req = (WXQRCodePay.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/QRCodePay"), (String[])null, (String)null, new String[] { this.appId, req.codeContent, req.extraMsg }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendSubscribeMessage(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final SubscribeMessage.Req req = (SubscribeMessage.Req)baseReq;
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[])null, (String)null, new String[] { this.appId, String.valueOf(1), String.valueOf(req.scene), req.templateID, req.reserved }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendSubscribeMiniProgramMsg(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), (String[])null, (String)null, new String[] { this.appId, String.valueOf(5), ((SubscribeMiniProgramMsg.Req)baseReq).miniProgramAppId }, (String)null);
        if (query != null) {
            query.close();
        }
        return true;
    }
    
    private boolean sendToWxaRedirectingPage(final Context context, final BaseReq baseReq) {
        this.launchWXIfNeed();
        final WXLaunchWxaRedirectingPage.Req req = (WXLaunchWxaRedirectingPage.Req)baseReq;
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWxaOpenApiRedirectingPage");
        try {
            final ArrayList list = new ArrayList();
            list.add(0, (Object)this.appId);
            list.addAll((Collection)Arrays.asList((Object[])req.toArray()));
            final Cursor query = contentResolver.query(parse, (String[])null, (String)null, (String[])list.toArray((Object[])new String[0]), (String)null);
            if (query != null) {
                query.close();
            }
            return true;
        }
        finally {}
    }
    
    public void detach() {
        Log.d("MicroMsg.SDK.WXApiImplV10", "detach");
        this.detached = true;
        this.context = null;
    }
    
    public int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
        if (!this.isWXAppInstalled()) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return 0;
        }
        this.wxSdkVersion = 0;
        try {
            this.wxSdkVersion = this.context.getPackageManager().getApplicationInfo("com.tencent.mm", 128).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
            final StringBuilder sb = new StringBuilder();
            sb.append("OPEN_SDK_VERSION = ");
            sb.append(this.wxSdkVersion);
            Log.d("MicroMsg.SDK.WXApiImplV10", sb.toString());
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("get from metaData failed : ");
            sb2.append(ex.getMessage());
            Log.e("MicroMsg.SDK.WXApiImplV10", sb2.toString());
        }
        if (this.wxSdkVersion == 0) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            b.b.submit((Runnable)new BaseWXApiImplV10$1(this, countDownLatch));
            try {
                countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
            }
            catch (final InterruptedException ex2) {
                Log.w("MicroMsg.SDK.WXApiImplV10", ex2.getMessage());
            }
        }
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("wxSdkVersion = ");
        sb3.append(this.wxSdkVersion);
        Log.d("MicroMsg.SDK.WXApiImplV10", sb3.toString());
        return this.wxSdkVersion;
    }
    
    public boolean handleIntent(final Intent intent, final IWXAPIEventHandler iwxapiEventHandler) {
        try {
            if (!WXApiImplComm.isIntentFromWx(intent, "com.tencent.mm.openapi.token")) {
                Log.i("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, intent not from weixin msg");
                return false;
            }
            if (this.detached) {
                throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
            }
            final String stringExtra = intent.getStringExtra("_mmessage_content");
            final int intExtra = intent.getIntExtra("_mmessage_sdkVersion", 0);
            final String stringExtra2 = intent.getStringExtra("_mmessage_appPackage");
            if (stringExtra2 == null || stringExtra2.length() == 0) {
                Log.e("MicroMsg.SDK.WXApiImplV10", "invalid argument");
                return false;
            }
            if (!this.checkSumConsistent(intent.getByteArrayExtra("_mmessage_checksum"), a.a(stringExtra, intExtra, stringExtra2))) {
                Log.e("MicroMsg.SDK.WXApiImplV10", "checksum fail");
                return false;
            }
            final int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
            final StringBuilder sb = new StringBuilder();
            sb.append("handleIntent, cmd = ");
            sb.append(intExtra2);
            Log.i("MicroMsg.SDK.WXApiImplV10", sb.toString());
            switch (intExtra2) {
                default: {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("unknown cmd = ");
                    sb2.append(intExtra2);
                    Log.e("MicroMsg.SDK.WXApiImplV10", sb2.toString());
                    break;
                }
                case 42: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelBind.Resp(intent.getExtras()));
                    return true;
                }
                case 41: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelOpenEvent.Resp(intent.getExtras()));
                    return true;
                }
                case 39: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelStartLive.Resp(intent.getExtras()));
                    return true;
                }
                case 38: {
                    iwxapiEventHandler.onResp((BaseResp)new WXQRCodePay.Resp(intent.getExtras()));
                    return true;
                }
                case 37: {
                    iwxapiEventHandler.onResp((BaseResp)new WXOpenCustomerServiceChat.Resp(intent.getExtras()));
                    return true;
                }
                case 36: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelOpenFeed.Resp(intent.getExtras()));
                    return true;
                }
                case 35: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelOpenLive.Resp(intent.getExtras()));
                    return true;
                }
                case 34: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelOpenProfile.Resp(intent.getExtras()));
                    return true;
                }
                case 33: {
                    iwxapiEventHandler.onResp((BaseResp)new WXChannelShareVideo.Resp(intent.getExtras()));
                    return true;
                }
                case 32: {
                    iwxapiEventHandler.onResp((BaseResp)new WXPreloadMiniProgramEnvironment.Resp(intent.getExtras()));
                    return true;
                }
                case 31: {
                    iwxapiEventHandler.onResp((BaseResp)new SendTdiAuth.Resp(intent.getExtras()));
                    return true;
                }
                case 30: {
                    iwxapiEventHandler.onResp((BaseResp)new WXLaunchWxaRedirectingPage.Resp(intent.getExtras()));
                    return true;
                }
                case 29: {
                    iwxapiEventHandler.onResp((BaseResp)new WXLaunchMiniProgramWithToken.Resp(intent.getExtras()));
                    return true;
                }
                case 28: {
                    iwxapiEventHandler.onResp((BaseResp)new WXPreloadMiniProgram.Resp(intent.getExtras()));
                    return true;
                }
                case 27: {
                    iwxapiEventHandler.onResp((BaseResp)new WXJointPay.JointPayResp(intent.getExtras()));
                    return true;
                }
                case 26: {
                    iwxapiEventHandler.onResp((BaseResp)new WXOpenBusinessView.Resp(intent.getExtras()));
                    return true;
                }
                case 25: {
                    iwxapiEventHandler.onResp((BaseResp)new WXOpenBusinessWebview.Resp(intent.getExtras()));
                    return true;
                }
                case 24: {
                    iwxapiEventHandler.onResp((BaseResp)new JumpToOfflinePay.Resp(intent.getExtras()));
                    return true;
                }
                case 19: {
                    iwxapiEventHandler.onResp((BaseResp)new WXLaunchMiniProgram.Resp(intent.getExtras()));
                    return true;
                }
                case 17: {
                    iwxapiEventHandler.onResp((BaseResp)new HandleScanResult.Resp(intent.getExtras()));
                    return true;
                }
                case 16: {
                    iwxapiEventHandler.onResp((BaseResp)new ChooseCardFromWXCardPackage.Resp(intent.getExtras()));
                    return true;
                }
                case 15: {
                    iwxapiEventHandler.onResp((BaseResp)new JoinChatroom.Resp(intent.getExtras()));
                    return true;
                }
                case 14: {
                    iwxapiEventHandler.onResp((BaseResp)new CreateChatroom.Resp(intent.getExtras()));
                    return true;
                }
                case 12: {
                    iwxapiEventHandler.onResp((BaseResp)new OpenWebview.Resp(intent.getExtras()));
                    return true;
                }
                case 9: {
                    iwxapiEventHandler.onResp((BaseResp)new AddCardToWXCardPackage.Resp(intent.getExtras()));
                    return true;
                }
                case 6: {
                    iwxapiEventHandler.onReq((BaseReq)new LaunchFromWX.Req(intent.getExtras()));
                    return true;
                }
                case 5: {
                    iwxapiEventHandler.onResp((BaseResp)new PayResp(intent.getExtras()));
                    return true;
                }
                case 4: {
                    final ShowMessageFromWX.Req req = new ShowMessageFromWX.Req(intent.getExtras());
                    final String messageExt = req.message.messageExt;
                    if (messageExt != null && messageExt.contains((CharSequence)"wx_internal_resptype")) {
                        final boolean handleWxInternalRespType = this.handleWxInternalRespType(messageExt, iwxapiEventHandler);
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("handleIntent, extInfo contains wx_internal_resptype, ret = ");
                        sb3.append(handleWxInternalRespType);
                        Log.i("MicroMsg.SDK.WXApiImplV10", sb3.toString());
                        return handleWxInternalRespType;
                    }
                    if (messageExt != null && messageExt.contains((CharSequence)"openbusinesswebview")) {
                        try {
                            final Uri parse = Uri.parse(messageExt);
                            if (parse != null && "openbusinesswebview".equals((Object)parse.getHost())) {
                                final WXOpenBusinessWebview.Resp resp = new WXOpenBusinessWebview.Resp();
                                final String queryParameter = parse.getQueryParameter("ret");
                                if (queryParameter != null && queryParameter.length() > 0) {
                                    resp.errCode = b.a(queryParameter, 0);
                                }
                                resp.resultInfo = parse.getQueryParameter("resultInfo");
                                resp.errStr = parse.getQueryParameter("errmsg");
                                final String queryParameter2 = parse.getQueryParameter("type");
                                if (queryParameter2 != null && queryParameter2.length() > 0) {
                                    resp.businessType = b.a(queryParameter2, 0);
                                }
                                iwxapiEventHandler.onResp((BaseResp)resp);
                                return true;
                            }
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append("not openbusinesswebview %");
                            sb4.append(messageExt);
                            Log.d("MicroMsg.SDK.WXApiImplV10", sb4.toString());
                        }
                        catch (final Exception ex) {
                            final StringBuilder sb5 = new StringBuilder();
                            sb5.append("parse fail, ex = ");
                            sb5.append(ex.getMessage());
                            Log.e("MicroMsg.SDK.WXApiImplV10", sb5.toString());
                        }
                    }
                    iwxapiEventHandler.onReq((BaseReq)req);
                    return true;
                }
                case 3: {
                    iwxapiEventHandler.onReq((BaseReq)new GetMessageFromWX.Req(intent.getExtras()));
                    return true;
                }
                case 2: {
                    iwxapiEventHandler.onResp((BaseResp)new SendMessageToWX.Resp(intent.getExtras()));
                    return true;
                }
                case 1: {
                    iwxapiEventHandler.onResp((BaseResp)new SendAuth.Resp(intent.getExtras()));
                    return true;
                }
            }
        }
        catch (final Exception ex2) {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("handleIntent fail, ex = ");
            sb6.append(ex2.getMessage());
            Log.e("MicroMsg.SDK.WXApiImplV10", sb6.toString());
        }
        return false;
    }
    
    public boolean isWXAppInstalled() {
        if (!this.detached) {
            try {
                final PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
                if (packageInfo == null) {
                    Log.w("MicroMsg.SDK.WXApiImplV10", "isWXAppInstalled packageInfo is null");
                    return false;
                }
                return WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
            }
            catch (final Exception ex) {
                final StringBuilder sb = new StringBuilder();
                sb.append("isWXAppInstalled ex:");
                sb.append(ex.getMessage());
                Log.w("MicroMsg.SDK.WXApiImplV10", sb.toString());
                return false;
            }
        }
        throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
    }
    
    public boolean openWXApp() {
        if (!this.detached) {
            String string = null;
            Label_0018: {
                if (this.isWXAppInstalled()) {
                    try {
                        this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
                        return true;
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("startActivity fail, exception = ");
                        sb.append(ex.getMessage());
                        string = sb.toString();
                        break Label_0018;
                    }
                    throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
                }
                string = "open wx app failed, not installed or signature check failed";
            }
            Log.e("MicroMsg.SDK.WXApiImplV10", string);
            return false;
        }
        throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
    }
    
    public boolean registerApp(final String s) {
        return this.registerApp(s, 0L);
    }
    
    public boolean registerApp(final String s, final long d) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("registerApp, appId = ");
        sb.append(s);
        Log.d("MicroMsg.SDK.WXApiImplV10", sb.toString());
        if (s != null) {
            this.appId = s;
        }
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("registerApp, appId = ");
        sb2.append(s);
        Log.d("MicroMsg.SDK.WXApiImplV10", sb2.toString());
        if (s != null) {
            this.appId = s;
        }
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("register app ");
        sb3.append(this.context.getPackageName());
        Log.d("MicroMsg.SDK.WXApiImplV10", sb3.toString());
        final a$a a$a = new a$a();
        a$a.a = "com.tencent.mm";
        a$a.b = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
        final StringBuilder sb4 = new StringBuilder();
        sb4.append("weixin://registerapp?appid=");
        sb4.append(this.appId);
        a$a.c = sb4.toString();
        a$a.d = d;
        return a.a(this.context, a$a);
    }
    
    public boolean sendReq(final BaseReq baseReq) {
        return this.sendReq(baseReq, null);
    }
    
    public boolean sendReq(final BaseReq baseReq, final SendReqCallback sendReqCallback) {
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "sendReq failed for wechat app signature check failed");
            return false;
        }
        if (baseReq.getType() == 2) {
            final SendMessageToWX.Req req = (SendMessageToWX.Req)baseReq;
            if (req.scene == 4) {
                final SendMessageToWX$IWXSceneDataObject sceneDataObject = req.sceneDataObject;
                if (sceneDataObject instanceof WXStateSceneDataObject) {
                    final WXStateSceneDataObject wxStateSceneDataObject = (WXStateSceneDataObject)sceneDataObject;
                    final WXMediaMessage message = req.message;
                    if (message.mediaObject == null) {
                        message.mediaObject = (WXMediaMessage$IMediaObject)new WXTextObject();
                    }
                    if (req.message.getType() == 1) {
                        final String stateTitle = wxStateSceneDataObject.stateTitle;
                        if (stateTitle == null || stateTitle.length() <= 0) {
                            wxStateSceneDataObject.stateTitle = ((WXTextObject)req.message.mediaObject).text;
                        }
                    }
                }
            }
        }
        if (!baseReq.checkArgs()) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "sendReq checkArgs fail");
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("sendReq, req type = ");
        sb.append(baseReq.getType());
        Log.i("MicroMsg.SDK.WXApiImplV10", sb.toString());
        final Bundle bundle = new Bundle();
        baseReq.toBundle(bundle);
        if (baseReq.getType() == 5 || baseReq.getType() == 27) {
            return this.sendPayReq(this.context, bundle);
        }
        if (baseReq.getType() == 9) {
            return this.sendAddCardToWX(this.context, bundle);
        }
        if (baseReq.getType() == 16) {
            return this.sendChooseCardFromWX(this.context, bundle);
        }
        if (baseReq.getType() == 11) {
            return this.sendOpenRankListReq(this.context, bundle);
        }
        if (baseReq.getType() == 12) {
            return this.sendOpenWebview(this.context, bundle);
        }
        if (baseReq.getType() == 25) {
            return this.sendOpenBusinessWebview(this.context, baseReq);
        }
        if (baseReq.getType() == 13) {
            return this.sendOpenBusiLuckyMoney(this.context, bundle);
        }
        if (baseReq.getType() == 14) {
            return this.createChatroom(this.context, bundle);
        }
        if (baseReq.getType() == 15) {
            return this.joinChatroom(this.context, bundle);
        }
        if (baseReq.getType() == 17) {
            return this.sendHandleScanResult(this.context, bundle);
        }
        if (baseReq.getType() == 18) {
            return this.sendSubscribeMessage(this.context, baseReq);
        }
        if (baseReq.getType() == 28) {
            return this.sendPreloadWXMiniprogram(this.context, baseReq);
        }
        if (baseReq.getType() == 29) {
            return this.sendLaunchWXMiniprogramWithToken(this.context, baseReq);
        }
        if (baseReq.getType() == 23) {
            return this.sendSubscribeMiniProgramMsg(this.context, baseReq);
        }
        if (baseReq.getType() == 19) {
            return this.sendLaunchWXMiniprogram(this.context, baseReq);
        }
        if (baseReq.getType() == 32) {
            return this.sendPreloadWXMiniProgramEnvironment(this.context, baseReq);
        }
        if (baseReq.getType() == 30) {
            return this.sendToWxaRedirectingPage(this.context, baseReq);
        }
        if (baseReq.getType() == 26) {
            return this.sendOpenBusinessView(this.context, baseReq);
        }
        if (baseReq.getType() == 33) {
            return this.sendFinderShareVideo(this.context, baseReq);
        }
        if (baseReq.getType() == 39) {
            return this.sendFinderStartLive(this.context, baseReq);
        }
        if (baseReq.getType() == 34) {
            return this.sendFinderOpenProfile(this.context, baseReq);
        }
        if (baseReq.getType() == 35) {
            return this.sendFinderOpenLive(this.context, baseReq);
        }
        if (baseReq.getType() == 36) {
            return this.sendFinderOpenFeed(this.context, baseReq);
        }
        if (baseReq.getType() == 41) {
            return this.sendFinderOpenEvent(this.context, baseReq);
        }
        if (baseReq.getType() == 42) {
            return this.sendFinderBind(this.context, baseReq);
        }
        if (baseReq.getType() == 37) {
            return this.sendOpenCustomerServiceChat(this.context, baseReq);
        }
        if (baseReq.getType() == 38) {
            return this.sendQRCodePayReq(this.context, baseReq);
        }
        if (baseReq.getType() == 20) {
            return this.sendInvoiceAuthInsert(this.context, baseReq);
        }
        if (baseReq.getType() == 21) {
            return this.sendNonTaxPay(this.context, baseReq);
        }
        if (baseReq.getType() == 22) {
            return this.sendPayInSurance(this.context, baseReq);
        }
        if (baseReq.getType() == 24) {
            return this.sendJumpToOfflinePayReq(this.context, bundle);
        }
        if (baseReq.getType() == 2) {
            final SendMessageToWX.Req req2 = (SendMessageToWX.Req)baseReq;
            if (b.a(req2.message.getType())) {
                final WXMiniProgramObject wxMiniProgramObject = (WXMiniProgramObject)req2.message.mediaObject;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(wxMiniProgramObject.userName);
                sb2.append("@app");
                wxMiniProgramObject.userName = sb2.toString();
                final String path = wxMiniProgramObject.path;
                if (!b.b(path)) {
                    final String[] split = path.split("\\?");
                    StringBuilder sb3;
                    String s;
                    if (split.length > 1) {
                        sb3 = new StringBuilder();
                        sb3.append(split[0]);
                        sb3.append(".html?");
                        s = split[1];
                    }
                    else {
                        sb3 = new StringBuilder();
                        sb3.append(split[0]);
                        s = ".html";
                    }
                    sb3.append(s);
                    wxMiniProgramObject.path = sb3.toString();
                }
                final int scene = req2.scene;
                if (scene != 3 && scene != 1) {
                    req2.scene = 0;
                }
                baseReq.toBundle(bundle);
            }
        }
        final String mayNeedDoSecurityCheckUrl = this.securityHelper.extractMayNeedDoSecurityCheckUrl(this.appId, baseReq);
        if (b.b(mayNeedDoSecurityCheckUrl)) {
            final boolean doLaunchApp = this.doLaunchApp(bundle);
            this.callbackReq(sendReqCallback, doLaunchApp);
            return doLaunchApp;
        }
        final int doExtraSecurityCheck = this.securityHelper.doExtraSecurityCheck(this.appId, "638065664", mayNeedDoSecurityCheckUrl, (WXAPiSecurityHelper$ISecurityCheck)new WXAPiSecurityHelper$ISecurityCheck(this, bundle, sendReqCallback) {
            final BaseWXApiImplV10 this$0;
            final Bundle val$data;
            final SendReqCallback val$sendReqCallback;
            
            public void onCheckFinish(final boolean b) {
                if (b) {
                    Log.i("MicroMsg.SDK.WXApiImplV10", "WXAPiSecurityHelper, extra check do next step: check pass, doLaunchApp");
                    this.this$0.callbackReq(this.val$sendReqCallback, this.this$0.doLaunchApp(this.val$data));
                }
                else {
                    Log.i("MicroMsg.SDK.WXApiImplV10", "WXAPiSecurityHelper, extra check do next step: check fail, stop process!");
                    this.this$0.callbackReq(this.val$sendReqCallback, false);
                }
            }
        });
        if (doExtraSecurityCheck == 0) {
            Log.i("MicroMsg.SDK.WXApiImplV10", "WXAPiSecurityHelper, extra check pass, doLaunchApp");
            final boolean doLaunchApp2 = this.doLaunchApp(bundle);
            this.callbackReq(sendReqCallback, doLaunchApp2);
            return doLaunchApp2;
        }
        if (doExtraSecurityCheck == 1) {
            Log.i("MicroMsg.SDK.WXApiImplV10", "WXAPiSecurityHelper, extra check fail, return");
            this.callbackReq(sendReqCallback, false);
            return false;
        }
        Log.i("MicroMsg.SDK.WXApiImplV10", "WXAPiSecurityHelper, hold on request, extra check need to do next step");
        return true;
    }
    
    public boolean sendResp(BaseResp baseResp) {
        if (!this.detached) {
            String s;
            if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
                s = "sendResp failed for wechat app signature check failed";
            }
            else {
                if (baseResp.checkArgs()) {
                    final Bundle bundle = new Bundle();
                    baseResp.toBundle(bundle);
                    baseResp = (BaseResp)new MMessageActV2$Args();
                    ((MMessageActV2$Args)baseResp).bundle = bundle;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("weixin://sendresp?appid=");
                    sb.append(this.appId);
                    ((MMessageActV2$Args)baseResp).content = sb.toString();
                    ((MMessageActV2$Args)baseResp).targetPkgName = "com.tencent.mm";
                    ((MMessageActV2$Args)baseResp).targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
                    try {
                        final String tokenFromWX = this.getTokenFromWX(this.context);
                        if (tokenFromWX != null) {
                            ((MMessageActV2$Args)baseResp).token = tokenFromWX;
                        }
                    }
                    catch (final Exception ex) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("getTokenFromWX fail, exception = ");
                        sb2.append((Object)ex);
                        Log.e("MicroMsg.SDK.WXApiImplV10", sb2.toString());
                    }
                    return MMessageActV2.send(this.context, (MMessageActV2$Args)baseResp);
                }
                s = "sendResp checkArgs fail";
            }
            Log.e("MicroMsg.SDK.WXApiImplV10", s);
            return false;
        }
        throw new IllegalStateException("sendResp fail, WXMsgImpl has been detached");
    }
    
    public void setLogImpl(final ILog logImpl) {
        Log.setLogImpl(logImpl);
    }
    
    public void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            Log.e("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("unregisterApp, appId = ");
        sb.append(this.appId);
        Log.d("MicroMsg.SDK.WXApiImplV10", sb.toString());
        final String appId = this.appId;
        if (appId != null && appId.length() != 0) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("unregister app ");
            sb2.append(this.context.getPackageName());
            Log.d("MicroMsg.SDK.WXApiImplV10", sb2.toString());
            final a$a a$a = new a$a();
            a$a.a = "com.tencent.mm";
            a$a.b = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_UNREGISTER";
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("weixin://unregisterapp?appid=");
            sb3.append(this.appId);
            a$a.c = sb3.toString();
            a.a(this.context, a$a);
            return;
        }
        Log.e("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
    }
}
