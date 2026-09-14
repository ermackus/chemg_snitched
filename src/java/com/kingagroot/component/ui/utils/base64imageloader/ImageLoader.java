package com.kingagroot.component.ui.utils.base64imageloader;

import com.kingagroot.component.ui.utils.GBitmapUtils;
import android.os.Message;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import android.os.Handler;
import android.graphics.Bitmap;

public class ImageLoader
{
    private static final int MSG_WHAT_ON_SUCCESS = 1;
    String base64String;
    Bitmap bitmap;
    private final Handler handler;
    OnCallBackListenre onCallBackListenre;
    String tag;
    Executor taskExecutor;
    private final WeakReference<ImageView> viewWeakReference;
    
    public ImageLoader(final String tag, final String base64String, final ImageView imageView, final Executor taskExecutor) {
        this.handler = new Handler() {
            final ImageLoader this$0;
            
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    this.this$0.onCallBackListenre.onResult((ImageCache)message.obj);
                    if (this.this$0.bitmap != null) {
                        ((ImageView)this.this$0.viewWeakReference.get()).setImageBitmap(this.this$0.bitmap);
                    }
                }
            }
        };
        this.tag = tag;
        this.base64String = base64String;
        this.taskExecutor = taskExecutor;
        this.viewWeakReference = (WeakReference<ImageView>)new WeakReference((Object)imageView);
    }
    
    public void setOnCallBackListenre(final OnCallBackListenre onCallBackListenre) {
        this.onCallBackListenre = onCallBackListenre;
    }
    
    public void start() {
        this.taskExecutor.execute((Runnable)new Runnable(this) {
            final ImageLoader this$0;
            
            public void run() {
                final ImageLoader this$0 = this.this$0;
                this$0.bitmap = GBitmapUtils.stringtoBitmap(this$0.base64String);
                final ImageCache imageCache = new ImageCache();
                imageCache.setTag(this.this$0.tag);
                imageCache.setBaseStr(this.this$0.base64String);
                imageCache.setBitmap(this.this$0.bitmap);
                this.this$0.handler.obtainMessage(1, (Object)imageCache).sendToTarget();
            }
        });
    }
    
    public interface OnCallBackListenre
    {
        void onResult(final ImageCache p0);
    }
}
