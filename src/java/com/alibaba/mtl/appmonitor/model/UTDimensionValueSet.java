package com.alibaba.mtl.appmonitor.model;

import com.alibaba.mtl.appmonitor.c.a;
import java.util.Map;
import com.alibaba.mtl.log.model.LogField;
import java.util.Set;

public class UTDimensionValueSet extends DimensionValueSet
{
    private static final Set<LogField> a;
    
    static {
        a = (Set)new UTDimensionValueSet$1();
    }
    
    public static UTDimensionValueSet create(final Map<String, String> map) {
        return (UTDimensionValueSet)com.alibaba.mtl.appmonitor.c.a.a().a((Class)UTDimensionValueSet.class, new Object[] { map });
    }
    
    @Override
    public void clean() {
        super.clean();
    }
    
    @Override
    public void fill(final Object... array) {
        super.fill(array);
    }
    
    public Integer getEventId() {
        while (true) {
            if (this.map == null) {
                break Label_0038;
            }
            final String s = (String)this.map.get((Object)LogField.EVENTID.toString());
            if (s == null) {
                break Label_0038;
            }
            try {
                return com.alibaba.mtl.appmonitor.f.a.a(s);
                a = 0;
                return a;
            }
            catch (final NumberFormatException ex) {
                continue;
            }
            break;
        }
    }
}
