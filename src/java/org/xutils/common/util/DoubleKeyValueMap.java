package org.xutils.common.util;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class DoubleKeyValueMap<K1, K2, V>
{
    private final ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>> k1_k2V_map;
    
    public DoubleKeyValueMap() {
        this.k1_k2V_map = (ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>>)new ConcurrentHashMap();
    }
    
    public void clear() {
        if (this.k1_k2V_map.size() > 0) {
            final Iterator iterator = this.k1_k2V_map.values().iterator();
            while (iterator.hasNext()) {
                ((ConcurrentHashMap)iterator.next()).clear();
            }
            this.k1_k2V_map.clear();
        }
    }
    
    public boolean containsKey(final K1 k1) {
        return this.k1_k2V_map.containsKey((Object)k1);
    }
    
    public boolean containsKey(final K1 k1, final K2 k2) {
        return this.k1_k2V_map.containsKey((Object)k1) && ((ConcurrentHashMap)this.k1_k2V_map.get((Object)k1)).containsKey((Object)k2);
    }
    
    public V get(final K1 k1, final K2 k2) {
        final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)this.k1_k2V_map.get((Object)k1);
        Object value;
        if (concurrentHashMap == null) {
            value = null;
        }
        else {
            value = concurrentHashMap.get((Object)k2);
        }
        return (V)value;
    }
    
    public ConcurrentHashMap<K2, V> get(final K1 k1) {
        return (ConcurrentHashMap<K2, V>)this.k1_k2V_map.get((Object)k1);
    }
    
    public Collection<V> getAllValues() {
        final Set keySet = this.k1_k2V_map.keySet();
        Collection<V> collection;
        if (keySet != null) {
            final ArrayList list = new ArrayList();
            final Iterator iterator = keySet.iterator();
            while (true) {
                collection = (Collection<V>)list;
                if (!iterator.hasNext()) {
                    break;
                }
                final Collection values = ((ConcurrentHashMap)this.k1_k2V_map.get(iterator.next())).values();
                if (values == null) {
                    continue;
                }
                ((Collection)list).addAll(values);
            }
        }
        else {
            collection = null;
        }
        return collection;
    }
    
    public Collection<V> getAllValues(final K1 k1) {
        final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)this.k1_k2V_map.get((Object)k1);
        Collection values;
        if (concurrentHashMap == null) {
            values = null;
        }
        else {
            values = concurrentHashMap.values();
        }
        return (Collection<V>)values;
    }
    
    public Set<K1> getFirstKeys() {
        return (Set<K1>)this.k1_k2V_map.keySet();
    }
    
    public void put(final K1 k1, final K2 k2, final V v) {
        if (k1 != null && k2 != null) {
            if (v != null) {
                if (this.k1_k2V_map.containsKey((Object)k1)) {
                    final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)this.k1_k2V_map.get((Object)k1);
                    if (concurrentHashMap != null) {
                        concurrentHashMap.put((Object)k2, (Object)v);
                    }
                    else {
                        final ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
                        concurrentHashMap2.put((Object)k2, (Object)v);
                        this.k1_k2V_map.put((Object)k1, (Object)concurrentHashMap2);
                    }
                }
                else {
                    final ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap();
                    concurrentHashMap3.put((Object)k2, (Object)v);
                    this.k1_k2V_map.put((Object)k1, (Object)concurrentHashMap3);
                }
            }
        }
    }
    
    public void remove(final K1 k1) {
        this.k1_k2V_map.remove((Object)k1);
    }
    
    public void remove(final K1 k1, final K2 k2) {
        final ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap)this.k1_k2V_map.get((Object)k1);
        if (concurrentHashMap != null) {
            concurrentHashMap.remove((Object)k2);
        }
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            this.k1_k2V_map.remove((Object)k1);
        }
    }
    
    public int size() {
        final int size = this.k1_k2V_map.size();
        int n = 0;
        if (size == 0) {
            return 0;
        }
        final Iterator iterator = this.k1_k2V_map.values().iterator();
        while (iterator.hasNext()) {
            n += ((ConcurrentHashMap)iterator.next()).size();
        }
        return n;
    }
}
