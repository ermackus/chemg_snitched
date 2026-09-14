package com.kingagroot.kingdraw.interfaces;

import com.kingagroot.kingdraw.model.SearchHisModel;
import java.util.List;
import com.kingagroot.kingdraw.model.SearchTypeEnum;

public interface SearchHisDBI
{
    boolean addHis(final String p0, final SearchTypeEnum p1);
    
    boolean clear(final SearchTypeEnum p0);
    
    boolean delete(final int p0);
    
    List<SearchHisModel> getList(final SearchTypeEnum p0);
}
