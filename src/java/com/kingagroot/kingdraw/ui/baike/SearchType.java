package com.kingagroot.kingdraw.ui.baike;

import com.kingagroot.kingdraw.utils.ChemTextVerifyUtil;
import com.kingagroot.kingdraw.core.data.ProtocolConverter;
import java.util.regex.Pattern;
import android.text.TextUtils;

public class SearchType
{
    public static boolean isCasNum(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final boolean b = false;
        if (empty) {
            return false;
        }
        boolean b2 = b;
        if (s.contains((CharSequence)"-")) {
            final String[] split = s.split("-");
            b2 = b;
            if (split.length == 3) {
                b2 = b;
                if (isPositiveInteger(split[0])) {
                    b2 = b;
                    if (isPositiveInteger(split[1])) {
                        b2 = b;
                        if (isPositiveInteger(split[2])) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public static boolean isInChI(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.startsWith("InChI=");
    }
    
    public static boolean isPositiveInteger(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && Pattern.compile("^[1-9]\\d*$").matcher((CharSequence)s).matches();
    }
    
    public static boolean isSmiles(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && (TextUtils.isEmpty((CharSequence)ProtocolConverter.smilesToMol(s)) ^ true);
    }
    
    public static boolean ismf(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && new ChemTextVerifyUtil().isSupGroup(s);
    }
}
