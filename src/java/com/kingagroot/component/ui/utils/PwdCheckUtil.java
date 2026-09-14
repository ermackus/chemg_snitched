package com.kingagroot.component.ui.utils;

import android.widget.Toast;
import android.widget.EditText;
import android.content.Context;
import java.util.regex.Pattern;

public class PwdCheckUtil
{
    public static boolean isContainAll(final String s) {
        final boolean b = false;
        int i = 0;
        int n = 0;
        int n2 = 0;
        boolean b2 = false;
        while (i < s.length()) {
            int n3;
            int n4;
            if (Character.isDigit(s.charAt(i))) {
                n3 = 1;
                n4 = n2;
            }
            else if (Character.isLowerCase(s.charAt(i))) {
                n4 = 1;
                n3 = n;
            }
            else {
                n3 = n;
                n4 = n2;
                if (Character.isUpperCase(s.charAt(i))) {
                    b2 = true;
                    n4 = n2;
                    n3 = n;
                }
            }
            ++i;
            n = n3;
            n2 = n4;
        }
        boolean b3 = b;
        if (n != 0) {
            b3 = b;
            if (n2 != 0) {
                b3 = b;
                if (b2) {
                    b3 = b;
                    if (s.matches("^[a-zA-Z0-9]+$")) {
                        b3 = true;
                    }
                }
            }
        }
        return b3;
    }
    
    public static boolean isContainLetterAndDigit(final String s) {
        return Pattern.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]").matcher((CharSequence)s).matches();
    }
    
    public static boolean isLetterDigit(final String s) {
        final boolean b = false;
        int i = 0;
        int n = 0;
        boolean b2 = false;
        while (i < s.length()) {
            int n2;
            if (Character.isDigit(s.charAt(i))) {
                n2 = 1;
            }
            else {
                n2 = n;
                if (Character.isLetter(s.charAt(i))) {
                    b2 = true;
                    n2 = n;
                }
            }
            ++i;
            n = n2;
        }
        boolean b3 = b;
        if (n != 0) {
            b3 = b;
            if (b2) {
                b3 = b;
                if (s.matches("^[a-zA-Z0-9]+$")) {
                    b3 = true;
                }
            }
        }
        return b3;
    }
    
    public static boolean isLetterOrDigit(final String s) {
        final boolean b = false;
        int i = 0;
        boolean b2 = false;
        while (i < s.length()) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                b2 = true;
            }
            ++i;
        }
        boolean b3 = b;
        if (b2) {
            b3 = b;
            if (s.matches("^[a-zA-Z0-9]+$")) {
                b3 = true;
            }
        }
        return b3;
    }
    
    public static void whatIsInput(final Context context, final EditText editText) {
        final String string = editText.getText().toString();
        if (Pattern.compile("[0-9]*").matcher((CharSequence)string).matches()) {
            Toast.makeText(context, (CharSequence)"\u8f93\u5165\u7684\u662f\u6570\u5b57", 0).show();
        }
        if (Pattern.compile("[a-zA-Z]").matcher((CharSequence)string).matches()) {
            Toast.makeText(context, (CharSequence)"\u8f93\u5165\u7684\u662f\u5b57\u6bcd", 0).show();
        }
        if (Pattern.compile("[\u4e00-\u9fa5]").matcher((CharSequence)string).matches()) {
            Toast.makeText(context, (CharSequence)"\u8f93\u5165\u7684\u662f\u6c49\u5b57", 0).show();
        }
    }
}
