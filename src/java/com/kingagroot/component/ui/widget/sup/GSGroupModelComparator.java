package com.kingagroot.component.ui.widget.sup;

import com.kingagroot.component.ui.model.GSGroupModel;
import java.util.Comparator;

public class GSGroupModelComparator implements Comparator<GSGroupModel>
{
    public int compare(final GSGroupModel gsGroupModel, final GSGroupModel gsGroupModel2) {
        if (gsGroupModel.getIsCollection() == 1 && gsGroupModel2.getIsCollection() != 1) {
            return -1;
        }
        if (gsGroupModel.getIsCollection() != 1 && gsGroupModel2.getIsCollection() == 1) {
            return 1;
        }
        return 0;
    }
}
