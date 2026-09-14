package com.luck.picture.lib.utils;

import android.os.Environment;
import android.content.Context;
import java.util.HashMap;

public final class FileDirMap
{
    private static final HashMap<Integer, String> dirMap;
    
    static {
        dirMap = new HashMap();
    }
    
    public static void clear() {
        FileDirMap.dirMap.clear();
    }
    
    public static String getFileDirPath(final Context context, final int n) {
        String s;
        if ((s = (String)FileDirMap.dirMap.get((Object)n)) == null) {
            init(context);
            s = (String)FileDirMap.dirMap.get((Object)n);
        }
        return s;
    }
    
    public static void init(final Context context) {
        if (!ActivityCompatHelper.assertValidRequest(context)) {
            return;
        }
        if (FileDirMap.dirMap.get((Object)1) == null) {
            FileDirMap.dirMap.put((Object)1, (Object)context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
        }
        if (FileDirMap.dirMap.get((Object)2) == null) {
            FileDirMap.dirMap.put((Object)2, (Object)context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath());
        }
        if (FileDirMap.dirMap.get((Object)3) == null) {
            FileDirMap.dirMap.put((Object)3, (Object)context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath());
        }
    }
}
