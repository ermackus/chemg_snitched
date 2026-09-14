package org.eclipse.paho.client.mqttv3.util;

public final class Strings
{
    private static final int INDEX_NOT_FOUND = -1;
    
    private Strings() {
    }
    
    public static boolean containsAny(final CharSequence charSequence, final CharSequence charSequence2) {
        return charSequence2 != null && containsAny(charSequence, toCharArray(charSequence2));
    }
    
    public static boolean containsAny(final CharSequence charSequence, final char[] array) {
        if (!isEmpty(charSequence) && !isEmpty(array)) {
            final int length = charSequence.length();
            final int length2 = array.length;
            for (int i = 0; i < length; ++i) {
                final char char1 = charSequence.charAt(i);
                for (int j = 0; j < length2; ++j) {
                    if (array[j] == char1) {
                        if (Character.isHighSurrogate(char1)) {
                            if (j == length2 - 1) {
                                return true;
                            }
                            if (i >= length - 1 || array[j + 1] != charSequence.charAt(i + 1)) {
                                continue;
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public static int countMatches(final CharSequence charSequence, final CharSequence charSequence2) {
        final boolean empty = isEmpty(charSequence);
        int n = 0;
        if (!empty && !isEmpty(charSequence2)) {
            int n2 = 0;
            while (true) {
                final int index = indexOf(charSequence, charSequence2, n);
                if (index == -1) {
                    break;
                }
                ++n2;
                n = index + charSequence2.length();
            }
            return n2;
        }
        return 0;
    }
    
    public static boolean equalsAny(final CharSequence obj, final CharSequence[] array) {
        boolean b2;
        boolean b = b2 = (obj == null && array == null);
        if (array != null) {
            for (final CharSequence charSequence : array) {
                b = (b || charSequence.equals(obj));
            }
            b2 = b;
        }
        return b2;
    }
    
    private static int indexOf(final CharSequence charSequence, final CharSequence charSequence2, final int n) {
        return charSequence.toString().indexOf(charSequence2.toString(), n);
    }
    
    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
    
    private static boolean isEmpty(final char[] array) {
        return array == null || array.length == 0;
    }
    
    private static char[] toCharArray(final CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String)charSequence).toCharArray();
        }
        final int length = charSequence.length();
        final char[] array = new char[charSequence.length()];
        for (int i = 0; i < length; ++i) {
            array[i] = charSequence.charAt(i);
        }
        return array;
    }
}
