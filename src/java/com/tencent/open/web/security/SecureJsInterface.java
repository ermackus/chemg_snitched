package com.tencent.open.web.security;

import com.tencent.open.log.SLog;
import com.tencent.open.a$b;

public class SecureJsInterface extends a$b
{
    public static boolean isPWDEdit;
    private String a;
    
    public void clearAllEdit() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        }
        catch (final Exception ex) {
            final StringBuilder sb = new StringBuilder();
            sb.append("-->clear all edit exception: ");
            sb.append(ex.getMessage());
            SLog.e("openSDK_LOG.SecureJsInterface", sb.toString());
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public void curPosFromJS(String a) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->curPosFromJS: ");
        sb.append(a);
        SLog.d("openSDK_LOG.SecureJsInterface", sb.toString());
        int int1;
        try {
            int1 = Integer.parseInt(a);
        }
        catch (final NumberFormatException ex) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", (Throwable)ex);
            int1 = -1;
        }
        if (int1 >= 0) {
            final boolean c = a.c;
            if (a.b) {
                if ((boolean)JniInterface.BackSpaceChar(a.b, int1)) {
                    a.b = false;
                }
            }
            else {
                a = a.a;
                JniInterface.insetTextToArray(int1, this.a = a, a.length());
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("curPosFromJS mKey: ");
                sb2.append(this.a);
                SLog.v("openSDK_LOG.SecureJsInterface", sb2.toString());
            }
            return;
        }
        throw new RuntimeException("position is illegal.");
    }
    
    public boolean customCallback() {
        return true;
    }
    
    public String getMD5FromNative() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        try {
            final String pwdKeyToMD5 = JniInterface.getPWDKeyToMD5((String)null);
            final StringBuilder sb = new StringBuilder();
            sb.append("-->getMD5FromNative, MD5= ");
            sb.append(pwdKeyToMD5);
            SLog.v("openSDK_LOG.SecureJsInterface", sb.toString());
            return pwdKeyToMD5;
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("-->get md5 form native exception: ");
            sb2.append(ex.getMessage());
            SLog.e("openSDK_LOG.SecureJsInterface", sb2.toString());
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public void isPasswordEdit(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("-->is pswd edit, flag: ");
        sb.append(s);
        SLog.i("openSDK_LOG.SecureJsInterface", sb.toString());
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (final Exception ex) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("-->is pswd edit exception: ");
            sb2.append(ex.getMessage());
            SLog.e("openSDK_LOG.SecureJsInterface", sb2.toString());
            int1 = -1;
        }
        if (int1 != 0 && int1 != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        }
        if (int1 == 0) {
            SecureJsInterface.isPWDEdit = false;
        }
        else if (int1 == 1) {
            SecureJsInterface.isPWDEdit = true;
        }
    }
}
