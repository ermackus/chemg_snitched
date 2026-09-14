package org.ccil.cowan.tagsoup;

import org.xml.sax.Attributes;

public class AttributesImpl implements Attributes
{
    String[] data;
    int length;
    
    public AttributesImpl() {
        this.length = 0;
        this.data = null;
    }
    
    public AttributesImpl(final Attributes attributes) {
        this.setAttributes(attributes);
    }
    
    private void badIndex(final int n) throws ArrayIndexOutOfBoundsException {
        final StringBuffer sb = new StringBuffer();
        sb.append("Attempt to modify attribute at illegal index: ");
        sb.append(n);
        throw new ArrayIndexOutOfBoundsException(sb.toString());
    }
    
    private void ensureCapacity(int length) {
        if (length <= 0) {
            return;
        }
        final String[] data = this.data;
        int i;
        if (data != null && data.length != 0) {
            if (data.length >= length * 5) {
                return;
            }
            i = data.length;
        }
        else {
            i = 25;
        }
        while (i < length * 5) {
            i *= 2;
        }
        final String[] data2 = new String[i];
        length = this.length;
        if (length > 0) {
            System.arraycopy((Object)this.data, 0, (Object)data2, 0, length * 5);
        }
        this.data = data2;
    }
    
    public void addAttribute(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.ensureCapacity(this.length + 1);
        final String[] data = this.data;
        final int length = this.length;
        data[length * 5] = s;
        data[length * 5 + 1] = s2;
        data[length * 5 + 2] = s3;
        data[length * 5 + 3] = s4;
        data[length * 5 + 4] = s5;
        this.length = length + 1;
    }
    
    public void clear() {
        if (this.data != null) {
            for (int i = 0; i < this.length * 5; ++i) {
                this.data[i] = null;
            }
        }
        this.length = 0;
    }
    
    public int getIndex(final String s) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i + 2].equals((Object)s)) {
                return i / 5;
            }
        }
        return -1;
    }
    
    public int getIndex(final String s, final String s2) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i].equals((Object)s) && this.data[i + 1].equals((Object)s2)) {
                return i / 5;
            }
        }
        return -1;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public String getLocalName(final int n) {
        if (n >= 0 && n < this.length) {
            return this.data[n * 5 + 1];
        }
        return null;
    }
    
    public String getQName(final int n) {
        if (n >= 0 && n < this.length) {
            return this.data[n * 5 + 2];
        }
        return null;
    }
    
    public String getType(final int n) {
        if (n >= 0 && n < this.length) {
            return this.data[n * 5 + 3];
        }
        return null;
    }
    
    public String getType(final String s) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i + 2].equals((Object)s)) {
                return this.data[i + 3];
            }
        }
        return null;
    }
    
    public String getType(final String s, final String s2) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i].equals((Object)s) && this.data[i + 1].equals((Object)s2)) {
                return this.data[i + 3];
            }
        }
        return null;
    }
    
    public String getURI(final int n) {
        if (n >= 0 && n < this.length) {
            return this.data[n * 5];
        }
        return null;
    }
    
    public String getValue(final int n) {
        if (n >= 0 && n < this.length) {
            return this.data[n * 5 + 4];
        }
        return null;
    }
    
    public String getValue(final String s) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i + 2].equals((Object)s)) {
                return this.data[i + 4];
            }
        }
        return null;
    }
    
    public String getValue(final String s, final String s2) {
        for (int length = this.length, i = 0; i < length * 5; i += 5) {
            if (this.data[i].equals((Object)s) && this.data[i + 1].equals((Object)s2)) {
                return this.data[i + 4];
            }
        }
        return null;
    }
    
    public void removeAttribute(int length) {
        if (length >= 0) {
            final int length2 = this.length;
            if (length < length2) {
                if (length < length2 - 1) {
                    final String[] data = this.data;
                    System.arraycopy((Object)data, (length + 1) * 5, (Object)data, length * 5, (length2 - length - 1) * 5);
                }
                length = this.length;
                final int n = (length - 1) * 5;
                final String[] data2 = this.data;
                final int n2 = n + 1;
                data2[n] = null;
                final int n3 = n2 + 1;
                data2[n2] = null;
                final int n4 = n3 + 1;
                data2[n3] = null;
                data2[n4 + 1] = (data2[n4] = null);
                this.length = length - 1;
                return;
            }
        }
        this.badIndex(length);
    }
    
    public void setAttribute(int n, final String s, final String s2, final String s3, final String s4, final String s5) {
        if (n >= 0 && n < this.length) {
            final String[] data = this.data;
            n *= 5;
            data[n] = s;
            data[n + 1] = s2;
            data[n + 2] = s3;
            data[n + 3] = s4;
            data[n + 4] = s5;
        }
        else {
            this.badIndex(n);
        }
    }
    
    public void setAttributes(final Attributes attributes) {
        this.clear();
        final int length = attributes.getLength();
        this.length = length;
        if (length > 0) {
            this.data = new String[length * 5];
            for (int i = 0; i < this.length; ++i) {
                final String[] data = this.data;
                final int n = i * 5;
                data[n] = attributes.getURI(i);
                this.data[n + 1] = attributes.getLocalName(i);
                this.data[n + 2] = attributes.getQName(i);
                this.data[n + 3] = attributes.getType(i);
                this.data[n + 4] = attributes.getValue(i);
            }
        }
    }
    
    public void setLocalName(final int n, final String s) {
        if (n >= 0 && n < this.length) {
            this.data[n * 5 + 1] = s;
        }
        else {
            this.badIndex(n);
        }
    }
    
    public void setQName(final int n, final String s) {
        if (n >= 0 && n < this.length) {
            this.data[n * 5 + 2] = s;
        }
        else {
            this.badIndex(n);
        }
    }
    
    public void setType(final int n, final String s) {
        if (n >= 0 && n < this.length) {
            this.data[n * 5 + 3] = s;
        }
        else {
            this.badIndex(n);
        }
    }
    
    public void setURI(final int n, final String s) {
        if (n >= 0 && n < this.length) {
            this.data[n * 5] = s;
        }
        else {
            this.badIndex(n);
        }
    }
    
    public void setValue(final int n, final String s) {
        if (n >= 0 && n < this.length) {
            this.data[n * 5 + 4] = s;
        }
        else {
            this.badIndex(n);
        }
    }
}
