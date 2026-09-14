package com.kingagroot.kingdraw.core.model;

import java.io.Serializable;

public class FormatValue implements Serializable
{
    @JsonValue(key = "atomFontName")
    public String atomFontName;
    @JsonValue(key = "atomFontSize")
    public int atomFontSize;
    @JsonValue(key = "atomFontStyle")
    public int atomFontStyle;
    @JsonValue(key = "boldWidth")
    public String boldWidth;
    @JsonValue(key = "chainsAngle")
    public int chainsAngle;
    @JsonValue(key = "documentType")
    public int documentType;
    @JsonValue(key = "fixedLength")
    public String fixedLength;
    @JsonValue(key = "hashSpacing")
    public String hashSpacing;
    @JsonValue(key = "lineWidth")
    public String lineWidth;
    @JsonValue(key = "marginWidth")
    public String marginWidth;
    @JsonValue(key = "spacing")
    public String spacing;
    @JsonValue(key = "textFontName")
    public String textFontName;
    @JsonValue(key = "textFontSize")
    public int textFontSize;
    @JsonValue(key = "textFontStyle")
    public int textFontStyle;
    
    public FormatValue() {
        this.documentType = 1;
        this.fixedLength = "0.78";
        this.spacing = "12";
        this.lineWidth = "0.0312";
        this.boldWidth = "0.0936";
        this.marginWidth = "0.0312";
        this.hashSpacing = "0.0624";
        this.chainsAngle = 120;
        this.atomFontName = "Arial";
        this.atomFontStyle = 1;
        this.atomFontSize = 10;
        this.textFontName = "Arial";
        this.textFontStyle = 1;
        this.textFontSize = 10;
    }
}
