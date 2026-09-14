package com.kingagroot.kingdraw.utils.LubanCompress;

import java.io.File;

public interface OnCompressListener
{
    void onError(final Throwable p0);
    
    void onStart();
    
    void onSuccess(final File p0);
}
