package org.xutils.view;

final class ViewInfo
{
    public int parentId;
    public int value;
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ViewInfo viewInfo = (ViewInfo)o;
        if (this.value != viewInfo.value) {
            return false;
        }
        if (this.parentId != viewInfo.parentId) {
            b = false;
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return this.value * 31 + this.parentId;
    }
}
