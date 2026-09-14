package com.tencent.open.b;

import java.util.Map;
import java.util.Iterator;
import android.os.Bundle;
import java.util.HashMap;
import java.io.Serializable;

public class c implements Serializable
{
    public final HashMap<String, String> a;
    
    public c(final Bundle bundle) {
        this.a = (HashMap<String, String>)new HashMap();
        if (bundle != null) {
            for (final String s : bundle.keySet()) {
                this.a.put((Object)s, (Object)bundle.getString(s));
            }
        }
    }
    
    public c(final HashMap<String, String> hashMap) {
        this.a = (HashMap<String, String>)new HashMap((Map)hashMap);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BaseData{time=");
        sb.append((String)this.a.get((Object)"time"));
        sb.append(", name=");
        sb.append((String)this.a.get((Object)"interface_name"));
        sb.append('}');
        return sb.toString();
    }
}
