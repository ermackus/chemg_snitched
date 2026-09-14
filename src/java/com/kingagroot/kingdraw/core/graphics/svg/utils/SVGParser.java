package com.kingagroot.kingdraw.core.graphics.svg.utils;

import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;
import com.kingagroot.kingdraw.core.graphics.svg.SVGParseException;
import java.io.InputStream;

interface SVGParser
{
    SVGBase parseStream(final InputStream p0) throws SVGParseException;
    
    SVGParser setExternalFileResolver(final SVGExternalFileResolver p0);
    
    SVGParser setInternalEntitiesEnabled(final boolean p0);
}
