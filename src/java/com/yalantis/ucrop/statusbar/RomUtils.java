package com.yalantis.ucrop.statusbar;

import java.util.regex.Pattern;
import android.os.Build$VERSION;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.text.TextUtils;
import android.os.Build;

public class RomUtils
{
    private static final String[] ROM_SAMSUNG;
    private static final String UNKNOWN = "unknown";
    private static Integer romType;
    
    static {
        ROM_SAMSUNG = new String[] { "samsung" };
    }
    
    private static String getBrand() {
        try {
            final String brand = Build.BRAND;
            if (!TextUtils.isEmpty((CharSequence)brand)) {
                return brand.toLowerCase();
            }
            return "unknown";
        }
        finally {
            return "unknown";
        }
    }
    
    public static int getFlymeVersion() {
        final String display = Build.DISPLAY;
        if (!TextUtils.isEmpty((CharSequence)display) && display.contains((CharSequence)"Flyme")) {
            return stringToInt(display.replaceAll("Flyme", "").replaceAll("OS", "").replaceAll(" ", "").substring(0, 1));
        }
        return 0;
    }
    
    public static int getLightStatausBarAvailableRomType() {
        final Integer romType = RomUtils.romType;
        if (romType != null) {
            return romType;
        }
        if (isMIUIV6OrAbove()) {
            return RomUtils.romType = 1;
        }
        if (isFlymeV4OrAbove()) {
            return RomUtils.romType = 2;
        }
        if (isAndroid5OrAbove()) {
            return RomUtils.romType = 3;
        }
        return RomUtils.romType = 4;
    }
    
    public static int getMIUIVersionCode() {
        final String systemProperty = getSystemProperty();
        if (!TextUtils.isEmpty((CharSequence)systemProperty)) {
            try {
                return toInt(systemProperty);
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
    
    private static String getManufacturer() {
        try {
            final String manufacturer = Build.MANUFACTURER;
            if (!TextUtils.isEmpty((CharSequence)manufacturer)) {
                return manufacturer.toLowerCase();
            }
            return "unknown";
        }
        finally {
            return "unknown";
        }
    }
    
    private static String getSystemProperty() {
        BufferedReader bufferedReader2;
        try {
            final BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(Runtime.getRuntime().exec("getprop ro.miui.ui.version.code").getInputStream()), 1024);
            try {
                final String line = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                }
                catch (final IOException ex) {
                    ex.printStackTrace();
                }
                return line;
            }
            catch (final IOException ex2) {}
        }
        catch (final IOException ex3) {
            bufferedReader2 = null;
        }
        finally {
            bufferedReader2 = null;
        }
        try {
            bufferedReader2.close();
            goto Label_0083;
        }
        catch (final IOException ex4) {}
        if (bufferedReader2 != null) {
            try {
                bufferedReader2.close();
            }
            catch (final IOException ex5) {
                ex5.printStackTrace();
            }
        }
        return null;
    }
    
    private static boolean isAndroid5OrAbove() {
        return Build$VERSION.SDK_INT >= 21;
    }
    
    private static boolean isFlymeV4OrAbove() {
        return getFlymeVersion() >= 4;
    }
    
    private static boolean isMIUIV6OrAbove() {
        final String systemProperty = getSystemProperty();
        if (!TextUtils.isEmpty((CharSequence)systemProperty)) {
            try {
                if (toInt(systemProperty) >= 4) {
                    return true;
                }
            }
            catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    private static boolean isRightRom(final String s, final String s2, final String... array) {
        for (final String s3 : array) {
            if (s.contains((CharSequence)s3) || s2.contains((CharSequence)s3)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isSamsung() {
        return isRightRom(getBrand(), getManufacturer(), RomUtils.ROM_SAMSUNG);
    }
    
    public static int stringToInt(final String s) {
        int int1;
        if (Pattern.compile("^[-\\+]?[\\d]+$").matcher((CharSequence)s).matches()) {
            int1 = toInt(s);
        }
        else {
            int1 = 0;
        }
        return int1;
    }
    
    public static int toInt(final Object o) {
        return toInt(o, 0);
    }
    
    public static int toInt(final Object o, int n) {
        if (o == null) {
            return n;
        }
        try {
            final String trim = o.toString().trim();
            if (trim.contains((CharSequence)".")) {
                n = Integer.parseInt(trim.substring(0, trim.lastIndexOf(".")));
            }
            else {
                n = Integer.parseInt(trim);
            }
            return n;
        }
        catch (final Exception ex) {
            return n;
        }
    }
    
    public static class AvailableRomType
    {
        public static final int ANDROID_NATIVE = 3;
        public static final int FLYME = 2;
        public static final int MIUI = 1;
        public static final int NA = 4;
    }
}
