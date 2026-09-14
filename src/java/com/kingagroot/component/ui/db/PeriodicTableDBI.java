package com.kingagroot.component.ui.db;

import java.util.List;
import com.kingagroot.component.ui.model.GAtom;

public interface PeriodicTableDBI
{
    GAtom findAtomByName(final String p0);
    
    List<GAtom> findPeriodicTable(final String p0);
    
    List<GAtom> getCollectPeriodicTable();
    
    boolean setCollect(final int p0, final boolean p1);
}
