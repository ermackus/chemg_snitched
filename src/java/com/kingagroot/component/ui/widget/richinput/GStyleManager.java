package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GUnderlineSpan;
import com.kingagroot.component.ui.widget.richinput.style.GUnderlineStyle;
import com.kingagroot.component.ui.widget.richinput.style.GSuperscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GSubscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GItalicStyle;
import com.kingagroot.component.ui.widget.richinput.span.GFontColorSpan;
import com.kingagroot.component.ui.widget.richinput.style.GFontColorStyle;
import com.kingagroot.component.ui.widget.richinput.span.GBlodItalicSpan;
import com.kingagroot.component.ui.widget.richinput.style.GBlodItalicStyle;
import com.kingagroot.component.ui.widget.richinput.style.GChemStyle;
import com.kingagroot.component.ui.widget.richinput.style.GBlodStyle;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GBlodSpan;
import com.kingagroot.component.ui.widget.richinput.span.GItalicSpan;
import android.text.Editable;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import com.kingagroot.component.ui.widget.richinput.span.GFontSizeSpan;
import java.util.Map$Entry;
import java.util.HashMap;
import com.kingagroot.component.ui.widget.richinput.span.GFontFamilySpan;
import java.util.Iterator;
import java.util.ArrayList;
import com.kingagroot.component.ui.widget.richinput.style.BaseStyle;
import java.util.List;
import com.kingagroot.component.ui.widget.richinput.style.GFontSizeStyle;
import com.kingagroot.component.ui.widget.richinput.style.GFontFamilyStyle;
import com.kingagroot.component.ui.widget.richinput.view.BaseRichEditor;

public class GStyleManager implements StyleBaseManager
{
    BaseRichEditor baseRichEditor;
    private String fontFamily;
    private int fontSize;
    private GFontFamilyStyle gFontFamilyStyle;
    private GFontSizeStyle gFontSizeStyle;
    private int gFontStyle;
    private boolean isCheckChem;
    List<BaseStyle> styleList;
    
    public GStyleManager(final BaseRichEditor baseRichEditor) {
        this.styleList = (List<BaseStyle>)new ArrayList();
        this.fontFamily = RichConfig.DEFAULT_FONT_FAMILY;
        this.fontSize = 10;
        this.gFontStyle = GFontStyleEnum.Regular.getCode();
        this.isCheckChem = false;
        this.baseRichEditor = baseRichEditor;
    }
    
    private void clearCheck() {
        final Iterator iterator = this.styleList.iterator();
        while (iterator.hasNext()) {
            ((BaseStyle)iterator.next()).setCheck(false);
        }
    }
    
    private List<GStyleManager.GStyleManager$SpanRegion> cutCake(final List<GStyleManager.GStyleManager$SpanRegion> list, final int n, final int n2) {
        final ArrayList list2 = new ArrayList();
        for (final GStyleManager.GStyleManager$SpanRegion gStyleManager$SpanRegion : list) {
            if (n <= gStyleManager$SpanRegion.start && n2 >= gStyleManager$SpanRegion.end) {
                continue;
            }
            if (n <= gStyleManager$SpanRegion.start && n2 < gStyleManager$SpanRegion.end) {
                ((List)list2).add((Object)new GStyleManager.GStyleManager$SpanRegion(this, n2, gStyleManager$SpanRegion.end));
            }
            else if (n > gStyleManager$SpanRegion.start && n2 < gStyleManager$SpanRegion.end) {
                ((List)list2).add((Object)new GStyleManager.GStyleManager$SpanRegion(this, gStyleManager$SpanRegion.start, n));
                ((List)list2).add((Object)new GStyleManager.GStyleManager$SpanRegion(this, n2, gStyleManager$SpanRegion.end));
            }
            else {
                if (n <= gStyleManager$SpanRegion.start || n2 < gStyleManager$SpanRegion.end) {
                    continue;
                }
                ((List)list2).add((Object)new GStyleManager.GStyleManager$SpanRegion(this, gStyleManager$SpanRegion.start, n));
            }
        }
        return (List<GStyleManager.GStyleManager$SpanRegion>)list2;
    }
    
