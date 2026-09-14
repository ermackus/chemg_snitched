package com.kingagroot.kingdraw.core.Html;

import android.graphics.Paint$FontMetrics;
import android.graphics.Paint;
import android.graphics.Canvas;
import com.kingagroot.kingdraw.core.utils.GDensityUtil;
import com.kingagroot.kingdraw.core.graphics.KDColor;
import android.content.res.AssetManager;
import java.io.IOException;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.graphics.Paint$Style;
import java.util.Locale;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.text.TextPaint;
import android.graphics.RectF;
import java.util.regex.Pattern;

class TextSpan extends BaseText
{
    private static final String FILE_EXTENSION = ".ttf";
    private static float SIZEOFFSET;
    private static Pattern pattern;
    public String align;
    private float ascentHeight;
    private float ascentOffset;
    private RectF spanRectF;
    private String text;
    private TextPaint textPaint;
    
    static {
        TextSpan.pattern = Pattern.compile("g|j|y|p|q|\u03b2|\u03c7|\u03d5|\u03b3|\u03b7|\u03c6|\u03bb|\u03bc|\u03c1|\u03c2");
        TextSpan.SIZEOFFSET = 1.8f;
    }
    
    TextSpan(final HtmlTag htmlTag) {
        this.textPaint = new TextPaint();
        this.ascentOffset = 0.0f;
        this.align = "1";
        if (KingDrawConfig.isIsPad()) {
            TextSpan.SIZEOFFSET = 1.5f;
        }
        this.textPaint.setTextLocale(Locale.ENGLISH);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setStyle(Paint$Style.FILL);
        this.textPaint.setColor(-16777216);
        this.textPaint.setTextSize(TextSpan.SIZEOFFSET * 10.0f);
        this.formatTextPaint(htmlTag);
    }
    
    private String ReplaceEscapeChar(final String s) {
        return s.replace((CharSequence)"&lt;", (CharSequence)"<").replace((CharSequence)"&gt;", (CharSequence)">").replace((CharSequence)"&nbsp;", (CharSequence)" ");
    }
    
    public static boolean findCapital(final String s) {
        return Pattern.compile("^.*[A-Z]+.*$").matcher((CharSequence)s).matches();
    }
    
    private boolean findChinese(final String s) {
        return s.matches("[\u4e00-\u9fa5]+");
    }
    
    public static boolean findLowercase(final String s) {
        return Pattern.compile("^.*[a-z]+.*$").matcher((CharSequence)s).matches();
    }
    
    public static boolean findLowercaseDown(final String s) {
        return Pattern.compile("^.*[gjpqy]+.*$").matcher((CharSequence)s).matches();
    }
    
    public static boolean findLowercaseUp(final String s) {
        return Pattern.compile("^.*[bdfhijklt]+.*$").matcher((CharSequence)s).matches();
    }
    
    public static boolean findNumber(final String s) {
        return Pattern.compile("^.*[0-9]+.*$").matcher((CharSequence)s).matches();
    }
    
    private void formatTextPaint(final HtmlTag htmlTag) {
        this.text = this.ReplaceEscapeChar(htmlTag.text);
        final String attr = htmlTag.getAttr("font");
        final String attr2 = htmlTag.getAttr("size");
        final String attr3 = htmlTag.getAttr("color");
        final String attr4 = htmlTag.getAttr("drawtype");
        final String attr5 = htmlTag.getAttr("fstyle");
        final String attr6 = htmlTag.getAttr("align");
        if (!TextUtils.isEmpty((CharSequence)attr6)) {
            this.align = attr6;
        }
        if (!TextUtils.isEmpty((CharSequence)attr)) {
            this.setPaintFontName(attr);
            if (attr.equals((Object)"Symbol")) {
                this.text = SymbolTable.convertToSymbol(this.text);
            }
        }
        this.setPaintFontSize(attr2);
        if (!TextUtils.isEmpty((CharSequence)attr3)) {
            this.setPaintFontColor(attr3);
        }
        if (!TextUtils.isEmpty((CharSequence)attr4)) {
            this.setPaintDrawType(attr4);
        }
        if (!TextUtils.isEmpty((CharSequence)attr5)) {
            this.setPaintFontStyle(attr5);
        }
    }
    
