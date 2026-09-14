package com.kingagroot.kingdraw.utils;

import java.security.NoSuchAlgorithmException;
import com.goodsrc.library.utils.Base64Encoder;
import java.security.MessageDigest;
import android.text.TextUtils;

public class ShaUtil
{
    public static String sha(String encode) {
        if (TextUtils.isEmpty((CharSequence)encode)) {
            return "";
        }
        try {
            encode = Base64Encoder.encode(MessageDigest.getInstance("sha-256").digest(encode.getBytes()));
            return encode;
        }
        catch (final NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
