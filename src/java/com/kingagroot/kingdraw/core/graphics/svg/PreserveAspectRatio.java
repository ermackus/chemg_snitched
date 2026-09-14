package com.kingagroot.kingdraw.core.graphics.svg;

import com.kingagroot.kingdraw.core.graphics.svg.utils.TextScanner;
import java.util.HashMap;
import java.util.Map;

public class PreserveAspectRatio
{
    public static final PreserveAspectRatio BOTTOM;
    public static final PreserveAspectRatio END;
    public static final PreserveAspectRatio FULLSCREEN;
    public static final PreserveAspectRatio FULLSCREEN_START;
    public static final PreserveAspectRatio LETTERBOX;
    public static final PreserveAspectRatio START;
    public static final PreserveAspectRatio STRETCH;
    public static final PreserveAspectRatio TOP;
    public static final PreserveAspectRatio UNSCALED;
    private static final Map<String, Alignment> aspectRatioKeywords;
    private final Alignment alignment;
    private final Scale scale;
    
    static {
        aspectRatioKeywords = (Map)new HashMap(10);
        UNSCALED = new PreserveAspectRatio(null, null);
        STRETCH = new PreserveAspectRatio(Alignment.none, null);
        LETTERBOX = new PreserveAspectRatio(Alignment.xMidYMid, Scale.meet);
        START = new PreserveAspectRatio(Alignment.xMinYMin, Scale.meet);
        END = new PreserveAspectRatio(Alignment.xMaxYMax, Scale.meet);
        TOP = new PreserveAspectRatio(Alignment.xMidYMin, Scale.meet);
        BOTTOM = new PreserveAspectRatio(Alignment.xMidYMax, Scale.meet);
        FULLSCREEN = new PreserveAspectRatio(Alignment.xMidYMid, Scale.slice);
        FULLSCREEN_START = new PreserveAspectRatio(Alignment.xMinYMin, Scale.slice);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"none", (Object)Alignment.none);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMinYMin", (Object)Alignment.xMinYMin);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMidYMin", (Object)Alignment.xMidYMin);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMaxYMin", (Object)Alignment.xMaxYMin);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMinYMid", (Object)Alignment.xMinYMid);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMidYMid", (Object)Alignment.xMidYMid);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMaxYMid", (Object)Alignment.xMaxYMid);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMinYMax", (Object)Alignment.xMinYMax);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMidYMax", (Object)Alignment.xMidYMax);
        PreserveAspectRatio.aspectRatioKeywords.put((Object)"xMaxYMax", (Object)Alignment.xMaxYMax);
    }
    
    PreserveAspectRatio(final Alignment alignment, final Scale scale) {
        this.alignment = alignment;
        this.scale = scale;
    }
    
    public static PreserveAspectRatio of(final String s) {
        try {
            return parsePreserveAspectRatio(s);
        }
        catch (final SVGParseException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    private static PreserveAspectRatio parsePreserveAspectRatio(final String s) throws SVGParseException {
        final TextScanner textScanner = new TextScanner(s);
        textScanner.skipWhitespace();
        String s2;
        if ("defer".equals((Object)(s2 = textScanner.nextToken()))) {
            textScanner.skipWhitespace();
            s2 = textScanner.nextToken();
        }
        final Alignment alignment = (Alignment)PreserveAspectRatio.aspectRatioKeywords.get((Object)s2);
        Scale scale = null;
        textScanner.skipWhitespace();
        if (!textScanner.empty()) {
            final String nextToken = textScanner.nextToken();
            int n = -1;
            final int hashCode = nextToken.hashCode();
            if (hashCode != 3347527) {
                if (hashCode == 109526418) {
                    if (nextToken.equals((Object)"slice")) {
                        n = 1;
                    }
                }
            }
            else if (nextToken.equals((Object)"meet")) {
                n = 0;
            }
            if (n != 0) {
                if (n != 1) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Invalid preserveAspectRatio definition: ");
                    sb.append(s);
                    throw new SVGParseException(sb.toString());
                }
                scale = Scale.slice;
            }
            else {
                scale = Scale.meet;
            }
        }
        return new PreserveAspectRatio(alignment, scale);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final PreserveAspectRatio preserveAspectRatio = (PreserveAspectRatio)o;
        if (this.alignment != preserveAspectRatio.alignment || this.scale != preserveAspectRatio.scale) {
            b = false;
        }
        return b;
    }
    
    public Alignment getAlignment() {
        return this.alignment;
    }
    
    public Scale getScale() {
        return this.scale;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append((Object)this.alignment);
        sb.append(" ");
        sb.append((Object)this.scale);
        return sb.toString();
    }
    
    public enum Alignment
    {
        private static final Alignment[] $VALUES;
        
        none, 
        xMaxYMax, 
        xMaxYMid, 
        xMaxYMin, 
        xMidYMax, 
        xMidYMid, 
        xMidYMin, 
        xMinYMax, 
        xMinYMid, 
        xMinYMin;
    }
    
    public enum Scale
    {
        private static final Scale[] $VALUES;
        
        meet, 
        slice;
    }
}
