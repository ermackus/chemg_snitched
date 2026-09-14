package com.kingagroot.kingdraw.core.Html;

import com.kingagroot.kingdraw.core.Html.cache.TextLineCache;
import java.util.Iterator;
import android.graphics.Canvas;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import java.util.ArrayList;
import java.util.List;

public class TextGroup extends BaseText
{
    private float lineSpace;
    private List<TextLine> textLines;
    
    public TextGroup(final String s) {
        this.textLines = (List<TextLine>)new ArrayList();
        this.lineSpace = 0.3f;
        if (KingDrawConfig.isIsPad()) {
            this.lineSpace = 0.1f;
        }
        this.parse(s);
    }
    
    private void measureDrawStartX() {
        final int size = this.textLines.size();
        final int n = 0;
        String align;
        if (size > 0) {
            align = ((TextLine)this.textLines.get(0)).getAlign();
        }
        else {
            align = "";
        }
        int i = n;
        String s = align;
        if (TextUtils.isEmpty((CharSequence)align)) {
            s = "1";
            i = n;
        }
        while (i < size) {
            final TextLine textLine = (TextLine)this.textLines.get(i);
            final boolean equals = s.equals((Object)"2");
            final float n2 = 0.0f;
            float drawStartX;
            if (equals) {
                if ((drawStartX = this.width - textLine.getWidth()) < 0.0f) {
                    drawStartX = n2;
                }
            }
            else {
                drawStartX = n2;
                if (s.equals((Object)"3")) {
                    if ((drawStartX = this.width / 2.0f - textLine.getWidth() / 2.0f) < 0.0f) {
                        drawStartX = n2;
                    }
                }
            }
            textLine.setDrawStartX(drawStartX);
            ++i;
        }
    }
    
    public void draw(final Canvas canvas, final float n, final float n2) {
        final Iterator iterator = this.textLines.iterator();
        float n3 = 0.0f;
        while (iterator.hasNext()) {
            final TextLine textLine = (TextLine)iterator.next();
            textLine.draw(canvas, n, n2 + n3);
            final float heigth = textLine.getHeigth();
            n3 += heigth + this.lineSpace * heigth;
        }
    }
    
    protected void onMeasure() {
        for (int size = this.textLines.size(), i = 0; i < size; ++i) {
            final TextLine textLine = (TextLine)this.textLines.get(i);
            textLine.measure();
            this.width = Math.max(this.width, textLine.getWidth());
            this.heigth += textLine.getHeigth();
            if (i != 0) {
                this.heigth += textLine.getHeigth() * this.lineSpace;
            }
        }
        this.measureDrawStartX();
    }
    
    protected void onMeasure(final boolean b) {
        for (int size = this.textLines.size(), i = 0; i < size; ++i) {
            final TextLine textLine = (TextLine)this.textLines.get(i);
            textLine.measure(b);
            this.width = Math.max(this.width, textLine.getWidth());
            this.heigth += textLine.getHeigth();
            if (i != 0) {
                this.heigth += textLine.getHeigth() * this.lineSpace;
            }
        }
        if (!b) {
            this.measureDrawStartX();
        }
    }
    
    protected void parse(final String s) {
        for (final String s2 : s.split("<br/>")) {
            TextLine textLine;
            if ((textLine = (TextLine)TextLineCache.getInstance().getTextLine(s2)) == null) {
                textLine = new TextLine(s2);
            }
            this.textLines.add((Object)textLine);
        }
    }
}
