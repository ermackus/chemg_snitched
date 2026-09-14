package com.kingagroot.component.ui.widget.richinput.style;

import com.kingagroot.component.ui.widget.richinput.view.GFontFamilyButton;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import com.kingagroot.component.ui.widget.richinput.view.SymbolInputFilter;
import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.view.BaseStyleView;
import com.kingagroot.component.ui.widget.richinput.span.GFontFamilySpan;

public class GFontFamilyStyle extends MetricAffectingStyle<GFontFamilySpan>
{
    String family;
    
    public GFontFamilyStyle() {
    }
    
    public GFontFamilyStyle(final BaseStyleView baseStyleView) {
        super(baseStyleView);
    }
    
    public void addSpan(final Editable editable, final int n, final int n2) {
        if (n < n2) {
            final GFontFamilySpan[] array = (GFontFamilySpan[])editable.getSpans(n, n2, this.styleClass);
            Object o = null;
            GFontFamilySpan gFontFamilySpan = null;
            SymbolInputFilter.filter(editable, this.getFamily(), n, n2);
            if (array != null) {
                final int length = array.length;
                int n3 = 0;
                while (true) {
                    o = gFontFamilySpan;
                    if (n3 >= length) {
                        break;
                    }
                    final GFontFamilySpan gFontFamilySpan2 = array[n3];
                    if (!gFontFamilySpan2.getFamily().equals((Object)this.getFamily())) {
                        this.removeMarginSapan(editable, n, n2, (BaseSpan)gFontFamilySpan2);
                    }
                    else {
                        gFontFamilySpan = gFontFamilySpan2;
                    }
                    ++n3;
                }
            }
            if (o != null) {
                this.checkAndMergeSpan(editable, n, n2, this.styleClass);
            }
            else {
                editable.setSpan((Object)this.creatSpan(), n, n2, 34);
            }
        }
    }
    
    protected void checkAndMergeSpan(final Editable editable, final int n, final int n2, final Class<GFontFamilySpan> clazz) {
        final GFontFamilySpan[] array = (GFontFamilySpan[])editable.getSpans(n, n, (Class)clazz);
        final int n3 = 0;
        final Object o = null;
        final Object o2 = null;
        GFontFamilySpan gFontFamilySpan2;
        if (array != null) {
            final int length = array.length;
            GFontFamilySpan gFontFamilySpan = null;
            int n4 = 0;
            while (true) {
                gFontFamilySpan2 = gFontFamilySpan;
                if (n4 >= length) {
                    break;
                }
                final GFontFamilySpan gFontFamilySpan3 = array[n4];
                if (gFontFamilySpan3.getFamily().equals((Object)this.getFamily())) {
                    gFontFamilySpan = gFontFamilySpan3;
                }
                ++n4;
            }
        }
        else {
            gFontFamilySpan2 = null;
        }
        final GFontFamilySpan[] array2 = (GFontFamilySpan[])editable.getSpans(n2, n2, (Class)clazz);
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
                final GFontFamilySpan gFontFamilySpan4 = array2[n5];
                if (gFontFamilySpan4.getFamily().equals((Object)this.getFamily())) {
                    o4 = gFontFamilySpan4;
                }
                ++n5;
            }
        }
        final int spanStart = editable.getSpanStart((Object)gFontFamilySpan2);
        final int spanEnd = editable.getSpanEnd(o3);
        if (gFontFamilySpan2 != null && o3 != null) {
            editable.removeSpan((Object)gFontFamilySpan2);
            editable.removeSpan(o3);
            editable.setSpan((Object)this.creatSpan(), spanStart, spanEnd, 34);
        }
        else if (gFontFamilySpan2 != null && o3 == null) {
            editable.removeSpan((Object)gFontFamilySpan2);
            editable.setSpan((Object)this.creatSpan(), spanStart, n2, 34);
        }
        else if (gFontFamilySpan2 == null && o3 != null) {
            editable.removeSpan(o3);
            editable.setSpan((Object)this.creatSpan(), n, spanEnd, 34);
        }
        else {
            editable.setSpan((Object)this.creatSpan(), n, n2, 34);
        }
    }
    
    GFontFamilySpan creatSpan() {
        return new GFontFamilySpan(this.getFamily());
    }
    
    public String getFamily() {
        if (this.baseStyleView != null && this.baseStyleView instanceof GFontFamilyButton) {
            return ((GFontFamilyButton)this.baseStyleView).getFamily();
        }
        return this.family;
    }
    
    public void setFamily(final String s) {
        if (this.baseStyleView != null && this.baseStyleView instanceof GFontFamilyButton) {
            ((GFontFamilyButton)this.baseStyleView).setFamily(s);
        }
        this.family = s;
    }
}
