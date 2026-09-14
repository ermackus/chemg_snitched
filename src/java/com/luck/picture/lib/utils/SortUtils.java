package com.luck.picture.lib.utils;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;

public class SortUtils
{
    public static void sortFolder(final List<LocalMediaFolder> list) {
        Collections.sort((List)list, (Comparator)_$$Lambda$SortUtils$1I0l490UB5vMPzvqgAdwarc5648.INSTANCE);
    }
    
    public static void sortLocalMediaAddedTime(final List<LocalMedia> list) {
        Collections.sort((List)list, (Comparator)_$$Lambda$SortUtils$MUb6Ta42_g8hBOWtfhxD147QVcs.INSTANCE);
    }
}
