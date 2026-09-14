package com.kingagroot.component.ui.model;

import android.text.TextUtils;
import com.kingagroot.component.ui.widget.richinput.RichConfig;
import com.kingagroot.kingdraw.core.model.FormatValue;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import java.io.Serializable;

@Table(name = "GFormatValue")
public class GFormatValue implements Serializable, Cloneable
{
    @Column(autoGen = false, isId = true, name = "Id")
    int Id;
    @Column(name = "atomFontName")
    String atomFontName;
    @Column(name = "atomFontSize")
    int atomFontSize;
    @Column(name = "atomFontStyle")
    int atomFontStyle;
    @Column(name = "boldWidth")
    String boldWidth;
    @Column(name = "chainsAngle")
    int chainsAngle;
    @Column(name = "documentType")
    int documentType;
    @Column(name = "fixedLength")
    String fixedLength;
    @Column(name = "hashSpacing")
    String hashSpacing;
    @Column(name = "isNormal")
    boolean isNormal;
    @Column(name = "lineWidth")
    String lineWidth;
    @Column(name = "marginWidth")
    String marginWidth;
    @Column(name = "proof")
    boolean proof;
    @Column(name = "spacing")
    int spacing;
    @Column(name = "textFontName")
    String textFontName;
    @Column(name = "textFontSize")
    int textFontSize;
    @Column(name = "textFontStyle")
    int textFontStyle;
    @Column(name = "zoomer")
    boolean zoomer;
    
    public static GFormatValue formatValue(final FormatValue formatValue) {
        final GFormatValue gFormatValue = new GFormatValue();
        gFormatValue.documentType = formatValue.documentType;
        gFormatValue.fixedLength = formatValue.fixedLength;
        gFormatValue.boldWidth = formatValue.boldWidth;
        gFormatValue.lineWidth = formatValue.lineWidth;
        gFormatValue.spacing = Integer.parseInt(formatValue.spacing);
        gFormatValue.hashSpacing = formatValue.hashSpacing;
        gFormatValue.marginWidth = formatValue.marginWidth;
        gFormatValue.chainsAngle = formatValue.chainsAngle;
        gFormatValue.atomFontName = formatValue.atomFontName;
        gFormatValue.atomFontSize = formatValue.atomFontSize;
        gFormatValue.atomFontStyle = formatValue.atomFontStyle;
        gFormatValue.textFontName = formatValue.textFontName;
        gFormatValue.textFontSize = formatValue.textFontSize;
        gFormatValue.textFontStyle = formatValue.textFontStyle;
        return gFormatValue;
    }
    
    public static String[] getAllDocumentType() {
        return GDocumentTypeEnum.getAllType();
    }
    
    public static String[] getAllFontName() {
        return RichConfig.getFontFamily();
    }
    
    public static int[] getAllFontSize() {
        return RichConfig.getFontSize();
    }
    
    public static String[] getAllFontStyle() {
        return RichConfig.getFontStyle();
    }
    
    public void checkFormatValue() {
        if (TextUtils.isEmpty((CharSequence)this.fixedLength)) {
            this.fixedLength = "0.78";
        }
        if (TextUtils.isEmpty((CharSequence)this.lineWidth)) {
            this.lineWidth = "0.0312";
        }
        if (TextUtils.isEmpty((CharSequence)this.boldWidth)) {
            this.boldWidth = "0.0936";
        }
        if (TextUtils.isEmpty((CharSequence)this.atomFontName)) {
            this.atomFontName = RichConfig.DEFAULT_FONT_FAMILY;
        }
        if (this.atomFontSize == 0) {
            this.atomFontSize = 10;
        }
        if (TextUtils.isEmpty((CharSequence)this.textFontName)) {
            this.textFontName = RichConfig.DEFAULT_FONT_FAMILY;
        }
        if (this.textFontSize == 0) {
            this.textFontSize = 10;
        }
    }
    
    public GFormatValue clone() {
        try {
            return (GFormatValue)super.clone();
        }
        catch (final CloneNotSupportedException ex) {
            ex.printStackTrace();
            return this;
        }
    }
    
