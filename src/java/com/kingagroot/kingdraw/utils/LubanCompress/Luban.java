package com.kingagroot.kingdraw.utils.LubanCompress;

import android.os.Message;
import android.text.TextUtils;
import java.io.IOException;
import java.io.File;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;
import android.os.Handler$Callback;

public class Luban implements Handler$Callback
{
    private static final String DEFAULT_DISK_CACHE_DIR = "imagecompress";
    private static final int MSG_COMPRESS_ERROR = 2;
    private static final int MSG_COMPRESS_START = 1;
    private static final int MSG_COMPRESS_SUCCESS = 0;
    private final String filePath;
    private final Handler mHandler;
    private final OnCompressListener onCompressListener;
    
    private Luban(final Builder builder) {
        this.filePath = builder.filePath;
        this.onCompressListener = builder.onCompressListener;
        this.mHandler = new Handler(Looper.getMainLooper(), (Handler$Callback)this);
    }
    
    private File get(final Context context) throws IOException {
        return new Engine(this.filePath, this.getImageCacheFile(context)).compress();
    }
    
    private File getGrey(final Context context) throws IOException {
        return new Engine(this.filePath, this.getImageCacheFile(context)).compressAndGray();
    }
    
    private File getImageCacheDir(final Context context) {
        return this.getImageCacheDir(context, "imagecompress");
    }
    
    private File getImageCacheDir(final Context context, final String s) {
        final File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            return null;
        }
        final File file = new File(externalCacheDir, s);
        if (!file.mkdirs() && (!file.exists() || !file.isDirectory())) {
            return null;
        }
        return file;
    }
    
    private File getImageCacheFile(final Context context) {
        if (this.getImageCacheDir(context) != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append((Object)this.getImageCacheDir(context));
            sb.append("/");
            sb.append(System.currentTimeMillis());
            sb.append((int)(Math.random() * 1000.0));
            sb.append(".jpg");
            return new File(sb.toString());
        }
        return null;
    }
    
    private File getWithBackgound(final int n, final Context context) throws IOException {
        return new Engine(this.filePath, this.getImageCacheFile(context)).compressWithBackground(n);
    }
    
    private void launch(final Context context) {
        if (TextUtils.isEmpty((CharSequence)this.filePath)) {
            final OnCompressListener onCompressListener = this.onCompressListener;
            if (onCompressListener != null) {
                onCompressListener.onError((Throwable)new NullPointerException("image file cannot be null"));
            }
        }
        new Thread((Runnable)new Runnable(this, context) {
            final Luban this$0;
            final Context val$context;
            
            public void run() {
                try {
                    this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(1));
                    this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(0, (Object)new Engine(this.this$0.filePath, this.this$0.getImageCacheFile(this.val$context)).compress()));
                }
                catch (final IOException ex) {
                    this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(2, (Object)ex));
                }
            }
        }).start();
    }
    
    public static Builder with(final Context context) {
        return new Builder(context);
    }
    
    public boolean handleMessage(final Message message) {
        if (this.onCompressListener == null) {
            return false;
        }
        final int what = message.what;
        if (what != 0) {
            if (what != 1) {
                if (what == 2) {
                    this.onCompressListener.onError((Throwable)message.obj);
                }
            }
            else {
                this.onCompressListener.onStart();
            }
        }
        else {
            this.onCompressListener.onSuccess((File)message.obj);
        }
        return false;
    }
    
    public static class Builder
    {
        private final Context context;
        private String filePath;
        private OnCompressListener onCompressListener;
        
        Builder(final Context context) {
            this.context = context;
        }
        
        private Luban build() {
            return new Luban(this, null);
        }
        
        public File get() throws IOException {
            return this.build().get(this.context);
        }
        
        public File getGrey() throws IOException {
            return this.build().getGrey(this.context);
        }
        
        public File getWithBackground(final int n) throws IOException {
            return this.build().getWithBackgound(n, this.context);
        }
        
        public void launch() {
            this.build().launch(this.context);
        }
        
        public Builder load(final String filePath) {
            this.filePath = filePath;
            return this;
        }
        
        public Builder putGear(final int n) {
            return this;
        }
        
        public Builder setCompressListener(final OnCompressListener onCompressListener) {
            this.onCompressListener = onCompressListener;
            return this;
        }
    }
}
