package com.otaliastudios.cameraview.preview;

import com.otaliastudios.cameraview.filter.Filter;

public interface FilterCameraPreview
{
    Filter getCurrentFilter();
    
    void setFilter(final Filter p0);
}
