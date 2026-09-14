package com.kingagroot.kingdraw.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileTagUtils
{
    private static final String TAG_FLAG = "#";
    
    public static boolean fileTagContainTags(final String s, final List<String> list) {
        if (list != null) {
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                if (!s.contains((CharSequence)tagFormat((String)iterator.next()))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static List<String> stringToList(String s) {
        final ArrayList list = new ArrayList();
        final String[] split = s.split(",");
        if (split != null) {
            for (int length = split.length, i = 0; i < length; ++i) {
                s = split[i];
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    ((List)list).add((Object)s.substring(1, s.length() - 1));
                }
            }
        }
        return (List<String>)list;
    }
    
    public static String tagFormat(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(s);
        sb.append("#");
        sb.append(",");
        return sb.toString();
    }
    
    public static String tagsFormat(final List<String> list) {
        String s = "";
        if (list != null) {
            final Iterator iterator = list.iterator();
            String string = s;
            while (true) {
                s = string;
                if (!iterator.hasNext()) {
                    break;
                }
                final String s2 = (String)iterator.next();
                final StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append("#");
                sb.append(s2);
                sb.append("#");
                sb.append(",");
                string = sb.toString();
            }
        }
        return s;
    }
}
