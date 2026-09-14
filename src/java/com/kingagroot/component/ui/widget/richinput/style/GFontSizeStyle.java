package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.GFontSizeButton;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import android.text.Editable;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GFontSizeSpan;

public class GFontSizeStyle extends MetricAffectingStyle<GFontSizeSpan>
{
    int fontSize;
    
    public GFontSizeStyle() {
        this.fontSize = 10;
    }
    
    public GFontSizeStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
        this.fontSize = 10;
    }
    
    private int transfFontSize() {
        return GDensityUtil.sp2px((float)this.getFontSize());
    }
    
    public void addSpan(final Editable editable, final int n, final int n2) {
        if (n < n2) {
            final GFontSizeSpan[] array = (GFontSizeSpan[])editable.getSpans(n, n2, this.styleClass);
            Object o = null;
            GFontSizeSpan gFontSizeSpan = null;
            if (array != null) {
                final int length = array.length;
                int n3 = 0;
                while (true) {
                    o = gFontSizeSpan;
                    if (n3 >= length) {
                        break;
                    }
                    final GFontSizeSpan gFontSizeSpan2 = array[n3];
                    if (gFontSizeSpan2.getSize() != this.transfFontSize()) {
                        this.removeMarginSapan(editable, n, n2, (BaseSpan)gFontSizeSpan2);
                    }
                    else {
                        gFontSizeSpan = gFontSizeSpan2;
                    }
                    ++n3;
                }
            }
            if (o != null) {
                this.checkAndMergeSpan(editable, n, n2, this.styleClass);
            }
            else {
                editable.setSpan((Object)this.creatSpan(), n, n2, 33);
            }
        }
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        super.applyStyle(editable, n, n2);
    }
    
    protected void checkAndMergeSpan(final Editable editable, final int n, final int n2, final Class<GFontSizeSpan> clazz) {
        final GFontSizeSpan[] array = (GFontSizeSpan[])editable.getSpans(n, n, (Class)clazz);
        final int n3 = 0;
        final Object o = null;
        final Object o2 = null;
        GFontSizeSpan gFontSizeSpan2;
        if (array != null) {
            final int length = array.length;
            GFontSizeSpan gFontSizeSpan = null;
            int n4 = 0;
            while (true) {
                gFontSizeSpan2 = gFontSizeSpan;
                if (n4 >= length) {
                    break;
                }
                final GFontSizeSpan gFontSizeSpan3 = array[n4];
                if (gFontSizeSpan3.getSize() == this.transfFontSize()) {
                    gFontSizeSpan = gFontSizeSpan3;
                }
                ++n4;
            }
        }
        else {
            gFontSizeSpan2 = null;
        }
        final GFontSizeSpan[] array2 = (GFontSizeSpan[])editable.getSpans(n2, n2, (Class)clazz);
        Object o3 = o;
        if (array2 != null) {
            final int length2 = array2.length;
            Object o4 = o2;
            int n5 = n3;
            while (true) {
                o3 = o4;
                if (n5 >= length2) {
                    break;
                }
                final GFontSizeSpan gFontSizeSpan4 = array2[n5];
                if (gFontSizeSpan4.getSize() == this.transfFontSize()) {
                    o4 = gFontSizeSpan4;
                }
                ++n5;
            }
        }
        final int spanStart = editable.getSpanStart((Object)gFontSizeSpan2);
        final int spanEnd = editable.getSpanEnd(o3);
        if (gFontSizeSpan2 != null && o3 != null) {
            editable.removeSpan((Object)gFontSizeSpan2);
            editable.removeSpan(o3);
            editable.setSpan((Object)this.creatSpan(), spanStart, spanEnd, 33);
        }
        else if (gFontSizeSpan2 != null && o3 == null) {
            editable.removeSpan((Object)gFontSizeSpan2);
            editable.setSpan((Object)this.creatSpan(), spanStart, n2, 33);
        }
        else if (gFontSizeSpan2 == null && o3 != null) {
            editable.removeSpan(o3);
            editable.setSpan((Object)this.creatSpan(), n, spanEnd, 33);
        }
        else {
            editable.setSpan((Object)this.creatSpan(), n, n2, 33);
        }
    }
    
    GFontSizeSpan creatSpan() {
        return new GFontSizeSpan(this.transfFontSize());
    }
    
    public int getFontSize() {
        if (this.baseStyleView != null && this.baseStyleView instanceof GFontSizeButton) {
            return ((GFontSizeButton)this.baseStyleView).getSize();
        }
        return this.fontSize;
    }
    
    public void setFontSize(final int fontSize) {
        if (this.baseStyleView != null && this.baseStyleView instanceof GFontSizeButton) {
            ((GFontSizeButton)this.baseStyleView).setText((CharSequence)String.valueOf(fontSize));
        }
        this.fontSize = fontSize;
    }
}
