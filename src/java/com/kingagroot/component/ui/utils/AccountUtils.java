package com.kingagroot.component.ui.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;
import com.goodsrc.library.utils.SPUtil;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class AccountUtils
{
    public static boolean checkIpPort(final String s, final int n) {
        try {
            final Socket socket = new Socket();
            try {
                socket.connect((SocketAddress)new InetSocketAddress(s, n), 3000);
                socket.close();
                return true;
            }
            finally {
                try {
                    socket.close();
                }
                finally {
                    final Throwable t;
                    ((Throwable)s).addSuppressed(t);
                }
            }
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    public static String getCountryCode() {
        return SPUtil.getString("AREA", "areaKey", "");
    }
    
    public static String getLoginPass() {
        return MD5.convertMD5(SPUtil.getString("loginInfo", "loginUserPass", ""));
    }
    
    public static String getLoginSuffix() {
        return SPUtil.getString("loginInfo", "loginSuffix", "");
    }
    
    public static int getLoginType() {
        return SPUtil.getInt("loginInfo", "loginType", -1);
    }
    
    public static String getLoginUserName() {
        return SPUtil.getString("loginInfo", "loginUserName", "");
    }
    
    public static boolean isEmail(final String s) {
        return s != null && !"".equals((Object)s) && Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher((CharSequence)s).matches();
    }
    
    public static boolean isIpAddress(final String s) {
        if (s.length() < 7 || s.length() > 15) {
            return false;
        }
        final String[] split = s.split("\\.");
        if (split.length != 4) {
            return false;
        }
        final int length = split.length;
        int n = 0;
        while (true) {
            if (n >= length) {
                return true;
            }
            final String s2 = split[n];
            try {
                final int int1 = Integer.parseInt(s2);
                if (int1 >= 0) {
                    if (int1 <= 255) {
                        ++n;
                        continue;
                    }
                }
                return false;
            }
            catch (final Exception ex) {
                return false;
            }
            break;
        }
    }
    
    public static boolean isNetPort(final int n) {
        return n >= 0 && n <= 65535;
    }
    
    public static boolean isPhone(final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s) && "+86".equals((Object)s)) {
            return Pattern.compile("^((13[0-9])|(15[0-9])|(16[0-9])|(18[0-9])|(17[0-9])|(14[0-9])|(19[0-9]))\\d{8}$").matcher((CharSequence)s2).matches();
        }
        return TextUtils.isEmpty((CharSequence)s2) ^ true;
    }
    
    public static void setLoginInfo(final boolean b, final String s, final String s2, final int n, final String s3, final String s4) {
        if (b) {
            SPUtil.setString("loginInfo", "loginUserName", s);
            SPUtil.setString("loginInfo", "loginUserPass", MD5.convertMD5(s2));
            SPUtil.setString("loginInfo", "loginSuffix", s3);
            SPUtil.setInt("loginInfo", "loginType", n);
            SPUtil.setString("AREA", "areaKey", s4);
        }
        else {
            SPUtil.clearSharePreference("loginInfo");
        }
    }
}
