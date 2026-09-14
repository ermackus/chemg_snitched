package com.luck.picture.lib.entity;

import java.util.ArrayList;

public class MediaData
{
    public ArrayList<LocalMedia> data;
    public boolean isHasNextMore;
    
    public MediaData() {
    }
    
    public MediaData(final boolean isHasNextMore, final ArrayList<LocalMedia> data) {
        this.isHasNextMore = isHasNextMore;
        this.data = data;
    }
}
