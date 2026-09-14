package com.kingagroot.kingdraw.core.graphics.svg.utils;

import java.util.Locale;

public class TextScanner
{
    final String input;
    int inputLength;
    private final NumberParser numberParser;
    int position;
    
    public TextScanner(String trim) {
        this.position = 0;
        this.numberParser = new NumberParser();
        trim = trim.trim();
        this.input = trim;
        this.inputLength = trim.length();
    }
    
    int advanceChar() {
        int position = this.position;
        final int inputLength = this.inputLength;
        if (position == inputLength) {
            return -1;
        }
        ++position;
        if ((this.position = position) < inputLength) {
            return this.input.charAt(position);
        }
        return -1;
    }
    
    String ahead() {
        final int position = this.position;
        while (!this.empty() && !this.isWhitespace(this.input.charAt(this.position))) {
            ++this.position;
        }
        final String substring = this.input.substring(position, this.position);
        this.position = position;
        return substring;
    }
    
    Boolean checkedNextFlag(final Object o) {
        if (o == null) {
            return null;
        }
        this.skipCommaWhitespace();
        return this.nextFlag();
    }
    
    float checkedNextFloat(final float n) {
        if (Float.isNaN(n)) {
            return Float.NaN;
        }
        this.skipCommaWhitespace();
        return this.nextFloat();
    }
    
    float checkedNextFloat(final Boolean b) {
        if (b == null) {
            return Float.NaN;
        }
        this.skipCommaWhitespace();
        return this.nextFloat();
    }
    
    public boolean consume(final char c) {
        final int position = this.position;
        final boolean b = position < this.inputLength && this.input.charAt(position) == c;
        if (b) {
            ++this.position;
        }
        return b;
    }
    
    public boolean consume(final String s) {
        final int length = s.length();
        final int position = this.position;
        final boolean b = position <= this.inputLength - length && this.input.substring(position, position + length).equals((Object)s);
        if (b) {
            this.position += length;
        }
        return b;
    }
    
    public boolean empty() {
        return this.position == this.inputLength;
    }
    
    boolean hasLetter() {
        final int position = this.position;
        final int inputLength = this.inputLength;
        final boolean b = false;
        if (position == inputLength) {
            return false;
        }
        final char char1 = this.input.charAt(position);
        if (char1 < 'a' || char1 > 'z') {
            boolean b2 = b;
            if (char1 < 'A') {
                return b2;
            }
            b2 = b;
            if (char1 > 'Z') {
                return b2;
            }
        }
        return true;
    }
    
    boolean isEOL(final int n) {
        return n == 10 || n == 13;
    }
    
    boolean isWhitespace(final int n) {
        return n == 32 || n == 10 || n == 13 || n == 9;
    }
    
    Integer nextChar() {
        final int position = this.position;
        if (position == this.inputLength) {
            return null;
        }
        final String input = this.input;
        this.position = position + 1;
        return (int)input.charAt(position);
    }
    
    Boolean nextFlag() {
        final int position = this.position;
        if (position == this.inputLength) {
            return null;
        }
        final char char1 = this.input.charAt(position);
        if (char1 != '0' && char1 != '1') {
            return null;
        }
        final int position2 = this.position;
        boolean b = true;
        this.position = position2 + 1;
        if (char1 != '1') {
            b = false;
        }
        return b;
    }
    
    public float nextFloat() {
        final float number = this.numberParser.parseNumber(this.input, this.position, this.inputLength);
        if (!Float.isNaN(number)) {
            this.position = this.numberParser.getEndPos();
        }
        return number;
    }
    
