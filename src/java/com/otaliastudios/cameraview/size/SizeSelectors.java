package com.otaliastudios.cameraview.size;

public class SizeSelectors
{
    public static SizeSelector and(final SizeSelector... array) {
        return (SizeSelector)new SizeSelectors.SizeSelectors$AndSelector(array, (SizeSelectors$1)null);
    }
    
    public static SizeSelector aspectRatio(final AspectRatio aspectRatio, final float n) {
        return withFilter((Filter)new SizeSelectors$5(aspectRatio.toFloat(), n));
    }
    
    public static SizeSelector biggest() {
        return (SizeSelector)new SizeSelectors$6();
    }
    
    public static SizeSelector maxArea(final int n) {
        return withFilter((Filter)new SizeSelectors$8(n));
    }
    
    public static SizeSelector maxHeight(final int n) {
        return withFilter((Filter)new SizeSelectors$3(n));
    }
    
    public static SizeSelector maxWidth(final int n) {
        return withFilter((Filter)new SizeSelectors$1(n));
    }
    
    public static SizeSelector minArea(final int n) {
        return withFilter((Filter)new SizeSelectors$9(n));
    }
    
    public static SizeSelector minHeight(final int n) {
        return withFilter((Filter)new SizeSelectors$4(n));
    }
    
    public static SizeSelector minWidth(final int n) {
        return withFilter((Filter)new SizeSelectors$2(n));
    }
    
    public static SizeSelector or(final SizeSelector... array) {
        return (SizeSelector)new SizeSelectors.SizeSelectors$OrSelector(array, (SizeSelectors$1)null);
    }
    
    public static SizeSelector smallest() {
        return (SizeSelector)new SizeSelectors$7();
    }
    
    public static SizeSelector withFilter(final Filter filter) {
        return (SizeSelector)new SizeSelectors.SizeSelectors$FilterSelector(filter, (SizeSelectors$1)null);
    }
    
    public interface Filter
    {
        boolean accepts(final Size p0);
    }
}
