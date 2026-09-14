package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.ArrayList;

public final class al extends k implements Cloneable
{
    private static ArrayList<ak> b;
    public ArrayList<ak> a;
    
    public al() {
        this.a = null;
    }
    
    public final void a(final i i) {
        if (al.b == null) {
            (al.b = (ArrayList<ak>)new ArrayList()).add((Object)new ak());
        }
        this.a = (ArrayList<ak>)i.a((Object)al.b, 0, true);
    }
    
    public final void a(final j j) {
        j.a((Collection)this.a, 0);
    }
    
    public final void a(final StringBuilder sb, final int n) {
    }
}