    private GFontFamilySpan[] deduplicationFontFamilySpan(GFontFamilySpan[] array) {
        final HashMap hashMap = new HashMap();
        if (array != null) {
            for (final GFontFamilySpan gFontFamilySpan : array) {
                final String family = gFontFamilySpan.getFamily();
                if (!hashMap.containsKey((Object)family)) {
                    hashMap.put((Object)family, (Object)gFontFamilySpan);
                }
            }
        }
        array = new GFontFamilySpan[hashMap.size()];
        final Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            array[0] = (GFontFamilySpan)((Map$Entry)iterator.next()).getValue();
        }
        return array;
    }
    
    private GFontSizeSpan[] deduplicationFontSizeSpan(GFontSizeSpan[] array) {
        final HashMap hashMap = new HashMap();
        if (array != null) {
            for (final GFontSizeSpan gFontSizeSpan : array) {
                final int unitySize = gFontSizeSpan.getUnitySize();
                if (!hashMap.containsKey((Object)unitySize)) {
                    hashMap.put((Object)unitySize, (Object)gFontSizeSpan);
                }
            }
        }
        array = new GFontSizeSpan[hashMap.size()];
        final Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            array[0] = (GFontSizeSpan)((Map$Entry)iterator.next()).getValue();
        }
        return array;
    }
    
    private boolean isContainSpan(final BaseSpan[] array, int n, int length, final Class clazz) {
        final Editable text = this.baseRichEditor.getText();
        Object o = new ArrayList();
        ((List)o).add((Object)new GStyleManager.GStyleManager$SpanRegion(this, n, length));
        boolean b = false;
        if (array != null) {
            length = array.length;
            n = 0;
            while (true) {
                b = b;
                if (n >= length) {
                    break;
                }
                final BaseSpan baseSpan = array[n];
                List cutCake = (List)o;
                if (baseSpan.getClass() == clazz && (cutCake = this.cutCake((List<GStyleManager.GStyleManager$SpanRegion>)o, text.getSpanStart((Object)baseSpan), text.getSpanEnd((Object)baseSpan))).isEmpty()) {
                    b = true;
                    break;
                }
                ++n;
                o = cutCake;
            }
        }
        return b;
    }
    
    private void restFormat() {
        this.gFontSizeStyle.setFontSize(this.fontSize);
        this.gFontFamilyStyle.setFamily(this.fontFamily);
        if (this.gFontStyle == GFontStyleEnum.Bold.getCode()) {
            final BaseStyle style = this.findStyle(new GItalicSpan());
            if (style != null) {
                style.setCheck(false);
            }
            final BaseStyle style2 = this.findStyle(new GBlodSpan());
            if (style2 != null) {
                style2.setCheck(true);
            }
        }
        else if (this.gFontStyle == GFontStyleEnum.Italic.getCode()) {
            final BaseStyle style3 = this.findStyle(new GItalicSpan());
            if (style3 != null) {
                style3.setCheck(true);
            }
            final BaseStyle style4 = this.findStyle(new GBlodSpan());
            if (style4 != null) {
                style4.setCheck(false);
            }
        }
        else if (this.gFontStyle == GFontStyleEnum.BoldItalic.getCode()) {
            final BaseStyle style5 = this.findStyle(new GItalicSpan());
            if (style5 != null) {
                style5.setCheck(true);
            }
            final BaseStyle style6 = this.findStyle(new GBlodSpan());
            if (style6 != null) {
                style6.setCheck(true);
            }
        }
        else {
            final BaseStyle style7 = this.findStyle(new GItalicSpan());
            if (style7 != null) {
                style7.setCheck(false);
            }
            final BaseStyle style8 = this.findStyle(new GBlodSpan());
            if (style8 != null) {
                style8.setCheck(false);
            }
        }
        final BaseStyle style9 = this.findStyle(new GChemStyleSpan());
        if (style9 != null) {
            style9.setCheck(this.isCheckChem);
        }
        final BaseStyle style10 = this.findStyle(new GSuperscriptSpan());
        if (style10 != null) {
            style10.setCheck(false);
        }
        final BaseStyle style11 = this.findStyle(new GSubscriptSpan());
        if (style11 != null) {
            style11.setCheck(false);
        }
    }
    
    public void addSpan(final BaseStyle baseStyle) {
        if (baseStyle != null) {
            this.styleList.add((Object)baseStyle);
        }
    }
    
    public void applyStyle(final Editable editable, final int n, final int n2) {
        final GFontSizeStyle gFontSizeStyle = this.gFontSizeStyle;
        if (gFontSizeStyle != null) {
            gFontSizeStyle.applyStyle(editable, n, n2);
        }
        final GFontFamilyStyle gFontFamilyStyle = this.gFontFamilyStyle;
        if (gFontFamilyStyle != null) {
            gFontFamilyStyle.applyStyle(editable, n, n2);
        }
        final Iterator iterator = this.styleList.iterator();
        while (iterator.hasNext()) {
            ((BaseStyle)iterator.next()).applyStyle(editable, n, n2);
        }
    }
    
    public BaseStyle findStyle(final Object o) {
        for (final BaseStyle baseStyle : this.styleList) {
            if (baseStyle instanceof GBlodStyle && o instanceof GBlodSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GChemStyle && o instanceof GChemStyleSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GBlodItalicStyle && o instanceof GBlodItalicSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GFontColorStyle && o instanceof GFontColorSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GItalicStyle && o instanceof GItalicSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GSubscriptStyle && o instanceof GSubscriptSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GSuperscriptStyle && o instanceof GSuperscriptSpan) {
                return baseStyle;
            }
            if (baseStyle instanceof GUnderlineStyle && o instanceof GUnderlineSpan) {
                return baseStyle;
            }
        }
        return null;
    }
    
    public void onSelectionChanged(int i, int length) {
        if (length <= 0) {
            this.restFormat();
            return;
        }
        this.clearCheck();
        final int n = 0;
        if (i == length) {
            final Editable text = this.baseRichEditor.getText();
            i = length - 1;
            final BaseSpan[] array = (BaseSpan[])text.getSpans(i, length, (Class)BaseSpan.class);
            final GFontFamilySpan[] array2 = (GFontFamilySpan[])this.baseRichEditor.getText().getSpans(i, length, (Class)GFontFamilySpan.class);
            final GFontSizeSpan[] array3 = (GFontSizeSpan[])this.baseRichEditor.getText().getSpans(i, length, (Class)GFontSizeSpan.class);
            if (array2 != null && array2.length >= 1) {
                this.gFontFamilyStyle.setFamily(array2[array2.length - 1].getFamily());
            }
            else {
                this.gFontFamilyStyle.setFamily(RichConfig.DEFAULT_FONT_FAMILY);
            }
            if (array3 != null && array3.length >= 1) {
                i = array3[array3.length - 1].getUnitySize();
                this.gFontSizeStyle.setFontSize(i);
            }
            else {
                this.gFontSizeStyle.setFontSize(10);
            }
            if (array != null) {
                BaseStyle style;
                for (length = array.length, i = n; i < length; ++i) {
                    style = this.findStyle(array[i]);
                    if (style != null) {
                        style.setCheck(true);
                    }
                }
            }
        }
        else {
            final BaseSpan[] array4 = (BaseSpan[])this.baseRichEditor.getText().getSpans(i, length, (Class)BaseSpan.class);
            final GFontFamilySpan[] deduplicationFontFamilySpan = this.deduplicationFontFamilySpan((GFontFamilySpan[])this.baseRichEditor.getText().getSpans(i, length, (Class)GFontFamilySpan.class));
            final GFontSizeSpan[] deduplicationFontSizeSpan = this.deduplicationFontSizeSpan((GFontSizeSpan[])this.baseRichEditor.getText().getSpans(i, length, (Class)GFontSizeSpan.class));
            if (deduplicationFontFamilySpan != null) {
                if (deduplicationFontFamilySpan.length == 1) {
                    this.gFontFamilyStyle.setFamily(deduplicationFontFamilySpan[0].getFamily());
                }
                else {
                    this.gFontFamilyStyle.setFamily("");
                }
            }
            if (deduplicationFontSizeSpan != null) {
                if (deduplicationFontSizeSpan.length == 1) {
                    this.gFontSizeStyle.setFontSize(deduplicationFontSizeSpan[0].getUnitySize());
                }
                else {
                    this.gFontSizeStyle.setFontSize(0);
                }
            }
            final boolean containSpan = this.isContainSpan(array4, i, length, GBlodSpan.class);
            if (containSpan) {
                final BaseStyle style2 = this.findStyle(new GBlodSpan());
                if (style2 != null) {
                    style2.setCheck(containSpan);
                }
            }
            final boolean containSpan2 = this.isContainSpan(array4, i, length, GItalicSpan.class);
            if (containSpan2) {
                final BaseStyle style3 = this.findStyle(new GItalicSpan());
                if (style3 != null) {
                    style3.setCheck(containSpan2);
                }
            }
            final boolean containSpan3 = this.isContainSpan(array4, i, length, GSuperscriptSpan.class);
            if (containSpan3) {
                final BaseStyle style4 = this.findStyle(new GSuperscriptSpan());
                if (style4 != null) {
                    style4.setCheck(containSpan3);
                }
            }
            final boolean containSpan4 = this.isContainSpan(array4, i, length, GSubscriptSpan.class);
            if (containSpan4) {
                final BaseStyle style5 = this.findStyle(new GSubscriptSpan());
                if (style5 != null) {
                    style5.setCheck(containSpan4);
                }
            }
            final boolean containSpan5 = this.isContainSpan(array4, i, length, GChemStyleSpan.class);
            if (containSpan5) {
                final BaseStyle style6 = this.findStyle(new GChemStyleSpan());
                if (style6 != null) {
                    style6.setCheck(containSpan5);
                }
            }
        }
    }
    
    public void setFontStyle(final String fontFamily, final int fontSize, final int gFontStyle, final boolean isCheckChem) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.gFontStyle = gFontStyle;
        this.isCheckChem = isCheckChem;
    }
    
    public void setgFontFamilyStyle(final GFontFamilyStyle gFontFamilyStyle) {
        this.gFontFamilyStyle = gFontFamilyStyle;
    }
    
    public void setgFontSizeStyle(final GFontSizeStyle gFontSizeStyle) {
        this.gFontSizeStyle = gFontSizeStyle;
    }
}
