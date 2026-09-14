package com.goodsrc.library.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Locale;

public class StringUtils
{
    public static String format(final String s, final Object... array) {
        return String.format(Locale.ENGLISH, s, array);
    }
    
    public static String reFileName(final String s) {
        final Matcher matcher = Pattern.compile("\\\\|\\/|\\*|\n|\\?|\\:|\\<|\\>|\\|| |\"").matcher((CharSequence)s);
        matcher.find();
        return matcher.replaceAll("");
    }
    
    public static List<String> separateCLable(final String s) {
        final Character value = ' ';
        final ArrayList list = new ArrayList();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final char[] charArray = s.toCharArray();
            int i = 0;
            Character c = value;
            while (i < charArray.length) {
                final Character value2 = charArray[i];
                if (Character.isSpaceChar((char)value2)) {
                    ((List)list).add((Object)String.valueOf((Object)value2));
                }
                else if (Character.isUpperCase((char)value2)) {
                    ((List)list).add((Object)String.valueOf((Object)value2));
                }
                else if (Character.isLowerCase((char)value2)) {
                    if (!Character.isUpperCase((char)c) && !Character.isLowerCase((char)c)) {
                        ((List)list).add((Object)String.valueOf((Object)value2));
                    }
                    else {
                        final int n = ((List)list).size() - 1;
                        final StringBuilder sb = new StringBuilder();
                        sb.append((String)((List)list).get(n));
                        sb.append((Object)value2);
                        ((List)list).set(n, (Object)sb.toString());
                    }
                }
                else if (Character.isDigit((char)value2)) {
                    if (!Character.isDigit((char)c) && !Character.isUpperCase((char)c) && !Character.isLowerCase((char)c)) {
                        ((List)list).add((Object)String.valueOf((Object)value2));
                    }
                    else {
                        final int n2 = ((List)list).size() - 1;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append((String)((List)list).get(n2));
                        sb2.append((Object)value2);
                        ((List)list).set(n2, (Object)sb2.toString());
                    }
                }
                else {
                    ((List)list).add((Object)String.valueOf((Object)value2));
                }
                ++i;
                c = value2;
            }
        }
        return (List<String>)list;
    }
    
    public static String[] separateTextWithSubCount(final String s) {
        final boolean matches = Pattern.compile("^[^0-9]{1,}\\d*$").matcher((CharSequence)s).matches();
        String group;
        final String s2 = group = "";
        String replace = s;
        if (matches) {
            final Matcher matcher = Pattern.compile("\\d*$").matcher((CharSequence)s);
            group = s2;
            replace = s;
            if (matcher.find()) {
                group = matcher.group(0);
                replace = s.replace((CharSequence)group, (CharSequence)"");
            }
        }
        return new String[] { replace, group };
    }
}
