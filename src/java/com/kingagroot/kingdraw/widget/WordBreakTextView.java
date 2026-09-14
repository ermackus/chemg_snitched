package com.kingagroot.kingdraw.widget;

import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.goodsrc.library.utils.StringUtils;
import android.view.View$MeasureSpec;
import android.text.TextUtils;
import android.graphics.Canvas;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Style;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;

public class WordBreakTextView extends AppCompatTextView
{
    private int color;
    private Context context;
    private Object[] items;
    private Paint paint;
    private String text;
    private char[] textChars;
    private int viewHeight;
    private int viewWidth;
    
    public WordBreakTextView(final Context context) {
        super(context);
        this.color = -16777216;
        this.init(context);
    }
    
    public WordBreakTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.color = -16777216;
        this.init(context);
    }
    
    public WordBreakTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.color = -16777216;
        this.init(context);
    }
    
    private void init(final Context context) {
        this.context = context;
        (this.paint = new Paint()).setAntiAlias(true);
        this.paint.setStyle(Paint$Style.FILL);
        this.paint.setTextSize(this.getTextSize());
    }
    
    private void measure() {
        final char[] textChars = this.textChars;
        final int n = 0;
        int i = 0;
        int n2 = n;
        if (textChars != null) {
            n2 = n;
            if (textChars.length > 0) {
                float n3 = 0.0f;
                final int length = textChars.length;
                n2 = 1;
                while (i < length) {
                    final float measureText = this.paint.measureText(String.valueOf(textChars[i]));
                    n3 += measureText;
                    int n4 = n2;
                    if (n3 > this.viewWidth) {
                        n4 = n2 + 1;
                        n3 = measureText;
                    }
                    ++i;
                    n2 = n4;
                }
            }
        }
        final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
        final int viewHeight = (int)((fontMetrics.bottom - fontMetrics.top) * n2);
        this.viewHeight = viewHeight;
        this.setMeasuredDimension(this.viewWidth, viewHeight);
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty((CharSequence)this.text)) {
            final String[] split = this.text.split("%s");
            final Paint$FontMetrics fontMetrics = this.paint.getFontMetrics();
            final float bottom = fontMetrics.bottom;
            final float top = fontMetrics.top;
            float n = -fontMetrics.top;
            int i = 0;
            float n2 = 0.0f;
            while (i < split.length) {
                final String s = split[i];
                final Object[] items = this.items;
                String string;
                if (i < items.length) {
                    string = items[i].toString();
                }
                else {
                    string = "";
                }
                final StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append(string);
                final char[] charArray = sb.toString().toCharArray();
                final int length = s.length();
                float n3;
                for (int j = 0; j < charArray.length; ++j, n = n3) {
                    if (j < length) {
                        this.paint.setColor(this.color);
                    }
                    else {
                        this.paint.setColor(-12534529);
                    }
                    final String value = String.valueOf(charArray[j]);
                    final float measureText = this.paint.measureText(value);
                    n3 = n;
                    float n4 = n2;
                    if (measureText + n2 > this.viewWidth) {
                        n3 = n + (bottom - top);
                        n4 = 0.0f;
                    }
                    canvas.drawText(value, n4, n3, this.paint);
                    n2 = n4 + measureText;
                }
                ++i;
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.viewWidth = View$MeasureSpec.getSize(n);
        this.measure();
    }
    
    public void setText(final String text, final Object... items) {
        this.text = text;
        this.items = items;
        if (TextUtils.isEmpty((CharSequence)text)) {
            this.textChars = null;
        }
        else {
            this.textChars = StringUtils.format(text, items).toCharArray();
        }
        this.measure();
        this.invalidate();
    }
    
    public void setTextColor(final int color) {
        this.color = color;
    }
    
    public void setTextSize(final float n) {
        this.paint.setTextSize((float)GDensityUtil.sp2px(n));
        this.invalidate();
    }
}
