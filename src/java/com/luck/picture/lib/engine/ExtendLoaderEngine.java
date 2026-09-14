package com.luck.picture.lib.engine;

import com.luck.picture.lib.interfaces.OnQueryAlbumListener;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnQueryDataResultListener;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.interfaces.OnQueryAllAlbumListener;
import android.content.Context;

@Deprecated
public interface ExtendLoaderEngine
{
    void loadAllAlbumData(final Context p0, final OnQueryAllAlbumListener<LocalMediaFolder> p1);
    
    void loadFirstPageMediaData(final Context p0, final long p1, final int p2, final int p3, final OnQueryDataResultListener<LocalMedia> p4);
    
    void loadMoreMediaData(final Context p0, final long p1, final int p2, final int p3, final int p4, final OnQueryDataResultListener<LocalMedia> p5);
    
    void loadOnlyInAppDirAllMediaData(final Context p0, final OnQueryAlbumListener<LocalMediaFolder> p1);
}
