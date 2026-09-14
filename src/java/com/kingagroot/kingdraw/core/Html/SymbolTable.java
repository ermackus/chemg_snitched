package com.kingagroot.kingdraw.core.Html;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.HashMap;

public class SymbolTable
{
    private static final HashMap<Character, Integer> table;
    
    static {
        table = new HashMap<Character, Integer>() {
            {
                this.put((Object)'A', (Object)913);
                this.put((Object)'B', (Object)914);
                this.put((Object)'C', (Object)935);
                this.put((Object)'D', (Object)916);
                this.put((Object)'E', (Object)917);
                this.put((Object)'F', (Object)934);
                this.put((Object)'G', (Object)915);
                this.put((Object)'H', (Object)919);
                this.put((Object)'I', (Object)921);
                this.put((Object)'J', (Object)977);
                this.put((Object)'K', (Object)922);
                this.put((Object)'L', (Object)923);
                this.put((Object)'M', (Object)924);
                this.put((Object)'N', (Object)925);
                this.put((Object)'O', (Object)927);
                this.put((Object)'P', (Object)928);
                this.put((Object)'Q', (Object)920);
                this.put((Object)'R', (Object)929);
                this.put((Object)'S', (Object)931);
                this.put((Object)'T', (Object)932);
                this.put((Object)'U', (Object)933);
                this.put((Object)'V', (Object)962);
                this.put((Object)'W', (Object)937);
                this.put((Object)'X', (Object)926);
                this.put((Object)'Y', (Object)936);
                this.put((Object)'Z', (Object)918);
                this.put((Object)'a', (Object)945);
                this.put((Object)'b', (Object)946);
                this.put((Object)'c', (Object)967);
                this.put((Object)'d', (Object)948);
                this.put((Object)'e', (Object)949);
                this.put((Object)'f', (Object)981);
                this.put((Object)'g', (Object)947);
                this.put((Object)'h', (Object)951);
                this.put((Object)'i', (Object)953);
                this.put((Object)'j', (Object)966);
                this.put((Object)'k', (Object)954);
                this.put((Object)'l', (Object)955);
                this.put((Object)'m', (Object)956);
                this.put((Object)'n', (Object)957);
                this.put((Object)'o', (Object)959);
                this.put((Object)'p', (Object)960);
                this.put((Object)'q', (Object)952);
                this.put((Object)'r', (Object)961);
                this.put((Object)'s', (Object)963);
                this.put((Object)'t', (Object)964);
                this.put((Object)'u', (Object)965);
                this.put((Object)'v', (Object)982);
                this.put((Object)'w', (Object)969);
                this.put((Object)'x', (Object)958);
                this.put((Object)'y', (Object)968);
                this.put((Object)'z', (Object)950);
                this.put((Object)'@', (Object)8773);
                this.put((Object)'$', (Object)8707);
                this.put((Object)'^', (Object)8869);
                this.put((Object)'\'', (Object)604);
                this.put((Object)'\"', (Object)8704);
            }
        };
    }
    
    public static String converToNormal(final char c) {
        if (SymbolTable.table != null) {
            final int codePoint = Character.codePointAt(new char[] { c }, 0);
            for (final Map$Entry map$Entry : SymbolTable.table.entrySet()) {
                if (codePoint == (int)map$Entry.getValue()) {
                    return ((Character)map$Entry.getKey()).toString();
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append("");
        return sb.toString();
    }
    
    public static String converToNormal(String string) {
        final char[] charArray = string.toCharArray();
        String s;
        string = (s = "");
        if (charArray != null) {
            final int length = charArray.length;
            int n = 0;
            while (true) {
                s = string;
                if (n >= length) {
                    break;
                }
                final char c = charArray[n];
                final StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(converToNormal(c));
                string = sb.toString();
                ++n;
            }
        }
        return s;
    }
    
    public static String convertToSymbol(final char c) {
        if (SymbolTable.table.containsKey((Object)c)) {
            return new String(Character.toChars((int)SymbolTable.table.get((Object)c)));
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append("");
        return sb.toString();
    }
    
    public static String convertToSymbol(String string) {
        final char[] charArray = string.toCharArray();
        String s;
        string = (s = "");
        if (charArray != null) {
            final int length = charArray.length;
            int n = 0;
            while (true) {
                s = string;
                if (n >= length) {
                    break;
                }
                final char c = charArray[n];
                final StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(convertToSymbol(c));
                string = sb.toString();
                ++n;
            }
        }
        return s;
    }
}
