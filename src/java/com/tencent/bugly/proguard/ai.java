package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.ArrayList;

public final class ai extends k implements Cloneable
{
    private static ArrayList<String> c;
    private String a;
    private ArrayList<String> b;
    
    public ai() {
        this.a = "";
        this.b = null;
    }
    
    public final void a(final i i) {
        this.a = i.b(0, true);
        if (ai.c == null) {
            (ai.c = (ArrayList<String>)new ArrayList()).add((Object)"");
        }
        this.b = (ArrayList<String>)i.a((Object)ai.c, 1, false);
    }
    
    public final void a(final j j) {
        j.a(this.a, 0);
        final ArrayList<String> b = this.b;
        if (b != null) {
            j.a((Collection)b, 1);
        }
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
