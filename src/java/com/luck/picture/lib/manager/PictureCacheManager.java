package com.luck.picture.lib.manager;

import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.thread.PictureThreadUtils;
import com.luck.picture.lib.basic.PictureMediaScannerConnection;
import java.io.File;
import android.os.Environment;
import com.luck.picture.lib.interfaces.OnCallbackListener;
import android.content.Context;

public class PictureCacheManager
{
    public static void deleteAllCacheDirFile(final Context context) {
        deleteAllCacheDirFile(context, false, null);
    }
    
    public static void deleteAllCacheDirFile(final Context context, final OnCallbackListener<String> onCallbackListener) {
        deleteAllCacheDirFile(context, false, onCallbackListener);
    }
    
    private static void deleteAllCacheDirFile(final Context context, final boolean b, final OnCallbackListener<String> onCallbackListener) {
        final File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        final int n = 0;
        if (externalFilesDir != null) {
            final File[] listFiles = externalFilesDir.listFiles();
            if (listFiles != null) {
                for (final File file : listFiles) {
                    if (file.isFile() && file.delete()) {
                        if (b) {
                            PictureThreadUtils.runOnUiThread((Runnable)new Runnable(context, file) {
                                final Context val$context;
                                final File val$file;
                                
                                public void run() {
                                    new PictureMediaScannerConnection(this.val$context, this.val$file.getAbsolutePath());
                                }
                            });
                        }
                        else if (onCallbackListener != null) {
                            onCallbackListener.onCall(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
        final File externalFilesDir2 = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        if (externalFilesDir2 != null) {
            final File[] listFiles2 = externalFilesDir2.listFiles();
            if (listFiles2 != null) {
                for (final File file2 : listFiles2) {
                    if (file2.isFile() && file2.delete()) {
                        if (b) {
                            PictureThreadUtils.runOnUiThread((Runnable)new Runnable(context, file2) {
                                final Context val$context;
                                final File val$file;
                                
                                public void run() {
                                    new PictureMediaScannerConnection(this.val$context, this.val$file.getAbsolutePath());
                                }
                            });
                        }
                        else if (onCallbackListener != null) {
                            onCallbackListener.onCall(file2.getAbsolutePath());
                        }
                    }
                }
            }
        }
        final File externalFilesDir3 = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (externalFilesDir3 != null) {
            final File[] listFiles3 = externalFilesDir3.listFiles();
            if (listFiles3 != null) {
                for (int length3 = listFiles3.length, k = n; k < length3; ++k) {
                    final File file3 = listFiles3[k];
                    if (file3.isFile() && file3.delete()) {
                        if (b) {
                            PictureThreadUtils.runOnUiThread((Runnable)new Runnable(context, file3) {
                                final Context val$context;
                                final File val$file;
                                
                                public void run() {
                                    new PictureMediaScannerConnection(this.val$context, this.val$file.getAbsolutePath());
                                }
                            });
                        }
                        else if (onCallbackListener != null) {
                            onCallbackListener.onCall(file3.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
    
    public static void deleteAllCacheDirRefreshFile(final Context context) {
        deleteAllCacheDirFile(context, true, null);
    }
    
    public static void deleteCacheDirFile(final Context context, final int n) {
        deleteCacheDirFile(context, n, false, null);
    }
    
    public static void deleteCacheDirFile(final Context context, final int n, final OnCallbackListener<String> onCallbackListener) {
        deleteCacheDirFile(context, n, false, onCallbackListener);
    }
    
    private static void deleteCacheDirFile(final Context context, int i, final boolean b, final OnCallbackListener<String> onCallbackListener) {
        String s;
        if (i == SelectMimeType.ofImage()) {
            s = Environment.DIRECTORY_PICTURES;
        }
        else {
            s = Environment.DIRECTORY_MOVIES;
        }
        final File externalFilesDir = context.getExternalFilesDir(s);
        if (externalFilesDir != null) {
            final File[] listFiles = externalFilesDir.listFiles();
            if (listFiles != null) {
                int length;
                File file;
                for (length = listFiles.length, i = 0; i < length; ++i) {
                    file = listFiles[i];
                    if (file.isFile() && file.delete()) {
                        if (b) {
                            PictureThreadUtils.runOnUiThread((Runnable)new Runnable(context, file) {
                                final Context val$context;
                                final File val$file;
                                
                                public void run() {
                                    new PictureMediaScannerConnection(this.val$context, this.val$file.getAbsolutePath());
                                }
                            });
                        }
                        else if (onCallbackListener != null) {
                            onCallbackListener.onCall(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
    
    public static void deleteCacheDirFile(final String s) {
        deleteCacheDirFile(s, null);
    }
    
    public static void deleteCacheDirFile(final String s, final OnCallbackListener<String> onCallbackListener) {
        final File[] listFiles = new File(s).listFiles();
        if (listFiles != null) {
            for (final File file : listFiles) {
                if (file.isFile() && file.delete() && onCallbackListener != null) {
                    onCallbackListener.onCall(file.getAbsolutePath());
                }
            }
        }
    }
    
    public static void deleteCacheRefreshDirFile(final Context context, final int n) {
        deleteCacheDirFile(context, n, true, null);
    }
}
