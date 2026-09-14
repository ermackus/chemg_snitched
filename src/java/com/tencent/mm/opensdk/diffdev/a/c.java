package com.tencent.mm.opensdk.diffdev.a;

import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import org.json.JSONObject;
import com.tencent.mm.opensdk.channel.a.a;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import android.os.AsyncTask;

class c extends AsyncTask<Void, Void, a>
{
    private String a;
    private String b;
    private OAuthListener c;
    private int d;
    
    public c(final String a, final OAuthListener c) {
        this.a = a;
        this.c = c;
        this.b = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", new Object[] { a });
    }
    
    protected Object doInBackground(final Object[] array) {
        final Void[] array2 = (Void[])array;
        Thread.currentThread().setName("OpenSdkNoopingTask");
        final String a = this.a;
        a a7 = null;
        OAuthErrCode a8 = null;
        Label_0678: {
            Label_0673: {
                if (a != null && a.length() != 0) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("doInBackground start ");
                    sb.append(this.isCancelled());
                    Log.i("MicroMsg.SDK.NoopingTask", sb.toString());
                    while (!this.isCancelled()) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.b);
                        String string;
                        if (this.d == 0) {
                            string = "";
                        }
                        else {
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("&last=");
                            sb3.append(this.d);
                            string = sb3.toString();
                        }
                        sb2.append(string);
                        final String string2 = sb2.toString();
                        final long currentTimeMillis = System.currentTimeMillis();
                        final byte[] a2 = com.tencent.mm.opensdk.channel.a.a.a(string2, 60000);
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        final a a3 = new a();
                        Log.d("MicroMsg.SDK.NoopingResult", "star parse NoopingResult");
                        Label_0433: {
                            OAuthErrCode a5;
                            if (a2 != null && a2.length != 0) {
                                String s2;
                                try {
                                    final String s = new String(a2, "utf-8");
                                    try {
                                        final JSONObject jsonObject = new JSONObject(s);
                                        final int int1 = jsonObject.getInt("wx_errcode");
                                        a3.c = int1;
                                        Log.d("MicroMsg.SDK.NoopingResult", String.format("nooping uuidStatusCode = %d", new Object[] { int1 }));
                                        final int c = a3.c;
                                        OAuthErrCode a4 = null;
                                        Label_0355: {
                                            Label_0351: {
                                                if (c != 408) {
                                                    if (c != 500) {
                                                        switch (c) {
                                                            case 405: {
                                                                a3.a = OAuthErrCode.WechatAuth_Err_OK;
                                                                a3.b = jsonObject.getString("wx_code");
                                                                break Label_0433;
                                                            }
                                                            case 403: {
                                                                a4 = OAuthErrCode.WechatAuth_Err_Cancel;
                                                                break Label_0355;
                                                            }
                                                            case 402: {
                                                                a4 = OAuthErrCode.WechatAuth_Err_Timeout;
                                                                break Label_0355;
                                                            }
                                                            case 404: {
                                                                break Label_0351;
                                                            }
                                                        }
                                                    }
                                                    a4 = OAuthErrCode.WechatAuth_Err_NormalErr;
                                                    break Label_0355;
                                                }
                                            }
                                            a4 = OAuthErrCode.WechatAuth_Err_OK;
                                        }
                                        a3.a = a4;
                                    }
                                    catch (final Exception ex) {
                                        s2 = String.format("parse json fail, ex = %s", new Object[] { ex.getMessage() });
                                    }
                                }
                                catch (final Exception ex2) {
                                    s2 = String.format("parse fail, build String fail, ex = %s", new Object[] { ex2.getMessage() });
                                }
                                Log.e("MicroMsg.SDK.NoopingResult", s2);
                                a5 = OAuthErrCode.WechatAuth_Err_NormalErr;
                            }
                            else {
                                Log.e("MicroMsg.SDK.NoopingResult", "parse fail, buf is null");
                                a5 = OAuthErrCode.WechatAuth_Err_NetworkErr;
                            }
                            a3.a = a5;
                        }
                        Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", new Object[] { string2, a3.a.toString(), a3.c, currentTimeMillis2 - currentTimeMillis }));
                        final OAuthErrCode a6 = a3.a;
                        if (a6 != OAuthErrCode.WechatAuth_Err_OK) {
                            Log.e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", new Object[] { a6.toString(), a3.c }));
                            a7 = a3;
                            return a7;
                        }
                        if ((this.d = a3.c) == com.tencent.mm.opensdk.diffdev.a.d.d.a()) {
                            this.c.onQrcodeScanned();
                        }
                        else {
                            if (a3.c == com.tencent.mm.opensdk.diffdev.a.d.f.a()) {
                                continue;
                            }
                            if (a3.c == com.tencent.mm.opensdk.diffdev.a.d.e.a()) {
                                final String b = a3.b;
                                if (b != null) {
                                    a7 = a3;
                                    if (b.length() != 0) {
                                        return a7;
                                    }
                                }
                                Log.e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                                a7 = a3;
                                break Label_0673;
                            }
                            continue;
                        }
                    }
                    Log.i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
                    a7 = new a();
                    a8 = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
                    break Label_0678;
                }
                Log.e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
                a7 = new a();
            }
            a8 = OAuthErrCode.WechatAuth_Err_NormalErr;
        }
        a7.a = a8;
        return a7;
    }
    
    protected void onPostExecute(final Object o) {
        final a a = (a)o;
        this.c.onAuthFinish(a.a, a.b);
    }
    
    static class a
    {
        public OAuthErrCode a;
        public String b;
        public int c;
    }
}
