package com.kingagroot.component.ui.widget.richinput.SelectableSelector;

import android.text.Layout;
import android.widget.TextView;
import android.content.Context;

public class TextLayoutUtil
{
    public static int dp2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    public static int getHysteresisOffset(final TextView textView, final int n, int n2, int lineBottom) {
        final Layout layout = textView.getLayout();
        if (layout == null) {
            return -1;
        }
        final int lineForVertical = layout.getLineForVertical(n2);
        int n3 = lineBottom;
        if (isEndOfLineOffset(layout, lineBottom)) {
            final int n4 = (int)layout.getPrimaryHorizontal(lineBottom - 1);
            final int n5 = (int)layout.getLineRight(lineForVertical);
            n3 = lineBottom;
            if (n > n5 - (n5 - n4) / 2) {
                n3 = lineBottom - 1;
            }
        }
        final int lineForOffset = layout.getLineForOffset(n3);
        final int lineTop = layout.getLineTop(lineForOffset);
        lineBottom = layout.getLineBottom(lineForOffset);
        final int n6 = (lineBottom - lineTop) / 2;
        Label_0154: {
            if (lineForVertical != lineForOffset + 1 || n2 - lineBottom >= n6) {
                if ((lineBottom = lineForVertical) != lineForOffset - 1) {
                    break Label_0154;
                }
                lineBottom = lineForVertical;
                if (lineTop - n2 >= n6) {
                    break Label_0154;
                }
            }
            lineBottom = lineForOffset;
        }
        final int offsetForHorizontal = layout.getOffsetForHorizontal(lineBottom, (float)n);
        if ((n2 = offsetForHorizontal) < textView.getText().length() - 1) {
            final int n7 = offsetForHorizontal + 1;
            n2 = offsetForHorizontal;
            if (isEndOfLineOffset(layout, n7)) {
                final int n8 = (int)layout.getPrimaryHorizontal(offsetForHorizontal);
                lineBottom = (int)layout.getLineRight(lineBottom);
                n2 = offsetForHorizontal;
                if (n > lineBottom - (lineBottom - n8) / 2) {
                    n2 = n7;
                }
            }
        }
        return n2;
    }
    
    public static int getPreciseOffset(final TextView textView, final int n, int offsetForHorizontal) {
        final Layout layout = textView.getLayout();
        if (layout == null) {
            return -1;
        }
        offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(offsetForHorizontal), (float)n);
        if ((int)layout.getPrimaryHorizontal(offsetForHorizontal) > n) {
            return layout.getOffsetToLeftOf(offsetForHorizontal);
        }
        return offsetForHorizontal;
    }
    
    private static boolean isEndOfLineOffset(final Layout layout, final int n) {
        boolean b = true;
        if (n <= 0 || layout.getLineForOffset(n) != layout.getLineForOffset(n - 1) + 1) {
            b = false;
        }
        return b;
    }
}
