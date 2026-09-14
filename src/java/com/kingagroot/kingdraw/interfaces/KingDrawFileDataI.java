package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.GTagModel;
import java.util.List;

public interface KingDrawFileDataI
{
    List<Long> getDrawFileCountByTags(final List<GTagModel> p0);
}