    private Typeface getTypeface(final String s) {
        try {
            final AssetManager assets = KingDrawConfig.getContext().getAssets();
            final String[] list = assets.list("fonts");
            if (list != null) {
                Block_4: {
                    for (final String s2 : list) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(s);
                        sb.append(".ttf");
                        if (s2.startsWith(sb.toString())) {
                            break Block_4;
                        }
                    }
                    return null;
                }
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("fonts/");
                sb2.append(s);
                sb2.append(".ttf");
                return Typeface.createFromAsset(assets, sb2.toString());
            }
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private void setPaintDrawType(final String s) {
        if (s != null) {
            if (!s.equals((Object)"4") && !s.equals((Object)"1")) {
                if (s.equals((Object)"2")) {
                    final TextPaint textPaint = this.textPaint;
                    textPaint.baselineShift = (int)(textPaint.ascent() / 2.0f);
                    this.textPaint.setTextSize(this.textPaint.getTextSize() * 0.7f);
                }
            }
            else {
                final TextPaint textPaint2 = this.textPaint;
                textPaint2.baselineShift = 0 - (int)(textPaint2.ascent() / 4.0f);
                this.textPaint.setTextSize(this.textPaint.getTextSize() * 0.7f);
            }
        }
    }
    
    private void setPaintFontColor(final String s) {
        this.textPaint.setColor(KDColor.parseColor(s));
    }
    
    private void setPaintFontName(final String s) {
        final Typeface typeface = this.getTypeface(s);
        if (typeface == null) {
            return;
        }
        final Typeface typeface2 = this.textPaint.getTypeface();
        int style;
        if (typeface2 == null) {
            style = 0;
        }
        else {
            style = typeface2.getStyle();
        }
        final int n = style & ~typeface.getStyle();
        if ((n & 0x1) != 0x0) {
            this.textPaint.setFakeBoldText(true);
        }
        if ((n & 0x2) != 0x0) {
            this.textPaint.setTextSkewX(-0.25f);
        }
        this.textPaint.setTypeface(typeface);
    }
    
    private void setPaintFontSize(final String s) {
        int int1 = 10;
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                int1 = Integer.parseInt(s);
            }
        }
        catch (final NumberFormatException ex) {
            ex.printStackTrace();
            int1 = int1;
        }
        final float n = (float)int1;
        final float n2 = (float)GDensityUtil.sp2px(n);
        final float sizeoffset = TextSpan.SIZEOFFSET;
        this.ascentOffset = n * TextSpan.SIZEOFFSET * (GDensityUtil.dp2px(2.0f) / 10.0f);
        this.textPaint.setTextSize(n2 * sizeoffset);
    }
    
    private void setPaintFontStyle(final String s) {
        final boolean equals = s.equals((Object)"2");
        int style = 0;
        int n;
        if (equals) {
            n = 1;
        }
        else if (s.equals((Object)"3")) {
            n = 2;
        }
        else if (s.equals((Object)"4")) {
            n = 3;
        }
        else {
            n = 0;
        }
        final Typeface typeface = this.textPaint.getTypeface();
        if (typeface != null) {
            style = typeface.getStyle();
        }
        final int n2 = n | style;
        Typeface typeface2;
        if (typeface == null) {
            typeface2 = Typeface.defaultFromStyle(n2);
        }
        else {
            typeface2 = Typeface.create(typeface, n2);
        }
        final int n3 = n2 & ~typeface2.getStyle();
        if ((n3 & 0x1) != 0x0) {
            this.textPaint.setFakeBoldText(true);
        }
        if ((n3 & 0x2) != 0x0) {
            this.textPaint.setTextSkewX(-0.25f);
        }
        this.textPaint.setTypeface(typeface2);
    }
    
    public void draw(final Canvas canvas, final float n, final float n2) {
        canvas.drawText(this.text, n, n2 + this.textPaint.baselineShift, (Paint)this.textPaint);
    }
    
    float getAscentHeight() {
        if (!this.isMeasure) {
            this.onMeasure();
        }
        return this.ascentHeight;
    }
    
    RectF getSpanRect() {
        if (!this.isMeasure) {
            this.onMeasure();
        }
        return this.spanRectF;
    }
    
    protected void onMeasure() {
        this.width = this.textPaint.measureText(this.text);
        final Paint$FontMetrics fontMetrics = this.textPaint.getFontMetrics();
        if (this.text.matches("[\u4e00-\u9fa5]+")) {
            this.heigth = Math.abs(fontMetrics.ascent);
        }
        else {
            this.heigth = Math.abs(fontMetrics.ascent) - fontMetrics.descent;
        }
        this.ascentHeight = this.heigth;
        if (this.textPaint.baselineShift < 0) {
            this.ascentHeight -= this.textPaint.baselineShift;
        }
        if (TextSpan.pattern.matcher((CharSequence)this.text).find()) {
            this.heigth += fontMetrics.descent;
        }
        final RectF spanRectF = new RectF();
        this.spanRectF = spanRectF;
        spanRectF.left = 0.0f;
        this.spanRectF.right = this.width;
        final RectF spanRectF2 = this.spanRectF;
        spanRectF2.top -= this.heigth - this.textPaint.baselineShift;
        this.spanRectF.bottom = (float)this.textPaint.baselineShift;
    }
    
    protected void onMeasure(final boolean b) {
        this.width = this.textPaint.measureText(this.text);
        final Paint$FontMetrics fontMetrics = this.textPaint.getFontMetrics();
        final float n = fontMetrics.descent / 2.0f;
        final float descent = fontMetrics.descent;
        if (this.findChinese(this.text)) {
            this.heigth = Math.abs(fontMetrics.ascent);
            this.ascentHeight = this.heigth;
            this.heigth += descent;
        }
        else if (findLowercase(this.text)) {
            this.heigth = Math.abs(fontMetrics.ascent) - fontMetrics.descent - n;
            if (findLowercaseUp(this.text)) {
                this.heigth += n;
            }
            this.ascentHeight = this.heigth;
            if (findLowercaseDown(this.text)) {
                this.heigth += descent;
            }
        }
        else {
            this.heigth = Math.abs(fontMetrics.ascent) - fontMetrics.descent;
            this.ascentHeight = this.heigth;
        }
        this.heigth += Math.abs(this.textPaint.baselineShift);
        if (this.textPaint.baselineShift < 0) {
            this.ascentHeight -= this.textPaint.baselineShift;
        }
        final RectF spanRectF = new RectF();
        this.spanRectF = spanRectF;
        spanRectF.left = 0.0f;
        this.spanRectF.right = this.width;
        if (this.textPaint.baselineShift < 0) {
            final RectF spanRectF2 = this.spanRectF;
            spanRectF2.top -= this.heigth;
            this.spanRectF.bottom = 0.0f;
        }
        else {
            final RectF spanRectF3 = this.spanRectF;
            spanRectF3.top -= this.heigth - this.textPaint.baselineShift;
            this.spanRectF.bottom = (float)this.textPaint.baselineShift;
        }
    }
}
