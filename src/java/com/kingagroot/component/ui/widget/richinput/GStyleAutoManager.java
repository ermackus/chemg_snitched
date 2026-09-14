package com.kingagroot.component.ui.widget.richinput;

import com.kingagroot.component.ui.widget.richinput.span.GChemStyleSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSuperscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GSubscriptSpan;
import com.kingagroot.component.ui.widget.richinput.span.GItalicSpan;
import com.kingagroot.component.ui.widget.richinput.span.GBlodSpan;
import com.kingagroot.component.ui.widget.richinput.span.GFontSizeSpan;
import com.kingagroot.component.ui.widget.richinput.span.GFontFamilySpan;
import com.kingagroot.component.ui.widget.richinput.span.BaseSpan;
import android.text.SpannableStringBuilder;
import java.util.Iterator;
import android.text.Editable;
import java.util.ArrayList;
import com.kingagroot.component.ui.widget.richinput.style.BaseStyle;
import java.util.List;
import com.kingagroot.component.ui.widget.richinput.style.GSuperscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GSubscriptStyle;
import com.kingagroot.component.ui.widget.richinput.style.GItalicStyle;
import com.kingagroot.component.ui.widget.richinput.style.GFontSizeStyle;
import com.kingagroot.component.ui.widget.richinput.style.GFontFamilyStyle;
import com.kingagroot.component.ui.widget.richinput.style.GChemStyle;
import com.kingagroot.component.ui.widget.richinput.style.GBlodStyle;

public class GStyleAutoManager implements StyleBaseManager
{
    GBlodStyle gBlodStyle;
    GChemStyle gChemStyle;
    private GFontFamilyStyle gFontFamilyStyle;
    private GFontSizeStyle gFontSizeStyle;
    GItalicStyle gItalicStyle;
    GSubscriptStyle gSubscriptStyle;
    GSuperscriptStyle gSuperscriptStyle;
    List<BaseStyle> styleList;
    
    public GStyleAutoManager() {
        this.styleList = (List<BaseStyle>)new ArrayList();
        this.gBlodStyle = new GBlodStyle();
        this.gItalicStyle = new GItalicStyle();
        this.gSubscriptStyle = new GSubscriptStyle();
        this.gSuperscriptStyle = new GSuperscriptStyle();
        this.gChemStyle = new GChemStyle();
        this.addSpan((BaseStyle)this.gBlodStyle);
        this.addSpan((BaseStyle)this.gItalicStyle);
        this.addSpan((BaseStyle)this.gSubscriptStyle);
        this.addSpan((BaseStyle)this.gSuperscriptStyle);
        this.addSpan((BaseStyle)this.gChemStyle);
        this.gFontFamilyStyle = new GFontFamilyStyle();
        this.gFontSizeStyle = new GFontSizeStyle();
        this.gFontFamilyStyle.setCheck(true);
        this.gFontSizeStyle.setCheck(true);
    }
    
    public GStyleAutoManager(final int n) {
        this.styleList = (List<BaseStyle>)new ArrayList();
        if (n == 1) {
            (this.gFontSizeStyle = new GFontSizeStyle()).setCheck(true);
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
    
    public void applyStyle(final SpannableStringBuilder spannableStringBuilder, final int n, final int n2, final List<BaseSpan> list) {
        if (list == null) {
            return;
        }
        final Iterator iterator = this.styleList.iterator();
        while (iterator.hasNext()) {
            ((BaseStyle)iterator.next()).setCheck(false);
        }
        for (final BaseSpan baseSpan : list) {
            if (baseSpan instanceof GFontFamilySpan) {
                final GFontFamilyStyle gFontFamilyStyle = this.gFontFamilyStyle;
                if (gFontFamilyStyle != null) {
                    gFontFamilyStyle.setFamily(((GFontFamilySpan)baseSpan).getFamily());
                    continue;
                }
            }
            if (baseSpan instanceof GFontSizeSpan) {
                final GFontSizeStyle gFontSizeStyle = this.gFontSizeStyle;
                if (gFontSizeStyle != null) {
                    gFontSizeStyle.setFontSize(((GFontSizeSpan)baseSpan).getUnitySize());
                    continue;
                }
            }
            if (baseSpan instanceof GBlodSpan) {
                final GBlodStyle gBlodStyle = this.gBlodStyle;
                if (gBlodStyle != null) {
                    gBlodStyle.setCheck(true);
                    continue;
                }
            }
            if (baseSpan instanceof GItalicSpan) {
                final GItalicStyle gItalicStyle = this.gItalicStyle;
                if (gItalicStyle != null) {
                    gItalicStyle.setCheck(true);
                    continue;
                }
            }
            if (baseSpan instanceof GSubscriptSpan) {
                final GSubscriptStyle gSubscriptStyle = this.gSubscriptStyle;
                if (gSubscriptStyle != null) {
                    gSubscriptStyle.setCheck(true);
                    continue;
                }
            }
            if (baseSpan instanceof GSuperscriptSpan) {
                final GSuperscriptStyle gSuperscriptStyle = this.gSuperscriptStyle;
                if (gSuperscriptStyle != null) {
                    gSuperscriptStyle.setCheck(true);
                    continue;
                }
            }
            if (baseSpan instanceof GChemStyleSpan) {
                final GChemStyle gChemStyle = this.gChemStyle;
                if (gChemStyle == null) {
                    continue;
                }
                gChemStyle.setCheck(true);
            }
        }
        this.applyStyle((Editable)spannableStringBuilder, n, n2);
    }
    
    public void onSelectionChanged(final int n, final int n2) {
    }
}
