package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GFontSizeSpan;
import com.kingagroot.component.ui.widget.richinput.html.Html;
import java.util.ArrayList;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import java.util.List;
import com.kingagroot.kingdraw.core.Html.SymbolTable;
import com.kingagroot.component.ui.widget.richinput.span.GFontFamilySpan;
import android.text.SpannableStringBuilder;

public class SpanUtil
{
    public static String SpanToString(final SpannableStringBuilder spannableStringBuilder) {
        final int length = spannableStringBuilder.length();
        int n = 0;
        final GFontFamilySpan[] array = (GFontFamilySpan[])spannableStringBuilder.getSpans(0, length, (Class)GFontFamilySpan.class);
        String string;
        String s = string = spannableStringBuilder.toString();
        if (array != null) {
            final int length2 = array.length;
            while (true) {
                string = s;
                if (n >= length2) {
                    break;
                }
                final GFontFamilySpan gFontFamilySpan = array[n];
                String replace = s;
                if (gFontFamilySpan.getFamily().equals((Object)GFontFamilyEnum.Symbol.getFontFamily())) {
                    final String substring = s.substring(spannableStringBuilder.getSpanStart((Object)gFontFamilySpan), spannableStringBuilder.getSpanEnd((Object)gFontFamilySpan));
                    replace = s.replace((CharSequence)substring, (CharSequence)SymbolTable.converToNormal(substring));
                }
                ++n;
                s = replace;
            }
        }
        return string;
    }
    
    public static List<BaseSpan> getDrawSpan(final SpannableStringBuilder spannableStringBuilder, final int n) {
        final BaseSpan[] array = (BaseSpan[])spannableStringBuilder.getSpans(n, n, (Class)BaseSpan.class);
        final ArrayList list = new ArrayList();
        if (array != null) {
            for (final BaseSpan baseSpan : array) {
                final int spanStart = spannableStringBuilder.getSpanStart((Object)baseSpan);
                final int spanEnd = spannableStringBuilder.getSpanEnd((Object)baseSpan);
                if (spanStart <= n && spanEnd > n) {
                    ((List)list).add((Object)baseSpan);
                }
                else if (spanStart == spanEnd && spanStart == n) {
                    ((List)list).add((Object)baseSpan);
                }
            }
        }
        return (List<BaseSpan>)list;
    }
    
    public static SpannableStringBuilder htmlToSpan(final String s) {
        final SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder)Html.fromHtml(s).getSpanned();
        for (int i = 0; i < spannableStringBuilder.length(); ++i) {
            if (spannableStringBuilder.charAt(i) == ' ') {
                spannableStringBuilder.replace(i, i + 1, (CharSequence)" ");
            }
        }
        return spannableStringBuilder;
    }
    
    public static void setSpanFontSize(final SpannableStringBuilder spannableStringBuilder, final int n) {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)new GFontSizeSpan(n));
        new GStyleAutoManager(1).applyStyle(spannableStringBuilder, 0, spannableStringBuilder.length(), (List)list);
    }
}
