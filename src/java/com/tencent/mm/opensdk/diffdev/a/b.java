package com.tencent.mm.opensdk.diffdev.a;

import android.util.Base64;
import org.json.JSONObject;
import android.os.Build$VERSION;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.channel.a.a;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import android.os.AsyncTask;

public class b extends AsyncTask<Void, Void, a>
{
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private OAuthListener f;
    private c g;
    
    public b(final String a, final String b, final String c, final String d, final String e, final OAuthListener f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public boolean a() {
        Log.i("MicroMsg.SDK.GetQRCodeTask", "cancelTask");
        final c g = this.g;
        if (g == null) {
            return this.cancel(true);
        }
        return g.cancel(true);
    }
    
    protected Object doInBackground(final Object[] array) {
        final Void[] array2 = (Void[])array;
        Thread.currentThread().setName("OpenSdkGetQRCodeTask");
        Log.i("MicroMsg.SDK.GetQRCodeTask", "doInBackground");
        final String format = String.format("https://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s", new Object[] { this.a, this.c, this.d, this.b, this.e });
        final long currentTimeMillis = System.currentTimeMillis();
        final byte[] a = com.tencent.mm.opensdk.channel.a.a.a(format, 60000);
        Log.d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", new Object[] { format, System.currentTimeMillis() - currentTimeMillis }));
        return com.tencent.mm.opensdk.diffdev.a.b.a.a(a);
    }
    
    protected void onPostExecute(final Object o) {
        final a a = (a)o;
        final OAuthErrCode a2 = a.a;
        if (a2 == OAuthErrCode.WechatAuth_Err_OK) {
            final StringBuilder sb = new StringBuilder();
            sb.append("onPostExecute, get qrcode success imgBufSize = ");
            sb.append(a.e.length);
            Log.d("MicroMsg.SDK.GetQRCodeTask", sb.toString());
            this.f.onAuthGotQrcode(a.d, a.e);
            final c g = new c(a.b, this.f);
            this.g = g;
            if (Build$VERSION.SDK_INT >= 11) {
                g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
            }
            else {
                g.execute((Object[])new Void[0]);
            }
        }
        else {
            Log.e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", new Object[] { a2 }));
            this.f.onAuthFinish(a.a, null);
        }
    }
    
    static class a
    {
        public OAuthErrCode a;
        public String b;
        public String c;
        public String d;
        public byte[] e;
        
        private a() {
        }
        
        public static a a(final byte[] array) {
            final a a = new a();
            while (true) {
                Label_0292: {
                    if (array == null) {
                        break Label_0292;
                    }
                    if (array.length == 0) {
                        break Label_0292;
                    }
                    while (true) {
                        try {
                            final String s = new String(array, "utf-8");
                            String s2;
                            try {
                                final JSONObject jsonObject = new JSONObject(s);
                                final int int1 = jsonObject.getInt("errcode");
                                if (int1 != 0) {
                                    Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", new Object[] { int1 }));
                                    a.a = OAuthErrCode.WechatAuth_Err_NormalErr;
                                    jsonObject.optString("errmsg");
                                    return a;
                                }
                                final String string = jsonObject.getJSONObject("qrcode").getString("qrcodebase64");
                                if (string == null || string.length() == 0) {
                                    Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                                    a.a = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                                    return a;
                                }
                                final byte[] decode = Base64.decode(string, 0);
                                if (decode != null && decode.length != 0) {
                                    a.a = OAuthErrCode.WechatAuth_Err_OK;
                                    a.e = decode;
                                    a.b = jsonObject.getString("uuid");
                                    final String string2 = jsonObject.getString("appname");
                                    a.c = string2;
                                    Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", new Object[] { a.b, string2, a.e.length }));
                                    return a;
                                }
                                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                                a.a = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                                return a;
                            }
                            catch (final Exception ex) {
                                s2 = String.format("parse json fail, ex = %s", new Object[] { ex.getMessage() });
                            }
                            Log.e("MicroMsg.SDK.GetQRCodeResult", s2);
                            final OAuthErrCode a2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                            a.a = a2;
                            return a;
                        }
                        catch (final Exception ex2) {
                            final String s2 = String.format("parse fail, build String fail, ex = %s", new Object[] { ex2.getMessage() });
                            continue;
                        }
                        break;
                    }
                }
                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
                final OAuthErrCode a2 = OAuthErrCode.WechatAuth_Err_NetworkErr;
                continue;
            }
        }
    }
}
