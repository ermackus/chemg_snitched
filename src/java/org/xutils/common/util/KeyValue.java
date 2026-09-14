package org.xutils.common.util;

public class KeyValue
{
    public final String key;
    public final Object value;
    
    public KeyValue(final String key, final Object value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean equals = true;
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final KeyValue keyValue = (KeyValue)o;
            final String key = this.key;
            final String key2 = keyValue.key;
            if (key == null) {
                if (key2 != null) {
                    equals = false;
                }
            }
            else {
                equals = key.equals((Object)key2);
            }
            return equals;
        }
        return false;
    }
    
    public String getValueStr() {
        final Object value = this.value;
        String string;
        if (value == null) {
            string = null;
        }
        else {
            string = value.toString();
        }
        return string;
    }
    
    @Override
    public int hashCode() {
        final String key = this.key;
        int hashCode;
        if (key != null) {
            hashCode = key.hashCode();
        }
        else {
            hashCode = 0;
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("KeyValue{key='");
        sb.append(this.key);
        sb.append('\'');
        sb.append(", value=");
        sb.append(this.value);
        sb.append('}');
        return sb.toString();
    }
}
