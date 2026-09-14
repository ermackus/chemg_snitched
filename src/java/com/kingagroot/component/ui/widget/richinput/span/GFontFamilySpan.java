package com.kingagroot.component.ui.widget.richinput.span;

import android.text.TextPaint;
import android.graphics.Paint;
import android.content.res.AssetManager;
import java.io.IOException;
import com.kingagroot.component.ui.UIComponentHelper;
import android.graphics.Typeface;
import android.text.style.TypefaceSpan;

public class GFontFamilySpan extends TypefaceSpan implements BaseSpan
{
    private static final String FILE_EXTENSION = ".ttf";
    String family;
    private boolean familySupport;
    private Typeface newType;
    
    public GFontFamilySpan(final String family) {
        super(family);
        this.family = family;
        try {
            final AssetManager assets = UIComponentHelper.getInstance().getAssets();
            final String[] list = assets.list("fonts");
            if (list != null) {
                Block_5: {
                    for (final String s : list) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(family);
                        sb.append(".ttf");
                        if (s.startsWith(sb.toString())) {
                            break Block_5;
                        }
                    }
                    return;
                }
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("fonts/");
                sb2.append(family);
                sb2.append(".ttf");
                this.newType = Typeface.createFromAsset(assets, sb2.toString());
                this.familySupport = true;
            }
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void applyCustomTypeFace(final Paint paint, final Typeface typeface) {
        final Typeface typeface2 = paint.getTypeface();
        int style;
        if (typeface2 == null) {
            style = 0;
        }
        else {
            style = typeface2.getStyle();
        }
        final int n = style & ~typeface.getStyle();
        if ((n & 0x1) != 0x0) {
            paint.setFakeBoldText(true);
        }
        if ((n & 0x2) != 0x0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }
    
    public boolean equals(final Object o) {
        return o instanceof GFontFamilySpan && ((GFontFamilySpan)o).getFamily().equals((Object)this.family);
    }
    
    public String getTagName() {
        return "font-family";
    }
    
    public int hashCode() {
        return this.getFamily().hashCode();
    }
    
    public BaseSpan newInstance() {
        return (BaseSpan)new GFontFamilySpan(this.family);
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        if (this.familySupport) {
            applyCustomTypeFace((Paint)textPaint, this.newType);
        }
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        if (this.familySupport) {
            applyCustomTypeFace((Paint)textPaint, this.newType);
        }
    }
}
