package com.kingagroot.kingdraw.core.data;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ProtocolUtils
{
    static {
        System.loadLibrary("kingdrawCore-data");
    }
    
    public static boolean CheckKDXVersionAllow(final String s) {
        return getKDXFileVersion(s) <= getCurrentKDXVersion();
    }
    
    public static boolean CheckKingVersionAllow(final String s) {
        try {
            if (compareVersion(new JSONObject(getCurrentKingVersion()).getString("version"), new JSONObject(getKingFileVersion(s)).getString("version")) >= 0) {
                return true;
            }
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static String chemFormulaParse(String nativeChemFormulaParse) {
        nativeChemFormulaParse = nativeChemFormulaParse(nativeChemFormulaParse);
        final DataOutModel dataOutModel = new DataOutModel();
        dataOutModel.parse(nativeChemFormulaParse);
        return dataOutModel.content;
    }
    
    private static int compareVersion(String split, final String s) {
        final boolean equals = split.equals((Object)s);
        int n = 0;
        if (equals) {
            return 0;
        }
        split = (String)(Object)split.split("\\.");
        final String[] split2 = s.split("\\.");
        final int min = Math.min(split.length, split2.length);
        long n2 = 0L;
        long n3;
        while (true) {
            n3 = n2;
            if (n < min) {
                try {
                    n2 = (n3 = Integer.parseInt(split[n]) - Integer.parseInt(split2[n]));
                    if (n2 == 0L) {
                        ++n;
                        continue;
                    }
                }
                catch (final NumberFormatException ex) {
                    ex.printStackTrace();
                    n3 = n2;
                }
                break;
            }
            break;
        }
        int n4;
        if (n3 != 0L) {
            n4 = (int)n3;
        }
        else {
            n4 = split.length - split2.length;
        }
        return n4;
    }
    
    public static native String convertFindChirlity(final String p0);
    
    public static native String convertKDJson2Smarts(final String p0);
    
    public static native String convertMol2Smarts(final String p0);
    
    public static native String convertSmiles2Smarts(final String p0);
    
    public static native String convertTFDatatoKdJson(final String p0);
    
    public static native String convertTFDatatoKing(final String p0);
    
    public static native boolean exceedMolV2000(final String p0);
    
    public static native int getCurrentKDXVersion();
    
    public static native String getCurrentKingVersion();
    
    public static DataOutModel getKDXFilePics(String kdxPicsInfo) {
        kdxPicsInfo = getKDXPicsInfo(kdxPicsInfo);
        final DataOutModel dataOutModel = new DataOutModel();
        dataOutModel.parse(kdxPicsInfo);
        return dataOutModel;
    }
    
    public static native int getKDXFileVersion(final String p0);
    
    private static native String getKDXPicsInfo(final String p0);
    
    public static native String getKingFileVersion(final String p0);
    
    public static native boolean isKing(final String p0);
    
    public static native boolean isMol(final String p0);
    
    public static native boolean isMolV2000(final String p0);
    
    public static boolean isMolV2000File(String molHead) {
        molHead = readMolHead(molHead);
        return !TextUtils.isEmpty((CharSequence)molHead) && isMolV2000(molHead);
    }
    
    public static native boolean isMolV3000(final String p0);
    
    public static boolean isMolV3000File(String molHead) {
        molHead = readMolHead(molHead);
        return !TextUtils.isEmpty((CharSequence)molHead) && isMolV3000(molHead);
    }
    
    public static native boolean isSmiles(final String p0);
    
    private static native String nativeChemFormulaParse(final String p0);
    
    public static String readMolHead(String s) {
        try {
            final StringBuilder sb = new StringBuilder();
            final File file = new File(s);
            if (file.exists()) {
                final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(file)));
                int n = 0;
                int n2;
                do {
                    s = bufferedReader.readLine();
                    if (s == null) {
                        break;
                    }
                    n2 = n + 1;
                    sb.append(s);
                    sb.append("\n");
                } while ((n = n2) < 4);
            }
            s = sb.toString();
            return s;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
