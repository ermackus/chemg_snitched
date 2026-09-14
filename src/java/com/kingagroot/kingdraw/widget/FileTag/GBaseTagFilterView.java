package com.kingagroot.kingdraw.widget.FileTag;

import java.util.List;

public interface GBaseTagFilterView
{
    List<String> getTags();
    
    void refreshData();
    
    void removeTag(final String p0);
    
    void setTags(final List<String> p0);
}
