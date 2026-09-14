package com.kingagroot.component.ui.widget.richinput.view;

import java.lang.reflect.Field;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.Html.SymbolTable;
import com.kingagroot.component.ui.widget.richinput.GFontFamilyEnum;
import android.text.SpannableStringBuilder;
import android.text.Editable;

public class SymbolInputFilter
{
    public static Editable filter(final Editable editable, final String s, int n, final int n2) {
        try {
            final int length = editable.length();
            final Field declaredField = ((SpannableStringBuilder)editable).getClass().getDeclaredField("mText");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                final char[] array = (char[])declaredField.get((Object)editable);
                while (n < length && n <= n2) {
                    final char c = array[n];
                    String s2;
                    if (s.equals((Object)GFontFamilyEnum.Symbol.getFontFamily())) {
                        s2 = SymbolTable.convertToSymbol(c);
                    }
                    else {
                        s2 = SymbolTable.converToNormal(c);
                    }
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        array[n] = s2.charAt(0);
                    }
                    ++n;
                }
                declaredField.set((Object)editable, (Object)array);
                declaredField.setAccessible(false);
            }
        }
        catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
        return editable;
    }
}