    String nextFunction() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        int n;
        for (n = this.input.charAt(position); (n >= 97 && n <= 122) || (n >= 65 && n <= 90); n = this.advanceChar()) {}
        final int position2 = this.position;
        while (this.isWhitespace(n)) {
            n = this.advanceChar();
        }
        if (n == 40) {
            ++this.position;
            return this.input.substring(position, position2);
        }
        this.position = position;
        return null;
    }
    
    Integer nextInteger(final boolean b) {
        final IntegerParser int1 = IntegerParser.parseInt(this.input, this.position, this.inputLength, b);
        if (int1 == null) {
            return null;
        }
        this.position = int1.getEndPos();
        return int1.value();
    }
    
    SVGBase.Length nextLength() {
        final float nextFloat = this.nextFloat();
        if (Float.isNaN(nextFloat)) {
            return null;
        }
        final SVGBase.Unit nextUnit = this.nextUnit();
        if (nextUnit == null) {
            return new SVGBase.Length(nextFloat, SVGBase.Unit.px);
        }
        return new SVGBase.Length(nextFloat, nextUnit);
    }
    
    public String nextQuotedString() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        final char char1 = this.input.charAt(position);
        if (char1 != '\'' && char1 != '\"') {
            return null;
        }
        int n;
        for (n = this.advanceChar(); n != -1 && n != char1; n = this.advanceChar()) {}
        if (n == -1) {
            this.position = position;
            return null;
        }
        final int position2 = this.position + 1;
        this.position = position2;
        return this.input.substring(position + 1, position2 - 1);
    }
    
    public String nextToken() {
        return this.nextToken(' ', false);
    }
    
    public String nextToken(final char c) {
        return this.nextToken(c, false);
    }
    
    String nextToken(final char c, final boolean b) {
        if (this.empty()) {
            return null;
        }
        final char char1 = this.input.charAt(this.position);
        if ((!b && this.isWhitespace(char1)) || char1 == c) {
            return null;
        }
        final int position = this.position;
        for (int i = this.advanceChar(); i != -1; i = this.advanceChar()) {
            if (i == c) {
                break;
            }
            if (!b && this.isWhitespace(i)) {
                break;
            }
        }
        return this.input.substring(position, this.position);
    }
    
    String nextTokenWithWhitespace(final char c) {
        return this.nextToken(c, true);
    }
    
    SVGBase.Unit nextUnit() {
        if (this.empty()) {
            return null;
        }
        if (this.input.charAt(this.position) == '%') {
            ++this.position;
            return SVGBase.Unit.percent;
        }
        final int position = this.position;
        if (position > this.inputLength - 2) {
            return null;
        }
        try {
            final SVGBase.Unit value = SVGBase.Unit.valueOf(this.input.substring(position, position + 2).toLowerCase(Locale.US));
            this.position += 2;
            return value;
        }
        catch (final IllegalArgumentException ex) {
            return null;
        }
    }
    
    public String nextWord() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        final char char1 = this.input.charAt(position);
        if ((char1 >= 'A' && char1 <= 'Z') || (char1 >= 'a' && char1 <= 'z')) {
            for (int n = this.advanceChar(); (n >= 65 && n <= 90) || (n >= 97 && n <= 122); n = this.advanceChar()) {}
            return this.input.substring(position, this.position);
        }
        this.position = position;
        return null;
    }
    
    float possibleNextFloat() {
        this.skipCommaWhitespace();
        final float number = this.numberParser.parseNumber(this.input, this.position, this.inputLength);
        if (!Float.isNaN(number)) {
            this.position = this.numberParser.getEndPos();
        }
        return number;
    }
    
    String restOfText() {
        if (this.empty()) {
            return null;
        }
        final int position = this.position;
        this.position = this.inputLength;
        return this.input.substring(position);
    }
    
    public boolean skipCommaWhitespace() {
        this.skipWhitespace();
        final int position = this.position;
        if (position == this.inputLength) {
            return false;
        }
        if (this.input.charAt(position) != ',') {
            return false;
        }
        ++this.position;
        this.skipWhitespace();
        return true;
    }
    
    public void skipWhitespace() {
        while (true) {
            final int position = this.position;
            if (position >= this.inputLength || !this.isWhitespace(this.input.charAt(position))) {
                break;
            }
            ++this.position;
        }
    }
}
