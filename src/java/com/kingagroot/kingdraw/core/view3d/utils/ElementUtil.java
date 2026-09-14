package com.kingagroot.kingdraw.core.view3d.utils;

import com.kingagroot.kingdraw.core.view3d.element.KdChemElement;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdElement;
import java.util.List;

public class ElementUtil
{
    private static List<List<KdElement>> distinguishFormula(final List<KdElement> list) {
        final ArrayList list2 = new ArrayList();
        final ArrayList list3 = new ArrayList((Collection)list);
        if (list.size() > 0) {
            for (final KdElement kdElement : list) {
                final ArrayList list4 = new ArrayList();
                if (((List)list3).contains((Object)kdElement)) {
                    ((List)list4).addAll((Collection)getMolecule(kdElement, (List<KdElement>)list3));
                    ((List)list2).add((Object)list4);
                }
            }
        }
        return (List<List<KdElement>>)list2;
    }
    
    public static List<KdFragment> elementsInFragments(final List<KdElement> list) {
        final List<List<KdElement>> distinguishFormula = distinguishFormula(list);
        final ArrayList list2 = new ArrayList();
        for (final List list3 : distinguishFormula) {
            final KdFragment kdFragment = new KdFragment();
            kdFragment.addChildElements(list3);
            ((List)list2).add((Object)kdFragment);
        }
        return (List<KdFragment>)list2;
    }
    
    private static List<KdElement> getMolecule(final KdElement kdElement, final List<KdElement> list) {
        final ArrayList list2 = new ArrayList();
        if (list.contains((Object)kdElement)) {
            ((List)list2).add((Object)kdElement);
            list.remove((Object)kdElement);
            if (kdElement instanceof KdChemElement) {
                final Iterator iterator = ((KdChemElement)kdElement).getRelationElements().iterator();
                while (iterator.hasNext()) {
                    ((List)list2).addAll((Collection)getMolecule((KdElement)iterator.next(), list));
                }
            }
        }
        return (List<KdElement>)list2;
    }
}
