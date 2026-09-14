package com.tencent.open.web.security;

import android.content.Context;
import com.tencent.open.log.SLog;
import java.io.File;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.open.utils.f;

public class JniInterface
{
    public static boolean isJniOk;
    
    public static native boolean BackSpaceChar(final boolean p0, final int p1);
    
    public static native boolean clearAllPWD();
    
    public static native String d1(final String p0);
    
    public static native String d2(final String p0);
    
    public static native String getPWDKeyToMD5(final String p0);
    
    public static native boolean insetTextToArray(final int p0, final String p1, final int p2);
    
    public static void loadSo() {
        if (JniInterface.isJniOk) {
            return;
        }
        try {
            final Context a = f.a();
            if (a != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append(a.getFilesDir().toString());
                sb.append("/");
                sb.append(AuthAgent.SECURE_LIB_NAME);
                if (new File(sb.toString()).exists()) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(a.getFilesDir().toString());
                    sb2.append("/");
                    sb2.append(AuthAgent.SECURE_LIB_NAME);
                    System.load(sb2.toString());
                    JniInterface.isJniOk = true;
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("-->load lib success:");
                    sb3.append(AuthAgent.SECURE_LIB_NAME);
                    SLog.i("openSDK_LOG.JniInterface", sb3.toString());
                }
                else {
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("-->fail, because so is not exists:");
                    sb4.append(AuthAgent.SECURE_LIB_NAME);
                    SLog.i("openSDK_LOG.JniInterface", sb4.toString());
                }
            }
            else {
                final StringBuilder sb5 = new StringBuilder();
                sb5.append("-->load lib fail, because context is null:");
                sb5.append(AuthAgent.SECURE_LIB_NAME);
                SLog.i("openSDK_LOG.JniInterface", sb5.toString());
            }
        }
        finally {
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("-->load lib error:");
            sb6.append(AuthAgent.SECURE_LIB_NAME);
            final Throwable t;
            SLog.e("openSDK_LOG.JniInterface", sb6.toString(), t);
        }
    }
}