    public FormatValue formatValue() {
        final FormatValue formatValue = new FormatValue();
        formatValue.documentType = this.documentType;
        formatValue.fixedLength = this.fixedLength;
        formatValue.boldWidth = this.boldWidth;
        formatValue.lineWidth = this.lineWidth;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.spacing);
        sb.append("");
        formatValue.spacing = sb.toString();
        formatValue.hashSpacing = this.hashSpacing;
        formatValue.marginWidth = this.marginWidth;
        formatValue.chainsAngle = this.chainsAngle;
        formatValue.atomFontName = this.atomFontName;
        formatValue.atomFontSize = this.atomFontSize;
        formatValue.atomFontStyle = this.atomFontStyle;
        formatValue.textFontName = this.textFontName;
        formatValue.textFontSize = this.textFontSize;
        formatValue.textFontStyle = this.textFontStyle;
        return formatValue;
    }
    
    public String getAtomFontName() {
        return this.atomFontName;
    }
    
    public int getAtomFontSize() {
        return this.atomFontSize;
    }
    
    public int getAtomFontStyle() {
        return this.atomFontStyle;
    }
    
    public String getBoldWidth() {
        return this.boldWidth;
    }
    
    public int getChainsAngle() {
        return this.chainsAngle;
    }
    
    public int getDocumentType() {
        return this.documentType;
    }
    
    public String getFixedLength() {
        return this.fixedLength;
    }
    
    public String getHashSpacing() {
        return this.hashSpacing;
    }
    
    public int getId() {
        return this.Id;
    }
    
    public String getLineWidth() {
        return this.lineWidth;
    }
    
    public String getMarginWidth() {
        return this.marginWidth;
    }
    
    public int getSpacing() {
        return this.spacing;
    }
    
    public String getTextFontName() {
        return this.textFontName;
    }
    
    public int getTextFontSize() {
        return this.textFontSize;
    }
    
    public int getTextFontStyle() {
        return this.textFontStyle;
    }
    
    public boolean isNormal() {
        return this.isNormal;
    }
    
    public boolean isProof() {
        return this.proof;
    }
    
    public boolean isZoomer() {
        return this.zoomer;
    }
    
    public boolean sameToFormat(final GFormatValue gFormatValue) {
        final boolean equals = this.fixedLength.equals((Object)gFormatValue.getFixedLength());
        boolean b = false;
        if (!equals) {
            return false;
        }
        if (this.spacing != gFormatValue.getSpacing()) {
            return false;
        }
        if (!this.lineWidth.equals((Object)gFormatValue.getLineWidth())) {
            return false;
        }
        if (!this.boldWidth.equals((Object)gFormatValue.getBoldWidth())) {
            return false;
        }
        if (!this.marginWidth.equals((Object)gFormatValue.getMarginWidth())) {
            return false;
        }
        if (!this.hashSpacing.equals((Object)gFormatValue.getHashSpacing())) {
            return false;
        }
        if (this.chainsAngle != gFormatValue.getChainsAngle()) {
            return false;
        }
        if (!this.atomFontName.equals((Object)gFormatValue.getAtomFontName())) {
            return false;
        }
        if (this.atomFontStyle != gFormatValue.getAtomFontStyle()) {
            return false;
        }
        if (this.atomFontSize != gFormatValue.getAtomFontSize()) {
            return false;
        }
        if (!this.textFontName.equals((Object)gFormatValue.getTextFontName())) {
            return false;
        }
        if (this.textFontStyle != gFormatValue.getTextFontStyle()) {
            return false;
        }
        if (this.textFontSize != gFormatValue.getTextFontSize()) {
            return false;
        }
        if (this.zoomer != gFormatValue.isZoomer()) {
            return false;
        }
        if (this.proof == gFormatValue.isProof()) {
            b = true;
        }
        return b;
    }
    
    public void setAtomFontName(final String atomFontName) {
        this.atomFontName = atomFontName;
    }
    
    public void setAtomFontSize(final int atomFontSize) {
        this.atomFontSize = atomFontSize;
    }
    
    public void setAtomFontStyle(final int atomFontStyle) {
        this.atomFontStyle = atomFontStyle;
    }
    
    public void setBoldWidth(final String boldWidth) {
        this.boldWidth = boldWidth;
    }
    
    public void setChainsAngle(final int chainsAngle) {
        this.chainsAngle = chainsAngle;
    }
    
    public void setDocumentType(final int documentType) {
        this.documentType = documentType;
    }
    
    public void setFixedLength(final String fixedLength) {
        this.fixedLength = fixedLength;
    }
    
    public void setHashSpacing(final String hashSpacing) {
        this.hashSpacing = hashSpacing;
    }
    
    public void setId(final int id) {
        this.Id = id;
    }
    
    public void setLineWidth(final String lineWidth) {
        this.lineWidth = lineWidth;
    }
    
    public void setMarginWidth(final String marginWidth) {
        this.marginWidth = marginWidth;
    }
    
    public void setNormal(final boolean isNormal) {
        this.isNormal = isNormal;
    }
    
    public void setProof(final boolean proof) {
        this.proof = proof;
    }
    
    public void setSpacing(final int spacing) {
        this.spacing = spacing;
    }
    
    public void setTextFontName(final String textFontName) {
        this.textFontName = textFontName;
    }
    
    public void setTextFontSize(final int textFontSize) {
        this.textFontSize = textFontSize;
    }
    
    public void setTextFontStyle(final int textFontStyle) {
        this.textFontStyle = textFontStyle;
    }
    
    public void setZoomer(final boolean zoomer) {
        this.zoomer = zoomer;
    }
}
