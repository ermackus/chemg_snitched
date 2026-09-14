package com.kingagroot.kingdraw.utils;

import java.util.Comparator;

public class LetterComparator implements Comparator<String>
{
    public int compare(final String s, final String s2) {
        if (s.equals((Object)"*") || s2.equals((Object)"#")) {
            return 1;
        }
        if (!s.equals((Object)"#") && !s2.equals((Object)"*")) {
            return s.compareTo(s2);
        }
        return -1;
    }
}
