package com.luck.picture.lib.basic;

public interface IPictureSelectorEvent
{
    void loadAllAlbumData();
    
    void loadFirstPageMediaData(final long p0);
    
    void loadMoreMediaData();
    
    void loadOnlyInAppDirectoryAllMediaData();
}
