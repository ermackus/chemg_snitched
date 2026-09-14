package com.kingagroot.component.ui.db;

import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.model.GFormatValue;
import java.util.List;

public interface GFormatValueDBI
{
    boolean initFormatData();
    
    List<GFormatValue> readAllFormat();
    
    GFormatValue readNormalFormat();
    
    GFormatValue readerFormatForType(final GDocumentTypeEnum p0);
    
    boolean setNormalFormat(final GDocumentTypeEnum p0);
    
    boolean updateFormat(final GFormatValue p0);
}
