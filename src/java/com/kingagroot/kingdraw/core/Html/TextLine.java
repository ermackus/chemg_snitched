package com.kingagroot.kingdraw.core.Html;

import android.graphics.RectF;
import java.util.Iterator;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;

class TextLine extends BaseText
{
    private float baselineHeight;
    private float drawStartX;
    private List<TextSpan> textSpans;
    
    public TextLine(final String s) {
        this.textSpans = (List<TextSpan>)new ArrayList();
        this.drawStartX = 0.0f;
        this.parse(s);
    }
    
    public void draw(final Canvas canvas, final float n, final float n2) {
        this.measure();
        final float baselineHeight = this.baselineHeight;
        final float drawStartX = this.drawStartX;
        final Iterator iterator = this.textSpans.iterator();
        float n3 = 0.0f;
        while (iterator.hasNext()) {
            final TextSpan textSpan = (TextSpan)iterator.next();
            textSpan.draw(canvas, n + drawStartX + n3, n2 + baselineHeight);
            n3 += textSpan.getWidth();
        }
    }
    
    public String getAlign() {
        String align;
        if (this.textSpans.size() > 0) {
            align = ((TextSpan)this.textSpans.get(0)).align;
        }
        else {
            align = "1";
        }
        return align;
    }
    
    protected void onMeasure() {
        final Iterator iterator = this.textSpans.iterator();
        float min = Float.MAX_VALUE;
        float max = -3.4028235E38f;
        while (iterator.hasNext()) {
            final TextSpan textSpan = (TextSpan)iterator.next();
            textSpan.measure();
            this.width += textSpan.getWidth();
            this.baselineHeight = Math.max(this.baselineHeight, textSpan.getAscentHeight());
            final RectF spanRect = textSpan.getSpanRect();
            min = Math.min(min, spanRect.top);
            max = Math.max(max, spanRect.bottom);
            this.heigth = Math.max(max, spanRect.bottom);
        }
        this.heigth = max - min;
    }
    
    protected void onMeasure(final boolean b) {
        final Iterator iterator = this.textSpans.iterator();
        float min = Float.MAX_VALUE;
        float max = -3.4028235E38f;
        while (iterator.hasNext()) {
            final TextSpan textSpan = (TextSpan)iterator.next();
            textSpan.measure(b);
            this.width += textSpan.getWidth();
            this.baselineHeight = Math.max(this.baselineHeight, textSpan.getAscentHeight());
            final RectF spanRect = textSpan.getSpanRect();
            min = Math.min(min, spanRect.top);
            max = Math.max(max, spanRect.bottom);
            this.heigth = Math.max(max, spanRect.bottom);
        }
        this.heigth = max - min;
    }
    
    protected void parse(String string) {
        for (final HtmlTag htmlTag : new HtmlParse().parse(string)) {
            if (htmlTag.name.equals((Object)"span")) {
                final Iterator iterator2 = htmlTag.getChildTags().iterator();
                string = "";
                while (iterator2.hasNext()) {
                    final HtmlTag htmlTag2 = (HtmlTag)iterator2.next();
                    if (htmlTag2.name.equals((Object)"text")) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append(htmlTag2.text);
                        string = sb.toString();
                    }
                }
                htmlTag.text = string;
                this.textSpans.add((Object)new TextSpan(htmlTag));
            }
        }
    }
    
    public void setDrawStartX(final float drawStartX) {
        this.drawStartX = drawStartX;
    }
}
