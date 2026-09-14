package com.yalantis.ucrop;

public interface UCropFragmentCallback
{
    void loadingProgress(final boolean p0);
    
    void onCropFinish(final UCropFragment.UCropResult p0);
}
