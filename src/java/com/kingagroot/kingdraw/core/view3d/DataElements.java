package com.kingagroot.kingdraw.core.view3d;

import java.util.Collection;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import java.util.List;

public class DataElements
{
    static List<KdFragment> kdFragments;
    
    static {
        DataElements.kdFragments = (List<KdFragment>)new ArrayList();
    }
    
    public static void addElements(final List<KdFragment> list) {
        DataElements.kdFragments.clear();
        if (list != null) {
            DataElements.kdFragments.addAll((Collection)list);
        }
    }
    
    public static void cleanElements() {
        DataElements.kdFragments.clear();
    }
    
    public static List<KdFragment> getAllElements() {
        return DataElements.kdFragments;
    }
}
